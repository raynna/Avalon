package com.rs.game.player.actions.combat.lunarspells;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldObject;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

/**
 * @author -Andreas 20 feb. 2020 10:20:47
 * @project source
 * 
 */

public class FertileSoil {

	public static boolean cast(Player player, double xp, WorldObject object) {
		player.getSkills().addXp(Skills.MAGIC, xp);
		player.animate(new Animation(4413));
		player.getPackets().sendGraphics(new Graphics(724), object);
		player.getVarsManager().sendVarBit(object.getConfigByFile(), 1);
		return true;
	}

}
