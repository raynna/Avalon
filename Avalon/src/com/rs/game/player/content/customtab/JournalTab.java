package com.rs.game.player.content.customtab;

import java.text.DecimalFormat;

import com.rs.Settings;
import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.game.player.Ranks.Rank;
import com.rs.utils.HexColours.Colour;

public class JournalTab extends CustomTab {

	/**
	 * @author Andreas
	 *
	 */

	public enum JournalStore {

		TITLE(25) {
			@Override
			public void usage(Player p) {
			}

			@Override
			public String text(Player p) {
				return "Journal";
			}
		},

		INFORMATION(3) {
			@Override
			public void usage(Player p) {
			}

			@Override
			public String text(Player p) {
				return "<u>Server Information";
			}
		},

		PLAYERCOUNT(4) {
			@Override
			public void usage(Player p) {
				p.sendPlayersList();
				p.getPackets().sendGameMessage("Players online: " + World.getPlayers().size() + ".");

			}

			@Override
			public String text(Player p) {
				return "Players Online: <col=04BB3B>" + World.getPlayers().size();
			}
		},

		WILDYCOUNT(5) {
			@Override
			public void usage(Player p) {
				p.getPackets().sendGameMessage("Players in the Wilderness: " + World.getPlayersInWilderness() + ".");
			}

			@Override
			public String text(Player p) {
				return "Players in Wilderness: <col=04BB3B>" + World.getPlayersInWilderness();
			}
		},
		
		PVPCOUNT(6) {
			@Override
			public void usage(Player p) {
				p.getPackets().sendGameMessage("Players in Edgeville PvP: " + World.getPlayersInPVP() + ".");
			}

			@Override
			public String text(Player p) {
				return "Players in Edgeville PvP: <col=04BB3B>" + World.getPlayersInPVP();
			}
		},

		FFACOUNT(7) {
			@Override
			public void usage(Player p) {
				p.getPackets().sendGameMessage("Players in Clan Wars (FFA): " + World.getPlayersInFFA() + ".");
			}

			@Override
			public String text(Player p) {
				return "Clan Wars (FFA): <col=04BB3B>" + World.getPlayersInFFA();
			}
		},

		DOUBLEDROPS(8) {
			@Override
			public void usage(Player p) {
				p.getPackets()
						.sendGameMessage("Double drops is " + (Settings.DOUBLE_DROP ? " activated." : "inactivated."));
			}

			@Override
			public String text(Player p) {
				return "Double Drops: " + (Settings.DOUBLE_DROP ? "<col=04BB3B>Active" : "<col=BB0404>Inactive");
			}
		},

		PLAYERINFO(10) {
			@Override
			public void usage(Player p) {
			}

			@Override
			public String text(Player p) {
				return "<u>Player Information";
			}
		},
		PLAYERRANK(11) {
			@Override
			public void usage(Player p) {
				p.getPackets().sendGameMessage(
						"My ranks is: <img=" + p.getMessageIcon() + ">" + p.getPlayerRank().getRankNames());
				p.setNextForceTalk(new ForceTalk(
						"My ranks is: <img=" + p.getMessageIcon() + ">" + p.getPlayerRank().getRankNames()));
			}

			@Override
			public String text(Player p) {
				return "Rank: " + Colour.GREEN.getHex() + "<img="
						+ (p.getPlayerRank().getRank()[0] == Rank.DEVELOPER
								|| p.getPlayerRank().getRank()[0] == Rank.DEVELOPER
										? p.getMessageIcon() - 1
										: p.getPlayerRank().isHardcore() ? 24
												: p.getPlayerRank().isIronman() ? 23 : p.getMessageIcon())
						+ ">" + p.getPlayerRank().getRankName(0);
			}
		},
		PLAYERRANK2(12) {
			@Override
			public void usage(Player p) {
				boolean donator = p.getPlayerRank().isDonator();
				p.getPackets()
						.sendGameMessage("My Donator rank is: "
								+ (donator ? "<img=" + p.getDonatorIcon() + ">" + p.getPlayerRank().getRankName(1)
										: "I'm not a donator"));
				p.setNextForceTalk(new ForceTalk("My Donator rank is: "
						+ (donator ? "<img=" + p.getDonatorIcon() + ">" + p.getPlayerRank().getRankName(1)
								: "I'm not a donator")));
			}

			@Override
			public String text(Player p) {
				boolean donator = p.getPlayerRank().isDonator();
				return "Donator rank:  " + Colour.WHITE.getHex()
						+ (donator ? "<img=" + p.getDonatorIcon() + ">" + p.getPlayerRank().getRankName(1)
								: Colour.RED.getHex() +  "I'm not a donator");
			}
		},
		PLAYERTITLE(13) {
			@Override
			public void usage(Player p) {
				p.setCustomTitle(null);
				p.getTemporaryAttributes().put("SET_TITLE", Boolean.TRUE);
				p.getPackets().sendRunScript(108, new Object[] { "Enter title id, 0-58, 0 = none:" });
			}

			@Override
			public String text(Player p) {
				return "Title: " + (p.getAppearence().getTitle() != -1 && p.getCustomTitle() == null
						? p.getAppearence().getTitleString()
						: "<col=BB0404>None - click to set");
			}
		},
		KILLS(14) {
			@Override
			public void usage(Player p) {
				p.getPackets().sendGameMessage("My killcount is: " + p.getKillCount() + ".");
				p.setNextForceTalk(new ForceTalk("My killcount is: " + p.getKillCount() + "."));
			}

			@Override
			public String text(Player p) {
				return "Kills: <col=04BB3B>" + p.getKillCount();
			}
		},
		DEATHS(15) {
			@Override
			public void usage(Player p) {
				p.getPackets().sendGameMessage("My deathcount is: " + p.getDeathCount() + ".");
				p.setNextForceTalk(new ForceTalk("My deathcount is: " + p.getDeathCount() + "."));
			}

			@Override
			public String text(Player p) {
				return "Deaths: <col=04BB3B>" + p.getDeathCount();
			}
		},
		KDR(16) {
			@Override
			public void usage(Player p) {
				double kill = p.getKillCount();
				double death = p.getDeathCount();
				double dr = kill / death;
				p.getPackets().sendGameMessage("My kill/death ratio is: " + new DecimalFormat("##.#").format(dr) + ".");
				p.setNextForceTalk(
						new ForceTalk("My kill/death ratio is: " + new DecimalFormat("##.#").format(dr) + "."));
			}

			@Override
			public String text(Player p) {
				double kill = p.getKillCount();
				double death = p.getDeathCount();
				double dr = kill / death;
				return "K/D Ratio: <col=04BB3B>" + new DecimalFormat("##.#").format(dr);
			}
		},
		PLAYERXP(17) {
			@Override
			public void usage(Player p) {
				p.getPackets().sendGameMessage("Your bonus experience is " + p.getBonusExp() + ".");
				p.setNextForceTalk(new ForceTalk("My bonus experience multiplier is " + p.getBonusExp() + "."));
			}

			@Override
			public String text(Player p) {
				return "Bonus Experience: " + (p.getBonusExp() > 1 ? "<col=04BB3B>" + p.getBonusExp() + "x"
						: "<col=BB0404>" + p.getBonusExp() + "x");
			}
		},

