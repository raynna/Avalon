package com.rs.game.player.content.quest.impl.princealirescue.dialogues;

import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.impl.princealirescue.PrinceAliRescue;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.Mood;

public class Prince extends Dialogue {

	private NPC ali = World.getNPC(920);
	@SuppressWarnings("unused")
	private NPC ali2 = World.getNPC(921);

	@Override
	public int getNPCID() {
		return 920;
	}

	@Override
	public void start() {
		stageInt = (Integer) parameters[0];
		int progress = player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).getStage();
		switch(progress) {
		case 7:
			stageInt = 1;
			break;
		}
		switch(stageInt) {
		case 1:
			if(player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).getStage() > 6) {
				sendPlayerChat(Mood.HAPPY, "Prince, I come to rescue you.");
				stageInt = 2;
			} else {
				end();
				player.getPackets().sendGameMessage("Can't reach that!");
			}
			break;
		}
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stageInt) {
		case 2:
			sendNPCChat(Mood.HAPPY, "That is very very kind of you, how do I get out?");
			stageInt = 3;
			break;

		case 3:
			sendPlayerChat(Mood.NORMAL, "With a disgise. I have removed the Lady Keli. She is "
					+ "tied up, but will not stay tied up for long.");
			stageInt = 4;
			break;

		case 4:
			sendPlayerChat(Mood.NORMAL, "Take this disguise, and this key.");
			stageInt = 5;
			break;

		case 5:
			for (Item i : PrinceAliRescue.DISGUISE) {
				if (!player.getInventory().contains(i)) {
					player.getPackets().sendGameMessage("You don't have all the parts of the disguise.");
					end();
					return;
				}
			}
			sendDialogue("You hand the disguise and the key to the prince.");
			stageInt = 6;
			break;

		case 6:
			for (Item i : PrinceAliRescue.DISGUISE) {
				player.getInventory().deleteItem(i);
			}
			if(ali.getId() == 920) 
				ali.transformIntoNPC(921);

			player.getQuestManager().get(Quests.PRINCE_ALI_RESCUE).setStage(8);
			sendNPCChat(Mood.HAPPY, "Thank you my friend, I must leave you now. My "
					+ "father will pay you well for this.");
			stageInt = 7;
			break;

		case 7:
			sendPlayerChat(Mood.NORMAL, "Go to Leela, she is close to here.");
			stageInt = 8;
			break;

		case 8:
			sendDialogue("The prince has escaped, well done! You are not a friend of Al- "
					+ "Kharid and may pass through the Al-Kharid toll gate for free.");
			transformAli();
			stageInt = -2;
			break;

		case -2:
			end();
			break;
		}
	}

	private void transformAli() {
		for (final NPC n : World.getNPCs()) {
			if (n == null || n.getId() != 921)
				continue;
			n.transformIntoNPC(920);
			n.sendDeath(n);
		}
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
