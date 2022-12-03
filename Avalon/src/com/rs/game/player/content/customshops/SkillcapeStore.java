package com.rs.game.player.content.customshops;

import java.util.ArrayList;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

public class SkillcapeStore extends CustomStore {
	
	/**
	 * @Author -Andreas
	 * 2019-11
	 */

	public SkillcapeStore(Player player) {
		super(player);
	}

	protected static final int CURRENCY_SPRITE = 1371;

	protected static String TITLE = "Max' Skillcape Store";
	
	
	protected static ArrayList<Item> capes = new ArrayList<Item>();
	
	protected static void generateCapes(Player player) {
		capes.clear();
		capes.trimToSize();
		if (player.getSkills().getLevelForXp(Skills.ATTACK) == 99)
			capes.add(new Item(9748, 1));
		if (player.getSkills().getLevelForXp(Skills.STRENGTH) == 99)
			capes.add(new Item(9751, 1));
		if (player.getSkills().getLevelForXp(Skills.DEFENCE) == 99)
			capes.add(new Item(9754, 1));
		if (player.getSkills().getLevelForXp(Skills.RANGE) == 99)
			capes.add(new Item(9757, 1));
		if (player.getSkills().getLevelForXp(Skills.PRAYER) == 99)
			capes.add(new Item(9760, 1));
		if (player.getSkills().getLevelForXp(Skills.MAGIC) == 99)
			capes.add(new Item(9763, 1));
		if (player.getSkills().getLevelForXp(Skills.RUNECRAFTING) == 99)
			capes.add(new Item(9766, 1));
		if (player.getSkills().getLevelForXp(Skills.HITPOINTS) == 99)
			capes.add(new Item(9769, 1));
		if (player.getSkills().getLevelForXp(Skills.AGILITY) == 99)
			capes.add(new Item(9772, 1));
		if (player.getSkills().getLevelForXp(Skills.HERBLORE) == 99)
			capes.add(new Item(9775, 1));
		if (player.getSkills().getLevelForXp(Skills.THIEVING) == 99)
			capes.add(new Item(9778, 1));
		if (player.getSkills().getLevelForXp(Skills.CRAFTING) == 99)
			capes.add(new Item(9781, 1));
		if (player.getSkills().getLevelForXp(Skills.FLETCHING) == 99)
			capes.add(new Item(9784, 1));
		if (player.getSkills().getLevelForXp(Skills.SLAYER) == 99)
			capes.add(new Item(9787, 1));
		if (player.getSkills().getLevelForXp(Skills.CONSTRUCTION) == 99)
			capes.add(new Item(9790, 1));
		if (player.getSkills().getLevelForXp(Skills.MINING) == 99)
			capes.add(new Item(9793, 1));
		if (player.getSkills().getLevelForXp(Skills.SMITHING) == 99)
			capes.add(new Item(9796, 1));
		if (player.getSkills().getLevelForXp(Skills.FISHING) == 99)
			capes.add(new Item(9799, 1));
		if (player.getSkills().getLevelForXp(Skills.COOKING) == 99)
			capes.add(new Item(9802, 1));
		if (player.getSkills().getLevelForXp(Skills.FIREMAKING) == 99)
			capes.add(new Item(9805, 1));
		if (player.getSkills().getLevelForXp(Skills.WOODCUTTING) == 99)
			capes.add(new Item(9808, 1));
		if (player.getSkills().getLevelForXp(Skills.FARMING) == 99)
			capes.add(new Item(9811, 1));
		if (player.getSkills().getLevelForXp(Skills.HUNTER) == 99)
			capes.add(new Item(9949, 1));
		if (player.getSkills().getLevelForXp(Skills.SUMMONING) == 99)
			capes.add(new Item(12170, 1));
		if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) >= 99)
			capes.add(new Item(18509, 1));
		if (player.getSkills().getLevelForXp(Skills.DUNGEONEERING) == 120)
			capes.add(new Item(19709, 1));
		if (player.hasMaxCapeRequirements())
			capes.add(new Item(20767, 1));
	}
}
