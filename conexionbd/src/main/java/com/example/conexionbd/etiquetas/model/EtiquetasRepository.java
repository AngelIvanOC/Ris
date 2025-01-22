package com.example.conexionbd.etiquetas.model;

import com.example.conexionbd.etiquetas.model.Etiquetas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtiquetasRepository  extends JpaRepository<Etiquetas, Long> {

}