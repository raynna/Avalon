package com.rs.game.player.dialogues;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - 1. Rain
 * @Date - 9 Apr 2016
 *
 **/
public class SimpleItemDialogue extends Dialogue {

	private int item, amount;

	@Override
	public void start() {
		item = (Integer) parameters[0];
		amount = (Integer) parameters[1];
		String[] message = new String[parameters.length - 2];
		for (int i = 0; i < message.length; i++)
			message[i] = (String) parameters[i + 2];
		sendItemDialogue(item, amount, message);

	}

	@Override
	public void run(int interfaceId, int componentId) {
		end();
	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
