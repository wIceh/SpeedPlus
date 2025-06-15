package dev.wiceh.speedPlus.server.listeners;

import dev.wiceh.speedPlus.SpeedPlus;
import dev.wiceh.speedPlus.managers.ConfigManager;
import nl.sbdeveloper.vehiclesplus.api.VehiclesPlusAPI;
import nl.sbdeveloper.vehiclesplus.api.events.impl.KeyPressEvent;
import nl.sbdeveloper.vehiclesplus.api.vehicles.statics.VehicleStatics;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class VehicleListener implements Listener {
    private final ConfigManager configManager;

    private static final Map<UUID, Long> cooldowns = new HashMap<>();

    public VehicleListener(SpeedPlus plugin) {
        this.configManager = plugin.getConfigManager();
    }

    @EventHandler
    public void onKeyPress(KeyPressEvent event) {
        Player player = event.getPlayer();

        if (!(player.getVehicle() instanceof ArmorStand stand)) return;

        VehiclesPlusAPI.getVehicleFromPart(stand).ifPresent(vehicle -> {
            ArmorStand holder = vehicle.getHolder();
            if (holder == null || !holder.isOnGround()) return;

            Location location = holder.getLocation();
            Block under = location.getBlock().getRelative(0, -1, 0);

            configManager.getMaterial().ifPresent(material -> {
                if (under.getType() == material) {
                    if (cooldowns.containsKey(player.getUniqueId())) return;

                    VehicleStatics stats = vehicle.getStatics();
                    stats.setCurrentSpeed(stats.getCurrentSpeed() * configManager.getBoost());

                    cooldowns.put(player.getUniqueId(), System.currentTimeMillis());
                }
            });
        });
    }

    public static Map<UUID, Long> getCooldowns() {
        return cooldowns;
    }
}
