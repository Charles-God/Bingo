package bingo.bingo.bingoMap;

import bingo.bingo.Bingo;
import bingo.bingo.bingoItem.BingoItem;
import bingo.bingo.bingoItem.RandomItem;
import bingo.bingo.teamManager.TeamRegister;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapPalette;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class BingoMapDrawer {
    public static Bingo plugin = Bingo.plugin;
    public static BingoItem[][] map_item = RandomItem.mapItem;
    public static ItemStack bingo_map;
    private static MapMeta mapMeta;
    public static MapView mapView;
    private static ArrayList<Integer> lines;

    static {
        bingo_map = new ItemStack(Material.FILLED_MAP, 1);
        mapMeta = (MapMeta) (bingo_map.getItemMeta());
        mapMeta.setDisplayName("§aBingo");
        mapMeta.setLore(Collections.singletonList("——by Charles_God"));
        mapView = mapMeta.hasMapView() ? mapMeta.getMapView() : plugin.getServer().getMap(0);
        lines = new ArrayList<>();
        for (int each_line : new int[]{3, 4, 27, 28, 51, 52, 75, 76, 99, 100, 123, 124}) {
            lines.add(each_line);
        }
    }

    public static void drawMap(BingoItem[][] map_item) {
        mapView.getRenderers().clear();
        mapView.setTrackingPosition(false);
        mapView.addRenderer(new MapRenderer() {
            @Override
            public void render(MapView map, MapCanvas canvas, Player player) {
                for (int x = 0; x < 128; x++) {
                    for (int y = 0; y < 128; y++) {
                        canvas.setPixel(x, y, MapPalette.matchColor(255, 255, 255));
                        if ((3 <= x && x <= 124 && lines.contains(y)) || (3 <= y && y <= 124 && lines.contains(x))) {
                            canvas.setPixel(x, y, MapPalette.matchColor(0, 0, 0));
                        }
                    }
                }
                String filePath;
                Image image = null;
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 5; j++) {
                        filePath = "./plugins/Bingo/bingo_item/" + map_item[i][j].getFile_name();
                        try {
                            image = ImageIO.read(new File(filePath));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        canvas.drawImage(8 + 24 * i, 8 + 24 * j, image);
                    }
                }
            }
        });
        itemSetMapView(mapView);
    }

    public static void itemSetMapView(MapView mapView) {
        mapMeta.setMapView(mapView);
        bingo_map.setItemMeta(mapMeta);
    }

    public static void fullFill(int i, int j, TeamRegister.TeamColor color) {
        mapView.addRenderer(new MapRenderer() {
            @Override
            public void render(MapView map, MapCanvas canvas, Player player) {
                for (int x = 5 + 24 * i; x <= 26 + 24 * i; x++) {
                    for (int y = 5 + 24 * i; y <= 26 + 24 * i; y++) {
                        if (7 + 24 * i <= x && x <= 24 + 24 * i && 7 + 24 * j <= y && y <= 24 + 24 * j) {
                            continue;
                        }
                        canvas.setPixel(x, y, MapPalette.matchColor(color.R, color.G, color.B));
                    }
                }
            }
        });
        itemSetMapView(mapView);
    }

    public static void t4Fill(int i, int j, TeamRegister.TeamColor color) {
        mapView.addRenderer(new MapRenderer() {
            @Override
            public void render(MapView map, MapCanvas canvas, Player player) {
                for (int x = 5 + 24 * i; x <= 26 + 24 * i; x++) {
                    for (int y = 5 + 24 * i; y <= 26 + 24 * i; y++) {
                        if (7 + 24 * i <= x && x <= 24 + 24 * i && 7 + 24 * j <= y && y <= 24 + 24 * j) {
                            continue;
                        }
                        boolean left = x <= 15 + 24 * i;
                        boolean up = y <= 15 + 24 * j;
                        switch (color) {
                            case dark_blue:
                                if (!left && up) {
                                    canvas.setPixel(x, y, MapPalette.matchColor(color.R, color.G, color.B));
                                }
                                break;
                            case dark_red:
                                if (!left && !up) {
                                    canvas.setPixel(x, y, MapPalette.matchColor(color.R, color.G, color.B));
                                }
                                break;
                            case yellow:
                                if (left && !up) {
                                    canvas.setPixel(x, y, MapPalette.matchColor(color.R, color.G, color.B));
                                }
                                break;
                            case dark_green:
                                if (left && up) {
                                    canvas.setPixel(x, y, MapPalette.matchColor(color.R, color.G, color.B));
                                }
                                break;
                            default:
                                throw new IllegalArgumentException("Team color is illegal!");
                        }
                    }
                }
            }
        });
        itemSetMapView(mapView);
    }

    public static void t8Fill(int i, int j, TeamRegister.TeamColor color) {
        mapView.addRenderer(new MapRenderer() {
            @Override
            public void render(MapView map, MapCanvas canvas, Player player) {
                switch (color) {
                    case dark_blue:
                        for (int y = 7 + 24 * j; y < 16 + 24 * j; y++) {
                            canvas.setPixel(25 + 24 * i, y, MapPalette.matchColor(color.R, color.G, color.B));
                            canvas.setPixel(26 + 24 * i, y, MapPalette.matchColor(color.R, color.G, color.B));
                        }
                        break;
                    case dark_red:
                        for (int y = 16 + 24 * j; y < 25 + 24 * j; y++) {
                            canvas.setPixel(25 + 24 * i, y, MapPalette.matchColor(color.R, color.G, color.B));
                            canvas.setPixel(26 + 24 * i, y, MapPalette.matchColor(color.R, color.G, color.B));
                        }
                        break;
                    case yellow:
                        for (int x = 16 + 24 * j; x < 25 + 24 * j; x++) {
                            canvas.setPixel(x, 25 + 24 * i, MapPalette.matchColor(color.R, color.G, color.B));
                            canvas.setPixel(x, 26 + 24 * i, MapPalette.matchColor(color.R, color.G, color.B));
                        }
                        break;
                    case dark_green:
                        for (int x = 7 + 24 * j; x < 16 + 24 * j; x++) {
                            canvas.setPixel(x, 25 + 24 * i, MapPalette.matchColor(color.R, color.G, color.B));
                            canvas.setPixel(x, 26 + 24 * i, MapPalette.matchColor(color.R, color.G, color.B));
                        }
                        break;
                    case light_purple:
                        for (int y = 16 + 24 * j; y < 25 + 24 * j; y++) {
                            canvas.setPixel(5 + 24 * i, y, MapPalette.matchColor(color.R, color.G, color.B));
                            canvas.setPixel(6 + 24 * i, y, MapPalette.matchColor(color.R, color.G, color.B));
                        }
                        break;
                    case aqua:
                        for (int y = 7 + 24 * j; y < 16 + 24 * j; y++) {
                            canvas.setPixel(5 + 24 * i, y, MapPalette.matchColor(color.R, color.G, color.B));
                            canvas.setPixel(6 + 24 * i, y, MapPalette.matchColor(color.R, color.G, color.B));
                        }
                        break;
                    case gold:
                        for (int x = 7 + 24 * j; x < 16 + 24 * j; x++) {
                            canvas.setPixel(x, 5 + 24 * i, MapPalette.matchColor(color.R, color.G, color.B));
                            canvas.setPixel(x, 6 + 24 * i, MapPalette.matchColor(color.R, color.G, color.B));
                        }
                        break;
                    case dark_purple:
                        for (int x = 16 + 24 * j; x < 25 + 24 * j; x++) {
                            canvas.setPixel(x, 5 + 24 * i, MapPalette.matchColor(color.R, color.G, color.B));
                            canvas.setPixel(x, 6 + 24 * i, MapPalette.matchColor(color.R, color.G, color.B));
                        }
                        break;
                }
            }
        });
        itemSetMapView(mapView);
    }
}
