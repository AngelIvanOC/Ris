package com.example.conexionbd.personal.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class PersonalDTO {

    // Definir las validaciones para el DTO
    public interface Register {}

    private Long id;

    @NotEmpty(message = "El nombre es obligatorio", groups = {Register.class})
    @Size(max = 50, message = "El nombre no debe exceder los 50 caracteres", groups = {Register.class})
    private String name;

    @NotEmpty(message = "La edad es obligatoria", groups = {Register.class})
    private String edad;

    @NotEmpty(message = "El género es obligatorio", groups = {Register.class})
    private String genero;

    @NotEmpty(message = "El puesto es obligatorio", groups = {Register.class})
    @Size(max = 50, message = "El puesto no debe exceder los 50 caracteres", groups = {Register.class})
    private String puesto;

    // Constructor
    public PersonalDTO() {}

    public PersonalDTO(Long id, String name, String edad, String genero, String puesto) {
        this.id = id;
        this.name = name;
        this.edad = edad;
        this.genero = genero;
        this.puesto = puesto;
    }

    // Getters y Setters
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
