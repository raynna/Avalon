package com.rs.game.npc.familiar;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.summoning.Summoning.Pouch;
import com.rs.utils.Utils;

public class Wolpertinger extends Familiar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4097036858996221680L;

	public Wolpertinger(Player owner, Pouch pouch, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea) {
		super(owner, pouch, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
	}

	@Override
	public String getSpecialName() {
		return "Magic Focus";
	}

	@Override
	public String getSpecialDescription() {
		return "Boosts your restistance towards magic by 5% while also boosting your magic by 7%.";
	}

	@Override
	public int getBOBSize() {
		return 0;
	}

	@Override
	public int getSpecialAmount() {
		return 12;
	}

	@Override
	public SpecialAttack getSpecialAttack() {
		return SpecialAttack.CLICK;
	}

	@Override
	public boolean submitSpecial(Object object) {
		Player player = (Player) object;
		if (player.getFamiliarDelay() > Utils.currentTimeMillis())
			return false;
		long familiarDelay = 3000;
		player.addFamiliarDelay(familiarDelay);
		int newLevel = player.getSkills().getLevel(Skills.MAGIC) + 7;
		if (newLevel > player.getSkills().getLevelForXp(Skills.MAGIC) + 7)
			newLevel = player.getSkills().getLevelForXp(Skills.MAGIC) + 7;
		player.gfx(new Graphics(1300));
		player.animate(new Animation(7660));
		animate(new Animation(8308));
		gfx(new Graphics(1464));
		player.getSkills().set(Skills.MAGIC, newLevel);
		player.getSkills().addXp(Skills.SUMMONING, 1.22);
		player.getInventory().deleteItem(player.getFamiliarScroll(), 1);
		return true;
	}
}
