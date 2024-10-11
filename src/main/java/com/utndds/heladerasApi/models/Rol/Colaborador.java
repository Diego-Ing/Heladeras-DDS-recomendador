package com.utndds.heladerasApi.models.Rol;

import java.util.ArrayList;
import java.util.List;

import com.utndds.heladerasApi.models.Colaboraciones.Colaboracion;
import com.utndds.heladerasApi.models.Persona.Persona;
import com.utndds.heladerasApi.models.Suscripciones.Suscripcion;
import com.utndds.heladerasApi.models.Tarjetas.TarjetaColaborador;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "colaborador")
public class Colaborador extends Rol {

    @OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Colaboracion> colaboraciones = new ArrayList<>();

    @OneToMany(mappedBy = "colaborador", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Suscripcion> suscripciones = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "tarjeta")
    private TarjetaColaborador tarjeta;

    @Column(name = "puntos_gastados")
    private double puntosGastados;

    // Constructor vacío para JPA
    public Colaborador() {
    }

    public Colaborador(Persona persona) {
        super(persona);
        this.puntosGastados = 0;
    }

    public void sumarPuntosGastados(double puntos) {
        this.puntosGastados += puntos;
    }

}
