package com.antiafk.runnables;

import com.antiafk.AntiAFK;
import com.antiafk.managers.AntiAFKPlayersManager;
import com.antiafk.objects.PlayerData;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

class AntiAFKReminder extends BukkitRunnable {
    private final PlayerData playerData;
    private final boolean isKickable;
    private final AntiAFKPlayersManager playersManager = AntiAFK.getPlayersManager();

    public AntiAFKReminder(PlayerData playerData, boolean isKickable) {
        this.playerData = playerData;
        this.isKickable = isKickable;
    }

    private boolean isPlayerPositionNotChanged(PlayerData playerData, Location loc) {
        return loc.getX() == playerData.x &&
                loc.getZ() == playerData.z;
    }

    public void run() {
        Location loc = playerData.player.getLocation();
        if (isPlayerPositionNotChanged(playerData, loc)) {
            if (!isKickable) {
                playerData.player.playSound(loc, Sound.ENTITY_ENDERDRAGON_AMBIENT, 1, 1);
                playerData.player.sendMessage(ChatColor.YELLOW + "[AntiAFK] Rusz sie albo zostaniesz wyrzucony/a!");
            } else {
                new AntiAFKKick(playerData).runTask(AntiAFK.getMainPlugin());
                playersManager.deletePlayer(playerData);
            }
        }
    }
}
