package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Banshee extends MobRewardNodeBuilder {

	public Banshee() {
		super(new Object[] { "Banshee", 1612 });
	}

	@Override
	public void populate(Player player) {
		if (shake(player, 512)) {
			createNodeBatch(1, node(15355, 1));
		} else
			switch (rarityNode(player)) {
			case -1:
			case COMMON:
				createNodeBatch(1, node("air rune", 3), nodeB("chaos rune", 3, 6, 7, 17), node("grimy guam", 1),
						node("grimy tarromin", 1), node("grimy marrentill", 1), node("grimy harralander", 1),
						node("grimy kwuarm", 1), nodeB("pure essence#noted", 13, 23), nodeB("fishing bait", 7, 15));
				break;
			case UNCOMMON:
				createNodeBatch(1, node("iron mace", 1), node("cosmic rune", 2), nodeB("fire rune", 6, 7),
						node("grimy ranarr", 1), node("grimy irit", 1), node("grimy avantoe", 1),
						node("grimy lantadyme", 1), node("grimy dwarf weed", 1), node("eye of newt", 1),
						node("iron ore", 1));
				break;
			case RARE:
				createNodeBatch(1, node("iron dagger", 1), node("iron kiteshield", 1), node("grimy cadantine", 1));
				break;
			case VERY_RARE:
				createNodeBatch(1, node("adamant kiteshield", 1), node(4105, 1));
				break;
			}
		shakeTreasureTrail(player, EASY_CLUE);
		shakeSummoningCharm(1, 2.5, 7, 1, 0.35);

	}
}
