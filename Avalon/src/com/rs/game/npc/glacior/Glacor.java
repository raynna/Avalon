package com.rs.game.npc.glacior;

import java.util.ArrayList;
import java.util.List;

import com.rs.game.Entity;
import com.rs.game.Hit;
import com.rs.game.World;
import com.rs.game.WorldTile;

@SuppressWarnings("serial")
public class Glacor extends Glacyte {

	private List<Glacyte> glacites;
	private boolean rangeAttack;

	/**
	 * TODO Glacor Cave, Glacor Combat - Glacors never move unless out of range
	 */

	public Glacor(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		super(null, id, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
		setCapDamage(2500);
		setEffect((byte) -1);
		setGlacor(this);
		setSpawned(false);
	}

	@Override
	public void handleHit(final Hit hit) {
		if (glacites == null) {
			if (getHitpoints() <= getMaxHitpoints() / 2) {
				glacites = new ArrayList<Glacyte>(2);
				createGlacites();
			}
		} else if (glacites.size() != 0)
			hit.setDamage(0);
		super.handleHit(hit);
	}

	private void createGlacites() {
		for (int index = 0; index < 3; index++) {
			tileLoop: for (int tileAttempt = 0; tileAttempt < 10; tileAttempt++) {
				WorldTile tile = new WorldTile(this, 2);
				if (World.isTileFree(0, tile.getX(), tile.getY(), 1)) {
					glacites.add(new Glacyte(this, 14302 + index, tile, -1, true));
					break tileLoop;
				}
			}
		}
	}

	public void verifyGlaciteEffect(Glacyte glacite) {
		if (glacites.size() == 1)
			setEffect(glacites.get(0).getEffect());
		glacites.remove(glacite);
	}

	@Override
	public void sendDeath(Entity killer) {
		super.sendDeath(killer);
		glacites = null;
	}

	public void setRangeAttack(boolean rangeAttack) {
		this.rangeAttack = rangeAttack;
	}

	public boolean isRangeAttack() {
		return rangeAttack;
	}

	public void resetMinions() {
		glacites = null;
		setEffect((byte) -1);
	}
}
