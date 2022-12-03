package com.rs.game.player.dialogues;

import com.rs.Settings;
import com.rs.game.item.Item;
import com.rs.game.player.Equipment;

/**
 *
 * @Author Tristam <Hassan>
 * @Project - Rain
 * @Date - 29 Feb 2016
 *
 **/
public class Potato_CMTool extends Dialogue {

	@Override
	public void start() {
		if (!player.isDeveloper()) {
			sendDialogue("Please contact Tristam, knowing him how you achieved this item.");
			stage = END;
			return;
		}
		sendOptions("Potato_CMTool", "Server message",
				"DXP is " + (Settings.BONUS_EXP_WEEK_MULTIPLIER > 1.0 ? "<col=ff000>ON" : "<col=ff0000>OFF"),
				"Double drop is " + (Settings.DOUBLE_DROP ? "<col=ff000>ON" : "<col=ff0000>OFF"),
				"Completionist Cape please!", "Update server");
		stage = 1;

	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case END:
			end();
			break;
		case 1:
			switch (componentId) {
			case OPTION_1:
				player.getPackets()
						.sendInputLongTextScript("Enter the message you wish to send to all online players:");
				player.temporaryAttribute().put("servermsg", Boolean.TRUE);
				end();
				break;
			case OPTION_2:
				player.getPackets().sendInputIntegerScript(true, Settings.BONUS_EXP_WEEK_MULTIPLIER > 1.0
						? "To turn DXP off, type in 1" : "Enter DXP amount (Max 5):");
				player.temporaryAttribute().put("doubleexp", Boolean.TRUE);
				end();
				break;
			case OPTION_3:
				player.getPackets().sendInputLongTextScript(Settings.DOUBLE_DROP
						? "To turn off Double Drop, type in: Disable" : "To enable, type in: Enable");
				player.temporaryAttribute().put("doubledrop", Boolean.TRUE);
				end();
				break;
			case OPTION_4:
				end();
				player.getEquipment().getItems().set(Equipment.SLOT_CAPE, new Item(20769));
				player.getEquipment().getItems().set(Equipment.SLOT_HAT, new Item(20770));
				player.getEquipment().refresh(Equipment.SLOT_CAPE);
				player.getEquipment().refresh(Equipment.SLOT_HAT);
				player.getAppearence().generateAppearenceData();
				break;
			case OPTION_5:
				player.getPackets().sendInputIntegerScript(true, "Enter the time in minutes:");
				player.temporaryAttribute().put("serverupdate", Boolean.TRUE);
				end();
				break;
			}
			break;
		}

	}

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

}
