package com.rs.game.npc;

import java.util.ArrayList;
import java.util.List;

import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

@SuppressWarnings("serial")
public class CombatEventNPC extends NPC {

	private final Player target;

	public static enum CombatEventNPCS {
		TREE_SPIRIT(4470, Skills.WOODCUTTING, "Leave these woods and never return!"),
		ROCK_GOLEM(8648, Skills.MINING, "Raarrrgghh! Flee human!"),
		RIVER_TROLL(8646, Skills.FISHING, "Fishies be mine! Leave dem fishies!"), 
		SHADE(8645, Skills.PRAYER, "Flee from me mortal, for the undead has returned!"),
		ZOMBIE(75, -2, "Braaiinnzzzzzzzzzz")

		;

		private CombatEventNPCS(int id, int skill, String spawnMessage) {
			this.id = id;
			this.skill = skill;
			this.spawnMessage = spawnMessage;
		}

		private int id, skill;
		private String spawnMessage;
	}

	public static void startRandomEvent(Player player, int skill) {
		if (player.isMember()) {
			return;
		}
		List<CombatEventNPCS> events = new ArrayList<CombatEventNPCS>();
		player.animate(new Animation(-1));
		for (CombatEventNPCS e : CombatEventNPCS.values()) {
			if (e.skill == skill || e.skill == -1 || (e.skill == -2
					&& (skill == Skills.SUMMONING || (skill >= Skills.ATTACK && skill <= Skills.MAGIC))))
				events.add(e);
		}
		if (events.size() == 0)
			return;
		player.stopAll();
		new CombatEventNPC(events.get(Utils.random(events.size())), player);
	}

	public CombatEventNPC(CombatEventNPCS cbn, Player target) {
		super(cbn.id, new WorldTile(target), -1, true, true);
		setIntelligentRouteFinder(true);
		setForceAgressive(true);
		setTarget(target);
		if (getDefinitions().hasAttackOption()) {
			setCombatLevel((int) (target.getSkills().getCombatLevel() * 1.3));
			setHitpoints(getMaxHitpoints());
		} else
			setCantSetTargetAutoRelatio(true);
		getCombat().setTarget(target);
		this.target = target;
		if (cbn.spawnMessage != null)
			setNextForceTalk(new ForceTalk(cbn.spawnMessage));
	}

	public static boolean canRandomEvent(Player player) {
		return player.getControlerManager().getControler() == null && !player.getCutscenesManager().hasCutscene()
				&& !player.isDead();
	}

	@Override
	public double getMagePrayerMultiplier() {
		return 0.6;
	}

	@Override
	public double getRangePrayerMultiplier() {
		return 0.6;
	}

	@Override
	public double getMeleePrayerMultiplier() {
		return 0.6;
	}

	@Override
	public int getMaxHitpoints() {
		return getCombatLevel() * 20 + 1;
	}

	public int getMaxHit() {
		int cb = getCombatLevel();
		return cb == -1 ? 10 : cb;
	}

	@Override
	public void processNPC() {
		if (target.hasFinished() || !withinDistance(target, 16) || !canRandomEvent(target) || !isUnderCombat())
			finish();
		else
			super.processNPC();
	}
}