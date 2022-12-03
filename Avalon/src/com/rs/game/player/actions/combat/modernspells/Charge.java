package com.rs.game.player.actions.combat.modernspells;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public class Charge {

	public static boolean castSpell(Player player) {
		if ((player.getEquipment().getWeaponId() == 2415 && player.getEquipment().getCapeId() == 2412)
				|| (player.getEquipment().getWeaponId() == 2416 && player.getEquipment().getCapeId() == 2413)
				|| (player.getEquipment().getWeaponId() == 2417 && player.getEquipment().getCapeId() == 2414)) {
			if (player.getChargeImmune() > Utils.currentTimeMillis()) {
				player.getPackets().sendGameMessage("You can't cast charge yet, spell is currently on a cooldown.");
				return false;
			} else {
				player.animate(new Animation(811));
				player.gfx(new Graphics(308, 0, 100));
				player.getSkills().addXp(Skills.MAGIC, 180);
				player.setChargeImmune(60000);
				player.setChargeDelay(420000);
				return true;
			}
		} else {
			player.getPackets().sendGameMessage("You do not have the required equipment to cast this spell.");
			return false;
		}
	}

}
