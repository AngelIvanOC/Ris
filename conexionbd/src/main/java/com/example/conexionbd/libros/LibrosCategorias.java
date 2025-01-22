package com.example.conexionbd.libros;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class LibrosCategorias {
    @Id
    private Long id;

    @OneToMany(mappedBy = "librosCategorias")
    @JsonIgnore
    private List<Libros> libros;

    @OneToMany(mappedBy = "librosCategorias")
    @JsonIgnore
    private List<Categorias> categorias;

    @Column(name = "status",columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;
}
