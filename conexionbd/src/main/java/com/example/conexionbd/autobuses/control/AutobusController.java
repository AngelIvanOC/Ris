package com.example.conexionbd.autobuses.control;

import com.example.conexionbd.autobuses.model.AutobusDTO;
import com.example.conexionbd.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autobus")
public class AutobusController {

    private final AutobusService autobusService;

    @Autowired
    public AutobusController(AutobusService autobusService) {
        this.autobusService = autobusService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAll() {
        return autobusService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable Long id) {
        return autobusService.findById(id);
    }

    @PostMapping("/save")
    public ResponseEntity<Message> save(@Validated(AutobusDTO.Register.class) @RequestBody AutobusDTO dto) {
        return autobusService.save(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> update(@Validated(AutobusDTO.Modify.class) @RequestBody AutobusDTO dto) {
        return autobusService.update(dto);
    }

    @PutMapping("/change-status")
    public ResponseEntity<Message> changeStatus(@Validated(AutobusDTO.ChangeStatus.class) @RequestBody AutobusDTO dto) {
        return autobusService.changeStatus(dto);
    }

    @GetMapping("/by-placa/{placa}")
    public ResponseEntity<Message> getByPlaca(@PathVariable String placa) {
        return autobusService.findByPlaca(placa);
    }

    @GetMapping("/by-anio")
    public ResponseEntity<Message> getByAnioRange(@RequestParam int anioInicio, @RequestParam int anioFin) {
        return autobusService.findByAnioRange(anioInicio, anioFin);
    }
}
