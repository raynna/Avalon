package com.rs.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.rs.Launcher;
import com.rs.Settings;
import com.rs.cache.loaders.ObjectDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.area.AreaManager;
import com.rs.game.item.FloorItem;
import com.rs.game.item.Item;
import com.rs.game.map.MapUtils;
import com.rs.game.map.MapUtils.Structure;
import com.rs.game.minigames.clanwars.FfaZone;
import com.rs.game.minigames.clanwars.RequestController;
import com.rs.game.minigames.godwars.armadyl.GodwarsArmadylFaction;
import com.rs.game.minigames.godwars.armadyl.KreeArra;
import com.rs.game.minigames.godwars.bandos.GeneralGraardor;
import com.rs.game.minigames.godwars.bandos.GodwarsBandosFaction;
import com.rs.game.minigames.godwars.saradomin.CommanderZilyana;
import com.rs.game.minigames.godwars.saradomin.GodwarsSaradominFaction;
import com.rs.game.minigames.godwars.zamorak.GodwarsZammorakFaction;
import com.rs.game.minigames.godwars.zamorak.KrilTstsaroth;
import com.rs.game.minigames.godwars.zaros.Nex;
import com.rs.game.minigames.godwars.zaros.NexMinion;
import com.rs.game.minigames.godwars.zaros.ZarosGodwars;
import com.rs.game.minigames.warriorguild.WarriorsGuild;
import com.rs.game.npc.NPC;
import com.rs.game.npc.corporeal.CorporealBeast;
import com.rs.game.npc.dagannoth.DagannothKings;
import com.rs.game.npc.dagannoth.Spyinolyp;
import com.rs.game.npc.dragons.KingBlackDragon;
import com.rs.game.npc.glacior.Glacor;
import com.rs.game.npc.kalphite.KalphiteQueen;
import com.rs.game.npc.nomad.FlameVortex;
import com.rs.game.npc.nomad.Nomad;
import com.rs.game.npc.others.*;
import com.rs.game.player.OwnedObjectManager;
import com.rs.game.player.Player;
import com.rs.game.player.actions.BoxAction.HunterNPC;
import com.rs.game.player.actions.skills.mining.LivingRockCavern;
import com.rs.game.player.content.ItemConstants;
import com.rs.game.player.content.Update;
import com.rs.game.player.content.grandexchange.GrandExchange;
import com.rs.game.player.content.shootingstar.ShootingStar;
import com.rs.game.player.controlers.EdgevillePvPControler;
import com.rs.game.player.controlers.WildernessControler;
import com.rs.game.route.Flags;
import com.rs.game.timer.TimerRepository;
import com.rs.utils.AntiFlood;
import com.rs.utils.IPBanL;
import com.rs.utils.Logger;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;

/**
 * @Improved Andreas - AvalonPK
 */

public final class World {

    public static int exiting_delay;
    public static long exiting_start;


    private static TimerRepository timers = new TimerRepository();
    private transient static final EntityList<Player> players = new EntityList<Player>(Settings.PLAYERS_LIMIT);

    private static final EntityList<NPC> npcs = new EntityList<NPC>(Settings.NPCS_LIMIT);
    private static final Map<Integer, Region> regions = Collections.synchronizedMap(new HashMap<Integer, Region>());

    public static boolean isInUpdate;

    public static List<Player> getLocalPlayers(WorldTile tile, int i) {
        return getLocalPlayers(tile, 16);
    }

    public static int getPlayersInWilderness() {
        int result = 0;
        for (Player players : World.getPlayers())
            if ((players.getControlerManager().getControler() instanceof WildernessControler)) {
                result++;
            }
        return result;
    }

    public static int getPlayersInFFA() {
        int result = 0;
        for (Player players : World.getPlayers()) {
            if (FfaZone.inPvpArea(players))
                result++;
        }
        return result;
    }

    public static int getPlayersInPVP() {
        int result = 0;
        for (Player players : World.getPlayers())
            if ((players.getControlerManager().getControler() instanceof EdgevillePvPControler)) {
                result++;
            }
        return result;
    }

    public static void init() {
        addRandomMessagesTask();
        addRestoreShopItemsTask();
        addDegradeShopItemsTask();
        addOwnedObjectsTask();
        Update.ProcessUpdates();
        LivingRockCavern.init();
        WarriorsGuild.init();
        executeShootingStar();
        artisanWorkShopBonusExp();
    }

    public enum ARTISAN_TYPES {
		HELM, CHEST, GLOVES, BOOTS
	};

	public static ARTISAN_TYPES artisanBonusExp;

	public static void artisanWorkShopBonusExp() {
		spawnNPC(6654, new WorldTile(3060, 3339, 0), -1, true);
		NPC suak = getNPC(6654);
		int time = 36000;
		CoresManager.fastExecutor.schedule(new TimerTask() {

			@Override
			public void run() {
				try {
					ARTISAN_TYPES[] values2 = ARTISAN_TYPES.values();
					artisanBonusExp = values2[Utils.random(0, values2.length -1)];
					suak.setNextForceTalk(new ForceTalk("Smith " + artisanBonusExp));
				} catch (Throwable e) {
					World.sendWorldMessage("" + e, true);
					Logger.handle(e);
				}
			}
		}, 0, time);
		World.sendWorldMessage("" + artisanBonusExp, true);
	}
    /*
     * private static void addLogicPacketsTask() {
     * CoresManager.fastExecutor.scheduleAtFixedRate(new TimerTask() {
     *
     * @Override public void run() { for (Player player : World.getPlayers()) { if
     * (!player.hasStarted() || player.hasFinished()) continue;
     * player.processLogicPackets(); } } }, 300, 300); }
     */

    /*
     * private static void addNpcAgressionTask() {
     * CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
     *
     * @Override public void run() { try { for (NPC npc : getNPCs()) { for (Player
     * player : getPlayers()) { if (player.withinDistance(npc, 5) &&
     * player.inWilderness() && !npc.isDead() && npc.canWalkNPC(player.getX(),
     * player.getY(), true)) { npc.setTarget(player); player.sm("NPC " + npc.getId()
     * + " is wanting the D."); } } } } catch (Throwable e) { Logger.handle(e); }
     *
     * }
     *
     * }, 0, 2, TimeUnit.SECONDS); }
     */

    public static void executeAfterLoadRegion(final int regionId, final Runnable event) {
        executeAfterLoadRegion(regionId, 0, event);
    }

    public static void executeAfterLoadRegion(final int regionId, long startTime, final Runnable event) {
        executeAfterLoadRegion(regionId, startTime, 10000, event);
    }

