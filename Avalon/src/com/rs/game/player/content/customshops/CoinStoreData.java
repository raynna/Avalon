package com.rs.game.player.content.customshops;

import com.rs.game.player.Player;

public class CoinStoreData extends CustomStore {
	
	/**
	 * @Author -Andreas
	 * 2019-11
	 */

	public CoinStoreData(Player player) {
		super(player);
	}

	protected static final int CURRENCY_SPRITE = 1371;

	protected static String TITLE = "Coins Store";
	
	protected static int[][] RUNE_STORE = {{555, 100}, {556, 100}, {557, 100}, {558, 100}};//TODO
	protected static int[][] MELEE_STORE = {{}};
	protected static int[][] MAGIC_STORE = {{}};
	protected static int[][] RANGE_STORE = {{}};

	protected static int[][] ITEMS = { { 6, 1, 1000 }, { 7, 1, 850 }, { 8, 1, 2 }, { 9, 1, 2 }, { 10, 1, 2 },
			{ 11, 1, 2 }, { 12, 1, 2 }, { 13, 1, 2 }, { 14, 1, 2 }, { 15, 1, 2 }, { 16, 1, 2 }, { 17, 1, 2 },
			{ 18, 1, 2 }, { 19, 1, 2 }, { 20, 1, 2 } , { 21, 1, 2 } , { 22, 1, 2 } , { 23, 1, 2 } , { 24, 1, 2 } , { 25, 1, 2 } , { 26, 1, 2 } , { 27, 1, 2 } , { 28, 1, 2 } };

}
