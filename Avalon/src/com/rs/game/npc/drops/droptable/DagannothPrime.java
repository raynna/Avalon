package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class DagannothPrime extends MobRewardNodeBuilder {

	public DagannothPrime() {
		super(new Object[] { "Dagannoth Prime", 2882 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(AIR_BATTLESTAFF, 1), 
					node(EARTH_BATTLESTAFF, 1), 
					node(WATER_BATTLESTAFF, 1), 
					node(COINS, 972, 3000));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(AIR_TALISMAN_NOTED, 1, 80), 
					node(EARTH_TALISMAN_NOTED, 1, 80), 
					node(WATER_TALISMAN_NOTED, 1, 80), 
					node(AIR_RUNE, 155), 
					node(MUD_RUNE, 32), 
					node(DEATH_RUNE, 22, 85), 
					node(FREMENNIK_SHIELD, 1), 
					node(FREMENNIK_HELM, 1), 
					node(PURE_ESSENCE_NOTED, 150), 
					node(GRIMY_RANARR, 1), 
					node(BELLADONNA_SEED, 1), 
					node(CACTUS_SEED, 1), 
					node(POISON_IVY_SEED, 1), 
					node(IRIT_SEED, 1), 
					node(TOADFLAX_SEED, 1), 
					node(AVANTOE_SEED, 1));
			break;
		case RARE:
			createNodeBatch(1, node(MUD_BATTLESTAFF, 1), 
					node(BATTLESTAFF_NOTED, 1, 10), 
					node(DRAGON_AXE, 1), 
					node(SEERS_RING, 1), 
					node(SKELETAL_TOP, 1), 
					node(SKELETAL_BOTTOMS, 1), 
					node(FARSEER_HELM, 1), 
					node(RUNITE_BAR, 1), 
					node(KWUARM_SEED, 1), 
					node(CADANTINE_SEED, 1), 
					node(LANTADYME_SEED, 1), 
					node(DWARF_WEED_SEED, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(RUNE_BATTLEAXE, 1), 
					node(RUNE_2H_SWORD, 1), 
					node(FIRE_TALISMAN, 1), 
					node(RUNE_SQ_SHIELD, 1));
			break;
		}
		addObj(DAGANNOTH_BONES, 1);
		addObj(DAGANNOTH_HIDE, 1);
		shakeTreasureTrail(player, HARD_CLUE);
		shakeTreasureTrail(player, ELITE_CLUE);
	}
}
