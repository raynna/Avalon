package com.rs.game.player.actions.skills.woodcutting;

import java.util.HashMap;
import java.util.Map;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * @author -Andreas 17 feb. 2020 09:10:06
 * @project 1. Avalon
 * 
 */

public class BirdNest {

	private static final int EMPTY_NEST_ID = 5075, RED_NEST_ID = 5070, GREEN_NEST_ID = 5071, BLUE_NEST_ID = 5072,
			RAVEN_NEST_ID = 11966, SEED_NEST_1_ID = 5073, SEED_NEST_2_ID = 7413, RING_NEST_ID = 5074;

	private static final int RED_EGG_ID = 5076, GREEN_EGG_ID = 5078, BLUE_EGG_ID = 5077, RAVEN_EGG_ID = 11964;

	public enum Nest {

		RED_EGG_NEST(RED_NEST_ID, 1.0, NestReward.RED_EGG),

		GREEN_EGG_NEST(GREEN_NEST_ID, 1.0, NestReward.GREEN_EGG),

		BLUE_EGG_NEST(BLUE_NEST_ID, 1.0, NestReward.BLUE_EGG),

		RAVEN_EGG_NEST(RAVEN_NEST_ID, 1.0, NestReward.RAVEN_EGG),

		RING_NEST(RING_NEST_ID, 32.5, NestReward.GOLD_RING, NestReward.SAPPHIRE_RING, NestReward.EMERALD_RING,
				NestReward.RUBY_RING, NestReward.DIAMOND_RING),

		SEED_NEST_1(SEED_NEST_1_ID, 31.75, NestReward.ACORN, NestReward.WILLOW_SEED, NestReward.MAPLE_SEED,
				NestReward.YEW_SEED, NestReward.MAGIC_SEED, NestReward.APPLE_TREE_SEED, NestReward.BANANA_TREE_SEED,
				NestReward.ORANGE_TREE_SEED, NestReward.CURRY_TREE_SEED, NestReward.PINEAPPLE_SEED,
				NestReward.PAPAYA_TREE_SEED, NestReward.PALM_TREE_SEED, NestReward.CALQUAT_TREE_SEED,
				NestReward.SPIRIT_SEED),

		SEED_NEST_2(SEED_NEST_2_ID, 31.75, NestReward.SWEETCORN_SEED, NestReward.TOMATO_SEED, NestReward.LIMPWURT_SEED,

				NestReward.CABBAGE_SEED, NestReward.WATERMELON_SEED, NestReward.EVIL_TURNIP_SEED,
				NestReward.STRAWBERRY_SEED, NestReward.ACORN_2, NestReward.RANARR_SEED, NestReward.WILLOW_SEED_2,
				NestReward.MAPLE_SEED_2, NestReward.YEW_SEED_2, NestReward.MORCHELLA_MUSHROOM_SPORE,
				NestReward.MAGIC_SEED_2, NestReward.SPIRIT_SEED_2);

		private final int itemId;
		private final NestReward[] reward;
		private final double chance;

		private Nest(int itemId, double chance, NestReward... reward) {
			this.itemId = itemId;
			this.chance = chance;
			this.reward = reward;
		}

		private NestReward[] getRewards() {
			return reward;
		}

		private double getChance() {
			return chance;
		}

		private static Map<Integer, Nest> nests = new HashMap<Integer, Nest>();

		public static Nest forId(int itemId) {
			return nests.get(itemId);
		}

		static {
			for (final Nest nest : Nest.values()) {
				nests.put(nest.itemId, nest);
			}
		}
	}

	public enum NestReward {

		/**
		 * Egg nests
		 */

		RED_EGG(new Item(RED_EGG_ID)),

		GREEN_EGG(new Item(GREEN_EGG_ID)),

		BLUE_EGG(new Item(BLUE_EGG_ID)),

		RAVEN_EGG(new Item(RAVEN_EGG_ID)),

		/**
		 * Ring nest
		 */

		GOLD_RING(new Item(1635), 33.5),

		SAPPHIRE_RING(new Item(1637), 41.5),

		EMERALD_RING(new Item(1639), 14.5),

		RUBY_RING(new Item(1641), 8.5),

		DIAMOND_RING(new Item(1643), 1.0),

		/**
		 * Seed nest (1)
		 */

		ACORN(new Item(5312), 21.5),

		WILLOW_SEED(new Item(5313), 13.5),

