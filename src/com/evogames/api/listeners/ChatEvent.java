package com.evogames.api.listeners;

import com.evogames.api.main.API;
import com.evogames.api.objects.EGPlayer;
import com.evogames.api.objects.Message;
import com.evogames.api.objects.Rank;
import com.evogames.api.utils.Data;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatEvent implements Listener {
    public static int actualServerAntispam = 2;
    public static boolean actualServerChatToggle = true;
    private static Map<UUID, Integer> actualPlayerCooldown = new HashMap<>();
    private API api;
    public ChatEvent(API api){
        this.api = api;
    }
    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void chat(AsyncPlayerChatEvent e) {
        EGPlayer egPlayer = Data.getPlayerData(e.getPlayer().getUniqueId());
        Rank playerRank = Rank.getFromEGPlayer(egPlayer.getRank());
        // Chat désactivé
        if (!actualServerChatToggle) {
            if (playerRank.getModerationPower() < 1) {
                e.setCancelled(true);
                Message.CHAT_OFF_PERMISSION.sendContent(e.getPlayer());
                return;
            }
        }
        // Cooldown
        if(actualServerAntispam > 0){
            if(playerRank.getModerationPower() < 1){
                if(actualPlayerCooldown.containsKey(e.getPlayer().getUniqueId())){
                    e.getPlayer().sendMessage(Message.CHAT_ON_COOLDOWN.getContent() + " (" + actualPlayerCooldown.get(egPlayer.getUuid()) + "s)");
                    e.setCancelled(true);
                    return;
                } else {
                    setOnCooldown(egPlayer);
                }
            }
        }
        // Envoi de messages
        String message = e.getMessage();
        if(playerRank.getVipPower() > 3) message = message.replaceAll("&", "§");
        if(playerRank.getModerationPower() > 0) {
            e.setFormat(playerRank.getPrefix() + egPlayer.getUsername() + " §6§l» §f" + message);
        } else {
            e.setFormat(playerRank.getPrefix() + egPlayer.getUsername() + " §6§l» §f" + message);
        }
    }

    private void setOnCooldown(EGPlayer egPlayer) {
        actualPlayerCooldown.put(egPlayer.getUuid(), actualServerAntispam);
        new BukkitRunnable() {
            int timeBeforeDemute = actualServerAntispam+1;
            @Override
            public void run() {
                timeBeforeDemute--;
                actualPlayerCooldown.replace(egPlayer.getUuid(), timeBeforeDemute);
                if(timeBeforeDemute <= 0){
                    actualPlayerCooldown.remove(egPlayer.getUuid());
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(api, 0, 20L);
    }
}
