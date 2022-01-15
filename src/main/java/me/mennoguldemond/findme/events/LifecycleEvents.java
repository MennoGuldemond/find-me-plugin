package me.mennoguldemond.findme.events;

import me.mennoguldemond.findme.FindMe;
import me.mennoguldemond.findme.models.PlayerData;
import me.mennoguldemond.findme.models.Vector3;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class LifecycleEvents implements Listener {

    FindMe findMe;

    public LifecycleEvents(FindMe findMe) {
        this.findMe = findMe;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        PlayerData playerData = this.findMe.playerManager.getPlayerData(player);
        if (playerData != null) {
            playerData.jos = playerData.jos > 10 ? playerData.jos - 10 : 0;
            this.findMe.dataManager.saveData();
        } else {
            Bukkit.getLogger().warning("PlayerData was null in [LifecycleEvents.onPlayerDeath]");
        }
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = this.findMe.playerManager.getPlayerData(player);
        if (playerData != null) {
            event.setRespawnLocation(new Location(this.findMe.getServer().getWorld("world"), playerData.spawnLocation.x, playerData.spawnLocation.y, playerData.spawnLocation.z));
        } else {
            Bukkit.getLogger().warning("PlayerData was null in [LifecycleEvents.onPlayerRespawn]");
        }
    }

    @EventHandler
    public void onPLayerSleep(PlayerBedEnterEvent event) {
        Player player = event.getPlayer();
        PlayerData playerData = this.findMe.playerManager.getPlayerData(player);
        if (playerData != null) {
            Location location = player.getLocation();
            playerData.spawnLocation = new Vector3(location.getX(), location.getY(), location.getZ());
            this.findMe.dataManager.saveData();
        } else {
            Bukkit.getLogger().warning("PlayerData was null in [LifecycleEvents.onPLayerSleep]");
        }
    }

}
