package com.rs.net.decoders.handlers;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cores.CoresManager;
import com.rs.cores.WorldThread;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.Region;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.item.ItemPluginLoader;
import com.rs.game.item.ItemPlugin;
import com.rs.game.item.itemdegrading.ArmourRepair;
import com.rs.game.item.itemdegrading.ChargesManager;
import com.rs.game.item.itemdegrading.ItemDegrade.ItemStore;
import com.rs.game.minigames.clanwars.FfaZone;
import com.rs.game.npc.NPC;
import com.rs.game.npc.NpcPlugin;
import com.rs.game.npc.NpcPluginLoader;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.npc.pet.Pet;
import com.rs.game.player.Equipment;
import com.rs.game.player.Inventory;
import com.rs.game.player.Player;
import com.rs.game.player.RouteEvent;
import com.rs.game.player.Skills;
import com.rs.game.player.Toolbelt;
import com.rs.game.player.actions.BoxAction;
import com.rs.game.player.actions.BoxAction.HunterEquipment;
import com.rs.game.player.actions.ItemOnItem;
import com.rs.game.player.actions.combat.LunarMagicks;
import com.rs.game.player.actions.combat.LunarMagicks.RSLunarSpellStore;
import com.rs.game.player.actions.combat.Magic;
import com.rs.game.player.actions.combat.ModernMagicks;
import com.rs.game.player.actions.combat.ModernMagicks.RSSpellStore;
import com.rs.game.player.actions.skills.cooking.DoughCooking;
import com.rs.game.player.actions.skills.cooking.DoughCooking.Cook;
import com.rs.game.player.actions.skills.crafting.GemCutting;
import com.rs.game.player.actions.skills.crafting.GemCutting.Gem;
import com.rs.game.player.actions.skills.crafting.LeatherCrafting;
import com.rs.game.player.actions.skills.crafting.LeatherCrafting.Craft;
import com.rs.game.player.actions.skills.farming.FarmingManager.ProductInfo;
import com.rs.game.player.actions.skills.farming.TreeSaplings;
import com.rs.game.player.actions.skills.firemaking.FireLighter;
import com.rs.game.player.actions.skills.firemaking.FireLighter.Lighters;
import com.rs.game.player.actions.skills.firemaking.Firemaking;
import com.rs.game.player.actions.skills.fletching.Fletching;
import com.rs.game.player.actions.skills.fletching.Fletching.Fletch;
import com.rs.game.player.actions.skills.herblore.HerbCleaning;
import com.rs.game.player.actions.skills.herblore.Herblore;
import com.rs.game.player.actions.skills.hunter.Hunter;
import com.rs.game.player.actions.skills.prayer.Burying.Bone;
import com.rs.game.player.actions.skills.runecrafting.Talisman;
import com.rs.game.player.actions.skills.summoning.Summoning;
import com.rs.game.player.actions.skills.summoning.Summoning.Pouch;
import com.rs.game.player.actions.skills.woodcutting.BirdNest;
import com.rs.game.player.content.AncientEffigies;
import com.rs.game.player.content.ArmourSets;
import com.rs.game.player.content.ArmourSets.Sets;
import com.rs.game.player.content.BarrowsRewards;
import com.rs.game.player.content.Bell;
import com.rs.game.player.content.Dicing;
import com.rs.game.player.content.Foods;
import com.rs.game.player.content.GilesBusiness;
import com.rs.game.player.content.Lamps;
import com.rs.game.player.content.MysteryBox;
import com.rs.game.player.content.Pots;
import com.rs.game.player.content.Pots.Pot;
import com.rs.game.player.content.SkillCapeCustomizer;
import com.rs.game.player.content.ToyHorsey;
import com.rs.game.player.content.WeaponPoison;
import com.rs.game.player.content.WildernessArtefacts;
import com.rs.game.player.content.WildernessArtefacts.Artefacts;
import com.rs.game.player.content.dungeoneering.rooms.puzzles.ColouredRecessRoom.Block;
import com.rs.game.player.content.quest.QuestList.Quests;
import com.rs.game.player.content.treasuretrails.TreasureTrailsManager;
import com.rs.game.player.controlers.Barrows;
import com.rs.game.player.controlers.FightKiln;
import com.rs.game.player.controlers.GodCapes;
import com.rs.game.player.dialogues.skilling.AmuletAttaching;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.io.InputStream;
import com.rs.utils.HexColours;
import com.rs.utils.HexColours.Colour;
import com.rs.utils.Logger;
import com.rs.utils.Utils;

public class InventoryOptionsHandler {

    public static void handleItemOption2(final Player player, final int slotId, final int itemId, Item item) {
        if (Settings.FREE_TO_PLAY && item.getDefinitions().isMembersOnly()) {
            player.sm("This is a members object.");
            return;
        }
        ItemPlugin plugin = ItemPluginLoader.getPlugin(item);
        if (plugin != null) {
            boolean pluginExecuted = plugin.processItem2(player, item, slotId);
            if (!pluginExecuted) {
                Logger.log("ItemPlugin", "Option 2 - Class: " + plugin.getClass().getSimpleName() + ".java, Failed: " + item.getName() + "(" + item.getId() + ") plugin does not have this option.");
            }
            if (pluginExecuted) {
                Logger.log("ItemPlugin", "Option 2 - Class: " + plugin.getClass().getSimpleName() + ".java, Executed: " + item.getName() + "(" + item.getId() + ")");
                return;
            }
        }
        if (itemId >= 15086 && itemId <= 15100) {
            if (player.getLockDelay() > Utils.currentTimeMillis())
                return;
            Dicing.handleRoll(player, itemId, true);
        } else {
            long passedTime = Utils.currentTimeMillis() - WorldThread.LAST_CYCLE_CTM;
            WorldTasksManager.schedule(new WorldTask() {

                @Override
                public void run() {
                    List<Integer> slots = player.getSwitchItemCache();
                    int[] slot = new int[slots.size()];
                    for (int i = 0; i < slot.length; i++)
                        slot[i] = slots.get(i);
                    player.getSwitchItemCache().clear();
                    ButtonHandler.sendWear(player, slot);
                }
            }, passedTime >= 600 ? 0 : 0);
            if (player.getSwitchItemCache().contains(slotId))
                return;
            player.getSwitchItemCache().add(slotId);
        }
    }

    public static void dig(final Player player) {
        player.resetWalkSteps();
        player.animate(new Animation(830));
        player.lock();
        WorldTasksManager.schedule(new WorldTask() {

            @Override
            public void run() {
                player.unlock();
                if (Barrows.digIntoGrave(player))
                    return;
                if (player.getTreasureTrailsManager().useDig())
                    return;
                if (player.getX() == 3005 && player.getY() == 3376 || player.getX() == 2999 && player.getY() == 3375 || player.getX() == 2996 && player.getY() == 3377 || player.getX() == 2989 && player.getY() == 3378 || player.getX() == 2987 && player.getY() == 3387 || player.getX() == 2984 && player.getY() == 3387) {
                    player.setNextWorldTile(new WorldTile(1752, 5137, 0));
                    player.getPackets().sendGameMessage("You seem to have dropped down into a network of mole tunnels.");
                    return;
                }
                player.sm("You find nothing.");
            }
        }, 1);
    }


