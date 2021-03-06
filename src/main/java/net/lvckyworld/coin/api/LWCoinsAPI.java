package net.lvckyworld.coin.api;
/*
 * ©2016-2021 LvckyWorld - By LvckyAPI all Rights reserved
 * Licensed to Iven Schlenther & Lukas Oetken
 */

import net.lvckyworld.coin.mysql.MySQL;
import org.bukkit.entity.Player;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class LWCoinsAPI {

    /**
     * Check if User exist per UUID in Database
     * @param p The player whose existence is to be checked in the database
     * @return If exist = true else false
     */
    public static boolean isUserExist(Player p){
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Spielername FROM Coins WHERE UUID = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    /**
     * Check if User exist per Name in Database (Check if offline User exists)
     * @param playerName The player whose existence is to be checked in the database b< Name
     * @return If exist = true else false
     */
    public static boolean isOfflineUserExist(String playerName){
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Coins FROM Coins WHERE Spielername = ?");
            ps.setString(1, playerName);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }


    public static void firstConnect(Player p, Long value) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO Coins (UUID,Spielername,Coins) VALUE (?,?,?)");
            ps.setString(1, p.getUniqueId().toString());
            ps.setString(2, p.getName());
            ps.setLong(3, value);
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Update the Coins of a Player
     * @param p The player to whom the money will be updated so
     * @param value The money to be wagered on the player
     */
    public static void update(Player p, Long value) {
        if (isUserExist(p)) {
            try {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE Coins SET Coins = ? WHERE UUID = ?");
                ps.setLong(1, value);
                ps.setString(2, p.getUniqueId().toString());
                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            try {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("INSERT INTO Coins (UUID,Spielername,Coins) VALUE (?,?,?)");
                ps.setString(1, p.getUniqueId().toString());
                ps.setString(2, p.getName());
                ps.setLong(3, value);
                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    /**
     * Update the Add Coins to Player
     * @param p The player to whom the money will be updated so
     * @param value The money to be wagered on the player
     */
    public static void addCoins(Player p, Long value) {
        long coins = getCoins(p);
        long newCoins = coins + value;
        update(p, newCoins);
    }

    /**
     * Update the Add Coins to Player
     * @param playerName The player to whom the money will be updated so
     * @param value The money to be wagered on the player
     */
    public static void addCoinsOffline(String playerName, Long value) {
        long coins = getOfflinePlayerCoins(playerName);
        long newCoins = coins + value;
        updateOffline(playerName, newCoins);
    }

    /**
     * Update the Remove Coins to Player
     * @param p The player to whom the money will be updated so
     * @param value The money to be wagered on the player
     */
    public static void removeCoins(Player p, Long value) {
        long coins = getCoins(p);
        long newCoins = coins - value;
        update(p, newCoins);
    }

    /**
     * Update the Remove Coins to Player
     * @param playername The player to whom the money will be updated so
     * @param value The money to be wagered on the player
     */
    public static void removeCoinsOffline(String playername, Long value) {
        long coins = getOfflinePlayerCoins(playername);
        long newCoins = coins - value;
        updateOffline(playername, newCoins);
    }


    /**
     * Get the Coins of the Player
     * @param p The player from whom the account Coins is to be checked
     * @return Account Coins of p (Player)
     */
    public static Long getCoins(Player p) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Coins FROM Coins WHERE UUID = ?");
            ps.setString(1, p.getUniqueId().toString());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getLong("Coins");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }



    /**
     * Gives you the account Coins of a player who is offline
     * @param playerName PlayerName (String)
     * @return Account Coins
     */
    public static Long getOfflinePlayerCoins(String playerName) {
        try {
            PreparedStatement ps = MySQL.getConnection().prepareStatement("SELECT Coins FROM Coins WHERE UUID = ?");
            ps.setString(1, playerName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getLong("Coins");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    /**
     * Update the Coins of a offlinePlayer
     * @param playerName The player to whom the money will be updated so
     * @param value The money to be wagered on the player
     */
    public static void updateOffline(String playerName, Long value){
        if (isOfflineUserExist(playerName)) {
            try {
                PreparedStatement ps = MySQL.getConnection().prepareStatement("UPDATE Coins SET Coins = ? WHERE Spielername = ?");
                ps.setLong(1, value);
                ps.setString(2, playerName);
                ps.executeUpdate();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            System.err.println(MySQL.sqlPrefix + "Failed, Player not exist");
        }
    }
}
