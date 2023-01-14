package com.codecool.dungeoncrawl;

import com.codecool.dungeoncrawl.logic.Drawable;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;

public class Tiles {
    public static int TILE_WIDTH = 32;

    private static Image tileset = new Image("/tiles.png", 543 * 2, 543 * 2, true, false);
    private static Map<String, Tile> tileMap = new HashMap<>();
    public static class Tile {
        public final int x, y, w, h;
        Tile(int i, int j) {
            x = i * (TILE_WIDTH + 2);
            y = j * (TILE_WIDTH + 2);
            w = TILE_WIDTH;
            h = TILE_WIDTH;
        }
    }

    static {
        tileMap.put("empty", new Tile(0, 0));
        tileMap.put("stonewall", new Tile(10, 17));
        tileMap.put("floor", new Tile(2, 0));
        tileMap.put("player", new Tile(27, 0));
        tileMap.put("skeleton", new Tile(29, 6));
        tileMap.put("mage", new Tile(24, 2));
        tileMap.put("sword", new Tile(0, 30));
        tileMap.put("lifepotion", new Tile(17, 25));

        tileMap.put("closeddoor", new Tile(4, 9));
        tileMap.put("opendoor", new Tile(6, 9));

        tileMap.put("upstairs", new Tile(2, 6));
        tileMap.put("downstairs", new Tile(3, 6));


        tileMap.put("maplewall", new Tile(13, 16));
        tileMap.put("maplewindow", new Tile(13, 15));
        tileMap.put("torch", new Tile(4, 15));
        tileMap.put("stonefloor", new Tile(2, 12));

    }

    public static void drawTile(GraphicsContext context, Drawable d, int x, int y) {
        Tile tile = tileMap.get(d.getTileName());
        context.drawImage(tileset, tile.x, tile.y, tile.w, tile.h,
                x * TILE_WIDTH, y * TILE_WIDTH, TILE_WIDTH, TILE_WIDTH);
    }
}
