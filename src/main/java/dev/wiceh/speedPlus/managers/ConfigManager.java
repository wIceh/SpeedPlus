package dev.wiceh.speedPlus.managers;

import dev.wiceh.speedPlus.SpeedPlus;
import dev.wiceh.speedPlus.utils.LogUtils;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Optional;

public class ConfigManager {
    private final FileConfiguration config;

    public ConfigManager(SpeedPlus plugin) {
        this.config = plugin.getPlugin().getConfig();
    }

    public Optional<Material> getMaterial() {
        String material = config.getString("settings.block", "GOLD_BLOCK");
        try {
            return Optional.of(Material.valueOf(material));
        } catch (IllegalArgumentException e) {
            LogUtils.logError("Unable to get material '" + material + "'");
            return Optional.empty();
        }
    }

    public int getBoost() {
        return config.getInt("settings.boost", 3);
    }

    public int getCooldownTime() {
        return config.getInt("settings.cooldown-time", 5);
    }
}
