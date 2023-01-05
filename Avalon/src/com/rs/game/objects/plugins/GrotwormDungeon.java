package com.rs.game.objects.plugins;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.ObjectPlugin;
import com.rs.game.player.Player;

public class GrotwormDungeon extends ObjectPlugin {

	@Override
	public Object[] getKeys() {
		return new Object[] { 70794, 70796, 70795, 70799, 70796, 70797, 70798, 70793 };
	}
	
	@Override
	public boolean processObject(Player player, WorldObject object) {
		int id = object.getId();
		if (id == 70794)
			player.setNextWorldTile(new WorldTile(1340, 6488, 0));
		else if (id == 70796)
			player.setNextWorldTile(new WorldTile(1090, 6360, 0));
		else if (id == 70795)
			player.setNextWorldTile(new WorldTile(1205, 6506, 0));
		else if (id == 70799)
			player.setNextWorldTile(new WorldTile(1178, 6355, 0));
		else if (id == 70796)
			player.setNextWorldTile(new WorldTile(1090, 6360, 0));
		else if (id == 70797)
			player.setNextWorldTile(new WorldTile(1090, 6497, 0));
		else if (id == 70798)
			player.setNextWorldTile(new WorldTile(1340, 6488, 0));
		else if (id == 70792)
			player.setNextWorldTile(new WorldTile(1206, 6371, 0));
		else if (id == 70793)
			player.setNextWorldTile(new WorldTile(2992, 3236, 0));
		return true;
	}
}
