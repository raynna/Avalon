package com.rs.game.player.dialogues.skilling;

import java.util.LinkedList;
import java.util.List;

import com.rs.game.item.Item;
import com.rs.game.player.dialogues.Dialogue;

public class LarderDialouge extends Dialogue {

	private static final int[] LARDER_ITEMS =
	{ 7738, 1927, 1944, 1933, 1942, 1550, 1957, 1985 };
	private static final String[] LARDER_NAMES =
	{ "Tea leaves", "Bucket of Milk", "Eggs", "Pot of Flour", "Potato", "Garlic", "Onion", "Cheese" };

	private final List<String> currentOptions = new LinkedList<String>();
	private int max_index;

	@Override
	public void start() {
		int objectId = (int) this.parameters[0];
		max_index = objectId == 13566 ? 5 : objectId == 13567 ? 7 : 2;
		for (int i = 0; i < max_index; i++)
			currentOptions.add(LARDER_NAMES[i]);
		if (currentOptions.size() >= 4) {
			currentOptions.add(4, max_index == 7 ? "Next Page" : "Nothing.");
			currentOptions.add("Previous Options");
		}
		sendOptions(false);
	}

	private void sendOptions(boolean secondOptions) {
		String[] strings = new String[max_index > 5 ? secondOptions ? 4 : 5 : max_index];
		System.arraycopy(currentOptions.toArray(new String[1]), secondOptions ? 5 : 0, strings, 0, strings.length);
		sendOptionsDialogue("Please select an item", strings);
	}

	private void addItem(int index, boolean nextPage) {
		player.getInventory().addItem(new Item(LARDER_ITEMS[nextPage ? index + 4 : index], 1));
		end();
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == 0 || stage == -1) {
			if (componentId != OPTION_5 && stage == -1 || (componentId != OPTION_4 && stage == 0)) {
				addItem(componentId == 11 ? 0 : componentId - 12, stage == 0);
			} else {
				if (componentId == OPTION_5) {
					if (currentOptions.get(4).equals("Nothing."))
						end();
					else
						sendOptions(true);
					stage = 0;
				} else if (componentId == OPTION_4) {
					if (stage == 0) {
						sendOptions(false);
						stage = -1;
					} else if (stage == 1) {
						addItem(3, false);
					}
				}
			}
		}
	}

	@Override
	public void finish() {

	}
}
