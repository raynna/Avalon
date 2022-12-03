package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public class LeatherDragonCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Green dragon", "Blue dragon", "Red dragon", "Black dragon", "Brutal green dragon", 742,
				14548 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int distanceX = target.getX() - npc.getX();
		int distanceY = target.getY() - npc.getY();
		int size = npc.getSize();
		if (distanceX > size || distanceX < -1 || distanceY > size || distanceY < -1)
			return 0;
		if (Utils.getRandom(3) != 0) {
			npc.animate(new Animation(defs.getAttackEmote()));
			delayHit(npc, 0, target,
					getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
		} else {
			int damage = Utils.getRandom(650);
			npc.animate(new Animation(12259));
			npc.gfx(new Graphics(1, 0, 100));
			final Player player = target instanceof Player ? (Player) target : null;
			if (player.getEquipment().getShieldId() == 11283 || player.getEquipment().getShieldId() == 11284
					|| player.getEquipment().getShieldId() == 1540) {
				damage *= 0.1; 
				player.getPackets().sendGameMessage("Your shield aborsbs most of the dragon fire!");
			} else if (player.getAntifire() > Utils.currentTimeMillis()) {
				damage *= 0.1;
				player.getPackets()
						.sendGameMessage("Your potion protects you from some of the heat of the dragon's breath!");
			} else if (player.getSuperAntifire() > Utils.currentTimeMillis()) {
				damage *= 0.0;
				player.getPackets()
						.sendGameMessage("Your potion fully protects you from the heat of the dragon's breath.");
			} else if (player.getPrayer().usingPrayer(0, 17) || player.getPrayer().usingPrayer(1, 7)) {
				damage *= 0.1;
				player.getPackets()
						.sendGameMessage("Your prayer protects you from some of the heat of the dragon's breath!");
			} else if (player.getEquipment().getShieldId() != 11283 && player.getEquipment().getShieldId() != 11284
					&& player.getEquipment().getShieldId() != 1540
					&& player.getSuperAntifire() < Utils.currentTimeMillis()
					&& player.getAntifire() < Utils.currentTimeMillis() && !player.getPrayer().usingPrayer(0, 17)
					&& !player.getPrayer().usingPrayer(1, 7))
				player.getPackets().sendGameMessage("You are hit by the dragon's fiery breath!", true);
			delayHit(npc, 1, target, getRegularHit(npc, damage));
			if (player.getEquipment().getShieldId() == 11283) {
				if (player.getDfsCharges() < 50) {
					player.animate(new Animation(6695));
					player.gfx(new Graphics(1164, 1, 100));
					player.setDfsCharges(player.getDfsCharges() + 1);
					player.addDFSDefence();
					player.getPackets().sendGameMessage("Your dragonfire shield absorbs the dragon breath.");
				}
			}
			if (player.getEquipment().getShieldId() == 11284) {
				player.getEquipment().getItems().set(5, new Item(11283));
				player.getAppearence().generateAppearenceData();
				if (player.getDfsCharges() < 50) {
					player.animate(new Animation(6695));
					player.gfx(new Graphics(1164, 1, 100));
					player.setDfsCharges(player.getDfsCharges() + 1);
					player.addDFSDefence();
					player.getPackets().sendGameMessage("Your dragonfire shield absorbs the dragon breath.");
				}
			}
		}
		return defs.getAttackDelay();
	}
}
