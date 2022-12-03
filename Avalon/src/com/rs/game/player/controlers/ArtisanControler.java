package com.rs.game.player.controlers;

import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.content.ArtisanWorkshop.Ingots;

public class ArtisanControler extends Controler {

	@Override
	public void start() {
		player.setNextWorldTile(new WorldTile(3036, 3338, 0));
		player.getArtisanWorkshop().sendOverlayInterface();
		player.getPackets().sendIComponentText(1073, 11, ""+ World.artisanBonusExp);
	}
	
	
	@Override
	public void process() {
		player.getPackets().sendIComponentText(1073, 11, ""+World.artisanBonusExp);
	}
	
	@Override
	public boolean processMagicTeleport(WorldTile toTile) {
		player.getInterfaceManager().closeOverlay(player.getInterfaceManager().resizableScreen ? true : false); 
		player.closeInterfaces();
		player.getControlerManager().forceStop();
		return true;
	}
	
	@Override
	public boolean logout() {
		return false;
	}

	
	@Override
	public boolean processNPCClick1(NPC npc) {
		int id = npc.getId();
		if (id == 6663 || id == 6647 || id == 6642) {
			player.sm("I should leave this worker alone.");
			return false;
		}
		return true;
	}
	
	@Override
	public boolean processObjectClick1(WorldObject object) {
		int id = object.getId();
		if (id == 30420 || id == 30421) {
			player.getDialogueManager().startDialogue("SimpleItemMessage", 2347,
					"Says here that we're supposed to make: " +World.artisanBonusExp);
			return false;
		}
		if (id == 29387 || id == 29386 || id == 29385) {
			player.sm("I shouldn't wander off into areas i'm not authorized in..");
		}
		if (object.getId() == 15526 || id == 4047 || id == 20258 || id == 24678 || id == 24677 || id == 24664 || id == 4046) {
			Ingots bar = Ingots.getBar(player);
			if (bar != null) {
				player.getDialogueManager().startDialogue("ArtisanSmithingD", bar);
				return false;
			} else {
				player.sm("You have no bars to use on the anvil.");
				return false;
			}
		}
        if (id == 29395 || id == 29394) { 
			player.getArtisanWorkshop().sendIngotInterface();
			return false;
		} else if (id == 29396) { 
			player.getArtisanWorkshop().depositArmour();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean processObjectClick2(WorldObject object) {
		int id = object.getId();
		if (id == 29395 || id == 29394) { 
			player.getArtisanWorkshop().despositIngots();
			return false;
		}
		return true;
	}
	
	@Override
	public boolean processObjectClick3(WorldObject object) {
		int id = object.getId();
		if(id== 29395 || id == 29394) {
			player.getDialogueManager().startDialogue("depositeArtisan");
			return false;
		}
		return true;
	}
	
	@Override
	public boolean processObjectClick4(WorldObject object) {
		int id = object.getId();
		 if (id == 29395 || id == 29394)  {
			player.getDialogueManager().startDialogue("WithdrawArtisanD");
			return false;	  
		 }
		return true;
	}
	
	@Override
	public boolean processButtonClick(int interfaceId, int componentId, int slotId, int slotId2, int packetId) {
        if (interfaceId == 1072) {
			player.getArtisanWorkshop().handelButtons(componentId);
			return false;
        } else if (interfaceId == 825) {
			player.getArtisanWorkshop().handelRewardButtons(componentId);
			return false;
        }
		return true;
	}
	
	@Override
	public boolean login() {
		player.getArtisanWorkshop().sendOverlayInterface();
		player.getPackets().sendIComponentText(1073, 11, ""+World.artisanBonusExp);
		return false;
	}
	
	@Override
	public void magicTeleported(int teleType) {
		player.getInterfaceManager().closeOverlay(player.getInterfaceManager().resizableScreen ? true : false);
		player.closeInterfaces();
		player.getControlerManager().forceStop();
	}
	
}
