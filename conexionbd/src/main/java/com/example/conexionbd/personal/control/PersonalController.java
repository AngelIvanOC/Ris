package com.example.conexionbd.personal.control;

import com.example.conexionbd.personal.model.PersonalDTO;
import com.example.conexionbd.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
        return personalService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Message> savePersonal(@Validated(PersonalDTO.Register.class) @RequestBody PersonalDTO dto) {
        return personalService.save(dto);
    }
}
