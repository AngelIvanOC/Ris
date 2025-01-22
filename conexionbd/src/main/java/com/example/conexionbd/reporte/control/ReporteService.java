package com.example.conexionbd.reporte.control;

import com.example.conexionbd.reporte.model.Reporte;
import com.example.conexionbd.reporte.model.ReporteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    public List<Reporte> findAll() {
        return reporteRepository.findAll();
    }

    public Reporte findById(Long id) {
        return reporteRepository.findById(id).orElseThrow(() -> new RuntimeException("Reporte no encontrado"));
    }

    public List<Reporte> findByPersonalId(Long personalId) {
        return reporteRepository.findByPersonalId(personalId);
    }

    public List<Reporte> findByTurno(String turno) {
        return reporteRepository.findByTurno(turno);
    }

    public Reporte save(Reporte reporte) {
        return reporteRepository.save(reporte);
    }

    public void delete(Long id) {
        reporteRepository.deleteById(id);
    }
}

