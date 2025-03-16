package com.itowaka044.todo_list_rpg.services;

import com.itowaka044.todo_list_rpg.entities.Player;
import com.itowaka044.todo_list_rpg.repositories.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player savePlayer(Player player){
        return playerRepository.save(player);
    }

    public void deletePlayerById(int id){
        playerRepository.deleteById((long) id);
    }
}
