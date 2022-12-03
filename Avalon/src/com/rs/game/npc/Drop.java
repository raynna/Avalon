package com.rs.game.npc;

public class Drop {

	private int itemId, minAmount, maxAmount;
	private int rate;
	private String stringRate;
	public static int ALWAYS = 0, VERY_COMMON = 1, COMMON = 2, UNCOMMON = 3, RARE = 4, VERY_RARE = 5, SUPER_RARE = 6;

	public static Drop create(int itemId, int rate, int minAmount, int maxAmount) {
		return new Drop((short) itemId, rate, minAmount, maxAmount);
	}

	public Drop(int itemId, int rate, int minAmount, int maxAmount) {
		this.itemId = itemId;
		this.rate = rate;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
	}
	
	public Drop(int itemId, String stringRate, int minAmount, int maxAmount) {
		this.itemId = itemId;
		this.stringRate = stringRate;
		this.minAmount = minAmount;
		this.maxAmount = maxAmount;
	}

	public int getMinAmount() {
		return minAmount;
	}

	public int getExtraAmount() {
		return maxAmount - minAmount;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public int getItemId() {
		return itemId;
	}

	public int getRate() {
		return rate;
	}
	
	public String getStringRate() {
		return stringRate;
	}

	public void setItemId(short itemId) {
		this.itemId = itemId;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public void setMinAmount(int amount) {
		this.minAmount = amount;
	}

	public void setMaxAmount(int amount) {
		this.maxAmount = amount;
	}

}