    public static void handleItemOption1(Player player, final int slotId, final int itemId, Item item) {
        long time = Utils.currentTimeMillis();
        if (player.getLockDelay() >= time || player.getEmotesManager().getNextEmoteEnd() >= time)
            return;
        player.stopAll(false);
        if (Settings.FREE_TO_PLAY && item.getDefinitions().isMembersOnly()) {
            player.sm("This is a members object.");
            return;
        }
        if (!player.getControlerManager().processItemClick(slotId, item, player))
            return;
        ItemPlugin plugin = ItemPluginLoader.getPlugin(item);
        if (plugin != null) {
            boolean pluginExecuted = plugin.processItem(player, item, slotId);
            if (!pluginExecuted) {
                Logger.log("ItemPlugin", "Option 1 - Class: " + plugin.getClass().getSimpleName() + ".java, Failed: " + item.getName() + "(" + item.getId() + ") plugin does not have this option.");
            }
            if (pluginExecuted) {
                Logger.log("ItemPlugin", "Option 1 - Class: " + plugin.getClass().getSimpleName() + ".java, Executed: " + item.getName() + "(" + item.getId() + ")");
                return;
            }
        }
        if (Foods.eat(player, item, slotId))
            return;
        if (Pots.pot(player, item, slotId))
            return;
        if (BirdNest.searchNest(player, itemId, slotId))
            return;
        if (itemId == 13663) {
            int amount = player.getInventory().getAmountOf(itemId);
            player.removeItem(itemId, amount);
            player.sm(HexColours.getShortMessage(Colour.RED, amount + "") + " pk points were added to your account.");
            player.addPKP(amount);
            return;
        }
        if (itemId == 15707)
            player.getDungManager().openPartyInterface();
        if (player.getTreasureTrailsManager().useItem(item, slotId))
            return;
        if ((itemId >= 1601 && itemId <= 1615 || itemId == 6573) || itemId == 10105 || itemId == 10107) {
            if (Settings.FREE_TO_PLAY) {
                player.sm("You can't fletch items in free to play.");
                return;
            }
            if (!player.getInventory().containsItem(1755, 1) && !player.getToolbelt().contains(1755)) {
                player.sm("You need a chisel to cut this item.");
            } else {
                Fletch fletch = Fletching.isFletching(new Item(1755), new Item(itemId));
                if (fletch != null) {
                    player.getDialogueManager().startDialogue("FletchingD", fletch);
                    return;
                }
            }
        }
        if (itemId == 2574) {
            player.getTreasureTrailsManager().useSextant();
            return;
        }
        if (itemId == 2798 || itemId == 3565 || itemId == 3576 || itemId == 19042) {
            player.getTreasureTrailsManager().openPuzzle(itemId);
        }
        if (itemId == 15046) {
            player.getDialogueManager().startDialogue("MagicSkullball", itemId, slotId);
            return;
        }
        if (itemId == 15048) {
            player.sm("Turning...");
            String[] responses = new String[]{"Absolutely", "Ask again another time.", "Don't hold your breath.", "I wouldn't have a clue.", "I'd be lying if i said no.", "Not so sure about that", "Seems probable", "Surely not.", "The fortunes are with you", "Without a shadow of a doubt.", "Yes, I'd say so."};
            CoresManager.slowExecutor.schedule(new Runnable() {

                @Override
                public void run() {
                    player.sm("Your Magic skullball (Long) says '" + (responses[Utils.getRandom(responses.length - 1)]) + "'");
                }
            }, 2000, TimeUnit.MILLISECONDS);
        }
        if (itemId == 15050) {
            player.sm("Turning...");
            CoresManager.slowExecutor.schedule(new Runnable() {

                @Override
                public void run() {
                    player.sm("Your Magic skullball (Yes/no) says '" + (Utils.random(2) == 1 ? "Yes" : "No") + "'");
                }
            }, 2000, TimeUnit.MILLISECONDS);
        }
        if (itemId == 15052) {
            player.sm("Turning...");
            String[] responses = new String[]{"Enjoy mass combat in Clan Wars.", "Experienced runecrafters can play the Great Orb Project.", "Fight for glory and rewards in the Duel Arena.", "Match weapons and wits against your foe in Fist of Guthix."};
            CoresManager.slowExecutor.schedule(new Runnable() {

                @Override
                public void run() {
                    player.sm("Your Magic skullball (activities) says '" + (responses[Utils.getRandom(responses.length - 1)]) + "'");
                }
            }, 2000, TimeUnit.MILLISECONDS);
        }
        if (itemId == 15054) {
            player.sm("Turning...");
            String[] responses = new String[]{"Black.", "Blue.", "Green.", "Orange.", "Red.", "Yellow.", "Purple.", "White."};
            CoresManager.slowExecutor.schedule(new Runnable() {

                @Override
                public void run() {
                    player.sm("Your Magic skullball (colours) says '" + (responses[Utils.getRandom(responses.length - 1)]) + "'");
                }
            }, 2000, TimeUnit.MILLISECONDS);
        }
        if (itemId == 15075) {
            for (Entity e : Utils.getAroundEntities(player, player, 14)) {
                if (e instanceof Player)
                    continue;
                NPC n = (NPC) e;
                if (n.getId() >= 9150 && n.getId() <= 9158) {
                    player.sm("Theres already a plant nearby!");
                    return;
                }
            }
            if (!player.hasMarker()) {
                player.setMarker(true);
                World.spawnNPC(9151, player, -1, true, true, player);
                player.addWalkSteps(player.getX(), player.getY() - 1, 1, true);
                return;
            } else {
                player.sm("You already have a marker plant.");
                return;
            }
        }
        if (itemId == 15061) {
            player.animate(new Animation(11908));
            player.getInventory().getItems().set(slotId, new Item(15063));
            player.getInventory().refresh();
            player.setTimePiece(0 + Utils.currentTimeMillis());
            return;
        }
        if (itemId == 15063) {
            player.animate(new Animation(11908));
            player.sm("Timer: " + Utils.getTimePiece(player.getTimePiece()));
            player.getInventory().getItems().set(slotId, new Item(15064));
            player.getInventory().refresh();
            return;
        }
        if (itemId == 15064) {
            player.animate(new Animation(11908));
            player.sm("Timer: " + Utils.getTimePiece(player.getTimePiece()));
            player.getInventory().getItems().set(slotId, new Item(15063));
            player.getInventory().refresh();
            return;
        }
        if (itemId >= 13561 && itemId <= 13562 || itemId == 19760) {
            CoresManager.slowExecutor.schedule(new Runnable() {
                @Override
                public void run() {
                    if (player.getSwitchItemCache().contains(slotId))
                        return;
                    player.getSwitchItemCache().add(slotId);
                    player.stopAll(false, true, true);
                }
            }, 200, TimeUnit.MILLISECONDS);
            // ButtonHandler.sendWear2(player, slotId, itemId);
        }
        if (itemId == 405) {// barrows casket
            if (player.getBarrowsRewards().get(0) == null) {
                BarrowsRewards.sendRewards(player);
            }
            player.getDialogueManager().startDialogue("BarrowsCasket", slotId, item);
            return;
        }
        if (itemId == 24154 || itemId == 24155) {
            player.getInventory().deleteItem(slotId, item);
            player.getSquealOfFortune().giveEarnedSpins(itemId == 24154 ? 1 : 2);
            player.getInterfaceManager().sendOverlay(1252, false);
            return;
        }
        if (itemId >= 15086 && itemId <= 15100) {
            if (player.getLockDelay() > Utils.currentTimeMillis())
                return;
            Dicing.handleRoll(player, itemId, false);
            return;
        } else if (Lamps.isSelectable(itemId) || Lamps.isSkillLamp(itemId) || Lamps.isOtherLamp(itemId))
            Lamps.processLampClick(player, slotId, itemId);
        if (itemId == 299) {
            if (player.getLockDelay() > Utils.currentTimeMillis())
                return;
            if (!World.isTileFree(player.getPlane(), player.getX(), player.getY(), 1) || World.getObjectWithSlot(player, Region.OBJECT_SLOT_FLOOR) != null || player.getControlerManager().getControler() != null) {
                player.sm("You can't plant flowers here.");
                return;
            }
            player.lock(2);
            final WorldTile tile = new WorldTile(player);
            int flower = Utils.random(2980, 2987);
            if (flower < 0.2) {
                flower = Utils.random(2987, 2988);
            }
            final WorldObject flowerObject = new WorldObject(flower, 10, Utils.getRandom(4), tile.getX(), tile.getY(), tile.getPlane());
            World.spawnObjectTemporary(flowerObject, 30000);
            player.getInventory().deleteItem(slotId, (new Item(itemId, 1)));
            if (!player.addWalkSteps(player.getX() - 1, player.getY(), 1))
                if (!player.addWalkSteps(player.getX() + 1, player.getY(), 1))
                    if (!player.addWalkSteps(player.getX(), player.getY() + 1, 1))
                        player.addWalkSteps(player.getX(), player.getY() - 1, 1);
        }

        if (itemId == 11159) {
            if (player.getInventory().getFreeSlots() < 9) {
                player.sm("You do not have enough inventory slots. You need 8 or more.");
                return;
            }
            final int[] hunter_items = {10150, 10010, 10006, 10031, 10029, 596, 10008, 11260};
            player.getInventory().deleteItem(11159, 1);
            for (int items : hunter_items)
                player.getInventory().addItem(items, 1);
            return;
        }
        if (itemId == 11949) {
            player.closeInterfaces();
            player.animate(new Animation(1745));
            player.getInterfaceManager().sendInterface(659);
            player.getInventory().addItem(10501, 10);
            return;
        }
        if (itemId == 15084) {
            player.getDialogueManager().startDialogue("DiceBag", itemId);
            return;
        }
        if (itemId >= 1617 && itemId <= 1631 || itemId == 6571) {
            if (!player.getInventory().containsItem(1755, 1) && !player.getToolbelt().contains(1755)) {
                player.sm("You need a chisel to cut this item.");
            } else {
                if (itemId == 1617)
                    GemCutting.cut(player, Gem.DIAMOND);
                if (itemId == 1619)
                    GemCutting.cut(player, Gem.RUBY);
                if (itemId == 1621)
                    GemCutting.cut(player, Gem.EMERALD);
                if (itemId == 1623)
                    GemCutting.cut(player, Gem.SAPPHIRE);
                if (itemId == 1625)
                    GemCutting.cut(player, Gem.OPAL);
                if (itemId == 1627)
                    GemCutting.cut(player, Gem.JADE);
                if (itemId == 1631)
                    GemCutting.cut(player, Gem.DRAGONSTONE);
                if (itemId == 6571)
                    GemCutting.cut(player, Gem.ONYX);
            }
        }
        if (itemId == 8014) {
            int bones = player.getInventory().getNumberOf(526);
            if (bones == 0) {
                player.sm("You don't have any bones.");
                return;
            }
            player.getInventory().deleteItem(526, bones);
            player.getInventory().deleteItem(8014, 1);
            player.getInventory().addItem(6883, bones);
            return;
        }
        if (itemId == 10952) {
            Bell.play(player);
            return;
        }
        if (itemId == 2520 || itemId == 2522 || itemId == 2524 || itemId == 2526) {
            ToyHorsey.play(player, item);
            return;
        }
        if (itemId == 2528) {
            player.getDialogueManager().startDialogue("TouristTrapLamp");
            return;
        }
        if (itemId == 18343) {
            if (player.hasRenewal) {
                player.sm("You already have this prayer unlocked.");
                return;
            }
            player.getInventory().deleteItem(18343, 1);
            player.sm("You have unlocked prayer: Rapid Renewal.");
            player.hasRenewal = true;
        }

        if (itemId == 18839) {
            if (player.hasRigour) {
                player.sm("You already have this prayer unlocked.");
                return;
            }
            player.getInventory().deleteItem(18839, 1);
            player.sm("You have unlocked prayer: Rigour.");
            player.hasRigour = true;
        }

        if (itemId == 19670) {
            if (player.hasEfficiency) {
                player.sm("You already have this unlocked.");
                return;
            }
            player.getInventory().deleteItem(19670, 1);
            player.sm("You have unlocked prayer: Effiecency.");
            player.hasEfficiency = true;
        }

        if (itemId == 18344) {
            if (player.hasAugury) {
                player.sm("You already have this prayer unlocked.");
                return;
            }
            player.getInventory().deleteItem(18344, 1);
            player.sm("You have unlocked prayer: Augury.");
            player.hasAugury = true;
        }

        /*
         * Coal bag
         */

        if (itemId == 18339) {
            player.getPackets().sendGameMessage("This coal bag currently contains " + player.getCoalStored() + " coal ores.");
        }

        if (itemId == 24853) {
            player.getDialogueManager().startDialogue("BenefitTicket");
        }

        /*
         * Coin bags
         */
        if (itemId >= 10521 && itemId <= 10524) {
            int amount = itemId == 10521 ? Utils.random(2500, 7500) : itemId == 10522 ? Utils.random(10000, 20000) : itemId == 10523 ? Utils.random(25000, 35000) : Utils.random(100000, 130000);
            player.getInventory().deleteItem(slotId, item);
            player.getMoneyPouch().addMoney(amount, false);
            player.getDialogueManager().startDialogue("SimpleMessage", "You find " + Utils.getFormattedNumber(amount, ',') + " coins.");
        }

        /*
         * Clue Scroll Reward Chest
         */
        int count = 0;
        for (int i : TreasureTrailsManager.REWARD_CASKETS) {
            if (i == itemId) {
                player.getInventory().deleteItem(itemId, 1);
                player.getTreasureTrailsManager().openReward(count);
                return;
            } else {
                count++;
                continue;
            }
        }
        if (itemId == 6199) {
            MysteryBox.sendRewards(player, slotId);
        }

        /*
         * Map Clue Scrolls - Wilderness
         */
        if (itemId == 2677)
            player.getInterfaceManager().sendInterface(338);
        if (itemId == 2678)
            player.getInterfaceManager().sendInterface(359);
        if (itemId == 952) {// spade
            dig(player);
            return;
        }
        if (itemId == AncientEffigies.SATED_ANCIENT_EFFIGY || itemId == AncientEffigies.GORGED_ANCIENT_EFFIGY || itemId == AncientEffigies.NOURISHED_ANCIENT_EFFIGY || itemId == AncientEffigies.STARVED_ANCIENT_EFFIGY) {
            player.getDialogueManager().startDialogue("AncientEffigiesD", itemId);
            return;
        }
        if (itemId == 7509) {
            long foodDelay = player.getHitpoints() < 50 ? 1800 : 200;
            if (player.getFoodDelay() > Utils.currentTimeMillis())
                return;
            player.getActionManager().setActionDelay((int) foodDelay / 5000);
            player.addFoodDelay(foodDelay);
            player.getActionManager().setActionDelay(player.getActionManager().getActionDelay() + 4);
            player.animate(new Animation(829));
            if (player.getHitpoints() == 1)
                return;
            if (player.getHitpoints() > 10)
                player.applyHit(new Hit(player, 10, HitLook.REGULAR_DAMAGE));
            else
                player.applyHit(new Hit(player, player.getHitpoints() - 1, HitLook.REGULAR_DAMAGE));
            player.setNextForceTalk(new ForceTalk("Ow! My tooth!"));
        }
        if (itemId >= 2520 && itemId <= 2526) {
            player.animate(new Animation(itemId == 2520 ? 918 : itemId == 2522 ? 919 : itemId == 2524 ? 920 : 921));
            switch (Utils.random(2)) {
                case 0:
                    player.setNextForceTalk(new ForceTalk("Come on Dobbin, we can win the race!"));
                    break;
                case 1:
                    player.setNextForceTalk(new ForceTalk("Hi-ho Silver, and away!"));
                    break;
                case 2:
                    player.setNextForceTalk(new ForceTalk("Neaahhhyyy! Giddy-up horsey!"));
                    break;
            }
            return;
        }
        if (itemId == 12844) {
            player.lock(4);
            player.animate(new Animation(8990));
            return;
        }
        if (itemId == 4613) {
            player.lock(3);
            player.animate(new Animation(1902));
            return;
        }
        if (itemId == 7510) {
            long foodDelay = player.getHitpoints() < 200 ? 1800 : 200;
            if (player.getFoodDelay() > Utils.currentTimeMillis())
                return;
            player.getActionManager().setActionDelay((int) foodDelay / 5000);
            player.addFoodDelay(foodDelay);
            player.getActionManager().setActionDelay(player.getActionManager().getActionDelay() + 4);
            player.animate(new Animation(829));
            if (player.getHitpoints() == 1)
                return;
            if (player.getHitpoints() > 50)
                player.applyHit(new Hit(player, 50, HitLook.REGULAR_DAMAGE));
            else
                player.applyHit(new Hit(player, player.getHitpoints() - 1, HitLook.REGULAR_DAMAGE));
            player.setNextForceTalk(new ForceTalk("Ow! I nearly broke a tooth!"));
        }
        if (itemId == 10476) {
            long foodDelay = 1800;
            int runEnergy = (int) (player.getRunEnergy() * 1.1);
            if (player.getFoodDelay() > Utils.currentTimeMillis())
                return;
            player.getInventory().deleteItem(itemId, 1);
            if (runEnergy > 100)
                runEnergy = 100;
            player.setRunEnergy(runEnergy);
            player.getActionManager().setActionDelay((int) foodDelay / 1000);
            player.addFoodDelay(foodDelay);
            player.getActionManager().setActionDelay(player.getActionManager().getActionDelay() + 4);
            player.animate(new Animation(829));
            player.heal(10 + Utils.random(20));
        }
        if (HerbCleaning.clean(player, item, slotId))
            return;
        Bone bone = Bone.forId(itemId);
        if (bone != null) {
            Bone.bury(player, slotId);
            return;
        }
        if (Magic.useTabTeleport(player, itemId))
            return;
        if (Magic.useLumberYardTeleport(player, itemId))
            return;
        else if (itemId == 4155)
            player.getDialogueManager().startDialogue("EnchantedGemDialouge");
        else if (itemId == 4251)
            Magic.useEctoPhial(player, item);
        else if (itemId >= 23653 && itemId <= 23658)
            FightKiln.useCrystal(player, itemId);
        else if (itemId == HunterEquipment.BOX.getId())
            player.getActionManager().setAction(new BoxAction(HunterEquipment.BOX));
        else if (itemId == HunterEquipment.BRID_SNARE.getId())
            player.getActionManager().setAction(new BoxAction(HunterEquipment.BRID_SNARE));
        else if (item.getDefinitions().getName().startsWith("Burnt"))
            player.getDialogueManager().startDialogue("SimplePlayerMessage", "Ugh, this is inedible.");
    }

