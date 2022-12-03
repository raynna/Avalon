package com.rs.game.cityhandler.impl;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.cityhandler.CityEvent;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.utils.ShopsHandler;

public class Taverly implements CityEvent {

	@Override
	public boolean init() {
		registerNPC(6988, this);
		registerNPC(14866, this);
		registerNPC(14868, this);
		registerNPC(587, this);
		registerNPC(15086, this);
		registerNPC(15055, this);
		registerNPC(455, this);
		registerNPC(14854, this);
		registerNPC(454, this);
		registerNPC(14862, this);
		registerNPC(14911, this);
		registerNPC(15085, this);
		registerNPC(14860, this);
		registerNPC(14877, this);
		registerNPC(14870, this);
		registerNPC(14874, this);
		registerObject(66991, this);
		registerObject(66992, this);
		registerObject(2142, this);
		return registerNPC(6893, this);
	}

	@Override
	public boolean handleNPCClick(Player player, NPC npc) {
		switch (npc.getId()) {
		// Pet Shop Owner
		case 6893:
			player.getDialogueManager().startDialogue("PetShopOwner", npc.getId());
			break;

		// Pikkupstix
		case 6988:
			player.getDialogueManager().startDialogue("Pikkupstix", npc.getId());
			break;

		// Magestix
		case 14866:
			player.getDialogueManager().startDialogue("Magestix", npc.getId());
			break;

		// Jacquelyn Manslaughter
		case 14868:
			player.getDialogueManager().startDialogue("JacquelynManslaughter", npc.getId());
			break;

		// Jatix
		case 587:
			player.getDialogueManager().startDialogue("Jatix", npc.getId());
			break;

		// Foppish Pierre
		case 15086:
			player.getDialogueManager().startDialogue("FoppishPierre", npc.getId());
			break;

		// Scalectrix
		case 15055:
			player.getDialogueManager().startDialogue("Scalectrix", npc.getId());
			break;

		// Kaqemeex
		case 455:
			player.getDialogueManager().startDialogue("Kaqemeex", 0);
			break;

		// Poletax
		case 14854:
			player.getDialogueManager().startDialogue("Poletax", npc.getId());
			break;

		// Sanfew
		case 454:
			player.getDialogueManager().startDialogue("Sanfew", 0);
			break;

		// Alfred Stonemason
		case 14862:
			player.getDialogueManager().startDialogue("AlfredStonemason", npc.getId());
			break;

		// Pompous Merchant
		case 14911:
			player.getDialogueManager().startDialogue("PompousMerchant", npc.getId());
			break;

		// Nails Newton
		case 15085:
			player.getDialogueManager().startDialogue("NailsNewton", npc.getId());
			break;
		// Head Farmer
		case 14860:
			player.getDialogueManager().startDialogue("HeadFarmerJones", npc.getId());
			break;
		// Jack Oval
		case 14877:
			player.getDialogueManager().startDialogue("JackOval", npc.getId());
			break;
		// Tobias Bronzearms
		case 14870:
			player.getDialogueManager().startDialogue("TobiasBronzearms", npc.getId());
			break;
		// Martin Steelweaver
		case 14874:
			player.getDialogueManager().startDialogue("MartinSteelweaver", npc.getId());
			break;
		}
		return false;
	}

	@Override
	public boolean handleNPCClick2(Player player, NPC npc) {
		switch (npc.getId()) {
		// Pet Shop Owner
		case 6893:
			ShopsHandler.openShop(player, 163);
			break;
		// Pikkupstix
		case 6988:
			ShopsHandler.openShop(player, 164);
			break;
		// Jatix
		case 587:
			ShopsHandler.openShop(player, 55);
			break;
		}
		return false;
	}

	@Override
	public boolean handleNPCClick3(Player player, NPC npc) {
		switch (npc.getId()) {
		// Magestix
		case 14866:
			ShopsHandler.openShop(player, 162);
			break;
		// Jacquelyn Manslaughter
		case 14868:
			ShopsHandler.openShop(player, 54);
			break;
		// Poletax
		case 14854:
			ShopsHandler.openShop(player, 165);
			break;
		// Head Farmer Jones
		case 14860:
			ShopsHandler.openShop(player, 167);
			break;
		// Tobias Bronzearms
		case 14870:
			ShopsHandler.openShop(player, 169);
			break;
		// Martin Steelweaver
		case 14874:
			ShopsHandler.openShop(player, 170);
			break;
		}
		return false;
	}

	@Override
	public boolean handleNPCClick4(Player player, NPC npc) {
		switch (npc.getId()) {
		// Jack Oval
		case 14877:
			ShopsHandler.openShop(player, 168);
			break;
		}
		return false;
	}

	@Override
	public boolean handleObjectClick(Player player, WorldObject object) {
		switch (object.getId()) {
		case 66991:
			player.setNextWorldTile(new WorldTile(2885, 9795, 0));
			break;
		case 66992:
			player.setNextWorldTile(new WorldTile(2885, 3395, 0));
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
		switch (object.getId()) {
		case 2142:
			switch (item.getId()) {
			case 2132:
				player.removeItem(item.getId(), 1);
				player.addItem(522, 1);
				break;
			case 2134:
				player.removeItem(item.getId(), 1);
				player.addItem(523, 1);
				break;
			case 2136:
				player.removeItem(item.getId(), 1);
				player.addItem(524, 1);
				break;
			case 2138:
				player.removeItem(item.getId(), 1);
				player.addItem(525, 1);
				break;
			}
			break;
		}
		return false;
	}

}
