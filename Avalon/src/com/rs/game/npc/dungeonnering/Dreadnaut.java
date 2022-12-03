package com.rs.game.npc.dungeonnering;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.actions.combat.Combat;
import com.rs.game.player.actions.combat.PlayerCombat;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.utils.Utils;
import com.rs.utils.WeaponTypesLoader.WeaponType;

@SuppressWarnings("serial")
public class Dreadnaut extends DungeonBoss {

	private List<GassPuddle> puddles;

	private int ticks;
	private boolean reduceMagicLevel;

	public Dreadnaut(int id, WorldTile tile, DungeonManager manager, RoomReference reference) {
		super(id, tile, manager, reference);
		setForceFollowClose(true);
		setRun(true);
		setLureDelay(6000);//6 seconds
		puddles = new CopyOnWriteArrayList<>();
	}

	@Override
	public void processNPC() {
		if (puddles == null) //still loading
			return;
		super.processNPC();
		if (!reduceMagicLevel) {
			if (isUnderCombat()) {
				for (Entity t : getPossibleTargets()) {
					if (!t.withinDistance(this, 1)) {
						ticks++;
						break;
					}
				}
			}
			if (ticks == 25) {
				reduceMagicLevel = true;
				setNextForceTalk(new ForceTalk("You cannot run from me forever!"));
			}
		}

		for (GassPuddle puddle : puddles) {
			puddle.cycles++;
			if (puddle.canDestroyPuddle()) {
				puddles.remove(puddle);
				continue;
			} else if (puddle.cycles % 2 != 0)
				continue;
			if (puddle.cycles % 2 == 0)
				puddle.refreshGraphics();
			List<Entity> targets = getPossibleTargets(true, true);
			for (Entity t : targets) {
				if (!t.matches(puddle.tile))
					continue;
				t.applyHit(new Hit(this, (int) Utils.random((int) (t.getHitpoints() * 0.25)) + 1, HitLook.REGULAR_DAMAGE));
			}
		}
	}
	
	private static final WeaponType[][] WEAKNESS =
		{{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.STAB_ATTACK), new WeaponType(Combat.MAGIC_TYPE, PlayerCombat.FIRE_SPELL) },};

	public WeaponType[][] getWeaknessStyle() {
		return WEAKNESS;
	}

	@Override
	public double getMeleePrayerMultiplier() {
		return 0.60;
	}

	public boolean canReduceMagicLevel() {
		return reduceMagicLevel;
	}

	public void setReduceMagicLevel(boolean reduceMagicLevel) {
		this.reduceMagicLevel = reduceMagicLevel;
	}

	public void addSpot(WorldTile tile) {
		GassPuddle puddle = new GassPuddle(this, tile);
		puddle.refreshGraphics();
		puddles.add(puddle);
	}

	private static class GassPuddle {
		final Dreadnaut boss;
		final WorldTile tile;
		int cycles;

		public GassPuddle(Dreadnaut boss, WorldTile tile) {
			this.tile = tile;
			this.boss = boss;
		}

		public void refreshGraphics() {
			World.sendGraphics(boss, new Graphics(2859, 0, 10), tile);
		}

		public boolean canDestroyPuddle() {
			return cycles == 50;
		}
	}
}
