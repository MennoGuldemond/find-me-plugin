package me.mennoguldemond.findme.events;

import me.mennoguldemond.findme.managers.PlayerManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakEvents implements Listener {

    PlayerManager playerManager;

    public BreakEvents(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (playerManager.readyPlayers.contains(player)) {
            event.setCancelled(true);
        }
    }

}