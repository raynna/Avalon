package com.rs.game.player.dialogues.npcs;

import com.rs.Settings;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.WorldTile;
import com.rs.game.player.actions.combat.Magic;
import com.rs.game.player.dialogues.Dialogue;

/**
 * 
 * @author Rhys AOC
 *
 */

public class FatherAereck extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		if (Settings.ECONOMY) {
			player.getPackets().sendGameMessage("Father Aereck is not useable in Economy mode.");
			end();
			return;
		}
		npcId = (Integer) parameters[0];
		sendEntityDialogue(SEND_2_TEXT_CHAT,
				new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Hello, Would you like to teleport to " + Settings.SERVER_NAME + " Altar's?" },
				IS_NPC, npcId, 9827);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { player.getDisplayName(), "Yes, Please!" }, IS_PLAYER,
					player.getIndex(), 9827);
			stage = 1;
		} else if (stage == 1) {
			sendOptionsDialogue("Where would you like to go?", "Curses Altar.", "Lunar Altar.", "Ancient Altar.",
					"close");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3182, 5713, 0));
			if (componentId == OPTION_2)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2157, 3862, 0));
			if (componentId == OPTION_3)
				Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3233, 9313, 0));
			if (componentId == OPTION_4)
				;
			player.closeInterfaces();
		}
	}

	@Override
	public void finish() {

	}
}