    /*
     * returns the other
     */
    public static Item contains(int id1, Item item1, Item item2) {
        if (item1.getId() == id1)
            return item2;
        if (item2.getId() == id1)
            return item1;
        return null;
    }

    public static boolean contains(int id1, int id2, Item... items) {
        boolean containsId1 = false;
        boolean containsId2 = false;
        for (Item item : items) {
            if (item.getId() == id1)
                containsId1 = true;
            else if (item.getId() == id2)
                containsId2 = true;
        }
        return containsId1 && containsId2;
    }

    @SuppressWarnings("unused")
    public static void handleMagicOnItem(final Player player, InputStream stream) {
        int inventoryInter = stream.readInt() >> 16;
        int itemId = stream.readShort128();
        int junk = stream.readShort();
        int itemSlot = stream.readShortLE();
        int interfaceSet = stream.readIntV1();
        int spellId = interfaceSet & 0xFFF;
        int magicInter = interfaceSet >> 16;
        if (inventoryInter == Inventory.INVENTORY_INTERFACE && magicInter == 192) {
            switch (spellId) {

            }
        }

    }

    public static void handleItemOnItem(final Player player, InputStream stream) {
        int itemUsedWithId = stream.readShort();
        int toSlot = stream.readShortLE128();
        int hash1 = stream.readInt();
        int hash2 = stream.readInt();
        int interfaceId = hash1 >> 16;
        int interfaceId2 = hash2 >> 16;
        int spellId = hash1 & 0xFFFF;
        int compId = hash1 & 0xFFFF;
        int fromSlot = stream.readShort();
        int itemUsedId = stream.readShortLE128();
        player.stopAll(false);
        if (interfaceId == 192 && interfaceId2 == Inventory.INVENTORY_INTERFACE) {
            player.getTemporaryAttributtes().put("spell_itemid", itemUsedWithId);
            player.getTemporaryAttributtes().put("spell_slotid", toSlot);
            RSSpellStore modern = RSSpellStore.getSpell(compId);
            if (modern != null) {
                if (!ModernMagicks.hasRequirement(player, spellId, false, false)) {
                    return;
                }
            } else {
                player.sm("Nothing interesting happens.");
                return;
            }
        } else if (interfaceId == 430 && interfaceId2 == Inventory.INVENTORY_INTERFACE) {
            player.getTemporaryAttributtes().put("spell_itemid", itemUsedWithId);
            player.getTemporaryAttributtes().put("spell_slotid", toSlot);
            RSLunarSpellStore lunar = RSLunarSpellStore.getSpell(compId);
            if (lunar != null) {
                if (!LunarMagicks.hasRequirement(player, spellId)) {
                    return;
                }
            } else {
                player.sm("Nothing interesting happens.");
                return;
            }
        } else if (interfaceId == 747 || interfaceId == 662 && (interfaceId2 == Inventory.INVENTORY_INTERFACE)) {
            if (!player.hasStarted() || !player.clientHasLoadedMapRegion() || player.isDead())
                return;
            if (player.getLockDelay() > Utils.currentTimeMillis())
                return;
            if (player.getFamiliar() == null)
                return;
            if (!player.getInventory().containsItem(Summoning.getScrollId(player.getFamiliar().getPouch().getRealPouchId()), 1)) {
                player.sm("You don't have enough scrolls to do that.");
                return;
            }
            if (player.getFamiliar().specialEnergy < player.getFamiliar().getSpecialAmount()) {
                player.sm("You familiar doesn't have enough special energy.");
                return;
            }
            player.getFamiliar().submitSpecial(toSlot);
            player.getFamiliar().drainSpecial();
            return;
        } else if (interfaceId == Inventory.INVENTORY_INTERFACE && interfaceId == interfaceId2 && !player.getInterfaceManager().containsInventoryInter()) {
            if (toSlot >= 28 || fromSlot >= 28)
                return;
            Item usedWith = player.getInventory().getItem(toSlot);
            Item itemUsed = player.getInventory().getItem(fromSlot);
            if (itemUsed == null || usedWith == null || itemUsed.getId() != itemUsedId || usedWith.getId() != itemUsedWithId)
                return;
            player.stopAll();
            if (interfaceId == 192 && interfaceId2 == Inventory.INVENTORY_INTERFACE)
                player.sm("alch on " + toSlot);
            if (!player.getControlerManager().canUseItemOnItem(itemUsed, usedWith))
                return;
            ItemPlugin plugin = ItemPluginLoader.getPlugin(itemUsed);
            if (plugin == null)
                plugin = ItemPluginLoader.getPlugin(usedWith);
            if (plugin != null) {
                boolean pluginExecuted = plugin.processItemOnItem(player, itemUsed, usedWith, fromSlot, toSlot);
                if (!pluginExecuted) {
                    Logger.log("ItemPlugin", "ItemOnItem - Class: " + plugin.getClass().getSimpleName() + ".java, Failed: " + itemUsed.getName() + "(" + itemUsed.getId() + ") > " + usedWith.getName() + "(" + usedWith.getId() + ") does not meet the requirements");
                }
                if (pluginExecuted) {
                    Logger.log("ItemPlugin", "ItemOnItem - Class: " + plugin.getClass().getSimpleName() + ".java, Executed: " + itemUsed.getName() + "(" + itemUsed.getId() + ") > " + usedWith.getName() + "(" + usedWith.getId() + ") does meet the requirements");
                    return;
                }
            }
            if (WeaponPoison.poison(player, itemUsed, usedWith, false))
                return;
            if (Firemaking.isFiremaking(player, itemUsed, usedWith))
                return;
            Craft craft = LeatherCrafting.isCrafting(usedWith, itemUsed);
            if (craft != null) {
                player.getDialogueManager().startDialogue("LeatherCraftingD", craft);
                return;
            }
            Cook cook = DoughCooking.isCooking(usedWith, itemUsed);
            if (cook != null) {
                player.getDialogueManager().startDialogue("DoughCookingD", cook);
                return;
            }
            Lighters lighters = FireLighter.getColoredLog(itemUsedId, usedWith.getId());
            if (lighters != null) {
                FireLighter.handleLighter(player, lighters);
                return;
            }
            int herblore = Herblore.isHerbloreSkill(itemUsed, usedWith);
            if (herblore > -1) {
                player.getDialogueManager().startDialogue("HerbloreD", herblore, itemUsed, usedWith);
                return;
            }
            Sets set = ArmourSets.getArmourSet(itemUsedId, itemUsedWithId);
            if (set != null) {
                ArmourSets.exchangeSets(player, set);
                return;
            }
            if (AmuletAttaching.isAttaching(itemUsedId, itemUsedWithId)) {
                player.getDialogueManager().startDialogue("AmuletAttaching");
                return;
            }
            if (Pots.mixPot(player, itemUsed, usedWith, fromSlot, toSlot))
                return;
            if (Firemaking.isFiremaking(player, itemUsed, usedWith))
                return;
            else if (TreeSaplings.hasSaplingRequest(player, itemUsedId, usedWith.getId())) {
                if (itemUsedId == 5354)
                    TreeSaplings.plantSeed(player, usedWith.getId(), fromSlot);
                else
                    TreeSaplings.plantSeed(player, itemUsedId, toSlot);
            }
            if (ItemOnItem.isValidCombination(player, itemUsed, usedWith, fromSlot, toSlot))
                return;
            if (contains(6585, 19333, itemUsed, usedWith)) {// fury or
                player.getInventory().deleteItem(itemUsed.getId(), 1);
                player.getInventory().deleteItem(usedWith.getId(), 1);
                player.getInventory().addItem(19335, 1);
                player.sm("The ornament kit attaches itself to the item.");
                return;
            } else if (contains(11335, 19346, itemUsed, usedWith)) {// dragon
                // fullhelm
                // or
                player.getInventory().deleteItem(itemUsed.getId(), 1);
                player.getInventory().deleteItem(usedWith.getId(), 1);
                player.getInventory().addItem(19336, 1);
                player.sm("The ornament kit attaches itself to the item.");
                return;
            } else if (contains(4585, 19348, itemUsed, usedWith)) {// dragon
                // legs or
                player.getInventory().deleteItem(itemUsed.getId(), 1);
                player.getInventory().deleteItem(usedWith.getId(), 1);
                player.getInventory().addItem(19339, 1);
                player.sm("The ornament kit attaches itself to the item.");
                return;
            } else if (contains(4087, 19348, itemUsed, usedWith)) {// dragon
                // skirt or
                player.getInventory().deleteItem(itemUsed.getId(), 1);
                player.getInventory().deleteItem(usedWith.getId(), 1);
                player.getInventory().addItem(19338, 1);
                player.sm("The ornament kit attaches itself to the item.");
                return;
            } else if (contains(14479, 19350, itemUsed, usedWith)) {// dragon
                // platebody
                // or
                player.getInventory().deleteItem(itemUsed.getId(), 1);
                player.getInventory().deleteItem(usedWith.getId(), 1);
                player.getInventory().addItem(19337, 1);
                player.sm("The ornament kit attaches itself to the item.");
                return;
            } else if (contains(1187, 19352, itemUsed, usedWith)) {// dragon sq
                // or
                player.getInventory().deleteItem(itemUsed.getId(), 1);
                player.getInventory().deleteItem(usedWith.getId(), 1);
                player.getInventory().addItem(19340, 1);
                player.sm("The ornament kit attaches itself to the item.");
                return;
            } else if (contains(11335, 19354, itemUsed, usedWith)) {// dragon
                // fullhelm
                // sp
                player.getInventory().deleteItem(itemUsed.getId(), 1);
                player.getInventory().deleteItem(usedWith.getId(), 1);
                player.getInventory().addItem(19341, 1);
                player.sm("The ornament kit attaches itself to the item.");
                return;
            } else if (contains(4585, 19356, itemUsed, usedWith)) {// dragon
                // legs sp
                player.getInventory().deleteItem(itemUsed.getId(), 1);
                player.getInventory().deleteItem(usedWith.getId(), 1);
                player.getInventory().addItem(19344, 1);
                player.sm("The ornament kit attaches itself to the item.");
                return;
            } else if (contains(4087, 19356, itemUsed, usedWith)) {// dragon
                // skirt sp
                player.getInventory().deleteItem(itemUsed.getId(), 1);
                player.getInventory().deleteItem(usedWith.getId(), 1);
                player.getInventory().addItem(19343, 1);
                player.sm("The ornament kit attaches itself to the item.");
                return;
            } else if (contains(14479, 19358, itemUsed, usedWith)) {// dragon
                // platebody
                // sp
                player.getInventory().deleteItem(itemUsed.getId(), 1);
                player.getInventory().deleteItem(usedWith.getId(), 1);
                player.getInventory().addItem(19342, 1);
                player.sm("The ornament kit attaches itself to the item.");
                return;
            } else if (contains(1187, 19360, itemUsed, usedWith)) {// dragon sq
                // sp
                player.getInventory().deleteItem(itemUsed.getId(), 1);
                player.getInventory().deleteItem(usedWith.getId(), 1);
                player.getInventory().addItem(19345, 1);
                player.sm("The ornament kit attaches itself to the item.");
                return;
            } else if (contains(2366, 2368, itemUsed, usedWith)) {// dragon sq
                // shield
                player.getInventory().deleteItem(itemUsed.getId(), 1);
                player.getInventory().deleteItem(usedWith.getId(), 1);
                player.getInventory().addItem(1187, 1);
                player.getPackets().sendGameMessage("You attach the parts together and create a mysterious dragon shield.");
                return;
            } else if (contains(11690, 11702, itemUsed, usedWith)) {
                player.getInventory().deleteItem(itemUsed.getId(), 1);
                player.getInventory().deleteItem(usedWith.getId(), 1);
                player.getInventory().addItem(11694, 1);
                return;
            } else if (contains(11690, 11704, itemUsed, usedWith)) {
                player.getInventory().deleteItem(itemUsed.getId(), 1);
                player.getInventory().deleteItem(usedWith.getId(), 1);
                player.getInventory().addItem(11696, 1);
                return;
            } else if (contains(11690, 11706, itemUsed, usedWith)) {
                player.getInventory().deleteItem(itemUsed.getId(), 1);
                player.getInventory().deleteItem(usedWith.getId(), 1);
                player.getInventory().addItem(11698, 1);
                return;
            } else if (contains(11690, 11708, itemUsed, usedWith)) {
                player.getInventory().deleteItem(itemUsed.getId(), 1);
                player.getInventory().deleteItem(usedWith.getId(), 1);
                player.getInventory().addItem(11700, 1);
                return;
            } else if (contains(11710, 11712, itemUsed, usedWith) || contains(11710, 11714, itemUsed, usedWith) || contains(11712, 11714, itemUsed, usedWith)) {
                if (!player.getInventory().containsItem(11710, 1) || !player.getInventory().containsItem(11712, 1) || !player.getInventory().containsItem(11714, 1)) {
                    player.sm("You need to have all shards to combine them.");
                    return;
                }
                player.getInventory().deleteItem(11710, 1);
                player.getInventory().deleteItem(11712, 1);
                player.getInventory().deleteItem(11714, 1);
                player.getInventory().addItem(11690, 1);
                return;
            } else if (contains(453, 18339, itemUsed, usedWith)) {
                if (player.getCoalStored() == 27) {
                    player.sm("Your coal bag can't hold more coal.");
                    return;
                }
                player.getInventory().deleteItem(fromSlot, itemUsed);
                player.addCoalStored(1);
                player.sm("You store coal in your coal bag.");
                return;
            } else if (contains(13263, 15488, itemUsed, usedWith) || contains(13263, 15490, itemUsed, usedWith)) {
                if (player.getInventory().containsItem(13263, 1) && player.getInventory().containsItem(15488, 1) && player.getInventory().containsItem(15490, 1)) {
                    player.getInventory().deleteItem(13263, 1);
                    player.getInventory().deleteItem(15488, 1);
                    player.getInventory().deleteItem(15490, 1);
                    player.getInventory().addItem(15492, 1);
                    return;
                } else {
                    player.sm("Requirements; Slayer helmet, Hexcrest, Focus sight.");
                    return;
                }
            } else if (contains(9007, 9008, itemUsed, usedWith)) {
                player.getInventory().deleteItem(itemUsed);
                player.getInventory().deleteItem(usedWith);
                player.getInventory().addItem(9009, 1);
                player.sm("The two halves of the skull fit perfectly, they appear to have a fixing point, perhaps they are to be mounted on something?");
            } else if (contains(9010, 9011, itemUsed, usedWith)) {
                player.getInventory().deleteItem(itemUsed);
                player.getInventory().deleteItem(usedWith);
                player.getInventory().addItem(9012, 1);
                player.sm("The two halves of the Sceptre fit perfectly. The Sceptre appears to be designed to have something on top.");
            } else if (contains(9012, 9009, itemUsed, usedWith)) {
                player.getInventory().deleteItem(itemUsed);
                player.getInventory().deleteItem(usedWith);
                player.getInventory().addItem(9013, 1);
                player.getPackets().sendGameMessage("The two halves fit perfectly. Successfully completing a full Skull sceptre.");
            } else if (contains(8921, 4551, itemUsed, usedWith) || contains(8921, 4166, itemUsed, usedWith) || contains(8921, 4164, itemUsed, usedWith) || contains(8921, 4168, itemUsed, usedWith)) {// mask,
                // //
                // helmet
                if (!player.getSlayerManager().hasLearnedSlayerHelmet()) {
                    player.sm("You haven't learned to create slayer helmet.");
                    return;
                }
                if (player.getSkills().getLevelForXp(Skills.CRAFTING) < 70) {
                    player.getPackets().sendGameMessage("You need an level of atleast 70 crafting to make slayer helmet");
                    return;
                }
                if (player.getInventory().containsItem(4166, 1) && // earmuffs
                        player.getInventory().containsItem(4164, 1) && // facemask
                        player.getInventory().containsItem(4168, 1) && player.getInventory().containsItem(8921, 1) && player.getInventory().containsItem(4551, 1)) {
                    player.getInventory().deleteItem(8921, 1);
                    player.getInventory().deleteItem(4551, 1);
                    player.getInventory().deleteItem(4164, 1);
                    player.getInventory().deleteItem(4168, 1);
                    player.getInventory().deleteItem(4166, 1);
                    player.getInventory().addItem(13263, 1);
                    return;
                } else {
                    player.getPackets().sendGameMessage("Requirements; Earmuffs, Facemask, Nose peg, Black mask, Spiny helmet.");
                    return;
                }
            } else if (contains(985, 987, itemUsed, usedWith)) {
                player.getInventory().deleteItem(985, 1);
                player.getInventory().deleteItem(987, 1);
                player.getInventory().addItem(989, 1);
                player.sm("You combine the two peices and create a crystal key.");
                return;

            } else if (contains(13754, 13734, itemUsed, usedWith)) {
                if (player.getSkills().getLevel(Skills.PRAYER) < 85) {
                    player.getDialogueManager().startDialogue("SimpleMessage", "You need a prayer level of at least 85 to bless this shield, try talking to the monk at the Edgeville Monastery.");
                }
                player.getInventory().deleteItem(13754, 1);
                player.getInventory().deleteItem(13734, 1);
                player.getInventory().addItem(13736, 1);
                player.sm("You bless your spirit shield with the power of the holy elixir.");
                return;
            } else if (contains(13752, 13736, itemUsed, usedWith)) {
                if (player.getSkills().getLevel(Skills.PRAYER) < 90) {
                    player.getDialogueManager().startDialogue("SimpleMessage", "You need a prayer level of at least 90 to create this shield, try talking to the monk at the Edgeville Monastery.");
                }
                player.getInventory().deleteItem(13752, 1);
                player.getInventory().deleteItem(13736, 1);
                player.getInventory().addItem(13744, 1);
                player.getPackets().sendGameMessage("You use the sigil on the shield to create an Spectral Spirit Shield.");
                return;
            } else if (contains(13746, 13736, itemUsed, usedWith)) {
                if (player.getSkills().getLevel(Skills.PRAYER) < 90) {
                    player.getDialogueManager().startDialogue("SimpleMessage", "You need a prayer level of at least 90 to create this shield, try talking to the monk at the Edgeville Monastery.");
                }
                player.getInventory().deleteItem(13746, 1);
                player.getInventory().deleteItem(13736, 1);
                player.getInventory().addItem(13738, 1);
                player.getPackets().sendGameMessage("You use the sigil on the shield to create an Arcane Spirit Shield.");
                return;
            } else if (contains(13750, 13736, itemUsed, usedWith)) {
                if (player.getSkills().getLevel(Skills.PRAYER) < 90) {
                    player.getDialogueManager().startDialogue("SimpleMessage", "You need a prayer level of at least 90 to create this shield, try talking to the monk at the Edgeville Monastery.");
                }
                player.getInventory().deleteItem(13750, 1);
                player.getInventory().deleteItem(13736, 1);
                player.getInventory().addItem(13742, 1);
                player.getPackets().sendGameMessage("You use the sigil on the shield to create an Elysian Spirit Shield.");
                return;
            } else if (contains(13748, 13736, itemUsed, usedWith)) {
                if (player.getSkills().getLevel(Skills.PRAYER) < 90) {
                    player.getDialogueManager().startDialogue("SimpleMessage", "You need a prayer level of at least 90 to create this shield, try talking to the monk at the Edgeville Monastery.");
                }
                player.getInventory().deleteItem(13748, 1);
                player.getInventory().deleteItem(13736, 1);
                player.getInventory().addItem(13740, 1);
                player.getPackets().sendGameMessage("You use the sigil on the shield to create a Divine Spirit Shield.");
                return;
            } else if (contains(4151, 21369, itemUsed, usedWith)) {
                if (!player.getSkills().hasRequirements(Skills.ATTACK, 75, Skills.SLAYER, 80)) {
                    player.sm("You need an attack level of 75 and slayer level of 80 in order to attach the whip vine to the whip.");
                    return;
                }
                player.getInventory().replaceItem(21371, 1, toSlot);
                player.getInventory().deleteItem(fromSlot, itemUsed);
                player.sm("You attach the whip vine to the abbysal whip.");
                return;
            } else
                player.sm("Nothing interesting happens.");
        }
        if (Settings.DEBUG)
            player.getPackets().sendGameMessage("itemUsedWithId:" + itemUsedWithId + ", toSlot: " + toSlot + ", interfaceId: " + interfaceId + ", interfaceId2 " + interfaceId2 + ", spellId:" + spellId + ", compId: " + compId + ", fromSlot: " + fromSlot + ", itemUsedId: " + itemUsedId);
    }

