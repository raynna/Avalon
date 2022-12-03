package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class ChaosDwarf extends MobRewardNodeBuilder {

	public ChaosDwarf() {
		super(new Object[] { "Chaos dwarf", 119 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(STEEL_FULL_HELM, 1), 
					node(MITHRIL_LONGSWORD, 1), 
					node(MITHRIL_SQ_SHIELD, 1), 
					node(WATER_RUNE, 10), 
					nodeB(AIR_RUNE, 12, 24), 
					node(MIND_RUNE, 37), 
					nodeB(COSMIC_RUNE, 3, 6), 
					node(COAL, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, nodeB(CHAOS_RUNE, 10, 37), 
					nodeB(NATURE_RUNE, 9, 18), 
					nodeB(LAW_RUNE, 3, 6), 
					node(MUDDY_KEY, 1), 
					node(MITHRIL_BAR, 1), 
					node(TOMATO, 1));
			break;
		case RARE:
			createNodeBatch(1, node(BLACK_FULL_HELM, 1), 
					nodeB(DEATH_RUNE, 3, 6), 
					node(GRIMY_RANARR, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(DRAGON_PICKAXE, 1));
			break;
		}
		addObj(BONES, 1);
		shakeTreasureTrail(player, MEDIUM_CLUE);
		shakeSummoningCharm(1, 9.5, 4.5, 2.5, 2.5);

	}
}
