package com.antiafk;
import com.antiafk.events.AntiAFKEvents;
import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class AntiAFK extends JavaPlugin{
    static JavaPlugin plugin;
    public static JavaPlugin getMainPlugin() {
        return plugin;
    }
    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "[AntiAFK] Plugin is enabled");
        getServer().getPluginManager().registerEvents(new AntiAFKEvents(), this);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getServer().getConsoleSender().sendMessage(ChatColor.RED + "[AntiAFK] Plugin is disabled");
    }
}
// testing github webhook

