package me.mennoguldemond.findme.models;

import org.bukkit.block.Block;

import java.util.ArrayList;

public class GameData {
    public boolean isTowerBuild;
    public ArrayList<PlayerData> playerData;
    public String cursedBlock;

    public GameData() {
        this.playerData = new ArrayList<>();
        this.isTowerBuild = false;
    }
}