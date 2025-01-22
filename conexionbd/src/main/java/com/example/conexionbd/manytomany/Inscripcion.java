package com.example.conexionbd.manytomany;

import com.example.conexionbd.onetomany.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Inscripcion {
    @Id
    private Long id;

    @ManyToOne
    private Estudiante estudiante;

    @ManyToOne
    private Curso curso;
}
