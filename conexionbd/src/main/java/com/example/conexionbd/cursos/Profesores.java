package com.example.conexionbd.cursos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Profesores {
    @Id
    private Long id;

    @Column(name = "nombre", length = 30, nullable = false)
    private String nombre;

    @Column(name = "especialidad", length = 30, nullable = false)
    private String especialidad;

    @OneToMany(mappedBy = "profesores")
    @JsonIgnore
    private List<Cursos> cursos;
}
