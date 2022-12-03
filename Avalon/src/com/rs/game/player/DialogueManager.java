package com.rs.game.player;

import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.DialogueHandler;

public class DialogueManager {

	private transient Player player;
	private Dialogue lastDialogue;

	public DialogueManager(Player player) {
		this.player = player;
	}

	public void startDialogue(Object key, Object... parameters) {
		if (!player.getControlerManager().useDialogueScript(key))
			return;
		if (lastDialogue != null)
			lastDialogue.finish();
		lastDialogue = DialogueHandler.getDialogue(key);
		if (lastDialogue == null)
			return;
		lastDialogue.parameters = parameters;
		lastDialogue.setPlayer(player);
		lastDialogue.start();
	}

	public void continueDialogue(int interfaceId, int componentId) {
		if (interfaceId == 13 && componentId == 6 || interfaceId == 13 && componentId == 7
				|| interfaceId == 13 && componentId == 8 || interfaceId == 13 && componentId == 9
				|| interfaceId == 13 && componentId == 10 || interfaceId == 13 && componentId == 11
				|| interfaceId == 13 && componentId == 12 || interfaceId == 13 && componentId == 13
				|| interfaceId == 13 && componentId == 14 || interfaceId == 13 && componentId == 15) {
			// player.getBankPin().handleButtons(interfaceId, componentId);
			return;
		}
		if (interfaceId == 14 && componentId == 18 || interfaceId == 14 && componentId == 33
				|| interfaceId == 14 && componentId == 35 || interfaceId == 14 && componentId == 19) {
			// player.getBankPin().handleButtons(interfaceId, componentId);
			return;
		}
		if (lastDialogue == null)
			return;
		lastDialogue.run(interfaceId, componentId);
	}

	public void finishDialogue() {
		if (player.getInterfaceManager().containsChatBoxInter())
			player.getInterfaceManager().closeChatBoxInterface();

		if (lastDialogue == null)
			return;
		lastDialogue.finish();
		lastDialogue = null;
	}
	
	public Dialogue getLast() {
		return lastDialogue;
	}

}
