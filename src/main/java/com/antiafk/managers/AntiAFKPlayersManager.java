package com.antiafk.managers;

import com.antiafk.AntiAFK;
import com.antiafk.runnables.AntiAFKThread;
import com.antiafk.objects.PlayerPosition;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class AntiAFKPlayersManager {
    public List<PlayerPosition> thread1 = new ArrayList<>();
    public List<PlayerPosition> thread2 = new ArrayList<>();

    public void addPlayer(Player player) {
        PlayerPosition playerPosition = new PlayerPosition(player);
        if (thread1.size() <= thread2.size()) {
            thread1.add(playerPosition);
        } else {
            thread2.add(playerPosition);
        }
    }

    public void deletePlayer(PlayerPosition player) {
        if (thread1.contains(player)) {
            thread1.remove(player);
        } else {
            thread2.remove(player);
        }
    }

    public void run() {
        BukkitTask fthread = new AntiAFKThread(1).runTaskTimer(AntiAFK.getMainPlugin(), 0, 30 * 20);
        BukkitTask sthread = new AntiAFKThread(2).runTaskTimer(AntiAFK.getMainPlugin(), 5 * 20, 30 * 20);
    }
}