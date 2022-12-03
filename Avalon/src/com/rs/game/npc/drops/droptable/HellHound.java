package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class HellHound extends MobRewardNodeBuilder {

	public HellHound() {
		super(new Object[] { "Hellhound", -1 });
	}

	@Override
	public void populate(Player player) {
		addObj(526, 1);// bones
		shakeTreasureTrail(player, HARD_CLUE);
		shakeSummoningCharm(1, 69, 5, 5, 1);

	}
}
