package com.gym.gymclub;

/**
 * Created by ahmed on 5/10/17.
 */

public class Game {

    private String gameName;
    private String fees;
    private String description;

    public Game(String gameName, String fees, String description) {
        this.gameName = gameName;
        this.fees = fees;
        this.description = description;
    }

    public String getGameName() {
        return gameName;
    }

    public String getFees() {
        return fees;
    }

    public String getDescription() {
        return description;
    }
}
