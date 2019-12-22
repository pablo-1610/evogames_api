package com.evogames.api.main;

import com.evogames.api.commands.Rank;
import com.evogames.api.listeners.*;
import com.evogames.api.utils.Data;
import com.evogames.api.utils.Tablist;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.command.Command;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Arrays;



public class API extends JavaPlugin {
    private static API INSTANCE;
    private static String PREFIX;

    public static API getInstance() {
        return INSTANCE;
    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        PREFIX = "[" + this.getName() + "] ";
        new Data(this);
        new Tablist(this);
        this.loadListeners(this);
        this.registerCommands(this);
    }

    private void loadListeners(API api) {;
        Arrays.asList(
                new JoinEvent(this),
                new QuitEvent(this),
                new ChatEvent(this),
                new InventoryEvents(),
                new CancelCommands()
        ).forEach(event ->{
            this.getServer().getPluginManager().registerEvents((Listener) event, (Plugin)this);
        });
    }

    private void registerCommands(API api) {
        Arrays.asList(new Command[]{new Rank(api)}).forEach(this::registerCommand);
    }

    public void registerCommand(Command cmd, String fallbackPrefix) {
        MinecraftServer.getServer().server.getCommandMap().register(cmd.getName(), fallbackPrefix, cmd);
    }

    private void registerCommand(Command cmd) {
        this.registerCommand(cmd, this.getName());
    }

    public static void log(String input){
        System.out.println(PREFIX + input);
    }
}
