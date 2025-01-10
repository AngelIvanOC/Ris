package com.example.conexionbd.turnos.model;
import java.time.LocalDateTime;

public class TurnosDTO {

    private Long id;
    private Long personalId;
    private String nombrePersonal;
    private LocalDateTime fechaHoraLlegada;
    private LocalDateTime fechaHoraSalida;
    private boolean asistencia;

    // Constructor vacío
    public TurnosDTO() {
    }

    // Constructor con parámetros
    public TurnosDTO(Long id, Long personalId, String nombrePersonal, LocalDateTime fechaHoraLlegada, LocalDateTime fechaHoraSalida, boolean asistencia) {
        this.id = id;
        this.personalId = personalId;
        this.nombrePersonal = nombrePersonal;
        this.fechaHoraLlegada = fechaHoraLlegada;
        this.fechaHoraSalida = fechaHoraSalida;
        this.asistencia = asistencia;
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

    public LocalDateTime getFechaHoraLlegada() {
        return fechaHoraLlegada;
    }

    public void setFechaHoraLlegada(LocalDateTime fechaHoraLlegada) {
        this.fechaHoraLlegada = fechaHoraLlegada;
    }

    public LocalDateTime getFechaHoraSalida() {
        return fechaHoraSalida;
    }

    public void setFechaHoraSalida(LocalDateTime fechaHoraSalida) {
        this.fechaHoraSalida = fechaHoraSalida;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }
}
