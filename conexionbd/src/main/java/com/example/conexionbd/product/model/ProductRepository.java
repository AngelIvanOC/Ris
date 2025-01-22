package com.example.conexionbd.product.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByStatusIsTrue();

    ///Querys Personalizadas haciendo uso de JPA

    @Query("SELECT p FROM Product p")
    List<Product> findAllProducts();

    @Query("SELECT p FROM Product p WHERE p.status = true")
    List<Product> findActiveProducts();

    @Query("SELECT p FROM Product p WHERE p.id = :id")
    Optional<Product> findProductById(@Param("id") Long id);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name%")
    List<Product> findProductsByName(@Param("name") String name);

    @Query(value = "SELECT * FROM productos", nativeQuery = true)
    List<Product> findAllProductsNative();

    @Query(value = "SELECT * FROM productos WHERE status = TRUE", nativeQuery = true)
    List<Product> findActiveProductsIsActiveNative();

    @Query(value = "SELECT * FROM productos WHERE id = ?1", nativeQuery = true)
    Optional<Product> findProductByIdNative(Long id);
}