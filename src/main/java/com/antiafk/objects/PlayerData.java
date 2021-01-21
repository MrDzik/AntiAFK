package com.antiafk.objects;

import org.bukkit.entity.Player;

public class PlayerData {
    private Player player;
    private double x;
    private double z;

    public void setZ(double z) {
        this.z = z;
    }
    public double getZ() {
        return z;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getX() {
        return x;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public Player getPlayer() {
        return player;
    }

    public PlayerData(Player player) {
        this.player = player;
        this.x = 0;
        this.z = 0;
    }
}
