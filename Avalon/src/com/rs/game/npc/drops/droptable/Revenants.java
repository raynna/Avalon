package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public final class Revenants extends MobRewardNodeBuilder {

	public Revenants() {
		super(new Object[] { "Revenant imp", "Revenant goblin", "Revenant pyrefiend", "Revenant hobgoblin",
				"Revenant cyclops", "Revenant hellhound", "Revenant demon", "Revenant ork", "Revenant dark beast",
				"Revenant knight", "Revenant dragon", "Revenant werewolf", "Revenant icefiend", "Revenant vampyre", -1 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			break;
		case VERY_RARE:
		case RARE:
			createNodeBatch(1, node(14876 + Utils.getRandom(15), 1), node(13958 + Utils.getRandom(10) * 3, 1),
					node(13858 + Utils.getRandom(8) * 3, 1), node(Utils.random(2) == 1 ? 13879 : 13883, 1, 50),
					node(Utils.random(2) == 1 ? 13953 : 13957, 1, 50), node(13884 + Utils.getRandom(22) * 3, 1),
					node(7158, 1), node(1149, 1), node(5316, 5, 9));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(1163, 1), node(1127, 1), node(1079, 1), node(1201, 1), node(1347, 1),
					node(4087, 1), node(4585, 1), node(1215, 1), node(1305, 1), node(1392, 3), node(560, 70, 100),
					node(565, 70, 100), node(563, 100, 200), node(9193, 50, 100), node(9194, 5, 20),
					node(454, 150, 200), node(392, 30, 60), node(1516, 50, 100), node(1514, 10, 30), node(1748, 10, 20),
					node(8783, 10, 20), node(452, 5, 10), node(2364, 5, 10), node(2362, 5, 10), node(1632, 5, 7),
					node(3025, 3, 5));
			break;
		}
		addObj(526, 1);// bones
		addObj(995, Utils.random(1, 100));// coins
	}
}
