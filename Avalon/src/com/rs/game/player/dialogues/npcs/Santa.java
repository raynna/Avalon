package com.rs.game.player.dialogues.npcs;

import com.rs.game.player.dialogues.Dialogue;

public class Santa extends Dialogue {

	int npcId;

	@Override
	public void start() {
		npcId = (Integer) parameters[0];
		sendNPCDialogue(npcId, 9840, "Ho-Ho-Ho! It appears christmas is over!");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 0;
			sendNPCDialogue(npcId, 9840, "Happy New Year from the AvalonPK Staff Team!");
		} else if (stage == 0) {
			stage = 1;
			end();
		}
	}

	@Override
	public void finish() {

	}
}
/*
 * if (player.helptSanta) { if (!player.recievedReward) { giveReward(); } else {
 * if (!player.isSnowNPC) { stage = 25; sendNPCDialogue(npcId, 9840,
 * "Want me to turn you into an Christmas NPC?"); } else { stage = 35;
 * sendNPCDialogue(npcId, 9840, "You want to become a human again?"); } } } else
 * if (!player.helpingSanta) { sendPlayerDialogue(9827,
 * "Hello santa, Wonderful Christmas isn't it?"); } else {
 * sendNPCDialogue(npcId, 9847, "Have you found the imps yet?"); } } else if
 * (stage == 0) { stage = 1; if (!player.helpingSanta) { sendNPCDialogue(npcId,
 * 9843, "Well, yes.."); } else { if (player.impsFound == 0) {
 * sendPlayerDialogue(9836, "No.. I haven't found any yet."); } else if
 * (player.impsFound > 0 && player.impsFound < 3) { sendPlayerDialogue(9836,
 * "Yes, i actually found " + player.impsFound +" imps so far!"); } else if
 * (player.impsFound == 3) { sendPlayerDialogue(9836,
 * "Yes, i have saved them all!"); } } } else if (stage == 1) { stage = 2; if
 * (!player.helpingSanta) { sendNPCDialogue(npcId, 9836,
 * "There is a problem this year.."); } else { if (player.impsFound == 0) {
 * stage = 50; sendNPCDialogue(npcId, 9843,
 * "Alright, come back to me when you found them."); } else if (player.impsFound
 * == 1) { stage = 50; sendNPCDialogue(npcId, 9840,
 * "Good job! only 2 more to go!"); } else if (player.impsFound == 2) { stage =
 * 50; sendNPCDialogue(npcId, 9840, "Good job! only 1 more to go!"); } else if
 * (player.impsFound == 3) { sendNPCDialogue(npcId, 9840,
 * "Excellent! Thank you so much!"); } } } else if (stage == 2) { stage = 3; if
 * (!player.helpingSanta) { sendPlayerDialogue(9843, "Whats wrong?"); } else {
 * if (player.impsFound == 3) { sendNPCDialogue(npcId, 9840,
 * "Here, please take this reward!"); } } } else if (stage == 3) { stage = 4; if
 * (!player.helpingSanta) { sendNPCDialogue(npcId, 9836,
 * "My imp workers has ran away and i need to deliver all the presents before christmas is ruined."
 * ); } else { player.helptSanta = true; sendPlayerDialogue(9847,
 * "Thank you Santa!"); giveReward(); } } else if (stage == 4) { stage = 5; if
 * (!player.helpingSanta) { sendNPCDialogue(npcId, 9843,
 * "Could you help me find them?"); } else { stage = 50; sendNPCDialogue(npcId,
 * 9836, "Oh yes! almost forgot, Merry Christmas!"); } } else if (stage == 5) {
 * stage = 6; sendOptionsDialogue("Will you help santa?",
 * "Sure! I'd like to help!", "I don't have time for that, sorry."); } else if
 * (stage == 6) { if (componentId == OPTION_1) { stage = 7;
 * sendPlayerDialogue(9847, "Sure! I'd like to help!"); player.helpingSanta =
 * true; } else if (componentId == OPTION_2) { stage = 7;
 * sendPlayerDialogue(9836, "I don't have time for that, sorry.");
 * player.helpingSanta = false; } } else if (stage == 7) { if
 * (!player.helpingSanta) { stage = 50; sendNPCDialogue(npcId, 9836,
 * "Aww.. Have a good christmas!"); } else if (player.helpingSanta) { stage =
 * 50; sendNPCDialogue(npcId, 9847, "Thank you very much! Goodluck my friend!");
 * } } else if (stage == 50) { end(); } else if (stage == 25) { stage = 26;
 * sendOptionsDialogue("Want to become an NPC?", "Sure! That'd be cool!",
 * "Im fine, thanks."); } else if (stage == 26) { if (componentId == OPTION_1) {
 * stage = 27; sendPlayerDialogue(9847, "Sure! That'd be cool!"); } else if
 * (componentId == OPTION_2) { stage = 50; sendPlayerDialogue(9836,
 * "Im fine, thanks."); } } else if (stage == 27) { int[] ChristmasNPCS = {
 * 14765, 14766, 8541, 8536, 6742, 6743, 6744, 6745, 6746, 6747, 6748, 6749,
 * 13637}; stage = 50; sendNPCDialogue(npcId, 9847, "As you wish!");
 * player.isSnowNPC = true;
 * player.getAppearence().transformIntoNPC(ChristmasNPCS[Utils.random(
 * ChristmasNPCS.length)]); player.getPackets().sendGameMessage(
 * "You turn into a christmas npc"); } else if (stage == 35) { stage = 36;
 * sendOptionsDialogue("Want to become an Human?", "Yes, please.",
 * "Nah, It's cool."); } else if (stage == 36) { if (componentId == OPTION_1) {
 * stage = 37; sendPlayerDialogue(9847, "Yes, please."); } else if (componentId
 * == OPTION_2) { stage = 50; sendPlayerDialogue(9836, "Nah, It's cool."); } }
 * else if (stage == 37) { stage = 50; sendNPCDialogue(npcId, 9847,
 * "As you wish!"); player.isSnowNPC = false;
 * player.getAppearence().transformIntoNPC(-1);
 * player.getPackets().sendGameMessage("You turn back to a human."); } }
 * 
 * @Override public void finish() {
 * 
 * }
 * 
 * 
 * public void giveReward() { player.getInventory().addItem(6542, 1);
 * player.recievedReward = true; }
 * 
 * }
 */