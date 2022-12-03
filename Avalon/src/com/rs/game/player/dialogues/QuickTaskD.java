package com.rs.game.player.dialogues;

import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.slayer.Slayer.SlayerMaster;

public class QuickTaskD extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		SlayerMaster master = (SlayerMaster) parameters[0];
		npcId = master.getNPCId();
		stage = -1;
		if (player.getSlayerManager().getCurrentTask() != null) {
			sendNPCDialogue(npcId, 9827,
					"You already have a slayer task, "
							+ player.getSlayerManager().getCurrentTask().getName().replace("$", "'") + "; only "
							+ player.getSlayerManager().getCount() + " more to go.");
			stage = 1;
			return;
		}
		sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Easy task", "Medium task", "Hard task", "Expert task (Boss)");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				player.getSlayerManager().setCurrentTask(true, 1);
				sendNPCDialogue(npcId, 9827,
						"Your new assignment is: "
								+ player.getSlayerManager().getCurrentTask().getName().replace("$", "'") + "; only "
								+ player.getSlayerManager().getCount() + " more to go.");
				stage = 1;
				try {
					player.refreshTask();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else if (componentId == OPTION_2) {
				if (player.getSkills().getLevel(Skills.SLAYER) < 30) {
					sendNPCDialogue(npcId, 9827, "You need at least a level of 30 slayer to get boss tasks.");
					stage = 1;
					return;
				} else {
					player.getSlayerManager().setCurrentTask(true, 2);
					sendNPCDialogue(npcId, 9827,
							"Your new assignment is: "
									+ player.getSlayerManager().getCurrentTask().getName().replace("$", "'") + "; only "
									+ player.getSlayerManager().getCount() + " more to go.");
					stage = 1;
					try {
						player.refreshTask();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (componentId == OPTION_3) {
				if (player.getSkills().getLevel(Skills.SLAYER) < 60) {
					sendNPCDialogue(npcId, 9827, "You need at least a level of 60 slayer to get boss tasks.");
					stage = 1;
					return;
				} else {
					player.getSlayerManager().setCurrentTask(true, 3);
					sendNPCDialogue(npcId, 9827,
							"Your new assignment is: "
									+ player.getSlayerManager().getCurrentTask().getName().replace("$", "'") + "; only "
									+ player.getSlayerManager().getCount() + " more to go.");
					stage = 1;
					try {
						player.refreshTask();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} else if (componentId == OPTION_4) {
				if (player.getSkills().getLevel(Skills.SLAYER) < 90 && player.getSkills().getCombatLevel() < 110) {
					sendNPCDialogue(npcId, 9827,
							"You need at least a level of 90 slayer and 110 combat to get boss tasks.");
					stage = 1;
					return;
				} else {
					player.getSlayerManager().setCurrentTask(true, 4);
					sendNPCDialogue(npcId, 9827,
							"Your new assignment is: "
									+ player.getSlayerManager().getCurrentTask().getName().replace("$", "'") + "; only "
									+ player.getSlayerManager().getCount() + " more to go.");
					stage = 1;
					try {
						player.refreshTask();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} else if (stage == 1)
			end();
	}

	@Override
	public void finish() {

	}
}
