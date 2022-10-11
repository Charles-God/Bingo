package bingo.bingo.bingoItem;

import bingo.bingo.bingoGame.BingoGame;
import bingo.bingo.teamManager.TeamManager;
import org.bukkit.Material;

import java.util.Random;

public class RandomItem {
    final static Random random = new Random();
    final static BingoItem[] items = BingoItem.getArray();
    public static BingoItem[][] mapItem;
    public static Material[] targets = new Material[25];
    Boolean filled = false;

    public static BingoItem getRandomItem() {
        return items[random.nextInt(items.length)];
    }

    public static BingoItem getItem(int index) {
        return items[index];
    }

    public void randomEasyBingo() {
        mapItem = new BingoItem[5][5];
        targets = new Material[25];
        BingoGame.allGained = new boolean[5][5];
        BingoItem item;
        int i = 0;
        int j = 0;
        out:
        while (!filled) {
            item = getRandomItem();
            if (!(item.getDifficulty().equals(BingoItem.Difficulty.easy) ||
                    item.getDifficulty().equals(BingoItem.Difficulty.item))) {
                continue;
            }
            for (int index = 0; index < i + 5 * j; index++) {
                if (item.getMaterial().equals(targets[index])) {
                    continue out;
                }
            }
            mapItem[i][j] = item;
            targets[i + 5 * j] = item.getMaterial();
            i++;
            if (i == 5) {
                j++;
                i = 0;
            }
            if (!(mapItem[4][4] == null)) {
                filled = true;
            }
        }
    }

    public void randomItemBingo() {
        mapItem = new BingoItem[5][5];
        targets = new Material[25];
        BingoGame.allGained = new boolean[5][5];
        BingoItem item;
        int i = 0;
        int j = 0;
        out:
        while (!filled) {
            item = getRandomItem();
            if (!(item.getDifficulty().equals(BingoItem.Difficulty.item))) {
                continue;
            }
            for (int index = 0; index < i + 5 * j; index++) {
                if (item.getMaterial().equals(targets[index])) {
                    continue out;
                }
            }
            mapItem[i][j] = item;
            targets[i + 5 * j] = item.getMaterial();
            i++;
            if (i == 5) {
                j++;
                i = 0;
            }
            if (!(mapItem[4][4] == null)) {
                filled = true;
            }
        }
    }

    public void randomNormalBingo() {
        mapItem = new BingoItem[5][5];
        targets = new Material[25];
        BingoGame.allGained = new boolean[5][5];
        BingoItem item;
        int i = 0;
        int j = 0;
        out:
        while (!filled) {
            item = getRandomItem();
            if (!(item.getDifficulty().equals(BingoItem.Difficulty.item) ||
                    item.getDifficulty().equals(BingoItem.Difficulty.normal))) {
                continue;
            }
            for (int index = 0; index < i + 5 * j; index++) {
                if (item.getMaterial().equals(targets[index])) {
                    continue out;
                }
            }
            mapItem[i][j] = item;
            targets[i + 5 * j] = item.getMaterial();
            i++;
            if (i == 5) {
                j++;
                i = 0;
            }
            if (!(mapItem[4][4] == null)) {
                filled = true;
            }
        }
    }

    public void randomNetherBingo() {
        mapItem = new BingoItem[5][5];
        targets = new Material[25];
        BingoGame.allGained = new boolean[5][5];
        BingoItem item;
        int i = 0;
        int j = 0;
        out:
        while (!filled) {
            item = getRandomItem();
            if (!(item.getDifficulty().equals(BingoItem.Difficulty.normal) ||
                    item.getDifficulty().equals(BingoItem.Difficulty.nether))) {
                continue;
            }
            for (int index = 0; index < i + 5 * j; index++) {
                if (item.getMaterial().equals(targets[index])) {
                    continue out;
                }
            }
            mapItem[i][j] = item;
            targets[i + 5 * j] = item.getMaterial();
            i++;
            if (i == 5) {
                j++;
                i = 0;
            }
            if (!(mapItem[4][4] == null)) {
                filled = true;
            }
        }
    }

    public void randomEndBingo() {
        mapItem = new BingoItem[5][5];
        targets = new Material[25];
        BingoGame.allGained = new boolean[5][5];
        BingoItem item;
        int i = 0;
        int j = 0;
        out:
        while (!filled) {
            item = getRandomItem();
            if (!(item.getDifficulty().equals(BingoItem.Difficulty.nether) ||
                    item.getDifficulty().equals(BingoItem.Difficulty.end))) {
                continue;
            }
            for (int index = 0; index < i + 5 * j; index++) {
                if (item.getMaterial().equals(targets[index])) {
                    continue out;
                }
            }
            mapItem[i][j] = item;
            targets[i + 5 * j] = item.getMaterial();
            i++;
            if (i == 5) {
                j++;
                i = 0;
            }
            if (!(mapItem[4][4] == null)) {
                filled = true;
            }
        }
    }

    public void randomImpossibleBingo() {
        mapItem = new BingoItem[5][5];
        targets = new Material[25];
        BingoGame.allGained = new boolean[5][5];
        BingoItem item;
        int i = 0;
        int j = 0;
        out:
        while (!filled) {
            item = getRandomItem();
            if (!(item.getDifficulty().equals(BingoItem.Difficulty.impossible))) {
                continue;
            }
            for (int index = 0; index < i + 5 * j; index++) {
                if (item.getMaterial().equals(targets[index])) {
                    continue out;
                }
            }
            mapItem[i][j] = item;
            targets[i + 5 * j] = item.getMaterial();
            i++;
            if (i == 5) {
                j++;
                i = 0;
            }
            if (!(mapItem[4][4] == null)) {
                filled = true;
            }
        }
    }

    public void randomPossibleBingo() {
        mapItem = new BingoItem[5][5];
        targets = new Material[25];
        BingoGame.allGained = new boolean[5][5];
        BingoItem item;
        int i = 0;
        int j = 0;
        out:
        while (!filled) {
            item = getRandomItem();
            if (item.getDifficulty().equals(BingoItem.Difficulty.impossible)) {
                continue;
            }
            for (int index = 0; index < i + 5 * j; index++) {
                if (item.getMaterial().equals(targets[index])) {
                    continue out;
                }
            }
            mapItem[i][j] = item;
            targets[i + 5 * j] = item.getMaterial();
            i++;
            if (i == 5) {
                j++;
                i = 0;
            }
            if (!(mapItem[4][4] == null)) {
                filled = true;
            }
        }
    }
}

