package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class ChaosDruid extends MobRewardNodeBuilder {

	public ChaosDruid() {
		super(new Object[] { "Chaos druid", -1 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, nodeB(COINS, 2, 3, 15, 21, 29, 35, 365, 485),
					node(VIAL_OF_WATER, 1), 
					node(MITHRIL_BOLTS, 2, 12), 
					node(DRUIDS_ROBE, 1), 
					node(DRUIDS_ROBE_TOP, 1), 
					node(GRIMY_GUAM, 1, 2), 
					node(GRIMY_HARRALANDER, 1, 2), 
					node(GRIMY_MARRENTILL, 1, 2), 
					node(GRIMY_TARROMIN, 1, 2), 
					node(AIR_RUNE, 9, 36), 
					node(BODY_RUNE, 9), 
					node(EARTH_RUNE, 9), 
					node(LAW_RUNE, 2), 
					node(MIND_RUNE, 12));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(GRIMY_AVANTOE, 1, 2), 
					node(GRIMY_CADANTINE, 1, 2),
					node(GRIMY_DWARF_WEED, 1, 2),
					node(GRIMY_IRIT, 1, 2),
					node(GRIMY_KWUARM, 1, 2),
					node(GRIMY_LANTADYME, 1, 2),
					node(GRIMY_RANARR, 1, 2),
					node(NATURE_RUNE, 3), 
					node(BRONZE_LONGSWORD, 1));
			break;
		case RARE:
			createNodeBatch(1, node(SNAPE_GRASS, 1), 
					node(UNHOLY_MOULD, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(IRON_MED_HELM, 1), 
					node(UNCUT_JADE, 1), 
					node(GRIMY_TORSTOL, 1, 2), 
					node(COSMIC_TALISMAN, 1));
			break;
		}
		addObj(BONES, 1);
	}
}
