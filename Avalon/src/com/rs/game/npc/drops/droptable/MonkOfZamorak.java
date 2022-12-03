package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class MonkOfZamorak extends MobRewardNodeBuilder {

	public MonkOfZamorak() {
		super(new Object[] { "Monk of Zamorak", 190 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case UNCOMMON:
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(1033, 1), 
					node(1035, 1));
			break;
		}
		addObj(BONES, 1);
		shakeSummoningCharm(1, 0.7, 9, 1.5, 0.7);
	}
}
