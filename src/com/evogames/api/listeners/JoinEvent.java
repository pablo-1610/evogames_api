package com.evogames.api.listeners;

import com.evogames.api.main.API;
import com.evogames.api.objects.EGPlayer;
import com.evogames.api.utils.Data;
import com.evogames.api.utils.Tablist;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinEvent implements Listener {
    private API api;
    public JoinEvent(API api){
        this.api = api;
    }
    @EventHandler(priority = EventPriority.HIGH)
    public void join(PlayerJoinEvent e){
        e.setJoinMessage("");
        Player player = e.getPlayer();
        if(!Data.playerAccountIsStored(player.getUniqueId())){
            EGPlayer.createNew(player);
        }
        EGPlayer egPlayer = Data.getPlayerData(player.getUniqueId());
        Tablist.updatePlayer(api, true, egPlayer);
    }



}
