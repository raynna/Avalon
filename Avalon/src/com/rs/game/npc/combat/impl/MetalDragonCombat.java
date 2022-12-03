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
import com.rs.utils.Utils;

public class MetalDragonCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { "Bronze dragon", "Iron dragon", "Steel dragon" };
	}

	@Override
	public int attack(NPC npc, Entity target) {
		NPCCombatDefinitions defs = npc.getCombatDefinitions();
		final Player player = target instanceof Player ? (Player) target : null;
		int damage;

		switch (Utils.getRandom(2)) {
		case 0:
		case 1:
			if (npc.withinDistance(target, 2)) {
				damage = getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target);
				npc.animate(new Animation(defs.getAttackEmote()));
				delayHit(npc, 0, target, getMeleeHit(npc, damage));
			} else {
				damage = Utils.getRandom(650);
				if (player.getEquipment().getShieldId() == 11283 || player.getEquipment().getShieldId() == 11284
						|| player.getEquipment().getShieldId() == 1540) {
					if (player.getAntifire() > Utils.currentTimeMillis()) {
						damage = 0;
						player.getPackets().sendGameMessage("Your shield aborsbs most of the dragon fire!");
						player.getPackets()
								.sendGameMessage("Your potion protects you from the heat of the dragon's breath!");
					} else if (player.getSuperAntifire() > Utils.currentTimeMillis()) {
						damage = 0;
						player.getPackets().sendGameMessage(
								"Your potion fully protects you from the heat of the dragon's breath.");
					} else if (player.getPrayer().usingPrayer(0, 17) || player.getPrayer().usingPrayer(1, 7)) {
						damage = 0;
						player.sm("Your shield and prayer protect you completly from the dragon's breath!");
						// player.sm("Your shield aborsbs most of the dragon
						// fire!");
						// player.sm("Your prayer protects you from the heat of
						// the dragon's breath!");
					} else {
						damage *= 0.1;
						player.getPackets().sendGameMessage("Your shield aborsbs most of the dragon fire!");
					}
				} else if (player.getAntifire() > Utils.currentTimeMillis()) {
					damage *= 0.1;
					player.getPackets()
							.sendGameMessage("Your potion protects you from some of the heat of the dragon's breath!");
				} else if (player.getSuperAntifire() > Utils.currentTimeMillis()) {
					damage = 0;
					player.getPackets()
							.sendGameMessage("Your potion fully protects you from the heat of the dragon's breath.");
				} else if (player.getPrayer().usingPrayer(0, 17) || player.getPrayer().usingPrayer(1, 7)) {
					damage *= 0.1;
					player.getPackets().sendGameMessage("Your protection prayer absorbs some of the dragon fire!");
				} else if (player.getEquipment().getShieldId() != 11283 && player.getEquipment().getShieldId() != 11284
						&& player.getEquipment().getShieldId() != 1540
						&& player.getSuperAntifire() < Utils.currentTimeMillis()
						&& player.getAntifire() < Utils.currentTimeMillis() && !player.getPrayer().usingPrayer(0, 17)
						&& !player.getPrayer().usingPrayer(1, 7))
					player.getPackets().sendGameMessage("You are hit by the dragon's fiery breath!", true);
				npc.animate(new Animation(13160));
				World.sendDragonfireProjectile(npc, target, 393);
				if (player.getEquipment().getShieldId() == 11283) {
					if (player.getDfsCharges() < 50) {
						player.animate(new Animation(6695));
						player.gfx(new Graphics(1164, 1, 100));
						player.setDfsCharges(player.getDfsCharges() + 1);
						player.addDFSDefence();
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
						player.addDFSDefence();
						player.getPackets().sendGameMessage("Your dragonfire shield absorbs the dragon breath");
					}
				}
				delayHit(npc, Utils.getDistance(player, npc) > 2 ? 2 : 1, target, getRegularHit(npc, damage));
			}
			break;
		case 2:
			if (npc.withinDistance(target, 2)) {
				damage = Utils.getRandom(650);
				if (player.getEquipment().getShieldId() == 11283 || player.getEquipment().getShieldId() == 1540) {
					if (player.getAntifire() > Utils.currentTimeMillis()) {
						damage = 0;
						player.getPackets().sendGameMessage("Your shield aborsbs most of the dragon fire!");
						player.getPackets()
								.sendGameMessage("Your potion protects you from the heat of the dragon's breath!");
					} else if (player.getSuperAntifire() > Utils.currentTimeMillis()) {
						damage = 0;
						player.getPackets().sendGameMessage(
								"Your potion fully protects you from the heat of the dragon's breath.");
					} else if (player.getPrayer().usingPrayer(0, 17) || player.getPrayer().usingPrayer(1, 7)) {
						damage = 0;
						player.getPackets().sendGameMessage("Your shield aborsbs most of the dragon fire!");
						player.getPackets()
								.sendGameMessage("Your prayer protects you from the heat of the dragon's breath!");
					} else {
						damage *= 0.1;
						player.getPackets().sendGameMessage("Your shield aborsbs most of the dragon fire!");
					}
				} else if (player.getAntifire() > Utils.currentTimeMillis()) {
					damage *= 0.1;
					player.getPackets()
							.sendGameMessage("Your potion protects you from some of the heat of the dragon's breath!");
				} else if (player.getSuperAntifire() > Utils.currentTimeMillis()) {
					damage = 0;
					player.getPackets()
							.sendGameMessage("Your potion fully protects you from the heat of the dragon's breath.");
				} else if (player.getPrayer().usingPrayer(0, 17) || player.getPrayer().usingPrayer(1, 7)) {
					damage *= 0.1;
					player.getPackets().sendGameMessage("Your protection prayer absorbs some of the dragon fire!");
				} else if (player.getEquipment().getShieldId() != 11283 && player.getEquipment().getShieldId() != 1540
						&& player.getSuperAntifire() < Utils.currentTimeMillis()
						&& player.getAntifire() < Utils.currentTimeMillis() && !player.getPrayer().usingPrayer(0, 17)
						&& !player.getPrayer().usingPrayer(1, 7))
					player.getPackets().sendGameMessage("You are hit by the dragon's fiery breath!", true);
				npc.animate(new Animation(13164));
				npc.gfx(new Graphics(2465));
				if (player.getEquipment().getShieldId() == 11283) {
					if (player.getDfsCharges() < 50) {
						player.animate(new Animation(6695));
						player.gfx(new Graphics(1164, 1, 100));
						player.setDfsCharges(player.getDfsCharges() + 1);
						player.addDFSDefence();
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
						player.addDFSDefence();
						player.getPackets().sendGameMessage("Your dragonfire shield absorbs the dragon breath");
					}
				}
				delayHit(npc, Utils.getDistance(player, npc) > 2 ? 2 : 1, target, getRegularHit(npc, damage));
			} else {
				damage = Utils.getRandom(650);
				if (player.getEquipment().getShieldId() == 11283 || player.getEquipment().getShieldId() == 1540) {
					if (player.getAntifire() > Utils.currentTimeMillis()) {
						damage = 0;
						player.getPackets().sendGameMessage("Your shield aborsbs most of the dragon fire!");
						player.getPackets()
								.sendGameMessage("Your potion protects you from the heat of the dragon's breath!");
					} else if (player.getSuperAntifire() > Utils.currentTimeMillis()) {
						damage = 0;
						player.getPackets().sendGameMessage(
								"Your potion fully protects you from the heat of the dragon's breath.");
					} else if (player.getPrayer().usingPrayer(0, 17) || player.getPrayer().usingPrayer(1, 7)) {
						damage = 0;
						player.sm("Your shield and prayer protect you completly from the dragon's breath!");
						// player.getPackets().sendGameMessage("Your shield
						// aborsbs most of the dragon fire!");
						// player.getPackets().sendGameMessage("Your prayer
						// protects you from the heat of the dragon's breath!");
					} else {
						damage *= 0.1;
						player.getPackets().sendGameMessage("Your shield aborsbs most of the dragon fire!");
					}
				} else if (player.getAntifire() > Utils.currentTimeMillis()) {
					damage *= 0.1;
					player.getPackets()
							.sendGameMessage("Your potion protects you from some of the heat of the dragon's breath!");
				} else if (player.getSuperAntifire() > Utils.currentTimeMillis()) {
					damage = 0;
					player.getPackets()
							.sendGameMessage("Your potion fully protects you from the heat of the dragon's breath.");
				} else if (player.getPrayer().usingPrayer(0, 17) || player.getPrayer().usingPrayer(1, 7)) {
					damage *= 0.1;
					player.getPackets().sendGameMessage("Your protection prayer absorbs some of the dragon fire!");
				} else if (player.getEquipment().getShieldId() != 11283 && player.getEquipment().getShieldId() != 1540
						&& player.getSuperAntifire() < Utils.currentTimeMillis()
						&& player.getAntifire() < Utils.currentTimeMillis() && !player.getPrayer().usingPrayer(0, 17)
						&& !player.getPrayer().usingPrayer(1, 7))
					player.getPackets().sendGameMessage("You are hit by the dragon's fiery breath!", true);
				npc.animate(new Animation(13160));
				World.sendDragonfireProjectile(npc, target, 393);
				if (player.getEquipment().getShieldId() == 11283) {
					if (player.getDfsCharges() < 50) {
						player.animate(new Animation(6695));
						player.gfx(new Graphics(1164, 1, 100));
						player.setDfsCharges(player.getDfsCharges() + 1);
						player.addDFSDefence();
						player.getPackets().sendGameMessage("Your dragonfire shield absorbs the dragon breath");
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
						player.getPackets().sendGameMessage("Your dragonfire shield absorbs the dragon breath");
					}
				}
				delayHit(npc, Utils.getDistance(player, npc) > 2 ? 2 : 1, target, getRegularHit(npc, damage));
			}
			break;
		}

		return defs.getAttackDelay();
	}

}
