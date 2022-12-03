package com.rs.game.player.actions.skills.runecrafting;

import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public class Talisman extends Runecrafting {
	
	public static void locate(Player p, int xPos, int yPos) {
		String x = "";
		String y = "";
		int absX = p.getX();
		int absY = p.getY();
		if (absX >= xPos)
			x = "West";
		if (absY > yPos)
			y = "South";
		if (absX < xPos)
			x = "East";
		if (absY <= yPos)
			y = "North";
		if (Utils.inCircle(new WorldTile(p.getX(), p.getY(), p.getPlane()), new WorldTile(xPos, yPos, p.getPlane()),
				5)) {
			p.getPackets().sendGameMessage("You are right next to the altar.");
			return;
		}
		p.getPackets().sendGameMessage("The talisman pulls towards " + y + "-" + x + ".", false);
	}

}
