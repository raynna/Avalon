package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Zombie extends MobRewardNodeBuilder {

	public Zombie() {
		super(new Object[] { "Zombie", 75 });
	}

	@Override
	public void populate(Player player) {
		if (shake(player, 5000)) {
			createNodeBatch(1, node(ZOMBIE_CHAMPION_SCROLL, 1));
		} else
			switch (rarityNode(player)) {
			case -1:
			case COMMON:
				createNodeBatch(1, node(GRIMY_GUAM, 1), node(GRIMY_MARRENTILL, 1), node(GRIMY_TARROMIN, 1),
						node(STEEL_ARROW, 5, 32), node(BODY_RUNE, 3, 11), node(COINS, 1, 82),
						nodeB(FISHING_BAIT, 1, 5, 7, 9), node(LIMESTONE_BRICK, 2));
				break;
			case UNCOMMON:
				createNodeBatch(1, node(GRIMY_HARRALANDER, 1), node(GRIMY_RANARR, 1), node(GRIMY_IRIT, 1),
						node(GRIMY_AVANTOE, 1), node(BRONZE_AXE, 1), node(BRONZE_LONGSWORD, 1),
						node(BRONZE_MED_HELM, 1), node(BRONZE_KITESHIELD, 1), node(IRON_DAGGER, 1), node(IRON_AXE, 1),
						node(IRON_MACE, 1), nodeB(IRON_ARROW, 5, 8), nodeB(MITHRIL_ARROW, 1, 2), node(SLING, 1),
						node(CHAOS_RUNE, 4), node(AIR_RUNE, 3), node(FIRE_RUNE, 7, 84), node(MIND_RUNE, 5, 7),
						node(NATURE_RUNE, 5), node(LAW_RUNE, 2), node(COSMIC_RUNE, 2), node(TINDERBOX, 1),
						node(TILE, 1), node(BEER, 1), node(COPPER_ORE, 1), node(TIN_ORE, 1), node(ASHES, 1),
						node(EYE_OF_NEWT, 1), node(UNHOLY_MOULD, 1));
				break;
			case RARE:
				createNodeBatch(1, node(GRIMY_KWUARM, 1));
				break;
			case VERY_RARE:
				createNodeBatch(1, node(GRIMY_CADANTINE, 1), node(GRIMY_LANTADYME, 1), node(GRIMY_DWARF_WEED, 1),
						node(HALF_A_MEAT_PIE, 1));
				break;
			}
		addObj(BONES, 1);
	}
}
