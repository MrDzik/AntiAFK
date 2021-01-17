package com.antiafk.events;

import com.antiafk.AntiAFK;
import com.antiafk.AntiAFKRunnable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitTask;

public class AntiAFKEvents implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        BukkitTask task = new AntiAFKRunnable(player)
                .runTaskTimer(AntiAFK.getMainPlugin(), 30 * 20, 30 * 20);
    }
}
