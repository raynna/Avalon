package com.rs.game.player.dialogues.item;

import com.rs.game.player.dialogues.Dialogue;

public class GreaterRunicStaffD extends Dialogue {


	@Override
	public void start() {
		stage = 0;
		sendOptionsDialogue("How many casts do you with to charge?", "Store 10", "Store 100", "Store X");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case 0:
			stage = -1;
			if (componentId == OPTION_1) {
				end();
				player.getRunicStaff().chargeStaff(10, player.getRunicStaff().getSpellId());
			} else if (componentId == OPTION_2) {
				end();
				player.getRunicStaff().chargeStaff(100, player.getRunicStaff().getSpellId());
			} else if (componentId == OPTION_3) {
				end();
				player.temporaryAttribute().put("charge_staff", Boolean.TRUE);
				player.getPackets().sendRunScript(108, new Object[] { "Enter how many you want to charge" });
			}
			break;
		case -1:
			end();
			break;
		default:
			end();
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