    public static void handleItemOption3(Player player, int slotId, int itemId, Item item) {
        long time = Utils.currentTimeMillis();
        if (player.getLockDelay() >= time || player.getEmotesManager().getNextEmoteEnd() >= time)
            return;
        player.stopAll(false);
        ItemPlugin plugin = ItemPluginLoader.getPlugin(item);
        if (plugin != null) {
            boolean pluginExecuted = plugin.processItem3(player, item, slotId);
            if (!pluginExecuted) {
                Logger.log("ItemPlugin", "Option 3 - Class: " + plugin.getClass().getSimpleName() + ".java, Failed: " + item.getName() + "(" + item.getId() + ") plugin does not have this option.");
            }
            if (pluginExecuted) {
                Logger.log("ItemPlugin", "Option 3 - Class: " + plugin.getClass().getSimpleName() + ".java, Executed: " + item.getName() + "(" + item.getId() + ")");
                return;
            }
        }
        Hunter.FlyingEntities impling = Hunter.FlyingEntities.forItemId(itemId);
        if (impling != null) {
            Hunter.openJar(player, slotId, new Item(itemId), impling);
            return;
        }
        if (itemId == 20767 || itemId == 20769 || itemId == 20771)
            SkillCapeCustomizer.startCustomizing(player, itemId);
        if (itemId >= 18349 && itemId <= 18363) {
            player.getCharges().checkPercentage("Your " + ItemDefinitions.getItemDefinitions(itemId).getName() + " has " + ChargesManager.REPLACE + "% charges left.", itemId, false);
        } else if (itemId == 9013) {
            if (player.getSkullSkeptreCharges() == 1) {
                player.getInventory().deleteItem(slotId, item);
                player.getAppearence().generateAppearenceData();
                player.setSkullSkeptreCharges(5);
                player.sm("You have no more charges, the sceptre crumbled to dust.");
                player.animate(new Animation(9601));
                player.animate(new Animation(9601));
                player.gfx(new Graphics(94));
                WorldTasksManager.schedule(new WorldTask() {
                    @Override
                    public void run() {
                        Magic.sendSkullSceptreTeleport(player, false, 4731, -1, 2, new WorldTile(3081, 3421, 0));
                    }
                }, 2);
                return;
            }
            player.setSkullSkeptreCharges(player.getSkullSkeptreCharges() - 1);
            player.sm("You have " + player.getSkullSkeptreCharges() + " charges left.");
            player.animate(new Animation(9601));
            player.gfx(new Graphics(94));
            WorldTasksManager.schedule(new WorldTask() {
                @Override
                public void run() {
                    Magic.sendSkullSceptreTeleport(player, false, 4731, -1, 2, new WorldTile(3081, 3421, 0));
                }
            }, 2);
        } else if (itemId >= 15048 && itemId <= 15054) {
            player.getDialogueManager().startDialogue("MagicSkullball", 15046, slotId);
            return;
        } else if (itemId == 11283)
            player.sm("Your dragonfire shield has " + player.getDfsCharges() + " charges.");
        else if (itemId == 11284)
            player.sm("Your dragonfire shield is not charged.");
        else if (itemId >= 15084 && itemId <= 15100)
            player.getDialogueManager().startDialogue("DiceBag", itemId);
        else if (itemId == 24437 || itemId == 24439 || itemId == 24440 || itemId == 24441)
            player.getDialogueManager().startDialogue("FlamingSkull", item, slotId);
        else if (itemId >= 20801 && itemId <= 20806) {
            player.getDialogueManager().startDialogue("WildStalkerHelmet");
            return;
        } else if (itemId >= 13561 && itemId <= 13562 || itemId == 19760) {
            player.sm("Run-replenish here.");
        } else if (itemId >= 20795 && itemId <= 20800) {
            player.getDialogueManager().startDialogue("DuellistCap");
            return;
        } else if (itemId == 23659) {
            player.getInventory().deleteItem(23659, 1);
            player.getInventory().addItem(24876, 1);
            return;
        } else if (itemId == 24876) {
            player.getInventory().deleteItem(24876, 1);
            player.getInventory().addItem(23659, 1);
            return;
        } else if (Equipment.getItemSlot(itemId) == Equipment.SLOT_AURA)
            player.getAuraManager().sendTimeRemaining(itemId);
        else if (itemId == 18339) {
            if (player.getCoalStored() == 0) {
                player.sm("You don't have any coal ores stored.");
                return;
            }
            if (!player.getInventory().hasFreeSlots()) {
                player.sm("You don't have enough inventory space.");
                return;
            }
            player.removeCoalStored(1);
            player.getInventory().addItem(453, 1);
            player.sm("You withraw one coal ore.");
        } else if (itemId == 19335) {
            if (player.getInventory().getFreeSlots() > 0) {
                player.getInventory().replaceItem(6585, 1, slotId);
                player.getInventory().addItem(19333, 1);
            } else {
                player.sm("You don't have enough inventory space left.");
                return;
            }
        } else if (itemId == 19336) {
            if (player.getInventory().getFreeSlots() > 0) {
                player.getInventory().replaceItem(11335, 1, slotId);
                player.getInventory().addItem(19346, 1);
            } else {
                player.sm("You don't have enough inventory space left.");
                return;
            }
        } else if (itemId == 19337) {
            if (player.getInventory().getFreeSlots() > 0) {
                player.getInventory().replaceItem(14479, 1, slotId);
                player.getInventory().addItem(19350, 1);
            } else {
                player.sm("You don't have enough inventory space left.");
                return;
            }
        } else if (itemId == 19338) {
            if (player.getInventory().getFreeSlots() > 0) {
                player.getInventory().replaceItem(4087, 1, slotId);
                player.getInventory().addItem(19348, 1);
            } else {
                player.sm("You don't have enough inventory space left.");
                return;
            }
        } else if (itemId == 19339) {
            if (player.getInventory().getFreeSlots() > 0) {
                player.getInventory().replaceItem(4585, 1, slotId);
                player.getInventory().addItem(19348, 1);
            } else {
                player.sm("You don't have enough inventory space left.");
                return;
            }
        } else if (itemId == 19340) {
            if (player.getInventory().getFreeSlots() > 0) {
                player.getInventory().replaceItem(1187, 1, slotId);
                player.getInventory().addItem(19352, 1);
            } else {
                player.sm("You don't have enough inventory space left.");
                return;
            }

        } else if (itemId == 19341) {// sp helm
            if (player.getInventory().getFreeSlots() > 0) {
                player.getInventory().replaceItem(11335, 1, slotId);
                player.getInventory().addItem(19354, 1);
            } else {
                player.sm("You don't have enough inventory space left.");
                return;
            }
        } else if (itemId == 19342) {// sp platebody
            if (player.getInventory().getFreeSlots() > 0) {
                player.getInventory().replaceItem(14479, 1, slotId);
                player.getInventory().addItem(19358, 1);
            } else {
                player.sm("You don't have enough inventory space left.");
                return;
            }
        } else if (itemId == 19343) {// sp legs
            if (player.getInventory().getFreeSlots() > 0) {
                player.getInventory().replaceItem(4087, 1, slotId);
                player.getInventory().addItem(19356, 1);
            } else {
                player.sm("You don't have enough inventory space left.");
                return;
            }
        } else if (itemId == 19344) {// sp legs
            if (player.getInventory().getFreeSlots() > 0) {
                player.getInventory().replaceItem(4585, 1, slotId);
                player.getInventory().addItem(19356, 1);
            } else {
                player.sm("You don't have enough inventory space left.");
                return;
            }
        } else if (itemId == 19345) {// sp shield
            if (player.getInventory().getFreeSlots() > 0) {
                player.getInventory().replaceItem(1187, 1, slotId);
                player.getInventory().addItem(19360, 1);
            } else {
                player.sm("You don't have enough inventory space left.");
                return;
            }
        }
        }

