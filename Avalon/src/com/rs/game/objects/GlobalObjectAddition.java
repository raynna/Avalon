package com.rs.game.objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.WorldTile;

public class GlobalObjectAddition {

    private static ArrayList<WorldTile> tiles = new ArrayList<WorldTile>();

    public static void init() {
        try {
            readToStoreCollection();
        } catch (Exception e) {
        }
    }

    private static void readToStoreCollection() throws Exception {

        /**
         * Edgeville General store north walls candle
         */

        tiles.add(new WorldTile(3082, 3513, 0));

    }

    public static ArrayList<WorldTile> getTiles() {
        return tiles;
    }

    public static void reloadTiles() {
        try {
            tiles.clear();
            readToStoreCollection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean removableTile(WorldTile tile) {
        for (int i = 0; i < tiles.size(); i++) {
            if (tile == tiles.get(i)) {
                return true;
            }
        }
        return false;
    }

}
