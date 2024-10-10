package com.utndds.heladerasApi.services.Colaboraciones;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utndds.heladerasApi.controllers.DTOs.DonacionViandaDTO;
import com.utndds.heladerasApi.models.Colaboraciones.DonacionVianda;
import com.utndds.heladerasApi.models.Enums.MotivoApertura;
import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Heladera.Vianda;
import com.utndds.heladerasApi.models.Rol.Colaborador;
import com.utndds.heladerasApi.repositories.ColaboradorRepository;
import com.utndds.heladerasApi.repositories.HeladeraRepository;
import com.utndds.heladerasApi.repositories.ViandaRepository;
import com.utndds.heladerasApi.repositories.ColaboracionesRepositories.DonacionViandaRepository;
import com.utndds.heladerasApi.services.AperturaService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DonacionViandaService {

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Autowired
    private HeladeraRepository heladeraRepository;

    @Autowired
    private DonacionViandaRepository donacionViandaRepository;

    @Autowired
    private ViandaRepository viandaRepository;
    @Autowired
    private AperturaService solicitudAperturaService;

    public void guardarDonacionVianda(DonacionViandaDTO donacionViandaDTO, Long colaboradorId) {

        // Buscar al colaborador
        Colaborador colaborador = colaboradorRepository.findById(colaboradorId)
                .orElseThrow(() -> new EntityNotFoundException("Colaborador no encontrado con id " + colaboradorId));

        // Buscar la heladera
        Heladera heladera = heladeraRepository.findById(donacionViandaDTO.getHeladeraId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Heladera no encontrada con id " + donacionViandaDTO.getHeladeraId()));

        // Crear la vianda con los datos del formulario
        Vianda vianda = new Vianda();
        vianda.setComida(donacionViandaDTO.getComida());
        vianda.setFechaCaducidad(donacionViandaDTO.getFechaCaducidad());
        vianda.setCalorias(donacionViandaDTO.getCalorias());
        vianda.setPeso(donacionViandaDTO.getPeso());
        vianda.setEstado(donacionViandaDTO.isEstado());
        vianda.setHeladera(heladera);

        // Guardar la vianda en la base de datos
        viandaRepository.save(vianda);

        // Crear la donación de vianda
        DonacionVianda donacionVianda = new DonacionVianda(colaborador, vianda, heladera);

        donacionViandaRepository.save(donacionVianda);

        // Crear la solicitud de apertura automáticamente
        solicitudAperturaService.crearSolicitud(colaboradorId, donacionViandaDTO.getHeladeraId(),
                MotivoApertura.DONACION);
    }
}
