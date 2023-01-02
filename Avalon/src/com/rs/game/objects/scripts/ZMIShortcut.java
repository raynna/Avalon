package com.rs.game.objects.scripts;

import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.objects.ObjectScript;
import com.rs.game.player.Player;
import com.rs.game.player.content.FadingScreen;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class ZMIShortcut extends ObjectScript {

	@Override
	public Object[] getKeys() {
		return new Object[] { 26844, 26845 };
	}

	@Override
	public int getDistance() {
		return 0;
	}

	@Override
	public boolean processObject(Player player, WorldObject object) {
		FadingScreen.fade(player);
		player.getAppearence().setRenderEmote(295);
		player.sm("You squeeze through the cracks..");
		WorldTasksManager.schedule(new WorldTask() {
			int unfade = 0;
			@Override
			public void run() {
				if (unfade == 2) {
					player.getInterfaceManager().sendFadingInterface(170);
					if (object.getY() == 4818) {
						player.setNextWorldTile(new WorldTile(3308, 4820, 0));
					} else if(object.getY() == 4819) {
						player.setNextWorldTile(new WorldTile(3312, 4818, 0));
					}
				} else if (unfade == 3) {
					stop();
					player.getAppearence().setRenderEmote(-1);
					player.getInterfaceManager().closeFadingInterface();
				}
				unfade++;
			}
		}, 0, 1);
		return true;
	}
}
