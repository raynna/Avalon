package com.rs.game.cityhandler.impl;

import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.cityhandler.CityEvent;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.combat.Magic;
import com.rs.game.player.content.PlayerLook;
import com.rs.utils.ShopsHandler;

/**
 * Varrock.java
 * 
 * @author Austin
 * 
 *         Handles Varrock interactions.
 */

public class Varrock implements CityEvent {

	@Override
	public boolean init() {
		registerNPC(9362, this);
		registerNPC(552, this);
		registerNPC(551, this);
		registerNPC(756, this);
		registerNPC(733, this);
		registerNPC(11, this);
		registerNPC(5913, this);
		registerNPC(368, this);
		registerNPC(337, this);
		registerNPC(339, this);
		registerNPC(732, this);
		registerNPC(341, this);
		registerNPC(342, this);
		registerNPC(6135, this);
		registerNPC(549, this);
		registerNPC(547, this);
		registerNPC(13727, this);
		registerNPC(546, this);
		registerNPC(2778, this);
		registerNPC(548, this);
		registerNPC(638, this);
		registerNPC(2320, this);
		registerNPC(4905, this);
		registerNPC(5910, this);
		registerNPC(550, this);
		registerNPC(5925, this);
		registerNPC(9359, this);
		registerNPC(8174, this);
		registerNPC(647, this);
		registerNPC(648, this);
		registerNPC(646, this);
		registerNPC(5947, this);
		registerNPC(5941, this);
		registerNPC(5938, this);
		registerNPC(5946, this);
		registerNPC(640, this);
		registerNPC(3374, this);
		registerNPC(5984, this);
		registerNPC(6529, this);
		return registerNPC(641, this);
	}

	/**
	 * A method to handle NPC clicks
	 */

