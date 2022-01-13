package me.mennoguldemond.findme.events;

import me.mennoguldemond.findme.FindMe;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class BreakEvents implements Listener {

    FindMe findMe;

    public BreakEvents(FindMe findMe) {
        this.findMe = findMe;
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (this.findMe.playerManager.readyPlayers.contains(player)) {
            event.setCancelled(true);
        } else if (event.getBlock().getType().name().equals(this.findMe.dataManager.gameData.cursedBlock)) {
            player.getWorld().createExplosion(player.getLocation(), 10, true, true);
        }
        Bukkit.getLogger().info(event.getBlock().getType().name() + " VS " + this.findMe.dataManager.gameData.cursedBlock);
    }

}