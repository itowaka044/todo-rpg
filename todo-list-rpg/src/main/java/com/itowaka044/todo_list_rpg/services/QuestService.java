package com.itowaka044.todo_list_rpg.services;

import com.itowaka044.todo_list_rpg.entities.DailyQuest;
import com.itowaka044.todo_list_rpg.entities.Quest;
import com.itowaka044.todo_list_rpg.repositories.QuestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestService {

    private final QuestRepository questRepository;

    public QuestService(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    public Quest saveQuest(Quest quest) {
        return questRepository.save(quest);
    }

    public DailyQuest saveDailyQuest(DailyQuest quest){
        return questRepository.save(quest);
    }

    public void changeQuestName(Long id, String newName){
        questRepository.changeQuestName(id, newName);
    }

    @Transactional
    public void deleteQuestById(Long id) {
        Optional<Quest> quest = questRepository.findById(id);
        if (quest.isPresent()) {
            questRepository.deleteById(id);
        } else {
            throw new RuntimeException("quest not found with id: " + id);
        }
    }

    public List<Quest> listAllQuests() {
        return questRepository.findAll();
    }
}
