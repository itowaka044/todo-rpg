package com.itowaka044.todo_list_rpg.controllers;

import com.itowaka044.todo_list_rpg.entities.Quest;
import com.itowaka044.todo_list_rpg.services.QuestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quest")
public class QuestController {

    @Autowired
    private QuestService questService;

    @PostMapping
    public void createQuest(Quest quest){
        questService.saveQuest(quest);
    }

    @GetMapping
    public List<Quest> listAllQuests(){
        return questService.listAllQuests();
    }

    @DeleteMapping
    public void deleteQuestById(long id){
        questService.deleteQuestById(id);
    }
}
