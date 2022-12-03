package com.rs.game.cityhandler.impl;

import com.rs.game.WorldObject;
import com.rs.game.cityhandler.CityEvent;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.actions.skills.fishing.Fishing.FishingSpots;
import com.rs.game.player.dialogues.Dialogue;

public class Miscellania implements CityEvent {

	@Override
	public boolean init() {
		registerNPC(3120, this);
		registerNPC(1395, this);
		registerNPC(1374, this);
		registerNPC(1396, this);
		registerNPC(1397, this);
		registerNPC(3917, this);
		registerNPC(1399, this);
		registerNPC(3916, this);
		registerObject(46277, this);
		registerObject(54778, this);
		registerObject(29215, this);
		registerObject(29216, this);
		registerObject(29517, this);
		return registerObject(54787, this);
	}

	@Override
	public boolean handleNPCClick(Player player, NPC npc) {
		switch(npc.getId()) {
		case 3120:
			player.getDialogueManager().startDialogue("AdvisorGhrim", npc.getId());
			return true;
		case 1395:
			player.getDialogueManager().startDialogue("LumberjackLeif", npc.getId());
			return true;
		case 1396:
			player.getDialogueManager().startDialogue("MinerMagnus", npc.getId());
			return true;
		case 1397:
			player.getDialogueManager().startDialogue("FishermanFrodi", npc.getId());
			return true;
		case 1374:
			player.getDialogueManager().startDialogue(new Dialogue() {

				@Override
				public void start() {
					sendPlayerDialogue(NORMAL, "I probably shouldn't bother this guard.");
				}

				@Override
				public void run(int interfaceId, int componentId) {
					end();
				}

				@Override
				public void finish() {
					
				}
				
			});
			return true;
		case 1399:
			FishingSpots spot = FishingSpots.forId(npc.getId() | 1 << 24);
			if (spot != null) {
				//TODO player.getActionManager().setAction(new MiscellaniaFishing(spot, npc));
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean handleNPCClick2(Player player, NPC npc) {
		switch(npc.getId()) {
		case 3120:
			//TODO player.getThrone().openResources();
			return true;
		case 1395:
		case 1396:
		case 1397:
		case 3917:
			//TODO player.getPackets().sendGameMessage("Your reputation with your kingdom is at " + player.getThrone().getReputation() + "%.");
			return true;
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
		if(!isInArea(player)) {
			player.sm("You are not in miscellenia");
			return false;
		}
		switch(object.getDefinitions().name.toLowerCase()) {
		case "maple tree":
			//TODO player.getActionManager().setAction(new MiscellaniaWoodcutting(object, TreeDefinitions.MAPLE));
			return true;
			
		case "evergreen":
			//TODO player.getActionManager().setAction(new MiscellaniaWoodcutting(object, TreeDefinitions.EVERGREEN));
			return true;
			
		case "coal rocks":
			//TODO player.getActionManager().setAction(new MiscellaniaMining(object, RockDefinitions.Coal_Ore));
			return true;
			
		}
		return true;
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
	
	public static boolean isInArea(Player player) {
		switch(player.getRegionId()) {
		case 10044:
		case 10301:
		case 10300:
			return true;
		}
		return false;
	}

	@Override
	public boolean handleItemOnObject(Player player, WorldObject object, Item item) {
		// TODO Auto-generated method stub
		return false;
	}

}
