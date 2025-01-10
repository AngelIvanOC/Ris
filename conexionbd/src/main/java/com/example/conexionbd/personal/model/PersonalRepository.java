package com.example.conexionbd.personal.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonalRepository extends JpaRepository<Personal, Long> {
    // Método para obtener todos los registros de personal
    List<Personal> findAll();
}
