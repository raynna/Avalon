package com.rs.game.player.dialogues;

import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.slayer.Slayer.SlayerMaster;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;

public class SlayerMasterD extends Dialogue {

	private int npcId = 9085;
	private SlayerMaster master;

	@Override
	public void start() {
		master = (SlayerMaster) parameters[0];
		sendNPCDialogue(npcId, 9827, "'Ello and what are you after then?");
		stage = -1;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (player.getSlayerManager().getCurrentTask() != null) {
				sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "How many monsters do I have left?",
						"What do you have in your shop?", "Give me a tip.", "Nothing, Nevermind.");
				stage = 0;
			} else {
				sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Please give me a task.",
						"What do you have in your shop?", "Nothing, Nevermind.");
				stage = 1;
			}
		} else if (stage == 0) {
			if (componentId == OPTION_1) {
				player.getSlayerManager().checkKillsLeft(true);
				end();
			} else if (componentId == OPTION_2) {
				sendNPCDialogue(npcId, 9827, "Only the best slayer equipment money could buy. Come check it out.");
				stage = 5;
			} else if (componentId == OPTION_3) {
				stage = 6;
				if (player.getSlayerManager().getCurrentTask() == null) {
					sendNPCDialogue(npcId, 9827, "You currently don't have a task.");
					return;
				}
				String[] tipDialouges = player.getSlayerManager().getCurrentTask().getTips();
				if (tipDialouges != null && tipDialouges.length != 0) {
					String chosenDialouge = tipDialouges[Utils.random(tipDialouges.length)].toString().replace("$",
							"'");
					if (chosenDialouge == null || chosenDialouge.equals(""))
						sendNPCDialogue(npcId, 9827, "I don't have any tips for you currently.");
					else
						sendNPCDialogue(npcId, 9827, player.getSlayerManager().getCurrentTask().getName()
								+ "'s can be found in the " + chosenDialouge);
				} else
					sendNPCDialogue(npcId, 9827, "I don't have any tips for you currently.");
			} else
				end();
		} else if (stage == 1) {
			if (componentId == OPTION_1) {
				sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Easy task", "Medium task", "Hard task", "Boss Task");
				stage = 10;
			} else if (componentId == OPTION_2) {
				sendNPCDialogue(npcId, 9827, "Only the best slayer equipment money could buy. Come check it out.");
				stage = 5;
			} else
				end();
		} else if (stage == 2) {
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Can you become my master?",
					"What do you have in your shop?", "Nothing, Nevermind.");
			stage = 3;
		} else if (stage == 3) {
			if (componentId == OPTION_1) {
				stage = 6;
				if (player.getSkills().getCombatLevelWithSummoning() < master.getRequiredCombatLevel())
					sendNPCDialogue(npcId, 9827, "Your too weak overall, come back when you've become stronger.");
				else if (player.getSkills().getLevel(Skills.SLAYER) < master.getRequiredSlayerLevel()) {
					sendNPCDialogue(npcId, 9827,
							"Your slayer level is too low to take on my challenges, come back when you have a level of at least "
									+ master.getRequiredSlayerLevel() + " slayer.");
				} else {
					sendNPCDialogue(npcId, 9827,
							"You meet my requirements, so therefore I will agree to leading you. Good luck adventurer, you will need it.");
					player.getSlayerManager().setCurrentMaster(master);
				}
			} else if (componentId == OPTION_2) {
				stage = 5;
				sendNPCDialogue(npcId, 9827, "Only the best slayer equipment money could buy. Come check it out.");
			} else {
				end();
			}
		} else if (stage == 10) {
			if (componentId == OPTION_1) {
				player.getSlayerManager().setCurrentTask(true, 1);
				sendNPCDialogue(npcId, 9827,
						"Your new assignment is: "
								+ player.getSlayerManager().getCurrentTask().getName().replace("$", "'") + "; only "
								+ player.getSlayerManager().getCount() + " more to go.");
				stage = 6;
			} else if (componentId == OPTION_2) {
				if (player.getSkills().getLevel(Skills.SLAYER) < 30) {
					sendNPCDialogue(npcId, 9827, "You need at least a level of 30 slayer to get medium tasks.");
					stage = 6;
				} else {
					player.getSlayerManager().setCurrentTask(true, 2);
					sendNPCDialogue(npcId, 9827,
							"Your new assignment is: "
									+ player.getSlayerManager().getCurrentTask().getName().replace("$", "'") + "; only "
									+ player.getSlayerManager().getCount() + " more to go.");
					stage = 6;
				}
			} else if (componentId == OPTION_3) {
				if (player.getSkills().getLevel(Skills.SLAYER) < 60) {
					sendNPCDialogue(npcId, 9827, "You need at least a level of 60 slayer to get hard tasks.");
					stage = 6;
				} else {
					player.getSlayerManager().setCurrentTask(true, 3);
					sendNPCDialogue(npcId, 9827,
							"Your new assignment is: "
									+ player.getSlayerManager().getCurrentTask().getName().replace("$", "'") + "; only "
									+ player.getSlayerManager().getCount() + " more to go.");
					stage = 6;
				}
			} else if (componentId == OPTION_4) {
				if (player.getSkills().getLevel(Skills.SLAYER) < 90 && player.getSkills().getCombatLevel() < 110) {
					sendNPCDialogue(npcId, 9827,
							"You need at least a level of 90 slayer and 110 combat to get boss tasks.");
					stage = 6;
				} else {
					player.getSlayerManager().setCurrentTask(true, 4);
					sendNPCDialogue(npcId, 9827,
							"Your new assignment is: "
									+ player.getSlayerManager().getCurrentTask().getName().replace("$", "'") + "; only "
									+ player.getSlayerManager().getCount() + " more to go.");
					stage = 6;
				}
			}
		} else if (stage == 2) {
			ShopsHandler.openShop(player, 29);
			end();
		} else if (stage == 5) {
			ShopsHandler.openShop(player, 29);
			end();
		} else if (stage == 6)
			end();
	}

	@Override
	public void finish() {

	}

}