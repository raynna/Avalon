package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class SergeantSteelWill extends MobRewardNodeBuilder {

	public SergeantSteelWill() {
		super(new Object[] { "Sergeant steelwill", 6263 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(886, 95, 100), node(808, 95, 100), node(1917, 1), node(7054, 3), node(1025, 1),
					node(1971, 1), node(226, 5), node(385, 2), node(9741, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(11724, 1), node(11726, 1), node(11728, 1), node(19043, 1));
			break;
		case RARE:
			createNodeBatch(1, node(11710, 1), node(11712, 1), node(11714, 1), node(995, 1000, 1500));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(3051, 1), node(454, 15, 19));
			break;
		}
		addObj(526, 1);// bones
		shakeTreasureTrail(player, HARD_CLUE);

	}

}
