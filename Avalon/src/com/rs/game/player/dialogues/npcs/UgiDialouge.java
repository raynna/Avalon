package com.rs.game.player.dialogues.npcs;

import com.rs.game.npc.others.Ugi;
import com.rs.game.player.content.treasuretrails.TreasureTrailsManager;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.utils.Utils;

public class UgiDialouge extends Dialogue {

	@Override
	public void start() {
		Ugi npc = (Ugi) parameters[0];
		stageInt = (int) (npc.getTarget() == player && player.getTreasureTrailsManager().getPhase() == 4 ? (byte) 0
				: (byte) -1);
		run(-1, -1);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		Ugi npc = (Ugi) parameters[0];
		if (stageInt == 0) {
			sendNPCDialogue(npc.getId(), NORMAL,
					TreasureTrailsManager.UGIS_QUOTES[Utils.random(TreasureTrailsManager.UGIS_QUOTES.length)]);
			stageInt = 1;
		} else if (stageInt == 1) {
			sendPlayerDialogue(NORMAL, "What?");
			stageInt = 2;
		} else if (stageInt == 2) {
			end();
			npc.finish();

			player.getTreasureTrailsManager().setPhase(5);
			player.getTreasureTrailsManager().setNextClue(TreasureTrailsManager.SOURCE_EMOTE);
		} else if (stageInt == -1) {
			sendNPCDialogue(npc.getId(), NORMAL, TreasureTrailsManager.UGI_BADREQS);
			stageInt = -2;
		} else {
			end();
		}
	}

	@Override
	public void finish() {

	}
}
