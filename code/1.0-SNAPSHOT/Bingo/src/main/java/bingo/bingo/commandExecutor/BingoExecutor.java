package bingo.bingo.commandExecutor;

import bingo.bingo.Bingo;
import bingo.bingo.bingoGame.BingoGame;
import bingo.bingo.bingoGame.BingoPrepare;
import bingo.bingo.bingoGame.BingoEnd;
import bingo.bingo.bingoMap.BingoMapDrawer;
import bingo.bingo.teamManager.TeamManager;
import bingo.bingo.teamManager.TeamRegister;
import bingo.bingo.util.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.IOException;
import java.util.*;

/*
bingo team add [name] X
               [name] [color] X
           remove [name] X
           list
           list [name]
           join [player] [name]
           leave [player]
           random [amount] -> random
           randomElse [amount] -> randomElse
      game start [difficulty]
           stop
           result
      settings enablePvp [true/false]
               killLoot [true/false]
               respawn [true/false]
               spawnRadius [r]
               borderRadius [r]
               refill [true/false]
      clear
 */
public class BingoExecutor implements TabExecutor {
    private final Scoreboard scoreboard = Bingo.scoreboard;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (label.equalsIgnoreCase("bingo")) {
            switch (args.length) {
                case 0:
                    bingoHelp(sender, "bingo");
                    return true;
                case 1:
                    switch (args[0].toLowerCase(Locale.ROOT)) {
                        case "help":
                            bingoHelp(sender, "bingo");
                            return true;
                        case "team":
                            bingoHelp(sender, "team");
                            return true;
                        case "game":
                            bingoHelp(sender, "game");
                            return true;
                        case "settings":
                            bingoHelp(sender, "settings");
                            return true;
                        case "clear":
//                            try {
//                                TeamRegister.unregisterAll();
//                            } catch (Throwable e) {
//                                e.printStackTrace();
//                                return false;
//                            }
                            for (Team each_team : scoreboard.getTeams()) {
                                each_team.unregister();
                            }
                            for (Player each_player : BingoPrepare.getAllPlayers()) {
                                each_player.getInventory().clear();
                            }
                            BingoEnd.resetWorld();
                            //todo world
                            return true;
                        default:
                            return false;
                    }
                default:
                    switch (args[0].toLowerCase(Locale.ROOT)) {
                        case "team":
                            Player player;
                            TeamManager teamManager;
                            int amount;
                            switch (args[1].toLowerCase(Locale.ROOT)) {
//                                case "add":
//                                    if (TeamRegister.hasTeam(args[2])) {
//                                        sender.sendMessage("§4队伍§r" + args[2] + "§r§4已经被创建！");
//                                        return true;
//                                    }
//                                    switch (args.length) {
//                                        case 3:
//                                            TeamRegister.register(args[2]);
//                                            sender.sendMessage("§a队伍§r" + args[2] + "§r§a创建成功！");
//                                            return true;
//                                        case 4:
//                                            TeamRegister.register(ChatColor.valueOf(args[3].toUpperCase(Locale.ROOT)), args[2]);
//                                            sender.sendMessage("§a队伍§l" + ChatColor.valueOf(args[3]) +
//                                                    args[2] + "§r§a创建成功！");
//                                            return true;
//                                        default:
//                                            return false;
//                                    }
//                                case "remove":
//                                    if (args.length > 3) {
//                                        return false;
//                                    }
//                                    if (!TeamRegister.hasTeam(args[2])) {
//                                        sender.sendMessage("§4队伍§r" + args[2] + "§r§4还未被创建！");
//                                        return true;
//                                    }
//                                    try {
//                                        TeamRegister.unregister(args[2]);
//                                    } catch (Throwable e) {
//                                        e.printStackTrace();
//                                        return false;
//                                    }
//                                    sender.sendMessage("§a队伍§r" + args[2] + "§r§a删除成功！");
//                                    return true;
                                case "list":
                                    switch (args.length) {
                                        case 2:
                                            HashMap<Team, TeamManager> register = TeamRegister.register;
                                            StringBuilder teamList = new StringBuilder("/");
                                            for (Team each_team : register.keySet()) {
                                                teamList.append(each_team.getColor()).append(each_team.getName()).append("§r/");
                                            }
                                            sender.sendMessage(teamList.toString());
                                            return true;
                                        case 3:
                                            if (!TeamRegister.hasTeam(args[2])) {
                                                sender.sendMessage("§4队伍§r" + args[2] + "§r§4不存在！");
                                                return true;
                                            }
                                            teamManager = TeamRegister.fromName(args[2]);
                                            ArrayList<Player> players = teamManager.getPlayers();
                                            StringBuilder playerList = new StringBuilder("/");
                                            for (Player eachPlayer : players) {
                                                playerList.append(eachPlayer.getName()).append("/");
                                            }
                                            sender.sendMessage("§a队伍§r" + args[2] + "§a的玩家如下：");
                                            sender.sendMessage(playerList.toString());
                                            return true;
                                        default:
                                            return false;
                                    }
                                case "join":
                                    if (args.length > 4) {
                                        return false;
                                    }
                                    if (!TeamRegister.hasTeam(args[3])) {
                                        sender.sendMessage("§4队伍§r" + args[3] + "§r§4不存在！");
                                        return true;
                                    }
                                    player = Bukkit.getPlayerExact(args[2]);
                                    if (player == null) {
                                        sender.sendMessage("§4玩家§r" + args[2] + "§r§4不存在！");
                                        return true;
                                    }
                                    if (!Objects.isNull(TeamRegister.fromPlayer(player))) {
                                        sender.sendMessage("§4玩家§r" + args[2] + "§r§4已经有队伍！");
                                        return true;
                                    }
                                    teamManager = TeamRegister.fromName(args[3]);
                                    teamManager.addPlayer(player);
                                    sender.sendMessage("§a玩家§r" + args[2] + "§r§a成功进入" +
                                            args[3] + "§r§a队伍！");
                                    return true;
                                case "leave":
                                    if (args.length > 3) {
                                        return false;
                                    }
                                    player = Bukkit.getPlayerExact(args[2]);
                                    if (player == null) {
                                        sender.sendMessage("§4玩家§r" + args[2] + "§r§4不存在！");
                                        return true;
                                    }
                                    teamManager = TeamRegister.fromPlayer(player);
                                    if (teamManager == null) {
                                        sender.sendMessage("§4玩家§r" + args[2] + "§r§4不在队伍中！");
                                        return true;
                                    }
                                    teamManager.delPlayer(player);
                                    sender.sendMessage("§a玩家§r" + args[2] + "§r§a成功退出队伍！");
                                    return true;
                                case "random":
                                    if (args.length > 2) {
                                        return false;
                                    }
//                                    try {
//                                        TeamRegister.unregisterAll();
//                                    } catch (Throwable e) {
//                                        e.printStackTrace();
//                                        return false;
//                                    }
                                    ArrayList<Player> allPlayers = BingoPrepare.getAllPlayers();
                                    for (Player each_player : allPlayers) {
                                        if (TeamRegister.fromPlayer(each_player) == null) {
                                            continue;
                                        }
                                        TeamRegister.fromPlayer(each_player).delPlayer(each_player);
                                    }
                                    BingoPrepare.randomTeam(allPlayers, TeamRegister.register.size());
                                    return true;
                                case "randomelse":
                                    if (!(sender instanceof Player)) {
                                        sender.sendMessage("§4只有玩家才能执行这个命令！");
                                        return true;
                                    }
                                    if (args.length > 2) {
                                        return false;
                                    }
//                                    try {
//                                        TeamRegister.unregisterAll();
//                                    } catch (Throwable e) {
//                                        e.printStackTrace();
//                                        return false;
//                                    }
                                    ArrayList<Player> allPlayersExcept = BingoPrepare.getAllPlayersExcept((Player) sender);
                                    for (Player each_player : allPlayersExcept) {
                                        if (TeamRegister.fromPlayer(each_player) == null) {
                                            continue;
                                        }
                                        TeamRegister.fromPlayer(each_player).delPlayer(each_player);
                                    }
                                    BingoPrepare.randomTeam(allPlayersExcept, TeamRegister.register.size());
                                    return true;
                            }
                        case "game":
                            switch (args[1].toLowerCase(Locale.ROOT)) {
                                case "start":
                                    if (args.length != 3) {
                                        return false;
                                    }
                                    if (BingoGame.start) {
                                        sender.sendMessage("§4已经有游戏开始！");
                                        return true;
                                    }
                                    if (Config.refill && TeamRegister.register.size() != 4 && TeamRegister.register.size() != 8) {
                                        sender.sendMessage("§4请关闭refill或调整总队伍数！");
                                        return true;
                                    }
                                    switch (args[2].toLowerCase(Locale.ROOT)) {
                                        case "easy":
                                            BingoPrepare.startEasyBingo((Player) sender);
                                            return true;
                                        case "item":
                                            BingoPrepare.startItemBingo((Player) sender);
                                            return true;
                                        case "normal":
                                            BingoPrepare.startNormalBingo((Player) sender);
                                            return true;
                                        case "nether":
                                            BingoPrepare.startNetherBingo((Player) sender);
                                            return true;
                                        case "end":
                                            BingoPrepare.startEndBingo((Player) sender);
                                            return true;
                                        case "impossible":
                                            BingoPrepare.startImpossibleBingo((Player) sender);
                                            return true;
                                        case "possible":
                                            BingoPrepare.startPossibleBingo((Player) sender);
                                            return true;
                                        default:
                                            return false;
                                    }
                                case "result":
                                    BingoEnd.getResult();
                                    return true;
                                case "stop":
                                    if (BingoGame.start) {
                                        BingoGame.stopGame();
                                    }
                                    if (!BingoGame.start) {
                                        BingoGame.continueGame();
                                    }//todo
                                    return true;
                                default:
                                    return false;
                            }
                        case "settings":
                            if (args.length > 3) {
                                return false;
                            }
                            if (args.length == 2) {
                                switch (args[1].toLowerCase(Locale.ROOT)) {
                                    case "enablepvp":
                                        sender.sendMessage("enablePvP:" + Config.enablePvp);
                                        return true;
                                    case "killloot":
                                        sender.sendMessage("killLoot:" + Config.killLoot);
                                        return true;
                                    case "respawn":
                                        sender.sendMessage("respawn:" + Config.respawn);
                                        return true;
                                    case "spawnradius":
                                        sender.sendMessage("spawnRadius:" + Config.spawnRadius);
                                        return true;
                                    case "borderradius":
                                        sender.sendMessage("borderRadius:" + Config.borderRadius);
                                        return true;
                                    case "refill":
                                        sender.sendMessage("refill:" + Config.refill);
                                        return true;
                                    default:
                                        return false;
                                }
                            }
                            switch (args[1].toLowerCase(Locale.ROOT)) {
                                case "enablepvp":
                                    boolean enablePvp;
                                    try {
                                        enablePvp = Boolean.parseBoolean(args[2]);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        return false;
                                    }
                                    Config.enablePvp = enablePvp;
                                    sender.sendMessage("enablePvP:" + Config.enablePvp);
                                    return true;
                                case "killloot":
                                    boolean killLoot;
                                    try {
                                        killLoot = Boolean.parseBoolean(args[2]);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        return false;
                                    }
                                    Config.killLoot = killLoot;
                                    sender.sendMessage("killLoot:" + Config.killLoot);
                                    return true;
                                case "respawn":
                                    boolean respawn;
                                    try {
                                        respawn = Boolean.parseBoolean(args[2]);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        return false;
                                    }
                                    Config.respawn = respawn;
                                    sender.sendMessage("respawn:" + Config.respawn);
                                    return true;
                                case "spawnradius":
                                    int spawnRadius;
                                    try {
                                        spawnRadius = Integer.parseInt(args[2]);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        return false;
                                    }
                                    Config.spawnRadius = spawnRadius;
                                    sender.sendMessage("spawnRadius:" + Config.spawnRadius);
                                case "borderradius":
                                    int borderRadius;
                                    try {
                                        borderRadius = Integer.parseInt(args[2]);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        return false;
                                    }
                                    Config.borderRadius = borderRadius;
                                    sender.sendMessage("borderRadius:" + Config.borderRadius);
                                case "refill":
                                    boolean refill;
                                    try {
                                        refill = Boolean.parseBoolean(args[2]);
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        return false;
                                    }
                                    Config.refill = refill;
                                    sender.sendMessage("refill:" + Config.refill);
                                    return true;
                                default:
                                    return false;
                            }
                        case "debug":
                            int i = Integer.parseInt(args[1]);
                            int j = Integer.parseInt(args[2]);
                            BingoMapDrawer.fullFill(i, j, TeamRegister.TeamColor.dark_blue);
                            return true;
                        default:
                            return false;
                    }
            }
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        return null;
    }