		MAPLE_SEED(new Item(5314), 5.5),

		YEW_SEED(new Item(5315), 3.0),

		MAGIC_SEED(new Item(5316), 1.0),

		APPLE_TREE_SEED(new Item(5283), 16.5),

		BANANA_TREE_SEED(new Item(5284), 11.0),

		ORANGE_TREE_SEED(new Item(5285), 8.5),

		CURRY_TREE_SEED(new Item(5286), 6.5),

		PINEAPPLE_SEED(new Item(5287), 4.0),

		PAPAYA_TREE_SEED(new Item(5288), 3.0),

		PALM_TREE_SEED(new Item(5289), 2.0),

		CALQUAT_TREE_SEED(new Item(5290), 2.0),

		SPIRIT_SEED(new Item(5317), 2.0),

		/**
		 * Seed nest (2)
		 */

		SWEETCORN_SEED(new Item(5320, 3), 16.5),

		TOMATO_SEED(new Item(5322, 6), 13.5),

		LIMPWURT_SEED(new Item(5100, 2), 12.5),

		CABBAGE_SEED(new Item(5324, 9), 12.5),

		WATERMELON_SEED(new Item(5321, 2), 11.5),

		EVIL_TURNIP_SEED(new Item(12148, 2), 11.5),

		STRAWBERRY_SEED(new Item(5323, 3), 10.5),

		ACORN_2(new Item(5312), 4.0),

		RANARR_SEED(new Item(5295), 2.5),

		WILLOW_SEED_2(new Item(5313), 2.5),

		MAPLE_SEED_2(new Item(5314), 1.0),

		YEW_SEED_2(new Item(5315), 1.0),

		MORCHELLA_MUSHROOM_SPORE(new Item(21620, 2), 1.0),

		MAGIC_SEED_2(new Item(5316), 0.15),

		SPIRIT_SEED_2(new Item(5317), 0.15);

		private final Item item;
		private final double chance;

		private NestReward(Item item, double chance) {
			this.item = item;
			this.chance = chance;
		}

		private NestReward(Item item) {
			this(item, 100.0);
		}

		private double getChance() {
			return chance;
		}

		private Item getItem() {
			return item;
		}

		private ItemDefinitions getDefinitions() {
			return ItemDefinitions.getItemDefinitions(item.getId());
		}
	}

	private static NestReward calculateReward(Nest nest) {
		NestReward rewards = nest.getRewards()[Utils.getRandom(nest.getRewards().length - 1)];
		if (rewards.getChance() == 100.0) {
			return rewards;
		}
		boolean r = false;
		while (!r) {
			double roll = Utils.getRandomDouble(100);
			if (roll <= rewards.getChance()) {
				r = true;
			} else {
				rewards = nest.getRewards()[Utils.getRandom(nest.getRewards().length - 1)];
			}
		}
		return rewards;
	}

	private static Nest[] nests = Nest.values();

	private static Nest getRandomNest() {
		Nest nest = nests[Utils.getRandom(nests.length - 1)];
		boolean r = false;
		while (!r) {
			double roll = Utils.getRandomDouble(100);
			if (roll <= nest.getChance()) {
				r = true;
			} else {
				nest = nests[Utils.getRandom(nests.length - 1)];
			}
		}
		return nest;
	}

	public static boolean searchNest(Player player, int itemId, int slot) {
		Nest nest = Nest.forId(itemId);
		if (nest == null)
			return false;
		NestReward reward = calculateReward(nest);
		if (reward == null)// shouldn't happen
			return false;
		if (!player.getInventory().hasFreeSlots()) {
			player.message("You don't have enough inventory space to search this.");
			return false;
		}
		Item item = reward.getItem();
		player.message("You find " + (reward.getItem().getAmount() > 1 ? reward.getItem().getAmount() + " x " : "")
				+ reward.getDefinitions().getName() + " inside the bird's nest.");
		player.getInventory().deleteItem(slot, new Item(itemId));
		player.getInventory().addItem(EMPTY_NEST_ID, 1);
		player.getInventory().addItem(item);
		return true;
	}

	public static boolean spawnBirdNest(Player player, WorldTile tile) {
		Nest nest = getRandomNest();
		if (nest == null)
			return false;
		Item item = new Item(nest.itemId);
		World.updateGroundItem(item, tile, player, 60, 2);
		player.getPackets().sendGameMessage("A bird's nest falls out of the tree!", true);
		return true;
	}

}
