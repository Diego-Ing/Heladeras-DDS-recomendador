package com.utndds.heladerasApi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utndds.heladerasApi.models.Persona.PersonaHumana;
import com.utndds.heladerasApi.models.Rol.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
    Colaborador findByPersona(PersonaHumana persona);
}