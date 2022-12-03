package com.rs.game.cityhandler.impl;


import com.rs.game.WorldObject;
import com.rs.game.cityhandler.CityEvent;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.utils.ShopsHandler;

public class Rimmington implements CityEvent {

	@Override
	public boolean init() {
		registerNPC(585, this);
		registerObject(31459, this);
		registerNPC(367, this);
		return registerNPC(1860, this);
	}

	@Override
	public boolean handleNPCClick(Player player, NPC npc) {
		switch(npc.getId()) {
		//Brian
		case 1860:
			player.getDialogueManager().startDialogue("RimmingtonBrian", npc.getId());
			break;
			
		//Rommik
		case 585:
			player.getDialogueManager().startDialogue("Rommik", npc.getId());
			break;
			
		//Chemsit
		case 367:
			player.getDialogueManager().startDialogue("Chemist", npc.getId());
			break;
		}
		return false;
	}

	@Override
	public boolean handleNPCClick2(Player player, NPC npc) {
		switch(npc.getId()) {
		//Brian
		case 1860:
			ShopsHandler.openShop(player, 100);
			break;
			
		//Rommik
		case 585:
			ShopsHandler.openShop(player, 101);
			break;
		}
		return false;
	}

	@Override
	public boolean handleNPCClick3(Player player, NPC npc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleNPCClick4(Player player, NPC npc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleObjectClick(Player player, WorldObject object) {
		switch(object.getId()) {
		//Customs Sergeant
		case 31459:
			player.getDialogueManager().startDialogue("CustomsSergeant", object.getId());
			break;
		}
		return false;
	}

	@Override
	public boolean handleObjectClick2(Player player, WorldObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleObjectClick3(Player player, WorldObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleObjectClick4(Player player, WorldObject object) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleObjectClick5(Player player, WorldObject object) {
		// TODO Auto-generated method stub
		return false;
	}
	
	boolean registerNPC(int npcId, CityEvent cityevent) {
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
