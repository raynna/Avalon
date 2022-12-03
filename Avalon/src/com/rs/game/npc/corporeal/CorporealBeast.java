package com.rs.game.npc.corporeal;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Entity;
import com.rs.game.Hit;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;

@SuppressWarnings("serial")
public class CorporealBeast extends NPC {

	private DarkEnergyCore core;

	public CorporealBeast(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea,
			boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		setCapDamage(1000);
		setLureDelay(3000);
		setForceTargetDistance(64);
		setForceAgressiveDistance(64);
		setIntelligentRouteFinder(true);
	}

	public void spawnDarkEnergyCore() {
		if (core != null)
			return;
		core = new DarkEnergyCore(this);
	}

	public void removeDarkEnergyCore() {
		if (core == null)
			return;
		core.finish();
		core = null;
	}

	@Override
	public void handleHit(Hit hit) {
		if (hit.getSource() instanceof Player) {
		Player target = (Player) hit.getSource();
		if (!ItemDefinitions.getItemDefinitions(target.getEquipment().getWeaponId()).getName().toLowerCase()
				.contains(" spear")) {
			hit.setDamage(hit.getDamage() / 2);
			target.getPackets().sendGameMessage("You cannot deal full damage without a spear weapon.");
		}
		}
		super.handleHit(hit);
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (isDead())
			return;
		int maxhp = getMaxHitpoints();
		if (maxhp > getHitpoints() && getPossibleTargets().isEmpty())
			setHitpoints(maxhp);
	}

	@Override
	public void sendDeath(Entity source) {
		super.sendDeath(source);
		if (core != null)
			core.sendDeath(source);
	}

	@Override
	public double getMagePrayerMultiplier() {
		return 0.6;
	}

}
