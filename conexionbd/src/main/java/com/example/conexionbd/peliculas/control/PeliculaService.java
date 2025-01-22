package com.example.conexionbd.peliculas.control;

import com.example.conexionbd.peliculas.model.Pelicula;
import com.example.conexionbd.peliculas.model.PeliculaDTO;
import com.example.conexionbd.peliculas.model.PeliculaRepository;
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
public class PeliculaService {
    private static final Logger logger = LoggerFactory.getLogger(PeliculaService.class);

    private final PeliculaRepository peliculaRepository;

    @Autowired
    public PeliculaService(PeliculaRepository peliculaRepository) {
        this.peliculaRepository = peliculaRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Pelicula> peliculas = peliculaRepository.findAllByStatusIsTrue();
        logger.info("La búsqueda ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(peliculas, "Listado de peliculas", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(PeliculaDTO dto) {
        if (dto.getName().length() > 30) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (dto.getDescription().length() > 50) {
            return new ResponseEntity<>(new Message("La descripción excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Pelicula pelicula = new Pelicula(dto.getName(), dto.getDescription(), dto.getGenero(), dto.getCantidad(), dto.getPrecio(), true);
        pelicula = peliculaRepository.saveAndFlush(pelicula);
        if (pelicula == null) {
            return new ResponseEntity<>(new Message("La película no se registró", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        logger.info("El registro ha sido realizado correctamente");
        return new ResponseEntity<>(new Message(pelicula, "La película se registró correctamente", TypesResponse.SUCCESS), HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(PeliculaDTO dto) {
        Optional<Pelicula> peliculaOptional = peliculaRepository.findById(dto.getId());
        if (!peliculaOptional.isPresent()) {
            return new ResponseEntity<>(new Message("La película no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        if (dto.getName().length() > 30) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }
        if (dto.getDescription().length() > 50) {
            return new ResponseEntity<>(new Message("La descripción excede el número de caracteres", TypesResponse.WARNING), HttpStatus.BAD_REQUEST);
        }

        Pelicula pelicula = peliculaOptional.get();
        pelicula.setName(dto.getName());
        pelicula.setDescription(dto.getDescription());
        pelicula.setGenero(dto.getGenero());
        pelicula.setCantidad(dto.getCantidad());
        pelicula.setPrecio(dto.getPrecio());
        pelicula = peliculaRepository.saveAndFlush(pelicula);
        if (pelicula == null) {
            return new ResponseEntity<>(new Message("La película no se actualizó", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        logger.info("La actualización ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(pelicula, "La película se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(PeliculaDTO dto) {
        Optional<Pelicula> peliculaOptional = peliculaRepository.findById(dto.getId());
        if (!peliculaOptional.isPresent()) {
            return new ResponseEntity<>(new Message("La película no existe", TypesResponse.ERROR), HttpStatus.NOT_FOUND);
        }

        Pelicula pelicula = peliculaOptional.get();
        pelicula.setStatus(!pelicula.isStatus());
        pelicula = peliculaRepository.saveAndFlush(pelicula);
        if (pelicula == null) {
            return new ResponseEntity<>(new Message("El estado de la película no se actualizó", TypesResponse.ERROR), HttpStatus.BAD_REQUEST);
        }

        logger.info("La actualización del estado ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(pelicula, "El estado de la película se actualizó correctamente", TypesResponse.SUCCESS), HttpStatus.OK);
    }
}
