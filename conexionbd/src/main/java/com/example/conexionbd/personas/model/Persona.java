package com.example.conexionbd.personas.model;

import jakarta.persistence.*;

@Entity
@Table(name = "personas")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", length = 30, nullable = false)
    private String nombre;

    @Column(name = "apellidos", length = 70, nullable = false)
    private String apellidos;

    @Column(name = "curp", length = 20, nullable = false, unique = true)
    private String curp;

    @Column(name = "telefono", length = 15, nullable = false)
    private String telefono;

    @Column(name = "status")
    private boolean status;

    public Persona() {
        this.status = true;
    }

    public Persona(String nombre, String apellidos, String curp, String telefono, boolean status) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.curp = curp;
        this.telefono = telefono;
        this.status = status;
    }

    public Persona(Long id, String nombre, String apellidos, String curp, String telefono, boolean status) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.curp = curp;
        this.telefono = telefono;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}