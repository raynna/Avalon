package com.rs.game.player.dialogues;

import com.rs.game.player.Player.Limits;
import com.rs.game.player.Skills;
import com.rs.game.player.dialogues.Dialogue;

public class StarterQuickStatsD extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Select an option", "No quick stat boost", "Quick stat boost");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (componentId == OPTION_1) {
			end();
		}
		if (componentId == OPTION_2) {
			for (int skills = 0; skills < 7; skills++) {
				Limits i = Limits.forId(skills);
				if (i != null) {
					player.getSkills().set(skills, i.getLevel());
					player.getSkills().setXp(skills, Skills.getXPForLevel(i.getLevel()));
				}
			}
			end();
		}
	}

	@Override
	public void finish() {

	}

}