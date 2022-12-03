package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class CaveCrawler extends MobRewardNodeBuilder {

	public CaveCrawler() {
		super(new Object[] { "Cave crawler", 1600 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(EARTH_RUNE, 9, 10), 
					node(FIRE_RUNE, 12), 
					node(NATURE_RUNE, 1), 
					node(POTATO_SEED, 1, 4), 
					node(ONION_SEED, 1, 3), 
					node(CABBAGE_SEED, 1, 3), 
					node(TOMATO_SEED, 1, 3), 
					node(SWEETCORN_SEED, 1, 2), 
					node(STRAWBERRY_SEED, 1), 
					node(GRIMY_GUAM, 1, 2), 
					node(GRIMY_MARRENTILL, 1, 2), 
					node(GRIMY_TARROMIN, 1, 2), 
					node(GRIMY_HARRALANDER, 1, 2), 
					node(EYE_OF_NEWT, 1), 
					node(LIMPWURT_ROOT, 1), 
					node(WHITE_BERRIES, 1), 
					node(VIAL_OF_WATER, 1), 
					nodeB(COINS, 3, 8, 11, 29));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(WATERMELON_SEED, 1), 
					node(EVIL_TURNIP_SEED, 1, 4), 
					node(GRIMY_RANARR, 1, 2), 
					node(GRIMY_IRIT, 1, 2), 
					node(GRIMY_AVANTOE, 1, 2), 
					node(GRIMY_KWUARM, 1, 2), 
					node(RED_SPIDERS_EGGS, 1), 
					node(UNICORN_HORN_DUST, 1), 
					node(BRONZE_BOOTS, 1), 
					node(WEAPON_POISON, 1));
			break;
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(GRIMY_CADANTINE, 1, 2), 
					node(GRIMY_LANTADYME, 1, 2), 
					node(GRIMY_DWARF_WEED, 1, 2), 
					node(SNAPE_GRASS, 1));
			break;
		}
		shakeSummoningCharm(1, 9, 2, 0.95, 0.45);
	}
}