    public static void handleItemOption4(Player player, int slotId, int itemId, Item item) {
        ItemPlugin plugin = ItemPluginLoader.getPlugin(item);
        if (plugin != null) {
            boolean pluginExecuted = plugin.processItem4(player, item, slotId);
            if (!pluginExecuted) {
                Logger.log("ItemPlugin", "Option 4 - Class: " + plugin.getClass().getSimpleName() + ".java, Failed: " + item.getName() + "(" + item.getId() + ") plugin does not have this option.");
            }
            if (pluginExecuted) {
                Logger.log("ItemPlugin", "Option 4 - Class: " + plugin.getClass().getSimpleName() + ".java, Executed: " + item.getName() + "(" + item.getId() + ")");
                return;
            }
        }
       }

    public static void handleItemOption5(Player player, int slotId, int itemId, Item item) {
        ItemPlugin plugin = ItemPluginLoader.getPlugin(item);
        if (plugin != null) {
            boolean pluginExecuted = plugin.processItem5(player, item, slotId);
            if (!pluginExecuted) {
                Logger.log("ItemPlugin", "Option 5 - Class: " + plugin.getClass().getSimpleName() + ".java, Failed: " + item.getName() + "(" + item.getId() + ") plugin does not have this option.");
            }
            if (pluginExecuted) {
                Logger.log("ItemPlugin", "Option 5 - Class: " + plugin.getClass().getSimpleName() + ".java, Executed: " + item.getName() + "(" + item.getId() + ")");
                return;
            }
        }
        }

