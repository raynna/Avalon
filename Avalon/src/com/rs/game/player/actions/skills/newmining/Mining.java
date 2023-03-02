package com.rs.game.player.actions.skills.newmining;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.game.player.actions.skills.newmining.GoldenMiningOutfit.Pieces;
import com.rs.game.player.actions.skills.newmining.Ores.OreStore;
import com.rs.game.player.actions.skills.newmining.Pickaxes.PickaxeStore;
import com.rs.game.player.content.tasksystem.TaskManager.Tasks;
import com.rs.utils.Utils;

public class Mining extends Action {

	OreStore[] oreData = OreStore.values();
	PickaxeStore[] pickaxeData = PickaxeStore.values();

	private WorldObject rock;
	private OreStore realOre;
	private PickaxeStore realPickaxe;

	public Mining(WorldObject rock) {
		this.rock = rock;
	}

	public PickaxeStore getPickaxe(Player player) {
		for (PickaxeStore pickaxe : pickaxeData) {
			if (pickaxe != null) {
				if (player.getInventory().containsOneItem(pickaxe.getPickAxeId())
						|| player.getEquipment().getWeaponId() == pickaxe.getPickAxeId()) {
					if (player.getSkills().getLevelForXp(Skills.MINING) >= pickaxe.getLevelRequried())
						return pickaxe;

				}
			}
		}
		return null;
	}

	public OreStore getOre(Player player) {
		for (OreStore ore : oreData) {
			if (ore != null) {
				if (rock.getId() == ore.objectId) {
					return ore;
				}
			}
		}
		return null;
	}

	@Override
	public boolean start(Player player) {
		realOre = getOre(player);
		realPickaxe = getPickaxe(player);
		if (!checkAll(player))
			return false;
		player.getPackets().sendGameMessage("You swing your pickaxe at the rock.", true);
		setActionDelay(player, getMiningDelay(player));
		return true;
	}

	private int getMiningDelay(Player player) {
		int summoningBonus = 0;
		if (player.getFamiliar() != null) {
			if (player.getFamiliar().getId() == 7342 || player.getFamiliar().getId() == 7342)
				summoningBonus += 10;
			else if (player.getFamiliar().getId() == 6832 || player.getFamiliar().getId() == 6831)
				summoningBonus += 1;
		}
		int mineTimer = realOre.oreBaseTime - (player.getSkills().getLevel(Skills.MINING) + summoningBonus)
				- Utils.getRandom(realPickaxe.getPickAxeTime());
		if (mineTimer < 1 + Utils.getRandom(realOre.oreRandomTime))
			mineTimer = 1 + Utils.getRandom(realOre.oreRandomTime);
		mineTimer /= player.getAuraManager().getMininingAccurayMultiplier();
		return mineTimer;
	}

	public boolean checkAll(Player player) {
		if (realOre == null) {
			return false;
		}
		if (realPickaxe == null) {
			player.getPackets().sendGameMessage("You need a pickaxe to mine this rock.");
			return false;
		}
		if (player.getSkills().getLevel(Skills.MINING) < realOre.level) {
			String oreName = realOre.name().toLowerCase().replace('_', ' ').replace("2", "").replace("3", "")
					.replace("4", "").replace("5", "").replace("6", "").replace("lrc_", "");
			player.getPackets()
					.sendGameMessage("You need a mining level of " + realOre.level + " to mine " + oreName + ".");
			return false;
		}
		if (!player.getInventory().hasFreeSlots()) {
			player.animate(new Animation(-1));
			player.getPackets().sendGameMessage("You don't have any inventory spaces left.");
			return false;
		}
		return true;
	}

	@Override
	public boolean process(Player player) {
		if (!checkAll(player)) {
			return false;
		}
		player.animate(new Animation(realPickaxe.getAnimationId()));
		return checkRock(player);
	}

	private boolean checkRock(Player player) {
		return World.containsObjectWithId(rock, rock.getId());
	}

	@Override
	public int processWithDelay(Player player) {
		addOre(player);
		player.animate(new Animation(-1));
		if (Utils.getRandom(realOre.randomLife) == 0) {
			WorldObject emptySpot = new WorldObject(realOre.emptySpot, rock.getType(), rock.getRotation(), rock);
			World.spawnObjectTemporary(emptySpot, realOre.respawnDelay * 600);
			if (realOre.emptySpot == 4028 || realOre.emptySpot == 4029 || realOre.emptySpot == 4030) {
				player.animate(new Animation(-1));
				player.getActionManager().setAction(new Mining(rock));
				return getMiningDelay(player);
			}
			return -1;
		}
		return getMiningDelay(player);
	}

