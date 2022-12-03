package com.rs.game.player;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.rs.cache.loaders.ClientScriptMap;
import com.rs.cache.loaders.GeneralRequirementMap;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.map.MapBuilder;
import com.rs.game.npc.NPC;
import com.rs.game.player.cutscenes.Cutscene;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.net.decoders.WorldPacketsDecoder;
import com.rs.utils.ItemExamines;
import com.rs.utils.Utils;

public final class DominionTower implements Serializable {

    public static final int CLIMBER = 0, ENDURANCE = 1, MAX_FACTOR = 10000000;
    private static final long serialVersionUID = -5230255619877910203L;

    private transient Player player;
    private transient int[] mapBaseCoords;
    private transient ItemsContainer<Item> rewards;

    public int nextBossIndex;
    private int progress;
    private transient boolean hasBoss;
    private long totalScore;
    private boolean talkedWithFace;
    private int killedBossesCount;
    private int maxFloorEndurance;
    private int maxFloorClimber;

    private static final Item[] REWARD_CHEST = new Item[]
            {
                    new Item(3024, 1),
                    new Item(6685, 1),
                    new Item(2448, 1),
                    new Item(15328, 1),
                    new Item(565, 10),
                    new Item(566, 10),
                    new Item(560, 10),
                    new Item(561, 10),
                    new Item(892, 10),
                    new Item(9144, 10),
                    new Item(532, 1),
                    new Item(536, 1),
                    new Item(532, 1),
                    new Item(6729, 1),
                    new Item(22346, 1),
                    new Item(22347, 1),
                    new Item(22348, 1),
                    new Item(22358, 1),
                    new Item(22362, 1),
                    new Item(22366, 1),
                    new Item(22370, 1)};

    private static final int[] NORMAL_ARENA =
            {456, 768}, LARGE_PILAR_ARENA =
            {456, 776}, SMALL_PILAR_ARENA =
            {456, 784}, PODIUM_ARENA =
            {456, 760};


    public void setPlayer(Player player) {
        this.player = player;
    }

    public DominionTower() {
        nextBossIndex = -1;
    }

    public boolean hasRequirements() {
        return player.getSkills().getCombatLevelWithSummoning() < 110;
    }

    public void openSpectate() {
        player.getInterfaceManager().sendInterface(1157);
    }

    public void growFace() {
        player.getPackets().sendSound(7913, 0, 2);
        player.getDialogueManager().startDialogue("SimpleMessage", "The face on the wall groans and cowls at you. Perhaps you should", "talk to it first.");
    }

    public void openModes() {
        if (hasRequirements()) {
            player.getDialogueManager().startDialogue("DTSpectateReq");
            return;
        }
        if (!talkedWithFace) {
            growFace();
            return;
        }
        if (getDominionFactor() > 0) {
            player.getDialogueManager().startDialogue("SimpleMessage", "You have some dominion factor which you must exchange before", "starting another match.");
            player.getPackets().sendGameMessage("You can't go back into the arena, you must go to the next floor on entrance.");
            return;
        }
        player.getInterfaceManager().sendInterface(1164);
        player.getPackets().sendIComponentText(1164, 27, progress == 0 ? "Ready for a new match" : "Floor progress: " + progress);
    }

    public void handleButtons(int interfaceId, int componentId, int slotId, int packetId) {
        if (interfaceId == 1164) {
            if (componentId == 26)
                openClimberMode();
            else if (componentId == 28)
                openEnduranceMode();
            else if (componentId == 29)
                openSpecialMode();
            else if (componentId == 30)
                openFreeStyleMode();
            else if (componentId == 31)
                openSpectate();
        } else if (interfaceId == 1163) {
            if (componentId == 90)
                player.closeInterfaces();
            else if (componentId == 95)
                startClimbMode();
        } else if (interfaceId == 1168) {
            if (componentId == 254)
                player.closeInterfaces();
        } else if (interfaceId == 1170) {
            if (componentId == 85)
                player.closeInterfaces();
        } else if (interfaceId == 1171) {
            if (componentId == 7) {
                if (packetId == WorldPacketsDecoder.ACTION_BUTTON1_PACKET)
                    sendTake(0, slotId);
                else if (packetId == WorldPacketsDecoder.ACTION_BUTTON2_PACKET)
                    sendTake(1, slotId);
                else if (packetId == WorldPacketsDecoder.ACTION_BUTTON3_PACKET)
                    sendTake(2, slotId);
                else
                    sendExamine(slotId);
            } else if (componentId == 9)
                sendBankAll();
        } else if (interfaceId == 1173) {
            if (componentId == 58)
                player.closeInterfaces();
            else if (componentId == 59)
                startEnduranceMode();
        }
    }

