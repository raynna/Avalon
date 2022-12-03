package com.rs.game.player.actions.combat.lunarspells;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public class MonsterExamine {

	public static boolean cast(Player player, Entity target, double xp) {
		if (target instanceof NPC) {
			NPC npc = (NPC) target;
			player.faceEntity(target);
			if (!player.withinDistance(npc, 9)) {
				player.sm(npc.getName() + " is too far away!");
				return false;
			}
			if ((Long) player.temporaryAttribute().get("LAST_SPELL") != null
					&& (Long) player.temporaryAttribute().get("LAST_SPELL") + 8250 > Utils.currentTimeMillis()) {
				return false;
			}
			player.stopAll();
			player.faceEntity(npc);
			player.animate(new Animation(6293));
			player.gfx(new Graphics(1059));
			npc.gfx(new Graphics(736, 0, 100));
			player.getInterfaceManager().sendTab(player.getInterfaceManager().hasRezizableScreen() ? 114 : 174, 522);
			player.getPackets().sendIComponentText(522, 0, npc.getName());
			player.getPackets().sendIComponentText(522, 1, "Combat level: " + npc.getCombatLevel());
			player.getPackets().sendIComponentText(522, 2, "Life Points: " + npc.getHitpoints());
			player.getPackets().sendIComponentText(522, 3, "Max hit: " + npc.getMaxHit());
			player.getPackets().sendIComponentText(522, 4, "Agreesive: " + (npc.checkAgressivity() ? "Yes." : "No."));
			player.getInterfaceManager().openGameTab(3);
			player.addXp(Skills.MAGIC, 61);
			player.temporaryAttribute().put("LAST_SPELL", Utils.currentTimeMillis());
			return true;
		}
		return false;
	}

}
