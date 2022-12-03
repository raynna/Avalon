package com.rs.game.player.dialogues.artisianworkshop;

import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.smithing.ArtisanSmithing;
import com.rs.game.player.content.ArtisanWorkshop.Ingots;
import com.rs.game.player.content.SkillsDialogue;
import com.rs.game.player.content.SkillsDialogue.ItemNameFilter;
import com.rs.game.player.dialogues.Dialogue;

public class ArtisanSmithingD extends Dialogue {

	private Ingots ingot;
	
	@Override
	public void start() {
		ingot = (Ingots) parameters[0];
		int[] ids = new int[ingot.getProducts().length];
		for (int i = 0; i < ids.length; i++)
			ids[i] = ingot.getProduct(i).getId();
		SkillsDialogue.sendSkillsDialogue(player, SkillsDialogue.MAKE,
				"How many bars you would like to smelt?<br>Choose a number, then click the bar to begin.", 28, ids,
				new ItemNameFilter() {
					@Override
					public String rename(String name) {
						if (player.getSkills().getLevel(Skills.SMITHING) < ingot.getLevel())
							name = "<col=ff0000>" + name + "<br><col=ff0000>Level " + ingot.getLevel();
						return name;

					}
				});
	}

	@Override
	public void run(int interfaceId, int componentId) {
		player.getActionManager().setAction(
				new ArtisanSmithing(SkillsDialogue.getQuantity(player),SkillsDialogue.getItemSlot(componentId),ingot));
		end();
	}

	@Override
	public void finish() {
	}
}