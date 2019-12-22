package com.evogames.api.commands;

import com.evogames.api.main.API;
import com.evogames.api.objects.EGFinder;
import com.evogames.api.objects.EGPlayer;
import com.evogames.api.objects.Message;
import com.evogames.api.utils.Data;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Rank extends Command {
    private API api;
    public Rank(API api){
        super("rank");
        this.api = api;
    }
    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if(!(sender instanceof Player)) return false;
        Player player = (Player) sender;
        EGPlayer egPlayer = Data.getPlayerData(player.getUniqueId());
        com.evogames.api.objects.Rank rank = com.evogames.api.objects.Rank.getFromEGPlayer(egPlayer.getRank());
        if(rank.getModerationPower() < 4){
            Message.MISSING_PERMISSION.sendContent(player);
            return false;
        }
        if(args.length == 0){
            Message.MISSING_PLAYER_ARG.sendContent(player);
            return false;
        }
        String target = args[0];
        if(!Data.playerFinderIsStored(target)){
            Message.PLAYER_NOT_STORED.sendContent(player);
            return false;
        }
        EGFinder egFinder = Data.getFinderData(target);
        EGPlayer targetPlayer = Data.getPlayerData(egFinder.getUuid());
        Inventory inv = Bukkit.createInventory(null, 18, "§8Gestion des grades");
        for(com.evogames.api.objects.Rank ranks : com.evogames.api.objects.Rank.values()) {
            ItemStack guiStack = new ItemStack(ranks.getGuiMaterial(), 1);
            ItemMeta guiMeta = guiStack.getItemMeta();
            guiMeta.setDisplayName(ranks.getPrefix() + target);
            guiMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_PLACED_ON, ItemFlag.HIDE_POTION_EFFECTS);
            guiMeta.setLore(Arrays.asList("","§dCible: §b" + target + "", "", "§e•----• §bDescription §e•----•", "", "§6Niv. Permission : §b" + ranks.getModerationPower(), "§6Niv. VIP : §b" + ranks.getVipPower(), "§6Pos. Hiérarchique : §b" + ranks.getGuiPosition() + "", "", "§7Cliquez sur cet item pour", "§7attribuer ce rang à §b" + target, "", "ID: " + target, "R.ID: " + ranks.toString() ));
            guiStack.setItemMeta(guiMeta);
            inv.setItem(ranks.getGuiPosition(), guiStack);
            ItemStack barr = new ItemStack(Material.BARRIER, 1);
            ItemMeta barrmeta = barr.getItemMeta();
            barrmeta.setDisplayName("§c§lQuitter");
            barr.setItemMeta(barrmeta);
            inv.setItem(17, barr);
        }
        player.openInventory(inv);
        return true;

    }
}
