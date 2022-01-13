package me.mennoguldemond.findme.events;

import me.mennoguldemond.findme.managers.PlayerManager;
import me.mennoguldemond.findme.models.PlayerData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

public class AdvancementEvents implements Listener {
    PlayerManager playerManager;

    public AdvancementEvents(PlayerManager playerManager) {
        this.playerManager = playerManager;
    }

    @EventHandler
    public void onAdvancement(PlayerAdvancementDoneEvent event) {
        Player player = event.getPlayer();
        if (!event.getAdvancement().getKey().toString().contains("recipes")) {
            player.sendMessage(event.getAdvancement().getKey().toString());
            PlayerData playerData = this.playerManager.getPlayerData(player);
            if (playerData != null) {
                playerData.jos += 1;
                playerData.score += 2;
                player.sendMessage("§6You gained §2" + 1 + " §6Jos!");
            } else {
                Bukkit.getLogger().warning("PlayerData was null in [AdvancementEvents.onAdvancement]");
            }
        }
    }
}
