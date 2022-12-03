package com.rs.game.player.dialogues;

public class RichardManeyMembershipD extends Dialogue {

	int npcId = 11460;

	@Override
	public void start() {
		sendNPCDialogue(npcId, 9827, "Hello there good sir, what can I do for you today?");
		stage = 0;
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch(stage) {
		case 0:
			sendOptionsDialogue("what can I do for you today?","Ask about Avalon Membership", "Ask how to become wealthy", "Nevermind.");
			stage = 1;
			break;
		case 1:
			if (componentId == OPTION_1) {
				if (player.isMember()) {
					sendNPCDialogue(npcId, 9827, "Well let me see..You look like you're currently a Avalon Member already.");
					stage = 2;
				} else {
					player.getPackets().sendOpenURL("https://avalon-rsps.com/forum/Thread-Other-Membership-Benefits-WIP");
					sendNPCDialogue(npcId, 9827, "I've sent you some basic information about Avalon Membership, it's updated frequently.");
				}
			} else if (componentId == OPTION_2){
				player.getPackets().sendOpenURL("https://avalon-rsps.com/forum/Forum-Guides");
				sendNPCDialogue(npcId, 9827, "I've sent you some useful Guides created by fellow players of Avalon. Give them a good read.");
			} else {
				end();
			}
			break;
		case 2:
			if (stage == 2) {
				sendPlayerChat(Mood.CHEERFULLY_TALK, "Oh right, my membership will expire: " + player.getMemberTill());
				stage = 0;
			}
			break;
		}
	}

	@Override
	public void finish() {
	}
}