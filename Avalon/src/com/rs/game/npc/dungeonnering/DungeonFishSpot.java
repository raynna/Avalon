package com.rs.game.npc.dungeonnering;

import com.rs.game.WorldTile;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.skills.DungeoneeringFishing.Fish;
import com.rs.utils.Utils;

@SuppressWarnings("serial")
public class DungeonFishSpot extends DungeonNPC {

	private Fish fish;
	private int fishes;

	public DungeonFishSpot(int id, WorldTile tile, DungeonManager manager, Fish fish) {
		super(id, tile, manager, 1);
		this.fish = fish;
		setName(Utils.formatPlayerNameForDisplay(fish.toString()));
		fishes = 14;
	}

	@Override
	public void processNPC() {

	}

	public Fish getFish() {
		return fish;
	}

	public int desecreaseFishes() {
		return fishes--;
	}

	public void addFishes() {
		fishes += Utils.random(5, 10);
	}
}
