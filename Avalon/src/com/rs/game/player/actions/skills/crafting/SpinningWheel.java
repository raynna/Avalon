package com.rs.game.player.actions.skills.crafting;

import java.util.HashMap;
import java.util.Map;

import com.rs.game.Animation;
import com.rs.game.WorldObject;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;

public class SpinningWheel extends Action {

	public enum Products {

		BALL_OF_WOOL(1, 2.5, new Item(1737), new Item(1759), 0),
		
		BALL_OF_BLACK_WOOL(1, -1, new Item(15415), new Item(15416), 1),
		
		BOWSTRING(1, 15, new Item(1779), new Item(1777), 2),
		
		CROSSBOW_STRING(10, 15, new Item(9436), new Item(9438), 3),
		
		MAGIC_STRING(19, 30, new Item(6051), new Item(6038), 4),
		
		ROPE(30, 25, new Item(10814), new Item(954), 5)
		
		;

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
		private Item itemsRequired;
		private int buttonId;
		private Item producedItem;

		private Products(int levelRequired, double experience, Item itemsRequired, Item producedItem, int buttonId) {
			this.levelRequired = levelRequired;
			this.experience = experience;
			this.itemsRequired = itemsRequired;
			this.producedItem = producedItem;
			this.buttonId = buttonId;
		}

		public Item getItemsRequired() {
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
	public int quantity;

	public SpinningWheel(int slotId, WorldObject object, int quantity) {
		this.object = object;
		this.prod = Products.forId(slotId);
		this.quantity = quantity;
	}

	@Override
	public boolean start(Player player) {
		if (prod == null || player == null || object == null) {
			return false;
		}
		if (player.getSkills().getLevel(Skills.CRAFTING) < prod.getLevelRequired()) {
			player.getPackets().sendGameMessage("You need a Crafting level of at least " + prod.getLevelRequired()
					+ " to create " + prod.getProducedItem().getDefinitions().getName());
			return false;
		}
		if (!player.getInventory().containsItem(prod.getItemsRequired().getId(),
				prod.getItemsRequired().getAmount())) {
			player.getPackets()
					.sendGameMessage("You need atleast " + prod.getItemsRequired().getAmount() + " "
							+ prod.getItemsRequired().getDefinitions().getName() + " to create a "
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
		if (!player.getInventory().containsItem(prod.getItemsRequired().getId(),
				prod.getItemsRequired().getAmount())) {
			player.getPackets().sendGameMessage("You need " + prod.getItemsRequired().getDefinitions().getName()
					+ " to create a " + prod.getProducedItem().getDefinitions().getName() + ".");
			return false;
		}
		player.faceObject(object);
		return true;
	}

	@Override
	public int processWithDelay(Player player) {
		quantity--;
		player.animate(new Animation(896));
		player.getSkills().addXp(Skills.CRAFTING, prod.getExperience());
		player.getInventory().deleteItem(prod.getItemsRequired().getId(), prod.getItemsRequired().getAmount());
		player.getInventory().addItem(prod.getProducedItem());
		player.getPackets().sendGameMessage(
				"You make a " + prod.getProducedItem().getDefinitions().getName().toLowerCase() + ".", true);
		if (quantity == 0)
			return -1;
		return 3;
	}

	@Override
	public void stop(Player player) {
		this.setActionDelay(player, 3);
	}
}
