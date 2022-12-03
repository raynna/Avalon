package com.rs.game.player.content;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.rs.game.player.Player;
import com.rs.game.player.controlers.WildernessControler;
import com.rs.utils.Utils;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - 1. Rain
 * @Date - 21 Apr 2016 Credits to Rome for the base.
 * 
 */

public class WildernessKills {

	/** The Kills. */
	private static List<String> Kills = new ArrayList<String>();

	/** The Constant File_Path. */
	private static final String File_Path = "data/wilderness/recentkills.txt";

	public static String getText(Player killer, Player victim) {
		Calendar now = Calendar.getInstance();
		String highestCB = null;
		int kCB = killer.getSkills().getCombatLevelWithSummoning();
		int vCB = victim.getSkills().getCombatLevelWithSummoning();
		String date = "<col=990000>" + Utils.getDay() + " " + Utils.getMonth(now.get(Calendar.MONTH + 1)) + "</col> - ";
		highestCB = date + "<img=" + killer.getRights() + ">" + killer.getDisplayName() + "("
				+ (kCB > vCB ? "<col=990000>" + kCB : "<col=990000>" + vCB) + "</col>)<col=ffffff>killed</col> "
				+ "<img=" + victim.getRights() + ">" + victim.getDisplayName() + "("
				+ (kCB > vCB ? "<col=990000>" + kCB : "<col=990000>" + vCB) + " at level <col=990000>"
				+ WildernessControler.getWildLevel(killer) + "</col> wilderness.";
		return highestCB;
	}

	/**
	 * Adds the kill.
	 *
	 * @param killer
	 *            the killer
	 * @param victim
	 *            the victim
	 */
	public static void addKill(Player killer, Player victim) {
		BufferedReader reader = null;
		BufferedWriter writer = null;
		if (killer == victim) {
			return;
		}
		if (killer == null || victim == null) {
			return;
		}
		try {
			reader = new BufferedReader(new FileReader(File_Path));
			String line;
			while ((line = reader.readLine()) != null)
				Kills.add(line);
			reader.close();
			Kills.add(0, getText(killer, victim));
			writer = new BufferedWriter(new FileWriter(File_Path));
			for (String list : Kills)
				writer.write(list + "\r\n");
		} catch (Exception e) {
			System.err.print("The killer " + killer.getDisplayName() + " and the victim " + victim.getDisplayName()
					+ " could not be added to the list due to some exception.");
			e.printStackTrace();
		} finally {
			assert reader != null;
			assert writer != null;
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {

			}

		}
	}

	/**
	 * Load kills.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void loadKills() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(File_Path));
		String line;
		Kills.clear();
		while ((line = br.readLine()) != null) {
			Kills.add(line);
		}
		br.close();
	}

	/**
	 * Display kills.
	 *
	 * @param player
	 *            the player
	 */
	public static void DisplayKills(Player player) {
		int StartLine = 12;
		try {
			loadKills();
		} catch (IOException e) {
			return;
		}
		player.getInterfaceManager().sendInterface(275);
		for (int i = 0; i < 100; i++)
			player.getPackets().sendIComponentText(275, i, "");
		player.getPackets().sendIComponentText(275, 1, "Recent Wilderness Kills");
		if (Kills.size() == 0) {
			player.getPackets().sendIComponentText(275, 15, "<col=990000>There have been no recent kills.");
			return;
		}
		player.getPackets().sendIComponentText(275, 10,
				player.hasWildstalker()
						? "<col=990000>Your kill/death ratio: " + player.getKillCount() + "/" + player.getDeathCount()
						: "Claim wildstalker helmet to view KDR.");
		for (int i = 0; i < Kills.size(); i++) {
			StartLine++;
			player.getPackets().sendIComponentText(275, StartLine, Kills.get(i));
		}
	}

}
