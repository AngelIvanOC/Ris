package com.example.conexionbd.onetomanyandmanytoone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cliente {
    @Id
    private Long id;

    @Column(name = "nombre",columnDefinition = "VARCHAR(30)")
    private String nombre;

    @Column(name = "direccion",columnDefinition = "VARCHAR(70)")
    private String direccion;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Orden> ordenes;
}
