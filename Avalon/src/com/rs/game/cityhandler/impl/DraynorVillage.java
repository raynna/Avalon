package com.rs.game.cityhandler.impl;

import com.rs.game.ForceTalk;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.cityhandler.CityEvent;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.Magic;
import com.rs.utils.ShopsHandler;

public class DraynorVillage implements CityEvent {

	@Override
	public boolean init() {
		registerNPC(3299, this);
		registerNPC(3671, this);
		registerNPC(3820, this);
		registerNPC(4585, this);
		registerNPC(300, this);
		registerNPC(755, this);
		registerNPC(915, this);
		registerNPC(916, this);
		registerNPC(918, this);
		registerNPC(919, this);
		registerNPC(922, this);
		registerNPC(970, this);
		registerNPC(2233, this);
		registerObject(2347, this);
		registerObject(2348, this);
		return registerNPC(2574, this);
	}

	@Override
	public boolean handleNPCClick(Player player, NPC npc) {
		switch(npc.getId()) {
		/**
		 * Draynor Village NPC'S
		 */
		//Bank Guard
		case 2574:
			player.getDialogueManager().startDialogue("BankGuard", npc.getId());
			break;

			//Wise Old Man
		case 3820:
			player.getDialogueManager().startDialogue("WiseOldMan", npc.getId());
			break;

		case 755:
			player.getDialogueManager().startDialogue("Morgan", 0);
			break;

			/**
			 * Ned
			 */
		case 918:
			player.getDialogueManager().startDialogue("Ned", npc.getId());
			break;

			/**
			 * Joe
			 */

		case 916:
			player.getDialogueManager().startDialogue("Joe", 0);
			break;

			/**
			 * Leela
			 */
		case 915:
			player.getDialogueManager().startDialogue("Leela", 0);
			break;

			/**
			 * Aggie
			 */
		case 922:
			player.getDialogueManager().startDialogue("Aggie", 0);
			break;

			/**
			 * Master Garderner
			 */
		case 3299:
			player.getDialogueManager().startDialogue("MartinTheMasterGardener", npc.getId());
			break;

			/**
			 * Lady keli
			 */

		case 919:
			player.getDialogueManager().startDialogue("LadyKeli", 0);
			break;

			/**
			 * Fortunato
			 */

		case 3671:
			player.getDialogueManager().startDialogue("Fortunato", npc.getId());
			break;

			/**
			 * Diango
			 */

		case 970:
			player.getDialogueManager().startDialogue("Diango", npc.getId(), 0);
			break;

			/**
			 * Olivia
			 */
		case 2233:
			player.getDialogueManager().startDialogue("Olivia", npc.getId());
			break;

			/**
			 * Wizard's Tower NPC'S
			 */
		case 4585:
			player.getDialogueManager().startDialogue("ProfessorOnglewip", npc.getId());
			break;
			/*
			 * Sedridor
			 */
		case 300:
			player.getDialogueManager().startDialogue("Sedridor", 0);
			break;
		}
		return false;
	}

	@Override
	public boolean handleNPCClick2(Player player, NPC npc) {
		switch(npc.getId()) {
		/*
		 * Aggie
		 */
		case 922:
			player.getDialogueManager().startDialogue("Aggie", -3);
			break;
			/**
			 * Diango
			 */
		case 970:
			ShopsHandler.openShop(player, 98);
			break;

			/*
			 * Olivia
			 */
		case 2233:
			ShopsHandler.openShop(player, 99);
			break;

			/**
			 * Sedridor
			 */
		case 300:
			npc.setNextForceTalk(new ForceTalk("Senventior Disthine Molenko!"));
			player.lock(2);
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(
					2909, 4834, 0));
			break;
		}
		return false;
	}

	@Override
	public boolean handleNPCClick3(Player player, NPC npc) {
		switch(npc.getId()) {
		/**
		 * Diango
		 */
		case 970:
			player.getDialogueManager().startDialogue("Diango", npc.getId(), 3);
			break;
		}
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
		case 2347:
			if (object.getX() == 3100 && object.getY() == 3255)
				player.useStairs(828, new WorldTile(3102, 3255, 1), 1, 2);
			else if (object.getX() == 3091 && object.getY() == 3251)
				player.useStairs(828, new WorldTile(3093, 3251, 1), 1, 2);
			else if (object.getX() == 3084 && object.getY() == 3262)
				player.useStairs(828, new WorldTile(3083, 3262, 1), 1, 2);
			else if (object.getX() == 3100 && object.getY() == 3266)
				player.useStairs(828, new WorldTile(3102, 3266, 1), 1, 2);
			else if (object.getX() == 3098 && object.getY() == 3281)
				player.useStairs(828, new WorldTile(3097, 3281, 1), 1, 2);
			else if (object.getX() == 3092 && object.getY() == 3281)
				player.useStairs(828, new WorldTile(3094, 3281, 1), 1, 2);
			break;
		case 2348:
			if (object.getX() == 3100 && object.getY() == 3255)
				player.useStairs(828, new WorldTile(3099, 3255, 0), 1, 2);
			else if (object.getX() == 3091 && object.getY() == 3251)
				player.useStairs(828, new WorldTile(3090, 3251, 0), 1, 2);
			else if (object.getX() == 3084 && object.getY() == 3262)
				player.useStairs(828, new WorldTile(3086, 3262, 0), 1, 2);
			else if (object.getX() == 3100 && object.getY() == 3266)
				player.useStairs(828, new WorldTile(3099, 3266, 0), 1, 2);
			else if (object.getX() == 3098 && object.getY() == 3281)
				player.useStairs(828, new WorldTile(3100, 3281, 0), 1, 2);
			else if (object.getX() == 3092 && object.getY() == 3281)
				player.useStairs(828, new WorldTile(3091, 3281, 0), 1, 2);
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
