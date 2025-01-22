package com.example.conexionbd.manytomany;

import com.example.conexionbd.onetomany.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Estudiante {
    @Id
    private Long id;

    @Column(name = "nombre", length = 30, nullable = false)
    private String nombre;

    @Column(name = "matricula", length = 70, nullable = false)
    private String matricula;

    @OneToMany(mappedBy = "estudiante")
    @JsonIgnore
    private List<Inscripcion> inscripcion;
}
