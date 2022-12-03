package com.rs.game.player.dialogues.npcs;

import com.rs.Settings;
import com.rs.game.player.controlers.WelcomeTutorial;
import com.rs.game.player.dialogues.Dialogue;

public class TutorialMakeover extends Dialogue {

	WelcomeTutorial controler;
	int npcId = 2676;

	@Override
	public void start() {
		controler = (WelcomeTutorial) parameters[0];
		sendNPCDialogue(npcId, Happy,
				"Hello " + player.getDisplayName() + ", I will be the one in charge of your appearance.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case -1:
			sendNPCDialogue(npcId, Happy,
					"Different specialists around " + Settings.FORMAL_SERVER_NAME
							+ " will be doing the work on you but I will be "
							+ "teleporting you to them so they can do their thing, you won't notice it though.");
			break;
		case 0:
			sendNPCDialogue(npcId, Happy,
					"I will now let you finish your tutorial, come see me after you are done and we "
							+ "can customize your personal appearance!");
			break;
		case 1:
			controler.setStage(3);
			controler.refreshStage();
			end();
			break;
		}
		stage++;

	}

	@Override
	public void finish() {

	}
}