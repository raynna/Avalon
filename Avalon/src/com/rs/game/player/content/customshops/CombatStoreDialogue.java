package com.rs.game.player.content.customshops;

import java.util.HashMap;
import java.util.Map;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.actions.Action;

public class CombatStoreDialogue extends Action {
	
	/**
	 * @Author -Andreas
	 * 2019-11
	 */

	public enum CombatStores {

		SUPPLY_STORE(new Item(6685), 0),

		MELEE_STORE(new Item(4587), 1),

		RANGE_STORE(new Item(861), 2),

		MAGIC_STORE(new Item(4675), 3),

		ACCESSORIES(new Item(1712), 4),

		BARROWS_STORE(new Item(4720), 5);

		private static Map<Integer, CombatStores> shops = new HashMap<Integer, CombatStores>();

		public static CombatStores forId(int buttonId) {
			return shops.get(buttonId);
		}

		static {
			for (CombatStores prod : CombatStores.values()) {
				shops.put(prod.getButtonId(), prod);
			}
		}

		private int buttonId;
		private Item itemShow;

		private CombatStores(Item itemShow, int buttonId) {
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

	public CombatStores prod;
	public int ticks;

	public CombatStoreDialogue(int slotId, int ticks) {
		this.prod = CombatStores.forId(slotId);
		this.ticks = ticks;
	}

	@Override
	public boolean start(Player player) {
		if (prod == null || player == null)
			return false;
		player.getCustomStore().sendInterface(player, 0, prod.getButtonId() + 1);
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
