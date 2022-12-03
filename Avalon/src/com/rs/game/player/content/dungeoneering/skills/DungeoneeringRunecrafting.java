package com.rs.game.player.content.dungeoneering.skills;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.game.player.content.dungeoneering.DungeonConstants;

public class DungeoneeringRunecrafting extends Action {

	private final int runeId;
	private final int levelRequirement;
	private final double experience;
	private final int[] multipliers;
	private int cycles;

	public DungeoneeringRunecrafting(int cycles, int runeId, int levelRequirement, double experience, int... multipliers) {
		this.cycles = cycles;
		this.runeId = runeId;
		this.levelRequirement = levelRequirement;
		this.experience = experience;
		this.multipliers = multipliers;
	}

	@Override
	public boolean start(Player player) {
		int actualLevel = player.getSkills().getLevel(Skills.RUNECRAFTING);
		if (actualLevel < levelRequirement) {
			player.getDialogueManager().startDialogue("SimpleMessage", "You need a runecrafting level of " + levelRequirement + " to craft this rune.");
			return false;
		}
		int essense = player.getInventory().getAmountOf(DungeonConstants.ESSENCE);
		if (essense == 0) {
			player.sm("You don't have any rune essence.");
			return false;
		}
		if (cycles < essense)
			cycles = essense;
		if (cycles > 200)
			cycles = 200;
		return true;
	}

	@Override
	public boolean process(Player player) {
		return cycles > 0;
	}

	@Override
	public int processWithDelay(Player player) {
		boolean incompleteCycle = cycles < 10;

		int cycleCount = incompleteCycle ? cycles : 10;
		cycles -= cycleCount;

		player.animate(new Animation(13659));
		player.gfx(new Graphics(2571));

		player.getSkills().addXp(Skills.RUNECRAFTING, cycleCount * experience);
		player.getInventory().deleteItem(new Item(DungeonConstants.ESSENCE, cycleCount));
		for (int i = multipliers.length - 2; i >= 0; i -= 2) {
			if (player.getSkills().getLevel(Skills.RUNECRAFTING) >= multipliers[i]) {
				cycleCount *= multipliers[i + 1];
				break;
			}
		}

		player.getInventory().addItem(new Item(runeId, cycleCount));
		return 0;
	}

	@Override
	public void stop(Player player) {
		setActionDelay(player, 3);
	}
}
