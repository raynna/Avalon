package com.rs.game.player.content.quest.impl.druidicritual.impl;

import com.rs.game.Animation;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class CauldronOfThunder {

	/**
	 * Represents the animation to use.
	 */
	private static final Animation ANIMATION = new Animation(833);


	public static void dipItem(final Player player, Item item) {

		switch (item.getId()) {
			//raw bear meat
			case 2136:

				player.animate(ANIMATION);

				player.getInventory().deleteItem(2136, 1);

				WorldTasksManager.schedule(new WorldTask() {

					@Override
					public void run() {

						player.getPackets().sendGameMessage("You dip Raw Bear Meat into the cauldron.");

						player.getInventory().addItem(new Item(524, 1));

						stop();
					}


				});

				break;
			//raw beef
			case 2132:

				player.animate(ANIMATION);

				player.getInventory().deleteItem(2132, 1);

				WorldTasksManager.schedule(new WorldTask() {

					@Override
					public void run() {

						player.sm("You dip Raw Beef into the cauldron.");

						player.getInventory().addItem(new Item(522, 1));

						stop();
					}


				});

				break;
			//raw rat meat
			case 2134:
				player.animate(ANIMATION);

				player.getInventory().deleteItem(2134, 1);

				WorldTasksManager.schedule(new WorldTask() {

					@Override
					public void run() {

						player.sm("You dip Raw Rat Meat into the cauldron.");

						player.getInventory().addItem(new Item(523, 1));

						stop();
					}


				});

				break;

			//raw chicken
			case 2138:
				player.animate(ANIMATION);

				player.getInventory().deleteItem(2138, 1);

				WorldTasksManager.schedule(new WorldTask() {

					@Override
					public void run() {

						player.sm("You dip Raw Chicken into the cauldron.");

						player.getInventory().addItem(new Item(525, 1));

						stop();
					}


				});
				break;

			default:

				player.sm("You can not dip this item.");

				break;

		}
		return;
	}

}
