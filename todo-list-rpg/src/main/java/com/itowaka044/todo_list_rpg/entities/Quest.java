package com.itowaka044.todo_list_rpg.entities;

import com.itowaka044.todo_list_rpg.entities.enums.QuestTypeAttributes;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "quest_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "quest")
public class Quest{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "name", nullable = false)
    protected String questName;

    @Column(name = "desc", nullable = false)
    public String questDesc;

    @Column(name = "xp", nullable = false)
    public Integer xpGained;

    @Column(name = "status", nullable = false)
    public Boolean questStatus;

    @Column(name = "difficult", nullable = false)
    public Integer questDifficult;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private QuestTypeAttributes questType;


    public Quest() {
    }

    public Quest(String questName, String questDesc, Integer xpGained, Integer questDifficult, QuestTypeAttributes questType) {
        this.questName = questName;
        this.questDesc = questDesc;
        this.xpGained = xpGained;
        this.questDifficult = questDifficult;
        this.questType = questType;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
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

    public void setXpGained(Integer xpGained) {
        this.xpGained = xpGained;
    }

    public Boolean getQuestStatus() {
        return questStatus;
    }

    public void setQuestStatus(Boolean questStatus) {
        this.questStatus = questStatus;
    }

    public Integer getQuestDifficult() {
        return questDifficult;
    }

    public void setQuestDifficult(Integer questDifficult) {
        this.questDifficult = questDifficult;
    }

    public QuestTypeAttributes getQuestType() {
        return questType;
    }

    public void setQuestType(QuestTypeAttributes questType) {
        this.questType = questType;
    }

    public void questReward(Player player){
        questStatus = false;
        player.gainXp(this.xpGained);

        switch (questType) {
            case STRENGTH -> player.upStrength();
            case INTELLIGENCE -> player.upIntelligence();
            case CHARISMA -> player.upCharisma();
            case CONSTITUTION -> player.upConstitution();
        }

        if (player.isNextLvl()){
            player.lvlUp(xpGained);
        }
    }

    public void changeQuestName(String name){
        questName = name;
    }

    public String toString(){
        return "\nnome da quest: " + questName
                + "\ndescricao: " + questDesc
                + "\nstatus da quest: " + questStatus
                + "\nxp da quest: " + xpGained + " xp";
    }
}