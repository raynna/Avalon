package com.rs;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.Player;

/**
 * @author -Andreas 26 feb. 2020 15:29:18
 * @project Trinfix/Avalon
 * 
 */

public class CreationKiln {

	private transient Player player;

	public CreationKiln(Player player) {
		this.player = player;
	}

	private transient final int INTERFACE_ID = 813;
	private transient final int[] GLOBALCONFIG = { 583, 584, 585, 586, 587 };
	private transient final int[] clayIds = { 14182, 14184, 14186, 14188, 14190 };
	private transient final int[] QUALITY_COMP = { 99, 101, 103, 105, 107 };
	private transient final int CATEGORY_WEAPONS_COMP = 29, CATEGORY_POTIONS_COMP = 32;

	/**
	 * Static variables
	 */
	private transient static Category[] category = Category.values();
	private transient static Category currentCategory;
	private transient static Data[] data = Data.values();

	private enum Category {

		WEAPONS(29, 52),

		ARMOUR(30, 43),

		EQUIPMENT(31, 37),

		POTIONS(32, 64);

		private int categoryId, firstSlot;

		private Category(int categoryId, int firstSlot) {
			this.categoryId = categoryId;
			this.firstSlot = firstSlot;
		}

		public int getCategoryId() {
			return categoryId;
		}

		public int getFirstSlot() {
			return firstSlot;
		}
	}

	public enum Data {

		DAGGER(Category.WEAPONS, 14297),

		SCIMITAR(Category.WEAPONS, 14287),

		WARHAMMER(Category.WEAPONS, 14307),

		BOW(Category.WEAPONS, 14192),

		ARROWS(Category.WEAPONS, 14202, 15),

		ELEMENTAL_RUNES(Category.WEAPONS, 12850, 15),

		CATALYTIC_RUNES(Category.WEAPONS, 12851, 15),

		SUMMONING_POUCH(Category.WEAPONS, 14422),

		MAGIC_STAFF(Category.WEAPONS, 14377),

		SUMMONING_SCROLL(Category.WEAPONS, 14421, 1),

		HELM(Category.ARMOUR, 14367),

		PLATELEGS(Category.ARMOUR, 14357),

		PLATEBODY(Category.ARMOUR, 14347),

		COIF(Category.ARMOUR, 14411),

		CHAPS(Category.ARMOUR, 14401),

		HARDBODY(Category.ARMOUR, 14391),

		MAGE_HAT(Category.ARMOUR, 14337),

		ROBE_BOTTOM(Category.ARMOUR, 14327),

		ROBE_TOP(Category.ARMOUR, 14317),

		HATCHET(Category.EQUIPMENT, 14132),

		PICKAXE(Category.EQUIPMENT, 14122),

		HARPOON(Category.EQUIPMENT, 14142),

		BUTTERFLY_NET(Category.EQUIPMENT, 14152),

		BARRIER(Category.EQUIPMENT, 14172),

		FOOD(Category.EQUIPMENT, 14162),

		PRAYER(Category.POTIONS, 0), //TODO

		ENERGY(Category.POTIONS, 0),

		ATTACK(Category.POTIONS, 0),

		STRENGTH(Category.POTIONS, 0),

		RANGING(Category.POTIONS, 0),

		DEFENCE(Category.POTIONS, 0),

		MAGIC(Category.POTIONS, 0),

		SUMMONING(Category.POTIONS, 0),

		;

		private int baseId, amount;
		private Category category;

		Data(Category type, int baseId, int amount) {
			this.category = type;
			this.baseId = baseId;
			this.amount = amount;
		}

		Data(Category type, int baseId) {
			this(type, baseId, 0);
		}

		public static Data getData(int id) {
			int nextFirstSlot = 0;
			for (Data data : data) {
				if (data.getCategory() == currentCategory)
					break;
				nextFirstSlot++;
			}
			for (Data data : data) {
				if (data.getCategory() == currentCategory) {
					if (data.getCategory().getFirstSlot() + (data.ordinal() - nextFirstSlot) == id)
						return data;
				}
			}
			return null;
		}

