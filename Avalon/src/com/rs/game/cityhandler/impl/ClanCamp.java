package com.rs.game.cityhandler.impl;

import com.rs.game.WorldObject;
import com.rs.game.cityhandler.CityEvent;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;

public class ClanCamp implements CityEvent {

	@Override
	public boolean init() {
		return registerNPC(13633, this);
	}

	@Override
	public boolean handleNPCClick(Player player, NPC npc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleNPCClick2(Player player, NPC npc) {
		switch(npc.getId()) {
		
		/**
		 * Unknown
		 */
		case 13633:
			if (player.getClanManager() == null) {
				player.getPackets()
				.sendGameMessage(
						"You must be a member of a clan in order to get a Cloak.");
				return false;
			}
			if (player.getInventory().containsOneItem(20708)) {
				player.getDialogueManager().startDialogue(
						"SimpleMessage",
						"You already have a Clan Cloak");
				return false;
			}
			if (!player.getInventory().hasFreeSlots()) {
				player.getDialogueManager().startDialogue(
						"SimpleMessage",
						"You don't have enough inventory space.");
				return false;
			}
			player.getInventory().addItem(20708, 1);
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
		// TODO Auto-generated method stub
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
