package com.rs.game.npc.dungeonnering;

import com.rs.game.Entity;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.WorldTile;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.Combat;
import com.rs.game.player.actions.skills.mining.Mining;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.utils.Utils;
import com.rs.utils.WeaponTypesLoader.WeaponType;

@SuppressWarnings("serial")
public final class BulwarkBeast extends DungeonBoss {

	private int shieldHP;
	private int maxShieldHP;

	public BulwarkBeast(int id, WorldTile tile, DungeonManager manager, RoomReference reference) {
		super(id, tile, manager, reference);
		maxShieldHP = shieldHP = 500;
	}

	@Override
	public void handleHit(final Hit hit) {
		handleHit(hit);
		super.handleHit(hit);
		if (shieldHP <= 0 || hit.getLook() == HitLook.MAGIC_DAMAGE)
			return;
		hit.setDamage(0);
		Entity source = hit.getSource();
		if (source == null || !(source instanceof Player))
			return;
		if (hit.getLook() != HitLook.MELEE_DAMAGE)
			return;
		Player playerSource = (Player) source;
		int weaponId = playerSource.getEquipment().getWeaponId();
		if (weaponId != -1 && Mining.getPickAxeDefinitions(playerSource) != null/*(weaponId >= 16295 && weaponId <= 16315) || (weaponId >= 16142 && weaponId <= 16152)*/) {
			hit.setDamage(Utils.random(50));
			hit.setLook(HitLook.ABSORB_DAMAGE);
			shieldHP -= hit.getDamage();
			playerSource.getPackets().sendGameMessage(shieldHP > 0 ? "Your pickaxe chips away at the beast's armour plates." : "Your pixkaxe finnaly breaks throught the heavy armour plates.");
			refreshBar();
		}
	}

	private static final WeaponType[][] WEAKNESS =
		{
		{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.STAB_ATTACK), new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.CRUSH_ATTACK), new WeaponType(Combat.MAGIC_TYPE, -1)}, };

	public WeaponType[][] getWeaknessStyle() {
		return WEAKNESS;
	}

	public int getShieldHP() {
		return shieldHP;
	}

	public void setShieldHP(int shieldHP) {
		this.shieldHP = shieldHP;
	}

	public boolean hasShield() {
		return shieldHP > 0 && !isDead() && !hasFinished();
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
		refreshBar();
	}

	public void refreshBar() {
		if (hasShield())
			getManager().showBar(getReference(), "Bulwark Beast's Armour", shieldHP * 100 / maxShieldHP);
		else
			getManager().hideBar(getReference());
	}

}
