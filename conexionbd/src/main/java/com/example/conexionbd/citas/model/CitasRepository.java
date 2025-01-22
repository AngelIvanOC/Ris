package com.example.conexionbd.citas.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CitasRepository extends JpaRepository<Citas, Long> {

    List<Citas> findByPersonalId(Long personalId);

    List<Citas> findByPacientesId(Long pacientesId);

}
