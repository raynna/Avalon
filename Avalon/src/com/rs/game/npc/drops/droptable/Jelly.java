package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Jelly extends MobRewardNodeBuilder {

	public Jelly() {
		super(new Object[] { "Jelly", 1637 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(STEEL_BATTLEAXE, 1), 
					node(STEEL_2H_SWORD, 1), 
					node(STEEL_AXE, 1), 
					node(COINS, 11, 497));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(CHAOS_RUNE, 15), 
					node(DEATH_RUNE, 5), 
					node(MITHRIL_KITESHIELD, 1), 
					node(MITHRIL_BOOTS, 1), 
					node(GOLD_BAR, 1));
			break;
		case RARE:
			createNodeBatch(1, node(WATER_RUNE, 5, 21), 
					node(BLOOD_RUNE, 5), 
					node(RUNE_FULL_HELM, 1), 
					node(STEEL_PLATEBODY, 1), 
					node(THREAD, 10));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(RUNE_BATTLEAXE, 1));
			break;
		}
		shakeTreasureTrail(player, HARD_CLUE);
		shakeSummoningCharm(1, 7.5, 22, 3.5, 0.95);
	}
}
