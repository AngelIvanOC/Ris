package com.example.conexionbd.pacientes.control;

import com.example.conexionbd.pacientes.control.PacientesService;
import com.example.conexionbd.pacientes.model.Pacientes;
import com.example.conexionbd.pacientes.model.PacientesDTO;
import com.example.conexionbd.utils.Message;
import com.example.conexionbd.utils.TypesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/pacientes")
public class PacientesController {
    private final PacientesService pacientesService;

    @Autowired
    public PacientesController(PacientesService pacientesService) {
        this.pacientesService = pacientesService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllPacientes() {
        try {
            return pacientesService.findAll();
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage(), TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Message> savePersonal(@Validated(PacientesDTO.Register.class) @RequestBody PacientesDTO dto) {
        try {
            return pacientesService.save(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage(), TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updatePersonal(@Validated(PacientesDTO.Register.class) @RequestBody PacientesDTO dto) {
        try {
            return pacientesService.update(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage(), TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deletePersonal(@PathVariable Long id) {
        try {
            return pacientesService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage(), TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getPersonalById(@PathVariable Long id) {
        try {
            Optional<Pacientes> pacientes = pacientesService.findById(id);
            if (pacientes.isPresent()) {
                return new ResponseEntity<>(new Message(pacientes.get(), "Paciente encontrado", TypesResponse.SUCCESS), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Message("El paciente no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage(), TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
