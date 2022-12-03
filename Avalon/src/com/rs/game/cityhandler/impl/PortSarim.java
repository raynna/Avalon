package com.rs.game.cityhandler.impl;

import com.rs.game.WorldObject;
import com.rs.game.cityhandler.CityEvent;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.actions.Rest;
import com.rs.utils.ShopsHandler;

public class PortSarim implements CityEvent {

	@Override
	public boolean init() {
		registerNPC(2690, this);
		registerNPC(2691, this);
		registerNPC(6667, this);
		registerNPC(3509, this);
		registerNPC(734, this);
		registerNPC(558, this);
		registerNPC(557, this);
		registerNPC(559, this);
		registerNPC(583, this);
		registerNPC(556, this);
		registerNPC(604, this);
		registerNPC(375, this);
		return registerNPC(2692, this);
	}

	@Override
	public boolean handleNPCClick(Player player, NPC npc) {
		switch(npc.getId()) {
		//Ahab
		case 2692:
			player.getDialogueManager().startDialogue("Ahab", npc.getId());
			break;
			//Jack Seagull
		case 2690:
			player.getDialogueManager().startDialogue("JackSeagull", npc.getId());
			break;
			//Longbow Ben
		case 2691:
			player.getDialogueManager().startDialogue("LongBowBen", npc.getId());
			break;
			//Stanky Morgan
		case 6667:
			player.getDialogueManager().startDialogue("StankyMorgan", npc.getId());
			break;
			//Bard Roberts
		case 3509:
			player.stopAll();
			player.getActionManager().setAction(new Rest());
			break;
			//Bartender
		case 734:
			player.getDialogueManager().startDialogue("PortSarimBartender", npc.getId());
			break;
			//Gerrant
		case 558:
			player.getDialogueManager().startDialogue("Gerrant", npc.getId());
			break;
			//Wydin
		case 557:
			player.getDialogueManager().startDialogue("Wydin", 0);
			break;
			//Brian
		case 559:
			player.getDialogueManager().startDialogue("PortSarimBrian", npc.getId());
			break;
			//Betty
		case 583:
			player.getDialogueManager().startDialogue("Betty", npc.getId());
			break;
			//Grum
		case 556:
			player.getDialogueManager().startDialogue("Grum", npc.getId());
			break;
			//Thurgo
		case 604:
			player.getDialogueManager().startDialogue("Thurgo", npc.getId());
			break;
			//Redbeard frank
		case 375:
			player.getDialogueManager().startDialogue("RedbeardFrank", 0);
			break;
		}
		return false;
	}

	@Override
	public boolean handleNPCClick2(Player player, NPC npc) {
		switch(npc.getId()) {
		//Bard Roberts
		case 3509:
			player.getDialogueManager().startDialogue("BardRoberts", npc.getId());
			break;
			//Gerrant
		case 558:
			ShopsHandler.openShop(player, 31);
			break;
			//Wydin
		case 557:
			ShopsHandler.openShop(player, 30);
			break;
			//Brian
		case 559:
			ShopsHandler.openShop(player, 33);
			break;
			//Betty
		case 583:
			ShopsHandler.openShop(player, 34);
			break;
			//Grum
		case 556:
			ShopsHandler.openShop(player, 32);
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
