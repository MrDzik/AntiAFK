package com.antiafk;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

class Join implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev) {
        ev.setJoinMessage("Witamy na serwerze!");
        Player player = ev.getPlayer();
        AntiAFK.AFK(player);
    }
}
