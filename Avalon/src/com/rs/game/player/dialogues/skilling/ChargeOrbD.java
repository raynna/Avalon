package com.rs.game.player.dialogues.skilling;

import com.rs.game.player.actions.combat.modernspells.ChargeOrb;
import com.rs.game.player.content.SkillsDialogue;
import com.rs.game.player.content.SkillsDialogue.ItemNameFilter;
import com.rs.game.player.dialogues.Dialogue;

public class ChargeOrbD extends Dialogue {

	private int itemId;

	@Override
	public void start() {
		this.itemId = (int) parameters[0];
		final int[] PRODUCTS = { itemId };
		SkillsDialogue.sendSkillsDialogue(player, SkillsDialogue.MAKE,
				"Choose how many you wish to make,<br>then click on the item to begin.", 28, PRODUCTS,
				new ItemNameFilter() {

					@Override
					public String rename(String name) {
						return name;
					}
				});
	}

	/*
	 * @Override public void start() { this.gem = (Gem) parameters[0];
	 * SkillsDialogue .sendSkillsDialogue( player, SkillsDialogue.CUT,
	 * "Choose how many you wish to cut,<br>then click on the item to begin.",
	 * player.getInventory().getItems() .getNumberOf(gem.getUncut()), new int[]
	 * { gem.getUncut() }, null);
	 * 
	 * }
	 */

	@Override
	public void run(int interfaceId, int componentId) {
		player.getActionManager().setAction(new ChargeOrb(itemId, SkillsDialogue.getQuantity(player)));
		end();
	}

	@Override
	public void finish() {

	}

}
