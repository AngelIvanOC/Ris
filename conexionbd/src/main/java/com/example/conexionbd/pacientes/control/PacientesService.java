package com.example.conexionbd.pacientes.control;

import com.example.conexionbd.pacientes.model.PacientesDTO;
import com.example.conexionbd.pacientes.model.PacientesRepository;
import com.example.conexionbd.pacientes.model.Pacientes;
import com.example.conexionbd.utils.Message;
import com.example.conexionbd.utils.TypesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class PacientesService {
    private static final Logger logger = LoggerFactory.getLogger(PacientesService.class);

    private final PacientesRepository pacientesRepository;

    @Autowired
    public PacientesService(PacientesRepository pacientesRepository) {
        this.pacientesRepository = pacientesRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Pacientes> pacienteslList = pacientesRepository.findAll();
        logger.info("Búsqueda de paciente realizada correctamente.");
        return new ResponseEntity<>(new Message(pacienteslList, "Listado de pacientes", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(PacientesDTO dto) {
        if (dto.getName().length() > 50) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Pacientes pacientes = new Pacientes(dto.getName(), dto.getEdad(), dto.getGenero(), dto.getFechaNacimiento());
        pacientes = pacientesRepository.saveAndFlush(pacientes);
        logger.info("Registro de paciente realizado correctamente.");
        return new ResponseEntity<>(new Message(pacientes, "El paciente se registró correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(PacientesDTO dto) {
        Optional<Pacientes> pacientesOptional = pacientesRepository.findById(dto.getId());
        if (!pacientesOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El paciente no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Pacientes pacientes = pacientesOptional.get();
        pacientes.setName(dto.getName());
        pacientes.setEdad(dto.getEdad());
        pacientes.setGenero(dto.getGenero());
        pacientes.setFechaNacimiento(dto.getFechaNacimiento());
        pacientes = pacientesRepository.saveAndFlush(pacientes);
        logger.info("Actualización de paciente realizada correctamente.");
        return new ResponseEntity<>(new Message(pacientes, "El paciente se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> delete(Long id) {
        Optional<Pacientes> pacientesOptional = pacientesRepository.findById(id);
        if (!pacientesOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El paciente no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        pacientesRepository.delete(pacientesOptional.get());
        logger.info("Eliminación de paciente realizada correctamente.");
        return new ResponseEntity<>(new Message("El paciente fue eliminado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public Optional<Pacientes> findById(Long id) {
        return pacientesRepository.findById(id);
    }
}
