package com.itowaka044.todo_list_rpg.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Player{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(nullable = false, unique = true)
    private String playerName;

    @Column(nullable = false)
    private Integer playerXp;

    @Column(nullable = false)
    private Short playerLvl;

    @Column(nullable = false)
    private Short strength, intelligence, constitution, charisma;

    public Player() {}

    public Player(String playerName) {
        this.playerName = playerName;
        this.playerLvl = 1;
        this.playerXp = 0;
        this.strength = 0;
        this.intelligence = 0;
        this.constitution = 0;
        this.charisma = 0;
    }

    @PrePersist
    protected void onCreate() {
        if (playerLvl == null) playerLvl = 1;
        if (playerXp == null) playerXp = 0;
        if (strength == null) strength = 0;
        if (intelligence == null) intelligence = 0;
        if (constitution == null) constitution = 0;
        if (charisma == null) charisma = 0;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Integer getPlayerXp() {
        return playerXp;
    }

    public void setPlayerXp(Integer playerXp) {
        this.playerXp = playerXp;
    }

    public Short getPlayerLvl() {
        return playerLvl;
    }

    public void setPlayerLvl(Short playerLvl) {
        this.playerLvl = playerLvl;
    }

    public Short getStrength() {
        return strength;
    }

    public void setStrength(Short strength) {
        this.strength = strength;
    }

    public Short getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(Short intelligence) {
        this.intelligence = intelligence;
    }

    public Short getConstitution() {
        return constitution;
    }

    public void setConstitution(Short constitution) {
        this.constitution = constitution;
    }

    public Short getCharisma() {
        return charisma;
    }

    public void setCharisma(Short charisma) {
        this.charisma = charisma;
    }

    public int xpGap(short level){
        int xpGap;
        if(playerLvl < 100){
            xpGap = 120 * playerLvl;
        } else {
            xpGap = 115 * playerLvl;
        }
        return xpGap;
    }

    public boolean isNextLvl(){
        return playerXp >= xpGap(playerLvl);
    }

    public void gainXp(int quantXp){
        playerXp += quantXp;
        if(!isNextLvl()) {
            System.out.println("\nganhou xp");
        } else {
            playerXp = playerXp - xpGap(playerLvl);
            playerLvl++;

            System.out.println("\nupou de lvl");
        }
    }

    public void lvlUp(Integer xpGained){
        playerXp = playerXp - xpGap(playerLvl);
        playerLvl++;
    }

    public void changePlayerName(String actualName){
        playerName = actualName;

        System.out.println("\nnome atualizado");
    }


    public void upStrength() {
        strength++;
    }

    public void upIntelligence() {
        intelligence++;
    }

    public void upConstitution() {
        constitution++;
    }

    public void upCharisma() {
        charisma++;
    }

    public String toString(){
        return "\nnome: " + playerName
                + "\nlevel: " + playerLvl
                + "\nxp do player: " + playerXp
                + "\nstrength: " + strength
                + "\nintelligence: " + intelligence
                + "\ncharisma: " + charisma
                + "\nconstitution: " + constitution;
    }
}
