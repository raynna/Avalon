package com.rs.game.player.content.dungeoneering.rooms.puzzles;

import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.WorldObject;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.dungeoneering.DungeonConstants;
import com.rs.game.player.content.dungeoneering.rooms.PuzzleRoom;

public class FremennikCampRoom extends PuzzleRoom {

	public static final int FREMENNIK_SCOUT = 11001;
	private static final int[] RAW_FISH =
	{ 49522, 49523, 49524, 49524, 49524 };
	private static final int[] COOKED_FISH =
	{ 49525, 49526, 49527, 49527, 49527 };
	private static final int[] BARS =
	{ 49528, 49529, 49530, 49530, 49530 };
	private static final int[] BATTLE_AXES =
	{ 49531, 49532, 49533, 49533, 49533 };
	private static final int[] LOGS =
	{ 49534, 49535, 49536, 49536, 49536 };
	private static final int[] BOWS =
	{ 49537, 49538, 49539, 49539, 49539 };

	private int stage = 0;

	@Override
	public void openRoom() {
		manager.spawnNPC(reference, FREMENNIK_SCOUT, 8, 5, false, DungeonConstants.NORMAL_NPC);
	}

	@Override
	public boolean processObjectClick1(Player player, WorldObject object) {
		if (object.getId() == RAW_FISH[type]) {
			if (!hasRequirement(player, Skills.COOKING)) {
				player.getPackets().sendGameMessage("You need a cooking level of " + getRequirement(Skills.COOKING) + " to cook these fish.");
				return false;
			}
			giveXP(player, Skills.COOKING);
			replaceObject(object, COOKED_FISH[type]);
			advance(player);
			player.animate(new Animation(897));
			return false;
		} else if (object.getId() == BARS[type]) {
			if (!hasRequirement(player, Skills.SMITHING)) {
				player.getPackets().sendGameMessage("You need a smithing level of " + getRequirement(Skills.SMITHING) + " to smith these battle axes.");
				return false;
			}
			//TODO if (!player.getInventory().containsItemToolBelt(Smithing.DUNG_HAMMER)) {
			//TODO 	player.getPackets().sendGameMessage("You need a hammer to smith battle axes.");
			//TODO 	return false;
			//TODO }
			giveXP(player, Skills.SMITHING);
			replaceObject(object, BATTLE_AXES[type]);
			advance(player);
			player.animate(new Animation(898));
			player.gfx(new Graphics(2123));
			return false;
		} else if (object.getId() == LOGS[type]) {
			if (!hasRequirement(player, Skills.FLETCHING)) {
				player.getPackets().sendGameMessage("You need a fletching level of " + getRequirement(Skills.FLETCHING) + " to fletch these bows.");
				return false;
			}
			//TODO if (!player.getInventory().containsItemToolBelt(Fletching.DUNGEONEERING_KNIFE)) {
			//TODO 	player.getPackets().sendGameMessage("You need a knife to fletch bows.");
			//TODO 	return false;
			//TODO }
			giveXP(player, Skills.FLETCHING);
			replaceObject(object, BOWS[type]);
			advance(player);
			player.animate(new Animation(1248));
			return false;
		}
		return true;
	}

	public void advance(Player player) {
		if (++stage == 3) {
			setComplete();
			player.getDialogueManager().startDialogue("FremennikScoutD", this);
		}
	}

	@Override
	public boolean processNPCClick1(Player player, NPC npc) {
		if (npc.getId() == FREMENNIK_SCOUT) {
			player.getDialogueManager().startDialogue("FremennikScoutD", this);
			return false;
		}
		return true;
	}

	@Override
	public String getCompleteMessage() {
		return null;
	}

}
