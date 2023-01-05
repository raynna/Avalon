package com.rs.game.objects.plugins;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.ObjectPlugin;
import com.rs.game.player.Player;
import com.rs.game.player.controlers.NomadsRequiem;

public class SoulWarsObjects extends ObjectPlugin {

	@Override
	public Object[] getKeys() {
		return new Object[] { 18425, 42219, 42220 };
	}
	
	@Override
	public boolean processObject(Player player, WorldObject object) {
		if (object.getId() == 18425) {
			NomadsRequiem.enterNomadsRequiem(player);
			return true;
		}
		if (object.getId() == 42219) {
			player.useStairs(-1, new WorldTile(1886, 3178, 0), 0, 1);
			return true;
		}
		if (object.getId() == 42220) {
			player.useStairs(-1, new WorldTile(3082, 3475, 0), 0, 1);
			return true;
		}
		return true;
	}
}
