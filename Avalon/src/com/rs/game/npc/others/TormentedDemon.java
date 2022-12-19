package com.rs.game.npc.others;

import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public final class TormentedDemon extends NPC {

	private static final long serialVersionUID = -3391513753727542071L;

	private boolean[] demonPrayer;
	private int[] cachedDamage;
	private int shieldTimer;
	private int currentCombatType;
	private int previousCombatType;
	private int attackTicks = 0;
	private int currentType = 0;

	public TormentedDemon(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea,
			boolean spawned) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		demonPrayer = new boolean[3];
		cachedDamage = new int[3];
		setForceTargetDistance(64);
		setForceAgressiveDistance(10);
		shieldTimer = 0;
		switchPrayers(Utils.random(1, 2));
	}

	public void switchPrayers(int type) {
		transformIntoNPC(8349 + type);
		demonPrayer[type] = true;
	}

	@Override
	public void processNPC() {
		super.processNPC();
		if (isDead())
			return;
		if (getCombat().process()) {
			if (attackTicks < 26)
				attackTicks++;
			if (getAttackTicks() == 26) {// 16 seconds change random attackstyle
				resetAttackTicks();
				int attackType = Utils.getRandom(2);
				while (attackType == getCurrentCombatType()) {// make sure it cant pick same attack style twice.
					attackType = Utils.getRandom(2);
				}
				sendRandomProjectile();// always send random projectile when changign attackstyle that can hit nearby
				// players, even when hiding
				setPreviousCombatType(getCurrentCombatType());
				setCurrentCombatType(attackType);
				getCombat().setCombatDelay(6);
			}
			if (shieldTimer > 0)
				shieldTimer--;
			if (cachedDamage[currentType] >= 310) {// if damage more than 310, change prayer
				demonPrayer = new boolean[3];
				int pray = currentType;
				switchPrayers(pray);
				cachedDamage = new int[3];
			}
		}
	}

	@Override
	public void handleIncommingHit(final Hit hit) {// process before hit is applied
		super.handleIncommingHit(hit);
		if (shieldTimer <= 0 && hit.getDamage() > 0) {// 75% of damage is absorbed
			hit.setDamage((int) (hit.getDamage() * 0.25));
			gfx(new Graphics(1885));
		}
		if (hit.getLook() == HitLook.MELEE_DAMAGE) {
			currentType = 0;
			if (hit.getSource() instanceof Player) {// darklight
				Player player = (Player) hit.getSource();
				if (demonPrayer[currentType] && player.getTemporaryAttributes().get("VERAC_EFFECT") == Boolean.FALSE) {
					hit.setDamage(0);// hits 0 if pray melee unless verac effect
				} else {
					if ((player.getEquipment().getWeaponId() == 6746 || player.getEquipment().getWeaponId() == 2402)
							&& hit.getLook() == HitLook.MELEE_DAMAGE && hit.getDamage() > 0) {
						shieldTimer = 60;// resets shield timer every > 0 hit
						player.getPackets().sendGameMessage("The demon is temporarily weakend by your weapon.");
					}
					cachedDamage[currentType] += hit.getDamage();
				}
			}
		} else if (hit.getLook() == HitLook.MAGIC_DAMAGE) {
			currentType = 1;
			if (demonPrayer[currentType])
				hit.setDamage(0);
			else
				cachedDamage[currentType] += hit.getDamage();
		} else if (hit.getLook() == HitLook.RANGE_DAMAGE || hit.getLook() == HitLook.CANNON_DAMAGE) {
			currentType = 2;
			if (demonPrayer[currentType])
				hit.setDamage(0);
			else
				cachedDamage[currentType] += hit.getDamage();
		}
		if (hit.getDamage() <= 0) {
			cachedDamage[currentType] += 20;// always 20+ even on miss
		}
		if (hit.getSource() instanceof Player) {// darklight
			Player player = (Player) hit.getSource();
			if (shieldTimer <= 0 && hit.getDamage() > 0)
				player.getPackets().sendGameMessage("The demon shield absorbs most of your damage.");
		}
	}

	public void sendRandomProjectile() {
		WorldTile tile = new WorldTile(getX() + Utils.random(-7, 7), getY() + Utils.random(-7, 7), getPlane());
		animate(new Animation(10918));
		World.sendGroundProjectile(this, tile, 1884);
		NPC npc = this;
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				for (int regionId : getMapRegionsIds()) {
					List<Integer> playerIndexes = World.getRegion(regionId).getPlayerIndexes();
					if (playerIndexes != null) {
						for (int npcIndex : playerIndexes) {
							Player player = World.getPlayers().get(npcIndex);
							if (player == null || player.isDead() || player.hasFinished() || !player.hasStarted()
									|| Utils.getDistance(player, tile) > 3 || player.getAttackedBy() != npc)
								continue;
							player.gfx(new Graphics(1883, 0, 0));
							player.getPackets().sendGameMessage("The demon's magical attack splashes on you.");
							player.applyHit(new Hit(npc, Utils.random(138, 289), HitLook.MAGIC_DAMAGE, 0));
						}
					}
				}
			}
		}, 2);
	}

	public int getCurrentCombatType() {
		return currentCombatType;
	}

	public void setCurrentCombatType(int combatType) {
		this.currentCombatType = combatType;
	}

	public int getPreviousCombatType() {
		return previousCombatType;
	}

	public void setPreviousCombatType(int combatType) {
		this.previousCombatType = combatType;
	}

	public int getAttackTicks() {
		return attackTicks;
	}

	public void resetAttackTicks() {
		attackTicks = 0;
	}

}
