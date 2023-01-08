package com.rs.game.player;

import com.rs.Settings;
import com.rs.game.World;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - 1. Rain
 * @Date - 17 Apr 2016
 * 
 *       This is only for test purposes.
 */

public class AssistManager {

	/** The player. */
	private transient Player player;

	/** The Constant SKILL_ORDER. */
	private static final int[] SKILL_ORDER = new int[] { Skills.RUNECRAFTING, Skills.CRAFTING, Skills.FLETCHING,
			Skills.MAGIC, Skills.SMITHING, Skills.COOKING, Skills.HERBLORE };

	/** The real levels. */
	private int realLevels;

	/** The assisted. */
	private boolean assisted;
	
	public boolean isAssisted() {
		return assisted;
	}

	/** The assisting. */
	public boolean assisting;
	
	public boolean isAssisting() {
		return assisting;
	}

	/** The ros. */
	public boolean ROS;

	/** The Assisting who. */
	public String AssistingWho;

	/** The Assisted by. */
	public String AssistedBy;

	/** The Total xp gained. */
	public int TotalXPGained;

	/**
	 * Instantiates a new assist manager.
	 *
	 * @param assister
	 *            the assister
	 */
	public AssistManager(Player assister) {
		player = assister;
	}

	/**
	 * Assist.
	 *
	 * @param p2
	 *            the p2
	 */
	public void Assist(Player p2) {
		player.sm("Currently disabled.");
		return;
		/*
		 * if (player.isLocked() || player.getControlerManager().getControler()
		 * != null || player.isInCombat(10000) || player.isInLiveChat ||
		 * AntiBot.getInstance().hasEvent ||
		 * player.getInterfaceManager().containsScreenInter()) { player.sm(
		 * "You are busy."); if (AssistingWho != null)
		 * player.getAssist().Reset(p2); return; } player.faceEntity(p2);
		 * p2.faceEntity(player); AssistingWho = p2.getDisplayName();
		 * p2.getAssist().AssistedBy = player.getDisplayName(); isAssisting =
		 * true; p2.getAssist().isAssisted = true; player.setNextAnimation(new
		 * Animation(7299)); player.setNextGraphics(new Graphics(1247));
		 * Actions(p2); CoresManager.slowExecutor.schedule(new Runnable() {
		 * 
		 * @Override public void run() { p2.setNextAnimation(new
		 * Animation(7299)); } }, 450, TimeUnit.MILLISECONDS); Open();
		 */

	}

	/**
	 * Actions.
	 *
	 * @param p2
	 *            the p2
	 */
	public void Actions(Player p2) {
		for (int skill : SKILL_ORDER) {
			int p2Skills = p2.getSkills().getLevel(skill);
			int getBoostedLevels = player.getSkills().getLevel(skill);
			setRealLevels(p2Skills);
			Freeze(p2);
			p2.getSkills().set(skill, getBoostedLevels);
			p2.getSkills().setXp(skill, Skills.getXPForLevel(getBoostedLevels));
		}

	}

	/**
	 * Give exp.
	 *
	 * @param player
	 *            the player
	 * @param skill
	 *            the skill
	 * @param exp
	 *            the exp
	 */
	public static void giveXP(Player player, int skill, double exp) {
		Player assister = World.getPlayerByDisplayName(player.getAssist().AssistedBy);
		if (assister == null || !assister.isActive()) {
			player.sm(assister + " is offline.");
			player.getAssist().Reset(assister);
			player.closeInterfaces();
			return;
		}
		assister.getSkills().addXp(skill, exp);
		assister.getAssist().addXP(exp);
		assister.getAssist().Refresh();
	}

	/**
	 * Check.
	 *
	 * @return true, if successful
	 */
	public boolean Check() {
		Player p2 = World.getPlayerByDisplayName(AssistingWho);
		if (!player.getInterfaceManager().containsInterface(301)) {
			player.getAssist().Reset(p2);
			return true;
		}
		return false;
	}

	/**
	 * Check ros.
	 *
	 * @return true, if successful
	 */
	public boolean CheckROS() {
		Player p2 = World.getPlayerByDisplayName(AssistedBy);
		if (!ROS && AssistedBy != null || assisted) {
			player.sm("Your stats has been restored.");
			Reset(p2);
			return true;
		}
		return false;
	}

	/**
	 * Freeze.
	 *
	 * @param p2
	 *            the p2
	 */
	public void Freeze(Player p2) {
		player.setFreezeDelay(Integer.MAX_VALUE);
		p2.setFreezeDelay(Integer.MAX_VALUE);
	}

	/**
	 * Removes the freeze.
	 *
	 * @param p2
	 *            the p2
	 */
	public void RemoveFreeze(Player p2) {
		player.setFreezeDelay(0);
		p2.setFreezeDelay(0);
	}

	/**
	 * Reset.
	 *
	 * @param p2
	 *            the p2
	 */
	public void Reset(Player p2) {
		player.sm("You stopped assisting " + AssistingWho);
		p2.sm(p2.getAssist().AssistedBy + " has stopped assisting you.");
		for (int skill : SKILL_ORDER) {
			p2.getSkills().set(skill, getRealLevels());
			p2.getSkills().setXp(skill, Skills.getXPForLevel(getRealLevels()));
			p2.getSkills().refresh(skill);
			p2.getAssist().ROS = true;
		}
		player.closeInterfaces();
		TotalXPGained = 0;
		RemoveFreeze(p2);
		assisting = false;
		p2.getAssist().assisted = false;
		AssistingWho = null;
		p2.getAssist().AssistedBy = null;
		p2.getAssist().ROS = false;
	}

	/**
	 * Open.
	 */
	public void Open() {
		player.getInterfaceManager().sendInterface(301);
		player.getPackets().sendIComponentText(301, 73,
				"Assist System XP Display - You are assisting " + AssistingWho + ".");
		player.getPackets().sendHideIComponent(301, 10, true);
		Refresh();
	}

	/**
	 * Refresh.
	 */
	public void Refresh() {
		player.getPackets().sendConfig(1087, Integer.MAX_VALUE);
		player.getPackets().sendIComponentText(301, 84, String.valueOf(TotalXPGained));
	}

	/**
	 * Sets the real levels.
	 *
	 * @param levels
	 *            the levels
	 * @return the int
	 */
	public int setRealLevels(int levels) {
		return realLevels = levels;
	}

	/**
	 * Gets the real levels.
	 *
	 * @return the real levels
	 */
	public int getRealLevels() {
		return realLevels;
	}

	/**
	 * Adds the xp.
	 *
	 * @param XP
	 *            the xp
	 * @return the int
	 */
	public int addXP(double XP) {
		return TotalXPGained += XP * Settings.SKILLING_XP_RATE;
	}

}
