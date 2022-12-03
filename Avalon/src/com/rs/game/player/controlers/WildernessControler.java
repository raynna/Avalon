package com.rs.game.player.controlers;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceMovement;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.Pots;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class WildernessControler extends Controler {

	private boolean showingSkull;

	@Override
	public void start() {
		checkBoosts(player);
		moved();
	}

	public static void checkBoosts(Player player) {
		boolean changed = false;
		int level = player.getSkills().getLevelForXp(Skills.ATTACK);
		int maxLevel = (int) (level + 5 + (level * 0.15));
		if (maxLevel < player.getSkills().getLevel(Skills.ATTACK)) {
			player.getSkills().set(Skills.ATTACK, maxLevel);
			changed = true;
		}
		level = player.getSkills().getLevelForXp(Skills.STRENGTH);
		maxLevel = (int) (level + 5 + (level * 0.15));
		if (maxLevel < player.getSkills().getLevel(Skills.STRENGTH)) {
			player.getSkills().set(Skills.STRENGTH, maxLevel);
			changed = true;
		}
		level = player.getSkills().getLevelForXp(Skills.DEFENCE);
		maxLevel = (int) (level + 5 + (level * 0.15));
		if (maxLevel < player.getSkills().getLevel(Skills.DEFENCE)) {
			player.getSkills().set(Skills.DEFENCE, maxLevel);
			changed = true;
		}
		level = player.getSkills().getLevelForXp(Skills.RANGE);
		maxLevel = (int) (level + 5 + (level * 0.1));
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
			player.getPackets().sendGameMessage("Your extreme potion bonus has been reduced.");
		if (player.getOverloadDelay() > 1)
			Pots.resetOverLoadEffect(player);
	}

	@Override
	public boolean login() {
		moved();
		return false;
	}

	@Override
	public boolean keepCombating(Entity target) {
		if (target instanceof NPC)
			return true;
		if (!canAttack(target))
			return false;
		if (target instanceof Player) {
			Player p2 = (Player) target;
			if (player.isCanPvp() && !p2.isCanPvp()) {
				player.getPackets().sendGameMessage("That player is not in the wilderness.");
				return false;
			}
			if (Math.abs(player.getSkills().getCombatLevel() - p2.getSkills().getCombatLevel()) > getWildLevel(
					player)) {
				player.getPackets().sendGameMessage("Your level diffrence is too great!");
				player.getPackets().sendGameMessage("You need to move deeper into the Wilderness.");
				return false;
			}
			if (Math.abs(player.getSkills().getCombatLevel() - p2.getSkills().getCombatLevel()) > getWildLevel(p2)) {
				player.getPackets().sendGameMessage("Your level diffrence is too great!");
				player.getPackets().sendGameMessage("You need to move deeper into the Wilderness.");
				return false;
			}
		}
		if (target instanceof Player) {
			Player p2 = (Player) target;
			if (!player.attackedBy.containsKey(p2))
				player.setWildernessSkull();
		}
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
			if (Math.abs(player.getSkills().getCombatLevel() - p2.getSkills().getCombatLevel()) > getWildLevel(
					player)) {
				player.getPackets().sendGameMessage("The difference between your combat level and the combat level of "
						+ p2.getDisplayName() + " is too great.");
				player.getPackets()
						.sendGameMessage("You needs to move deeper into the Wilderness before you can attack him.");
				return false;
			}
			if (Math.abs(player.getSkills().getCombatLevel() - p2.getSkills().getCombatLevel()) > getWildLevel(p2)) {
				player.getPackets().sendGameMessage("The difference between your combat level and the combat level of "
						+ p2.getDisplayName() + " is too great.");
				player.getPackets()
						.sendGameMessage("He needs to move deeper into the Wilderness before you can attack him.");
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
		if (player.getCombatDefinitions().getSpellId() <= 0
				&& Utils.inCircle(new WorldTile(3105, 3933, 0), target, 23)) {
			player.getPackets().sendGameMessage("You can only use magic in the arena.");
			return false;
		}
		Player p2 = (Player) target;
		if (Math.abs(player.getSkills().getCombatLevel() - p2.getSkills().getCombatLevel()) > getWildLevel(player))
			return false;
		if (Math.abs(player.getSkills().getCombatLevel() - p2.getSkills().getCombatLevel()) > getWildLevel(p2))
			return false;
		return true;
	}

	@Override
	public boolean processMagicTeleport(WorldTile toTile) {
		if (getWildLevel(player) >= 21) {
			player.getPackets().sendGameMessage("You can't teleport above level 20 wilderness.");
			return false;
		}
		if (player.getTeleBlockDelay() > Utils.currentTimeMillis()) {
			player.getPackets()
					.sendGameMessage("You are teleblocked for another " + player.getTeleBlockTimeleft() + ".");
			return false;
		}
		loseEP();
		return true;

	}

	@Override
	public boolean processItemTeleport(WorldTile toTile) {
		if (getWildLevel(player) >= 21) {
			player.getPackets().sendGameMessage("You can't teleport above level 20 wilderness.");
			return false;
		}
		if (player.getTeleBlockDelay() > Utils.currentTimeMillis()) {
			player.getPackets()
					.sendGameMessage("You are teleblocked for another " + player.getTeleBlockTimeleft() + ".");
			return false;
		}
		loseEP();
		return true;
	}

	@Override
	public boolean processJewerlyTeleport(WorldTile toTile) {
		if (getWildLevel(player) >= 31) {
			player.getPackets().sendGameMessage("You can't teleport above level 30 wilderness.");
			return false;
		}
		if (player.getTeleBlockDelay() > Utils.currentTimeMillis()) {
			player.getPackets()
					.sendGameMessage("You are teleblocked for another " + player.getTeleBlockTimeleft() + ".");
			return false;
		}
		loseEP();
		return true;
	}

	@Override
	public boolean processObjectTeleport(WorldTile toTile) {
		if (player.getTeleBlockDelay() > Utils.currentTimeMillis()) {
			player.getPackets()
					.sendGameMessage("You are teleblocked for another " + player.getTeleBlockTimeleft() + ".");
			return false;
		}
		loseEP();
		return true;
	}

	public void loseEP() {
		if (player.getEp() >= 10 && player.isInCombat(10000)) {
			player.setEp(player.getEp() - 10);
			player.sm("You lose 10% EP for teleporting while being in combat.");
		}
	}

	public void showSkull() {
		player.getInterfaceManager().sendTab(player.getInterfaceManager().hasRezizableScreen() ? 11 : 0, 381);
		if (player.toggles("KDRINTER", false)) {
			double kill = player.getKillCount();
			double death = player.getDeathCount();
			double dr = kill / death;
			player.getInterfaceManager().sendTab(10, 3040);
			player.getPackets().sendIComponentText(3040, 2, "Kills: " + (int) kill);
			player.getPackets().sendIComponentText(3040, 3, "Deaths: " + (int) death);
			player.getPackets().sendIComponentText(3040, 4, "Ratio: " + dr);
		}
	}

	public static void showKDRInter(Player player) {
		if (player.toggles("KDRINTER", false)) {
			player.sm("send kdr");
			double kill = player.getKillCount();
			double death = player.getDeathCount();
			double dr = kill / death;
			player.getInterfaceManager().sendTab(10, 3040);
			player.getPackets().sendIComponentText(3040, 2, "Kills: " + (int) kill);
			player.getPackets().sendIComponentText(3040, 3, "Deaths: " + (int) death);
			player.getPackets().sendIComponentText(3040, 4, "Ratio: " + dr);
		}
	}

	public static boolean isDitch(int id) {
		return id >= 1440 && id <= 1444 || id >= 65076 && id <= 65087;
	}

	@Override
	public boolean processObjectClick1(final WorldObject object) {
		if (isDitch(object.getId())) {
			player.lock();
			player.animate(new Animation(6132));
			final WorldTile toTile = new WorldTile(
					object.getRotation() == 1 || object.getRotation() == 3 ? object.getX() + 2 : player.getX(),
					object.getRotation() == 0 || object.getRotation() == 2 ? object.getY() - 1 : player.getY(),
					object.getPlane());

			player.setNextForceMovement(new ForceMovement(new WorldTile(player), 1, toTile, 2,
					object.getRotation() == 0 || object.getRotation() == 2 ? ForceMovement.SOUTH : ForceMovement.EAST));
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					player.setNextWorldTile(toTile);
					player.faceObject(object);
					removeIcon();
					removeControler();
					player.resetReceivedDamage();
					player.unlock();
				}
			}, 2);
			return false;
		}
		return true;
	}

	@Override
	public boolean processObjectClick2(final WorldObject object) {
		return true;
	}

	@Override
	public void sendInterfaces() {
		if (isAtWild(player)) {
			showSkull();
		}
	}

	@Override
	public boolean sendDeath() {
		final Player instance = player;
		player.resetWalkSteps();
		player.lock(7);
		player.animate(new Animation(836));
		if (player.getFamiliar() != null)
			player.getFamiliar().sendDeath(player);
		player.checkPetDeath();
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 0) {
				} else if (loop == 1) {
					player.getPackets().sendGameMessage("Oh dear, you have died.");
				} else if (loop == 2) {
					Player killer = player.getMostDamageReceivedSourcePlayer();
					if (killer != null) {
						player.sendItemsOnDeath(killer, true);
						killer.increaseKillCount(player);
						// WildernessKills.addKill(killer, player);
					} else
						player.sendItemsOnDeath(instance, true);
					player.getEquipment().init();
					player.getInventory().init();
					player.reset();
					player.setNextWorldTile(new WorldTile(Settings.RESPAWN_PLAYER_LOCATION));
					player.animate(new Animation(-1));
					removeIcon();
					removeControler();
				} else if (loop == 3) {
					player.getPackets().sendMusicEffect(90);
					stop();
				}
				loop++;
			}
		}, 0, 1);
		return false;
	}

	@Override
	public void moved() {
		boolean isAtWild = isAtWild(player);
		boolean isAtWildSafe = isAtWildSafe(player);
		if (!player.isAtWild() && !isAtWildSafe && !isAtWild && !showingSkull) {
			player.setCanPvp(false);
			removeIcon();
			removeControler();
		} else if (!showingSkull && isAtWild && !isAtWildSafe) {
			showingSkull = true;
			player.setCanPvp(true);
			showSkull();
			player.getPackets().sendGlobalConfig(1000, player.getSkills().getCombatLevel() + player.getSkills().getSummoningCombatLevel());
			player.getAppearence().generateAppearenceData();
			checkBoosts(player);
		} else if (showingSkull && (isAtWildSafe || !isAtWild)) {
			removeIcon();
		} else if (!isAtWildSafe && !isAtWild) {
			player.setCanPvp(false);
			removeIcon();
		}
	}

	public void removeIcon() {
		if (showingSkull) {
			showingSkull = false;
			player.setCanPvp(false);
			if (player.toggles("KDRINTER", false)) {
				if (player.getInterfaceManager().containsTab(10))
					player.getInterfaceManager().closeTab(player.getInterfaceManager().hasRezizableScreen(), 10);
			}
			player.getPackets().closeInterface(player.getInterfaceManager().hasRezizableScreen() ? 11 : 0);
			player.getAppearence().generateAppearenceData();
			player.getEquipment().refresh(null);
			player.getPackets().sendGlobalConfig(1000, 0);
		}
	}

	@Override
	public boolean logout() {
		return false; // so doesnt remove script
	}

	@Override
	public void forceClose() {
		removeIcon();
	}

	public static final boolean isAtWild(WorldTile tile) {
		return (tile.getX() >= 3011 && tile.getX() <= 3132 && tile.getY() >= 10052 && tile.getY() <= 10175)
				|| (tile.getX() >= 2940 && tile.getX() <= 3395 && tile.getY() >= 3525 && tile.getY() <= 4000)
				|| (tile.getX() >= 3264 && tile.getX() <= 3279 && tile.getY() >= 3525 && tile.getY() <= 3672)
				|| (tile.getX() >= 2756 && tile.getX() <= 2875 && tile.getY() >= 5512 && tile.getY() <= 5627)
				|| (tile.getX() >= 3158 && tile.getX() <= 3181 && tile.getY() >= 3679 && tile.getY() <= 3697)
				|| (tile.getX() >= 3280 && tile.getX() <= 3183 && tile.getY() >= 3885 && tile.getY() <= 3888)
				|| (tile.getX() >= 3012 && tile.getX() <= 3059 && tile.getY() >= 10303 && tile.getY() <= 10351)
				|| (tile.getX() >= 3060 && tile.getX() <= 3072 && tile.getY() >= 10251 && tile.getY() <= 10263);
	}

	public static boolean isAtWildSafe(Player player) {
		return (player.getX() >= 2940 && player.getX() <= 3395 && player.getY() >= 3524 && player.getY() <= 3523
				|| player.getX() >= 2327 && player.getX() <= 2332 && player.getY() >= 3686 && player.getY() <= 3693
				|| player.getX() >= 2994 && player.getX() <= 3030 && player.getY() >= 3526 && player.getY() <= 3533
				|| player.getX() >= 3005 && player.getX() <= 3025 && player.getY() >= 3534 && player.getY() <= 3543
				|| player.getX() >= 3001 && player.getX() <= 3004 && player.getY() >= 3534 && player.getY() <= 3538
				|| player.getX() >= 3386 && player.getX() <= 3396 && player.getY() >= 3612 && player.getY() <= 3630);
	}

	public static int getWildLevel(Player player) {
		if ((player.getX() >= 3060 && player.getX() <= 3072 && player.getY() >= 10251 && player.getY() <= 10263))
			return 42;
		if (player.getY() > 9900)
			return (player.getY() - 9912) / 8 + 1;
		return (player.getY() - 3520) / 8 + 1;
	}
}
