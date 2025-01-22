package com.example.conexionbd.personas.control;

import com.example.conexionbd.personas.model.Persona;
import com.example.conexionbd.personas.model.PersonaRepository;
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
public class PersonaService {
    private static final Logger logger = LoggerFactory.getLogger(PersonaService.class);

    private final PersonaRepository personaRepository;

    @Autowired
    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAllPersonas() {
        logger.info("Obteniendo todas las personas");
        List<Persona> personas = personaRepository.findAllPersonas();
        return new ResponseEntity<>(new Message(personas, "Listado de personas", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findActiveProducts() {
        logger.info("Obteniendo todas las personas");
        List<Persona> personas = personaRepository.findActiveProducts();
        return new ResponseEntity<>(new Message(personas, "Listado de personas", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findInactiveProducts() {
        logger.info("Obteniendo todas las personas");
        List<Persona> personas = personaRepository.findInactiveProducts();
        return new ResponseEntity<>(new Message(personas, "Listado de personas", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findProductByCurp(String curp) {
        logger.info("Buscando persona con el curp: {}", curp);
        Optional<Persona> personaOptional = personaRepository.findProductByCurp(curp);
        if (personaOptional.isPresent()) {
            return new ResponseEntity<>(new Message(personaOptional.get(), "Persona encontrada", TypesResponse.SUCCESS), HttpStatus.OK);
        } else {
            logger.warn("La persona con id {} no existe", curp);
            return new ResponseEntity<>(new Message("Persona no encontrada", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id) {
        logger.info("Buscando persona con el id: {}", id);
        Optional<Persona> personaOptional = personaRepository.findById(id);
        if (personaOptional.isPresent()) {
            return new ResponseEntity<>(new Message(personaOptional.get(), "Persona encontrada", TypesResponse.SUCCESS), HttpStatus.OK);
        } else {
            logger.warn("La persona con id {} no existe", id);
            return new ResponseEntity<>(new Message("Persona no encontrada", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(Persona persona) {
        logger.info("Guardando nueva persona: {}", persona);

        if (persona.getNombre().length() > 30) {
            logger.warn("El nombre excede el número de caracteres.");
            throw new IllegalArgumentException("El nombre excede el número de caracteres.");
        }
        if (persona.getApellidos().length() > 70) {
            logger.warn("Los apellidos exceden el número de caracteres.");
            throw new IllegalArgumentException("Los apellidos exceden el número de caracteres.");
        }

        if (personaRepository.existsByCurp(persona.getCurp())) {
            logger.warn("Ya existe una persona con el CURP: {}", persona.getCurp());
            throw new IllegalArgumentException("Ya existe una persona con el CURP proporcionado.");
        }
        if (personaRepository.existsByTelefono(persona.getTelefono())) {
            logger.warn("Ya existe una persona con el teléfono: {}", persona.getTelefono());
            throw new IllegalArgumentException("Ya existe una persona con el teléfono proporcionado.");
        }

        persona.setStatus(true);
        Persona savedPersona = personaRepository.save(persona);
        return new ResponseEntity<>(new Message(savedPersona, "Persona guardada correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }



    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(Persona persona) {
        logger.info("Actualizando la persona con id: {}", persona.getId());
        Optional<Persona> personaOptional = personaRepository.findById(persona.getId());
        if (personaOptional.isPresent()) {
            Persona personaNew = personaOptional.get();

            if (persona.getNombre().length() > 30) {
                logger.warn("El nombre excede el número de caracteres.");
                throw new IllegalArgumentException("El nombre excede el número de caracteres.");
            }
            if (persona.getApellidos().length() > 70) {
                logger.warn("Los apellidos exceden el número de caracteres.");
                throw new IllegalArgumentException("Los apellidos exceden el número de caracteres.");
            }

            if (personaRepository.existsByCurpAndIdNot(persona.getCurp(), persona.getId())) {
                logger.warn("Ya existe una persona con el CURP: {}", persona.getCurp());
                throw new IllegalArgumentException("Ya existe una persona con el CURP proporcionado.");
            }
            if (personaRepository.existsByTelefonoAndIdNot(persona.getTelefono(), persona.getId())) {
                logger.warn("Ya existe una persona con el teléfono: {}", persona.getTelefono());
                throw new IllegalArgumentException("Ya existe una persona con el teléfono proporcionado.");
            }

            logger.info("Persona encontrada. Actualizando datos.");
            personaNew.setNombre(persona.getNombre());
            personaNew.setApellidos(persona.getApellidos());
            personaNew.setTelefono(persona.getTelefono());
            personaNew.setCurp(persona.getCurp());
            Persona updatedPersona = personaRepository.save(personaNew);

            return new ResponseEntity<>(new Message(updatedPersona, "Persona actualizada correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
        } else {
            logger.warn("La persona con id {} no existe", persona.getId());
            return new ResponseEntity<>(new Message("La persona no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
    }



    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(Long id) {
        logger.info("Cambiando estado de la persona con id: {}", id);
        Optional<Persona> personaOptional = personaRepository.findById(id);

        if (personaOptional.isPresent()) {
            Persona personaNew = personaOptional.get();
            personaNew.setStatus(!personaNew.isStatus());

            Persona updatedPersona = personaRepository.save(personaNew);
            logger.info("Estado de la persona actualizado correctamente");
            return new ResponseEntity<>(new Message(updatedPersona, "Estado de la persona actualizado correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
        } else {
            logger.warn("La persona con id {} no existe", id);
            return new ResponseEntity<>(new Message("La persona no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }
    }
}
