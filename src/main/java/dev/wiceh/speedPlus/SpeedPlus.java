package dev.wiceh.speedPlus;

import dev.wiceh.speedPlus.managers.ConfigManager;
import dev.wiceh.speedPlus.server.listeners.VehicleListener;
import dev.wiceh.speedPlus.server.tasks.CooldownTask;
import org.bukkit.plugin.Plugin;

public class SpeedPlus {
    private final Plugin plugin;
    private final ConfigManager configManager;

    public SpeedPlus(Plugin plugin) {
        this.plugin = plugin;
        this.configManager = new ConfigManager(this);
    }

    public void enable() {
        plugin.saveDefaultConfig();

        // listeners
        plugin.getServer().getPluginManager().registerEvents(new VehicleListener(this), plugin);

        // tasks
        new CooldownTask(this).runTaskTimer(plugin, 0L, 20L);
    }

    public void disable() {

    }

    public Plugin getPlugin() {
        return plugin;
    }

    public ConfigManager getConfigManager() {
        return configManager;
    }
}
