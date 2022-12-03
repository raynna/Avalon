package com.rs.game.cityhandler.impl;

import com.rs.game.WorldObject;
import com.rs.game.cityhandler.CityEvent;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;

public class Priffidinas implements CityEvent {

	@Override
	public boolean init() {
		return registerNPC(19927);
	}

	@Override
	public boolean handleNPCClick(Player player, NPC npc) {
		switch(npc.getId()) {
		case 19927:
			player.getDialogueManager().startDialogue("LadyTrahaearn", npc.getId());
			break;
		}
		return false;
	}

	@Override
	public boolean handleNPCClick2(Player player, NPC npc) {
		switch(npc.getId()) {
		}
		return false;
	}

	@Override
	public boolean handleNPCClick3(Player player, NPC npc) {
		return false;
	}

	@Override
	public boolean handleNPCClick4(Player player, NPC npc) {
		return false;
	}

	@Override
	public boolean handleObjectClick(Player player, WorldObject object) {
		return true;
	}

	@Override
	public boolean handleObjectClick2(Player player, WorldObject object) {
		return false;
	}

	@Override
	public boolean handleObjectClick3(Player player, WorldObject object) {
		return false;
	}

	@Override
	public boolean handleObjectClick4(Player player, WorldObject object) {
		return false;
	}

	@Override
	public boolean handleObjectClick5(Player player, WorldObject object) {
		return false;
	}
	
	boolean registerNPC(int npcId) {
		return CityEventHandler.registerNPCs(npcId, this);
	}

	boolean registerObject(int objectId, CityEvent cityEvent) {
		return CityEventHandler.registerObjects(objectId, this);
	}

	@Override
	public boolean handleItemOnObject(Player player, WorldObject object, Item item) {
		// TODO Auto-generated method stub
		return false;
	}
	
}
