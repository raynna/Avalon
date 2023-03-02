package com.rs.game.player.content;

import java.io.Serializable;

import com.rs.cache.loaders.ObjectDefinitions;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Ranks.Rank;
import com.rs.game.player.controlers.CrucibleControler;
import com.rs.game.player.controlers.WildernessControler;
import com.rs.utils.Utils;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - 1. Rain
 * @Date - 15 Mar 2016
 * 
 */

public class Ironman implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7849063233657093838L;

	/** The player. */
	private transient Player player;

	/** The HC death check. */
	public boolean hardcoreDeath;

	/** The meaning of life. @Kappa */
	public int lifes;

	/**
	 * Instantiates a new ironman.
	 */
	public Ironman() {

	}

	/**
	 * Sets the player.
	 *
	 * @param ironman the new player
	 */
	public void setPlayer(Player ironman) {
		player = ironman;
	}

	/**
	 * Adds the life.
	 *
	 * @param  //life the l
	 * @return the int
	 */
	public int addLife(int l) {
		return lifes += l;
	}

	/**
	 * The price handler for each life.
	 */
	public int getLifePrices(double multiplier) {
		return (int) ((lifes * 2500000) * multiplier);
	}

	/**
	 * Exchange items used on bank.
	 *
	 * @param player the player
	 * @param item   the item
	 */
	public static void ExchangeItems(Player player, Item item) {
		int amount = player.getInventory().getAmountOf(item.getId());
		int freeSlots = player.getInventory().getFreeSlots();
		amount = amount > freeSlots && amount != freeSlots + 1 ? freeSlots : amount;
		if (!item.getDefinitions().canBeNoted()) {
			player.message("<u><col=99000>" + item.getName()
					+ " can not be exchanged, this means that it can not be noted or un noted.");
			return;
		}
		if (item.getDefinitions().isNoted()) {
			player.getInventory().deleteItem(item.getId(), amount);
			player.getInventory().addItem(item.getId() - 1, amount);
		} else {
			player.getInventory().deleteItem(item.getId(), amount);
			player.getInventory().addItem(item.getId() + 1, amount);
		}
	}

	/**
	 * Handles deaths in wilderness.
	 *
	 * @param player the player
	 * @param killer the killer
	 */
	public void WildernessDeath(Player player, Player killer) {
		if (player.getPlayerRank().isHardcore()) {
			if (player.getControlerManager().getControler() instanceof WildernessControler
					|| player.getControlerManager().getControler() instanceof CrucibleControler) {
				takeLife(player, killer);
			}
		}
	}

	/**
	 * Take life life from HC ironman.
	 *
	 * @param killer the killer
	 */
	public void takeLife(Player player, Player killer) {
		final int PlayerTotal = player.getSkills().getTotalLevel(player),
				PlayerTotalXP = player.getSkills().getTotalXP(player);
		if (killer != null) {
			if (player.getIronman().lifes > 1) {
				player.getIronman().lifes--;
				player.message("<col=990000>You lost one life! You have " + player.getIronman().lifes + " left.");
			} else {
				World.sendWorldMessage(
						"<img=24>News: <col=990000>" + player.getDisplayName() + " just died with a total level of "
								+ PlayerTotal + " (" + Utils.getFormattedNumber(PlayerTotalXP, ',') + " XP)"
								+ " in a battle against " + killer.getDisplayName() + ".",
						false);
				player.getPackets()
						.sendGameMessage("You have fallen as a Hardcore Iron "
								+ (player.getAppearence().isMale() ? "Man" : "Woman")
								+ ", your Hardcore status has been revoked.");
				player.getPlayerRank().setRank(2, Rank.IRONMAN);
			}
		} else {
			if (player.getIronman().lifes > 1) {
				player.getIronman().lifes--;
				player.message("<col=990000>You lost one life! You have " + player.getIronman().lifes + " left.");
			} else {
				World.sendWorldMessage("<img=24>News: <col=990000>" + player.getDisplayName()
						+ "</col> just died with a total level of <col=990000>" + PlayerTotal + "</col> ("
						+ Utils.getFormattedNumber(PlayerTotalXP, ',') + " XP)" + ".", false);
				player.getPackets()
						.sendGameMessage("You have fallen as a Hardcore Iron"
								+ (player.getAppearence().isMale() ? "man" : "woman")
								+ ", your Hardcore status has been revoked.");
				//if (Settings.discordEnabled) {
				//	Launcher.getDiscordBot().getChannelByName("public-chat")
				//			.sendMessage(player.getDisplayName().toString()
				//					+ " just died in Hardcore ironman mode with a skill total of " + PlayerTotal + " ("
				//					+ Utils.getFormattedNumber(PlayerTotalXP, ',') + " XP)" + "!");
				//}
				player.getPlayerRank().setRank(2, Rank.IRONMAN);
			}
		}
	}

	/**
	 * Takes life from HC ironman.
	 *
	 * @param npc the npc
	 */
	public void takeLife(NPC npc) {
		final int PlayerTotal = player.getSkills().getTotalLevel(player),
				PlayerTotalXP = player.getSkills().getTotalXP(player);
		if (lifes > 0) {
			lifes--;
			player.message("<col=990000>You lost one life! You have " + lifes + " left.");
			if (lifes == 0) {
				finish();
				World.sendWorldMessage("<img=12><col=990000>News: " + player.getDisplayName()
						+ " just died in Hardcore ironman mode with a skill total of " + PlayerTotal + " ("
						+ Utils.getFormattedNumber(PlayerTotalXP, ',') + " XP) fighting against " + npc.getName() + "!",
						false);
				//if (Settings.discordEnabled) {
				//	Launcher.getDiscordBot().getChannelByName("public-chat")
				//			.sendMessage(player.getDisplayName().toString()
				//					+ " just died in Hardcore ironman mode with a skill total of " + PlayerTotal + " ("
				//					+ Utils.getFormattedNumber(PlayerTotalXP, ',') + " XP) fighting against "
				//					+ npc.getName() + "!");
				//}
			}
		}
	}

	/**
	 * Finishes the life of the HC ironman.
	 */
	private void finish() {
		hardcoreDeath = true;
		lifes = 0;
		player.getSession().getChannel().disconnect();
	}

	/**
	 * Spawns death and portal.
	 */
	public void SpawnDeath() {
		NPC death = new NPC(8977, new WorldTile(1888, 5130, 0), -1, false);
		WorldObject portal = new WorldObject(11369, 10, 0, 3077, 3484, 0);
		ObjectDefinitions defs = ObjectDefinitions.getObjectDefinitions(11369);
		defs.setName("Death's Portal");
		World.spawnObject(portal);
		death.setName("Death");
	}
}
