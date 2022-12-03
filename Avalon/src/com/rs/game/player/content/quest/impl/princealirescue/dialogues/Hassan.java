package com.rs.game.player.content.quest.impl.princealirescue.dialogues;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.State.QuestState;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;

/**
 * @Author Frostbite
 * @Contact<frostbitersps@gmail.com;skype:frostbitersps>
 */
public class Hassan extends Dialogue {

	@Override
	public int getNPCID() {
		return 923;
	}

	@Override
	public void start() {
		stageInt = (Integer) parameters[0];
		int progress = player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).getStage();
		QuestState state = player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).getState();
		/**
		 * Completion
		 */
		if(progress == 8)
			stageInt = 2;
		/**
		 * During quest.
		 */
		if(progress > 0 && progress < 8) 
			stageInt = 1;

		/**
		 * Already Completed
		 */
		if(state == QuestState.COMPLETED)
			stageInt = 3;
		
		switch(stageInt) {
		case 0:
			sendNPCChat(Mood.NORMAL, "Greetings I am Hassan, Chancellor to the Emir of Al-" +
					"Kharid.");
			stageInt = -1;
			break;
		case 1:
			sendNPCChat(Mood.ASKING, "Have you found the spymaster, Osman yet? You " +
					"cannot proceed in your task without reporting to him.");
			stageInt = -2;
			break;

		case 2:
			sendNPCChat(Mood.HAPPY, "You have the eternal gratitude for the Emir for "
					+ "rescuing my son. I am authorised to pay you 700 "
					+ "coins.");
			stageInt = -3;
			break;

		case 3:
			sendNPCChat(Mood.HAPPY, "You are a friend of the own of Al-Kharid. If we have "
					+ "more taks to complete, we will ask you. Pelase, keep in "
					+ "contact. Good employees are not to easy to find.");
			stageInt = -2;
			break;
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stageInt) {
		case -1:
			sendOptionsDialogue(DEFAULT, "Can I help you? You must need some help here in the desert.", "It's just too hot here. How can you stand it?", "" +
					"Do you mind if I just kill your warriors?");
			stageInt = 1;
			break;

		case 1:
			switch (componentId) {
			case OPTION_1:
				player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).sendStartOption();
				break;
			case OPTION_2:
				sendNPCChat(Mood.NORMAL, "We manage, in our humble way. We are a wealthy " +
						"town and we have water. It cures many thirsts.");
				stageInt = 2;
				break;
			case OPTION_3:
				sendNPCChat(Mood.NORMAL, "You are welcome. They are not expensive. We have " +
						"them here to stop the elite guard being bothered. They " +
						"are a little harder to kill.");
				stageInt = -2;
				break;
			}
			break;

		case 2:
			sendHandedItem(1937, "The chancellor hands your some water.");
			if(player.getInventory().getFreeSlots() < 1) {
				World.addGroundItem(new Item(1937), new WorldTile(player));
			} else {
				player.getInventory().addItem(1937, 1);
			}
			stageInt = -2;
			break;

		case -2:
			end();
			break;

		case -3:
			end();
			player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).setStage(9);
			player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).setState(QuestState.COMPLETED);
			player.getPackets().sendGameMessage("The chancellor pays you 700 coins.");
			player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).finish();
			break;
		}
	}

	@Override
	public void finish() {

	}
}
