package com.rs.net.decoders.handlers;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.ObjectDefinitions;
import com.rs.cores.CoresManager;
import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.cityhandler.CityEventHandler;
import com.rs.game.item.Item;
import com.rs.game.item.itemdegrading.ArmourRepair;
import com.rs.game.minigames.castlewars.CastleWars;
import com.rs.game.minigames.crucible.Crucible;
import com.rs.game.minigames.fightpits.FightPits;
import com.rs.game.minigames.pest.Lander;
import com.rs.game.minigames.warriorguild.WarriorsGuild;
import com.rs.game.objects.ObjectScript;
import com.rs.game.objects.ObjectScriptHandler;
import com.rs.game.objects.scripts.DoorsAndGates;
import com.rs.game.objects.scripts.GlobalStairs;
import com.rs.game.player.CombatDefinitions;
import com.rs.game.player.Player;
import com.rs.game.player.RouteEvent;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.BoxAction;
import com.rs.game.player.actions.Charter;
import com.rs.game.player.actions.CowMilkingAction;
import com.rs.game.player.actions.WaterFilling;
import com.rs.game.player.actions.WhirlPool;
import com.rs.game.player.actions.combat.Magic;
import com.rs.game.player.actions.combat.PlayerCombat;
import com.rs.game.player.actions.construction.BoneOffering;
import com.rs.game.player.actions.runecrafting.SihponActionNodes;
import com.rs.game.player.actions.skills.agility.Agility;
import com.rs.game.player.actions.skills.agility.impl.AgilityFalador;
import com.rs.game.player.actions.skills.agility.impl.AgilityPyramid;
import com.rs.game.player.actions.skills.agility.impl.ApeAtollAgility;
import com.rs.game.player.actions.skills.agility.impl.BarbarianOutpostAgility;
import com.rs.game.player.actions.skills.agility.impl.GnomeAgility;
import com.rs.game.player.actions.skills.agility.impl.WildyAgility;
import com.rs.game.player.actions.skills.construction.House;
import com.rs.game.player.actions.skills.construction.HouseConstants;
import com.rs.game.player.actions.skills.construction.Sawmill;
import com.rs.game.player.actions.skills.cooking.Cooking;
import com.rs.game.player.actions.skills.cooking.Cooking.Cookables;
import com.rs.game.player.actions.skills.firemaking.Bonfire;
import com.rs.game.player.actions.skills.mining.EssenceMining;
import com.rs.game.player.actions.skills.mining.EssenceMining.EssenceDefinitions;
import com.rs.game.player.actions.skills.mining.LavaFlowMine;
import com.rs.game.player.actions.skills.mining.MiningBase;
import com.rs.game.player.actions.skills.newmining.Mining;
import com.rs.game.player.actions.skills.prayer.Burying.Bone;
import com.rs.game.player.actions.skills.runecrafting.AbbysObsticals;
import com.rs.game.player.actions.skills.runecrafting.CombinationRunes;
import com.rs.game.player.actions.skills.runecrafting.CombinationRunes.CombinationRunesStore;
import com.rs.game.player.actions.skills.runecrafting.Tiaras;
import com.rs.game.player.actions.skills.runecrafting.Tiaras.RunecraftingTiaraStore;
import com.rs.game.player.actions.skills.smithing.JewllerySmithing;
import com.rs.game.player.actions.skills.smithing.Smelting;
import com.rs.game.player.actions.skills.smithing.Smelting.SmeltingBar;
import com.rs.game.player.actions.skills.smithing.Smithing.ForgingBar;
import com.rs.game.player.actions.skills.smithing.Smithing.ForgingInterface;
import com.rs.game.player.actions.skills.summoning.Summoning;
import com.rs.game.player.actions.skills.woodcutting.Woodcutting;
import com.rs.game.player.actions.skills.woodcutting.Woodcutting.TreeDefinitions;
import com.rs.game.player.content.DwarfMultiCannon;
import com.rs.game.player.content.FadingScreen;
import com.rs.game.player.content.GrotwormLair;
import com.rs.game.player.content.ItemConstants;
import com.rs.game.player.content.KillScoreBoard;
import com.rs.game.player.content.WildernessKills;
import com.rs.game.player.content.dungeoneering.rooms.puzzles.FishingFerretRoom;
import com.rs.game.player.content.shootingstar.ShootingStarAction;
import com.rs.game.player.controlers.Barrows;
import com.rs.game.player.controlers.Falconry;
import com.rs.game.player.controlers.FightCaves;
import com.rs.game.player.controlers.RecipeForDisaster;
import com.rs.game.player.dialogues.npcs.MiningGuildDwarf;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.io.InputStream;
import com.rs.utils.Logger;
import com.rs.utils.Utils;

/**
 * @Improved Andreas
 */

public final class ObjectHandler {

    private ObjectHandler() {

    }

    public static void handleOption(final Player player, InputStream stream, int option) {
        if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead())
            return;
        if (player.isLocked() || player.getEmotesManager().getNextEmoteEnd() >= Utils.currentTimeMillis())
            return;

