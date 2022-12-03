package com.rs.game.cityhandler.impl;

import com.rs.game.WorldObject;
import com.rs.game.cityhandler.CityEvent;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;

public class TutorialIsland implements CityEvent {

	@Override
	public boolean init() {
		registerNPC(2796, this);
		registerNPC(944, this);
		registerNPC(949, this);
		registerNPC(954, this);
		registerNPC(948, this);
		registerNPC(942, this);
		registerNPC(943, this);
		return registerNPC(945, this);
	}

	@Override
	public boolean handleNPCClick(Player player, NPC npc) {
		switch(npc.getId()) {
		case 945:
			player.getDialogueManager().startDialogue("ArrowGuide", new Object[] { Integer.valueOf(npc.getId()), Boolean.valueOf(true) });
			break;
		case 943:
			player.getDialogueManager().startDialogue("SurvivalExpert", new Object[] { Integer.valueOf(npc.getId()), Boolean.valueOf(true) });
			break;
		case 942:
			player.getDialogueManager().startDialogue("MasterChef", new Object[] { Integer.valueOf(npc.getId()), Boolean.valueOf(true) });
			break;
		case 948:
			player.getDialogueManager().startDialogue("MiningExpert", new Object[] { Integer.valueOf(npc.getId()), Boolean.valueOf(true) });
			break;
		case 954:
			player.getDialogueManager().startDialogue("BrotherBrace", npc);
			break;
		case 944:
			player.getDialogueManager().startDialogue("CombatInstructor");
			break;
		case 2796:
			player.getDialogueManager().startDialogue("Skippy", npc);
			break;
		case 949:
			player.getDialogueManager().startDialogue("QuestGuide",
					npc.getId(), null);
			break;

		}
		return false;
	}

	@Override
	public boolean handleNPCClick2(Player player, NPC npc) {

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
