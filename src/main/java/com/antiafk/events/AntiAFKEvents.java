package com.antiafk.events;

        import com.antiafk.AntiAFK;
        import com.antiafk.managers.AntiAFKPlayersManager;
        import org.bukkit.entity.Player;
        import org.bukkit.event.EventHandler;
        import org.bukkit.event.Listener;
        import org.bukkit.event.player.PlayerJoinEvent;

public class AntiAFKEvents implements Listener {
    AntiAFKPlayersManager playersManager = AntiAFK.getPlayersManager();
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        playersManager.addPlayer(player);
    }
}
