package com.rs.game.player.content;

import java.util.HashMap;
import java.util.Map;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @author Andreas - AvalonPK
 * 
 */

public class ComboFoods {

	public static enum Food {

		/**
		 * Fish Combo Foods
		 */
		KARAMBWANJI(3151, 3),

		KARAMBWANI(3144, 18),

		/**
		 * Gnome Combo Foods
		 */
		TOAD_CRUNCHIES(2217, 8),

		SPICY_CRUNCHIES(2213, 7),

		WORM_CRUNCHIES(2205, 8),

		CHOCOCHIP_CRUNCHIES(9544, 7),

		FRUIT_BATTA(2277, 11),

		TOAD_BATTA(2255, 11),

		WORM_BATTA(2253, 11),

		VEGETABLE_BATTA(2281, 11),

		CHEESE_AND_TOMATO_BATTA(9535, 11),

		WORM_HOLE(2191, 12),

		VEG_BALL(2195, 12),

		PRE_MADE_VEG_BALL(2235, 12),

		TANGLED_TOAD_LEGS(2187, 15),

		CHOCOLATE_BOMB(2185, 15);

		private int id;
		private int heal;
		private int newId;
		private int extraHP;
		private static Map<Integer, Food> foods = new HashMap<Integer, Food>();

		public static Food forId(int itemId) {
			return foods.get(itemId);
		}

		static {
			for (final Food food : Food.values()) {
				foods.put(food.id, food);
			}
		}

		private Food(int id, int heal) {
			this.id = id;
			this.heal = heal;
		}

		public int getId() {
			return id;
		}

		public int getHeal() {
			return heal;
		}

		public int getNewId() {
			return newId;
		}

		public int getExtraHP() {
			return extraHP;
		}
	}

	private static final Animation EAT_ANIM = new Animation(829);

	public static boolean eat(final Player player, final Item item, final int slot) {
		Food food = Food.forId(item.getId());
		if (food == null)
			return false;
		if (player.getComboFoodDelay() > Utils.currentTimeMillis())
			return true;
		String name = ItemDefinitions.getItemDefinitions(food.getId()).getName().toLowerCase();
		player.getPackets().sendGameMessage("You eat the " + name + ".");
		player.animate(EAT_ANIM);
		long combofoodDelay = 1200;
		player.addComboFoodDelay(combofoodDelay);
		player.getActionManager().setActionDelay(player.getActionManager().getActionDelay() + 3);
		player.getInventory().getItems().set(slot, food.getNewId() == 0 ? null : new Item(food.getNewId(), 1));
		player.getInventory().refresh(slot);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				eat2(player, item, slot);
			}
		}, 0);
		return true;
	}

	public static boolean eat2(final Player player, Item item, int slot) {
		Food food = Food.forId(item.getId());
		if (player.getHitpoints() == 0)
			return false;
		player.heal(food.getHeal() * 10, food.getExtraHP() * 10);
		player.getPackets().sendGameMessage("It heals some health.");
		player.getInventory().refresh();
		return true;
	}

}