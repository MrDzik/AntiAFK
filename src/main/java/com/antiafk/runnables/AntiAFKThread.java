package com.antiafk.runnables;

import com.antiafk.AntiAFK;
import com.antiafk.managers.AntiAFKPlayersManager;
import org.bukkit.Location;
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
                                new AntiAFKReminder(playerData, isKickable).runTaskLaterAsynchronously(AntiAFK.getMainPlugin(), i * 20L);
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

