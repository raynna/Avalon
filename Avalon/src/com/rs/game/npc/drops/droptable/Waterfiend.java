package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Waterfiend extends MobRewardNodeBuilder {

	public Waterfiend() {
		super(new Object[] { "Waterfiend", 5361 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(WATER_RUNE, 90), 
					node(BLOOD_RUNE, 4, 12), 
					node(MITHRIL_ARROW, 90), 
					node(GRIMY_GUAM, 1, 10), 
					node(GRIMY_MARRENTILL, 1), 
					node(GRIMY_TARROMIN, 1), 
					node(COINS, 97, 692), 
					node(WATER_ORB_NOTED, 5, 10));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(DEATH_RUNE, 9, 19), 
					node(MIST_RUNE, 3, 10), 
					node(MUD_RUNE, 3, 9), 
					node(STEAM_RUNE, 3, 12), 
					node(AVANTOE_SEED, 1), 
					node(BELLADONNA_SEED, 1), 
					node(CACTUS_SEED, 1), 
					node(CADANTINE_SEED, 1), 
					node(HARRALANDER_SEED, 1), 
					node(IRIT_SEED, 1), 
					node(JANGERBERRY_SEED, 1), 
					node(KWUARM_SEED, 1), 
					node(LANTADYME_SEED, 1), 
					node(LIMPWURT_SEED, 1), 
					node(MARRENTILL_SEED, 1), 
					node(BITTERCAP_MUSHROOM_SEED, 1), 
					node(POISON_IVY_SEED, 1), 
					node(SPIRIT_WEED_SEED, 1), 
					node(STRAWBERRY_SEED, 1), 
					node(TARROMIN_SEED, 1), 
					node(TOADFLAX_SEED, 1), 
					node(WHITEBERRY_SEED, 1), 
					node(WILDBLOOD_SEED, 1), 
					node(WATERMELON_SEED, 1, 9), 
					node(ADAMANT_CHAINBODY, 1), 
					node(MITHRIL_FULL_HELM, 1), 
					node(MITH_GRAPPLE, 1), 
					node(MITHRIL_WARHAMMER, 1), 
					node(RUNE_MED_HELM, 1), 
					node(STAFF_OF_WATER, 1), 
					node(WATER_BATTLESTAFF, 1), 
					node(SAPPHIRE_BOLTS, 15), 
					node(GRIMY_RANARR, 1), 
					node(GRIMY_IRIT, 1), 
					node(GRIMY_HARRALANDER, 1), 
					node(GRIMY_AVANTOE, 1), 
					node(GRIMY_KWUARM, 1), 
					node(GRIMY_CADANTINE, 1), 
					node(GRIMY_DWARF_WEED, 1), 
					node(GRIMY_LANTADYME, 1), 
					node(MITHRIL_ORE_NOTED, 1, 5), 
					node(MITHRIL_BAR, 2), 
					node(UNCUT_SAPPHIRE_NOTED, 1, 4), 
					node(COAL_NOTED, 1, 6), 
					node(GOLD_ORE_NOTED, 3, 8), 
					node(RAW_LOBSTER, 5, 12), 
					node(RAW_SWORDFISH, 5, 7), 
					node(RAW_SHARK, 3, 6), 
					node(SHARK, 1), 
					node(SNAPE_GRASS_NOTED, 20), 
					node(OYSTER, 1), 
					node(SEAWEED_NOTED, 10, 29), 
					node(WATER_TALISMAN, 1), 
					node(VIAL_OF_WATER_NOTED, 5, 10), 
					node(FISHBOWL, 1), 
					node(ZAMORAK_BREW1, 1));
			break;
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(RANARR_SEED, 1), 
					node(DWARF_WEED_SEED, 1), 
					node(BLUE_DHIDE_VAMB, 1), 
					node(ADAMANT_PLATEBODY, 1), 
					node(STEEL_BAR_NOTED, 3), 
					node(CANNONBALL, 11, 27), 
					node(SARADOMIN_BREW1, 1));
			break;
		}
		addObj(WATER_RUNE, 1);
		shakeTreasureTrail(player, HARD_CLUE);
		shakeSummoningCharm(1, 9.5, 5, 79, 2);

	}
}
