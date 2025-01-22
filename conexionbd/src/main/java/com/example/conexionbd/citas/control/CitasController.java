package com.example.conexionbd.citas.control;

import com.example.conexionbd.citas.model.Citas;
import com.example.conexionbd.citas.model.CitasDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/citas")
public class CitasController {
    @Autowired
    private CitasService citasService;

    @GetMapping
    public ResponseEntity<List<CitasDTO>> getAllCitas(){
        List<Citas> citas = citasService.findAll();
        List<CitasDTO> citasDTOs = citas.stream().map(this::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(citasDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CitasDTO> getCitasById(@PathVariable Long id) {
        Citas citas = citasService.findById(id);
        return ResponseEntity.ok(convertToDTO(citas));
    }

    @PostMapping
    public ResponseEntity<CitasDTO> createCitas(@RequestBody Citas citas) {
        Citas savedCitas = citasService.save(citas);
        return ResponseEntity.ok(convertToDTO(savedCitas));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CitasDTO> updateCitas(@PathVariable Long id, @RequestBody Citas citas) {
        Citas existingCitas = citasService.findById(id);
        existingCitas.setPersonal(citas.getPersonal());
        existingCitas.setPacientes(citas.getPacientes());
        existingCitas.setFechaCita(citas.getFechaCita());
        existingCitas.setHoraCita(citas.getHoraCita());
        existingCitas.setMotivo(citas.getMotivo());
        existingCitas.setDiagnostico(citas.getDiagnostico());

        Citas updatedCitas = citasService.save(existingCitas);
        return ResponseEntity.ok(convertToDTO(updatedCitas));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCitas(@PathVariable Long id) {
        citasService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private CitasDTO convertToDTO(Citas citas) {
        return new CitasDTO(
                citas.getId(),
                citas.getPersonal().getId(),
                citas.getPersonal().getName(),
                citas.getPacientes().getId(),
                citas.getPacientes().getName(),
                citas.getFechaCita(),
                citas.getHoraCita(),
                citas.getMotivo(),
                citas.getDiagnostico()
        );
    }
}
