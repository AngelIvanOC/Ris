package com.example.conexionbd.reporte.model;

import com.example.conexionbd.personal.model.Personal;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

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

    private LocalTime horaEntrada;  // Hora de entrada
    private LocalTime horaSalida;   // Hora de salida
    private Boolean asistencia;     // Asistencia (true/false)

    public Reporte() {}

    public Reporte(Long id, Personal personal, LocalDate fechaFalta, String turno, String descripcion,
                   LocalTime horaEntrada, LocalTime horaSalida, Boolean asistencia) {
        this.id = id;
        this.personal = personal;
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
