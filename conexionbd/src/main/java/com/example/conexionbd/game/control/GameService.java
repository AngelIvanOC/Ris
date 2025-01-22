package com.example.conexionbd.game.control;


import com.example.conexionbd.game.model.Game;
import com.example.conexionbd.game.model.GameDTO;
import com.example.conexionbd.game.model.GameRepository;
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
public class GameService {
    private static final Logger logger = LoggerFactory.getLogger(GameService.class);

    private final GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Transactional(readOnly = true)
    public ResponseEntity<Message> findAll() {
        List<Game> games = gameRepository.findAll();
        logger.info("La búsqueda ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(games,"Listado de juegos", TypesResponse.SUCCESS), HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> save(GameDTO dto) {
        if(dto.getName().length() > 20) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        if(dto.getDescription().length() > 50) {
            return new ResponseEntity<>(new Message("La descripción excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        Game game = new Game(dto.getName(),dto.getDescription(),true);
        game = gameRepository.saveAndFlush(game);
        if(game == null){
            return new ResponseEntity<>(new Message("El juego no se registró",TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }
        logger.info("El registro ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(game,"El juego se registró correctamente",TypesResponse.SUCCESS),HttpStatus.CREATED);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> update(GameDTO dto) {
        Optional<Game> gameOptional = gameRepository.findById(dto.getId());
        if(!gameOptional.isPresent()){
            return new ResponseEntity<>(new Message("El juego no existe",TypesResponse.ERROR),HttpStatus.NOT_FOUND);
        }
        if(dto.getName().length() > 30) {
            return new ResponseEntity<>(new Message("El nombre excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }
        if(dto.getDescription().length() > 50) {
            return new ResponseEntity<>(new Message("La descripción excede el número de caracteres",TypesResponse.WARNING),HttpStatus.BAD_REQUEST);
        }

        Game game = gameOptional.get();
        game.setName(dto.getName());
        game.setDescription(dto.getDescription());
        game = gameRepository.saveAndFlush(game);
        if(game == null){
            return new ResponseEntity<>(new Message("El juego no se actualizó",TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }
        logger.info("La actualización ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(game,"El juego se actualizó correctamente",TypesResponse.SUCCESS),HttpStatus.OK);
    }

    @Transactional(rollbackFor = {SQLException.class})
    public ResponseEntity<Message> changeStatus(GameDTO dto) {
        Optional<Game> gameOptional = gameRepository.findById(dto.getId());
        if(!gameOptional.isPresent()){
            return new ResponseEntity<>(new Message("El juego no existe",TypesResponse.ERROR),HttpStatus.NOT_FOUND);
        }
        Game game = gameOptional.get();
        game.setStatus(!game.isStatus());
        game = gameRepository.saveAndFlush(game);
        if(game == null){
            return new ResponseEntity<>(new Message("El estado del juego no se actualizó",TypesResponse.ERROR),HttpStatus.BAD_REQUEST);
        }
        logger.info("La actualización ha sido realizada correctamente");
        return new ResponseEntity<>(new Message(game,"El estado del juego se actualizó correctamente",TypesResponse.SUCCESS),HttpStatus.OK);
    }
}