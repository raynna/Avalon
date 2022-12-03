package com.rs.game.player.actions.skills.runecrafting;

import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class RunecraftingPouches extends Runecrafting {

	public static final int[] POUCH_SIZE = { 3, 6, 9, 12 };

	public final static int[] POUCH_REQ = { 1, 25, 50, 75 };

	public static void fillPouch(Player p, int i) {
		if (i < 0)
			return;
		if (POUCH_REQ[i] > p.getSkills().getLevel(Skills.RUNECRAFTING)) {
			p.getPackets().sendGameMessage("You need a runecrafting level of " + POUCH_REQ[i] + " to fill this pouch.",
					false);
			return;
		}
		int essenceToAdd = POUCH_SIZE[i] - p.getPouches()[i];
		if (essenceToAdd > p.getInventory().getItems().getNumberOf(7936))
			essenceToAdd = p.getInventory().getItems().getNumberOf(7936);
		if (essenceToAdd > POUCH_SIZE[i] - p.getPouches()[i])
			essenceToAdd = POUCH_SIZE[i] - p.getPouches()[i];
		if (essenceToAdd > 0) {
			p.getInventory().deleteItem(7936, essenceToAdd);
			p.getPouches()[i] += essenceToAdd;
			return;
		}
		if (!p.getInventory().containsOneItem(7936)) {
			p.getPackets().sendGameMessage("You don't have any essence with you.", false);
			return;
		}
		if (essenceToAdd == 0) {
			p.getPackets().sendGameMessage("Your pouch is full.", false);
			return;
		}
	}

	public static void emptyPouch(Player p, int i) {
		if (i < 0)
			return;
		int toAdd = p.getPouches()[i];
		if (toAdd > p.getInventory().getFreeSlots())
			toAdd = p.getInventory().getFreeSlots();
		if (toAdd > 0) {
			p.getInventory().addItem(7936, toAdd);
			p.getPouches()[i] -= toAdd;
			return;
		}
		if (toAdd == 0) {
			p.getPackets().sendGameMessage("Your pouch has no essence left in it.", false);
			return;
		}
	}

	public static void checkPouch(Player p, int i) {
		if (i < 0)
			return;
		p.getPackets().sendGameMessage("This pouch has " + p.getPouches()[i] + " essences in it.", false);
	}

}
