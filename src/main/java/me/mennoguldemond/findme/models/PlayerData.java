package me.mennoguldemond.findme.models;

import java.util.UUID;

public class PlayerData {

    public UUID id;
    public int jos;
    public int score;

    public PlayerData(UUID id) {
        this.id = id;
        this.jos = 0;
        this.score = 0;
    }

}

