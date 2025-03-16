package com.itowaka044.todo_list_rpg.entities;

import com.itowaka044.todo_list_rpg.entities.enums.QuestAttributes;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("daily")
public class DailyQuest extends Quest{

    public boolean refreshQuest;

    public DailyQuest(boolean refreshQuest) {
        this.refreshQuest = refreshQuest;
    }

    public DailyQuest(Long questId, String questName, String questDesc, int xpGained, int questValue, QuestAttributes questType) {
        super(questId, questName, questDesc, xpGained, questValue, questType);
        this.refreshQuest = true;
    }

    public void restartQuest(){
        questStatus = false;
    }

    @Override
    public String toString(){

        String status;

        if(!refreshQuest) {
            status = "sim";
        }else{
            status = "nao";
        }

        return "\nnome da quest: " + questName
                + "\ndescricao: " + questDesc
                + "\nstatus da quest: " + questStatus
                + "\nxp da quest: " + xpGained + " xp"
                + "\nja foi feita: " + status;
    }
}