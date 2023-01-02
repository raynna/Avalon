package com.rs.game.player;

/**
 * @author -Andreas 5 feb. 2020 17:09:11
 * @project 1. Avalon
 * 
 */

public class Ranks {

	public enum Rank {

		PLAYER("Player", 1.0),
		
		IRONMAN("Ironman"),
		
		HARDCORE_IRONMAN("Hardcore Ironman"),

		BRONZE_DONATOR("Bronze Donator", 1.10),

		SILVER_DONATOR("Silver Donator", 1.20),

		GOLD_DONATOR("Gold Donator", 1.30),

		YOUTUBER("Youtuber"),

		MODERATOR("Player Moderator"),

		PLAYERSUPPORT("Player Support"),

		DEVELOPER("Developer", 1.0, 1);

		private String rankName;
		private double xpBoost;

		private int iconId;

		private Rank(String rankName) {
			this(rankName, 1.0, -1);
		}

		private Rank(String rankName, double xpBoost) {
			this(rankName, xpBoost, -1);
		}

		private Rank(String rankName, double xpBoost, int iconId) {
			this.rankName = rankName;
			this.xpBoost = xpBoost;
			this.iconId = iconId;
		}

		public String getRankName() {
			return rankName;
		}

		public double getXpBoost() {
			return xpBoost;
		}

		public int getIconId() {
			return iconId;
		}
	}

}
