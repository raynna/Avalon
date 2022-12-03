package com.rs.game.npc.dungeonnering;

import com.rs.game.Entity;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.actions.combat.Combat;
import com.rs.game.player.actions.combat.PlayerCombat;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.utils.WeaponTypesLoader.WeaponType;

@SuppressWarnings("serial")
public final class AsteaFrostweb extends DungeonBoss {

	private int meleeNPCId;
	private int switchPrayersDelay;
	private int spawnedSpiders;
	private NPC[] spiders;

	public AsteaFrostweb(int id, WorldTile tile, DungeonManager manager, RoomReference reference) {
		super(id, tile, manager, reference);
		spiders = new NPC[6];
		meleeNPCId = id;
		resetSwitchPrayersDelay();
	}

	public void resetSwitchPrayersDelay() {
		switchPrayersDelay = 35; // 25sec
	}

	public void switchPrayers() {
		setNextNPCTransformation(getId() == meleeNPCId + 2 ? meleeNPCId : getId() + 1);
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (isDead())
			return;
		if (switchPrayersDelay > 0)
			switchPrayersDelay--;
		else {
			switchPrayers();
			resetSwitchPrayersDelay();
		}
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
		for (NPC minion : spiders) {
			if (minion == null)
				continue;
			minion.sendDeath(this);
		}
	}

	// max 6 spiders per fight
	public void spawnSpider() {
		if (spawnedSpiders >= spiders.length)
			return;
		// spawnedSpiders
		for (int tryI = 0; tryI < 10; tryI++) {
			WorldTile tile = new WorldTile(this, 2);
			if (World.isTileFree(0, tile.getX(), tile.getY(), 1)) {
				NPC spider = spiders[spawnedSpiders++] = new NPC(64, tile, -1, true, true);
				spider.setForceMultiArea(true);
				spider.setForceAgressive(true);
				break;
			}
		}
	}
	
	private static final WeaponType[][] WEAKNESS =
		{
		{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.SLASH_ATTACK), new WeaponType(Combat.RANGE_TYPE, -1), new WeaponType(Combat.MAGIC_TYPE, PlayerCombat.FIRE_SPELL) },};

	public WeaponType[][] getWeaknessStyle() {
		return WEAKNESS;
	}

	@Override
	public void handleHit(final Hit hit) {
		super.handleHit(hit);
		if (getId() == meleeNPCId) {
			if (hit.getLook() == HitLook.MELEE_DAMAGE)
				hit.setDamage(0);
		} else if (getId() == meleeNPCId + 1) {
			if (hit.getLook() == HitLook.MAGIC_DAMAGE)
				hit.setDamage(0);
		} else if (hit.getLook() == HitLook.RANGE_DAMAGE)
			hit.setDamage(0);
	}

}
