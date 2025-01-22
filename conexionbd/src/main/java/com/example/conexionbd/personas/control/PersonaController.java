package com.example.conexionbd.personas.control;

import com.example.conexionbd.personas.model.Persona;
import com.example.conexionbd.utils.TypesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import com.example.conexionbd.utils.Message;



import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persona")
public class PersonaController {
    private final PersonaService personaService;

    @Autowired
    public PersonaController(PersonaService personaService) {
        this.personaService = personaService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllPersonas() {
        try {
            return personaService.findAllPersonas();
        } catch (Exception e) {
            return new ResponseEntity<>(new Message("Error al obtener personas", TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/true")
    public ResponseEntity<Message> getActiveProducts() {
        try {
            return personaService.findActiveProducts();
        } catch (Exception e) {
            return new ResponseEntity<>(new Message("Error al obtener personas", TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/false")
    public ResponseEntity<Message> getInactiveProducts() {
        try {
            return personaService.findInactiveProducts();
        } catch (Exception e) {
            return new ResponseEntity<>(new Message("Error al obtener personas", TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/curp/{curp}")
    public ResponseEntity<Message> getProductsByCurp(@PathVariable String curp) {
        try {
            return personaService.findProductByCurp(curp);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message("Error al obtener personas", TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getPersonaById(@PathVariable Long id) {
        try {
            return personaService.findById(id);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message("Error al obtener persona", TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Message>  savePersona(@RequestBody Persona persona) {
        return personaService.save(persona);
    }

    @PutMapping("/update")
    public ResponseEntity<Message>  updatePersona(@RequestBody Persona persona) {
        return personaService.update(persona);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Message>  deletePersona(@PathVariable Long id) {
        return personaService.changeStatus(id);
    }
}
