package bingo.bingo.util;

import bingo.bingo.Bingo;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.IOException;

public class Config {
    private static final FileConfiguration config = Bingo.config;
    public static boolean enablePvp;
    public static boolean killLoot;
    public static boolean respawn;
    public static int spawnRadius;
    public static int borderRadius;
    public static boolean refill;

    public static void loadFromConfig() {
        enablePvp = config.getBoolean("enablePvp");
        killLoot = config.getBoolean("killLoot");
        respawn = config.getBoolean("respawn");
        spawnRadius = config.getInt("spawnRadius");
        borderRadius = config.getInt("borderRadius");
        refill = config.getBoolean("refill");
    }

    public static void saveToConfig() throws IOException {
        config.set("enablePvp", enablePvp);
        config.set("killLoot", killLoot);
        config.set("respawn", respawn);
        config.set("spawnRadius", spawnRadius);
        config.set("borderRadius", spawnRadius);
        config.set("refill", refill);
        config.save("config.yml");
    }
}
