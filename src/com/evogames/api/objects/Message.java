package com.evogames.api.objects;

import org.bukkit.entity.Player;

public enum  Message {
    // Errors
    MISSING_PERMISSION ("§cVous n'avez pas la permission de faire cela !"),
    CHAT_OFF_PERMISSION ("§cLe chat est actuelement muet, seul le staff peut parler dans ce serveur."),
    CHAT_ON_COOLDOWN ("§cVeuillez patienter avant de re-parler !"),
    // Warns
    MISSING_PLAYER_ARG ("§6Veuillez préciser un joueur"),
    PLAYER_NOT_STORED("§6Ce joueur ne s'est jamais connecté sur EvoGames !"),
    // Infos
    COMMAND_DISABLE("§3Cette commande est désactivée"),
    // Sucess
    RANK_CHANGED ("§aGrade changé !");

    private String content;

    Message(String content) {
        this.content = content;
    }

    public void sendContent(Player player){
        player.sendMessage(content);
    }

    public String getContent(){
        return content;
    }

}
