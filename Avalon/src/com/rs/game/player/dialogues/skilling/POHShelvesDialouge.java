package com.rs.game.player.dialogues.skilling;

import java.util.LinkedList;
import java.util.List;

import com.rs.game.item.Item;
import com.rs.game.player.dialogues.Dialogue;

public class POHShelvesDialouge extends Dialogue {

	private static final int[] LARDER_ITEMS =
	{ 7688, 7702, 1980, 1919, 1923, 2313, 1931, 1949 };
	private static final String[] LARDER_NAMES =
	{ "Kettle", "Teapot", "Tea Cup", "Beer Glass", "Bowl", "Pie dish", "Empty pot", "Chef's Hat" };

	private final List<String> currentOptions = new LinkedList<String>();
	private int max_index;

	@Override
	public void start() {
		int objectId = (int) this.parameters[0];
		max_index = objectId - 13543;
		for (int i = 0; i < max_index; i++)
			currentOptions.add(LARDER_NAMES[i]);
		if (currentOptions.size() >= 4) {
			currentOptions.add(4, max_index == 8 ? "Next Page" : "Nothing.");
			currentOptions.add("Previous Options");
		}
		sendOptions(false);
	}

	private void sendOptions(boolean secondOptions) {
		String[] strings = new String[max_index > 5 ? 5 : max_index];
		System.arraycopy(currentOptions.toArray(new String[1]), secondOptions ? 5 : 0, strings, 0, strings.length);
		sendOptionsDialogue("Please select an item", strings);
	}

	private void addItem(int index, boolean nextPage) {
		player.getInventory().addItem(new Item(LARDER_ITEMS[nextPage ? index + 4 : index], 1));
		end();
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stageInt == 0 || stageInt == -1) {
			if (componentId != OPTION_5) {
				addItem(componentId == 11 ? 0 : componentId - 12, stageInt == 0);
			} else {
				if (componentId == OPTION_5) {
					if (currentOptions.get(4).equals("Nothing."))
						end();
					else
						sendOptions(stageInt == -1);
					stageInt = (int) (stageInt == 0 ? -1 : 0);
				}
			}
		}
	}

	@Override
	public void finish() {

	}
}
