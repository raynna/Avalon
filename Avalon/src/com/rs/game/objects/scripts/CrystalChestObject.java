package com.rs.game.objects.scripts;

import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.item.Item;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;
import com.rs.game.player.content.CrystalChest;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class CrystalChestObject extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 172 };
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		if (!player.getInventory().containsItem(989, 1)) {
			player.getPackets().sendGameMessage("You need a crystal key to open this chest.");
			return false;
		}
		openChest(player, object);
		return true;
	}

	@Override
	public boolean processItemOnObject(Player player, WorldObject object, Item item) {
		if (object.getId() != 172)
			return false;
		openChest(player, object);
		return true;
	}

	public void openChest(Player player, WorldObject object) {
		player.animate(new Animation(536));
		player.lock(2);
		player.getPackets().sendGameMessage("You attempt to unlock the chest...");
		WorldObject openedChest = new WorldObject(173, object.getType(), object.getRotation(), object);
		if (World.removeObjectTemporary(object, 1200))
			World.spawnObject(openedChest);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.getInventory().deleteItem(989, 1);
				CrystalChest.sendRewards(false, player);
			}
		}, 1);
	}
}
