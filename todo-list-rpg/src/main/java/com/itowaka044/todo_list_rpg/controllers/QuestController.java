package com.itowaka044.todo_list_rpg.controllers;

import com.itowaka044.todo_list_rpg.entities.Quest;
import com.itowaka044.todo_list_rpg.services.QuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/quest")
public class QuestController {

    private final QuestService questService;

    public QuestController(QuestService questService) {
        this.questService = questService;
    }

    @PostMapping("/create")
    public ResponseEntity<Quest> createQuest(@RequestBody Quest quest) {
        Quest createdQuest = questService.saveQuest(quest);
        return ResponseEntity.status(201).body(createdQuest);
    }

    @GetMapping("/list-all")
    public ResponseEntity<List<Quest>> listAllQuests() {
        List<Quest> quests = questService.listAllQuests();
        return ResponseEntity.ok(quests);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteQuestById(@PathVariable Long id) {
        try {
            questService.deleteQuestById(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
