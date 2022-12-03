package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Goblin extends MobRewardNodeBuilder {

	public Goblin() {
		super(new Object[] { "Goblin", 11236 });
	}

	@Override
	public void populate(Player player) {

		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(SLING, 1), node(IRON_DAGGER, 1), node(BRONZE_ARROW, 16), node(BRONZE_BOLTS, 8), node(BRONZE_MED_HELM, 1), node(BRONZE_SQ_SHIELD, 1),
					node(BRONZE_SPEAR, 1), node(BEER, 1), node(BREAD_DOUGH, 1), node(CABBAGE, 1), node(AIR_RUNE, 2, 10),
					nodeB(BODY_RUNE, 2, 7), node(EARTH_RUNE, 4), node(WATER_RUNE, 6));
			break;
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(RED_CAPE, 1), node(IRON_FULL_HELM, 1), node(LEATHER_BODY, 1), node(BRONZE_AXE, 1), node(BRONZE_JAVELIN, 5), node(BRONZE_SCIMITAR, 1),
					node(BLOOD_RUNE, 1), node(CHAOS_RUNE, 1), node(NATURE_RUNE, 1), node(GRIMY_AVANTOE, 1), node(GRIMY_CADANTINE, 1), node(GRIMY_DWARF_WEED, 1), node(GRIMY_IRIT, 1),
					node(GRIMY_KWUARM, 1), node(GRIMY_LANTADYME, 1), node(GRIMY_RANARR, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(BRASS_NECKLACE, 1), node(IRON_ARROW, 3, 6), node(BRONZE_CHAINBODY, 1), node(BRONZE_FULL_HELM, 1), node(BRONZE_KITESHIELD, 1),
					node(BRONZE_LONGSWORD, 1), node(BRONZE_SWORD, 1), node(STAFF_OF_AIR, 1), node(RAW_CHICKEN, 1), node(MIND_RUNE, 2, 19), node(GRIMY_GUAM, 1),
					node(GRIMY_HARRALANDER, 1), node(GRIMY_MARRENTILL, 1), node(GRIMY_TARROMIN, 1), node(GOBLIN_MAIL, 1));
			break;
		}
		addObj(BONES, 1);// bones
		shakeSummoningCharm(1, 9, 0.3, 0.9, 0.1);

	}

}
