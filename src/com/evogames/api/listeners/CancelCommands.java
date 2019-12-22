package com.evogames.api.listeners;

import com.evogames.api.objects.Message;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class CancelCommands implements Listener {
    @EventHandler(priority = EventPriority.HIGH)
    public void cmd(PlayerCommandPreprocessEvent e){
        ArrayList<String> blCmds = new ArrayList<>();
        blCmds.add("?");
        blCmds.add("bukkit:?");
        blCmds.add("about");
        blCmds.add("bukkit:about");
        blCmds.add("help");
        blCmds.add("bukkit:help");
        blCmds.add("ver");
        blCmds.add("bukkit:ver");
        blCmds.add("version");
        blCmds.add("bukkit:version");
        //TODO -> Mettre /pl & plugins ici aussi gérés pas BUKKIT quand tous les plugins terminés
        blCmds.add("stop");
        blCmds.add("minecraft:stop");
        blCmds.add("spigot:restart");
        blCmds.add("restart");
        blCmds.add("me");
        blCmds.add("minecraft:me");
        blCmds.add("tell");
        blCmds.add("minecraft:tell");
        blCmds.add("say");
        blCmds.add("minecraft:say");

        String msg = e.getMessage().replaceAll("/", "");
        blCmds.forEach(command ->{
            if(command.equalsIgnoreCase(msg)){
                e.setCancelled(true);
                Message.COMMAND_DISABLE.sendContent(e.getPlayer());
                return;
            }
        });
    }
}
