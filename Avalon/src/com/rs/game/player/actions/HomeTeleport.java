package com.rs.game.player.actions;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.Magic;
import com.rs.utils.Utils;

public class HomeTeleport extends Action {

	private final int FIRST_ANIMATION = 16385, SECOND_ANIMATION = 16386, LAST_ANIMATION = 16393, FIRST_GRAPHIC = 3017, SECOND_GRAPHIC = 3019, LAST_GRAPHIC = 3018;
	private int currentTime;
	private WorldTile tile;

	public static final WorldTile LUMBRIDGE_LODE_STONE = new WorldTile(3233, 3221, 0),
			BURTHORPE_LODE_STONE = new WorldTile(2899, 3544, 0), LUNAR_ISLE_LODE_STONE = new WorldTile(2085, 3914, 0),
			BANDIT_CAMP_LODE_STONE = new WorldTile(3214, 2954, 0), TAVERLY_LODE_STONE = new WorldTile(2878, 3442, 0),
			ALKHARID_LODE_STONE = new WorldTile(3297, 3184, 0), VARROCK_LODE_STONE = new WorldTile(3214, 3376, 0),
			EDGEVILLE_LODE_STONE = new WorldTile(3067, 3505, 0), FALADOR_LODE_STONE = new WorldTile(2967, 3403, 0),
			PORT_SARIM_LODE_STONE = new WorldTile(3011, 3215, 0),
			DRAYNOR_VILLAGE_LODE_STONE = new WorldTile(3105, 3298, 0),
			ARDOUGNE_LODE_STONE = new WorldTile(2634, 3348, 0), CATHERBY_LODE_STONE = new WorldTile(2831, 3451, 0),
			YANILLE_LODE_STONE = new WorldTile(2529, 3094, 0), SEERS_VILLAGE_LODE_STONE = new WorldTile(2689, 3482, 0);

	public HomeTeleport(WorldTile tile) {
		this.tile = tile;
	}

	@Override
	public boolean start(final Player player) {
		if (!player.getControlerManager().processMagicTeleport(tile))
			return false;
		return process(player);
	}

	@Override
	public int processWithDelay(Player player) {
		player.getAppearence().resetAppearence(); //keeping this in case
		if (currentTime++ == 0) {
			player.animate(new Animation(FIRST_ANIMATION));
			player.gfx(new Graphics(FIRST_GRAPHIC));
		} else if (currentTime == 17) {
			player.gfx(new Graphics(SECOND_GRAPHIC));
		} else if (currentTime == 18) {
			player.setNextWorldTile(tile.transform(0, 1, 0));
			player.getControlerManager().magicTeleported(Magic.MAGIC_TELEPORT);
			if (player.getControlerManager().getControler() == null)
				Magic.teleControlersCheck(player, tile);
			player.setNextFaceWorldTile(new WorldTile(tile.getX(), tile.getY() - 1, tile.getPlane()));
			player.setDirection(6);
		} else if (currentTime == 19) {
			player.animate(new Animation(-1));
			player.gfx(new Graphics(LAST_GRAPHIC));
			player.animate(new Animation(SECOND_ANIMATION));
		} else if (currentTime == 24) {
			player.animate(new Animation(LAST_ANIMATION));
		} else if (currentTime == 25) {
			player.setNextWorldTile(new WorldTile(tile.getX(), tile.getY(), tile.getPlane()));
		} else if (currentTime == 26) {
			return -1;
		}
		return 0;
	}

	@Override
	public boolean process(Player player) {
		if (player.getAttackedByDelay() + 10000 > Utils.currentTimeMillis()) {
			player.getPackets().sendGameMessage("You can't home teleport until 10 seconds after the end of combat.");
			return false;
		}
		return true;
	}

	@Override
	public void stop(Player player) {
		player.animate(new Animation(-1));
		player.gfx(new Graphics(-1));
	}
}