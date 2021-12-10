package net.lvckyworld.coin.commands;
/*
 * ©2016-2021 LvckyWorld - By LvckyAPI all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 */

import io.netty.internal.tcnative.Buffer;
import net.lvckyworld.coin.LWCoins;
import net.lvckyworld.coin.api.LWCoinsAPI;
import org.bukkit.Bukkit;
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
            if (p.hasPermission("lwc.seeother")) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null) {
                    Long coins = LWCoinsAPI.getCoins(target);
                    p.sendMessage(LWCoins.prefix + "§7Der Spieler §3" + args[0] + "§7 hat §6" + coins + "§7 Coins.");
                } else {
                    Long coins = LWCoinsAPI.getOfflinePlayerCoins(args[0]);
                    if (coins != null) {
                        p.sendMessage(LWCoins.prefix + "§7Der Spieler §3" + args[0] + "§7 hat §6" + coins + "§7 Coins.");
                    } else {
                        p.sendMessage(LWCoins.prefix + "§4Da der Spieler offline ist muss er min. 1 Connected sein, um in der DatenBank registriert zu werden.");
                        p.sendMessage(" ");
                    }
                }
            } else {
                p.sendMessage(LWCoins.prefix + "§4Dazu fehlt dir die Permission: " + "lwc.seeother");
            }


        } else if (args.length == 3) {
            // /coins <set | add | remove> <Player> <Amount>
            if (p.hasPermission("lwc.admin")) {
                if (args[0].equalsIgnoreCase("set") || args[0].equalsIgnoreCase("add") || args[0].equalsIgnoreCase("remove")) {
                    Player target = Bukkit.getPlayer(args[1]);
                    try {Long.parseLong(args[2]);} catch (Exception e) {p.sendMessage(LWCoins.prefix + "§4Wrong usage: /coins <set | add | remove> <Player> <Amount>");}
                    if (target == null) {
                        p.sendMessage(LWCoins.prefix + "§4Da der Spieler offline ist muss er min. 1 Connected sein, um in der DatenBank registriert zu werden.");
                        p.sendMessage(" ");
                    }
                    // SET
                    if (args[0].equalsIgnoreCase("set")) {
                        if (target != null) {
                            LWCoinsAPI.update(target, Long.parseLong(args[2]));
                            p.sendMessage(LWCoins.prefix + "§7Der Spieler §3" + args[1] + "§7 hat jetzt §6" + args[2] + "§7 Coins.");
                        } else {
                            LWCoinsAPI.updateOffline(args[1], Long.parseLong(args[2]));
                            p.sendMessage(LWCoins.prefix + "§7Der Spieler §3" + args[1] + "§7 hat jetzt §6" + args[2] + "§7 Coins.");
                        }
                    }
                    // ADD
                    if (args[0].equalsIgnoreCase("add")) {
                        if (target != null) {
                            Long coins = LWCoinsAPI.getCoins(target);
                            long newCoins = coins + Long.parseLong(args[2]);
                            LWCoinsAPI.update(target, newCoins);
                            p.sendMessage(LWCoins.prefix + "§7Der Spieler §3" + args[1] + "§7 hat jetzt §6" + newCoins + "§7 Coins.");
                        } else {
                            Long coins = LWCoinsAPI.getOfflinePlayerCoins(args[1]);
                            long newCoins = coins + Long.parseLong(args[2]);
                            LWCoinsAPI.updateOffline(args[1], newCoins);
                            p.sendMessage(LWCoins.prefix + "§7Der Spieler §3" + args[1] + "§7 hat jetzt §6" + newCoins + "§7 Coins.");
                        }
                    }
                    // REMOVE
                    if (args[0].equalsIgnoreCase("remove")) {
                        if (target != null) {
                            Long coins = LWCoinsAPI.getCoins(target);
                            long newCoins = coins - Long.parseLong(args[2]);
                            LWCoinsAPI.update(target, newCoins);
                            p.sendMessage(LWCoins.prefix + "§7Der Spieler §3" + args[1] + "§7 hat jetzt §6" + newCoins + "§7 Coins.");
                        } else {
                            Long coins = LWCoinsAPI.getOfflinePlayerCoins(args[1]);
                            long newCoins = coins - Long.parseLong(args[2]);
                            LWCoinsAPI.updateOffline(args[1], newCoins);
                            p.sendMessage(LWCoins.prefix + "§7Der Spieler §3" + args[1] + "§7 hat jetzt §6" + newCoins + "§7 Coins.");
                        }
                    }
                } else {
                    p.sendMessage(LWCoins.prefix + "§4Wrong usage /coins <set | add | remove> <Player> <Amount>");
                }

            } else {
                p.sendMessage(LWCoins.prefix + "§4Dazu fehlt dir die Permission: " + "lwc.admin");
            }

        }
        return false;
    }
}
