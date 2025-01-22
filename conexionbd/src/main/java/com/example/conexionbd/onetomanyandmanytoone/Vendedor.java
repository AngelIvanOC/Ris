package com.example.conexionbd.onetomanyandmanytoone;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Vendedor {
    @Id
    private Long id;

    @Column(name = "nombre",columnDefinition = "VARCHAR(30)")
    private String nombre;

    @Column(name = "direccion",columnDefinition = "VARCHAR(70)")
    private String direccion;

    @OneToMany(mappedBy = "vendedor")
    private List<Orden> ordenes;
}
