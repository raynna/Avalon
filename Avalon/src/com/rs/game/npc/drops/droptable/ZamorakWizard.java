package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class ZamorakWizard extends MobRewardNodeBuilder {

	public ZamorakWizard() {
		super(new Object[] { "Zamorak wizard", 1007 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, nodeB(AIR_RUNE, 60, 189, 192), 
					nodeB(EARTH_RUNE, 79, 86, 107), 
					nodeB(FIRE_RUNE, 51, 180, 189), 
					nodeB(WATER_RUNE, 86, 107, 146), 
					nodeB(COINS, 77, 89, 240));
			break;
		case UNCOMMON:
		case RARE:
		case VERY_RARE:
			createNodeBatch(1, node(AIR_BATTLESTAFF, 1), 
					node(EARTH_BATTLESTAFF, 1), 
					node(FIRE_BATTLESTAFF, 1), 
					node(WATER_BATTLESTAFF, 1), 
					node(ZAMORAK_ROBE, 1), 
					node(ZAMORAK_SKIRT, 1), 
					node(BLOOD_RUNE, 8), 
					node(COSMIC_RUNE, 25), 
					node(DEATH_RUNE, 45), 
					node(LAW_RUNE, 9), 
					nodeB(NATURE_RUNE, 12, 28));
			break;
		}
		addObj(BONES, 1);

	}
}
