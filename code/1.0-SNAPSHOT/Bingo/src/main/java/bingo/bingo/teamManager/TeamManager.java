package bingo.bingo.teamManager;

import bingo.bingo.Bingo;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.Objects;

/*
    队伍颜色：
    1.深蓝色：dark_blue \u00A71 RGB:0 0 42
    2.深红色：dark_red \u00A74 RGB:42 0 0
    3.黄色：yellow \u00A7e RGB:63 63 21
    4.深绿色：dark_green \u00A72 RGB:0 42 0
    5.粉红色：light_purple \u00A7d RGB:63 21 63
    6.天蓝色：aqua \u00A7b RGB:21 63 63
    7.金色：gold \u00A76 RGB:42 42 0
    8.紫色：dark_purple \u00A75 RGB:42 0 42
 */
public class TeamManager {
    final static Bingo plugin = Bingo.plugin;
    final static FileConfiguration config = Bingo.config;
    public static Scoreboard scoreboard = Bingo.scoreboard;
    public Team team;
    public ChatColor color;
    public String name;
    public boolean[][] bingo = new boolean[5][5];
    public ArrayList<Player> players = new ArrayList<>();
    public int score;

    TeamManager(ChatColor color, String name) {
        team = scoreboard.getTeam(name);
        if (Objects.isNull(team)) {
            team = scoreboard.registerNewTeam(name);
        }
        team.setAllowFriendlyFire(false);
        team.setColor(color);
        if (config.getBoolean("setPrefix")) {
            team.setPrefix(name);
        }
        this.color = color;
        this.name = name;
    }

    public Team getTeam() {
        return team;
    }

    public ChatColor getColor() {
        return color;
    }

    public void setColor(ChatColor color) {
        this.team.setColor(color);
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void addScore(int add) {
        this.score += add;
    }

//    void unregister() throws Throwable {
//        this.team.unregister();
//        this.finalize();
//    }

    public void bingoReset() {
        this.bingo = new boolean[5][5];
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public boolean hasPlayer(Player player) {
        return players.contains(player);
    }

    public boolean addPlayer(Player player) {
        if (hasPlayer(player)) {
            return false;
        }
        team.addEntry(player.getName());
        player.setScoreboard(scoreboard);
        players.add(player);
        return true;
    }

    public boolean delPlayer(Player player) {
        if (hasPlayer(player)) {
            team.removeEntry(player.getName());
            players.remove(player);
            return true;
        }
        return false;
    }
}
