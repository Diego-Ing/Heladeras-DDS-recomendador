package com.example.demo.models.Rol.Tecnico;

import java.util.List;

import com.example.demo.models.Heladera.Incidentes.VisitaTecnico;
import com.example.demo.models.Persona.Persona;
import com.example.demo.models.Rol.Rol;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tecnico")
public class Tecnico extends Rol {

    @Column(name = "cuil")
    private String cuil;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "area_cobertura_id")
    @JsonIgnore // Ignorado durante la serialización
    private AreaCobertura areaCobertura;

    @OneToMany(mappedBy = "tecnico", fetch = FetchType.LAZY)
    @JsonManagedReference // Se serializa en Tecnico
    private List<VisitaTecnico> visitas;

    // Constructor vacío para JPA
    public Tecnico() {
    }

    public Tecnico(Persona persona, String cuil, AreaCobertura areaCobertura) {
        super(persona);
        this.cuil = cuil;
        this.areaCobertura = areaCobertura;
    }

}
