package com.rs.game.player;

import java.io.Serializable;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.minigames.clanwars.ClanWars;
import com.rs.game.minigames.clanwars.ClanWars.Rules;
import com.rs.utils.Utils;

public class PrayerBook implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2082861520556582824L;

	private final static int[][] prayerLvls = {
			// normal prayer book
			{ 1, 4, 7, 8, 9, 10, 13, 16, 19, 22, 25, 26, 27, 28, 31, 34, 35, 37, 40, 43, 44, 45, 46, 49, 52, 60, 65, 70,
					74, 77 },
			// ancient prayer book
			{ 50, 50, 52, 54, 56, 59, 62, 65, 68, 71, 74, 76, 78, 80, 82, 84, 86, 89, 92, 95 } };

	private final static int[][][] closePrayers = { { // normal prayer book
			{ 0, 5, 13 }, // Skin prayers 0
			{ 1, 6, 14 }, // Strength prayers 1
			{ 2, 7, 15 }, // Attack prayers 2
			{ 3, 11, 20, 28 }, // Range prayers 3
			{ 4, 12, 21, 29 }, // Magic prayers 4
			{ 8, 9, 26 }, // Restore prayers 5
			{ 10 }, // Protect item prayers 6
			{ 17, 18, 19 }, // Protect prayers 7
			{ 16 }, // Other protect prayers 8
			{ 22, 23, 24 }, // Other special prayers 9
			{ 25, 27 } // Other prayers 10
			}, { // ancient prayer book
					{ 0 }, // Protect item prayers 0
					{ 1, 2, 3, 4 }, // sap prayers 1
					{ 5 }, // other prayers 2
					{ 7, 8, 9, 17, 18 }, // protect prayers 3
					{ 6 }, // other protect prayers 4
					{ 10, 11, 12, 13, 14, 15, 16 }, // leech prayers 5
					{ 19 }, // other prayers
			} };

	private final static int[] prayerSlotValues = { 1, 2, 4, 262144, 524288, 8, 16, 32, 64, 128, 256, 1048576, 2097152,
			512, 1024, 2048, 16777216, 4096, 8192, 16384, 4194304, 8388608, 32768, 65536, 131072, 33554432, 134217728,
			67108864, 268435456 * 2, 268435456 };

	private final static double[][] prayerDrainRate = {
			{ 1.2, 1.2, 1.2, 1.2, 1.2, 0.6, 0.6, 0.6, 3.6, 1.8, 1.8, 0.6, 0.6, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3,
					0.3, 1.2, 0.6, 0.18, 0.15, 0.3, 0.15, 0.2, 0.18 },
			{ 1.8, 0.25, 0.25, 0.25, 0.25, 1.8, 0.3, 0.3, 0.3, 0.3, 0.35, 0.35, 0.35, 0.35, 0.35, 0.33, 0.4, 0.1, 0.2,
					0.22 } };

	private transient Player player;
	private transient boolean[][] onPrayers;
	private transient boolean usingQuickPrayer;
	private transient int onPrayersCount;

	private boolean[][] quickPrayers;
	private int prayerpoints;
	public transient int[] leechBonuses;
	public boolean ancientcurses;
	private transient long[] nextDrain;
	private transient boolean boostedLeech;

	public double getMageMultiplier() {
		if (onPrayersCount == 0)
			return 1.0;
		double value = 1.0;

		// normal
		if (usingPrayer(0, 4))
			value += 0.05;
		else if (usingPrayer(0, 12))
			value += 0.10;
		else if (usingPrayer(0, 21))
			value += 0.15;
		else if (usingPrayer(0, 29))
			value += 0.20;
		else if (usingPrayer(1, 3)) {
			double d = (leechBonuses[4]);
			value += d / 100;
		} else if (usingPrayer(1, 12)) {
			double d = (5 + leechBonuses[4]);
			value += d / 100;
		}
		return value;
	}

	public double getRangeMultiplier() {
		if (onPrayersCount == 0)
			return 1.0;
		double value = 1.0;

		// normal
		if (usingPrayer(0, 3))
			value += 0.05;
		else if (usingPrayer(0, 11))
			value += 0.10;
		else if (usingPrayer(0, 20))
			value += 0.15;
		else if (usingPrayer(0, 28))
			value += 0.20;
		else if (usingPrayer(1, 2)) {
			double d = (leechBonuses[3]);
			value += d / 100;
		} else if (usingPrayer(1, 11)) {
			double d = (5 + leechBonuses[3]);
			value += d / 100;
		}
		return value;
	}

	public double getAttackMultiplier() {
		if (onPrayersCount == 0)
			return 1.0;
		double value = 1.0;

		if (usingPrayer(0, 2))
			value += 0.05;
		else if (usingPrayer(0, 7))
			value += 0.10;
		else if (usingPrayer(0, 15))
			value += 0.15;
		else if (usingPrayer(0, 25))
			value += 0.15;
		else if (usingPrayer(0, 27))
			value += 0.20;
		else if (usingPrayer(1, 1)) {
			double d = (leechBonuses[0]);
			value += d / 100;
		} else if (usingPrayer(1, 10)) {
			double d = (5 + leechBonuses[0]);
			value += d / 100;
		} else if (usingPrayer(1, 19)) {
			double d = (15 + leechBonuses[0]);
			value += d / 100;
		}
		return value;
	}

	public int getTurmMultiplier() {
		leechBonuses[0] = (int) (Math.round(player.getSkills().getLevelForXp(Skills.ATTACK) * 0.15));
		int value = 0;
		if (usingPrayer(1, 19)) {
			int d = (15 + leechBonuses[0]);
			value = d;
		}
		return value;
	}

	public double getStrengthMultiplier() {
		if (onPrayersCount == 0)
			return 1.0;
		double value = 1.0;

		// normal
		if (usingPrayer(0, 1))
			value += 0.05;
		else if (usingPrayer(0, 6))
			value += 0.10;
		else if (usingPrayer(0, 14))
			value += 0.15;
		else if (usingPrayer(0, 25))
			value += 0.18;
		else if (usingPrayer(0, 27))
			value += 0.23;
		else if (usingPrayer(1, 1)) {
			double d = (leechBonuses[1]);
			value += d / 100;
		} else if (usingPrayer(1, 14)) {
			double d = (5 + leechBonuses[1]);
			value += d / 100;
		} else if (usingPrayer(1, 19)) {
			double d = (23 + leechBonuses[1]);
			value += d / 100;
		}
		return value;
	}

	public double getDefenceMultiplier() {
		if (onPrayersCount == 0)
			return 1.0;
		double value = 1.0;

		// normal
		if (usingPrayer(0, 0))
			value += 0.05;
		else if (usingPrayer(0, 5))
			value += 0.10;
		else if (usingPrayer(0, 13))
			value += 0.15;
		else if (usingPrayer(0, 25))
			value += 0.20;
		else if (usingPrayer(0, 27))
			value += 0.25;
		else if (usingPrayer(0, 28))
			value += 0.25;
		else if (usingPrayer(0, 29))
			value += 0.25;
		else if (usingPrayer(1, 1)) {
			double d = (leechBonuses[2]);
			value += d / 100;
		} else if (usingPrayer(1, 13)) {
			double d = (5 + leechBonuses[2]);
			value += d / 100;
		} else if (usingPrayer(1, 19)) {
			double d = (15 + leechBonuses[2]);
			value += d / 100;
		}
		return value;
	}

	public boolean reachedMax(int bonus) {
		if (leechBonuses[bonus] >= 15) {
			return true;
		} else
			return false;
	}

	public boolean reachedMin(int bonus) {
		if (leechBonuses[bonus] <= -15) {
			return true;
		} else
			return false;
	}

	/*
	 * public void decreaseLeechBonus(int bonus) { leechBonuses[bonus]--; if (bonus
	 * == 0) adjustStat(0, leechBonuses[bonus]); else if (bonus == 1) adjustStat(1,
	 * leechBonuses[bonus]); else if (bonus == 2) adjustStat(2,
	 * leechBonuses[bonus]); else if (bonus == 3) adjustStat(3,
	 * leechBonuses[bonus]); else if (bonus == 4) adjustStat(4,
	 * leechBonuses[bonus]); else if (bonus == 5) adjustStat(5,
	 * leechBonuses[bonus]); }
	 */

	public void decrease(int bonus) {
		leechBonuses[bonus]--;
		if (bonus == 0) {
			adjustStat(0, leechBonuses[bonus]);
		} else if (bonus == 1) {
			adjustStat(1, leechBonuses[bonus]);
		} else if (bonus == 2) {
			adjustStat(2, leechBonuses[bonus]);
		} else if (bonus == 3) {
			adjustStat(3, leechBonuses[bonus]);
		} else if (bonus == 4) {
			adjustStat(4, leechBonuses[bonus]);
		}
	}

	public void increase(int bonus) {
		leechBonuses[bonus]++;
		if (bonus == 0) {
			adjustStat(0, leechBonuses[bonus]);
		} else if (bonus == 1) {
			adjustStat(1, leechBonuses[bonus]);
		} else if (bonus == 2) {
			adjustStat(2, leechBonuses[bonus]);
		} else if (bonus == 3) {
			adjustStat(3, leechBonuses[bonus]);
		} else if (bonus == 4) {
			adjustStat(4, leechBonuses[bonus]);
		}
	}

	/*
	 * public void increaseLeechBonus(int bonus) { leechBonuses[bonus]++; if (bonus
	 * == 0) adjustStat(0, leechBonuses[bonus]); else if (bonus == 1) adjustStat(1,
	 * leechBonuses[bonus]); else if (bonus == 2) adjustStat(2,
	 * leechBonuses[bonus]); else if (bonus == 3) adjustStat(3,
	 * leechBonuses[bonus]); else if (bonus == 4) adjustStat(4,
	 * leechBonuses[bonus]); else if (bonus == 5) adjustStat(5,
	 * leechBonuses[bonus]); }
	 */

	public void increaseTurmoilBonus(Player player, Entity target) {
		Player p2 = (Player) target;
		leechBonuses[0] = (int) ((Math.floor(0.15 * p2.getSkills().getLevelForXp(Skills.ATTACK))));
		leechBonuses[2] = (int) ((Math.floor(0.15 * p2.getSkills().getLevelForXp(Skills.DEFENCE))));
		leechBonuses[1] = (int) ((Math.floor(0.1 * p2.getSkills().getLevelForXp(Skills.STRENGTH))));
		// player.getPackets().sendGameMessage("Attack: " + leechBonuses[0]);
		// player.getPackets().sendGameMessage("Strength: " + leechBonuses[1]);
		// player.getPackets().sendGameMessage("Defence: " + leechBonuses[2]);
		adjustStat(0, leechBonuses[0]);
		adjustStat(1, leechBonuses[1]);
		adjustStat(2, leechBonuses[2]);
	}

	public void increaseTurmoilBonusNPC(Player player) {
		leechBonuses[0] = (int) ((Math.floor(0.15 * player.getSkills().getLevelForXp(Skills.ATTACK))));
		leechBonuses[2] = (int) ((Math.floor(0.15 * player.getSkills().getLevelForXp(Skills.DEFENCE))));
		leechBonuses[1] = (int) ((Math.floor(0.1 * player.getSkills().getLevelForXp(Skills.STRENGTH))));
		adjustStat(0, leechBonuses[0]);
		adjustStat(1, leechBonuses[1]);
		adjustStat(2, leechBonuses[2]);
	}

	public void testTurm(Player player) {
		leechBonuses[0] = (int) ((Math.floor(player.getSkills().getLevelForXp(Skills.ATTACK) * 0.15)));
		leechBonuses[2] = (int) ((Math.floor(player.getSkills().getLevelForXp(Skills.DEFENCE) * 0.15)));
		leechBonuses[1] = (int) ((Math.floor(player.getSkills().getLevelForXp(Skills.STRENGTH) * 0.10)));
		player.getPackets().sendGameMessage("Attack: " + leechBonuses[0]);
		player.getPackets().sendGameMessage("Strength: " + leechBonuses[1]);
		player.getPackets().sendGameMessage("Defence: " + leechBonuses[2]);
		adjustStat(0, leechBonuses[0]);
		adjustStat(1, leechBonuses[1]);
		adjustStat(2, leechBonuses[2]);
	}

	public void adjustStat(int stat, int percentage) {
		player.getVarsManager().sendVarBit(6857 + stat, percentage + 30);
	}

	public void closePrayers(int prayerId) {
		onPrayers[getPrayerBook()][prayerId] = false;
		if (ancientcurses) {
			if (prayerId == 19) {
				leechBonuses[0] = 0;
				leechBonuses[1] = 0;
				leechBonuses[2] = 0;
				adjustStat(0, 0);
				adjustStat(1, 0);
				adjustStat(2, 0);
			}
		}
	}

	public int getPrayerHeadIcon() {
		if (onPrayersCount == 0)
			return -1;
		int value = -1;
		if (usingPrayer(0, 16))
			value += 8;
		if (usingPrayer(0, 17))
			value += 3;
		else if (usingPrayer(0, 18))
			value += 2;
		else if (usingPrayer(0, 19))
			value += 1;
		else if (usingPrayer(0, 22))
			value += 4;
		else if (usingPrayer(0, 23))
			value += 6;
		else if (usingPrayer(0, 24))
			value += 5;
		else if (usingPrayer(1, 6)) {
			value += 16;
			if (usingPrayer(1, 8))
				value += 2;
			else if (usingPrayer(1, 7))
				value += 3;
			else if (usingPrayer(1, 9))
				value += 1;
		} else if (usingPrayer(1, 7))
			value += 14;
		else if (usingPrayer(1, 8))
			value += 15;
		else if (usingPrayer(1, 9))
			value += 13;
		else if (usingPrayer(1, 17))
			value += 20;
		else if (usingPrayer(1, 18))
			value += 21;
		return value;
	}

	public void switchSettingQuickPrayer() {
		usingQuickPrayer = !usingQuickPrayer;
		player.getPackets().sendGlobalConfig(181, usingQuickPrayer ? 1 : 0);
		unlockPrayerBookButtons();
		player.getAppearence().generateAppearenceData();
		recalculatePrayer();
		if (usingQuickPrayer)
			player.getPackets().sendGlobalConfig(168, 6);
		player.getAppearence().generateAppearenceData();
		recalculatePrayer();
	}

	public void switchQuickPrayers() {
		if (player.isDead())
			return;
		if (!checkPrayer())
			return;
		if (hasPrayersOn()) {
			closeAllPrayers();
		} else {
			if (player.getCurrentFriendChat() != null) {
				ClanWars war = player.getCurrentFriendChat().getClanWars();
				if (war != null && war.get(Rules.NO_PRAYER)
						&& (war.getFirstPlayers().contains(player) || war.getSecondPlayers().contains(player))) {
					player.getPackets().sendGameMessage("Prayer has been disabled during this war.");
					return;
				}
			}
			boolean hasOn = false;
			int index = 0;
			for (boolean prayer : quickPrayers[getPrayerBook()]) {
				if (prayer) {
					if (usePrayer(index))
						hasOn = true;
				}
				index++;
			}
			if (hasOn) {
				player.getPackets().sendGlobalConfig(182, 1);
				recalculatePrayer();
			}
		}
	}

	private void closePrayers(int[]... prayers) {
		for (int[] prayer : prayers)
			for (int prayerId : prayer)
				if (usingQuickPrayer)
					quickPrayers[getPrayerBook()][prayerId] = false;
				else {
					if (onPrayers[getPrayerBook()][prayerId])
						onPrayersCount--;
					onPrayers[getPrayerBook()][prayerId] = false;
					closePrayers(prayerId);

				}
	}

	public void switchPrayer(int prayerId) {
		if (!usingQuickPrayer)
			if (!checkPrayer())
				return;
		usePrayer(prayerId);
		recalculatePrayer();
	}

	private boolean usePrayer(int prayerId) {
		if (player.isDead())
			return false;
		if (prayerId < 0 || prayerId >= prayerLvls[getPrayerBook()].length)
			return false;
		if (Settings.FREE_TO_PLAY) {
			if (prayerId == 16 || prayerId >= 22) {
				player.getPackets().sendGameMessage("This prayer is only for members.");
				return false;
			}
		}
		if (player.getSkills().getLevelForXp(5) < prayerLvls[this.getPrayerBook()][prayerId]) {
			player.getPackets().sendGameMessage("You need a prayer level of at least "
					+ prayerLvls[getPrayerBook()][prayerId] + " to use this prayer.");
			return false;
		}
		if (getPrayerBook() == 0 && prayerId == 25 || prayerId == 27) {
			if (player.getSkills().getLevelForXp(Skills.DEFENCE) < 70) {
				player.getPackets().sendGameMessage("You need a defence level of at least 70 to use this prayer.");
				return false;
			}
		} else if (getPrayerBook() == 1) {
			if (player.getSkills().getLevelForXp(Skills.DEFENCE) < 30) {
				player.getPackets().sendGameMessage("You need a defence level of at least 30 to use this prayer.");
				return false;
			}
		}
		if (getPrayerBook() == 1 && prayerId >= 6 && prayerId <= 9) {
			if (player.getPrayerDelay() >= Utils.currentTimeMillis()) {
				player.getPackets().sendGameMessage("You are currently injured and cannot use protection prayers!");
				return false;
			}
		}
		if (getPrayerBook() == 0 && prayerId == 9)
			player.healTick = 0;
		if (getPrayerBook() == 0 && prayerId == 26) {
			if (!player.hasRenewal) {
				player.sm("You can buy this prayer from Daemonheim. You also need a Prayer level of 65.");
				return false;
			}
			player.healTick = 0;
		}
		if (getPrayerBook() == 0 && prayerId == 28) {
			if (!player.hasRigour) {
				player.sm("You can buy this prayer from Daemonheim. You also need a Prayer level of 74.");
				return false;
			}
		}
		if (getPrayerBook() == 0 && prayerId == 29) {
			if (!player.hasAugury) {
				player.sm("You can buy this prayer from Daemonheim. You also need a Prayer level of 77.");
				return false;
			}
		}
		if (getPrayerBook() == 0 && prayerId >= 17 && prayerId <= 19) {
			if (player.getPrayerDelay() >= Utils.currentTimeMillis()) {
				player.sm("You are currently injured and cannot use protection prayers!");
				return false;
			}
		}
		if (player.getCurrentFriendChat() != null) {
			ClanWars war = player.getCurrentFriendChat().getClanWars();
			if (war != null && war.get(Rules.NO_PRAYER)
					&& (war.getFirstPlayers().contains(player) || war.getSecondPlayers().contains(player))) {
				player.getPackets().sendGameMessage("Prayer has been disabled during this war.");
				return false;
			}
		}
		if (!usingQuickPrayer) {
			if (onPrayers[getPrayerBook()][prayerId]) {
				onPrayers[getPrayerBook()][prayerId] = false;
				closePrayers(prayerId);
				onPrayersCount--;
				player.getAppearence().generateAppearenceData();
				player.getPackets().sendSound(2663, 0, 1);
				return true;
			}
		} else {
			if (quickPrayers[getPrayerBook()][prayerId]) {
				quickPrayers[getPrayerBook()][prayerId] = false;
				player.getPackets().sendSound(2663, 0, 1);
				return true;
			}
		}
		boolean needAppearenceGenerate = false;
		if (getPrayerBook() == 0) {
			switch (prayerId) {
			case 0:
			case 5:
			case 13:
				closePrayers(closePrayers[getPrayerBook()][0], closePrayers[getPrayerBook()][10]);
				closePrayers(28);
				closePrayers(29);
				break;
			case 1:
			case 6:
			case 14:
				closePrayers(closePrayers[getPrayerBook()][1], closePrayers[getPrayerBook()][3],
						closePrayers[getPrayerBook()][4], closePrayers[getPrayerBook()][10]);
				break;
			case 2:
			case 7:
			case 15:
				closePrayers(closePrayers[getPrayerBook()][2], closePrayers[getPrayerBook()][3],
						closePrayers[getPrayerBook()][4], closePrayers[getPrayerBook()][10]);
				break;
			case 3:
			case 11:
			case 20:
				closePrayers(closePrayers[getPrayerBook()][1], closePrayers[getPrayerBook()][2],
						closePrayers[getPrayerBook()][3], closePrayers[getPrayerBook()][4],
						closePrayers[getPrayerBook()][10]);
				break;
			case 4:
			case 12:
			case 21:
				closePrayers(closePrayers[getPrayerBook()][1], closePrayers[getPrayerBook()][2],
						closePrayers[getPrayerBook()][3], closePrayers[getPrayerBook()][4],
						closePrayers[getPrayerBook()][10]);
				break;
			case 8:
			case 9:
				closePrayers(closePrayers[getPrayerBook()][5]);
				break;
			case 26:
				closePrayers(closePrayers[getPrayerBook()][5]);
				break;
			case 10:
				closePrayers(closePrayers[getPrayerBook()][6]);
				break;
			case 17:
			case 18:
			case 19:
				closePrayers(closePrayers[getPrayerBook()][7], closePrayers[getPrayerBook()][9]);
				needAppearenceGenerate = true;
				break;
			case 16:
				closePrayers(closePrayers[getPrayerBook()][8], closePrayers[getPrayerBook()][9]);
				needAppearenceGenerate = true;
				break;
			case 22:
			case 23:
			case 24:
				closePrayers(closePrayers[getPrayerBook()][7], closePrayers[getPrayerBook()][8],
						closePrayers[getPrayerBook()][9]);
				needAppearenceGenerate = true;
				break;
			case 25:
			case 27:
				closePrayers(closePrayers[getPrayerBook()][0], closePrayers[getPrayerBook()][1],
						closePrayers[getPrayerBook()][2], closePrayers[getPrayerBook()][3],
						closePrayers[getPrayerBook()][4], closePrayers[getPrayerBook()][10]);
				break;

			case 28:
				closePrayers(closePrayers[getPrayerBook()][0], closePrayers[getPrayerBook()][1],
						closePrayers[getPrayerBook()][2], closePrayers[getPrayerBook()][3],
						closePrayers[getPrayerBook()][4], closePrayers[getPrayerBook()][10]);
				break;

			case 29:
				closePrayers(closePrayers[getPrayerBook()][0], closePrayers[getPrayerBook()][1],
						closePrayers[getPrayerBook()][2], closePrayers[getPrayerBook()][3],
						closePrayers[getPrayerBook()][4], closePrayers[getPrayerBook()][10]);
				break;
			default:
				return false;
			}
		} else {
			switch (prayerId) {
			case 0:
				if (!usingQuickPrayer) {
					player.animate(new Animation(12567));
					player.gfx(new Graphics(2213));
				}
				closePrayers(closePrayers[getPrayerBook()][0]);
				break;
			case 1:
			case 2:
			case 3:
			case 4:
				closePrayers(closePrayers[getPrayerBook()][5], closePrayers[getPrayerBook()][6]);
				break;
			case 5:
				if (!usingQuickPrayer) {
					player.animate(new Animation(12589));
					player.gfx(new Graphics(2266));
				}
				closePrayers(closePrayers[getPrayerBook()][2]);
				break;
			case 7:
			case 8:
			case 9:
			case 17:
			case 18:
				closePrayers(closePrayers[getPrayerBook()][3]);
				needAppearenceGenerate = true;
				break;
			case 6:
				closePrayers(closePrayers[getPrayerBook()][4]);
				needAppearenceGenerate = true;
				break;
			case 10:
			case 11:
			case 12:
			case 13:
			case 14:
			case 15:
			case 16:
				closePrayers(closePrayers[getPrayerBook()][1], closePrayers[getPrayerBook()][6]);
				break;
			case 19:
				// stop changing this idiot. it doesnt stop walk on rs
				if (!usingQuickPrayer) {
					player.animate(new Animation(12565));
					player.gfx(new Graphics(2226));
				}
				closePrayers(closePrayers[getPrayerBook()][1], closePrayers[getPrayerBook()][5],
						closePrayers[getPrayerBook()][6]);
				break;
			default:
				return false;
			}
		}
		if (!usingQuickPrayer) {
			onPrayers[getPrayerBook()][prayerId] = true;
			resetDrainPrayer(prayerId);
			onPrayersCount++;
			if (needAppearenceGenerate)
				player.getAppearence().generateAppearenceData();
		} else {
			quickPrayers[getPrayerBook()][prayerId] = true;
		}
		player.getPackets().sendSound(2662, 0, 1);
		return true;
	}

	public void processPrayer() {
		if (!hasPrayersOn())
			return;
		processPrayerDrain();
		boostedLeech = false;
	}

	// 600

	public void processPrayerDrain() {
		if (!hasPrayersOn())
			return;
		int prayerBook = getPrayerBook();
		long currentTime = Utils.currentTimeMillis();
		int drain = 0;
		int prayerPoints = player.getCombatDefinitions().getBonuses()[CombatDefinitions.PRAYER_BONUS];
		int hatId = player.getEquipment().getHatId();
		if (hatId >= 18744 && hatId <= 18746) // hallos give hidden effect 15pray bonus
			prayerPoints += 15;
		for (int index = 0; index < onPrayers[prayerBook].length; index++) {
			if (onPrayers[prayerBook][index]) {
				long drainTimer = nextDrain[index];
				if (drainTimer != 0 && drainTimer <= currentTime) {
					int rate = (int) ((prayerDrainRate[getPrayerBook()][index] * 1000) + (prayerPoints * 50));
					int passedTime = (int) (currentTime - drainTimer);
					drain++;
					int count = 0;
					while (passedTime >= rate && count++ < 10) {
						drain++;
						passedTime -= rate;
					}
					nextDrain[index] = (currentTime + rate) - passedTime;
				}
			}
		}
		if (drain > 0) {
			drainPrayer(drain, true);
			if (!checkPrayer())
				closeAllPrayers();
		}
	}

	public void resetDrainPrayer(int index) {
		nextDrain[index] = (long) (Utils.currentTimeMillis() + (prayerDrainRate[getPrayerBook()][index] * 1000)
				+ (player.getCombatDefinitions().getBonuses()[CombatDefinitions.PRAYER_BONUS] * 50));
	}

	public int getOnPrayersCount() {
		return onPrayersCount;
	}

	public void closeAllPrayers() {
		onPrayers = new boolean[][] { new boolean[30], new boolean[20] };
		leechBonuses = new int[11];
		onPrayersCount = 0;
		player.getPackets().sendGlobalConfig(182, 0);
		adjustStat(0, 0);
		adjustStat(1, 0);
		adjustStat(2, 0);
		adjustStat(3, 0);
		adjustStat(4, 0);
		player.getPackets().sendConfig(ancientcurses ? 1582 : 1395, 0);
		player.getAppearence().generateAppearenceData();
		resetStatAdjustments();
	}

	public void closeProtectPrayers() {
		if (getPrayerBook() == 0) {
			onPrayers[getPrayerBook()][16] = false;
			onPrayers[getPrayerBook()][17] = false;
			onPrayers[getPrayerBook()][18] = false;
			onPrayers[getPrayerBook()][19] = false;
			closePrayers(16);
			closePrayers(17);
			closePrayers(18);
			closePrayers(19);
		} else {
			onPrayers[getPrayerBook()][6] = false;
			onPrayers[getPrayerBook()][7] = false;
			onPrayers[getPrayerBook()][8] = false;
			onPrayers[getPrayerBook()][9] = false;
			closePrayers(6);
			closePrayers(7);
			closePrayers(8);
			closePrayers(9);
		}
		recalculatePrayer();
		player.getAppearence().generateAppearenceData();
	}

	public boolean hasPrayersOn() {
		return onPrayersCount > 0;
	}

	private boolean checkPrayer() {
		if (prayerpoints <= 0) {
			player.getPackets().sendSound(2672, 0, 1);
			player.getPackets().sendGameMessage("Please recharge your prayer.");
			return false;
		}
		return true;
	}

	public int getPrayerBook() {
		return ancientcurses == false ? 0 : 1;
	}

	private void recalculatePrayer() {
		int value = 0;
		int index = 0;
		for (boolean prayer : (!usingQuickPrayer ? onPrayers[getPrayerBook()] : quickPrayers[getPrayerBook()])) {
			if (prayer)
				value += ancientcurses ? Math.pow(2, index) : prayerSlotValues[index];
			index++;
		}
		player.getPackets()
				.sendConfig(ancientcurses ? (usingQuickPrayer ? 1587 : 1582) : (usingQuickPrayer ? 1397 : 1395), value);
	}

	public void refresh() {
		player.getPackets().sendGlobalConfig(181, usingQuickPrayer ? 1 : 0);
		player.getPackets().sendConfig(1584, ancientcurses ? 1 : 0);
		unlockPrayerBookButtons();
	}

	public void resetStatAdjustments() {
		for (int i = 0; i < 5; i++) {
			leechBonuses[i] = 0;
			adjustStat(i, 0);
		}
	}

	public void init() {
		refresh();
		resetStatAdjustments();
	}

	public void unlockPrayerBookButtons() {
		player.getPackets().sendUnlockIComponentOptionSlots(271, usingQuickPrayer ? 42 : 8, 0, 29, 0);
	}

	public void setPrayerBook(boolean ancientcurses) {
		closeAllPrayers();
		this.ancientcurses = ancientcurses;
		player.getInterfaceManager().sendPrayerBook();
		refresh();
	}

	public PrayerBook() {
		quickPrayers = new boolean[][] { new boolean[30], new boolean[20] };
		prayerpoints = 10;
	}

	public void setPlayer(Player player) {
		this.player = player;
		onPrayers = new boolean[][] { new boolean[30], new boolean[20] };
		nextDrain = new long[30];
		leechBonuses = new int[11];
	}

	public boolean isAncientCurses() {
		return ancientcurses;
	}

	public boolean usingPrayer(int book, int prayerId) {
		return onPrayers[book][prayerId];
	}

	public boolean isUsingQuickPrayer() {
		return usingQuickPrayer;
	}

	public boolean isBoostedLeech() {
		return boostedLeech;
	}

	public void setBoostedLeech(boolean boostedLeech) {
		this.boostedLeech = boostedLeech;
	}

	public int getPrayerpoints() {
		return prayerpoints;
	}

	public void setPrayerpoints(int prayerpoints) {
		this.prayerpoints = prayerpoints;
	}

	public void refreshPrayerPoints(boolean update) {
		if (player.toggles("ONEXHITS", false)) {
			if (update)
				player.getPackets().sendConfig(2382, prayerpoints);
			player.getPackets().sendIComponentText(749, 6, prayerpoints / 10 + "");
		} else
			player.getPackets().sendConfig(2382, prayerpoints);
	}

	public void refreshPrayerPoints() {
		if (player.toggles("ONEXHITS", false)) {
			player.getPackets().sendConfig(2382, prayerpoints);
			player.getPackets().sendIComponentText(749, 6, prayerpoints / 10 + "");
		} else
			player.getPackets().sendConfig(2382, prayerpoints);
	}

	public void drainPrayerOnHalf() {
		if (prayerpoints > 0) {
			prayerpoints = prayerpoints / 2;
			refreshPrayerPoints();
		}
	}

	public boolean hasFullPrayerpoints() {
		return getPrayerpoints() >= player.getSkills().getLevelForXp(Skills.PRAYER) * 10;
	}

	public void drainPrayer(int amount) {
		if ((prayerpoints - amount) >= 0)
			prayerpoints -= amount;
		else
			prayerpoints = 0;
		refreshPrayerPoints();
	}

	public void drainPrayer(int amount, boolean updateCheck) {
		if ((prayerpoints - amount) >= 0)
			prayerpoints -= amount;
		else
			prayerpoints = 0;
		refreshPrayerPoints(prayerpoints % 10 <= 0);
	}

	public void restorePrayer(int amount) {
		int maxPrayer = player.getSkills().getLevelForXp(Skills.PRAYER) * 10;
		if ((prayerpoints + amount) <= maxPrayer)
			prayerpoints += amount;
		else
			prayerpoints = maxPrayer;
		refreshPrayerPoints();
	}

	public void restorePrayer(int amount, boolean updateCheck) {
		int maxPrayer = player.getSkills().getLevelForXp(Skills.PRAYER) * 10;
		if ((prayerpoints + amount) <= maxPrayer)
			prayerpoints += amount;
		else
			prayerpoints = maxPrayer;
		refreshPrayerPoints(prayerpoints % 10 <= 0);
	}

	public void calcRestorePrayer() {
		int amount = (int) ((int) (Math.floor(player.getSkills().getLevelForXp(Skills.PRAYER) * 2.5) + 990)
				* player.getAuraManager().getPrayerPotsRestoreMultiplier());
		int maxPrayer = player.getSkills().getLevelForXp(Skills.PRAYER) * 10;
		if ((prayerpoints + amount) <= maxPrayer)
			prayerpoints += amount;
		else
			prayerpoints = maxPrayer;
		refreshPrayerPoints();
	}

	public void reset() {
		closeAllPrayers();
		prayerpoints = player.getSkills().getLevelForXp(Skills.PRAYER) * 10;
		refreshPrayerPoints();
	}

	public void drainPrayer() {
		prayerpoints = 0;
		refreshPrayerPoints();
	}
	
	public static boolean usingBerserker(Player player) {
		return player.getPrayer().usingPrayer(1, 15);
	}

	public static boolean usingRapidRenewal(Player player) {
		return player.getPrayer().usingPrayer(0, 26);
	}

	public static boolean usingRapidRestore(Player player) {
		return player.getPrayer().usingPrayer(0, 8);
	}

	public static boolean usingRapidHeal(Player player) {
		return player.getPrayer().usingPrayer(0, 9);
	}

	public boolean isUsingProtectionPrayer() {
		return isMageProtecting() || isRangeProtecting() || isMeleeProtecting();
	}

	public boolean isMageProtecting() {
		return ancientcurses ? usingPrayer(1, 11) : usingPrayer(0, 11);
	}

	public boolean isRangeProtecting() {
		return ancientcurses ? usingPrayer(1, 12) : usingPrayer(0, 12);
	}

	public boolean isMeleeProtecting() {
		return ancientcurses ? usingPrayer(1, 13) : usingPrayer(0, 13);
	}

}