    public static void handleItemOption6(Player player, int slotId, int itemId, Item item) {
        long time = Utils.currentTimeMillis();
        if (player.getLockDelay() >= time || player.getEmotesManager().getNextEmoteEnd() >= time)
            return;
        player.stopAll(false);
        ItemPlugin plugin = ItemPluginLoader.getPlugin(item);
        if (plugin != null) {
            boolean pluginExecuted = plugin.processItem6(player, item, slotId);
            if (!pluginExecuted) {
                Logger.log("ItemPlugin", "Option 6 - Class: " + plugin.getClass().getSimpleName() + ".java, Failed: " + item.getName() + "(" + item.getId() + ") plugin does not have this option.");
            }
            if (pluginExecuted) {
                Logger.log("ItemPlugin", "Option 6 - Class: " + plugin.getClass().getSimpleName() + ".java, Executed: " + item.getName() + "(" + item.getId() + ")");
                return;
            }
        }
        Pouch pouch = Pouch.forId(itemId);
        Pot pot = Pots.getPot(item.getId());
        if (pot != null) {
            player.sm("You empty the contents of the " + item.getName().replace(" (4)", "").replace(" (3)", "").replace(" (2)", "").replace(" (1)", "") + " on the floor.");
            player.getInventory().getItem(slotId).setId(229);
            player.getInventory().refresh();
            return;
        }
        if (itemId == 20801) {
            if (player.getKillCount() < 10) {
                player.sm("You can't change the look of your wildstalker helmet until you earned additional tiers.");
                player.sm("You need at least ten wilderness kills.");
                return;
            } else if (player.getKillCount() > 99) {
                player.getDialogueManager().startDialogue("WildStalkerTier1");
                return;
            }
            player.getInventory().deleteItem(itemId, 1);
            player.getInventory().addItem(itemId + 1, 1);
        } else if (itemId == 1921) {
            player.getInventory().getItems().set(slotId, new Item(1923));
            player.getInventory().refresh();
            player.sm("You empty the bowl.");
        } else if (itemId == 1937) {
            player.getInventory().getItems().set(slotId, new Item(1935));
            player.getInventory().refresh();
            player.sm("You empty the jug.");
        } else if (itemId == 227) {
            player.getInventory().getItems().set(slotId, new Item(229));
            player.getInventory().refresh();
            player.sm("You empty the vial.");
        } else if (itemId == 1929 || itemId == 1927) {
            player.getInventory().getItems().set(slotId, new Item(1925));
            player.getInventory().refresh();
            player.sm("You empty the bucket.");
        } else if (itemId == 9013) {
            player.sm("You have " + player.getSkullSkeptreCharges() + " charges left.");
        } else if (itemId >= 15048 && itemId <= 15054) {
            player.getInventory().getItems().set(slotId, new Item(15046));
            player.getInventory().refresh();
            player.sm("You reset the magic skullball.");
        } else if (Toolbelt.checkStorage(itemId)) {
            if (Settings.FREE_TO_PLAY && ItemDefinitions.getItemDefinitions(itemId).isMembersOnly()) {
                player.sm("You can't add members items to toolbelt in free to play.");
                return;
            }
            player.getToolbelt().addItem(new Item(itemId));
            return;
        } else if (itemId == 20802) {
            if (player.getKillCount() < 100) {
                player.getInventory().deleteItem(itemId, 1);
                player.getInventory().addItem(itemId - 1, 1);
                return;
            }
            player.getDialogueManager().startDialogue("WildStalkerTier2");
            return;
        } else if (itemId == 20803) {
            player.getDialogueManager().startDialogue("WildStalkerTier3");
            return;
        } else if (itemId == 20804) {
            player.getDialogueManager().startDialogue("WildStalkerTier4");
            return;
        } else if (itemId == 20805) {
            player.getDialogueManager().startDialogue("WildStalkerTier5");
            return;
        } else if (itemId == 20806) {
            player.getDialogueManager().startDialogue("WildStalkerTier6");
            return;
        } else if (itemId == 20795) {
            if (player.getDuelkillCount() < 10) {
                player.sm("You can't change the look of your duellists' cap until you earned additional tiers.");
                player.sm("You need at least ten duel arena kills.");
                return;
            } else if (player.getDuelkillCount() > 99) {
                player.getDialogueManager().startDialogue("DuellistTier1");
                return;
            }
            player.getInventory().deleteItem(itemId, 1);
            player.getInventory().addItem(itemId + 1, 1);
        } else if (itemId == 20796) {
            if (player.getDuelkillCount() < 100) {
                player.getInventory().deleteItem(itemId, 1);
                player.getInventory().addItem(itemId - 1, 1);
                return;
            }
            player.getDialogueManager().startDialogue("DuellistTier2");
            return;
        } else if (itemId == 20797) {
            player.getDialogueManager().startDialogue("DuellistTier3");
            return;
        } else if (itemId == 20798) {
            player.getDialogueManager().startDialogue("DuellistTier4");
            return;
        } else if (itemId == 20799) {
            player.getDialogueManager().startDialogue("DuellistTier5");
            return;
        } else if (itemId == 20800) {
            player.getDialogueManager().startDialogue("DuellistTier6");
            return;
        } else if (pouch != null) {
            if (player.getFamiliarDelay() > Utils.currentTimeMillis()) {
                player.getPackets().sendGameMessage("You gotta wait 5 seconds before spawning a familiar after dissmissing.");
                return;
            }
            if (pouch != null)
                Summoning.spawnFamiliar(player, pouch);
            return;
        } else if (itemId == 18339) {
            if (player.getCoalStored() == 0) {
                player.sm("You don't have any coal ores stored.");
                return;
            }
            if (!player.getInventory().hasFreeSlots()) {
                player.sm("You don't have enough inventory space.");
                return;
            }
            if (player.getCoalStored() >= player.getInventory().getFreeSlots()) {
                player.removeCoalStored(player.getInventory().getFreeSlots());
                player.getInventory().addItem(453, player.getInventory().getFreeSlots());
            }
        } else if (itemId == 1438)
            Talisman.locate(player, 3127, 3405);
        else if (itemId == 11283) {
            player.setDfsCharges(0);
            player.sm("You empty your dragonfire shield charges.");
            player.getInventory().deleteItem(slotId, new Item(11283, 1));
            player.getInventory().addItem(11284, 1);
        } else if (itemId == 1440)
            Talisman.locate(player, 3306, 3474);
        else if (itemId == 1442)
            Talisman.locate(player, 3313, 3255);
        else if (itemId == 1444)
            Talisman.locate(player, 3185, 3165);
        else if (itemId == 1446)
            Talisman.locate(player, 3053, 3445);
        else if (itemId == 1448)
            Talisman.locate(player, 2982, 3514);
        else if (itemId == 1454)
            Talisman.locate(player, 2407, 4376);
        else if (itemId == 1452)
            Talisman.locate(player, 3059, 3590);
        else if (itemId == 1462)
            Talisman.locate(player, 2868, 3018);
        else if (itemId == 1456)
            Talisman.locate(player, 1860, 4638);
        else if (itemId == 13263) {
            if (player.getInventory().getFreeSlots() > 3) {
                player.getInventory().deleteItem(13263, 1);
                player.getInventory().addItem(8921, 1);
                player.getInventory().addItem(4551, 1);
                player.getInventory().addItem(4164, 1);
                player.getInventory().addItem(4166, 1);
                player.getInventory().addItem(4168, 1);
                player.sm("You disassemble your Slayer headgear.");
            } else {
                player.sm("You don't have enough inventory space.");
                return;
            }
        } else if (itemId == 15492) {
            if (player.getInventory().getFreeSlots() > 2) {
                player.getInventory().deleteItem(15492, 1);
                player.getInventory().addItem(13263, 1);
                player.getInventory().addItem(15488, 1);
                player.getInventory().addItem(15490, 1);
                player.sm("You disassemble your Slayer headgear.");
            } else {
                player.sm("You don't have enough inventory space.");
                return;
            }
        } else if (itemId == 995) {
            if (player.isAtWild() || FfaZone.inRiskArea(player)) {
                player.sm("You can't store money in your money pouch in the " + (player.isAtWild() ? "wilderness." : "risk ffa zone."));
                return;
            }
            player.getMoneyPouch().addMoneyFromInventory(player.getInventory().getItems().getNumberOf(995), true);
        } else if (itemId >= 1706 && itemId <= 1712 || itemId >= 10354 && itemId <= 10360) {
            player.getDialogueManager().startDialogue("Transportation", "Edgeville", new WorldTile(3087, 3496, 0), "Forinthry Dungeon", new WorldTile(3081, 3648, 0), "Draynor Village", new WorldTile(3105, 3251, 0), "Al Kharid", new WorldTile(3293, 3163, 0), itemId);
        } else if (itemId <= 1712 && itemId >= 1706) {
            player.getDialogueManager().startDialogue("Transportation", "Edgeville", new WorldTile(3087, 3496, 0), "Forinthry Dungeon", new WorldTile(3081, 3648, 0), "Draynor Village", new WorldTile(3105, 3251, 0), "Al Kharid", new WorldTile(3293, 3163, 0), itemId);
        } else if (itemId == 1704)
            player.sm("The amulet has ran out of charges. You need to recharge it if you wish it use it once more.");
        else if (itemId >= 3853 && itemId <= 3867) {
            player.getDialogueManager().startDialogue("Transportation", "Burthrope Games Room", new WorldTile(2880, 3559, 0), "Barbarian Outpost", new WorldTile(2519, 3571, 0), "Gamers' Grotto", new WorldTile(2970, 9679, 0), "Corporeal Beast", new WorldTile(2966, 4383, 2), itemId);
        } else if (itemId >= 2552 && itemId <= 2566) {
            player.getDialogueManager().startDialogue("Transportation", "Duel Arena", new WorldTile(3367, 3267, 0), "Castle Wars", new WorldTile(2443, 3088, 0), "Mobilising Armies", new WorldTile(2412, 2849, 0), "Pest Control", new WorldTile(2662, 2653, 0), itemId);
        }
        }

