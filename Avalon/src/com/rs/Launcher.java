package com.rs;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import com.alex.store.Index;
import com.rs.cache.Cache;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.ItemsEquipIds;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.cache.loaders.ObjectDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.Region;
import com.rs.game.World;
import com.rs.game.area.AreaManager;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.ground.AutomaticGroundItem;
import com.rs.game.map.MapBuilder;
import com.rs.game.npc.combat.CombatScriptsHandler;
import com.rs.game.npc.drops.MobRewardRDT;
import com.rs.game.objects.GlobalObjectAddition;
import com.rs.game.objects.GlobalObjectDeletion;
import com.rs.game.objects.ObjectScriptsHandler;
import com.rs.game.player.AccountCreation;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.weaponscript.WeaponScriptsManager;
import com.rs.game.player.actions.skills.fishing.FishingSpotsHandler;
import com.rs.game.player.content.EdgevillePvPInstance;
import com.rs.game.player.content.KillScoreBoard;
import com.rs.game.player.content.clans.ClansManager;
import com.rs.game.player.content.customshops.CustomStoreData;
import com.rs.game.player.content.friendschat.FriendChatsManager;
import com.rs.game.player.content.grandexchange.GrandExchange;
import com.rs.game.player.content.grandexchange.LimitedGEReader;
import com.rs.game.player.content.grandexchange.UnlimitedGEReader;
import com.rs.game.player.controlers.ControlerHandler;
import com.rs.game.player.cutscenes.CutscenesHandler;
import com.rs.game.player.dialogues.DialogueHandler;
import com.rs.game.worldlist.WorldList;
import com.rs.net.DiscordBot;
import com.rs.net.ServerChannelHandler;
import com.rs.net.sql.DatabaseUtility;
import com.rs.utils.Credentials;
import com.rs.utils.DTRank;
import com.rs.utils.DisplayNames;
import com.rs.utils.IPBanL;
import com.rs.utils.ItemBonuses;
import com.rs.utils.ItemExamines;
import com.rs.utils.Logger;
import com.rs.utils.MapArchiveKeys;
import com.rs.utils.MapAreas;
import com.rs.utils.MusicHints;
import com.rs.utils.NPCBonuses;
import com.rs.utils.NPCCombatDefinitionsL;
import com.rs.utils.NPCExamines;
import com.rs.utils.NPCSpawns;
import com.rs.utils.ObjectSpawns;
import com.rs.utils.ShopsHandler;
import com.rs.utils.WeaponTypesLoader;
import com.rs.utils.Weights;
import com.rs.utils.huffman.Huffman;

public final class Launcher {

	public static void main(String[] args) throws Exception {
		if (args.length < 4) {
			Settings.VPS_HOSTED = false;
			Settings.PORT_ID = 43594;
			Settings.HOSTED = true;
			Settings.DEBUG = true;
		} else {
			Settings.VPS_HOSTED = Boolean.parseBoolean(args[3]);
			Settings.PORT_ID = Integer.valueOf(args[2]);
			Settings.HOSTED = Boolean.parseBoolean(args[1]);
			Settings.DEBUG = Boolean.parseBoolean(args[0]);
		}
		Cache.init();
		AreaManager.init();
		ItemsEquipIds.init();
		Huffman.init();
		DisplayNames.init();
		MapArchiveKeys.init();
		MapAreas.init();
		IPBanL.init();
		ObjectSpawns.init();
		NPCSpawns.init();
		NPCCombatDefinitionsL.init();
		NPCBonuses.init();
		ItemExamines.init();
		ItemBonuses.init();
		MusicHints.init();
		ShopsHandler.init();
		FishingSpotsHandler.init();
		CombatScriptsHandler.init();
		ObjectScriptsHandler.init();
		WeaponScriptsManager.init();
		DialogueHandler.init();
		ControlerHandler.init();
		CutscenesHandler.init();
		FriendChatsManager.init();
		ClansManager.init();
		CoresManager.init();
		World.init();
		MapBuilder.init();
		Weights.init();
		GrandExchange.init();
		LimitedGEReader.init();
		UnlimitedGEReader.init();
		GlobalObjectDeletion.init();
		GlobalObjectAddition.init();
		//StarterProtection.loadIPS();
		NPCExamines.loadPackedExamines();
		Credentials.init();
		WeaponTypesLoader.loadDefinitions();
		AutomaticGroundItem.initialize();
		MobRewardRDT.getInstance().structureNode();
		WorldList.init();
		KillScoreBoard.init();
		EdgevillePvPInstance.buildInstance();
		CityEventHandler.registerCitys();
		CustomStoreData.init();
		DTRank.init();
		try {
			ServerChannelHandler.init();
		} catch (Throwable e) {
			Logger.handle(e);
			Logger.log("Launcher", "Failed initing Server Channel Handler. Shutting down...");
			System.exit(1);
			return;
		}
		if (Settings.discordEnabled)
			discord = new DiscordBot();
		Logger.log("Economy",
				Settings.ECONOMY_MODE == 2 ? "Full Spawn" : Settings.ECONOMY_MODE == 1 ? "Half Economy" : "Economy");
		Logger.log("Debug", Settings.DEBUG ? "Debug is activated." : "Debug is inactived.");
		Logger.log("Status", Settings.SERVER_NAME + " is now online.");
		if (Settings.sqlQueries)
			DatabaseUtility.init();
		if (!Settings.DEBUG) {
			discord.getChannelByName("server-status").sendMessage("Avalon is now online!");
		}
		addAccountsSavingTask();
		addCleanMemoryTask();
	}

