package com.rs.game.player.dialogues;

import com.rs.game.WorldTile;
import com.rs.game.player.actions.combat.Magic;

// Referenced classes of package com.rs.game.player.dialogues:
//            Dialogue

public class MinigameTeles extends Dialogue {

	public MinigameTeles() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("What minigame do you want to go to?", "Barrows", "Warrior Guild", "Fight Kiln",
				"Fight Caves", "Next Page");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3565, 3289, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2880, 3542, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4743, 5171, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_4) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(4612, 5131, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_5) {
				stage = 2;
				sendOptionsDialogue("What minigame do you want to go to?", "Castle Wars", "Dominion Tower",
						"Duel Arena", "Pest Control", "No Thanks.");

			}
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2442, 3090, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_2) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3744, 6425, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_3) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3365, 3275, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_4) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2657, 2649, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_5) {
				player.getInterfaceManager().closeChatBoxInterface();

			}
		}
	}

	@Override
	public void finish() {
	}

}
