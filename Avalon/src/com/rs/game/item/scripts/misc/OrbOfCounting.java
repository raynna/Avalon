package com.rs.game.item.scripts.misc;

import com.rs.cores.CoresManager;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemScripts;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

import java.util.concurrent.TimeUnit;

public class OrbOfCounting extends ItemScripts {

	@Override
	public Object[] getKeys() {
		return new Object[] { ItemId.ORB_OF_COUNTING };
	}
	@Override
	public boolean processItem(Player player, Item item, int slotId) {
		if (player.getTemporaryAttributtes().get("ORB_OF_COUNTING") == Boolean.TRUE) {
			player.sm("You already have one counting.");
			return true;
		}
		player.getTemporaryAttributtes().put("ORB_OF_COUNTING", Boolean.TRUE);
		player.sm("Counting...");
		CoresManager.slowExecutor.schedule(new Runnable() {

			@Override
			public void run() {
				int blueHats = 0;
				int redHats = 0;
				for (Entity e : Utils.getAroundEntities(player, player, 14)) {
					if (e instanceof NPC)
						continue;
					Player p = (Player) e;
					if (p.getEquipment().getHatId() == ItemId.VOTING_HAT_RED)
						redHats++;
					if (p.getEquipment().getHatId() == ItemId.VOTING_HAT_BLUE)
						blueHats++;
				}
				player.getTemporaryAttributtes().remove("ORB_OF_COUNTING");
				player.sm("Blue hats: " + blueHats + ", Red hats: " + redHats);
				if (blueHats == redHats) {
					player.sm("No winner! It's a tie!");
				} else
					player.sm("Winner is: " + (blueHats < redHats ? "Red!" : "Blue!"));
			}
		}, 2000, TimeUnit.MILLISECONDS);
		return true;
	}
	@Override
	public boolean processItem2(Player player, Item item, int slotId) {
		if (player.getTemporaryAttributtes().get("ORB_OF_COUNTING") == Boolean.TRUE) {
			player.sm("You already have one counting.");
			return true;
		}
		player.getTemporaryAttributtes().put("ORB_OF_COUNTING", Boolean.TRUE);
		player.sm("Counting...");
		CoresManager.slowExecutor.schedule(new Runnable() {

			@Override
			public void run() {
				int blueHats = 0;
				int redHats = 0;
				for (Player players : World.getPlayers()) {
					if (players.getEquipment().getHatId() == ItemId.VOTING_HAT_RED)
						redHats++;
					if (players.getEquipment().getHatId() == ItemId.VOTING_HAT_BLUE)
						blueHats++;
				}
				player.getTemporaryAttributtes().remove("ORB_OF_COUNTING");
				player.sm("Blue hats: " + blueHats + ", Red hats: " + redHats);
				if (blueHats == redHats) {
					player.sm("No winner! It's a tie!");
				} else
					player.sm("Winner is: " + (blueHats < redHats ? "Red!" : "Blue!"));
			}
		}, 2000, TimeUnit.MILLISECONDS);
		return true;
	}
}