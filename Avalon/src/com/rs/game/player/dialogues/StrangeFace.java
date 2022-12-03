package com.rs.game.player.dialogues;

public class StrangeFace extends Dialogue {

	@Override
	public void start() {
		sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { player.getDisplayName(), "Hello?" }, IS_PLAYER,
				player.getIndex(), 9827);

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stageInt == -1) {
			stageInt = 0;
			sendDialogue("Hello.");
			player.getPackets().sendVoice(7890);
			// set camera
		} else if (stageInt == 0) {
			stageInt = 1;
			sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { player.getDisplayName(), "Woah!" }, IS_PLAYER,
					player.getIndex(), 9827);
		} else if (stageInt == 1) {
			stageInt = 2;
			sendDialogue(

					"It is intrigring that you took so before comming to me. Fearful,", "traveller?");
			player.getPackets().sendVoice(7895);
		} else if (stageInt == 2) {
			stageInt = 3;
			sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { player.getDisplayName(), "Should I be?" }, IS_PLAYER,
					player.getIndex(), 9827);
		} else if (stageInt == 3) {
			stageInt = 4;
			sendDialogue(

					"It is my duty inform you that many warriors fight here, and they",
					"all succumb to defeat eventually. If that instills terror in you, walk", "away now.");
			player.getPackets().sendVoice(7881);
		} else if (stageInt == 4) {
			stageInt = 5;
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "There are monsters in the tower?" }, IS_PLAYER,
					player.getIndex(), 9827);
		} else if (stageInt == 5) {
			stageInt = 6;
			sendDialogue(

					"If that is the terminolgy you would use, yes. Through the powers",
					"bestowed upon me by my creator, I can generate opponents for you",
					"based on your memories of them. Men and women have fought here", "for generations.");
			player.getPackets().sendVoice(7908);
		} else if (stageInt == 6) {
			stageInt = 7;
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), "Impressive. So you control the tower?" }, IS_PLAYER,
					player.getIndex(), 9827);

		} else if (stageInt == 7) {
			stageInt = 8;
			sendDialogue(

					"The Tower is I, and I have control of the tower. I see what happens,",
					"in any corner of any floor. I am always watching.");
			player.getPackets().sendVoice(7909);
		} else if (stageInt == 8) {
			stageInt = 9;
			sendDialogue("So you believe yourself a mighty warrior?");
			player.getPackets().sendVoice(7907);
		} else if (stageInt == 9) {
			stageInt = 10;
			sendOptionsDialogue(SEND_DEFAULT_OPTIONS_TITLE, "Only the greatest warrior that ever lived!",
					"I'm pretty handy with a weapon.");
		} else if (stageInt == 10) {
			stageInt = (componentId == OPTION_1 ? 100 : 101);
			sendEntityDialogue(SEND_1_TEXT_CHAT,
					new String[] { player.getDisplayName(), componentId == 2
							? "Only the greatest warrior that ever lived!" : "I'm pretty handy with a weapon." },
					IS_PLAYER, player.getIndex(), 9827);
		} else if (stageInt == 100 || stageInt == 101) {
			sendDialogue("Intriguing. "
					+ (stageInt == 100 ? "Such belief in your own abilities..." : "I sence humility in you."));
			if (stageInt == 101)
				player.getPackets().sendVoice(7887);
			else
				player.getPackets().sendVoice(7906);
			stageInt = 12;
		} else if (stageInt == 12) {
			stageInt = 13;
			sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { player.getDisplayName(), "What?" }, IS_PLAYER,
					player.getIndex(), 9827);
		} else if (stageInt == 13) {
			stageInt = 14;
			sendDialogue(

					"Your confidence may have a foundation, but judgment will come in", "battle.");
			player.getPackets().sendVoice(7896);
		} else if (stageInt == 14) {
			stageInt = 15;
			sendEntityDialogue(
					SEND_2_TEXT_CHAT, new String[] { player.getDisplayName(),
							"You mentioned that you were created by someone, but", "why?" },
					IS_PLAYER, player.getIndex(), 9827);
		} else if (stageInt == 15) {
			stageInt = 16;
			sendDialogue("My purpose...must never stop...");
			player.getPackets().sendVoice(7902);
		} else if (stageInt == 16) {
			stageInt = 17;
			sendEntityDialogue(SEND_1_TEXT_CHAT, new String[] { player.getDisplayName(), "Sorry? Are you alright?" },
					IS_PLAYER, player.getIndex(), 9827);
		} else if (stageInt == 17) {
			stageInt = 18;
			sendDialogue(

					"You must fight in the tower, warrior. Demonstrate your ability to", "others and learn.");
			player.getPackets().sendVoice(7879);
		} else if (stageInt == 18) {
			stageInt = 19;
			sendEntityDialogue(
					SEND_2_TEXT_CHAT, new String[] { player.getDisplayName(),
							"I'd thought that, as a guide, you'd be a little more", "welcoming." },
					IS_PLAYER, player.getIndex(), 9827);
		} else if (stageInt == 19) {
			stageInt = 20;
			sendDialogue("You will find I am welcoming enough.");
			player.getPackets().sendVoice(7911);
		} else if (stageInt == 20) {
			stageInt = 21;
			sendDialogue(

					"Now, I can offer you more guidance; or, if you overflow with",
					"confidence, you can figure out yourself. I am the tower, I am",
					"ever-present, so come to me if you change your mind.");
			player.getPackets().sendVoice(7872);
		} else if (stageInt == 21) {
			stageInt = 22;
			sendOptionsDialogue("Receive further instruction?", "Yes.", "No.");
			player.getDominionTower().setTalkedWithFace(true);
		} else if (stageInt == 22) {
			stageInt = 23;
			if (componentId == OPTION_2) {
				sendDialogue("Your choice. Come back if you change your mind.");
				player.getPackets().sendVoice(7878);
			} else {
				player.getDominionTower().talkToFace(true);
				end();
			}
		} else {
			end();
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
