package bingo.bingo.bingoGame;

import bingo.bingo.Bingo;
import bingo.bingo.eventListener.GetItemListener;
import bingo.bingo.teamManager.TeamManager;
import bingo.bingo.teamManager.TeamRegister;
import org.bukkit.event.HandlerList;

import java.util.HashMap;

public class BingoEnd {
    static Bingo plugin = Bingo.plugin;
    private static final GetItemListener getItemListener = GetItemListener.getItemListener;

    public static void BingoSuccess(TeamManager teamManager) {
        HandlerList.unregisterAll(getItemListener);
        teamManager.addScore(1);
        plugin.getServer().broadcastMessage("§a恭喜§l" + teamManager.getColor() + teamManager.getName() + "§r§a获得了本次游戏的胜利！");
        getResult();
    }

    public static void resetWorld() {
        //todo
    }

    public static void getResult() {
        HashMap<Integer, TeamManager> rank = TeamRegister.getRank();
        StringBuilder message = new StringBuilder("当前积分表：\n");
        for (int each_rank = 1; each_rank <= rank.size(); each_rank++) {
            message.append("§r").append(each_rank).append(".\t§l").append(rank.get(each_rank).getColor())
                    .append(rank.get(each_rank).getName()).append("§r").append(rank.get(each_rank).getScore()).append("\n");
        }
        plugin.getServer().broadcastMessage(message.toString());
    }
}
