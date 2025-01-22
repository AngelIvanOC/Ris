package com.example.conexionbd.peliculas.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PeliculaDTO {
    @NotNull(groups = {com.example.conexionbd.peliculas.model.PeliculaDTO.Modify.class, com.example.conexionbd.peliculas.model.PeliculaDTO.ChangeStatus.class})
    private Long id;
    @NotBlank(groups = {com.example.conexionbd.peliculas.model.PeliculaDTO.Register.class, com.example.conexionbd.peliculas.model.PeliculaDTO.Modify.class})
    private String name;
    @NotBlank(groups = {com.example.conexionbd.peliculas.model.PeliculaDTO.Register.class, com.example.conexionbd.peliculas.model.PeliculaDTO.Modify.class})
    private String description;
    @NotBlank(groups = {com.example.conexionbd.peliculas.model.PeliculaDTO.Register.class, com.example.conexionbd.peliculas.model.PeliculaDTO.Modify.class})
    private String genero;
    @NotBlank(groups = {com.example.conexionbd.peliculas.model.PeliculaDTO.Register.class, com.example.conexionbd.peliculas.model.PeliculaDTO.Modify.class})
    private String cantidad;
    @NotBlank(groups = {com.example.conexionbd.peliculas.model.PeliculaDTO.Register.class, com.example.conexionbd.peliculas.model.PeliculaDTO.Modify.class})
    private String precio;

    public PeliculaDTO() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public interface Register{}
    public interface Modify{}
    public interface ChangeStatus{}
}