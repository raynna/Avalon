package com.rs.game.player.cutscenes;

import java.util.ArrayList;

import com.rs.game.player.Player;
import com.rs.game.player.cutscenes.actions.CutsceneAction;
import com.rs.game.player.cutscenes.actions.DialogueAction;
import com.rs.game.player.cutscenes.actions.LookCameraAction;


class CorporealBeastScene extends Cutscene {

	@Override
	public boolean hiddenMinimap() {
		return true;
	}

	@Override
	public CutsceneAction[] getActions(Player player) {
		ArrayList<CutsceneAction> actionsList = new ArrayList<>();
		actionsList.add(new DialogueAction("You peek through the door."));
		actionsList.add(new LookCameraAction(getX(player, 2992), getY(player, 4383), 1000, 3));
		return actionsList.toArray(new CutsceneAction[actionsList.size()]);
	}

}
