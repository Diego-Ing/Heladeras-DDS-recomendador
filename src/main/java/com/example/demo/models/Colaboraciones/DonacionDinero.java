package com.example.demo.models.Colaboraciones;

import java.time.LocalDate;

import com.example.demo.models.Rol.Colaborador;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class DonacionDinero extends Colaboracion {

    @Column(name = "monto")
    private double monto;

    @Column(name = "frecuencia")
    private int frecuencia;

    // Constructor vacío para JPA
    public DonacionDinero() {
        this.fecha = LocalDate.now();
    }

    public DonacionDinero(Colaborador colaborador, double monto, int frecuencia) {
        super(colaborador);
        this.monto = monto;
        this.frecuencia = frecuencia;
    }

}
