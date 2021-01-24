package com.antiafk.runnables;

import com.antiafk.objects.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class AntiAFKKick extends BukkitRunnable {
    private final PlayerData playerData;
    public AntiAFKKick (PlayerData playerData) {
        this.playerData = playerData;
    }
    public void run() {
        playerData.player.kickPlayer(ChatColor.GREEN + "Zostales wyrzucony/a za bycie AFK");
    }
}
