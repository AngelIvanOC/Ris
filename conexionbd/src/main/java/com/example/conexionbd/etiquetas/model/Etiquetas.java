package com.example.conexionbd.etiquetas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "etiqueta")
public class Etiquetas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",columnDefinition = "VARCHAR(30)")
    private String name;
    @Column(name = "color",columnDefinition = "VARCHAR(70)")
    private String color;

    public Etiquetas() {
    }

    public Etiquetas(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public Etiquetas(Long id, String name, String color) {
        this.id = id;
        this.name = name;
        this.color = color;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}