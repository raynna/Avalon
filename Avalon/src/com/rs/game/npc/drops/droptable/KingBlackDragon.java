package com.rs.game.npc.drops.droptable;

import com.rs.game.item.Item;
import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class KingBlackDragon extends MobRewardNodeBuilder {

	public KingBlackDragon() {
		super(new Object[] { "King black dragon", 50 });
	}

	@Override
	public void populate(Player player) {
		if (shake(player, 200)) {
			createNodeBatch(1, node(DRACONIC_VISAGE, 1));
		} else if (shake(player, 64)) {
			createNodeBatch(1, node(DRAGON_PICKAXE, 1));
		} else
			switch (rarityNode(player)) {
			case -1:
			case COMMON:
				createNodeBatch(2, node(RUNE_LONGSWORD, 1), node(ADAMANT_PLATEBODY, 1), node(AIR_RUNE, 300),
						node(IRON_ARROW, 690), node(RUNITE_BOLTS, 10, 20), node(YEW_LOGS_NOTED, 150),
						node(AMULET_OF_POWER, 1));
				break;
			case UNCOMMON:
				createNodeBatch(2, node(ADAMANT_KITESHIELD, 1), node(FIRE_RUNE, 300), node(LAW_RUNE, 30),
						node(BLOOD_RUNE, 30), node(ADAMANT_BAR, 3), node(RUNITE_BAR, 1), node(GOLD_ORE_NOTED, 100),
						node(DRAGON_ARROWTIPS, 5, 14), node(DRAGON_DART_TIP, 5, 14),
						node(RUNITE_LIMBS, 1), node(SHARK, 4));
				break;
			case RARE:
			case VERY_RARE:
				createNodeBatch(1, node(DRAGON_MED_HELM, 1), node(KBD_HEADS_7980, 1));
				break;
			}
		addObj(DRAGON_BONES, 1);// dragon bones
		addObj(BLACK_DRAGONHIDE, 2);// dragonhide
		shakeTreasureTrail(player, HARD_CLUE);
		shakeTreasureTrail(player, ELITE_CLUE);
		shakeSummoningCharm(4, 5.0, 4.0, 61.5, 1.45);

	}
}
