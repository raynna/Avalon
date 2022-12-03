package com.rs.game.player.controlers;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class Nonprod extends Controler {

	/**
	 * @Author Andreas
	 * @category Non prod pking area
	 */

	private boolean showingSkull;

	@Override
	public void start() {
		// sendInterfaces();
		checkBoosts(player);
	}

	public static void checkBoosts(Player player) {
		boolean changed = false;
		int level = player.getSkills().getLevelForXp(Skills.ATTACK);
		int maxLevel = level;
		if (maxLevel < player.getSkills().getLevel(Skills.ATTACK)) {
			player.getSkills().set(Skills.ATTACK, maxLevel);
			changed = true;
		}
		level = player.getSkills().getLevelForXp(Skills.STRENGTH);
		maxLevel = (int) (level + 5 + (level * 0.08));
		if (maxLevel < player.getSkills().getLevel(Skills.STRENGTH)) {
			player.getSkills().set(Skills.STRENGTH, maxLevel);
			changed = true;
		}
		level = player.getSkills().getLevelForXp(Skills.DEFENCE);
		maxLevel = level;
		if (maxLevel < player.getSkills().getLevel(Skills.DEFENCE)) {
			player.getSkills().set(Skills.DEFENCE, maxLevel);
			changed = true;
		}
		level = player.getSkills().getLevelForXp(Skills.RANGE);
		maxLevel = level;
		if (maxLevel < player.getSkills().getLevel(Skills.RANGE)) {
			player.getSkills().set(Skills.RANGE, maxLevel);
			changed = true;
		}
		level = player.getSkills().getLevelForXp(Skills.MAGIC);
		maxLevel = level + 7;
		if (maxLevel < player.getSkills().getLevel(Skills.MAGIC)) {
			player.getSkills().set(Skills.MAGIC, maxLevel);
			changed = true;
		}
		if (changed)
			player.getPackets().sendGameMessage("Your potion bonus has been reduced.");
	}

	@Override
	public boolean login() {
		start();
		moved();
		return false;
	}

	@Override
	public boolean keepCombating(Entity target) {
		if (target instanceof NPC)
			return true;
		if (!canAttack(target))
			return false;
		if (target.getAttackedBy() != player && player.getAttackedBy() != target)
			player.setWildernessSkull();

		return true;
	}

	@Override
	public boolean canAttack(Entity target) {
		if (target instanceof Player) {
			Player p2 = (Player) target;
			if (player.isCanPvp() && !p2.isCanPvp()) {
				player.getPackets().sendGameMessage("That player is not in the wilderness.");
				return false;
			}
			if (canHit(target))
				return true;
			return false;
		}
		return true;
	}

	@Override
	public boolean canHit(Entity target) {
		if (target instanceof NPC)
			return true;
		Player p2 = (Player) target;
		if (Math.abs(player.getSkills().getCombatLevel() - p2.getSkills().getCombatLevel()) > 5) // 5
																									// levels
																									// apart
																									// should
																									// work
																									// :)
			return false;
		return true;
	}

	@Override
	public boolean processMagicTeleport(WorldTile toTile) {
		player.getPackets().sendGameMessage("A mysterious force prevents you from teleporting.");
		return false;

	}

	@Override
	public boolean processItemTeleport(WorldTile toTile) {
		player.getPackets().sendGameMessage("A mysterious force prevents you from teleporting.");
		return false;
	}

	@Override
	public boolean processJewerlyTeleport(WorldTile toTile) {
		player.getPackets().sendGameMessage("A mysterious force prevents you from teleporting.");
		return false;
	}

	@Override
	public boolean processObjectTeleport(WorldTile toTile) {
		return true;// Only letting people teleport with the dung tele
	}

	@Override
	public void sendInterfaces() {
		/*
		 * int getMinusCB = player.getSkills().getCombatLevel() - 15; int
		 * getPlussCB = player.getSkills().getCombatLevel() + 15;
		 * player.getPackets().sendIComponentText(24, 7, "" + getMinusCB + " - "
		 * + getPlussCB + ""); player.getPackets().sendIComponentText(24, 3,
		 * ""); player.getPackets().sendIComponentText(24, 6, "");
		 */
	}

	@Override
	public boolean sendDeath() {
		player.resetWalkSteps();
		player.lock(7);
		player.animate(new Animation(836));
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
				} else if (loop == 1) {
					player.getPackets().sendGameMessage("Oh dear, you have died.");
				} else if (loop == 3) {
					Player killer = player.getMostDamageReceivedSourcePlayer();
					if (killer != null) {
						killer.removeDamage(player);
						killer.increaseKillCount(player);
					}
					player.reset();
					player.animate(new Animation(-1));
				} else if (loop == 4) {
					removeControler();
					// WildernessDitch.removePlayer(player);
					player.getPackets().sendMusicEffect(90);
					player.setNextWorldTile(new WorldTile(3219, 3219, 0));
					stop();
				}
				loop++;
			}
		}, 0, 1);
		return false;
	}

	@Override
	public void moved() {
		if (player.isAtNonprod()) {
			player.setCanPvp(true);
		} else if (!player.isAtNonprod()) {
			player.setCanPvp(false);
			player.getControlerManager().forceStop();
		}
	}

	public void removeIcon() {
		if (showingSkull) {
			showingSkull = false;
			player.setCanPvp(false);
			player.getAppearence().generateAppearenceData();
			player.getEquipment().refresh(null);
		}
	}

	@Override
	public boolean logout() {
		return true; // script will auto start
	}

	@Override
	public void forceClose() {
		player.getInterfaceManager().closeOverlay(false);
	}

	public static final boolean isAtNonprod(WorldTile tile) {
		return (tile.getX() >= 1859 && tile.getX() <= 1915 && tile.getY() >= 3215 && tile.getY() <= 3249);
	}

	/*
	 * public boolean isAtWildSafe() { return (player.getX() >= 3250 &&
	 * player.getX() <= 3257 && player.getY() >= 3416 && player.getY() <= 3423
	 * || player.getX() >= 3179 && player.getX() <= 3194 && player.getY() >=
	 * 3432 && player.getY() <= 3446); }
	 */

}