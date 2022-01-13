package me.mennoguldemond.findme.commands;

import me.mennoguldemond.findme.FindMe;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class JosCommand implements CommandExecutor {

    private final FindMe findMe;

    public JosCommand(FindMe findMe) {
        this.findMe = findMe;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            player.sendMessage("§6You currently have §2" + this.findMe.playerManager.getPlayerData(player).jos + " §6Jos");
        }
        return true;
    }
}
