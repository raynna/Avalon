package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class FireGiant extends MobRewardNodeBuilder {

	public FireGiant() {
		super(new Object[] { "Fire giant", -1 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node("steel hatchet", 1), 
					node("mithril sq shield", 1), 
					node("steel helm", 1), 
					nodeB("fire rune", 37, 100, 150, 200), 
					node("grimy guam", 1), 
					node("grimy marrentill", 1), 
					node("grimy tarromin", 1), 
					node("grimy avantoe", 1), 
					nodeB("coins", 15, 25, 60, 360));
			break;
		case UNCOMMON:
			createNodeBatch(1, node("fire battlestaff", 1), 
					node("rune scimitar", 1), 
					node("rune arrow", 12), 
					node("chaos rune", 5), 
					node("blood rune", 5), 
					nodeB("law rune", 2, 4, 45), 
					node("grimy harralander", 1), 
					node("grimy ranarr", 1), 
					node("grimy irit", 1), 
					node("grimy cadantine", 1), 
					node("lobster", 1), 
					node("steel bar", 1), 
					node("strength potion (2)", 1));
			break;
		case RARE:
			createNodeBatch(1, node("nature rune", 15), 
					node("grimy lantadyme", 1), 
					node("grimy kwuarm", 1), 
					node("grimy dwarf weed", 1), 
					node("long bone", 1), 
					node("rune bar", 1), 
					node("curved bone", 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node("rune 2h sword", 1), 
					node("rune battleaxe", 1), 
					node("rune sq shield", 1), 
					node("chaos talisman", 1));
			break;
		}
		addObj("big bones", 1);
		shakeSummoningCharm(1, 55.5, 7, 5, 1.5);

	}
}
