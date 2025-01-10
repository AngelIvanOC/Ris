package com.example.conexionbd.turnos.model;

import com.example.conexionbd.turnos.model.Turnos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TurnosRepository extends JpaRepository<Turnos, Long> {

    Optional<Turnos> findByPersonalIdAndFechaHoraLlegadaBetween(Long personalId, LocalDateTime start, LocalDateTime end);

    List<Turnos> findByPersonalId(Long personalId); // Agrega esta línea
}



