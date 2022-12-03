package com.rs.game.npc.drops.droptable;

import com.rs.game.item.Item;
import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class BASIC extends MobRewardNodeBuilder {

	public BASIC() {
		super(new Object[] { "nameofnpc", -1 });
	}

	@Override
	public void populate(Player player) {
		if (shake(player, 512)) {
			createNodeBatch(1, node(000, 1));
		} else
			switch (rarityNode(player)) {
			case -1:
			case COMMON:
				createNodeBatch(1, node(000, 1), node(000, 1));
				break;
			case UNCOMMON:
				createNodeBatch(1, node(000, 1), node(000, 1));
				break;
			case RARE:
				createNodeBatch(1, node(000, 1), node(000, 1));
				break;
			case VERY_RARE:
				createNodeBatch(1, node(000, 1), node(000, 1));
				break;
			}
		addObj(BONES, 1);
		shakeTreasureTrail(player, HARD_CLUE);
		shakeTreasureTrail(player, ELITE_CLUE);
		shakeSummoningCharm(1, 16, 18, 22, 9);

	}
}
