package com.rs.game.player.dialogues.skilling;

import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.crafting.GemCutting;
import com.rs.game.player.actions.skills.crafting.GemCutting.Gem;
import com.rs.game.player.content.SkillsDialogue;
import com.rs.game.player.content.SkillsDialogue.ItemNameFilter;
import com.rs.game.player.dialogues.Dialogue;

public class GemCuttingD extends Dialogue {

	private Gem gem;

	@Override
	public void start() {
		this.gem = (Gem) parameters[0];
		final int[] PRODUCTS = { gem.getUncut() };
		SkillsDialogue.sendSkillsDialogue(player, SkillsDialogue.CUT,
				"Choose how many you wish to make,<br>then click on the item to begin.", 28, PRODUCTS,
				new ItemNameFilter() {

					@Override
					public String rename(String name) {
						int levelRequired = gem.getLevelRequired();
						if (player.getSkills().getLevel(Skills.CRAFTING) < levelRequired)
							name = "<col=ff0000>" + name + "<br><col=ff0000>Level " + levelRequired;
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
		if (player.getSkills().getLevel(Skills.CRAFTING) < gem.getLevelRequired()) {
			player.getPackets().sendGameMessage("You need a Crafting level of " + gem.getLevelRequired() + ".");
			end();
			return;
		}
		player.getActionManager().setAction(new GemCutting(gem, SkillsDialogue.getQuantity(player)));
		end();
	}

	@Override
	public void finish() {

	}

}
