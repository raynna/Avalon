package com.rs.game.player.cutscenes;

import java.util.ArrayList;

import com.rs.game.player.Player;
import com.rs.game.player.cutscenes.actions.CutsceneAction;

/**
 * @author arno
 * @date 25-8-13
 * @time 23:09
 */
public class IntroductionCutscene extends Cutscene {

	@Override
	public boolean hiddenMinimap() {
		return true;
	}

	@Override
	public CutsceneAction[] getActions(Player player) {

		ArrayList<CutsceneAction> actionsList = new ArrayList<CutsceneAction>();
		return actionsList.toArray(new CutsceneAction[actionsList.size()]);
	}
}
