package com.rs.game.player.actions;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.Magic;
import com.rs.utils.Utils;

public class ObjectActionTeleport extends Action {

	private final int[] START_ANIMATION = new int[] { 645, 15184 };
	private final int[] START_GRAPHIC = new int[] { 2324, 2325 };
	private final int[] ENDING_ANIMATION = new int[] { 767 };
	private int stage;
	private int currentTime;
	private WorldTile tile;

	public ObjectActionTeleport(WorldTile tile, int stage) {
		this.tile = tile;
		this.stage = stage;
	}

	@Override
	public boolean start(final Player player) {
		if (!player.getControlerManager().processItemTeleport(tile))
			return false;
		return process(player);
	}

	@Override
	public boolean process(Player player) {
		if (player.getAttackedByDelay() + 10000 > Utils.currentTimeMillis()) {
			player.getPackets().sendGameMessage("You cannot be in combat and focus on this at the same time!");
			return false;
		}
		return true;
	}

	@Override
	public int processWithDelay(Player player) {
		if (currentTime++ == 0) {
			player.animate(new Animation(START_ANIMATION[stage]));
			player.getPackets().sendGameMessage("You pray to the chaos altar...");
		} else if (currentTime == 5) {
			player.gfx(new Graphics(START_GRAPHIC[stage]));
		} else if (currentTime == 8) {
			player.gfx(new Graphics(START_GRAPHIC[stage]));
		} else if (currentTime == 10) {
			player.gfx(new Graphics(START_GRAPHIC[stage + 1]));
		} else if (currentTime == 12) {
			player.animate(new Animation(START_ANIMATION[stage + 1]));
		} else if (currentTime == 18) {
			player.setNextWorldTile(tile.transform(0, 1, 0));
			player.getControlerManager().magicTeleported(Magic.MAGIC_TELEPORT);
			if (player.getControlerManager().getControler() == null)
				Magic.teleControlersCheck(player, tile);
			player.setNextFaceWorldTile(new WorldTile(tile.getX(), tile.getY(), tile.getPlane()));
			player.setDirection(6);
		} else if (currentTime == 19) {
			player.animate(new Animation(ENDING_ANIMATION[stage]));
		} else if (currentTime == 25)
			return -1;
		// player.getPackets().sendGameMessage("Stage: %d Time: %d", stage,
		// currentTime); debug
		return 0;
	}

	@Override
	public void stop(Player player) {
		player.animate(new Animation(-1));
		player.gfx(new Graphics(-1));
	}

}
