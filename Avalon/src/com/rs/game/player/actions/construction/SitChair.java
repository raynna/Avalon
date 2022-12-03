package com.rs.game.player.actions.construction;

import com.rs.game.Animation;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.actions.Action;
import com.rs.game.player.actions.skills.construction.HouseConstants;

public class SitChair extends Action {

	private int chair;
	private WorldTile originalTile;
	private WorldTile chairTile;

	public SitChair(Player player, int chair, WorldObject object) {
		this.chair = chair;
		this.originalTile = new WorldTile(player);
		chairTile = object;
		WorldTile face = new WorldTile(player);
		if (object.getType() == 10) {
			if (object.getRotation() == 0)
				face.moveLocation(0, -1, 0);
			else if (object.getRotation() == 1)
				face.moveLocation(-1, 0, 0);
			else if (object.getRotation() == 2)
				face.moveLocation(0, 1, 0);
			else if (object.getRotation() == 3)
				face.moveLocation(1, 0, 0);

		} else if (object.getType() == 11) {
			if (object.getRotation() == 0)
				face.moveLocation(-1, -1, 0);
			else if (object.getRotation() == 1)
				face.moveLocation(-1, 1, 0);
			else if (object.getRotation() == 2)
				face.moveLocation(1, 1, 0);
			else if (object.getRotation() == 3)
				face.moveLocation(1, -1, 0);
		}
		player.setNextFaceWorldTile(face);
	}

	@Override
	public boolean start(Player player) {
		setActionDelay(player, 1);
		player.animate(new Animation(chair > 12 ? HouseConstants.THRONE_EMOTES[chair - 12]
				: chair > 6 ? HouseConstants.BENCH_EMOTES[chair - 6] : HouseConstants.CHAIR_EMOTES[chair]));
		player.setNextWorldTile(chairTile);
		return true;
	}

	@Override
	public boolean process(Player player) {
		return true;
	}

	@Override
	public int processWithDelay(Player player) {
		player.animate(new Animation(chair > 12 ? HouseConstants.THRONE_EMOTES[chair - 12]
				: chair > 6 ? HouseConstants.BENCH_EMOTES[chair - 6] : HouseConstants.CHAIR_EMOTES[chair]));
		player.setNextWorldTile(chairTile);
		return 0;
	}

	@Override
	public void stop(final Player player) {
		player.lock(1);
		player.setNextWorldTile(originalTile);
		player.animate(new Animation(-1));
	}
}
