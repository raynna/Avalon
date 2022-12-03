package com.rs.game.cityhandler.impl;


import com.rs.game.WorldObject;
import com.rs.game.cityhandler.CityEvent;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.content.PlayerLook;
import com.rs.utils.ShopsHandler;

public class Falador implements CityEvent {

	@Override
	public boolean init() {
		registerNPC(39, this);
		registerNPC(284, this);
		registerNPC(370, this);
		registerNPC(577, this);
		registerNPC(580, this);
		registerNPC(598, this);
		registerNPC(650, this);
		registerNPC(659, this);
		registerNPC(1217, this);
		registerNPC(2290, this);
		registerNPC(2676, this);
		registerNPC(3217, this);
		registerNPC(3234, this);
		registerNPC(6137, this);
		registerNPC(6663, this);
		registerNPC(12237, this);
		registerNPC(13939, this);
		return registerObject(41337, this);

	}

	@Override
	public boolean handleNPCClick(Player player, NPC npc) {
		switch(npc.getId()) {
		/*
		 * Town Crier
		 */
		case 6137:
			player.getDialogueManager().startDialogue("TownCrier", npc.getId());
			break;
			/*
			 * Kaylee
			 */
		case 3217:
			player.getDialogueManager().startDialogue("Kaylee", npc.getId());
			break;
			/*
			 * Flynn
			 */
		case 580:
			player.getDialogueManager().startDialogue("Flynn", npc.getId());
			break;
			/*
			 * Cassie
			 */
		case 577:
			player.getDialogueManager().startDialogue("Cassie", npc.getId());
			break;
			/**
			 * Hair dresser
			 */
		case 598:
			PlayerLook.openHairdresserSalon(player);
			break;
			/*
			 * Sir Tiffy Cashien
			 */
		case 2290:
			player.getDialogueManager().startDialogue("SirTiffyCashien", npc.getId());
			break;
			/*
			 * Ikis Krum
			 */
		case 12237:
			player.getDialogueManager().startDialogue("IkisKrum", npc.getId());
			break;
			/*
			 * Party Pete
			 */
		case 659:
			player.getDialogueManager().startDialogue("PartyPete", npc.getId());
			break;
			/*
			 * Aksel
			 */
		case 6663:
			player.getDialogueManager().startDialogue("Aksel", npc.getId());
			break;
			/*
			 * Herald of Falador
			 */
		case 13939:
			player.getDialogueManager().startDialogue("HeraldOfFalador", npc.getId());
			break;

			/*
			 * Silif
			 */
		case 370:
			player.getDialogueManager().startDialogue("Silif", npc.getId());
			break;

			/*
			 * Sir Vyvin
			 */
		case 605:
			player.getDialogueManager().startDialogue("SirVyvin", npc.getId());
			break;

			/*
		Doric
			 */
		case 284:
			player.getDialogueManager().startDialogue("Doric", npc.getId());
			break;
			/*
			 * Wyson the Gardener
			 */
		case 39:
			player.getDialogueManager().startDialogue("Wyson");
			break;

		}
		return false;
	}

	@Override
	public boolean handleNPCClick2(Player player, NPC npc) {
		switch(npc.getId()) {
		/*
		 * Flynn's Mace Shop
		 */
		case 580:
			ShopsHandler.openShop(player, 44);
			break;

			/*
			 * Cassies Shield Shop
			 */
		case 577:
			ShopsHandler.openShop(player, 45);
			break;

			/**
			 * Hair dresser
			 */
		case 598:
			PlayerLook.openHairdresserSalon(player);
			break;

			/*
			 * Gardener
			 */
		case 1217:
			player.getDialogueManager().startDialogue("Gardener", npc.getId());
			break;

			/**
			 *  Make over mage
			 */
		case 2676:
			PlayerLook.openMageMakeOver(player);
			break;

			/*
			 * Gardener
			 */
		case 3234:
			player.getDialogueManager().startDialogue("Gardener", npc.getId());
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
		/*
		 * Wanted Poster
		 */
		if (object.getId() == 41337)
			player.getDialogueManager().startDialogue("WantedPoster", object.getId());
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
