package com.rs.game.player.actions;

import com.rs.cores.CoresManager;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.content.FadingScreen;
import com.rs.utils.Logger;

public class Charter extends Action {

	private WorldTile tile;
	public static final WorldTile KARAMJA_GANGPLANK = new WorldTile(2956, 3145, 0),
			PORT_SARIM = new WorldTile(3029, 3217, 0);

	public Charter(WorldTile tile) {
		this.setTile(tile);
	}

	public WorldTile getTile() {
		return tile;
	}

	public void setTile(WorldTile tile) {
		this.tile = tile;
	}

	@Override
	public boolean start(Player player) {
		return process(player);
	}

	@Override
	public boolean process(Player player) {
		return true;
	}

	@Override
	public int processWithDelay(Player player) {
		final long time = FadingScreen.fade(player);
		player.getPackets().sendGameMessage("You cross the gangplank and charter back to Port Sarim.");
		CoresManager.slowExecutor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					FadingScreen.unfade(player, time, new Runnable() {
						@Override
						public void run() {
							player.setNextWorldTile(new WorldTile(PORT_SARIM));
						}
					});
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		});
		return -1;
	}

	@Override
	public void stop(Player player) {
	}

}
