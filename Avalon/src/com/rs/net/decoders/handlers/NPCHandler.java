package com.rs.net.decoders.handlers;

import java.util.concurrent.TimeUnit;

import com.rs.Settings;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.minigames.lividfarm.LividFarmControler;
import com.rs.game.minigames.pest.CommendationExchange;
import com.rs.game.npc.NPC;
import com.rs.game.npc.NpcScript;
import com.rs.game.npc.NpcScriptHandler;
import com.rs.game.npc.combat.NPCCombatDefinitions;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.npc.others.LivingRock;
import com.rs.game.npc.pet.Pet;
import com.rs.game.objects.ObjectScript;
import com.rs.game.objects.ObjectScriptHandler;
import com.rs.game.player.Player;
import com.rs.game.player.RouteEvent;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.PlayerFollow;
import com.rs.game.player.actions.Rest;
import com.rs.game.player.actions.combat.Magic;
import com.rs.game.player.actions.runecrafting.SiphonActionCreatures;
import com.rs.game.player.actions.skills.construction.Sawmill;
import com.rs.game.player.actions.skills.fishing.Fishing;
import com.rs.game.player.actions.skills.fishing.Fishing.FishingSpots;
import com.rs.game.player.actions.skills.mining.LivingMineralMining;
import com.rs.game.player.actions.skills.mining.MiningBase;
import com.rs.game.player.actions.skills.slayer.Slayer.SlayerMaster;
import com.rs.game.player.actions.skills.thieving.PickPocketAction;
import com.rs.game.player.actions.skills.thieving.PickPocketableNPC;
import com.rs.game.player.content.DungShop;
import com.rs.game.player.content.FadingScreen;
import com.rs.game.player.content.ItemSets;
import com.rs.game.player.content.PlayerLook;
import com.rs.game.player.content.customshops.CustomStoreData;
import com.rs.game.player.content.customtab.TeleportTab;
import com.rs.game.player.content.dungeoneering.rooms.puzzles.SlidingTilesRoom;
import com.rs.game.player.controlers.Falconry;
import com.rs.game.player.dialogues.Dialogue;
import com.rs.game.player.dialogues.npcs.FremennikShipmaster;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.io.InputStream;
import com.rs.utils.Logger;
import com.rs.utils.NPCSpawns;
import com.rs.utils.ShopsHandler;
import com.rs.utils.Utils;

/**
 * @Improved Andreas, Phillip - AvalonPK
 */

public class NPCHandler {

    public static void handleExamine(final Player player, InputStream stream) {
        int npcIndex = stream.readUnsignedShort128();
        boolean forceRun = stream.read128Byte() == 1;
        if (forceRun)
            player.setRun(forceRun);
        final NPC npc = World.getNPCs().get(npcIndex);
        if (npc == null || npc.hasFinished() || !player.getMapRegionsIds().contains(npc.getRegionId()))
            return;
        if (npc.getDefinitions().hasAttackOption()) {
            if (npc.getHitpoints() > 0)
                player.getPackets().sendGameMessage("%s has %s hitpoints left.", npc.getName(), npc.getHitpoints());
            else
                player.getPackets().sendGameMessage("%s is dead.", npc.getName());
        } else {
            player.getPackets().sendGameMessage("It's " + player.grammar(npc) + " " + npc.getName() + ".");
        }
        if (player.dropTesting) {
            try {
                if (NPCSpawns.removeSpawn(npc)) {
                    player.getPackets().sendGameMessage("Removed spawn!");
                    return;
                }
            } catch (Throwable e) {
                e.printStackTrace();
            }
            player.getPackets().sendGameMessage("Failed removing spawn!");
        }
        if (Settings.DEBUG) {
            player.sm("NpcId: " + npc.getId() + ", Index: " + npcIndex);
            player.sm(npc.getName() + " size:" + npc.getSize());
            player.sm("Visible On Map: " + npc.getDefinitions().isVisibleOnMap);
            player.sm("Visible:" + npc.getDefinitions().aBoolean849);
            for (int id : npc.getDefinitions().modelIds)
                player.sm("ModelId: " + id);
        }
    }

