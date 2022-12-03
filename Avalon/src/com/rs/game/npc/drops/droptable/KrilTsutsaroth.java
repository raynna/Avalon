package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class KrilTsutsaroth extends MobRewardNodeBuilder {

	public KrilTsutsaroth() {
		super(new Object[] { "K'ril tsutsaroth", 6203 });
	}

	@Override
	public void populate(Player player) {
		if (shake(player, 128)) {
			createNodeBatch(1, node(ZAMORAK_HILT, 1), node(ZAMORAKIAN_SPEAR, 1), node(STEAM_BATTLESTAFF, 1));
		} else
			switch (rarityNode(player)) {
			case -1:
			case COMMON:
				createNodeBatch(1, node(995, 19501, 21000), node(20269, 5), node(1123, 1), node(1079, 1), node(145, 3),
						node(3026, 3), node(157, 3), node(189, 3), node(1333, 1));
				break;
			case VERY_RARE:
			case RARE:
				createNodeBatch(1, node(995, 19501, 21000), node(995, 20500, 21000), node(11710, 1), node(11712, 1),
						node(11714, 1));
				break;
			case UNCOMMON:
				createNodeBatch(1, node(246, 2, 10), node(2486, 10), node(452, 2), node(5302, 3));
				break;
			}
		addObj(20268, 1);// ashes
		shakeTreasureTrail(player, HARD_CLUE);
		shakeTreasureTrail(player, ELITE_CLUE);
		shakeSummoningCharm(1, 12, 4, 1, 18);

	}
}
