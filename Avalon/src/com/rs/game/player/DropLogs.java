package com.rs.game.player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.rs.game.item.Item;
import com.rs.utils.Utils;

public class DropLogs implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Item> drops;
	private int lowestValue;
	private boolean lowestValueMessage;
	private boolean toggledMessage;

	private transient Player player;

	public void displayInterface() {
		int itemsSize = 13;
		player.getInterfaceManager().sendInterface(275);
		for (int i = 0; i < 100; i++)
			player.getPackets().sendIComponentText(275, i, "");
		player.getPackets().sendIComponentText(275, 1, "Droplog");
		player.getPackets().sendIComponentText(275, 10, "Lowest value: " + Utils.getFormattedNumber(lowestValue, ','));
		for (Item items : drops) {
			if (items == null)
				continue;
			player.getPackets().sendIComponentText(275, itemsSize,
					items.getDefinitions().getName() + " : " + items.getAmount());
			itemsSize++;
		}
	}

	public boolean toggledMessage() {
		return toggledMessage;
	}

	public void toggleMessage() {
		this.toggledMessage = toggledMessage() ? false : true;
		player.getPackets()
				.sendGameMessage("Drop log messages are now toggled " + (toggledMessage() ? "off" : "on") + ".");
	}

	public void setLowestValueMessage(boolean value) {
		this.lowestValueMessage = value;
	}

	public boolean getLowestValueMessage() {
		return lowestValueMessage;
	}

	public void setLowestValue(int value) {
		this.lowestValue = value;
	}

	public int getLowestValue() {
		return lowestValue;
	}

	public void addDrop(Item item) {
		for (Item items : drops) {
			if (items.getId() == item.getId()) {
				items.setAmount(items.getAmount() + item.getAmount());
				return;
			}
		}
		drops.add(item);
	}

	public void clearDrops() {
		drops.clear();
		player.getPackets().sendGameMessage("All drops from droplog has been cleared.");
	}

	public void removeDrop(Item item) {
		drops.remove(item);
	}

	public DropLogs(Player player) {
		if (drops == null) {
			drops = new ArrayList<Item>();
			setLowestValue(1000000);
			setLowestValueMessage(false);
		}
		this.player = player;
	}

}