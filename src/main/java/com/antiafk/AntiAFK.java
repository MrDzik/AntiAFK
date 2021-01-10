package com.antiafk;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiAFK extends JavaPlugin {
    private static Server server;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[AntiAFK] Plugin is enabled");
        server = getServer();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[AntiAFK] Plugin is disabled");
    }
    public static void AFK(Player player) {
        double oldX = player.getLocation().getX();
        double oldZ = player.getLocation().getZ();
        server.getConsoleSender().sendMessage(ChatColor.AQUA + "x: " + oldX + "z: " + oldZ);
    }
}
class Join implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        ev.setJoinMessage("Witamy na serwerze!");
        Player player = ev.getPlayer();
        AntiAFK.AFK(player);
    }
}


