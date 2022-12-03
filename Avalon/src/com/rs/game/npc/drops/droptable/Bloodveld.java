package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Bloodveld extends MobRewardNodeBuilder {

	public Bloodveld() {
		super(new Object[] { "Bloodveld", 1618 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(STEEL_AXE, 1), 
					node(STEEL_FULL_HELM, 1), 
					node(FIRE_RUNE, 60), 
					nodeB(COINS, 10, 40, 120, 200, 460));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(STEEL_SCIMITAR, 1), 
					node(MITHRIL_SQ_SHIELD, 1), 
					node(MITHRIL_CHAINBODY, 1), 
					node(BLACK_BOOTS, 1), 
					node(RUNE_MED_HELM, 1), 
					nodeB(BLOOD_RUNE, 3, 10, 30), 
					node(GRIMY_GUAM, 1), 
					node(GRIMY_MARRENTILL, 1), 
					node(GRIMY_TARROMIN, 1), 
					node(GRIMY_HARRALANDER, 1), 
					node(GRIMY_RANARR, 1), 
					node(GRIMY_IRIT, 1), 
					node(GRIMY_AVANTOE, 1), 
					node(GRIMY_KWUARM, 1), 
					node(GRIMY_CADANTINE, 1), 
					node(GRIMY_LANTADYME, 1), 
					node(GRIMY_DWARF_WEED, 1), 
					node(GOLD_ORE, 1), 
					node(BONES, 2, 3), 
					nodeB(BIG_BONES, 1, 4, 5), 
					node(MEAT_PIZZA, 1));
			break;
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(RUNE_FULL_HELM, 1), 
					node(GOLD_RING, 1));
			break;
		}
		addObj(BONES, 1);
		shakeSummoningCharm(1, 9, 31, 5, 1);

	}
}
