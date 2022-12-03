package com.rs.game.player.content;

import com.rs.game.Animation;
import com.rs.game.player.Player;

public class Bell {

	public static void play(Player player) {
		player.animate(new Animation(6083));
	}

}
