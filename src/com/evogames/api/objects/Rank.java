package com.evogames.api.objects;

import org.bukkit.Material;

public enum Rank {
    /*
    Niveaux de modération ;
    0 = Joueur, simple utilisateur
    1 = Assistant
    2 = T MOD / Mod
    3 = Super Mod
    4 = Admins / Fondateurs
     */
    FONDATEUR ("A", 4, 4,"§6✫ §4Fondateur ", Material.DIAMOND, 0),
    ADMIN ("B", 4, 4,"§6✫ §cAdmin ", Material.GOLD_BLOCK, 1),
    SMOD ("C", 3, 4, "§6✫ §6Mod§c+§6 ", Material.IRON_SWORD, 2),
    MOD ("D", 2, 4, "§6✫ §6Mod ", Material.STONE_SWORD, 3),
    TMOD ("E", 2, 4,"§6✫ §9T-Mod ", Material.WOOD_SWORD, 4),
    ASSISTANT ("F", 1, 4, "§6✫ §dAssistant ", Material.BOOK, 5),
    JOUEUR ("G", 0, 0, "§7Joueur ", Material.LEATHER, 6);

    private String tablistPower;
    private int moderationPower;
    private int vipPower;
    private String prefix;
    private Material guiMaterial;
    private int guiPosition;

    Rank(String tablistPower, int moderationPower, int vipPower, String prefix, Material guiMaterial, int guiPosition) {
        this.tablistPower = tablistPower;
        this.moderationPower = moderationPower;
        this.vipPower = vipPower;
        this.prefix = prefix;
        this.guiMaterial = guiMaterial;
        this.guiPosition = guiPosition;

    }


    public int getGuiPosition() {
        return guiPosition;
    }

    public Material getGuiMaterial() {
        return guiMaterial;
    }



    public String getTablistPower() {
        return tablistPower;
    }

    public int getVipPower() {
        return vipPower;
    }

    public int getModerationPower() {
        return moderationPower;
    }

    public String getPrefix() {
        return prefix;
    }

    public static Rank getFromEGPlayer(String rank){
        for(Rank ranks : Rank.values()){
            if(ranks.toString().equalsIgnoreCase(rank)){
                return ranks;
            }
        }
        return null;
    }
}
