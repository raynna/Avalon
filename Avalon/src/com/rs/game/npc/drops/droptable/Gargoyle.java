package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Gargoyle extends MobRewardNodeBuilder {

	public Gargoyle() {
		super(new Object[] { "Gargoyle", 1610 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, nodeB(FIRE_RUNE, 75, 150), 
					node(CHAOS_RUNE, 30), 
					node(GOLD_ORE_NOTED, 10, 20), 
					node(PURE_ESSENCE_NOTED, 150), 
					node(STEEL_BAR_NOTED, 15), 
					node(COINS, 400, 800), 
					node(COINS, 500, 1000));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(ADAMANT_PLATELEGS, 1), 
					node(RUNE_FULL_HELM, 1), 
					node(RUNE_2H_SWORD, 1), 
					node(DEATH_RUNE, 15), 
					node(GOLD_BAR_NOTED, 10, 15), 
					node(MITHRIL_BAR_NOTED, 15), 
					node(RUNITE_ORE, 1), 
					node(COINS, 10000));
			break;
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(ADAMANT_BOOTS, 1), 
					node(GRANITE_MAUL, 1), 
					node(MYSTIC_ROBE_TOP_DARK, 1), 
					node(RUNE_BATTLEAXE, 1), 
					node(RUNE_PLATELEGS, 1));
			break;
		}
		shakeTreasureTrail(player, HARD_CLUE);
		shakeSummoningCharm(1, 9.5, 5, 5, 7);
	}
}
