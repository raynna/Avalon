package com.rs.game.player.dialogues;

import java.util.concurrent.TimeUnit;

import com.rs.cores.CoresManager;
import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.Magic;

public class KBDEntrance extends Dialogue {

	/*
	 * 
	 * Written by Tristam
	 */

	@Override
	public void finish() {
		// TODO Auto-generated method stub

	}

	public static void Teleport(final Player player) {
		CoresManager.slowExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				Magic.pushLeverTeleport(player, new WorldTile(2273, 4681, 0));
				return;
			}
		}, 1000, TimeUnit.MILLISECONDS);
	}

	@Override
	public void run(int interfaceId, int componentId) {
		switch (stage) {
		case 1:
			sendOptionsDialogue("Select an option", "Yes", "Yes and remember my decision", "No, i'll just stay here.");
			stage = 2;
			break;
		case 2:
			switch (componentId) {
			case OPTION_1:
				Teleport(player);
				end();
				break;
			case OPTION_2:
				player.rememberChoice(true);
				Teleport(player);
				player.sm("The warning shall not pop up for you anymore.");
				end();
				break;
			case OPTION_3:
				end();
			}
			break;
		}

	}

	@Override
	public void start() {
		sendDialogue("Warning! You're about to enter the lair of King Black Dragon, are you sure you want to proceed?");
		stage = 1;

	}

}
