package com.utndds.heladerasApi.services.GeneradorReportes.Reportes;

import java.time.LocalDate;

import java.util.List;

import com.utndds.heladerasApi.models.Heladera.Heladera;
import com.utndds.heladerasApi.models.Reportes.ViandasMovidasPorHeladera;
import com.utndds.heladerasApi.repositories.HeladeraRepository;
import com.utndds.heladerasApi.repositories.reportesRepositories.ReporteViandasMovidasHeladeraRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReporteViandasMovidasPorHeladeraService implements Reporte {

    @Autowired
    private HeladeraRepository heladeraRepository;

    @Autowired
    private ReporteViandasMovidasHeladeraRepository reporteViandasHeladeraRepository; // Repositorio para guardar los
                                                                                      // reportes

    @Override
    public void generar() {
        List<Heladera> heladeras = heladeraRepository.findAll();
        for (Heladera heladera : heladeras) {
            int cantViandasMovidas = 0; // falta implementar

            // Crear y guardar el reporte en la base de datos
            ViandasMovidasPorHeladera reporte = new ViandasMovidasPorHeladera(LocalDate.now(), heladera.getId(),
                    cantViandasMovidas);
            reporteViandasHeladeraRepository.save(reporte);
        }
    }
}