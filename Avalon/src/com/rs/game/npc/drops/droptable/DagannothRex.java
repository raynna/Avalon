package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class DagannothRex extends MobRewardNodeBuilder {

	public DagannothRex() {
		super(new Object[] { "Dagannoth Rex", 2883 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node(FREMENNIK_BLADE, 1), 
					node(MITHRIL_WARHAMMER, 1), 
					node(ADAMANT_AXE, 1), 
					node(STEEL_KITESHIELD, 1), 
					node(STEEL_PLATEBODY, 1), 
					node(BODY_TALISMAN, 1), 
					node(EARTH_TALISMAN, 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node(MITHRIL_2H_SWORD, 1), 
					node(RUNE_AXE, 1), 
					node(ADAMANT_PLATEBODY, 1), 
					node(FREMENNIK_SHIELD, 1), 
					node(FREMENNIK_HELM, 1), 
					node(SUPER_ATTACK2, 1), 
					node(SUPER_STRENGTH2, 1), 
					node(SUPER_DEFENCE2, 1), 
					node(PRAYER_POTION2, 1), 
					node(ANTIFIRE_POTION2, 1), 
					node(ZAMORAK_BREW2, 1), 
					node(ADAMANT_BAR, 1), 
					node(MITHRIL_ORE_NOTED, 25), 
					node(COAL_NOTED, 100), 
					node(BASS, 5), 
					node(SWORDFISH, 5), 
					node(SHARK, 5), 
					node(GRIMY_RANARR, 1), 
					node(COINS, 156, 3000));
			break;
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(DRAGON_AXE, 1), 
					node(BERSERKER_RING, 1), 
					node(WARRIOR_RING, 1), 
					node(RING_OF_LIFE, 1), 
					node(ROCK_SHELL_PLATE, 1), 
					node(ROCK_SHELL_LEGS, 1), 
					node(RESTORE_POTION2, 1), 
					node(IRON_ORE_NOTED, 150), 
					node(STEEL_BAR_NOTED, 17, 38));
			break;
		}
		addObj(DAGANNOTH_BONES, 1);
		addObj(DAGANNOTH_HIDE, 1);
		shakeTreasureTrail(player, HARD_CLUE);
		shakeTreasureTrail(player, ELITE_CLUE);

	}
}
