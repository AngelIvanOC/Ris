package com.example.conexionbd.document.control;

import com.example.conexionbd.document.model.Document;
import com.example.conexionbd.document.model.DocumentRepository;
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
public class DocumentService {

    private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);

    private final DocumentRepository documentRepository;

    @Autowired
    public DocumentService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Document> documents = documentRepository.findAll();
        logger.info("La búsqueda ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(documents,"Listado de documentos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(Document document) {
        if(document.getName().length() > 30) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        if(document.getDescription().length() > 70) {
            return new ResponseEntity<>(new Message("La descripción excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        document = documentRepository.saveAndFlush(document);
        if(document == null){
            return new ResponseEntity<>(new Message("El documento no se registró",TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }

        logger.info("El registro ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(document,"El documento se registró correctamente",TypesResponse.SUCCESS),HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(Document document) {
        Optional<Document> documentOptional = documentRepository.findById(document.getId());
        if(!documentOptional.isPresent()){
            return new ResponseEntity<>(new Message("El documento no existe",TypesResponse.ERROR),HttpStatus.NOT_FOUND);
        }

        Document documentUpdated = documentOptional.get();
        if(document.getName().length() > 30) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        if(document.getDescription().length() > 70) {
            return new ResponseEntity<>(new Message("La descripción excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }

        documentUpdated.setName(document.getName());
        documentUpdated.setDescription(document.getDescription());
        documentUpdated = documentRepository.saveAndFlush(documentUpdated);
        if(documentUpdated == null){
            return new ResponseEntity<>(new Message("El documento no se actualizó",TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }
        logger.info("La actualización ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(document,"El documento se actualizó correctamente",TypesResponse.SUCCESS),HttpStatus.OK);
    }

}
