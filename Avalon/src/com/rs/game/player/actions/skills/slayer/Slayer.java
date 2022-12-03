package com.rs.game.player.actions.skills.slayer;

import java.io.Serializable;
import java.util.List;

import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 *
 * @author Andreas - AvalonPK
 *
 */

public class Slayer {

	public static boolean canAttackNPC(int slayerLevel, String name) {
		if (slayerLevel >= getLevelToAttack(name))
			return true;
		return false;
	}

	public static int getLevelToAttack(String name) {
		for (SlayerTask task : SlayerTask.values()) {
			if (name.toLowerCase()
					.equalsIgnoreCase(task.toString().toLowerCase().replace("_", " ").replace("$", "'"))) {
				return task.getLevelRequried();
			}
		}
		return 1;
	}

	public static int getLevelRequirement(String name) {
		for (SlayerTask task : SlayerTask.values()) {
			if (name.toLowerCase().contains(task.toString().replace("_", " ").replace("$", "'"))) {
				return task.getLevelRequried();
			}
		}
		return 1;
	}

	public static boolean hasNosepeg(Entity target) {
		if (!(target instanceof Player))
			return true;
		Player targetPlayer = (Player) target;
		int hat = targetPlayer.getEquipment().getHatId();
		return hat == 4168 || hasSlayerHelmet(target);
	}

	public static boolean hasMask(Entity target) {
		if (!(target instanceof Player))
			return true;
		Player targetPlayer = (Player) target;
		int hat = targetPlayer.getEquipment().getHatId();
		return hat == 1506 || hat == 4164 || hat == 13277 || hasSlayerHelmet(target);
	}

	public static boolean hasEarmuffs(Entity target) {
		if (!(target instanceof Player))
			return true;
		Player targetPlayer = (Player) target;
		int hat = targetPlayer.getEquipment().getHatId();
		return hat == 4166 || hat == 13277 || hasSlayerHelmet(target);
	}

	public static boolean hasWitchWoodIcon(Entity target) {
		if (!(target instanceof Player))
			return true;
		Player targetPlayer = (Player) target;
		int hat = targetPlayer.getEquipment().getAmuletId();
		return hat == 8923;
	}

	public static boolean hasSlayerHelmet(Entity target) {
		if (!(target instanceof Player))
			return true;
		Player targetPlayer = (Player) target;
		int hat = targetPlayer.getEquipment().getHatId();
		return hat == 13263 || hat == 14636 || hat == 14637 || hasFullSlayerHelmet(target);
	}

	public static boolean hasFullSlayerHelmet(Entity target) {
		if (!(target instanceof Player))
			return true;
		Player targetPlayer = (Player) target;
		int hat = targetPlayer.getEquipment().getHatId();
		return hat == 15492 || hat == 15496 || hat == 15497 || (hat >= 22528 && hat <= 22550);
	}

	public static boolean hasReflectiveEquipment(Entity target) {
		if (!(target instanceof Player))
			return true;
		Player targetPlayer = (Player) target;
		int shieldId = targetPlayer.getEquipment().getShieldId();
		return shieldId == 4156;
	}

	public static boolean hasSpinyHelmet(Entity target) {
		if (!(target instanceof Player))
			return true;
		Player targetPlayer = (Player) target;
		int hat = targetPlayer.getEquipment().getHatId();
		return hat == 4551 || hasSlayerHelmet(target);
	}

	public static boolean isUsingBell(final Player player) {
		player.lock(3);
		player.animate(new Animation(6083));
		List<WorldObject> objects = World.getRegion(player.getRegionId()).getAllObjects();
		if (objects == null)
			return false;
		for (final WorldObject object : objects) {
			if (!object.withinDistance(player, 3) || object.getId() != 22545)
				continue;
			player.getPackets().sendGameMessage("The bell re-sounds loudly throughout the cavern.");
			WorldTasksManager.schedule(new WorldTask() {

				@Override
				public void run() {
					NPC npc = World.spawnNPC(5751, player, -1, true);
					npc.getCombat().setTarget(player);
					WorldObject o = new WorldObject(object);
					o.setId(22544);
					World.spawnObjectTemporary(o, 30000);
				}
			}, 1);
			return true;
		}
		return false;
	}

