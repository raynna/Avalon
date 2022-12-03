package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public final class ChaosElemental extends MobRewardNodeBuilder {

	public ChaosElemental() {
		super(new Object[] { "Chaos Elemental", 3200 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(530, 5), 
					node(532, 2), 
					node(526, 4), 
					node(2289, 2), 
					node(385, 10), 
					node(464, 1,6), 
					node(361, 5), 
					node(1621, 1), 
					node(1623, 1), 
					node(125, 1), 
					node(137, 1), 
					node(119, 1), 
					node(5281, 1,3), 
					node(5294, 1,3), 
					node(5104, 1,3), 
					node(5292, 1,3), 
					node(5293, 1,3), 
					node(5105, 1,3), 
					node(5311, 1,3), 
					node(809, 400), 
					node(1289, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(14876 + Utils.getRandom(15), 1),
					node(13958 + Utils.getRandom(10) * 3, 1),
					node(13858 + Utils.getRandom(8) * 3, 1),
					node(Utils.random(2) == 1 ? 13879 : 13883, 19, 50),
					node(Utils.random(2) == 1 ? 13953 : 13957, 19, 50),
					node(13884 + Utils.getRandom(22) * 3, 1));
			break;
		case RARE:
			createNodeBatch(1, node(211, 1), 
					node(215, 1), 
					node(199, 1), 
					node(209, 1), 
					node(213, 1), 
					node(201, 1), 
					node(207, 1), 
					node(203, 1),
					node(2446, 4), 
					node(3026, 4,5), 
					node(560, 100, 226), 
					node(5298, 1,3), 
					node(5301, 1,3), 
					node(5299, 1,3), 
					node(5302, 1), 
					node(5295, 1,3), 
					node(5300, 1), 
					node(5304, 1), 
					node(7158, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(995, 10282, 12000), 
					node(534, 1,2), 
					node(536, 1), 
					node(2114, 5), 
					node(2003, 10), 
					node(1617, 1), 
					node(1619, 1), 
					node(6689, 5,6), 
					node(565, 100, 300), 
					node(562, 100, 500), 
					node(5280, 1,3), 
					node(5297, 1,3), 
					node(5100, 1,3), 
					node(5106, 1,3), 
					node(12176, 1,3), 
					node(5323, 1,3), 
					node(5296, 1,3), 
					node(5321, 1,3));
			break;
		}
		shakeTreasureTrail(player, HARD_CLUE);
		shakeTreasureTrail(player, ELITE_CLUE);
	}
}
