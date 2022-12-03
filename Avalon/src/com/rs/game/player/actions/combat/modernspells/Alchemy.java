package com.rs.game.player.actions.combat.modernspells;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.ItemConstants;

public class Alchemy {
	
	public static boolean castSpell(Player player, int itemId, int slotId, boolean fireStaff, boolean lowAlch) {
		if (player.isLocked() || !player.getInventory().containsOneItem(itemId) || player.hasSpellDelay()) {
			return false;
		}
		Item alchedItem = new Item(itemId);
		if (!ItemConstants.isTradeable(new Item(itemId))) {
			player.getPackets().sendGameMessage("You cannot cast an alchemy spell on untradeables.");
			return false;
		}
		if (itemId == 995) {
			player.getPackets().sendGameMessage("You cannot cast an alchemy spell on coins.");
			return false;
		}
		player.castSpellDelay(3);
		player.setNextAnimationNoPriority(new Animation(fireStaff ? 9633 : 713), player);
		player.gfx(new Graphics(fireStaff ? 1693 : 113));
		player.getInventory().deleteItem(slotId, new Item(itemId));
		if (alchedItem.getDefinitions().isNoted())
			alchedItem = new Item(alchedItem.getDefinitions().getCertId());
		player.getMoneyPouch().addMoney(lowAlch ? alchedItem.getDefinitions().getLowAlchPrice() : alchedItem.getDefinitions().getHighAlchPrice(), false);
		player.getInventory().refresh();
		player.getPackets().sendGlobalConfig(168, 7);
		player.getSkills().addXp(Skills.MAGIC, lowAlch ? 31 : 65);
		player.lock(1);
		return true;
	}

}
