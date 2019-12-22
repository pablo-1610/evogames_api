package com.evogames.api.listeners;

import com.evogames.api.objects.EGFinder;
import com.evogames.api.objects.EGPlayer;
import com.evogames.api.objects.Message;
import com.evogames.api.objects.Rank;
import com.evogames.api.utils.Data;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryEvents implements Listener {
    @EventHandler
    public void inv(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        EGPlayer egPlayer = Data.getPlayerData(player.getUniqueId());
        Rank rank = Rank.getFromEGPlayer(egPlayer.getRank());
        if (e.getView().getTitle().equals("§8Gestion des grades")) {
            e.setCancelled(true);
            if (rank.getModerationPower() < 4) {
                return;
            }
            String target = e.getCurrentItem().getItemMeta().getLore().get(12).replaceAll("ID: ", "");
            String targetRank = e.getCurrentItem().getItemMeta().getLore().get(13).replaceAll("R.ID: ", "");
            EGFinder tofind = Data.getFinderData(target);
            EGPlayer toset = Data.getPlayerData(tofind.getUuid());
            toset.setRank(targetRank);
            Data.pushPlayerData(toset);
            player.sendMessage(Message.RANK_CHANGED.getContent() + " Le joueur §b" + target + "§a est désormais un " + Rank.getFromEGPlayer(targetRank).getPrefix() + " §a!");
            player.playSound(player.getLocation(), Sound.VILLAGER_YES, 100, 1);
        }
    }
}
