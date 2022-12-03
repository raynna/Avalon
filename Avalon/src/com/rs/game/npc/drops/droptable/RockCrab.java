package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class RockCrab extends MobRewardNodeBuilder {

	public RockCrab() {
		super(new Object[] { "", 1265, 1266, 1267, 1268 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(BRONZE_PICKAXE, 1), 
					node(IRON_PICKAXE, 1), 
					node(FIRE_RUNE, 7), 
					node(COINS, 4, 413), 
					node(EDIBLE_SEAWEED, 2), 
					node(EMPTY_OYSTER, 1), 
					node(OYSTER, 1, 2), 
					node(OYSTER_PEARL, 1), 
					node(SEAWEED_NOTED, 1, 5));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(BRONZE_AXE, 1), 
					node(IRON_JAVELIN, 3), 
					node(NATURE_RUNE, 4), 
					node(FISHING_BAIT, 1, 10), 
					node(KNIFE, 1), 
					node(SPINACH_ROLL, 1), 
					node(OPAL_BOLT_TIPS, 5), 
					node(COAL, 2), 
					node(COPPER_ORE, 3), 
					node(IRON_ORE, 1), 
					node(TIN_ORE, 3));
			break;
		case RARE:
			createNodeBatch(1, node(GRIMY_AVANTOE, 1), 
					node(GRIMY_RANARR, 1), 
					node(RANARR_SEED, 1), 
					node(TARROMIN_SEED, 1), 
					node(CHAOS_RUNE, 1), 
					node(COSMIC_TALISMAN, 1), 
					node(CASKET, 1), 
					node(LIMPWURT_ROOT, 1), 
					node(JADE_BOLT_TIPS, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(IRIT_SEED, 1), 
					node(TORSTOL_SEED, 1), 
					node(RUNE_PICKAXE, 1));
			break;
		}
		shakeTreasureTrail(player, EASY_CLUE);
		shakeSummoningCharm(1, 10, 0.45, 1, 0.3);

	}
}
