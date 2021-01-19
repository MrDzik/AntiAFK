package com.antiafk.managers;

import com.antiafk.AntiAFK;
import com.antiafk.runnables.AntiAFKThread;
import com.antiafk.objects.PlayerPosition;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class AntiAFKPlayersManager {
    public List<PlayerPosition> PlayerList1 = new ArrayList<>();
    public List<PlayerPosition> PlayerList2 = new ArrayList<>();

    public void addPlayer(Player player) {
        PlayerPosition playerPosition = new PlayerPosition(player);
        if (PlayerList1.size() <= PlayerList2.size()) {
            PlayerList1.add(playerPosition);
        } else {
            PlayerList2.add(playerPosition);
        }
    }

    public void deletePlayer(PlayerPosition player) {
        if (PlayerList1.contains(player)) {
            PlayerList1.remove(player);
        } else {
            PlayerList2.remove(player);
        }
    }

    public void run() {
        new AntiAFKThread(1).runTaskTimer(AntiAFK.getMainPlugin(), 0, 180 * 20);
        new AntiAFKThread(2).runTaskTimer(AntiAFK.getMainPlugin(), 5 * 20, 180 * 20);
    }
}