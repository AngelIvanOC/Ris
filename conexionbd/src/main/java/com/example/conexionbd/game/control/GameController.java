package com.example.conexionbd.game.control;


import com.example.conexionbd.game.model.GameDTO;
import com.example.conexionbd.utils.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
@CrossOrigin(origins = {"*"},methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class GameController {

    private final GameService gameService;

    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/all")
    public ResponseEntity<Message> getAllDocument() {
        return gameService.findAll();
    }

    @PostMapping("/save")
    public ResponseEntity<Message> saveDocument(@Validated(GameDTO.Register.class) @RequestBody GameDTO dto) {
        return gameService.save(dto);
    }

    @PutMapping("/update")
    public ResponseEntity<Message> updateDocument(@Validated(GameDTO.Modify.class) @RequestBody GameDTO dto) {
        return gameService.update(dto);
    }

    @PutMapping("/change-status")
    public ResponseEntity<Message> changeStatus(@Validated(GameDTO.ChangeStatus.class) @RequestBody GameDTO dto) {
        return gameService.changeStatus(dto);
    }

}