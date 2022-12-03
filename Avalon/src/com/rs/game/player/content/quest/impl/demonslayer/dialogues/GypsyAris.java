/**
 * Copyright 2015 - 2015 (C) Project-Arrow
 * 
 * File name : GypsyAris.java
 * Created on : Aug 9, 2015
 * Author     : Ethan
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the GNU Lesser General Public License
 * (LGPL) version 2.1 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/lgpl-2.1.html
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * Contributors:
 * Tristan Swasey / Abexlry
 * Brandon / Frostbite
 * Ethan Millard / Fate
 * Yassin / Displee / Maffia-rpg
 * Austin Owens / Whitelight
 */
package com.rs.game.player.content.quest.impl.demonslayer.dialogues;

import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.State.QuestState;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;

/**
 * The Class GypsyAris.
 */
public class GypsyAris extends Dialogue {

	private int npcId;

	@Override
	public int getNPCID() {
		return 9362;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arrow.game.content.dialogues.Dialogue#start()
	 */
	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		stageInt = (Integer) parameters[1];
		if (player.getQuestManager().get(Quests.DEMON_SLAYER).getStage() == 1) {
			sendNPCDialogue(npcId, HAPPY, "Have you killed the demon yet?");
			stageInt = 55;
		} else if (player.getQuestManager().get(Quests.DEMON_SLAYER).getStage() == 2) {
			sendPlayerChat(Mood.HAPPY, "I killed the demon!");
			stageInt = 50;
		} else if (player.getQuestManager().get(Quests.DEMON_SLAYER).getState() == QuestState.COMPLETED) {
			sendNPCDialogue(npcId, HAPPY, "Hello young one.");
			stageInt = 100;
		} else {
			sendNPCDialogue(npcId, HAPPY, "Hello young one.");
			stageInt = -1;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arrow.game.content.dialogues.Dialogue#run(int, int)
	 */
	@Override
	public void run(int interfaceId, int componentId) {
		switch (stageInt) {
		case -1:
			sendNPCDialogue(npcId, HAPPY, "Cross my palm with silver and the future will be<br>revealed to you.");
			stageInt = 0;
			break;
		case 0:
			sendOptionsDialogue(DEFAULT, "Ok, here you go.", "Who are you calling young one?!",
					"No, I don't believe in that stuff.", "With silver?");
			stageInt = 1;
			break;
		case 1:
			switch (componentId) {
			case OPTION_1:
				if (player.getInventory().containsOneItem(2402) || player.getBank().getItem(2402) != null) {
					sendNPCChat(Mood.SAD, "You already have a silverlight.");
					stageInt = -2;
					return;
				}
				if (!player.getInventory().hasFreeSlots()) {
					sendNPCChat(Mood.SAD, "You don't have any inventory space for the silverlight.");
					stageInt = -2;
					return;
				}
				player.getQuestManager().get(Quests.DEMON_SLAYER).sendStartOption();
				player.getInventory().addItem(2402, 1);
				stageInt = -2;
				break;
			case OPTION_2:
				sendPlayerChat(Mood.MAD, "Who are you calling young one?!");
				stageInt = 3;
				break;
			case OPTION_3:
				sendPlayerChat(Mood.CONFUSED, "No, I don't believe in that stuff.");
				stageInt = 2;
				break;
			case OPTION_4:
				sendPlayerChat(Mood.QUESTIONING, "With silver?");
				stageInt = 13;
				break;
			}
			break;
		case 2:
			sendNPCChat(Mood.SAD, "Ok suit yourself.");
			stageInt = -2;
			break;
		case 3:
			sendNPCChat(Mood.PLAIN_TALKING, "You have been on this world a relatively short time. At",
					"least compared to me.");
			if (player.getQuestManager().get(Quests.DEMON_SLAYER).getState() == QuestState.COMPLETED)
				stageInt = -2;
			else
				stageInt = 4;
			break;
		case 4:
			sendNPCChat(Mood.ASKING, "So, do you want your fortune told or not?");
			stageInt = 5;
			break;
		case 5:
			sendOptionsDialogue(DEFAULT, "Yes please.", "No, I don't believe in that stuff.",
					"Ooh, how old are you then?");
			stageInt = 6;
			break;
		case 6:
			switch (componentId) {
			case OPTION_1:
				break;
			case OPTION_2:
				sendPlayerChat(Mood.CONFUSED, "No, I don't believe in that stuff.");
				stageInt = 2;
				break;
			case OPTION_3:
				sendPlayerChat(Mood.ASKING, "Ooh, how old are you then?");
				stageInt = 7;
				break;
			}
			break;
		case 7:
			sendNPCChat(Mood.CHEERFULLY_TALK, "Older than you imagine.");
			stageInt = 8;
			break;
		case 8:
			sendOptionsDialogue(DEFAULT, "Believe me, I have a good imagination.",
					"How do you know how old I think you are?", "Oh, pretty old then.");
			stageInt = 9;
			break;
		case 9:
			switch (componentId) {
			case OPTION_1:
				sendPlayerChat(Mood.CHEERFULLY_TALK, "Believe me, I have a good imagination.");
				stageInt = 12;
				break;
			case OPTION_2:
				sendPlayerChat(Mood.CONFUSED, "How do you know how old I think you are?");
				stageInt = 11;
				break;
			case OPTION_3:
				sendPlayerChat(Mood.QUESTIONING, "Oh, pretty old then.");
				stageInt = 10;
				break;
			}
			break;
		case 10:
			sendNPCChat(Mood.SAD, "Yes I am old. Don't rub it in.");
			stageInt = -2;
			break;
		case 11:
			sendNPCChat(Mood.CHEERFULLY_TALK, "I have the power to know, just as I have the power to",
					"see the future.");
			stageInt = -2;
			break;
		case 12:
			sendNPCChat(Mood.HAPPY, "You seem like just the sort of person who would want", "their fortune told then.");
			stageInt = -2;
			break;
		case 13:
			sendNPCChat(Mood.CHEERFULLY_TALK, "Oh, sorry, I forgot. With gold, I mean. They haven't",
					"used silver coins since before you were born!", "So, do", "you want your fortune told?");
			stageInt = 14;
			break;
		case 14:
			sendOptionsDialogue(DEFAULT, "Ok, here you go.", "No, I don't believe in that stuff.");
			stageInt = 15;
			break;
		case 15:
			switch (componentId) {
			case OPTION_1:

				break;
			case OPTION_2:
				break;
			}
			break;
		case 50:
			sendNPCChat(Mood.CHEERFULLY_TALK, "Did you really?");
			stageInt = 51;
			break;
		case 51:
			sendPlayerChat(Mood.HAPPY, "Yes, he won't harm Varrock anymore.");
			stageInt = 52;
			break;
		case 52:
			sendNPCChat(Mood.CHEERFULLY_TALK, "Thank you so much.");
			stageInt = 53;
			break;
		case 53:
			end();
			player.getQuestManager().get(Quests.DEMON_SLAYER).setStage(3);
			player.getQuestManager().get(Quests.DEMON_SLAYER).finish();
			break;
		case 55:
			sendPlayerChat(Mood.SAD, "No, i haven't.");
			stageInt = 56;
			break;
		case 56:
			sendNPCChat(Mood.CHEERFULLY_TALK, "Hurry up!");
			stageInt = -2;
			break;
		case 100:
			sendOptionsDialogue(DEFAULT, "I've lost my silverlight", "Who are you calling young one?!");
			stageInt = 101;
			break;
		case 101:
			switch (componentId) {
			case OPTION_1:
				stageInt = 102;
				if (player.getInventory().containsOneItem(2402) || player.getBank().getItem(2402) != null) {
					sendNPCChat(Mood.SAD, "You already have a silverlight.");
					stageInt = -2;
					return;
				}
				if (!player.getInventory().hasFreeSlots()) {
					sendNPCChat(Mood.SAD, "You don't have any inventory space for the silverlight.");
					stageInt = -2;
					return;
				}
				player.getQuestManager().get(Quests.DEMON_SLAYER).sendStartOption();
				player.getInventory().addItem(2402, 1);
				stageInt = -2;
				break;
			case OPTION_2:
				sendPlayerChat(Mood.MAD, "Who are you calling young one?!");
				stageInt = 3;
				break;
			}
			break;
		case 102:
			sendPlayerChat(Mood.QUESTIONING, "I lost my silverlight, can i have a new one?");
			stageInt = 103;
			break;
		case 103:
			if (player.getInventory().containsOneItem(2402) || player.getBank().getItem(2402) != null) {
				sendNPCChat(Mood.SAD, "You already have a silverlight.");
				stageInt = -2;
				return;
			}
			if (!player.getInventory().hasFreeSlots()) {
				sendNPCChat(Mood.SAD, "You don't have any inventory space for the silverlight.");
				stageInt = -2;
				return;
			}
			player.getInventory().addItem(2402, 1);
			stageInt = -2;
			sendNPCChat(Mood.CHEERFULLY_TALK, "Yes, here you go.");
			break;
		case -2:
			end();
			break;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.arrow.game.content.dialogues.Dialogue#finish()
	 */
	@Override
	public void finish() {

	}

}
