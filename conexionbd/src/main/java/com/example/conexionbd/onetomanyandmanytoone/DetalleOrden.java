package com.example.conexionbd.onetomanyandmanytoone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class DetalleOrden {
    @Id
    private Long id;

    @ManyToOne
    private Orden orden;

    @ManyToOne
    @JsonIgnore
    private Producto producto;
}
