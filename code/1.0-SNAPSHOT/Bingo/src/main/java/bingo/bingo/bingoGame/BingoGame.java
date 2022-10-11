package bingo.bingo.bingoGame;

import bingo.bingo.Bingo;
import bingo.bingo.bingoItem.RandomItem;
import bingo.bingo.bingoMap.BingoMapDrawer;
import bingo.bingo.eventListener.GetItemListener;
import bingo.bingo.teamManager.TeamManager;
import bingo.bingo.teamManager.TeamRegister;
import bingo.bingo.util.Config;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.scoreboard.Team;

import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class BingoGame {
    public static boolean start;
    private static final Bingo plugin = Bingo.plugin;
    private static final GetItemListener getItemListener = GetItemListener.getItemListener;
    public static boolean[][] allGained = new boolean[5][5];

    public static void bingoGet(Player player, int i, int j) {
        TeamManager teamManager = TeamRegister.fromPlayer(player);
        if (Objects.isNull(teamManager)) {
            return;
        }
        if (allGained[i][j] && !Config.refill) {
            return;
        }
        teamManager.bingo[i][j] = true;
        allGained[i][j] = true;
        if (!Config.refill) {
            BingoMapDrawer.fullFill(i, j, TeamRegister.TeamColor.fromChatColor(teamManager.color));
        } else if (TeamRegister.register.size() == 4) {
            BingoMapDrawer.t4Fill(i, j, TeamRegister.TeamColor.fromChatColor(teamManager.color));
        } else if (TeamRegister.register.size() == 8) {
            BingoMapDrawer.t8Fill(i, j, TeamRegister.TeamColor.fromChatColor(teamManager.color));
        }
        plugin.getServer().broadcastMessage("§a玩家§l" + teamManager.color + player.getName() +
                "§r§a第一个获取了§l" + teamManager.color + RandomItem.getItem(i + 5 * j) + "§r§a！");
        if (bingoSuccess(teamManager)) {
            BingoEnd.BingoSuccess(teamManager);
        }
    }

    public static boolean bingoSuccess(TeamManager teamManager) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (!teamManager.bingo[i][j]) {
                    break;
                }
                if (j == 4) {
                    return true;
                }
            }
        }
        for (int j = 0; j < 5; j++) {
            for (int i = 0; i < 5; i++) {
                if (!teamManager.bingo[i][j]) {
                    break;
                }
                if (i == 4) {
                    return true;
                }
            }
        }
        for (int t = 0; t < 5; t++) {
            if (!teamManager.bingo[t][t]) {
                break;
            }
            if (t == 4) {
                return true;
            }
        }
        for (int t = 0; t < 5; t++) {
            if (!teamManager.bingo[t][4 - t]) {
                break;
            }
            if (t == 4) {
                return true;
            }
        }
        return false;
    }

    public void bingRemove(TeamManager teamManager, int i, int j) {
        teamManager.bingo[i][j] = false;
    }

    public static void stopGame() {
        start = false;
        HandlerList.unregisterAll(getItemListener);
    }

    public static void continueGame() {
        start = true;
        plugin.getServer().getPluginManager().registerEvents(getItemListener, plugin);
    }
}
