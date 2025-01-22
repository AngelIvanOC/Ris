package com.example.conexionbd.libros;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Autores {
    @Id
    private Long id;

    @Column(name = "nombre", length = 30, nullable = false)
    private String nombre;

    @Column(name = "nacionalidad", length = 30, nullable = false)
    private String nacionalidad;

    @ManyToOne
    private Libros libros;
}
