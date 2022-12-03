package com.rs.game.cityhandler.impl;

import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.WorldObject;
import com.rs.game.cityhandler.CityEvent;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;

public class WorldWide implements CityEvent {

	int[] manDialogue = { 1, 2, 3, 4, 5, 6, 7875, 7876, 7877, 7878, 7879, 7880, 7881, 7882, 7883, 7884
	
	},

	slayerMasters = { 8462, 8480, 8481, 8465, 1597, 1598, 7780, 8466, 8467, 9085

	},
	
	sheep = { 5157, 1765, 43, 5160, 5161, 5156
	
	},
	
	bankers = { 13455, 2617, 2618, 15194
			
	};
	
	

	@Override
	public boolean init() {
		for(int registerManD = 0; registerManD < manDialogue.length; registerManD++) {
			registerNPC(manDialogue[registerManD], this);
		}
		for(int registerSlayerMasters = 0; registerSlayerMasters < slayerMasters.length;registerSlayerMasters++) {
			registerNPC(slayerMasters[registerSlayerMasters], this);
		}
		for(int registerSheep = 0;registerSheep < sheep.length;registerSheep++) {
			registerNPC(sheep[registerSheep], this);
		}
		for(int registerBankers = 0; registerBankers < bankers.length;registerBankers++) {
			registerNPC(bankers[registerBankers], this);
		}
		registerNPC(1 << 24, this);
		registerNPC(523, this);
		registerNPC(4247, this);
		return registerNPC(11498, this);
	}

	@Override
	public boolean handleNPCClick(Player player, final NPC npc) {

		/**
		 * Bankers
		 */
		for(int bankerNpcId = 0; bankerNpcId < bankers.length;bankerNpcId++) {
			if(npc.getId() == bankers[bankerNpcId]) {
				player.getBank().openBank();
			}
		}
		
		/**
		 * Man || woman dialogues 
		 */
		if (npc.getId() >= 1 && npc.getId() <= 6
				|| npc.getId() >= 7875 && npc.getId() <= 7884) {
			player.getDialogueManager().startDialogue("Man",
					npc.getId());
		}
		
		
		/**
		 * Handles sheep
		 */
		for(int x = 0; x < sheep.length;x++) {
			if(npc.getId() == sheep[x]) {
				final int npcId = npc.getId();
				if (Utils.getRandom(2) == 0) {
					npc.setNextForceTalk(new ForceTalk("Baa!"));
					npc.playSound(756, 1);
					npc.addWalkSteps(npcId, npcId, 4, true);
					npc.setRun(true);
					player.getPackets().sendGameMessage(
							"The sheep runs away from you.");
				} else if (player.getInventory().containsItem(1735,
						1)) {
					player.playSound(761, 1);
					player.getInventory().addItem(1737, 1);
					player.getPackets().sendGameMessage(
							"You shear the sheep of it's fleece.");
					player.animate(new Animation(893));
					npc.transformIntoNPC(5149);
					WorldTasksManager.schedule(new WorldTask() {
						@Override
						public void run() {
							npc.transformIntoNPC(npcId);
						}
					}, 10);
				} else
					player.getPackets()
					.sendGameMessage(
							"You need a pair of shears to shear the sheep.");
			}
		}
		switch(npc.getId()) {

		/**
		 * Xuan
		 */
		case 11498:
			player.getDialogueManager().startDialogue("LoyaltyPoints",
					npc.getId());
			return true;
			/**
			 * Shop Keepers
			 */
		case 523:
			player.getDialogueManager().startDialogue("StoreKeeper");
			return true;
			
			/**
			 * Estate agents
			 */
		case 4247:
			player.getDialogueManager().startDialogue("EstateAgentD", npc.getId());
			return true;
		}
		return false;
	}

	@Override
	public boolean handleNPCClick2(Player player, NPC npc) {
		
		switch(npc.getId()) {
		/**
		 * Shop Keepers
		 */
		case 523:
			ShopsHandler.openShop(player,  1);
			return true;
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
		
		switch(npc.getId()) {
		
		}
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
