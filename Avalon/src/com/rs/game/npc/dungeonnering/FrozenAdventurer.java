package com.rs.game.npc.dungeonnering;

import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;

@SuppressWarnings("serial")
public class FrozenAdventurer extends NPC {

	private transient Player player;

	public FrozenAdventurer(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
		super(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, true);
	}

	@Override
	public void processNPC() {
		if (player == null || player.isDead() || player.hasFinished()) {
			finish();
			return;
		} else if (!player.getAppearence().isNPC()) {
			//TODO ToKashBloodChillerCombat.removeSpecialFreeze(player);
			finish();
			return;
		}
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getFrozenPlayer() {
		return player;
	}

}
