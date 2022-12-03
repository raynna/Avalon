package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class MossGiant extends MobRewardNodeBuilder {

	public MossGiant() {
		super(new Object[] { "Moss giant", -1 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case COMMON:
			createNodeBatch(1, node(IRON_ARROW, 15), 
					node(STEEL_ARROW, 30), 
					node(MAGIC_STAFF, 1), 
					node(MITHRIL_SPEAR, 1), 
					node(MITHRIL_SWORD, 1), 
					node(BLACK_FULL_HELM, 1), 
					node(BLACK_SQ_SHIELD, 1), 
					node(STEEL_MED_HELM, 1), 
					node(AIR_RUNE, 18), 
					node(COSMIC_RUNE, 3), 
					node(EARTH_RUNE, 27), 
					node(NATURE_RUNE, 6), 
					node(HARRALANDER_SEED, 1), 
					node(JANGERBERRY_SEED, 1), 
					node(MARRENTILL_SEED, 1), 
					node(STRAWBERRY_SEED, 1), 
					node(TARROMIN_SEED, 1), 
					node(WILDBLOOD_SEED, 1), 
					node(GRIMY_GUAM, 1), 
					node(GRIMY_HARRALANDER, 1), 
					node(GRIMY_MARRENTILL, 1), 
					node(GRIMY_TARROMIN, 1), 
					nodeB(COINS, 2, 5, 7, 10, 37, 108, 119, 300, 358, 361, 419), 
					node(STEEL_BAR, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(STEEL_KITESHIELD, 1), 
					node(BLOOD_RUNE, 1), 
					node(CHAOS_RUNE, 7), 
					node(LAW_RUNE, 3, 6), 
					node(COAL, 1), 
					node(BELLADONNA_SEED, 1), 
					node(BITTERCAP_MUSHROOM_SEED, 1), 
					node(LIMPWURT_SEED, 1), 
					node(SPIRIT_WEED_SEED, 1), 
					node(TOADFLAX_SEED, 1), 
					node(WHITEBERRY_SEED, 1), 
					node(SPINACH_ROLL, 1));
			break;
		case RARE:
			createNodeBatch(1, node(ADAMANT_SWORD, 1), 
					nodeB(DEATH_RUNE, 1, 3), 
					node(AVANTOE_SEED, 1), 
					node(CACTUS_SEED, 1), 
					node(CADANTINE_SEED, 1), 
					node(IRIT_SEED, 1), 
					node(KWUARM_SEED, 1), 
					node(LANTADYME_SEED, 1), 
					node(POISON_IVY_SEED, 1), 
					node(RANARR_SEED, 1), 
					node(WATERMELON_SEED, 1), 
					node(GRIMY_AVANTOE, 1), 
					node(GRIMY_CADANTINE, 1), 
					node(GRIMY_DWARF_WEED, 1), 
					node(GRIMY_IRIT, 1), 
					node(GRIMY_KWUARM, 1), 
					node(GRIMY_LANTADYME, 1), 
					node(GRIMY_RANARR, 1), 
					node(FIRE_TALISMAN, 1), 
					node(CURVED_BONE, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(SOUL_RUNE, 20), 
					node(DWARF_WEED_SEED, 1), 
					node(SNAPDRAGON_SEED, 1), 
					node(TORSTOL_SEED, 1));
			break;
		}
		addObj(BIG_BONES, 1);// bones
		shakeSummoningCharm(1, 35, 2.5, 2.5, 0.55);
	}
}
