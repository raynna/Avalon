package com.rs.game.player.dialogues.npcs;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.dialogues.Dialogue;

public class Rasolo extends Dialogue {

	private int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		stageName = "FirstStage";
		sendOptionsDialogue("Choose an option.", "Claim Mystery box", "Tell me more about the Mystery box");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stageName == "FirstStage") {
			if (componentId == OPTION_1) {
				stageName = "ClaimMysteryBox";
				sendPlayerDialogue(9827, "I would like to claim a mystery box.");
			} else if (componentId == OPTION_2) {
				stageName = "TellMore";
				sendPlayerDialogue(9827, "Tell me more about the Mystery box.");
			}
		} else if (stageName == "ClaimMysteryBox") {
			if (player.canClaimBox()) {
				if (player.getInventory().hasFreeSlots())
					stageName = "GiveMysteryBox";
				else
					stageName = "NoSpace";
				sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
						"Look like i got one waiting for you right here, here you go!" }, IS_NPC, npcId, 9827);
			} else {
				stageName = "TellTime";
				sendEntityDialogue(SEND_2_TEXT_CHAT,
						new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
								"You can only gain a Mystery box every 30 minutes, Come back later." },
						IS_NPC, npcId, 9827);
			}
		} else if (stageName == "TellTime") {
			stageName = "end";
			/*
			 * long gameSessionMinutes =
			 * TimeUnit.MILLISECONDS.toMinutes(player.sessionTime); long
			 * gameSessionSeconds =
			 * TimeUnit.MILLISECONDS.toSeconds(player.sessionTime);
			 * sendItemDialogue(6199, 1, "You must wait " + (59 -
			 * gameSessionMinutes) + " minutes & " + (60 - gameSessionSeconds) +
			 * " seconds."); sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] {
			 * NPCDefinitions.getNPCDefinitions(npcId).name,
			 * "You can't do that yet." }, // "You // can // claim // your //
			 * mystery // box // in;<br> // " // + // (59 // - //
			 * player.gameSessionMinutes) // + // " // minutes // & // " // + //
			 * (60 // - // player.gameSessionSeconds) // + // " // seconds." //
			 * }, IS_NPC, npcId, 9827);
			 */
		} else if (stageName == "TellMore") {
			stageName = "TellMore2";
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"From me you can claim a Mystery box every 30 minutes of game session, If you logout the session timer will be reset." },
					IS_NPC, npcId, 9827);
		} else if (stageName == "TellMore2") {
			stageName = "TellMore3";
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"The Mystery box contains untradeable cosmetic items and the box will dissapear once you opened it." },
					IS_NPC, npcId, 9827);
		} else if (stageName == "TellMore3") {
			stageName = "Done";
			sendEntityDialogue(SEND_2_TEXT_CHAT,
					new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
							"Once you claimed it, you will have to wait 30 minutes before claiming a new one." },
					IS_NPC, npcId, 9827);
		} else if (stageName == "GiveMysteryBox") {
			stageName = "Done";
			player.setClaimBox(false);
			player.getInventory().addItem(6199, 1);
			sendEntityDialogue(SEND_ITEM_DIALOGUE, new String[] { ItemDefinitions.getItemDefinitions(6199).name,
					"Rasolo gives you " + 1 + " mystery boxes." }, IS_ITEM, 6199, -1);
		} else if (stageName == "NoSpace") {
			stageName = "end";
			sendEntityDialogue(SEND_2_TEXT_CHAT, new String[] { NPCDefinitions.getNPCDefinitions(npcId).name,
					"Looks like you have no inventory space to claim this." }, IS_NPC, npcId, 9827);
		} else if (stageName == "Done") {
			stageName = "end";
			sendPlayerDialogue(9827, "Hey, thanks!");
		} else if (stageName == "end")
			end();

	}

	@Override
	public void finish() {

	}
}