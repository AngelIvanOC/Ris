package com.example.conexionbd.reporte.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Long> {

    List<Reporte> findByPersonalId(Long personalId);

    List<Reporte> findByTurno(String turno);
}