        boolean forceRun = stream.readUnsignedByte128() == 1;
        final int id = stream.readIntLE();
        int x = stream.readUnsignedShortLE();
        int y = stream.readUnsignedShortLE128();
        final WorldTile tile = new WorldTile(x, y, player.getPlane());
        final int regionId = tile.getRegionId();
        if (!player.getMapRegionsIds().contains(regionId)) {
            player.sm("map doesnt contains region");
            return;
        }
        WorldObject mapObject = World.getObjectWithId(tile, id);
        if (mapObject == null) {
            return;
        }
        if (mapObject.getId() != id) {
            return;
        }
        final WorldObject object = mapObject;
        if (option != -1)
        player.stopAll();
        if (forceRun)
            player.setRun(forceRun);
        switch (option) {
            case 1:
                handleOption1(player, object, stream);
                break;
            case 2:
                handleOption2(player, object);
                break;
            case 3:
                handleOption3(player, object);
                break;
            case 4:
                handleOption4(player, object);
                break;
            case 5:
                handleOption5(player, object);
                break;
            case -1:
                handleOptionExamine(player, object);
                break;
        }
    }

    public static void renewSummoningPoints(Player player) {
        int summonLevel = player.getSkills().getLevelForXp(Skills.SUMMONING);
        if (player.getSkills().getLevel(Skills.SUMMONING) < summonLevel) {
            player.lock(3);
            player.animate(new Animation(8502));
            player.gfx(new Graphics(1308));
            player.getSkills().set(Skills.SUMMONING, summonLevel);
            player.getPackets().sendGameMessage("You have recharged your Summoning points.", true);
        } else
            player.getPackets().sendGameMessage("You already have full Summoning points.");
    }

    public static void healFamiliar(Player owner) {
        if (owner.getFamiliar() != null && owner.getFamiliar().getHitpoints() < owner.getFamiliar().getMaxHitpoints()) {
            owner.getFamiliar().heal(owner.getFamiliar().getMaxHitpoints());
            owner.getPackets().sendGameMessage("Your follower was hurt, and is now healed.");
        } else {
            owner.getPackets().sendGameMessage("Your follower familiar is in good health.");
        }
    }

    private static void handleOption1(final Player player, final WorldObject object, InputStream stream) {
        final ObjectDefinitions objectDef = object.getDefinitions();
        final int id = object.getId();
        final int x = object.getX();
        final int y = object.getY();
        player.setNextFaceEntity(null);
        ObjectScript script = ObjectScriptHandler.getScript(object);
        if (script != null) {
            player.setRouteEvent(new RouteEvent(object, new Runnable() {
                @Override
                public void run() {
                    player.stopAll();
                    player.faceObject(object);
                    boolean scriptExecuted = script.processObject(player, object);
                    if (!scriptExecuted)
                        Logger.log("ObjectScript;Option 1", "Class: " + script.getClass().getSimpleName() + ".java, Option 1 method was empty in script.");
                    if (scriptExecuted) {
                        if (Settings.DEBUG)
                            Logger.log("ObjectScript;Option 1", "Class: " + script.getClass().getSimpleName() + ".java, Name of Object: " + object.getName() + ", ObjectId: " + object.getId());
                        return;
                    }
                }
            }, true));
            return;
        }
        if (SihponActionNodes.siphon(player, object))
            return;
        if (id >= 30555 && id <= 30557) {
            player.setRouteEvent(new RouteEvent(object, new Runnable() {

                @Override
                public void run() {
                    if (!player.withinDistance(object, 2))
                        return;
                    boolean west = player.getX() <= 3545;
                    player.animate(new Animation(west ? 753 : 752));
                    player.lock(3);
                    WorldTasksManager.schedule(new WorldTask() {

                        int x;

                        @Override
                        public void run() {
                            player.getAppearence().setRenderEmote(west ? 157 : 156);
                            if (x == 1)
                                player.addWalkSteps(west ? player.getX() + 2 : player.getX() - 2, player.getY(), 2,
                                        false);
                            if (x == 2)
                                player.addWalkSteps(west ? player.getX() + 2 : player.getX() - 2, player.getY(), 2,
                                        false);
                            if (x == 3) {
                                player.getAppearence().setRenderEmote(-1);
                                player.setNextAnimationNoPriority(new Animation(west ? 759 : 758), player);
                                stop();
                                return;
                            }
                            x++;
                        }
                    }, 0, 1);
                }
            }, true));
            return;
        }
        if (object.getId() == 69514) {
            player.setRouteEvent(new RouteEvent(object, new Runnable() {

                @Override
                public void run() {
                    if (!player.withinDistance(object, 3))
                        return;
                    GnomeAgility.RunGnomeBoard(player, object);
                    return;
                }
            }, true));
            return;
        }
        if (object.getId() == 43529) {
            player.setRouteEvent(new RouteEvent(object, new Runnable() {
                @Override
                public void run() {
                    if (!player.withinDistance(object, 4))
                        return;
                    GnomeAgility.PreSwing(player, object);
                    return;
                }
            }, true));
            return;
        }
        player.setRouteEvent(new RouteEvent(object, new Runnable() {

            @Override
            public void run() {
                player.stopAll();
                player.faceObject(object);
                if (!player.getControlerManager().processObjectClick1(object))
                    return;
                if (player.getDungManager().enterResourceDungeon(object))
                    return;
                if (GrotwormLair.handleObject1(object, player))
                    return;
                if (CastleWars.handleObjects(player, id))
                    return;
                if (CityEventHandler.handleObjectClick(player, object, object.getId()))
                    return;
                if (player.getFarmingManager().isFarming(id, null, 1))
                    return;
                if (player.getTreasureTrailsManager().useObject(object))
                    return;
                BoxAction.HunterNPC info = BoxAction.HunterNPC.forObjectId(object.getId());
                if (info != null) {
                    player.getActionManager().setAction(new BoxAction(object, info));
                    return;
                }
                if (id == 57033) {
                	player.getDialogueManager().startDialogue("SimpleItemMessage", 1275,
        					"You have mined over: " + Utils.format(player.lavaflowCrustsMined) + " crusts.");
                }
                if (info == null) {
                    BoxAction.HunterEquipment hunt = BoxAction.HunterEquipment.forObjectId(object.getId());
                    if (hunt != null) {
                        player.getActionManager().setAction(new BoxAction(hunt, object));
                        return;
                    }
                }
                if (object.getId() == 24720) {
                	player.sm("This furnace is seriously hot.");
                	return;
                }
                if (id >= 15477 && id <= 15482 && House.enterHousePortal(player))
                    return;
                if (id == 29405 || id == 29404 || id == 29403) {
                    DwarfMultiCannon.pickupCannon(player, id - 29403, object, 2);
                    return;
                }
                if (id == 29398 || id == 29401 || id == 29402) {
                    DwarfMultiCannon.pickupCannon(player, id == 29398 ? 0 : id - 29400, object, 1);
                    return;
                }
                if (id == 29406 || id == 29408) {
                    DwarfMultiCannon.fire(player, object);
                    return;
                }
                if (object.getId() == 52220 && object.getX() == 2635 && object.getY() == 9049) {
                    player.setNextWorldTile(new WorldTile(2646, 2673, 0));
                    return;
                }
                if (object.getId() == 52219) {
                    player.setNextWorldTile(new WorldTile(2634, 9049, 0));
                }
                if (object.getId() == 31844 || object.getId() == 31841 && object.getY() == 9910) {
                    player.sm("These doors look like they're better off closed..");
                    return;
                }
                if (id == 46307 && x == 3311 && y == 3491) {
                    Sawmill.enter(player, object);
                    return;
                }
                if (id == 23921) {
                    if ((player.getCombatDefinitions().getSpellId() > 0
                            && player.getCombatDefinitions().getAutoCastSpell() > 0)) {
                        player.getPackets().sendGameMessage("You can't use magic on a dummy.");
                        return;
                    } else if (PlayerCombat.isRanging(player) > 0) {
                        player.getPackets().sendGameMessage("You can't use ranged on a dummy.");
                        return;
                    }
                    int weaponId = player.getEquipment().getWeaponId();
                    final ItemDefinitions defs = ItemDefinitions.getItemDefinitions(weaponId);
                    if (defs == null || weaponId == -1)
                        player.lock(weaponId == -1 ? 3 : 4);
                    player.lock(defs.getAttackSpeed() - 1);
                    player.animate(new Animation(PlayerCombat.getWeaponAttackEmote(player.getEquipment().getWeaponId(),
                            player.getCombatDefinitions().getAttackStyle())));
                    int xpStyle = player.getCombatDefinitions().getXpStyle(player.getEquipment().getWeaponId(),
                            player.getCombatDefinitions().getAttackStyle());
                    if (xpStyle != CombatDefinitions.SHARED) {
                        player.getSkills().addXp(xpStyle, 3);
                    } else {
                        player.getSkills().addXp(Skills.ATTACK, 1);
                        player.getSkills().addXp(Skills.STRENGTH, 1);
                        player.getSkills().addXp(Skills.DEFENCE, 1);
                    }
                    player.getSkills().addXp(Skills.HITPOINTS, 1);
                    return;
                }
                if (id == 213 || id == 214) {
                    AgilityFalador.FaladorGrappleWall(player, object);
                    return;

                } else if (id == 7143 || id == 7153) {
                    AbbysObsticals.clearRocks(player, object);
                    return;
                } else if (id == 7152 || id == 7144) {
                    AbbysObsticals.clearTendrills(player, object, new WorldTile(id == 7144 ? 3028 : 3051, 4824, 0));
                    return;
                } else if (id == 7150 || id == 7146) {
                    AbbysObsticals.clearEyes(player, object,
                            new WorldTile(object.getX() == 3021 ? 3028 : 3050, 4839, 0));
                    return;
                } else if (id == 7147) {
                    AbbysObsticals.clearGap(player, object, new WorldTile(3030, 4843, 0), false);
                    return;
                } else if (id == 7148) {
                    AbbysObsticals.clearGap(player, object, new WorldTile(3040, 4845, 0), true);
                    return;
                } else if (id == 7149) {
                    AbbysObsticals.clearGap(player, object, new WorldTile(3048, 4842, 0), false);
                    return;
                } else if (id == 7151) {
                    AbbysObsticals.burnGout(player, object, new WorldTile(3053, 4831, 0));
                    return;
                } else if (id == 7145) {
                    AbbysObsticals.burnGout(player, object, new WorldTile(3024, 4834, 0));
                    return;
                }

                if (id == 9309 || id == 9310) {
                    AgilityFalador.FaladorTunnel(player, object);
                    return;
                }
                if (id >= 10850 && id <= 10888) {
                    AgilityPyramid.pyramidCourse(player, object);
                    return;
                }
                if (ApeAtollAgility.isObject(object)) {
                    ApeAtollAgility.handleObjects(object, player);
                    return;
                }
                if (id == 17051 || id == 17052) {
                    AgilityFalador.FaladorJumpDown(player, object);
                    return;
                }
                if (id == 11844) {
                    AgilityFalador.FaladorCrumbledWall(player, object);
                    return;
                }
                if (id == 2399) {
                    DoorsAndGates.handleDoorTemporary(player, object, 1200);
                    return;
                }
                if (id == 54019 || id == 54020) {
                    KillScoreBoard.showRanks(player);
                    return;
                }
                if (id == 2339) {
                    DoorsAndGates.handleDoorTemporary(player, object, 1200);
                    return;
                }
                if (id == 31314) {
                    int yPos = player.getLocation().getY() < object.getY() ? 2 : -2;
                    player.setNextWorldTile(
                            new WorldTile(player.getLocation().getX(), player.getLocation().getY() + yPos, 0));
                }
                if (id == 30560 && object.getX() == 3510 && object.getY() == 9811) {
                    player.lock(5);
                    final long time = FadingScreen.fade(player);
                    CoresManager.slowExecutor.execute(new Runnable() {
                        @Override
                        public void run() {
                            player.animate(new Animation(844));
                            try {
                                FadingScreen.unfade(player, time, new Runnable() {
                                    @Override
                                    public void run() {
                                        player.setNextWorldTile(new WorldTile(3492, 9809, 0));
                                    }
                                });
                            } catch (Throwable e) {
                                Logger.handle(e);
                            }
                        }
                    });
                    return;
                }
                if (id == 5046 && object.getX() == 3492 && object.getY() == 9808) {
                    player.lock(5);
                    final long time = FadingScreen.fade(player);
                    CoresManager.slowExecutor.execute(new Runnable() {

                        @Override
                        public void run() {
                            player.animate(new Animation(844));
                            try {
                                FadingScreen.unfade(player, time, new Runnable() {
                                    @Override
                                    public void run() {
                                        player.setNextWorldTile(new WorldTile(3511, 9811, 0));
                                    }
                                });
                            } catch (Throwable e) {
                                Logger.handle(e);
                            }
                        }
                    });
                    return;
                }
                if (id == 24360) {
                    player.movePlayer(new WorldTile(3190, 9833, 0), 1, 2);
                    return;
                }
                if (id == 24365) {
                    player.movePlayer(new WorldTile(3188, 3432, 0), 1, 2);
                    return;
                }
                if (id == 1804) {
                    if (player.getY() == 3449 && !player.getInventory().containsItem(983, 1)) {
                        player.getPackets().sendGameMessage("You need some sort of key to unlock this door.");
                        return;
                    }
                    DoorsAndGates.handleDoorTemporary(player, object, 1200);
                    return;
                }
                if (id == 7272) {
                    player.movePlayer(new WorldTile(3253, 3401, 0), 1, 2);
                    return;
                }
                if (id == 11620 || id == 11621) {
                    DoorsAndGates.handleGateTemporary(player, object, 1200);
                    return;
                }
                if (id == 11724) {
                    if (object.getX() == 2968 && object.getY() == 3347)
                        player.movePlayer(new WorldTile(player.getX() - 3, player.getY() + 1, player.getPlane() + 1), 1,
                                2);
                    else
                        player.movePlayer(new WorldTile(player.getX() + 1, player.getY() + 1, player.getPlane() + 1), 1,
                                2);
                    return;
                }
                if (id == 11725) {
                    if (object.getX() == 2968 && object.getY() == 3347)
                        player.movePlayer(new WorldTile(player.getX() + 3, player.getY() - 1, player.getPlane() - 1), 1,
                                2);
                    else
                        player.movePlayer(new WorldTile(player.getX() + 1, player.getY() + 1, player.getPlane() + 1), 1,
                                2);
                    return;
                }
                if (id == 2718 || id == 24072) {
                    if (player.hopper) {
                        player.getPackets().sendGameMessage("You operate the hopper. The grain slides down the chute.");
                        player.lever = true;
                        WorldObject mill = null;
                        if (id == 24072)
                            mill = new WorldObject(24070, 10, 1, 3140, 3449, 0);
                        else
                            mill = new WorldObject(36878, 10, 3, 3166, 3306, 0);
                        World.spawnObject(mill);
                        return;
                    }
                    player.getPackets().sendGameMessage("You pull the lever.. but nothing happens.");
                    return;
                }

                if (id == 66115 || id == 66116) {
                    Barrows.digIntoGrave(player);
                    return;
                }
                if (id == 24074) {
                    // player.movePlayer(new WorldTile(3144, 3446, 2), 1, 2);
                }
                if (id == 25154) {
                    player.movePlayer(new WorldTile(2833, 9656, 0), 1, 2);
                    return;
                }
                if (id == 36878 || id == 24070) {
                    if (!player.getInventory().containsItem(1931, 1)) {
                        player.getPackets().sendGameMessage("You need an empty pot to fill.");
                        return;
                    }
                    WorldObject mill = null;
                    if (id == 24070)
                        mill = new WorldObject(954, 10, 1, 3140, 3449, 0);
                    else
                        mill = new WorldObject(36880, 10, 3, 3166, 3306, 0);
                    World.spawnObject(mill);
                    player.getInventory().deleteItem(1931, 1);
                    player.getInventory().addItem(1933, 1);
                    player.hopper = false;
                    player.lever = false;
                    return;
                }
                if (id >= 2073 && id <= 2078) {// banana tree
                    if (id == 2078) {
                        player.getPackets().sendGameMessage("You search the tree, but there is no more bananas.");
                        return;
                    }
                    if (!player.getInventory().hasFreeSlots()) {
                        player.getPackets().sendGameMessage("Not enough inventory space.");
                        return;
                    }
                    WorldObject fullTree = new WorldObject(2073, object.getType(), object.getRotation(), object.getX(),
                            object.getY(), object.getPlane());
                    World.spawnObjectTemporary(new WorldObject(id + 1, fullTree.getType(), fullTree.getRotation(),
                            fullTree.getX(), fullTree.getY(), fullTree.getPlane()), 60000);
                    player.getInventory().addItem(1963, 1);
                    player.getPackets().sendGameMessage("You pick a banana from the tree.");
                    return;
                }
                if (id >= 23625 && id <= 23627) {// cadava bush
                    if (!player.getInventory().hasFreeSlots()) {
                        player.getPackets().sendGameMessage("Not enough inventory space.");
                        return;
                    }
                    if (id == 23627) {
                        player.getPackets().sendGameMessage("You search the bush, but there is no more berries.");
                        return;
                    }
                    WorldObject fullBush = new WorldObject(23625, object.getType(), object.getRotation(), object.getX(),
                            object.getY(), object.getPlane());
                    World.spawnObjectTemporary(new WorldObject(id == 23625 ? 23626 : 23627, fullBush.getType(),
                            fullBush.getRotation(), fullBush.getX(), fullBush.getY(), fullBush.getPlane()), 5000);
                    player.getInventory().addItem(753, 1);
                    player.getPackets().sendGameMessage("You pick some cadava berries from the bush.");

                    return;
                }
                if (id >= 23628 && id <= 23630) {// red berry bush
                    if (!player.getInventory().hasFreeSlots()) {
                        player.getPackets().sendGameMessage("Not enough inventory space.");
                        return;
                    }
                    if (id == 23630) {
                        player.getPackets().sendGameMessage("You search the bush, but there is no more berries.");
                        return;
                    }
                    WorldObject fullBush = new WorldObject(23628, object.getType(), object.getRotation(), object.getX(),
                            object.getY(), object.getPlane());
                    World.spawnObjectTemporary(new WorldObject(id == 23628 ? 23629 : 23630, fullBush.getType(),
                            fullBush.getRotation(), fullBush.getX(), fullBush.getY(), fullBush.getPlane()), 5000);
                    player.getInventory().addItem(1951, 1);
                    player.getPackets().sendGameMessage("You pick some redberries from the bush.");
                    return;
                }
                if (id == 33060) {
                    player.setNextWorldTile(new WorldTile(3107, 9570, 0));
                    return;
                }
                if (id == 2147) {
                    player.setNextWorldTile(new WorldTile(3104, 9576, 0));
                    return;
                }
                if (id == 5085) {
                    WildernessKills.DisplayKills(player);
                    return;
                }

                if (id == 28296) {
                    if (player.getInventory().containsOneItem(10501)) {
                        player.getInventory().addItem(10501, 10);
                        player.animate(new Animation(7529));
                        player.sm("You make some snowballs and put them in your inventory");
                    } else if (player.getInventory().hasFreeSlots()) {
                        player.getInventory().addItem(10501, 10);
                        player.animate(new Animation(7529));
                        player.sm("You make some snowballs and put them in your inventory");
                    } else {
                        player.sm("Try to free up some inventory space before making snowballs!");
                    }
                    return;
                }

                if (id == 36974) {
                    WorldObject regularLog = new WorldObject(36975, object.getType(), object.getRotation(),
                            object.getX(), object.getY(), object.getPlane());
                    World.spawnObjectTemporary(regularLog, 60000);
                    player.getInventory().addItem(1351, 1);
                    return;
                }
                if (id == 36974) {
                    WorldObject regularLog = new WorldObject(36975, object.getType(), object.getRotation(),
                            object.getX(), object.getY(), object.getPlane());
                    World.spawnObjectTemporary(regularLog, 60000);
                    player.getInventory().addItem(1351, 1);
                    return;
                }
                if (id == 2623) {
                    if (!player.getInventory().containsOneItem(1590) && player.getX() >= object.getX()) {
                        player.getPackets().sendGameMessage("You need a dusty key to enter this gate.");
                        return;
                    }
                    DoorsAndGates.handleDoorTemporary2(player, object, 1200);
                    return;
                }
                if (id == 5090) {
                    if (player.getX() == 2687)
                        player.addWalkSteps(player.getX() - 5, player.getY(), 5, false);
                    return;
                }
                if (id == 5088) {
                    if (player.getX() == 2682)
                        player.addWalkSteps(player.getX() + 5, player.getY(), 5, false);
                    return;
                }
                if (id == 9294) {
                    if (player.getX() == 2880)
                        player.addWalkSteps(player.getX() - 2, player.getY(), 2, false);
                    else
                        player.addWalkSteps(player.getX() + 2, player.getY(), 2, false);
                    return;
                }
                if (object.getId() == 2562) {
                    player.getDialogueManager().startDialogue("CompletionistStand", 3373);
                    return;
                }
                if (object.getId() == 12356
                        && !(player.getControlerManager().getControler() instanceof RecipeForDisaster)) {
                    RecipeForDisaster.enterRfd(player);
                    return;
                }
                if ((object.getId() == 8958 || object.getId() == 8959 || object.getId() == 8960)
                        && object.getX() == 2491) {
                    if (player.getX() == 2490)
                        player.addWalkSteps(player.getX() + 2, player.getY(), 2, false);
                    else
                        player.addWalkSteps(player.getX() - 2, player.getY(), 2, false);
                }

                if (object.getId() == 2566) {
                    World.sendObjectAnimation(player, object, new Animation(555));
                    return;
                }
                if (object.getId() == 26342) {
                	if (player.getInventory().contains(new Item(954)) && player.getVarBitList().get(3932) == null) {
                		player.getVarsManager().sendVarBit(3932, 1, true);
                    	player.getInventory().removeItems(new Item(954));
                    	player.sm("You tie the rope to the enterance.");
                	}
                    player.setNextWorldTile(new WorldTile(2882, 5311, 2));
                    player.getControlerManager().startControler("GodWars");
                    return;
                }
                if (object.getId() == 35390 && object.getX() == 2907 && object.getY() == 3709) {
                    player.sm("It's way too heavy to lift, perhaps I can squeeze by that gap...");
                    return;
                }
                if (object.getId() == 26323 && object.getX() == 2928 && object.getY() == 3758) {
                    player.sm("That look's too dangerous...");
                    return;
                }
                if (object.getId() == 26293 && object.getX() == 2881 && object.getY() == 5311) {
                    player.getControlerManager().forceStop();
                    player.setNextWorldTile(new WorldTile(2916, 3746, 0));
                    return;
                }
                if (object.getId() == 25340 && object.getX() == 1778 && object.getY() == 5344) {
                    player.setNextWorldTile(new WorldTile(1778, 5346, 0));
                    return;
                }

                if (object.getId() == 25339 && object.getX() == 1778 && object.getY() == 5344) {
                    player.setNextWorldTile(new WorldTile(1778, 5343, 1));
                    return;
                }

                /**
                 */

                if (id == 15653) {
                    if (World.isSpawnedObject(object) || !WarriorsGuild.canEnter(player))
                        return;
                    player.lock(2);
                    player.addWalkSteps(object.getX() - 1, player.getY(), 2, false);
                    player.getControlerManager().startControler("WarriorsGuild");
                    return;
                }

                if (object.getId() == 2081 && object.getX() == 2956 && object.getY() == 3145) {
                    player.getActionManager().setAction(new Charter(Charter.KARAMJA_GANGPLANK));
                    return;
                }

                if (object.getId() == 29732) {
                    if (player.getX() == 3085)
                        player.getPackets().sendGameMessage("This door is locked.");
                    else
                        player.getPackets()
                                .sendGameMessage("This door is locked, maybe i can find another way to get in.");
                    return;
                }
                if (object.getId() == 29736) {// leverhere
                    if (player.getVarBitList().containsKey(object.getConfigByFile())) {
                        player.getPackets().sendGameMessage("You have already pulled this lever.");
                        return;
                    }
                    player.getVarsManager().sendVarBit(object.getConfigByFile(), 1, true);
                    player.getPackets().sendGameMessage("You pull the lever.. wonder what it does..");
                    return;
                }
                if (object.getId() == 29624) {
                    if (!player.getVarBitList().containsKey(object.getConfigByFile())) {
                        player.getPackets().sendGameMessage("This gate is locked.");
                        return;
                    }
                    if (object.getX() == 3178 && object.getY() == 4269)
                        player.useStairs(-1, new WorldTile(3177, 4266, 0), 1, 2);
                    if (object.getX() == 3178 && object.getY() == 4266)
                        player.useStairs(-1, new WorldTile(3177, 4269, 2), 1, 2);
                    if (object.getX() == 3142 && object.getY() == 4270)
                        player.useStairs(-1, new WorldTile(3142, 4272, 1), 1, 2);
                    if (object.getX() == 3141 && object.getY() == 4272)
                        player.useStairs(-1, new WorldTile(3143, 4270, 0), 1, 2);
                    return;
                }

                if (object.getId() == 29734) {
                    if (player.getVarBitList().containsKey(object.getConfigByFile())) {
                        player.getPackets().sendGameMessage("You have already opened this chest.");
                        return;
                    }
                    if (!player.getInventory().hasFreeSlots()) {
                        player.getPackets().sendGameMessage("You need one inventory space to open this chest.");
                        return;
                    }
                    player.getInventory().addItem(12629, 1);
                    player.getMoneyPouch().addMoney(10000, false);
                    player.getDialogueManager().startDialogue("SimpleItemDialogue", 12629, 1,
                            "You open the chest to find a large pile of gold, along with a pair of safety gloves.");
                    player.getVarsManager().sendVarBit(object.getConfigByFile(), 1, true);
                    return;
                }
                /**
                 * Start of Ancient Cavern Stairs, Shortcuts, and Barriers added
                 *
                 * @author Phillip
                 */

                /** Whirlpool */

                if (object.getId() == 67966) {
                    player.getActionManager().setAction(new WhirlPool(WhirlPool.WHIRLPOOL_LOC));
                    return;
                }

                if (object.getId() == 25216) {
                    player.setNextWorldTile(new WorldTile(Settings.HOME_PLAYER_LOCATION));
                }

                if (object.getId() == 25336 && object.getX() == 1770 && object.getY() == 5365) {
                    player.setNextWorldTile(new WorldTile(1768, 5366, 1));
                    return;
                }

                if (object.getId() == 25338 && object.getX() == 1769 && object.getY() == 5365) {
                    player.setNextWorldTile(new WorldTile(1772, 5366, 0));
                    return;
                }

                if (object.getId() == 25337 && object.getX() == 1744 && object.getY() == 5323) {
                    player.setNextWorldTile(new WorldTile(1744, 5321, 1));
                    return;
                }

                if (object.getId() == 39468 && object.getX() == 1744 && object.getY() == 5322) {
                    player.setNextWorldTile(new WorldTile(1745, 5325, 0));
                    return;
                }

                if (object.getId() == 47232) {
                    player.setNextWorldTile(new WorldTile(1661, 5257, 0));
                    return;
                }

                if (object.getId() == 47231) {
                    player.setNextWorldTile(new WorldTile(1735, 5313, 1));
                    return;
                }

                // barriers
                if (object.getId() == 47236 && object.getX() == 1634) {
                    if (player.getX() == 1635) {
                        player.setNextWorldTile(new WorldTile(player.getX() - 1, player.getY(), 0));
                    } else if (player.getX() == 1634) {
                        player.setNextWorldTile(new WorldTile(player.getX() + 1, player.getY(), 0));
                    }
                    return;
                }

                if (object.getId() == 47236 && object.getY() == 5265) {
                    if (player.getY() == 5264) {
                        player.setNextWorldTile(new WorldTile(player.getX(), player.getY() + 1, 0));
                    } else if (player.getY() == 5265) {
                        player.setNextWorldTile(new WorldTile(player.getX(), player.getY() - 1, 0));
                    }
                    return;
                }

                if (object.getId() == 47236 && object.getY() == 5289) {
                    if (player.getY() == 5288) {
                        player.setNextWorldTile(new WorldTile(player.getX(), player.getY() + 1, 0));
                    } else if (player.getY() == 5289) {
                        player.setNextWorldTile(new WorldTile(player.getX(), player.getY() - 1, 0));
                    }
                    return;
                }

                if (object.getId() == 47236 && object.getX() == 1625) {
                    if (player.getX() == 1625) {
                        player.setNextWorldTile(new WorldTile(player.getX() + 1, player.getY(), 0));
                    } else if (player.getX() == 1626) {
                        player.setNextWorldTile(new WorldTile(player.getX() - 1, player.getY(), 0));
                    }
                    return;
                }

                if (object.getId() == 47236 && object.getX() == 1649) {
                    if (player.getX() == 1649) {
                        player.setNextWorldTile(new WorldTile(player.getX() + 1, player.getY(), 0));
                    } else if (player.getX() == 1650) {
                        player.setNextWorldTile(new WorldTile(player.getX() - 1, player.getY(), 0));
                    }
                    return;
                }

                if (object.getId() == 47236 && object.getY() == 5281) {
                    if (player.getY() == 5281) {
                        player.setNextWorldTile(new WorldTile(player.getX(), player.getY() - 1, 0));
                    } else if (player.getY() == 5280) {
                        player.setNextWorldTile(new WorldTile(player.getX(), player.getY() + 1, 0));
                    }
                    return;
                }

                // shortcuts

                if (object.getId() == 47233 && player.getY() == 5294) {
                    if (!Agility.hasLevel(player, 86)) {
                        player.sm("You must have an Agility level of 86 or higher to use this shortcut.");
                        return;
                    }
                    player.getPackets().sendGameMessage("You climb the low wall...", true);
                    player.lock(3);
                    player.animate(new Animation(4853));
                    final WorldTile toTile = new WorldTile(object.getX(), object.getY() - 1, object.getPlane());
                    player.setNextForceMovement(new ForceMovement(player, 0, toTile, 2, ForceMovement.SOUTH));

                    WorldTasksManager.schedule(new WorldTask() {

                        @Override
                        public void run() {
                            player.setNextWorldTile(toTile);
                        }
                    }, 1);
                    return;
                }

                if (object.getId() == 47233 && player.getY() == 5292) {
                    if (!Agility.hasLevel(player, 86)) {
                        player.sm("You must have an Agility level of 86 or higher to use this shortcut.");
                        return;
                    }
                    player.getPackets().sendGameMessage("You climb the low wall...", true);
                    player.lock(3);
                    player.animate(new Animation(4853));
                    final WorldTile toTile = new WorldTile(object.getX(), object.getY() + 1, object.getPlane());
                    player.setNextForceMovement(new ForceMovement(player, 0, toTile, 2, ForceMovement.NORTH));

                    WorldTasksManager.schedule(new WorldTask() {
                        @Override
                        public void run() {
                            player.setNextWorldTile(toTile);
                            player.getSkills().addXp(Skills.AGILITY, 13.7);
                        }
                    }, 1);
                    return;
                }

                if (object.getId() == 47237 && player.getY() == 5268) {
                    player.getRun();
                    player.lock(4);
                    player.animate(new Animation(2922));
                    final WorldTile toTile = new WorldTile(object.getX(), object.getY() - 7, object.getPlane());
                    player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.SOUTH));
                    player.getPackets().sendGameMessage("You skilfully run across the Gap.", true);
                    WorldTasksManager.schedule(new WorldTask() {

                        @Override
                        public void run() {
                            player.setNextWorldTile(toTile);
                        }

                    }, 1);
                    return;
                }

                if (object.getId() == 47237 && player.getY() == 5260) {
                    player.getRun();
                    player.lock(4);
                    player.animate(new Animation(2922));
                    final WorldTile toTile = new WorldTile(object.getX(), object.getY() + 7, object.getPlane());
                    player.setNextForceMovement(new ForceMovement(player, 1, toTile, 3, ForceMovement.NORTH));
                    player.getPackets().sendGameMessage("You skilfully run across the Gap.", true);
                    WorldTasksManager.schedule(new WorldTask() {

                        @Override
                        public void run() {
                            player.setNextWorldTile(toTile);
                        }

                    }, 1);
                    return;
                } else if (id == 11554 || id == 11552)
                    player.getPackets().sendGameMessage("That rock is currently unavailable.");
                else if (id == 38279)
                    player.getDialogueManager().startDialogue("RunespanPortalD");
                else if (id == 2491)
                    player.getActionManager()
                            .setAction(new EssenceMining(object,
                                    player.getSkills().getLevel(Skills.MINING) < 30 ? EssenceDefinitions.Rune_Essence
                                            : EssenceDefinitions.Pure_Essence));
                    // } else if (id == 65371) {

                    /*
                     * if (player.getTeleBlockDelay() > Utils.currentTimeMillis()) {
                     * player.getPackets().sendGameMessage (
                     * "A magical force interrupts your focus..."); return; } else {
                     * //player.getActionManager().setAction(new
                     * ObjectActionTeleport(Settings.HOME_PLAYER_LOCATION, 0));
                     *
                     * return; }
                     */
                else if (id == 47120) {
                    if (player.getPrayer().getPrayerpoints() < player.getSkills().getLevelForXp(Skills.PRAYER) * 10) {
                        player.lock(12);
                        player.animate(new Animation(12563));
                        player.getPrayer()
                                .setPrayerpoints((int) ((player.getSkills().getLevelForXp(Skills.PRAYER) * 10) * 1.15));
                        player.getPrayer().refreshPrayerPoints();
                    }
                    player.getDialogueManager().startDialogue("SwitchPrayers");
                } else if (id == 19222 && player.getY() == 3622) {
                    Falconry.beginFalconry(player);
                }
                if (id == 36786)
                    player.getDialogueManager().startDialogue("Banker", 4907);
                else if (id == 42377 || id == 42378)
                    player.getDialogueManager().startDialogue("Banker", 2759);
                else if (id == 42217 || id == 782 || id == 34752)
                    player.getDialogueManager().startDialogue("Banker", 553);
                else if (id == 57437)
                    player.getBank().openBank();
                else if (id == 42425 && object.getX() == 3220 && object.getY() == 3222) { // zaros
                    // portal
                    player.useStairs(10256, new WorldTile(3353, 3416, 0), 4, 5,
                            "And you find yourself into a digsite.");
                    player.addWalkSteps(3222, 3223, -1, false);
                    player.getPackets().sendGameMessage("You examine portal and it aborves you...");
                } else if (id == 9356)
                    FightCaves.enterFightCaves(player);
                else if (id == 65365)
                    WildyAgility.DoorStart(player, object);
                else if (id == 65367)
                    WildyAgility.DoorStart2(player, object);
                else if (id == 69514)
                    GnomeAgility.RunGnomeBoard(player, object);
                else if (id == 69389)
                    GnomeAgility.JumpDown(player, object);
                else if (id == 68223)
                    FightPits.enterLobby(player, false);
                else if (id == 46500 && object.getX() == 3351 && object.getY() == 3415) { // zaros
                    // portal
                    player.useStairs(-1, new WorldTile(Settings.RESPAWN_PLAYER_LOCATION.getX(),
                                    Settings.RESPAWN_PLAYER_LOCATION.getY(), Settings.RESPAWN_PLAYER_LOCATION.getPlane()), 2, 3,
                            "You found your way back to home.");
                    player.addWalkSteps(3351, 3415, -1, false);
                } else if (id == 9293) {
                    if (player.getSkills().getLevel(Skills.AGILITY) < 70) {
                        player.getPackets().sendGameMessage("You need an agility level of 70 to use this obstacle.",
                                true);
                        return;
                    }
                    player.animate(new Animation(844));
                    int x = player.getX() == 2886 ? 2892 : 2886;
                    WorldTasksManager.schedule(new WorldTask() {
                        int count = 0;

                        @Override
                        public void run() {
                            player.animate(new Animation(844));
                            if (count++ == 1)
                                stop();
                        }

                    }, 0, 0);
                    player.setNextForceMovement(
                            new ForceMovement(new WorldTile(x, 9799, 0), 3, player.getX() == 2886 ? 1 : 3));
                    player.useStairs(-1, new WorldTile(x, 9799, 0), 3, 4);
                } else if (id == 29370 && (object.getX() == 3150 || object.getX() == 3153) && object.getY() == 9906) { // edgeville
                    if (Settings.FREE_TO_PLAY) {
                        player.getPackets().sendGameMessage("Nothing interesting happens.");
                        return;
                    }
                    // cut
                    if (player.getSkills().getLevel(Skills.AGILITY) < 53) {
                        player.getPackets().sendGameMessage("You need an agility level of 53 to use this obstacle.");
                        return;
                    }
                    final boolean running = player.getRun();
                    player.setRunHidden(false);
                    player.lock(8);
                    player.addWalkSteps(x == 3150 ? 3155 : 3149, 9906, -1, false);
                    player.getPackets().sendGameMessage("You pulled yourself through the pipes.", true);
                    WorldTasksManager.schedule(new WorldTask() {
                        boolean secondloop;

                        @Override
                        public void run() {
                            if (!secondloop) {
                                secondloop = true;
                                player.getAppearence().setRenderEmote(295);
                            } else {
                                player.getAppearence().setRenderEmote(-1);
                                player.setRunHidden(running);
                                player.getSkills().addXp(Skills.AGILITY, 7);
                                stop();
                            }
                        }
                    }, 0, 5);
                } else if (id == 8689)
                    player.getActionManager().setAction(new CowMilkingAction());
                else if (id == 2112 && object.getX() == 3046 && object.getY() == 9756) {
                    if (player.getSkills().getLevelForXp(Skills.MINING) < 60) {
                        player.getDialogueManager().startDialogue("SimpleNPCMessage",
                                MiningGuildDwarf.getClosestDwarfID(player),
                                "Sorry, but you need level 60 Mining to go in there.");
                        return;
                    }
                    DoorsAndGates.handleDoorTemporary(player, object, 1200);
                } else if (id == 2113) {
                    if (player.getSkills().getLevelForXp(Skills.MINING) < 60) {
                        player.getDialogueManager().startDialogue("SimpleNPCMessage",
                                MiningGuildDwarf.getClosestDwarfID(player),
                                "Sorry, but you need level 60 Mining to go in there.");
                        return;
                    }
                    player.useStairs(-1, new WorldTile(3021, 9739, 0), 0, 1);

                } else if (id == 5111 && object.getX() == 2647 && object.getY() == 9558) { // Steps
                    // brimhaven
                    player.lock(14);
                    WorldTasksManager.schedule(new WorldTask() {
                        int y;

                        @Override
                        public void run() {
                            if (y++ == 7) {
                                stop();
                                return;
                            }
                            if (y < 4) {
                                final WorldTile toTile = new WorldTile(player.getX(), player.getY() + 1,
                                        player.getPlane());
                                player.setNextForceMovement(new ForceMovement(toTile, 1, ForceMovement.NORTH));
                                player.setNextWorldTile(toTile);
                            }
                            if (y > 3 && y < 7) {
                                final WorldTile toTile = new WorldTile(player.getX() + 1, player.getY(),
                                        player.getPlane());
                                player.setNextForceMovement(new ForceMovement(toTile, 1, ForceMovement.EAST));
                                player.setNextWorldTile(toTile);
                            }
                            if (y > 5) {
                                final WorldTile toTile = new WorldTile(player.getX(), player.getY() + 1,
                                        player.getPlane());
                                player.setNextForceMovement(new ForceMovement(toTile, 1, ForceMovement.NORTH));
                                player.setNextWorldTile(toTile);
                            }
                            player.animate(new Animation(741));
                        }
                    }, 0, 1);
                } else if (id == 5110 && object.getX() == 2649 && object.getY() == 9561) { // Steps
                    // brimhaven
                    player.lock(14);
                    WorldTasksManager.schedule(new WorldTask() {
                        int x;

                        @Override
                        public void run() {
                            if (x++ == 7) {
                                stop();
                                return;
                            }
                            if (x == 1 || x == 2) {
                                final WorldTile toTile = new WorldTile(player.getX(), player.getY() - 1,
                                        player.getPlane());
                                player.setNextForceMovement(new ForceMovement(toTile, 1, ForceMovement.SOUTH));
                                player.setNextWorldTile(toTile);
                            }
                            if (x == 3 || x == 4) {
                                final WorldTile toTile = new WorldTile(player.getX() - 1, player.getY(),
                                        player.getPlane());
                                player.setNextForceMovement(new ForceMovement(toTile, 1, ForceMovement.WEST));
                                player.setNextWorldTile(toTile);
                            }
                            if (x > 4) {
                                final WorldTile toTile = new WorldTile(player.getX(), player.getY() - 1,
                                        player.getPlane());
                                player.setNextForceMovement(new ForceMovement(toTile, 1, ForceMovement.SOUTH));
                                player.setNextWorldTile(toTile);
                            }
                            player.animate(new Animation(741));
                        }
                    }, 0, 1);
                } else if (id == 30963)
                    player.getBank().openBank();
                else if (id == 6045)
                    player.getPackets().sendGameMessage("You search the cart but find nothing.");
                else if (id == 5906) {
                    if (player.getSkills().getLevel(Skills.AGILITY) < 42) {
                        player.getPackets().sendGameMessage("You need an agility level of 42 to use this obstacle.");
                        return;
                    }
                    player.lock();
                    WorldTasksManager.schedule(new WorldTask() {
                        int count = 0;

                        @Override
                        public void run() {
                            if (count == 0) {
                                player.animate(new Animation(2594));
                                WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -2 : +2),
                                        object.getY(), 0);
                                player.setNextForceMovement(new ForceMovement(tile, 4, Utils
                                        .getMoveDirection(tile.getX() - player.getX(), tile.getY() - player.getY())));
                            } else if (count == 2) {
                                WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -2 : +2),
                                        object.getY(), 0);
                                player.setNextWorldTile(tile);
                            } else if (count == 5) {
                                player.animate(new Animation(2590));
                                WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -5 : +5),
                                        object.getY(), 0);
                                player.setNextForceMovement(new ForceMovement(tile, 4, Utils
                                        .getMoveDirection(tile.getX() - player.getX(), tile.getY() - player.getY())));
                            } else if (count == 7) {
                                WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -5 : +5),
                                        object.getY(), 0);
                                player.setNextWorldTile(tile);
                            } else if (count == 10) {
                                player.animate(new Animation(2595));
                                WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -6 : +6),
                                        object.getY(), 0);
                                player.setNextForceMovement(new ForceMovement(tile, 4, Utils
                                        .getMoveDirection(tile.getX() - player.getX(), tile.getY() - player.getY())));
                            } else if (count == 12) {
                                WorldTile tile = new WorldTile(object.getX() + (object.getRotation() == 2 ? -6 : +6),
                                        object.getY(), 0);
                                player.setNextWorldTile(tile);
                            } else if (count == 14) {
                                stop();
                                player.unlock();
                            }
                            count++;
                        }

                    }, 0, 0);
                    // BarbarianOutpostAgility start
                    // BarbarianOutpostAgility start
                } else if (id == 20210)
                    BarbarianOutpostAgility.enterObstaclePipe(player, object);
                else if (id == 43526)
                    BarbarianOutpostAgility.swingOnRopeSwing(player, object);
                else if (id == 43595 && x == 2550 && y == 3546)
                    BarbarianOutpostAgility.walkAcrossLogBalance(player, object);
                else if (id == 20211 && x == 2538 && y == 3545)
                    BarbarianOutpostAgility.climbObstacleNet(player, object);
                else if (id == 2302 && x == 2535 && y == 3547)
                    BarbarianOutpostAgility.walkAcrossBalancingLedge(player, object);
                else if (id == 1948)
                    BarbarianOutpostAgility.climbOverCrumblingWall(player, object);
                else if (id == 43533)
                    BarbarianOutpostAgility.runUpWall(player, object);
                else if (id == 43597)
                    BarbarianOutpostAgility.climbUpWall(player, object);
                else if (id == 43587)
                    BarbarianOutpostAgility.fireSpringDevice(player, object);
                else if (id == 43527)
                    BarbarianOutpostAgility.crossBalanceBeam(player, object);
                else if (id == 43531)
                    BarbarianOutpostAgility.jumpOverGap(player, object);
                else if (id == 43532)
                    BarbarianOutpostAgility.slideDownRoof(player, object);

                else if (id == 9295) {// nonprod
                    player.getControlerManager().forceStop();
                    player.setNextWorldTile(new WorldTile(3219, 3219, 0));
                    player.reset();
                }

                // rock living caverns
                else if (id == 45077) {
                    player.lock();
                    if (player.getX() != object.getX() || player.getY() != object.getY())
                        player.addWalkSteps(object.getX(), object.getY(), -1, false);
                    WorldTasksManager.schedule(new WorldTask() {

                        private int count;

                        @Override
                        public void run() {
                            if (count == 0) {
                                player.setNextFaceWorldTile(new WorldTile(object.getX() - 1, object.getY(), 0));
                                player.animate(new Animation(12216));
                                player.unlock();
                            } else if (count == 2) {
                                player.setNextWorldTile(new WorldTile(3651, 5122, 0));
                                player.setNextFaceWorldTile(new WorldTile(3651, 5121, 0));
                                player.animate(new Animation(12217));
                            } else if (count == 3) {
                                // TODO find emote
                                // player.getPackets().sendObjectAnimation(new
                                // WorldObject(45078, 0, 3, 3651, 5123, 0), new
                                // Animation(12220));
                            } else if (count == 5) {
                                player.unlock();
                                stop();
                            }
                            count++;
                        }

                    }, 1, 0);
                } else if (id == 45079)
                    player.getBank().openDepositBox();
                else if (id == 2606) {

                    WorldObject openedDoor = new WorldObject(object.getId(), object.getType(), object.getRotation() - 1,
                            object.getX(), object.getY() - 1, object.getPlane());
                    if (World.removeObjectTemporary(object, 1200, false))
                        World.spawnObjectTemporary(openedDoor, 1200);
                    player.lock(2);
                    player.stopAll();
                    player.addWalkSteps(2836, player.getY() >= object.getY() ? object.getY() - 1 : object.getY(), -1,
                            false);
                } else if (id == 69526)
                    GnomeAgility.walkGnomeLog(player);
                else if (id == 69383)
                    GnomeAgility.climbGnomeObstacleNet(player);
                else if (id == 69508)
                    GnomeAgility.climbUpGnomeTreeBranch(player);
                else if (id == 69506)
                    GnomeAgility.climbUpGnomeTreeBranch2(player);
                else if (id == 2312)
                    GnomeAgility.walkGnomeRope(player);
                else if (id == 4059)
                    GnomeAgility.walkBackGnomeRope(player);
                else if (id == 69507)
                    GnomeAgility.climbDownGnomeTreeBranch(player);
                else if (id == 69384)
                    GnomeAgility.climbGnomeObstacleNet2(player);
                else if (id == 65362)
                    WildyAgility.PipeStart(player, object);
                else if (id == 64696)
                    WildyAgility.swingOnRopeSwing(player, object);
                else if (id == 64698)
                    WildyAgility.walkLog(player);
                else if (id == 64699)
                    WildyAgility.crossSteppingPalletes(player, object);
                else if (id == 65734)
                    WildyAgility.climbCliff(player, object);
                else if (id == 69377 || id == 69378)
                    GnomeAgility.enterGnomePipe(player, object.getX(), object.getY());
                else if (id == 9369)
                    player.getControlerManager().startControler("FightPits");
                else if (id == 14315) {
                    if (Lander.canEnter(player, 0))
                        return;
                } else if (id == 25631) {
                    if (Lander.canEnter(player, 1))
                        return;
                } else if (id == 25632) {
                    if (Lander.canEnter(player, 2))
                        return;
                } else if (id == 5959) {
                    Magic.pushLeverTeleport(player, new WorldTile(2539, 4712, 0));
                } else if (id == 5960) {
                    Magic.pushLeverTeleport(player, new WorldTile(3089, 3957, 0));
                } else if (id == 1814) {
                    player.getDialogueManager().startDialogue("WildyLever");
                } else if (id == 1815) {
                    Magic.pushLeverTeleport(player, new WorldTile(2561, 3311, 0));
                } else if (object.getId() >= 11552 && object.getId() <= 11557 || object.getId() == 4030) {
                    player.getPackets().sendGameMessage("That rock is currently unavailable.");
                    return;

                } else if (id == 26194) {
                    player.getDialogueManager().startDialogue("PartyRoomLever");
                } else if (id == 61190 || id == 61191 || id == 61192 || id == 61193) {
                    if (objectDef.containsOption(0, "Chop down"))
                        player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.NORMAL));
                } else if (id == 46274) {
                    player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.MAHOGANY_TREE));
                } else if (id == 46275) {
                    player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.TEAK_TREE));
                } else if (id == 20575)
                    player.getControlerManager().startControler("RefugeOfFear");

                else if (id == 67051)
                    player.getDialogueManager().startDialogue("Marv", false);
                else if (id == 67052)
                    Crucible.enterCrucibleEntrance(player);
                else if (id == 12266) {// trapdoor
                    player.faceObject(object);
                    player.setNextWorldTile(new WorldTile(3077, 9893, 0));
                } else if (id == 29362)
                    player.sm("Eew it's all slimey...");
                else if (id == 21600)
                    DoorsAndGates.handleDoor(player, object);
                else if (id == 26721 || id == 1317) {
                    player.getDialogueManager().startDialogue("SpiritTree", id);
                } else if (objectDef.name.contains("rocks") || objectDef.name.equalsIgnoreCase("Rocks")) {
                    if (object.getDefinitions().containsOption("Mine")) {
                        player.getActionManager().setAction(new Mining(object));
                    }
                } else if (objectDef.name.toLowerCase().contains("hay bale")) {
                    if (!player.getInventory().hasFreeSlots() && player.getInventory().containsItem(1733, 1)) {
                        player.getPackets().sendGameMessage("You need inventory space to search this.");
                        return;
                    }
                    switch (Utils.getRandom(2)) {
                        case 0:
                            player.lock(3);
                            player.getInventory().addItem(1733, 1);
                            player.getPackets().sendGameMessage("Wow! A needle! Now what are the chances of finding that?");
                            break;
                        case 1:
                            player.lock(3);
                            player.getPackets().sendGameMessage("You find nothing of interest.");
                            break;
                        case 2:
                            player.applyHit(new Hit(player, 10, HitLook.REGULAR_DAMAGE));
                            player.getPackets().sendGameMessage("You hurt your finger by something pointy.");
                            break;
                    }
                } else {
                    switch (objectDef.name.toLowerCase()) {
                        case "crashed star":
                            if (World.shootingStar.isFound())
                                player.getActionManager().setAction(new ShootingStarAction(20, World.shootingStar.getSkillId()));
                            else
                                World.shootingStar.findStar(player);
                            break;
                        case "altar":
                        case "gorilla statue":
                        case "chaos altar":
                            if (objectDef.containsOption(0, "Pray") || objectDef.containsOption(0, "Pray-at")) {
                                final int maxPrayer = player.getSkills().getLevelForXp(Skills.PRAYER) * 10;
                                if (player.getPrayer().getPrayerpoints() < maxPrayer) {
                                    player.lock(1);
                                    player.getPackets().sendGameMessage("You pray to the gods...", true);
                                    player.getPrayer().restorePrayer(maxPrayer);
                                    player.getPackets().sendGameMessage("...and recharged your prayer.", true);
                                    player.animate(new Animation(645));
                                } else
                                    player.getPackets().sendGameMessage("You already have full prayer.");
                                if (id == 6552)
                                    player.getDialogueManager().startDialogue("AncientAltar");
                            }
                            break;
                        case "crate":
                        case "crates":
                        case "boxes":
                        case "bookcase":
                            if (objectDef.containsOption(0, "Search"))
                                player.getPackets().sendGameMessage(
                                        "You search the " + objectDef.name.toLowerCase() + " but find nothing.");
                            break;
                        case "trapdoor":
                        case "manhole":
                            if (objectDef.containsOption(0, "Open")) {
                                WorldObject openedHole = new WorldObject(object.getId() + 1, object.getType(),
                                        object.getRotation(), object.getX(), object.getY(), object.getPlane());
                                player.faceObject(openedHole);
                                World.spawnObjectTemporary(openedHole, 60000);
                                player.animate(new Animation(536));
                            } else {
                                player.sm("It won't budge!");
                            }
                            break;
                        case "drawers":
                            if (objectDef.containsOption(0, "Open")) {
                                WorldObject openedDrawer = new WorldObject(getOpenId(object.getId()), object.getType(),
                                        object.getRotation(), object.getX(), object.getY(), object.getPlane());
                                player.faceObject(openedDrawer);
                                World.spawnObjectTemporary(openedDrawer, 60000);
                                player.animate(new Animation(536));
                            }
                            if (objectDef.containsOption(0, "Search")) {
                                player.getPackets().sendGameMessage("You search the drawers but find nothing.");
                                return;
                            }
                            break;
                        case "closed chest":
                            if (objectDef.containsOption(0, "Open")) {
                                player.animate(new Animation(536));
                                player.lock(2);
                                WorldObject openedChest = new WorldObject(object.getId() + 1, object.getType(),
                                        object.getRotation(), object.getX(), object.getY(), object.getPlane());
                                player.faceObject(openedChest);
                                World.spawnObjectTemporary(openedChest, 60000);
                            }
                            break;
                        case "open chest":
                            if (objectDef.containsOption(0, "Search"))
                                player.getPackets().sendGameMessage("You search the chest but find nothing.");
                            break;
                        case "spiderweb":
                            if (object.getRotation() == 2) {
                                player.lock(2);
                                if (Utils.getRandom(1) == 0) {
                                    player.addWalkSteps(player.getX(),
                                            player.getY() < y ? object.getY() + 2 : object.getY() - 1, -1, false);
                                    player.getPackets().sendGameMessage("You squeeze though the web.");
                                } else
                                    player.getPackets().sendGameMessage(
                                            "You fail to squeeze though the web; perhaps you should try again.");
                            }
                            break;
                        case "web":
                            if (objectDef.containsOption(0, "Slash")) {
                                slashWeb(player, object);
                            }
                            break;
                        case "crust":
							if (objectDef.containsOption(0, "Mine"))
								player.getActionManager().setAction(
										new LavaFlowMine(object));
							break;
                        case "anvil":
                            player.getDialogueManager().startDialogue("SimpleMessage",
                                    "Use a metal on the anvil in order to begin working with the metal.");
                            break;
                        case "bank deposit box":
                            if (objectDef.containsOption(0, "Deposit"))
                                player.getBank().openDepositBox();
                            break;
                        case "bank":
                        case "bank chest":
                        case "bank booth":
                        case "counter":
                            if (objectDef.containsOption(0, "Bank") || objectDef.containsOption(0, "Use"))
                                player.getBank().openBank();
                            break;
                        // Woodcutting start
                        case "tree":
                            if (objectDef.containsOption(0, "Chop down"))
                                player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.NORMAL));
                            break;
                        case "evergreen":
                            if (objectDef.containsOption(0, "Chop down"))
                                player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.EVERGREEN));
                            break;
                        case "dead tree":
                            if (objectDef.containsOption(0, "Chop down"))
                                player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.DEAD));
                            break;
                        case "swamp tree":
                            if (objectDef.containsOption(0, "Chop down"))
                                player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.DEAD));
                            break;
                        case "oak":
                            if (objectDef.containsOption(0, "Chop down"))
                                player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.OAK));
                            break;
                        case "willow":
                            if (objectDef.containsOption(0, "Chop down"))
                                player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.WILLOW));
                            break;
                        case "raple tree":
                        case "maple tree":
                            if (objectDef.containsOption(0, "Chop down"))
                                player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.MAPLE));
                            break;
                        case "ivy":
                            if (objectDef.containsOption(0, "Chop"))
                                player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.IVY));
                            break;
                        case "yew":
                            if (objectDef.containsOption(0, "Chop down"))
                                player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.YEW));
                            break;
                        case "magic tree":
                            if (objectDef.containsOption(0, "Chop down"))
                                player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.MAGIC));
                            break;
                        case "cursed magic tree":
                            if (objectDef.containsOption(0, "Chop down"))
                                player.getActionManager().setAction(new Woodcutting(object, TreeDefinitions.CURSED_MAGIC));
                            break;
                        case "bloodwood tree":// TODO
                            // if (objectDef.containsOption(0, "Chop down"))
                            // player.getActionManager().setAction(new Woodcutting(object,
                            // TreeDefinitions.BLOODWOOD));
                            break;
                        case "achey tree":// TODO
                            // if (objectDef.containsOption(0, "Chop"))
                            // player.getActionManager().setAction(new Woodcutting(object,
                            // TreeDefinitions.));
                            break;
                        // Woodcutting end
                        case "gate":
                        case "large door":
                        case "castle door":
                        case "metal door":
                        case "long hall door":
                            if ((object.getId() == 29320 || object.getId() == 29319 || object.getId() == 2051
                                    || object.getId() == 45856 || object.getId() == 45857 || object.getId() == 24561
                                    || object.getId() == 24370 || object.getId() == 28691 || object.getId() == 24369)
                                    && Settings.FREE_TO_PLAY) {
                                player.getPackets().sendGameMessage("You can't acess this member area.");
                                return;
                            }
                            if (objectDef.containsOption(0, "Open"))
                                DoorsAndGates.handleGate(player, object);
                            break;
                        case "door":
                            if (object.getType() == 0
                                    && (objectDef.containsOption(0, "Open") || objectDef.containsOption(0, "Unlock")))
                                DoorsAndGates.handleDoor(player, object);
                            else if (object.getType() == 0 && objectDef.containsOption(0, "Close"))
                                DoorsAndGates.handleCloseDoor(player, object);
                            break;
                        case "ladder":
                        case "bamboo ladder":
                            GlobalStairs.handleLadder(player, object, 1);
                            break;
                        case "staircase":
                        case "steps":
                            GlobalStairs.handleStaircases(player, object, 1);
                            break;
                        case "stairs":
                            GlobalStairs.handleStairs(player, object, 1);
                            break;
                        case "small obelisk":
                            if (objectDef.containsOption(0, "Renew-points"))
                                renewSummoningPoints(player);
                            healFamiliar(player);
                            break;
                        case "obelisk":
                            if (objectDef.containsOption(0, "Infuse-pouch"))
                                Summoning.openInfusionInterface(player);
                            break;

                        case "locked door":
                            player.sm("The door appears to be locked!");
                            break;

                        default:
                            player.getPackets().sendGameMessage("Nothing interesting happens.");
                            break;
                    }
                }
                if (Settings.DEBUG)
                    Logger.log("ObjectHandler",
                            "clicked 1 at object id : " + id + ", " + object.getX() + ", " + object.getY() + ", "
                                    + object.getPlane() + ", " + object.getType() + ", " + object.getRotation() + ", "
                                    + object.getDefinitions().name);
            }
        }));
    }

    private static void handleOption2(final Player player, final WorldObject object) {
        final ObjectDefinitions objectDef = object.getDefinitions();
        final int id = object.getId();
        player.setNextFaceEntity(null);
        ObjectScript script = ObjectScriptHandler.getScript(object);
        if (script != null) {
            if (script.getDistance() == 0) {
                player.setRouteEvent(new RouteEvent(object, new Runnable() {
                    @Override
                    public void run() {
                        player.stopAll();
                        player.faceObject(object);
                        boolean scriptExecuted = script.processObject2(player, object);
                        if (!scriptExecuted)
                            Logger.log("ObjectScript;Option 2", "Class: " + script.getClass().getSimpleName() + ".java, Option 2 method was empty in script.");
                        if (scriptExecuted) {
                            if (Settings.DEBUG)
                                Logger.log("ObjectScript;Option 2", "Class: " + script.getClass().getSimpleName() + ".java, Name of Object: " + object.getName() + ", ObjectId: " + object.getId());
                            return;
                        }
                    }
                }, true));
                return;
            } else {
                // TODO route to script.getDistane()
            }
        }
        player.setRouteEvent(new RouteEvent(object, new Runnable() {

            @Override
            public void run() {
                player.stopAll();
                player.faceObject(object);

                if (!player.getControlerManager().processObjectClick2(object))
                    return;

                if (id == 29406 || id == 29408) {
                    DwarfMultiCannon.pickupCannon(player, 4, object, id == 29406 ? 1 : 2);
                    return;
                }
                if (id == 29405 || id == 29404 || id == 29403) {
                    DwarfMultiCannon.pickupCannon(player, id - 29403, object, 2);
                    return;
                }
                if (id == 29398 || id == 29401 || id == 29402) {
                    DwarfMultiCannon.pickupCannon(player, id == 29398 ? 0 : id - 29400, object, 1);
                    return;
                }
                if (GrotwormLair.handleObject2(object, player))
                    return;
                if (player.getFarmingManager().isFarming(id, null, 2))
                    return;
                if (object.getDefinitions().name.equalsIgnoreCase("furnace"))
                    player.getDialogueManager().startDialogue("SmeltingD", object);
                else if (object.getDefinitions().name.toLowerCase().contains("spinning wheel"))
                    player.getDialogueManager().startDialogue("SpinningWheelD", object);
                else if (object.getDefinitions().name.toLowerCase().contains("loom"))
                    player.getDialogueManager().startDialogue("LoomD", object);
                else if (id == 17010)
                    player.getDialogueManager().startDialogue("LunarAltar");
                else {
                    switch (objectDef.name.toLowerCase()) {
                        case "crashed star":
                            if (!World.shootingStar.isFound())
                                return;
                            player.getDialogueManager().startDialogue("SimpleMessage", "This is a size " + World.shootingStar.getStage() + " star. A Mining level of at least " + World.shootingStar.getReqLevel() + " is required to mine this layer. It has been mined about " + World.shootingStar.getMinedPerc() + " percent of the way to the " + (World.shootingStar.getStage() == 1 ? "core" : "next layer") + ".");
                            break;
                        case "drawers":
                            if (objectDef.containsOption(1, "Search"))
                                player.getPackets().sendGameMessage("You search the drawers but find nothing.");
                            if (objectDef.containsOption(1, "Close") || objectDef.containsOption(1, "Shut")) {
                                player.faceObject(object);
                                World.removeObject(object);
                                player.animate(new Animation(537));
                                return;
                            }
                            break;
                        case "open chest":
                            if (objectDef.containsOption(1, "Search"))
                                player.getPackets().sendGameMessage("You search the chest but find nothing.");
                            break;
                        case "trapdoor":
                        case "manhole":
                            if (objectDef.containsOption(1, "Close")) {
                                player.faceObject(object);
                                World.removeObject(object);
                                player.animate(new Animation(535));
                            } else {
                                player.sm("It won't budge!");
                            }
                            break;
                        case "cabbage":
                            if (objectDef.containsOption(1, "Pick") && player.getInventory().addItem(1965, 1)) {
                                player.addWalkSteps(object.getX(), object.getY());
                                player.animate(new Animation(827));
                                player.lock(3);
                                World.removeObjectTemporary(object, 30000, true);
                            }
                            break;
                        case "wheat":
                            if (objectDef.containsOption(1, "Pick") && player.getInventory().addItem(1947, 1)) {
                                player.addWalkSteps(object.getX(), object.getY());
                                player.animate(new Animation(827));
                                player.lock(3);
                                World.removeObjectTemporary(object, 30000, true);
                            }
                            break;
                        case "potato":
                            if (objectDef.containsOption(1, "Pick") && player.getInventory().addItem(1942, 1)) {
                                player.addWalkSteps(object.getX(), object.getY());
                                player.animate(new Animation(827));
                                player.lock(3);
                                World.removeObjectTemporary(object, 30000, true);
                            }
                            break;
                        case "onion":
                            if (objectDef.containsOption(1, "Pick") && player.getInventory().addItem(1957, 1)) {
                                player.addWalkSteps(object.getX(), object.getY());
                                player.animate(new Animation(827));
                                player.lock(3);
                                World.removeObjectTemporary(object, 30000, true);
                            }
                            break;
                        case "flax":
                            if (objectDef.containsOption(1, "Pick") && player.getInventory().addItem(1779, 1)) {
                                player.animate(new Animation(827));
                                player.lock(1);
                                if (Utils.getRandom(3) == 0)
                                    World.removeObjectTemporary(object, 30000, true);
                            }
                            break;
                        case "bank":
                        case "bank booth":
                        case "counter":
                        case "bank chest":
                            if (objectDef.containsOption(1, "Bank"))
                                player.getBank().openBank();
                            break;
                        case "gates":
                        case "gate":
                        case "metal door":
                        case "castle door":
                        case "long hall door":
                            if (object.getId() == 24369 && Settings.FREE_TO_PLAY) {
                                player.getPackets().sendGameMessage("You can't acess this member area.");
                                return;
                            }
                            if (object.getType() == 0 && objectDef.containsOption(1, "Open"))
                                DoorsAndGates.handleGate(player, object);
                            break;
                        case "door":
                            if (object.getType() == 0 && objectDef.containsOption(1, "Open"))
                                DoorsAndGates.handleDoor(player, object);
                            break;
                        case "ladder":
                            GlobalStairs.handleLadder(player, object, 2);
                            break;
                        case "staircase":
                            GlobalStairs.handleStaircases(player, object, 2);
                            break;
                        default:
                            player.getPackets().sendGameMessage("Nothing interesting happens.");
                            break;
                    }
                }
                if (Settings.DEBUG)
                    Logger.log("ObjectHandler", "clicked 2 at object id : " + id + ", " + object.getX() + ", "
                            + object.getY() + ", " + object.getPlane());
            }

        }));
    }

    private static void handleOption3(final Player player, final WorldObject object) {
        final ObjectDefinitions objectDef = object.getDefinitions();
        final int id = object.getId();
        player.setNextFaceEntity(null);
        ObjectScript script = ObjectScriptHandler.getScript(object);
        if (script != null) {
                player.setRouteEvent(new RouteEvent(object, new Runnable() {
                    @Override
                    public void run() {
                        player.stopAll();
                        player.faceObject(object);
                        boolean scriptExecuted = script.processObject3(player, object);
                        if (!scriptExecuted)
                            Logger.log("ObjectScript;Option 3", "Class: " + script.getClass().getSimpleName() + ".java, Option 3 method was empty in script.");
                        if (scriptExecuted) {
                            if (Settings.DEBUG)
                                Logger.log("ObjectScript;Option 3", "Class: " + script.getClass().getSimpleName() + ".java, Name of Object: " + object.getName() + ", ObjectId: " + object.getId());
                            return;
                        }
                    }
                }, true));
        }
        player.setRouteEvent(new RouteEvent(object, new Runnable() {

            @Override
            public void run() {
                player.stopAll();
                player.faceObject(object);
                if (!player.getControlerManager().processObjectClick3(object))
                    return;
                if (GrotwormLair.handleObject3(object, player))
                    return;
                if (player.getFarmingManager().isFarming(id, null, 3))
                    return;
                switch (objectDef.name.toLowerCase()) {
                    case "bank":
                    case "bank chest":
                    case "bank booth":
                    case "counter":
                        if (objectDef.containsOption(1, "Bank"))
                            player.getGeManager().openCollectionBox();
                        break;
                    case "open chest":
                        if (objectDef.containsOption(2, "Shut") && object.getX() != 3185 && object.getY() != 3274) {
                            player.faceObject(object);
                            World.removeObject(object);
                            player.animate(new Animation(537));
                        } else {
                            player.sm("It won't budge!");
                        }
                        break;
                    case "drawers":
                        if (objectDef.containsOption(2, "Close") || objectDef.containsOption(2, "Shut")) {
                            player.faceObject(object);
                            World.removeObject(object);
                            player.animate(new Animation(537));
                        } else {
                            player.sm("It won't budge!");
                        }
                        break;
                    case "gate":
                    case "metal door":
                    case "castle door":
                    case "long hall door":
                        if (object.getType() == 0 && objectDef.containsOption(2, "Open"))
                            DoorsAndGates.handleGate(player, object);
                        else if (object.getType() == 0 && objectDef.containsOption(2, "Close"))
                            DoorsAndGates.handleCloseGate(player, object);
                        break;

                    case "door":
                        if (object.getType() == 0 && objectDef.containsOption(2, "Open"))
                            DoorsAndGates.handleDoor(player, object);
                        break;
                    case "ladder":
                        GlobalStairs.handleLadder(player, object, 3);
                        break;
                    case "staircase":
                        GlobalStairs.handleStaircases(player, object, 3);
                        break;
                    default:
                        player.getPackets().sendGameMessage("Nothing interesting happens.");
                        break;
                }
                if (Settings.DEBUG)
                    Logger.log("ObjectHandler", "cliked 3 at object id : " + id + ", " + object.getX() + ", "
                            + object.getY() + ", " + object.getPlane() + ", ");
            }

        }));
    }

    private static void handleOption4(final Player player, final WorldObject object) {
        final ObjectDefinitions objectDef = object.getDefinitions();
        final int id = object.getId();
        player.setNextFaceEntity(null);
        ObjectScript script = ObjectScriptHandler.getScript(object);
        if (script != null) {
                player.setRouteEvent(new RouteEvent(object, new Runnable() {
                    @Override
                    public void run() {
                        player.stopAll();
                        player.faceObject(object);
                        boolean scriptExecuted = script.processObject4(player, object);
                        if (!scriptExecuted)
                            Logger.log("ObjectScript;Option 4", "Class: " + script.getClass().getSimpleName() + ".java, Option 4 method was empty in script.");
                        if (scriptExecuted) {
                            if (Settings.DEBUG)
                                Logger.log("ObjectScript;Option 4", "Class: " + script.getClass().getSimpleName() + ".java, Name of Object: " + object.getName() + ", ObjectId: " + object.getId());
                            return;
                        }
                    }
                }, true));
        }
        player.setRouteEvent(new RouteEvent(object, new Runnable() {

            @Override
            public void run() {
                player.stopAll();
                player.faceObject(object);
                if (!player.getControlerManager().processObjectClick4(object))
                    return;
                if (GrotwormLair.handleObject4(object, player))
                    return;
                if (player.getFarmingManager().isFarming(id, null, 4))
                    return;
                // living rock Caverns
                if (id == 45076)
                    MiningBase.propect(player, "This rock contains a large concentration of gold.");
                else if (id == 5999)
                    MiningBase.propect(player, "This rock contains a large concentration of coal.");
                else {
                    switch (objectDef.name.toLowerCase()) {
                        default:
                            player.getPackets().sendGameMessage("Nothing interesting happens.");
                            break;
                    }
                }
                if (Settings.DEBUG)
                    Logger.log("ObjectHandler", "cliked 4 at object id : " + id + ", " + object.getX() + ", "
                            + object.getY() + ", " + object.getPlane() + ", ");
            }

        }));
    }

    private static void handleOption5(final Player player, final WorldObject object) {
        final ObjectDefinitions objectDef = object.getDefinitions();
        final int id = object.getId();
        player.setNextFaceEntity(null);
        ObjectScript script = ObjectScriptHandler.getScript(object);
        if (script != null) {
                player.setRouteEvent(new RouteEvent(object, new Runnable() {
                    @Override
                    public void run() {
                        player.stopAll();
                        player.faceObject(object);
                        boolean scriptExecuted = script.processObject5(player, object);
                        if (!scriptExecuted)
                            Logger.log("ObjectScript;Option 5", "Class: " + script.getClass().getSimpleName() + ".java, Option 5 method was empty in script.");
                        if (scriptExecuted) {
                            if (Settings.DEBUG)
                                Logger.log("ObjectScript;Option 5", "Class: " + script.getClass().getSimpleName() + ".java, Name of Object: " + object.getName() + ", ObjectId: " + object.getId());
                            return;
                        }
                    }
                }, true));
        }
        if (object.getId() >= HouseConstants.HObject.WOOD_BENCH.getId()
                && object.getId() <= HouseConstants.HObject.GILDED_BENCH.getId()) {
            player.setRouteEvent(new RouteEvent(object, new Runnable() {

                @Override
                public void run() {
                    player.getControlerManager().processObjectClick5(object);
                }
            }, true));
            return;
        }
        player.setRouteEvent(new RouteEvent(object, new Runnable() {
            @Override
            public void run() {
                player.stopAll();
                player.faceObject(object);
                if (!player.getControlerManager().processObjectClick5(object)) {
                    return;
                }
                switch (objectDef.name.toLowerCase()) {
                    case "fire":
                        if (objectDef.containsOption(4, "Add-logs"))
                            Bonfire.addLogs(player, object);
                        break;
                    default:
                        player.getPackets().sendGameMessage("Nothing interesting happens.");
                        break;
                }
                if (Settings.DEBUG)
                    Logger.log("ObjectHandler", "cliked 5 at object id : " + id + ", " + object.getX() + ", "
                            + object.getY() + ", " + object.getPlane() + ", ");
            }
        }));
    }

    private static void handleOptionExamine(final Player player, final WorldObject object) {
        if (Settings.DEBUG) {
            int offsetX = object.getX() - player.getX();
            int offsetY = object.getY() - player.getY();
        }
        if (object.getId() == 29735) {
            player.useStairs(-1, new WorldTile(3140, 4230, 2), 1, 2);
            player.getPackets().sendGameMessage("You find a secret passage.");
            return;
        }
        player.getPackets().sendObjectMessage(0, object,
                "It's " + player.grammar(object) + " " + object.getDefinitions().name + ".");

        if (Settings.DEBUG)
           System.out.println("ObjectHandler: examined object id : " + object.getId() + ", x" + object.getX() + ", y" + object.getY() + ", z"
                            + object.getPlane() + ", t" + object.getType() + ", r" + object.getRotation() + ", "
                            + object.getDefinitions().name + ", varbit: " + object.getConfigByFile() + ", var: "
                            + object.getConfig());
    }

    private static void slashWeb(Player player, WorldObject object) {
        boolean usingKnife = false;
        int defs = CombatDefinitions.getMeleeBonusStyle(player.getEquipment().getWeaponId(), 0);
        int defs2 = CombatDefinitions.getMeleeBonusStyle(player.getEquipment().getWeaponId(), 1);
        int defs3 = CombatDefinitions.getMeleeBonusStyle(player.getEquipment().getWeaponId(), 2);
        if (defs != CombatDefinitions.SLASH_ATTACK && defs2 != CombatDefinitions.SLASH_ATTACK
                && defs3 != CombatDefinitions.SLASH_ATTACK) {
            if (!player.getInventory().containsItem(946, 1)) {
                player.getPackets().sendGameMessage("You need something sharp to cut this with.");
                return;
            }
            usingKnife = true;
        }

        int weaponEmote = PlayerCombat.getWeaponAttackEmote(player.getEquipment().getWeaponId(),
                player.getCombatDefinitions().getAttackStyle());
        int knifeEmote = -1;

        player.animate(new Animation(usingKnife ? knifeEmote : weaponEmote));

        if (Utils.getRandom(1) == 0) {
            World.spawnObjectTemporary(new WorldObject(object.getId() + 1, object.getType(), object.getRotation(),
                    object.getX(), object.getY(), object.getPlane()), 60000);
            player.getPackets().sendGameMessage("You slash through the web!");
        } else
            player.getPackets().sendGameMessage("You fail to cut through the web.");
    }

    public static void handleItemOnObject(final Player player, final WorldObject object, final int interfaceId,
                                          final Item item) {
        final int itemId = item.getId();
        final ObjectDefinitions objectDef = object.getDefinitions();
        ObjectScript script = ObjectScriptHandler.getScript(object);
        if (script != null) {
                player.setRouteEvent(new RouteEvent(object, new Runnable() {
                    @Override
                    public void run() {
                        player.stopAll();
                        player.faceObject(object);
                        boolean scriptExecuted = script.processItemOnObject(player, object, item);
                        if (!scriptExecuted)
                            Logger.log("ObjectScript;ItemOnObject", "Class: " + script.getClass().getSimpleName() + ".java, ItemOnObject method was empty in script.");
                        if (scriptExecuted) {
                            if (Settings.DEBUG)
                                Logger.log("ObjectScript;ItemOnObject", "Class: " + script.getClass().getSimpleName() + ".java, Name of Object: " + object.getName() + ", ObjectId: " + object.getId() + ", Name of Item: " + item.getName() + ", ItemId: " + item.getId());
                            return;
                        }
                    }
                }, true));
        }
        if (item.getId() == 954 && object.getId() == 26342) {
        	player.getVarsManager().sendVarBit(3932, 1, true);
        	player.getInventory().removeItems(new Item(954));
        	player.sm("You tie the rope to the enterance.");
        }
        if (item.getId() == 3695 && object.getId() == 57180) {
        	player.getInventory().removeItems(new Item(3695));
        	player.setNextForceTalk(new ForceTalk("Fuck outa here boi!"));
        	player.gfx(new Graphics(1838));
        	player.animate(new Animation(10504));
        	player.sm("The rock didn't want to be your friend anyways..");
        	return;
        }
        if (object.getId() == 36695 && item.getId() == 1704) {
            int gloryAmount = player.getInventory().getAmountOf(1704);
            player.getInventory().deleteItem(new Item(1704, gloryAmount));
            player.getInventory().addItem(new Item(1712, gloryAmount));
            player.getDialogueManager().startDialogue("ItemMessage", "You recharge all of your glories from the fountain.", itemId);
            return;
        }
        if (object.getId() == 1996) {
            if (item.getId() != 954)
                return;
            for (int i = 0; i < 7; i++) {
                WorldObject o = new WorldObject(object);
                o.setId(1998);
                o.setLocation(o.getX(), 3475 - i, o.getPlane());
                player.getPackets().sendAddObject(o);
            }
            WorldObject o = new WorldObject(object);
            o.setId(1997);
            player.getPackets().sendAddObject(o);
            player.getAppearence().setRenderEmote(188);
            player.setNextForceMovement(new ForceMovement(object, 8, ForceMovement.SOUTH));
            WorldTasksManager.schedule(new WorldTask() {

                @Override
                public void run() {
                    for (int i = 0; i < 7; i++) {
                        WorldObject o = new WorldObject(object);
                        o.setId(1998);
                        o.setLocation(o.getX(), 3475 - i, o.getPlane());
                        player.getPackets().sendRemoveObject(o);
                    }
                    WorldObject o = new WorldObject(object);
                    o.setId(1996);
                    player.getPackets().sendAddObject(o);
                    player.getAppearence().setRenderEmote(-1);
                    player.setNextWorldTile(new WorldTile(2513, 3468, 0));
                }
            }, 7);
            return;
        } else {
            if (FishingFerretRoom.handleFerretThrow(player, object, item))
                return;
        }
        player.setRouteEvent(new RouteEvent(object, new Runnable() {
            @Override
            public void run() {
                if (!player.getControlerManager().handleItemOnObject(object, item)) {
                    return;
                }
                player.faceObject(object);
                if (object.getId() == 13715) {
                    if (item.getName().toLowerCase().contains(" 100") || item.getName().toLowerCase().contains(" 75")
                            || item.getName().toLowerCase().contains(" 50")
                            || item.getName().toLowerCase().contains(" 25")
                            || item.getName().toLowerCase().contains(" 0")) {
                        ArmourRepair.repairItem(player, item, true);
                        return;
                    } else {
                        player.sm("You can't repair this item.");
                        return;
                    }
                }
                for (CombinationRunesStore data : CombinationRunesStore.values()) {
                    if (data != null) {
                        if ((item.getId() == data.itemId || item.getId() == data.talisman)
                                && object.getId() == data.objectId2) {
                            CombinationRunes.craftComboRune(player, data.itemId, data.level, data.exp, data.finalItem,
                                    data.talisman);
                            return;
                        } else if ((item.getId() == data.itemId2 || item.getId() == data.talisman2)
                                && object.getId() == data.objectId) {
                            CombinationRunes.craftComboRune(player, data.itemId2, data.level, data.exp2, data.finalItem,
                                    data.talisman2);
                            return;
                        }
                    }
                }
                for (RunecraftingTiaraStore data : RunecraftingTiaraStore.values()) {
                    if (data != null) {
                        if (item.getId() == data.talismanId && object.getId() == data.altarId) {
                            Tiaras.enchantTiara(player, data.talismanId, data.tiaraId, data.level, data.exp);
                            return;
                        }
                    }
                }
                if (HouseConstants.Builds.ALTAR.containsObject(object)) {
                    Bone bone = Bone.forId(item.getId());
                    if (bone != null) {
                        player.getActionManager().setAction(new BoneOffering(object, bone, 2));
                        return;
                    }
                }
                if (CityEventHandler.handleItemOnObject(player, object, item))
                    return;
                if (player.getFarmingManager().isFarming(object.getId(), item, 0)) {
                    return;
                } else if (itemId == 1947 && (object.getId() == 70034 || object.getId() == 24071)) {
                    if (player.hopper) {
                        player.getPackets().sendGameMessage("You already put some wheat in the hopper.");
                        return;
                    }
                    player.hopper = true;
                    player.getInventory().deleteItem(itemId, 1);
                    player.getPackets().sendGameMessage("You put the wheat in the hopper.");
                    return;
                    /*
                     * } else if (itemId == 536 && object.getId() == 172) {
                     * GildedAltar.addBones(player, object);
                     */
                } else if (itemId == 983 && object.getId() == 1804 && player.getY() == 3449) {
                    DoorsAndGates.handleDoorTemporary(player, object, 1200);
                } else if (itemId == 14472 && object.getId() == 2783 || itemId == 14474 && object.getId() == 2783
                        || itemId == 14476 && object.getId() == 2783) {
                    if (!player.getInventory().containsItem(2347, 1)) {
                        player.getPackets().sendGameMessage("You don't have any hammer to smith this.");
                        return;
                    }
                    if (player.getSkills().getLevel(Skills.SMITHING) >= 92) {
                        if (player.getInventory().containsItem(14472, 1) && player.getInventory().containsItem(14474, 1)
                                && player.getInventory().containsItem(14476, 1)) {
                            player.animate(new Animation(898));
                            player.getSkills().addXp(Skills.SMITHING, 5000);
                            player.getInventory().deleteItem(14472, 1);
                            player.getInventory().deleteItem(14474, 1);
                            player.getInventory().deleteItem(14476, 1);
                            player.getInventory().addItem(14479, 1);
                        } else {
                            player.getPackets().sendGameMessage(
                                    "You need ruined dragon lump, slice and shard in order to smith this.");
                        }
                    } else {
                        player.getPackets().sendGameMessage("You need an level of at least 92 smithing to smith this.");
                    }
                } else if (itemId == 11286 && object.getId() == 2783) {
                    if (!player.getInventory().containsItem(2347, 1)) {
                        player.getPackets().sendGameMessage("You don't have any hammer to smith this.");
                        return;
                    }
                    if (player.getSkills().getLevel(Skills.SMITHING) >= 90) {
                        if (player.getInventory().containsItem(1540, 1)) {
                            player.animate(new Animation(898));
                            player.getSkills().addXp(Skills.SMITHING, 2000);
                            player.getInventory().deleteItem(1540, 1);
                            player.getInventory().deleteItem(11286, 1);
                            player.getInventory().addItem(11284, 1);
                        } else {
                            player.getPackets()
                                    .sendGameMessage("You need an anti-dragon shield in order to smith this.");
                        }
                    } else {
                        player.getPackets().sendGameMessage("You need an level of at least 90 smithing to smith this.");
                    }
                } else if (object.getId() == 733 || object.getId() == 64729) {
                    player.animate(new Animation(PlayerCombat.getWeaponAttackEmote(-1, 0)));
                    slashWeb(player, object);
                } else if (itemId == 2355) {
                    if (player.getSkills().getLevel(Skills.CRAFTING) < 23) {
                        player.getPackets().sendGameMessage("You need a crafting level of 23 to craft tiara.");
                        return;
                    }
                    if (!player.getInventory().containsItem(2355, 1) && player.getToolbelt().contains(2355)) {
                        player.getPackets().sendGameMessage("You need a tiara mould.");
                        return;
                    }
                    player.lock(4);
                    player.animate(new Animation(3243));
                    player.getSkills().addXp(Skills.CRAFTING, 52.5);
                    player.getInventory().deleteItem(2355, 1);
                    player.getInventory().addItem(5525, 1);
                    player.getPackets().sendGameMessage("You craft a tiara.");
                } else {
                    switch (objectDef.name.toLowerCase()) {
                        // case "altar":

                        // GildedAltar.addBone(player, object, item);
                        // break;
                        case "small table":
                        case "large table":
                        case "crate":
                        case "counter":
                        case "table":
                            if (objectDef.containsOption("bank")) {
                                player.getPackets().sendGameMessage("Nothing interesting happens.");
                                return;
                            }
                            if (!ItemConstants.isTradeable(item)) {
                                player.getPackets().sendGameMessage("You can't put that on the " + objectDef.name + ".");
                                return;
                            }
                            player.animate(new Animation(833));
                            player.getInventory().deleteItem(new Item(item.getId(), item.getAmount()));
                            World.updateGroundItem(item, new WorldTile(object), player, 60, 0);
                            break;
                        case "anvil":
                            ForgingBar bar = ForgingBar.forId(itemId);
                            if (bar != null)
                                ForgingInterface.sendSmithingInterface(player, bar);
                            break;
                        case "furnace":
                        case "lava furnace":
                            if (item.getId() == 2357) {
                                JewllerySmithing.openInterface(player);
                                return;
                            } else if (item.getId() == 2353 || item.getId() == 4) {
                                if (player.getInventory().containsItem(4, 1))
                                    player.getActionManager().setAction(new Smelting(SmeltingBar.CANNONBALL.getButtonId(),
                                            object, player.getInventory().getNumberOf(2353)));
                                else
                                    player.sm("You need a cannonball mould.");
                                return;
                            } else if (item.getId() == 4155) {
                                if (!player.getSlayerManager().hasLearnedRing()) {
                                    player.getPackets().sendGameMessage("You haven't learnt to do this yet.");
                                    return;
                                }
                                if (player.getSkills().getLevel(Skills.SMITHING) < 75) {
                                    player.getPackets()
                                            .sendGameMessage("You need at least a level of 75 to smith ring of slayings.");
                                    return;
                                }
                                if (!player.getInventory().containsItem(2357, 1)) {
                                    player.getPackets().sendGameMessage("You need a gold bar to smith a ring of slaying.");
                                    return;
                                }
                                player.animate(new Animation(3243));
                                player.getSkills().addXp(Skills.SMITHING, 15);
                                player.getInventory().deleteItem(4155, 1);
                                player.getInventory().deleteItem(2357, 1);
                                player.getInventory().addItem(13281, 1);
                                return;
                            }
                            player.sm("Nothing interesting happens.");
                            break;
                        case "sink":
                        case "fountain":
                        case "well":
                        case "waterpump":
                            if (WaterFilling.isFilling(player, itemId, false))
                                return;
                            break;
                        case "range":
                        case "cooking range":
                        case "stove":
                        case "fire":
                            if (objectDef.containsOption(4, "Add-logs") && Bonfire.addLog(player, object, item))
                                return;
                            Cookables cook = Cooking.isCookingSkill(item);
                            if (cook != null) {
                                player.getDialogueManager().startDialogue("CookingD", cook, object);
                                return;
                            }
                            player.getPackets().sendGameMessage(
                                    "You can't cook that on a " + (objectDef.name.equals("Fire") ? "fire" : "range") + ".");
                            break;
                        default:
                            player.getPackets().sendGameMessage("Nothing interesting happens.");
                            break;
                    }
                    if (Settings.DEBUG)
                        System.out.println("Item on object: " + object.getId());
                }
            }
        }));
    }

    private static int getOpenId(int objectId) {
        if (objectId == 44293)
            return 44305;
        return objectId + 1;
    }
}
