package com.itowaka044.todo_list_rpg.services;

import com.itowaka044.todo_list_rpg.entities.Quest;
import com.itowaka044.todo_list_rpg.repositories.QuestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class QuestService {

    @Autowired
    private QuestRepository questRepository;

    public Quest saveQuest(Quest quest){
        return questRepository.save(quest);
    }

    public void deleteQuestById(long id){
        questRepository.deleteById(id);
    }

    public List<Quest> listAllQuests(){
        return questRepository.findAll();
    }

}
