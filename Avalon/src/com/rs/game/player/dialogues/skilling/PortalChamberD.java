package com.rs.game.player.dialogues.skilling;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.construction.House.ObjectReference;
import com.rs.game.player.actions.skills.construction.House.RoomReference;
import com.rs.game.player.actions.skills.construction.HouseConstants.Builds;
import com.rs.game.player.dialogues.Dialogue;

public class PortalChamberD extends Dialogue {

	private static final int[][] RUNES =
	{
	{ 563, 100, 556, 300, 554, 100 },
	{ 563, 100, 556, 300, 557, 100 },
	{ 563, 100, 556, 300, 555, 100 },
	{ 563, 100, 556, 500 },
	{ 563, 200, 555, 200 },
	{ 563, 200, 557, 200 },
	{ 563, 200, 565, 200 } };

	private static final int[] LEVELS =
	{ 25, 31, 37, 45, 51, 58, 66 };
	private static final double[] EXPERIENCE =
	{ 19, 41, 48, 55.5, 61, 68, 76 };
	private static final String[] OPTION_NAMES =
	{ "Nowhere", "Varrock", "Lumbridge", "Falador", "Camelot", "Ardougne", "Yanille", "Kharyll" };

	private RoomReference rRef;
	private boolean[] existingPortals;
	private int redirectSlot;

	@Override
	public void start() {
		existingPortals = new boolean[3];
		this.rRef = (RoomReference) this.parameters[0];
		for (ObjectReference oRef : rRef.getObjects()) {
			if (oRef.getBuild() == Builds.PORTAL_1)
				existingPortals[0] = true;
			if (oRef.getBuild() == Builds.PORTAL_2)
				existingPortals[1] = true;
			if (oRef.getBuild() == Builds.PORTAL_3)
				existingPortals[2] = true;
		}
		sendDialogue("To direct a portal you ned enough runes for 100 castings of that teleport spell. \n(Combination runes and staffs cannot be used.)");
	}

	@Override
	public void run(int interfaceId, int componentId) {
		if (stage == -1) {
			if (rRef.getDirectedPortals() == null)
				rRef.setDirectedPortals(new byte[3]);
			byte primaryFrame = rRef.getDirectedPortals()[0];
			byte secondaryFrame = rRef.getDirectedPortals()[1];
			byte tertiaryFrame = rRef.getDirectedPortals()[2];
			sendOptionsDialogue("Redirect which portal?", existingPortals[0] ? OPTION_NAMES[primaryFrame] : "No portal frame", existingPortals[1] ? OPTION_NAMES[secondaryFrame] : "No portal frame", existingPortals[2] ? OPTION_NAMES[tertiaryFrame] : "No portal frame", "Cancel");
			stage = 0;
		} else if (stage == 0) {
			if (componentId >= OPTION_1 && componentId <= OPTION_3) {
				redirectSlot = componentId == 11 ? 0 : componentId - 12;
				if (!existingPortals[redirectSlot]) {
					sendDialogue("There must be a portal frame in order for the centerpiece to specify a location.");
					stage = 10;
					return;
				}
				sendOptionsDialogue("Select a desination", "Varrock", "Lumbridge", "Falador", "Camelot", "More");
				stage = 1;
			} else if (componentId == OPTION_4) {
				end();
			}
		} else if (stage == 1) {
			if (componentId >= OPTION_1 && componentId <= OPTION_4) {
				placePortal(componentId == 11 ? 0 : componentId - 12);
			} else if (componentId == OPTION_5) {
				sendOptionsDialogue("Select a desination", "Ardougne", "Yanille", "Kharyll", "Previous");
				stage = 2;
			}
		} else if (stage == 2) {
			if (componentId >= OPTION_1 && componentId <= OPTION_3) {
				placePortal(4 + (componentId == 11 ? 0 : componentId - 12));
			} else if (componentId == OPTION_4) {
				sendOptionsDialogue("Select a desination", "Varrock", "Lumbridge", "Falador", "Camelot", "More");
				stage = 1;
			}
		} else {
			end();
		}
	}

	private void placePortal(int index) {
		stage = 10;
		int requriedLevel = LEVELS[index];
		if (player.getSkills().getLevel(Skills.MAGIC) < requriedLevel) {
			sendDialogue("You need a magic level of at least " + requriedLevel + " in order to focus to this location");
			return;
		}
		int runesCount = 0;
		int[] runes = RUNES[index];
		while (runesCount < runes.length) {
			int runeId = runes[runesCount++];
			int amount = runes[runesCount++];
			if (!player.getInventory().containsItem(runeId, amount)) {
				sendDialogue("You do not have enough " + ItemDefinitions.getItemDefinitions(runeId).getName().replace("rune", "Rune") + "s to create this portal.");
				return;
			}
		}
		runesCount = 0;
		while (runesCount < runes.length) {
			int runeId = runes[runesCount++];
			int amount = runes[runesCount++];
			player.getInventory().deleteItem(runeId, amount);
		}
		player.getSkills().addXp(Skills.MAGIC, 5 * EXPERIENCE[index]);
		player.getPackets().sendGameMessage("You redirected the portal. Turn build mode off to see it.");
		rRef.getDirectedPortals()[redirectSlot] = (byte) (index + 1);
		end();
	}

	@Override
	public void finish() {

	}
}
