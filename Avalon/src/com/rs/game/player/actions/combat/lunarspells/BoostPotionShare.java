package com.rs.game.player.actions.combat.lunarspells;

import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Pots;
import com.rs.utils.Utils;

public class BoostPotionShare {

	public static boolean cast(Player player, double xp, int itemId, int slotId) {
		Item item = new Item(itemId);
		if (!item.getName().toLowerCase().contains("strength") && !item.getName().toLowerCase().contains("attack")
				&& !item.getName().toLowerCase().contains("defence") && !item.getName().toLowerCase().contains("combat")
				&& !item.getName().toLowerCase().contains("ranging")
				&& !item.getName().toLowerCase().contains("magic")) {
			player.getPackets().sendGameMessage(
					"You can only use this spell on strength, attack, defence, ranging, magic and combat potions.");
			return false;
		}
		if (player.getPotDelay() > Utils.currentTimeMillis())
			return false;
		player.addXp(Skills.MAGIC, xp);
		Pots.pot(player, item, slotId);
		for (Player other : World.getPlayers()) {
			if (other == null || other == player)
				continue;
			if (other.withinDistance(player, 4) && other.isAcceptAid() && other.isAtMultiArea()) {
				Pots.sharedPot(other, item, slotId);
				other.sm(player.getDisplayName() + " shared a " + item.getName().replace("(6)", "").replace("(5)", "")
						.replace("(4)", "").replace("(3)", "").replace("(2)", "").replace("(1)", "")
						+ "dose with you.");
			}
		}
		return true;
	}
}
