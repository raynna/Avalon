package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class AbyssalDemon extends MobRewardNodeBuilder {

	public AbyssalDemon() {
		super(new Object[] { "Abyssal demon", 1615 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(BLACK_SWORD, 1), 
					node(BLACK_AXE, 1), 
					node(STEEL_BATTLEAXE, 1), 
					node(GRIMY_GUAM, 1), 
					node(GRIMY_MARRENTILL, 1), 
					node(GRIMY_TARROMIN, 1), 
					node(AIR_RUNE, 50), 
					node(BLOOD_RUNE, 7), 
					node(CHAOS_RUNE, 10), 
					node(COINS, 9, 3000), 
					node(LOBSTER, 1), 
					node(COSMIC_TALISMAN, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(MITHRIL_KITESHIELD, 1), 
					node(RUNE_CHAINBODY, 1), 
					node(RUNE_MED_HELM, 1), 
					node(GRIMY_HARRALANDER, 1), 
					node(GRIMY_RANARR, 1), 
					node(GRIMY_IRIT, 1), 
					node(GRIMY_AVANTOE, 1), 
					node(GRIMY_CADANTINE, 1), 
					node(GRIMY_LANTADYME, 1), 
					node(GRIMY_KWUARM, 1), 
					node(GRIMY_DWARF_WEED, 1), 
					nodeB(LAW_RUNE, 3, 45), 
					node(PURE_ESSENCE_NOTED, 60), 
					node(EARTH_TALISMAN, 1), 
					node(ADAMANT_BAR, 1), 
					node(DEFENCE_POTION3, 1));
			break;
		case RARE:
			createNodeBatch(1, node(ABYSSAL_WHIP, 1), 
					node(RUNE_SQ_SHIELD, 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node(ABYSSAL_HEAD, 1), 
					node(RUNE_BATTLEAXE, 1), 
					node(RUNE_2H_SWORD, 1));
			break;
		}
		addObj(INFERNAL_ASHES, 1);
		shakeTreasureTrail(player, HARD_CLUE);
		shakeSummoningCharm(1, 9, 4, 33, 1);

	}
}
