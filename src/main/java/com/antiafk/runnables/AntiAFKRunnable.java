package com.antiafk.runnables;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class AntiAFKRunnable extends BukkitRunnable {
    private Player player;
    private double oldX;
    private double oldZ;

    private boolean isPlayerPositionNotChanged(){
        return player.getLocation().getX() == oldX && player.getLocation().getZ() == oldZ;
    }
    private void updatePlayerPosition(){
        oldX = player.getLocation().getX();
        oldZ = player.getLocation().getZ();
    }
    public void run() {
        if (player.isOnline()) {
            if (isPlayerPositionNotChanged()) {
                player.kickPlayer(ChatColor.RED + "Zostałeś wyrzucony za bycie AFK");
            } else {
                updatePlayerPosition();
            }
        } else {
            this.cancel();
        }
    }

    public AntiAFKRunnable(Player player){
        this.player = player;
    }
}
