package me.mennoguldemond.findme.events;

import me.mennoguldemond.findme.FindMe;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectionEvents implements Listener {

    FindMe findMe;

    public ConnectionEvents(FindMe findMe) {
        this.findMe = findMe;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(ChatColor.BLUE + "Welcome to Find me. Type /ready when you want to start the game.");
        this.findMe.playerManager.handlePlayerJoin(player);
        this.findMe.dataManager.saveData();
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        this.findMe.playerManager.unreadyPlayer((player));
        this.findMe.dataManager.saveData();
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event) {
        Player player = event.getPlayer();
        this.findMe.playerManager.unreadyPlayer((player));
        this.findMe.dataManager.saveData();
    }
}
