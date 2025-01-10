package com.example.conexionbd.personal.model;

import jakarta.persistence.*;

@Entity
@Table(name = "personal")
public class Personal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "edad", columnDefinition = "VARCHAR(50)")
    private String edad;

    @Column(name = "genero",columnDefinition = "VARCHAR(50)")
    private String genero;

    @Column(name = "puesto",columnDefinition = "VARCHAR(50)")
    private String puesto;

    public Personal() {
    }

    public Personal(String name, String edad, String genero, String puesto) {
        this.name = name;
        this.edad = edad;
        this.genero = genero;
        this.puesto = puesto;
    }

    public Personal(Long id, String name, String edad, String genero, String puesto) {
        this.id = id;
        this.name = name;
        this.edad = edad;
        this.genero = genero;
        this.puesto = puesto;
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

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
}
