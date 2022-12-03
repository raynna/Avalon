package com.rs.game.cityhandler.impl;

import com.rs.game.WorldObject;
import com.rs.game.cityhandler.CityEvent;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.npc.others.LivingRock;
import com.rs.game.player.Player;
import com.rs.game.player.actions.skills.mining.LivingMineralMining;
import com.rs.game.player.actions.skills.mining.MiningBase;

public class LivingRockCaverns implements CityEvent {

	int[] livingRock = { 8837, 8838
			
	};//8839
	
	@Override
	public boolean init() {
		for(int x = 0; x < livingRock.length;x++) {
			registerNPC(livingRock[x], this);
		}
		return registerNPC(8839, this);
	}

	@Override
	public boolean handleNPCClick(Player player, NPC npc) {
		/**
		 * Living rock creatures
		 */
		for(int x = 0; x < livingRock.length;x++) {
			if(npc.getId() == livingRock[x]) {
				player.getActionManager().setAction(
						new LivingMineralMining((LivingRock) npc));
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean handleNPCClick2(Player player, NPC npc) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean handleNPCClick3(Player player, NPC npc) {
		/**
		 * Mining Bases
		 */
		for(int base = 0; base < livingRock.length;base++) {
			if(npc.getId() == livingRock[base]) {
				MiningBase.propect(player, "You examine the remains...",
						"The remains contain traces of living minerals.");
			}
		}
		return false;
	}

	@Override
	public boolean handleNPCClick4(Player player, NPC npc) {
		/**
		 * Living rock
		 */
		
		for(int rocks = 0; rocks < livingRock.length;rocks++) {
			if(npc.getId() == livingRock[rocks]) {
				MiningBase.propect(player, "You examine the remains...",
						"The remains contain traces of living minerals.");
			}
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
