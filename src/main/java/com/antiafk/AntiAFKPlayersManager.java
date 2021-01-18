package com.antiafk;

import com.antiafk.runnables.AntiAFKThread;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public class AntiAFKPlayersManager {
    public List<Player> thread1 = new ArrayList<>();
    public List<Player> thread2 = new ArrayList<>();

    public void addPlayer(Player player) {
        if (thread1.size() <= thread2.size()) {
            thread1.add(player);
        } else {
            thread2.add(player);
        }
    }

    public void deletePlayer(Player player) {
        if (thread1.contains(player)) {
            thread1.remove(player);
        } else {
            thread2.remove(player);
        }
    }

    public void run() {
        BukkitTask fthread = new AntiAFKThread(thread1).runTaskTimer(AntiAFK.getMainPlugin(), 30 * 20, 30 * 20);
        BukkitTask sthread = new AntiAFKThread(thread2).runTaskTimer(AntiAFK.getMainPlugin(), 30 * 20, 30 * 20);
    }
}