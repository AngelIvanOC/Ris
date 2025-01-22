package com.example.conexionbd.peliculas.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "peliculas")
public class Pelicula {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",columnDefinition = "VARCHAR(30)")
    private String name;

    @Column(name = "description",columnDefinition = "VARCHAR(50)")
    private String description;

    @Column(name = "genero",columnDefinition = "VARCHAR(50)")
    private String genero;

    @Column(name = "cantidad",columnDefinition = "VARCHAR(50)")
    private String cantidad;

    @Column(name = "precio",columnDefinition = "VARCHAR(50)")
    private String precio;

    //Este campo no tiene ni getter ni setter
    @Column(name = "create_at",columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "status",columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;

    public Pelicula() {
    }

    public Pelicula(String name, String description, String genero, String cantidad, String precio, boolean status) {
        this.name = name;
        this.description = description;
        this.genero = genero;
        this.cantidad = cantidad;
        this.precio = precio;
        this.status = status;
    }

    public Pelicula(Long id, String name, String description, String genero, String cantidad, String precio, boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.genero = genero;
        this.cantidad = cantidad;
        this.precio = precio;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}