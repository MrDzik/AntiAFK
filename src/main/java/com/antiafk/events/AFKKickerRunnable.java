package com.antiafk.events;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AFKKickerRunnable extends BukkitRunnable {
    private Player player;
    private double oldX;
    private double oldZ;

    private boolean isNotPlayerPositionChanged(){
        return player.getLocation().getX() == oldX && player.getLocation().getZ() == oldZ;
    }
    private void updatePlayerPosition(){
        oldX = player.getLocation().getX();
        oldZ = player.getLocation().getZ();
    }
    public void run() {
        if (player.isOnline())
            if (isNotPlayerPositionChanged())
                player.kickPlayer(ChatColor.RED + "Zostałeś wyrzucony za bycie AFK");
            else
                updatePlayerPosition();
        else
            this.cancel();
    }

    public AFKKickerRunnable(Player player){
        this.player = player;
    }
}
