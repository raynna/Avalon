package com.rs.game.objects.plugins;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.ObjectPlugin;
import com.rs.game.player.Player;

public class LumbridgeDungeon extends ObjectPlugin {

	@Override
	public Object[] getKeys() {
		return new Object[] { 48798, 48677, 48679, 48688, 48683, 48682};
	}

	@Override
	public int getDistance() {
		return 0;
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		int id = object.getId();
		int x = object.getX();
		int y = object.getY();
		if (id == 48798)
			player.useStairs(-1, new WorldTile(3246, 3198, 0), 0, 1);
		else if (id == 48678 && x == 3858 && y == 5533)
			player.useStairs(-1, new WorldTile(3861, 5533, 0), 0, 1);
		else if (id == 48678 && x == 3858 && y == 5543)
			player.useStairs(-1, new WorldTile(3861, 5543, 0), 0, 1);
		else if (id == 48678 && x == 3858 && y == 5533)
			player.useStairs(-1, new WorldTile(3861, 5533, 0), 0, 1);
		else if (id == 48677 && x == 3858 && y == 5543)
			player.useStairs(-1, new WorldTile(3856, 5543, 1), 0, 1);
		else if (id == 48677 && x == 3858 && y == 5533)
			player.useStairs(-1, new WorldTile(3856, 5533, 1), 0, 1);
		else if (id == 48679)
			player.useStairs(-1, new WorldTile(3875, 5527, 1), 0, 1);
		else if (id == 48688)
			player.useStairs(-1, new WorldTile(3972, 5565, 0), 0, 1);
		else if (id == 48683)
			player.useStairs(-1, new WorldTile(3868, 5524, 0), 0, 1);
		else if (id == 48682)
			player.message("I can hardly read anything on this..");
		return true;
	}
}
