package com.rs.game.area;

import java.util.ArrayList;
import java.util.List;

import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;

public class AreaManager {

	private static List<Area> areas;

	public static void init() {
		try {
			areas(new ArrayList<Area>());
			areas().add(new Multi());
			areas().add(new WildyAgilityArea());
			areas().add(new Godwars());
			areas().add(new NexMulti());
			areas().add(new CorpMulti());
			areas().add(new DKSMulti());
			areas().add(new ChaosTunnelMulti());
			areas().add(new TormDemonMulti());
			areas().add(new KalphiteQueenMulti());
			areas().add(new ForinthryDungeonMulti());
			areas().add(new KingBlackDragon());
			// FFA
			areas().add(new FFASafe());
			areas().add(new FFASafePvP());
			areas().add(new FFASafeZone());
			//end of FFA
			areas().add(new LividFarmArea());
		} catch (Exception e) {
			System.out.print(e);
		}
	}

	public static Area get(final WorldTile location) {
		try {
			Area current = null;
			boolean found = false;
			for (Area area : areas()) {

				for (Shape shape : area.shapes()) {

					if (shape.inside(location)) {
						current = area;
						found = true;
						break;
					}

				}

				if (!found)
					continue;

				return current;
			}
		} catch (Exception e) {
			System.out.print(e);
		}
		return null;
	}

	public static void update(final Player player, final Area area) {
		player.getPackets().sendIComponentText(1073, 10, "<col=ffffff>You have reached");
		player.getPackets().sendIComponentText(1073, 11, "<col=ffcff00>" + area.name());
		player.getInterfaceManager().sendTab(player.getInterfaceManager().hasRezizableScreen() ? 1 : 11, 1073);
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.getPackets().closeInterface(player.getInterfaceManager().hasRezizableScreen() ? 1 : 11);
				stop();
			}
		}, 3);
	}

	public static List<Area> areas() {
		return areas;
	}

	public static void areas(List<Area> list) {
		areas = list;
	}
}