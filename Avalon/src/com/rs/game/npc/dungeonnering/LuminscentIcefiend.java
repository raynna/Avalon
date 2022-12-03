package com.rs.game.npc.dungeonnering;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.NewForceMovement;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.Combat;
import com.rs.game.player.actions.combat.PlayerCombat;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.RoomReference;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;
import com.rs.utils.WeaponTypesLoader.WeaponType;

public class LuminscentIcefiend extends DungeonBoss {

	private static final long serialVersionUID = -624907500327825702L;
	private static final byte FIRST_STAGE = 3;
	private static final Graphics ICE_SHARDS = new Graphics(2525);
	private static final Animation KNOCKBACK = new Animation(10070);

	private List<WorldTile> icicles;

	private int specialStage;
	private boolean specialEnabled;

	public LuminscentIcefiend(int id, WorldTile tile, DungeonManager manager, RoomReference reference) {
		super(id, tile, manager, reference);
		specialStage = FIRST_STAGE;
		icicles = new LinkedList<WorldTile>();
	}

	@Override
	public void processNPC() {
		super.processNPC();

		int max_hp = getMaxHitpoints();
		int current_hp = getHitpoints();

		if (current_hp < max_hp * (.25 * specialStage) && !specialEnabled)//75, 50, 25
			prepareSpecial();
	}

	private void prepareSpecial() {
		setNextFaceEntity(null);
		setCapDamage(0);
		specialEnabled = true;
		specialStage--;
	}

	@Override
	public void handleHit(Hit hit) {
		int current_hp = getHitpoints();
		if (hit.getDamage() >= current_hp && specialStage == 0 && !specialEnabled) {
			hit.setDamage(current_hp - 1);
			prepareSpecial();
		}
		super.handleHit(hit);
	}
	
	private static final WeaponType[][] WEAKNESS =
		{{ new WeaponType(Combat.MELEE_TYPE, CombatDefinitions.STAB_ATTACK), new WeaponType(Combat.MAGIC_TYPE, PlayerCombat.FIRE_SPELL)},};

	public WeaponType[][] getWeaknessStyle() {
		return WEAKNESS;
	}

	public boolean isSpecialEnabled() {
		return specialEnabled;
	}

	public void commenceSpecial() {
		specialEnabled = false;

		final NPC icefiend = this;
		WorldTasksManager.schedule(new WorldTask() {

			int count = 0;

			@Override
			public void run() {
				if (count == 21 || isDead()) {
					stop();
					icicles.clear();
					setCapDamage(-1);
					for (Player player : getManager().getParty().getTeam()) {
						//TODO player.setCantWalk(false);
						player.getTemporaryAttributtes().remove("FIEND_FLAGGED");
					}
					return;
				}
				count++;

				if (count < 5) {
					if (count == 1) {
						for (Entity t : getPossibleTargets()) {
							Player player = (Player) t;
							player.getPackets().sendGameMessage("The luminescent ice fiend is encased in ice and cannot be harmed!");
						}
					}
					return;
				}
				
				for (int idx = 0; idx < getManager().getParty().getTeam().size(); idx++) {
					Player player = getManager().getParty().getTeam().get(idx);
					if (player == null || !player.withinDistance(icefiend, getForceTargetDistance()) || player.isDead() || player.hasFinished())
						continue;
					boolean flagEnabled = player.getTemporaryAttributtes().get("FIEND_FLAGGED") != null;
					WorldTile currentTile = flagEnabled ? new WorldTile(player) : player.getLastWorldTile();
					tileLoop: for (int i = 0; i < icicles.size(); i++) {
						WorldTile tile = icicles.remove(i);
						player.getPackets().sendGraphics(ICE_SHARDS, tile);
						if (flagEnabled || player.getX() != tile.getX() || player.getY() != tile.getY())
							continue tileLoop;
						player.getTemporaryAttributtes().put("FIEND_FLAGGED", true);
					}
					icicles.add(currentTile);
				}

				if (count < 5)
					return;

				for (Iterator<WorldTile> it = icicles.iterator(); it.hasNext();) {
					WorldTile tile = it.next();

					entityLoop: for (Entity t : getPossibleTargets()) {
						Player player = (Player) t;
						if (player.getTemporaryAttributtes().get("FIEND_FLAGGED") == null)
							continue entityLoop;

						WorldTile nextTile = Utils.getFreeTile(player, 1);

						//if (!player.isCantWalk())
						//TODO 	player.setCantWalk(true);
						if (player.getActionManager().getAction() != null)
							player.getActionManager().forceStop();
						player.animate(KNOCKBACK);
						player.setNextWorldTile(nextTile);
						player.setNextForceMovement(new NewForceMovement(tile, 0, nextTile, 1, Utils.getAngle(tile.getX() - nextTile.getX(), tile.getY() - nextTile.getY())));
						int damageCap = (int) (player.getMaxHitpoints() * .10);
						if (player.getHitpoints() < damageCap)// If has 10% of HP.
							continue;
						int damage = Utils.random(20, 100);
						if (player.getHitpoints() - damage <= damageCap)
							damage = damageCap;
						player.applyHit(new Hit(icefiend, damage, HitLook.REGULAR_DAMAGE));
					}
				}
			}
		}, 0, 0);
	}
}
