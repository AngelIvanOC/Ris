package com.example.conexionbd.autobuses.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "autobuses")
public class Autobus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "marca", columnDefinition = "VARCHAR(30)", nullable = false)
    private String marca;

    @Column(name = "modelo", columnDefinition = "VARCHAR(30)", nullable = false)
    private String modelo;

    @Column(name = "anio", columnDefinition = "INT", nullable = false)
    private int anio;

    @Column(name = "placa", columnDefinition = "VARCHAR(10)", unique = true, nullable = false)
    private String placa;

    @Column(name = "status", columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP DEFAULT NOW()")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    public Autobus() {}

    public Autobus(String marca, String modelo, int anio, String placa, boolean status) {
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.placa = placa;
        this.status = status;
        this.createdAt = new Date();
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
