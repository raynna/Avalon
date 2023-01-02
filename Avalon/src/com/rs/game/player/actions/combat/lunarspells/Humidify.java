package com.rs.game.player.actions.combat.lunarspells;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public class Humidify {

	private static final int[] empty_Items = { 229, 1831, 1829, 1827, 1825, 1925, 1923, 1935, 5331, 5332, 5333, 5334,
			5335, 5337, 5338, 5339, 6667, 7688, 731, 1980, 434 };

	private static final int[] filled_Items = { 227, 1823, 1823, 1823, 1823, 1929, 1921, 1937, 5340, 5340, 5340, 5340,
			5340, 5340, 5340, 5340, 6668, 7690, 732, 4458, 1761 };

	public static boolean cast(Player player, double xp) {
		boolean hasItems = false;
		if ((Long) player.getTemporaryAttributtes().get("LAST_SPELL") != null
				&& (long) player.getTemporaryAttributtes().get("LAST_SPELL") + 4800 > Utils.currentTimeMillis()) {
			return false;
		}
		for (int empty : empty_Items) {
			if (player.getInventory().containsOneItem(empty))
				hasItems = true;
		}
		if (hasItems) {
			for (int i = 0; i < 28; i++) {
				final int amount = player.getInventory().getAmountOf(empty_Items[i]);
				if (player.getInventory().containsOneItem(empty_Items[i])) {
					player.getSkills().addXp(Skills.MAGIC, xp);
					player.animate(new Animation(6294));
					player.gfx(new Graphics(1061));
					player.getTemporaryAttributtes().put("LAST_SPELL", Utils.currentTimeMillis());
					player.getInventory().deleteItem(empty_Items[i], amount);
					player.getInventory().addItem(filled_Items[i], amount);
				}
			}
			return true;
		} else {
			player.sm("You do not have any empty vessels to fill.");
			return false;
		}

	}

}
