package com.example.conexionbd.reporte.model;
import java.time.LocalDate;

public class ReporteDTO {

    private Long id;
    private Long personalId;
    private String nombrePersonal;
    private LocalDate fechaFalta;
    private String turno;
    private String descripcion;

    public ReporteDTO() {}

    public ReporteDTO(Long id, Long personalId, String nombrePersonal, LocalDate fechaFalta, String turno, String descripcion) {
        this.id = id;
        this.personalId = personalId;
        this.nombrePersonal = nombrePersonal;
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

    public Long getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Long personalId) {
        this.personalId = personalId;
    }

    public String getNombrePersonal() {
        return nombrePersonal;
    }

    public void setNombrePersonal(String nombrePersonal) {
        this.nombrePersonal = nombrePersonal;
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
