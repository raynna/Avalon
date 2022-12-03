package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Pyrefiend extends MobRewardNodeBuilder {

	public Pyrefiend() {
		super(new Object[] { "Pyrefiend", 1633 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, nodeB(FIRE_RUNE, 30, 60, 90), 
					node(STEEL_FULL_HELM, 1), 
					node(STAFF_OF_FIRE, 1), 
					node(STEEL_AXE, 1), 
					nodeB(COINS, 10, 40, 100, 120, 200, 240, 320, 400, 446, 450, 474), 
					node(GOLD_ORE, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(CHAOS_RUNE, 12), 
					node(LAW_RUNE, 1), 
					node(DEATH_RUNE, 3), 
					node(STEEL_BATTLEAXE, 1), 
					node(STEEL_ARROW, 1), 
					node(MITHRIL_CHAINBODY, 1), 
					node(JUG_OF_WINE, 1));
			break;
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(STEEL_BOOTS, 1), 
					node(ADAMANT_MED_HELM, 1));
			break;
		}
		addObj(IMPIOUS_ASHES, 1);
		shakeTreasureTrail(player, MEDIUM_CLUE);
		shakeSummoningCharm(1, 5, 2.5, 5.5, 0.5);

	}
}
