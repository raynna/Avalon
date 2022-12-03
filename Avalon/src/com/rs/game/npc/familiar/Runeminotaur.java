package com.rs.game.npc.familiar;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.actions.skills.summoning.Summoning.Pouch;

public class Runeminotaur extends Familiar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4657392160246588028L;

	public Runeminotaur(Player owner, Pouch pouch, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea) {
		super(owner, pouch, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
	}

	@Override
	public String getSpecialName() {
		return "special name";
	}

	@Override
	public String getSpecialDescription() {
		return "special description.";
	}

	@Override
	public int getBOBSize() {
		return 0;
	}

	@Override
	public int getSpecialAmount() {
		return 6;
	}

	@Override
	public SpecialAttack getSpecialAttack() {
		return SpecialAttack.ENTITY;
	}

	@Override
	public boolean submitSpecial(Object object) {
		Player player = (Player) object;
		player.gfx(new Graphics(1316));
		player.animate(new Animation(7660));
		return true;
	}
}
