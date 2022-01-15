package me.mennoguldemond.findme.models;

import org.bukkit.Location;

import java.util.UUID;

public class PlayerData {

    public UUID id;
    public int jos;
    public int score;
    public Vector3 spawnLocation;

    public PlayerData(UUID id) {
        this.id = id;
        this.jos = 0;
        this.score = 0;
    }

    public PlayerData(UUID id, Location location) {
        this.id = id;
        this.jos = 0;
        this.score = 0;
        this.spawnLocation = new Vector3(location.getX(), location.getY(), location.getZ());
    }

}

