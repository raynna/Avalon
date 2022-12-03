package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class DagannothSupreme extends MobRewardNodeBuilder {

	public DagannothSupreme() {
		super(new Object[] { "Dagannoth Supreme", 2881 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(IRON_ARROW, 218, 590), 
					node(STEEL_ARROW, 54, 98), 
					node(COINS, 900, 3000), 
					node(OYSTER_PEARLS, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(ADAMANT_AXE, 1), 
					node(IRON_KNIFE, 214, 500), 
					node(STEEL_KNIFE, 54, 90), 
					node(MITHRIL_KNIFE, 31, 69), 
					node(RUNE_THROWNAXE, 7), 
					node(RUNE_JAVELIN, 1, 10), 
					node(RUNITE_BOLTS, 2, 14), 
					node(RED_DHIDE_VAMB, 1), 
					node(SPINED_BODY, 1), 
					node(SPINED_CHAPS, 1), 
					node(FREMENNIK_SHIELD, 1), 
					node(FREMENNIK_HELM, 1), 
					node(SHARK, 5), 
					node(BELLADONNA_SEED, 1), 
					node(CACTUS_SEED, 1), 
					node(POISON_IVY_SEED, 1), 
					node(IRIT_SEED, 1), 
					node(TOADFLAX_SEED, 1), 
					node(AVANTOE_SEED, 1), 
					node(YEW_LOGS_NOTED, 94), 
					node(MAPLE_LOGS_NOTED, 91, 125), 
					node(GRIMY_RANARR, 1));
			break;
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(RUNE_LONGSWORD, 1), 
					node(FREMENNIK_BLADE, 1), 
					node(RUNE_BATTLEAXE, 1), 
					node(RUNE_2H_SWORD, 1), 
					node(SEERCULL, 1), 
					node(DRAGON_AXE, 1), 
					node(ARCHER_HELM, 1), 
					node(KWUARM_SEED, 1), 
					node(CADANTINE_SEED, 1), 
					node(LANTADYME_SEED, 1), 
					node(DWARF_WEED_SEED, 1), 
					node(AIR_TALISMAN, 1), 
					node(MIND_TALISMAN, 1), 
					node(EARTH_TALISMAN, 1), 
					node(FIRE_TALISMAN, 1), 
					node(BODY_TALISMAN, 1), 
					node(COSMIC_TALISMAN, 1), 
					node(FEATHER, 1, 200), 
					node(ARCHERS_RING, 1), 
					node(RUNITE_LIMBS, 1));
			break;
		}
		addObj(DAGANNOTH_BONES, 1);
		addObj(DAGANNOTH_HIDE, 1);
		shakeTreasureTrail(player, HARD_CLUE);
		shakeTreasureTrail(player, ELITE_CLUE);
	}
}
