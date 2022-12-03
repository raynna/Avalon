package com.rs.game.player.dialogues.skilling;

import com.rs.game.player.actions.skills.construction.House.RoomReference;
import com.rs.game.player.actions.skills.construction.HouseConstants.Builds;
import com.rs.game.player.actions.skills.construction.HouseConstants.Room;
import com.rs.game.player.dialogues.Dialogue;

public class CreateRoomStairsD extends Dialogue {

	private RoomReference room;
	private boolean up;
	private boolean dungeonEntrance;

	@Override
	public void start() {
		this.room = (RoomReference) parameters[0];
		up = (boolean) parameters[1];
		dungeonEntrance = (boolean) parameters[2];
		sendOptionsDialogue((dungeonEntrance ? "This entrance does" : "These stairs do") + " not lead anywhere. Do you want to build a room at the " + (up ? "top" : "bottom") + "?", "Yes.", "No.");

	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (componentId == OPTION_1) {
				stage = 0;
				if (room.getPlane() == 1 && !up)
					sendOptionsDialogue("Select an option", "Skill hall", "Quest hall", "Dungeon stairs room");
				else
					sendOptionsDialogue("Select an option", "Skill hall", "Quest hall");
				return;
			}
		} else {
			Room r = room.getPlane() == 1 && !up && componentId == OPTION_3 ? Room.DUNGEON_STAIRS : componentId == OPTION_2 ? up ? Room.HALL_QUEST_DOWN : Room.HALL_QUEST : up ? Room.HALL_SKILL_DOWN : Room.HALL_SKILL;
			Builds stair = (room.getPlane() == 1 && !up && componentId == OPTION_3) ? Builds.STAIRCASE : componentId == OPTION_2 ? up ? Builds.STAIRCASE_DOWN_1 : Builds.STAIRCASE_1 : up ? Builds.STAIRCASE_DOWN : Builds.STAIRCASE;
			RoomReference newRoom = new RoomReference(r, room.getX(), room.getY(), room.getPlane() + (up ? 1 : -1), room.getRotation());
			int slot = dungeonEntrance ? 0 : room.getStaircaseSlot();
			if (slot != -1) {
				newRoom.addObject(stair, slot);
				player.getHouse().createRoom(newRoom);
			}
		}
		end();

	}

	@Override
	public void finish() {

	}

}
