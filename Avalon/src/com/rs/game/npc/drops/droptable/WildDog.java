package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class WildDog extends MobRewardNodeBuilder {

	public WildDog() {
		super(new Object[] { "Wild dog", 1593 });
	}

	@Override
	public void populate(Player player) {
		addObj(BONES, 1);
		shakeSummoningCharm(1, 6, 16, 6.5, 1.5);

	}
}
