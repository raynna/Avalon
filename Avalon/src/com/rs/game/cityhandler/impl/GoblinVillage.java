package com.rs.game.cityhandler.impl;

import com.rs.game.WorldObject;
import com.rs.game.cityhandler.CityEvent;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;

public class GoblinVillage implements CityEvent {

	@Override
	public boolean init() {
		registerObject(16560, this);
		registerNPC(4493, this);
		registerNPC(4494, this);
		return registerNPC(4495, this);
	}

	@Override
	public boolean handleNPCClick(Player player, NPC npc) {
		switch(npc.getId()) {
		//Grubfoot
		case 4495:
			player.getDialogueManager().startDialogue("Grubfoot", 0);
			break;
			//General Wartface
		case 4494:
			player.getDialogueManager().startDialogue("GeneralWartface", 0);
			break;
			//General Bentnoze
		case 4493:
			player.getDialogueManager().startDialogue("GeneralBentnoze", 0);
			break;
		}
		return false;
	}

	@Override
	public boolean handleNPCClick2(Player player, NPC npc) {
		// TODO Auto-generated method stub
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
		case 16560:
			if(!player.getInventory().containsItem(288, 1)) {
				if(player.getInventory().getFreeSlots() > 1) {
					player.getDialogueManager().startDialogue("ItemDialogue", 288, "You take the goblin mail from the crate.");
					player.getInventory().addItem(288, 1);
				} else {
					player.sm("You do not have enough inventory space.");
				}
			} else {
				player.sm("You already have one of these.");
			}
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
