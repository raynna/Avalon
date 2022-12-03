package com.rs.game.player.controlers;

import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.map.MapBuilder;
import com.rs.game.npc.recipeofdisaster.RfdNPC;
import com.rs.game.player.Player;
import com.rs.game.player.actions.skills.summoning.Summoning;
import com.rs.game.player.content.FadingScreen;
import com.rs.game.player.content.pet.Pets;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Logger;

public class RecipeForDisaster extends Controler {

	public static final WorldTile OUTSIDE = new WorldTile(3087, 3502, 0);

	public void playMusic() {
		player.getMusicsManager().playMusic(selectedMusic);
	}

	private final int[][] WAVES = { { 3493 }, { 3494 }, { 3495 }, { 3496 }, { 3497 }, { 3491 } };

	private int[] boundChuncks;
	private Stages stage;
	private boolean logoutAtEnd;
	private boolean login;
	public boolean spawned;
	public int selectedMusic;

	public static void enterRfd(Player player) {
		//if (player.getQuestManager().get(Quests.RECIPE_FOR_DISASTER).getState() == QuestState.NOT_STARTED) {
		//	player.sm("I should probably talk to Gypsy Aris first.");
		//	return;
		//}
		//if (player.getQuestManager().get(Quests.RECIPE_FOR_DISASTER).getState() == QuestState.COMPLETED) {
		//	player.sm("I have already completed this miniquest.");
		//	return;
		//}
		if (player.getPet() != null || Pets.hasPet(player)) {
			player.getPackets().sendGameMessage("You can't bring any pets in here.");
			return;
		}
		if (player.getFamiliar() != null) {
			player.getPackets().sendGameMessage("You can't bring any followers in here.");
			return;
		}
		if (Summoning.hasPouch(player)) {
			player.getPackets().sendGameMessage("You can't bring any familiar pouches in here.");
			return;
		}
		player.getControlerManager().startControler("RecipeDisasterControler", 1);
		player.getPrayer().closeAllPrayers();
		player.getPackets().sendGameMessage("This minigame is safe, meaning you don't lose items on death.");
	}

	private static enum Stages {
		LOADING, RUNNING, DESTROYING
	}

	@Override
	public void start() {
		loadCave(false);
	}

	@Override
	public boolean processButtonClick(int interfaceId, int componentId, int slotId, int packetId) {
		if (stage != Stages.RUNNING)
			return false;
		if (interfaceId == 182 && (componentId == 6 || componentId == 13)) {
			if (!logoutAtEnd) {
				logoutAtEnd = true;
				player.getPackets()
						.sendGameMessage("<col=ff0000>You will be logged out automatically at the end of this wave.");
				player.getPackets()
						.sendGameMessage("<col=ff0000>If you log out sooner, you will have to repeat this wave.");
			} else
				player.forceLogout();
			return false;
		}
		if (interfaceId == 271 || interfaceId == 749 && componentId == 4) {
			player.getPackets().sendGameMessage("You can't use prayers inside this minigame.");
			return false;
		}
		return true;
	}

	/**
	 * return process normaly
	 */
	@Override
	public boolean processObjectClick1(WorldObject object) {
		if (object.getId() == 12356) {
			if (stage != Stages.RUNNING)
				return false;
			exitCave(1);
			return false;
		}
		return true;
	}

	/*
	 * return false so wont remove script
	 */
	@Override
	public boolean login() {
		loadCave(true);
		return false;
	}

	public void loadCave(final boolean login) {
		this.login = login;
		stage = Stages.LOADING;
		player.lock(); // locks player
		CoresManager.slowExecutor.execute(new Runnable() {
			@Override
			public void run() {
				boundChuncks = MapBuilder.findEmptyChunkBound(8, 8);
				MapBuilder.copyAllPlanesMap(236, 668, boundChuncks[0], boundChuncks[1], 8);
				player.setNextWorldTile(getWorldTile(9, 8));
				WorldTasksManager.schedule(new WorldTask() {
					@Override
					public void run() {
						player.unlock(); // unlocks player
						stage = Stages.RUNNING;
					}

				}, 1);
				if (!login) {
					CoresManager.fastExecutor.schedule(new TimerTask() {

						@Override
						public void run() {
							if (stage != Stages.RUNNING)
								return;
							try {
								startWave();
							} catch (Throwable t) {
								Logger.handle(t);
							}
						}
					}, 6000);
				}
			}
		});
	}

	public WorldTile getSpawnTile() {
		return getWorldTile(10, 10);
	}

