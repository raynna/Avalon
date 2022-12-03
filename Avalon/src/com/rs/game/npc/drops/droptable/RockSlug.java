package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class RockSlug extends MobRewardNodeBuilder {

	public RockSlug() {
		super(new Object[] { "Rockslug", 1631 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, nodeB(EARTH_RUNE, 5, 42), 
					node(RUNE_ESSENCE, 1), 
					node(TIN_ORE, 1), 
					node(COPPER_ORE, 1), 
					node(BRONZE_BAR, 1), 
					node(IRON_ORE, 1), 
					node(IRON_BAR, 1), 
					node(COAL, 1), 
					node(POTATO_SEED, 4), 
					node(ONION_SEED, 4), 
					node(CABBAGE_SEED, 4), 
					node(HAMMER, 1), 
					node(DWARVEN_STOUT, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, nodeB(CHAOS_RUNE, 2, 5), 
					node(MITHRIL_ORE, 1), 
					node(TOMATO_SEED, 3), 
					node(SWEETCORN_SEED, 3), 
					nodeB(COINS, 279, 418, 449, 853));
			break;
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(STRAWBERRY_SEED, 2), 
					node(WATERMELON_SEED, 2), 
					node(MYSTIC_GLOVES_LIGHT, 1));
			break;
		}
	}
}
