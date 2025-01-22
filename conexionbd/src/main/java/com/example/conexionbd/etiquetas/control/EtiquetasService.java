package com.example.conexionbd.etiquetas.control;

import com.example.conexionbd.etiquetas.model.Etiquetas;
import com.example.conexionbd.etiquetas.model.EtiquetasRepository;
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
public class EtiquetasService {

    private static final Logger logger = LoggerFactory.getLogger(EtiquetasService.class);

    private final EtiquetasRepository etiquetasRepository;

    @Autowired
    public EtiquetasService(EtiquetasRepository etiquetasRepository) {
        this.etiquetasRepository = etiquetasRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Etiquetas> documents = etiquetasRepository.findAll();
        logger.info("La búsqueda ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(documents, "Listado de documentos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(Etiquetas etiquetas) {
        if (etiquetas.getName().length() > 30) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (etiquetas.getColor().length() > 70) {
            return new ResponseEntity<>(new Message("La descripción excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        etiquetas = etiquetasRepository.saveAndFlush(etiquetas);
        if (etiquetas == null) {
            return new ResponseEntity<>(new Message("El documento no se registró", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        logger.info("El registro ha sido realizado correctamente");
        return new ResponseEntity<>(new Message(etiquetas, "El documento se registró correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(Etiquetas etiquetas) {
        Optional<Etiquetas> documentOptional = etiquetasRepository.findById(etiquetas.getId());
        if (!documentOptional.isPresent()) {
            return new ResponseEntity<>(new Message("El documento no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Etiquetas etiquetaUpdate = documentOptional.get();
        if (etiquetas.getName().length() > 30) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (etiquetas.getColor().length() > 70) {
            return new ResponseEntity<>(new Message("La descripción excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        etiquetaUpdate.setName(etiquetas.getName());
        etiquetaUpdate.setColor(etiquetas.getColor());
        etiquetaUpdate = etiquetasRepository.saveAndFlush(etiquetaUpdate);
        if (etiquetaUpdate == null) {
            return new ResponseEntity<>(new Message("El documento no se actualizó", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }
        logger.info("La actualización ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(etiquetaUpdate, "El documento se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }
}