    public static void executeAfterLoadRegion(final int regionId, long startTime, final long expireTime,
                                              final Runnable event) {
        final long start = Utils.currentTimeMillis();
        World.getRegion(regionId, true); // forces check load if not loaded
        CoresManager.fastExecutor.schedule(new TimerTask() {

            @Override
            public void run() {
                try {
                    if (!World.isRegionLoaded(regionId) && Utils.currentTimeMillis() - start < expireTime)
                        return;
                    event.run();
                    cancel();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }

        }, startTime, 600);
    }

    public static void executeAfterLoadRegion(final int fromRegionX, final int fromRegionY, final int toRegionX,
                                              final int toRegionY, long startTime, final long expireTime, final Runnable event) {
        final long start = Utils.currentTimeMillis();
        for (int x = fromRegionX; x <= toRegionX; x++) {
            for (int y = fromRegionY; y <= toRegionY; y++) {
                int regionId = MapUtils.encode(Structure.REGION, x, y);
                World.getRegion(regionId, true); // forces check load if not loaded
            }
        }
        CoresManager.fastExecutor.schedule(new TimerTask() {

            @Override
            public void run() {
                try {
                    for (int x = fromRegionX; x <= toRegionX; x++) {
                        for (int y = fromRegionY; y <= toRegionY; y++) {
                            int regionId = MapUtils.encode(Structure.REGION, x, y);
                            if (!World.isRegionLoaded(regionId) && Utils.currentTimeMillis() - start < expireTime)
                                return;
                        }
                    }
                    event.run();
                    cancel();
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }

        }, startTime, 600);
    }

    /**
     * shooting star handler
     */
    public static ShootingStar shootingStar;

    public static void executeShootingStar() {
        CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                shootingStar = new ShootingStar();
            }
        }, 0, 30, TimeUnit.MINUTES);
    }

    private static void addOwnedObjectsTask() {
        CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    OwnedObjectManager.processAll();
                } catch (Throwable e) {
                    Logger.handle(e);
                }
            }
        }, 0, 1, TimeUnit.SECONDS);
    }

    private static void addRestoreShopItemsTask() {
        CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    ShopsHandler.restoreShops();
                } catch (Throwable e) {
                    Logger.handle(e);
                }
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    private static void addDegradeShopItemsTask() {
        CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    ShopsHandler.degradeShops();
                } catch (Throwable e) {
                    Logger.handle(e);
                }
            }
        }, 0, 90, TimeUnit.SECONDS);
    }

    private static int lastMessage = -1;

    private static void addRandomMessagesTask() {
        CoresManager.slowExecutor.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                try {
                    for (Player player : getPlayers()) {
                        if (player == null || !player.isActive())
                            continue;
                        int random = Utils.getRandom(5);
                        while (random != lastMessage) {
                            random = Utils.getRandom(5);
                        }
                        lastMessage = random;
                        String colorIcon = "<img=7><col=9966ff> ";
                        switch (random) {
                            case 0:
                                player.getPackets().sendFilteredGameMessage(true,
                                        colorIcon + "Tip: It's always smart to report something suspicious!");
                                break;
                            case 1:
                                player.getPackets().sendFilteredGameMessage(true,
                                        colorIcon + "Like the server? Don't forget to give us your feedback!");
                                break;
                            case 2:
                                player.getPackets().sendFilteredGameMessage(true,
                                        colorIcon + "Tip: Don't forget to send in a ticket, if you need assistance.");
                                break;
                            case 3:
                                player.getPackets().sendFilteredGameMessage(true,
                                        colorIcon + "Tip: You can hide these kind of messages by filtering your chat.");
                                break;
                            case 4:
                                player.getPackets().sendFilteredGameMessage(true,
                                        colorIcon + "Did you know? " + Settings.SERVER_NAME + " has achievement diaries?");
                                break;
                            case 5:
                                player.getPackets().sendFilteredGameMessage(true,
                                        colorIcon + Utils.getRegisteredAccounts());
                                break;
                        }
                    }
                } catch (Throwable e) {
                    Logger.handle(e);
                }
            }
        }, 0, 120, TimeUnit.SECONDS);
    }

    public static final Map<Integer, Region> getRegions() {
        // synchronized (lock) {
        return regions;
        // }
    }

    public static final Region getRegion(int id) {
        return getRegion(id, false);
    }

    public static final Region getRegion(int id, boolean load) {
        // synchronized (lock) {
        Region region = regions.get(id);
        if (region == null) {
            region = new Region(id);
            regions.put(id, region);
        }
        if (load)
            region.checkLoadMap();
        return region;
        // }
    }

    public static final void addNPC(NPC npc) {
        npcs.add(npc);
    }

    public static final void removeNPC(NPC npc) {
        npcs.remove(npc);
    }

    public static NPC getNPC(int npcId) {
        for (NPC npc : getNPCs()) {
            if (npc.getId() == npcId) {
                return npc;
            }
        }
        return null;
    }

    public static final NPC spawnNPC(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea,
                                     boolean spawned) {
        NPC n = null;
        HunterNPC hunterNPCs = HunterNPC.forId(id);
        if (hunterNPCs != null) {
            if (id == hunterNPCs.getNpcId())
                n = new ItemHunterNPC(hunterNPCs, id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        } else if (id >= 5533 && id <= 5558)
            n = new Elemental(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 6078 || id == 6079 || id == 4292 || id == 4291 || id == 6080 || id == 6081)
            n = new Cyclopse(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
        else if (id == 7134)
            n = new Bork(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 9463 || id == 9465 || id == 9467)
            n = new Strykewyrms(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 2881 || id == 2882 || id == 2883)
            n = new DagannothKings(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 2892 || id == 2893 || id == 2894)
            n = new Spyinolyp(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 9441)
            n = new FlameVortex(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id >= 8832 && id <= 8834)
            n = new LivingRock(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id >= 13465 && id <= 13481)
            n = new Revenant(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 1158 || id == 1160)
            n = new KalphiteQueen(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id >= 8528 && id <= 8532)
            n = new Nomad(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
            /*
             * else if (id == 6281 || id == 6282 || id == 6275 || id == 6279 || id == 9184
             * || id == 6268 || id == 6270 || id == 6274 || id == 6277 || id == 6276 || id
             * == 6278) n = new GodwarsBandosFaction(id, tile, mapAreaNameHash,
             * canBeAttackFromOutOfArea, spawned);
             */
        else if (id == 6260)
            n = new GeneralGraardor(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 3200)
            n = new ChaosElemental(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 6222)
            n = new KreeArra(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 6203)
            n = new KrilTstsaroth(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 50 || id == 2642)
            n = new KingBlackDragon(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id >= 9462 && id <= 9467)
            n = new Strykewyrm(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
        else if (id == 6247)
			n = new CommanderZilyana(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		else if (id >= 6210 && id <= 6221)
			n = new GodwarsZammorakFaction(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		else if (id >= 6254 && id <= 6259)
			n = new GodwarsSaradominFaction(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		else if (id >= 6268 && id <= 6283)
			n = new GodwarsBandosFaction(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
		else if (id >= 6228 && id <= 6246)
			n = new GodwarsArmadylFaction(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 8133)
            n = new CorporealBeast(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 19000 || id == 19001 || id == 19002 || id == 6367 || id == 3229 || id == 1919)
            n = new Hybrid(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 4474 || id == 7891)
            n = new Dummy(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 13447)
            n = ZarosGodwars.nex = new Nex(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 13451)
            n = ZarosGodwars.fumus = new NexMinion(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 13452)
            n = ZarosGodwars.umbra = new NexMinion(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 13453)
            n = ZarosGodwars.cruor = new NexMinion(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 14301)
            n = new Glacor(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea);
        else if (id == 13454)
            n = ZarosGodwars.glacies = new NexMinion(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 8349 || id == 8450 || id == 8451)
            n = new TormentedDemon(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 15149)
            n = new MasterOfFear(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 1266 || id == 1268)
            n = new Rocks(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        else if (id == 1265 || id == 1267)
            n = new RockCrabs(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
            else if (id == 3373)
            n = new Max(id, tile, canBeAttackFromOutOfArea);
        else
            n = new NPC(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned);
        return n;
    }

    public static final NPC spawnNPC(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea,
                                     boolean spawned, Player owner) {
        NPC n = null;
        n = new NPC(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, spawned, owner);
        if (n.getId() == 9151) {
            n.animate(new Animation(11907));
        }
        return n;
    }

    public static final NPC spawnNPC(int id, WorldTile tile, int mapAreaNameHash, boolean canBeAttackFromOutOfArea) {
        return spawnNPC(id, tile, mapAreaNameHash, canBeAttackFromOutOfArea, false);
    }

    /*
     * check if the entity region changed because moved or teled then we update it
     */
    public static final void updateEntityRegion(Entity entity) {
        if (entity.hasFinished()) {
            if (entity instanceof Player)
                getRegion(entity.getLastRegionId()).removePlayerIndex(entity.getIndex());
            else
                getRegion(entity.getLastRegionId()).removeNPCIndex(entity.getIndex());
            return;
        }
        int regionId = entity.getRegionId();
        if (entity.getLastRegionId() != regionId) { // map region entity at
            // changed
            if (entity instanceof Player) {
                if (entity.getLastRegionId() > 0)
                    getRegion(entity.getLastRegionId()).removePlayerIndex(entity.getIndex());
                Region region = getRegion(regionId);
                region.addPlayerIndex(entity.getIndex());
                Player player = (Player) entity;
                int musicId = region.getRandomMusicId();
                if (musicId != -1)
                    player.getMusicsManager().checkMusic(musicId);
                if (player.getControlerManager() != null)
                    player.getControlerManager().moved();
                if (player.hasStarted())
                    checkControlersAtMove(player);
            } else {
                if (entity.getLastRegionId() > 0)
                    getRegion(entity.getLastRegionId()).removeNPCIndex(entity.getIndex());
                getRegion(regionId).addNPCIndex(entity.getIndex());
            }
            entity.checkMultiArea();
            entity.setLastRegionId(regionId);
        } else {
            if (entity instanceof Player) {
                Player player = (Player) entity;
                player.getControlerManager().moved();
                if (player.hasStarted())
                    checkControlersAtMove(player);
            }
            entity.checkMultiArea();
        }
    }

    private static void checkControlersAtMove(Player player) {
        if (!(player.getControlerManager().getControler() instanceof RequestController)
                && RequestController.inWarRequest(player))
            player.getControlerManager().startControler("clan_wars_request");
        else if (player.getRegionId() == 13363)
            player.getControlerManager().startControler("DuelControler");
        else if (FfaZone.inArea(player))
            player.getControlerManager().startControler("clan_wars_ffa");
    }

    /*
     * checks clip
     */
    public static boolean canMoveNPC(int plane, int x, int y, int size) {
        for (int tileX = x; tileX < x + size; tileX++)
            for (int tileY = y; tileY < y + size; tileY++)
                if (getMask(plane, tileX, tileY) != 0)
                    return false;
        return true;
    }

    /*
     * checks clip
     */
    public static boolean isNotCliped(int plane, int x, int y, int size) {
        for (int tileX = x; tileX < x + size; tileX++)
            for (int tileY = y; tileY < y + size; tileY++)
                if ((getMask(plane, tileX, tileY) & 2097152) != 0)
                    return false;
        return true;
    }

    public static boolean isClipped(int plane, int x, int y) {
        if ((getMask(plane, x, y) & 2097152) != 0)
            return true;
        return false;
    }

    public static void setMask(int plane, int x, int y, int mask) {
        WorldTile tile = new WorldTile(x, y, plane);
        int regionId = tile.getRegionId();
        Region region = getRegion(regionId);
        if (region == null)
            return;
        int baseLocalX = x - ((regionId >> 8) * 64);
        int baseLocalY = y - ((regionId & 0xff) * 64);
        region.setMask(tile.getPlane(), baseLocalX, baseLocalY, mask);
    }

    public static int getRotation(int plane, int x, int y) {
        WorldTile tile = new WorldTile(x, y, plane);
        int regionId = tile.getRegionId();
        Region region = getRegion(regionId);
        if (region == null)
            return 0;
        int baseLocalX = x - ((regionId >> 8) * 64);
        int baseLocalY = y - ((regionId & 0xff) * 64);
        return region.getRotation(tile.getPlane(), baseLocalX, baseLocalY);
    }

    /*
     * checks clip
     */
    public static boolean isRegionLoaded(int regionId) {
        Region region = getRegion(regionId);
        if (region == null)
            return false;
        return region.getLoadMapStage() == 2;
    }

    public static boolean isTileFree(int plane, int x, int y, int size) {
        for (int tileX = x; tileX < x + size; tileX++)
            for (int tileY = y; tileY < y + size; tileY++)
                if (!isFloorFree(plane, tileX, tileY) || !isWallsFree(plane, tileX, tileY))
                    return false;
        return true;
    }

    public static boolean isFloorFree(int plane, int x, int y) {
        return (getMask(plane, x, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ)) == 0;
    }

    public static boolean isWallsFree(int plane, int x, int y) {
        return (getMask(plane, x, y) & (Flags.CORNEROBJ_NORTHEAST | Flags.CORNEROBJ_NORTHWEST
                | Flags.CORNEROBJ_SOUTHEAST | Flags.CORNEROBJ_SOUTHWEST | Flags.WALLOBJ_EAST | Flags.WALLOBJ_NORTH
                | Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST)) == 0;
    }

    public static int getMask(int plane, int x, int y) {
        WorldTile tile = new WorldTile(x, y, plane);
        Region region = getRegion(tile.getRegionId());
        if (region == null) {
            return -1;
        }
        return region.getMask(tile.getPlane(), tile.getXInRegion(), tile.getYInRegion());
    }

    private static int getClipedOnlyMask(int plane, int x, int y) {
        WorldTile tile = new WorldTile(x, y, plane);
        Region region = getRegion(tile.getRegionId());
        if (region == null)
            return -1;
        return region.getMaskClipedOnly(tile.getPlane(), tile.getXInRegion(), tile.getYInRegion());
    }

    public static final boolean checkProjectileStep(int plane, int x, int y, int dir, int size) {
        int xOffset = Utils.DIRECTION_DELTA_X[dir];
        int yOffset = Utils.DIRECTION_DELTA_Y[dir];
        /*
         * int rotation = getRotation(plane,x+xOffset,y+yOffset); if(rotation != 0) {
         * dir += rotation; if(dir >= Utils.DIRECTION_DELTA_X.length) dir = dir -
         * (Utils.DIRECTION_DELTA_X.length-1); xOffset = Utils.DIRECTION_DELTA_X[dir];
         * yOffset = Utils.DIRECTION_DELTA_Y[dir]; }
         */
        if (size == 1) {
            int mask = getClipedOnlyMask(plane, x + Utils.DIRECTION_DELTA_X[dir], y + Utils.DIRECTION_DELTA_Y[dir]);
            if (xOffset == -1 && yOffset == 0)
                return (mask & 0x42240000) == 0;
            if (xOffset == 1 && yOffset == 0)
                return (mask & 0x60240000) == 0;
            if (xOffset == 0 && yOffset == -1)
                return (mask & 0x40a40000) == 0;
            if (xOffset == 0 && yOffset == 1)
                return (mask & 0x48240000) == 0;
            if (xOffset == -1 && yOffset == -1) {
                return (mask & 0x43a40000) == 0 && (getClipedOnlyMask(plane, x - 1, y) & 0x42240000) == 0
                        && (getClipedOnlyMask(plane, x, y - 1) & 0x40a40000) == 0;
            }
            if (xOffset == 1 && yOffset == -1) {
                return (mask & 0x60e40000) == 0 && (getClipedOnlyMask(plane, x + 1, y) & 0x60240000) == 0
                        && (getClipedOnlyMask(plane, x, y - 1) & 0x40a40000) == 0;
            }
            if (xOffset == -1 && yOffset == 1) {
                return (mask & 0x4e240000) == 0 && (getClipedOnlyMask(plane, x - 1, y) & 0x42240000) == 0
                        && (getClipedOnlyMask(plane, x, y + 1) & 0x48240000) == 0;
            }
            if (xOffset == 1 && yOffset == 1) {
                return (mask & 0x78240000) == 0 && (getClipedOnlyMask(plane, x + 1, y) & 0x60240000) == 0
                        && (getClipedOnlyMask(plane, x, y + 1) & 0x48240000) == 0;
            }
        } else if (size == 2) {
            if (xOffset == -1 && yOffset == 0)
                return (getClipedOnlyMask(plane, x - 1, y) & 0x43a40000) == 0
                        && (getClipedOnlyMask(plane, x - 1, y + 1) & 0x4e240000) == 0;
            if (xOffset == 1 && yOffset == 0)
                return (getClipedOnlyMask(plane, x + 2, y) & 0x60e40000) == 0
                        && (getClipedOnlyMask(plane, x + 2, y + 1) & 0x78240000) == 0;
            if (xOffset == 0 && yOffset == -1)
                return (getClipedOnlyMask(plane, x, y - 1) & 0x43a40000) == 0
                        && (getClipedOnlyMask(plane, x + 1, y - 1) & 0x60e40000) == 0;
            if (xOffset == 0 && yOffset == 1)
                return (getClipedOnlyMask(plane, x, y + 2) & 0x4e240000) == 0
                        && (getClipedOnlyMask(plane, x + 1, y + 2) & 0x78240000) == 0;
            if (xOffset == -1 && yOffset == -1)
                return (getClipedOnlyMask(plane, x - 1, y) & 0x4fa40000) == 0
                        && (getClipedOnlyMask(plane, x - 1, y - 1) & 0x43a40000) == 0
                        && (getClipedOnlyMask(plane, x, y - 1) & 0x63e40000) == 0;
            if (xOffset == 1 && yOffset == -1)
                return (getClipedOnlyMask(plane, x + 1, y - 1) & 0x63e40000) == 0
                        && (getClipedOnlyMask(plane, x + 2, y - 1) & 0x60e40000) == 0
                        && (getClipedOnlyMask(plane, x + 2, y) & 0x78e40000) == 0;
            if (xOffset == -1 && yOffset == 1)
                return (getClipedOnlyMask(plane, x - 1, y + 1) & 0x4fa40000) == 0
                        && (getClipedOnlyMask(plane, x - 1, y + 1) & 0x4e240000) == 0
                        && (getClipedOnlyMask(plane, x, y + 2) & 0x7e240000) == 0;
            if (xOffset == 1 && yOffset == 1)
                return (getClipedOnlyMask(plane, x + 1, y + 2) & 0x7e240000) == 0
                        && (getClipedOnlyMask(plane, x + 2, y + 2) & 0x78240000) == 0
                        && (getClipedOnlyMask(plane, x + 1, y + 1) & 0x78e40000) == 0;
        } else {
            if (xOffset == -1 && yOffset == 0) {
                if ((getClipedOnlyMask(plane, x - 1, y) & 0x43a40000) != 0
                        || (getClipedOnlyMask(plane, x - 1, -1 + (y + size)) & 0x4e240000) != 0)
                    return false;
                for (int sizeOffset = 1; sizeOffset < size - 1; sizeOffset++)
                    if ((getClipedOnlyMask(plane, x - 1, y + sizeOffset) & 0x4fa40000) != 0)
                        return false;
            } else if (xOffset == 1 && yOffset == 0) {
                if ((getClipedOnlyMask(plane, x + size, y) & 0x60e40000) != 0
                        || (getClipedOnlyMask(plane, x + size, y - (-size + 1)) & 0x78240000) != 0)
                    return false;
                for (int sizeOffset = 1; sizeOffset < size - 1; sizeOffset++)
                    if ((getClipedOnlyMask(plane, x + size, y + sizeOffset) & 0x78e40000) != 0)
                        return false;
            } else if (xOffset == 0 && yOffset == -1) {
                if ((getClipedOnlyMask(plane, x, y - 1) & 0x43a40000) != 0
                        || (getClipedOnlyMask(plane, x + size - 1, y - 1) & 0x60e40000) != 0)
                    return false;
                for (int sizeOffset = 1; sizeOffset < size - 1; sizeOffset++)
                    if ((getClipedOnlyMask(plane, x + sizeOffset, y - 1) & 0x63e40000) != 0)
                        return false;
            } else if (xOffset == 0 && yOffset == 1) {
                if ((getClipedOnlyMask(plane, x, y + size) & 0x4e240000) != 0
                        || (getClipedOnlyMask(plane, x + (size - 1), y + size) & 0x78240000) != 0)
                    return false;
                for (int sizeOffset = 1; sizeOffset < size - 1; sizeOffset++)
                    if ((getClipedOnlyMask(plane, x + sizeOffset, y + size) & 0x7e240000) != 0)
                        return false;
            } else if (xOffset == -1 && yOffset == -1) {
                if ((getClipedOnlyMask(plane, x - 1, y - 1) & 0x43a40000) != 0)
                    return false;
                for (int sizeOffset = 1; sizeOffset < size; sizeOffset++)
                    if ((getClipedOnlyMask(plane, x - 1, y + (-1 + sizeOffset)) & 0x4fa40000) != 0
                            || (getClipedOnlyMask(plane, sizeOffset - 1 + x, y - 1) & 0x63e40000) != 0)
                        return false;
            } else if (xOffset == 1 && yOffset == -1) {
                if ((getClipedOnlyMask(plane, x + size, y - 1) & 0x60e40000) != 0)
                    return false;
                for (int sizeOffset = 1; sizeOffset < size; sizeOffset++)
                    if ((getClipedOnlyMask(plane, x + size, sizeOffset + (-1 + y)) & 0x78e40000) != 0
                            || (getClipedOnlyMask(plane, x + sizeOffset, y - 1) & 0x63e40000) != 0)
                        return false;
            } else if (xOffset == -1 && yOffset == 1) {
                if ((getClipedOnlyMask(plane, x - 1, y + size) & 0x4e240000) != 0)
                    return false;
                for (int sizeOffset = 1; sizeOffset < size; sizeOffset++)
                    if ((getClipedOnlyMask(plane, x - 1, y + sizeOffset) & 0x4fa40000) != 0
                            || (getClipedOnlyMask(plane, -1 + (x + sizeOffset), y + size) & 0x7e240000) != 0)
                        return false;
            } else if (xOffset == 1 && yOffset == 1) {
                if ((getClipedOnlyMask(plane, x + size, y + size) & 0x78240000) != 0)
                    return false;
                for (int sizeOffset = 1; sizeOffset < size; sizeOffset++)
                    if ((getClipedOnlyMask(plane, x + sizeOffset, y + size) & 0x7e240000) != 0
                            || (getClipedOnlyMask(plane, x + size, y + sizeOffset) & 0x78e40000) != 0)
                        return false;
            }
        }
        return true;
    }

    public static final boolean checkWalkStep(int plane, int x, int y, int dir, int size) {
        return checkWalkStep(plane, x, y, Utils.DIRECTION_DELTA_X[dir], Utils.DIRECTION_DELTA_Y[dir], size);
    }

    public static Player[] getNearPlayers(Player player, int distance, int maxTargets) {
        List<Entity> possibleTargets = new ArrayList<Entity>();
        stop:
        for (int regionId : player.getMapRegionsIds()) {
            Region region = World.getRegion(regionId);
            List<Integer> playerIndexes = region.getPlayerIndexes();
            if (playerIndexes == null)
                continue;
            for (int playerIndex : playerIndexes) {
                Player p2 = World.getPlayers().get(playerIndex);
                if (p2 == null || p2 == player || p2.isDead() || !p2.hasStarted() || p2.hasFinished()
                        || !p2.withinDistance(player, distance))
                    continue;
                possibleTargets.add(p2);
                if (possibleTargets.size() == maxTargets)
                    break stop;
            }
        }
        return possibleTargets.toArray(new Player[possibleTargets.size()]);
    }

    public static final boolean checkWalkStep(int plane, int x, int y, int xOffset, int yOffset, int size) {
        if (size == 1) {
            int mask = getMask(plane, x + xOffset, y + yOffset);
            if (xOffset == -1 && yOffset == 0)
                return (mask
                        & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_EAST)) == 0;
            if (xOffset == 1 && yOffset == 0)
                return (mask
                        & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_WEST)) == 0;
            if (xOffset == 0 && yOffset == -1)
                return (mask
                        & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH)) == 0;
            if (xOffset == 0 && yOffset == 1)
                return (mask
                        & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_SOUTH)) == 0;
            if (xOffset == -1 && yOffset == -1)
                return (mask & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH
                        | Flags.WALLOBJ_EAST | Flags.CORNEROBJ_NORTHEAST)) == 0
                        && (getMask(plane, x - 1, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_EAST)) == 0
                        && (getMask(plane, x, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_NORTH)) == 0;
            if (xOffset == 1 && yOffset == -1)
                return (mask & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH
                        | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST)) == 0
                        && (getMask(plane, x + 1, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_WEST)) == 0
                        && (getMask(plane, x, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_NORTH)) == 0;
            if (xOffset == -1 && yOffset == 1)
                return (mask & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_EAST
                        | Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_SOUTHEAST)) == 0
                        && (getMask(plane, x - 1, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_EAST)) == 0
                        && (getMask(plane, x, y + 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_SOUTH)) == 0;
            if (xOffset == 1 && yOffset == 1)
                return (mask & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_SOUTH
                        | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHWEST)) == 0
                        && (getMask(plane, x + 1, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_WEST)) == 0
                        && (getMask(plane, x, y + 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_SOUTH)) == 0;
        } else if (size == 2) {
            if (xOffset == -1 && yOffset == 0)
                return (getMask(plane, x - 1, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.CORNEROBJ_NORTHEAST)) == 0
                        && (getMask(plane, x - 1, y + 1)
                        & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_EAST
                        | Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_SOUTHEAST)) == 0;
            if (xOffset == 1 && yOffset == 0)
                return (getMask(plane, x + 2, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST)) == 0
                        && (getMask(plane, x + 2, y + 1)
                        & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_SOUTH
                        | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHWEST)) == 0;
            if (xOffset == 0 && yOffset == -1)
                return (getMask(plane, x, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.CORNEROBJ_NORTHEAST)) == 0
                        && (getMask(plane, x + 1, y - 1)
                        & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH
                        | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST)) == 0;
            if (xOffset == 0 && yOffset == 1)
                return (getMask(plane, x, y + 2) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_SOUTHEAST)) == 0
                        && (getMask(plane, x + 1, y + 2)
                        & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_SOUTH
                        | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHWEST)) == 0;
            if (xOffset == -1 && yOffset == -1)
                return (getMask(plane, x - 1, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_NORTHEAST
                        | Flags.CORNEROBJ_SOUTHEAST)) == 0
                        && (getMask(plane, x - 1, y - 1)
                        & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH
                        | Flags.WALLOBJ_EAST | Flags.CORNEROBJ_NORTHEAST)) == 0
                        && (getMask(plane, x, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.WALLOBJ_WEST
                        | Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_NORTHEAST)) == 0;
            if (xOffset == 1 && yOffset == -1)
                return (getMask(plane, x + 1, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST
                        | Flags.CORNEROBJ_NORTHEAST)) == 0
                        && (getMask(plane, x + 2, y - 1)
                        & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH
                        | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST)) == 0
                        && (getMask(plane, x + 2, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST
                        | Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_SOUTHWEST)) == 0;
            if (xOffset == -1 && yOffset == 1)
                return (getMask(plane, x - 1, y + 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_NORTHEAST
                        | Flags.CORNEROBJ_SOUTHEAST)) == 0
                        && (getMask(plane, x - 1, y + 1)
                        & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_EAST
                        | Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_SOUTHEAST)) == 0
                        && (getMask(plane, x, y + 2) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST
                        | Flags.CORNEROBJ_SOUTHEAST | Flags.CORNEROBJ_SOUTHWEST)) == 0;
            if (xOffset == 1 && yOffset == 1)
                return (getMask(plane, x + 1, y + 2) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHEAST
                        | Flags.CORNEROBJ_SOUTHWEST)) == 0
                        && (getMask(plane, x + 2, y + 2)
                        & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_SOUTH
                        | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHWEST)) == 0
                        && (getMask(plane, x + 1, y + 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK
                        | Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST
                        | Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_SOUTHWEST)) == 0;
        } else {
            if (xOffset == -1 && yOffset == 0) {
                if ((getMask(plane, x - 1, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.CORNEROBJ_NORTHEAST)) != 0
                        || (getMask(plane, x - 1, -1 + (y + size))
                        & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_EAST
                        | Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_SOUTHEAST)) != 0)
                    return false;
                for (int sizeOffset = 1; sizeOffset < size - 1; sizeOffset++)
                    if ((getMask(plane, x - 1, y + sizeOffset) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK
                            | Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH
                            | Flags.CORNEROBJ_NORTHEAST | Flags.CORNEROBJ_SOUTHEAST)) != 0)
                        return false;
            } else if (xOffset == 1 && yOffset == 0) {
                if ((getMask(plane, x + size, y) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST)) != 0
                        || (getMask(plane, x + size, y - (-size + 1))
                        & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_SOUTH
                        | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHWEST)) != 0)
                    return false;
                for (int sizeOffset = 1; sizeOffset < size - 1; sizeOffset++)
                    if ((getMask(plane, x + size, y + sizeOffset) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK
                            | Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST
                            | Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_SOUTHWEST)) != 0)
                        return false;
            } else if (xOffset == 0 && yOffset == -1) {
                if ((getMask(plane, x, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.CORNEROBJ_NORTHEAST)) != 0
                        || (getMask(plane, x + size - 1, y - 1)
                        & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH
                        | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST)) != 0)
                    return false;
                for (int sizeOffset = 1; sizeOffset < size - 1; sizeOffset++)
                    if ((getMask(plane, x + sizeOffset, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK
                            | Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.WALLOBJ_WEST
                            | Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_NORTHEAST)) != 0)
                        return false;
            } else if (xOffset == 0 && yOffset == 1) {
                if ((getMask(plane, x, y + size) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_SOUTHEAST)) != 0
                        || (getMask(plane, x + (size - 1), y + size)
                        & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_SOUTH
                        | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHWEST)) != 0)
                    return false;
                for (int sizeOffset = 1; sizeOffset < size - 1; sizeOffset++)
                    if ((getMask(plane, x + sizeOffset, y + size) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK
                            | Flags.OBJ | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST
                            | Flags.CORNEROBJ_SOUTHEAST | Flags.CORNEROBJ_SOUTHWEST)) != 0)
                        return false;
            } else if (xOffset == -1 && yOffset == -1) {
                if ((getMask(plane, x - 1, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.CORNEROBJ_NORTHEAST)) != 0)
                    return false;
                for (int sizeOffset = 1; sizeOffset < size; sizeOffset++)
                    if ((getMask(plane, x - 1, y + (-1 + sizeOffset)) & (Flags.FLOOR_BLOCKSWALK
                            | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST
                            | Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_NORTHEAST | Flags.CORNEROBJ_SOUTHEAST)) != 0
                            || (getMask(plane, sizeOffset - 1 + x, y - 1) & (Flags.FLOOR_BLOCKSWALK
                            | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST
                            | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_NORTHEAST)) != 0)
                        return false;
            } else if (xOffset == 1 && yOffset == -1) {
                if ((getMask(plane, x + size, y - 1) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST)) != 0)
                    return false;
                for (int sizeOffset = 1; sizeOffset < size; sizeOffset++)
                    if ((getMask(plane, x + size, sizeOffset + (-1 + y)) & (Flags.FLOOR_BLOCKSWALK
                            | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_SOUTH
                            | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_SOUTHWEST)) != 0
                            || (getMask(plane, x + sizeOffset, y - 1) & (Flags.FLOOR_BLOCKSWALK
                            | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST
                            | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_NORTHEAST)) != 0)
                        return false;
            } else if (xOffset == -1 && yOffset == 1) {
                if ((getMask(plane, x - 1, y + size) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ
                        | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.CORNEROBJ_SOUTHEAST)) != 0)
                    return false;
                for (int sizeOffset = 1; sizeOffset < size; sizeOffset++)
                    if ((getMask(plane, x - 1, y + sizeOffset) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK
                            | Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH
                            | Flags.CORNEROBJ_NORTHEAST | Flags.CORNEROBJ_SOUTHEAST)) != 0
                            || (getMask(plane, -1 + (x + sizeOffset), y + size) & (Flags.FLOOR_BLOCKSWALK
                            | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH
                            | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHEAST | Flags.CORNEROBJ_SOUTHWEST)) != 0)
                        return false;
            } else if (xOffset == 1 && yOffset == 1) {
                if ((getMask(plane, x + size, y + size) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK
                        | Flags.OBJ | Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_SOUTHWEST)) != 0)
                    return false;
                for (int sizeOffset = 1; sizeOffset < size; sizeOffset++)
                    if ((getMask(plane, x + sizeOffset, y + size) & (Flags.FLOOR_BLOCKSWALK | Flags.FLOORDECO_BLOCKSWALK
                            | Flags.OBJ | Flags.WALLOBJ_EAST | Flags.WALLOBJ_SOUTH | Flags.WALLOBJ_WEST
                            | Flags.CORNEROBJ_SOUTHEAST | Flags.CORNEROBJ_SOUTHWEST)) != 0
                            || (getMask(plane, x + size, y + sizeOffset) & (Flags.FLOOR_BLOCKSWALK
                            | Flags.FLOORDECO_BLOCKSWALK | Flags.OBJ | Flags.WALLOBJ_NORTH | Flags.WALLOBJ_SOUTH
                            | Flags.WALLOBJ_WEST | Flags.CORNEROBJ_NORTHWEST | Flags.CORNEROBJ_SOUTHWEST)) != 0)
                        return false;
            }
        }
        return true;
    }

    public static final boolean containsPlayer(String username) {
        for (Player p2 : players) {
            if (p2 == null)
                continue;
            if (p2.getUsername().equals(username))
                return true;
        }
        return false;
    }

    public static Player getPlayer(String username) {
        for (Player player : getPlayers()) {
            if (player == null)
                continue;
            if (player.getUsername().equals(username))
                return player;
        }
        return null;
    }

    public static final Player getPlayerByDisplayName(String username) {
        String formatedUsername = Utils.formatPlayerNameForDisplay(username);
        for (Player player : getPlayers()) {
            if (player == null)
                continue;
            if (player.getUsername().equalsIgnoreCase(formatedUsername)
                    || player.getDisplayName().equalsIgnoreCase(formatedUsername))
                return player;
        }
        return null;
    }

    public static final EntityList<Player> getPlayers() {
        return players;
    }

    public static final EntityList<NPC> getNPCs() {
        return npcs;
    }

    private World() {

    }

    public static final void setUpdateTime(int newTime) {
        if (exiting_start == 0) {
            // System.out.println("There was an error setting the new update time.");
            return;
        }
        exiting_start = Utils.currentTimeMillis();
        exiting_delay = newTime;
        for (Player player : World.getPlayers()) {
            if (player == null || !player.hasStarted() || player.hasFinished())
                continue;
            player.getPackets().sendSystemUpdate(newTime);
        }
        CoresManager.slowExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    for (Player player : World.getPlayers()) {
                        if (player == null || !player.hasStarted())
                            continue;
                        player.realFinish();
                    }
                    IPBanL.save();
                    GrandExchange.save();
                    // PlayerOwnedShops.save();
                    Launcher.shutdown();
                } catch (Throwable e) {
                    Logger.handle(e);
                }
            }
        }, newTime, TimeUnit.SECONDS);
    }

    public static final void safeShutdown(int delay) {
        if (exiting_start != 0)
            return;
        exiting_start = Utils.currentTimeMillis();
        exiting_delay = delay;
        for (Player player : World.getPlayers()) {
            if (player == null || !player.hasStarted() || player.hasFinished())
                continue;
            player.getPackets().sendSystemUpdate(delay);
        }
        CoresManager.slowExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    for (Player player : World.getPlayers()) {
                        if (player == null || !player.hasStarted())
                            continue;
                        player.realFinish();
                    }
                    IPBanL.save();
                    GrandExchange.save();
                    // PlayerOwnedShops.save();
                    Launcher.shutdown();
                } catch (Throwable e) {
                    Logger.handle(e);
                }
            }
        }, delay, TimeUnit.SECONDS);
    }

    public static final void safeRestart(int delay) {
        if (exiting_start != 0)
            return;
        exiting_start = Utils.currentTimeMillis();
        exiting_delay = delay;
        for (Player player : World.getPlayers()) {
            if (player == null || !player.hasStarted() || player.hasFinished())
                continue;
            player.getPackets().sendSystemUpdate(delay);
            isInUpdate = true;
            if (delay == 5) {
                player.getSession().getChannel().disconnect();
            }
        }
        CoresManager.slowExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    for (Player player : World.getPlayers()) {
                        if (player == null || !player.hasStarted())
                            continue;
                        player.realFinish();
                    }
                    isInUpdate = false;
                    GrandExchange.save();
                    // PlayerOwnedShops.save();
                    Launcher.restart();
                } catch (Throwable e) {
                    Logger.handle(e);
                }
            }
        }, delay, TimeUnit.SECONDS);
    }

    public static final boolean isSpawnedObject(WorldObject object) {
        return getRegion(object.getRegionId()).getSpawnedObjects().contains(object);
    }

    public static final void spawnObject(WorldObject object) {
        getRegion(object.getRegionId()).spawnObject(object, object.getPlane(), object.getXInRegion(),
                object.getYInRegion(), false);
    }

    public static final void unclipTile(WorldTile tile) {
        getRegion(tile.getRegionId()).unclip(tile.getPlane(), tile.getXInRegion(), tile.getYInRegion());
    }

    public static final void removeObject(WorldObject object) {
        getRegion(object.getRegionId()).removeObject(object, object.getPlane(), object.getXInRegion(),
                object.getYInRegion());
    }

    public static final void spawnObjectTemporary(final WorldObject object, long time) {
        spawnObjectTemporary(object, time, false, false);
    }

    public static final void spawnObjectTemporary(final WorldObject object, long time,
                                                  final boolean checkObjectInstance, boolean checkObjectBefore) {
        final WorldObject before = checkObjectBefore ? World.getObjectWithType(object, object.getType()) : null;
        spawnObject(object);
        CoresManager.slowExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    if (checkObjectInstance && World.getObjectWithId(object, object.getId()) != object)
                        return;
                    if (before != null)
                        spawnObject(before);
                    else
                        removeObject(object);
                } catch (Throwable e) {
                    Logger.handle(e);
                }
            }

        }, time, TimeUnit.MILLISECONDS);
    }

    public static final boolean removeObjectTemporary(final WorldObject object, long time) {
        removeObject(object);
        CoresManager.slowExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    spawnObject(object);
                } catch (Throwable e) {
                    Logger.handle(e);
                }
            }

        }, time, TimeUnit.MILLISECONDS);
        return true;
    }

    public static final void spawnTempGroundObject(final WorldObject object, final int replaceId, long time) {
        spawnObject(object);
        CoresManager.slowExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    removeObject(object);
                    addGroundItem(new Item(replaceId), object, null, false, 180);
                } catch (Throwable e) {
                    Logger.handle(e);
                }
            }
        }, time, TimeUnit.MILLISECONDS);
    }

    public static final WorldObject getStandardFloorObject(WorldTile tile) {
        return getRegion(tile.getRegionId()).getStandardFloorObject(tile.getPlane(), tile.getXInRegion(),
                tile.getYInRegion());
    }

    public static final WorldObject getStandardFloorDecoration(WorldTile tile) {
        return getRegion(tile.getRegionId()).getStandardFloorDecoration(tile.getPlane(), tile.getXInRegion(),
                tile.getYInRegion());
    }

    public static final WorldObject getStandardWallDecoration(WorldTile tile) {
        return getRegion(tile.getRegionId()).getStandardWallDecoration(tile.getPlane(), tile.getXInRegion(),
                tile.getYInRegion());
    }

    public static final void spawnObject(WorldObject object, boolean clip) {
        getRegion(object.getRegionId()).spawnObject(object, object.getPlane(), object.getXInRegion(), object.getYInRegion(), false);
    }

    public static final WorldObject getStandardWallObject(WorldTile tile) {
        return getRegion(tile.getRegionId()).getStandardWallObject(tile.getPlane(), tile.getXInRegion(),
                tile.getYInRegion());
    }

    public static final WorldObject getObjectWithType(WorldTile tile, int type) {
        return getRegion(tile.getRegionId()).getObjectWithType(tile.getPlane(), tile.getXInRegion(),
                tile.getYInRegion(), type);
    }

    public static final WorldObject getObjectWithSlot(WorldTile tile, int slot) {
        return getRegion(tile.getRegionId()).getObjectWithSlot(tile.getPlane(), tile.getXInRegion(),
                tile.getYInRegion(), slot);
    }

    public static final WorldObject getRealObject(WorldTile tile, int slot) {
        return getRegion(tile.getRegionId()).getRealObject(tile.getPlane(), tile.getXInRegion(), tile.getYInRegion(),
                slot);
    }

    public static final boolean containsObjectWithId(WorldTile tile, int id) {
        return getRegion(tile.getRegionId()).containsObjectWithId(tile.getPlane(), tile.getXInRegion(),
                tile.getYInRegion(), id);
    }

    public static final WorldObject getObjectWithId(WorldTile tile, int id) {
        return getRegion(tile.getRegionId()).getObjectWithId(tile.getPlane(), tile.getXInRegion(), tile.getYInRegion(),
                id);
    }

    public static final boolean isSpawnedObject(Player player, WorldObject object) {
        return getRegion(player.getRegionId()).getSpawnedObjects().contains(object);
    }

    public static final void removeObject(WorldObject object, boolean removeClip) {
        getRegion(object.getRegionId()).removeObject(object, object.getPlane(), object.getXInRegion(),
                object.getYInRegion(), removeClip);
    }

    public static final void spawnObjectTemporaryNewItem(final WorldObject object, long time, int newId) {
        WorldObject newObject = new WorldObject(newId, object.getType(), object.getRotation(), object.getX(),
                object.getY(), object.getPlane());
        spawnObject(object);
        CoresManager.slowExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    if (!World.isSpawnedObject(object))
                        return;
                    spawnObject(newObject);
                } catch (Throwable e) {
                    Logger.handle(e);
                }
            }

        }, time, TimeUnit.MILLISECONDS);
    }

    public static final boolean removeObjectTemporary(final WorldObject object, long time, boolean removeClip) {
        removeObject(object, removeClip);
        CoresManager.slowExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    // if (!World.containsObjectWithId(new WorldTile(object.getTileHash()),
                    // object.getId()))
                    spawnObject(object);
                } catch (Throwable e) {
                    Logger.handle(e);
                }
            }

        }, time, TimeUnit.MILLISECONDS);
        return true;
    }

    public static final void spawnTempGroundObject(final WorldObject object, final int replaceId, long time,
                                                   final boolean removeClip) {
        spawnObject(object);
        CoresManager.slowExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    removeObject(object, removeClip);
                    addGroundItem(new Item(replaceId), object, null, false, 180);
                } catch (Throwable e) {
                    Logger.handle(e);
                }
            }
        }, time, TimeUnit.MILLISECONDS);
    }

    public static final void addGlobalGroundItem(final Item item, final WorldTile tile, final int tick,
                                                 final boolean spawned) {
        FloorItem floorItem = World.getRegion(tile.getRegionId()).getGroundItem(item.getId(), tile, null);
        if (floorItem == null) {
            floorItem = new FloorItem(item, tile, null, false, tick, spawned);
            final Region region = getRegion(tile.getRegionId());
            if (floorItem.isGlobalPicked())
                return;
            region.getGroundItemsSafe().add(floorItem);
            int regionId = tile.getRegionId();
            for (Player player : players) {
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || player.getPlane() != tile.getPlane() || !player.getMapRegionsIds().contains(regionId)
                        || player.getRegionId() != region.getRegionId())
                    continue;
                player.getPackets().sendGroundItem(floorItem);
            }
        }
    }

    public static final void addGroundItem(final Item item, final WorldTile tile) {
        addGroundItem(item, tile, null, false, -1, 2, -1);
    }

    public static final void addGroundItem(final Item item, final WorldTile tile, int publicTime) {
        addGroundItem(item, tile, null, false, -1, 2, publicTime);
    }

    public static final void addGroundItem(final Item item, final WorldTile tile, final Player owner, boolean invisible,
                                           long hiddenTime) {
        addGroundItem(item, tile, owner, invisible, hiddenTime, 2, 60);
    }

    public static final FloorItem addGroundItem(final Item item, final WorldTile tile, final Player owner,
                                                boolean invisible, long hiddenTime, int type) {
        return addGroundItem(item, tile, owner, invisible, hiddenTime, type, 60);
    }

    public static final FloorItem addGroundItem(final Item item, final WorldTile tile, final Player owner,
                                                boolean invisible, long hiddenTime, int type, String ironmanName) {
        return addGroundItem(item, tile, owner, invisible, hiddenTime, type, 60, ironmanName);
    }

    public static final void turnPublic(FloorItem floorItem, int publicTime) {
        if (!floorItem.isInvisible())
            return;
        int regionId = floorItem.getTile().getRegionId();
        final Region region = getRegion(regionId);
        if (!region.getGroundItemsSafe().contains(floorItem))
            return;
        Player realOwner = floorItem.hasOwner() ? World.getPlayer(floorItem.getOwner()) : null;
        if (ItemConstants.removeAttachedId(floorItem) != -1) {
            if (ItemConstants.removeAttachedId2(floorItem) != -1)
                World.updateGroundItem(new Item(ItemConstants.removeAttachedId2(floorItem), 1),
                        new WorldTile(floorItem.getTile()), realOwner, 1, 0);
            removeGroundItem(floorItem, 0);
            World.updateGroundItem(new Item(ItemConstants.removeAttachedId(floorItem), 1), floorItem.getTile(),
                    realOwner, 1, 0);
            return;
        }
        floorItem.setInvisible(false);
        if (realOwner != null) {
            if (realOwner.getBeam() != null && realOwner.getBeamItem() != null) {
                if (floorItem.getTile().matches(realOwner.getBeam())
                        && realOwner.getBeamItem().getId() == floorItem.getId()) {
                    realOwner.setBeam(null);
                    realOwner.setBeamItem(null);
                    // System.out.println("Removed beam due to turnt public.");
                }
            }
        }
        for (Player player : players) {
            if (player == null || player == realOwner || !player.hasStarted() || player.hasFinished()
                    || player.getPlane() != floorItem.getTile().getPlane()
                    || !player.getMapRegionsIds().contains(regionId) || !ItemConstants.isTradeable(floorItem))
                continue;
            player.getPackets().sendGroundItem(floorItem);
        }
        if (publicTime != -1)
            removeGroundItem(floorItem, publicTime);
    }

    @Deprecated
    public static final void addGroundItemForever(Item item, final WorldTile tile) {
        int regionId = tile.getRegionId();
        final FloorItem floorItem = new FloorItem(item, tile, true);
        final Region region = getRegion(tile.getRegionId());
        region.getGroundItemsSafe().add(floorItem);
        for (Player player : players) {
            if (player == null || !player.hasStarted() || player.hasFinished()
                    || player.getPlane() != floorItem.getTile().getPlane()
                    || !player.getMapRegionsIds().contains(regionId))
                continue;
            player.getPackets().sendGroundItem(floorItem);
        }
    }

    /*
     * type 0 - gold if not tradeable type 1 - gold if destroyable type 2 - no gold
     */
    public static final FloorItem addGroundItem(final Item item, final WorldTile tile, final Player owner,
                                                boolean invisible, long hiddenTime, int type, final int publicTime) {
        final FloorItem floorItem = new FloorItem(item, tile, owner, false, invisible);
        final Region region = getRegion(tile.getRegionId());
        if (type == 1) {
            if (ItemConstants.isTradeable(item) || ItemConstants.turnCoins(item)) {
                region.getGroundItemsSafe().add(floorItem);
            }
            if (invisible) {
                if (owner != null) {
                    if (ItemConstants.isTradeable(item) || ItemConstants.turnCoins(item)) {
                        owner.getPackets().sendGroundItem(floorItem);

                    }
                }
                if (hiddenTime != -1) {
                    CoresManager.slowExecutor.schedule(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                turnPublic(floorItem, publicTime);
                            } catch (Throwable e) {
                                Logger.handle(e);
                            }
                        }
                    }, hiddenTime, TimeUnit.SECONDS);
                }
            } else {
                int regionId = tile.getRegionId();
                for (Player player : players) {
                    if (player == null || !player.hasStarted() || player.hasFinished()
                            || player.getPlane() != tile.getPlane() || !player.getMapRegionsIds().contains(regionId))
                        continue;
                    player.getPackets().sendGroundItem(floorItem);
                }
                if (publicTime != -1)
                    removeGroundItem(floorItem, publicTime);
            }
        } else {
            region.getGroundItemsSafe().add(floorItem);
            if (invisible) {
                if (owner != null) {
                    owner.getPackets().sendGroundItem(floorItem);
                }
                if (hiddenTime != -1) {
                    CoresManager.slowExecutor.schedule(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                turnPublic(floorItem, publicTime);
                            } catch (Throwable e) {
                                Logger.handle(e);
                            }
                        }
                    }, hiddenTime, TimeUnit.SECONDS);
                }
            } else {
                int regionId = tile.getRegionId();
                for (Player player : players) {
                    if (player == null || !player.hasStarted() || player.hasFinished()
                            || player.getPlane() != tile.getPlane() || !player.getMapRegionsIds().contains(regionId)
                            || !ItemConstants.isTradeable(item))
                        continue;
                    player.getPackets().sendGroundItem(floorItem);
                }
                if (publicTime != -1)
                    removeGroundItem(floorItem, publicTime);
            }
        }
        return floorItem;
    }

    public static final FloorItem addGroundItem(final Item item, final WorldTile tile, final Player owner,
                                                boolean invisible, long hiddenTime, int type, final int publicTime, String ironmanName) {
        final FloorItem floorItem = new FloorItem(item, tile, owner, false, invisible, ironmanName);
        final Region region = getRegion(tile.getRegionId());
        if (type == 1) {
            if (ItemConstants.isTradeable(item) || ItemConstants.turnCoins(item)) {
                region.getGroundItemsSafe().add(floorItem);
            }
            if (invisible) {
                if (owner != null) {
                    if (ItemConstants.isTradeable(item) || ItemConstants.turnCoins(item)) {
                        owner.getPackets().sendGroundItem(floorItem);

                    }
                }
                if (hiddenTime != -1) {
                    CoresManager.slowExecutor.schedule(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                turnPublic(floorItem, publicTime);
                            } catch (Throwable e) {
                                Logger.handle(e);
                            }
                        }
                    }, hiddenTime, TimeUnit.SECONDS);
                }
            } else {
                int regionId = tile.getRegionId();
                for (Player player : players) {
                    if (player == null || !player.hasStarted() || player.hasFinished()
                            || player.getPlane() != tile.getPlane() || !player.getMapRegionsIds().contains(regionId))
                        continue;
                    player.getPackets().sendGroundItem(floorItem);
                }
                if (publicTime != -1)
                    removeGroundItem(floorItem, publicTime);
            }
        } else {
            region.getGroundItemsSafe().add(floorItem);
            if (invisible) {
                if (owner != null) {
                    owner.getPackets().sendGroundItem(floorItem);
                }
                if (hiddenTime != -1) {
                    CoresManager.slowExecutor.schedule(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                turnPublic(floorItem, publicTime);
                            } catch (Throwable e) {
                                Logger.handle(e);
                            }
                        }
                    }, hiddenTime, TimeUnit.SECONDS);
                }
            } else {
                int regionId = tile.getRegionId();
                for (Player player : players) {
                    if (player == null || !player.hasStarted() || player.hasFinished()
                            || player.getPlane() != tile.getPlane() || !player.getMapRegionsIds().contains(regionId)
                            || !ItemConstants.isTradeable(item))
                        continue;
                    player.getPackets().sendGroundItem(floorItem);
                }
                if (publicTime != -1)
                    removeGroundItem(floorItem, publicTime);
            }
        }
        return floorItem;
    }

    public static final void updateGroundItem(Item item, final WorldTile tile, final Player owner) {
        final FloorItem floorItem = World.getRegion(tile.getRegionId()).getGroundItem(item.getId(), tile, owner);
        if (floorItem == null) {
            addGroundItem(item, tile, owner, true, 60);
            return;
        }
        /*
         * owner.getPackets().sendGameMessage( "FloorItem " +
         * floorItem.getDefinitions().getName() + " set from " + floorItem.getAmount() +
         * " to " + (floorItem.getAmount() + item.getAmount()) + "");
         */
        floorItem.setAmount(floorItem.getAmount() + item.getAmount());
        /*
         * addGroundItem(floorItem, tile, owner, true, 60);
         */

    }

    public static final void updateGroundItem(Item item, final WorldTile tile, final Player owner, final int time,
                                              final int type) {
        final FloorItem floorItem = World.getRegion(tile.getRegionId()).getGroundItem(item.getId(), tile, owner);
        if (floorItem == null) {
            if (item.getAmount() != 1 && !item.getDefinitions().isStackable() && !item.getDefinitions().isNoted()) {
                for (int i = 0; i < item.getAmount(); i++) {
                    addGroundItem(new Item(item.getId(), 1), tile, owner, true, time, type);
                }
                return;
            } else {
                addGroundItem(item, tile, owner, true, time, type);
                return;
            }
        }
        if (floorItem.getDefinitions().isStackable() || floorItem.getDefinitions().isNoted()) {
            if (floorItem.getAmount() + item.getAmount() < 0
                    || floorItem.getAmount() + item.getAmount() > Integer.MAX_VALUE) {
                int totalAmount = Integer.MAX_VALUE - floorItem.getAmount();
                floorItem.setAmount(Integer.MAX_VALUE);
                item.setAmount(item.getAmount() - totalAmount);
                addGroundItem(item, tile, owner, true, time, type);
                owner.getPackets().sendRemoveGroundItem(floorItem);
                owner.getPackets().sendGroundItem(floorItem);
            } else
                floorItem.setAmount(floorItem.getAmount() + item.getAmount());
            owner.getPackets().sendRemoveGroundItem(floorItem);
            owner.getPackets().sendGroundItem(floorItem);
        } else {
            if (item.getAmount() != 1) {
                for (int i = 0; i < item.getAmount(); i++) {
                    addGroundItem(new Item(item.getId(), 1), tile, owner, true, time, type);
                }
                return;
            } else {
                addGroundItem(item, tile, owner, true, time, type);
                return;
            }
        }
    }

    public static final void updateGroundItem(Item item, final WorldTile tile, final Player owner, final int time,
                                              final int type, String ironmanName) {
        final FloorItem floorItem = World.getRegion(tile.getRegionId()).getGroundItem(item.getId(), tile, owner);
        if (floorItem == null) {
            if (item.getAmount() != 1 && !item.getDefinitions().isStackable() && !item.getDefinitions().isNoted()) {
                for (int i = 0; i < item.getAmount(); i++) {
                    addGroundItem(new Item(item.getId(), 1), tile, owner, true, time, type, ironmanName);
                }
                return;
            } else {
                addGroundItem(item, tile, owner, true, time, type, ironmanName);
                return;
            }
        }
        if (floorItem.getDefinitions().isStackable() || floorItem.getDefinitions().isNoted()) {
            if (floorItem.getAmount() + item.getAmount() < 0
                    || floorItem.getAmount() + item.getAmount() > Integer.MAX_VALUE) {
                int totalAmount = Integer.MAX_VALUE - floorItem.getAmount();
                floorItem.setAmount(Integer.MAX_VALUE);
                item.setAmount(item.getAmount() - totalAmount);
                addGroundItem(item, tile, owner, true, time, type, ironmanName);
                owner.getPackets().sendRemoveGroundItem(floorItem);
                owner.getPackets().sendGroundItem(floorItem);
            } else
                floorItem.setAmount(floorItem.getAmount() + item.getAmount());
            owner.getPackets().sendRemoveGroundItem(floorItem);
            owner.getPackets().sendGroundItem(floorItem);
        } else {
            if (item.getAmount() != 1) {
                for (int i = 0; i < item.getAmount(); i++) {
                    addGroundItem(new Item(item.getId(), 1), tile, owner, true, time, type, ironmanName);
                }
                return;
            } else {
                addGroundItem(item, tile, owner, true, time, type, ironmanName);
                return;
            }
        }
    }

    private static final void removeGroundItem(final FloorItem floorItem, long publicTime) {
        CoresManager.slowExecutor.schedule(new Runnable() {
            @Override
            public void run() {
                try {
                    int regionId = floorItem.getTile().getRegionId();
                    Region region = getRegion(regionId);
                    if (!region.getGroundItemsSafe().contains(floorItem))
                        return;
                    region.getGroundItemsSafe().remove(floorItem);
                    for (Player player : World.getPlayers()) {
                        if (player == null || !player.hasStarted() || player.hasFinished()
                                || player.getPlane() != floorItem.getTile().getPlane()
                                || !player.getMapRegionsIds().contains(regionId))
                            continue;
                        player.getPackets().sendRemoveGroundItem(floorItem);
                    }
                } catch (Throwable e) {
                    Logger.handle(e);
                }
            }
        }, publicTime, TimeUnit.SECONDS);
    }

    public static final boolean removeGroundItem(Player player, FloorItem floorItem) {
        return removeGroundItem(player, floorItem, true);
    }

    public void removeGroundCoins() {

    }

    public static final boolean removeGroundItem(Player player, final FloorItem floorItem, boolean add) {
        int regionId = floorItem.getTile().getRegionId();
        Region region = getRegion(regionId);
        if (player == null) {
            region.getGroundItemsSafe().remove(floorItem);
            for (Player p2 : World.getPlayers()) {
                if (p2 == null || !p2.hasStarted() || p2.hasFinished()
                        || p2.getPlane() != floorItem.getTile().getPlane() || !p2.getMapRegionsIds().contains(regionId))
                    continue;
                p2.getPackets().sendRemoveGroundItem(floorItem);
            }
            if (floorItem.isForever()) {
                CoresManager.slowExecutor.schedule(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            addGroundItemForever(floorItem, floorItem.getTile());
                        } catch (Throwable e) {
                            Logger.handle(e);
                        }
                    }
                }, 60, TimeUnit.SECONDS);
            }
            return false;
        }
        WorldTile playerTile = new WorldTile(player.getX(), player.getY(), player.getPlane());
        if (!region.getGroundItemsSafe().contains(floorItem))
            return false;
        if (floorItem.cantPickupBy(player.getDisplayName()) && player.getPlayerRank().isIronman()) {
            player.getPackets().sendGameMessage("You can't pickup other players items as an Iron "
                    + (player.getAppearence().isMale() ? "Man" : "Woman") + ".");
            return false;
        }
        if (floorItem.getOwn() != player && player.getPlayerRank().isIronman()) {
            player.getPackets().sendGameMessage("You can't pickup other players items as an Iron "
                    + (player.getAppearence().isMale() ? "Man" : "Woman") + ".");
            return false;
        }
        if (floorItem.getId() == 2677
                && (player.getInventory().containsOneItem(2677) || player.getBank().getItem(2677) != null)) {
            player.getPackets().sendGameMessage("You can only have one easy clue scroll at a time.");
            return false;
        }
        if (floorItem.getId() == 2801
                && (player.getInventory().containsOneItem(2801) || player.getBank().getItem(2801) != null)) {
            player.getPackets().sendGameMessage("You can only have one medium clue scroll at a time.");
            return false;
        }
        if (floorItem.getId() == 2722
                && (player.getInventory().containsOneItem(2722) || player.getBank().getItem(2722) != null)) {
            player.getPackets().sendGameMessage("You can only have one hard clue scroll at a time.");
            return false;
        }
        if (floorItem.getId() == 19043
                && (player.getInventory().containsOneItem(19043) || player.getBank().getItem(19043) != null)) {
            player.getPackets().sendGameMessage("You can only have one elite clue scroll at a time.");
            return false;
        }
        if (floorItem.getOwn() != null && floorItem.getOwn().isDeveloper() && !player.isDeveloper()) {
            player.getPackets()
                    .sendGameMessage("This item has been dropped by a developer, therefor you can't pick it up.");
            return false;
        }
        int amount = floorItem.getAmount();
        if (floorItem.getId() == 995 && !player.isAtWild() && !FfaZone.inRiskArea(player)) {
            amount = floorItem.getAmount();
            int leftOver = 0;
            if (player.getMoneyPouch().getTotal() + amount < 0) {
                if (player.getMoneyPouch().getTotal() != Integer.MAX_VALUE)
                    player.getPackets().sendGameMessage("You can't hold more coins in your money pouch.");
                leftOver = Integer.MAX_VALUE - player.getMoneyPouch().getTotal();
                amount = amount - leftOver;
                if (player.getMoneyPouch().getTotal() != Integer.MAX_VALUE) {
                    player.getPackets().sendRunScript(5561, 1, leftOver);
                    player.getMoneyPouch().setTotal(Integer.MAX_VALUE);
                    player.getPackets().sendGameMessage(
                            Utils.getFormattedNumber(leftOver, ',') + " coins have been added to your money pouch.");
                    player.getMoneyPouch().refresh();
                    floorItem.setAmount(leftOver);
                }
                if (!player.getInventory().hasFreeSlots() && !player.getInventory().containsOneItem(995)) {
                    player.getPackets().sendGameMessage("You don't have enough inventory space.");
                    return false;
                }
                if (player.getInventory().getNumberOf(995) + amount < 0) {
                    leftOver = Integer.MAX_VALUE - player.getInventory().getNumberOf(995);
                    amount = amount - leftOver;
                    if (player.getInventory().getNumberOf(995) != Integer.MAX_VALUE) {
                        player.getInventory().deleteItem(995, Integer.MAX_VALUE);
                        player.getInventory().addItem(995, Integer.MAX_VALUE);
                    }
                    floorItem.setAmount(amount);
                    player.getPackets().sendRemoveGroundItem(floorItem);
                    player.getPackets().sendGroundItem(floorItem);
                    return false;
                }
                player.getInventory().addItem(995, amount);
                region.getGroundItemsSafe().remove(floorItem);
                if (floorItem.isInvisible()) {
                    player.getPackets().sendRemoveGroundItem(floorItem);
                    return true;
                } else {
                    for (Player p2 : World.getPlayers()) {
                        if (p2 == null || !p2.hasStarted() || p2.hasFinished()
                                || p2.getPlane() != floorItem.getTile().getPlane()
                                || !p2.getMapRegionsIds().contains(regionId))
                            continue;
                        p2.getPackets().sendRemoveGroundItem(floorItem);
                    }
                    if (floorItem.isForever()) {
                        CoresManager.slowExecutor.schedule(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    addGroundItemForever(floorItem, floorItem.getTile());
                                } catch (Throwable e) {
                                    Logger.handle(e);
                                }
                            }
                        }, 60, TimeUnit.SECONDS);
                    }
                    return true;
                }
            }
            if (player.getMoneyPouch().getTotal() == Integer.MAX_VALUE)
                return false;
            if (amount > 1)
                player.getPackets().sendGameMessage(
                        Utils.getFormattedNumber(amount, ',') + " coins have been added to your money pouch.");
            else
                player.getPackets().sendGameMessage("One coin has been added to your money pouch.");
            player.getPackets().sendRunScript(5561, 1, amount);
            player.getMoneyPouch().setTotal(player.getMoneyPouch().getTotal() + amount);
            player.getMoneyPouch().refresh();
            if (player.getFreezeDelay() >= Utils.currentTimeMillis()) {
                if (!floorItem.getTile().matches(playerTile))
                    player.animate(new Animation(537));
            }
            if (!floorItem.getTile().matches(player.getTile()) && add)
                player.animate(new Animation(832));
            region.getGroundItemsSafe().remove(floorItem);
            if (floorItem.isInvisible()) {
                player.getPackets().sendRemoveGroundItem(floorItem);
            } else {
                for (Player p2 : World.getPlayers()) {
                    if (p2 == null || !p2.hasStarted() || p2.hasFinished()
                            || p2.getPlane() != floorItem.getTile().getPlane()
                            || !p2.getMapRegionsIds().contains(regionId))
                        continue;
                    p2.getPackets().sendRemoveGroundItem(floorItem);
                }
                if (floorItem.isForever()) {
                    CoresManager.slowExecutor.schedule(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                addGroundItemForever(floorItem, floorItem.getTile());
                            } catch (Throwable e) {
                                Logger.handle(e);
                            }
                        }
                    }, 60, TimeUnit.SECONDS);
                }
                return true;
            }
        }
        int inventoryLeftOver = 0;
        if (player.getInventory().getAmountOf(floorItem.getId()) == Integer.MAX_VALUE) {
            player.getPackets().sendGameMessage("Not enough space in your inventory.");
            return false;
        }
        if (!player.getInventory().hasFreeSlots()
                && ((floorItem.getDefinitions().isStackable() || floorItem.getDefinitions().isNoted())
                && !player.getInventory().containsItem(floorItem.getId(), 1))) {
            player.getPackets().sendGameMessage("Not enough space in your inventory.");
            return false;
        } else if (!player.getInventory().hasFreeSlots()
                && (!floorItem.getDefinitions().isStackable() && !floorItem.getDefinitions().isNoted())) {
            player.getPackets().sendGameMessage("Not enough space in your inventory.");
            return false;
        }
        if (player.getInventory().getNumberOf(floorItem.getId()) + amount < 0) {
            inventoryLeftOver = Integer.MAX_VALUE - player.getInventory().getNumberOf(floorItem.getId());
            amount = amount - inventoryLeftOver;
            if (player.getInventory().getNumberOf(floorItem.getId()) != Integer.MAX_VALUE) {
                player.getInventory().deleteItem(floorItem.getId(), Integer.MAX_VALUE);
                player.getInventory().addItem(floorItem.getId(), Integer.MAX_VALUE);
            }
            floorItem.setAmount(amount);
            player.getPackets().sendRemoveGroundItem(floorItem);
            player.getPackets().sendGroundItem(floorItem);
            return false;
        }
        if (player.getFreezeDelay() >= Utils.currentTimeMillis()) {
            if (!floorItem.getTile().matches(playerTile))
                player.animate(new Animation(832));
        }
        if (!floorItem.getTile().matches(player.getTile()) && add)
            player.animate(new Animation(832));
        if (player.getBeam() != null && player.getBeamItem() != null) {
            if (floorItem.getTile().matches(player.getBeam()) && player.getBeamItem().getId() == floorItem.getId()) {
                World.sendGraphics(player, new Graphics(-1, 0, 0), new WorldTile(floorItem.getTile()));
                player.setBeam(null);
                player.setBeamItem(null);
                // System.out.println("Removed beam due to picked up.");
            }
        }
        region.getGroundItemsSafe().remove(floorItem);
        if (add)
            player.getInventory()
                    .addItem(new Item(floorItem.getId() == 7957 ? 1005 : floorItem.getId(), floorItem.getAmount()));
        if (floorItem.isInvisible()) {
            player.getPackets().sendRemoveGroundItem(floorItem);
            return true;
        } else {
            for (Player p2 : World.getPlayers()) {
                if (p2 == null || !p2.hasStarted() || p2.hasFinished()
                        || p2.getPlane() != floorItem.getTile().getPlane() || !p2.getMapRegionsIds().contains(regionId))
                    continue;
                p2.getPackets().sendRemoveGroundItem(floorItem);
            }
            if (floorItem.isForever()) {
                CoresManager.slowExecutor.schedule(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            addGroundItemForever(floorItem, floorItem.getTile());
                        } catch (Throwable e) {
                            Logger.handle(e);
                        }
                    }
                }, 60, TimeUnit.SECONDS);
            }
        }
        return true;
    }

    public static final void sendObjectAnimation(WorldObject object, Animation animation) {
        sendObjectAnimation(null, object, animation);
    }

    public static final void sendObjectAnimation(Entity creator, WorldObject object, Animation animation) {
        if (creator == null) {
            for (Player player : World.getPlayers()) {
                if (player == null || !player.hasStarted() || player.hasFinished() || !player.withinDistance(object))
                    continue;
                player.getPackets().sendObjectAnimation(object, animation);
            }
        } else {
            for (int regionId : creator.getMapRegionsIds()) {
                List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
                if (playersIndexes == null)
                    continue;
                for (Integer playerIndex : playersIndexes) {
                    Player player = players.get(playerIndex);
                    if (player == null || !player.hasStarted() || player.hasFinished()
                            || !player.withinDistance(object))
                        continue;
                    player.getPackets().sendObjectAnimation(object, animation);
                }
            }
        }
    }

    public static final void sendGraphics(Entity creator, Graphics graphics, WorldTile tile) {
        if (creator == null) {
            for (Player player : World.getPlayers()) {
                if (player == null || !player.hasStarted() || player.hasFinished() || !player.withinDistance(tile))
                    continue;
                player.getPackets().sendGraphics(graphics, tile);
            }
        } else {
            for (int regionId : creator.getMapRegionsIds()) {
                List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
                if (playersIndexes == null)
                    continue;
                for (Integer playerIndex : playersIndexes) {
                    Player player = players.get(playerIndex);
                    if (player == null || !player.hasStarted() || player.hasFinished() || !player.withinDistance(tile))
                        continue;
                    player.getPackets().sendGraphics(graphics, tile);
                }
            }
        }
    }

    public static final void sendPrivateGraphics(Entity creator, Graphics graphics, WorldTile tile) {
        if (creator == null) {
            for (Player player : World.getPlayers()) {
                if (player != creator || player == null || !player.hasStarted() || player.hasFinished()
                        || !player.withinDistance(tile))
                    continue;
                player.getPackets().sendGraphics(graphics, tile);
            }
        } else {
            for (int regionId : creator.getMapRegionsIds()) {
                List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
                if (playersIndexes == null)
                    continue;
                for (Integer playerIndex : playersIndexes) {
                    Player player = players.get(playerIndex);
                    if (player != creator || player == null || !player.hasStarted() || player.hasFinished()
                            || !player.withinDistance(tile))
                        continue;
                    player.getPackets().sendGraphics(graphics, tile);
                }
            }
        }
    }

    public static final void sendDragonfireProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, 53, 34, distance < 4 ? 36 : 41, 41, 0,
                        receiver instanceof NPC ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendNPCProjectile(Entity shooter, WorldTile tile, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(tile)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, tile);
                int startOffsetDistance = distance > 2 ? 0 : 0;
                player.getPackets().sendProjectile(null,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        tile, gfxId, 53, 34, distance < 4 ? 36 : 41, 41, 0, startOffsetDistance, size);
            }
        }
    }

    public static final void sendGroundProjectile(Entity shooter, WorldTile tile, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(tile)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, tile);
                int startOffsetDistance = distance > 2 ? 0 : 0;
                player.getPackets().sendProjectile(null,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        tile, gfxId, 53, 0, distance < 4 ? 36 : 41, 41, 0, startOffsetDistance, size);
            }
        }
    }

    public static final void sendNPCSlowProjectile(Entity shooter, WorldTile tile, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(tile)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, tile);
                int startOffsetDistance = distance > 2 ? 0 : 0;
                player.getPackets().sendProjectile(null,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        tile, gfxId, 53, 34, distance < 4 ? 26 : 31, 21, 0, startOffsetDistance, size);
            }
        }
    }

    public static final void sendJadProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;
                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, 53, 34, distance < 4 ? 32 : 48, 0, 0,
                        receiver instanceof NPC ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendProjectile(Entity shooter, Entity receiver, int gfxId, int startHeight, int endHeight,
                                            int speed, int delay, int curve) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, startHeight, endHeight, speed, delay, curve,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendElementalProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver, shooter, receiver, gfxId, 43, 34,
                        distance < 2 ? 51 : distance == 2 ? 56 : 61, 51, 6,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendFastBowProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver, shooter, receiver, gfxId, 43, 34,
                        distance < 2 ? 51 : distance == 2 ? 56 : 61, 41, 6,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendObjectProjectile(Entity shooter, WorldTile tile, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished())
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, tile);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(null, shooter, tile, gfxId, 54, 4, 61, 41, 6, startOffsetDistance,
                        size);
            }
        }
    }

    public static final void sendMSBProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, 43, 34, distance < 2 ? 51 : distance == 2 ? 56 : 61, 31, 6,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendMSBProjectile2(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, 43, 34, distance < 2 ? 51 : distance == 2 ? 56 : 61, 56, 6,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendFastBowSwiftProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, 43 - 5, 34 - 5, distance < 2 ? 51 : distance == 2 ? 56 : 61, 41, 6,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendSlowBowProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, 43, 34, distance < 2 ? 41 : distance == 2 ? 46 : 51, 41, 16,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendSlowBowProjectile(Entity shooter, WorldTile tile, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(tile)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, tile);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(shooter,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        tile, gfxId, 43, 34, distance < 2 ? 41 : distance == 2 ? 46 : 51, 41, 16, startOffsetDistance,
                        size);
            }
        }
    }

    public static final void sendSlowBow2Projectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, 43, 34, distance < 2 ? 41 : distance == 2 ? 46 : 51, 61, 16,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendSlowBowSwiftProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, 43 - 5, 34 - 5, distance > 2 ? 51 : distance == 2 ? 56 : 61, 21, 6,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendCBOWProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getX(), shooter.getY(), shooter.getPlane()), receiver, gfxId, 43, 34,
                        distance > 2 ? 61 : 51, 41, 6,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendCBOWSwiftProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, 43 - 5, 34 - 5, distance > 2 ? 61 : 51, 41, 6,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendThrowSwiftProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, 43 - 5, 34 - 5, 42, 32, 6,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendThrowProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, 43, 34, 42, 31, 6,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendDartProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, 43, 34, 42, 16, 6,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendCannonProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, 18, 18, distance < 2 ? 51 : distance == 2 ? 56 : 61, 41, 0,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendSOAProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, 0, 0, distance < 4 ? 51 : 61, 51, 6,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendSoulsplitProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, 0, 0, distance < 4 ? 26 : 31, 41, 6,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendLeechProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, 35, 35, distance < 2 ? 21 : 26, 5, 0,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendElemantalProjectile(Entity shooter, Entity receiver, int gfxId) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver,
                        new WorldTile(shooter.getCoordFaceX(size), shooter.getCoordFaceY(size), shooter.getPlane()),
                        receiver, gfxId, 28, 18, 50, 50, 0,
                        receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final void sendProjectile(Entity shooter, WorldTile startTile, WorldTile receiver, int gfxId,
                                            int startHeight, int endHeight, int speed, int delay, int curve, int startDistanceOffset) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                player.getPackets().sendProjectile(null, startTile, receiver, gfxId, startHeight, endHeight, speed,
                        delay, curve, startDistanceOffset, shooter.getSize());
            }
        }
    }

    public static final void sendProjectile(WorldTile shooter, Entity receiver, int gfxId, int startHeight,
                                            int endHeight, int speed, int delay, int curve, int startDistanceOffset) {
        for (int regionId : receiver.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver, shooter, receiver, gfxId, startHeight, endHeight, speed,
                        delay, curve, receiver instanceof Player ? startOffsetDistance : startOffsetDistance, 1);
            }
        }
    }

    public static final void sendProjectile(Entity shooter, WorldTile receiver, int gfxId, int startHeight,
                                            int endHeight, int speed, int delay, int curve, int startDistanceOffset) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(null, shooter, receiver, gfxId, startHeight, endHeight, speed, delay,
                        curve, receiver instanceof Player ? startOffsetDistance : startOffsetDistance,
                        shooter.getSize());
            }
        }
    }

    public static final void sendProjectile(Entity shooter, Entity receiver, int gfxId, int startHeight, int endHeight,
                                            int speed, int delay, int curve, int startDistanceOffset) {
        for (int regionId : shooter.getMapRegionsIds()) {
            List<Integer> playersIndexes = getRegion(regionId).getPlayerIndexes();
            if (playersIndexes == null)
                continue;
            for (Integer playerIndex : playersIndexes) {
                Player player = players.get(playerIndex);
                if (player == null || !player.hasStarted() || player.hasFinished()
                        || (!player.withinDistance(shooter) && !player.withinDistance(receiver)))
                    continue;
                int size = shooter.getSize();
                int distance = Utils.getDistance(shooter, receiver);
                int startOffsetDistance = distance > 2 ? 0 : 11;

                player.getPackets().sendProjectile(receiver, shooter, receiver, gfxId, startHeight, endHeight, speed,
                        delay, curve, receiver instanceof Player ? startOffsetDistance : startOffsetDistance, size);
            }
        }
    }

    public static final boolean isMultiArea(WorldTile tile) {
        return (AreaManager.get(tile) != null && (AreaManager.get(tile).name().equalsIgnoreCase("Multi Area")
                || AreaManager.get(tile).name().equalsIgnoreCase("Wildy Agility Multi")
                || AreaManager.get(tile).name().equalsIgnoreCase("Godwars")
                || AreaManager.get(tile).name().equalsIgnoreCase("ForinthryMulti")
                || AreaManager.get(tile).name().equalsIgnoreCase("Nex Multi")
                || AreaManager.get(tile).name().equalsIgnoreCase("Corp Multi")
                || AreaManager.get(tile).name().equalsIgnoreCase("Tormdemon Multi")
                || AreaManager.get(tile).name().equalsIgnoreCase("KQ Multi")
                || AreaManager.get(tile).name().equalsIgnoreCase("Chaos Tunnel Multi")
                || AreaManager.get(tile).name().equalsIgnoreCase("Dks Multi")
                || AreaManager.get(tile).name().equalsIgnoreCase("KBD Multi")) || Bork.atBork(tile));
    }

    public static final boolean atMultiArea(Player player) {
        return (AreaManager.get(player) != null && (AreaManager.get(player).name().equalsIgnoreCase("Multi Area")
                || AreaManager.get(player).name().equalsIgnoreCase("Wildy Agility Multi")
                || AreaManager.get(player).name().equalsIgnoreCase("Godwars")
                || AreaManager.get(player).name().equalsIgnoreCase("ForinthryMulti")
                || AreaManager.get(player).name().equalsIgnoreCase("Nex Multi")
                || AreaManager.get(player).name().equalsIgnoreCase("Corp Multi")
                || AreaManager.get(player).name().equalsIgnoreCase("Tormdemon Multi")
                || AreaManager.get(player).name().equalsIgnoreCase("KQ Multi")
                || AreaManager.get(player).name().equalsIgnoreCase("Chaos Tunnel Multi")
                || AreaManager.get(player).name().equalsIgnoreCase("Dks Multi")
                || AreaManager.get(player).name().equalsIgnoreCase("KBD Multi")) || Bork.atBork(player));
    }

    public static final boolean inWilderness() {
        /**
         * Add to check if the player is in the wilderness if so then start the
         * controler.
         */

        return false;
    }

    public static final boolean isPvpArea(WorldTile tile) {
        return WildernessControler.isAtWild(tile) || EdgevillePvPControler.isAtPvP(tile);
    }

    private transient static final EntityList<Player> lobbyPlayers = new EntityList<Player>(Settings.PLAYERS_LIMIT);

    public static final Player getLobbyPlayerByDisplayName(String username) {
        String formatedUsername = Utils.formatPlayerNameForDisplay(username);
        for (Player player : getLobbyPlayers()) {
            if (player == null) {
                continue;
            }
            if (player.getUsername().equalsIgnoreCase(formatedUsername)
                    || player.getDisplayName().equalsIgnoreCase(formatedUsername)) {
                return player;
            }
        }
        return null;
    }

    public static final EntityList<Player> getLobbyPlayers() {
        return lobbyPlayers;
    }

    public static final void addPlayer(Player player) {
        players.add(player);
        if (World.containsLobbyPlayer(player.getUsername())) {
            World.removeLobbyPlayer(player);
            AntiFlood.remove(player.getSession().getIP());
        }
        AntiFlood.add(player.getSession().getIP());
    }

    public static final void addLobbyPlayer(Player player) {
        lobbyPlayers.add(player);
        AntiFlood.add(player.getSession().getIP());
    }

    public static final boolean containsLobbyPlayer(String username) {
        for (Player p2 : lobbyPlayers) {
            if (p2 == null) {
                continue;
            }
            if (p2.getUsername().equalsIgnoreCase(username)) {
                return true;
            }
        }
        return false;
    }

    public static void removeLobbyPlayer(Player player) {
        for (Player p : lobbyPlayers) {
            if (p.getUsername().equalsIgnoreCase(player.getUsername())) {
                if (player.getCurrentFriendChat() != null) {
                    player.getCurrentFriendChat().leaveChat(player, true);
                }
                lobbyPlayers.remove(p);
            }
        }
        AntiFlood.remove(player.getSession().getIP());
    }

    public static void removePlayer(Player player) {
        for (Player p : players) {
            if (p.getUsername().equalsIgnoreCase(player.getUsername())) {
                players.remove(p);
            }
        }
        AntiFlood.remove(player.getSession().getIP());
    }

    public enum MessageType {

        EVENT("<img=7><col=ff0000>Event: "),
        NEWS("<img=7><col=ff0000>News: "),
        SERVER("<img=7><col=ff0000>Server: "),
        RARE_DROP("<img=7><col=36648b>Drop: ");//TODO ALL TYPES

        private String message;

        MessageType(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public static void sendWorldMessage(MessageType type, String message) {
        StringBuilder builder = new StringBuilder();
        builder.append(type.getMessage()).append(message);
        for (Player p : World.getPlayers()) {
            if (p == null || !p.isActive())
                continue;
            p.getPackets().sendGameMessage(builder.toString());
        }
    }

    public static void sendWorldMessage(String message, boolean forStaff) {
        for (Player p : World.getPlayers()) {
            if (p == null || !p.isActive() || p.isYellOff() || (forStaff && !p.isStaff()))
                continue;
            p.getPackets().sendGameMessage(message);
        }
    }

    public static final void sendProjectile(WorldObject object, WorldTile startTile, WorldTile endTile, int gfxId,
                                            int startHeight, int endHeight, int speed, int delay, int curve, int startOffset) {
        for (Player pl : players) {
            if (pl == null || !pl.withinDistance(object, 20))
                continue;
            pl.getPackets().sendProjectile(null, startTile, endTile, gfxId, startHeight, endHeight, speed, delay, curve,
                    startOffset, 1);
        }
    }

    public static void sendNewsMessage(String message, boolean TwoHundredM) {
        World.sendWorldMessage("<img=7>" + (TwoHundredM ? "<col=ff0000>" : "<col=ff8c38>") + "News: " + message, false);
    }
    

	public static final Projectile sendProjectileNew(WorldTile from, WorldTile to, int graphicId, int startHeight, int endHeight, int startTime, double speed, int angle, int slope) {
		return sendProjectile(from, to, false, false, 0, graphicId, startHeight, endHeight, startTime, speed, angle, slope);
	}
	

	public static final Projectile sendProjectile(WorldTile from, WorldTile to, boolean adjustFlyingHeight, boolean adjustSenderHeight, int senderBodyPartId, int graphicId, int startHeight, int endHeight, int startTime, double speed, int angle, int slope) {
		int fromSizeX, fromSizeY;
		if (from instanceof Entity)
			fromSizeX = fromSizeY = ((Entity) from).getSize();
		else if (from instanceof WorldObject) {
			ObjectDefinitions defs = ((WorldObject) from).getDefinitions();
			fromSizeX = defs.getSizeX();
			fromSizeY = defs.getSizeY();
		} else
			fromSizeX = fromSizeY = 1;
		int toSizeX, toSizeY;
		if (to instanceof Entity)
			toSizeX = toSizeY = ((Entity) to).getSize();
		else if (to instanceof WorldObject) {
			ObjectDefinitions defs = ((WorldObject) to).getDefinitions();
			toSizeX = defs.getSizeX();
			toSizeY = defs.getSizeY();
		} else
			toSizeX = toSizeY = 1;

		Projectile projectile = new Projectile(from, to, adjustFlyingHeight, adjustSenderHeight, senderBodyPartId, graphicId, startHeight, endHeight, startTime, startTime + (speed == -1 ? Utils.getProjectileTimeSoulsplit(from, fromSizeX, fromSizeY, to, toSizeX, toSizeY) : Utils.getProjectileTimeNew(from, fromSizeX, fromSizeY, to, toSizeX, toSizeY, speed)), slope, angle);
		getRegion(from.getRegionId()).addProjectile(projectile);
		return projectile;
	}

}
