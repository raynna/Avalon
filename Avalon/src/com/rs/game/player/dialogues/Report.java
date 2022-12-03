package com.rs.game.player.dialogues;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.rs.game.player.Player;
import com.rs.game.player.content.ReportAbuse;
import com.rs.utils.Utils;

public class Report extends Dialogue {

	public static String now(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}

	public static String category = " ", bug = " ";

	public static void archiveBug(Player player) {
		Calendar now = Calendar.getInstance();
		//Mail.SendMail(player, category + " - " + player.getUsername(), player.getUsername()
		//		+ " has submitted a bug. Please review it in the bug report folder (data/reports/bug/playername.txt)");
		try {
			String location = "data/reports/bug/" + player.getUsername() + ".txt";
			BufferedWriter writer = new BufferedWriter(new FileWriter(location, true));
			writer.write("Date: " + Utils.getDay() + " " + Utils.getMonth(now.get(Calendar.MONTH) + 1));
			writer.newLine();
			writer.write("Category: " + category);
			writer.newLine();
			writer.write("Description: " + bug);
			writer.newLine();
			writer.write(player.getAttackedBy() != null ? "Combat: Yes" : "Combat: No");
			writer.newLine();
			writer.write(player.getControlerManager().getControler() != null
					? "Controler: " + player.getControlerManager().getControler().getClass().getCanonicalName()
					: "Controler: No.");
			writer.newLine();
			writer.write("Coords: " + player.getX() + ", " + player.getY() + ", " + player.getPlane() + " and "
					+ player.getRegionId());
			writer.newLine();
			writer.newLine();
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void start() {
		sendOptionsDialogue(TITLE, "Report a player", "Report a bug");
		stage = 1;

	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case 1:
			switch (componentId) {
			case OPTION_1:
				ReportAbuse.open(player);
				end();
				break;
			case OPTION_2:
				player.getPackets().sendInputLongTextScript("Category of issue:");
				player.temporaryAttribute().put("report_category", Boolean.TRUE);
				end();
			}
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
