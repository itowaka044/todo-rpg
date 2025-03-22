package com.itowaka044.todo_list_rpg.entities;

import com.itowaka044.todo_list_rpg.entities.enums.QuestTypeAttributes;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "quest", discriminatorType = DiscriminatorType.STRING)
@Table(name = "quest")
public class Quest{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false)
    protected String questName;

    @Column(nullable = false)
    public String questDesc;

    @Column(nullable = false)
    public Integer xpGained;

    @Column(nullable = false)
    public Boolean questStatus;

    @Column(nullable = false)
    public Integer questValue;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestTypeAttributes questType;


    public Quest() {
    }

    public Quest(String questName, String questDesc, Integer xpGained, Integer questValue, QuestTypeAttributes questType) {
        this.questName = questName;
        this.questDesc = questDesc;
        this.xpGained = xpGained;
        this.questValue = questValue;
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

    public Integer getQuestValue() {
        return questValue;
    }

    public void setQuestValue(Integer questValue) {
        this.questValue = questValue;
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

    public String toString(){
        return "\nnome da quest: " + questName
                + "\ndescricao: " + questDesc
                + "\nstatus da quest: " + questStatus
                + "\nxp da quest: " + xpGained + " xp";
    }
}