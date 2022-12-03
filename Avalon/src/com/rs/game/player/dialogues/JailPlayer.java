package com.rs.game.player.dialogues;

public class JailPlayer extends Dialogue {

	@Override
	public void start() {
		sendOptionsDialogue("Jail for how long?", "Minutes", "Hours", "Days", "Permanent");
	}

	/**
	 * Notes: Could be going about this wrong, send dialogue first, then enter
	 * name, time, reason
	 */

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				player.temporaryAttribute().put("jailplayerm", Boolean.TRUE);
				player.getPackets().sendInputNameScript("Enter username:");
			} else if (componentId == OPTION_2) {
				player.temporaryAttribute().put("jailplayerh", Boolean.TRUE);
				player.getPackets().sendInputNameScript("Enter username:");
			} else if (componentId == OPTION_3) {
				player.temporaryAttribute().put("jailplayerd", Boolean.TRUE);
				player.getPackets().sendInputNameScript("Enter username:");
			} else if (componentId == OPTION_4) {
				player.temporaryAttribute().put("jailplayerperm", Boolean.TRUE);
				player.getPackets().sendInputNameScript("Enter username:");
			}
		}
	}

	@Override
	public void finish() {

	}
}
