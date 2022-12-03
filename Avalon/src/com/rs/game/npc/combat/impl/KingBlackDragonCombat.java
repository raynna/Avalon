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

public class KingBlackDragonCombat extends CombatScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 50 };
	}

	@Override
	public int attack(final NPC npc, final Entity target) {
		final NPCCombatDefinitions defs = npc.getCombatDefinitions();
		int attackStyle = 0;
		int size = npc.getSize();
		final Player player = target instanceof Player ? (Player) target : null;
		int damage;
		int distanceX = target.getX() - npc.getX();
		int distanceY = target.getY() - npc.getY();
		if (distanceX > size || distanceX < -1 || distanceY > size || distanceY < -1)
			attackStyle = Utils.getRandom(3);
		else
			attackStyle = Utils.getRandom(5);
		if (attackStyle == 4 || attackStyle == 5) {
			delayHit(npc, 0, target,
					getMeleeHit(npc, getRandomMaxHit(npc, defs.getMaxHit(), NPCCombatDefinitions.MELEE, target)));
			npc.animate(new Animation(defs.getAttackEmote()));
			return defs.getAttackDelay();
		} else if (attackStyle == 2 || attackStyle == 3) {
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
					player.getPackets()
							.sendGameMessage("Your potion fully protects you from the heat of the dragon's breath.");
				} else if (player.getPrayer().usingPrayer(0, 17) || player.getPrayer().usingPrayer(1, 7)) {
					damage = 0;
					player.sm("Your shield and prayer protect you completly from the dragon's breath!");
					// player.sm("Your shield aborsbs most of the dragon
					// fire!");
					// player.sm("Your prayer protects you from the heat of the
					// dragon's breath!");
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
			delayHit(npc, 2, target, getRegularHit(npc, damage));
			// World.sendProjectile(npc, target, 393, 34, 16, 30, 35, 16, 0);
			World.sendDragonfireProjectile(npc, player, 393);
			npc.animate(new Animation(81));

		} else if (attackStyle == 0) {
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
					player.getPackets()
							.sendGameMessage("Your potion fully protects you from the heat of the dragon's breath.");
				} else if (player.getPrayer().usingPrayer(0, 17) || player.getPrayer().usingPrayer(1, 7)) {
					damage = 0;
					player.sm("Your shield and prayer protect you completly from the dragon's breath!");
					// player.sm("Your shield aborsbs most of the dragon
					// fire!");
					// player.sm("Your prayer protects you from the heat of the
					// dragon's breath!");
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
			if (Utils.getRandom(2) == 0)
				target.getPoison().makePoisoned(80);
			delayHit(npc, 2, target, getRegularHit(npc, damage));
			// World.sendProjectile(npc, target, 394, 34, 16, 30, 35, 16, 0);
			World.sendDragonfireProjectile(npc, player, 394);
			npc.animate(new Animation(81));
		} else if (attackStyle == 1) {
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
					player.getPackets()
							.sendGameMessage("Your potion fully protects you from the heat of the dragon's breath.");
				} else if (player.getPrayer().usingPrayer(0, 17) || player.getPrayer().usingPrayer(1, 7)) {
					damage = 0;
					player.sm("Your shield and prayer protect you completly from the dragon's breath!");
					// player.sm("Your shield aborsbs most of the dragon
					// fire!");
					// player.sm("Your prayer protects you from the heat of the
					// dragon's breath!");
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
			if (Utils.getRandom(3) == 0) {
				target.setFreezeDelay(15000);
				target.setFreezeImmune(20000);
			}
			delayHit(npc, 2, target, getRegularHit(npc, damage));
			// World.sendProjectile(npc, target, 395, 34, 16, 30, 35, 16, 0);
			World.sendDragonfireProjectile(npc, player, 395);
			npc.animate(new Animation(81));
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
					player.getPackets()
							.sendGameMessage("Your potion fully protects you from the heat of the dragon's breath.");
				} else if (player.getPrayer().usingPrayer(0, 17) || player.getPrayer().usingPrayer(1, 7)) {
					damage = 0;
					player.sm("Your shield and prayer protect you completly from the dragon's breath!");
					// player.sm("Your shield aborsbs most of the dragon
					// fire!");
					// player.sm("Your prayer protects you from the heat of the
					// dragon's breath!");
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
			delayHit(npc, 2, target, getRegularHit(npc, damage));
			// World.sendProjectile(npc, target, 396, 34, 16, 30, 35, 16, 0);
			World.sendDragonfireProjectile(npc, player, 396);
			npc.animate(new Animation(81));
		}
		return defs.getAttackDelay();
	}
}
