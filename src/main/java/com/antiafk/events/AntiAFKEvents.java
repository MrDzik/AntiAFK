package com.antiafk.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


public class AntiAFKEvents implements Listener {
    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        while (true) {
            double oldX = player.getLocation().getX();
            double oldZ = player.getLocation().getZ();
            long startTime = System.currentTimeMillis();
            for (int count = 0; ;count++) {
                long now = System.currentTimeMillis();
                if(now - startTime >= 10000) break;
            }
            if(player.getLocation().getX() == oldX && player.getLocation().getZ() == oldZ ) {
                player.kickPlayer(ChatColor.RED + "Zostałeś wyrzucony za bycie AFK");
                return;
            }
        }
    }
}
