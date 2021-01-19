package com.antiafk.objects;

import org.bukkit.entity.Player;

public class PlayerData {
    private Player player;
    private Position position;

    public void setX(double x) {
        position.setX(x);
    }

    public double getX() {
        return position.getX();
    }

    public PlayerData(Player player) {
        this.player = player;
    }

    public double getZ() {
        return position.getZ();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public void setZ(double z) {
        position.setZ(z);
    }
}
