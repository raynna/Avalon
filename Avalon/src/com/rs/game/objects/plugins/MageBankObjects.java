package com.rs.game.objects.plugins;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.ObjectPlugin;
import com.rs.game.player.Player;
import com.rs.game.player.controlers.GodCapes;

public class MageBankObjects extends ObjectPlugin {

	@Override
	public Object[] getKeys() {
		return new Object[] { 2878, 2879};
	}
		
	@Override
	public boolean processObject(Player player, WorldObject object) {
		int id = object.getId();
		if (id == 2878) {
			player.sm("You jump into the spring and fall into a dark cavern...");
			player.setNextWorldTile(new WorldTile(2509, 4689, 0));// mb// fountain
		} else if (id == 2879) {
			player.sm("You jump into the spring...");
			player.setNextWorldTile(new WorldTile(2542, 4718, 0));// god// cape// tunnel // fountain
		} else if (id >= 2873 && id <= 2875)
			GodCapes.handleStatue(object, player);
		return true;
	}
}
