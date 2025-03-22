package com.itowaka044.todo_list_rpg.controllers;

import com.itowaka044.todo_list_rpg.entities.Player;
import com.itowaka044.todo_list_rpg.services.PlayerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping("/create")
    public ResponseEntity<Player> savePlayer(@RequestBody Player player) {
        Player createdPlayer = playerService.createPlayer(player);
        return ResponseEntity.status(201).body(createdPlayer);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlayerById(@PathVariable Long id) {
        try {
            playerService.deletePlayerById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}