package com.rs.game.player.content;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.combat.Magic;

public class GrotwormLair {

	private transient Player player;

	public void setPlayer(Player player) {
		this.player = player;
	}

	public static boolean handleObject1(WorldObject object, Player player) {
		// queen lair entrance (Summoning Portal)
		if (object.getId() == 70812) {
			player.getPackets()
					.sendGameMessage("The portal emanates a strange aura... You feel like you should go inside...");
			return true;
		}

		if (object.getId() == 70813) {
			Magic.sendObjectTeleportSpell(player, false, new WorldTile(1199, 6499, 0));
			return true;
		}
		return false;
	}

	public static boolean handleObject2(WorldObject object, Player player) {
		// queen lair entrance (Summoning Portal)
		if (object.getId() == 70812) {
			player.grotwormLair().checkRequirements();
			return true;
		}
		return false;
	}

	public static boolean handleObject3(WorldObject object, Player player) {
		// queen lair entrance (Summoning Portal)
		if (object.getId() == 70812) {
			//System.out.println("Handled Option 3 in Grotworm Lair");
			return true;
		}
		return false;
	}

	public static boolean handleObject4(WorldObject object, Player player) {
		// queen lair entrance (Summoning Portal)
		if (object.getId() == 70812) {
			//System.out.println("Handled Option 4 in Grotworm Lair");
			return true;
		}
		return false;
	}

	private boolean checkRequirements() {
		if (player.getSkills().getLevel(Skills.SUMMONING) < 60) {
			player.getDialogueManager().startDialogue("SimpleMessage",
					"You must have a summoning level of 60 to enter the QBD Lair."
							+ "<br> Come back when you are strong enough.");
			return false;
		}
		player.getControlerManager().startControler("QueenBlackDragonControler");
		return true;
	}
}