	public enum SlayerMaster implements Serializable {

		TURAEL(1, 40, 1, new int[] { 10, 20, 60 }, new int[] { 30, 50 }, SlayerTask.ABERRANT_SPECTRE),

		CHELDAER(2, 40, 1, new int[] { 10, 20, 60 }, new int[] { 30, 50 }, SlayerTask.ABERRANT_SPECTRE),

		KURADAL(3, 40, 1, new int[] { 10, 20, 60 }, new int[] { 30, 50 }, SlayerTask.ABERRANT_SPECTRE),

		EXPERT(4, 40, 1, new int[] { 10, 20, 60 }, new int[] { 30, 50 }, SlayerTask.ABERRANT_SPECTRE),

		EASY(1597, 1, 1, new int[] { 15, 50, 100 }, new int[] { 15, 30 }, SlayerTask.ABERRANT_SPECTRE,
				SlayerTask.BASILISK, SlayerTask.COCKATRICE,
				SlayerTask.OGRE, SlayerTask.DUST_DEVIL, SlayerTask.EARTH_WARRIOR,
				SlayerTask.HILL_GIANT, SlayerTask.ICE_GIANT, SlayerTask.ICE_WARRIOR,
				SlayerTask.JELLY, SlayerTask.PYREFIEND, SlayerTask.ROCKSLUG,
				SlayerTask.MOSS_GIANT, SlayerTask.ROCK_CRAB, SlayerTask.YAK, SlayerTask.CHICKEN, SlayerTask.KALPHITE, SlayerTask.BANSHEE, SlayerTask.GHOST, SlayerTask.CAVE_CRAWLER
				, SlayerTask.COW, SlayerTask.GOBLIN, SlayerTask.FLESH_CRAWLER, SlayerTask.MINOTAUR),

		MEDIUM(1598, 50, 30, new int[] { 30, 60, 120 }, new int[] { 25, 50 }, SlayerTask.ABERRANT_SPECTRE,
				SlayerTask.BASILISK, SlayerTask.BLOODVELD, SlayerTask.BLUE_DRAGON, SlayerTask.HELLHOUND,
				SlayerTask.DAGANNOTH, SlayerTask.DUST_DEVIL, SlayerTask.FIRE_GIANT, SlayerTask.GARGOYLE,
				SlayerTask.INFERNAL_MAGE, SlayerTask.OGRE, SlayerTask.JELLY, SlayerTask.KALPHITE, SlayerTask.CYCLOPS,
				SlayerTask.WALLASALKI, SlayerTask.GREEN_DRAGON, SlayerTask.INFERNAL_MAGE, SlayerTask.LESSER_DEMON, SlayerTask.ANKOU, SlayerTask.BLOODVELD, SlayerTask.TZHAAR),

		HARD(9085, 100, 70, new int[] { 50, 100, 150 }, new int[] { 40, 80 }, SlayerTask.ABYSSAL_DEMON,
				SlayerTask.BLACK_DRAGON, SlayerTask.AVIANSIE, SlayerTask.FIRE_GIANT, SlayerTask.BRONZE_DRAGON,
				SlayerTask.GREATER_DEMON, SlayerTask.IRON_DRAGON, SlayerTask.GREEN_DRAGON, SlayerTask.MOSS_GIANT,
				SlayerTask.RED_DRAGON, SlayerTask.STEEL_DRAGON, SlayerTask.BLUE_DRAGON, SlayerTask.BLOODVELD,
				SlayerTask.BLACK_DRAGON, SlayerTask.HELLHOUND, SlayerTask.REVENANT, SlayerTask.WATERFIEND,
				SlayerTask.NECHRYAEL, SlayerTask.ABERRANT_SPECTRE, SlayerTask.DUST_DEVIL, SlayerTask.DARK_BEAST,
				SlayerTask.DAGANNOTH, SlayerTask.GARGOYLE, SlayerTask.ICE_STRYKEWYRM, SlayerTask.MUTATED_JADINKO_MALE, SlayerTask.TZHAAR),

