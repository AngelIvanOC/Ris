package com.example.conexionbd.pacientes.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "pacientes")
public class Pacientes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(name = "edad", columnDefinition = "VARCHAR(50)")
    private String edad;

    @Column(name = "genero",columnDefinition = "VARCHAR(50)")
    private String genero;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    public Pacientes(){
    }

    public Pacientes(String name, String edad, String genero, LocalDate fechaNacimiento) {
        this.name = name;
        this.edad = edad;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Pacientes(Long id, String name, String edad, String genero, LocalDate fechaNacimiento) {
        this.id = id;
        this.name = name;
        this.edad = edad;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
