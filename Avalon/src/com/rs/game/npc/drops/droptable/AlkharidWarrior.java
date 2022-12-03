package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class AlkharidWarrior extends MobRewardNodeBuilder {

	public AlkharidWarrior() {
		super(new Object[] { "Al-Kharid warrior", 18 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(COINS, 3, 25), 
					node(FISHING_BAIT, 1), 
					node(BRONZE_ARROW, 7), 
					node(BRONZE_BOLTS, 1, 12), 
					node(BRONZE_MED_HELM, 1), 
					node(GRIMY_GUAM, 1), 
					node(GRIMY_MARRENTILL, 1), 
					node(EARTH_RUNE, 4), 
					node(FIRE_RUNE, 6), 
					node(MIND_RUNE, 9));
			break;
		case VERY_RARE:
		case RARE:
			createNodeBatch(1, node(LAW_RUNE, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(AIR_RUNE, 5), 
					node(IRON_DAGGER, 1), 
					node(IRON_MED_HELM, 1), 
					node(STAFF_OF_AIR, 1), 
					node(SLING, 1), 
					node(GRIMY_TARROMIN, 1), 
					node(GRIMY_HARRALANDER, 1), 
					node(GRIMY_RANARR, 1), 
					node(GRIMY_IRIT, 1), 
					node(GRIMY_AVANTOE, 1), 
					node(GRIMY_KWUARM, 1), 
					node(GRIMY_CADANTINE, 6), 
					node(GRIMY_LANTADYME, 1), 
					node(GRIMY_DWARF_WEED, 1), 
					node(COPPER_ORE, 1), 
					node(CABBAGE, 1), 
					node(EARTH_TALISMAN, 1));
			break;
		}
		addObj(BONES, 1);// bones
		shakeTreasureTrail(player, EASY_CLUE);
	}
}
