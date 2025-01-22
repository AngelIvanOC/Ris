package com.example.conexionbd.reporte.model;
import java.time.LocalDate;
import java.time.LocalTime;

public class ReporteDTO {

    private Long id;
    private Long personalId;
    private String nombrePersonal;
    private LocalDate fechaFalta;
    private String turno;
    private String descripcion;
    private LocalTime horaEntrada;   // Hora de entrada
    private LocalTime horaSalida;    // Hora de salida
    private Boolean asistencia;      // Asistencia

    public ReporteDTO() {}

    public ReporteDTO(Long id, Long personalId, String nombrePersonal, LocalDate fechaFalta, String turno,
                      String descripcion, LocalTime horaEntrada, LocalTime horaSalida, Boolean asistencia) {
        this.id = id;
        this.personalId = personalId;
        this.nombrePersonal = nombrePersonal;
        this.fechaFalta = fechaFalta;
        this.turno = turno;
        this.descripcion = descripcion;
        this.horaEntrada = horaEntrada;
        this.horaSalida = horaSalida;
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

    public LocalTime getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(LocalTime horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public LocalTime getHoraSalida() {
        return horaSalida;
    }

    public void setHoraSalida(LocalTime horaSalida) {
        this.horaSalida = horaSalida;
    }

    public Boolean getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(Boolean asistencia) {
        this.asistencia = asistencia;
    }
}