    private static final int[] MUSICS =
            {1026, 1027, 1028, 1029, 1030};

    private void startEnduranceMode() {
        if (getDominionFactor() > 0) {
            player.getDialogueManager().startDialogue("SimpleMessage", "You have some dominion factor which you must exchange before", "starting another match.");
            player.getPackets().sendGameMessage("You can't go back into the arena, you must go to the next floor on entrance.");
            return;
        }
        createArena(ENDURANCE);
    }

    private void startClimbMode() {
        if (getDominionFactor() > 0) {
            player.getDialogueManager().startDialogue("SimpleMessage", "You have some dominion factor which you must exchange before", "starting another match.");
            player.getPackets().sendGameMessage("You can't go back into the arena, you must go to the next floor on entrance.");
            return;
        }
        createArena(CLIMBER);
    }

    /**
     * for (Map.Entry<Long, Object> maps : GeneralRequirementMap.getMap(ClientScriptMap.getMap(5213).getIntValue(player.getDominionTower().getNextBossIndex())).getValues().entrySet()) {
     *                         System.out.print(maps.getKey() + " - " + maps.getValue() + "\n");
     *                     }
     * @return
     */

    public int getFactorReward() {
        return getMap().getIntValue(2122);
    }

    public GeneralRequirementMap getMap() {
        return GeneralRequirementMap.getMap(ClientScriptMap.getMap(5213).getIntValue(getNextBossIndex()));
    }

