package com.rs.utils;

import java.io.File;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;

import com.rs.game.player.Player;

public final class KillTables implements Serializable {

	private static final long serialVersionUID = 5403480618483552509L;

	private String username;
	private int kills, deaths;

	private static KillTables[] ranks;

	private static final String PATH = "data/killTables.ser";

	public KillTables(Player player) {
		this.username = player.getUsername();
		this.kills = player.getKillCount();
		this.deaths = player.getDeathCount();
	}

	public static void init() {
		File file = new File(PATH);
		if (file.exists())
			try {
				ranks = (KillTables[]) SerializableFilesManager.loadSerializedFile(file);
				return;
			} catch (Throwable e) {
				Logger.handle(e);
			}
		ranks = new KillTables[300];
	}

	public static final void save() {
		try {
			SerializableFilesManager.storeSerializableClass(ranks, new File(PATH));
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}

	public static void showRanks(Player player) {
		for (int i = 10; i < 310; i++)
			player.getPackets().sendIComponentText(275, i, "");
		for (int i = 0; i < 300; i++) {
			if (ranks[i] == null)
				break;
			String text;
			if (i >= 0 && i <= 2)
				text = "<col=ff9900>";
			else if (i <= 9)
				text = "<col=ff0000>";
			else if (i <= 49)
				text = "<col=38610B>";
			else
				text = "<col=000000>";
			player.getPackets().sendIComponentText(275, i + 10,
					text + "Top " + (i + 1) + " - " + Utils.formatPlayerNameForDisplay(ranks[i].username) + " - kills: "
							+ ranks[i].kills + " - deaths: " + ranks[i].deaths);
		}
		player.getPackets().sendIComponentText(275, 1, "Player Killing Ranks Table");
		player.getInterfaceManager().sendInterface(275);
	}

	public static void sort() {
		Arrays.sort(ranks, new Comparator<KillTables>() {
			@Override
			public int compare(KillTables arg0, KillTables arg1) {
				if (arg0 == null)
					return 1;
				if (arg1 == null)
					return -1;
				if (arg0.kills < arg1.kills)
					return 1;
				else if (arg0.kills > arg1.kills)
					return -1;
				else
					return 0;
			}

		});
	}

	public static void checkRank(Player player) {
		int kills = player.getKillCount();
		for (int i = 0; i < ranks.length; i++) {
			KillTables rank = ranks[i];
			if (rank == null)
				break;
			if (rank.username.equalsIgnoreCase(player.getUsername())) {
				ranks[i] = new KillTables(player);
				sort();
				return;
			}
		}
		for (int i = 0; i < ranks.length; i++) {
			KillTables rank = ranks[i];
			if (rank == null) {
				ranks[i] = new KillTables(player);
				sort();
				return;
			}
		}
		for (int i = 0; i < ranks.length; i++) {
			if (ranks[i].kills < kills) {
				ranks[i] = new KillTables(player);
				sort();
				return;
			}
		}
	}

}