		BOSS(10000, 110, 90, new int[] { 75, 150, 225 }, new int[] { 5, 20 }, SlayerTask.CORPOREAL_BEAST,
				SlayerTask.KALPHITE_QUEEN, SlayerTask.KREE$ARRA, SlayerTask.GENERAL_GRAARDOR,
				SlayerTask.K$RIL_TSUTSAROTH, SlayerTask.COMMANDER_ZILYANA, SlayerTask.KING_BLACK_DRAGON,
				SlayerTask.DAGANNOTH_KINGS, SlayerTask.TORMENTED_DEMON, SlayerTask.HAR¤AKEN, SlayerTask.TZTOK¤JAD);

		private SlayerTask[] task;
		private int[] tasksRange, pointsRange;
		private int requriedCombatLevel, requiredSlayerLevel, npcId;

		private SlayerMaster(int npcId, int requriedCombatLevel, int requiredSlayerLevel, int[] pointsRange,
							 int[] tasksRange, SlayerTask... task) {
			this.npcId = npcId;
			this.requriedCombatLevel = requriedCombatLevel;
			this.requiredSlayerLevel = requiredSlayerLevel;
			this.pointsRange = pointsRange;
			this.tasksRange = tasksRange;
			this.task = task;
		}

		public static boolean startInteractionForId(Player player, int npcId, int option) {
			for (SlayerMaster master : SlayerMaster.values()) {
				if (master.getNPCId() == npcId) {
					if (option == 1)
						player.getDialogueManager().startDialogue("SlayerMasterD", master);
					else if (option == 2)
						player.getDialogueManager().startDialogue("QuickTaskD", master);
					else if (option == 3)
						player.getDialogueManager().startDialogue("SlayerShop");
					else if (option == 4)
						player.getSlayerManager().sendSlayerInterface(SlayerManager.BUY_INTERFACE);
					return true;
				}
			}
			return false;
		}

		public int getNPCId() {
			return npcId;
		}

		public int getRequiredCombatLevel() {
			return requriedCombatLevel;
		}

		public int getRequiredSlayerLevel() {
			return requiredSlayerLevel;
		}

		public SlayerTask[] getTask() {
			return task;
		}

		public int[] getTasksRange() {
			return tasksRange;
		}

		public int[] getPointsRange() {
			return pointsRange;
		}
	}

	public enum SlayerTask implements Serializable {// 79 matches out of 117

		BANSHEE(15, new String[] { "Slayer Tower." }),

		// finished
		BAT(1, new String[] {}),

		// finished
		ROCK_CRAB(1, new String[] { "Rellekka." }),

		// finished
		GIANT_ROCK_CRAB(1, new String[] { " Waterbirth Dungeon, Chaos Tunnels" }),

		AVIANSIE(1, new String[] { "Godwars dungeon." }),

		YAK(1, new String[] {}),

		// finished
		CHICKEN(1, new String[] {}),

		// not needed
		CHOMPY_BIRD(1, new String[] {}),

		// finished
		DUCK(1, new String[] {}),

		// finished
		BIRD(1, new String[] { "Lumbridge river." }, AVIANSIE, CHICKEN, CHOMPY_BIRD, DUCK),

		// finished
		BEAR(1, new String[] {}),

		// finished
		CAVE_BUG(7, new String[] {}),

		// finished
		CAVE_SLIME(17, new String[] {}),

		// finished
		COW(1, new String[] {}),

		// finished
		ZOMBIE_HAND(5, new String[] {}),

		// finished
		SKELETAL_HAND(5, new String[] {}),

		MONEY_GUARD(1, new String[] {}),

