package com.rs.game.player.actions.combat;

import java.util.concurrent.TimeUnit;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.World;
import com.rs.game.Hit.HitLook;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.actions.Action;
import com.rs.game.player.actions.combat.weaponscript.WeaponScript;
import com.rs.game.player.actions.combat.weaponscript.WeaponScript.CombatType;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;
import com.rs.game.player.actions.combat.weaponscript.WeaponScriptsManager;

/**
 * @author -Andreas 11 feb. 2020 12:20:55
 * @project 1. Avalon
 * 
 */

public class CombatDummy extends Action {

	WeaponScript script = null;

	private Entity target;

	private CombatDummy combat;

	public CombatDummy(Entity target) {
		this.target = target;
	}

	@Override
	public boolean start(Player player) {
		if (target.isDead())
			return false;
		player.setNextFaceEntity(target);
		int weaponId = player.getEquipment().getWeaponId();
		String name = ItemDefinitions.getItemDefinitions(weaponId).getName();
		WeaponScript script = WeaponScriptsManager.weaponScripts.getOrDefault(weaponId,
				WeaponScriptsManager.weaponScripts.get(name));
		if (script == null)
			return false;
		this.combat = this;
		this.script = script;
		player.setNextFaceEntity(target);
		return true;
	}

	@Override
	public boolean process(Player player) {
		if (target.isDead())
			return false;
		return true;
	}

	@Override
	public int processWithDelay(Player player) {
		if (target.isDead())
			return 0;
		if (script == null)
			return 0;
		if (script.getType() == null)
			return 0;
		if (script.getType() == CombatType.RANGE)
			return rangeAttack(player);
		if (script.getType() == CombatType.MELEE)
			return meleeAttack(player);
		return 0;
	}

	private boolean usedSpecialAttack(Player player, int weaponId) {
		int specialAmount = script.getSpecialAmount();
		player.getCombatDefinitions().switchUsingSpecialAttack();
		if (player.getCombatDefinitions().getSpecialAttackPercentage() < specialAmount) {
			player.getPackets().sendGameMessage("You don't have enough special points.");
			return false;
		}
		player.getCombatDefinitions().desecreaseSpecialAttack(specialAmount);
		return true;
	}

	private int meleeAttack(final Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		final int attackStyle = player.getCombatDefinitions().getAttackStyle();
		int combatDelay = script.getAttackSpeed();
		int soundId = script.getSoundId();
		if (player.getCombatDefinitions().isUsingSpecialAttack()) {
			if (!usedSpecialAttack(player, weaponId))
				return combatDelay;
			if (script.getSpecialAmount() > 0) {
				script.sendSpecialAttack(player, weaponId, attackStyle);
			}
		} else {
			meleeDelay(player);
			player.setNextAnimationNoPriority(new Animation(script.getAttackAnimation(attackStyle)), player);
			CoresManager.slowExecutor.schedule(new Runnable() {

				@Override
				public void run() {
					if (target.getNextAnimation() != null)
						return;
					performBlockAnimation(target);
				}

			}, 0, TimeUnit.MILLISECONDS);
		}
		playSound(soundId, player, target);
		return combatDelay;
	}

	private boolean checkValidAmmo(int ammoId) {
		if (script.isValidAmmo(ammoId))
			return true;
		return false;
	}

