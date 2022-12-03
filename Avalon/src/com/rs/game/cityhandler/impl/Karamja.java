package com.rs.game.cityhandler.impl;

import com.rs.game.WorldObject;
import com.rs.game.cityhandler.CityEvent;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.utils.ShopsHandler;

/**
 * @Author arrow
 * @Contact<arrowrsps@gmail.com;skype:arrowrsps>
 */
public class Karamja implements CityEvent {
	@Override
	public boolean init() {
		registerObject(2072, this);
		registerNPC(379, this);
		registerNPC(380, this);
		return registerNPC(568, this);
	}

	@Override
	public boolean handleNPCClick(Player player, NPC npc) {
		switch(npc.getId()) {
		/*
		 * Luthas
		 */
		case 379:
			player.getDialogueManager().startDialogue("Luthas", npc.getId());
			break;

			/**
			 * Customs officer
			 */
		case 380:
			player.getDialogueManager().startDialogue(
					"CustomsOfficer", npc.getId());
			break;

			/*
			 * Zambo
			 */
		case 568:
			player.getDialogueManager().startDialogue("Zambo", npc.getId());
			break;
		}
		return false;
	}

	@Override
	public boolean handleNPCClick2(Player player, NPC npc) {
		switch(npc.getId()) {
		/*
		 * Zambo
		 */
		case 568:
			ShopsHandler.openShop(player, 172);
			break;
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
		return false;
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
