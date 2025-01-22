package com.example.conexionbd.etiquetas.control;

import com.example.conexionbd.etiquetas.model.Etiquetas;
import com.example.conexionbd.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/etiquetas")
public class EtiquetasController {

    private final EtiquetasService etiquetasService;

    @Autowired
    public EtiquetasController(EtiquetasService etiquetasService) {
        this.etiquetasService = etiquetasService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllEtiquetas() {
        return etiquetasService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Message> saveEtiqueta(@RequestBody Etiquetas etiquetas) {
        return etiquetasService.save(etiquetas);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updateEtiqueta(@RequestBody Etiquetas etiquetas) {
        return etiquetasService.update(etiquetas);
    }
}
