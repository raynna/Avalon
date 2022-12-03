package com.rs.game.player.dialogues;

public class ChangePin extends Dialogue {

	@Override
	public void start() {
		sendDialogue("Are you sure you would like to change your staff PIN?");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			stage = 1;
			sendOptionsDialogue("Staff PIN", "Change PIN", "Cancel");
		} else if (stage == 1) {
			if (componentId == OPTION_1) {
				stage = 2;
				player.temporaryAttribute().put("staff_pin", Boolean.TRUE);
				player.getPackets().sendRunScript(108, new Object[] { "Enter NEW Staff PIN" });
				player.lock();
				end();
			} else if (componentId == OPTION_2) {
				end();
			} else if (stage == 2) {
				end();
			}
		}
	}

	@Override
	public void finish() {

	}

}
