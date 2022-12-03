package com.rs.game.player.actions.skills.cooking;

import java.util.HashMap;
import java.util.Map;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;

/**
 * 
 * @Improved Andreas - AvalonPK
 * 
 */

public class DoughCooking extends Action {

	public static enum Cook {

		PIZZA_BASE(1933, 1929, -1, new int[] { 2307, 1953, 2283 }, new int[] { 1, 1, 35 }, new double[] { 40.5, 0, 1 }), 
		UNCOOKED_PIZZA(2283, 1982, 1985, new int[] { 2287 }, new int[] { 35 }, new double[] { 143 }), 
		MEAT_PIZZA(2289, 2142, -1, new int[] { 2293 }, new int[] { 45 }, new double[] { 26 }), 
		ANCHOVY_PIZZA(2289, 319, -1, new int[] { 2297 }, new int[] { 55 }, new double[] { 39 });

		private static Map<Integer, Cook> cooking = new HashMap<Integer, Cook>();

		public static Cook forId(int id) {
			return cooking.get(id);
		}

		static {
			for (Cook cook : Cook.values())
				cooking.put(cook.id, cook);
		}

		private int[] product, level;
		private int id, selected, secondIngredient;
		private double[] xp;

		private Cook(int id, int selected, int secondIngredient, int[] product, int level[], double[] xp) {
			this.id = id;
			this.selected = selected;
			this.secondIngredient = secondIngredient;
			this.product = product;
			this.xp = xp;
			this.level = level;
		}

		public int getId() {
			return id;
		}

		public int getSelected() {
			return selected;
		}

		public int getSecondIngredient() {
			return secondIngredient;
		}

		public int[] getProduct() {
			return product;
		}

		public int[] getLevel() {
			return level;
		}

		public double[] getXp() {
			return xp;
		}
	}

	private Cook cook;
	private int option;
	private int ticks;

	public DoughCooking(Cook cook, int option, int ticks) {
		this.cook = cook;
		this.option = option;
		this.ticks = ticks;
	}

	@Override
	public boolean start(Player player) {
		if (option >= cook.getProduct().length)
			return false;
		if (cook.getSecondIngredient() != -1) {
			if (!player.getInventory().containsItem(cook.getSecondIngredient(), 1)) {
				player.getPackets().sendGameMessage("You need a "
						+ ItemDefinitions.getItemDefinitions(cook.getSecondIngredient()).getName() + " to make this.");
				return false;
			}
		}
		if (!process(player))
			return false;
		setActionDelay(player, 1);
		return true;
	}

	@Override
	public boolean process(Player player) {
		if (!player.getInventory().containsItem(cook.getId(), 1)) {
			player.getPackets().sendGameMessage(
					"You don't have any " + ItemDefinitions.getItemDefinitions(cook.getId()).getName() + " left.");
			return false;
		}
		if (!player.getInventory().containsItem(cook.getSelected(), 1)) {
			player.getPackets().sendGameMessage(
					"You don't have any " + ItemDefinitions.getItemDefinitions(cook.getSelected()).getName() + " left.");
			return false;
		}
		if (cook.getSecondIngredient() != -1) {
			if (!player.getInventory().containsItem(cook.getSecondIngredient(), 1)) {
				player.getPackets().sendGameMessage("You don't have any "
						+ ItemDefinitions.getItemDefinitions(cook.getSecondIngredient()).getName() + " left.");
				return false;
			}
		}
		if (ticks <= 0) {
			return false;
		}
		if (player.getSkills().getLevel(Skills.COOKING) < cook.getLevel()[option]) {
			player.getPackets().sendGameMessage("You need a level of " + cook.getLevel()[option] + " to make this.");
			return false;
		}
		return true;
	}

	public Cook getCook() {
		return cook;
	}

	@Override
	public int processWithDelay(Player player) {
		ticks--;
		player.getInventory().deleteItem(cook.getId(), 1);
		player.sm("id: " + cook.getId());
		player.sm("selected: " + cook.getSelected());
		player.getInventory().deleteItem(cook.getSelected(), 1);
		player.getInventory().addItem(cook.getProduct()[option], 1);
		if (cook.getSecondIngredient() != -1)
			player.getInventory().deleteItem(cook.getSecondIngredient(), 1);
		player.getPackets().sendGameMessage("Message here");
		player.getSkills().addXp(Skills.COOKING, cook.getXp()[option]);
		return 2;
	}

	@Override
	public void stop(final Player player) {
		setActionDelay(player, 3);
	}

	public static Cook isCooking(Item first, Item second) {
		Cook cook = RS2CookingInteraction(first.getId(), second.getId());
		return cook != null ? cook : null;
	}
	//wrong, second ingredient is just an extra ingredient, apart from the first 2 oh ok
	private static Cook RS2CookingInteraction(int item1, int item2) {
		for (Cook c : Cook.values()) {
			if ((item1 == c.id && item2 == c.selected) || (item1 == c.selected && item2 == c.id) || (item1 == c.id && item2 == c.getSecondIngredient()) || (item1 == c.getSecondIngredient() && item2 == c.id))
				return c;
			
		}
		return null;
	}
}
