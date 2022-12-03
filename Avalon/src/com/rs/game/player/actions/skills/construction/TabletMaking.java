package com.rs.game.player.actions.skills.construction;

import com.rs.game.Animation;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.combat.Magic;

public class TabletMaking {

	//12 = 4 -7, 13 = 3-7, 10 = 2-7, 8 = 6-7

	private static final int SOFT_CLAY = 1761;
	private static final int[][] ENABLED_SLOTS =
	{ //correct
	{ 9, 13 },
	{ 9, 11, 10, 13 },
	{ 6, 9, 13, 1 },
	{ 9, 11, 10, 13, 0, 5 },
	{ 6, 8, 9, 13, 4, 1 },
	{ 11, 9, 10, 13, 3, 0, 14, 12 },
	{ 7, 6, 5, 8, 9, 4, 13, 1, 2 }, };
	private static final int[] REQUIRED_LEVELS =
	{ //levels are correct

	51, 15, 60, 45, 57, 40, 68, 27, 87, 49, 7, 37, 31, 25, 58 };
	private static final int[][] REQUIRED_RUNES =
	{//correct

	{ SOFT_CLAY, 1, 555, 2, 563, 2 },
	{ SOFT_CLAY, 1, 555, 2, 557, 2, 561, 2 },
	{ SOFT_CLAY, 1, 555, 4, 557, 4, 561, 2 },
	{ SOFT_CLAY, 1, 556, 5, 563, 2 },
	{ 564, 1, SOFT_CLAY, 557, 10 },
	{ 564, 1, SOFT_CLAY, 1, 555, 15, 557, 15 },
	{ 564, 1, SOFT_CLAY, 556, 3 },
	{ 564, 1, SOFT_CLAY, 1, 557, 20, 554, 20 },
	{ 564, 1, SOFT_CLAY, 1, 554, 5 },
	{ 564, 1, SOFT_CLAY, 1, 555, 1 },
	{ SOFT_CLAY, 1, 556, 3, 555, 1, 563, 1 },
	{ SOFT_CLAY, 1, 556, 3, 557, 1, 563, 1 },
	{ SOFT_CLAY, 1, 556, 1, 557, 1, 563, 1 },
	{ SOFT_CLAY, 1, 556, 3, 554, 1, 563, 1 },
	{ SOFT_CLAY, 1, 557, 2, 563, 2 } };

	private static final int[] PRODUCTS =
	{ 8011, 8014, 8015, 8010, 8019, 8020, 8017, 8021, 8018, 8016, 8009, 8008, 8013, 8007, 8012 };

	private static final double[] TABLET_EXPERIENCE =
	{ 63.5, 10.5, 35.5, 55.5, 33.3, 58.5, 25.1, 76.5, 35.5, 15.5, 48, 25, 30, 35, 66.5 };

	public static void openTabInterface(Player player, int index) {
		player.getPackets().sendCSVarInteger(943, index + 1);
		player.getInterfaceManager().sendInterface(400);
		player.getTemporaryAttributtes().put("tablet_index", index);
		player.animate(new Animation(3652));
	}

	public static void handleTabletCreation(final Player player, int componentId, int amount) {
		player.closeInterfaces();
		final int index = (int) player.getTemporaryAttributtes().get("tablet_index");
		final int slot = componentId - 2;
		for (int enabledSlot : ENABLED_SLOTS[index]) {
			if (enabledSlot == slot) {
				int requiredLevel = REQUIRED_LEVELS[slot];
				if (player.getSkills().getLevel(Skills.MAGIC) < requiredLevel) {
					player.getPackets().sendGameMessage("You need a Magic level of " + requiredLevel + " in order to create this tablet.");
					return;
				}
				int realAmount = 0;
				for (int loop = 0; loop < amount; loop++) {
					if (!Magic.checkRunes(player, false, REQUIRED_RUNES[slot]))
						return;
					realAmount++;
					Magic.checkRunes(player, true, REQUIRED_RUNES[slot]);
					player.getInventory().addItem(new Item(PRODUCTS[slot], 1));

				}
				player.getSkills().addXp(Skills.MAGIC, TABLET_EXPERIENCE[slot] * realAmount);
				player.lock(2);
				player.animate(new Animation(3645));
				player.getTemporaryAttributtes().remove("tablet_index");
				return;
			}
		}
		player.getPackets().sendGameMessage("You cannot create that tablet, please select another.");
	}

}
