package com.antiafk.runnables;

import com.antiafk.AntiAFK;
import com.antiafk.managers.AntiAFKPlayersManager;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

import com.antiafk.objects.PlayerData;

import java.util.List;

public class AntiAFKThread extends BukkitRunnable {
    AntiAFKPlayersManager playersManager = AntiAFK.getPlayersManager();
    private List<PlayerData> playerDataList;
    private final int number;

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
                boolean isKickable = false;
                for (int i = 0; i < 16; i += 3) {
                    if (i == 15)
                        isKickable = true;
                    new Reminder(playerData, isKickable).runTaskLater(AntiAFK.getMainPlugin(), i * 20L);
                }
            } else {
                updatePlayerPosition(playerData);
            }
        }

    }
    public void run() {
        setPlayerList();
        if (!playerDataList.isEmpty()) {
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

class Reminder extends BukkitRunnable {
    private final PlayerData playerData;
    private final boolean isKickable;
    AntiAFKPlayersManager playersManager = AntiAFK.getPlayersManager();

    public Reminder(PlayerData playerData, boolean isKickable) {
        this.playerData = playerData;
        this.isKickable = isKickable;
    }

    private boolean isPlayerPositionNotChanged(PlayerData playerData) {
        Player player = playerData.getPlayer();
        return player.getLocation().getX() == playerData.getX() &&
                player.getLocation().getZ() == playerData.getZ();
    }

    public void run() {
        if (isPlayerPositionNotChanged(playerData)) {
            if (!isKickable) {
                playerData.getPlayer().playSound(playerData.getPlayer().getLocation(), Sound.ENTITY_ENDERDRAGON_AMBIENT, 1, 1);
                playerData.getPlayer().sendMessage(ChatColor.YELLOW + "[AntiAFK] Rusz sie albo zostaniesz wyrzucony/a");
            } else {
                playerData.getPlayer().kickPlayer(ChatColor.GREEN + "Zostales wyrzucony/a za bycie AFK");
                playersManager.deletePlayer(playerData);
            }
        }
        this.cancel();
    }
}