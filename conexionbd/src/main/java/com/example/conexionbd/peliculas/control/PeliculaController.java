package com.example.conexionbd.peliculas.control;

import com.example.conexionbd.peliculas.model.PeliculaDTO;
import com.example.conexionbd.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pelicula")
public class PeliculaController {
    private final PeliculaService peliculaService;

    @Autowired
    public PeliculaController(PeliculaService peliculaService) {
        this.peliculaService = peliculaService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllDocument() {
        return peliculaService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Message> saveDocument(@Validated(PeliculaDTO.Register.class) @RequestBody PeliculaDTO dto) {
        return peliculaService.save(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updateDocument(@Validated(PeliculaDTO.Modify.class) @RequestBody PeliculaDTO dto) {
        return peliculaService.update(dto);
    }

    @PutMapping("/change-status")
    public ResponseEntity<Message> changeStatus(@Validated(PeliculaDTO.ChangeStatus.class) @RequestBody PeliculaDTO dto) {
        return peliculaService.changeStatus(dto);
    }

}