package com.example.conexionbd.autobuses.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AutobusDTO {
    @NotNull(groups = {AutobusDTO.Modify.class, AutobusDTO.ChangeStatus.class})
    private Long id;
    @NotBlank(groups = {AutobusDTO.Register.class, AutobusDTO.Modify.class})
    private String marca;
    @NotBlank(groups = {AutobusDTO.Register.class, AutobusDTO.Modify.class})
    private String modelo;
    @NotNull(groups = {AutobusDTO.Register.class, AutobusDTO.Modify.class})
    private Integer anio;
    @NotBlank(groups = {AutobusDTO.Register.class, AutobusDTO.Modify.class})
    private String placa;

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

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public interface Register {}
    public interface Modify {}
    public interface ChangeStatus {}
}
