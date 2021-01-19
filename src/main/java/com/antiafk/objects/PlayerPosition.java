package com.antiafk.objects;

import org.bukkit.entity.Player;

public class PlayerPosition {
    private Player player;
    private double x;
    private double z;

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public PlayerPosition(Player player) {
        this.player = player;
    }

    public double getZ() {
        return z;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setZ(double z) {
        this.z = z;
    }
}
