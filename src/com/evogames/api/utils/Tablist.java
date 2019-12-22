package com.evogames.api.utils;

import com.evogames.api.main.API;
import com.evogames.api.objects.EGPlayer;
import com.evogames.api.objects.Rank;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

public class Tablist {
    public Tablist(API api){
        ScoreboardManager manager = api.getServer().getScoreboardManager();
        Scoreboard sb = manager.getMainScoreboard();
        sb.getTeams().forEach(team -> {
            team.getEntries().forEach(entry -> team.removeEntry(entry));
            team.unregister();
        });
        for(Rank ranks : Rank.values()){
            sb.registerNewTeam(ranks.getTablistPower() + ranks.toString());
            sb.getTeam(ranks.getTablistPower() + ranks.toString()).setPrefix(ranks.getPrefix());
        }
    }

    public static void updatePlayer(API api, Boolean add, EGPlayer egPlayer){
        if(add){
            Scoreboard sb = api.getServer().getScoreboardManager().getMainScoreboard();
            String rank = Rank.JOUEUR.toString();
            Rank rank1 = Rank.JOUEUR;
            for(Rank ranks : Rank.values()){
                if(egPlayer.getRank().equalsIgnoreCase(ranks.toString())) {
                    rank = ranks.toString();
                    rank1 = ranks;
                }
            }
            Team team = sb.getTeam(rank1.getTablistPower() + rank);
            team.addEntry(egPlayer.getUsername());
        } else {
            Scoreboard sb = api.getServer().getScoreboardManager().getMainScoreboard();
            String rank = Rank.JOUEUR.toString();
            Rank rank1 = Rank.JOUEUR;
            for(Rank ranks : Rank.values()){
                if(egPlayer.getRank() == ranks.toString()){
                    rank = ranks.toString();
                    rank1 = ranks;
                }
            }
            Team team = sb.getTeam(rank1.getTablistPower() + rank);
            team.removeEntry(egPlayer.getUsername());
        }
    }
}
