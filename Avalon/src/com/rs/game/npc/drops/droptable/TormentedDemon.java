package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class TormentedDemon extends MobRewardNodeBuilder {

	public TormentedDemon() {
		super(new Object[] { "Tormented demon", 8349, 8350, 8351 });
	}

	@Override
	public void populate(Player player) {
		if (shake(player, 150)) {
			createNodeBatch(1, node(14472, 1), node(14474, 1), node(14476, 1));
		} else if (shake(player, 100)) {
			createNodeBatch(1, node(14484, 1));
		} else
			switch (rarityNode(player)) {
			case -1:
			case COMMON:
				createNodeBatch(1, node(ADAMANT_BATTLEAXE, 1),

						node(RUNE_MACE, 1),

						node(RUNE_SWORD, 1),

						node(RUNE_SQ_SHIELD, 1),

						node(SUPER_DEFENCE3_163, 1, 6),

						node(AVANTOE_SEED, 1, 2),

						node(BELLADONNA_SEED, 1),

						node(BITTERCAP_MUSHROOM_SEED, 1),

						node(HARRALANDER_SEED, 1, 2),

						node(JANGERBERRY_SEED, 1, 3),

						node(KWUARM_SEED, 1, 2),

						node(MARRENTILL_SEED, 1, 2),

						node(WILDBLOOD_SEED, 1, 3),

						node(GRIMY_AVANTOE, 1, 11),

						node(GRIMY_CADANTINE, 1, 11),

						node(GRIMY_GUAM, 1, 11),

						node(GRIMY_HARRALANDER, 1, 11),

						node(GRIMY_IRIT, 1, 11),

						node(GRIMY_KWUARM, 1, 11),

						node(GRIMY_LANTADYME, 1, 11),

						node(GRIMY_MARRENTILL, 1, 11),

						node(GRIMY_TARROMIN, 1, 11),

						node(DIAMOND_NOTED, 3, 5),

						node(ADAMANT_BAR_NOTED, 3, 7),

						node(DEATH_RUNE, 20, 40),

						node(LAVA_RUNE, 20, 45),

						node(LAW_RUNE, 20, 45),

						node(FIRE_TALISMAN, 2, 5),

						node(COINS, 3000, 15000));
				break;
			case UNCOMMON:
				createNodeBatch(1, node(RUNE_CHAINBODY, 1),

						node(RUNE_MED_HELM, 1),

						node(SHARK, 5, 15),

						node(PRAYER_POTION3, 1, 3),

						node(CACTUS_SEED, 4, 10),

						node(DWARF_WEED_SEED, 1, 2),

						node(LANTADYME_SEED, 1, 2),

						node(LIMPWURT_SEED, 1, 2),

						node(RANARR_SEED, 1, 3),

						node(SPIRIT_WEED_SEED, 1, 2),

						node(STRAWBERRY_SEED, 1, 4),

						node(TOADFLAX_SEED, 1, 2),

						node(WHITEBERRY_SEED, 1, 3),

						node(GRIMY_DWARF_WEED, 1, 11),

						node(GRIMY_RANARR, 1, 11),

						node(BLOOD_RUNE, 10, 20));
				break;
			case RARE:
				createNodeBatch(1, node(CADANTINE_SEED, 1, 2));
				break;
			case VERY_RARE:
				createNodeBatch(1, node(RUNE_2H_SWORD, 1), node(RUNE_BATTLEAXE, 1));
				break;
			}
		addObj(INFERNAL_ASHES, 1);
		shakeTreasureTrail(player, ELITE_CLUE);
		shakeTreasureTrail(player, HARD_CLUE);
		shakeSummoningCharm(3, 20.5, 10.5, 20.5, 36.5);

	}
}
