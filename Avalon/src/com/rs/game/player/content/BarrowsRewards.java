package com.rs.game.player.content;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * 
 * @author Andreas - AvalonPK
 * 
 */

public class BarrowsRewards {

	public static final Rewards[] items = Rewards.values();
	public static final double NO_REWARD_CHANCE = 0;
	public static final double RARE = 1.50;
	public static final double COMMON = 75.0;
	public static final double UNCOMMON = 25.0;
	public static final double VERY_UNCOMMON = 15.0;

	public enum Rewards {

		AHRIM_HOOD(4708, RARE), AHRIM_STAFF(4710, RARE), AHRIM_TOP(4712, RARE), AHRIM_BOTTOMS(4714, RARE), DHAROK_HELM(
				4716, RARE), DHAROK_AXE(4718, RARE), DHAROK_PLATEBODY(4720, RARE), DHAROK_PLATELEGS(4722,
						RARE), GUTHAN_HELM(4724, RARE), GUTHAN_SPEAR(4726, RARE), GUTHAN_BODY(4728,
								RARE), GUTHAN_LEGS(4730, RARE), KARIL_HOOD(4732, RARE), KARIL_CROSSBOW(4734,
										RARE), KARIL_BODY(4736, RARE), KARIL_CHAPS(4738, RARE), TORAG_HELM(4745,
												RARE), TORAG_HAMMERS(4747, RARE), TORAG_PLATEBODY(4749,
														RARE), TORAG_PLATELEGS(4751, RARE), VERAC_HELM(4753,
																RARE), VERAC_FLAIL(4755, RARE), VERAC_BRASSARD(4757,
																		RARE), VERAC_SKIRT(4759, RARE);

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

	public static void sendRewards(Player player) {
		for (int i = 0; i < 3; i++) {
		Rewards reward = calculateRewards(player);
		player.getBarrowsRewards().add(new Item(reward.getItemId(), getRewardAmount(reward)));
		}
		/*Item item = new Item(reward.getItemId(), getRewardAmount(reward));
		if (item.getDefinitions().isStackable() || item.getDefinitions().isNoted()) {
			if (player.getInventory().hasFreeSlots())
				player.getInventory().addItem(item);
			else {
				if (player.getInventory().containsItem(item.getId(), 1))
					player.getInventory().addItem(item);
				else
					World.updateGroundItem(item, player.getLastWorldTile(), player, 60, 0);
			}
		} else {
			if (player.getInventory().getFreeSlots() >= item.getAmount())
				player.getInventory().addItem(item);
			else
				World.updateGroundItem(item, player.getLastWorldTile(), player, 60, 0);
		}
		if (reward.getChance() == RARE) {
			player.getPackets()
					.sendGameMessage("<col=FF614D>You found a " + item.getName() + " from the barrows chest.");
			player.getAdventureLog().addActivity("I have found " + player.grammar(item) + " " + item.getName() + "");
		}*/
	}

	private static Rewards calculateRewards(Player player) {
		while (true) {
			double chance = Utils.getRandomDouble(100);
			Rewards reward = Rewards.values()[Utils.getRandom(Rewards.values().length - 1)];
			if ((reward.getChance() + ((double) player.getBarrowsKillCount() * 0.5)) > chance)
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
