package com.example.conexionbd.onetomanyandmanytoone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Producto {
    @Id
    private Long id;

    @Column(name = "nombre",columnDefinition = "VARCHAR(30)")
    private String nombre;

    @Column(name = "precio",columnDefinition = "VARCHAR(30)")
    private String precio;

    @Column(name = "descripcion",columnDefinition = "VARCHAR(70)")
    private String descripcion;

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private List<DetalleOrden> detalleOrdens;
}
