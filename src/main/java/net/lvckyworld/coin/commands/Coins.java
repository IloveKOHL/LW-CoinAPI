package net.lvckyworld.coin.commands;
/*
 * ©2016-2021 LvckyWorld - By LvckyAPI all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 */

import net.lvckyworld.coin.LWCoins;
import net.lvckyworld.coin.api.LWCoinsAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Coins implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player p = (Player) sender;
        if (args.length == 0) {
            // /coins
            Long coins = LWCoinsAPI.getCoins(p);
            p.sendMessage(LWCoins.prefix + "§7Du hast §6" + coins + "§7 Coins.");
        } else if (args.length == 1) {
            // /coins <Player>
        }
        return false;
    }
}
