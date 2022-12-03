package com.rs.game.player.actions;

import com.rs.game.WorldTile;
import com.rs.game.player.Player;

public class WildyViewing extends Action {

	public static final WorldTile[] ORB_TELEPORTS = { new WorldTile(2332, 3680, 0) };
	public static final WorldTile[] HOME = { new WorldTile(3089, 3498, 0) };

	private WorldTile tile;

	@Override
	public boolean start(Player player) {
		if (!process(player))
			return false;
		tile = new WorldTile(player);
		player.getAppearence().isHidden();
		player.getPackets().sendBlackOut(5);
		player.setNextWorldTile(ORB_TELEPORTS[0]);
		player.getAppearence().transformIntoNPC(15155);
		player.getAppearence().generateAppearenceData();
		return true;
	}

	@Override
	public boolean process(Player player) {
		if (player.getPoison().isPoisoned()) {
			player.getPackets().sendGameMessage("You can't view fights while you're poisoned.");
			return false;
		}
		if (player.getFamiliar() != null) {
			player.getPackets().sendGameMessage("You can't view fights with a familiar.");
			return false;
		}
		return true;
	}

	@Override
	public int processWithDelay(Player player) {
		return 0;
	}

	@Override
	public void stop(final Player player) {
		player.lock(2);
		player.getInterfaceManager().closeInventoryInterface();
		player.getAppearence().resetAppearence();
		player.getAppearence().generateAppearenceData();
		player.getPackets().sendBlackOut(0);
		player.setNextWorldTile(HOME[0]);
		player.getAppearence().transformIntoNPC(-1);
		player.setNextWorldTile(tile);
	}

}
