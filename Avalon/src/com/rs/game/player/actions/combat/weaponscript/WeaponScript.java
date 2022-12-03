package com.rs.game.player.actions.combat.weaponscript;

import com.rs.game.Entity;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.CombatDummy;
import com.rs.game.player.actions.combat.PlayerCombat;

/**
 * @author -Andreas 11 feb. 2020 12:24:38
 * @project 1. Avalon
 * 
 */

public abstract class WeaponScript {

	public enum CombatType {
		
		MELEE,
		
		RANGE,
		
		MAGIC;
	}
	
	protected CombatType type;
	
	protected PlayerCombat combat;

	public abstract Object[] getKeys();

	public CombatType getType() {
		return CombatType.MELEE;// default melee
	}

	public int getAttackSpeed() {
		return 4;// default 4
	}
	
	public int[] getValidAmmo() {
		return null;
	}

	public int getBlockAnimation() {
		return 424;// default
	}

	public int getAttackSound() {
		return -1;
	}

	public int[] getDefensiveSound() {
		return null;
	}

	public void processAttack() {
	}

	public int getAttackAnimation() {
		return -1;
	}

	public int getAttackAnimation(int attackStyle) {
		switch (attackStyle) {// defaults punch/kick
		case 1:
			return 14307;
		default:
			return 14393;
		}
	}

	public int getSpecialAmount() {
		return 0;
	}

	public double getDamageMultiplier() {
		return 1.0;// default 1.0
	}

	public void sendSpecialAttack(Player player, int weaponId, int attackStyle) {
	}
	
	public void sendSpecialAttack(Player player, Player target, int weaponId, int attackStyle) {
	}

	public int[] getAmmo() {
		return null;
	}

	public boolean isValidAmmo(int ammoId) {
		return false;
	}

	public double getAccuracyMultiplier() {
		return 1.0;
	}
	
	public void sendSpecialAttack(CombatDummy combat, Player player, Entity target, int weaponId, int attackStyle) {
	}

	public int getSoundId() {
		return -1;
	}
}