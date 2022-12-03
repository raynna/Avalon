package com.rs.game.player.content.dungeoneering;

import java.util.concurrent.CopyOnWriteArrayList;

import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public final class DungeonPartyManager {

	private String leader; // username
	private int floor;
	private int complexity;
	private int size;
	private int dificulty;
	private boolean guideMode;
	private int keyType;
	private Long customseed;
	private boolean customseeddlg;

	private transient CopyOnWriteArrayList<Player> team;
	private DungeonManager dungeon;

	public DungeonPartyManager() {
		team = new CopyOnWriteArrayList<Player>();
	}

	public void setDefaults() {
		floor = 1;
		complexity = 6;
		dificulty = team.size();
		guideMode = true;
	}

	public void leaveParty(Player player, boolean logout) {
		if (dungeon != null)
			dungeon.exitDungeon(player, logout);
		else {
			player.stopAll();
			remove(player, logout);
		}
	}

	public void remove(Player player, boolean logout) {
		synchronized (this) {
			team.remove(player);
			player.getDungManager().setParty(null);
			player.getDungManager().expireInvitation();
			player.getDungManager().refreshPartyDetailsComponents();
			player.getPackets().sendGameMessage("You leave the party.");
			if (dungeon != null && team.size() == 0) {
				if (dungeon.hasLoadedNoRewardScreen() && logout) //destroy timer cant exist with a party member on anyway, team must be 0
					dungeon.setDestroyTimer();
				else
					dungeon.destroy();
			} else {
				for (Player p2 : team)
					p2.getPackets().sendGameMessage(player.getDisplayName() + " has left the party.");
				if (isLeader(player) && team.size() > 0)
					setLeader(team.get(0));
				for (Player p2 : team)
					refreshPartyDetails(p2);
			}
		}
	}

	public void refreshPartyDetails(Player player) {
		player.getDungManager().refreshPartyDetailsComponents();
		player.getDungManager().refreshNames();
	}

	public void add(Player player) {
		synchronized (this) {
			for (Player p2 : team)
				p2.getPackets().sendGameMessage(player.getDisplayName() + " has joined the party.");
			team.add(player);
			player.getDungManager().setParty(this);
			if (team.size() == 1) {
				setLeader(player);
				if (dungeon != null)
					dungeon.endDestroyTimer();
			} else
				player.getPackets().sendGameMessage("You join the party.");
			for (Player p2 : team)
				refreshPartyDetails(p2);
		}
	}

	public boolean isLeader(Player player) {
		return player.getUsername().equals(leader);
	}

	public void setLeader(Player player) {
		leader = player.getUsername();
		if (team.size() > 1) {
			Player positionZero = team.get(0);
			team.set(0, player);
			team.remove(player);
			team.add(positionZero);
		}
		player.getPackets().sendGameMessage("You have been set as the party leader.");
	}

	public void lockParty() {
		for (Player player : team) {
			player.stopAll();
			player.lock();
		}
	}

	public void start(int hsType) {
		synchronized (this) {
			if (dungeon != null)
				return;
			if (customseed != null)
				dungeon = new DungeonManager(this, customseed.longValue());
			else
				dungeon = new DungeonManager(this);
			dungeon.setHsType(hsType);
		}
	}
	
	

	public int getComplexity() {
		return complexity;
	}

	public void setComplexity(int complexity) {
		this.complexity = complexity;
		for (Player player : team)
			player.getDungManager().refreshComplexity();
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setDifficulty(int dificulty) {
		if (dificulty > team.size())
			dificulty = team.size();
		this.dificulty = dificulty;
	}

	public int getFloor() {
		return floor;
	}

	public String getLeader() {
		return leader;
	}

	public Player getLeaderPlayer() {
		for (Player player : team)
			if (player.getUsername().equals(leader))
				return player;
		return null;
	}

	public Player getGateStonePlayer() {
		for (Player player : team)
			if (player.getInventory().containsItem(DungeonConstants.GROUP_GATESTONE, 1))
				return player;
		return null;
	}

	public void setFloor(int floor) {
		this.floor = floor;
		for (Player player : team)
			player.getDungManager().refreshFloor();
	}

	public int getFloorType() {
		return DungeonUtils.getFloorType(floor);
	}

	public int getDungeoneeringLevel() {
		int level = 120;
		for (Player player : team) {
			int playerLevel = player.getSkills().getLevelForXp(Skills.DUNGEONEERING);
			if (playerLevel < level)
				level = playerLevel;
		}
		return level;
	}

	public double getLevelDiferencePenalty(Player player) {
		int average = getAverageCombatLevel();
		int cb = player.getSkills().getCombatLevelWithSummoning();
		double diff = Math.abs(cb - average);
		return  (diff > 50 ? ((diff - 50)*0.01) : 0);
		
	}
	
	public int getMaxLevelDiference() {
		if (team.size() <= 1)
			return 0;
		int maxLevel = 0;
		int minLevel = 138;
		for (Player player : team) {
			int level = player.getSkills().getCombatLevelWithSummoning();
			if (maxLevel < level)
				maxLevel = level;
			if (minLevel > level)
				minLevel = level;
		}
		return Math.abs(maxLevel - minLevel);
	}

	public DungeonManager getDungeon() {
		return dungeon;
	}

	public int getMaxFloor() {
		int floor = 60;
		for (Player player : team) {
			if (player.getDungManager().getMaxFloor() < floor)
				floor = player.getDungManager().getMaxFloor();
		}
		return floor;
	}

	public int getMaxComplexity() {
		int complexity = 6;
		for (Player player : team) {
			if (player.getDungManager().getMaxComplexity() < complexity)
				complexity = player.getDungManager().getMaxComplexity();
		}
		return complexity;
	}

	public int getCombatLevel() {
		int level = 0;
		for (Player player : team)
			level += player.getSkills().getCombatLevelWithSummoning();
		return team.size() == 0 ? 138 : level;
	}

	public int getAverageCombatLevel() {
		if (team.size() == 0)
			return 138;
		int level = 0;
		for (Player player : team)
			level += player.getSkills().getCombatLevelWithSummoning();
		return level / team.size();
	}

	public int getDefenceLevel() {
		if (team.size() == 0)
			return 99;
		int level = 0;
		for (Player player : team)
			level += player.getSkills().getLevelForXp(Skills.DEFENCE);
		return level / team.size();
	}

	public double getDificultyRatio() {
		if (dificulty > team.size())
			return 1;
		return 0.7 + (((double) dificulty / (double) team.size()) * 0.3);
	}

	public int getMaxLevel(int skill) {
		if (team.size() == 0)
			return 1;
		int level = 0;
		for (Player player : team) {
			int lvl = player.getSkills().getLevelForXp(skill);
			if (lvl > level)
				level = lvl;
		}
		return level;
	}

	public int getAttackLevel() {
		if (team.size() == 0)
			return 99;
		int level = 0;
		for (Player player : team)
			level += player.getSkills().getLevelForXp(Skills.ATTACK);
		return level / team.size();
	}

	public int getMagicLevel() {
		if (team.size() == 0)
			return 99;
		int level = 0;
		for (Player player : team)
			level += player.getSkills().getLevelForXp(Skills.MAGIC);
		return level / team.size();
	}

	public int getRangeLevel() {
		if (team.size() == 0)
			return 99;
		int level = 0;
		for (Player player : team)
			level += player.getSkills().getLevelForXp(Skills.RANGE);
		return level / team.size();
	}

	public CopyOnWriteArrayList<Player> getTeam() {
		return team;
	}

	public int getSize() {
		return size;
	}

	public int getIndex(Player player) {
		int index = 0;
		for (Player p2 : team) {
			if (p2 == player)
				return index;
			index++;
		}
		return 0;
	}

	public int getDificulty() {
		if (dificulty > team.size())
			dificulty = team.size();
		return dificulty;
	}

	public boolean isGuideMode() {
		return guideMode || complexity <= 4;
	}

	/*
	 * dont use for dung itself
	 */

	public boolean getGuideMode() {
		return guideMode;
	}

	public void setGuideMode(boolean guideMode) {
		this.guideMode = guideMode;
	}

	public boolean isKeyShare() {
		return keyType == 1;
	}

	public void setKeyShare(boolean isKeyShare) {
		this.keyType = isKeyShare ? 1 : 2;
	}

	public int getKeyType() {
		return keyType;
	}
	
	public boolean hasShownCustomSeedDialog() {
		return customseeddlg;
	}
	
	public void setHasShownCustomSeedDialog(boolean has) {
		customseeddlg = has;
	}
	
	public Long getCustomSeed() {
		return customseed;
	}
	
	public void setCustomSeed(Long seed) {
		this.customseed = seed;
	}
}
