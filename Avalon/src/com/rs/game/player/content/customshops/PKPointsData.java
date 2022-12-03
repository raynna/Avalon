package com.rs.game.player.content.customshops;

import com.rs.game.player.Player;

public class PKPointsData extends CustomStore {
	
	/**
	 * @Author -Andreas
	 * 2019-11
	 */

	public PKPointsData(Player player) {
		super(player);
	}

	protected static final int CURRENCY_SPRITE = 1583;

	protected static String TITLE = "PK Points Store";

	protected static int[][] ITEMS = { {} };//unused

}
