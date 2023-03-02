package com.rs.game.player.dialogues;

import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;

public class BarrowDrop extends Dialogue {

	int slotId;
	Item item;

	@Override
	public void start() {
		slotId = (Integer) parameters[0];
		item = (Item) parameters[1];
		player.getInterfaceManager().sendChatBoxInterface(1183);
		player.getPackets().sendIComponentText(1183, 12, "This item will break as it touches the ground.");
		player.getPackets().sendIComponentText(1183, 22, "Are you sure you want to drop this object?");
		player.getPackets().sendIComponentText(1183, 7, item.getName());
		player.getPackets().sendItemOnIComponent(1183, 13, item.getId(), 1);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (interfaceId == 1183 && componentId == 9) {
			if (!player.getInventory().containsItem(item)) {
				player.message(item.getName() + " doesn't exist in your inventory anymore.");
				return;
			}
			player.message("Your " + item.getName() + " breaks as it hits the ground.");
			player.getInventory().deleteItem(slotId, item);
			if (player.getCharges().degrade(item))
				return;
			World.addGroundItem(item, new WorldTile(player), player, true, 60);
			player.getPackets().sendSound(4500, 0, 1);
		}
		end();
	}

	@Override
	public void finish() {

	}

}
