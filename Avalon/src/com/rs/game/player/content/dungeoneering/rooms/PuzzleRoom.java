package com.rs.game.player.content.dungeoneering.rooms;

import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.dungeoneering.VisibleRoom;
import com.rs.utils.Utils;

public abstract class PuzzleRoom extends VisibleRoom {

	private boolean complete;
	private int[] requirements = new int[25];
	private int[] giveXPCount = new int[25];

	public final boolean hasRequirement(Player p, int skill) {
		return p.getSkills().getLevel(skill) >= getRequirement(skill);
	}

	public final int getRequirement(int skill) {
		setLevel(skill);
		return requirements[skill];
	}

	public final void giveXP(Player player, int skill) {
		if (giveXPCount[skill] < 4) {
			//You only gain xp for the first 4 times you do an action
			giveXPCount[skill]++;
			player.getSkills().addXp(skill, getRequirement(skill) * 5 +10);
		}
	}

	private void setLevel(int skill) {
		if (requirements[skill] == 0)
			requirements[skill] = !manager.getRoom(reference).isCritPath() ? Utils.random(30, skill == Skills.SUMMONING || skill == Skills.PRAYER ? 100 : 106) : Math.max(1, (manager.getParty().getMaxLevel(skill) - Utils.random(10)));
	}

	public void replaceObject(WorldObject object, int newId) {
		if(object == null)
			return;
		WorldObject newObject = new WorldObject(object);
		newObject.setId(newId);
		World.spawnObject(newObject);
	}

	public boolean isComplete() {
		return complete;
	}

	public void setComplete() {
		this.complete = true;
		if (getCompleteMessage() != null)
			manager.message(reference, getCompleteMessage());
		manager.getRoom(reference).removeChallengeDoors();
	}

	public String getCompleteMessage() {
		return "You hear a clunk as the doors unlock.";
	}

	public String getLockMessage() {
		return "The door is locked. You can't see any obvious keyhole or mechanism.";
	}

}
