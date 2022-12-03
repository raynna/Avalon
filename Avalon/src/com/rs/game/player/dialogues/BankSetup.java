package com.rs.game.player.dialogues;

import com.rs.game.item.Item;
import com.rs.utils.Utils;

public class BankSetup extends Dialogue {

	@Override
	public void start() {
		sendDialogue(
				"This will delete all current setups and replace your bank with the original setup, this action CANNOT be undone.");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			sendOptionsDialogue("This CANNOT be undone!", "REPLACE my bank <col=ff0000>CANNOT BE UNDONE", "Nevermind");
			stage = 2;
		} else if (stage == 2) {
			if (componentId == OPTION_1) {
				for (int id = 0; id < Utils.getItemDefinitionsSize(); id++) {
					Item item = player.getBank().getItem(id);
					if (item == null)
						continue;
					player.getBank().removeItem(player.getBank().getItemSlot(item.getId()), item.getAmount(), true,
							false);
					player.getBank().refreshItems();
				}
				Item[] StarterItems = { new Item(4151, 2147000000), new Item(20072, 2147000000),
						new Item(2412, 2147000000), new Item(2413, 2147000000), new Item(2414, 2147000000),
						new Item(7462, 2147000000), new Item(8850, 2147000000), new Item(5698, 2147000000),
						new Item(4675, 2147000000), new Item(1434, 2147000000), new Item(1305, 2147000000),
						new Item(3105, 2147000000), new Item(10551, 2147000000), new Item(10548, 2147000000),
						new Item(10549, 2147000000), new Item(15486, 2147000000), new Item(6889, 2147000000),
						new Item(6914, 2147000000), new Item(6916, 2147000000), new Item(6924, 2147000000),
						new Item(6918, 2147000000), new Item(18346, 2147000000), new Item(1187, 2147000000),
						new Item(4587, 2147000000), new Item(4153, 2147000000), new Item(560, 2147000000),
						new Item(555, 2147000000), new Item(565, 2147000000), new Item(566, 2147000000),
						new Item(9075, 2147000000), new Item(557, 2147000000), new Item(1027, 2147000000),
						new Item(1079, 2147000000), new Item(1093, 2147000000), new Item(2497, 2147000000),
						new Item(2503, 2147000000), new Item(24379, 2147000000), new Item(24382, 2147000000),
						new Item(4101, 2147000000), new Item(4103, 2147000000), new Item(4091, 2147000000),
						new Item(4093, 2147000000), new Item(14501, 2147000000), new Item(14497, 2147000000),
						new Item(14499, 2147000000), new Item(7398, 2147000000), new Item(7399, 2147000000),
						new Item(6920, 2147000000), new Item(10828, 2147000000), new Item(15272, 2147000000),
						new Item(23279, 2147000000), new Item(23255, 2147000000), new Item(23351, 2147000000),
						new Item(23567, 2147000000), new Item(3144, 2147000000), new Item(10370, 2147000000),
						new Item(10372, 2147000000), new Item(10378, 2147000000), new Item(10380, 2147000000),
						new Item(6585, 2147000000), new Item(861, 2147000000), new Item(892, 2147000000),
						new Item(19162, 2147000000), new Item(19157, 2147000000), new Item(19152, 2147000000),
						new Item(9185, 2147000000), new Item(9244, 2147000000), new Item(3842, 2147000000),
						new Item(4749, 2147000000), new Item(4751, 2147000000), new Item(4716, 2147000000),
						new Item(4718, 2147000000), new Item(4720, 2147000000), new Item(4722, 2147000000),
						new Item(4724, 2147000000), new Item(4736, 2147000000), new Item(4745, 2147000000),
						new Item(4759, 2147000000), new Item(4757, 2147000000), new Item(10499, 2147000000),
						new Item(2550, 2147000000), new Item(2187, 2147000000), new Item(11090, 2147000000),
						new Item(6107, 2147000000), new Item(6108, 2147000000), new Item(662, 2147000000),
						new Item(6328, 2147000000), new Item(15300, 2147000000), new Item(21787, 2147000000),
						new Item(21790, 2147000000), new Item(21793, 2147000000), new Item(13734, 2147000000),
						new Item(13736, 2147000000), new Item(4712, 2147000000), new Item(4714, 2147000000),
						new Item(4714, 2147000000), new Item(4714, 2147000000), new Item(1712, 2147000000) };
				for (Item items : StarterItems) {
					player.getBank().addItem(items, true);
				}
				player.getDialogueManager().startDialogue("SimpleMessage", "Your bank has been setup!");

			} else if (componentId == OPTION_2) {
				player.getDialogueManager().startDialogue("SimpleMessage", "Action Aborted");
			}
		}
	}

	@Override
	public void finish() {

	}
}