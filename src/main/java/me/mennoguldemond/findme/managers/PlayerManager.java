package me.mennoguldemond.findme.managers;

import me.mennoguldemond.findme.FindMe;
import me.mennoguldemond.findme.models.PlayerData;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerManager {
    private final FindMe findMe;

    public ArrayList<Player> readyPlayers = new ArrayList<>();

    public PlayerManager(FindMe findMe) {
        this.findMe = findMe;
    }

    public void readyPlayer(Player player) {
        this.readyPlayers.add(player);
    }

    public void unreadyPlayer(Player player) {
        this.readyPlayers.remove(player);
    }

    public void handlePlayerJoin(Player player) {
        if(this.findMe.dataManager.gameData.playerData.stream().noneMatch(p -> p.id.equals(player.getUniqueId()))) {
            PlayerData data = new PlayerData(player.getUniqueId(), player.getLocation());
            this.findMe.dataManager.gameData.playerData.add(data);
        }
    }

    public PlayerData getPlayerData(Player player) {
        return this.findMe.dataManager.gameData.playerData.stream().filter(p -> p.id.equals(player.getUniqueId())).findFirst().orElse(null);
    }

    public void clearReadyPlayers() {
        for (Player player:this.readyPlayers) {
            // Set full food
            player.setFoodLevel(20);
        }
        this.readyPlayers.clear();
    }
}
