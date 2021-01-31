package com.antiafk;

import com.antiafk.events.AntiAFKEvents;
import com.antiafk.managers.AntiAFKPlayersManager;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class AntiAFK extends JavaPlugin {
    static JavaPlugin plugin;
    static AntiAFKPlayersManager PlayersManager = new AntiAFKPlayersManager();

    public static AntiAFKPlayersManager getPlayersManager() {
        return PlayersManager;
    }

    public static JavaPlugin getMainPlugin() {
        return plugin;
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getLogger().info(ChatColor.GREEN + "[AntiAFK] Plugin is enabled");
        getServer().getPluginManager().registerEvents(new AntiAFKEvents(), this);
        PlayersManager.run();
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info(ChatColor.RED + "[AntiAFK] Plugin is disabled");
    }
}
