package com.example.conexionbd.libros;

import com.example.conexionbd.onetomany.Post;
import com.example.conexionbd.onetomany.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Libros {
    @Id
    private Long id;

    @Column(name = "titulo", length = 30, nullable = false)
    private String titulo;

    //Este campo no tiene ni getter ni setter
    @Column(name = "create_at",columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @OneToMany(mappedBy = "libros")
    @JsonIgnore
    private List<Autores> autores;

    @ManyToOne
    private LibrosCategorias librosCategorias;
}