    public static void handleItemOption7(Player player, int slotId, int itemId, Item item) {
        String name = ItemDefinitions.getItemDefinitions(itemId).getName().toLowerCase();
        long time = Utils.currentTimeMillis();
        int amount = player.getInventory().getNumberOf(item.getId());
        if (player.getLockDelay() >= time || player.getEmotesManager().getNextEmoteEnd() >= time)
            return;
        if (!player.getControlerManager().canDropItem(item))
            return;
        player.stopAll(false);
        ItemPlugin plugin = ItemPluginLoader.getPlugin(item);
        if (plugin != null) {
            boolean pluginExecuted = plugin.processDrop(player, item, slotId);
            if (!pluginExecuted) {
                Logger.log("ItemPlugin", "Drop - Class: " + plugin.getClass().getSimpleName() + ".java, Failed: " + item.getName() + "(" + item.getId() + ") plugin does not have this option.");
            }
            if (pluginExecuted) {
                Logger.log("ItemPlugin", "Drop - Class: " + plugin.getClass().getSimpleName() + ".java, Executed: " + item.getName() + "(" + item.getId() + ")");
                return;
            }
        }
        if (player.getPetManager().spawnPet(itemId, true)) {
            return;
        }
        if (item.getDefinitions().isOverSized() || item.getName().contains("null")) {
            player.sm("The item appears to be oversized.");
            player.getInventory().deleteItem(item);
            return;
        }
        if (GodCapes.handleCape(player, item))
            return;
        if (item.getDefinitions().isDestroyItem() || item.getId() == 9952) {
            player.getDialogueManager().startDialogue("DestroyItemOption", slotId, item);
            return;
        }
        if (!name.contains(" 0")) {
            if (name.contains("verac") || name.contains("dharok") || name.contains("ahrim") || name.contains("karil") || name.contains("guthan") || name.contains("torag")) {
                player.getDialogueManager().startDialogue("BarrowDrop", slotId, item);
                return;
            }
        }
        if ((item.getDefinitions().getTipitPrice() * amount >= 1000000)) {
            player.getDialogueManager().startDialogue("HighValueOption", slotId, item);
            return;
        }
        if (itemId == 4045) {
            if (player.getHitpoints() == 0)
                return;
            player.animate(new Animation(827));
            player.applyHit(new Hit(player, 250, HitLook.REGULAR_DAMAGE));
            player.setNextForceTalk(new ForceTalk("Oww!!"));
            player.getInventory().deleteItem(item);
            return;
        }
        if (itemId == 703) {
            if (player.getHitpoints() == 0)
                return;
            player.animate(new Animation(827));
            player.applyHit(new Hit(player, 550, HitLook.REGULAR_DAMAGE));
            player.setNextForceTalk(new ForceTalk("Oww!!"));
            player.getInventory().deleteItem(item);
            return;
        }
        player.getCharges().degrade(item);
        player.getInventory().dropItem(slotId, item, true);
    }

