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

    private boolean isPlayerPositionNotChanged(PlayerPosition playerPosition) {
        Player player = playerPosition.getPlayer();
        return player.getLocation().getX() == playerPosition.getX() &&
                player.getLocation().getZ() == playerPosition.getZ();
    }

    private void updatePlayerPosition(PlayerPosition playerPosition){
        Player player = playerPosition.getPlayer();
        playerPosition.setX(player.getLocation().getX());
        playerPosition.setZ(player.getLocation().getZ());
    }

    public void run() {
        if (number == 1) {
            playerList = new ArrayList<>(playersManager.PlayerList1);
        } else {
            playerList = new ArrayList<>(playersManager.PlayerList2);
        }
        if (!playerList.isEmpty()) {
            AntiAFK.getMainPlugin().getServer().getConsoleSender()
                    .sendMessage("Thread" + number + " is running and have: " + playerList.size() + " players");
            for (PlayerPosition playerPosition : playerList) {
                if (playerPosition.getPlayer().isOnline()) {
                    if (playerPosition.getX() == 0) {
                        updatePlayerPosition(playerPosition);
                    } else {
                        if (isPlayerPositionNotChanged(playerPosition)) {
                            playerPosition.getPlayer()
                                    .kickPlayer(ChatColor.GREEN + "Zostałeś wyrzucony za bycie AFK");
                            playersManager.deletePlayer(playerPosition);
                        } else {
                            updatePlayerPosition(playerPosition);
                        }
                    }
                } else {
                    playersManager.deletePlayer(playerPosition);
                }
            }
        }
    }
}