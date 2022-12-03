package com.rs.game.player.content.dungeoneering;

import com.rs.game.player.Player;

public class DungeonPartyPlayer {

    private transient Player player;
    private int deaths;

    public DungeonPartyPlayer(Player player) {
	this.player = player;
    }

    public Player getPlayer() {
	return player;
    }

    public void refreshDeaths() {
	player.getVarsManager().sendVarBit(7554, deaths);
    }

    public void increaseDeaths() {
	if (deaths == 15)
	    return;
	deaths++;
	refreshDeaths();
    }
}
