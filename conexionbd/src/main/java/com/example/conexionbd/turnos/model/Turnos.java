package com.example.conexionbd.turnos.model;

import com.example.conexionbd.personal.model.Personal;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Turnos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "personal_id")
    private Personal personal;

    @Column(name = "fechaHoraLlega", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaHoraLlegada;

    @Column(name = "fechaHoraSalida", columnDefinition = "TIMESTAMP")
    private LocalDateTime fechaHoraSalida;

    @Column(name = "asistencia")
    private boolean asistencia; // Nuevo campo

    // Constructor por defecto
    public Turnos() {
    }

    // Constructor con parámetros
    public Turnos(Long id, Personal personal, LocalDateTime fechaHoraLlegada, LocalDateTime fechaHoraSalida, boolean asistencia) {
        this.id = id;
        this.personal = personal;
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

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
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