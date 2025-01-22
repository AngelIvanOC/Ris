package com.example.conexionbd.cursos;

import com.example.conexionbd.libros.Libros;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Matriculas {
    @Id
    private Long id;

    @Column(name = "create_at",columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @ManyToOne
    private Estudiantes estudiantes;

    @ManyToOne
    private Cursos cursos;
}
