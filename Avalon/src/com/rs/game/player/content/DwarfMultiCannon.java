package com.rs.game.player.content;

import java.util.List;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.Region;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.OwnedObjectManager;
import com.rs.game.player.OwnedObjectManager.ProcessEvent;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public class DwarfMultiCannon {

	public static int[] CANNON_PIECES = { 6, 8, 10, 12, 20494, 20495, 20496, 20497, 20498, 20499, 20500, 20501 };
	public static int[] CANNON_OBJECTS = { 7, 8, 9, 6, 29398, 29401, 29402, 29406, 29403, 29404, 29405, 29408 };
	private static int[] CANNON_EMOTES = { 303, 305, 307, 289, 184, 182, 178, 291 };

	public static void fire(Player player, WorldObject object) {
		if (!OwnedObjectManager.isPlayerObject(player, object)) {
			player.getPackets().sendGameMessage("This is not your cannon.");
			return;
		}
		if (player.getCannonBalls() < 30) {
			int ammount = player.getInventory().getAmountOf(2);
			if (ammount == 0)
				player.getPackets().sendGameMessage("You need to load your cannon with cannon balls before firing it!");
			else {
				int add = 30 - player.getCannonBalls();
				if (ammount > add)
					ammount = add;
				player.addCannonBalls(ammount);
				player.getInventory().deleteItem(2, ammount);
				player.getPackets().sendGameMessage("You load the cannon with " + ammount + " cannon balls.");
			}
		} else
			player.getPackets().sendGameMessage("Your cannon is full.");
	}

	public static void pickupCannon(Player player, int stage, WorldObject object, int type) {
		if (!OwnedObjectManager.isPlayerObject(player, object))
			player.getPackets().sendGameMessage("This is not your cannon.");
		else {
			int space = stage;
			if (player.getInventory().getFreeSlots() < space) {
				player.getPackets()
						.sendGameMessage("You need atleast " + space + " inventory spots to pickup your cannon.");
				return;
			}
			player.getPackets().sendGameMessage("You pick up the cannon. It's really heavy.");
			for (int i = 0; i < stage; i++)
				player.getInventory().addItem(CANNON_PIECES[i + 4 * type], 1);
			if (player.getCannonBalls() > 0) {
				player.getInventory().addItem(2, player.getCannonBalls());
				player.removeCannonBalls();
			}
			OwnedObjectManager.removeObject(player, object);
			World.removeObject(object);
		}
	}

	public static int getAngle(int i) {
		return i * 360 / CANNON_EMOTES.length;
	}

	public static void setUp(Player player, int type) {
		if (OwnedObjectManager.containsObjectValue(player, CANNON_OBJECTS)) {
			player.getPackets().sendGameMessage("You can only have one cannon at a time.");
			return;
		}
		int count = 0;
		for (int item : CANNON_PIECES) {
			if (type == 0) {
				if (ItemDefinitions.getItemDefinitions(item).getName().toLowerCase().contains("gold")
						|| ItemDefinitions.getItemDefinitions(item).getName().toLowerCase().contains("royal"))
					continue;
			}
			if (type == 1) {
				if (!ItemDefinitions.getItemDefinitions(item).getName().toLowerCase().contains("gold")) {
					continue;
				}
			}
			if (type == 2) {
				if (!ItemDefinitions.getItemDefinitions(item).getName().toLowerCase().contains("royal")) {
					continue;
				}
			}
			if (!player.getInventory().containsItem(item, 1))
				break;
			count++;
		}
		WorldTile pos = player.transform(-3, -3, 0);
		if (!World.isTileFree(pos.getPlane(), pos.getX(), pos.getY(), 3) || World.getStandardFloorObject(player) != null
				|| World.getStandardFloorObject(pos) != null) {// World.getRegion(player.getRegionId()).getSpawnedObject(pos)
			player.getPackets().sendGameMessage("There isn't enough space to setup here.");
			return;
		}
		WorldObject[] objects = new WorldObject[count];
		for (int i = 0; i < count; i++)
			objects[i] = getObject(i, pos, type);
		final long[] cycles = new long[count];
		for (int i = 0; i < count - 1; i++)
			cycles[i] = 1200;
		cycles[count - 1] = 1500000;
		player.setNextFaceWorldTile(pos);
		player.lock(2);
		OwnedObjectManager.addOwnedObjectManager(player, objects, cycles, new ProcessEvent() {

			private int step;
			private int rotation;
			private boolean warned;
			private long disapearTime;

			@Override
			public void spawnObject(Player player, WorldObject object) {
				player.animate(new Animation(827));
				if (step == 0)
					player.getPackets().sendGameMessage("You place the cannon base on the ground.");
				else if (step == 1)
					player.getPackets().sendGameMessage("You add the stand.");
				else if (step == 2)
					player.getPackets().sendGameMessage("You add the barrels.");
				else if (step == 3) {
					player.getPackets().sendGameMessage("You add the furnance.");
					disapearTime = Utils.currentTimeMillis() + cycles[cycles.length - 1];
				}
				player.getInventory().deleteItem(CANNON_PIECES[step + type * 4], 1);
				if (step++ == cycles.length - 1)
					player.lock(2);
			}

			@Override
			public void process(Player player, WorldObject currentObject) {
				if (step != 4 || !player.clientHasLoadedMapRegion() || player.hasFinished())
					return;
				if (warned && (disapearTime - Utils.currentTimeMillis()) < 1 * 1000) {
					warned = false;
					player.getPackets().sendGameMessage("<col=ff0000>Your cannon has decayed!");
					player.lostCannon(true);
					OwnedObjectManager.removeObject(player, currentObject);
					World.removeObject(currentObject);
					return;
				}
				if (!warned && (disapearTime - Utils.currentTimeMillis()) < 5 * 1000 * 60) {
					player.getPackets().sendGameMessage("<col=ff0000>Your cannon is about to decay!");
					warned = true;
				}
				if (player.getCannonBalls() == 0)
					return;
				rotation++;
				rotation++;
				if (rotation == CANNON_EMOTES.length * 2)
					rotation = 0;
				World.sendObjectAnimation(player, currentObject, new Animation(CANNON_EMOTES[rotation / 2]));
				NPC nearestN = null;
				double lastD = Integer.MAX_VALUE;
				int angle = getAngle(rotation / 2);
				int objectSizeX = currentObject.getDefinitions().sizeX;
				int objectSizeY = currentObject.getDefinitions().sizeY;
				for (int regionId : player.getMapRegionsIds()) {
					Region region = World.getRegion(regionId);
					List<Integer> npcIndexes = region.getNPCsIndexes();
					if (npcIndexes == null)
						continue;
					for (int npcIndex : npcIndexes) {
						NPC npc = World.getNPCs().get(npcIndex);
						if (npc == null || npc == player.getFamiliar() || npc.isDead() || npc.hasFinished()
								|| npc.getPlane() != currentObject.getPlane()
								|| !Utils.isOnRange(npc.getX(), npc.getY(), npc.getSize(), currentObject.getX(),
										currentObject.getY(), objectSizeX, 16)
								|| !npc.getDefinitions().hasAttackOption()
								|| !npc.clipedProjectile(currentObject, false) || npc.isCantInteract()
								|| ((!player.isAtMultiArea() || !npc.isAtMultiArea()) && player.getAttackedBy() != npc
										&& player.getAttackedByDelay() > Utils.currentTimeMillis())
								|| ((!player.isAtMultiArea() || !npc.isAtMultiArea()) && npc.getAttackedBy() != player
										&& npc.getAttackedByDelay() > Utils.currentTimeMillis()))
							continue;
						if ((npc instanceof Familiar && (!player.isAtMultiArea() || !npc.isAtMultiArea()))
								|| !player.getControlerManager().canHit(npc))
							continue;
						int size = npc.getSize();
						double xOffset = (npc.getX() + size / 2) - (currentObject.getX() + objectSizeX / 2);
						double yOffset = (npc.getY() + size / 2) - (currentObject.getY() + objectSizeY / 2);
						double distance = Math.hypot(xOffset, yOffset);
						double targetAngle = Math.toDegrees(Math.atan2(xOffset, yOffset));
						if (targetAngle < 0)
							targetAngle += 360;
						double ratioAngle = Math.toDegrees(Math.atan(distance)) / 2;
						if (targetAngle < angle - ratioAngle || targetAngle > angle + ratioAngle || lastD <= distance)
							continue;
						lastD = distance;
						nearestN = npc;
					}
				}
				if (nearestN != null) {
					int damage = rollCannonHit(player, nearestN) ? Utils.random(301) : 0;
					World.sendProjectile(currentObject.transform(objectSizeX / 2, objectSizeY / 2, 0), nearestN, 53, 38,
							38, 30, 0, 0, 0);
					Hit hit = new Hit(player, damage, HitLook.CANNON_DAMAGE,
							Utils.getDistance(
									new WorldTile(currentObject.getX(), currentObject.getY(), currentObject.getPlane()),
									nearestN));
					nearestN.applyHit(hit);
					nearestN.handleIncommingHit(hit);
					player.addCannonBalls(-1);
					if (player.toggles("ONEXPPERHIT", false))
						player.getSkills().addXpNoBonus(Skills.RANGE,
								(player.toggles("ONEXHITS", false) ? Math.round(damage) / 10 : damage));
					else
						player.getSkills().addXp(Skills.RANGE, damage / 5);
					if (Utils.getRandom(100) < 25) {
						damage = rollCannonHit(player, nearestN) ? Utils.random(301) : 0;
						World.sendProjectile(currentObject.transform(objectSizeX / 2, objectSizeY / 2, 0), nearestN, 53,
								38, 38, 30, 0, 0, 0);
						hit = new Hit(player, damage, HitLook.CANNON_DAMAGE, Utils.getDistance(
								new WorldTile(currentObject.getX(), currentObject.getY(), currentObject.getPlane()),
								nearestN));
						nearestN.applyHit(hit);
						nearestN.handleIncommingHit(hit);
						if (player.getCannonBalls() > 0)
							player.addCannonBalls(-1);
						if (player.toggles("ONEXPPERHIT", false))
							player.getSkills().addXpNoBonus(Skills.RANGE,
									(player.toggles("ONEXHITS", false) ? Math.round(damage) / 10 : damage));
						else
							player.getSkills().addXp(Skills.RANGE, damage / 5);
					}
					nearestN.setTarget(player);
					if (nearestN instanceof Familiar)
						player.setWildernessSkull();
				}
			}
		});
	}

	private static boolean rollCannonHit(Player player, NPC npc) {
		int attackMethod = CombatDefinitions.getMeleeBonusStyle(player.getEquipment().getWeaponId(),
				player.getCombatDefinitions().getAttackStyle());
		int[] bonuses = player.getCombatDefinitions().getBonuses();
		int melee = attackMethod == CombatDefinitions.CRUSH_ATTACK ? bonuses[CombatDefinitions.CRUSH_ATTACK]
				: attackMethod == CombatDefinitions.SLASH_ATTACK ? bonuses[CombatDefinitions.SLASH_ATTACK]
						: bonuses[CombatDefinitions.STAB_ATTACK];
		int range = bonuses[CombatDefinitions.RANGE_ATTACK];
		int bestBonus = melee >= range ? melee : range;
		int attackLevel = melee >= range ? player.getSkills().getLevel(Skills.ATTACK)
				: player.getSkills().getLevel(Skills.RANGE);
		double attack = 0;
		double A = 0;
		attack += attackLevel;
		attack *= melee >= range ? player.getPrayer().getAttackMultiplier() : player.getPrayer().getRangeMultiplier();
		attack = Math.round(attack);
		attack += 8;
		attack = attack * (1 + bestBonus / 64);
		A = Math.round(attack);

		double defenceBonus = npc.getBonuses() == null ? 0 : npc.getBonuses()[CombatDefinitions.NPC_RANGE_DEFENCE] * 2;
		double defence = 0;
		double D = 0;
		defence = npc.getBonuses() == null ? 0 : npc.getBonuses()[CombatDefinitions.NPC_DEFENCE_LEVEL];
		defence = Math.round(defence);
		defence += 8;
		defence = defence + (1 + defenceBonus);
		D = Math.round(defence);

		double prob = A / D;
		double random = Utils.getRandomDouble(100);
		if (A < D) {
			prob = (A - 1) / (D * 2);
		} else if (A >= D) {
			prob = 1 - (D + 1) / (A * 2);
		}
		/*
		 * if (Settings.DEBUG) { System.out .println(npc.getName() + ", DefenceLevel: "
		 * + npc.getBonuses()[CombatDefinitions.NPC_DEFENCE_LEVEL] + ", RangeDefence: "
		 * + npc.getBonuses()[CombatDefinitions.NPC_RANGE_DEFENCE]);
		 * System.out.println(player.getDisplayName() + ", AttackLevel " + attackLevel +
		 * ", bestBonus = " + (melee >= range ? "melee" : "range") + " at " +
		 * bestBonus); System.out.println("Probability to hit: " + new
		 * DecimalFormat("##.##").format(prob * 100) + "%, Roll: " + new
		 * DecimalFormat("##.##").format(random) + ", Sucess hit?:" + (prob < random /
		 * 100 ? false : true)); }
		 */
		if (prob < random / 100)
			return false;
		return true;
	}

	private static WorldObject getObject(int i, WorldTile tile, int type) {
		return new WorldObject(CANNON_OBJECTS[i + 4 * type], 10, 0, tile.getX(), tile.getY(), tile.getPlane());
	}

}