	private static void addCleanMemoryTask() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					cleanMemory(Runtime.getRuntime().freeMemory() < Settings.MIN_FREE_MEM_ALLOWED);
				} catch (Throwable e) {
					Logger.handle(e);
				}
			}
		}, 0, 10, TimeUnit.SECONDS);
	}

	private static int i = 0;

	private static void addAccountsSavingTask() {
		CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
			@Override
			public void run() {
				try {
					i++;
					if (i % 30 == 0 && !Settings.DEBUG) {
						discord.getChannelByName("public-chat").sendMessage("30 Minutes Message\n"
								+ World.getPlayers().size() + " players are currently playing Avalon.");
						i = 0;
					}
					saveFiles();
				} catch (Throwable e) {
					Logger.handle(e);
				}

			}
		}, 0, 1, TimeUnit.MINUTES);
	}

	public static String Time(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}

	public static String Time;

	public static void saveFiles() {
		for (Player player : World.getPlayers()) {
			if (player == null || !player.hasStarted() || player.hasFinished())
				continue;
			AccountCreation.savePlayer(player);
		}
		IPBanL.save();
		GrandExchange.save();
		// PlayerOwnedShops.save();
		Time = Time("dd MMMMM yyyy 'at' hh:mm:ss z");
		// System.out.println("Saved players " + Time + ", " + World.getPlayers().size()
		// + " players online.");
	}

	public static void cleanMemory(boolean force) {
		if (force) {
			ItemDefinitions.clearItemsDefinitions();
			NPCDefinitions.clearNPCDefinitions();
			ObjectDefinitions.clearObjectDefinitions();
			skip: for (Region region : World.getRegions().values()) {
				for (int regionId : MapBuilder.FORCE_LOAD_REGIONS)
					if (regionId == region.getRegionId())
						continue skip;
				region.unloadMap();
			}
		}
		for (Index index : Cache.STORE.getIndexes())
			index.resetCachedFiles();
		CoresManager.fastExecutor.purge();
		System.gc();
	}

	public static void shutdown() {
		try {
			closeServices();
		} finally {
			System.exit(0);
		}
	}

	public static void closeServices() {
		if (!Settings.DEBUG) {
			discord.getChannelByName("public-chat").sendMessage("Avalon is now offline!");
			discord.getChannelByName("server-status").sendMessage("Avalon is now offline!");
		}
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int cycle = 0;; cycle++) {
			Logger.log("Launcher", "Logging out players... Cycle #" + cycle);
			if (World.getPlayers().size() == 0 && World.getLobbyPlayers().size() == 0)
				break;
			for (Player player : World.getPlayers())
				player.realFinish();
			for (Player player : World.getLobbyPlayers())
				player.realFinish();
			Logger.log("Launcher",
					"Logging out players: " + (World.getPlayers().size() + World.getLobbyPlayers().size()) + ".");
			try {
				Thread.sleep(2000);
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}
		ServerChannelHandler.shutdown();
		CoresManager.shutdown();
	}

	public static void restart() {
		closeServices();
		System.gc();
		try {
			Runtime.getRuntime().exec(
					"java -XX:-OmitStackTraceInFastThrow -Xms1024m -cp bin;data/libs/netty-3.5.2.Final.jar;data/libs/RuneTopListV2.jar;data/libs/FileStore.jar;data/lib/GTLVote.jar;data/lib/mysql2.jar com.rs.Launcher false false false");
			System.exit(0);
		} catch (Throwable e) {
			Logger.handle(e);
		}

	}

	private static DiscordBot discord;

	public static DiscordBot getDiscordBot() {
		return discord;
	}

}
