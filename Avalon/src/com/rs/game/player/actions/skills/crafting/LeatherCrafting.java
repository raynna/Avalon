package com.rs.game.player.actions.skills.crafting;

import java.util.HashMap;
import java.util.Map;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.game.player.content.tasksystem.TaskManager.Tasks;
import com.rs.utils.Utils;

/**
 *
 * @Improved Andreas - AvalonPK
 *
 */

public class LeatherCrafting extends Action {

	public static final int NEEDLE = ItemId.NEEDLE;
	public static final int CHISEL = ItemId.CHISEL;
	public static final int BOW_STRING = 1777;
	public static final int CROSSBOW_STRING = 9438;

	public static enum Craft {

		UNCUT_OPAL(123, CHISEL, new int[] {1},  new int[] { 1 }, new int[] { 1 }, new double[] { 64 }, new Animation(1249)),
		GREEN_DRAGON_LEATHER(1745, NEEDLE, new int[] { 1065, 1099, 1135 }, new int[] { 1, 2, 3 }, new int[] { 57, 60, 63 }, new double[] { 64, 128, 186 }, new Animation(1249)),

		;

		private static Map<Integer, Craft> crafting = new HashMap<Integer, Craft>();

		public static Craft forId(int id) {
			return crafting.get(id);
		}

		static {
			for (Craft craft : Craft.values())
				crafting.put(craft.id, craft);
		}

		private int[] product, level, amount;
		private int id, selected;
		private double[] xp;
		private Animation anim;

		private Craft(int id, int selected, int[] product, int[] amount, int level[], double[] xp, Animation anim) {
			this.id = id;
			this.product = product;
			this.selected = selected;
			this.amount = amount;
			this.xp = xp;
			this.anim = anim;
			this.level = level;
		}

		private Craft(int id, int selected, int[] product, int level[], double[] xp, Animation anim) {
			this(id, selected, product, new int[] {1}, level, xp, anim);
		}

		public int getId() {
			return id;
		}

		public int[] getAmount() {
			return amount;
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
		if (!player.getInventory().containsItem(craft.getId(), craft.getAmount()[option])) {
			player.sm("You need at least " + craft.getAmount()[option] + " " + ItemDefinitions.getItemDefinitions(craft.getId()).getName() + " to craft this item.");
			return false;
		}
		if (ticks <= 0) {
			return false;
		}
		if ((!player.getInventory().containsItem(craft.getSelected(), 1)
				&& !player.getToolbelt().contains(craft.getSelected()))) {
			if (craft.getSelected() == CHISEL || craft.getSelected() == NEEDLE)
				player.getPackets().sendGameMessage("You need a "
						+ ItemDefinitions.getItemDefinitions(craft.getSelected()).getName() + " to craft this.");
			else
				player.getPackets().sendGameMessage("You don't have any "
						+ ItemDefinitions.getItemDefinitions(craft.getSelected()).getName() + " left.");
			return false;
		}
		if (player.getSkills().getLevel(Skills.CRAFTING) < craft.getLevel()[option]) {
			player.getPackets()
					.sendGameMessage("You need a level of " + craft.getLevel()[option] + " to craft this.");
			return false;
		}
		if (!player.getInventory().hasFreeSlots() && craft.getSelected() != CHISEL && craft.getSelected() != NEEDLE
				&& craft.getSelected() != BOW_STRING && craft.getSelected() != CROSSBOW_STRING) {
			if (ItemDefinitions.getItemDefinitions(craft.getProduct()[option]).isStackable()
					&& player.getInventory().containsItem(craft.getProduct()[option], 1)) {
				return true;
			}
			player.getPackets().sendGameMessage("You don't have any inventory space left to craft this.");
			return false;
		}
		return true;
	}

	public static int maxMakeQuantity(Craft craft, Item item) {
		String defs = ItemDefinitions.getItemDefinitions(craft.getId()).getName().toLowerCase();
		String itemDefs = ItemDefinitions.getItemDefinitions(item.getId()).getName().toLowerCase();
		if (craft.getSelected() == CHISEL) {
			if (craft.getId() == 6573)
				return 24;
			if (craft.getId() == 10105 || craft.getId() == 10107)
				return 6;
			if (craft.getId() == 2859)
				return Utils.random(2, 6);
			return 12;
		}
		return 1;

	}

	public Craft getCraft() {
		return craft;
	}

