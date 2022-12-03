package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class IceGiant extends MobRewardNodeBuilder {

	public IceGiant() {
		super(new Object[] { "Ice giant", -1 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(BLACK_KITESHIELD, 1), 
					node(IRON_PLATELEGS, 1), 
					node(IRON_2H_SWORD, 1), 
					node(IRON_BATTLEAXE, 1), 
					node(STEEL_AXE, 1), 
					node(STEEL_SWORD, 1), 
					node(BODY_RUNE, 37), 
					node(COSMIC_RUNE, 2, 4), 
					node(MIND_RUNE, 24), 
					node(WATER_RUNE, 12), 
					node(MARRENTILL_SEED, 1), 
					node(TARROMIN_SEED, 1), 
					node(COINS, 8, 450));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(ADAMANT_ARROW, 5), 
					node(LAW_RUNE, 3), 
					node(NATURE_RUNE, 6), 
					node(HARRALANDER_SEED, 1), 
					node(JANGERBERRY_SEED, 1), 
					node(LIMPWURT_SEED, 1), 
					node(RANARR_SEED, 1), 
					node(STRAWBERRY_SEED, 1), 
					node(WATERMELON_SEED, 1), 
					node(BANANA, 1), 
					node(JUG_OF_WINE, 1), 
					node(MITHRIL_ORE, 1));
			break;
		case RARE:
			createNodeBatch(1, node(MITHRIL_SQ_SHIELD, 1), 
					node(MITHRIL_MACE, 1), 
					node(BLOOD_RUNE, 2, 4), 
					node(DEATH_RUNE, 2, 4), 
					node(AVANTOE_SEED, 1), 
					node(BELLADONNA_SEED, 1), 
					node(BITTERCAP_MUSHROOM_SEED, 1), 
					node(CACTUS_SEED, 1), 
					node(CADANTINE_SEED, 1), 
					node(DWARF_WEED_SEED, 1), 
					node(IRIT_SEED, 1), 
					node(LANTADYME_SEED, 1), 
					node(TOADFLAX_SEED, 1), 
					node(WHITEBERRY_SEED, 1), 
					node(WILDBLOOD_SEED, 1), 
					node(CURVED_BONE, 1), 
					node(LONG_BONE, 1), 
					node(COSMIC_TALISMAN, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(KWUARM_SEED, 1), 
					node(POISON_IVY_SEED, 1), 
					node(SNAPDRAGON_SEED, 1), 
					node(SPIRIT_WEED_SEED, 1), 
					node(TORSTOL_SEED, 1));
			break;
		}
		addObj(BIG_BONES, 1);// bones
		shakeTreasureTrail(player, MEDIUM_CLUE);
		shakeSummoningCharm(1, 45, 4, 4.5, 0.7);
	}
}
