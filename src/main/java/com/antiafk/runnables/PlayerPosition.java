package com.antiafk.runnables;

import org.bukkit.entity.Player;

public class PlayerPosition {
    private double x;
    private double y;
    private Player player;

    public void setX(double x) {
        this.x = x;
    }

    public double getX() {
        return x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public PlayerPosition(double x, double y, Player player){
        this.player = player;
        this.x = x;
        this.y = y;
    }
}
