package com.utndds.heladerasApi.models.Colaboraciones;

import com.utndds.heladerasApi.models.Rol.Colaborador;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Oferta extends Colaboracion {

    @Column(name = "rubro")
    private String rubro;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "cantidad_puntos_nec")
    private double cantidadPuntosNec;

    @Column(name = "imagen")
    private String imagen;

    // Constructor vacío para JPA
    public Oferta() {
    }

    public void canjear(Colaborador colaborador) {
        colaborador.restarPuntos(cantidadPuntosNec);
    }
}
