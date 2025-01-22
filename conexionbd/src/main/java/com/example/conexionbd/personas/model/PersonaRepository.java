package com.example.conexionbd.personas.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findAllByStatusIsTrue();
    boolean existsByCurp(String curp);
    boolean existsByCurpAndIdNot(String curp, Long id);
    boolean existsByTelefono(String telefono);
    boolean existsByTelefonoAndIdNot(String telefono, Long id);

    @Query("SELECT p FROM Persona p")
    List<Persona> findAllPersonas();

    @Query("SELECT p FROM Persona p WHERE p.status = true")
    List<Persona> findActiveProducts();

    @Query("SELECT p FROM Persona p WHERE p.status = false")
    List<Persona> findInactiveProducts();

    @Query("SELECT p FROM Persona p WHERE p.curp = :curp")
    Optional<Persona> findProductByCurp(@Param("curp") String curp);

    @Query("SELECT p FROM Persona p WHERE p.nombre LIKE %:nombre%")
    List<Persona> findProductsByName(@Param("nombre") String nombre);

    @Query(value = "SELECT * FROM personas", nativeQuery = true)
    List<Persona> findAllProductsNative();

    @Query(value = "SELECT * FROM personas WHERE status = TRUE", nativeQuery = true)
    List<Persona> findActiveProductsIsActiveNative();

    @Query(value = "SELECT * FROM personas WHERE id = ?1", nativeQuery = true)
    Optional<Persona> findProductByIdNative(Long id);
}