	public static boolean maxMakeQuantityTen(Item item) {
		String defs = ItemDefinitions.getItemDefinitions(item.getId()).getName().toLowerCase();
		if (defs.contains(" arrow") || defs.toLowerCase().equalsIgnoreCase("mutated vine") || defs.contains(" dart")
				|| defs.contains("feather") || defs.contains(" bolt"))
			return true;
		return false;
	}

	@Override
	public int processWithDelay(Player player) {
		ticks--;
		player.animate(craft.getAnim());
		int amount = maxMakeQuantity(craft, new Item(craft.getProduct()[option]));
		if (!player.getInventory().containsItem(craft.getSelected(), amount) && craft.getSelected() != CHISEL && craft.getSelected() != NEEDLE)
			amount = player.getInventory().getNumberOf(craft.getSelected());
		if (!player.getInventory().containsItem(craft.getId(), amount) && craft.getSelected() != CHISEL && craft.getSelected() != NEEDLE)
			amount = player.getInventory().getNumberOf(craft.getId());
		if (craft.getProduct()[option] == 4825)
			player.getInventory().deleteItem(2859, 1);
		if (craft.getId() == 21359)
			player.getInventory().deleteItem(craft.getId(), 2);
		else
			player.getInventory().deleteItem(craft.getId(),
					(craft.getAmount()[option] > 1 ? craft.getAmount()[option] : 1));
		player.getInventory().addItem(craft.getProduct()[option], amount);
		if (craft.getSelected() != NEEDLE && craft.getSelected() != CHISEL)
			player.getInventory().deleteItem(craft.getSelected(), amount);
		player.getPackets().sendGameMessage(
				fletchingMessage(new Item(craft.getProduct()[option]), craft.getSelected(), craft.getId(), amount));
		player.getSkills().addXp(Skills.CRAFTING, craft.getXp()[option] * amount);
		return 2;
	}

	@Override
	public void stop(final Player player) {
		setActionDelay(player, 3);
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

	private String fletchingMessage(Item item, int used, int usedWith, int amount) {
		Item usedItem = new Item(used);
		Item usedWithItem = new Item(usedWith);
		String defs = ItemDefinitions.getItemDefinitions(item.getId()).getName().toLowerCase();
		String usedItemDefs = ItemDefinitions.getItemDefinitions(usedItem.getId()).getName().toLowerCase();
		String usedItemWithDefs = ItemDefinitions.getItemDefinitions(usedWithItem.getId()).getName().toLowerCase();
		if (usedItemDefs.contains(" limbs") || usedItemWithDefs.contains(" limbs"))
			return "You attach the stock to the limbs and create an unstrung crossbow";
		if (usedItemDefs.contains("string") || usedItemWithDefs.contains("string")) {
			if (usedItemDefs.contains("crossbow") || usedItemWithDefs.contains("crossbow"))
				return "You add a string to the crossbow.";
			return "You add a string to the bow.";
		}
		if (item.getId() == 9419)
			return "You attach the unfinished mithril grapple with the rope, completing a mithril grapple.";
		if (defs.contains("grapple"))
			return "You attach the mithril grapple tip to mithril bolt, making a unfinished mithril grapple.";
		if (defs.contains("sagaie"))
			return "You fletch " + amount + " " + (amount > 1 ? defs.replace("sagaie", "sagaies'") : defs) + ".";
		if (defs.contains("bolas")) {
			return "You add 2 excrescence and a mutated vine together and make some bolas.";
		}
		if (defs.contains("headless"))
			return "You attach feathers to " + amount + " arrow shaft" + (amount > 1 ? "s" : "") + ".";
		if (defs.contains("dart"))
			return "You finish making " + amount + " " + (amount > 1 ? defs.replace("dart", "darts") : defs) + ".";
		if (item.getId() == 52)
			return "You carefully cut the wood into " + amount + " arrow shaft" + (amount > 1 ? "s" : "") + ".";
		if (defs.contains("arrow"))
			return "You attach " + defs.replace(" arrow", "")
					+ " heads to some of your arrows.<br>You attach arrow head" + (amount > 1 ? "s" : "") + " to "
					+ amount + " arrow shaft" + (amount > 1 ? "s" : "") + ".";
		if (defs.contains("bolt") || defs.contains("stake") || defs.contains("brutal"))
			return "You fletch " + amount + " " + (amount > 1 ? defs : defs.replace("bolts", "bolt")) + ".";
		return "You carefully cut the wood into a " + defs.replace(" (u)", "") + ".";
	}

}
