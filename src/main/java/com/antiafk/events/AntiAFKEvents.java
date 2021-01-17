package com.antiafk.events;

import com.antiafk.AntiAFK;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class AntiAFKEvents implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        BukkitTask task = new BukkitRunnable() {
            double oldX;
            double oldZ;
            public void run() {
                if (player.isOnline()) {
                    if (player.getLocation().getX() == oldX && player.getLocation().getZ() == oldZ) {
                        player.kickPlayer(ChatColor.RED + "Zostałeś wyrzucony za bycie AFK");
                    } else {
                        oldX = player.getLocation().getX();
                        oldZ = player.getLocation().getZ();
                    }
                } else {
                    this.cancel();
                }
            }
        }.runTaskTimer(AntiAFK.getMainPlugin(), 30 * 20, 30 * 20);
    }
}