	@Override
	public boolean handleNPCClick(Player player, NPC npc) {
		switch (npc.getId()) {
		// Gypsy aris
		case 9362:
			player.getDialogueManager().startDialogue("GypsyAris", npc.getId(), -1);
			break;
		// Charlie The Tramp
		case 641:
			player.getDialogueManager().startDialogue("CharlieTheTramp", npc.getId());
			break;

		// Varrock Sword Shop
		case 552:
			player.getDialogueManager().startDialogue("SwordShopKeeper", npc.getId());
			break;

		// Varrock Sword Shop
		case 551:
			player.getDialogueManager().startDialogue("SwordShopKeeper", npc.getId());
			break;

		// Dr Harlow
		case 756:
			player.getDialogueManager().startDialogue("DrHarlow", npc.getId());
			break;

		// Tramp
		case 11:
			player.getDialogueManager().startDialogue("Tramp", npc.getId());
			break;

		// Aubury
		case 5913:
			player.getDialogueManager().startDialogue("Aubury", 0);
			break;

		// Guard
		case 368:
			player.getDialogueManager().startDialogue("Guard", npc.getId());
			break;

		// Da Vinci
		case 337:
			player.getDialogueManager().startDialogue("DaVinci", npc.getId());
			break;

		// Chancey
		case 339:
			player.getDialogueManager().startDialogue("Chancey", npc.getId());
			break;

		// South Eastern Bartender
		case 732:
			player.getDialogueManager().startDialogue("SouthEastBartender", npc.getId());
			break;

		// Hops
		case 341:
			player.getDialogueManager().startDialogue("Hops", npc.getId());
			break;

		// Guidor's Wife
		case 342:
			player.getDialogueManager().startDialogue("GuidorsWife", npc.getId());
			break;

		// Town Crier
		case 6135:
			player.getDialogueManager().startDialogue("TownCrier", npc.getId());
			break;

		// Horvik
		case 549:
			player.getDialogueManager().startDialogue("Horvik", npc.getId());
			break;

		// Baraek
		case 547:
			player.getDialogueManager().startDialogue("Baraek", npc.getId());
			break;

		// Xuan
		case 13727:
			player.getDialogueManager().startDialogue("Xuan", npc.getId());
			break;

		// Zaff
		case 546:
			player.getDialogueManager().startDialogue("Zaff", npc.getId());
			break;

		// Iffie
		case 2778:
			player.getDialogueManager().startDialogue("Iffie", npc.getId());
			break;

		// Thessalia
		case 548:
			player.getDialogueManager().startDialogue("Thessalia", npc.getId());
			break;

		// Apothercary
		case 638:
			player.getDialogueManager().startDialogue("Apothecary", npc.getId());
			break;

		// Varrock Tanner
		case 2320:
			player.getDialogueManager().startDialogue("VarrockTanner", npc.getId());
			break;

		// Sani
		case 4905:
			player.getDialogueManager().startDialogue("Sani", npc.getId());
			break;

		// Blue Moon Inn Cook
		case 5910:
			player.getDialogueManager().startDialogue("BlueMoonCook", npc.getId());
			break;

		// Lowe
		case 550:
			player.getDialogueManager().startDialogue("Lowe", npc.getId());
			break;

		// Benny
		case 5925:
			player.getDialogueManager().startDialogue("Benny", npc.getId());
			break;

		// Sir Prysin
		case 9359:
			player.getDialogueManager().startDialogue("SirPrysin", npc.getId());
			break;

		// Clive
		case 8174:
			player.getDialogueManager().startDialogue("Clive", npc.getId());
			break;

		// Reldo
		case 647:
			player.getDialogueManager().startDialogue("Reldo", npc.getId());
			break;

		// King Roald
		case 648:
			player.getDialogueManager().startDialogue("KingRoald", npc.getId());
			break;

		// Curator Haig Halen
		case 646:
			player.getDialogueManager().startDialogue("CuratorHaigHalen", npc.getId());
			break;
		// Teacher & Pupil
		case 5947:
			player.getDialogueManager().startDialogue("TeacherAndPupil", npc.getId());
			break;

		// Schoolboy
		case 5946:
			player.getDialogueManager().startDialogue("Schoolboy", npc.getId());
			break;

		// Information Clerk
		case 5938:
			player.getDialogueManager().startDialogue("MuseumClerk", npc.getId());
			break;

		// Father Lawrence
		case 640:
			player.getDialogueManager().startDialogue("FatherLawrence", npc.getId());
			break;

		// Schoolgirl
		case 5984:
			player.getDialogueManager().startDialogue("Schoolgirl", npc.getId());
			break;

		case 523:
			player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 1);
			break;

		case 522:
			player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 1);
			break;
		}
		return false;
	}

	@Override
	public boolean handleNPCClick2(Player player, NPC npc) {
		switch (npc.getId()) {
		/*
		 * Gypsy
		 */

		case 9362:
			player.getDialogueManager().startDialogue("GypsyAris", 9362);
			break;
		/*
		 * Varrock Sword Shop
		 */
		case 552:
			ShopsHandler.openShop(player, 4);
			break;

		case 551:
			ShopsHandler.openShop(player, 4);
			break;
		/*
		 * Horvik
		 */
		case 549:
			ShopsHandler.openShop(player, 8);
			break;
		/*
		 * Zaff
		 */
		case 546:
			ShopsHandler.openShop(player, 3);
			break;
		/*
		 * Thessalia
		 */
		case 548:
			ShopsHandler.openShop(player, 2);
			break;
		/*
		 * Iffie
		 */
		case 2778:
			ShopsHandler.openShop(player, 161);
			break;
		/*
		 * Lowe
		 */
		case 550:
			ShopsHandler.openShop(player, 7);
			break;

		/*
		 * Aubury
		 */
		case 5913:
			ShopsHandler.openShop(player, 6);
			break;

		case 522:
			ShopsHandler.openShop(player, 1);
			break;

		case 523:
			ShopsHandler.openShop(player, 1);
			break;
		}
		return false;
	}

	@Override
	public boolean handleNPCClick3(Player player, NPC npc) {
		switch (npc.getId()) {
		/*
		 * Aubury
		 */
		case 5913:
			ShopsHandler.openShop(player, 6);
			break;

		/**
		 * Zaff
		 */
		case 546:
			ShopsHandler.openShop(player, 10);
			break;

		/*
		 * Thessalia
		 */
		case 548:
			PlayerLook.openThessaliasMakeOver(player);
			break;

		/**
		 * Max
		 */

		case 3374:
			if (player.getSkills().getLevelForXp(Skills.ATTACK) < 99
					|| player.getSkills().getLevelForXp(Skills.STRENGTH) < 99
					|| player.getSkills().getLevelForXp(Skills.DEFENCE) < 99
					|| player.getSkills().getLevelForXp(Skills.RANGE) < 99
					|| player.getSkills().getLevelForXp(Skills.PRAYER) < 99
					|| player.getSkills().getLevelForXp(Skills.MAGIC) < 99
					|| player.getSkills().getLevelForXp(Skills.RUNECRAFTING) < 99
					|| player.getSkills().getLevelForXp(Skills.CONSTRUCTION) < 99
					|| player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 99
					|| player.getSkills().getLevelForXp(Skills.HITPOINTS) < 99
					|| player.getSkills().getLevelForXp(Skills.AGILITY) < 99
					|| player.getSkills().getLevelForXp(Skills.HERBLORE) < 99
					|| player.getSkills().getLevelForXp(Skills.THIEVING) < 99
					|| player.getSkills().getLevelForXp(Skills.CRAFTING) < 99
					|| player.getSkills().getLevelForXp(Skills.FLETCHING) < 99
					|| player.getSkills().getLevelForXp(Skills.SLAYER) < 99
					|| player.getSkills().getLevelForXp(Skills.HUNTER) < 99
					|| player.getSkills().getLevelForXp(Skills.MINING) < 99
					|| player.getSkills().getLevelForXp(Skills.SMITHING) < 99
					|| player.getSkills().getLevelForXp(Skills.FISHING) < 99
					|| player.getSkills().getLevelForXp(Skills.COOKING) < 99
					|| player.getSkills().getLevelForXp(Skills.FIREMAKING) < 99
					|| player.getSkills().getLevelForXp(Skills.WOODCUTTING) < 99
					|| player.getSkills().getLevelForXp(Skills.FARMING) < 99
					|| player.getSkills().getLevelForXp(Skills.SUMMONING) < 99) {
				player.getPackets().sendGameMessage("You need to be maxed to access Max's store.");
				return false;
			}
			for (Player players : World.getPlayers()) {
				players.getPackets().sendGameMessage("<img=6></img><shad=FF0000> News: " + player.getDisplayName()
						+ " has been awarded the Max Cape!");
				ShopsHandler.openShop(player, 52);
			}
			break;

		/**
		 * Bob Barter
		 */
		case 6529:
			player.getDialogueManager().startDialogue("BobBarterD", npc.getId());
			break;

		}
		return false;
	}

	@Override
	public boolean handleNPCClick4(Player player, NPC npc) {
		switch (npc.getId()) {
		/**
		 * Max
		 */
		case 3374:
			if (player.getSkills().getLevelForXp(Skills.ATTACK) < 99
					|| player.getSkills().getLevelForXp(Skills.STRENGTH) < 99
					|| player.getSkills().getLevelForXp(Skills.DEFENCE) < 99
					|| player.getSkills().getLevelForXp(Skills.RANGE) < 99
					|| player.getSkills().getLevelForXp(Skills.PRAYER) < 99
					|| player.getSkills().getLevelForXp(Skills.MAGIC) < 99
					|| player.getSkills().getLevelForXp(Skills.RUNECRAFTING) < 99
					|| player.getSkills().getLevelForXp(Skills.CONSTRUCTION) < 99
					|| player.getSkills().getLevelForXp(Skills.DUNGEONEERING) < 99
					|| player.getSkills().getLevelForXp(Skills.HITPOINTS) < 99
					|| player.getSkills().getLevelForXp(Skills.AGILITY) < 99
					|| player.getSkills().getLevelForXp(Skills.HERBLORE) < 99
					|| player.getSkills().getLevelForXp(Skills.THIEVING) < 99
					|| player.getSkills().getLevelForXp(Skills.CRAFTING) < 99
					|| player.getSkills().getLevelForXp(Skills.FLETCHING) < 99
					|| player.getSkills().getLevelForXp(Skills.SLAYER) < 99
					|| player.getSkills().getLevelForXp(Skills.HUNTER) < 99
					|| player.getSkills().getLevelForXp(Skills.MINING) < 99
					|| player.getSkills().getLevelForXp(Skills.SMITHING) < 99
					|| player.getSkills().getLevelForXp(Skills.FISHING) < 99
					|| player.getSkills().getLevelForXp(Skills.COOKING) < 99
					|| player.getSkills().getLevelForXp(Skills.FIREMAKING) < 99
					|| player.getSkills().getLevelForXp(Skills.WOODCUTTING) < 99
					|| player.getSkills().getLevelForXp(Skills.FARMING) < 99
					|| player.getSkills().getLevelForXp(Skills.SUMMONING) < 99) {
				player.getPackets().sendGameMessage("You need to be maxed to access Max's store.");
				return false;
			}
			for (Player players : World.getPlayers()) {
				players.getPackets().sendGameMessage("<img=6></img><shad=FF0000> News: " + player.getDisplayName()
						+ " has been awarded the Max Cape!");
				ShopsHandler.openShop(player, 52);
			}
			break;

		/**
		 * Aubury
		 */
		case 5913:
			npc.setNextForceTalk(new ForceTalk("Senventior Disthinte Molesko!"));
			Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2909, 4834, 0));
			break;
		}
		return false;
	}

	@Override
	public boolean handleObjectClick(Player player, WorldObject object) {
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
