package com.rs.game.player.actions.skills.crafting;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class Enchanting {

	@SuppressWarnings("unused")
	private static final int AIR_RUNE = 556, WATER_RUNE = 555, EARTH_RUNE = 557, FIRE_RUNE = 554, COSMIC_RUNE = 564;

	public enum Types {
		SAPPHIRE(1, 7, 2550, 3853, 1727, 11074), EMERALD(2, 27, 2552, 5521, 1729, 11079), RUBY(3, 49, 2568, 11194, 1725,
				11088), DIAMOND(4, 57, 2570, 11090, 1731,
						11095), DRAGONSTONE(5, 86, 2572, 11105, 1712, 11188), ONYX(6, 87, 6583, 11128, 6585, 11133);

		int enchantLevel, level, ring, necklace, amulet, bracelet;

		Types(int enchantLevel, int level, int ring, int necklace, int amulet, int bracelet) {
			this.enchantLevel = enchantLevel;
			this.level = level;
			this.ring = ring;
			this.necklace = necklace;
			this.amulet = amulet;
			this.bracelet = bracelet;
		}
	}

	public static boolean startEnchant(Player player, int itemId, int slotId, int enchantLevel, double xp) {
		Base base = getBase(enchantLevel);
		if (!canBeEnchanted(base, itemId)) {
			player.getPackets().sendGameMessage("This item can't be enchanted.");
			return false;
		}
		for (Types t : Types.values()) {
			if (enchantLevel == t.enchantLevel) {
				if (player.getSkills().getLevel(Skills.MAGIC) < t.level) {
					player.getPackets().sendGameMessage(
							"You need atleast " + t.level + " to enchant " + t.name().toLowerCase() + "");
					return false;
				}
				if (itemId == base.baseRing)
					return enchant(player, t.level, base.baseRing, t.ring, enchantLevel, slotId, xp);
				if (itemId == base.baseNecklace)
					return enchant(player, t.level, base.baseNecklace, t.necklace, enchantLevel, slotId, xp);
				if (itemId == base.baseAmulet)
					return enchant(player, t.level, base.baseAmulet, t.amulet, enchantLevel, slotId, xp);
				if (itemId == base.baseBracelet)
					return enchant(player, t.level, base.baseBracelet, t.bracelet, enchantLevel, slotId, xp);
			}
		}
		return false;
	}

	public static boolean enchant(Player player, int reqLevel, int toEnchant, int toMake, int enchantLevel, int slotId, double xp) {
		String name = ItemDefinitions.getItemDefinitions(toEnchant).getName();
		String makeName = ItemDefinitions.getItemDefinitions(toMake).getName();
		if (enchantLevel <= 2) {
			player.animate(new Animation(719));
			player.gfx(new Graphics(114, 0, 100));
		} else if (enchantLevel == 3 && enchantLevel == 4) {
			player.animate(new Animation(720));
			player.gfx(new Graphics(115, 0, 100));
		} else if (enchantLevel == 5) {
			player.animate(new Animation(721));
			player.gfx(new Graphics(116, 0, 100));
		} else if (enchantLevel == 6) {
			player.animate(new Animation(721));
			player.gfx(new Graphics(452, 0, 100));
		}
		player.getInventory().deleteItem(slotId, new Item(toEnchant));
		player.getInventory().addItem(toMake, 1);
		player.getSkills().addXp(Skills.MAGIC, (enchantLevel * 10));
		player.getPackets().sendGameMessage("You enchant the " + name + " into a " + makeName + "!");
		return false;
	}

	public enum Base {

		SAPPHIRE(1, 1637, 1656, 1694, 11072), EMERALD(2, 1639, 1658, 1696, 11076), RUBY(3, 1641, 1660, 1698,
				11085), DIAMOND(4, 1643, 1662, 1700,
						11092), DRAGONSTONE(5, 1645, -1, 1702, -1), ONYX(6, 6575, 6577, 6581, 11130);

		int level, baseRing, baseNecklace, baseAmulet, baseBracelet;

		Base(int level, int baseRing, int baseNecklace, int baseAmulet, int baseBracelet) {
			this.level = level;
			this.baseRing = baseRing;
			this.baseNecklace = baseNecklace;
			this.baseAmulet = baseAmulet;
			this.baseBracelet = baseBracelet;
		}

	}

	public static boolean canBeEnchanted(Base b, int itemId) {
		return b.baseAmulet == itemId || b.baseNecklace == itemId || b.baseRing == itemId || b.baseBracelet == itemId;
	}

	public static Base getBase(int type) {
		for (Base t : Base.values()) {
			if (type == t.level) {
				return t;
			}
		}
		return null;
	}

}