    public static void handleOption1(final Player player, final InputStream stream) {
        int npcIndex = stream.readUnsignedShort128();
        boolean forceRun = stream.read128Byte() == 1;
        final NPC npc = World.getNPCs().get(npcIndex);
        if (npc == null || npc.isCantInteract() || npc.isDead() || npc.hasFinished()
                || !player.getMapRegionsIds().contains(npc.getRegionId()) || player.isLocked())
            return;
        player.stopAll();
        player.setNextFaceEntity(npc);
        if (forceRun)
            player.setRun(forceRun);
        NpcScript script = NpcScriptHandler.getScript(npc);
        if (script != null) {
            player.setRouteEvent(new RouteEvent(npc, new Runnable() {
                @Override
                public void run() {
                    npc.resetWalkSteps();
                    npc.faceEntity(player);
                    player.stopAll();
                    player.faceEntity(npc);
                    boolean scriptExecuted = script.processNpc(player, npc);
                    if (!scriptExecuted)
                        Logger.log("NpcScript;Option 1", "Class: " + script.getClass().getSimpleName() + ".java, Option 1 method was empty in script.");
                    if (scriptExecuted) {
                        if (Settings.DEBUG)
                            Logger.log("NpcScript;Option 1", "Class: " + script.getClass().getSimpleName() + ".java, Name of Npc: " + npc.getName() + ", NpcId: " + npc.getId());
                        return;
                    }
                }
            }, true));
        }
        if (SlidingTilesRoom.handleSlidingBlock(player, npc))
            return;
        if (npc.getId() == 4296 || npc.getId() == 6362 || npc.getDefinitions().name.toLowerCase().contains("banker")
                || npc.getDefinitions().name.toLowerCase().contains("gundai")) {
            player.setRouteEvent(new RouteEvent(npc, new Runnable() {
                @Override
                public void run() {
                    if (!player.withinDistance(npc, 3))
                        return;
                    npc.resetWalkSteps();
                    npc.faceEntity(player);
                    player.faceEntity(npc);
                    player.getDialogueManager().startDialogue("Banker", npc.getId());
                    return;
                }
            }, true));
            return;
        }
        if (npc.getId() == 8091) {
			player.getDialogueManager().startDialogue("StarSprite");
        }
        if (npc.getId() == 11460) {
			player.getDialogueManager().startDialogue("RichardManeyMembershipD");
        }
        if (npc.getId() == 733) {
            npc.resetWalkSteps();
            npc.faceEntity(player);
            player.faceEntity(npc);
            if (player.getTreasureTrailsManager().useNPC(npc))
                return;
            else {
                player.sm(npc.getName() + " is to busy talking to you.");
                return;
            }
        }
        if (npc.getId() == 2593 || npc.getDefinitions().name.toLowerCase().contains("clerk")) {
            player.setRouteEvent(new RouteEvent(npc, new Runnable() {
                @Override
                public void run() {
                    if (player.getPlayerRank().isIronman()) {
                        player.sm("You cannot use the Grand Exchange in this game mode.");
                        return;
                    }
                    if (!player.withinDistance(npc, 3))
                        return;
                    npc.resetWalkSteps();
                    npc.faceEntity(player);
                    player.faceEntity(npc);
                    player.getGeManager().openGrandExchange();
                    return;
                }
            }, true));
            return;
        }
        if (player.getControlerManager().getControler() instanceof Falconry) {
            player.getControlerManager().getControler().processNPCClick1(npc);
        	return;   
        }
        if (SiphonActionCreatures.siphon(player, npc))
            return;
        player.setRouteEvent(new RouteEvent(npc, new Runnable() {
            @Override
            public void run() {
                npc.resetWalkSteps();
                player.faceEntity(npc);
                npc.faceEntity(player);
                if (!player.getControlerManager().processNPCClick1(npc))
                    return;
                if (player.getTreasureTrailsManager().useNPC(npc))
                    return;
                if (CityEventHandler.handleNPCClick(player, npc, npc.getId()))
                    return;
                FishingSpots spot = FishingSpots.forId(npc.getId() | 1 << 24);
                if (spot != null) {
                    player.getActionManager().setAction(new Fishing(spot, npc));
                    return; // its a spot, they wont face us
                } else if (npc.getId() >= 8837 && npc.getId() <= 8839) {
                    player.getActionManager().setAction(new LivingMineralMining((LivingRock) npc));
                    return;
                    /*
                     * } else if (npc.getId() == 2593) { player.getGeManager().openGrandExchange();
                     * return;
                     */
                } else if (SlayerMaster.startInteractionForId(player, npc.getId(), 1))
                    return;
                else if (npc.getId() == 5141)
                    player.getDialogueManager().startDialogue("UgiDialouge", npc);
                else if (npc.getId() == 43) {
                    if (!player.getInventory().containsOneItem(1735)) {
                        player.getPackets().sendGameMessage("You need some shears to shear the sheep.");
                        return;
                    }
                    int npcId = npc.getId();
                    switch (Utils.getRandom(2)) {
                        case 0:
                        case 1:
                            player.lock(2);
                            npc.setNextForceTalk(new ForceTalk("Baa!"));
                            npc.playSound(756, 1);
                            npc.addWalkSteps(npcId, npcId, 4, true);
                            npc.setRun(true);
                            player.getPackets().sendGameMessage("The sheep runs away from you.");
                            break;
                        case 2:
                            if (player.getInventory().addItem(1737, 1))
                                npc.transformIntoNPC(42);
                            player.animate(new Animation(893));
                            CoresManager.slowExecutor.schedule(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        npc.transformIntoNPC(npcId);
                                    } catch (Throwable e) {
                                        Logger.handle(e);
                                    }
                                }

                            }, 60000, TimeUnit.MILLISECONDS);
                            break;
                    }
                } else if (npc.getName().contains("Marker plant")) {
                    if (npc.getOwner() == player) {
                        player.animate(new Animation(9497));
                        npc.finish();
                        player.setMarker(false);
                        player.getPackets().sendGameMessage("You pull up the plant and throw it away.");
                        return;
                    } else {
                        player.getPackets().sendGameMessage("You do not have permission to pull up the plant.");
                        return;
                    }
                } else if (npc.getId() == 8020 || npc.getId() == 5195) {
                    player.getInterfaceManager().openGameTab(3);
                    TeleportTab.open(player);
                    Dialogue.sendNPCDialogueNoContinue(player, 5195, 9827, "To teleport around in "
                            + Settings.FORMAL_SERVER_NAME + ", Pick a teleport from the teleporting tab.");
                } else if (npc.getId() == 4247 || npc.getId() == 6715)
                    player.getDialogueManager().startDialogue("EstateAgentD", npc.getId());
                else if (npc.getId() == 650)
                    player.getDialogueManager().startDialogue("StoreD");
                else if (npc.getId() == 231)
                    player.getDialogueManager().startDialogue("MiscStoreD");
                else if (npc.getId() == 6988)
                    player.getDialogueManager().startDialogue("Pikkupstix", npc.getId());
                else if (npc.getId() == 4250)
                    player.getDialogueManager().startDialogue("SawmillOperator", npc.getId());
                else if (npc.getName().contains("Zeke"))
                    ShopsHandler.openShop(player, 1);
                else if (npc.getName().contains("Ranael"))
                    ShopsHandler.openShop(player, 2);
                else if (npc.getName().contains("Louie"))
                    ShopsHandler.openShop(player, 3);
                else if (npc.getName().contains("Gem trader"))
                    ShopsHandler.openShop(player, 4);
                else if (npc.getId() == 6654)
					player.getDialogueManager().startDialogue("SuakD");
                else if (npc.getName().contains("Dommik"))
                    ShopsHandler.openShop(player, 5);
                else if (npc.getName().contains("Sigmund"))
                    player.getTradeStore().openTrade();
                else if (npc.getId() == 524 || npc.getId() == 525)
                    player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 6);
                else if (npc.getName().contains("Aubury"))
                    ShopsHandler.openShop(player, 7);
                else if (npc.getId() == 15513 || npc.getId() >= 11303 && npc.getId() <= 11307)
                    player.getDialogueManager().startDialogue("ServantDialogue", npc.getId());
                else if (npc.getId() == 548)
                    ShopsHandler.openShop(player, 8);
                else if (npc.getId() == 549)
                    ShopsHandler.openShop(player, 9);
                else if (npc.getId() == 550)
                    ShopsHandler.openShop(player, 10);
                else if (npc.getId() == 551 || npc.getId() == 552)
                    ShopsHandler.openShop(player, 11);
                else if (npc.getId() == 546)
                    ShopsHandler.openShop(player, 12);
                else if (npc.getId() == 522 || npc.getId() == 523)
                    player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 13);
                else if (npc.getId() == 538)
                    ShopsHandler.openShop(player, 14);
                else if (npc.getId() == 528 || npc.getId() == 529)
                    player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 15);
                else if (npc.getId() == 520 || npc.getId() == 521)
                    player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 17);
                else if (npc.getId() == 577)
                    ShopsHandler.openShop(player, 18);
                else if (npc.getId() == 580)
                    ShopsHandler.openShop(player, 19);
                else if (npc.getId() == 584)
                    ShopsHandler.openShop(player, 20);
                else if (npc.getId() == 581)
                    ShopsHandler.openShop(player, 21);
                else if (npc.getId() == 526 || npc.getId() == 527)
                    player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 22);
                else if (npc.getId() == 579)
                    ShopsHandler.openShop(player, 23);
                else if (npc.getId() == 594)
                    ShopsHandler.openShop(player, 24);
                else if (npc.getId() == 583)
                    ShopsHandler.openShop(player, 25);
                else if (npc.getId() == 559)
                    ShopsHandler.openShop(player, 26);
                else if (npc.getId() == 12261)
                    ShopsHandler.openShop(player, 27);
                else if (npc.getId() == 556)
                    ShopsHandler.openShop(player, 28);
                else if (npc.getId() == 557)
                    ShopsHandler.openShop(player, 29);
                else if (npc.getId() == 1860)
                    ShopsHandler.openShop(player, 30);
                else if (npc.getId() == 585)
                    ShopsHandler.openShop(player, 31);
                else if (npc.getId() == 555)
                    player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 32);
                else if (npc.getId() == 536)
                    ShopsHandler.openShop(player, 33);
                else if (npc.getId() == 537)
                    ShopsHandler.openShop(player, 34);
                else if (npc.getId() == 11674 || npc.getId() == 11678)
                    player.getDialogueManager().startDialogue("GeneralStore", npc.getId(), 36);
                else if (npc.getId() == 568)
                    ShopsHandler.openShop(player, 37);
                else if (npc.getId() == 970)
                    ShopsHandler.openShop(player, 38);
                else if (npc.getId() == 8864)
                    ShopsHandler.openShop(player, 39);
                else if (npc.getId() == 11547)
                    ShopsHandler.openShop(player, 40);
                if (npc.getId() == 15231) {
                	player.getDialogueManager().startDialogue("ArtisanWorskshopEnterD");
                	return;
                }
                else if (npc.getId() == 747) {
                    player.getDialogueManager().startDialogue("Oziach", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 1511) {
                    player.getDialogueManager().startDialogue("RangingStore", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 1512) {
                    player.getDialogueManager().startDialogue("MeleeStore", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 1513) {
                    player.getDialogueManager().startDialogue("MagicStore", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 1699) {
                    player.getDialogueManager().startDialogue("SuppliesStore", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 659) {
                    player.getDialogueManager().startDialogue("PartyPete", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 741) {
                    player.getDialogueManager().startDialogue("DukeHoracio", npc.getId());
                    npc.faceEntity(player);
                } else if (npc.getId() == 300) {
                    player.getDialogueManager().startDialogue("Sedridor", npc.getId());
                    npc.faceEntity(player);
                } else if (npc.getId() == 5913) {
                    player.getDialogueManager().startDialogue("Aubury", npc.getId());
                    npc.faceEntity(player);
                } else if (npc.getId() == 6521) {
                    player.getDialogueManager().startDialogue("AdventureLog");
                    npc.faceEntity(player);
                } else if (npc.getId() >= 6135 && npc.getId() <= 6139) {
                    player.getDialogueManager().startDialogue("Town");
                    npc.faceEntity(player);
                } else if (npc.getId() == 13295) {
                    player.getDialogueManager().startDialogue("ExplorerJack");
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 9361) {
                    npc.faceEntity(player);
                    player.getDialogueManager().startDialogue("GypsyAris", npc.getId());
                    return;
                } else if (npc.getId() == 8977) {
                    player.getDialogueManager().startDialogue("Death", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 6537) {
                    player.getDialogueManager().startDialogue("Mandrith", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 705) {
                    player.getDialogueManager().startDialogue("MeleeInstructor", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 1861) {
                    player.getDialogueManager().startDialogue("RangeInstructor", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 4707) {
                    player.getDialogueManager().startDialogue("MagicInstructor", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 740) {
                    player.getDialogueManager().startDialogue("Trufitus", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 0) {
                    player.getDialogueManager().startDialogue("ReferAFriend", npc.getId());
                } else if (npc.getId() == 3373) {
                    player.getDialogueManager().startDialogue("Max", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 8929) {
                    player.getDialogueManager().startDialogue("LordMarshalBrogan", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 9462 && player.getAttackedByDelay() < Utils.currentTimeMillis()) {
                    NPCCombatDefinitions defs = npc.getCombatDefinitions();
                    player.animate(new Animation(4278));
                    WorldTasksManager.schedule(new WorldTask() {
                        @Override
                        public void run() {
                            npc.transformIntoNPC(npc.getId() + 1);
                            npc.getCombat().addCombatDelay(defs.getAttackEmote());
                            npc.getCombat().setTarget(player);
                            npc.setAttackedByDelay(4000);
                            npc.animate(new Animation(12795));
                        }
                    }, 1);
                    return;
                } else if (npc.getId() == 9464 && player.getAttackedByDelay() < Utils.currentTimeMillis()) {
                    NPCCombatDefinitions defs = npc.getCombatDefinitions();
                    player.animate(new Animation(4278));
                    WorldTasksManager.schedule(new WorldTask() {
                        @Override
                        public void run() {
                            npc.transformIntoNPC(npc.getId() + 1);
                            npc.getCombat().addCombatDelay(defs.getAttackEmote());
                            npc.getCombat().setTarget(player);
                            npc.setAttackedByDelay(4000);
                            npc.animate(new Animation(12795));
                        }
                    }, 1);
                    return;
                } else if (npc.getId() == 9466 && player.getAttackedByDelay() < Utils.currentTimeMillis()) {
                    NPCCombatDefinitions defs = npc.getCombatDefinitions();
                    player.animate(new Animation(4278));
                    WorldTasksManager.schedule(new WorldTask() {
                        @Override
                        public void run() {
                            npc.transformIntoNPC(npc.getId() + 1);
                            npc.getCombat().addCombatDelay(defs.getAttackEmote());
                            npc.getCombat().setTarget(player);
                            npc.setAttackedByDelay(4000);
                            npc.animate(new Animation(12795));
                        }
                    }, 1);
                    return;
                }
                if (player.getTreasureTrailsManager().useNPC(npc))
                    return;
                else if (npc.getId() == 2824) {
                    player.getDialogueManager().startDialogue("LeatherTanning", npc.getId());
                } else if (npc.getId() == 961 || npc.getId() == 960) {
                    player.getPrayer().restorePrayer(player.getSkills().getLevelForXp(Skills.PRAYER) * 10);
                    if (player.getPoison().isPoisoned())
                        player.getPoison().reset();
                    player.setRunEnergy(100);
                    player.heal(player.getMaxHitpoints());
                    player.getSkills().restoreSkills();
                    player.getAppearence().generateAppearenceData();
                    player.getSkills().set(Skills.SUMMONING, player.getSkills().getLevelForXp(Skills.SUMMONING));
                    player.getSkills().refresh(Skills.SUMMONING);
                    player.getCombatDefinitions().resetSpecialAttack();
                    player.animate(new Animation(8502));
                    player.gfx(new Graphics(1308));
                    player.getPackets().sendGameMessage("The nurse heals you.");
                } else if (npc.getId() == 8635) {
                    npc.faceEntity(player);
                    player.getDialogueManager().startDialogue("Giles", npc.getId());
                    return;
                } else if (npc.getId() == 3709) {
                    npc.faceEntity(player);
                    player.getDialogueManager().startDialogue("MrEx", npc.getId());
                    return;
                } else if (npc.getId() == 904) {
                    npc.faceEntity(player);
                    player.getDialogueManager().startDialogue("ChamberGuardian", npc.getId());
                } else if (npc.getId() == 3670) {
                    npc.faceEntity(npc);
                    player.getDialogueManager().startDialogue("Estocada", npc.getId());
                    return;
                } else if (npc.getId() == 2253) {
                    player.getDialogueManager().startDialogue("WiseOldManOptions");
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 905) {
                    player.getDialogueManager().startDialogue("Kolodion");
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 1972) {
                    player.getDialogueManager().startDialogue("Rasolo", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 9711) {
                    DungShop.openShop(player);
                    npc.faceEntity(npc);
                    return;
                } else if (npc.getId() == 5915) {
                    player.getDialogueManager().startDialogue("ClanDialogue");
                } else if (npc.getId() == 6829 || npc.getId() == 6830) {
                    player.getDialogueManager().startDialogue("SpiritWolf", npc.getId());
                } else if (npc.getId() == 7343 || npc.getId() == 7344) {
                    player.getDialogueManager().startDialogue("SteelTitan", npc.getId());
                } else if (npc.getId() == 7375 || npc.getId() == 7376) {
                    player.getDialogueManager().startDialogue("IronTitan", npc.getId());
                } else if (npc.getId() == 7339 || npc.getId() == 7340) {
                    player.getDialogueManager().startDialogue("GeyserTitan", npc.getId());
                } else if (npc.getId() == 13958) {
                    player.getDialogueManager().startDialogue("PvMTeles");
                } else if (npc.getId() == 13939) {
                    player.getDialogueManager().startDialogue("MinigameTeles");
                } else if (npc.getId() == 2676) {
                    player.getDialogueManager().startDialogue("Appearence");
                } else if (npc.getId() == 13463) {
                    player.getDialogueManager().startDialogue("MemberXPRates");
                    return;
                } else if (npc.getId() == 6524) {
                    player.getDialogueManager().startDialogue("BobBarterD", npc.getId());
                    return;
                } else if (npc.getId() == 543) {
                    player.getDialogueManager().startDialogue("Karim", npc.getId());
                    return;
                } else if (npc.getId() == 2257) {
                    Magic.sendJewerlyTeleportSpell(player, true, 9603, 1684, 4, new WorldTile(3040, 4845, 0));
                } else if (npc.getId() == 7950) {
                    player.getDialogueManager().startDialogue("Blacklist");
                } else if (npc instanceof Familiar) {
                    Familiar familiar = (Familiar) npc;
                    if (player.getFamiliar() != familiar) {
                        player.getPackets().sendGameMessage("That isn't your familiar.");
                        return;
                    }
                }
                if (npc instanceof Familiar) {
                    if (npc.getDefinitions().hasOption("interact")) {
                        if (player.getFamiliar() != npc) {
                            player.getPackets().sendGameMessage("That isn't your familiar.");
                            return;
                        }
                        if (npc.getId() == npc.getId())
                            ;
                        player.getDialogueManager().startDialogue("FamiliarOptions");
                        return;
                    }
                }
                if (npc instanceof Pet) {
                    Pet pet = (Pet) npc;
                    if (pet != player.getPet()) {
                        player.getPackets().sendGameMessage("This isn't your pet.");
                        return;
                    }
                    if (!player.getInventory().hasFreeSlots()) {
                        player.getPackets().sendGameMessage("You don't have enough inventory space.");
                        return;
                    }
                    player.animate(new Animation(827));
                    pet.pickup();
                } else {
                    switch (npc.getDefinitions().name.toLowerCase()) {
                        case "musician":
                        case "drummer":
                            if (player.isResting()) {
                                player.stopAll();
                                return;
                            }
                            if (player.getEmotesManager().isDoingEmote()) {
                                player.getPackets().sendGameMessage("You can't rest while perfoming an emote.");
                                return;
                            }
                            if (player.isLocked()) {
                                player.getPackets().sendGameMessage("You can't rest while perfoming an action.");
                                return;
                            }
                            player.stopAll();
                            player.getActionManager().setAction(new Rest());
                            break;
                        default:
                            // player.getPackets().sendGameMessage("Nothing
                            // interesting happens.");
                            if (Settings.DEBUG)
                                System.out.println("clicked 1 at npc[" + npc.getIndex() + "] id : " + npc.getId() + ", "
                                        + npc.getX() + ", " + npc.getY() + ", " + npc.getPlane());
                            if (player.isDeveloper())
                                player.sm("clicked 1 at npc[" + npc.getIndex() + "] id : " + npc.getId() + ", " + npc.getX()
                                        + ", " + npc.getY() + ", " + npc.getPlane());
                            break;
                    }
                }
            }
        }));
    }

    public static void handleOption2(final Player player, InputStream stream) {
        int npcIndex = stream.readUnsignedShort128();
        boolean forceRun = stream.read128Byte() == 1;
        final NPC npc = World.getNPCs().get(npcIndex);
        if (npc == null || npc.isCantInteract() || npc.isDead() || npc.hasFinished()
                || !player.getMapRegionsIds().contains(npc.getRegionId()))
            return;
        player.stopAll(false);
        player.setNextFaceEntity(npc);
        if (forceRun)
            player.setRun(forceRun);
        NpcScript script = NpcScriptHandler.getScript(npc);
        if (script != null) {
            player.setRouteEvent(new RouteEvent(npc, new Runnable() {
                @Override
                public void run() {
                    npc.resetWalkSteps();
                    npc.faceEntity(player);
                    player.stopAll();
                    player.faceEntity(npc);
                    boolean scriptExecuted = script.processNpc2(player, npc);
                    if (!scriptExecuted)
                        Logger.log("NpcScript;Option 2", "Class: " + script.getClass().getSimpleName() + ".java, Option 2 method was empty in script.");
                    if (scriptExecuted) {
                        if (Settings.DEBUG)
                            Logger.log("NpcScript;Option 2", "Class: " + script.getClass().getSimpleName() + ".java, Name of Npc: " + npc.getName() + ", NpcId: " + npc.getId());
                        return;
                    }
                }
            }, true));
        }
        if (npc.getId() == 4296 || npc.getId() == 6362 || npc.getDefinitions().name.toLowerCase().contains("banker")
                || npc.getDefinitions().name.toLowerCase().contains("gundai")) {
            player.setRouteEvent(new RouteEvent(npc, new Runnable() {
                @Override
                public void run() {
                    player.faceEntity(npc);
                    if (!player.withinDistance(npc, 2))
                        return;
                    npc.setNextFaceWorldTile(new WorldTile(player.getX(), player.getY(), player.getPlane()));
                    player.getBank().openBank();
                    return;
                }
            }, true));
            return;
        }
        if (npc.getId() == 2593 || npc.getDefinitions().name.toLowerCase().contains("clerk")) {
            player.setRouteEvent(new RouteEvent(npc, new Runnable() {
                @Override
                public void run() {
                    if (!player.withinDistance(npc, 2))
                        return;
                    if (player.getPlayerRank().isIronman()) {
                        player.sm("You cannot use the Grand Exchange in this game mode.");
                        return;
                    }
                    npc.resetWalkSteps();
                    npc.setNextFaceWorldTile(new WorldTile(player.getX(), player.getY(), player.getPlane()));
                    player.faceEntity(npc);
                    player.getGeManager().openGrandExchange();
                    return;
                }
            }, true));
            return;
        }
        player.setRouteEvent(new RouteEvent(npc, new Runnable() {
            @Override
            public void run() {
                npc.resetWalkSteps();
                player.faceEntity(npc);
                npc.setNextFaceWorldTile(new WorldTile(player.getX(), player.getY(), player.getPlane()));
                FishingSpots spot = FishingSpots.forId(npc.getId() | (2 << 24));
                if (spot != null) {
                    player.getActionManager().setAction(new Fishing(spot, npc));
                    return;
                }
                PickPocketableNPC pocket = PickPocketableNPC.get(npc.getId());
                if (pocket != null) {
                    if (Settings.FREE_TO_PLAY) {
                        player.getPackets().sendGameMessage("You can't do thieving in free to play.");
                        return;
                    }
                    player.getActionManager().setAction(new PickPocketAction(npc, pocket));
                    return;
                }
                if (npc instanceof Familiar) {
                    if (npc.getDefinitions().hasOption("store")) {
                        if (player.getFamiliar() != npc) {
                            player.getPackets().sendGameMessage("That isn't your familiar.");
                            return;
                        }
                        player.getFamiliar().store();
                    } else if (npc.getDefinitions().hasOption("cure")) {
                        if (player.getFamiliar() != npc) {
                            player.getPackets().sendGameMessage("That isn't your familiar.");
                            return;
                        }
                        if (!player.getPoison().isPoisoned()) {
                            player.getPackets().sendGameMessage("Your arent poisoned or diseased.");
                            return;
                        } else {
                            player.getFamiliar().drainSpecial(2);
                            player.addPoisonImmune(120);
                        }
                    }
                    return;
                }
                if (!player.getControlerManager().processNPCClick2(npc))
                    return;
                switch (npc.getDefinitions().name.toLowerCase()) {
                    case "void knight":
                        CommendationExchange.openExchangeShop(player);
                        break;
                }
                if (npc.getId() == 9707)
                    FremennikShipmaster.sail(player, true);
                else if (SlayerMaster.startInteractionForId(player, npc.getId(), 2))
                    return;
                else if (npc.getId() == 961 || npc.getId() == 960) {
                    player.getPrayer().restorePrayer(player.getSkills().getLevelForXp(Skills.PRAYER) * 10);
                    if (player.getPoison().isPoisoned())
                        player.getPoison().reset();
                    player.setRunEnergy(100);
                    player.heal(player.getMaxHitpoints());
                    player.getSkills().restoreSkills();
                    player.getAppearence().generateAppearenceData();
                    player.getSkills().set(Skills.SUMMONING, player.getSkills().getLevelForXp(Skills.SUMMONING));
                    player.getSkills().refresh(Skills.SUMMONING);
                    player.getCombatDefinitions().resetSpecialAttack();
                    player.animate(new Animation(8502));
                    player.gfx(new Graphics(1308));
                    player.getPackets().sendGameMessage("The nurse heals you.");
                } else if (npc.getId() == 2824) {
                    player.getDialogueManager().startDialogue("LeatherTanning", npc.getId());
                } else if (npc.getId() == 3373) {
                    player.getActionManager().setAction(new PlayerFollow(npc));
                } else if (npc.getId() == 8929) {
                    player.getDialogueManager().startDialogue("Auras", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 5915)
                    player.getDialogueManager().startDialogue("ClaimClanItem", npc.getId(), 20709);
                else if (npc.getId() == 13633)
                    player.getDialogueManager().startDialogue("ClaimClanItem", npc.getId(), 20708);
                else if (npc.getId() == 9708)
                    FremennikShipmaster.sail(player, false);
                else if (npc.getName().contains("Sigmund"))
                    player.getTradeStore().openTrade();
                else if (npc.getId() == 4250)
                    Sawmill.openPlanksConverter(player);
                else if (npc.getId() == 13455 || npc.getId() == 2617 || npc.getId() == 2618 || npc.getId() == 15194)
                    player.getBank().openBank();
                else if (npc.getId() == 1699) {
                    player.getDialogueManager().startDialogue("SuppliesStore", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 11703 || npc.getId() == 11704 || npc.getId() == 11705) {
                    boolean charter = false;
                    if (player.canBuy(30)) {
                        charter = true;
                    } else {
                        player.getPackets().sendGameMessage("You need at least 30 coins to charter this boat.");
                    }
                    if (charter) {
                        final long time = FadingScreen.fade(player);
                        player.getPackets().sendGameMessage("You pay the fare and sail to Karamja.");
                        CoresManager.slowExecutor.execute(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    FadingScreen.unfade(player, time, new Runnable() {
                                        @Override
                                        public void run() {
                                            player.setNextWorldTile(new WorldTile(2956, 3146, 0));
                                        }
                                    });
                                } catch (Throwable e) {
                                    Logger.handle(e);
                                }
                            }
                        });
                    }
                } else if (npc.getName().contains("Zeke"))
                    ShopsHandler.openShop(player, 1);
                else if (npc.getName().contains("Ranael"))
                    ShopsHandler.openShop(player, 2);
                else if (npc.getName().contains("Louie"))
                    ShopsHandler.openShop(player, 3);
                else if (npc.getName().contains("Gem trader"))
                    ShopsHandler.openShop(player, 4);
                else if (npc.getName().contains("Dommik"))
                    ShopsHandler.openShop(player, 5);
                else if (npc.getId() == 524 || npc.getId() == 525)
                    ShopsHandler.openShop(player, 6);
                else if (npc.getName().contains("Aubury"))
                    ShopsHandler.openShop(player, 7);
                else if (npc.getId() == 548)
                    ShopsHandler.openShop(player, 8);
                else if (npc.getId() == 549)
                    ShopsHandler.openShop(player, 9);
                else if (npc.getId() == 550)
                    ShopsHandler.openShop(player, 10);
                else if (npc.getId() == 551 || npc.getId() == 552)
                    ShopsHandler.openShop(player, 11);
                else if (npc.getId() == 546)
                    ShopsHandler.openShop(player, 12);
                else if (npc.getId() == 522 || npc.getId() == 523)
                    ShopsHandler.openShop(player, 13);
                else if (npc.getId() == 538)
                    ShopsHandler.openShop(player, 14);
                else if (npc.getId() == 528 || npc.getId() == 529)
                    ShopsHandler.openShop(player, 15);
                else if (npc.getId() == 519)
                    ShopsHandler.openShop(player, 16);
                else if (npc.getId() == 520 || npc.getId() == 521)
                    ShopsHandler.openShop(player, 17);
                else if (npc.getId() == 577)
                    ShopsHandler.openShop(player, 18);
                else if (npc.getId() == 580)
                    ShopsHandler.openShop(player, 19);
                else if (npc.getId() == 584)
                    ShopsHandler.openShop(player, 20);
                else if (npc.getId() == 581)
                    ShopsHandler.openShop(player, 21);
                else if (npc.getId() == 526 || npc.getId() == 527)
                    ShopsHandler.openShop(player, 22);
                else if (npc.getId() == 579)
                    ShopsHandler.openShop(player, 23);
                else if (npc.getId() == 594)
                    ShopsHandler.openShop(player, 24);
                else if (npc.getId() == 583)
                    ShopsHandler.openShop(player, 25);
                else if (npc.getId() == 559)
                    ShopsHandler.openShop(player, 26);
                else if (npc.getId() == 12261)
                    ShopsHandler.openShop(player, 27);
                else if (npc.getId() == 556)
                    ShopsHandler.openShop(player, 28);
                else if (npc.getId() == 557)
                    ShopsHandler.openShop(player, 29);
                else if (npc.getId() == 1860)
                    ShopsHandler.openShop(player, 30);
                else if (npc.getId() == 585)
                    ShopsHandler.openShop(player, 31);
                else if (npc.getId() == 555)
                    ShopsHandler.openShop(player, 32);
                else if (npc.getId() == 536)
                    ShopsHandler.openShop(player, 33);
                else if (npc.getId() == 537)
                    ShopsHandler.openShop(player, 34);
                else if (npc.getId() == 747)
                    ShopsHandler.openShop(player, 35);
                else if (npc.getId() == 11674 || npc.getId() == 11678)
                    ShopsHandler.openShop(player, 36);
                else if (npc.getId() == 568)
                    ShopsHandler.openShop(player, 37);
                else if (npc.getId() == 970)
                    ShopsHandler.openShop(player, 38);
                else if (npc.getId() == 8864)
                    ShopsHandler.openShop(player, 39);
                else if (npc.getId() == 11547)
                    ShopsHandler.openShop(player, 40);
                else if (npc.getId() == 15149)
                    player.getDialogueManager().startDialogue("MasterOfFear", 3);
                else if (npc.getId() == 2676)
                    PlayerLook.openMageMakeOver(player);
                else if (npc.getId() == 598)
                    PlayerLook.openHairdresserSalon(player);
                else if (npc.getId() == 1301)
                    PlayerLook.openYrsaShop(player);
                else if (npc.getId() == 6988)
                    player.getCustomStore().sendInterface(player, 0, CustomStoreData.SUMMONING);
                else if (npc instanceof Pet) {
                    if (npc != player.getPet()) {
                        player.getPackets().sendGameMessage("This isn't your pet!");
                        return;
                    }
                    Pet pet = player.getPet();
                    player.getPackets().sendMessage(99,
                            "Pet [id=" + pet.getId() + ", hunger=" + pet.getDetails().getHunger() + ", growth="
                                    + pet.getDetails().getGrowth() + ", stage=" + pet.getDetails().getStage() + "].",
                            player);
                } else {
                    player.getPackets().sendGameMessage("Nothing interesting happens.");
                    if (Settings.DEBUG)
                        System.out.println("clicked 2 at npc id : " + npc.getId() + ", " + npc.getX() + ", "
                                + npc.getY() + ", " + npc.getPlane());
                    if (player.isDeveloper())
                        player.getPackets().sendGameMessage("clicked 2 at npc id : " + npc.getId() + ", " + npc.getX()
                                + ", " + npc.getY() + ", " + npc.getPlane());
                }
            }
        }));
    }

    public static void handleOption3(final Player player, InputStream stream) {
        int npcIndex = stream.readUnsignedShort128();
        boolean forceRun = stream.read128Byte() == 1;
        final NPC npc = World.getNPCs().get(npcIndex);
        if (npc == null || npc.isCantInteract() || npc.isDead() || npc.hasFinished()
                || !player.getMapRegionsIds().contains(npc.getRegionId()))
            return;
        player.stopAll(false);
        player.setNextFaceEntity(npc);
        if (forceRun)
            player.setRun(forceRun);
        NpcScript script = NpcScriptHandler.getScript(npc);
        if (script != null) {
            player.setRouteEvent(new RouteEvent(npc, new Runnable() {
                @Override
                public void run() {
                    npc.resetWalkSteps();
                    npc.faceEntity(player);
                    player.stopAll();
                    player.faceEntity(npc);
                    boolean scriptExecuted = script.processNpc3(player, npc);
                    if (!scriptExecuted)
                        Logger.log("NpcScript;Option 3", "Class: " + script.getClass().getSimpleName() + ".java, Option 3 method was empty in script.");
                    if (scriptExecuted) {
                        if (Settings.DEBUG)
                            Logger.log("NpcScript;Option 3", "Class: " + script.getClass().getSimpleName() + ".java, Name of Npc: " + npc.getName() + ", NpcId: " + npc.getId());
                        return;
                    }
                }
            }, true));
        }
        if (npc.getId() == 6362 || npc.getDefinitions().name.toLowerCase().contains("banker")
                || npc.getDefinitions().name.toLowerCase().contains("gundai")) {
            player.setRouteEvent(new RouteEvent(npc, new Runnable() {
                @Override
                public void run() {
                    if (!player.withinDistance(npc, 2))
                        return;
                    npc.resetWalkSteps();
                    npc.setNextFaceWorldTile(new WorldTile(player.getX(), player.getY(), player.getPlane()));
                    player.faceEntity(npc);
                    player.getGeManager().openCollectionBox();
                    ;
                    return;
                }
            }, true));
            return;
        }
        if (npc.getId() == 2593 || npc.getDefinitions().name.toLowerCase().contains("clerk")) {
            player.setRouteEvent(new RouteEvent(npc, new Runnable() {
                @Override
                public void run() {
                    if (!player.withinDistance(npc, 2))
                        return;
                    if (player.getPlayerRank().isIronman()) {
                        player.sm("You cannot use the Grand Exchange in this game mode.");
                        return;
                    }
                    npc.resetWalkSteps();
                    npc.setNextFaceWorldTile(new WorldTile(player.getX(), player.getY(), player.getPlane()));
                    player.faceEntity(npc);
                    player.getGeManager().openHistory();
                    ;
                    return;
                }
            }, true));
            return;
        }
        player.setRouteEvent(new RouteEvent(npc, new Runnable() {
            @Override
            public void run() {
                npc.resetWalkSteps();
                player.faceEntity(npc);
                npc.setNextFaceWorldTile(new WorldTile(player.getX(), player.getY(), player.getPlane()));
                if (!player.getControlerManager().processNPCClick3(npc))
                    return;
                if (npc instanceof Pet) {
                    Pet pet = (Pet) npc;
                    if (npc.getDefinitions().hasOption("transform")) {
                        if (pet != player.getPet()) {
                            player.getPackets().sendGameMessage("This isn't your pet.");
                            return;
                        }
                        pet.transform();
                        return;
                    }
                }
                if (npc.getId() >= 8837 && npc.getId() <= 8839) {
                    MiningBase.propect(player, "You examine the remains...",
                            "The remains contain traces of living minerals.");
                    return;

                }
                if (SlayerMaster.startInteractionForId(player, npc.getId(), 3))
                    return;
                else if (npc.getId() == 548)
                    PlayerLook.openThessaliasMakeOver(player);
                else if (npc.getId() == 1301)
                    PlayerLook.openYrsaShop(player);
                else if (npc.getId() == 3373) {
                    player.getCustomStore().sendInterface(3);
                    return;
                } else if (npc.getId() == 8929) {
                    player.getDialogueManager().startDialogue("Titles", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 9085)
                    ShopsHandler.openShop(player, 29);
                else if (npc.getId() == 4250)
                    player.getCustomStore().sendInterface(player, 0, CustomStoreData.CONSTRUCTION);
                else if (npc.getId() == 5532) {
                    npc.setNextForceTalk(new ForceTalk("Senventior Disthinte Molesko!"));
                    player.getControlerManager().startControler("SorceressGarden");

                } else
                    player.getPackets().sendGameMessage("Nothing interesting happens.");
            }
        }));
        if (Settings.DEBUG)
            System.out.println("clicked 3 at npc id : " + npc.getId() + ", " + npc.getX() + ", " + npc.getY() + ", "
                    + npc.getPlane());
        if (player.isDeveloper())
            player.getPackets().sendGameMessage("clicked 3 at npc id : " + npc.getId() + ", " + npc.getX() + ", "
                    + npc.getY() + ", " + npc.getPlane());
    }

    public static void handleOption4(final Player player, InputStream stream) {
        int npcIndex = stream.readUnsignedShort128();
        boolean forceRun = stream.read128Byte() == 1;
        final NPC npc = World.getNPCs().get(npcIndex);
        if (npc == null || npc.isCantInteract() || npc.isDead() || npc.hasFinished()
                || !player.getMapRegionsIds().contains(npc.getRegionId()))
            return;
        player.stopAll(false);
        player.setNextFaceEntity(npc);
        if (forceRun)
            player.setRun(forceRun);
        NpcScript script = NpcScriptHandler.getScript(npc);
        if (script != null) {
            player.setRouteEvent(new RouteEvent(npc, new Runnable() {
                @Override
                public void run() {
                    npc.resetWalkSteps();
                    npc.faceEntity(player);
                    player.stopAll();
                    player.faceEntity(npc);
                    boolean scriptExecuted = script.processNpc4(player, npc);
                    if (!scriptExecuted)
                        Logger.log("NpcScript;Option 4", "Class: " + script.getClass().getSimpleName() + ".java, Option 4 method was empty in script.");
                    if (scriptExecuted) {
                        if (Settings.DEBUG)
                            Logger.log("NpcScript;Option 4", "Class: " + script.getClass().getSimpleName() + ".java, Name of Npc: " + npc.getName() + ", NpcId: " + npc.getId());
                        return;
                    }
                }
            }, true));
        }
        if (npc.getId() == 2593 || npc.getDefinitions().name.toLowerCase().contains("clerk")) {
            player.setRouteEvent(new RouteEvent(npc, new Runnable() {
                @Override
                public void run() {
                    if (!player.withinDistance(npc, 2))
                        return;
                    if (player.getPlayerRank().isIronman()) {
                        player.sm("You cannot use the Grand Exchange in this game mode.");
                        return;
                    }
                    npc.resetWalkSteps();
                    npc.setNextFaceWorldTile(new WorldTile(player.getX(), player.getY(), player.getPlane()));
                    player.faceEntity(npc);
                    ItemSets.openSets(player);
                    return;
                }
            }, true));
            return;
        }
        player.setRouteEvent(new RouteEvent(npc, new Runnable() {
            @Override
            public void run() {
                npc.resetWalkSteps();
                player.faceEntity(npc);
                npc.setNextFaceWorldTile(new WorldTile(player.getX(), player.getY(), player.getPlane()));
                if (!player.getControlerManager().processNPCClick3(npc))
                    return;
                if (npc.getId() >= 8837 && npc.getId() <= 8839) {
                    MiningBase.propect(player, "You examine the remains...",
                            "The remains contain traces of living minerals.");
                    return;

                }
                if (SlayerMaster.startInteractionForId(player, npc.getId(), 4))
                    return;
                if ((npc.getId() == 8462 || npc.getId() == 8464 || npc.getId() == 1597 || npc.getId() == 1598
                        || npc.getId() == 7780 || npc.getId() == 8467 || npc.getId() == 9084))
                    ShopsHandler.openShop(player, 29);
                else if (npc.getId() == 548)
                    PlayerLook.openThessaliasMakeOver(player);
                else if (npc.getId() == 1301)
                    PlayerLook.openYrsaShop(player);
                else if (npc.getId() == 5913) {
                    Magic.sendObjectTeleportSpell(player, true, new WorldTile(2910, 4832, 0));
                    npc.setNextForceTalk(new ForceTalk("Senventior Disthine Molenko!"));
                } else if (npc.getId() == 8929) {
                    player.getDialogueManager().startDialogue("ImbuedRings", npc.getId());
                    npc.faceEntity(player);
                    return;
                } else if (npc.getId() == 5532) {
                    npc.setNextForceTalk(new ForceTalk("Senventior Disthinte Molesko!"));
                    player.getControlerManager().startControler("SorceressGarden");

                } else
                    player.getPackets().sendGameMessage("Nothing interesting happens.");
            }
        }));
        if (Settings.DEBUG)
            System.out.println("cliked 4 at npc id : " + npc.getId() + ", " + npc.getX() + ", " + npc.getY() + ", "
                    + npc.getPlane());
        if (player.isDeveloper())
            player.sm("cliked 4 at npc id : " + npc.getId() + ", " + npc.getX() + ", " + npc.getY() + ", "
                    + npc.getPlane());
    }
}
