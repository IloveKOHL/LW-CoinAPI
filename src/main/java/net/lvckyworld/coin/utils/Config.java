package net.lvckyworld.coin.utils;
/*
 * ©2016-2021 LvckyWorld - By LvckyAPI all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 */

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Config {

    public static File configFile = new File("plugins/Coins", "config.yml");
    public static FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);


    public static void save() throws IOException {
        config.save(configFile);
    }
}
