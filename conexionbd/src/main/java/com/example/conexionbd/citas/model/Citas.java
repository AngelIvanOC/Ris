package com.example.conexionbd.citas.model;

import com.example.conexionbd.pacientes.model.Pacientes;
import com.example.conexionbd.personal.model.Personal;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Citas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "personal_id", nullable = false)
    private Personal personal;

    @ManyToOne
    @JoinColumn(name = "pacientes_id", nullable = false)
    private Pacientes pacientes;

    private LocalDate fechaCita;

    private LocalTime horaCita;

    private String motivo;

    private String diagnostico;

    public Citas(){}

    public Citas(Personal personal, Pacientes pacientes, LocalDate fechaCita, LocalTime horaCita, String motivo, String diagnostico) {
        this.personal = personal;
        this.pacientes = pacientes;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
    }

    public Citas(Long id, Personal personal, Pacientes pacientes, LocalDate fechaCita, LocalTime horaCita, String motivo, String diagnostico) {
        this.id = id;
        this.personal = personal;
        this.pacientes = pacientes;
        this.fechaCita = fechaCita;
        this.horaCita = horaCita;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
    }

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

    public Pacientes getPacientes() {
        return pacientes;
    }

    public void setPacientes(Pacientes pacientes) {
        this.pacientes = pacientes;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public LocalTime getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(LocalTime horaCita) {
        this.horaCita = horaCita;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }
}
