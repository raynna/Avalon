package com.rs.game.player.content;

import com.rs.game.World;
import com.rs.game.player.Player;

public class Skulls {

	public static void checkSkulls(Player player) {
		if (player.getKillCount() < 50) {
			player.skullId = 0;
			player.getAppearence().generateAppearenceData();
		}
		if (player.getKillCount() >= 50 && player.getKillCount() < 100 && player.skullId != 1) {
			player.skullId = 1;
			player.getAppearence().generateAppearenceData();
			World.sendWorldMessage("<img=7><col=36648b>News: " + player.getDisplayName() + " has achieved "
					+ player.getKillCount() + " kills.", false);
		}
		if (player.getKillCount() >= 100 && player.getKillCount() < 250 && player.skullId != 6) {
			player.skullId = 6;
			player.getAppearence().generateAppearenceData();
			World.sendWorldMessage("<img=7><col=36648b>News: " + player.getDisplayName() + " has achieved "
					+ player.getKillCount() + " kills.", false);
		}
		if (player.getKillCount() >= 250 && player.getKillCount() < 500 && player.skullId != 5) {
			player.skullId = 5;
			player.getAppearence().generateAppearenceData();
			World.sendWorldMessage("<img=7><col=36648b>News: " + player.getDisplayName() + " has achieved "
					+ player.getKillCount() + " kills.", false);
		}
		if (player.getKillCount() >= 500 && player.getKillCount() < 750 && player.skullId != 4) {
			player.skullId = 4;
			player.getAppearence().generateAppearenceData();
			World.sendWorldMessage("<img=7><col=36648b>News: " + player.getDisplayName() + " has achieved "
					+ player.getKillCount() + " kills.", false);
		}
		if (player.getKillCount() >= 750 && player.getKillCount() < 1000 && player.skullId != 3) {
			player.skullId = 3;
			player.getAppearence().generateAppearenceData();
			World.sendWorldMessage("<img=7><col=36648b>News: " + player.getDisplayName() + " has achieved "
					+ player.getKillCount() + " kills.", false);
		}
		if (player.getKillCount() >= 1000 && player.skullId != 2) {
			player.skullId = 2;
			player.getAppearence().generateAppearenceData();
			World.sendWorldMessage("<img=7><col=36648b>News: " + player.getDisplayName() + " has achieved "
					+ player.getKillCount() + " kills.", false);
		}
	}

	public static void checkSkullId(Player player) {
		if (player.getKillCount() < 50) {
			player.skullId = 0;
			player.getAppearence().generateAppearenceData();
		}
		if (player.getKillCount() >= 50 && player.getKillCount() < 100 && player.skullId != 1) {
			player.skullId = 1;
			player.getAppearence().generateAppearenceData();
		}
		if (player.getKillCount() >= 100 && player.getKillCount() < 250 && player.skullId != 6) {
			player.skullId = 6;
			player.getAppearence().generateAppearenceData();
		}
		if (player.getKillCount() >= 250 && player.getKillCount() < 500 && player.skullId != 5) {
			player.skullId = 5;
			player.getAppearence().generateAppearenceData();
		}
		if (player.getKillCount() >= 500 && player.getKillCount() < 750 && player.skullId != 4) {
			player.skullId = 4;
			player.getAppearence().generateAppearenceData();
		}
		if (player.getKillCount() >= 750 && player.getKillCount() < 1000 && player.skullId != 3) {
			player.skullId = 3;
			player.getAppearence().generateAppearenceData();
		}
		if (player.getKillCount() >= 1000 && player.skullId != 2) {
			player.skullId = 2;
			player.getAppearence().generateAppearenceData();
		}
	}
}
