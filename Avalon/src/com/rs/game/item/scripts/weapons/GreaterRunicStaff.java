package com.rs.game.item.scripts.weapons;

import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.item.ItemId;
import com.rs.game.item.ItemScripts;
import com.rs.game.player.Player;
import com.rs.game.player.content.GreaterRunicStaff.*;

import java.util.Map;

public class GreaterRunicStaff extends ItemScripts {

	@Override
	public Object[] getKeys() {
		return new Object[] { ItemId.GREATER_RUNIC_STAFF_INACTIVE, ItemId.GREATER_RUNIC_STAFF_ACTIVE, ItemId.GREATER_RUNIC_STAFF_CHARGED };
	}

	@Override
	public boolean processItem(Player player, Item item, int slotId) {
		return false;//just to make charged staff wield
	}

	@Override
	public boolean processItem2(Player player, Item item, int slotId) {
		if (item.getId() == ItemId.GREATER_RUNIC_STAFF_INACTIVE)
			return false;
		RunicStaffSpellStore s = RunicStaffSpellStore.getSpell(player.getRunicStaff().getSpellId());
		player.sm("You currently have " + player.getRunicStaff().getCharges() + " "
						+ s.name().toLowerCase().replace('_', ' ') + " charges left.");
		return true;
	}

	@Override
	public boolean processItem3(Player player, Item item, int slotId) {
		if (item.getId() == ItemId.GREATER_RUNIC_STAFF_INACTIVE) {
			player.getRunicStaff().openChooseSpell(player);
			player.getRunicStaff().wearing = false;
			return true;
		}
		if (item.getId() == ItemId.GREATER_RUNIC_STAFF_ACTIVE || item.getId() == ItemId.GREATER_RUNIC_STAFF_CHARGED) {
			player.getDialogueManager().startDialogue("GreaterRunicStaffD");
			return true;
		}
		return false;
	}

	@Override
	public boolean processItem6(Player player, Item item, int slotId) {
		if (item.getId() == ItemId.GREATER_RUNIC_STAFF_CHARGED) {
			player.getRunicStaff().clearCharges(false, false);
		}
		if (item.getId() == ItemId.GREATER_RUNIC_STAFF_ACTIVE) {
			player.getRunicStaff().clearSpell(false);
		}
		return true;
	}

	@Override
	public boolean processDestroy(Player player, Item item, int slotId) {
		if (item.getId() == ItemId.GREATER_RUNIC_STAFF_CHARGED) {
			for (Map.Entry<Integer, Item[]> charges : player.getStaffCharges().entrySet()) {
				if (charges.getValue() == null)
					continue;
				for (Item staffRunes : charges.getValue()) {
					if (item == null)
						continue;
					World.updateGroundItem(staffRunes, player, player, player.isAtWild() ? 0 : 60, 1);
				}
			}
			player.sm("All your runes in your runic staff were dropped.");
		}
		player.getRunicStaff().clearSpell(false);
		player.getStaffCharges().clear();
		player.getInventory().dropItem(slotId, item, false);
		return true;
	}
}