	private Gems calculateGem(boolean gemRock) {
		while (true) {
			double chance = Utils.getRandomDouble(100);
			double chanceBoost = !gemRock ? 15.0 : 0.0;
			Gems gem = Gems.values()[Utils.getRandom(Gems.values().length - 1)];
			if (Settings.DEBUG)
				System.out
						.println(
								ItemDefinitions.getItemDefinitions(gem.getItemId()).getName() + ", gemRockOnly?: "
										+ gem.isGemRockOnly() + ", chance: " + (gem.getChance() + chanceBoost)
										+ "%, roll: " + chance + " "
										+ ((gem.getChance() + chanceBoost) >= chance ? (!gemRock && gem.isGemRockOnly()
												? "success, but gemrockonly gem, reroll"
												: "success!") : "failed, reroll!"));
			if (!gemRock && gem.isGemRockOnly())
				continue;
			if ((gem.getChance() + chanceBoost) >= chance)
				return gem;
			else
				continue;
		}
	}

	public enum Gems {

		OPAL(1625, 46.86, true),

		JADE(1627, 23.26, true),

		RED_TOPAZ(1629, 11.76, true),

		SAPPHIRE(1623, 7.14, false),

		EMERALD(1621, 3.94, false),

		RUBY(1619, 3.89, false),

		DIAMOND(1617, 3.15, false),

		DRAGONSTONE(1631, 1.45, false),

		ONYX(6571, 0.10, true),

		;

		private int itemId;

		private double chance;

		private boolean gemRockOnly;

		private Gems(int itemId, double chance, boolean gemRockOnly) {
			this.itemId = itemId;
			this.chance = chance;
			this.gemRockOnly = gemRockOnly;
		}

		public int getItemId() {
			return itemId;
		}

		public boolean isGemRockOnly() {
			return gemRockOnly;
		}

		public void setItemId(int itemId) {
			this.itemId = itemId;
		}

		public double getChance() {
			return chance;
		}

		public void setChance(double chance) {
			this.chance = chance;
		}

	}

	public enum VarrockArmours {

		VARROCK_ARMOUR_1(11756, 30, 8),

		VARROCK_ARMOUR_2(11757, 55, 10),

		VARROCK_ARMOUR_3(11758, 70, 12),

		VARROCK_ARMOUR_4(19757, 70, 14);

		private int itemId;
		private int levelCap;
		private double chance;

		private VarrockArmours(int itemId, int levelCap, double chance) {
			this.itemId = itemId;
			this.levelCap = levelCap;
			this.chance = chance;
		}

		public static VarrockArmours getArmour(int i) {
			for (VarrockArmours a : VarrockArmours.values()) {
				if (a.itemId == i)
					return a;
			}
			return null;
		}
	}

	private double getMiningBoost(Player player) {
		double boost = 1.0;
		for (Pieces pieces : GoldenMiningOutfit.data) {
			if (player.getEquipment().containsOneItem(pieces.getItemId()))
				boost += 0.01;
		}
		if (hasMiningSuit(player))
			boost += 0.01;
		return boost;
	}

	private boolean hasMiningSuit(Player player) {
		boolean hasSet = true;
		for (Pieces pieces : GoldenMiningOutfit.data) {
			if (!player.getEquipment().containsOneItem(pieces.getItemId()))
				hasSet = false;
		}
		return hasSet;
	}

	public void addOre(Player player) {
		double totalXp = realOre.xp;
		if (realOre.oreId == 1625) {
			Gems gem = calculateGem(true);
			player.addItem(gem.getItemId(), 1);
		} else {
			player.addItem(realOre.oreId, 1);
			GoldenMiningOutfit.addPiece(player);
			VarrockArmours a = VarrockArmours.getArmour(player.getEquipment().getChestId());
			if (a != null) {
				if (Settings.DEBUG)
					System.out.println(ItemDefinitions.getItemDefinitions(a.itemId).getName() + ", Max level ore: "
							+ a.levelCap + ", Chance: " + a.chance + "%");
				if (Utils.getRandomDouble(1) <= a.chance && realOre.level <= a.levelCap) {
					player.addItemDrop(realOre.oreId, 1);
					totalXp += realOre.xp;
					player.addXp(Skills.MINING, realOre.xp);
					player.message("The Varrock armour allows you to mine an additional ore.");
				}
			}
			if (Utils.getRandomDouble(100) <= 5.0) {
				Gems gem = calculateGem(false);
				player.addItemDrop(gem.getItemId(), 1);
				player.message("You find a " + ItemDefinitions.getItemDefinitions(gem.getItemId()).getName()
						+ " inside the rock.");
			}
		}
		totalXp *= getMiningBoost(player);
		player.getSkills().addSkillingXp(Skills.MINING, totalXp, getMiningBoost(player));
		if (realOre.oreId == 438 || realOre.oreId == 436)
			player.getTaskManager().checkComplete(Tasks.MINE_COPPER_AND_TIN);
		if (realOre.oreId == 447)
			player.getTaskManager().checkComplete(Tasks.MINE_MITHRIL_ORE);
		if (realOre.oreId == 449)
			player.getTaskManager().checkComplete(Tasks.MINE_ADAMANT_ORE);
		if (realOre.oreId == 451)
			player.getTaskManager().checkComplete(Tasks.MINE_RUNITE_ORE);
	}

	@Override
	public void stop(Player player) {
	}

}
