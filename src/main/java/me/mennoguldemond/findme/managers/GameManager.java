package me.mennoguldemond.findme.managers;

import me.mennoguldemond.findme.BlockGenerator;
import me.mennoguldemond.findme.FindMe;
import me.mennoguldemond.findme.models.CursedBlocks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

import java.util.Random;

public class GameManager {
    private int countdownTimer;
    private long lastTimeDayChange = 24000;

    private final FindMe findMe;

    public GameManager(FindMe findMe) {
        this.findMe = findMe;
        this.countDays();
    }

    public void start() {
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

    public void sendDayMessage(Player player) {
        int day = Math.round(this.findMe.getServer().getWorld("world").getFullTime() / 24000);
        player.sendTitle(ChatColor.LIGHT_PURPLE + "Day " + day, ChatColor.WHITE + "Be careful, " + ChatColor.RED + this.findMe.dataManager.gameData.cursedBlock + ChatColor.WHITE + " is cursed today!");
    }

    private void countDays() {
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(this.findMe, () -> {
            long currentTime = this.findMe.getServer().getWorld("world").getTime();
            if (currentTime <= lastTimeDayChange) {
                lastTimeDayChange = currentTime + 19;
                this.handleDayChange();
            }
        }
        , 0L, 20L);
    }

    private void setGameValues() {
        Bukkit.getScheduler().cancelTask(this.countdownTimer);
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule reducedDebugInfo true");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "spreadplayers 0 0 800 1200 false @a");

        if (!this.findMe.dataManager.gameData.isTowerBuild) {
            BlockGenerator.CreateHomeTower(Bukkit.getWorlds().get(0));
        }

        this.findMe.playerManager.clearReadyPlayers();

        // Reset time
        for (World world : Bukkit.getServer().getWorlds()) {
            world.setTime(0);
        }
    }

    private void handleDayChange() {
        Bukkit.getLogger().info("handleDayChange() was run");
        this.findMe.dataManager.gameData.cursedBlock = this.randomCurseBlock();
        for (Player player:this.findMe.getServer().getOnlinePlayers()) {
            this.sendDayMessage(player);
        }
    }

    private String randomCurseBlock() {
        return CursedBlocks.blocks[new Random().nextInt(CursedBlocks.blocks.length)];
    }

}
