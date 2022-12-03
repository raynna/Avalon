package com.rs.game.player.actions.skills.mining;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.Utils;

public class DungeoneeringMining extends MiningBase {

	public static enum DungeoneeringRocks {
		NOVITE_ORE(1, 15, 17630, 10, 1),

		BATHUS_ORE(10, 27.5, 17632, 15, 1),

		MARMAROS_ORE(20, 41, 17634, 25, 1),

		KRATONIUM_ORE(30, 56.5, 17636, 50, 5),

		FRACTITE_ORE(40, 71, 17638, 80, 10),

		ZEPHYRIUM_ORE(50, 85, 17640, 95, 10),

		AGRONITE_ORE(60, 100.5, 17642, 100, 15),

		KATAGON_ORE(70, 117, 17644, 110, 20),

		GORGONITE_ORE(80, 131, 17646, 123, 22),

		PROMETHIUM_ORE(90, 148, 17648, 130, 25);

		private int level;
		private double xp;
		private int oreId;
		private int oreBaseTime;
		private int oreRandomTime;

		private DungeoneeringRocks(int level, double xp, int oreId, int oreBaseTime, int oreRandomTime) {
			this.level = level;
			this.xp = xp;
			this.oreId = oreId;
			this.oreBaseTime = oreBaseTime;
			this.oreRandomTime = oreRandomTime;
		}

		public int getLevel() {
			return level;
		}

		public double getXp() {
			return xp;
		}

		public int getOreId() {
			return oreId;
		}

		public int getOreBaseTime() {
			return oreBaseTime;
		}

		public int getOreRandomTime() {
			return oreRandomTime;
		}

	}

	private WorldObject rock;
	private DungeoneeringRocks definitions;
	private PickAxeDefinitions axeDefinitions;

	public DungeoneeringMining(WorldObject rock, DungeoneeringRocks definitions) {
		this.rock = rock;
		this.definitions = definitions;
	}

	@Override
	public boolean start(Player player) {
		axeDefinitions = getPickAxeDefinitions(player);
		if (!checkAll(player))
			return false;
		player.getPackets().sendGameMessage("You swing your pickaxe at the rock.");
		setActionDelay(player, getMiningDelay(player));
		return true;
	}

	private int getMiningDelay(Player player) {
		int mineTimer = definitions.getOreBaseTime() - player.getSkills().getLevel(Skills.MINING) - Utils.random(axeDefinitions.getPickAxeTime());
		if (mineTimer < 1 + definitions.getOreRandomTime())
			mineTimer = 1 + Utils.random(definitions.getOreRandomTime());
		mineTimer /= player.getAuraManager().getMininingAccurayMultiplier();
		return mineTimer;
	}

	private boolean checkAll(Player player) {
		if (axeDefinitions == null) {
			player.getPackets().sendGameMessage("You do not have a pickaxe or do not have the required level to use the pickaxe.");
			return false;
		}
		if (!hasMiningLevel(player))
			return false;
		if (!player.getInventory().hasFreeSlots()) {
			player.getPackets().sendGameMessage("Not enough space in your inventory.");
			return false;
		}
		return true;
	}

	private boolean hasMiningLevel(Player player) {
		if (definitions.getLevel() > player.getSkills().getLevel(Skills.MINING)) {
			player.getPackets().sendGameMessage("You need a mining level of " + definitions.getLevel() + " to mine this rock.");
			return false;
		}
		return true;
	}

	@Override
	public boolean process(Player player) {
		player.animate(new Animation(axeDefinitions.getAnimationId()));
		return checkRock(player);
	}

	@Override
	public int processWithDelay(Player player) {
		addOre(player);
		if (Utils.random(5) == 0) {
			World.spawnObject(new WorldObject(rock.getId() + 1, rock.getType(), rock.getRotation(), rock.getX(), rock.getY(), rock.getPlane()));
			player.getPackets().sendGameMessage("You have depleted this resource.");
			player.animate(new Animation(-1));
			return -1;
		}
		if (!player.getInventory().hasFreeSlots() && definitions.getOreId() != -1) {
			player.animate(new Animation(-1));
			player.getPackets().sendGameMessage("Not enough space in your inventory.");
			return -1;
		}
		return getMiningDelay(player);
	}

	private void addOre(Player player) {
		double xpBoost = 1.0;
		player.getSkills().addXp(Skills.MINING, definitions.getXp() * xpBoost);
		player.getInventory().addItem(definitions.getOreId(), 1);
		String oreName = ItemDefinitions.getItemDefinitions(definitions.getOreId()).getName().toLowerCase();
		player.getPackets().sendGameMessage("You mine some " + oreName + ".", true);
	}

	private boolean checkRock(Player player) {
		return World.containsObjectWithId(rock, rock.getId());
	}
}
