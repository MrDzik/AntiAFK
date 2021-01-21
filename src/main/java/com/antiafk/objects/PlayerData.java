package com.antiafk.objects;

import org.bukkit.entity.Player;

public class PlayerData {
    public Player player;
    public double x;
    public double z;
    public double y;

    public PlayerData(Player player) {
        this.player = player;
        this.x = 0;
        this.y = 0;
        this.z = 0;
    }
    public PlayerData(Player player, double x, double y, double z) {
        this.player = player;
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
