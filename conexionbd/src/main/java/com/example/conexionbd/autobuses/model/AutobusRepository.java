package com.example.conexionbd.autobuses.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutobusRepository extends JpaRepository<Autobus, Long> {
    List<Autobus> findAllByStatusIsTrue();
    List<Autobus> findAllByStatusIsFalse();
    Autobus findByPlaca(String placa);
    List<Autobus> findByAnioBetween(int anioInicio, int anioFin);
    Autobus findTopByOrderByCreatedAtDesc();
}