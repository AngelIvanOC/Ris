package com.example.conexionbd.turnos.control;


import com.example.conexionbd.turnos.model.TurnosDTO;
import com.example.conexionbd.turnos.model.Turnos;
import com.example.conexionbd.turnos.control.TurnosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/turnos")
public class TurnosController {

    @Autowired
    private TurnosService turnosService;

    // Obtener todos los turnos
    @GetMapping
    public ResponseEntity<List<TurnosDTO>> getAllTurnos() {
        List<Turnos> turnos = turnosService.findAll();
        List<TurnosDTO> turnosDTOs = turnos.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(turnosDTOs);
    }

    // Obtener turnos por ID de personal
    @GetMapping("/personal/{personalId}")
    public ResponseEntity<List<TurnosDTO>> getTurnosByPersonal(@PathVariable Long personalId) {
        List<Turnos> turnos = turnosService.findByPersonalId(personalId);
        List<TurnosDTO> turnosDTOs = turnos.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(turnosDTOs);
    }

    // Obtener un turno por su ID
    @GetMapping("/{id}")
    public ResponseEntity<TurnosDTO> getTurnoById(@PathVariable Long id) {
        Turnos turno = turnosService.findById(id);
        return ResponseEntity.ok(convertToDTO(turno));
    }

    // Crear un nuevo turno
    @PostMapping
    public ResponseEntity<TurnosDTO> createTurno(@RequestBody Turnos turno) {
        Turnos savedTurno = turnosService.save(turno);
        return ResponseEntity.ok(convertToDTO(savedTurno));
    }

    // Actualizar un turno existente
    @PutMapping("/{id}")
    public ResponseEntity<TurnosDTO> updateTurno(@PathVariable Long id, @RequestBody Turnos turno) {
        Turnos updatedTurno = turnosService.update(id, turno);
        return ResponseEntity.ok(convertToDTO(updatedTurno));
    }

    // Cambiar asistencia de un turno
    @PatchMapping("/{id}/asistencia")
    public ResponseEntity<TurnosDTO> updateAsistencia(@PathVariable Long id, @RequestBody boolean asistencia) {
        Turnos updatedTurno = turnosService.updateAsistencia(id, asistencia);
        return ResponseEntity.ok(convertToDTO(updatedTurno));
    }

    // Método para convertir Turnos a TurnosDTO
    private TurnosDTO convertToDTO(Turnos turno) {
        return new TurnosDTO(
                turno.getId(),
                turno.getPersonal().getId(),
                turno.getPersonal().getName(),
                turno.getFechaHoraLlegada(),
                turno.getFechaHoraSalida(),
                turno.isAsistencia()
        );
    }
}
