package com.example.conexionbd.cursos;

import com.example.conexionbd.libros.Autores;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Estudiantes {
    @Id
    private Long id;

    @Column(name = "nombre", length = 30, nullable = false)
    private String nombre;

    @Column(name = "email", length = 30, nullable = false)
    private String email;

    @OneToMany(mappedBy = "estudiantes")
    @JsonIgnore
    private List<Matriculas> matriculas;
}
