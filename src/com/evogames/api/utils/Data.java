package com.evogames.api.utils;

import com.evogames.api.main.API;
import com.evogames.api.objects.EGConfig;
import com.evogames.api.objects.EGFinder;
import com.evogames.api.objects.EGPlayer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.util.UUID;

public class Data {
    private static final String DATA_FORMAT = ".json";
    private static final String DATA_LOCATION = "../Data/";
    private static final String ACCOUNTS_LOCATION = DATA_LOCATION + "Comptes/";
    private static final String GAMESERVER_STATUS_LOCATION = DATA_LOCATION + "Serveurs/";
    private static final String STATS_LOCATION = DATA_LOCATION + "Stats/";
    private static final String FINDER_LOCATION = DATA_LOCATION + "Finder/";
    private static final String BANS_LOCATION = DATA_LOCATION + "Bans/";
    private static API api;
    private static Gson gson;
    // Constructeur
    public Data(API api){
        this.api = api;
        // Génération des dossiers de stockage si inéxistants
        File dataLoc = new File(DATA_LOCATION);
        File accountsLoc = new File(ACCOUNTS_LOCATION);
        File gameServLoc = new File(GAMESERVER_STATUS_LOCATION);
        File statsLoc = new File(STATS_LOCATION);
        File bansLoc = new File(BANS_LOCATION);
        File finderLoc = new File(FINDER_LOCATION);
        if(!dataLoc.exists()) dataLoc.mkdir();
        if(!accountsLoc.exists()) accountsLoc.mkdir();
        if(!gameServLoc.exists()) gameServLoc.mkdir();
        if(!statsLoc.exists()) statsLoc.mkdir();
        if(!bansLoc.exists()) bansLoc.mkdir();
        if(!finderLoc.exists()) finderLoc.mkdir();
    }
    // Checks & Gestion des données
    public static Boolean playerAccountIsStored(UUID uuid){
        File playerPotentialStoredLocation = new File(ACCOUNTS_LOCATION + uuid + DATA_FORMAT);
        return playerPotentialStoredLocation.exists();
    }
    public static Boolean playerFinderIsStored(String name){
        File playerPotentialStoredLocation = new File(FINDER_LOCATION + name + DATA_FORMAT);
        return playerPotentialStoredLocation.exists();
    }

    public static void pushPlayerData(EGPlayer playerData) {
        Gson gson;
        gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(ACCOUNTS_LOCATION + playerData.getUuid() + DATA_FORMAT);
        try {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Writer writer = new FileWriter(file);
            writer.write(gson.toJson(playerData));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createFinder(EGFinder finder) {
        Gson gson;
        gson = new GsonBuilder().setPrettyPrinting().create();
        File file = new File(FINDER_LOCATION + finder.getName() + DATA_FORMAT);
        try {
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Writer writer = new FileWriter(file);
            writer.write(gson.toJson(finder));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static EGPlayer getPlayerData(UUID uuid) {
        Gson gson;
        gson = new GsonBuilder().setPrettyPrinting().create();
        File jFile = new File(ACCOUNTS_LOCATION + uuid + DATA_FORMAT);
        Reader in;
        try {
            in = new FileReader(jFile);
            JsonReader reader = new JsonReader(in);
            EGPlayer jplayer = gson.fromJson(reader, EGPlayer.class);
            reader.close();
            in.close();
            return jplayer;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static EGFinder getFinderData(String name) {
        Gson gson;
        gson = new GsonBuilder().setPrettyPrinting().create();
        File jFile = new File(FINDER_LOCATION + name + DATA_FORMAT);
        Reader in;
        try {
            in = new FileReader(jFile);
            JsonReader reader = new JsonReader(in);
            EGFinder jplayer = gson.fromJson(reader, EGFinder.class);
            reader.close();
            in.close();
            return jplayer;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    public static EGConfig getGlobalConfig() {
        Gson gson;
        gson = new GsonBuilder().setPrettyPrinting().create();
        File jFile = new File("../evogames" + DATA_FORMAT);
        Reader in;
        try {
            in = new FileReader(jFile);
            JsonReader reader = new JsonReader(in);
            EGConfig jplayer = gson.fromJson(reader, EGConfig.class);
            reader.close();
            in.close();
            return jplayer;
        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
    }
}
