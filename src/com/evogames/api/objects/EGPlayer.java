package com.evogames.api.objects;

import com.evogames.api.utils.Data;
import org.bukkit.entity.Player;

import java.net.InetSocketAddress;
import java.util.UUID;

public class EGPlayer {
    private UUID uuid;
    private String ip;
    private String username;
    private String rank;
    private double coins;
    private double coins2;

    public EGPlayer(UUID uuid, String ip, String username, String rank, double coins, double coins2) {
        this.uuid = uuid;
        this.ip = ip;
        this.username = username;
        this.rank = rank;
        this.coins = coins;
        this.coins2 = coins2;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public double getCoins() {
        return coins;
    }

    public void setCoins(double coins) {
        this.coins = coins;
    }

    public double getCoins2() {
        return coins2;
    }

    public void setCoins2(double coins2) {
        this.coins2 = coins2;
    }

    public static void createNew(Player player){
        EGPlayer egPlayer = new EGPlayer(player.getUniqueId(), player.getAddress().toString(), player.getName(), Rank.JOUEUR.toString(), 0.0, 0.0);
        EGFinder egFinder = new EGFinder(player.getUniqueId(), player.getName());
        Data.pushPlayerData(egPlayer);
        Data.createFinder(egFinder);
    }
}
