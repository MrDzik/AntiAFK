package com.antiafk.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.concurrent.TimeUnit;

public class AntiAFKEvents implements Listener {
    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        while (true) {
            double oldX = player.getLocation().getX();
            double oldZ = player.getLocation().getZ();
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            if(player.getLocation().getX() == oldX && player.getLocation().getZ() == oldZ ) {
                player.kickPlayer(ChatColor.RED + "Zostałeś wyrzucony za bycie AFK");
            }
        }
    }
}
