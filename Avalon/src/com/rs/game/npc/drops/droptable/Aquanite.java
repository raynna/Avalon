package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Aquanite extends MobRewardNodeBuilder {

	public Aquanite() {
		super(new Object[] { "Aquanite", 9172 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(WATER_RUNE, 30, 49), 
					node(WATER_RUNE, 100), 
					node(ADAMANT_BOLTS, 10), 
					node(RUNITE_BOLTS, 5), 
					node(RUNE_DART, 1), 
					node(RAW_LOBSTER, 1, 3), 
					node(RAW_SWORDFISH, 1, 2), 
					node(RAW_SHARK, 1), 
					node(COINS, 500), 
					node(SEAWEED_NOTED, 3, 6), 
					node(SNAPE_GRASS_NOTED, 3));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(AIR_RUNE, 40), 
					node(NATURE_RUNE, 4), 
					node(DEATH_RUNE, 3), 
					node(BLOOD_RUNE, 3), 
					node(SOUL_RUNE, 3, 6), 
					node(WATER_BATTLESTAFF, 1), 
					node(MORCHELLA_MUSHROOM_SPORE, 4), 
					node(POISON_IVY_SEED, 1), 
					node(CACTUS_SEED, 1), 
					node(BELLADONNA_SEED, 1), 
					node(AVANTOE_SEED, 1), 
					node(TOADFLAX_SEED, 1), 
					node(CADANTINE_SEED, 1), 
					node(IRIT_SEED, 1), 
					node(KWUARM_SEED, 1), 
					node(FELLSTALK_SEED, 1), 
					node(GRIMY_GUAM, 1), 
					node(GRIMY_MARRENTILL, 1), 
					node(GRIMY_HARRALANDER, 1), 
					node(GRIMY_RANARR, 1), 
					node(GRIMY_IRIT, 1), 
					node(GRIMY_AVANTOE, 1), 
					node(GRIMY_KWUARM, 1), 
					node(GRIMY_CADANTINE, 1), 
					node(GRIMY_LANTADYME, 1), 
					node(GRIMY_DWARF_WEED, 1));
			break;
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(AIR_BATTLESTAFF, 1), 
					node(AMULET_OF_RANGING, 1), 
					node(RANARR_SEED, 1), 
					node(SNAPDRAGON_SEED, 1), 
					node(LANTADYME_SEED, 1), 
					node(DWARF_WEED_SEED, 1), 
					node(TORSTOL_SEED, 1), 
					node(LONG_BONE, 1), 
					node(CURVED_BONE, 1));
			break;
		}
		addObj(BIG_BONES, 1);
		shakeTreasureTrail(player, HARD_CLUE);
		shakeSummoningCharm(1, 10, 5, 5, 6.5);
	}
}