	private int rangeAttack(final Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		final int attackStyle = player.getCombatDefinitions().getAttackStyle();
		int combatDelay = script.getAttackSpeed();
		String weaponName = ItemDefinitions.getItemDefinitions(weaponId).getName();
		int soundId = script.getSoundId();
		if (!checkValidAmmo(player.getEquipment().getAmmoId())) {
			player.sm("You don't have a valid ammo for this weapon.");
			return -1;
		}
		if (player.getCombatDefinitions().isUsingSpecialAttack()) {
			if (!usedSpecialAttack(player, weaponId))
				return combatDelay;
			if (script.getSpecialAmount() > 0) {
				script.sendSpecialAttack(combat, player, target, weaponId, attackStyle);
			}
		} else {
			player.setNextAnimationNoPriority(new Animation(script.getAttackAnimation(attackStyle)), player);
			CoresManager.slowExecutor.schedule(new Runnable() {

				@Override
				public void run() {
					if (target.getNextAnimation() != null)
						return;
					performBlockAnimation(target);
				}
			}, 0, TimeUnit.MILLISECONDS);
		}
		final int ammoId = player.getEquipment().getAmmoId();
		player.gfx(new Graphics(0, 0, 100));
		int hit = 200;
		World.sendFastBowProjectile(player, target, 0);
		delayHit(Utils.getDistance(player, target) > 3 ? 2 : 1, weaponId, attackStyle, getRangeHit(player, hit));
		if (weaponId != -1) {
			if (!weaponName.toLowerCase().contains("crystal") && !weaponName.toLowerCase().contains("sling")
					&& !weaponName.toLowerCase().contains("karil")) {
				//dropAmmo(player);
			}
		}
		playSound(soundId, player, target);
		return combatDelay;
	}

	public void playSound(int soundId, Player player, Entity target) {
		if (soundId == -1)
			return;
		player.getPackets().sendSound(soundId, 0, 1);
		if (target instanceof Player) {
			Player p2 = (Player) target;
			p2.getPackets().sendSound(soundId, 0, 1);
		}
	}

	public void delayNormalHit(final int weaponId, final int attackStyle, final Hit... hits) {
		if (weaponId != 4153 && (ItemDefinitions.getItemDefinitions(weaponId).getName().contains("maul")
				|| ItemDefinitions.getItemDefinitions(weaponId).getName().contains("ket-om") || weaponId == 10887))
			delayHit(1, weaponId, attackStyle, hits);
		else
			delayHit(0, weaponId, attackStyle, hits);
	}

	public void delayHit(int delay, final int weaponId, final int attackStyle, final Hit... hits) {
		final Entity target = this.target;
		for (Hit hit : hits) {
			final Player player = (Player) hit.getSource();
			if (target instanceof Player) {
				Player p2 = (Player) target;
				if (!p2.attackedBy.containsKey(player))
					p2.attackedBy.put(player, 1440);// 15minutes add to list
				p2.getCharges().processIncommingHit();
			}
			player.getCharges().processOutgoingHit();
			int damage = hit.getDamage() > target.getHitpoints() ? target.getHitpoints() : hit.getDamage();
			player.getAuraManager().checkSuccefulHits(damage);
			player.setDamage(hit.getDamage());
		}

		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				for (Hit hit : hits) {
					//boolean splash = false;
					Player player = (Player) hit.getSource();
					if (player == null || player.isDead() || player.hasFinished() || target.isDead()
							|| target.hasFinished())
						return;
					boolean rangeAttack = hit.getLook() == HitLook.RANGE_DAMAGE;
					boolean magicAttack = hit.getLook() == HitLook.MAGIC_DAMAGE;
					if (target instanceof Player) {
						//Player p2 = (Player) target;
						if (magicAttack || rangeAttack)
							performBlockAnimation(target);
					}
					if (target instanceof NPC) {
						NPC n = (NPC) target;
						performBlockAnimation(n);
					}
					target.applyHit(hit);
				}
			}
		}, delay);
	}

	public void performBlockAnimation(Entity target) {
		// TODO
	}

	public void meleeDelay(final Player player) {
		int weaponId = player.getEquipment().getWeaponId();
		int attackStyle = player.getCombatDefinitions().getAttackStyle();
		delayNormalHit(weaponId, attackStyle, getMeleeHit(player, 500));
	}

	public static Hit getMeleeHit(Player player, int damage) {
		return new Hit(player, damage, HitLook.MELEE_DAMAGE);
	}

	public static Hit getRangeHit(Player player, int damage) {
		return new Hit(player, damage, HitLook.RANGE_DAMAGE);
	}

	@Override
	public void stop(Player player) {
		player.setNextFaceEntity(null);
	}

}
