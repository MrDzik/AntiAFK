package com.antiafk.managers;

import com.antiafk.AntiAFK;
import com.antiafk.objects.Config;
import com.antiafk.objects.ConfigReader;
import com.antiafk.runnables.AntiAFKThread;
import com.antiafk.objects.PlayerData;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class AntiAFKPlayersManager {
    public List<PlayerData> PlayerList1 = new ArrayList<>();
    public List<PlayerData> PlayerList2 = new ArrayList<>();

    //TODO find better name
    private List<PlayerData> getFirstOrSecondList(boolean bool) {
        return bool? PlayerList1 : PlayerList2;
    }
    public void addPlayer(Player player) {
        PlayerData playerData = new PlayerData(player);
        boolean isList1Smaller = PlayerList1.size() <= PlayerList2.size();
        getFirstOrSecondList(isList1Smaller).add(playerData);
    }
    public void deletePlayer(PlayerData player) {
        boolean isInList1 = PlayerList1.contains(player);
        getFirstOrSecondList(isInList1).remove(player);
    }

    public void run() {
        Config config = new ConfigReader().readOrReturnDefault();
        long timeToKick = config.getTimeToKick();
        new AntiAFKThread(1).runTaskTimer(AntiAFK.getMainPlugin(), 0, timeToKick * 20);
        new AntiAFKThread(2).runTaskTimer(AntiAFK.getMainPlugin(), 5 * 20, timeToKick * 20);
    }
}