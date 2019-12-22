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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.jar.JarInputStream;
import java.util.zip.ZipEntry;


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
        /*System.out.println(PREFIX + " Downloading lastest version from Github");
        File file = new File("./EvogamesAPI.jar");
        file.delete();
        extractAllEntries("https://github.com/PABLO-1610/EvogamesAPI/raw/master/out/artifacts/EvogamesAPI_jar/EvogamesAPI.jar", "./");
        this.getServer().reload();*/
    }




    public static void extractAllEntries(String urlPath, String destination) {
        try {
            byte[] buffer = new byte[1024];
            InputStream input = new URL(urlPath).openStream();
            JarInputStream jin = new JarInputStream(input);
            ZipEntry entry;

            while ((entry = jin.getNextEntry()) != null) {
                ZipEntry file = (ZipEntry) entry;
                File f = new java.io.File(destination + java.io.File.separator
                        + file.getName());
                if (file.isDirectory()) {
                    f.mkdir();
                    continue;
                }
                FileOutputStream fos = new FileOutputStream(f);
                int len;
                while ((len = jin.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
            }
            jin.closeEntry();
            jin.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
