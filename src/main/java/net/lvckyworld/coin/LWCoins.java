package net.lvckyworld.coin;
/*
 * ©2016-2021 LvckyWorld - By LvckyAPI all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 */

import net.lvckyworld.coin.commands.Coins;
import net.lvckyworld.coin.listeners.Join;
import net.lvckyworld.coin.utils.Config;
import net.lvckyworld.coin.utils.SystemManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;

public class LWCoins extends JavaPlugin {

    public static LWCoins getPlugin() {
        return plugin;
    }

    public static LWCoins plugin;

    @Override
    public void onEnable() {
        plugin = this;

        loadConfig();
        SystemManager.startUp();

        // Listener
        getServer().getPluginManager().registerEvents(new Join(), plugin);
        getCommand("coins").setExecutor(new Coins());
    }

    public static String prefix;
    public static Long startCoins;

    public void loadConfig() {
        if (!Config.configFile.exists()) {
            Config.config.set("Prefix", "&8[&5LW&8-&3Coins&8]");
            Config.config.set("FirstJoinCoins", 10);
            try {
                Config.save();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        prefix = Config.config.getString("Prefix").replaceAll("&", "§") + "§r ";
        startCoins = Config.config.getLong("FirstJoinCoins");
    }
}
