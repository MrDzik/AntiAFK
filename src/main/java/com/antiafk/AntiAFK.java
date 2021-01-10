package com.antiafk;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
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
class Join implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        ev.setJoinMessage("Witamy");
        Player player = ev.getPlayer();
    }
}


