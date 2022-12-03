package com.rs.game.npc.combat.impl;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.CombatScript;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.Combat;
import com.rs.utils.Utils;

public class FrostDragonCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Frost dragon" };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		final Player player = target instanceof Player ? (Player) target : null;
		int damage;
		switch (Utils.getRandom(3)) {
		case 0: // Melee
			if (npc.withinDistance(target, 3)) {
				damage = getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target);
				npc.animate(new Animation(defs.getAttackEmote()));
				delayHit(npc, 0, target, getMeleeHit(npc, damage));
			} else {
				damage = Utils.getRandom(650);
				if (Combat.hasAntiDragProtection(target) || (player != null
						&& (player.getPrayer().usingPrayer(0, 17) || player.getPrayer().usingPrayer(1, 7)))) {
					damage = 0;
					player.getPackets()
							.sendGameMessage("Your " + (Combat.hasAntiDragProtection(target) ? "shield" : "prayer")
									+ " absorbs most of the dragon's breath!", true);
				} else if ((!Combat.hasAntiDragProtection(target) || !player.getPrayer().usingPrayer(0, 17)
						|| !player.getPrayer().usingPrayer(1, 7))
						&& player.getFireImmune() > Utils.currentTimeMillis()) {
					damage = Utils.getRandom(164);
					player.getPackets().sendGameMessage("Your potion absorbs most of the dragon's breath!", true);
				}
				npc.animate(new Animation(13155));
				World.sendProjectile(npc, target, 393, 28, 16, 35, 35, 16, 0);
				if (player.getEquipment().getShieldId() == 11283) {
					if (player.getDfsCharges() < 50) {
						player.animate(new Animation(6695));
						player.gfx(new Graphics(1164, 1, 100));
						player.setDfsCharges(player.getDfsCharges() + 1);
						player.checkDFSCharges();
						player.getPackets().sendGameMessage("Your dragonfire shield absorbs the dragon breath");
					}
				}
				if (player.getEquipment().getShieldId() == 11284) {
					if (player.getDfsCharges() < 50) {
						player.getEquipment().getItems().set(5, new Item(11283));
						player.getAppearence().generateAppearenceData();
						player.animate(new Animation(6695));
						player.gfx(new Graphics(1164, 1, 100));
						player.setDfsCharges(player.getDfsCharges() + 1);
						player.checkDFSCharges();
						player.getPackets().sendGameMessage("Your dragonfire shield absorbs the dragon breath");
					}
				}
				delayHit(npc, 1, target, getRegularHit(npc, damage));
			}
			break;
		case 1: // Dragon breath
			if (npc.withinDistance(target, 3)) {
				damage = Utils.getRandom(650);
				if (Combat.hasAntiDragProtection(target) || (player != null
						&& (player.getPrayer().usingPrayer(0, 17) || player.getPrayer().usingPrayer(1, 7)))) {
					damage = 0;
					player.getPackets()
							.sendGameMessage("Your " + (Combat.hasAntiDragProtection(target) ? "shield" : "prayer")
									+ " absorbs most of the dragon's breath!", true);
				} else if ((!Combat.hasAntiDragProtection(target) || !player.getPrayer().usingPrayer(0, 17)
						|| !player.getPrayer().usingPrayer(1, 7))
						&& player.getFireImmune() > Utils.currentTimeMillis()) {
					damage = Utils.getRandom(164);
					player.getPackets().sendGameMessage(
							"Your potion fully protects you from the heat of the dragon's breath!", true);
				}
				npc.animate(new Animation(13152));
				npc.gfx(new Graphics(2465));
				if (player.getEquipment().getShieldId() == 11283) {
					if (player.getDfsCharges() < 50) {
						player.animate(new Animation(6695));
						player.gfx(new Graphics(1164, 1, 100));
						player.setDfsCharges(player.getDfsCharges() + 1);
						player.checkDFSCharges();
						player.getPackets().sendGameMessage("Your dragonfire shield absorbs the dragon breath");
					}
				}
				if (player.getEquipment().getShieldId() == 11284) {
					if (player.getDfsCharges() < 50) {
						player.getEquipment().getItems().set(5, new Item(11283));
						player.getAppearence().generateAppearenceData();
						player.animate(new Animation(6695));
						player.gfx(new Graphics(1164, 1, 100));
						player.setDfsCharges(player.getDfsCharges() + 1);
						player.checkDFSCharges();
						player.getPackets().sendGameMessage("Your dragonfire shield absorbs the dragon breath");
					}
				}
				delayHit(npc, 1, target, getRegularHit(npc, damage));
			} else {
				damage = Utils.getRandom(650);
				if (Combat.hasAntiDragProtection(target) || (player != null
						&& (player.getPrayer().usingPrayer(0, 17) || player.getPrayer().usingPrayer(1, 7)))) {
					damage = 0;
					player.getPackets()
							.sendGameMessage("Your " + (Combat.hasAntiDragProtection(target) ? "shield" : "prayer")
									+ " absorbs most of the dragon's breath!", true);
				} else if ((!Combat.hasAntiDragProtection(target) || !player.getPrayer().usingPrayer(0, 17)
						|| !player.getPrayer().usingPrayer(1, 7))
						&& player.getFireImmune() > Utils.currentTimeMillis()) {
					damage = Utils.getRandom(164);
					player.getPackets().sendGameMessage(
							"Your potion fully protects you from the heat of the dragon's breath!", true);
				}
				npc.animate(new Animation(13155));
				if (player.getEquipment().getShieldId() == 11283) {
					if (player.getDfsCharges() < 50) {
						player.animate(new Animation(6695));
						player.gfx(new Graphics(1164, 1, 100));
						player.setDfsCharges(player.getDfsCharges() + 1);
						player.checkDFSCharges();
						player.getPackets().sendGameMessage("Your dragonfire shield absorbs the dragon breath");
					}
				}
				if (player.getEquipment().getShieldId() == 11284) {
					if (player.getDfsCharges() < 50) {
						player.getEquipment().getItems().set(5, new Item(11283));
						player.getAppearence().generateAppearenceData();
						player.animate(new Animation(6695));
						player.gfx(new Graphics(1164, 1, 100));
						player.setDfsCharges(player.getDfsCharges() + 1);
						player.checkDFSCharges();
						player.getPackets().sendGameMessage("Your dragonfire shield absorbs the dragon breath");
					}
				}
				World.sendProjectile(npc, target, 393, 28, 16, 35, 35, 16, 0);
				delayHit(npc, 1, target, getRegularHit(npc, damage));
			}
			break;
		case 2: // Range
			damage = Utils.getRandom(250);
			npc.animate(new Animation(13155));
			World.sendProjectile(npc, target, 2707, 28, 16, 35, 35, 16, 0);
			delayHit(npc, 1, target, getMagicHit(npc, damage));
			break;
		case 3: // Ice arrows range
			damage = Utils.getRandom(250);
			npc.animate(new Animation(13155));
			World.sendProjectile(npc, target, 16, 28, 16, 35, 35, 16, 0);
			delayHit(npc, 1, target, getRangeHit(npc, damage));
			break;
		}
		return defs.getAttackDelay();
	}

}
