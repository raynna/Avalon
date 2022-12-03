package com.rs.game.player.controlers;

import com.rs.Settings;
import com.rs.game.World;
import com.rs.game.npc.NPC;
import com.rs.game.player.content.friendschat.FriendChatsManager;

public class WelcomeTutorial extends Controler {

	// - Written by Phillip

	private static final int MAKEOVER_MAGE = 2676;

	@Override
	public void start() {
		refreshStage();
	}

	public void refreshStage() {
		int stage = getStage();
		switch (stage) {
		case 0:
			player.getDialogueManager().startDialogue("NewPlayer", stage, this);
			break;
		case 1:// the player is to go talk to the makeover mage
			player.getHintIconsManager().addHintIcon(3091, 3494, 0, 120, 0, 0, -1, false);
			break;
		case 2:// return from the makeover mage dialogue to continue to the next
				// script
			break;
		case 3:
			player.getHintIconsManager().removeAll();
			player.getDialogueManager().startDialogue("NewPlayer", 1, this);
			break;
		case 4:// setup the experience with the exp manager
			player.getHintIconsManager().addHintIcon(3095, 3499, 0, 120, 0, 0, -1, false);
			break;
		case 5:
			player.getHintIconsManager().removeAll();
			player.getDialogueManager().startDialogue("NewPlayer", 2, this);
			break;
		case 6:
			sendFinalStage();
			break;

		case 10:
			sendHalfKit();
			break;
		}
		sendInterfaces();
	}

	@Override
	public boolean processNPCClick1(NPC npc) {
		if (npc.getId() == MAKEOVER_MAGE) {
			if (getStage() < 7) {
				player.getDialogueManager().startDialogue("TutorialMakeover", this);
				//System.out.println("NPC " + npc.getId() + " handled in controler.");
			}
		}
		return false;
	}

	public NPC findNPCbyLocation(int id, int x, int y) {
		for (NPC npc : World.getNPCs()) {
			if (npc == null || (npc.getX() != x && npc.getY() != y))
				continue;
			return npc;
		}
		return null;
	}

	public NPC findNPC(int id) {
		for (NPC npc : World.getNPCs()) {
			if (npc == null || npc.getId() != id)
				continue;
			return npc;
		}
		return null;
	}

	public void setStage(int stage) {
		getArguments()[0] = stage;
	}

	public int getStage() {
		if (getArguments() == null)
			setArguments(new Object[] { 0 });
		return (Integer) getArguments()[0];
	}

	public void updateProgress() {
		setStage(getStage() + 1);
		refreshStage();
		player.sm("updated");
	}

	public void quickTutorialOption() {
		setStage(10);
		refreshStage();
	}

	public void sendFinalStage() {
		FriendChatsManager.joinChat(Settings.HELP_CC_NAME, player, true);
		player.getDialogueManager().startDialogue("SimpleMessage", "You recieved a full starter kit!");
		player.sm("YOU RECIEVE A FULL KIT.");
		// starter items
	}

	public void sendHalfKit() {
		FriendChatsManager.joinChat(Settings.HELP_CC_NAME, player, true);
		player.sm("YOU RECIEVE A HALF KIT.");
	}

}
