package com.example.conexionbd.personal.control;

import com.example.conexionbd.personal.model.Personal;
import com.example.conexionbd.personal.model.PersonalDTO;
import com.example.conexionbd.personal.control.PersonalService;
import com.example.conexionbd.utils.Message;
import com.example.conexionbd.utils.TypesResponse; // Asegúrate de que esta clase existe
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/personal")
public class PersonalController {
    private final PersonalService personalService;

    @Autowired
    public PersonalController(PersonalService personalService) {
        this.personalService = personalService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllPersonal() {
        try {
            return personalService.findAll();
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage(), TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/save")
    public ResponseEntity<Message> savePersonal(@Validated(PersonalDTO.Register.class) @RequestBody PersonalDTO dto) {
        try {
            return personalService.save(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage(), TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updatePersonal(@Validated(PersonalDTO.Register.class) @RequestBody PersonalDTO dto) {
        try {
            return personalService.update(dto);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage(), TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Message> deletePersonal(@PathVariable Long id) {
        try {
            return personalService.delete(id);
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage(), TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getPersonalById(@PathVariable Long id) {
        try {
            Optional<Personal> personal = personalService.findById(id);
            if (personal.isPresent()) {
                return new ResponseEntity<>(new Message(personal.get(), "Personal encontrado", TypesResponse.SUCCESS), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new Message("El personal no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(new Message(e.getMessage(), TypesResponse.ERROR), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
