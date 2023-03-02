package com.rs.game.player.actions.combat.lunarspells;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public class StatSpy {

	public static boolean cast(Player player, Entity target, double xp) {
		int start = 1;
		int end = 2;
		if (target instanceof Player) {
			Player other = (Player) target;
			player.faceEntity(target);
			if (!player.withinDistance(other, 9)) {
				player.message(other.getDisplayName() + " is too far away!");
				return false;
			}
			if ((Long) player.temporaryAttribute().get("LAST_SPELL") != null
					&& (Long) player.temporaryAttribute().get("LAST_SPELL") + 8250 > Utils.currentTimeMillis()) {
				return false;
			}
			player.stopAll();
			player.faceEntity(other);
			player.animate(new Animation(6293));
			player.gfx(new Graphics(1059));
			other.gfx(new Graphics(736, 0, 100));
			player.getInterfaceManager().sendTab(player.getInterfaceManager().hasRezizableScreen() ? 114 : 174, 523);
			player.getInterfaceManager().openGameTab(3);
			player.getPackets().sendIComponentText(523, 103,
					"Viewing stats for <br><img=" + other.getRights() + ">" + other.getDisplayName());
			for (int i = 0; i < 25; i++) {
				player.getPackets().sendIComponentText(523, start, "" + other.getSkills().getLevel(i));
				player.getPackets().sendIComponentText(523, end, "99");
				start += 4;
				end += 4;
			}
			player.getPackets().sendIComponentText(523, 98, "120");
			player.getPackets().sendIComponentText(523, 106, "Hitpoints: " + other.getHitpoints());
			player.getSkills().addXp(Skills.MAGIC, xp);
			player.temporaryAttribute().put("LAST_SPELL", Utils.currentTimeMillis());
			other.message("Your stats are being spied on by " + player.getDisplayName() + ".");
			return true;
		}
		return false;
	}

}