    public void createArena(final int mode) {
        player.closeInterfaces();
        player.lock();
        CoresManager.slowExecutor.execute(() -> {
            try {
                boolean needDestroy = mapBaseCoords != null;
                final int[] oldMapBaseCoords = mapBaseCoords;
                mapBaseCoords = MapBuilder.findEmptyChunkBound(8, 8);
                int[] arena;
                int a = getMap().getIntValue(2097);
                if (a == 1 || a == 2)
                    arena = NORMAL_ARENA;
                else if (a == 3)
                    arena = SMALL_PILAR_ARENA;
                else if (a == 4)
                    arena = LARGE_PILAR_ARENA;
                else
                    arena = PODIUM_ARENA;
                MapBuilder.copyAllPlanesMap(arena[0], arena[1], mapBaseCoords[0], mapBaseCoords[1], 8);
                teleportToArena(mode);
                if (needDestroy) {
                    WorldTasksManager.schedule(new WorldTask() {
                        @Override
                        public void run() {
                            CoresManager.slowExecutor.execute(() -> {
                                try {
                                    MapBuilder.destroyMap(oldMapBaseCoords[0], oldMapBaseCoords[1], 8, 8);
                                } catch (Exception | Error e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    });
                }
            } catch (Exception | Error e) {
                e.printStackTrace();
            }

        });
    }

    private void teleportToArena(int mode) {
        player.setNextFaceWorldTile(new WorldTile(getBaseX() + 11, getBaseY() + 29, 0));
        player.getControlerManager().startControler("DTControler", mode);
        player.getCombatDefinitions().refreshBonuses();
        player.unlock();
        player.setNextWorldTile(new WorldTile(getBaseX() + 10, getBaseY() + 29, 2));
        player.getMusicsManager().playMusic(MUSICS[Utils.random(MUSICS.length)]);
    }

    public String getStartFightText(int message) {
        switch (message) {
            case 0:
                return "Kick my ass!";
            case 1:
                return "Please don't hit my face";
            case 2:
                return "Argh!";
            default:
                return "Bring it on!";
        }
    }

    public int getNextBossIndex() {
        if (nextBossIndex < 0)
            selectBoss();
        return nextBossIndex;
    }

    public void startFight(final NPC[] bosses) {
        for (NPC boss : bosses) {
            boss.setCantInteract(true);
            boss.setNextFaceWorldTile(new WorldTile(boss.getX() - 1, boss.getY(), 0));
        }
        player.lock();
        player.setNextWorldTile(new WorldTile(getBaseX() + 25, getBaseY() + 32, 2));
        player.setNextFaceWorldTile(new WorldTile(getBaseX() + 26, getBaseY() + 32, 0));
        WorldTasksManager.schedule(new WorldTask() {

            private int count;

            @Override
            public void run() {
                if (count == 0) {
                    player.getInterfaceManager().sendTab(player.getInterfaceManager().hasRezizableScreen() ? 11 : 0, 1172);
                    player.getPackets().sendHideIComponent(1172, 2, true);
                    player.getPackets().sendHideIComponent(1172, 7, true);
                    player.getPackets().sendIComponentText(1172, 4, player.getDisplayName());
                    player.getVarsManager().sendVar(1241, 1);
                    player.getPackets().sendCameraPos(Cutscene.getX(player, getBaseX() + 25), Cutscene.getY(player, getBaseY() + 38), 1800);
                    player.getPackets().sendCameraLook(Cutscene.getX(player, getBaseX() + 25), Cutscene.getY(player, getBaseY() + 29), 800);
                    player.getPackets().sendCameraPos(Cutscene.getX(player, getBaseX() + 32), Cutscene.getY(player, getBaseY() + 38), 1800, 6, 6);
                } else if (count == 1) {
                    player.setNextForceTalk(new ForceTalk(getStartFightText(Utils.getRandom(1))));
                } else if (count == 3) {
                    player.getPackets().sendHideIComponent(1172, 2, false);
                    player.getPackets().sendHideIComponent(1172, 5, true);
                    player.getPackets().sendIComponentText(1172, 6, getMap().getStringValue(2095));
                    player.getVarsManager().sendVar(1241, 0);
                    player.getPackets().sendCameraPos(Cutscene.getX(player, getBaseX() + 35), Cutscene.getY(player, getBaseY() + 37), 1800);
                    player.getPackets().sendCameraLook(Cutscene.getX(player, getBaseX() + 35), Cutscene.getY(player, getBaseY() + 28), 800);
                    player.getPackets().sendCameraPos(Cutscene.getX(player, getBaseX() + 42), Cutscene.getY(player, getBaseY() + 37), 1800, 6, 6);
                } else if (count == 4) {

                    for (int i = 0; i < bosses.length; i++) {
                        String text = getMap().getStringValue(2102 + i);
                        if (!text.equals(""))
                            bosses[i].setNextForceTalk(new ForceTalk(text));
                    }
                    int voice = getMap().getIntValue(2118);
                    if (voice != 0)
                        player.getPackets().sendVoice(voice);
                } else if (count == 6) {
                    player.getControlerManager().sendInterfaces();
                    player.getInterfaceManager().sendInterface(1172);
                    player.getPackets().sendHideIComponent(1172, 2, true);
                    player.getPackets().sendHideIComponent(1172, 5, true);
                    player.getPackets().sendIComponentText(1172, 8, "Fight!");
                    player.getPackets().sendHideIComponent(1172, 10, true);
                    player.getPackets().sendCameraLook(Cutscene.getX(player, getBaseX() + 32), Cutscene.getY(player, getBaseY() + 36), 0);
                    player.getPackets().sendCameraPos(Cutscene.getX(player, getBaseX() + 32), Cutscene.getY(player, getBaseY() + 16), 5000);
                    player.getPackets().sendVoice(7882);
                } else if (count == 8) {
                    if (nextBossIndex == 47) {
                        World.addGroundItem(new Item(2402), new WorldTile(getBaseX() + 26, getBaseY() + 33, 2));
                    }
                    player.closeInterfaces();
                    player.getPackets().sendResetCamera();
                    for (NPC boss : bosses) {
                        boss.setCantInteract(false);
                        boss.setTarget(player);
                    }
                    player.unlock();
                    stop();
                }
                count++;
            }

        }, 0, 1);
    }

    public void removeItem() {
        if (nextBossIndex == -1)
            return;
        if (nextBossIndex == 47) {
            player.getInventory().deleteItem(2402, 1);
            player.getEquipment().deleteItem(2402, 1);
            player.getAppearence().generateAppearenceData();
        }
    }

    public void loss(final int mode) {
        removeItem();
        nextBossIndex = -1;
        player.lock();
        player.setNextWorldTile(new WorldTile(getBaseX() + 35, getBaseY() + 31, 2));
        player.setNextFaceWorldTile(new WorldTile(player.getX() + 1, player.getY(), 0));

        WorldTasksManager.schedule(new WorldTask() {
            int count;

            @Override
            public void run() {
                if (count == 0) {
                    player.animate(new Animation(836));
                    player.getInterfaceManager().closeOverlay(false);
                    player.getInterfaceManager().sendInterface(1172);
                    player.getPackets().sendHideIComponent(1172, 2, true);
                    player.getPackets().sendHideIComponent(1172, 5, true);
                    player.getPackets().sendIComponentText(1172, 8, "Unlucky, you lost!");
                    player.getPackets().sendIComponentText(1172, 10, "You leave with a dominion factor of: " + getDominionFactor());
                    player.getPackets().sendCameraPos(Cutscene.getX(player, getBaseX() + 35), Cutscene.getY(player, getBaseY() + 37), 2500);
                    player.getPackets().sendCameraLook(Cutscene.getX(player, getBaseX() + 35), Cutscene.getY(player, getBaseY() + 28), 800);
                    player.getPackets().sendCameraPos(Cutscene.getX(player, getBaseX() + 42), Cutscene.getY(player, getBaseY() + 37), 2500, 6, 6);
                    player.getPackets().sendVoice(7874);
                } else if (count == 4) {
                    player.setForceMultiArea(false);
                    player.reset();
                    player.animate(new Animation(-1));
                    player.closeInterfaces();
                    player.getPackets().sendResetCamera();
                    player.unlock();
                    destroyArena(false, mode);
                    stop();
                }
                count++;
            }
        }, 0, 1);
    }

    public void win(int mode) {
        removeItem();
        int factor = getBossesTotalLevel() * (mode == CLIMBER ? 100 : 10);
        progress++;
        if (mode == CLIMBER) {
            if (progress > maxFloorClimber)
                maxFloorClimber = progress;
        } else if (mode == ENDURANCE) {
            if (progress > maxFloorEndurance)
                maxFloorEndurance = progress;
        }

        killedBossesCount++;
       addFactor(factor);
        totalScore += factor;
        if (getDominionFactor() > MAX_FACTOR) {
            setDominionFactor(MAX_FACTOR);
            player.getPackets().sendGameMessage("You've reached the maximum Dominion Factor you can get so you should spend it!");
        }
        nextBossIndex = -1;
        player.lock(2);
        player.setNextWorldTile(new WorldTile(getBaseX() + 35, getBaseY() + 31, 2));
        player.setNextFaceWorldTile(new WorldTile(getBaseX() + 36, getBaseY() + 31, 0));

        WorldTasksManager.schedule(new WorldTask() {

            private int count;

            @Override
            public void run() {
                if (count == 0) {
                    player.getInterfaceManager().closeOverlay(false);
                    player.getInterfaceManager().sendInterface(1172);
                    player.getPackets().sendHideIComponent(1172, 2, true);
                    player.getPackets().sendHideIComponent(1172, 5, true);
                    player.getPackets().sendIComponentText(1172, 8, "Yeah! You won!");
                    player.getPackets().sendIComponentText(1172, 10, "You now have a dominion factor of: " + getDominionFactor());
                    player.getPackets().sendCameraPos(Cutscene.getX(player, getBaseX() + 35), Cutscene.getY(player, getBaseY() + 37), 2500);
                    player.getPackets().sendCameraLook(Cutscene.getX(player, getBaseX() + 35), Cutscene.getY(player, getBaseY() + 28), 800);
                    player.getPackets().sendCameraPos(Cutscene.getX(player, getBaseX() + 42), Cutscene.getY(player, getBaseY() + 37), 2500, 6, 6);
                    player.getPackets().sendVoice(7897);
                } else if (count == 4) {
                    player.reset();
                    player.closeInterfaces();
                    player.getPackets().sendResetCamera();
                    stop();
                }
                count++;
            }
        }, 0, 1);

    }

    public void destroyArena(final boolean logout, int mode) {
        WorldTile tile = new WorldTile(3733, 6437, 0);
        if (logout)
            player.setLocation(tile);
        else {
            player.getControlerManager().removeControlerWithoutCheck();
            player.lock();
            player.setNextWorldTile(tile);
            player.getCombatDefinitions().refreshBonuses();
            nextBossIndex = -1;
            if (mode == ENDURANCE)
                progress = 0;
        }
        WorldTasksManager.schedule(new WorldTask() {
            @Override
            public void run() {
                CoresManager.slowExecutor.execute(() -> {
                    try {
                        MapBuilder.destroyMap(mapBaseCoords[0], mapBaseCoords[1], 8, 8);
                        if (!logout) {
                            mapBaseCoords = null;
                            player.unlock();
                        }
                    } catch (Exception | Error e) {
                        e.printStackTrace();
                    }
                });
            }
        }, 1);
    }

    public NPC[] createBosses() {
        GeneralRequirementMap map = getMap();
        int npc2 = map.getIntValue(2169);
        int npc3 = map.getIntValue(2170);
        int npc4 = map.getIntValue(2171);
        int npc5 = map.getIntValue(2172);
        NPC[] bosses = new NPC[npc5 != 0 ? 5 : npc4 != 0 ? 4 : npc3 != 0 ? 3 : npc2 != 0 ? 2 : 1];
        for (int i = 0; i < bosses.length; i++) {
            int id = map.getIntValue(2168 + i);
            String name = NPCDefinitions.getNPCDefinitions(id).name;
            for (int i2 = 0; i < id; i2++) {
                NPCDefinitions def = NPCDefinitions.getNPCDefinitions(i2);
                if (def.getName().equals(name) && def.hasAttackOption()) {
                    id = i2;
                    break;
                }
            }
            bosses[i] = World.spawnNPC(id, new WorldTile(getBaseX() + 37 + (i * 2), getBaseY() + 31, 2), -1, true, true);
            bosses[i].setForceTargetDistance(64);
        }
        return bosses;
    }


    public int getBaseX() {
        return mapBaseCoords[0] << 3;
    }

    public int getBaseY() {
        return mapBaseCoords[1] << 3;
    }

    /**
     * Usefull configs
     * <p>
     * 10013 = remaining actions allowed - interfaceId: 1167, compId: 42
     */

    public void selectBoss() {
        if (nextBossIndex < 0) {
            nextBossIndex = (int) (long) ClientScriptMap.getMap(5213).getValues().keySet().toArray()[Utils.getRandom(ClientScriptMap.getMap(5213).getSize())];
            if (nextBossIndex < 0)
                nextBossIndex = 1;
            player.getPackets().sendRunScript(5435, 0, nextBossIndex);
            hasBoss = true;
        } else {
            player.getPackets().sendRunScript(5435, hasBoss ? 1 : 0, nextBossIndex);
            hasBoss = true;
        }
    }

    public void openClimberMode() {
        selectBoss();
        player.getInterfaceManager().sendScreenInterface(96, 1163);
    }

    public void openEnduranceMode() {
        selectBoss();
        player.getInterfaceManager().sendScreenInterface(93, 1173);
        player.getPackets().sendIComponentText(1173, 38, String.valueOf(getProgress()));
        player.getPackets().sendIComponentText(1173, 31, String.valueOf(getFactorReward()));
    }

    public int getBossesTotalLevel() {
        GeneralRequirementMap map = getMap();
        int npc2 = map.getIntValue(2169);
        int npc3 = map.getIntValue(2170);
        int npc4 = map.getIntValue(2171);
        int npc5 = map.getIntValue(2172);
        int count = npc5 != 0 ? 5 : npc4 != 0 ? 4 : npc3 != 0 ? 3 : npc2 != 0 ? 2 : 1;
        int level = 0;
        for (int i = 0; i < count; i++)
            level += NPCDefinitions.getNPCDefinitions(map.getIntValue(2168 + i)).combatLevel;
        return level;
    }

    public NPCDefinitions getBoss() {
        GeneralRequirementMap map = getMap();
        int npc2 = map.getIntValue(2169);
        int npc3 = map.getIntValue(2170);
        int npc4 = map.getIntValue(2171);
        int npc5 = map.getIntValue(2172);
        int count = npc5 != 0 ? 5 : npc4 != 0 ? 4 : npc3 != 0 ? 3 : npc2 != 0 ? 2 : 1;
        NPCDefinitions def = null;
        for (int i = 0; i < count; i++)
            def = NPCDefinitions.getNPCDefinitions(map.getIntValue(2168 + i));
        return def;
    }

    public void openSpecialMode() {
        player.getInterfaceManager().sendScreenInterface(96, 1170);
    }

    public void openFreeStyleMode() {
        player.getInterfaceManager().sendScreenInterface(96, 1168);
    }

    public void talkToFace() {
        talkToFace(false);
    }

    public void talkToFace(boolean fromDialogue) {
        if (hasRequirements()) {
            player.getDialogueManager().startDialogue("SimpleMessage", "You need at least level 110 combat to use this tower.");
            return;
        }
        if (!talkedWithFace)
            player.getDialogueManager().startDialogue("StrangeFace");
        else {
            if (!fromDialogue)
                player.getPackets().sendVoice(7893);
            player.getInterfaceManager().sendInterface(1160);
        }
    }

    public void openRewards() {
        if (!talkedWithFace) {
            talkToFace();
            return;
        }
        player.getPackets().sendVoice(7893);
        player.getInterfaceManager().sendInterface(1156);
    }

    public void openRewardsChest() {
        if (!talkedWithFace) {
            growFace();
            return;
        }
        rewards = new ItemsContainer<Item>(getDominionFactor() > 100000 ? 20 : getDominionFactor() / 5000, true);
        for (int i = 0; i < rewards.getSize(); i++) {
            Item item = new Item(REWARD_CHEST[Utils.random(REWARD_CHEST.length)]);
            if (item.getId() == 22358 || item.getId() == 22362 || item.getId() == 22366)
                item.setId(item.getId() + Utils.random(3));
            else if (item.getDefinitions().isStackable())
                item.setAmount(Utils.random(item.getAmount(), (int) (item.getAmount() * (getDominionFactor() * 0.005))));
            else if (item.getName().toLowerCase().contains("bones")) {
                item.setAmount((int) Utils.random(1, getDominionFactor() * 0.0005));
            }
            rewards.set(i, item);
        }
        progress = 0;
        setDominionFactor(0);
        player.getInterfaceManager().sendInterface(1171);
        player.getPackets().sendInterSetItemsOptionsScript(1171, 7, 100, 8, 3, "Take", "Bank", "Discard", "Examine");
        player.getPackets().sendUnlockIComponentOptionSlots(1171, 7, 0, 20, 0, 1, 2, 3);
        refreshRewards();
    }

    private void refreshRewards() {
        if (rewards == null)
            return;
        player.getPackets().sendItems(100, rewards);
    }

    public void sendTake(int type, int slot) {
        if (rewards == null || slot >= rewards.getSize())
            return;
        Item reward = rewards.get(slot);
        if (reward == null)
            return;
        if (type == 0 && !player.getInventory().hasFreeSlots()) {
            player.getPackets().sendGameMessage("Not enough space in your inventory.");
            return;
        }
        rewards.remove(slot, reward);
        rewards.shift();
        if (type == 1)
            player.getBank().addItems(new Item[]
                    {reward}, false);
        else if (type == 0) {
            if (reward.getAmount() > 1 && reward.getDefinitions().canBeNoted())
                reward.setId(reward.getDefinitions().getCertId());
            player.getInventory().addItem(reward);
        }
        refreshRewards();
    }

    public void sendBankAll() {
        if (rewards == null)
            return;
        for (Item reward : rewards.getItemsCopy()) {
            if (reward == null)
                continue;
            player.getBank().addItems(new Item[]
                    {reward}, false);
        }
        rewards.clear();
        refreshRewards();
    }

    public void sendExamine(int slot) {
        if (rewards == null || slot >= rewards.getSize())
            return;
        Item reward = rewards.get(slot);
        if (reward == null)
            return;
        player.getPackets().sendGameMessage(ItemExamines.getExamine(reward));
    }

    public void openBankChest() {
        if (!talkedWithFace) {
            growFace();
            return;
        }
        player.getBank().openBank();
    }

    public void viewScoreBoard() {
        player.getPackets().sendGameMessage("This feature has been disabled due to rework.");
    }

    public boolean isTalkedWithFace() {
        return talkedWithFace;
    }

    public void setTalkedWithFace(boolean talkedWithFace) {
        this.talkedWithFace = talkedWithFace;
    }

    public int getProgress() {
        return progress;
    }

    public long getTotalScore() {
        return totalScore;
    }

    public void addFactor(int value) {
        if (player.getVarBitList().get(9990) == null)
            player.getVarBitList().put(9990, 0);
        int factor = player.getVarBitList().get(9990).intValue();
        factor += value;
        player.getVarsManager().sendVarBit(9990, factor, true);
    }

    public void setDominionFactor(int value) {
        player.getVarsManager().sendVarBit(9990, value, true);
    }

    public int getDominionFactor() {
        if (player.getVarBitList().get(9990) == null)
            player.getVarBitList().put(9990, 0);
        int factor = player.getVarBitList().get(9990).intValue();
        return factor;
    }

    public int getMaxFloorClimber() {
        return maxFloorClimber;
    }

    public int getMaxFloorEndurance() {
        return maxFloorEndurance;
    }

    public int getKilledBossesCount() {
        return killedBossesCount;
    }

    public boolean isForceMulti() {
        int a = getMap().getIntValue(2097);
        return a == 2 || a == 4 || a == 5;
    }
}
