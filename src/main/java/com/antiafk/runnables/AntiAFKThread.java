package com.antiafk.runnables;

import com.antiafk.AntiAFK;
import com.antiafk.managers.AntiAFKPlayersManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
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
    private boolean isPlayerPositionNotChanged(PlayerData playerData, Location loc) {
        return loc.getX() == playerData.x &&
                loc.getZ() == playerData.z;
    }
    private void updatePlayerPosition(PlayerData playerData, Location loc){
        playerData.x = loc.getX();
        playerData.z = loc.getZ();
    }
    private void setPlayerList(){
        if (number == 1)
            playerDataList = new ArrayList<>(playersManager.PlayerList1);
        else
            playerDataList = new ArrayList<>(playersManager.PlayerList2);
    }
    public void run() {
        setPlayerList();
        if (!playerDataList.isEmpty()) {
            for (PlayerData playerData : playerDataList) {
                if (playerData.player.isOnline()) {
                    Location loc = playerData.player.getLocation();
                    if (playerData.x == 0) {
                        updatePlayerPosition(playerData, loc);
                    } else {
                        if (isPlayerPositionNotChanged(playerData, loc)) {
                            boolean isKickable = false;
                            for (int i = 0; i < 16; i += 3) {
                                if (i == 15)
                                    isKickable = true;
                                new Reminder(playerData, isKickable).runTaskLater(AntiAFK.getMainPlugin(), i * 20L);
                            }
                        } else {
                            updatePlayerPosition(playerData, loc);
                        }
                    }
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
        Player player = playerData.player;
        return player.getLocation().getX() == playerData.x &&
                player.getLocation().getZ() == playerData.z;
    }

    public void run() {
        if (isPlayerPositionNotChanged(playerData)) {
            if (!isKickable) {
                playerData.player.playSound(playerData.player.getLocation(), Sound.ENTITY_ENDERDRAGON_AMBIENT, 1, 1);
                playerData.player.sendMessage(ChatColor.YELLOW + "[AntiAFK] Rusz sie albo zostaniesz wyrzucony/a");
            } else {
                playerData.player.kickPlayer(ChatColor.GREEN + "Zostales wyrzucony/a za bycie AFK");
                playersManager.deletePlayer(playerData);
            }
        }
    }
}