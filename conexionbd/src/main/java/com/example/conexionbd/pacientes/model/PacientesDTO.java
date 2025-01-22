package com.example.conexionbd.pacientes.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class PacientesDTO {
    public interface Register {}

    private Long id; // El id es opcional en la creación, pero requerido para la actualización

    @NotEmpty(message = "El nombre es obligatorio", groups = {PacientesDTO.Register.class})
    @Size(max = 50, message = "El nombre no debe exceder los 50 caracteres", groups = {PacientesDTO.Register.class})
    private String name;

    @NotEmpty(message = "La edad es obligatoria", groups = {PacientesDTO.Register.class})
    private String edad;

    @NotEmpty(message = "El género es obligatorio", groups = {PacientesDTO.Register.class})
    private String genero;

    private LocalDate fechaNacimiento;

    public PacientesDTO(){}

    public PacientesDTO(String name, String edad, String genero, LocalDate fechaNacimiento) {
        this.name = name;
        this.edad = edad;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
    }

    public PacientesDTO(Long id, String name, String edad, String genero, LocalDate fechaNacimiento) {
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
