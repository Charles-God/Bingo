package bingo.bingo.teamManager;

import bingo.bingo.Bingo;
import bingo.bingo.util.Shuffle;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class TeamRegister {
    static Scoreboard scoreboard = Bingo.scoreboard;
    public static HashMap<Team, TeamManager> register = new HashMap<>();

    public static void register(ChatColor color, String name) {
        Team team = scoreboard.getTeam(name);
        TeamManager teamManager = new TeamManager(color, name);
        register.put(team, teamManager);
    }

//    public static void register(String name) {
//        register(ChatColor.RESET, name);
//    }

    public static void register(ChatColor color) {
        register(color, color.name());
    }

//    public static void unregister(Team team) throws Throwable {
//        if (!hasTeam(team)) {
//            return;
//        }
//        register.get(team).unregister();
//        register.remove(team);
//    }
//
//    public static void unregister(TeamManager teamManager) throws Throwable {
//        for (Team each_team : register.keySet()) {
//            if (register.get(each_team) == teamManager) {
//                unregister(each_team);
//            }
//        }
//    }
//
//    public static void unregister(String name) throws Throwable {
//        unregister(scoreboard.getTeam(name));
//    }
//
//    public static void unregisterAll() throws Throwable {
//        ArrayList<Team> teams = new ArrayList<>(register.keySet());
//        for (Team each_team : teams) {
//            TeamRegister.unregister(each_team);
//        }
//    }

    public static boolean hasTeam(Team team) {
        return register.containsKey(team);
    }

    public static boolean hasTeam(String name) {
        return hasTeam(scoreboard.getTeam(name));
    }

    public static boolean hasTeam(Player player) {
        return !Objects.isNull(fromPlayer(player));
    }

    public static TeamManager fromTeam(Team team) {
        return register.get(team);
    }

    public static TeamManager fromName(String name) {
        return fromTeam(scoreboard.getTeam(name));
    }

    public static TeamManager fromPlayer(Player player) {
        for (Team each_team : register.keySet()) {
            if (each_team.hasEntry(player.getName())) {
                return register.get(each_team);
            }
        }
        return null;
    }

    public static HashMap<Integer, TeamManager> getRank() {
        int length = register.size();
        TeamManager[] teamManagers = new TeamManager[length];
        int[] scores = new int[length];
        int index = 0;
        for (TeamManager each_teamManager : register.values()) {
            teamManagers[index] = each_teamManager;
            scores[index] = each_teamManager.getScore();
            index++;
        }
        Shuffle.quick_sort(scores, 0, length - 1, teamManagers);
        HashMap<Integer, TeamManager> scoresMap = new HashMap<>();
        for (index = 0; index < length; index++) {
            scoresMap.put(length - index, teamManagers[index]);
        }
        return scoresMap;
    }

    public enum TeamColor {
        dark_blue(0, 0, 255, "\\u00A71", "§1"),
        dark_red(255, 0, 0, "\\u00A74", "§4"),
        yellow(255, 255, 0, "\\u00A7e", "§e"),
        dark_green(0, 128, 0, "\\u00A72", "§2"),
        light_purple(255, 192, 203, "\\u00A7d", "§d"),
        aqua(0, 255, 255, "\\u00A7b", "§b"),
        gold(255, 215, 0, "\\u00A76", "§6"),
        dark_purple(128, 0, 128, "\\u00A75", "§5");

        public final int R;
        public final int G;
        public final int B;
        public final String MOTD;
        public final String code;

        TeamColor(int r, int g, int b, String MOTD, String code) {
            this.R = r;
            this.G = g;
            this.B = b;
            this.MOTD = MOTD;
            this.code = code;
        }

        public static TeamColor fromChatColor(ChatColor color) {
            if (ChatColor.DARK_BLUE.equals(color)) {
                return TeamColor.dark_blue;
            }
            if (ChatColor.DARK_RED.equals(color)) {
                return TeamColor.dark_red;
            }
            if (ChatColor.YELLOW.equals(color)) {
                return TeamColor.yellow;
            }
            if (ChatColor.DARK_GREEN.equals(color)) {
                return TeamColor.dark_green;
            }
            if (ChatColor.LIGHT_PURPLE.equals(color)) {
                return TeamColor.light_purple;
            }
            if (ChatColor.AQUA.equals(color)) {
                return TeamColor.aqua;
            }
            if (ChatColor.GOLD.equals(color)) {
                return TeamColor.gold;
            }
            if (ChatColor.DARK_PURPLE.equals(color)) {
                return TeamColor.dark_purple;
            }
            return null;
        }

        public ChatColor toChatColor() {
            return ChatColor.valueOf(this.toString().toUpperCase(Locale.ROOT));
        }
    }
}
