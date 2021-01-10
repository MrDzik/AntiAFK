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

import java.util.concurrent.TimeUnit;

public class AntiAFK extends JavaPlugin {
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
        server.getConsoleSender().sendMessage("jestem tutaj");
        double oldX = player.getLocation().getX();
        double oldZ = player.getLocation().getZ();
        server.getConsoleSender().sendMessage(ChatColor.AQUA + "x: " + oldX + "z: " + oldZ);

        for(int i=0; i<5;i++){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        double newX = player.getLocation().getX();
        double newZ = player.getLocation().getZ();
        if(newX == oldX && newZ == newX ){
            player.kickPlayer("Kick");
        }
    }
}



