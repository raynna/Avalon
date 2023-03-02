package com.rs.game.npc.familiar;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.fishing.Fishing.Fish;
import com.rs.game.player.actions.skills.summoning.Summoning.Pouch;
import com.rs.game.player.content.Foods.Food;

public class Bunyip extends Familiar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7203440350875823581L;

	public Bunyip(Player owner, Pouch pouch, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		super(owner, pouch, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
	}

	@Override
	public String getSpecialName() {
		return "Swallow Whole";
	}

	@Override
	public String getSpecialDescription() {
		return "Eat an uncooked fish and gain the correct number of life points corresponding to the fish eaten if you have the cooking level to cook the fish.";
	}

	@Override
	public int getBOBSize() {
		return 0;
	}

	@Override
	public int getSpecialAmount() {
		return 7;
	}

	@Override
	public SpecialAttack getSpecialAttack() {
		return SpecialAttack.ITEM;
	}

	private static Fish[] fishData = Fish.values();
	private static Food[] foodData = Food.values();

	@Override
	public boolean submitSpecial(Object object) {
		Item item = getOwner().getInventory().getItem((Integer) object);
		if (item == null)
			return false;
		getOwner().message("item: " + item.getId() + ", " + item.getName());
		boolean canCast = false;
		Fish fish = null;
		for (Fish rawFish : fishData) {
			if (rawFish == null)
				continue;
			if (rawFish.getId() == item.getId()) {
				canCast = true;
				fish = rawFish;
				break;
			}
		}
		if (canCast) {
			if (fish != null) {
				if (getOwner().getSkills().getLevel(Skills.COOKING) < fish.getLevel()) {
					getOwner().getPackets()
							.sendGameMessage("Your cooking level is not high enough for the bunyip to eat this fish.");
					return false;
				} else {
					getOwner().gfx(new Graphics(1316));
					getOwner().animate(new Animation(7660));
					for (Food food : foodData) {
						if (food == null)
							continue;
						if (fish.name().toLowerCase().contains(food.name().toLowerCase())) {
							getOwner().heal(Food.forId(food.getId()).getHeal() * 10);
							break;
						}
					}
					getOwner().getInventory().deleteItem(item.getId(), item.getAmount());
					getOwner().getInventory().deleteItem(getOwner().getFamiliarScroll(), 1);
					return true;// stop here
				}
			}

		} else {
			getOwner().getPackets().sendGameMessage("Your bunyip can only consume raw fish.");
		}
		return false;
	}
}
