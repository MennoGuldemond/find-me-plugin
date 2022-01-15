package me.mennoguldemond.findme;

import me.mennoguldemond.findme.commands.JosCommand;
import me.mennoguldemond.findme.commands.ReadyCommand;
import me.mennoguldemond.findme.commands.StartCommand;
import me.mennoguldemond.findme.events.AdvancementEvents;
import me.mennoguldemond.findme.events.BreakEvents;
import me.mennoguldemond.findme.events.ConnectionEvents;
import me.mennoguldemond.findme.events.LifecycleEvents;
import me.mennoguldemond.findme.managers.DataManager;
import me.mennoguldemond.findme.managers.GameManager;
import me.mennoguldemond.findme.managers.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class FindMe extends JavaPlugin {

    public DataManager dataManager;
    public PlayerManager playerManager;
    public GameManager gameManager;

    @Override
    public void onEnable() {
        System.out.println("<< Find Me Plugin loaded >>");

        this.dataManager = new DataManager(this);
        this.playerManager = new PlayerManager(this);
        this.gameManager = new GameManager(this);

        getServer().getPluginManager().registerEvents(new ConnectionEvents(this), this);
        getServer().getPluginManager().registerEvents(new BreakEvents(this), this);
        getServer().getPluginManager().registerEvents(new AdvancementEvents(this.playerManager), this);
        getServer().getPluginManager().registerEvents(new LifecycleEvents(this), this);

        getServer().getPluginCommand("ready").setExecutor(new ReadyCommand(this));
        getServer().getPluginCommand("start").setExecutor(new StartCommand(this));
        getServer().getPluginCommand("jos").setExecutor(new JosCommand(this));
    }

    @Override
    public void onDisable() {
        this.dataManager.saveData();
    }
}