		// finished
		CRAWLING_HAND(5, new String[] { "Slayer Tower." }, ZOMBIE_HAND, SKELETAL_HAND),

		// finished
		DWARF(1, new String[] {}),

		// finished
		LIZARD(22, null),

		// finished
		DESERT_LIZARD(22, new String[] {}, LIZARD),

		// finished
		REVENANT(60, new String[] { "Forinthry Dungeon." }),

		REVENANT_ORK(60, new String[] { "Forinthry Dungeon." }, REVENANT),

		// finished
		GHOST(1, new String[] {}, REVENANT),

		// finished
		GOBLIN(1, new String[] {}),

		// finished
		ICEFIEND(1, new String[] {}),

		// finished
		MINOTAUR(1, new String[] {}),

		// finished
		MONKEY(1, new String[] {}, MONEY_GUARD),

		// finished
		SCORPION(1, new String[] {}),

		// finished
		SKELETON(1, new String[] {}, SKELETAL_HAND),

		// finished
		SPIDER(1, new String[] {}),

		// finished
		WOLF(1, new String[] {}),

		// finished
		ZOMBIE(1, new String[] {}),

		// finished
		CATABLEPON(1, new String[] {}),

		// finished
		CAVE_CRAWLER(10, new String[] { "Rellekka Slayer Dungeon." }),

		// finished
		DOG(1, new String[] {}),

		// done
		FLESH_CRAWLER(1, new String[] {}),

		// finished
		HOBGOBLIN(1, new String[] {}),

		// finished
		KALPHITE(1, new String[] { "Kalphite Lair." }),

		// finished
		ROCKSLUG(20, new String[] {}),

		// finished
		ROCK_SLUG(20, new String[] {}, ROCKSLUG),

		// finished
		HOLE_IN_THE_WALL(35, new String[] {}),

		// finished
		WALL_BEAST(35, new String[] {}, HOLE_IN_THE_WALL),

		// finished
		ABERRANT_SPECTRE(60, new String[] { "Slayer Tower." }),

		// finished
		ANKOU(1, new String[] { "Wilderness and Safety dungeon." }),

		// finished
		BASILISK(40, new String[] {}),

		// finished
		BLOODVELD(50, new String[] { "Slayer Tower." }),

		// finished
		BRINE_RAT(47, new String[] {}),

		// finished
		COCKATRICE(25, new String[] { "Rellekka slayer dungeon." }),

		// finished
		CROCODILE(1, new String[] {}),

		// finished
		CYCLOPS(1, new String[] { "Warriors Guild and Godwars Dungeon." }),

		// finished
		CYCLOPSE(1, new String[] { "Warriors Guild and Godwars Dungeon." }, CYCLOPS),

		// finished
		DUST_DEVIL(65, new String[] { "Chaos Tunnels." }),

		// finished
		EARTH_WARRIOR(1, new String[] { "Edgeville Dungeon and Chaos Tunnels." }),

		// finished
		GHOUL(1, new String[] {}),

		// finished
		GREEN_DRAGON(30, new String[] { "Wilderness and Chaos Tunnels." }),

		// finished
		GROTWORM(1, new String[] {}),

		HARPIE_BUG_SWARM(33, new String[] {"Karamja."}),

		// finished
		HILL_GIANT(1, new String[] { "Edgeville Dungeon and Taverly Dungeon." }),

		// finished
		ICE_GIANT(1, new String[] { "Wilderness, Asgarnia Ice Dungeon and Chaos Tunnels." }),

		// finished
		ICE_WARRIOR(1, new String[] { "Wilderness, Asgarnia Ice Dungeon and Chaos Tunnels." }),

		// finished
		INFERNAL_MAGE(45, new String[] { "Slayer Tower and Chaos Tunnels." }),

		// finished
		JELLY(52, new String[] { "Rellekka Slayer Dungeon." }),

		// finished
		JUNGLE_HORROR(1, new String[] {}),

