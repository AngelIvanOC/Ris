package com.example.conexionbd.citas.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class CitasDTO {

    private Long id;
    private Long personalId;
    private String nombrePersonal;
    private Long pacientesId;
    private String nombrePacientes;
    private LocalDate fechaCita;
    private LocalTime horaCita;
    private String motivo;
    private String diagnostico;

    public CitasDTO(){}

    public CitasDTO(Long id, Long personalId, String nombrePersonal, Long pacientesId, String nombrePacientes, LocalDate fechaCita, LocalTime horaCita, String motivo, String diagnostico) {
        this.id = id;
        this.personalId = personalId;
        this.nombrePersonal = nombrePersonal;
        this.pacientesId = pacientesId;
        this.nombrePacientes = nombrePacientes;
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

    public Long getPersonalId() {
        return personalId;
    }

    public void setPersonalId(Long personalId) {
        this.personalId = personalId;
    }

    public Long getPacientesId() {
        return pacientesId;
    }

    public void setPacientesId(Long pacientesId) {
        this.pacientesId = pacientesId;
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

    public String getNombrePersonal() {
        return nombrePersonal;
    }

    public void setNombrePersonal(String nombrePersonal) {
        this.nombrePersonal = nombrePersonal;
    }

    public String getNombrePacientes() {
        return nombrePacientes;
    }

    public void setNombrePacientes(String nombrePacientes) {
        this.nombrePacientes = nombrePacientes;
    }
}
