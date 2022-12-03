package com.rs.game.npc.drops;

public class CachedMobRewardDisplayer {
	
	private int id, min, max, rarity;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getRarity() {
		return rarity;
	}

	public void setRarity(int rarity) {
		this.rarity = rarity;
	}

	public CachedMobRewardDisplayer(int id, int min, int max, int rarity) {
		super();
		this.id = id;
		this.min = min;
		this.max = max;
		this.rarity = rarity;
	}
	

}
