package com.rs.game.player.content.quest.impl.druidicritual.dialogues;


import com.rs.game.item.Item;
import com.rs.game.player.content.quest.Quest;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;

public class Sanfew extends Dialogue {
	
	@Override
	public int getNPCID() {
		return 454;
	}

	@Override
	public void start() {
		int progress = player.getQuestManager().get(Quests.DRUIDIC_RITUAL).getStage();
		if (progress == 1) {
			sendNPCDialogue(getNPCID(), NORMAL, "What can I do for you young 'un?");
			stage = 4;
		} else if (progress == 20) {
			sendNPCDialogue(getNPCID(), NORMAL, "Did you bring me the required ingredients for the potion?");
			stage = 26;
		} else {
			//TODO
			player.getPackets().sendGameMessage("Sanfew seems very busy, and is not interested in talking.");
		}
			
	}

	@Override
	public void run(int interfaceId, int componentId) {
		Quest quest = player.getQuestManager().get(Quests.DRUIDIC_RITUAL);
		switch(stage) {
		case -1:
			sendPlayerDialogue(NORMAL, "So... I've heard you druids might be able to teach me", "Herblore...");
			stage = 0;
			break;
		case 0:
			sendNPCDialogue(getNPCID(), BLANK, "Herblore eh? You're probably best off talking to", "Kaqemeex about that; he's the best Herblore teacher we", "currently have. I believe at the moment he's at our stone", "circle just north of here.");
			stage = 1;
			break;
		case 1:
			sendNPCDialogue(getNPCID(), BLANK, "'Course he's just the best teacher. You don't need him to", "show you how it works. Not like in my day. Back then you", "had to go running all over the place before you so much as", "learned how to wipe muck off a leaf.");
			stage = 2;
			break;
		case 2:
			sendNPCDialogue(getNPCID(), SAD, "Still, that's what the kids are all like these days. No time", "to stop and listen to a teacher. With their 'rune armour',", "and their 'chilly-chompers' and their loud music...");
			stage = 3;
			break;
		case 3:
			sendPlayerDialogue(NORMAL, "Uh... Thanks?");
			stage = 50;
			break;
		/*
		 * Druidic Ritual Dialogue
		 */
		case 4:
			sendOptionsDialogue(DEFAULT, "I've been sent to help purify the Varrock stone circle.", "Actually, I don't need to speak to you.");
			stage = 5;
			break;
		case 5:
			switch(componentId) {
			case OPTION_1:
				sendPlayerChat(Mood.HAPPY, "I've been sent to assist you with the ritual to purify the", "Varrockian stone circle.");
				stage = 6;
				break;
			case OPTION_2:
				sendNPCDialogue(getNPCID(), BLANK, "Well, we all make mistakes sometimes.");
				stage = 50;
				break;
			}
			break;
		case 6:
			sendNPCDialogue(getNPCID(), BLANK, "Well, what I'm struggling with right now is the meats", "needed for the potion to honour Guthix. I need the raw", "meat of four dirrerent animals for it, but not just any", "old meats will do.");
			stage = 7;
			break;
		case 7:
			sendNPCDialogue(getNPCID(), BLANK, "Each meat has to be dipped individually into the", "Cauldron of Thunder for it to work correctly.");
			quest.setStage(20);
			stage = 8;
			break;
		case 8:
			sendOptionsDialogue(DEFAULT, "Where can I find this cauldron?", "Ok, I'll do that then.");
			stage = 9;
			break;
		case 9:
			switch(componentId) {
			case OPTION_1:
				sendPlayerChat(Mood.ASKING, "Where can I find this cauldron?");
				stage = 10;
				break;
			case OPTION_2:
				sendPlayerChat(Mood.NORMAL, "Ok, I'll do that then.");
				stage = 50;
				break;
			}
			break;
		case 10:
			sendNPCDialogue(getNPCID(), BLANK, "It's located somewhere in the mysterious underground", "halls which are located somewhere in the woods just", "South of here. They are too dangerous for me to go", "myself however.");
			stage = 8;
			break;
		
		case 26:
			if (player.getInventory().contains(new Item(522), new Item(523), new Item(524), new Item(525))) {
				sendPlayerChat(Mood.HAPPY, "Yes, I have all four now!");
				stage = 28;
			} else {
				sendPlayerChat(Mood.NORMAL, "No, not yet...");
				stage = 27;
			}
			break;
		case 27:
			sendNPCChat(Mood.NORMAL, "Well let me know when you do young 'un.");
			stage = 50;
			break;
		case 28:
			sendNPCChat(Mood.NORMAL, "Well, hand em' over then lad!");
			stage = 29;
			break;
		case 29:
			sendNPCChat(Mood.HAPPY, "Thank you so much adventurer! These meats will allow", "our potion to honour Guthix to be completed, and bring", "us one step closer to reclaiming our stone circle!");
			stage = 30;
			break;
		case 30:
			sendNPCChat(Mood.HAPPY, "Now go and talk to Kaqemeex and he will introduce", "you to the wonderful world of herblore and the potion", "making!");
			player.getInventory().removeItems(new Item(522), new Item(523), new Item(524), new Item(525));
			quest.setStage(99);
			stage = 50;
			break;
		case 50:
			end();
			break;
		}
	}
	
	@Override
	public void finish() {
		// TODO Auto-generated method stub
		
	}

}