		EP(18) {
			@Override
			public void usage(Player p) {
				p.getPackets().sendGameMessage("Your Ep is: " + p.getEp() + ".");
				p.setNextForceTalk(new ForceTalk("I have a total of " + p.getEp() + "% EP."));
			}

			@Override
			public String text(Player p) {
				return "Ep: " + (p.getEp() == 100 ? "<col=04BB3B>" : p.getEp() > 0 ? "<col=FFF300>" : "<col=BB0404>")
						+ p.getEp() + "%";
			}
		},

		SLAYERTASK(19) {
			@Override
			public void usage(Player p) {
				p.getPackets().sendGameMessage(p.getSlayerTask() == null ? "I don't have a slayer task."
						: "I have " + p.getSlayerTask() + " to hunt.");
				p.setNextForceTalk(new ForceTalk(p.getSlayerTask() == null ? "I don't have a slayer task."
						: "I have " + p.getSlayerTask() + " to hunt."));
			}

			@Override
			public String text(Player p) {
				return "<br>Slayer Task: <col=04BB3B>"
						+ (p.getSlayerTask() == null ? "I don't have a task." : "<br><col=04BB3B>" + p.getSlayerTask());
			}
		},

		TASKLOCATION(20) {
			@Override
			public void usage(Player p) {
				if (p.getSlayerTaskTip() != null)
					p.getPackets().sendGameMessage("You can find your slayer monsters in:<br>" + p.getSlayerTaskTip());
			}

			@Override
			public String text(Player p) {
				return (p.getSlayerTaskTip() == null ? ""
						: "<u><br><br><br>Locations:<br><col=04BB3B>"
								+ p.getSlayerTaskTip().replace(" and ", "<br><col=04BB3B>")
										.replace(", ", "<br><col=04BB3B>").replace(".", ""));
			}
		};

		private int compId;

		private JournalStore(int compId) {
			this.compId = compId;
		}

		public abstract String text(Player p);

		public abstract void usage(Player p);

	}

	public static void open(Player player) {
		sendComponents(player);
		for (int i = 3; i <= 22; i++)
			player.getPackets().sendHideIComponent(3002, i, true);
		for (int i = 28; i <= 56; i++)
			player.getPackets().sendHideIComponent(3002, i, true);
		player.getTemporaryAttributes().put("CUSTOMTAB", 0);
		player.getPackets().sendHideIComponent(3002, BACK_BUTTON, true);
		player.getPackets().sendHideIComponent(3002, FORWARD_BUTTON, false);
		player.getPackets().sendSpriteOnIComponent(3002, BLUE_STAR_COMP, BLUE_HIGHLIGHTED);
		for (JournalStore store : JournalStore.values()) {
			if (store != null) {
				player.getPackets().sendHideIComponent(3002, store.compId, false);
				if (store.text(player) != null) {
					player.getPackets().sendIComponentText(3002, store.compId, store.text(player));
				}
			}
		}
	}

	public static void handleButtons(Player player, int compId) {
		for (JournalStore store : JournalStore.values()) {
			if (store != null) {
				if (compId != store.compId)
					continue;
				store.usage(player);
				open(player);
			}
		}
		switch (compId) {
		case FORWARD_BUTTON:
			TeleportTab.open(player);
			break;
		default:
			break;
		}
	}
}
