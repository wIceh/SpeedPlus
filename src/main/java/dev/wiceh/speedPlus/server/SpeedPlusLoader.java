package dev.wiceh.speedPlus.server;

import dev.wiceh.speedPlus.SpeedPlus;
import org.bukkit.plugin.java.JavaPlugin;

public final class SpeedPlusLoader extends JavaPlugin {
    private final SpeedPlus speedPlus;

    public SpeedPlusLoader() {
        this.speedPlus = new SpeedPlus(this);
    }

    @Override
    public void onEnable() {
        speedPlus.enable();
    }

    @Override
    public void onDisable() {
        speedPlus.disable();
    }
}
