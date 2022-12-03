package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Hobgoblin extends MobRewardNodeBuilder {

	public Hobgoblin() {
		super(new Object[] { "Hobgoblin", -1 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(BRONZE_SPEAR, 1), 
					node(IRON_JAVELIN, 5), 
					node(IRON_SWORD, 1), 
					node(POTATO_SEED, 4, 8), 
					node(ONION_SEED, 4), 
					node(CABBAGE_SEED, 4), 
					node(SWEETCORN_SEED, 3), 
					node(STRAWBERRY_SEED, 1, 2), 
					node(HARRALANDER_SEED, 1), 
					node(TARROMIN_SEED, 1), 
					node(GRIMY_GUAM, 1), 
					node(GRIMY_MARRENTILL, 1), 
					node(GRIMY_TARROMIN, 1), 
					node(GRIMY_HARRALANDER, 1), 
					node(COINS, 1, 71), 
					node(LIMPWURT_ROOT, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(IRON_LONGSWORD, 1), 
					node(IRON_SPEAR, 1), 
					node(STEEL_SPEAR, 1), 
					node(STEEL_LONGSWORD, 1), 
					node(STEEL_DAGGER, 1), 
					node(WATER_RUNE, 2), 
					node(BODY_RUNE, 6), 
					node(FIRE_RUNE, 7), 
					node(COSMIC_RUNE, 2), 
					node(CHAOS_RUNE, 3), 
					node(NATURE_RUNE, 4), 
					node(LAW_RUNE, 2), 
					node(TOMATO_SEED, 3), 
					node(WATERMELON_SEED, 1, 2), 
					node(JUTE_SEED, 1), 
					node(WILDBLOOD_SEED, 1), 
					node(JANGERBERRY_SEED, 1), 
					node(WHITEBERRY_SEED, 1, 2), 
					node(POISON_IVY_SEED, 1), 
					node(BITTERCAP_MUSHROOM_SEED, 1), 
					node(CACTUS_SEED, 1), 
					node(BELLADONNA_SEED, 1), 
					node(LIMPWURT_SEED, 1), 
					node(TOADFLAX_SEED, 1), 
					node(SPIRIT_WEED_SEED, 1), 
					node(MARRENTILL_SEED, 1), 
					node(IRIT_SEED, 1), 
					node(KWUARM_SEED, 1), 
					node(GRIMY_AVANTOE, 1), 
					node(GRIMY_IRIT, 1), 
					node(GRAPES, 1, 3));
			break;
		case RARE:
			createNodeBatch(1, node(CADANTINE_SEED, 1), 
					node(DWARF_WEED_SEED, 1), 
					node(RANARR_SEED, 1), 
					node(SNAPDRAGON_SEED, 1), 
					node(GRIMY_RANARR, 1), 
					node(GRIMY_KWUARM, 1), 
					node(GRIMY_CADANTINE, 1), 
					node(GRIMY_LANTADYME, 1), 
					node(GRIMY_DWARF_WEED, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(TORSTOL_SEED, 1), 
					node(CASKET, 1));
			break;
		}
		addObj(BONES, 1);
		shakeTreasureTrail(player, EASY_CLUE);
		shakeSummoningCharm(1, 16, 0.5, 1.0, 0.15);

	}
}
