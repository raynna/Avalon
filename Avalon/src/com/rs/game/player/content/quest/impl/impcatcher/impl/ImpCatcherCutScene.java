package com.rs.game.player.content.quest.impl.impcatcher.impl;


import java.util.ArrayList;

import com.rs.game.Animation;
import com.rs.game.player.Player;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.quest.State.QuestState;
import com.rs.game.player.cutscenes.Cutscene;
import com.rs.game.player.cutscenes.actions.CreateNPCAction;
import com.rs.game.player.cutscenes.actions.CutsceneAction;
import com.rs.game.player.cutscenes.actions.LookCameraAction;
import com.rs.game.player.cutscenes.actions.NPCAnimationAction;
import com.rs.game.player.cutscenes.actions.NPCFaceTileAction;
import com.rs.game.player.cutscenes.actions.PosCameraAction;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class ImpCatcherCutScene extends Cutscene {
	
	private static final Animation ANIMATION = new Animation(4285);
	
	/**
	 * Represents the black bead item.
	 */
	private static final int BLACK_BEAD = 1474;

	/**
	 * Represents the red bead item.
	 */
	private static final int RED_BEAD = 1470;

	/**
	 * Represents the white bead item.
	 */
	private static final int WHITE_BEAD = 1476;

	/**
	 * Represents the yellow bead item.
	 */
	private static final int YELLOW_BEAD = 1472;
	
	@SuppressWarnings("unused")
	private static int DESK = 1, WIZARD = 2;

	@Override
	public boolean hiddenMinimap() {
		return false;
	}

	@Override
	public CutsceneAction[] getActions(final Player player) {
		ArrayList<CutsceneAction> actionsList = new ArrayList<CutsceneAction>();
		
		player.lock(10);
		actionsList.add(new PosCameraAction(player.getLocalX(), player.getLocalY() - 4, 1200, 7, 8, -1));
		actionsList.add(new LookCameraAction(player.getLocalX(), player.getLocalY() + 4, 1200, 7, 8, 5));
		actionsList.add(new CreateNPCAction(WIZARD, 706, getX(player, 3103), getY(player, 3163), 2, -1));
		actionsList.add(new NPCFaceTileAction(WIZARD, getX(player, 3102), getY(player, 3163), 3));
		actionsList.add(new NPCAnimationAction(WIZARD, ANIMATION, 5));
		WorldTasksManager.schedule(new WorldTask() {

			@Override
			public void run() {
				player.getInventory().deleteItem(WHITE_BEAD, 1);
				player.getInventory().deleteItem(BLACK_BEAD, 1);
				player.getInventory().deleteItem(YELLOW_BEAD, 1);
				player.getInventory().deleteItem(RED_BEAD, 1);
				player.getQuestManager().get(Quests.IMP_CATCHER).finish();
				player.getQuestManager().get(Quests.IMP_CATCHER).setState(QuestState.COMPLETED);
				player.getQuestManager().get(Quests.IMP_CATCHER).setStage(100);
				stop();
			}

		}, 17);
		return actionsList.toArray(new CutsceneAction[actionsList.size()]);
	}

}
