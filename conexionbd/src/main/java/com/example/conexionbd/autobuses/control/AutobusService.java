package com.example.conexionbd.autobuses.control;

import com.example.conexionbd.autobuses.model.Autobus;
import com.example.conexionbd.autobuses.model.AutobusDTO;
import com.example.conexionbd.autobuses.model.AutobusRepository;
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
public class AutobusService {
    private static final Logger logger = LoggerFactory.getLogger(AutobusService.class);

    private final AutobusRepository autobusRepository;

    @Autowired
    public AutobusService(AutobusRepository autobusRepository) {
        this.autobusRepository = autobusRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Autobus> autobuses = autobusRepository.findAll();
        logger.info("La búsqueda de todos los autobuses ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(autobuses, "Listado de autobuses", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findById(Long id) {
        Optional<Autobus> autobusOptional = autobusRepository.findById(id);
        if (!autobusOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El autobús no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        logger.info("Búsqueda del autobús con ID: {}", id);
        return new ResponseEntity<>(new Message(autobusOptional.get(), "Autobús encontrado", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findByPlaca(String placa) {
        Autobus autobus = autobusRepository.findByPlaca(placa);
        if (autobus == null) {
            return new ResponseEntity<>(new Message("El autobús con esa placa no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        logger.info("Búsqueda del autobús con placa: {}", placa);
        return new ResponseEntity<>(new Message(autobus, "Autobús encontrado", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findByAnioRange(int anioInicio, int anioFin) {
        List<Autobus> autobuses = autobusRepository.findByAnioBetween(anioInicio, anioFin);
        if (autobuses.isEmpty()) {
            return new ResponseEntity<>(new Message("No se encontraron autobuses en el rango de años especificado", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        logger.info("Búsqueda de autobuses en el rango de años: {} - {}", anioInicio, anioFin);
        return new ResponseEntity<>(new Message(autobuses, "Autobuses encontrados", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(AutobusDTO dto) {
        if (dto.getPlaca().length() > 10) {
            return new ResponseEntity<>(new Message("La placa excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        if (autobusRepository.findByPlaca(dto.getPlaca()) != null) {
            return new ResponseEntity<>(new Message("La placa ya está registrada", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Autobus autobus = new Autobus(dto.getMarca(), dto.getModelo(), dto.getAnio(), dto.getPlaca(), true);
        autobus = autobusRepository.saveAndFlush(autobus);
        if (autobus == null) {
            return new ResponseEntity<>(new Message("El autobús no se registró", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        logger.info("El registro del autobús ha sido realizado correctamente");
        return new ResponseEntity<>(new Message(autobus, "El autobús se registró correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(AutobusDTO dto) {
        Optional<Autobus> autobusOptional = autobusRepository.findById(dto.getId());
        if (!autobusOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El autobús no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Autobus autobus = autobusOptional.get();
        autobus.setMarca(dto.getMarca());
        autobus.setModelo(dto.getModelo());
        autobus.setAnio(dto.getAnio());
        autobus.setPlaca(dto.getPlaca());

        autobus = autobusRepository.saveAndFlush(autobus);
        logger.info("El autobús con ID: {} ha sido actualizado", dto.getId());
        return new ResponseEntity<>(new Message(autobus, "El autobús se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(AutobusDTO dto) {
        Optional<Autobus> autobusOptional = autobusRepository.findById(dto.getId());
        if (!autobusOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El autobús no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Autobus autobus = autobusOptional.get();
        autobus.setStatus(!autobus.isStatus());
        autobusRepository.saveAndFlush(autobus);

        String message = autobus.isStatus() ? "Autobús activado" : "Autobús desactivado";
        logger.info("El estado del autobús con ID: {} ha sido cambiado", dto.getId());
        return new ResponseEntity<>(new Message(autobus, message, TypesResponse.SUCCESS), HttpStatus.OK);
    }
}
