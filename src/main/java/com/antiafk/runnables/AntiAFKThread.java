package com.antiafk.runnables;

import com.antiafk.AntiAFK;
import com.antiafk.managers.AntiAFKPlayersManager;
import com.antiafk.objects.PlayerPosition;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class AntiAFKThread extends BukkitRunnable {
    AntiAFKPlayersManager playersManager = AntiAFK.getPlayersManager();
    private List<PlayerPosition> playerList;
    private int number;

    public AntiAFKThread(int number) {
        this.number = number;
    }

    private boolean isPlayerPositionNotChanged(Player player, PlayerPosition playerPosition) {
        return player.getLocation().getX() == playerPosition.getX() && player.getLocation().getZ() == playerPosition.getZ();
    }

    public void run() {
        if (number == 1) {
            playerList = new ArrayList<>(playersManager.thread1);
        } else {
            playerList = new ArrayList<>(playersManager.thread2);
        }
        if (!playerList.isEmpty()) {
            AntiAFK.getMainPlugin().getServer().getConsoleSender().sendMessage("Thread" + number + " is running and have: " + playerList.size() + " players");
            for (PlayerPosition playerPosition : playerList) {
                /* Iterator<PlayerPosition> it = playerList.iterator(); it.hasNext(); PlayerPosition playerPosition = it.next(); */
                Player player = playerPosition.getPlayer();
                if (playerPosition.getPlayer().isOnline()) {
                    if (playerPosition.getX() == 0) {
                        playerPosition.setX(player.getLocation().getX());
                        playerPosition.setZ(player.getLocation().getZ());
                    } else {
                        if (isPlayerPositionNotChanged(player, playerPosition)) {
                            player.kickPlayer(ChatColor.GREEN + "Zostałeś wyrzucony za bycie AFK");
                            playersManager.deletePlayer(playerPosition);
                        } else {
                            playerPosition.setX(player.getLocation().getX());
                            playerPosition.setZ(player.getLocation().getZ());
                        }
                    }
                } else {
                    playersManager.deletePlayer(playerPosition);
                }
            }
        }

    }
}