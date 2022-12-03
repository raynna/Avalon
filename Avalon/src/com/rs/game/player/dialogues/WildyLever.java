package com.rs.game.player.dialogues;

import com.rs.game.WorldTile;
import com.rs.game.player.actions.combat.Magic;

public class WildyLever extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Wilderness Lever Teleportation", "West Dragons", "East Dragons", "Deserted Keep",
				"Chaos Altar");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (componentId == OPTION_1) {
			Magic.pushLeverTeleport(player, new WorldTile(2978, 3600, 0));
			end();
		} else if (componentId == OPTION_2) {
			Magic.pushLeverTeleport(player, new WorldTile(3354, 3667, 0));
			end();
		} else if (componentId == OPTION_3) {
			Magic.pushLeverTeleport(player, new WorldTile(3154, 3924, 0));
			end();
		} else if (componentId == OPTION_4) {
			Magic.pushLeverTeleport(player, new WorldTile(2963, 3821, 0));
			end();
		}
	}

	@Override
	public void finish() {

	}

}
