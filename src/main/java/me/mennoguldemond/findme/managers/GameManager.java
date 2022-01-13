package me.mennoguldemond.findme.managers;

import me.mennoguldemond.findme.BlockGenerator;
import me.mennoguldemond.findme.FindMe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class GameManager {
    public boolean isTowerBuild;
    private int countdownTimer;

    private final FindMe findMe;

    public GameManager(FindMe findMe) {
        this.findMe = findMe;
        this.isTowerBuild = this.findMe.dataManager.gameData.isTowerBuild;
    }

    public void start() {
        // Start countdown
        this.countdownTimer = Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.findMe, new Runnable() {
                    int i = 7;

                    public void run() {
                        for (Player player:findMe.playerManager.readyPlayers) {
                            if (i > 6) {
                                player.sendTitle(ChatColor.RED + "Game starting..", "");
                            } else if(i >= 1 && i < 6){
                                player.sendTitle(ChatColor.RED + String.valueOf(i), "");
                            } else if (i < 1){
                                player.sendTitle(ChatColor.GREEN + "Go!", "find the others..");
                            }
                        }

                        if (i <= 0) {
                            setGameValues();
                        }
                        i--;
                    }
                }
                , 0L, 20L);
    }

    private void setGameValues() {
        Bukkit.getScheduler().cancelTask(this.countdownTimer);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule reducedDebugInfo true");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spreadplayers 0 0 800 1200 false @a");

        if (!this.isTowerBuild) {
            BlockGenerator.CreateHomeTower(Bukkit.getWorlds().get(0));
        }

        this.findMe.playerManager.clearReadyPlayers();

        // Reset time
        for (World world : Bukkit.getServer().getWorlds()) {
            world.setTime(0);
        }
    }

}
