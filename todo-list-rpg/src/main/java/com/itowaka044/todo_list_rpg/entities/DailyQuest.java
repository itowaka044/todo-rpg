package com.itowaka044.todo_list_rpg.entities;

import com.itowaka044.todo_list_rpg.entities.enums.QuestTypeAttributes;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("daily")
public class DailyQuest extends Quest{

    public Boolean refresh;
    public LocalDateTime lastComplete = LocalDateTime.of(2020,1,1,1,1,1);

    public DailyQuest() {
        this.refresh = true;
    }

    public DailyQuest(String questName, String questDesc, int xpGained, int questValue, QuestTypeAttributes questType) {
        super(questName, questDesc, xpGained, questValue, questType);
        this.refresh = true;
    }

    public boolean canRefresh(LocalDateTime lastComplete) {
        LocalDateTime now = LocalDateTime.now();
        LocalDate lastCompleteDateOnly = lastComplete.toLocalDate();
        LocalDate actualDate = now.toLocalDate();

        return !lastCompleteDateOnly.equals(actualDate) || lastComplete == null;
    }

    @Override
    public void questReward(Player player){
        if (canRefresh(lastComplete)){
            refresh = true;
        }
        if (refresh){
            super.questReward(player);
            refresh = false;
            lastComplete = LocalDateTime.now();
        }
    }

    @Override
    public String toString(){

        String status;

        if(refresh) {
            status = "nao";
        }else{
            status = "sim";
        }

        return "\nnome da quest: " + questName
                + "\ndescricao: " + questDesc
                + "\nstatus da quest: " + questStatus
                + "\nxp da quest: " + xpGained + " xp"
                + "\nfoi feita: " + status;
    }
}