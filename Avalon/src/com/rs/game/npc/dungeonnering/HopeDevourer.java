package com.rs.game.npc.dungeonnering;

import java.util.ArrayList;

import com.rs.game.Entity;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.WorldTile;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.combat.Combat;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.utils.Utils;
import com.rs.utils.WeaponTypesLoader.WeaponType;

@SuppressWarnings("serial")
public class HopeDevourer extends DungeonBoss {

	private int auraTicks;
	private int auraDamage;

	public HopeDevourer(int id, WorldTile tile, DungeonManager manager, RoomReference reference) {
		super(id, tile, manager, reference);
		setLureDelay(10000);
		setForceFollowClose(true);
		this.auraDamage = (int) Utils.random(getMaxHit() * .1, getMaxHit() * .15);
	}

	@Override
	public void processNPC() {
		super.processNPC();
		auraTicks++;
		if (auraTicks == 20) {
			sendAuraAttack();
			auraTicks = 0;
		}
	}


	private void sendAuraAttack() {
		for (Entity t : super.getPossibleTargets()) {
			t.applyHit(new Hit(this, auraDamage, HitLook.REGULAR_DAMAGE, 60));
			if (t instanceof Player) {
				Player player = (Player) t;
				int combatSkill = Utils.random(Skills.MAGIC);
				if (combatSkill == 3)
					combatSkill = 1;
				player.getSkills().set(combatSkill, (int) (player.getSkills().getLevel(combatSkill) * Utils.random(0.94, .99)));
				player.getPackets().sendGameMessage("You feel hopeless...");
			}
		}
	}

	private static final WeaponType[][] WEAKNESS =
		{ { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.STAB_ATTACK) }};

	public WeaponType[][] getWeaknessStyle() {
		return WEAKNESS;
	}

	@Override
	public ArrayList<Entity> getPossibleTargets() {
		ArrayList<Entity> targets = super.getPossibleTargets();
		if (getAttackedBy() == null)
			return targets;
		else {
			ArrayList<Entity> possibleTargets = new ArrayList<Entity>();
			for (Entity t : targets) {
				if (t.getAttackedByDelay() > Utils.currentTimeMillis())
					possibleTargets.add(t);
			}
			return possibleTargets;
		}
	}
}
