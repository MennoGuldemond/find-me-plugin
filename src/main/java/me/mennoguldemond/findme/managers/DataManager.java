package me.mennoguldemond.findme.managers;

import com.google.gson.Gson;
import me.mennoguldemond.findme.FindMe;
import me.mennoguldemond.findme.models.GameData;
import org.bukkit.Bukkit;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DataManager {
    public GameData gameData;
    private final String filePath;
    private final Gson gson;
    private final FindMe findMe;

    public DataManager(FindMe findMe) {
        this.gson = new Gson();
        this.findMe = findMe;
        this.filePath = this.findMe.getDataFolder().getPath() + "\\player-data.json";

        this.gameData = this.loadData();
        if(this.gameData == null) {
            this.gameData = new GameData();
        } else {
            Bukkit.getLogger().info("Game data was loaded from file.");
        }
    }

    public void saveData() {
        try {
            if (this.gameData != null) {
                FileWriter writer = new FileWriter(filePath);
                this.gson.toJson(this.gameData, writer);
                writer.flush();
                writer.close();
            } else {
                Bukkit.getLogger().warning("game data was empty");
            }
        } catch (IOException e) {
            Bukkit.getLogger().warning(e.toString());
        }
    }

    public GameData loadData() {
        try {
            File file = new File(this.filePath);
            if (file.createNewFile()) {
                Bukkit.getLogger().info("player data file created: " + file.getName());
                this.saveData();
            }

            Reader reader = Files.newBufferedReader(Paths.get(filePath));
            return this.gson.fromJson(reader, GameData.class);
        } catch (Exception e) {
            Bukkit.getLogger().warning(e.toString());
            return null;
        }
    }
}