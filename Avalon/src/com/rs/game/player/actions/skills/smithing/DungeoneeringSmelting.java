package com.rs.game.player.actions.skills.smithing;

import java.util.HashMap;
import java.util.Map;

import com.rs.game.Animation;
import com.rs.game.WorldObject;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;

public class DungeoneeringSmelting extends Action {

	public enum SmeltingBar {

		NOVITE(1, 7, new Item[] { new Item(17630) }, new Item(17650), 0),

		BATHUS(10, 13.3, new Item[] { new Item(17632) }, new Item(17652), 1),

		MARMAROS(20, 19.6, new Item[] { new Item(17634) }, new Item(17654), 2),

		KRATONITE(30, 25.9, new Item[] { new Item(17636) }, new Item(17656),3),

		FRACTITE(40, 32.2, new Item[] { new Item(17638) }, new Item(17658), 4),

		ZEPHYRIUM(50, 38.5, new Item[] { new Item(17640) }, new Item(17660), 5),

		ARGONITE(60, 44.8, new Item[] { new Item(17642) }, new Item(17662), 6),

		KATAGON(70, 51.1, new Item[] { new Item(17644) }, new Item(17664), 7),

		GORGONITE(80, 57.4, new Item[] { new Item(17646) }, new Item(17666), 8),

		PROMETHIUM(90, 63.7, new Item[] { new Item(17648) }, new Item(17668), 9);

		private static Map<Integer, SmeltingBar> bars = new HashMap<Integer, SmeltingBar>();

		public static SmeltingBar forId(int buttonId) {
			return bars.get(buttonId);
		}

		static {
			for (SmeltingBar bar : SmeltingBar.values()) {
				bars.put(bar.getButtonId(), bar);
			}
		}

		private int levelRequired;
		private double experience;
		private Item[] itemsRequired;
		private int buttonId;
		private Item producedBar;

		private SmeltingBar(int levelRequired, double experience, Item[] itemsRequired, Item producedBar,
				int buttonId) {
			this.levelRequired = levelRequired;
			this.experience = experience;
			this.itemsRequired = itemsRequired;
			this.producedBar = producedBar;
			this.buttonId = buttonId;
		}

		public Item[] getItemsRequired() {
			return itemsRequired;
		}

		public int getLevelRequired() {
			return levelRequired;
		}

		public Item getProducedBar() {
			return producedBar;
		}

		public double getExperience() {
			return experience;
		}

		public int getButtonId() {
			return buttonId;
		}
	}

	public SmeltingBar bar;
	public WorldObject object;
	public int ticks;

	public DungeoneeringSmelting(int slotId, WorldObject object, int ticks) {
		this.object = object;
		this.bar = SmeltingBar.forId(slotId);
		this.ticks = ticks;
	}

	@Override
	public boolean start(Player player) {
		if (bar == null || player == null || object == null) {
			player.message("bar is null");
			return false;
		}
		if (player.getSkills().getLevel(Skills.SMITHING) < bar.getLevelRequired()) {
			player.getPackets().sendGameMessage("You need a Smithing level of at least " + bar.getLevelRequired()
					+ " to make " + bar.getProducedBar().getDefinitions().getName());
			return false;
		}
		if (bar.getItemsRequired().length > 1) {
			if (!player.getInventory().containsItem(bar.getItemsRequired()[1].getId(),
					bar.getItemsRequired()[1].getAmount())) {
				player.getPackets()
						.sendGameMessage("You need atleast " + bar.getItemsRequired()[0].getAmount() + " "
								+ bar.getItemsRequired()[0].getDefinitions().getName() + " and "
								+ bar.getItemsRequired()[1].getAmount() + " "
								+ bar.getItemsRequired()[1].getDefinitions().getName() + " to create a "
								+ bar.getProducedBar().getDefinitions().getName() + ".");
				return false;
			}
		} else {
			if (!player.getInventory().containsItem(bar.getItemsRequired()[0].getId(),
					bar.getItemsRequired()[0].getAmount())) {
				player.getPackets()
						.sendGameMessage("You need atleast " + bar.getItemsRequired()[0].getAmount() + " "
								+ bar.getItemsRequired()[0].getDefinitions().getName() + " to create a "
								+ bar.getProducedBar().getDefinitions().getName() + ".");
				return false;
			}
		}
		player.getPackets().sendGameMessage("You place the required materials and attempt to create "
				+ (bar.producedBar.getId() == 2 ? "some cannonballs."
						: "a bar of "
								+ bar.getProducedBar().getDefinitions().getName().toLowerCase().replace(" bar", "")
								+ "."),
				true);
		return true;
	}

	@Override
	public boolean process(Player player) {
		if (bar == null || player == null || object == null) {
			return false;
		}
		if (player.getSkills().getLevel(Skills.SMITHING) < bar.getLevelRequired()) {
			player.getPackets().sendGameMessage("You need a Smithing level of at least " + bar.getLevelRequired()
					+ " to make " + bar.getProducedBar().getDefinitions().getName());
			return false;
		}
		if (!player.getInventory().containsItem(bar.getItemsRequired()[0].getId(),
				bar.getItemsRequired()[0].getAmount())) {
			player.getPackets().sendGameMessage("You need " + bar.getItemsRequired()[0].getDefinitions().getName()
					+ " to create a " + bar.getProducedBar().getDefinitions().getName() + ".");
			return false;
		}
		if (bar.getItemsRequired().length > 1) {
			if (!player.getInventory().containsItem(bar.getItemsRequired()[1].getId(),
					bar.getItemsRequired()[1].getAmount())) {
				player.getPackets().sendGameMessage("You need " + bar.getItemsRequired()[1].getDefinitions().getName()
						+ " to create a " + bar.getProducedBar().getDefinitions().getName() + ".");
				return false;
			}
		}
		player.faceObject(object);
		return true;
	}

	@Override
	public int processWithDelay(Player player) {
		ticks--;
		player.animate(new Animation(3243));
		player.getSkills().addXp(Skills.SMITHING, bar.getExperience());
		for (Item required : bar.getItemsRequired()) {
			player.getInventory().deleteItem(required.getId(), required.getAmount());
		}
		player.getInventory().addItem(bar.getProducedBar());
		player.getPackets()
				.sendGameMessage("You retrieve " + (bar.getProducedBar().getId() == 2 ? "some cannonballs."
						: "a bar of "
								+ bar.getProducedBar().getDefinitions().getName().toLowerCase().replace(" bar", "")
								+ "."),
						true);
		if (ticks > 0)
			return 2;
		return -1;
	}

	@Override
	public void stop(Player player) {
		this.setActionDelay(player, 3);
	}
}
