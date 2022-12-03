package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class BlackKnight extends MobRewardNodeBuilder {

	public BlackKnight() {
		super(new Object[] { "Black Knight", -1 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(IRON_SWORD, 1), 
					node(IRON_FULL_HELM, 1), 
					node(STEEL_MACE, 1), 
					node(GRIMY_GUAM, 1), 
					node(GRIMY_MARRENTILL, 1), 
					node(GRIMY_TARROMIN, 1), 
					node(GRIMY_HARRALANDER, 1), 
					node(COINS, 1, 80), 
					node(STEEL_BAR, 1), 
					node(POT_OF_FLOUR, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, nodeB(MIND_RUNE, 2, 9), 
					node(EARTH_RUNE, 10), 
					node(BODY_RUNE, 9), 
					node(COSMIC_RUNE, 7), 
					nodeB(CHAOS_RUNE, 4, 6), 
					node(DEATH_RUNE, 2), 
					nodeB(LAW_RUNE, 3, 4), 
					node(POTATO_SEED, 4), 
					node(ONION_SEED, 4), 
					node(MITHRIL_ARROW, 3), 
					node(GRIMY_RANARR, 1), 
					node(GRIMY_IRIT, 1), 
					node(GRIMY_AVANTOE, 1), 
					node(GRIMY_KWUARM, 1), 
					node(GRIMY_CADANTINE, 1), 
					node(GRIMY_LANTADYME, 1), 
					node(GRIMY_DWARF_WEED, 1), 
					node(BREAD, 1), 
					node(TIN_ORE, 1));
			break;
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(NATURE_RUNE, 6), 
					node(CABBAGE_SEED, 4), 
					node(TOMATO_SEED, 3), 
					node(SWEETCORN_SEED, 3), 
					nodeB(STRAWBERRY_SEED, 2, 3), 
					node(WATERMELON_SEED, 2), 
					node(BLACK_FULL_HELM, 1), 
					node(BLACK_2H_SWORD, 1), 
					node(BLACK_SWORD, 1));
			break;
		}
		addObj(BONES, 1);
		shakeSummoningCharm(1, 3.5, 1, 0.8, 0.7);

	}
}
