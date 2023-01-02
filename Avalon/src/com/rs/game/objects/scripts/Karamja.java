package com.rs.game.objects.scripts;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;

public class Karamja extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 2083, 2081, 492, 1764, 2606, 25213, 25154};
	}
	
	@Override
	public boolean processObject(Player player, WorldObject object) {
		int id = object.getId();
		if (id == 2083 && object.getX() == 3030 && object.getY() == 3217) {
			player.setNextWorldTile(new WorldTile(2956, 3146, 0)); // Boat from Port Sarim to Karamja
		} else if (id == 2081 && object.getX() == 2956 && object.getY() == 3145) {
			player.setNextWorldTile(new WorldTile(3029, 3217, 0)); // Boat from Karamja to Port Sarim
		} else if (id == 492 && object.getX() == 2856 && object.getY() == 3168) {
			player.useStairs(-1, new WorldTile(2856, 9570, 0), 0, 1); // Vulcano rocks down to cave
		} else if (id == 1764 && object.getX() == 2856 && object.getY() == 9569) {
			player.useStairs(-1, new WorldTile(2858, 3168, 0), 0, 1); // Cave rope up to Vulcano
		} else if (id == 2606 && object.getX() == 2836 && object.getY() == 9600 && player.getY() <= 9599) {
			player.setNextWorldTile(new WorldTile(2836, 9600, 0)); // Wall from cave to Lessers
		} else if (id == 2606 && object.getX() == 2836 && object.getY() == 9600 && player.getY() >= 9600) {
			player.setNextWorldTile(new WorldTile(2836, 9599, 0)); // Wall from Lessers to Cave
		} else if (id == 25213 && object.getX() == 2833 && object.getY() == 9657) {
			player.useStairs(-1, new WorldTile(2834, 3258, 0), 0, 1); // Lessers rope up to Crandor
		} else if (id == 25154 && object.getX() == 2833 && object.getY() == 3255) {
			player.useStairs(-1, new WorldTile(2834, 9657, 0), 0, 1); // Crandor hole down to Lessers
		}
		return true;
	}
}
