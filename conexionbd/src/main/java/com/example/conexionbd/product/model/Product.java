package com.example.conexionbd.product.model;


import jakarta.persistence.*;

@Entity
@Table(name = "productos")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name",columnDefinition = "VARCHAR(30)")
    private String name;

    @Column(name = "stock",columnDefinition = "INT")
    private int stock;

    @Column(name = "status",columnDefinition = "BOOL DEFAULT TRUE")
    private boolean status;

    public Product() {
    }

    public Product(String name, int stock, boolean status) {
        this.name = name;
        this.stock = stock;
        this.status = status;
    }

    public Product(Long id, String name, int stock, boolean status) {
        this.id = id;
        this.name = name;
        this.stock = stock;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

