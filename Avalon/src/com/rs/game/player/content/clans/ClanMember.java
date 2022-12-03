package com.rs.game.player.content.clans;

import java.io.Serializable;

import com.rs.utils.Utils;

public class ClanMember implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1203833687881050886L;

	private String username;
	private int rank;
	private int job;
	private boolean banFromCitadel;
	private boolean banFromIsland;
	private boolean banFromKeep;
	private long joinDate;

	public ClanMember(String username, int rank) {
		this.username = username;
		this.rank = rank;
		joinDate = Utils.currentTimeMillis();
	}

	public int getRank() {
		return rank;
	}

	public String getUsername() {
		return username;
	}

	public int getJob() {
		return job;
	}

	public void setJob(int job) {
		this.job = job;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public boolean isBanFromCitadel() {
		return banFromCitadel;
	}

	public void setBanFromCitadel(boolean banFromCitadel) {
		this.banFromCitadel = banFromCitadel;
	}

	public boolean isBanFromKeep() {
		return banFromKeep;
	}

	public void setBanFromKeep(boolean banFromKeep) {
		this.banFromKeep = banFromKeep;
	}

	public boolean isBanFromIsland() {
		return banFromIsland;
	}

	public void setBanFromIsland(boolean banFromIsland) {
		this.banFromIsland = banFromIsland;
	}

	public boolean firstWeek() {
		return Utils.currentTimeMillis() - joinDate < 7 * 24 * 60 * 60 * 1000;
	}

}
