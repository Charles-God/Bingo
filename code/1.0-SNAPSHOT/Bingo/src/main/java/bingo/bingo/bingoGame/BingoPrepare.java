package bingo.bingo.bingoGame;

import bingo.bingo.Bingo;
import bingo.bingo.bingoItem.RandomItem;
import bingo.bingo.bingoMap.BingoMapDrawer;
import bingo.bingo.eventListener.GetItemListener;
import bingo.bingo.teamManager.TeamManager;
import bingo.bingo.teamManager.TeamRegister;
import bingo.bingo.util.Shuffle;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class BingoPrepare {
    static RandomItem randomItem;
    static Bingo plugin = Bingo.plugin;
    static Scoreboard scoreboard = TeamManager.scoreboard;
    private static final GetItemListener getItemListener = GetItemListener.getItemListener;

    public static void startEasyBingo(Player host) {
        randomItem = new RandomItem();
        randomItem.randomEasyBingo();
        BingoMapDrawer.drawMap(RandomItem.mapItem);
        givePlayersMap(getAllPlayers());
        plugin.getServer().getPluginManager().registerEvents(getItemListener, plugin);
        BingoGame.start = true;
    }

    public static void startItemBingo(Player host) {
        randomItem = new RandomItem();
        randomItem.randomItemBingo();
        BingoMapDrawer.drawMap(RandomItem.mapItem);
        givePlayersMap(getAllPlayers());
        plugin.getServer().getPluginManager().registerEvents(getItemListener, plugin);
        BingoGame.start = true;
    }

    public static void startNormalBingo(Player host) {
        randomItem = new RandomItem();
        randomItem.randomNormalBingo();
        BingoMapDrawer.drawMap(RandomItem.mapItem);
        givePlayersMap(getAllPlayers());
        plugin.getServer().getPluginManager().registerEvents(getItemListener, plugin);
        BingoGame.start = true;
    }

    public static void startNetherBingo(Player host) {
        randomItem = new RandomItem();
        randomItem.randomNetherBingo();
        BingoMapDrawer.drawMap(RandomItem.mapItem);
        givePlayersMap(getAllPlayers());
        plugin.getServer().getPluginManager().registerEvents(getItemListener, plugin);
        BingoGame.start = true;
    }

    public static void startEndBingo(Player host) {
        randomItem = new RandomItem();
        randomItem.randomEndBingo();
        BingoMapDrawer.drawMap(RandomItem.mapItem);
        givePlayersMap(getAllPlayers());
        plugin.getServer().getPluginManager().registerEvents(getItemListener, plugin);
        BingoGame.start = true;
    }

    public static void startImpossibleBingo(Player host) {
        randomItem = new RandomItem();
        randomItem.randomImpossibleBingo();
        BingoMapDrawer.drawMap(RandomItem.mapItem);
        givePlayersMap(getAllPlayers());
        plugin.getServer().getPluginManager().registerEvents(getItemListener, plugin);
        BingoGame.start = true;
    }

    public static void startPossibleBingo(Player host) {
        randomItem = new RandomItem();
        randomItem.randomPossibleBingo();
        BingoMapDrawer.drawMap(RandomItem.mapItem);
        givePlayersMap(getAllPlayers());
        plugin.getServer().getPluginManager().registerEvents(getItemListener, plugin);
        BingoGame.start = true;
    }

    public static ArrayList<Player> getAllPlayers() {
        ArrayList<Player> bingoPlayers = new ArrayList<>();
        for (World each_world : plugin.getServer().getWorlds()) {
            bingoPlayers.addAll(each_world.getPlayers());
        }
        return bingoPlayers;
    }

    public static ArrayList<Player> getAllPlayersExcept(Player player) {
        ArrayList<Player> bingoPlayers = getAllPlayers();
        bingoPlayers.remove(player);
        return bingoPlayers;
    }

    public static void givePlayerMap(Player player) {
        player.getInventory().setItem(player.getInventory().firstEmpty(), BingoMapDrawer.bingo_map);
    }

    public static void givePlayersMap(List<Player> players) {
        for (Player player : players) {
            givePlayerMap(player);
        }
    }

    public static void randomTeam(List<Player> players, int teamAmount) {
        TeamRegister.TeamColor[] teamColors = TeamRegister.TeamColor.values();
        for (int teamnum = 0; teamnum < teamAmount; teamnum++) {
            TeamRegister.register(teamColors[teamnum].toChatColor(), teamColors.toString());
        }
        List<TeamManager> teamManagerList = new ArrayList<>(TeamRegister.register.values());
        Shuffle.shuffleList(Collections.singletonList(teamManagerList));
        for (int playernum = 0; playernum < players.size(); playernum++) {
            teamManagerList.get(playernum % teamAmount).addPlayer(players.get(playernum));
        }
    }
}
