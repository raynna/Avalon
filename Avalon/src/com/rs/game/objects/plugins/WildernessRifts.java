package com.rs.game.objects.plugins;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.ObjectPlugin;
import com.rs.game.player.Player;

public class WildernessRifts extends ObjectPlugin {

	@Override
	public Object[] getKeys() {
		return new Object[] { 65203, 28782, 20602, 20604};
	}

	@Override
	public int getDistance() {
		return 0;
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		
		// Entrance from Wilderness Rift
		if (object.getId() == 65203 && object.getX() == 3118)
			player.setNextWorldTile(new WorldTile(3247, 5491, 0));

		if (object.getId() == 65203 && object.getX() == 3129)
			player.setNextWorldTile(new WorldTile(3235, 5560, 0));

		if (object.getId() == 65203 && object.getX() == 3164)
			player.setNextWorldTile(new WorldTile(3291, 5480, 0));

		if (object.getId() == 65203 && object.getX() == 3176)
			player.setNextWorldTile(new WorldTile(3291, 5538, 0));

		if (object.getId() == 65203 && object.getX() == 3058)
			player.setNextWorldTile(new WorldTile(3184, 5469, 0));

		// Entrance to wilderness from chaos tunnel ropes
		if (object.getId() == 28782 && object.getX() == 3248) {// level
																// 7
			player.setNextWorldTile(new WorldTile(3118, 3569, 0));
			player.getControlerManager().startControler("WildernessControler");
		}

		if (object.getId() == 28782 && object.getX() == 3234) {// level
																// 9
			player.setNextWorldTile(new WorldTile(3129, 3586, 0));
			player.getControlerManager().startControler("WildernessControler");
		}

		if (object.getId() == 28782 && object.getX() == 3292) {// to lvl
																// 5-6
																// (bot
																// tunnel
																// from
																// rs)
			player.setNextWorldTile(new WorldTile(3165, 3562, 0));
			player.getControlerManager().startControler("WildernessControler");
		}

		if (object.getId() == 28782 && object.getX() == 3291) {// to lvl
																// 9
			player.setNextWorldTile(new WorldTile(3176, 3584, 0));
			player.getControlerManager().startControler("WildernessControler");
		}

		if (object.getId() == 28782 && object.getX() == 3183) {// to lvl
																// 4
			player.setNextWorldTile(new WorldTile(3057, 3551, 0));
			player.getControlerManager().startControler("WildernessControler");
		}
		if (object.getId() == 20602) {
			player.movePlayer(new WorldTile(2954, 9675, 0), 1, 2);
			return false;
		}
		if (object.getId() == 20604) {
			player.movePlayer(new WorldTile(3018, 3404, 0), 1, 2);
			return false;
		}
		return true;
	}
}
