package com.rs.game.npc.familiar;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.actions.skills.summoning.Summoning.Pouch;
import com.rs.utils.Utils;

public class Packyak extends Familiar {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1397015887332756680L;

	public Packyak(Player owner, Pouch pouch, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		super(owner, pouch, tile, mapAreaNameHash, false);
	}

	@Override
	public int getSpecialAmount() {
		return 12;
	}

	@Override
	public String getSpecialName() {
		return "Winter Storage";
	}

	@Override
	public String getSpecialDescription() {
		return "Use special move on an item in your inventory to send it to your bank.";
	}

	@Override
	public SpecialAttack getSpecialAttack() {
		return SpecialAttack.ITEM;
	}

	@Override
	public int getBOBSize() {
		return 30;
	}

	@Override
	public boolean isAgressive() {
		return false;
	}

	@Override
	public boolean submitSpecial(Object object) {
		if (getOwner().getFamiliarDelay() > Utils.currentTimeMillis()) {
			getOwner().getPackets().sendGameMessage("You cant use this yet.");
			return false;
		}
		int slotId = (Integer) object;
		if (getOwner().getBank().hasBankSpace()) {
			getOwner().getBank().depositItem(slotId, 1, true);
			getOwner().getPackets().sendGameMessage("Your Pack Yak has sent an item to your bank.");
			getOwner().gfx(new Graphics(1316));
			getOwner().animate(new Animation(7660));
			long familiarDelay = 3000;
			getOwner().addFamiliarDelay(familiarDelay);
		}
		return true;
	}
}
