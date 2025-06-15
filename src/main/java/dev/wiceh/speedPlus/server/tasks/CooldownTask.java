package dev.wiceh.speedPlus.server.tasks;

import dev.wiceh.speedPlus.SpeedPlus;
import dev.wiceh.speedPlus.managers.ConfigManager;
import dev.wiceh.speedPlus.server.listeners.VehicleListener;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

public class CooldownTask extends BukkitRunnable {
    private final ConfigManager configManager;

    public CooldownTask(SpeedPlus plugin) {
        this.configManager = plugin.getConfigManager();
    }

    @Override
    public void run() {
        Map<UUID, Long> cooldowns = VehicleListener.getCooldowns();
        Iterator<Map.Entry<UUID, Long>> iterator = cooldowns.entrySet().iterator();

        long now = System.currentTimeMillis();

        while (iterator.hasNext()) {
            Map.Entry<UUID, Long> entry = iterator.next();
            UUID uuid = entry.getKey();
            long start = entry.getValue();

            Player player = Bukkit.getPlayer(uuid);
            if (player == null) continue;

            int cooldownTime = configManager.getCooldownTime() * 1000;
            if (now - start > cooldownTime)
                iterator.remove();
        }
    }
}
