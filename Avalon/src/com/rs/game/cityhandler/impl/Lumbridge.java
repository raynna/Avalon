package com.rs.game.cityhandler.impl;


import com.rs.game.WorldObject;
import com.rs.game.cityhandler.CityEvent;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.dialogues.npcs.FremennikShipmaster;
import com.rs.utils.ShopsHandler;

public class Lumbridge implements CityEvent {

	/**
	 * This is how we code in citys, aswell with dungeons.
	 */

	@Override
	public boolean init() {
		registerObject(278, this);
		registerObject(15468, this);
		registerObject(36974, this);
		registerNPC(0, this);
		registerNPC(29, this);
		registerNPC(2244, this);
		registerNPC(7969, this);
		registerNPC(3777, this);
		registerNPC(9633, this);
		registerNPC(519, this);
		registerNPC(520, this);
		registerNPC(521, this);
		registerNPC(741, this);
		registerNPC(7870, this);
		registerNPC(7885, this);
		registerNPC(7886, this);
		registerNPC(7887, this);
		registerNPC(7889, this);
		registerNPC(7890, this);
		registerNPC(3807, this);
		registerNPC(7937, this);
		registerNPC(7872, this);
		registerNPC(4906, this);
		registerNPC(452, this);
		registerNPC(3331, this);
		registerNPC(3328, this);
		registerNPC(758, this);
		registerNPC(4904, this);
		registerNPC(9709, this);
		registerNPC(458, this);
		registerNPC(9708, this);
		registerNPC(456, this);
		registerNPC(13937, this);
		registerNPC(2237, this);
		registerNPC(705, this);
		registerNPC(1861, this);
		registerNPC(4707, this);
		registerNPC(706, this);
		registerNPC(2079, this);
		return registerNPC(2238, this);
	}

