package com.example.demo.models.Heladera.Incidentes;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.example.demo.models.Heladera.Incidentes.Incidente.Incidente;
import com.example.demo.models.Rol.Tecnico.Tecnico;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "visita_tecnico")
public class VisitaTecnico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha")
    private LocalDate fecha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JoinColumn(name = "tecnico")
    private Tecnico tecnico;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "incidente")
    private Incidente incidente;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "foto")
    private String foto;

    @Column(name = "arreglado")
    private boolean arreglado;

    // Constructor vacío para JPA
    public VisitaTecnico() {
    }

    public VisitaTecnico(Tecnico tecnico, Incidente incidente, String descripcion,
            String foto, boolean arreglado) {
        this.fecha = LocalDate.now();
        this.tecnico = tecnico;
        this.incidente = incidente;
        this.descripcion = descripcion;
        this.foto = foto;
        this.arreglado = arreglado;

        this.verificarArreglo();

    }

    private void verificarArreglo() {
        if (this.arreglado) {
            this.incidente.getHeladera().setFuncionando(true);
        } else {
            System.out.println("Se arregla otra visita");
        }
    }
}
