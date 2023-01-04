package com.rs.game.objects;

import com.rs.game.WorldObject;
import com.rs.game.item.Item;
import com.rs.game.player.Player;

public abstract class ObjectScript {
	
	public abstract Object[] getKeys();

	public boolean processObject(Player player, WorldObject object) {
		return false;
	}
	
	public boolean processObject2(Player player, WorldObject object) {
		return false;
	}
	
	public boolean processObject3(Player player, WorldObject object) {
		return false;
	}
	
	public boolean processObject4(Player player, WorldObject object) {
		return false;
	}
	
	public boolean processObject5(Player player, WorldObject object) {
		return false;
	}
	
	public boolean processItemOnObject(Player player, WorldObject object, Item item) {
		return false;
	}
	

	public int getDistance() {
		return 0;
	}

}
