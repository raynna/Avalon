package com.rs.game.player.dialogues;

import com.rs.game.Animation;
import com.rs.game.Graphics;

public class SpellbookSwap extends Dialogue {

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Choose a SpellBook", "Ancient Spellbook", "Normal Spellbook");
	}

	/**
	 * Started dialogue portion Still need to generate method of automatic
	 * switch back if time > 2 mins or spell is casted
	 */

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				player.lock(10);
				player.gfx(new Graphics(1062));
				player.animate(new Animation(6299));
				player.getInventory().deleteItem(564, 2);
				player.getInventory().deleteItem(563, 1);
				player.getInventory().deleteItem(9075, 3);
				end();
			} else if (componentId == OPTION_2) {
				player.lock(10);
				player.gfx(new Graphics(1062));
				player.animate(new Animation(6299));
				player.getInventory().deleteItem(564, 2);
				player.getInventory().deleteItem(563, 1);
				player.getInventory().deleteItem(9075, 3);
				end();
			}
		}
	}

	@Override
	public void finish() {

	}

}