package com.rs.game.player.cutscenes.actions;

import com.rs.game.player.Player;

public class DialogueAction extends CutsceneAction {

	private int id;
	private String message;

	public DialogueAction(String message) {
		this(-1, message);
	}

	public DialogueAction(int id, String message) {
		super(-1, -1);
		this.id = id;
		this.message = message;
	}

	@Override
	public void process(Player player, Object[] cache) {
		if (id == -1)
			player.getDialogueManager().startDialogue("SimpleMessage", message);
		else
			player.getDialogueManager().startDialogue("SimpleItemMessage", id,
					message);
	}
}