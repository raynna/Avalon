package com.rs.game.player.content;

import java.io.File;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;

import com.rs.game.player.Player;
import com.rs.utils.Logger;
import com.rs.utils.SerializableFilesManager;
import com.rs.utils.Utils;

public final class KillScoreBoard implements Serializable {

	private static final long serialVersionUID = 5403480618483552509L;

	private String username;
	private double kills;
	private double deaths;
	private double kdr;

	private static KillScoreBoard[] ranks;

	private static final String PATH = "data/scoreboard/kills.ser";

	public KillScoreBoard(Player player) {
		this.username = player.getUsername();
		this.kills = player.getKillCount();
		this.deaths = player.getDeathCount();
		this.kdr = kills / deaths;
	}

	public static void showRanks(Player player) {
		player.getInterfaceManager().sendInterface(1158);
		int count = 0;
		player.getPackets().sendIComponentText(1158, 74, "Top 10 Scoreboard");
		boolean rankOne = true;
		for (int i = 9; i < 5 * 11; i += 5)
		player.getPackets().sendIComponentText(1158, i, "");
		for (int i = 10; i < 5 * 12; i += 5)
			player.getPackets().sendIComponentText(1158, i, "");
		for (int i = 11; i < 5 * 13; i += 5)
			player.getPackets().sendIComponentText(1158, i, "");
		for (KillScoreBoard rank : ranks) {
			if (rank == null)
				return;
			double dr = rank.kdr;
			player.getPackets().sendIComponentText(1158, 9 + count * 5, (rankOne ? "<col=F6A90E>" : "") +Utils.formatPlayerNameForDisplay(rank.username));
			player.getPackets().sendIComponentText(1158, 10 + count * 5, (rankOne ? "<col=F6A90E>" : "") +
					"K/D: " + new DecimalFormat("##.00").format(dr).replace(',', '.') + "                   K: " + new DecimalFormat("0000.#").format(rank.kills) + "                 D: " + new DecimalFormat("0000.#").format(rank.deaths));
			//player.getPackets().sendIComponentText(1158, 11 + count * 5,
			//		"K: " + kills + "<br>D: " + deaths);
			rankOne = false;
			count++;
		}
	}

	public static void init() {
		File file = new File(PATH);
		if (file.exists())
			try {
				ranks = (KillScoreBoard[]) SerializableFilesManager.loadSerializedFile(file);
				return;
			} catch (Throwable e) {
				Logger.handle(e);
			}
		ranks = new KillScoreBoard[10];
	}

	public static final void save() {
		try {
			SerializableFilesManager.storeSerializableClass(ranks, new File(PATH));
		} catch (Throwable e) {
			Logger.handle(e);
		}
	}

	public static void sort() {
		Arrays.sort(ranks, new Comparator<KillScoreBoard>() {
			@Override
			public int compare(KillScoreBoard arg0, KillScoreBoard arg1) {
				if (arg0 == null)
					return 1;
				if (arg1 == null)
					return -1;
				if (arg0.kdr < arg1.kdr)
					return 1;
				else if (arg0.kdr > arg1.kdr)
					return -1;
				else
					return 0;
			}

		});
	}

	public static void checkRank(Player player) {
		//if (player.getRights() == 2)
		//	return;
		for (int i = 0; i < ranks.length; i++) {
			KillScoreBoard rank = ranks[i];
			if (rank == null)
				break;
			if (rank.username.equalsIgnoreCase(player.getUsername())) {
				ranks[i] = new KillScoreBoard(player);
				sort();
				return;
			}
		}
		for (int i = 0; i < ranks.length; i++) {
			KillScoreBoard rank = ranks[i];
			if (rank == null) {
				ranks[i] = new KillScoreBoard(player);
				sort();
				return;
			}
		}
	}

}
