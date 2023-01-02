package com.rs.game.cityhandler.impl;

import com.rs.game.WorldObject;
import com.rs.game.cityhandler.CityEvent;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.objects.scripts.DoorsAndGates;
import com.rs.game.player.Player;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.impl.princealirescue.PrinceAliRescue;
import com.rs.utils.ShopsHandler;

public class AlKharid implements CityEvent {

	@Override
	public boolean init() {
		registerNPC(524, this);
		registerNPC(525, this);
		registerNPC(539, this);
		registerNPC(540, this);
		registerNPC(541, this);
		registerNPC(542, this);
		registerNPC(543, this);
		registerNPC(544, this);
		registerNPC(545, this);
		registerNPC(920, this);
		registerNPC(15910, this);
		registerNPC(925, this);
		registerNPC(15907, this);
		registerNPC(9159, this);
		registerNPC(2810, this);
		registerNPC(2811, this);
		registerNPC(2912, this);
		registerNPC(2813, this);
		registerNPC(2824, this);
		registerObject(2693, this);
		registerObject(3436, this);
		return registerNPC(3809, this);
	}

	@Override
	public boolean handleNPCClick(Player player, NPC npc) {
		switch(npc.getId()) {
		
		case 2824:
			player.getDialogueManager().startDialogue("Tanner", npc.getId());
			break;
		/*
		 * Captain Dalbur
		 */
		case 3809:
			player.getDialogueManager().startDialogue("CaptainDalbur", npc.getId());
			break;
			
			/*
			 * Gem Trader
			 */
		case 540:
			player.getDialogueManager().startDialogue("GemTrader", npc.getId());
			break;
			/*
			 * Silk Trader
			 */
		case 539:
			player.getDialogueManager().startDialogue("SilkTrader", npc.getId());
			break;
			/*
			 * Faruq
			 */
		case 9159:
			player.getDialogueManager().startDialogue("Faruq", npc.getId());
			break;

			/**
			 * Toll Guards
			 */
		case 925:
			player.getDialogueManager().startDialogue("AlKharidToll", npc.getId());
			break;

			/*
			 * Osman
			 */
		case 15907:
			player.getDialogueManager().startDialogue("Osman", 0);
			break;
			
			/**
			 * Prince Ali
			 */
			
		case 920:
			player.getDialogueManager().startDialogue("Prince", 0);
			break;

			/*
			 * Hassan
			 */
		case 15910:
			player.getDialogueManager().startDialogue("Hassan", 0);
			break;

			/**
			 * Shop Assistant
			 */
		case 524:
			player.getDialogueManager().startDialogue("AlkharidShopAssistant");
			break;

			/**
			 * Shop Keeper
			 */
		case 525:
			player.getDialogueManager().startDialogue("AlkharidShopKeeper", npc.getId());
			break;

			/**
			 * Zeke
			 */
		case 541:
			player.getDialogueManager().startDialogue("Zeke", npc.getId());
			break;

			/**
			 * Louie Legs
			 */
		case 542:
			player.getDialogueManager().startDialogue("LouieLegs", npc.getId());
			break;

			/**
			 * Karim
			 */
		case 543:
			player.getDialogueManager().startDialogue("Karim", npc.getId());
			break;

			/**
			 * Ranael	
			 */
		case 544:
			player.getDialogueManager().startDialogue("Ranael", npc.getId());
			break;

			/**
			 * Dommik
			 */
		case 545:
			player.getDialogueManager().startDialogue("Dommik", npc.getId());
			break;

			/**
			 * Ollie the Camel
			 */

		case 2810:
		case 2811:
		case 2812:
		case 2813:
			player.getDialogueManager().startDialogue("AlkharidCamel", npc.getId());
			break;


		}
		return false;
	}

	@Override
	public boolean handleNPCClick2(Player player, NPC npc) {
		switch(npc.getId()) {

		/**
		 * Shop Keeper || Shop Assistant - Alkharid General Store
		 */
		case 524:
		case 525:
			ShopsHandler.openShop(player, 173);
			break;

			/*
			 * Gem Trader Shop
			 */
		case 540:
			ShopsHandler.openShop(player, 35);
			break;


			/**
			 * Zeke
			 */
		case 541:
			ShopsHandler.openShop(player, 36);
			break;

			/**
			 * Louie Legs
			 */
		case 542:
			ShopsHandler.openShop(player, 37);
			break;

			/**
			 * Ranael	
			 */
		case 544:
			ShopsHandler.openShop(player, 38);
			break;

			/**
			 * Dommik
			 */
		case 545:
			ShopsHandler.openShop(player, 13);
			break;

			/**
			 * Captain Dalbur
			 */
		case 3809:
			player.getDialogueManager().startDialogue("CaptainDalbur", npc.getId());
			break;	

			/*
			 * Faruq Shop
			 */
		case 9159:
			ShopsHandler.openShop(player, 171);
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
		case 2693:
			player.getBank().openBank();
			break;
			/**
			 * Prince Ali Cell Door
			 */
		case 3436:
			int progress = player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).getStage();
			//if(progress > 6) {
			if(player.getY() <= 3243) {
				//player.setSingleDoorDelay(1200);
				DoorsAndGates.handleDoor(player, object, 1200);
				player.addWalkSteps(player.getX(), player.getY() + 1, 0);
			} else {
				if(PrinceAliRescue.isLadyKeliSpawned() == false) {
					if(!player.getInventory().containsItem(PrinceAliRescue.BRONZE_KEY,  1)) {
						player.getPackets().sendGameMessage("That door is locked.");
						return false;
					}
					//player.setSingleDoorDelay(1200);
					DoorsAndGates.handleDoor(player, object, 1200);
					player.addWalkSteps(player.getX(), player.getY() - 1, 0);
					player.getPackets().sendGameMessage("You Unlock the door.");
				} else {
					player.getPackets().sendGameMessage("You'd better get rid of lady Keli before trying to go through there.");
				}
			}
			if(progress < 6) 
				player.getPackets().sendGameMessage("You can't reach that!");
//			if(player.getRights() > 1) {
//			if(player.getY() < 3243) {
//				if(player.getInventory().containsItem(PrinceAliRescue.BRONZE_KEY, 1)) {
//				player.setSingleDoorDelay(1200);
//				ObjectHandler.handleDoor(player, object, 1200);
//				player.addWalkSteps(player.getY() == object.getY() ? object.getY() + 1
//						: object.getX(), object.getY() - 1, 1, false);
//				player.getPackets().sendGameMessage("You Unlock the door.");
//			} else {
//				player.getPackets().sendGameMessage("The door is locked.");
//			}
//			} else {
//				player.getPackets().sendGameMessage("You'd better get rid of Lady Keli before trying to go through there.");
//			}
//			} else {
//				player.getPackets().sendGameMessage("You can't reach that!");
//			}
			break;
		}
		return true;
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
