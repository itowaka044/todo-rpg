package com.itowaka044.todo_list_rpg.entities;

import com.itowaka044.todo_list_rpg.entities.enums.QuestAttributes;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "quest_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "quest")
public class Quest{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long questId;

    public String questName;
    public String questDesc;
    public Integer xpGained;
    public boolean questStatus;
    public Integer questValue;
    public QuestAttributes questAttributes;


    public Quest() {
    }

    public Quest(Long questId, String questName, String questDesc, Integer xpGained, Integer questValue, QuestAttributes questType) {
        this.questId = questId;
        this.questName = questName;
        this.questDesc = questDesc;
        this.xpGained = xpGained;
        this.questValue = questValue;
        this.questAttributes = questType;

        questStatus = true;
    }

    public Quest(Long questId, String questName, String questDesc, Integer xpGained, Integer questValue) {
        this.questId = questId;
        this.questName = questName;
        this.questDesc = questDesc;
        this.xpGained = xpGained;
        this.questValue = questValue;
    }

    public Long getQuestId() {
        return questId;
    }

    public void setQuestId(Long questId) {
        this.questId = questId;
    }

    public String getQuestName() {
        return questName;
    }

    public void setQuestName(String questName) {
        this.questName = questName;
    }

    public String getQuestDesc() {
        return questDesc;
    }

    public void setQuestDesc(String questDesc) {
        this.questDesc = questDesc;
    }

    public Integer getXpGained() {
        return xpGained;
    }

    public void questReward(Player player){
        if (!questStatus) {
            System.out.println("\nquest completa");
            player.playerXp += xpGained;

            switch(questAttributes){

                case QuestAttributes.STRENGTH -> {
                    System.out.println("+1 strength");
                    player.upStrength();
                }

                case QuestAttributes.INTELLIGENCE -> {
                    System.out.println("+1 intelligence");
                    player.upIntelligence();
                }

                case QuestAttributes.CHARISMA -> {
                    System.out.println("+1 charisma");
                    player.upCharisma();
                }

                case QuestAttributes.CONSTITUTION -> {
                    System.out.println("+1 constitution");
                    player.upConstitution();
                }

            }

            if (player.isNextLvl()){
                player.gainXp(xpGained);
                questStatus = true;
            }
        } else {
            System.out.println("\nquest ja foi feita");
        }
    }

    public String toString(){
        return "\nnome da quest: " + questName
                + "\ndescricao: " + questDesc
                + "\nstatus da quest: " + questStatus
                + "\nxp da quest: " + xpGained + " xp";
    }
}