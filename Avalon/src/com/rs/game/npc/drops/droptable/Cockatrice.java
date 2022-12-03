package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Cockatrice extends MobRewardNodeBuilder {

	public Cockatrice() {
		super(new Object[] { "Cockatrice", 1620 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(IRON_SWORD, 1), 
					node(STEEL_DAGGER, 1), 
					node(STEEL_LONGSWORD, 1), 
					node(WATER_RUNE, 1, 2), 
					node(FIRE_RUNE, 7), 
					nodeB(NATURE_RUNE, 2, 4, 6), 
					nodeB(LAW_RUNE, 2, 3), 
					node(GRIMY_GUAM, 1), 
					node(GRIMY_MARRENTILL, 1), 
					node(GRIMY_TARROMIN, 1), 
					node(GRIMY_HARRALANDER, 1), 
					node(GRIMY_IRIT, 1), 
					node(GRIMY_AVANTOE, 1), 
					node(POTATO_SEED, 4), 
					node(ONION_SEED, 4), 
					node(CABBAGE_SEED, 4), 
					node(TOMATO_SEED, 3), 
					node(SWEETCORN_SEED, 3), 
					node(STRAWBERRY_SEED, 2), 
					nodeB(COINS, 1, 5, 15, 28, 42, 62, 309), 
					node(LIMPWURT_ROOT, 1), 
					node(12109, 1));//cockatrice egg
			break;
		case UNCOMMON:
			createNodeBatch(1, node(IRON_JAVELIN, 5), 
					node(DEATH_RUNE, 50));
			break;
		case RARE:
			createNodeBatch(1, node(STEEL_PLATELEGS, 1), 
					node(IRON_BOOTS, 1), 
					node(MYSTIC_BOOTS_LIGHT, 1), 
					node(GRIMY_RANARR, 1), 
					node(GRIMY_KWUARM, 1), 
					node(GRIMY_CADANTINE, 1), 
					node(GRIMY_LANTADYME, 1), 
					node(WATERMELON_SEED, 2), 
					node(RANARR_SEED, 1), 
					node(MARIGOLD_SEED, 1), 
					node(MITHRIL_ORE, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(RUNE_MED_HELM, 1), 
					node(RUNE_CHAINBODY, 1), 
					node(GRIMY_DWARF_WEED, 1), 
					node(COCKATRICE_HEAD, 1));
			break;
		}
		addObj(BONES, 1);
		shakeTreasureTrail(player, MEDIUM_CLUE);
		shakeSummoningCharm(1, 2, 7, 1, 0.35);
	}
}
