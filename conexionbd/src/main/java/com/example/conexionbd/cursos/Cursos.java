package com.example.conexionbd.cursos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cursos {
    @Id
    private Long id;

    @Column(name = "nombre", length = 30, nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "cursos")
    @JsonIgnore
    private List<Matriculas> matriculas;

    @ManyToOne
    private Profesores profesores;
}
