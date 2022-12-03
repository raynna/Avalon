package com.rs.game.objects;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.WorldTile;

public class GlobalObjectDeletion {

	private static ArrayList<WorldTile> tiles = new ArrayList<WorldTile>();

	public static void init() {
		try {
			readToStoreCollection();
		} catch (Exception e) {
		}
	}

	private static void readToStoreCollection() throws Exception {
		/**
		 * Man house walls & candles
		 */
		tiles.add(new WorldTile(3094, 3506, 0));
		tiles.add(new WorldTile(3093, 3506, 0));
		tiles.add(new WorldTile(3094, 3514, 0));
		tiles.add(new WorldTile(3093, 3514, 0));
		tiles.add(new WorldTile(3094, 3513, 0));
		tiles.add(new WorldTile(3093, 3507, 0));

		/**
		 * Man house objects
		 */

		tiles.add(new WorldTile(3099, 3512,0));
		tiles.add(new WorldTile(3096, 3511,0));
		tiles.add(new WorldTile(3095, 3510,0));
		tiles.add(new WorldTile(3094, 3510,0));
		tiles.add(new WorldTile(3094, 3507,0));
		tiles.add(new WorldTile(3093, 3511,0));
		tiles.add(new WorldTile(3092, 3511,0));
		tiles.add(new WorldTile(3093, 3511,0));
		tiles.add(new WorldTile(3091, 3511,0));
		tiles.add(new WorldTile(3093, 3513,0));
		tiles.add(new WorldTile(0,0,0));
		tiles.add(new WorldTile(0,0,0));

		/**
		 * Barrels near man house
		 */

		tiles.add(new WorldTile(3090, 3505, 0));
		tiles.add(new WorldTile(3089, 3505, 0));

		/**
		 * General store edgeville
		 */

		tiles.add(new WorldTile(3082, 3507, 0));
		tiles.add(new WorldTile(3081, 3507, 0));
		tiles.add(new WorldTile(3081, 3510, 0));
		tiles.add(new WorldTile(3080, 3510, 0));
		tiles.add(new WorldTile(3078, 3510, 0));
		tiles.add(new WorldTile(3077, 3512, 0));
		tiles.add(new WorldTile(3082, 3513, 0));
		tiles.add(new WorldTile(3083, 3513, 0));
		tiles.add(new WorldTile(3084, 3513, 0));
		tiles.add(new WorldTile(3084, 3512, 0));
		tiles.add(new WorldTile(3084, 3511, 0));
		tiles.add(new WorldTile(3084, 3510, 0));
		tiles.add(new WorldTile(3084, 3509, 0));
		tiles.add(new WorldTile(3079, 3513, 0));
		tiles.add(new WorldTile(3077, 3513, 0));
		tiles.add(new WorldTile(3076, 3513, 0));
		tiles.add(new WorldTile(3076, 3512, 0));
		tiles.add(new WorldTile(3076, 3510, 0));
		tiles.add(new WorldTile(3076, 3509, 0));
		tiles.add(new WorldTile(3078, 3507, 0));







		/**
		 * Edgeville bank objects
		 */
		tiles.add(new WorldTile(3092, 3488, 0));
		for (int y = 3488; y <= 3495; y++)
			tiles.add(new WorldTile(3095, y, 0));
		for (int x = 3095; x <= 3098; x++)
			tiles.add(new WorldTile(x, 3495, 0));
		tiles.add(new WorldTile(3098, 3488, 0));
		tiles.add(new WorldTile(3098, 3492, 0));
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
