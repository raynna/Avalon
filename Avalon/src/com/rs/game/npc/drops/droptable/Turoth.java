package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Turoth extends MobRewardNodeBuilder {

	public Turoth() {
		super(new Object[] { "Turoth", 1626 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(STEEL_PLATELEGS, 1), 
					node(MITHRIL_AXE, 1), 
					node(MITHRIL_KITESHIELD, 1), 
					node(LAW_RUNE, 3), 
					node(POISON_IVY_SEED, 1), 
					node(CACTUS_SEED, 1), 
					node(BELLADONNA_SEED, 1), 
					node(TOADFLAX_SEED, 1), 
					node(IRIT_SEED, 1), 
					node(GRIMY_GUAM, 1, 3), 
					node(GRIMY_MARRENTILL, 1, 3),  
					node(GRIMY_TARROMIN, 1, 3), 
					node(GRIMY_HARRALANDER, 1, 3), 
					nodeB(COINS, 44, 88, 132, 440), 
					node(LIMPWURT_ROOT, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(ADAMANT_FULL_HELM, 1), 
					node(RUNE_DAGGER, 1), 
					node(NATURE_RUNE, 15, 45), 
					node(SNAPDRAGON_SEED, 1), 
					node(CADANTINE_SEED, 1), 
					node(MORCHELLA_MUSHROOM_SPORE, 3), 
					node(AVANTOE_SEED, 1), 
					node(KWUARM_SEED, 1), 
					node(GRIMY_RANARR, 1, 3), 
					node(GRIMY_IRIT, 1, 3),  
					node(GRIMY_AVANTOE, 1, 3), 
					node(GRIMY_KWUARM, 1, 3));
			break;
		case RARE:
			createNodeBatch(1, node(MYSTIC_ROBE_BOTTOM_LIGHT, 1), 
					node(LEAF_BLADED_SWORD, 1), 
					node(LANTADYME_SEED, 1), 
					node(DWARF_WEED_SEED, 1), 
					node(RANARR_SEED, 1), 
					node(GRIMY_LANTADYME, 1, 3), 
					node(GRIMY_CADANTINE, 1, 3), 
					node(GRIMY_DWARF_WEED, 1, 3));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(TORSTOL_SEED, 1));
			break;
		}
		addObj(BONES, 1);
		shakeTreasureTrail(player, HARD_CLUE);
		shakeSummoningCharm(1, 7.5, 22, 5.5, 1);

	}
}
