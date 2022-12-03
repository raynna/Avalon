package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class OgreFemale extends MobRewardNodeBuilder {

	public OgreFemale() {
		super(new Object[] { "Ogress", 7081 });
	}

	@Override
	public void populate(Player player) {
		if (shake(player, 5000)) {
			createNodeBatch(1, node(GIANT_CHAMPION_SCROLL, 1));
		} else
			switch (rarityNode(player)) {
			case -1:
			case COMMON:
				createNodeBatch(1, node(IRON_ARROW, 3), node(IRON_DAGGER, 1), node(IRON_FULL_HELM, 1),
						node(WATER_RUNE, 1), node(GRIMY_GUAM, 1), node(GRIMY_MARRENTILL, 1), node(GRIMY_TARROMIN, 1),
						node(TOMATO_SEED, 1, 3), node(POTATO_SEED, 1, 4), nodeB(ONION_SEED, 4, 8), node(TOMATO_SEED, 3),
						node(STRAWBERRY_SEED, 2), node(CABBAGE_SEED, 4), nodeB(COINS, 8, 15, 38, 52, 88, 310),
						node(COINS, 350, 493), node(LIMPWURT_ROOT, 1));
				break;
			case VERY_RARE:
				createNodeBatch(1, node(STEEL_PLATEBODY, 1), node(ADAMANT_ARROW, 1, 5), node(GRIMY_CADANTINE, 1),
						node(GRIMY_LANTADYME, 1), node(CURVED_BONE, 1));
				break;
			case RARE:
				createNodeBatch(1, node(IRON_2H_SWORD, 1), node(MITHRIL_ARROW, 3), node(STEEL_DAGGER, 1),
						node(DEATH_RUNE, 2), node(CHAOS_RUNE, 1), node(GRIMY_RANARR, 1), node(GRIMY_HARRALANDER, 1),
						node(GRIMY_IRIT, 1), node(GRIMY_AVANTOE, 1), node(GRIMY_KWUARM, 1), node(GRIMY_DWARF_WEED, 1),
						node(LIMPWURT_SEED, 1), node(WATERMELON_SEED, 1, 4), node(SWEETCORN_SEED, 3),
						node(LONG_BONE, 1));
				break;
			case UNCOMMON:
				createNodeBatch(1, node(STEEL_ARROW, 10, 15), node(IRON_KITESHIELD, 1), node(STEEL_LONGSWORD, 1),
						node(FIRE_RUNE, 15), node(COSMIC_RUNE, 2), node(LAW_RUNE, 2, 3), node(NATURE_RUNE, 6),
						node(MIND_RUNE, 3), node(BEER, 1), node(BODY_TALISMAN, 1));
				break;
			}
		addObj(BIG_BONES, 1);// bones
		shakeSummoningCharm(1, 14.5, 0.95, 1.0, 0.15);

	}
}
