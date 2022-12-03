package com.rs.game.player.content.quest.impl.goblindiplomacy.cutscenes;

import java.util.ArrayList;

import com.rs.game.player.Player;
import com.rs.game.player.cutscenes.Cutscene;
import com.rs.game.player.cutscenes.actions.ConstructMapAction;
import com.rs.game.player.cutscenes.actions.CreateNPCAction;
import com.rs.game.player.cutscenes.actions.CutsceneAction;
import com.rs.game.player.cutscenes.actions.LookCameraAction;
import com.rs.game.player.cutscenes.actions.MovePlayerAction;
import com.rs.game.player.cutscenes.actions.NPCFaceTileAction;
import com.rs.game.player.cutscenes.actions.NPCForceTalkAction;
import com.rs.game.player.cutscenes.actions.PosCameraAction;

public class GrubFootOrangeMailCutscene extends Cutscene {

	
	@SuppressWarnings("unused")
	private static int GRUBFOOT = 1,
			WARTFACE = 2,
			BENTNOZE = 3;
	
	/**
	 * Id's
	 */
	
	@SuppressWarnings("unused")
	private static int GRUBFOOT_ID = 4495,
			WARTFACE_ID = 4494,
			BENTNOZE_ID = 4493;
			
	
	@Override
	public boolean hiddenMinimap() {
		return true;
	}

	@Override
	public CutsceneAction[] getActions(Player player) {
		ArrayList<CutsceneAction> actionsList = new ArrayList<CutsceneAction>();
				actionsList.add(new ConstructMapAction(369, 438, 10, 10));
				actionsList.add(new MovePlayerAction(14, 57, 0, Player.TELE_MOVE_TYPE,
						0));
				actionsList.add(new PosCameraAction(8, 7, 2430, -1));
				actionsList.add(new LookCameraAction(7, 8, 2000, -1));
				actionsList.add(new CreateNPCAction(GRUBFOOT, GRUBFOOT_ID, 34, 58, 0, -1));
//				actionsList.add(new CreateNPCAction(WARTFACE, WARTFACE_ID, 15, 55, 0, -1));
//				actionsList.add(new CreateNPCAction(BENTNOZE, BENTNOZE_ID, 14, 56, 0, -1));
				actionsList.add(new NPCFaceTileAction(GRUBFOOT, 14, 1, -1));
//				actionsList.add(new NPCFaceTileAction(WARTFACE, 15, 0, -1));
//				actionsList.add(new NPCFaceTileAction(BENTNOZE, 14, 0, -1));
				//actionsList.add(new PosCameraAction(14, 50, 5000, 1, 1, -1));
				actionsList.add(new NPCForceTalkAction(GRUBFOOT, "FUCK YU ETHAN!", 2));
		return actionsList.toArray(new CutsceneAction[actionsList.size()]);
	}

}
