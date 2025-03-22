package com.itowaka044.todo_list_rpg.services;

import com.itowaka044.todo_list_rpg.entities.Player;
import com.itowaka044.todo_list_rpg.repositories.PlayerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Transactional
    public Player createPlayer(Player player) {
        return playerRepository.save(player);
    }

    @Transactional
    public void deletePlayerById(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        if (player.isPresent()) {
            playerRepository.deleteById(id);
        } else {
            throw new RuntimeException("player not found with ID: " + id);
        }
    }
}