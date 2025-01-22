package com.example.conexionbd.reporte.control;

import com.example.conexionbd.reporte.model.ReporteDTO;
import com.example.conexionbd.reporte.model.Reporte;
import com.example.conexionbd.reporte.control.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/reportes")
public class ReporteController {

    @Autowired
    private ReporteService reporteService;

    @GetMapping
    public ResponseEntity<List<ReporteDTO>> getAllReportes() {
        List<Reporte> reportes = reporteService.findAll();
        List<ReporteDTO> reporteDTOs = reportes.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(reporteDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReporteDTO> getReporteById(@PathVariable Long id) {
        Reporte reporte = reporteService.findById(id);
        return ResponseEntity.ok(convertToDTO(reporte));
    }

    @PostMapping
    public ResponseEntity<ReporteDTO> createReporte(@RequestBody Reporte reporte) {
        Reporte savedReporte = reporteService.save(reporte);
        return ResponseEntity.ok(convertToDTO(savedReporte));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReporteDTO> updateReporte(@PathVariable Long id, @RequestBody Reporte reporte) {
        Reporte existingReporte = reporteService.findById(id);
        existingReporte.setPersonal(reporte.getPersonal());
        existingReporte.setFechaFalta(reporte.getFechaFalta());
        existingReporte.setTurno(reporte.getTurno());
        existingReporte.setDescripcion(reporte.getDescripcion());
        existingReporte.setHoraEntrada(reporte.getHoraEntrada());
        existingReporte.setHoraSalida(reporte.getHoraSalida());
        existingReporte.setAsistencia(reporte.getAsistencia());

        Reporte updatedReporte = reporteService.save(existingReporte);
        return ResponseEntity.ok(convertToDTO(updatedReporte));
    }




    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReporte(@PathVariable Long id) {
        reporteService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ReporteDTO convertToDTO(Reporte reporte) {
        return new ReporteDTO(
                reporte.getId(),
                reporte.getPersonal().getId(),
                reporte.getPersonal().getName(),
                reporte.getFechaFalta(),
                reporte.getTurno(),
                reporte.getDescripcion(),
                reporte.getHoraEntrada(),
                reporte.getHoraSalida(),
                reporte.getAsistencia()
        );
    }
}
