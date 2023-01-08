package com.rs.game.player.content;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.utils.Logger;
import com.rs.utils.Utils;

/**
 *
 * @Author Tristam <Hassan>
 * 
 */

public class ReportAbuse {

	public static void open(Player player) {
		if (player.isInCombat(10000)) {
			player.sm("You can't open report abuse under attack.");
			return;
		}
		if (player.isLocked()) {
			player.sm("You are busy. Please finish with what you are doing.");
			return;
		}
		if (player.getInterfaceManager().containsScreenInter()) {
			player.sm("Close your interface to open Report Abuse.");
			return;
		}
		player.getInterfaceManager().sendInterface(594);
		player.getPackets().sendIComponentText(594, 68, "When you report someone, it will be reviewed by the staff of "
				+ Settings.SERVER_NAME + ". <col=ff0000>Please do not misuse this system.");
		player.getPackets().sendHideIComponent(594, 72, true);
		ShowButton(player);
	}
	
	public static void Report(Player player, String reported, int rule, boolean applyMute) {
		Player offender = World.getPlayerByDisplayName(reported);
		if (offender == null || !offender.isActive() || !offender.hasStarted() || player == offender) {
			return;
		}
		if (offender.isDeveloper()) {
			player.sm(
					"You can't report this player, since they are an admin (Gold Crown). Submit a staff report on the forums if you think, the offender are abusing their powers.");
			return;
		}
		if (applyMute) {
			offender.mute(player.getDisplayName(), " - ", 2);
		}
		Logger.log("Report", player.getDisplayName() + " reported " + reported);
		player.sm("Thank-you, your Report Abuse has been received.");
		SaveReport(player, reported, rule);
	}

	public static void SaveReport(Player player, String reported, int rule) {
		Player offender = World.getPlayerByDisplayName(reported);
		Calendar instance = Calendar.getInstance();
		String save = "data/reports/ " + Utils.formatPlayerNameForDisplay(reported) + ".txt";
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(save, true));
			writer.write("Date: " + Utils.getDay() + " " + Utils.getMonth(instance.get(Calendar.MONTH) + 1) + " "
					+ Utils.getCurrentTime2());
			writer.newLine();
			writer.write("Reported by: " + player.getUsername() + " (" + player.getSession().getIP() + ") ("
					+ player.getPlayerRank().getRankNames() + ") ~ " + "Mute?: " + (offender.isMuted() ? "Yes." : "No."));
			writer.newLine();
			writer.write("Reported for: " + getRule(rule));
			writer.newLine();
			writer.write("Coords: " + offender.getX() + " " + offender.getY() + " " + offender.getRegionId());
			writer.newLine();
			writer.write("In controler?: " + (offender.getControlerManager().getControler() != null
					? offender.getControlerManager().getControler().getClass().getSimpleName() : "No."));
			writer.newLine();
			writer.write("In combat?: " + (offender.isInCombat(10000) ? "Yes." : "No."));
			writer.newLine();
			writer.write("Locked?: " + (offender.isLocked() ? "Yes." : "No."));
			writer.newLine();
			writer.newLine();
			writer.flush();
			writer.close();
		} catch (IOException e) {
			System.err.print("Could not save report.");
		}
	}

	public static boolean ShowButton(Player player) {
		if (player.isDeveloper() || player.isModerator()) {
			player.getPackets().sendHideIComponent(594, 8, false);
			return true;
		}
		return false;

	}

	public static String getRule(int rule) {
		switch (rule) {
		case 4:
			return "Exploiting a bug";
		case 5:
			return "Staff impersonation";
		case 6:
			return "Buying or selling account";
		case 7:
			return "Macroing / use of bots";
		case 9:
			return "Encouraging rule breaking";
		case 11:
			return "Advertising websites";
		case 13:
			return "Asking for real life information";
		case 15:
			return "Scamming";
		case 16:
			return "Seriously offensive language";
		case 17:
			return "Soliciation";
		case 18:
			return "Disruptive behaviour";
		case 19:
			return "Offensive account name";
		case 20:
			return "Real life threats";
		case 21:
			return "Breaking real world law(s)";
		}
		return null;
	}

}