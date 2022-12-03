package com.rs.game.player;

import java.io.Serializable;

import com.rs.game.World;
import com.rs.game.player.Ranks.Rank;

/**
 * @author -Andreas 5 feb. 2020 16:09:56
 * @project 1. Avalon
 * 
 */

public class PlayerRank implements Serializable {

	private static final long serialVersionUID = -773408484495708325L;

	private transient Player player;
	private Rank[] rank;

	public PlayerRank() {
		if (rank == null)
			rank = new Rank[3];
		rank[0] = Rank.PLAYER;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Rank[] getRank() {
		return this.rank;
	}

	public void setRank(int index, Rank rank) {
		this.rank[index] = rank;
		if (this.rank[index] != Rank.DEVELOPER && this.rank[index] != Rank.MODERATOR)
			World.sendNewsMessage(player.getDisplayName() + " is now a " + rank.getRankName() + "!", false);
	}

	public void addRank(Rank rank) {
		if (rank == Rank.IRONMAN || rank == Rank.HARDCORE_IRONMAN) {
			setRank(2, rank);
			return;
		}
		if (rank.getRankName().toLowerCase().contains("donator")) {
			setRank(1, rank);
			return;
		}
		setRank(0, rank);
	}

	public String getRankNames() {
		StringBuffer names = new StringBuffer();
		for (Rank ranks : rank) {
			if (ranks == null)
				continue;
			names.append(ranks.getRankName()).append(", ");
		}
		return names.replace(names.length() - 2, names.length(), "").toString();
	}

	public String getRankName(int index) {
		Rank currentRank = rank[index];
		if (currentRank == null)
			return null;
		return currentRank.getRankName();
	}

	public boolean isDonator() {
		for (Rank ranks : rank) {
			if (ranks == null)
				continue;
			if (ranks.getRankName().toLowerCase().contains("donator"))
				return true;
		}
		return false;
	}

	public boolean isIronman() {
		return rank[2] == Rank.IRONMAN || rank[2] == Rank.HARDCORE_IRONMAN;
	}

	public boolean isHardcore() {
		return rank[2] == Rank.HARDCORE_IRONMAN;
	}

	public boolean isStaff() {
		Rank rank = getRank()[0];
		return rank != Rank.PLAYER && rank != Rank.YOUTUBER;
	}

	public String getTitle() {
		for (Rank rank : rank) {
			if (rank.getTitle() != null)
				return rank.getTitle();
		}
		return null;
	}
}