    public static void handleItemOption8(Player player, int slotId, int itemId, Item item) {
        player.getInventory().sendExamine(slotId);
       }

    private static boolean isFarmingItem(Item item) {
        // if (defs.getName().toLowerCase().contains("grimy"))
        // return true;
        for (ProductInfo infos : ProductInfo.values()) {
            if (infos.isProduct(item)) {
                return true;
            }
        }
        // if (item.getId() == 1942 || item.getId() == 1982)
        // return true;
        return false;
    }

    public static void handleItemOnNPC(final Player player, final NPC npc, final Item item) {
        if (item == null)
            return;
        player.stopAll(false);
        player.faceEntity(npc);
        NpcPlugin plugin = NpcPluginLoader.getPlugin(npc);
        if (plugin != null) {
            player.setRouteEvent(new RouteEvent(npc, new Runnable() {
                @Override
                public void run() {
                    npc.resetWalkSteps();
                    npc.faceEntity(player);
                    player.stopAll();
                    player.faceEntity(npc);
                    boolean pluginExecuted = plugin.processNpc4(player, npc);
                    if (!pluginExecuted) {
                        Logger.log("ItemPlugin", "ItemOnNpc - Class: " + plugin.getClass().getSimpleName() + ".java, Failed: " + item.getName() + "(" + item.getId() + ") > " + npc.getName() + "(" + npc.getId() + ") does not meet the requirements");
                    }
                    if (pluginExecuted) {
                        Logger.log("ItemPlugin", "ItemOnNpc - Class: " + plugin.getClass().getSimpleName() + ".java, Executed: " + item.getName() + "(" + item.getId() + ") > " + npc.getName() + "(" + npc.getId() + ") does meet the requirements");
                        return;
                    }
                }
            }, true));
        }
        player.setRouteEvent(new RouteEvent(npc, new Runnable() {
            @Override
            public void run() {
                if (npc instanceof Block) {
                    Block block = (Block) npc;
                    if (!block.useItem(player, item)) {
                        return;
                    }
                }
                if (!player.getInventory().containsItem(item.getId(), item.getAmount())) {
                    return;
                }
                if (!player.getControlerManager().processItemOnNPC(npc, item)) {
                    player.sm(item.getName() + " on " + npc.getName());
                    return;
                }
                if (npc.getId() == 519) {
                    ArmourRepair.repairItem(player, item, false);
                    return;
                }
                if (npc.getId() == 11460 && item.getId() == 617) {
                    if (!player.isMember()) {
                        player.getDialogueManager().startDialogue("SimpleItemDialogue", 617, 1, "Richard likes what you offered him. In return he has given a gift of 1 Month of Avalon Membership!");
                        return;
                    } else {
                        player.sm("Richard isn't interesting in trading right now.");
                        return;
                    }
                }
                if (npc.getId() == 9711) {
                    boolean wrongItem = true;
                    for (ItemStore data : ItemStore.values()) {
                        if (data == null)
                            continue;
                        if (data.getDegradedItem() != null && data.getDegradedItem().getId() == item.getId() && data.getHits() == 30000) {
                            player.getDialogueManager().startDialogue("RewardsTrader", npc.getId(), item.getId(), 2);
                            return;
                        }
                        if (data.getCurrentItem().getId() == item.getId() && data.getHits() == 30000) {
                            player.sm("Degraded Item: " + data.getDegradedItem().getName());
                            wrongItem = false;
                            if (player.getCharges().getCharges(item.getId()) == data.getHits()) {
                                player.getDialogueManager().startDialogue("RewardsTrader", npc.getId(), item.getId(), 3);
                                return;
                            }
                            player.getDialogueManager().startDialogue("RewardsTrader", npc.getId(), item.getId(), 2);
                            return;
                        }
                    }
                    if (wrongItem) {
                        player.getDialogueManager().startDialogue("RewardsTrader", npc.getId(), item.getId(), 4);
                        return;
                    }
                }
                if (npc instanceof Familiar) {
                    Familiar familiar = (Familiar) npc;
                    if (familiar != player.getFamiliar()) {
                        player.sm("This isn't your familiar.");
                        return;
                    }
                    if (npc.getId() == 519 && (item.getId() == 4980)) {
                        player.getDialogueManager().startDialogue("Bob", npc.getId());
                        return;
                    }
                }
                if (npc.getName().toLowerCase().contains("tool leprechaun")) {
                    if (isFarmingItem(item)) {
                        int amount = player.getInventory().getAmountOf(item.getId());
                        player.getInventory().deleteItem(item.getId(), amount);
                        player.getInventory().addItem(ItemDefinitions.getItemDefinitions(item.getId()).getCertId(), amount);
                        player.sm("Tool leprechaun noted your " + amount + " " + ItemDefinitions.getItemDefinitions(item.getId()).getName() + ".");
                        return;
                    } else {
                        player.sm("You can't note this item.");
                        return;
                    }
                }
                if (npc.getId() == 4493 && item.getId() == 286) {
                    if (player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getStage() > 0) {
                        player.getDialogueManager().startDialogue("GeneralBentnoze", 4);
                        return;
                    }
                }
                if (npc.getId() == 4494 && item.getId() == 286) {
                    if (player.getQuestManager().get(Quests.GOBLIN_DIPLOMACY).getStage() > 0) {
                        player.getDialogueManager().startDialogue("GeneralWartface", 4);
                        return;
                    }
                }

                for (Artefacts artefacts : Artefacts.values()) {
                    if (npc.getId() == 6537 && item.getId() == artefacts.getId()) {
                        WildernessArtefacts.useOnMandrith(player);
                        return;
                    }
                }
                if (npc.getId() == 8635) {
                    npc.faceEntity(player);
                    GilesBusiness.unnote(player, item.getId());
                    return;
                }
                if (npc.getId() == 747) {
                    player.getDialogueManager().startDialogue("TrimArmourD", item.getId());
                    return;
                }
                if (npc.getId() == 6873 || npc.getId() == 6874 || npc.getId() == 6815 || npc.getId() == 6816 || npc.getId() == 6794 || npc.getId() == 6795) {
                    player.faceEntity(npc);
                    player.getFamiliar().getBob().addItem(item);
                    return;
                }
                if (npc instanceof Pet) {
                    player.faceEntity(npc);
                    player.getPetManager().eat(item.getId(), (Pet) npc);
                    return;
                }
                player.sm("Nothing interesting happens.");
            }
        }));
    }
}
