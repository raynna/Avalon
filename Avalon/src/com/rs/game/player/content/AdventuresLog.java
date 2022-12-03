package com.rs.game.player.content;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - 1. Rain
 * @Date - 30 Mar 2016
 * 
 */

public class AdventuresLog implements Serializable {

	private static final long serialVersionUID = 5806760877438727867L;

	/** The player. */
	private transient Player player;

	/** The Activities. */
	private List<String> Activities;

	/** The Activities Time. */
	private List<String> ActivitiesTime;

	/** The Activities Date. */
	private List<String> ActivitiesDate;

	/** The Privacy settings. */
	public int PrivacySettings;

	/** The replace index integer. */
	public int replaceIndex;

	/**
	 * Sets the player.
	 *
	 * @param player
	 * 
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * Instantiates a new adventures log.
	 */
	public AdventuresLog() {
		Activities = new ArrayList<String>();
		ActivitiesTime = new ArrayList<String>();
		ActivitiesDate = new ArrayList<String>();
	}

	/**
	 * Gets the activities.
	 *
	 * @return the activities
	 */
	public List<String> getActivities() {
		return Activities;
	}

	/**
	 * Gets the activities time.
	 *
	 * @return the activities time
	 */
	public List<String> getActivitiesTime() {
		return ActivitiesTime;
	}

	/**
	 * Gets the activities date.
	 *
	 * @return the activities date
	 */
	public List<String> getActivitiesDate() {
		return ActivitiesDate;
	}

	/**
	 * Adds the activity.
	 *
	 * @param Activity
	 *            the activity
	 */
	public void addActivity(String Activity) {
		if (replaceIndex > 0)
			replaceIndex++;
		Activities.add(replaceIndex, Activity);
		ActivitiesTime.add(replaceIndex, Utils.getCurrentTime());
		ActivitiesDate.add(replaceIndex, currentDate());
	}

	/**
	 * Open adventure log.
	 */
	public void OpenAdventureLog() {
		int dateLine = 4;
		int titleLine = 5;
		int timeLine = 6;
		int[] times = { 11, 16, 21, 26, 31, 36, 41, 46, 51, 56 };
		int[] cids = { 10, 15, 20, 25, 30, 35, 40, 45, 50, 55 };
		int[] title = { 9, 14, 19, 24, 29, 34, 39, 44, 49, 54 };
		player.getInterfaceManager().sendInterface(1158);
		player.getPackets().sendIComponentText(1158, 74, player.getUsername() + " - Adventure log");
		for (int cid : cids)
			player.getPackets().sendIComponentText(1158, cid, "");
		for (int titles : title)
			player.getPackets().sendIComponentText(1158, titles, "");
		for (int time : times)
			player.getPackets().sendIComponentText(1158, time, "");
		for (int i = 0; i < Activities.size(); i++) {
			dateLine += 5;
			timeLine += 5;
			titleLine += 5;
			player.getPackets().sendIComponentText(1158, titleLine, Activities.get(i));
			player.getPackets().sendIComponentText(1158, timeLine, ActivitiesTime.get(i));
			player.getPackets().sendIComponentText(1158, dateLine, ActivitiesDate.get(i));
		}

	}

	/**
	 * Open adventure log.
	 *
	 * @param other
	 *            the player
	 * @param username
	 *            the display name
	 */
	public void OpenAdventureLog(Player other, String username) {
		int Line = 5;
		int[] times = { 11, 16, 21, 26, 31, 36, 41, 46, 51, 56 };
		int[] cids = { 10, 15, 20, 25, 30, 35, 40, 45, 50, 55 };
		int[] title = { 9, 14, 19, 24, 29, 34, 39, 44, 49, 54 };
		boolean hasAdded = other.getFriendsIgnores().getFriends().contains(player.getUsername());
		if (other.getAdventureLog().PrivacySettings == 0) {

		} else if (other.getAdventureLog().PrivacySettings == 1
				&& !hasAdded) {
			player.getDialogueManager().startDialogue("SimpleMessage",
					"This player has their privacy settings set to friends only.");
			return;
		} else if (other.getAdventureLog().PrivacySettings == 2) {
			player.getDialogueManager().startDialogue("SimpleMessage",
					"This player has their privacy settings set to private.");
			return;
		}
		player.getInterfaceManager().sendInterface(1158);
		for (int cid : cids)
			player.getPackets().sendIComponentText(1158, cid, "");
		for (int titles : title)
			player.getPackets().sendIComponentText(1158, titles, "");
		for (int time : times)
			player.getPackets().sendIComponentText(1158, time, "");
		player.getPackets().sendIComponentText(1158, 74,
				Utils.formatPlayerNameForDisplay(username) + " - Adventure log");
		for (int i = 0; i < other.getAdventureLog().getActivities().size(); i++) {
			Line += 5;
			player.getPackets().sendIComponentText(1158, Line, other.getAdventureLog().getActivities().get(i));
		}
		for (int i = 0; i < other.getAdventureLog().getActivitiesTime().size(); i++) {
			Line += 1;
			player.getPackets().sendIComponentText(1158, Line, other.getAdventureLog().getActivitiesTime().get(i));
		}
		for (int i = 0; i < other.getAdventureLog().getActivitiesDate().size(); i++) {
			Line += 2;
			player.getPackets().sendIComponentText(1158, Line, other.getAdventureLog().getActivitiesDate().get(i));
		}

	}

	/**
	 * Current time.
	 *
	 * @return the string
	 */
	public String currentDate() {
		Calendar now = Calendar.getInstance();
		return Utils.getDay() + " " + Utils.getMonth(now.get(Calendar.MONTH) + 1);
	}
}
