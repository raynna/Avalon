package com.rs.game.player;

import java.io.Serializable;

public class GodwarsKillcount implements Serializable {

	private static final long serialVersionUID = -3935672307271551069L;

	@SuppressWarnings("unused")
	private transient Player player;

	public int[] godwarsKillcount;

	public GodwarsKillcount() {
		godwarsKillcount = new int[4];
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getArmadylKillcount() {
		return godwarsKillcount[0];
	}

	public int getBandosKillcount() {
		return godwarsKillcount[1];
	}

	public int getSaradominKillcount() {
		return godwarsKillcount[2];
	}

	public int getZamorakKillcount() {
		return godwarsKillcount[3];
	}

	public int getKillcount(int id) {
		return godwarsKillcount[id];
	}

	public void removeKillcount(int id, int amount) {
		godwarsKillcount[id] -= amount;
	}

	public void addKillCount(int id) {
		godwarsKillcount[id]++;
	}
}