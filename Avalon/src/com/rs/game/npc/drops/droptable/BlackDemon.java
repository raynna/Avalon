package com.rs.game.npc.drops.droptable;

import com.rs.game.npc.drops.MobRewardNodeBuilder;
import com.rs.game.player.Player;

public final class BlackDemon extends MobRewardNodeBuilder {

	public BlackDemon() {
		super(new Object[] { "Black demon", 84 });
	}

	@Override
	public void populate(Player player) {
		switch (rarityNode(player)) {
		case -1:
		case COMMON:
			createNodeBatch(1, node("steel battleaxe", 1), 
					node("black hatchet", 1), 
					node("black sword", 1), 
					node("black 2h sword", 1), 
					node("black full helm", 1), 
					node("mithril kiteshield", 1), 
					node("grimy guam", 1), 
					node("grimy marrentill", 1), 
					node("grimy tarromin", 1), 
					node("grimy harralander", 1), 
					node("air rune", 50, 75), 
					node("fire rune", 37, 40), 
					node("chaos rune", 10), 
					node("law rune", 3, 4), 
					node("law rune", 45), 
					node("adamant bar", 1));
			break;
		case UNCOMMON:
			createNodeBatch(1, node("grimy ranarr", 1), 
					node("grimy irit", 1), 
					node("grimy avantoe", 1), 
					node("grimy kwuarm", 1), 
					node("grimy cadantine", 1), 
					node("blood rune", 7), 
					node("lobster", 1), 
					node("thread", 10), 
					node("defence potion (3)", 1));
			break;
		case RARE:
			createNodeBatch(1, node("rune battleaxe", 1), 
					node("rune helm", 1), 
					node("rune full helm", 1), 
					node("rune chainbody", 1), 
					node("rune sq shield", 1), 
					node("grimy lantadyme", 1), 
					node("grimy dwarf weed", 1), 
					node("grimy snapdragon", 1), 
					node("coal#noted", 20), 
					node("uncut diamond", 1));
			break;
		case VERY_RARE:
			createNodeBatch(1, node("rune 2h sword", 1));
			break;
		}
		addObj("infernal ashes", 1);
		shakeSummoningCharm(1, 21, 11, 44, 2);

	}
}
