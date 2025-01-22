package com.example.conexionbd.onetomany;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;


@Entity
public class Post {
    @Id
    private Long id;

    @Column(name = "titulo",columnDefinition = "VARCHAR(30)")
    private String titulo;

    @Column(name = "contenido",columnDefinition = "VARCHAR(70)")
    private String contenido;

    @ManyToOne
    private Usuario usuario;
}
