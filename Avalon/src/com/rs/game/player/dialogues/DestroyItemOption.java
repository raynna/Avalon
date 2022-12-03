package com.rs.game.player.dialogues;

import java.util.Map.Entry;

import com.rs.game.World;
import com.rs.game.item.Item;

public class DestroyItemOption extends Dialogue {

	int slotId;
	Item item;

	@Override
	public void start() {
		slotId = (Integer) parameters[0];
		item = (Item) parameters[1];
		player.getInterfaceManager().sendChatBoxInterface(1183);
		player.getPackets().sendIComponentText(1183, 22,
				"Are you sure you want to destroy your " + item.getName() + "?");
		player.getPackets().sendIComponentText(1183, 7, item.getName());
		player.getPackets().sendItemOnIComponent(1183, 13, item.getId(), 1);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (interfaceId == 1183 && componentId == 9) {
			player.getInventory().deleteItem(slotId, item);
			if (item.getId() == 24203 && player.getRunicStaff().hasRunes()) {
				for (Entry<Integer, Item[]> charges : player.getStaffCharges().entrySet()) {
					if (charges.getValue() == null)
						continue;
					for (Item staffRunes : charges.getValue()) {
						if (item == null)
							continue;
						World.updateGroundItem(staffRunes, player, player, 60, 1);
					}
				}
				player.sm("All your runes in your runic staff were dropped.");
				player.getStaffCharges().clear();
			} else if (item.getId() == 24202) {
				player.getStaffCharges().clear();
			} else if (item.getId() == 24497 && player.getRunePouch().getFreeSlots() < 3) {
				for (Item runes : player.getRunePouch().getContainerItems()) {
					if (runes == null)
						continue;
					World.updateGroundItem(runes, player, player, 60, 1);
				}
				player.getRunePouch().reset();
				player.getPackets().sendGameMessage("All your runes in your rune pouch were dropped on the floor.");
			}
			player.getCharges().degrade(item);
			player.getPackets().sendSound(4500, 0, 1);
		}
		end();
	}

	@Override
	public void finish() {

	}

}
