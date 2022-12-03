package com.rs.game.npc.others;

import com.rs.game.Entity;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;

public class MaxGuard extends NPC {
	
	@SuppressWarnings("unused")
	private NPC max;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1776392517680641886L;

	public MaxGuard(int id, WorldTile tile, NPC max) {
		super(id, tile, -1, true);
		this.max = max;
	}
	
	private String stage = null;

	@Override
	public void processNPC() {
		super.processNPC();
		if (isDead()) {
			return;
		}
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}
}
