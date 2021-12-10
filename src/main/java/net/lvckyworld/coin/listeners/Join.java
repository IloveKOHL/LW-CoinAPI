package net.lvckyworld.coin.listeners;
/*
 * ©2016-2021 LvckyWorld - By LvckyAPI all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 */

import net.lvckyworld.coin.LWCoins;
import net.lvckyworld.coin.api.LWCoinsAPI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (!LWCoinsAPI.isUserExist(p)) {
            LWCoinsAPI.firstConnect(p, LWCoins.startCoins);
        }
    }
}
