package com.antiafk.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

class PrimeThread extends Thread {
    Player player;
    PrimeThread(Player player) {
        this.player = player;
    }
    public void run() {
        while (player.isOnline()) {
            try {
                double oldX = player.getLocation().getX();
                double oldZ = player.getLocation().getZ();
                PrimeThread.sleep(10000);
                if(player.getLocation().getX() == oldX && player.getLocation().getZ() == oldZ ) {
                    player.kickPlayer(ChatColor.RED + "Zostałeś wyrzucony za bycie AFK");
                }
            } catch (InterruptedException ie) {

            }
        }
    }
}

public class AntiAFKEvents implements Listener {
    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        PrimeThread thread = new PrimeThread(player);
        thread.run();
    }
}