		// finished
		LESSER_DEMON(1, new String[] { "Karamja underground." }),

		// finished
		MOLANISK(39, new String[] {}),

		// finished
		MOSS_GIANT(1, new String[] { "Brimhaven Dungeon and Chaos Tunnels." }),

		// finished
		OGRE(1, new String[] { "East of Castle Wars." }),

		// finished
		OTHERWORLDLY_BEING(1, new String[] {}),

		// finished
		PYREFIEND(30, new String[] { "Rellekka Slayer Dungeon." }),

		// finished
		SHADE(1, new String[] {}),

		// finished
		SHADOW_WARRIOR(1, new String[] {}),

		// finished
		TUROTH(55, new String[] { "Rellekka Slayer Dungeon and Chaos Tunnels." }),

		// finished
		VAMPYRE(1, new String[] {}),

		// finished
		WEREWOLF(1, new String[] {}),

		// finished
		BLUE_DRAGON(1, new String[] { "Taverly Dungeon and Ancient Cavern." }),

		// finished
		BRONZE_DRAGON(1, new String[] { "Brimhaven Dungeon and Chaos Tunnels." }),

		// finished
		CAVE_HORROR(58, new String[] {}),

		DAGANNOTH(1, new String[] { "Waterbirth Dungeon and Chaos Tunnels." }),

		WALLASALKI(1, new String[] { "Waterbirth Dungeon." }),

		CRAWLING_HAND1(5, new String[] { "Slayer Tower." }, ZOMBIE_HAND, SKELETAL_HAND),

		DAGANNOTH_REX(1, new String[] { "Waterbirth Dungeon." }),

		DAGANNOTH_PRIME(1, new String[] { "Waterbirth Dungeon." }),

		DAGANNOTH_SUPREME(1, new String[] { "Waterbirth Dungeon." }),

		DAGANNOTH_KINGS(1, new String[] { "Waterbirth Dungeon." }, DAGANNOTH_REX, DAGANNOTH_PRIME, DAGANNOTH_SUPREME),

		TORMENTED_DEMON(1, new String[] { "Ancient Guthix Temple." }),

		ELF_WARRIOR(1, new String[] {}),

		// completed
		FEVER_SPIDER(42, new String[] {}),

		// completed
		FIRE_GIANT(1, new String[] { "Brimhaven Dungeon and Chaos Tunnels." }),

		FUNGAL_MAGE(1, new String[] {}),

		MUTATED_JADINKO_MALE(80, new String[] { "Jandiko cave" }),

		// finished
		GARGOYLE(75, new String[] { "Slayer Tower, Ancient Cavern and Chaos Tunnels." }),

		// finished
		GRIFOLAPINE(88, new String[] {}),

		// finished
		GRIFOLAROO(82, new String[] {}),

		GRIFALOPINE(88, new String[] {}, GRIFOLAPINE),

		GRIFALOROO(82, new String[] {}, GRIFOLAROO),

		// finished
		JUNGLE_STRYKEWYRM(73, new String[] { "North of Mobilising Armies." }),

		// finished
		KURASK(70, new String[] {}),

		// finished
		FUNGI(57, new String[] {}),

		// finished
		ZYGOMITE(57, new String[] {}, FUNGI),

		VYRELORD(31, new String[] {}), VYRELADY(31, new String[] {}), VYREWATCH(31, new String[] {}, VYRELORD,
				VYRELADY),

		// finished
		WARPED_TORTOISE(56, new String[] {}),

		// finished
		ABYSSAL_DEMON(85, new String[] { "Slayer Tower and Ancient Cavern." }),

		// finished
		AQUANITE(78, new String[] {}),

		// finished
		BLACK_DEMON(1, new String[] { "Edgeville Dungeon, Taverly Dungeon and Chaos Tunnels." }),

		// finished
		DESERT_STRYKEWYRM(77, new String[] { "Al-kharid." }),

