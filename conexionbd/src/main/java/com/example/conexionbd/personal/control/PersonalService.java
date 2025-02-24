package com.example.conexionbd.personal.control;

import com.example.conexionbd.personal.model.Personal;
import com.example.conexionbd.personal.model.PersonalDTO;
import com.example.conexionbd.personal.model.PersonalRepository;
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
public class PersonalService {
    private static final Logger logger = LoggerFactory.getLogger(PersonalService.class);

    private final PersonalRepository personalRepository;

    @Autowired
    public PersonalService(PersonalRepository personalRepository) {
        this.personalRepository = personalRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Personal> personalList = personalRepository.findAll();
        logger.info("Búsqueda de personal realizada correctamente.");
        return new ResponseEntity<>(new Message(personalList, "Listado de personal", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(PersonalDTO dto) {
        if (dto.getName().length() > 50) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (dto.getPuesto().length() > 50) {
            return new ResponseEntity<>(new Message("El puesto excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Personal personal = new Personal(dto.getName(), dto.getEdad(), dto.getGenero(), dto.getPuesto());
        personal = personalRepository.saveAndFlush(personal);
        logger.info("Registro de personal realizado correctamente.");
        return new ResponseEntity<>(new Message(personal, "El personal se registró correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(PersonalDTO dto) {
        Optional<Personal> personalOptional = personalRepository.findById(dto.getId());
        if (!personalOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El personal no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Personal personal = personalOptional.get();
        personal.setName(dto.getName());
        personal.setEdad(dto.getEdad());
        personal.setGenero(dto.getGenero());
        personal.setPuesto(dto.getPuesto());
        personal = personalRepository.saveAndFlush(personal);
        logger.info("Actualización de personal realizada correctamente.");
        return new ResponseEntity<>(new Message(personal, "El personal se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> delete(Long id) {
        Optional<Personal> personalOptional = personalRepository.findById(id);
        if (!personalOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El personal no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        personalRepository.delete(personalOptional.get());
        logger.info("Eliminación de personal realizada correctamente.");
        return new ResponseEntity<>(new Message("El personal fue eliminado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public Optional<Personal> findById(Long id) {
        return personalRepository.findById(id);
    }
}
