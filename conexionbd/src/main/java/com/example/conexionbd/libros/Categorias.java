package com.example.conexionbd.libros;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Categorias {
    @Id
    private Long id;

    @Column(name = "titulo", length = 30, nullable = false)
    private String titulo;

    @Column(name = "descripcion", length = 30, nullable = false)
    private String descripcion;

    @ManyToOne
    private LibrosCategorias librosCategorias;
}
