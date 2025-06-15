package dev.wiceh.speedPlus.utils;

import org.bukkit.Bukkit;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogUtils {
    private static final Logger LOGGER = Bukkit.getLogger();

    public static void logError(Throwable throwable, String message) {
        LOGGER.log(Level.SEVERE, throwable, () -> message);
    }

    public static void logError(String message) {
        LOGGER.log(Level.SEVERE, message);
    }
}
