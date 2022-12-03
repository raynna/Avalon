package com.rs.game.player.dialogues;

import com.rs.game.player.Skills;
import com.rs.game.player.content.AncientEffigies;
import com.rs.utils.Utils;

public class AncientEffigiesD extends Dialogue {

	/*
	 * Tristam
	 */

	int itemId, skill1, skill2;

	@Override
	public void start() {
		itemId = (Integer) parameters[0];
		sendItemDialogue(AncientEffigies.STARVED_ANCIENT_EFFIGY, 1,
				"As you inspect the ancient effigy, you begin to feel a "
						+ "strange sensation of relic searching your mind, drawing on your knowledge.");
		int random = Utils.getRandom(7);
		skill1 = AncientEffigies.SKILL_1[random];
		skill2 = AncientEffigies.SKILL_2[random];
		stage = 1;

	}

	@Override
	public void run(int interfaceId, int cid) {
		switch (stage) {
		case END:
			end();
			break;
		case 1:
			sendDialogue("Images from your experiences of " + AncientEffigies.getMessage(skill1) + " fill your mind.");
			stage = 2;
			break;
		case 2:
			player.temporaryAttribute().put("skill1", skill1);
			player.temporaryAttribute().put("skill2", skill2);
			sendOptions("Which images do you wish to focus on?", Skills.SKILL_NAME[skill1], Skills.SKILL_NAME[skill2]);
			stage = 3;
			break;
		case 3:
			switch (cid) {
			case OPTION_1:
				if (player.getSkills().getLevel((Integer) player.temporaryAttribute().get("skill1")) < AncientEffigies
						.getRequiredLevel(itemId)) {
					sendDialogue(
							"The images in your mind fade; the ancient effigy seems to desire knowledge of experiences you have not yet, had.");
					player.sm("You require atleast a level of " + AncientEffigies.getRequiredLevel(itemId) + " "
							+ Skills.SKILL_NAME[((Integer) player.temporaryAttribute().get("skill1"))]
							+ " to continue any further.");
					stage = END;
				} else {
					player.temporaryAttribute().put("skill", skill1);
					sendDialogue(
							"As you focus on your memories, you can almost hear a voice in the back of your mind whispering to you...");
					stage = 4;
				}
				break;
			case OPTION_2:
				if (player.getSkills().getLevel((Integer) player.temporaryAttribute().get("skill2")) < AncientEffigies
						.getRequiredLevel(itemId)) {
					sendDialogue(
							"The images in your mind fade; the ancient effigy seems to desire knowledge of experiences you have not yet, had.");
					player.sm("You require atleast a level of " + AncientEffigies.getRequiredLevel(itemId) + " "
							+ Skills.SKILL_NAME[((Integer) player.temporaryAttribute().get("skill2"))]
							+ " to continue any further.");
					stage = END;
				} else {
					player.temporaryAttribute().put("skill", skill2);
					sendDialogue(
							"As you focus on your memories, you can almost hear a voice in the back of your mind whispering to you..");
					stage = 4;
				}
				break;
			}
			break;
		case 4:
			AncientEffigies.continueEffigy(player, itemId);
			player.getSkills().addXp((Integer) player.temporaryAttribute().get("skill"),
					AncientEffigies.getExp(itemId) / 2.5);
			player.sm("You focus your memory on" + " "
					+ Skills.SKILL_NAME[(Integer) player.temporaryAttribute().get("skill")] + ".");
			sendDialogue(
					"The ancient effigy glows briefly; it seems to be changed, somehow and no longer responds to the same memories as before.");
			stage = 5;
			break;
		case 5:
			sendDialogue(
					"A sudden bolt of inspiration flashes through your mind, revealing a new insight into your experiences!");
			stage = END;
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