	@Override
	public boolean handleNPCClick(Player player, NPC npc) {
		switch (npc.getId()) {

		/* 
		 * Lumbridge Sage
		 */
		case 2244:
			player.getDialogueManager().startDialogue("LumbridgeSage",
					npc.getId());
			break;
			/*
			 * Explorer Jack
			 */
		case 7969:
			player.getDialogueManager().startDialogue("ExplorerJack",
					npc.getId());
			break;
			/*
			Doomsayer
			 */
		case 3777:
			player.getDialogueManager().startDialogue("Doomsayer", npc.getId());
			break;
			/*
			 * Xenia
			 */
		case 9633:
			player.getDialogueManager().startDialogue("Xenia", npc.getId());
			break;
			/*
			 * Donnie
			 */
		case 2238:
			player.getDialogueManager().startDialogue("Donie", npc.getId());
			break;
			/*
			 * Bob
			 */
		case 519:
			player.getDialogueManager().startDialogue("Bob", npc.getId());
			break;
			/*
			 * Gillie Groats
			 */
		case 3807:
			player.getDialogueManager().startDialogue("GillieGroats", npc.getId());
			break;
			/*
			 * Seth Groats
			 */
		case 452:
			player.getDialogueManager().startDialogue("SethGroats", npc.getId());
			break;
			/*
			 * Lactopher
			 */
		case 7870:
			player.getDialogueManager().startDialogue("Lactopher", npc.getId());
			break;
			/*
			 * Victoria
			 */
		case 7872:
			player.getDialogueManager().startDialogue("Victoria", npc.getId());
			break;
			/*
			 * Duke Horacio
			 */
		case 741:
			player.getDialogueManager().startDialogue("DukeHoracio", 0);
			break;
			/*
			 * Wizard Mizgog
			 */
		case 706:
			player.getDialogueManager().startDialogue("WizardMizgog", 0);
			break;

			/*
			 * Wilfred
			 */
		case 4906:
			player.getDialogueManager().startDialogue("Wilfred", npc.getId());
			break;
			/*
			 * Fred the Farmer
			 */
		case 758:
			player.getDialogueManager().startDialogue("FredTheFarmer", npc.getId());
			break;
			/*
			 * Guardsman Dante
			 */
		case 7885:
			player.getDialogueManager().startDialogue("GuardsmanDante", npc.getId());
			break;
			/*
			 * Guardsman DeShawn
			 */
		case 7886:
			player.getDialogueManager().startDialogue("GuardsmanDeShawn", npc.getId());
			break;
			/*
			 * Guardsman Brawn
			 */
		case 7887:
			player.getDialogueManager().startDialogue("GuardsmanBrawn", npc.getId());
			break;
			/*
			 * Guardsman Pazel
			 */
		case 7889:
			player.getDialogueManager().startDialogue("GuardsmanPazel", npc.getId());
			break;
			/*
			 * Guardsman Peale
			 */
		case 7890:
			player.getDialogueManager().startDialogue("GuardsmanPeale", npc.getId());
			break;
			/*
			 * Hans
			 */
		case 0:
			player.getDialogueManager().startDialogue("Hans", npc.getId());
			break;
			/*
			 * Father Aereck
			 */
		case 7937:
			player.getDialogueManager().startDialogue("FatherAereck", npc.getId());
			break;
			/*
			 * Barfy Bill
			 */
		case 3331:
			player.getDialogueManager().startDialogue("BarfyBill", npc.getId());
			break;
			/*
			 * Tarquin
			 */
		case 3328:
			player.getDialogueManager().startDialogue("Tarquin", npc.getId());
			break;
			/*
			 * Apprentice Smith
			 */
		case 4904:
			player.getDialogueManager().startDialogue("ApprenticeSmith", npc.getId());
			break;
			/*
			 * Father Urhney
			 */
		case 458:
			player.getDialogueManager().startDialogue("FatherUrhney", npc.getId());
			break;
			/*
			 * Fremennik Warrior
			 */
		case 9709:
			player.getDialogueManager().startDialogue("FremennikWarrior", npc.getId());
			break;
			/*
			 * Fremennik ShipMaster
			 */
		case 9708:
			player.getDialogueManager().startDialogue("FremennikShipMaster", npc.getId());
			break;
			/*
			 * Herald Of Lumbridge
			 */
		case 13937:
			player.getDialogueManager().startDialogue("HeraldOfLumbridge", npc.getId());
			break;
			/*
			 * Gee
			 */
		case 2237:
			player.getDialogueManager().startDialogue("Gee", npc.getId());
			break;

		case 4707:
			player.getDialogueManager().startDialogue("MagicInstructor");
			break;

		case 705:
			player.getDialogueManager().startDialogue("MeleeInstructor");
			break;

		case 1861:
			player.getDialogueManager().startDialogue("RangeInstructor");
			break;
		case 278:
			player.getDialogueManager().startDialogue("LumbridgeCook",
					0);
			break;
		case 520:
			player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 1);
			break;
		case 521:
			player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 1);
			break;
		}
		return false;
	}

	@Override
	public boolean handleNPCClick2(Player player, NPC npc) {
		switch(npc.getId()) {
		/*
		 * Bob's Axes Shop
		 */
		case 519:
			ShopsHandler.openShop(player, 58);
			break;
			/*
			 * Musician
			 */
		case 29:
			player.getDialogueManager().startDialogue("MusicianD", npc.getId());
			break;

			/**
			 * Unknown
			 */
		case 520:
		case 523:
			ShopsHandler.openShop(player, 1);
			break;

			/*
			 * Unknown
			 */
		case 521:
			ShopsHandler.openShop(player, 1);
			break;

			/**
			 * Fremennik Sailor
			 */
		case 9708:
			FremennikShipmaster.sail(player, false);
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
		if(object.getId() == 15468) 
			player.getDialogueManager().startDialogue("HammerCrate");
		else if(object.getId() == 36974)
			player.getDialogueManager().startDialogue("TakeHatchetFromLogs");
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