    public void bingoHelp(CommandSender sender, String stage) {
        switch (stage) {
            case "bingo":
                sender.sendMessage("========== Bingo Help ==========");
                sender.sendMessage("§a/bingo [team/game/settings]");
                sender.sendMessage("========== Bingo Help ==========");
                break;
            case "team":
                sender.sendMessage("========== Bingo Team Help ==========");
                sender.sendMessage("§aadd [name]\n" +
                        "add [name] [color]\n" +
                        "remove [name]\n" +
                        "list\n" +
                        "list [name]\n" +
                        "join [player] [name]\n" +
                        "leave [player]\n" +
                        "random [amount]\n" +
                        "randomElse [amount]");
                sender.sendMessage("========== Bingo Team Help ==========");
                break;
            case "game":
                sender.sendMessage("========== Bingo Game Help ==========");
                sender.sendMessage("§astart [difficulty]\n" +
                        "result\n" +
                        "stop");
                sender.sendMessage("========== Bingo Game Help ==========");
                break;
            case "settings":
                sender.sendMessage("========== Bingo Settings Help ==========");
                sender.sendMessage("§aenablePvp [true/false]\n" +
                        "killLoot [true/false]\n" +
                        "respawn [true/false]\n" +
                        "spawnRadius [r]\n" +
                        "borderRadius [r]\n" +
                        "refill [true/false]");
                sender.sendMessage("========== Bingo Settings Help ==========");
                break;
            default:
                break;
        }
    }
}
