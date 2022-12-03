package com.rs.game.player.actions.skills.crafting;

import java.util.HashMap;
import java.util.Map;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.utils.Utils;

/**
 * 
 * @Improved Andreas - AvalonPK
 * 
 */

public class LeatherCrafting extends Action {

	public static final int NEEDLE = 1733;
	public static final int THREAD = 1734;

	public static enum Craft {

		LEATHER(1741, NEEDLE, new int[] { 1059, 1061, 1167, 1063, 1129, 1095, 1169 },
				new int[] { 1, 7, 9, 11, 14, 18, 38 }, new double[] { 13.75, 16.25, 18.5, 22, 25, 27, 37 },
				new Animation(1249)),

		HARD_LEATHER(1743, NEEDLE, new int[] { 1131 }, new int[] { 28 }, new double[] { 35 }, new Animation(1249)),

		;

		private static Map<Integer, Craft> crafting = new HashMap<Integer, Craft>();

		public static Craft forId(int id) {
			return crafting.get(id);
		}

		static {
			for (Craft craft : Craft.values())
				crafting.put(craft.id, craft);
		}

		private int[] product, level;
		private int id, selected;
		private double[] xp;
		private Animation anim;

		private Craft(int id, int selected, int[] product, int level[], double[] xp, Animation anim) {
			this.id = id;
			this.product = product;
			this.selected = selected;
			this.xp = xp;
			this.anim = anim;
			this.level = level;
		}

		public int getId() {
			return id;
		}

		public int getSelected() {
			return selected;
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

		public Animation getAnim() {
			return anim;
		}
	}

	private Craft craft;
	private int option;
	private int ticks;

	public LeatherCrafting(Craft craft, int option, int ticks) {
		this.craft = craft;
		this.option = option;
		this.ticks = ticks;
	}

	@Override
	public boolean start(Player player) {
		if (option >= craft.getProduct().length)
			return false;
		if (!process(player))
			return false;
		setActionDelay(player, 1);
		return true;
	}

	@Override
	public boolean process(Player player) {
		if (!player.getInventory().containsItem(craft.getId(), 1)) {
			player.getPackets().sendGameMessage(
					"You don't have any " + ItemDefinitions.getItemDefinitions(craft.getId()).getName() + " left.");
			return false;
		}
		if (ticks <= 0) {
			return false;
		}
		if (player.getSkills().getLevel(Skills.CRAFTING) < craft.getLevel()[option]) {
			player.getPackets().sendGameMessage("You need a level of " + craft.getLevel()[option] + " to craft this.");
			return false;
		}
		if (!player.getInventory().containsItem(NEEDLE, 1)) {
			player.getPackets().sendGameMessage("You need a needle to craft this.");
			return false;
		}
		if (!player.getInventory().containsItem(THREAD, 1)) {
			player.getPackets().sendGameMessage("You need some thread to craft this.");
			return false;
		}
		if ((!player.getInventory().containsItem(craft.getSelected(), 1)
				&& !player.getToolbelt().contains(craft.getSelected()))) {
			if (craft.getSelected() == NEEDLE)
				player.getPackets().sendGameMessage("You need a "
						+ ItemDefinitions.getItemDefinitions(craft.getSelected()).getName() + " to craft this.");
			else
				player.getPackets().sendGameMessage("You don't have any "
						+ ItemDefinitions.getItemDefinitions(craft.getSelected()).getName() + " left.");
			return false;
		}
		return true;
	}

	public Craft getCraft() {
		return craft;
	}

	private boolean doubleLoot(Player player) {
		int chance = 5;
		if (Utils.getRandom(100) <= chance) {
			player.getPackets().sendGameMessage("You managed to craft an extra "
					+ ItemDefinitions.getItemDefinitions(craft.getProduct()[option]).getName() + ".");
			return true;
		}
		return false;
	}

	@Override
	public int processWithDelay(Player player) {
		ticks--;
		player.animate(craft.getAnim());
		player.getInventory().deleteItem(craft.getId(), 1);
		if (Utils.getRandom(3) == 0)
			player.getInventory().deleteItem(THREAD, 1);
		player.getInventory().addItem(craft.getProduct()[option], 1);
		player.getPackets().sendGameMessage(
				"You craft some " + ItemDefinitions.getItemDefinitions(craft.getProduct()[option]).getName() + ".");
		if (doubleLoot(player)) {
			if (player.getInventory().hasFreeSlots())
				player.getInventory().addItem(craft.getProduct()[option], 1);
			else
				World.updateGroundItem(new Item(craft.getProduct()[option], 1), player, player, 60, 0);
			player.getSkills().addXp(Skills.CRAFTING, craft.getXp()[option]);
		}
		player.getSkills().addXp(Skills.CRAFTING, craft.getXp()[option]);
		return 3;
	}

	@Override
	public void stop(final Player player) {
		setActionDelay(player, 4);
	}

	public static Craft isCrafting(Item first, Item second) {
		Craft craft = Craft.forId(first.getId());
		int selected;
		if (craft != null)
			selected = second.getId();
		else {
			craft = Craft.forId(second.getId());
			selected = first.getId();
		}
		return craft != null && selected == craft.getSelected() ? craft : null;
	}
}
