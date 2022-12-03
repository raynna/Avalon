package com.rs.game.player.dialogues;

import com.rs.game.WorldTile;
import com.rs.game.player.actions.combat.Magic;

// Referenced classes of package com.rs.game.player.dialogues:
//            Dialogue

public class PKTeles extends Dialogue {

	public PKTeles() {
	}

	@Override
	public void start() {
		stage = 1;
		sendOptionsDialogue("Where do you wanna go?", "Wests", "East dragons", "Mage bank", "Ardougne lever",
				"Never mind");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 1) {
			if (componentId == OPTION_1) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2981, 3600, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				WildernessDitch.addPlayer(player);
			} else if (componentId == OPTION_2) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3359, 3667, 0));
				player.getInterfaceManager().closeChatBoxInterface();
				WildernessDitch.addPlayer(player);
			} else if (componentId == OPTION_3) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2540, 4716, 0));
				player.getInterfaceManager().closeChatBoxInterface();
			} else if (componentId == OPTION_4) {
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2563, 3311, 0));
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
