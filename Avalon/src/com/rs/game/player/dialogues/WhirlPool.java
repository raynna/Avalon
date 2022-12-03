package com.rs.game.player.dialogues;

import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.WorldTile;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class WhirlPool extends Dialogue {

	@Override
	public void start() {
		player.addWalkSteps(2512, 3517);
		sendOptionsDialogue("Are you sure about this?", "<col=ff0000> I ain't afraid of no water",
				"I think I left something in my bank...");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {

			if (componentId == OPTION_1) {
				end();
				player.lock(10);
				player.addWalkSteps(2512, 3517);
				player.animate(new Animation(6723));
				final WorldTile toTile = new WorldTile(2512, 3508, 0);
				player.setNextForceMovement(new ForceMovement(player, 1, toTile, 7, ForceMovement.SOUTH));
				player.getPackets().sendGameMessage("You jump into the whirlpool!", true);
				WorldTasksManager.schedule(new WorldTask() {

					@Override
					public void run() {
						player.setNextWorldTile(new WorldTile(1764, 5365, 1));
						player.getSkills().addXp(Skills.AGILITY, 60);
					}

				}, 1);
			} else {
				end();
			}

		}
	}

	@Override
	public void finish() {

	}

}