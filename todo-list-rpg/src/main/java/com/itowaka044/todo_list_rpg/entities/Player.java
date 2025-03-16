package com.itowaka044.todo_list_rpg.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "player")
public class Player{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long playerId;

    public String playerName;
    public Integer playerXp=0;
    public Short playerLvl=1;

    public Short strength=0, intelligence=0, constitution=0, charisma=0;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Short getCharisma() {
        return charisma;
    }

    public Short getConstitution() {
        return constitution;
    }

    public Short getIntelligence() {
        return intelligence;
    }

    public Short getPlayerLvl() {
        return playerLvl;
    }

    public Short getStrength() {
        return strength;
    }

    public Player(String playerName) {
        this.playerName = playerName;
        this.playerLvl = 1;
        this.playerXp = 1;
    }

    public int xpGap(short level){
        int xpGap;
        if(playerLvl > 100){
            xpGap = 115 * playerLvl;
        } else {
            xpGap = 120 * playerLvl;
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
