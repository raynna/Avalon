package com.rs.game.npc.dungeonnering;

import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.actions.combat.Combat;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.skills.DungeoneeringTraps;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;
import com.rs.utils.WeaponTypesLoader.WeaponType;

@SuppressWarnings("serial")
public class DungeonHunterNPC extends DungeonNPC {

	public DungeonHunterNPC(int id, WorldTile tile, DungeonManager manager, double multiplier) {
		super(id, tile, manager, multiplier);
		setForceAgressive(false);
	}

	@Override
	public void processNPC() {
		if (isCantInteract() || getId() >= 11096)
			return;
		super.processNPC();
		List<MastyxTrap> traps = getManager().getMastyxTraps();
		if (traps.isEmpty())
			return;
		final int tier = DungeoneeringTraps.getNPCTier(getId());
		for (final MastyxTrap trap : traps) {
			if (!withinDistance(trap, 3) || Utils.random(3) != 0)
				continue;
			trap.setCantInteract(true);
			setCantInteract(true);
			setNextFaceEntity(trap);
			addWalkSteps(trap.getX() + 1, trap.getY() + 1);

			final int trap_tier = trap.getTier();
			double successRatio = getSuccessRatio(tier, trap_tier);
			final boolean failed = successRatio < Math.random();

			setCantInteract(true);
			if (failed) {
				WorldTasksManager.schedule(new WorldTask() {

					int ticks = 0;

					@Override
					public void run() {
						ticks++;
						if (ticks == 5) {
							animate(new Animation(13264));
						} else if (ticks == 8) {
							trap.setNextNPCTransformation(1957);
							trap.gfx(new Graphics(2561 + trap_tier));
						} else if (ticks == 16) {
							getManager().removeMastyxTrap(trap);
							setCantInteract(false);
							this.stop();
							return;
						}
					}
				}, 0, 0);
			} else {
				WorldTasksManager.schedule(new WorldTask() {

					int ticks = 0;

					@Override
					public void run() {
						ticks++;
						if (ticks == 9) {
							trap.setNextNPCTransformation(1957);
							trap.gfx(new Graphics(2551 + trap_tier));
						} else if (ticks == 13) {
							animate(new Animation(13260));
						} else if (ticks == 18) {
							setNextNPCTransformation(getId() + 10);
						} else if (ticks == 20) {
							setCantInteract(false);
							getManager().removeMastyxTrap(trap);
							this.stop();
							return;
						}
					}
				}, 0, 0);
			}
		}
	}
	
	public int getHide(int npcId) {
		if (npcId == 1337) {
			return 000;//hideid
		}
		return -1;
	}

	@Override
	public void drop() {
		World.addGroundItem(new Item(532), new WorldTile(this)); //big bones
		World.addGroundItem(new Item(getHide(this.getId())), new WorldTile(this)); //hide
	}

	private static double getSuccessRatio(int tier, int trapTier) {
		double successRatio = 0.0;
		int tierProduct = trapTier - tier;
		if (tierProduct == 0)
			successRatio = 0.5;
		else if (tierProduct > 0)
			successRatio = 0.5 + (tierProduct / 10.0);

		if (successRatio > 0.9)
			successRatio = 0.9;
		return successRatio;
	}

	private static final WeaponType[][] WEAKNESS = { { new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.STAB_ATTACK) },
		{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.SLASH_ATTACK) },
		{new WeaponType(Combat.RANGE_TYPE, -1) } };

	public WeaponType[][] getWeaknessStyle() {
		return WEAKNESS;
	}
}