		public Category getCategory() {
			return category;
		}

		public int getBaseItemId() {
			return baseId;
		}

		public int getAmount() {
			return amount;
		}
	}

	public void handleButton(int compId, int packetId) {
		if (compId >= QUALITY_COMP[0] && compId <= QUALITY_COMP[QUALITY_COMP.length - 1]) {
			selectClayQuality(compId);
			return;
		}
		if (compId >= CATEGORY_WEAPONS_COMP && compId <= CATEGORY_POTIONS_COMP) {
			for (Category type : category) {
				if (type.getCategoryId() == compId)
					setCategory(type);
			}
		} else {
			if (getCategory() == null) {
				player.sm("You don't have any category selected.");
				return;
			}
			Data data = Data.getData(compId);
			if (data == null)
				return;
			if (getQuality() == -1) {
				player.sm("You don't have any quality selected.");
				return;
			}
			switch (packetId) {
			case 14:// make-1
				addItem(data, 1);
				break;
			case 67:// make-5
				addItem(data, 5);
				break;
			case 5:// make-x
				player.getPackets().sendRunScript(108, new Object[] { "How many would you like to make?" });
				player.temporaryAttribute().put("CREATION_KILN_X", Boolean.TRUE);
				break;
			case 55:// make-all
				addItem(data, player.getInventory().getNumberOf(clayIds[getQuality() - 1]));
				break;
			}
		}
	}

	private void addItem(Data data, int amount) {
		int clayId = clayIds[getQuality() - 1];
		if (amount > player.getInventory().getAmountOf(clayId))
			amount = player.getInventory().getAmountOf(clayId);
		ItemDefinitions def = ItemDefinitions.getItemDefinitions(clayId);
		if (amount < 1) {
			player.sm("You don't have any " + def.getName() + ".");
			return;
		}
		Item item = data.getAmount() > 0 ? new Item(data.getBaseItemId(), (data.getAmount() * getQuality()) * amount)
				: new Item(data.getBaseItemId() + ((getQuality() - 1) * 2), amount);
		player.closeInterfaces();
		player.getInventory().deleteItem(clayId, amount);
		player.getInventory().addItem(item);
		player.sm("Added item: " + (item.getAmount() > 1 ? item.getAmount() + " x " : "") + item.getName() + ".");
	}

	private Category getCategory() {
		Category type = (Category) player.getTemporaryAttributtes().get("CATEGORY");
		if (type == null)
			return null;
		return type;
	}

	private int getQuality() {
		Integer quality = (Integer) player.getTemporaryAttributtes().get("QUALITY");
		if (quality == null)
			return -1;
		return quality;
	}

	private void setCategory(Category category) {
		player.getTemporaryAttributtes().put("CATEGORY", category);
		currentCategory = category;
	}

	private void selectClayQuality(int compId) {
		int quality = (compId - QUALITY_COMP[0]) / 2 + 1;
		if (!player.getInventory().containsOneItem(clayIds[quality - 1])) {
			player.sm("You don't have any " + ItemDefinitions.getItemDefinitions(clayIds[quality - 1]).getName() + ".");
			return;
		}
		for (int qualitycomp : QUALITY_COMP)
			player.getPackets().sendHideIComponent(INTERFACE_ID, qualitycomp - 1, true);
		player.getTemporaryAttributtes().put("QUALITY", quality);
		player.getPackets().sendHideIComponent(INTERFACE_ID, compId - 1, false);
	}

	public void openKilnInterface() {
		player.getInterfaceManager().sendInterface(813);
		for (int config : GLOBALCONFIG)
			player.getPackets().sendGlobalConfig(config, 0);
		int i = 0;
		for (int clay : clayIds) {
			if (player.getInventory().containsOneItem(clay)) {
				player.getPackets().sendGlobalConfig(GLOBALCONFIG[i], 1);
			}
			i++;
		}
		player.getPackets().sendHideIComponent(INTERFACE_ID, 16, false);
		player.getTemporaryAttributtes().put("QUALITY", -1);
		setCategory(Category.WEAPONS);
	}
}
