package com.rs.game.player.content;

import java.io.Serializable;

import com.rs.game.player.Player;

public class ClaimUntradeables implements Serializable {

	private static final long serialVersionUID = -3847090682601697992L;

	private transient Player player;

	public ClaimUntradeables(Player player) {
		this.setPlayer(player);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
}
