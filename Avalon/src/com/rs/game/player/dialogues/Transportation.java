package com.rs.game.player.dialogues;

import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.actions.combat.Magic;
import com.rs.game.player.controlers.WildernessControler;
import com.rs.utils.Utils;

public class Transportation extends Dialogue {

	// Ring of duelling
	// Combat bracelet
	// Amulet of glory

	public static int EMOTE = 9603, GFX = 1684;

	@Override
	public void start() {
		sendOptionsDialogue("Where would you like to teleport to", (String) parameters[0], (String) parameters[2],
				(String) parameters[4], (String) parameters[6], "Nowhere");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		boolean teleported = false;
		Item item = player.getInventory().getItems().lookup((Integer) parameters[8]);
		if (componentId == OPTION_1) {
			teleported = Magic.sendJewerlyTeleportSpell(player, true, EMOTE, GFX, 4, (WorldTile) parameters[1]);
		} else if (componentId == OPTION_2) {
			if (player.getTeleBlockDelay() < Utils.currentTimeMillis()
					&& !(player.getControlerManager().getControler() instanceof WildernessControler)) {
				player.getControlerManager().startControler("WildernessControler");
			}
			teleported = Magic.sendJewerlyTeleportSpell(player, true, EMOTE, GFX, 4, (WorldTile) parameters[3]);
		} else if (componentId == OPTION_3) {
			teleported = Magic.sendJewerlyTeleportSpell(player, true, EMOTE, GFX, 4, (WorldTile) parameters[5]);
		} else if (componentId == OPTION_4) {
			teleported = Magic.sendJewerlyTeleportSpell(player, true, EMOTE, GFX, 4, (WorldTile) parameters[7]);
		}
		if (!teleported) {
			end();
			return;
		}
		int slot = player.getInventory().getItems().getThisItemSlot(item);
		if (item.getId() == 3867 || item.getId() == 2566) {// last charge
			player.getPackets().sendGameMessage(
					"Your " + item.getName().toLowerCase().replace(" (1)", "") + " has crumbled to dust.");
			player.getInventory().deleteItem(item.getId(), 1);
		} else if (item.getId() >= 10354 && item.getId() <= 10360// glory t
				|| item.getId() >= 2552 && item.getId() <= 2565// glory
				|| item.getId() >= 3853 && item.getId() <= 3865) { // games neck
			player.getInventory().getItems().set(slot, new Item(item.getId() + 2));
			player.getInventory().refresh(slot);
		} else {
			player.getInventory().getItems().set(slot, new Item(item.getId() - 2));
			player.getInventory().refresh(slot);
		}
		player.stopAll();
		player.teleporting(player.getControlerManager().getControler() instanceof WildernessControler ? 10 : 8);
		end();
	}

	@Override
	public void finish() {
	}

}
