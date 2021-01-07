package com.antiafk;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiAFK extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[AntiAFK] Plugin is enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[AntiAFK] Plugin is disabled");
    }
}
