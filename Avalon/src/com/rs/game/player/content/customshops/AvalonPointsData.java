package com.rs.game.player.content.customshops;

import com.rs.Settings;
import com.rs.game.player.Player;

public class AvalonPointsData extends CustomStore {
	
	/**
	 * @Author -Andreas
	 * 2019-11
	 */

	public AvalonPointsData(Player player) {
		super(player);
	}

	protected static final int CURRENCY_SPRITE = 1371;
	
	protected static String TITLE = Settings.SERVER_NAME + " Points Store";
	
	protected static int[][] ITEMS = {{}};//unused

}
