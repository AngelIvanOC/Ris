package com.example.conexionbd.reporte.model;

import com.example.conexionbd.personal.model.Personal;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Reporte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "personal_id", nullable = false)
    private Personal personal;

    private LocalDate fechaFalta;

    private String turno;

    private String descripcion;

    public Reporte() {}

    public Reporte(Long id, Personal personal, LocalDate fechaFalta, String turno, String descripcion) {
        this.id = id;
        this.personal = personal;
        this.fechaFalta = fechaFalta;
        this.turno = turno;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public LocalDate getFechaFalta() {
        return fechaFalta;
    }

    public void setFechaFalta(LocalDate fechaFalta) {
        this.fechaFalta = fechaFalta;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
