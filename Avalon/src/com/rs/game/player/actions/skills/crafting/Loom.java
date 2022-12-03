package com.rs.game.player.actions.skills.crafting;

import java.util.HashMap;
import java.util.Map;

import com.rs.game.Animation;
import com.rs.game.WorldObject;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;

public class Loom extends Action {

	public enum Products {

		SACK(21, 38, new Item[] { new Item(5931, 4) }, new Item(5418), 0),

		BASKET(36, 56, new Item[] { new Item(5933, 6) }, new Item(5376), 1),

		CLOTH(1, 1.2, new Item[] { new Item(1759, 2) }, new Item(3224), 2),

		SEAWEED_NET(52, -1, new Item[] { new Item(1779, 5) }, new Item(14858), 3),

		MILESTONE_CAPES(1, -1, new Item[] { new Item(1759, 0) }, new Item(20735), 4);

		private static Map<Integer, Products> prods = new HashMap<Integer, Products>();

		public static Products forId(int buttonId) {
			return prods.get(buttonId);
		}

		static {
			for (Products prod : Products.values()) {
				prods.put(prod.getButtonId(), prod);
			}
		}

		private int levelRequired;
		private double experience;
		private Item[] itemsRequired;
		private int buttonId;
		private Item producedItem;

		private Products(int levelRequired, double experience, Item[] itemsRequired, Item producedItem, int buttonId) {
			this.levelRequired = levelRequired;
			this.experience = experience;
			this.itemsRequired = itemsRequired;
			this.producedItem = producedItem;
			this.buttonId = buttonId;
		}

		public Item[] getItemsRequired() {
			return itemsRequired;
		}

		public int getLevelRequired() {
			return levelRequired;
		}

		public Item getProducedItem() {
			return producedItem;
		}

		public double getExperience() {
			return experience;
		}

		public int getButtonId() {
			return buttonId;
		}
	}

	public Products prod;
	public WorldObject object;
	public int ticks;

	public Loom(int slotId, WorldObject object, int ticks) {
		this.object = object;
		this.prod = Products.forId(slotId);
		this.ticks = ticks;
	}

	@Override
	public boolean start(Player player) {
		if (prod == null || player == null || object == null) {
			player.sm("null");
			return false;
		}
		if (player.getSkills().getLevel(Skills.CRAFTING) < prod.getLevelRequired()) {
			player.getPackets().sendGameMessage("You need a Crafting level of at least " + prod.getLevelRequired()
					+ " to create " + prod.getProducedItem().getDefinitions().getName());
			return false;
		}
		if (!player.getInventory().containsItem(prod.getItemsRequired()[0].getId(),
				prod.getItemsRequired()[0].getAmount())) {
			player.getPackets()
					.sendGameMessage("You need atleast " + prod.getItemsRequired()[0].getAmount() + " "
							+ prod.getItemsRequired()[0].getDefinitions().getName() + " to create a "
							+ prod.getProducedItem().getDefinitions().getName() + ".");
			return false;
		}
		return true;
	}

	@Override
	public boolean process(Player player) {
		if (prod == null || player == null || object == null) {
			return false;
		}
		if (player.getSkills().getLevel(Skills.CRAFTING) < prod.getLevelRequired()) {
			player.getPackets().sendGameMessage("You need a Crafting level of at least " + prod.getLevelRequired()
					+ " to create " + prod.getProducedItem().getDefinitions().getName());
			return false;
		}
		if (!player.getInventory().containsItem(prod.getItemsRequired()[0].getId(),
				prod.getItemsRequired()[0].getAmount())) {
			player.getPackets().sendGameMessage("You need " + prod.getItemsRequired()[0].getDefinitions().getName()
					+ " to create a " + prod.getProducedItem().getDefinitions().getName() + ".");
			return false;
		}
		player.faceObject(object);
		return true;
	}

	@Override
	public int processWithDelay(Player player) {
		ticks--;
		player.animate(new Animation(896));
		if (prod.getExperience() > 0)
			player.getSkills().addXp(Skills.CRAFTING, prod.getExperience());
		for (Item required : prod.getItemsRequired()) {
			player.getInventory().deleteItem(required.getId(), required.getAmount());
		}
		player.getInventory().addItem(prod.getProducedItem());
		player.getPackets().sendGameMessage(
				"You make a " + prod.getProducedItem().getDefinitions().getName().toLowerCase() + ".", true);
		if (ticks > 0) {
			return 1;
		}
		return -1;
	}

	@Override
	public void stop(Player player) {
		this.setActionDelay(player, 3);
	}
}
