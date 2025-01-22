package com.example.conexionbd.onetomanyandmanytoone;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class Orden {
    @Id
    private Long id;

    @ManyToOne
    @JsonIgnore
    private Cliente cliente;

    @ManyToOne
    @JsonIgnore
    private Vendedor vendedor;

    @OneToMany(mappedBy = "orden")
    @JsonIgnore
    private List<DetalleOrden> detalleOrdens;
}
