package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class MithrilDragon extends MobRewardNodeBuilder {

	public MithrilDragon() {
		super(new Object[] { "Mithril dragon", 5363 });
	}

	@Override
	public void populate(Player player) {
		if (shake(player, 256)) {
			createNodeBatch(1, node(DRACONIC_VISAGE, 1));
		} else if (shake(player, 128)) {
			createNodeBatch(1, node(DRAGON_FULL_HELM_11335, 1));
		} else
			switch (rarityNode(player)) {
			case -1:
			case COMMON:
				createNodeBatch(1, node(1620, 6), node(1514, 10), nodeB(1516, 50, 80), node(565, 30, 50),
						node(560, 50));
				break;
			case VERY_RARE:
				createNodeBatch(1, node(7980, 1));
				break;
			case RARE:
				createNodeBatch(1, node(1149, 1));
				break;
			case UNCOMMON:
				createNodeBatch(1, node(888, 250), nodeB(892, 50, 75, 100), node(1127, 1), node(1185, 1), node(2362, 8),
						node(2364, 2), node(445, 50), node(452, 3), node(443, 100), node(563, 50), node(1373, 1),
						node(1303, 1));
				break;
			}
		addObj(536, 1);// dragon bones
		addObj(1747, 1);// dragonhide
		shakeTreasureTrail(player, HARD_CLUE);
		shakeTreasureTrail(player, ELITE_CLUE);
		shakeSummoningCharm(4, 4, 3, 59, 0.9);

	}
}