	@Override
	public void moved() {
		if (stage != Stages.RUNNING || !login)
			return;
		login = false;
		setWaveEvent();
	}

	public void startWave() {
		int currentWave = getCurrentWave();
		player.getInterfaceManager().sendOverlay(316, false);
		player.getVarsManager().sendVar(639, currentWave);
		if (stage != Stages.RUNNING)
			return;
		for (int id : WAVES[currentWave - 1]) {
			new RfdNPC(id, getSpawnTile());
		}
		spawned = true;
	}

	public void win() {
		if (stage != Stages.RUNNING)
			return;
		exitCave(4);
	}

	public void nextWave() {
		setCurrentWave(getCurrentWave() + 1);
		playMusic();
		if (logoutAtEnd) {
			player.forceLogout();
			return;
		}
		setWaveEvent();
	}

	public void setWaveEvent() {
		CoresManager.fastExecutor.schedule(new TimerTask() {

			@Override
			public void run() {
				try {
					if (stage != Stages.RUNNING)
						return;
					startWave();
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 600);
	}

	@Override
	public void process() {
		if (player.getPrayer().hasPrayersOn())
			player.getPrayer().closeAllPrayers();
		if (spawned) {
			List<Integer> npcs = World.getRegion(player.getRegionId()).getNPCsIndexes();
			if (npcs == null || npcs.isEmpty()) {
				player.getHintIconsManager().removeAll();
				spawned = false;
				nextWave();
			}
		}
	}

	@Override
	public boolean sendDeath() {
		player.lock(7);
		player.stopAll();
		player.animate(new Animation(836));
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				if (loop == 1) {
					player.getPackets().sendGameMessage("You have been defeated!");
				} else if (loop == 3) {
					player.reset();
					exitCave(1);
					player.animate(new Animation(-1));
				} else if (loop == 4) {
					player.getPackets().sendMusicEffect(90);
					stop();
				}
				loop++;
			}
		}, 0, 1);
		return false;
	}

	@Override
	public void magicTeleported(int type) {
		exitCave(1);
	}

	public void exitCave(int type) {
		stage = Stages.DESTROYING;
		WorldTile outside = new WorldTile(OUTSIDE, 2);
		if (type == 0)
			player.setLocation(outside);
		else {
			player.setForceMultiArea(false);
			player.getInterfaceManager().closeOverlay(false);
			if (type == 1 || type == 4) {
				player.useStairs(-1, outside, 0, 1);
				if (type == 4) {
					player.reset();
					player.getPackets().sendGameMessage("You managed to defeat the Culinaromancer.");
					FadingScreen.fade(player);
					WorldTasksManager.schedule(new WorldTask() {
						int unfade = 0;

						@Override
						public void run() {
							if (unfade == 3) {
								player.getInterfaceManager().sendFadingInterface(170);
							} else if (unfade == 5) {
								stop();
								player.getInterfaceManager().closeFadingInterface();
								//player.getQuestManager().get(Quests.RECIPE_FOR_DISASTER).finish();
							}
							unfade++;
						}
					}, 0, 1);
				} else if (type == 1)
					player.getDialogueManager().startDialogue("SimpleNPCMessage", 9361, player.getDisplayName()
							+ ", are you okay? I managed to teleport you out just in time, get back in and kill the pesky monsters!");
			}
			removeControler();
		}
		/*
		 * 1200 delay because of leaving
		 */
		CoresManager.slowExecutor.schedule(new Runnable() {
			@Override
			public void run() {
				MapBuilder.destroyMap(boundChuncks[0], boundChuncks[1], 8, 8);
			}
		}, 1200, TimeUnit.MILLISECONDS);
	}

	/*
	 * gets worldtile inside the map
	 */
	public WorldTile getWorldTile(int mapX, int mapY) {
		return new WorldTile(boundChuncks[0] * 8 + mapX, boundChuncks[1] * 8 + mapY, 2);
	}

	/*
	 * return false so wont remove script
	 */
	@Override
	public boolean logout() {
		if (stage != Stages.RUNNING)
			return false;
		return false;

	}

	public int getCurrentWave() {
		if (getArguments() == null || getArguments().length == 0)
			return 1;
		return (Integer) getArguments()[0];
	}

	public void setCurrentWave(int wave) {
		if (getArguments() == null || getArguments().length == 0)
			this.setArguments(new Object[1]);
		getArguments()[0] = wave;
	}

	@Override
	public void forceClose() {
		if (stage != Stages.RUNNING)
			return;
		exitCave(1);
	}
}
