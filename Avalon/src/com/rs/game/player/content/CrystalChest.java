package com.rs.game.player.content;

import com.rs.Launcher;
import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * 
 * @author Andreas - AvalonPK
 * 
 */

public class CrystalChest {

	public static final Rewards[] items = Rewards.values();
	public static final double COMMON = 75.0;
	public static final double UNCOMMON = 25.0;
	public static final double RARE = 1.250;
	public static final double VERY_RARE = 0.125;
	public static final double SUPER_RARE = 0.075;

	public enum Rewards {

		DRAGON_BONES(537, COMMON), BABY_DRAGON_BONES(535, COMMON), BIG_BONES(533, COMMON), ANCIENT_STAFF(4675,
				COMMON), DRAGON_DAGGER(1215, COMMON), DRAGON_MED_HELM(1149,
						COMMON), GRANITE_MAUL(4153, COMMON), CLIMBING_BOOTS(3105, COMMON),

		TOOTH_KEY(985, UNCOMMON), LOOP_KEY(987, UNCOMMON), DRAGONSTONE(1615, UNCOMMON), DRAGON_BATTLEAXE(1377,
				UNCOMMON), DRAGON_HALBERD(3204, UNCOMMON), DRAGON_SCIMITAR(4587, UNCOMMON), AMULET_OF_GLORY(1712,
						UNCOMMON), NEITIZNOT(10828, UNCOMMON), DRAGON_BOOTS(11732, UNCOMMON),

		AHRIM_HOOD(4708, RARE), AHRIM_STAFF(4710, RARE), AHRIM_TOP(4712, RARE), AHRIM_BOTTOMS(4714, RARE), DHAROK_HELM(
				4716,
				RARE), DHAROK_AXE(4718, RARE), DHAROK_PLATEBODY(4720, RARE), DHAROK_PLATELEGS(4722, RARE), GUTHAN_HELM(
						4724,
						RARE), GUTHAN_SPEAR(4726, RARE), GUTHAN_BODY(4728, RARE), GUTHAN_LEGS(4730, RARE), KARIL_HOOD(
								4732, RARE), KARIL_CROSSBOW(4734, RARE), KARIL_BODY(4736, RARE), KARIL_CHAPS(4738,
										RARE), TORAG_HELM(4745, RARE), TORAG_HAMMERS(4747, RARE), TORAG_PLATEBODY(4749,
												RARE), TORAG_PLATELEGS(4751, RARE), VERAC_HELM(4753, RARE), VERAC_FLAIL(
														4755, RARE), VERAC_BRASSARD(4757, RARE), VERAC_SKIRT(4759,
																RARE), ABYSSAL_WHIP(4151, RARE), STAFF_OF_LIGHT(15486,
																		RARE), UNCUT_ONYX(6571, RARE), CRYSTAL_BOW(4212,
																				RARE), CRYSTAL_SHIELD(4224, RARE),

		ARMADYL_GODSWORD(11694, VERY_RARE), BANDOS_GODSWORD(11696, VERY_RARE), SARADOMIN_GODSWORD(11698,
				VERY_RARE), ZAMORAK_GODSWORD(11700, VERY_RARE), AMULET_OF_FURY(6585, VERY_RARE), SARADOMIN_SWORD(11730,
						VERY_RARE), BANDOS_TASSETS(11726, VERY_RARE), BANDOS_CHESTPLATE(11724,
								VERY_RARE), ARMADYL_HELMET(11718, VERY_RARE), ARMADYL_CHESTPLATE(11720,
										VERY_RARE), ARMADYL_LEGS(11722, VERY_RARE);

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

	public static void sendRewards(boolean bank, Player player) {
		Rewards reward = calculateRewards();
		Item item = new Item(reward.getItemId());
		player.getPackets().sendGameMessage("You sucessfully unlocked the chest and find a reward inside of it.");
		if (bank)
			player.getBank().addItem(reward.getItemId(), getRewardAmount(reward), true);
		else
			player.getInventory().addItem(reward.getItemId(), getRewardAmount(reward));
		if (reward.name().toLowerCase().contains("onyx") || reward.name().toLowerCase().contains("staff of l")
				|| reward.getChance() == SUPER_RARE || reward.getChance() == VERY_RARE) {
			World.sendWorldMessage("<img=7><col=36648b>News: " + player.getDisplayName() + " looted " + item.getName()
					+ " from a crystal chest.", false);
			//f (Settings.discordEnabled) {
			//	Launcher.getDiscordBot().getChannelByName("public-chat").sendMessage(":sparkles:  " + player.getDisplayName() + " looted " + item.getName()
			//	+ " from a crystal chest.");
			//}
			player.getAdventureLog().addActivity("After opening a crystal chest, I found a " + item.getName() + ".");
		}

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
		case DRAGON_BONES:
		case BABY_DRAGON_BONES:
			return Utils.random(10, 30);
		case BIG_BONES:
			return Utils.random(30, 70);
		default:
			return 1;
		}
	}
}
