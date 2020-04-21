package com.ems.minecraft.plotmenu.config;

import org.bukkit.configuration.file.FileConfiguration;

public class ConfigHandler {


    public static void initConfig(FileConfiguration config) {
        config.addDefault("NumberSlots", 9);
        config.addDefault( "InventoryName", "Plotmenü");
        for (int i = 1; i < 5; i++) {
            config.addDefault("Slots." + i + ".material", "DIAMOND_AXE");
            config.addDefault("Slots." + i + ".name", "Name in GUI");
            config.addDefault("Slots." + i + ".description", "Description for item "+i);
            config.addDefault("Slots." + i + ".command", "/p h");
        }
        config.options().copyDefaults(true);
    }
}
