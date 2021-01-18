package com.antiafk;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AntiAFKPlayersManager {
    public List<Player> thread1 = new ArrayList<>();
    public List<Player> thread2 = new ArrayList<>();
    private List<Player> notAssignedPlayers = new ArrayList<>();

    public void addPlayer(Player player) {
        notAssignedPlayers.add(player);
    }

    public void deletePlayer(Player player) {
        if (thread1.contains(player)) {
            thread1.remove(player);
        } else {
            thread2.remove(player);
        }
    }

    public void run() {

    }

    private void addPlayersToThread(List<Player> notAssignedPlayers) {
        if (!notAssignedPlayers.isEmpty()) {
            for (Player player : notAssignedPlayers) {
                if (thread1.size() <= thread2.size()) {
                    thread1.add(player);
                } else {
                    thread2.add(player);
                }
            }
        }
    }
}