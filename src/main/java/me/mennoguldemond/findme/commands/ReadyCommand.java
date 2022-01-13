package me.mennoguldemond.findme.commands;

import me.mennoguldemond.findme.BlockGenerator;
import me.mennoguldemond.findme.FindMe;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ReadyCommand implements CommandExecutor {

    private final FindMe findMe;

    public ReadyCommand(FindMe findMe) {
        this.findMe = findMe;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (this.findMe.playerManager.readyPlayers.contains(player)) {
                return true;
            }
            this.findMe.playerManager.readyPlayer(player);

            int xOffset = this.findMe.playerManager.readyPlayers.size() * 6;

            BlockGenerator.createBox(player, xOffset, 200, 0);

            Location spawnLocation = new Location(player.getWorld(), xOffset, 200, 0);
            player.teleport(spawnLocation.add(0.5, 1, 0.5));
            player.getInventory().clear();
        } else {
            Bukkit.getLogger().warning("Only players can use the /ready command.");
        }
        return true;
    }
}
