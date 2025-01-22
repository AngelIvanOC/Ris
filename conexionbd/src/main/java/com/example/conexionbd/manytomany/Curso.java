package com.example.conexionbd.manytomany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Curso {
    @Id
    private Long id;

    @Column(name = "nombre", length = 30, nullable = false)
    private String nombre;

    @Column(name = "descripcion", length = 70, nullable = false)
    private String descripcion;

    @OneToMany(mappedBy = "curso")
    @JsonIgnore
    private List<Inscripcion> inscripcion;
}