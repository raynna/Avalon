package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public final class AberrantSpectre extends MobRewardNodeBuilder {

	public AberrantSpectre() {
		super(new Object[] { "Aberrant spectre", -1 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			if (Utils.random(2) == 0)
			createNodeBatch(3, node(GRIMY_GUAM, 1), 
					node(GRIMY_MARRENTILL, 1), 
					node(GRIMY_TARROMIN, 1), 
					node(GRIMY_HARRALANDER, 1), 
					node(GRIMY_RANARR, 1), 
					node(GRIMY_IRIT, 1), 
					node(GRIMY_AVANTOE, 1), 
					node(GRIMY_KWUARM, 1));
			else
				createNodeBatch(1, node(BELLADONNA_SEED, 1), 
						node(CACTUS_SEED, 1), 
						node(MORCHELLA_MUSHROOM_SPORE, 3), 
						node(IRIT_SEED, 1), 
						node(TOADFLAX_SEED, 1), 
						node(AVANTOE_SEED, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1,
					node(FELLSTALK_SEED, 1), 
					node(POISON_IVY_SEED, 1), 
					node(KWUARM_SEED, 1),
					node(GRIMY_CADANTINE, 1, 3), 
					node(GRIMY_LANTADYME, 1, 3), 
					node(GRIMY_DWARF_WEED, 1, 3), 
					node(STEEL_AXE, 1), 
					node(MITHRIL_KITESHIELD, 1), 
					node(LAVA_BATTLESTAFF, 1), 
					node(ADAMANT_PLATELEGS, 1), 
					node(COINS, 460),					
					node(CADANTINE_SEED, 1), 
					node(LANTADYME_SEED, 1));
			break;
		case RARE:
			createNodeBatch(1, node(SNAPDRAGON_SEED, 1), 
					node(DWARF_WEED_SEED, 1), 
					node(TORSTOL_SEED, 1), 
					node(MYSTIC_ROBE_BOTTOM_DARK, 1),
					node(RUNE_FULL_HELM, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(GRIMY_SNAPDRAGON, 1, 2));
			break;
		}
		shakeTreasureTrail(player, HARD_CLUE);
		shakeSummoningCharm(1, 12, 6, 4, 5);

	}
}
