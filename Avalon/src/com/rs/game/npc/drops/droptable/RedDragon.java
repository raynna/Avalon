package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;
import com.rs.game.player.content.pet.Pets;

public final class RedDragon extends MobRewardNodeBuilder {

	public RedDragon() {
		super(new Object[] { "Red dragon", 53 });
	}

	@Override
	public void populate(Player player) {
		petRoll(player, Pets.BABY_RED_DRAGON.getBabyItemId(), 500);
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(MITHRIL_2H_SWORD, 1), 
					node(MITHRIL_JAVELIN, 20), 
					node(MITHRIL_AXE, 1), 
					node(GRIMY_GUAM, 1), 
					node(GRIMY_MARRENTILL, 1), 
					node(GRIMY_TARROMIN, 1), 
					node(GRIMY_HARRALANDER, 1), 
					node(COINS, 30, 690));
			break;
		case UNCOMMON:
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(MITHRIL_KITESHIELD, 1), 
					node(ADAMANT_PLATEBODY, 1), 
					node(MITHRIL_BATTLEAXE, 1), 
					node(RUNE_ARROW, 4), 
					node(RUNE_DART, 8), 
					node(RUNE_LONGSWORD, 1), 
					node(LAW_RUNE, 4), 
					node(DEATH_RUNE, 5), 
					node(BLOOD_RUNE, 2), 
					node(GRIMY_RANARR, 1), 
					node(GRIMY_IRIT, 1), 
					node(GRIMY_AVANTOE, 1), 
					node(GRIMY_KWUARM, 1), 
					node(GRIMY_CADANTINE, 1), 
					node(GRIMY_LANTADYME, 1), 
					node(GRIMY_DWARF_WEED, 1), 
					node(CHOCOLATE_CAKE, 3), 
					node(ADAMANT_BAR, 1));
			break;
		}
		addObj(DRAGON_BONES, 1);
		addObj(RED_DRAGONHIDE, 1);
		shakeTreasureTrail(player, HARD_CLUE);
		shakeSummoningCharm(1, 17.5, 43.5, 17.5, 3.5);

	}
}
