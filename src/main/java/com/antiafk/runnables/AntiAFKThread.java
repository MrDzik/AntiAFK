package com.antiafk.runnables;

import com.antiafk.AntiAFK;
import com.antiafk.managers.AntiAFKPlayersManager;
import com.antiafk.objects.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class AntiAFKThread extends BukkitRunnable {
    AntiAFKPlayersManager playersManager = AntiAFK.getPlayersManager();
    private List<PlayerData> playerDataList;
    private int number;

    public AntiAFKThread(int number) {
        this.number = number;
    }

    private boolean isPlayerPositionNotChanged(PlayerData playerData) {
        Player player = playerData.getPlayer();
        return player.getLocation().getX() == playerData.getX() &&
                player.getLocation().getZ() == playerData.getZ();
    }

    private void updatePlayerPosition(PlayerData playerData){
        Player player = playerData.getPlayer();
        playerData.setX(player.getLocation().getX());
        playerData.setZ(player.getLocation().getZ());
    }

    private void setPlayerList(){
        if (number == 1)
            playerDataList = new ArrayList<>(playersManager.PlayerList1);
        else
            playerDataList = new ArrayList<>(playersManager.PlayerList2);
    }

    private void takeCareOfPlayer(PlayerData playerData){
        if (playerData.getX() == 0) {
            updatePlayerPosition(playerData);
        } else {
            if (isPlayerPositionNotChanged(playerData)) {
                playerData.getPlayer()
                        .kickPlayer(ChatColor.GREEN + "Zostałeś wyrzucony za bycie AFK");
                playersManager.deletePlayer(playerData);
            } else {
                updatePlayerPosition(playerData);
            }
        }

    }

    public void run() {
        setPlayerList();
        if (!playerDataList.isEmpty()) {
            AntiAFK.getMainPlugin().getServer().getConsoleSender()
                    .sendMessage("Thread" + number + " is running and have: " + playerDataList.size() + " players");
            for (PlayerData playerData : playerDataList) {
                if (playerData.getPlayer().isOnline()) {
                    takeCareOfPlayer(playerData);
                } else {
                    playersManager.deletePlayer(playerData);
                }
            }
        }
    }
}