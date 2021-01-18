package com.antiafk.runnables;

import com.antiafk.AntiAFK;
import com.antiafk.AntiAFKPlayersManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class AntiAFKThread extends BukkitRunnable {
    AntiAFKPlayersManager playersManager = AntiAFK.getPlayersManager();
    private List<Player> playerList;
    private List<Double> oldX = new ArrayList<>();
    private List<Double> oldZ = new ArrayList<>();

    public AntiAFKThread(List<Player> playerList) {
        this.playerList = playerList;
    }

    private boolean isPlayerPositionNotChanged(Player player, Double oldX, Double oldZ) {
        return player.getLocation().getX() == oldX && player.getLocation().getZ() == oldZ;
    }

    public void run() {
        if (!playerList.isEmpty()) {
            for (Player player : playerList) {
                int playerIndex = playerList.indexOf(player);
                if (player.isOnline()) {
                    if (oldX.size() < playerList.indexOf(player)) {
                        oldX.add(player.getLocation().getX());
                        oldX.add(player.getLocation().getZ());
                    } else {
                        if (isPlayerPositionNotChanged(player, oldX.get(playerIndex), oldZ.get(playerIndex))) {
                            player.kickPlayer(ChatColor.GREEN + "Zostałeś wyrzucony za bycie AFK");
                            playersManager.deletePlayer(player);
                            oldX.set(playerList.indexOf(player), null);
                            oldZ.set(playerList.indexOf(player), null);
                        } else {
                            oldX.set(playerIndex, player.getLocation().getX());
                            oldZ.set(playerIndex, player.getLocation().getX());
                        }
                    }
                } else {
                    if (oldX.size() >= playerList.indexOf(player)) {
                        oldX.set(playerList.indexOf(player), null);
                        oldZ.set(playerList.indexOf(player), null);
                    }
                    playersManager.deletePlayer(player);
                }
            }
        }

    }
}