package com.rs.game.npc.glacior;

import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.SecondaryBar;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.utils.Utils;

@SuppressWarnings("serial")
public class Glacyte extends NPC {

	private Glacor glacor;
	private byte effect, explosionTicks;
	private boolean isGlacior;
	private int targetIndex;

	public Glacyte(Glacor glacor, int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, true);
		setCapDamage(900);
		this.glacor = glacor;
		isGlacior = id == 14301;
		if (!isGlacior) {
			effect = (byte) (id - 14302);
			if (getCombatDefinitions().getAgressivenessType() == NPCCombatDefinitions.AGRESSIVE)
				getCombat().setTarget(glacor.getCombat().getTarget());
		}
		explosionTicks = 25;
		targetIndex = -1;
	}

	@Override
	public void sendDeath(Entity killer) {
		super.sendDeath(killer);
		if (!isGlacior)
			glacor.verifyGlaciteEffect(this);
	}

	@Override
	public void handleHit(final Hit hit) {
		if (targetIndex == -1)
			targetIndex = hit.getSource().getIndex();
		int damage = hit.getDamage();
		if (effect == 2) {
			Entity target = glacor != null ? glacor.getCombat().getTarget() : getCombat().getTarget();
			if (damage > 0)
				damage = (int) ((((6 - Utils.getDistance(target, glacor)) / 10) + .4D) * damage);
			hit.setDamage(damage);
		}
		super.handleHit(hit);
	}

	/**
	 * Effects go as this, 0 - explosion, 1 - sap prayer , 2 - endurance
	 */
	@Override
	public void processNPC() {
		super.processNPC();
		Entity target = glacor != null ? glacor.getCombat().getTarget() : getCombat().getTarget();
		if (glacor == null || target == null || target.isDead() || glacor.isDead() || isDead()) {
			explosionTicks = 0;
			targetIndex = -1;
			if (!isGlacior)
				finish();
			else {
				if (!glacor.isDead() || !isDead())
					reset();
				glacor.resetMinions();
			}
			return;
		}
		if (effect == 0) {
			explosionTicks--;
			if (explosionTicks <= 0) {
				explosionTicks = 25;
				final WorldTile tile = new WorldTile(this);
				for (Entity e : getPossibleTargets()) {
					if (e == null || e.isDead() || !e.withinDistance(tile, isGlacior ? 3 : 1))
						continue;
					e.applyHit(new Hit(target, e.getHitpoints() / 3, HitLook.REGULAR_DAMAGE));
				}
				applyHit(new Hit(target, (int) (getHitpoints() * .9), HitLook.REFLECTED_DAMAGE));
				gfx(new Graphics(956));
			} else {
				if (explosionTicks >= 20) {
					// just so it can delay healing a little
				} else if (explosionTicks >= 13) {
					heal((int) (getMaxHitpoints() * .05));
				} else if (explosionTicks == 12)
					setNextSecondaryBar(new SecondaryBar(0, 350, 1, false));
			}
		}
	}

	public byte getEffect() {
		return effect;
	}

	public void setEffect(byte effect) {
		this.effect = effect;
	}

	public void setGlacor(Glacor glacor) {
		this.glacor = glacor;
	}

	public Glacyte getGlacor() {
		return glacor;
	}

	public int getTargetIndex() {
		return targetIndex;
	}
}
