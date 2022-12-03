package com.rs.game.player.actions.skills.crafting;

import java.util.HashMap;
import java.util.Map;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.utils.Utils;

/**
 * 
 * @author Andreas - AvalonPK
 * 
 */

public class GanodermicCrafting extends Action {

	public final Animation CRAFT_ANIMATION = new Animation(1249);
	public static final Item NEEDLE = new Item(1733);
	public static final Item THREAD = new Item(1734);
	private int quantity;
	private ArmourData data;
	private int removeThread = 5;

	public static final int FLAKES[] = { 22449, 22450, 22451 };

	public static final int PRODUCTS[][] = { { 22458, 22466, 22462 }, { 22470, 22478, 22474 },
			{ 22482, 22490, 22486 } };

	public enum ArmourData {
		FUNGAL_VISOR(22449, 300, 22458, 3, 10),

		FUNGAL_PONCHO(22449, 2500, 22466, 21, 50),

		FUNGAL_LEGGINGS(22449, 1000, 22462, 12, 30),

		GRIFOLIC_VISOR(22450, 400, 22470, 65, 40),

		GRIFOLIC_PONCHO(22450, 3500, 22478, 78, 350),

		GRIFOLIC_LEGGINGS(22450, 1200, 22474, 72, 100),

		GANODERMIC_VISOR(22451, 500, 22482, 86, 100),

		GANODERMIC_PONCHO(22451, 5000, 22490, 98, 1000),

		GANODERMIC_LEGGINGS(22451, 1500, 22486, 92, 300);

		private int itemId, flakesAmount, finalProduct, requiredLevel;
		private double experience;
		private String name;

		private static Map<Integer, ArmourData> armourItems = new HashMap<Integer, ArmourData>();

		public static ArmourData forId(int id) {
			return armourItems.get(id);
		}

		static {
			for (ArmourData armour : ArmourData.values()) {
				armourItems.put(armour.finalProduct, armour);
			}
		}

		private ArmourData(int itemId, int flakesAmount, int finalProduct, int requiredLevel, int experience) {
			this.itemId = itemId;
			this.flakesAmount = flakesAmount;
			this.finalProduct = finalProduct;
			this.requiredLevel = requiredLevel;
			this.experience = experience;
			this.name = ItemDefinitions.getItemDefinitions(getFinalProduct()).getName();
		}

		public int getItemId() {
			return itemId;
		}

		public int getFlakesAmount() {
			return flakesAmount;
		}

		public int getFinalProduct() {
			return finalProduct;
		}

		public int getRequiredLevel() {
			return requiredLevel;
		}

		public double getExperience() {
			return experience;
		}

		public String getName() {
			return name;
		}
	}

	public static boolean handleItemOnItem(Player player, Item itemUsed, Item usedWith) {
		for (int i = 0; i < FLAKES.length; i++) {
			if (itemUsed.getId() == FLAKES[i] || usedWith.getId() == FLAKES[i]) {
				player.temporaryAttribute().put("armourType", FLAKES[i]);
				int index = getIndex(player);
				if (index == -1)
					return true;
				player.getDialogueManager().startDialogue("GanodermicCraftingD", ArmourData.forId(PRODUCTS[index][0]),
						ArmourData.forId(PRODUCTS[index][1]), ArmourData.forId(PRODUCTS[index][2]));
				return true;
			}
		}
		return false;
	}

	public static int getIndex(Player player) {
		int armour = (Integer) player.temporaryAttribute().get("armourType");
		if (armour == FLAKES[0])
			return 0;
		if (armour == FLAKES[1])
			return 1;
		if (armour == FLAKES[2])
			return 2;
		return -1;
	}

	public GanodermicCrafting(ArmourData data, int quantity) {
		this.data = data;
		this.quantity = quantity;
	}

	@Override
	public boolean start(Player player) {
		if (!checkAll(player))
			return false;
		setActionDelay(player, 1);
		player.animate(CRAFT_ANIMATION);
		return true;
	}

	private boolean checkAll(Player player) {
		if (!player.getInventory().hasFreeSlots()) {
			player.getPackets().sendGameMessage("You don't have enough inventory space.");
			return false;
		}
		if (data.getRequiredLevel() > player.getSkills().getLevel(Skills.CRAFTING)) {
			player.getPackets().sendGameMessage(
					"You need a crafting level of " + data.getRequiredLevel() + " to craft this armour.");
			return false;
		}
		if (player.getInventory().getItems().getNumberOf(data.getItemId()) < data.getFlakesAmount()) {
			player.getPackets().sendGameMessage("You don't have enough amount of flakes in your inventory.");
			return false;
		}
		if (!player.getInventory().getItems().containsOne(THREAD)) {
			player.getPackets().sendGameMessage("You need a thread to do this.");
			return false;
		}
		if (!player.getInventory().getItems().containsOne(NEEDLE)) {
			player.getPackets().sendGameMessage("You need a needle to craft this.");
			return false;
		}
		if (!player.getInventory().containsOneItem(data.getItemId())) {
			player.getPackets().sendGameMessage("You've ran out of "
					+ ItemDefinitions.getItemDefinitions(data.getItemId()).getName().toLowerCase() + ".");
			return false;
		}
		return true;
	}

	@Override
	public boolean process(Player player) {
		return checkAll(player);
	}

	@Override
	public int processWithDelay(Player player) {
		player.getInventory().deleteItem(data.getItemId(), data.getFlakesAmount());
		player.getInventory().addItem(data.getFinalProduct(), 1);
		player.getSkills().addXp(Skills.CRAFTING, data.getExperience());
		player.getPackets().sendGameMessage("You make a " + data.getName().toLowerCase() + ".");
		quantity--;
		removeThread--;
		if (removeThread == 0) {
			removeThread = 5;
			player.getInventory().removeItems(THREAD);
			player.getPackets().sendGameMessage("You use up a reel of your thread.");
		}
		if (Utils.getRandom(30) <= 3) {
			player.getInventory().removeItems(NEEDLE);
			player.getPackets().sendGameMessage("Your needle has broken.");
		}
		if (quantity <= 0)
			return -1;
		player.animate(CRAFT_ANIMATION);
		return 2;
	}

	@Override
	public void stop(final Player player) {
		setActionDelay(player, 2);
	}

}
