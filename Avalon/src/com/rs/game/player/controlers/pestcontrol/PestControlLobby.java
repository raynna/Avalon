package com.rs.game.player.controlers.pestcontrol;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.minigames.pest.Lander;
import com.rs.game.player.controlers.Controler;
import com.rs.utils.Utils;

public final class PestControlLobby extends Controler {

	private int landerId;

	@Override
	public void start() {
		this.landerId = (Integer) getArguments()[0];
	}

	@Override
	public void sendInterfaces() {
		player.getPackets().sendIComponentText(407, 3, Utils.fixChatMessage(Lander.getLanders()[landerId].toString()));
		int secondsLeft = (Lander.getLanders()[landerId].getTimer().getSeconds());
		player.getPackets().sendIComponentText(407, 13, "Next Departure: " + secondsLeft + " seconds ");
		player.getPackets().sendIComponentText(407, 15, "(Need 3 to 25 players)");
		player.getPackets().sendIComponentText(407, 14,
				"Player's Ready: " + Lander.getLanders()[landerId].getPlayers().size());
		player.getPackets().sendIComponentText(407, 16, "Commendations: " + player.pestControlPoints);
		player.getInterfaceManager().sendOverlay(407, false);
	}

	@Override
	public void magicTeleported(int teleType) {
		player.getControlerManager().forceStop();
	}

	@Override
	public boolean processMagicTeleport(WorldTile toTile) {
		player.getControlerManager().forceStop();
		return true;
	}

	@Override
	public boolean processItemTeleport(WorldTile toTile) {
		player.getControlerManager().forceStop();
		return true;
	}

	@Override
	public void forceClose() {
		player.getInterfaceManager().closeOverlay(false);
		Lander.getLanders()[landerId].exitLander(player);
	}

	@Override
	public boolean logout() {
		Lander.getLanders()[landerId].remove(player);// to stop the timer in the
														// lander and prevent
														// future errors
		return false;
	}

	@Override
	public boolean canSummonFamiliar() {
		player.getPackets().sendGameMessage("You feel it's best to keep your Familiar away during this game.");
		return false;
	}

	@Override
	public boolean processObjectClick1(WorldObject object) {
		switch (object.getId()) {
		case 14314:
		case 25629:
		case 25630:
			player.getDialogueManager().startDialogue("LanderD");
			return true;
		}
		return true;
	}
}
