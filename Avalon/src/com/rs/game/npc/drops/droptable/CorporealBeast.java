package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public final class CorporealBeast extends MobRewardNodeBuilder {

	public CorporealBeast() {
		super(new Object[] { "Corporeal beast", 8133 });
	}

	@Override
	public void populate(Player player) {
		if (shake(player, 526)) {
			int random = 2 + Utils.getRandom(3) * 2;//sigils
			addObj(13744 + random, 1);
		} else 
			switch (rarityNode(player)) {
			case -1:
			case COMMON:
				createNodeBatch(1, node(995, 250, 3000), node(1623, 1), node(1621, 1), node(5321, 24));
				break;
			case RARE:
			case VERY_RARE:
				createNodeBatch(1, node(5295, 10), node(13754, 1));
				break;
			case UNCOMMON:
				createNodeBatch(1, node(1617, 1), node(1619, 1), node(1631, 1), node(1462, 1), node(890, 750),
						node(9144, 250), node(1401, 1), node(1403, 1), node(1405, 1), node(1407, 1), node(4091, 1),
						node(4093, 1), node(9245, 175), node(2, 2000), node(13734, 1), node(7937, 2500), node(563, 250),
						node(11133, 1), node(564, 500), node(560, 300), node(566, 250), node(450, 125), node(452, 20),
						node(2362, 35), node(8781, 100), node(6333, 150), node(1514, 75), node(1754, 100),
						node(384, 70), node(240, 120), node(9736, 120), node(7060, 30), node(5953, 40));
				break;
			}
		shakeSummoningCharm(13, 22, 12, 22, 41.5);
		shakeTreasureTrail(player, ELITE_CLUE);
		shakeTreasureTrail(player, HARD_CLUE);

	}

}
