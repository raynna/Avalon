package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class MonkeyGuard extends MobRewardNodeBuilder {

	public MonkeyGuard() {
		super(new Object[] { "Monkey Guard", 1459, 1460 });
	}

	@Override
	public void populate(Player player) {
		addObj(MONKEY_BONES_GUARD, 1);
	}
}
