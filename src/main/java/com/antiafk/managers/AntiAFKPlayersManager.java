package com.antiafk.managers;

import com.antiafk.AntiAFK;
import com.antiafk.runnables.AntiAFKThread;
import com.antiafk.objects.PlayerData;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AntiAFKPlayersManager {
    public List<PlayerData> PlayerList1 = new ArrayList<>();
    public List<PlayerData> PlayerList2 = new ArrayList<>();

    private List getPlayerListWithLowerSize(){
        return (PlayerList1.size() <= PlayerList2.size()) ? PlayerList1 : PlayerList2;
    }

    public void addPlayer(Player player) {
        PlayerData playerData = new PlayerData(player);
        getPlayerListWithLowerSize().add(playerData);
    }
    public void deletePlayer(PlayerData player) {
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