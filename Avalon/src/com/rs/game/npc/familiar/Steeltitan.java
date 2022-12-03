package com.rs.game.npc.familiar;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.summoning.Summoning.Pouch;

public class Steeltitan extends Familiar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6377458256826528627L;

	public Steeltitan(Player owner, Pouch pouch, WorldTile tile, int mapAreaNameHash,
			boolean canBeAttackFromOutOfArea) {
		super(owner, pouch, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
		animate(new Animation(8188));
	}

	@Override
	public int getSpecialAmount() {
		return 12;
	}

	@Override
	public String getSpecialName() {
		return "Steel of Legends";
	}

	@Override
	public String getSpecialDescription() {
		return "Defence boost only applies to melee attacks. Scroll initiates attack on opponent, hitting four times, with either ranged or melee, depending on the distance to the target";
	}

	@Override
	public SpecialAttack getSpecialAttack() {
		return SpecialAttack.CLICK;
	}

	@Override
	public int getBOBSize() {
		return 0;
	}

	@Override
	public boolean submitSpecial(Object object) {
		if (getOwner().getFamiliar().hasSpecialActivated()) {
			getOwner().getPackets().sendGameMessage("Special attack is already activated.");
			return false;
		}
		if (specialEnergy < getOwner().getFamiliar().getSpecialAmount()) {
			getOwner().getPackets().sendGameMessage("Your special move bar is too low to use this scroll.");
			return false;
		}
		getOwner().gfx(new Graphics(1300));
		getOwner().animate(new Animation(7660));
		getOwner().getSkills().addXp(Skills.SUMMONING, 1.22);
		getOwner().getInventory().deleteItem(getOwner().getFamiliarScroll(), 1);
		return true;
	}
}
