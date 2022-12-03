package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class Thug extends MobRewardNodeBuilder {

	public Thug() {
		super(new Object[] { "Thug", -1 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(STEEL_AXE, 1), 
					node(IRON_MED_HELM, 1), 
					node(NATURE_RUNE, 2, 4), 
					node(GRIMY_GUAM, 1), 
					node(GRIMY_MARRENTILL, 1), 
					node(GRIMY_TARROMIN, 1), 
					node(COINS, 8), 
					node(IRON_BAR, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(IRON_BATTLEAXE, 1), 
					node(BRONZE_MED_HELM, 1), 
					node(CHAOS_RUNE, 2), 
					node(COSMIC_RUNE, 2), 
					node(DEATH_RUNE, 2), 
					node(LAW_RUNE, 1), 
					node(GRIMY_HARRALANDER, 1), 
					node(GRIMY_RANARR, 1), 
					node(GRIMY_IRIT, 1), 
					node(GRIMY_AVANTOE, 1), 
					node(GRIMY_LANTADYME, 1), 
					node(IRON_ORE, 1), 
					node(COAL, 1));
			break;
		case RARE:
			createNodeBatch(1, node(GRIMY_DWARF_WEED, 1), 
					node(GRIMY_KWUARM, 1), 
					node(GRIMY_CADANTINE, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(IRON_2H_SWORD, 1), 
					node(IRON_DAGGER, 1), 
					node(SUPER_DEFENCE1, 1), 
					node(SUPER_STRENGTH1, 1));
			break;
		}
		addObj(BONES, 1);
		shakeTreasureTrail(player, EASY_CLUE);
	}
}
