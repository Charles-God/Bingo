package bingo.bingo.eventListener;

import bingo.bingo.Bingo;
import bingo.bingo.bingoGame.BingoGame;
import bingo.bingo.bingoItem.RandomItem;
import bingo.bingo.teamManager.TeamManager;
import bingo.bingo.teamManager.TeamRegister;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseArmorEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.inventory.*;
import org.bukkit.event.player.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.scoreboard.Scoreboard;

import java.util.Objects;

public class GetItemListener implements Listener {
    final static Bingo plugin = Bingo.plugin;
    final static Scoreboard scoreboard = TeamManager.scoreboard;
    public static GetItemListener getItemListener = new GetItemListener();

    @EventHandler
    public void blockDispenseArmorEvent(BlockDispenseArmorEvent event) {
        LivingEntity entity = event.getTargetEntity();
        if (!(entity instanceof Player)) {
            return;
        }
        Player player = ((Player) entity).getPlayer();
        PlayerInventory inventory = player.getInventory();
        ItemStack helmet = inventory.getHelmet();
        ItemStack chestplate = inventory.getChestplate();
        ItemStack leggings = inventory.getLeggings();
        ItemStack boots = inventory.getBoots();
        for (int index = 0; index < 25; index++) {
            if (RandomItem.targets[index].equals(helmet.getType()) ||
                    RandomItem.targets[index].equals(chestplate.getType()) ||
                    RandomItem.targets[index].equals(leggings.getType()) ||
                    RandomItem.targets[index].equals(boots.getType())) {
                getBingoItem(player, index);
            }
        }
    }

    @EventHandler
    public void entityPickupItemEvent(EntityPickupItemEvent event) {
        Entity entity = event.getEntity();
        if (!(entity instanceof Player)) {
            return;
        }
        Player player = ((Player) entity).getPlayer();
        for (int index = 0; index < 25; index++) {
            if (player.getInventory().contains(RandomItem.targets[index])) {
                getBingoItem(player, index);
            }
        }
    }

    @EventHandler
    public void furnaceExtractEvent(FurnaceExtractEvent event) {
        Player player = event.getPlayer();
        for (int index = 0; index < 25; index++) {
            if (event.getItemType().equals(RandomItem.targets[index])) {
                getBingoItem(player, index);
            }
        }
    }

    @EventHandler
    public void inventoryInteractEvent(InventoryInteractEvent event) {
        HumanEntity humanEntity = event.getWhoClicked();
        if (!(humanEntity instanceof Player)) {
            return;
        }
        Player player = ((Player) humanEntity).getPlayer();
        for (int index = 0; index < 25; index++) {
            if (player.getInventory().contains(RandomItem.targets[index])) {
                getBingoItem(player, index);
            }
        }
    }

    @EventHandler
    public void playerArmorStandManipulateEvent(PlayerArmorStandManipulateEvent event) {
        Player player = event.getPlayer();
        ItemStack armorStandItem = event.getArmorStandItem();
        ItemStack playerItem = event.getPlayerItem();
        for (int index = 0; index < 25; index++) {
            if (RandomItem.targets[index].equals(armorStandItem.getType()) ||
                    RandomItem.targets[index].equals(playerItem.getType())) {
                getBingoItem(player, index);
            }
        }
    }

    @EventHandler
    public void playerBucketEntityEvent(PlayerBucketEntityEvent event) {
        Player player = event.getPlayer();
        ItemStack bukkit = event.getEntityBucket();
        for (int index = 0; index < 25; index++) {
            if (RandomItem.targets[index].equals(bukkit.getType())) {
                getBingoItem(player, index);
            }
        }
    }

    @EventHandler
    public void playerBucketEmptyEvent(PlayerBucketEmptyEvent event) {
        Player player = event.getPlayer();
        ItemStack bukkit = event.getItemStack();
        for (int index = 0; index < 25; index++) {
            if (RandomItem.targets[index].equals(bukkit.getType())) {
                getBingoItem(player, index);
            }
        }
    }

    @EventHandler
    public void playerBucketFillEvent(PlayerBucketFillEvent event) {
        Player player = event.getPlayer();
        ItemStack bukkit = event.getItemStack();
        for (int index = 0; index < 25; index++) {
            if (RandomItem.targets[index].equals(bukkit.getType())) {
                getBingoItem(player, index);
            }
        }
    }

    @EventHandler
    public void playerEditBookEvent(PlayerEditBookEvent event) {
        Player player = event.getPlayer();
        for (int index = 0; index < 25; index++) {
            if (player.getInventory().contains(RandomItem.targets[index])) {
                getBingoItem(player, index);
            }
        }
    }

    public void getBingoItem(Player player, int index) {
        int i = index % 5;
        int j = index / 5;
        BingoGame.bingoGet(player, i, j);
    }
}
