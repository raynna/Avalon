package com.rs.game.player.controlers;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class GodCapes {

	/** Phillip **/

	public static void handleStatue(WorldObject object, Player player) {
		switch (object.getId()) {
		case 2873:// sara
			process(object, 2412, player);
			break;
		case 2874:// zammy
			process(object, 2414, player);
			break;
		case 2875:// guthix
			process(object, 2413, player);
			break;
		}
	}

	private static void process(WorldObject object, int cape, Player player) {
		String capeType = ItemDefinitions.getItemDefinitions(cape).getName().replace(" cape", "");
		WorldTile capeTile = new WorldTile(player);
		player.animate(new Animation(645));
		player.getPackets().sendGameMessage("You kneel and begin to chant to "
				+ capeType + "...");
		// walks back
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.addWalkSteps(player.getX(), player.getY() - 1);
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						/*if (hasGodCape(player)) {
							player.getPackets().sendGameMessage("...but there is no response.");
							return;
						}*/
						player.faceObject(object);
						player.getDialogueManager().startDialogue("SimpleMessage",
								"You feel a rush of energy through your veins."
										+ " Suddenly a cape appears before you.");
						player.getPackets().sendGraphics(new Graphics(74), capeTile);
						World.updateGroundItem(new Item(cape), capeTile, player);
					}
				}, 2);
			}
		}, 1);
	}

	public static boolean hasGodCape(Player player) {
		if (player.getInventory().containsOneItem(2412, 2413, 2414))
			return true;
		if (player.getBank().getItem(2412) != null || player.getBank().getItem(2413) != null
				|| player.getBank().getItem(2414) != null)
			return true;
		return false;
	}

	public static boolean handleCape(Player player, Item item) {
		int id = item.getId();
		String capeType = ItemDefinitions.getItemDefinitions(id).getName().replace(" cape", "");
		if (id >= 2412 && id <= 2414) {
			player.getPackets().sendGameMessage("%s reclaims the cape as it touches the ground.",
					capeType);
			player.getInventory().deleteItem(item);
			return true;
		}
		return false;
	}
}
