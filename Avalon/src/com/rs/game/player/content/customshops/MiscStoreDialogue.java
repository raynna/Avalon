package com.rs.game.player.content.customshops;

import java.util.HashMap;
import java.util.Map;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.actions.Action;

public class MiscStoreDialogue extends Action {
	
	/**
	 * @Author -Andreas
	 * 2019-11
	 */

	public enum MiscStores {

		SKILLING(new Item(2347), 0),
		
		SKILLING2(new Item(221), 1);

		private static Map<Integer, MiscStores> shops = new HashMap<Integer, MiscStores>();

		public static MiscStores forId(int buttonId) {
			return shops.get(buttonId);
		}

		static {
			for (MiscStores prod : MiscStores.values()) {
				shops.put(prod.getButtonId(), prod);
			}
		}

		private int buttonId;
		private Item itemShow;

		private MiscStores(Item itemShow, int buttonId) {
			this.itemShow = itemShow;
			this.buttonId = buttonId;
		}

		public Item getShowItem() {
			return itemShow;
		}

		public int getButtonId() {
			return buttonId;
		}
	}

	public MiscStores prod;
	public int ticks;

	public MiscStoreDialogue(int slotId, int ticks) {
		this.prod = MiscStores.forId(slotId);
		this.ticks = ticks;
	}

	@Override
	public boolean start(Player player) {
		if (prod == null || player == null)
			return false;
		int startShop = CustomStoreData.SKILLING_SHOP;
		player.getCustomStore().sendInterface(player, 0, startShop + (prod.getButtonId()));
		stop(player);
		return true;
	}

	@Override
	public boolean process(Player player) {
		if (prod == null || player == null)
			return false;

		return true;
	}

	@Override
	public int processWithDelay(Player player) {
		return -1;
	}

	@Override
	public void stop(Player player) {
		this.setActionDelay(player, 3);
	}
}
