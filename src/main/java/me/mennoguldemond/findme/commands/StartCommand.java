package me.mennoguldemond.findme.commands;

import me.mennoguldemond.findme.FindMe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class StartCommand implements CommandExecutor {

    private final FindMe findMe;

    public StartCommand(FindMe findMe) {
        this.findMe = findMe;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (findMe.playerManager.readyPlayers.size() > 0 && Bukkit.getServer().getOnlinePlayers().size() == findMe.playerManager.readyPlayers.size()) {
            Bukkit.getServer().broadcastMessage(ChatColor.GREEN + "The game is starting...");
            this.findMe.gameManager.start();
        } else {
            Bukkit.getServer().broadcastMessage(ChatColor.RED + "All players must be ready to start the game.");
        }
        return true;
    }
}
