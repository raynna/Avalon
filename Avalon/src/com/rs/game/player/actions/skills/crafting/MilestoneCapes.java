package com.rs.game.player.actions.skills.crafting;

import java.util.HashMap;
import java.util.Map;

import com.rs.game.Animation;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;

public class MilestoneCapes extends Action {

	public enum Products {

		MILESTONE_CAPE_10(10, 10, new Item(1759, 1), new Item(20754), 0),

		MILESTONE_CAPE_20(20, 20, new Item(1759, 2), new Item(20755), 1),

		MILESTONE_CAPE_30(30, 30, new Item(1759, 3), new Item(20756), 2),

		MILESTONE_CAPE_40(40, 40, new Item(1759, 4), new Item(20757), 3),

		MILESTONE_CAPE_50(50, 50, new Item(1759, 5), new Item(20758), 4),

		MILESTONE_CAPE_60(60, 60, new Item(1759, 6), new Item(20759), 5),

		MILESTONE_CAPE_70(70, 70, new Item(1759, 7), new Item(20760), 6),

		MILESTONE_CAPE_80(80, 80, new Item(1759, 8), new Item(20761), 7),

		MILESTONE_CAPE_90(90, 90, new Item(1759, 9), new Item(20762), 8);

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
	public int ticks;

	public MilestoneCapes(int slotId, int ticks) {
		this.prod = Products.forId(slotId);
		this.ticks = ticks;
	}

	@Override
	public boolean start(Player player) {
		if (prod == null || player == null) {
			player.sm("null");
			return false;
		}
		int levelReq = prod.getLevelRequired();
		for (int i = 0; i < 25; i++) {
			if (player.getSkills().getLevelForXp(i) < levelReq) {
				player.getPackets().sendGameMessage("You need a level of at least " + prod.getLevelRequired()
						+ " in all skills to create " + prod.getProducedItem().getDefinitions().getName());
				return false;
			}
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
		if (prod == null || player == null) {
			return false;
		}
		int levelReq = prod.getLevelRequired();
		for (int i = 0; i < 25; i++) {
			if (player.getSkills().getLevelForXp(i) < levelReq) {
				player.getPackets().sendGameMessage("You need a level of at least " + prod.getLevelRequired()
						+ " in all skills to create " + prod.getProducedItem().getDefinitions().getName());
				return false;
			}
		}
		if (!player.getInventory().containsItem(prod.getItemsRequired().getId(),
				prod.getItemsRequired().getAmount())) {
			player.getPackets().sendGameMessage("You need " + prod.getItemsRequired().getDefinitions().getName()
					+ " to create a " + prod.getProducedItem().getDefinitions().getName() + ".");
			return false;
		}
		return true;
	}

	@Override
	public int processWithDelay(Player player) {
		ticks--;
		player.animate(new Animation(896));
		if (prod.getExperience() > 0)
			player.getSkills().addXp(Skills.CRAFTING, prod.getExperience());
		player.getInventory().deleteItem(prod.getItemsRequired().getId(), prod.getItemsRequired().getAmount());
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
