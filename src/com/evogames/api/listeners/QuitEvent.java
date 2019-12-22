package com.evogames.api.listeners;

import com.evogames.api.main.API;
import com.evogames.api.objects.EGPlayer;
import com.evogames.api.utils.Data;
import com.evogames.api.utils.Tablist;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {
    private API api;
    public QuitEvent(API api){
        this.api = api;
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void quit(PlayerQuitEvent e){
        e.setQuitMessage("");
        Player player = e.getPlayer();
        EGPlayer egPlayer = Data.getPlayerData(player.getUniqueId());
        Tablist.updatePlayer(api, false, egPlayer);
    }
}
