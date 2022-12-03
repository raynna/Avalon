package com.rs.game.player.content;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * 
 * @author Andreas - AvalonPK
 * 
 */

public class MysteryBox {

	public static final Rewards[] items = Rewards.values();
	public static final double NO_REWARD_CHANCE = 0;
	public static final double RARE = 1.0;
	public static final double COMMON = 75.0;
	public static final double UNCOMMON = 15.0;

	public enum Rewards {

		// MOGRE COSMETICS
		FLIPPERS(6666, COMMON),

		// CHICKEN SUIT
		CHICKEN_HAT(11021, COMMON), CHICKEN_LEGS(11022, COMMON), CHICKEN_WINGS(11020, COMMON), CHICKEN_FEET(11019,
				COMMON),

		// SKELETON COSTUME
				SKELETON_BOOTS(9921, COMMON), SKELETON_GLOVES(6665, COMMON), SKELETON_LEGS(9923,
						COMMON), SKELETON_SHIRT(9924, COMMON), SKELETON_MASK(9925, COMMON),

		// DOCTOR & NURSE
						DOCTORS_HAT(6547, COMMON), NURSE_HAT(6548, COMMON),

		// CHRISMAS 2006 HAT & SCARF
						BOBBLE_HAT(6856, COMMON), BOBBLE_SCARF(6857, COMMON), JESTER_HAT(6858,
								COMMON), JESTER_SCARF(6859, COMMON), TRIJESTER_HAT(6860, COMMON), TRIJESTER_SCARF(6861,
										COMMON), WOLLY_HAT(6862, COMMON), WOLLY_SCARF(6863, COMMON),

		// BLUE RUNECRAFTING COSTUME
										BLUE_RUNECRAFTER_HAT(13625, UNCOMMON), BLUE_RUNECRAFTER_TOP(13624,
												UNCOMMON), BLUE_RUNECRAFTER_BOTTOM(13627, UNCOMMON),

		// GREEN RUNECRAFTING COSTUME
												GREEN_RUNECRAFTER_HAT(13620, UNCOMMON), GREEN_RUNECRAFTER_TOP(13619,
														UNCOMMON), GREEN_RUNECRAFTER_BOTTOM(13622, UNCOMMON),

		// YELLOW RUNECRAFTING COSTUME
														YELLOW_RUNECRAFTER_HAT(13615, UNCOMMON), YELLOW_RUNECRAFTER_TOP(
																13614,
																UNCOMMON), YELLOW_RUNECRAFTER_BOTTOM(13617, UNCOMMON),

		// REINDEER HAT
																REINDEER_HAT(10507, COMMON),

		// MIME
																MIME_MASK(3057, COMMON), MIME_TOP(3058,
																		COMMON), MIME_LEGS(3059, COMMON), MIME_GLOVES(
																				3060, COMMON), MIME_BOOTS(3061, COMMON),

		// FROG MASK
																				FROG_MASK(6188, COMMON),

		// JACK LANTERN MASK
																				JACK_LANTERN_MASK(9920, COMMON),

		;

		private int itemId;

		private double chance;

		private Rewards(int itemId, double chance) {
			this.itemId = itemId;
			this.chance = chance;
		}

		public int getItemId() {
			return itemId;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}

		public double getChance() {
			return chance;
		}

		public void setChance(double chance) {
			this.chance = chance;
		}

	}

	public static void sendRewards(Player player, int slotId) {
		Rewards reward = calculateRewards();
		player.getInventory().deleteItem(slotId, new Item(6199));
		player.getInventory().addItem(reward.getItemId(), getRewardAmount(reward));

	}

	private static Rewards calculateRewards() {
		while (true) {
			double chance = Utils.getRandomDouble(100);
			Rewards reward = Rewards.values()[Utils.getRandom(Rewards.values().length - 1)];
			if ((reward.getChance()) > chance)
				return reward;
			else
				continue;
		}
	}

	private static int getRewardAmount(Rewards reward) {
		switch (reward) {
		default:
			return 1;
		}
	}
}
