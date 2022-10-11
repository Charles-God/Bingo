package bingo.bingo;

import bingo.bingo.bingoGame.BingoEnd;
import bingo.bingo.bingoItem.BingoItem;
import bingo.bingo.bingoMap.BingoMapDrawer;
import bingo.bingo.commandExecutor.BingoExecutor;
import bingo.bingo.eventListener.GetItemListener;
import bingo.bingo.teamManager.TeamManager;
import bingo.bingo.teamManager.TeamRegister;
import bingo.bingo.util.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public final class Bingo extends JavaPlugin {
    public static Bingo plugin;
    public static FileConfiguration config;
    public static Scoreboard scoreboard;

    @Override
    public void onLoad() {
        plugin = this;
        saveDefaultConfig();
        config = plugin.getConfig();
    }

    @Override
    public void onEnable() {
        scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();
        if (Objects.isNull(scoreboard)) {
            Bukkit.getScoreboardManager().getNewScoreboard();
        }
        registerTeams();
        saveDefaultImages();
        Config.loadFromConfig();
        ((PluginCommand) Objects.requireNonNull(this.getCommand("bingo"))).setExecutor(new BingoExecutor());
    }

    @Override
    public void onDisable() {
        try {
            Config.saveToConfig();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        BingoEnd.resetWorld();
    }

    private void saveDefaultImages() {
        BingoItem[] bingo_items = BingoItem.values();
        for (BingoItem bingo_item : bingo_items) {
            File image = new File(getDataFolder(), "bingo_item/" + bingo_item.getFile_name());
            saveResource("bingo_item/" + bingo_item.getFile_name(), true);
        }
        getLogger().info("图片资源加载完毕！");
    }

    private void registerTeams(){
        TeamRegister.register(TeamRegister.TeamColor.dark_blue.toChatColor());
        TeamRegister.register(TeamRegister.TeamColor.dark_red.toChatColor());
        TeamRegister.register(TeamRegister.TeamColor.yellow.toChatColor());
        TeamRegister.register(TeamRegister.TeamColor.dark_green.toChatColor());
        TeamRegister.register(TeamRegister.TeamColor.light_purple.toChatColor());
        TeamRegister.register(TeamRegister.TeamColor.aqua.toChatColor());
        TeamRegister.register(TeamRegister.TeamColor.gold.toChatColor());
        TeamRegister.register(TeamRegister.TeamColor.dark_purple.toChatColor());
        getLogger().info("队伍加载完毕！");
    }
}