		// finished
		GREATER_DEMON(1,
				new String[] { "Wilderness, Forinthry Dungeon, Brimhaven Dungeon and Ancient Cavern." }),

		// finished
		HELLHOUND(1, new String[] {
				"Wilderness, Forinthry Dungeon, Brimhaven Dungeon and Ancient Cavern." }),

		// finished
		IRON_DRAGON(1, new String[] { "Brimhaven Dungeon and Ancient Cavern." }),

		JADINKO(91, new String[] {"Jadinko's lair."}),

		// finished
		NECHRYAEL(80, new String[] { "Slayer Tower and Chaos Tunnels." }),

		// finished
		RED_DRAGON(1, new String[] { "Wilderness and Brimhaven Dungeon." }),

		LOCUST(1, new String[] {}), SCABARAS(1, new String[] {}), SCARAB(1, new String[] {}), SCABARITE(1,
				new String[] {}, LOCUST, SCABARAS, SCARAB),

		// finished
		SPIRITUAL_MAGE(83, new String[] { "Godwars Dungeon and Chaos Tunnels." }),

		// finished
		SPIRITUAL_WARRIOR(68, new String[] { "Godwars Dungeon and Chaos Tunnels." }),

		// finished
		TERROR_DOG(40, new String[] {}),

		// a stupid troll at death plateau
		ROCK(1, new String[] {}), TROLL(1, new String[] {}, ROCK),

		// finished
		BLACK_DRAGON(1, new String[] { "Taverly Dungeon and Chaos Tunnels." }),

		// finished
		DARK_BEAST(90, new String[] { "Slayer Tower and Ancient Cavern." }),

		// finished
		GANODERMIC(95, new String[] { "Polypore dungeon."}),

		// finished
		GORAK(1, new String[] {}),

		ICE_STRYKEWYRM(93, new String[] { "Ice strykewyrm Cavern" }),

		// finished
		MITHRIL_DRAGON(1, new String[] { "Ancient Cavern." }),

		// finished
		SKELETAL_WYVERN(72, new String[] { "Asgarnian Ice Dungeon." }),

		// finished
		STEEL_DRAGON(1, new String[] { "Brimhaven Dungeon and Ancient Cavern." }),

		// finished
		SUQAH(1, new String[] {}),

		// finished
		WARPED_TERRORBIRD(56, new String[] {}),

		// finished
		WATERFIEND(1, new String[] { "Ancient Cavern." }),

		// finished
		LIVING_ROCK(1, new String[] {}),

		// finished
		TZHAAR(1, new String[] { "Tzhaar City." }),

		// finished
		CORPOREAL_BEAST(1, new String[] { "Corporeal Beast Dungeon." }),

		// finished
		TZTOK¤JAD(1, new String[] { "Fight caves." }),

		// finished
		HAR¤AKEN(1, new String[] { "Fight kiln." }),

		// finished
		KREE$ARRA(1, new String[] { "Godwars Dungeon." }),

		// finished
		GENERAL_GRAARDOR(1, new String[] { "Godwars Dungeon." }),

		// finished
		KALPHITE_QUEEN(1, new String[] { "Kalphite Lair." }),

		// finished
		K$RIL_TSUTSAROTH(1, new String[] { "Godwars Dungeon." }),

		// finished
		COMMANDER_ZILYANA(1, new String[] { "Godwars Dungeon." }),

		// finished
		KING_BLACK_DRAGON(1, new String[] { "KBD Lair." });

		private String[] tips;
		private SlayerTask[] alternatives;
		private int levelRequried;

		private SlayerTask(int levelRequried, String[] tips, SlayerTask... alternatives) {
			this.levelRequried = levelRequried;
			this.tips = tips;
			this.alternatives = alternatives;
		}

		public String[] getTips() {
			return tips;
		}

		public SlayerTask[] getAlternatives() {
			return alternatives;
		}

		public int getLevelRequried() {
			return levelRequried;
		}

		public String getName() {
			return Utils.formatPlayerNameForDisplay(toString());
		}
	}
}
