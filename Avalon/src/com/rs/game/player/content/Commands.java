package com.rs.game.player.content;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import com.rs.Settings;
import com.rs.cache.loaders.ClientScriptMap;
import com.rs.cache.loaders.GeneralRequirementMap;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.cache.loaders.ObjectDefinitions;
import com.rs.game.Animation;
import com.rs.game.ForceMovement;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.Hit;
import com.rs.game.Hit.HitLook;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.item.itemdegrading.ArmourRepair;
import com.rs.game.minigames.lividfarm.LividFarmControler;
import com.rs.game.minigames.lividfarm.LividStore.Spell;
import com.rs.game.minigames.pest.CommendationExchange;
import com.rs.game.npc.NPC;
import com.rs.game.objects.ObjectScriptsHandler;
import com.rs.game.player.AccountCreation;
import com.rs.game.player.Player;
import com.rs.game.player.Ranks.Rank;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.combat.Magic;
import com.rs.game.player.actions.skills.construction.ConstructorsOutfit;
import com.rs.game.player.actions.skills.summoning.Summoning;
import com.rs.game.player.actions.skills.summoning.Summoning.Pouch;
import com.rs.game.player.content.WildernessArtefacts.Artefacts;
import com.rs.game.player.content.customshops.CustomStoreData;
import com.rs.game.player.content.dungeoneering.DungeonConstants;
import com.rs.game.player.content.dungeoneering.DungeonManager;
import com.rs.game.player.content.dungeoneering.DungeonPartyManager;
import com.rs.game.player.content.grandexchange.GrandExchange;
import com.rs.game.player.content.grandexchange.GrandExchangeManager;
import com.rs.game.player.content.randomevent.AntiBot;
import com.rs.game.player.controlers.EdgevillePvPControler;
import com.rs.game.player.dialogues.LevelUp;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.net.decoders.handlers.ButtonHandler;
import com.rs.utils.EconomyPrices;
import com.rs.utils.Encrypt;
import com.rs.utils.IPBanL;
import com.rs.utils.Logger;
import com.rs.utils.Utils;

public final class Commands {

    /**
     * returns if command was processed
     */

    public static boolean processCommand(Player player, String command, boolean console, boolean clientCommand) {
        if (command.length() == 0)
            return false;
        String[] cmd = command.toLowerCase().split(" ");
        if (cmd.length == 0)
            return false;
        if (player.getPlayerRank().getRank()[0] == Rank.DEVELOPER
                && (processAdminCommands(player, cmd, console, clientCommand))) {
            archiveLogs(player, cmd);
            return true;
        }
        if ((player.getPlayerRank().getRank()[0] == Rank.MODERATOR
                || player.getPlayerRank().getRank()[0] == Rank.DEVELOPER)
                && (processModCommand(player, cmd, console, clientCommand))) {
            archiveLogs(player, cmd);
            return true;
        }
        if (player.isStaff() && processSupportCommands(player, cmd, console, clientCommand)) {
            archiveLogs(player, cmd);
            return true;
        }
        if (Settings.ECONOMY) {
            player.getPackets().sendGameMessage("You can't use any commands in economy mode!");
            return true;
        }
        return processNormalCommand(player, cmd, console, clientCommand);
    }

    public static boolean processNormalCommand(final Player player, String[] cmd, boolean console,
                                               boolean clientCommand) {
        if (clientCommand) {
        } else {
            String name;
            switch (cmd[0]) {
                case "flowertest":
                    String[] flowers = {"red", "blue", "yellow", "orange", "pastel", "rainbow"};
                    Map<String, Integer> map1 = new HashMap<String, Integer>();
                    Map<String, Integer> map2 = new HashMap<String, Integer>();
                    String flowerType = null;
                    String flowerType2 = null;
                    for (int i = 0; i < flowers.length; i++) {
                        map1.put(flowers[i], 0);
                        map2.put(flowers[i], 0);
                    }
                    for (int i = 1; i <= 5; i++) {
                        flowerType = flowers[Utils.getRandom(flowers.length)];
                        flowerType2 = flowers[Utils.getRandom(flowers.length)];
                        map1.put(flowerType, map1.get(flowerType).intValue() + 1);
                        map2.put(flowerType2, map2.get(flowerType2).intValue() + 1);
                        System.out.println("flowerType: " + flowerType + ", flowerType2: " + flowerType2);
                    }
                    for (Map.Entry<String, Integer> flowa : map1.entrySet()) {
                        System.out.println(flowa.getKey() + " - " + flowa.getValue());
                    }
                    StringBuilder builder = new StringBuilder();
                    StringBuilder builder2 = new StringBuilder();

                    WorldTasksManager.schedule(new WorldTask() {

                        int index;

                        @Override
                        public void run() {
                            if (index >= 5) {
                                stop();
                                return;
                            }
                            builder.append(map1.keySet().toArray()[index].toString() + " - ");
                            builder2.append(map2.keySet().toArray()[index].toString() + " - ");
                            index++;
                            System.out.println("name1 Flowers: " + builder.toString() + "\nname2 Flowers: " + builder2.toString() + "");
                        }
                    }, 0, 2);
                    return true;
                case "store":
                    player.getPackets().sendOpenURL("http://avalonrsps718.everythingrs.com/services/store");
                    return true;
                case "highscores":
                    player.getPackets().sendOpenURL("https://avalon-rsps.com/highscores/");
                    return true;
                case "webge":
                case "ge":
                    player.getPackets().sendOpenURL("https://avalon-rsps.com/grand-exchange/");
                    return true;
                case "vote":
                    player.getPackets().sendOpenURL(Settings.VOTE_LINK);
                    return true;
                case "website":
                case "forum":
                case "forums":
                    player.getPackets().sendOpenURL(Settings.WEBSITE_LINK);
                    return true;
                case "update":
                case "updates":
                    player.getPackets().sendOpenURL(Settings.UPDATE_LINK);
                    return true;
                case "voted":
                    if (cmd.length == 1) {
                        player.getPackets().sendGameMessage("Please use [::reward id], [::reward id amount], or [::reward id all].");
                        return true;
                    }
                    //TODO: check for votepoints >= 10 give box, and else give points per vote
                    player.sm("Checking your vote statistics..");
                    final String playerName = player.getUsername();
                    final String idToString = cmd[1];
                    final String rewardAmount = cmd.length == 3 ? cmd[2] : "1";
                    com.everythingrs.vote.Vote.service.execute(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                com.everythingrs.vote.Vote[] reward = com.everythingrs.vote.Vote.reward("JkQT2VoUwdun6IyLu2xk0lc7fOH4RV077Gc5g6hUpwA6Q2E5Yaxxu24tQt86i4B26RbIGl40", playerName, idToString, rewardAmount);
                                if (reward[0].message != null) {
                                    player.getPackets().sendGameMessage(reward[0].message);
                                    return;
                                }
                                player.getInventory().addItem(new Item(reward[0].reward_id, reward[0].give_amount));
                                player.getPackets().sendGameMessage("Thank you for voting! You now have " + reward[0].vote_points + " vote points.");
                            } catch (Exception e) {
                                player.getPackets().sendGameMessage("Api Services are currently offline. Please check back shortly");
                                e.printStackTrace();
                            }
                        }

                    });
                    return true;
                case "claimshop":
                    new java.lang.Thread() {
                        public void run() {
                            try {
                                com.everythingrs.donate.Donation[] donations = com.everythingrs.donate.Donation.donations("JkQT2VoUwdun6IyLu2xk0lc7fOH4RV077Gc5g6hUpwA6Q2E5Yaxxu24tQt86i4B26RbIGl40", player.getUsername());
                                if (donations.length == 0) {
                                    player.getPackets().sendGameMessage("You currently don't have any items waiting. You must donate first!");
                                    return;
                                }
                                if (donations[0].message != null) {
                                    player.getPackets().sendGameMessage(donations[0].message);
                                }
                                for (com.everythingrs.donate.Donation donate : donations) {
                                    player.getInventory().addItem(donate.product_id, donate.product_amount);
//								player.donatedAmount += donate.product_price * donate.amount_purchased;
                                }
                                player.getPackets().sendGameMessage("Thank you for donating! ");
                            } catch (Exception e) {
                                player.sm("Api Services are currently offline. Please check back shortly");
                            }
                        }
                    }.start();
                    return true;
                case "claim":
                    ButtonHandler.refreshUntradeables(player);
                    return true;
                case "youtube":
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    player.getPackets().sendOpenURL("www.youtube.com/results?search_query=" + name + "&sm=3");
                    return true;
                case "channel":
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    player.getPackets().sendOpenURL("https://www.youtube.com/user/" + name);
                    return true;
                case "geoffers":
                    GrandExchange.sendOfferTracker(player);
                    return true;
                case "yell":
                    String data = "";
                    for (int i = 1; i < cmd.length; i++) {
                        data += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    ServerMessage.filterMessage(player, Utils.fixChatMessage(data), false);
                    archiveYell(player, Utils.fixChatMessage(data));
                    return true;
                case "mb":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2539, 4716, 0));
                    player.getPackets().sendGameMessage("You have teleported to mage bank.");
                    return true;

                case "clanwars":
                case "cw":
                case "clws":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2970, 9679, 0));
                    player.getPackets().sendGameMessage("You have teleported to clan wars.");
                    return true;

                case "home":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    if (player.isInCombat(10000)) {
                        player.getPackets().sendGameMessage("You can't teleport out of combat.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(Settings.HOME_PLAYER_LOCATION));
                    player.getPackets().sendGameMessage("You have teleported home.");
                    return true;
                case "east":
                case "easts":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3349, 3647, 0));
                    player.getPackets().sendGameMessage("You have teleported to east dragons.");
                    return true;

                case "edgeville":
                case "edge":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3082, 3545, 0));
                    player.getPackets().sendGameMessage("You have teleported to edgeville.");
                    return true;

                case "gdz":
                case "gds":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3289, 3886, 0));
                    player.getPackets().sendGameMessage("You have teleported to greater demons.");
                    return true;

                case "kbd":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3032, 3836, 0));
                    player.getPackets().sendGameMessage("You have teleported outside king black dragon lair.");
                    return true;

                case "44ports":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2980, 3867, 0));
                    player.getPackets().sendGameMessage("You have teleported lvl 44 wilderness port.");
                    return true;

                case "iceplatue":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2962, 3918, 0));
                    player.getPackets().sendGameMessage("You have teleported to ice platue.");
                    return true;

                case "50ports":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3308, 3916, 0));
                    player.getPackets().sendGameMessage("You have teleported to lvl 50 wilderness portal.");
                    return true;

                case "castle":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3012, 3631, 0));
                    player.getPackets().sendGameMessage("You have teleported to castle.");
                    return true;

                case "bh":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3153, 3709, 0));
                    player.getPackets().sendGameMessage("You have teleported to bounty hunter crater.");
                    return true;

                case "altar":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2950, 3821, 0));
                    player.getPackets().sendGameMessage("You have teleported to wilderness altar.");
                    return true;

                case "wests":
                case "west":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2978, 3598, 0));
                    player.getPackets().sendGameMessage("You have teleported to west dragons.");
                    return true;

                case "zerk":
                case "zerkspot":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3043, 3552, 0));
                    player.getPackets().sendGameMessage("You have teleported to zerker spot.");
                    return true;

                case "bridspot":
                case "brid":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3013, 3553, 0));
                    player.getPackets().sendGameMessage("You have teleported to hybridding spot.");
                    return true;

                case "teles":
                    player.getInterfaceManager().sendInterface(275);
                    player.getPackets().sendIComponentText(275, 1, "*Teleports*");
                    player.getPackets().sendIComponentText(275, 10, "");
                    player.getPackets().sendIComponentText(275, 11,
                            "::zerk *Teleports to western side of edgeville. SINGLE");
                    player.getPackets().sendIComponentText(275, 12, "::easts *Teleports to lvl 20 east dragons. SINGLE");
                    player.getPackets().sendIComponentText(275, 13, "::wests *Teleports to lvl 13 west dragons. SINGLE");
                    player.getPackets().sendIComponentText(275, 14, "::mb *Teleports inside the mage bank. NOT WILDY");
                    player.getPackets().sendIComponentText(275, 15,
                            "::brid *Teleports to west side of edgeville wilderness. SINGLE");
                    player.getPackets().sendIComponentText(275, 16, "::gdz *Teleports to Greater demons in lvl 48. MULTI");
                    player.getPackets().sendIComponentText(275, 17,
                            "::44ports *Teleports to lvl 44 wilderness portal. SINGLE");
                    player.getPackets().sendIComponentText(275, 18,
                            "::iceplatue *Teleports to ice platue in lvl 50 wilderness. SINGLE");
                    player.getPackets().sendIComponentText(275, 19,
                            "::kbd *Teleports outside king black dragon lair. MULTI");
                    player.getPackets().sendIComponentText(275, 20,
                            "::50ports *Teleports to lvl 50 wilderness portal. MULTI");
                    player.getPackets().sendIComponentText(275, 21,
                            "::bh *Teleports inside the Bounty hunter crate. MULTI");
                    player.getPackets().sendIComponentText(275, 22, "::revs *Teleports to rev cave. SINGLE & MULTI");
                    player.getPackets().sendIComponentText(275, 23,
                            "::altar *Teleports to an altar deep in west of wilderness.");
                    player.getPackets().sendIComponentText(275, 24,
                            "::castle *Teleports to castle near west dragons. MULTI");
                    return true;
                case "commands":
                    player.getInterfaceManager().sendInterface(275);
                    player.getPackets().sendIComponentText(275, 1, "*Commands*");
                    player.getPackets().sendIComponentText(275, 10, "");
                    player.getPackets().sendIComponentText(275, 11,
                            "::setlevel skillId level - Set your own combat skills<br>You can only set skillIds 1-6 & 23");
                    player.getPackets().sendIComponentText(275, 12, "");
                    player.getPackets().sendIComponentText(275, 13, "::teles - Shows all teleport commands");
                    player.getPackets().sendIComponentText(275, 14, "::tasks - Shows list of all tasks");
                    player.getPackets().sendIComponentText(275, 15, "::players - Tells you how many players are online");
                    player.getPackets().sendIComponentText(275, 16, "::playerslist - Shows a list of all players online");
                    player.getPackets().sendIComponentText(275, 17, "::pricecheck - Search & price checks an item");
                    player.getPackets().sendIComponentText(275, 18, "::checkoffers - Shows all Grand exchange offers");
                    player.getPackets().sendIComponentText(275, 19, "::kdr - Prints out your kills & deaths ratio");
                    player.getPackets().sendIComponentText(275, 20, "::skull - Makes you skulled");
                    player.getPackets().sendIComponentText(275, 21, "::droplog - Shows droplog");
                    player.getPackets().sendIComponentText(275, 22, "::cleardroplog - Clears all drops from droplog");
                    player.getPackets().sendIComponentText(275, 23,
                            "::toggledroplogmessage - Toggle on & off droplog messages");
                    player.getPackets().sendIComponentText(275, 24,
                            "::droplogvalue value - Set value of which items should be logged");
                    player.getPackets().sendIComponentText(275, 25, "::droplog - Shows droplog");
                    player.getPackets().sendIComponentText(275, 26,
                            "::switchitemslook - Changes items look from old / new");
                    player.getPackets().sendIComponentText(275, 27, "::compreqs - Shows completionist cape requirements");
                    player.getPackets().sendIComponentText(275, 28, "::emptybank - Resets your whole bank.");
                    for (int i = 29; i <= 150; i++)
                        player.getPackets().sendIComponentText(275, i, "");
                    return true;
                case "players":
                case "online":
                    player.getPackets().sendGameMessage("Players online: " + World.getPlayers().size());
                    return true;
                case "pc":
                case "price":
                case "pricecheck":
                    player.getPackets().sendHideIComponent(105, 196, true);
                    player.getPackets().sendConfig(1109, -1);
                    player.getPackets().sendConfig1(1241, 16750848);
                    player.getPackets().sendConfig1(1242, 15439903);
                    player.getPackets().sendConfig1(741, -1);
                    player.getPackets().sendConfig1(743, -1);
                    player.getPackets().sendConfig1(744, 0);
                    player.getPackets().sendInterface(true, 752, 7, 389);
                    player.getPackets().sendRunScript(570, new Object[]{"Price checker"});
                    return true;
                case "closeticket":
                    TicketSystem.closeTicket(player);
                    return true;
                case "checkoffers":
                    GrandExchange.sendOfferTracker(player);
                    return true;
                case "droplog":
                    player.getDropLogs().displayInterface();
                    return true;
                case "cleardroplog":
                    player.getDropLogs().clearDrops();
                    return true;
                case "toggledroplogmessage":
                    player.getDropLogs().toggleMessage();
                    return true;
                case "droplogvalue":
                    player.getDropLogs().setLowestValue(Integer.valueOf(cmd[1]));
                    player.getPackets().sendGameMessage("Lowest droplog value is now: "
                            + Utils.getFormattedNumber(Integer.valueOf(cmd[1]), ',') + " gp.");
                    return true;
                case "skull":
                    player.skullDelay = 2000; // 20minutes
                    player.skullId = 0;
                    player.getAppearence().generateAppearenceData();
                    return true;
                case "score":
                case "kdr":
                    double kill = player.getKillCount();
                    double death = player.getDeathCount();
                    double dr = kill / death;
                    if (kill == 0 && death == 0)
                        dr = 0;
                    player.setNextForceTalk(
                            new ForceTalk("Kills: " + player.getKillCount() + " Deaths: " + player.getDeathCount()
                                    + " Streak: " + player.killStreak + " Ratio: " + new DecimalFormat("##.#").format(dr)));
                    return true;
                case "switchitemslook":
                    player.switchItemsLook();
                    player.getPackets()
                            .sendGameMessage("You now have " + (player.isOldItemsLook() ? " old" : "new") + " items look.");
                    return true;
                case "compreqs":
                    player.sendCompReqMessages();
                    return true;
            }
        }
        return true;
    }

    /**
     * Section was created for secure things, admin section is for anyone with
     * rights = 2
     */

    public static boolean processAdminCommands(final Player player, String[] cmd, boolean console,
                                               boolean clientCommand) {
        if (clientCommand) {
            switch (cmd[0]) {
                case "playmusic":
                    int id = Integer.parseInt(cmd[1]);
                    player.getPackets().sendMusicEffect(id);
                    player.sm("now playing: " + id);
                    return true;
                case "tele":
                    cmd = cmd[1].split(",");
                    int plane = Integer.valueOf(cmd[0]);
                    int x = Integer.valueOf(cmd[1]) << 6 | Integer.valueOf(cmd[3]);
                    int y = Integer.valueOf(cmd[2]) << 6 | Integer.valueOf(cmd[4]);
                    player.setNextWorldTile(new WorldTile(x, y, plane));
                    if (player.getInterfaceManager().containsInterface(755))
                        player.getInterfaceManager().closeInterface(755, 1);
                    player.getPackets()
                            .sendPanelBoxMessage("Teleported to " + Integer.valueOf(cmd[0]) + "," + Integer.valueOf(cmd[1])
                                    + "," + Integer.valueOf(cmd[2]) + "," + Integer.valueOf(cmd[3]) + ","
                                    + Integer.valueOf(cmd[4]));
                    return true;
            }
        } else {
            String name;
            Player target;
            switch (cmd[0]) {

                case "pvp":
                case "edgepvp":
                    EdgevillePvPControler.enterPVP(player);
                    return true;
                case "ancient":
                case "ancients":
                    if (Settings.ECONOMY_MODE == 0 && !player.isDeveloper())
                        return false;
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    player.getPackets().sendGameMessage("Your mind clears and you switch back to the ancient spellbook.");
                    player.getCombatDefinitions().setSpellBook(1);
                    return true;
                case "prayers":
                case "prayer":
                case "curses":
                case "regular":
                case "regulars":
                case "pbook":
                    if (Settings.ECONOMY_MODE == 0 && !player.isDeveloper())
                        return false;
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    player.getPrayer().setPrayerBook(player.getPrayer().ancientcurses ? false : true);
                    player.getPackets().sendGameMessage("You switch your prayer book.");
                    return true;
                case "switch":
                case "spellbook":
                case "book":
                    if (Settings.ECONOMY_MODE == 0 && !player.isDeveloper())
                        return false;
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    int spellBook = player.getCombatDefinitions().spellBook;
                    player.getCombatDefinitions().setSpellBook(spellBook == 0 ? 1 : spellBook == 1 ? 2 : 0);
                    player.getPackets().sendGameMessage("You switch your spellbook.");
                    return true;
                case "normal":
                case "modern":
                    if (Settings.ECONOMY_MODE == 0 && !player.isDeveloper())
                        return false;
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " at this location.");
                        return true;
                    }
                    player.getPackets().sendGameMessage("Your mind clears and you switch back to the normal spellbook.");
                    player.getCombatDefinitions().setSpellBook(0);
                    return true;
                /**
                 * Temporary Commands
                 */
                case "hideloop":
                    int start = Integer.parseInt(cmd[1]);
                    int end = Integer.parseInt(cmd[2]);
                    for (int i = start; i < end; i++) {
                        player.getPackets().sendHideIComponent(813, i, false);
                    }
                    return true;
                case "hide":
                    start = Integer.parseInt(cmd[1]);
                    player.getPackets().sendHideIComponent(813, start, true);
                    return true;
                case "unhide":
                    start = Integer.parseInt(cmd[1]);
                    player.getPackets().sendHideIComponent(813, start, false);
                    return true;
                case "kiln":
                    player.getCreationKiln().openKilnInterface();
                    return true;
                case "testgfx":
                    for (int x = player.getX() - 1; x <= player.getX() + 1; x++) {
                        for (int y = player.getY() - 1; y <= player.getY() + 1; y++) {
                            World.sendGraphics(player, new Graphics(1883, 0, 0), new WorldTile(x, y, 0));
                        }
                    }
                    return true;
                case "testdrop":
                    int amount = 0;
                    int max = 32000;
                    for (int i = 0; i < 1000000; i++) {
                        int row = (int) (max * 0.05);
                        int random = Utils.getRandom(max - row);
                        // System.out.println("max: " + max + ", row: " + row + ", total: " + (max -
                        // row) + " random: " + random);
                        if (random <= 0) {
                            amount++;
                        }
                    }
                    System.out.println("amount: " + amount);
                    return true;
                case "runes":
                    for (int i = 554; i < 566; i++) {
                        player.getInventory().addItem(new Item(i, Integer.MAX_VALUE));
                        player.getInventory().addItem(new Item(9075, 5000));
                    }
                    return true;
                case "unlocklivid":
                    for (Spell s : Spell.values()) {
                        player.getLivid().getSpellsLearned().add(s);
                        player.getLivid().addSpellAmount();
                    }
                    player.sm("Unlocked all livid spells.");
                    return true;
                case "resetlivid":
                    player.getLivid().getSpellsLearned().clear();
                    player.getLivid().spellAmount = 0;
                    player.sm("Reset livid");
                    return true;
                case "sql":
//			DatabaseUtility.sendTask(player, new sendGEOffer());
                    return true;
                case "livid":
                    LividFarmControler.enterLividFarm(player);
                    return true;
                case "spins":
                    player.getSquealOfFortune().giveBoughtSpins(5000);
                    return true;
                case "starterbank":
                    amount = 1000;
                    for (Item item : CustomStoreData.range) {
                        if (item == null)
                            continue;
                        item.setAmount(amount);
                        player.sm(item.getName() + " - " + EconomyPrices.getPrice(item.getId()));
                        if (EconomyPrices.getPrice(item.getId()) > 0)
                            continue;
                        player.getBank().addItem(item, true);
                        player.sm(item.getName() + " added to your bank.");
                    }
                    for (Item item : CustomStoreData.melee) {
                        if (item == null)
                            continue;
                        item.setAmount(amount);
                        if (EconomyPrices.getPrice(item.getId()) > 0)
                            continue;
                        player.getBank().addItem(item, true);
                        player.sm(item.getName() + " added to your bank.");
                    }
                    for (Item item : CustomStoreData.magic) {
                        if (item == null)
                            continue;
                        item.setAmount(amount);
                        if (EconomyPrices.getPrice(item.getId()) > 0)
                            continue;
                        player.getBank().addItem(item, true);
                        player.sm(item.getName() + " added to your bank.");
                    }
                    for (Item item : CustomStoreData.supplies) {
                        if (item == null)
                            continue;
                        item.setAmount(amount);
                        if (EconomyPrices.getPrice(item.getId()) > 0)
                            continue;
                        player.getBank().addItem(item, true);
                        player.sm(item.getName() + " added to your bank.");
                    }
                    for (Item item : CustomStoreData.accessories) {
                        if (item == null)
                            continue;
                        item.setAmount(amount);
                        if (EconomyPrices.getPrice(item.getId()) > 0)
                            continue;
                        player.getBank().addItem(item, true);
                        player.sm(item.getName() + " added to your bank.");
                    }
                    return true;
                case "antibot":
                    AntiBot.getInstance().start(player);
                    return true;
                case "voidshop":
                    CommendationExchange.openExchangeShop(player);
                    return true;
                case "val":
                    player.sm(player.getToggleValue(player.toggles.get("DROPVALUE")));
                    return true;
                case "save":
                    AccountCreation.savePlayer(player);
                    player.sm("Saved your account.");
                    return true;
                case "player":
                    player.getPlayerRank().addRank(Rank.PLAYER);
                    player.sm("Changed rank to player.");
                    return true;
                case "bronzedonator":
                    player.getPlayerRank().addRank(Rank.BRONZE_DONATOR);
                    return true;
                case "golddonator":
                    player.getPlayerRank().addRank(Rank.GOLD_DONATOR);
                    return true;
                case "silverdonator":
                    player.getPlayerRank().addRank(Rank.SILVER_DONATOR);
                    return true;
                case "youtuber":
                    player.getPlayerRank().addRank(Rank.YOUTUBER);
                    return true;
                case "norank":
                    player.getPlayerRank().getRank()[0] = null;
                    player.getPlayerRank().getRank()[1] = null;
                    player.getPlayerRank().getRank()[2] = null;
                    player.getPlayerRank().setRank(0, Rank.PLAYER);
                    player.sm("Removed ranks");
                    return true;
                case "developer":
                    player.getPlayerRank().addRank(Rank.DEVELOPER);
                    return true;
                case "moderator":
                    player.getPlayerRank().addRank(Rank.MODERATOR);
                    return true;
                case "support":
                    player.getPlayerRank().addRank(Rank.PLAYERSUPPORT);
                    return true;
                case "ironman":
                    player.getPlayerRank().addRank(Rank.IRONMAN);
                    return true;
                case "hciron":
                    player.getPlayerRank().addRank(Rank.HARDCORE_IRONMAN);
                    return true;
                case "removeiron":
                    player.getPlayerRank().setRank(2, null);
                    return true;
                case "rank":
                    player.sm("Your ranks: " + player.getPlayerRank().getRankNames());
                    if (player.getMessageIcon() != -1)
                        player.sm("Your rank icon: <img=" + player.getMessageIcon() + ">");
                    return true;
                case "unlock":
                    player.unlock();
                    return true;
                case "maxdung":
                    player.getDungManager().setMaxComplexity(6);
                    player.getDungManager().setMaxFloor(60);
                    player.getPackets().sendGameMessage("Floors maxed");
                    return true;
                case "completedung":
                    DungeonManager dungeon = player.getDungManager().getParty().getDungeon();
                    for (Player partyPlayers : dungeon.getParty().getTeam()) {
                        if (partyPlayers == null)
                            continue;
                        player.sm(partyPlayers.getDisplayName());
                        dungeon.voteToMoveOn(partyPlayers);
                    }
                    return true;
                case "dungtest":
                    DungeonPartyManager party = new DungeonPartyManager();
                    for (Player p1 : World.getPlayers()) {
                        if (!p1.hasStarted() || p1.hasFinished())
                            continue;
                        p1.getDungManager().leaveParty();
                        party.add(p1);
                        for (Item inventory : p1.getInventory().getItems().getContainerItems()) {
                            if (inventory != null) {
                                p1.getInventory().deleteItem(inventory);
                                p1.getBank().addItem(inventory, true);
                            }
                        }
                        for (Item equipment : p1.getEquipment().getItems().getContainerItems()) {
                            if (equipment != null) {
                                p1.getEquipment().deleteItem(equipment.getId(), equipment.getAmount());
                                p1.getBank().addItem(equipment, true);
                            }
                        }
                        p1.getAppearence().generateAppearenceData();
                    }
                    party.setFloor(18);
                    party.setComplexity(6);
                    party.setDifficulty(party.getTeam().size());
                    party.setSize(DungeonConstants.SMALL_DUNGEON);
                    party.setKeyShare(true);
                    player.getDungManager().enterDungeon(false, true);
                    return true;
                case "dropparty":
                    int tilesAmount = 1;
                    for (Item items : player.getInventory().getItems().getContainerItems()) {
                        if (items == null)
                            continue;
                        WorldTile tile[] = {
                                new WorldTile(player.getX() + Utils.random(tilesAmount),
                                        player.getY() + Utils.random(tilesAmount), player.getPlane()),
                                new WorldTile(player.getX() - Utils.random(tilesAmount),
                                        player.getY() + Utils.random(tilesAmount), player.getPlane()),
                                new WorldTile(player.getX() - Utils.random(tilesAmount),
                                        player.getY() - Utils.random(tilesAmount), player.getPlane()),
                                new WorldTile(player.getX() + Utils.random(tilesAmount),
                                        player.getY() - Utils.random(tilesAmount), player.getPlane())};
                        player.getInventory().deleteItem(items);
                        World.addGroundItem(items, new WorldTile(tile[Utils.getRandom(tile.length - 1)]), player, false, 0);
                    }
                    return true;

                case "repairprice":
                    ArmourRepair.getTotalPrice(player);
                    return true;
                case "repairitems":
                    ArmourRepair.repairAllItems(player);
                    return true;
                /**
                 * end of temporary commands
                 */

                case "barragerunes":
                    if (player.getInventory().containsOneItem(24497)) {
                        player.getRunePouch().reset();
                        player.getRunePouch().add(new Item(565, 200));
                        player.getRunePouch().add(new Item(560, 400));
                        player.getRunePouch().add(new Item(555, 600));
                        player.getRunePouch().shift();
                        player.getPackets().sendGameMessage("100 ice barrage runes added to your rune pouch.");
                    } else {
                        if (!player.getInventory().hasFreeSlots() && !player.getInventory().containsOneItem(565))
                            player.getPackets().sendGameMessage("You don't have enough space for "
                                    + ItemDefinitions.getItemDefinitions(565).getName() + ".");
                        else {
                            player.getInventory().addItem(565, 200);
                            player.getPackets().sendGameMessage("200 " + ItemDefinitions.getItemDefinitions(565).getName()
                                    + "s added to your inventory.");
                        }
                        if (!player.getInventory().hasFreeSlots() && !player.getInventory().containsOneItem(560))
                            player.getPackets().sendGameMessage("You don't have enough space for "
                                    + ItemDefinitions.getItemDefinitions(560).getName() + ".");
                        else {
                            player.getInventory().addItem(560, 400);
                            player.getPackets().sendGameMessage("400 " + ItemDefinitions.getItemDefinitions(560).getName()
                                    + "s added to your inventory.");
                        }
                        if (!player.getInventory().hasFreeSlots() && !player.getInventory().containsOneItem(555))
                            player.getPackets().sendGameMessage("You don't have enough space for "
                                    + ItemDefinitions.getItemDefinitions(555).getName() + ".");
                        else {
                            player.getInventory().addItem(555, 600);
                            player.getPackets().sendGameMessage("600 " + ItemDefinitions.getItemDefinitions(555).getName()
                                    + "s added to your inventory.");
                        }
                    }
                    return true;
                case "vengrunes":
                    if (player.getInventory().containsOneItem(24497)) {
                        player.getRunePouch().reset();
                        player.getRunePouch().add(new Item(560, 40));
                        player.getRunePouch().add(new Item(9075, 80));
                        player.getRunePouch().add(new Item(557, 200));
                        player.getRunePouch().shift();
                        player.getPackets().sendGameMessage("100 vengeance runes added to your rune pouch.");
                    } else {
                        if (!player.getInventory().hasFreeSlots() && !player.getInventory().containsOneItem(560))
                            player.getPackets().sendGameMessage("You don't have enough space for "
                                    + ItemDefinitions.getItemDefinitions(560).getName() + ".");
                        else {
                            player.getInventory().addItem(560, 40);
                            player.getPackets().sendGameMessage("40 " + ItemDefinitions.getItemDefinitions(560).getName()
                                    + "s added to your inventory.");
                        }
                        if (!player.getInventory().hasFreeSlots() && !player.getInventory().containsOneItem(9075))
                            player.getPackets().sendGameMessage("You don't have enough space for "
                                    + ItemDefinitions.getItemDefinitions(9075).getName() + ".");
                        else {
                            player.getInventory().addItem(9075, 80);
                            player.getPackets().sendGameMessage("80 " + ItemDefinitions.getItemDefinitions(9075).getName()
                                    + "s added to your inventory.");
                        }
                        if (!player.getInventory().hasFreeSlots() && !player.getInventory().containsOneItem(557))
                            player.getPackets().sendGameMessage("You don't have enough space for "
                                    + ItemDefinitions.getItemDefinitions(557).getName() + ".");
                        else {
                            player.getInventory().addItem(557, 200);
                            player.getPackets().sendGameMessage("200 " + ItemDefinitions.getItemDefinitions(557).getName()
                                    + "s added to your inventory.");
                        }
                    }
                    return true;
                case "customname":
                    player.getPackets().sendRunScript(109,
                            new Object[]{"Please enter the color you would like. (HEX FORMAT)"});
                    player.temporaryAttribute().put("customname", Boolean.TRUE);
                    return true;
                case "abyte851":
                    NPCDefinitions npc = null;
                    byte byteValue = -96;
                    for (int i = 0; i < Utils.getNPCDefinitionsSize(); i++) {
                        npc = NPCDefinitions.getNPCDefinitions(i);
                        if (byteValue != npc.aByte851)
                            System.out.println("aByte851 - Unique byte: " + npc.aByte851 + " for npcId:" + npc.getId() + " - " + npc.getName());
                    }
                    System.out.println("looped through 1 - " + Utils.getNPCDefinitionsSize() + " npcs.");
                    return true;
                case "anint842":
                    npc = null;
                    for (int i = 0; i < Utils.getNPCDefinitionsSize(); i++) {
                        npc = NPCDefinitions.getNPCDefinitions(i);
                        int intValue = npc.anInt842;
                        if (intValue != -1)
                            System.out.println("anInt842 - Unqiue value: " + intValue + " for npcId:" + npc.getId() + " - " + npc.getName());
                    }
                    System.out.println("looped through 1 - " + Utils.getNPCDefinitionsSize() + " npcs.");
                    return true;
                case "anint876":
                    npc = null;
                    for (int i = 0; i < Utils.getNPCDefinitionsSize(); i++) {
                        npc = NPCDefinitions.getNPCDefinitions(i);
                        int intValue = npc.anInt876;
                        if (intValue != -1)
                            System.out.println("anInt876 - Unqiue value: " + intValue + " for npcId:" + npc.getId() + " - " + npc.getName());
                    }
                    System.out.println("looped through 1 - " + Utils.getNPCDefinitionsSize() + " npcs.");
                    return true;


                case "debug":
                    for (int i = 0; i < 100; i++) {
                        npc = NPCDefinitions.getNPCDefinitions(i);
                        System.out.println(npc.anInt884);
                    }
                    return true;

                case "di":
                    player.getPackets().sendHideIComponent(1173, 172, false);
                    player.getPackets().sendHideIComponent(1173, 173, false);
                    return true;
                case "did":
                    player.getPackets().sendHideIComponent(1173, 172, true);
                    player.getPackets().sendHideIComponent(1173, 173, true);
                    return true;
                case "r":
                    player.getDominionTower().addFactor(15000);
                    player.sm("factor added");
                    player.getDominionTower().openRewardsChest();
                    return true;
                case "e":
                    player.getDominionTower().openEnduranceMode();
                    break;
                case "randomboss":
                    player.getDominionTower().nextBossIndex = -1;
                    player.getDominionTower().selectBoss();
                    System.out.println("-----------------------------------------------------");
                    player.sm(player.getDominionTower().getNextBossIndex() + "");
                    player.sm("Boss total level: " + player.getDominionTower().getBossesTotalLevel());
                    player.sm("Boss name: " + player.getDominionTower().getBoss().getName());
                    System.out.println("-----------------------------------------------------");
                    return true;
                case "factordebug":
                    player.getDominionTower().nextBossIndex = -1;
                    player.getDominionTower().selectBoss();
                    System.out.println("-----------------------------------------------------");
                    player.sm("BossIndex: " + player.getDominionTower().getNextBossIndex() + "");
                    for (Map.Entry<Long, Object> maps : GeneralRequirementMap.getMap(ClientScriptMap.getMap(5213).getIntValue(player.getDominionTower().getNextBossIndex())).getValues().entrySet()) {
                        System.out.print(maps.getKey() + " - " + maps.getValue() + "\n");
                    }
                    System.out.println("-----------------------------------------------------");
                    return true;
                case "z":
                    player.getControlerManager().startControler("ArtisanControler");
                    return true;
                case "infhp":
                    player.setHitpoints(800000);
                    return true;
                case "region":
                    player.sm("regionID: " + player.getRegionId());
                    return true;
                case "24":
                    player.getInterfaceManager().sendInterface(25);
                    player.getPackets().sendItemOnIComponent(25, 2, 4765, 1);
                    player.getPackets().sendItemOnIComponent(25, 3, 4766, 1);
                    player.getPackets().sendItemOnIComponent(25, 5, 4767, 1);
                    return true;
                case "farm2":
                    player.getInterfaceManager().sendInventoryInterface(126);
                    return true;
                case "infuse":
                    player.getInterfaceManager().sendInterface(666);
                    player.getPackets().sendIComponentSettings(666, 16, 0, 462, 1400);
                    player.getPackets().sendInterSetItemsOptionsScript(666, 16, 78, 8, 10, "test",
                            "teste", "tester", "baaaa", "baaa",
                            "heh");
                    return true;
                case "farm":
                    //todo unlock options
                    player.getInterfaceManager().sendInterface(686);
                    return true;
                case "testlevel":
                    int lvl = Integer.valueOf(cmd[1]);
                    player.getVarsManager().sendVar(965, lvl);
                    player.getInterfaceManager().sendInterface(499);
                    return true;
                case "lvlup":
                    int skillId = Integer.valueOf(cmd[1]);
                    int oldLevel = 37;
                    int newLevel = 40;
                    if (cmd.length > 2) {
                        oldLevel = Integer.valueOf(cmd[2]);
                        newLevel = Integer.valueOf(cmd[3]);
                    }
                    int gainedLevels = oldLevel - newLevel;
                    boolean milestoneAchievement = true;
                    int configValue = Skills.SkillData.forId(skillId).getValue();
                    //varbit: 4729 - skillId to show which skill you leveld - usage (4729, skillId + 1)
                    //globalConfig: 1469 - how many levels you gained since your last level - usage (1469 + skillId, gainedLevels + newLevel)
                    //varbit: 4727 - what level you are now in that skill - usage (4727 - newLevel)

                    //varbit: 281 - config for telling that you reached max level in tutorial isle
                    //281 < 1000 && actualSkillLevel(skillId) == 3 - usage: unknown

                    //varbit: 4730 - display milestone level or not - usage (4730, 0-1)
                    //varbit: 4728 - what milestone level you are now - usage (4728 - 0, 1, 2, 3, 4) 0=50, 1=100, 2=150, 3=200, 4=250, 5=300..
                    player.getSkills().setXp(skillId, Skills.getXPForLevel(newLevel));
                    player.getSkills().set(skillId, newLevel);
                    player.getSkills().refresh(skillId);

                    //datamap(3644, int0) + (getSkillActualLvl(skill1) - int4) + datamap(3645, int0)
                    //1469, configValue + (newLevel - 1469) + configValue
                    player.getPackets().sendGlobalConfig(LevelUp.getLevelGainedConfig(skillId), gainedLevels + newLevel);
                    //player.getPackets().sendVarBit(4727, newLevel);
                    //player.getPackets().sendVarBit(281, 500);//unknown what to send
                    if (milestoneAchievement) {
                        player.getPackets().sendVarBit(4728, 4);
                        player.getPackets().sendVarBit(4730, milestoneAchievement ? 1 : 0);
                    }
                    player.getPackets().sendVarBit(4727, 13);
                    player.getPackets().sendVarBit(4731, 1);
                    player.getPackets().sendVarBit(4729, configValue);
                    player.getPackets().sendVarBit(5395, 1);
                    player.getInterfaceManager().sendInterface(741);
                    return true;
                case "totalmile":
                    player.getPackets().sendVarBit(2728, Integer.valueOf(cmd[1]));
                    player.getPackets().sendVarBit(2730, 1);
                    player.getPackets().sendVarBit(4729, 1);
                    player.getInterfaceManager().sendInterface(741);
                    player.sm("total mile");
                    return true;
                case "reset":
                    for (int i = 0; i < 25; i++) {
                        player.getSkills().setXp(i, 0);
                        player.getSkills().set(i, 1);
                        player.getSkills().refresh(i);
                    }
                    return true;
                case "levelup":
                    skillId = 0;
                    oldLevel = 59;
                    newLevel = 62;
                    gainedLevels = oldLevel - newLevel;
                    milestoneAchievement = true;
                    //varbit: 4729 - skillId to show which skill you leveld - usage (4729, skillId + 1)
                    //globalConfig: 1469 - how many levels you gained since your last level - usage (1469 + skillId, gainedLevels + newLevel)
                    //varbit: 4727 - what level you are now in that skill - usage (4727 - newLevel)

                    //varbit: 281 - config for telling that you reached max level in tutorial isle
                    //281 < 1000 && actualSkillLevel(skillId) == 3 - usage: unknown

                    //varbit: 4730 - display milestone level or not - usage (4730, 0-1)
                    //varbit: 4728 - what milestone level you are now - usage (4728 - 0, 1, 2, 3, 4) 0=50, 1=100, 2=150, 3=200, 4=250, 5=300..
                    player.getSkills().setXp(skillId, Skills.getXPForLevel(newLevel));
                    player.getSkills().refresh(skillId);
                    player.getPackets().sendGlobalConfig(1469 + skillId, gainedLevels + newLevel);
                    player.getPackets().sendVarBit(4727, newLevel);
                    //player.getPackets().sendVarBit(281, 500);//unknown what to send
                    if (milestoneAchievement) {
                        player.getPackets().sendVarBit(4728, 1);
                        player.getPackets().sendVarBit(4730, milestoneAchievement ? 1 : 0);
                    }
                    player.getPackets().sendVarBit(4729, skillId + 1);
                    return true;
                case "script":
                    player.getPackets().sendRunScript(1715, 1043, 4761, 1);
                    player.sm("script sent");
                    return true;
                case "loopvoice":
                    int startv = Integer.parseInt(cmd[1]);
                    int endv = Integer.parseInt(cmd[2]);
                    player.sm("Now looping npc sound ids " + startv + " to " + endv);
                    for (int i = startv; i < endv; i++) {
                        player.getPackets().sendSound(i, 0, 2);
                        player.sm("npc sound id: " + i);
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    return true;
                case "robjects":
                    ObjectScriptsHandler.init();
                    System.out.println("reload object scripts");
                    return true;
                case "regionid":
                    System.out.println("region id: " + player.getRegionId());
                    return true;
                case "mypos":
                    player.sm("x: " + player.getLocation().getX() + " y: " + player.getLocation().getY());
                    break;
                case "testarti":
                    double rollChance = 100 - (Integer.valueOf(cmd[1]) * 0.30);
                    double c = Utils.getRandomDouble2(rollChance);
                    Artefacts rolledItem = Artefacts.values()[Utils.getRandom(Artefacts.values().length - 1)];
                    System.out.println("--------------");
                    System.out.println("Your ep: " + Integer.valueOf(cmd[1]) + "%");
                    System.out.println("Roll: " + c + " [100 - (yourEp * 0.10)] = " + 0.0 + "-" + rollChance);
                    System.out.println("Rolling for drop: " + rolledItem.getName());
                    System.out.println("Rolled Item %chance: " + rolledItem.getChance() + "%");
                    System.out.println("--------------");
                    if (c <= rolledItem.getChance()) {
                        System.out.println("Success!");
                    } else
                        System.out.println("Failed!");
                    System.out.println("--------------");
                    return true;
                case "piece":
                    if (!ConstructorsOutfit.addPiece(player))
                        return true;
                    return true;
                case "ecomode":
                    int ecoMode = Integer.parseInt(cmd[1]);
                    Settings.ECONOMY_MODE = ecoMode;
                    World.sendWorldMessage(
                            "Server Economy mode has been set to: "
                                    + (ecoMode == 0 ? "Full Economy" : ecoMode == 1 ? "Half Economy" : "Full Spawn") + "!",
                            false);
                    return true;
                case "openassist":
                    player.getAssist().Open();
                    return true;
                case "claimtaskrewards":
                    player.getTaskManager().claimRewards();
                    return true;
                case "resetalltasks":
                    player.getTaskManager().resetAllTasks();
                    return true;
                case "lowhp":
                    player.applyHit(new Hit(player, player.getHitpoints() - 1, HitLook.REGULAR_DAMAGE));
                    return true;
                case "serverdoubledrop":
                    try {
                        boolean doubledrop = Boolean.valueOf(cmd[1]);
                        ;
                        if (Settings.DOUBLE_DROP == doubledrop) {
                            player.getPackets().sendGameMessage("Nothing interesting happens.");
                        } else if (doubledrop == false) {
                            ServerMessage.sendNews(true, "<img=12>Update: Double drops has been deactivated.", false, true);
                        } else
                            ServerMessage.sendNews(true, "<img=12>Update: Double drops has been activated!", false, true);
                        Settings.DOUBLE_DROP = doubledrop;
                    } catch (NumberFormatException f) {
                        player.getPackets().sendGameMessage("Use ::serverdoubledrop false-true.");
                    }
                    return true;
                case "serverskillingxp":
                    try {
                        int rate = Integer.valueOf(cmd[1]);
                        ;
                        if (rate > Integer.MAX_VALUE || rate < 1) {
                            player.getPackets().sendGameMessage("Use ::serverxp (int) 1 >.");
                            return true;
                        } else {
                            if (Settings.SKILLING_XP_RATE == rate) {
                                player.getPackets().sendGameMessage("Nothing interesting happens.");
                            } else if (rate == 1) {
                                ServerMessage.sendNews(true, "<img=12>Update: Skilling XP has been set to normal.", false,
                                        true);
                            } else
                                ServerMessage.sendNews(true, "<img=12>Update: Skilling XP has been set to (" + rate + ")",
                                        false, true);

                            Settings.SKILLING_XP_RATE = rate;
                        }
                    } catch (NumberFormatException f) {
                        player.getPackets().sendGameMessage("Use ::serverskillingxp (int) 1 >.");
                    }
                    return true;
                case "dxp":
                case "serverbonusxp":
                    try {
                        double rate = Double.valueOf(cmd[1]);
                        ;
                        if (rate > Integer.MAX_VALUE || rate < 1.0) {
                            player.getPackets().sendGameMessage("Use ::serverxp (double) 1-50.");
                            return true;
                        } else {
                            if (Settings.BONUS_EXP_WEEK_MULTIPLIER == rate) {
                                player.getPackets().sendGameMessage("Nothing interesting happens.");
                            } else if (rate == 1.0) {
                                ServerMessage.sendNews(true, "<img=12>Update: Bonus XP has been deactivated.", false, true);
                            } else
                                ServerMessage.sendNews(true, "<img=12>Update: Bonus XP (" + rate + ") has been activated!",
                                        false, true);

                            Settings.BONUS_EXP_WEEK_MULTIPLIER = rate;
                        }
                    } catch (NumberFormatException f) {
                        player.getPackets().sendGameMessage("Use ::serverxp (double) 1-50.");
                    }
                    return true;
                case "serverbonuspts":
                    try {
                        double rate = Double.valueOf(cmd[1]);
                        ;
                        if (rate > 50.0 || rate < 1.0) {
                            player.getPackets().sendGameMessage("Use ::serverbonuspts (double) 1-50.");
                            return true;
                        } else {
                            if (Settings.BONUS_POINTS_WEEK_MULTIPLIER == rate) {
                                player.getPackets().sendGameMessage("Nothing interesting happens.");
                            } else if (rate == 1.0) {
                                ServerMessage.sendNews(true,
                                        "<img=12>Update: Bonus  " + Settings.SERVER_NAME + " points has been deactivated.",
                                        false, true);
                            } else
                                ServerMessage.sendNews(true, "<img=12>Update: Bonus  " + Settings.SERVER_NAME + " points ("
                                        + rate + ") has been activated!", false, true);

                            Settings.BONUS_POINTS_WEEK_MULTIPLIER = rate;
                        }
                    } catch (NumberFormatException f) {
                        player.getPackets().sendGameMessage("Use ::serverxp (double) 1-50.");
                    }
                    return true;
                case "checkinv":
                    name = "";
                    for (int i = 1; i < cmd.length; i++) {
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    target = World.getPlayerByDisplayName(name);
                    try {
                        String contentsFinal = "";
                        String inventoryContents = "";
                        int contentsAmount;
                        int freeSlots = target.getInventory().getFreeSlots();
                        int usedSlots = 28 - freeSlots;
                        for (int i = 0; i < 28; i++) {
                            if (target.getInventory().getItem(i) == null) {
                                contentsAmount = 0;
                                inventoryContents = "";
                            } else {
                                int id = target.getInventory().getItem(i).getId();
                                contentsAmount = target.getInventory().getNumberOf(id);
                                inventoryContents = "slot " + (i + 1) + " - " + target.getInventory().getItem(i).getName()
                                        + " - " + contentsAmount + "<br>";
                            }
                            contentsFinal += inventoryContents;
                        }
                        player.getInterfaceManager().sendInterface(1166);
                        player.getPackets().sendIComponentText(1166, 1, contentsFinal);
                        player.getPackets().sendIComponentText(1166, 2, usedSlots + " / 28 Inventory slots used.");
                        player.getPackets().sendIComponentText(1166, 23,
                                "<col=FFFFFF><shad=000000>" + target.getDisplayName() + "</shad></col>");
                    } catch (Exception e) {
                        player.getPackets().sendGameMessage(
                                "[<col=FF0000>" + Utils.formatPlayerNameForDisplay(name) + "</col>] wasn't found.");
                    }
                    return true;
                case "shutdown":
                case "shutoff":
                    int delay = 60;
                    if (cmd.length >= 2) {
                        try {
                            delay = Integer.valueOf(cmd[1]);
                        } catch (NumberFormatException e) {
                            player.getPackets().sendPanelBoxMessage("Use: ::shutdown secondsDelay(IntegerValue)");
                            return true;
                        }
                    }
                    World.safeShutdown(delay);
                    return true;
                case "wolp":
                    Summoning.spawnFamiliar(player, Pouch.WOLPERTINGER);
                    return true;
                case "cstore":
                    int type = Integer.valueOf(cmd[1]);
                    int shop = Integer.valueOf(cmd[2]);
                    player.getCustomStore().sendInterface(player, type, shop);
                    return true;
                case "tradestore":
                    player.getTradeStore().openTrade();
                    return true;
                case "drop":
                    if (cmd.length < 1) {
                        player.getPackets()
                                .sendGameMessage("::drop 'amount of drops' 'amount of squares random generated'");
                        return true;
                    }
                    if (Integer.valueOf(cmd[1]) > 100)
                        return true;
                    if (Integer.valueOf(cmd[2]) > 32)
                        return true;
                    int itemIds[] = {4151, 15486, 11694, 11696, 11698, 11700, 11724, 11726, 11728, 11718, 11720, 11722,
                            6585, 6737, 6731, 6733, 6735, 14484, 15220, 15017, 15018, 15019, 15020, 4708, 4710, 4712, 4714,
                            4716, 4718, 4720, 4722, 4724, 4726, 4728, 4730, 4732, 4734, 4736, 4738, 4745, 4747, 4749, 4751,
                            4753, 4755, 4757, 4759};
                    for (int i = 0; i < Integer.valueOf(cmd[1]); i++) {
                        WorldTile tiles[] = {
                                new WorldTile(player.getX() + Utils.random(Integer.valueOf(cmd[2])),
                                        player.getY() + Utils.random(Integer.valueOf(cmd[2])), player.getPlane()),
                                new WorldTile(player.getX() - Utils.random(Integer.valueOf(cmd[2])),
                                        player.getY() + Utils.random(Integer.valueOf(cmd[2])), player.getPlane()),
                                new WorldTile(player.getX() - Utils.random(Integer.valueOf(cmd[2])),
                                        player.getY() - Utils.random(Integer.valueOf(cmd[2])), player.getPlane()),
                                new WorldTile(player.getX() + Utils.random(Integer.valueOf(cmd[2])),
                                        player.getY() - Utils.random(Integer.valueOf(cmd[2])), player.getPlane())};
                        World.addGroundItem(new Item(itemIds[Utils.getRandom(itemIds.length - 1)], 1),
                                new WorldTile(tiles[Utils.getRandom(tiles.length - 1)]), player, false, 0);
                    }
                    return true;
                case "raredrop":
                    if (cmd.length < 1) {
                        player.getPackets()
                                .sendGameMessage("::drop 'amount of drops' 'amount of squares random generated'");
                        return true;
                    }
                    if (Integer.valueOf(cmd[1]) > 100)
                        return true;
                    if (Integer.valueOf(cmd[2]) > 32)
                        return true;
                    int rareIds[] = {1038, 1040, 1042, 1044, 1046, 1048, 1050, 1053, 1055, 1057};
                    for (int i = 0; i < Integer.valueOf(cmd[1]); i++) {
                        WorldTile tiles[] = {
                                new WorldTile(player.getX() + Utils.random(Integer.valueOf(cmd[2])),
                                        player.getY() + Utils.random(Integer.valueOf(cmd[2])), player.getPlane()),
                                new WorldTile(player.getX() - Utils.random(Integer.valueOf(cmd[2])),
                                        player.getY() + Utils.random(Integer.valueOf(cmd[2])), player.getPlane()),
                                new WorldTile(player.getX() - Utils.random(Integer.valueOf(cmd[2])),
                                        player.getY() - Utils.random(Integer.valueOf(cmd[2])), player.getPlane()),
                                new WorldTile(player.getX() + Utils.random(Integer.valueOf(cmd[2])),
                                        player.getY() - Utils.random(Integer.valueOf(cmd[2])), player.getPlane())};
                        World.addGroundItem(new Item(rareIds[Utils.getRandom(rareIds.length - 1)], 1),
                                new WorldTile(tiles[Utils.getRandom(tiles.length - 1)]), player, false, 0);
                    }
                    return true;
                case "l":
                    player.getPackets().sendGameMessage("Sent all timers");
                    player.setOverload(35000);
                    player.setPrayerRenewal(35000);
                    player.setFreezeDelay(35000);
                    player.setTeleBlockDelay(35000);
                    player.setVengeance(35000);
                    return true;
                case "give":
                    StringBuilder itemName = new StringBuilder(cmd[1]);
                    int quantity = 1;
                    name = "";
                    if (cmd.length > 2) {
                        for (int i = 2; i < cmd.length; i++) {
                            if (cmd[i].startsWith("+")) {
                                quantity = Integer.parseInt(cmd[i].replace("+", ""));
                            }
                            if (cmd[i].startsWith("@")) {
                                name = cmd[i].replace("@", "");
                            } else if (cmd[i].startsWith("_")) {
                                itemName.append(" ").append(cmd[i]);
                            }
                        }
                    } else if (cmd.length > 3) {
                        for (int i = 3; i < cmd.length; i++) {
                            if (cmd[i].startsWith("+")) {
                                quantity = Integer.parseInt(cmd[i].replace("+", ""));
                            }
                            if (cmd[i].startsWith("@")) {
                                name = cmd[i].replace("@", "");
                            } else if (cmd[i].startsWith("_")) {
                                itemName.append(" ").append(cmd[i]);
                            }
                        }
                    }
                    String item1 = itemName.toString().toLowerCase().replace("[", "").replace("]", "").replace("(", "")
                            .replace(")", "").replaceAll(",", "'").replaceAll("_", " ").replace("#6", " (6)")
                            .replace("#5", " (5)").replace("#4", " (4)").replace("#3", " (3)").replace("#2", " (2)")
                            .replace("#1", " (1)").replace("#e", " (e)").replace("#i", " (i)").replace("#g", " (g)")
                            .replace("#or", " (or)").replace("#sp", " (sp)").replace("#t", " (t)");
                    target = World.getPlayerByDisplayName(name);
                    for (int i = 0; i < Utils.getItemDefinitionsSize(); i++) {
                        ItemDefinitions def = ItemDefinitions.getItemDefinitions(i);
                        if (def.getName().toLowerCase().equalsIgnoreCase(item1)) {
                            if (name.length() > 0 && target == null) {
                                player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
                                return true;
                            }
                            if (target != null) {
                                target.getInventory().addItem(i, quantity);
                                target.stopAll();
                            } else {
                                player.getInventory().addItem(i, quantity);
                                player.stopAll();
                            }
                            player.getPackets()
                                    .sendGameMessage("Gave item " + item1 + " (" + def.getId() + ")"
                                            + (quantity > 1 ? " x " + quantity + "" : "") + ""
                                            + (target != null ? " to " + target.getUsername() : "") + ".");
                            return true;
                        }
                    }
                    player.getPackets().sendGameMessage("Could not find item by the name " + item1 + ".");
                    return true;
                case "bgive":
                    itemName = new StringBuilder(cmd[1]);
                    quantity = 1;
                    name = "";
                    if (cmd.length > 2) {
                        for (int i = 2; i < cmd.length; i++) {
                            if (cmd[i].startsWith("+")) {
                                quantity = Integer.parseInt(cmd[i].replace("+", ""));
                            }
                            if (cmd[i].startsWith("@")) {
                                name = cmd[i].replace("@", "");
                            } else if (cmd[i].startsWith("_")) {
                                itemName.append(" ").append(cmd[i]);
                            }
                        }
                    } else if (cmd.length > 3) {
                        for (int i = 3; i < cmd.length; i++) {
                            if (cmd[i].startsWith("+")) {
                                quantity = Integer.parseInt(cmd[i].replace("+", ""));
                            }
                            if (cmd[i].startsWith("@")) {
                                name = cmd[i].replace("@", "");
                            } else if (cmd[i].startsWith("_")) {
                                itemName.append(" ").append(cmd[i]);
                            }
                        }
                    }
                    item1 = itemName.toString().toLowerCase().replace("[", "").replace("]", "").replace("(", "")
                            .replace(")", "").replaceAll(",", "'").replaceAll("_", " ").replace("#6", " (6)")
                            .replace("#5", " (5)").replace("#4", " (4)").replace("#3", " (3)").replace("#2", " (2)")
                            .replace("#1", " (1)").replace("#e", " (e)").replace("#i", " (i)").replace("#g", " (g)")
                            .replace("#or", " (or)").replace("#sp", " (sp)").replace("#t", " (t)");
                    target = World.getPlayerByDisplayName(name);
                    for (int i = 0; i < Utils.getItemDefinitionsSize(); i++) {
                        ItemDefinitions def = ItemDefinitions.getItemDefinitions(i);
                        if (def.getName().toLowerCase().equalsIgnoreCase(item1)) {
                            if (name.length() > 0 && target == null) {
                                player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
                                return true;
                            }
                            if (target != null) {
                                target.getBank().addItem(i, quantity, true);
                            } else {
                                player.getBank().addItem(i, quantity, true);
                            }
                            player.getPackets()
                                    .sendGameMessage("Gave item " + item1 + " (" + def.getId() + ")"
                                            + (quantity > 1 ? " x " + quantity + "" : "") + ""
                                            + (target != null ? " to " + target.getUsername() : "") + ".");
                            return true;
                        }
                    }
                    player.getPackets().sendGameMessage("Could not find item by the name " + item1 + ".");
                    return true;
                case "healmode":
                    if (!isDeveloper(player))
                        return true;
                    player.healMode = player.healMode ? false : true;
                    player.sm("You have successfully " + (player.healMode ? "enabled" : "disabled") + " immunity to hits.");
                    return true;
                case "droptest":
                    player.dropTesting = player.dropTesting ? false : true;
                    player.getPackets()
                            .sendGameMessage("Drop testing: " + (player.dropTesting ? "Enabled" : "Disabled") + ".");
                    return true;
                case "dropamount":
                    int droptimes = Integer.valueOf(cmd[1]);
                    player.dropTestingAmount = droptimes;
                    player.getPackets().sendGameMessage("Drop testing amount set to: " + (droptimes) + ".");
                    return true;
                case "zoom":
                    int zoomId = Integer.valueOf(cmd[1]);
                    player.getPackets().sendGlobalConfig(184, zoomId);
                    return true;
                case "global":
                    int globalConfigId = Integer.valueOf(cmd[1]);
                    int value = Integer.valueOf(cmd[2]);
                    player.getPackets().sendGlobalConfig(globalConfigId, value);
                    player.getPackets().sendGameMessage("Sent global config: " + globalConfigId + "; " + value);
                    return true;
                case "varbit":
                    globalConfigId = Integer.valueOf(cmd[1]);
                    value = Integer.valueOf(cmd[2]);
                    player.getVarsManager().sendVarBit(globalConfigId, value);
                    player.getPackets().sendGameMessage("Sent varbit: " + globalConfigId + "; " + value);
                    player.getInventory().refresh();
                    for (int i = 0; i < 9; i++)
                        player.getEquipment().refresh(i);
                    return true;
                case "var":
                    int configValues = Integer.valueOf(cmd[2]);
                    int configId = Integer.valueOf(cmd[1]);
                    player.getVarsManager().sendVar(configId, configValues);
                    // player.getVarsManager().sendVar(configId, configValue);
                    player.getPackets().sendGameMessage("Sent var: " + configId + "; " + configValues);
                    return true;
                case "varloop":
                    int var1 = Integer.valueOf(cmd[1]);
                    int var2 = Integer.valueOf(cmd[2]);
                    int varValue = Integer.valueOf(cmd[3]);
                    for (int i = var1; i < var2; i++) {
                        player.sm(i + "");
                        player.getVarsManager().sendVar(i, varValue);
                    }
                    player.sm("sent vars " + var1 + "-" + var2 + ":value:" + varValue);
                    return true;
                case "varbitloop":
                    var1 = Integer.valueOf(cmd[1]);
                    var2 = Integer.valueOf(cmd[2]);
                    varValue = Integer.valueOf(cmd[3]);
                    for (int i = var1; i < var2; i++)
                        player.getVarsManager().sendVarBit(i, varValue);
                    player.sm("sent varbit " + var1 + "-" + var2 + ":value:" + varValue);
                    return true;
                case "globalloop":
                    var1 = Integer.valueOf(cmd[1]);
                    var2 = Integer.valueOf(cmd[2]);
                    varValue = Integer.valueOf(cmd[3]);
                    for (int i = var1; i < var2; i++)
                        player.getPackets().sendGlobalConfig(i, varValue);
                    player.sm("sent global config " + var1 + "-" + var2 + ":value:" + varValue);
                    return true;
                case "sendstring":
                    int stringId = Integer.valueOf(cmd[1]);
                    String stringText = cmd[2].substring(cmd[2].indexOf(" ") + 1);
                    player.getPackets().sendGlobalString(stringId, stringText == null ? "Test" : stringText);
                    player.getPackets().sendGameMessage("Sent global string: " + stringId + "; " + stringText);
                    return true;
                case "resetzoom":
                    player.getPackets().sendGlobalConfig(184, 250);
                    return true;
                case "skull":
                    player.skullDelay = 2000; // 20minutes
                    player.skullId = 0;
                    player.getAppearence().generateAppearenceData();
                    return true;
                case "direction":
                    player.getPackets()
                            .sendGameMessage(
                                    player.getDirection() == 0 ? "South"
                                            : player.getDirection() == 2048 ? "South-west"
                                            : player.getDirection() == 4096 ? "West"
                                            : player.getDirection() == 6144 ? "North-west"
                                            : player.getDirection() == 8192 ? "North"
                                            : player.getDirection() == 10240 ? "North-east"
                                            : player.getDirection() == 12288
                                            ? "East"
                                            : "South-east");
                    player.getPackets().sendGameMessage("Id " + player.getDirection());
                    return true;
                case "resettask":
                    player.getSlayerManager().resetTask(false, false);
                    player.getPackets().sendGameMessage("Your task have been reset.");
                    player.refreshTask();
                    return true;
                case "resettaskother":
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    if (target != null) {
                        target.getSlayerManager().resetTask(false, false);
                    } else {
                        name = Utils.formatPlayerNameForProtocol(name);
                        if (!AccountCreation.exists(name)) {
                            player.getPackets().sendGameMessage(
                                    "Account name " + Utils.formatPlayerNameForDisplay(name) + " doesn't exist.");
                            return true;
                        }
                        target = AccountCreation.loadPlayer(name);
                        target.setUsername(name);
                        target.getSlayerManager().resetTask(false, false);
                        AccountCreation.savePlayer(target);
                    }
                    return true;
                case "completetask":
                    player.getSlayerManager().completedTasks++;
                    player.getSlayerManager().resetTask(true, true);
                    player.refreshTask();
                    return true;
                case "removebankitem":
                    if (cmd.length == 3 || cmd.length == 4) {
                        Player p = World.getPlayerByDisplayName(Utils.formatPlayerNameForDisplay(cmd[1]));
                        int amounts = 1;
                        if (cmd.length == 4) {
                            try {
                                amounts = Integer.parseInt(cmd[3]);
                            } catch (NumberFormatException e) {
                                amounts = 1;
                            }
                        }
                        if (p != null) {
                            try {
                                Item itemToRemove = new Item(Integer.parseInt(cmd[2]), amounts);
                                boolean multiple = itemToRemove.getAmount() > 1;
                                p.getBank().removeItem(itemToRemove.getId());
                                p.getPackets().sendGameMessage(player.getDisplayName() + " has removed "
                                        + (multiple ? itemToRemove.getAmount() : "one") + " "
                                        + itemToRemove.getDefinitions().getName() + (multiple ? "s" : "from your bank."));
                                player.getPackets()
                                        .sendGameMessage("You have removed " + (multiple ? itemToRemove.getAmount() : "one")
                                                + " " + itemToRemove.getDefinitions().getName() + (multiple ? "s" : "")
                                                + " from " + p.getDisplayName() + " bank ");
                                return true;
                            } catch (NumberFormatException e) {
                            }
                        }
                    }
                    player.getPackets().sendGameMessage("Use: ::removebankitem player id (optional:amount)");
                    return true;
                case "emptybank":
                    player.getDialogueManager().startDialogue("EmptyBank");
                    return true;
                case "kill":
                    if (!isDeveloper(player))
                        return true;
                    if (!isDeveloper(player)) {
                        player.getPackets().sendGameMessage("You don't have permission to use this command.");
                        return true;
                    }
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    if (target == null)
                        return true;
                    target.applyHit(new Hit(target, player.getHitpoints(), HitLook.REGULAR_DAMAGE));
                    target.stopAll();
                    return true;
                case "makemember":
                    if (cmd.length == 2 || cmd.length == 3) {
                        Player p = World.getPlayerByDisplayName(Utils.formatPlayerNameForDisplay(cmd[1]));
                        int amountz = 1;
                        if (cmd.length == 3) {
                            try {
                                amountz = Integer.parseInt(cmd[2]);
                            } catch (NumberFormatException e) {
                                amountz = 1;
                            }
                        }
                        if (p != null) {
                            try {
                                p.makeMember(amountz);
                                p.member = true;
                                p.getPackets().sendGameMessage("You recieve " + amountz + " days of member.");
                                return true;
                            } catch (NumberFormatException e) {
                            }
                        }
                    }
                    player.getPackets().sendGameMessage("Use: ::makemember username amount of days (optional:amount)");
                    return true;
                case "removemember":
                    player.member = false;
                    player.memberTill = 0;
                    return true;
                case "giveitem":
                    if (cmd.length == 3 || cmd.length == 4) {
                        Player p = World.getPlayerByDisplayName(Utils.formatPlayerNameForDisplay(cmd[1]));
                        int amountx = 1;
                        if (cmd.length == 4) {
                            try {
                                amountx = Integer.parseInt(cmd[3]);
                            } catch (NumberFormatException e) {
                                amountx = 1;
                            }
                        }
                        if (p != null) {
                            try {
                                Item itemToGive = new Item(Integer.parseInt(cmd[2]), amountx);
                                boolean multiple = itemToGive.getAmount() > 1;
                                if (!p.getInventory().addItem(itemToGive)) {
                                    p.getBank().addItem(itemToGive.getId(), itemToGive.getAmount(), true);
                                }
                                p.getPackets()
                                        .sendGameMessage(player.getDisplayName() + " has given you "
                                                + (multiple ? itemToGive.getAmount() : "one") + " "
                                                + itemToGive.getDefinitions().getName() + (multiple ? "s" : ""));
                                player.getPackets()
                                        .sendGameMessage("You have given " + (multiple ? itemToGive.getAmount() : "one")
                                                + " " + itemToGive.getDefinitions().getName() + (multiple ? "s" : "")
                                                + " to " + p.getDisplayName());
                                return true;
                            } catch (NumberFormatException e) {
                            }
                        }
                    }
                    player.getPackets().sendGameMessage("Use: ::giveitem player id (optional:amount)");
                    return true;
                case "god":
                    player.getPackets().sendGameMessage("Godmode is now "
                            + (player.getTemporaryAttributtes().get("GODMODE") != null ? "Inactive" : "Active."));
                    if (player.getTemporaryAttributtes().get("GODMODE") != null)
                        player.getTemporaryAttributtes().remove("GODMODE");
                    else
                        player.getTemporaryAttributtes().put("GODMODE", 0);
                    return true;
                case "givepkp":
                    if (!isDeveloper(player)) {
                        player.getPackets().sendGameMessage("You don't have permission to use this command.");
                        return true;
                    }
                    if (cmd.length == 2 || cmd.length == 3) {
                        Player p = World.getPlayerByDisplayName(Utils.formatPlayerNameForDisplay(cmd[1]));
                        int amountc = 1;
                        if (cmd.length == 3) {
                            try {
                                amountc = Integer.parseInt(cmd[2]);
                            } catch (NumberFormatException e) {
                                amountc = 1;
                            }
                        }
                        if (p != null) {
                            try {
                                p.PKP += amountc;
                                p.getPackets().sendGameMessage("You recieve " + amountc + " Pk points.");
                                player.getPackets().sendGameMessage("You gave " + amountc + " pkp to " + p.getDisplayName());
                                player.getPackets().sendGameMessage("They now have " + p.PKP + "");

                                return true;
                            } catch (NumberFormatException e) {
                            }
                        }
                    }
                    player.getPackets().sendGameMessage("Use: ::giveitem player id (optional:amount)");
                    return true;
                case "setks":
                    if (cmd.length == 2 || cmd.length == 3) {
                        Player p = World.getPlayerByDisplayName(Utils.formatPlayerNameForDisplay(cmd[1]));
                        int amountb = 1;
                        if (cmd.length == 3) {
                            try {
                                amountb = Integer.parseInt(cmd[2]);
                            } catch (NumberFormatException e) {
                                amountb = 1;
                            }
                        }
                        if (p != null) {
                            try {
                                p.killStreak += amountb;
                                p.getPackets().sendGameMessage("You recieve " + amountb + " killstreaks.");
                                return true;
                            } catch (NumberFormatException e) {
                            }
                        }
                    }
                    player.getPackets().sendGameMessage("Use: ::killstreak player amount of kills (optional:amount)");
                    return true;
                case "kills":
                    if (cmd.length == 2 || cmd.length == 3) {
                        Player p = World.getPlayerByDisplayName(Utils.formatPlayerNameForDisplay(cmd[1]));
                        int amount1 = 1;
                        if (cmd.length == 3) {
                            try {
                                amount1 = Integer.parseInt(cmd[2]);
                            } catch (NumberFormatException e) {
                                amount1 = 1;
                            }
                        }
                        if (p != null) {
                            try {
                                p.killCount += amount1;
                                p.getPackets().sendGameMessage("You recieve " + amount1 + " kills.");
                                return true;
                            } catch (NumberFormatException e) {
                            }
                        }
                    }
                    player.getPackets().sendGameMessage("Use: ::kills player amount of kills (optional:amount)");
                    return true;
                case "deaths":
                    if (cmd.length == 2 || cmd.length == 3) {
                        Player p = World.getPlayerByDisplayName(Utils.formatPlayerNameForDisplay(cmd[1]));
                        int amount1 = 1;
                        if (cmd.length == 3) {
                            try {
                                amount1 = Integer.parseInt(cmd[2]);
                            } catch (NumberFormatException e) {
                                amount1 = 1;
                            }
                        }
                        if (p != null) {
                            try {
                                p.deathCount += amount1;
                                p.getPackets().sendGameMessage("You recieve " + amount1 + " kills.");
                                return true;
                            } catch (NumberFormatException e) {
                            }
                        }
                    }
                    player.getPackets().sendGameMessage("Use: ::kills player amount of kills (optional:amount)");
                    return true;
                case "checkbank":
                    String playername = "";
                    for (int i = 1; i < cmd.length; i++) {
                        playername += cmd[i] + ((i == cmd.length - 1 ? "" : " "));
                    }
                    playername = Utils.formatPlayerNameForProtocol(playername);
                    if (!AccountCreation.exists(playername)) {
                        player.getPackets()
                                .sendGameMessage("No such account named " + playername + " was found in the database.");
                        return true;
                    }

                    Player p211 = World.getPlayerByDisplayName(playername);
                    p211 = AccountCreation.loadPlayer(playername);
                    p211.setUsername(playername);
                    player.getPackets().sendItems(95, p211.getBank().getContainerCopy());
                    player.getBank().openPlayerBank(p211);
                    return true;
                case "healother":
                    if (cmd.length == 2 || cmd.length == 3) {
                        Player p = World.getPlayerByDisplayName(Utils.formatPlayerNameForDisplay(cmd[1]));
                        int amount2 = 1;
                        if (cmd.length == 3) {
                            try {
                                amount2 = Integer.parseInt(cmd[2]);
                            } catch (NumberFormatException e) {
                                amount2 = 1;
                            }
                        }
                        if (p != null) {
                            try {
                                p.getPrayer().restorePrayer(
                                        (int) ((int) (Math.floor(p.getSkills().getLevelForXp(Skills.PRAYER) * 2.5) + 990)
                                                * p.getAuraManager().getPrayerPotsRestoreMultiplier()));
                                p.getPoison().makePoisoned(0);
                                p.setRunEnergy(100);
                                p.heal(p.getMaxHitpoints());
                                p.getSkills().restoreSkills();
                                p.getCombatDefinitions().resetSpecialAttack();
                                p.getAppearence().generateAppearenceData();
                                p.heal(amount2);
                                p.getPackets().sendGameMessage("You were healed.");
                                return true;
                            } catch (NumberFormatException e) {
                            }
                        }
                    }
                    player.getPackets().sendGameMessage("Use: ::healother player hp (optional:amount)");
                    return true;
                case "removealloffers":
                    player.getGeManager().cancelOffer();
                    GrandExchange.removeAllOffers();
                    player.getPackets().sendGameMessage("Removed all Grand Exchange offers.");
                    return true;
                case "permban":
                    if (!isDeveloper(player)) {
                        player.getPackets().sendGameMessage("You don't have permission to use this command.");
                        return true;
                    }
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    if (target != null) {
                        if (target.isDeveloper()) {
                            target.getPackets()
                                    .sendGameMessage("<col=ff0000>" + player.getDisplayName() + " just tried to ban you!");
                            return true;
                        }
                        if (target.getGeManager() == null) {
                            target.setGeManager(new GrandExchangeManager());
                        }
                        target.getGeManager().setPlayer(target);
                        target.getGeManager().init();
                        GrandExchange.removeOffers(target);
                        target.setPermBanned(true);
                        target.getSession().getChannel().close();
                        player.getPackets().sendGameMessage("You have perm banned: " + target.getDisplayName() + ".");
                        player.getPackets()
                                .sendGameMessage("Display: " + target.getDisplayName() + " User: " + target.getUsername());
                    } else {
                        name = Utils.formatPlayerNameForProtocol(name);
                        if (!AccountCreation.exists(name)) {
                            player.getPackets().sendGameMessage(
                                    "Account name " + Utils.formatPlayerNameForDisplay(name) + " doesn't exist.");
                            return true;
                        }
                        target = AccountCreation.loadPlayer(name);
                        if (target.isDeveloper()) {
                            return true;
                        }
                        if (target.getGeManager() == null) {
                            target.setGeManager(new GrandExchangeManager());
                        }
                        target.getGeManager().setPlayer(target);
                        target.getGeManager().init();
                        GrandExchange.removeOffers(target);
                        target.setUsername(name);
                        target.setPermBanned(true);
                        player.getPackets()
                                .sendGameMessage("You have perm banned: " + Utils.formatPlayerNameForDisplay(name) + ".");
                        AccountCreation.savePlayer(target);
                    }
                    return true;
                case "ipban":
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    boolean loggedIn = true;
                    if (target != null) {
                        if (target.isDeveloper())
                            return true;
                        IPBanL.ban(target, loggedIn);
                        player.getPackets().sendGameMessage(
                                "You've permanently ipbanned " + (loggedIn ? target.getDisplayName() : name) + ".");
                    }
                    return true;
                case "heal":
                    player.getPrayer().restorePrayer(
                            (int) ((int) (Math.floor(player.getSkills().getLevelForXp(Skills.PRAYER) * 2.5) + 990)
                                    * player.getAuraManager().getPrayerPotsRestoreMultiplier()));
                    if (player.getPoison().isPoisoned())
                        player.getPoison().makePoisoned(0);
                    player.setRunEnergy(100);
                    player.heal(player.getMaxHitpoints());
                    player.getSkills().restoreSkills();
                    player.getCombatDefinitions().resetSpecialAttack();
                    player.getAppearence().generateAppearenceData();
                    int hitpointsModification = (int) (player.getMaxHitpoints() * 0.15);
                    player.heal(hitpointsModification + 20, hitpointsModification);
                    return true;

                case "masterallskills":
                    if (cmd.length < 2) {
                        for (int skill1 = 0; skill1 < 25; skill1++)
                            player.getSkills().addXp(skill1, 150000000);
                        return true;
                    }
                    try {
                        player.getSkills().addXp(Integer.valueOf(cmd[1]), 150000000);
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("Use: ::master skill");
                    }
                    return true;

                case "setptsother":
                    if (cmd.length == 2 || cmd.length == 3) {
                        Player p = World.getPlayerByDisplayName(Utils.formatPlayerNameForDisplay(cmd[1]));
                        int amount3 = 1;
                        if (cmd.length == 3) {
                            try {
                                amount3 = Integer.parseInt(cmd[2]);
                            } catch (NumberFormatException e) {
                                amount3 = 1;
                            }
                        }
                        if (p != null) {
                            try {
                                player.getPackets().sendGameMessage("You gave " + p.getUsername() + " "
                                        + Utils.getFormattedNumber(amount3, ',') + " " + Settings.SERVER_NAME + " points.");
                                p.getPackets().sendGameMessage("You recieved " + Utils.getFormattedNumber(amount3, ',') + " "
                                        + Settings.SERVER_NAME + " points.");
                                p.setAvalonPoints(p.getAvalonPoints() + amount3);
                                return true;
                            } catch (NumberFormatException e) {
                            }
                        }
                    } else {
                        player.getPackets().sendGameMessage("Use: ::setkillsother username amount");
                    }
                    return true;

                case "setkillsother":
                    if (cmd.length == 2 || cmd.length == 3) {
                        Player p = World.getPlayerByDisplayName(Utils.formatPlayerNameForDisplay(cmd[1]));
                        int amount4 = 1;
                        if (cmd.length == 3) {
                            try {
                                amount4 = Integer.parseInt(cmd[2]);
                            } catch (NumberFormatException e) {
                                amount4 = 1;
                            }
                        }
                        if (p != null) {
                            try {
                                p.killCount = amount4;
                                return true;
                            } catch (NumberFormatException e) {
                            }
                        }
                    } else {
                        player.getPackets().sendGameMessage("Use: ::setkillsother username amount");
                    }
                    return true;

                case "setduelkillsother":
                    if (cmd.length == 2 || cmd.length == 3) {
                        Player p = World.getPlayerByDisplayName(Utils.formatPlayerNameForDisplay(cmd[1]));
                        int amount5 = 1;
                        if (cmd.length == 3) {
                            try {
                                amount5 = Integer.parseInt(cmd[2]);
                            } catch (NumberFormatException e) {
                                amount5 = 1;
                            }
                        }
                        if (p != null) {
                            try {
                                p.setDuelkillCount(amount5);
                                return true;
                            } catch (NumberFormatException e) {
                            }
                        }
                    } else {
                        player.getPackets().sendGameMessage("Use: ::setduelkillsother username amount");
                    }
                    return true;

                case "settitlecolor":
                case "settitlecolour":
                case "changetitlecolor":
                case "changetitlecolour":
                case "titlecolor":
                case "titlecolour":
                    player.getPackets().sendRunScript(109, new Object[]{"Please enter the title color in HEX format."});
                    player.temporaryAttribute().put("titlecolor", Boolean.TRUE);
                    return true;

                case "mb":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't do that command here.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2539, 4716, 0));
                    player.getPackets().sendGameMessage("You have teleported to mage bank.");
                    return true;

                case "clanwars":
                case "cw":
                case "clws":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't do that command here.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2970, 9679, 0));
                    player.getPackets().sendGameMessage("You have teleported to clan wars.");
                    return true;

                case "east":
                case "easts":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't do that command here.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3349, 3647, 0));
                    player.getPackets().sendGameMessage("You have teleported to east dragons.");
                    return true;

                case "edgeville":
                case "edge":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't use " + cmd[0] + " at this location.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3082, 3545, 0));
                    player.getPackets().sendGameMessage("You have teleported to edgeville.");
                    return true;

                case "gdz":
                case "gds":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't do that command here.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3289, 3886, 0));
                    player.getPackets().sendGameMessage("You have teleported to greater demons.");
                    return true;

                case "kbd":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't do that command here.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3032, 3836, 0));
                    player.getPackets().sendGameMessage("You have teleported outside king black dragon lair.");
                    return true;

                case "44ports":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't do that command here.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2980, 3867, 0));
                    player.getPackets().sendGameMessage("You have teleported lvl 44 wilderness port.");
                    return true;

                case "iceplatue":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't do that command here.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2962, 3918, 0));
                    player.getPackets().sendGameMessage("You have teleported to ice platue.");
                    return true;

                case "50ports":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't do that command here.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3308, 3916, 0));
                    player.getPackets().sendGameMessage("You have teleported to lvl 50 wilderness portal.");
                    return true;

                case "castle":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't do that command here.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3012, 3631, 0));
                    player.getPackets().sendGameMessage("You have teleported to castle.");
                    return true;

                case "bh":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't do that command here.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3153, 3709, 0));
                    player.getPackets().sendGameMessage("You have teleported to bounty hunter crater.");
                    return true;

                case "altar":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't do that command here.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2950, 3821, 0));
                    player.getPackets().sendGameMessage("You have teleported to wilderness altar.");
                    return true;

                case "wests":
                case "west":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't do that command here.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(2978, 3598, 0));
                    player.getPackets().sendGameMessage("You have teleported to west dragons.");
                    return true;

                case "zerk":
                case "zerkspot":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't do that command here.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3043, 3552, 0));
                    player.getPackets().sendGameMessage("You have teleported to zerker spot.");
                    return true;

                case "bridspot":
                case "brid":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't do that command here.");
                        return true;
                    }
                    Magic.sendNormalTeleportSpell(player, 0, 0, new WorldTile(3013, 3553, 0));
                    player.getPackets().sendGameMessage("You have teleported to hybridding spot.");
                    return true;

                case "teles":
                    player.getInterfaceManager().sendInterface(275);
                    player.getPackets().sendIComponentText(275, 1, "*Teleports*");
                    player.getPackets().sendIComponentText(275, 10, "");
                    player.getPackets().sendIComponentText(275, 11,
                            "::zerk *Teleports to western side of edgeville. SINGLE");
                    player.getPackets().sendIComponentText(275, 12, "::easts *Teleports to lvl 20 east dragons. SINGLE");
                    player.getPackets().sendIComponentText(275, 13, "::wests *Teleports to lvl 13 west dragons. SINGLE");
                    player.getPackets().sendIComponentText(275, 14, "::mb *Teleports inside the mage bank. NOT WILDY");
                    player.getPackets().sendIComponentText(275, 15,
                            "::brid *Teleports to west side of edgeville wilderness. SINGLE");
                    player.getPackets().sendIComponentText(275, 16, "::gdz *Teleports to Greater demons in lvl 48. MULTI");
                    player.getPackets().sendIComponentText(275, 17,
                            "::44ports *Teleports to lvl 44 wilderness portal. SINGLE");
                    player.getPackets().sendIComponentText(275, 18,
                            "::iceplatue *Teleports to ice platue in lvl 50 wilderness. SINGLE");
                    player.getPackets().sendIComponentText(275, 19,
                            "::kbd *Teleports outside king black dragon lair. MULTI");
                    player.getPackets().sendIComponentText(275, 20,
                            "::50ports *Teleports to lvl 50 wilderness portal. MULTI");
                    player.getPackets().sendIComponentText(275, 21,
                            "::bh *Teleports inside the Bounty hunter crate. MULTI");
                    player.getPackets().sendIComponentText(275, 22, "::revs *Teleports to rev cave. SINGLE & MULTI");
                    player.getPackets().sendIComponentText(275, 23,
                            "::altar *Teleports to an altar deep in west of wilderness.");
                    player.getPackets().sendIComponentText(275, 24,
                            "::castle *Teleports to castle near west dragons. MULTI");
                    return true;

                case "showrisk":
                    Integer[][] slots = ButtonHandler.getItemSlotsKeptOnDeath(player, player.isAtWild(), player.hasSkull(),
                            player.getPrayer().usingPrayer(0, 10) || player.getPrayer().usingPrayer(1, 0));
                    Item[][] riskitems = ButtonHandler.getItemsKeptOnDeath(player, slots);
                    long riskedWealth = 0;
                    long carriedWealth = 0;
                    for (Item item11 : riskitems[1]) {
                        if (item11 == null)
                            continue;
                        carriedWealth = riskedWealth += GrandExchange.getPrice(item11.getId()) * item11.getAmount();
                    }
                    player.getPackets()
                            .sendGameMessage("My risk is: " + Utils.getFormattedNumber(carriedWealth, ',') + " coins.");
                    player.setNextForceTalk(
                            new ForceTalk("My risk is: " + Utils.getFormattedNumber(carriedWealth, ',') + " coins."));
                    return true;

                case "score":
                case "kdr":
                    double kill = player.getKillCount();
                    double death = player.getDeathCount();
                    double dr = kill / death;
                    player.setNextForceTalk(new ForceTalk("Kills: " + player.getKillCount() + " Deaths: "
                            + player.getDeathCount() + " Ratio: " + new DecimalFormat("##.#").format(dr)));
                    return true;

                case "ks":
                case "killstreak":
                    player.setNextForceTalk(new ForceTalk("My current killstreak: " + player.killStreak));
                    return true;

                case "players":
                    player.getPackets().sendGameMessage("There are currently " + World.getPlayers().size()
                            + " players playing " + Settings.SERVER_NAME + ".");
                    return true;

                case "title":
                    if (cmd.length < 2) {
                        player.getPackets().sendGameMessage("Use: ::title id");
                        return true;
                    }
                    try {
                        player.getAppearence().setTitle(Integer.valueOf(cmd[1]));
                        player.getPackets().sendGameMessage("Title set to: " + player.getAppearence().getTitleString());
                    } catch (NumberFormatException e) {
                        player.getPackets().sendGameMessage("Use: ::title id");
                    }
                    return true;
                case "customtitle":
                    if (player.isMember()) {
                        player.getPackets().sendGameMessage("You need to be at least a member to use ::customtitle.");
                        return true;
                    } else {
                        player.temporaryAttribute().put("customtitle", Boolean.TRUE);
                        player.getPackets().sendInputNameScript("Enter your custom title");
                        player.getPackets().sendGameMessage("To get the title AFTER your name use commands ::customtitle.");
                    }
                    return true;

                case "lividshop":
                    player.getInterfaceManager().sendInterface(1083);
                    player.getPackets().sendHideIComponent(1083, 139, true);
                    return true;

                case "pointsplz":
                    player.getLivid().addProduce(10000);
                    return true;

                case "inters":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("Use: ::inter interfaceId");
                        return true;
                    }
                    try {
                        int interId1 = Integer.valueOf(cmd[1]);
                        for (int componentId = 0; componentId < Utils
                                .getInterfaceDefinitionsComponentsSize(interId1); componentId++) {
                            player.getPackets().sendIComponentText(interId1, componentId, "cid: " + componentId);
                        }
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("Use: ::inter interfaceId");
                    }
                    return true;

                case "customtitle1":
                case "customtitle2":
                    if (!player.isMember()) {
                        player.getPackets().sendGameMessage("You need to be at least a member to use ::customtitle.");
                        return true;
                    } else {
                        player.temporaryAttribute().put("setcustom_title2", Boolean.TRUE);
                        player.getPackets().sendInputNameScript("Enter your custom title");
                        player.getPackets().sendGameMessage("To get the title BEFORE your name use commands ::customtitle");
                    }
                    return true;

                case "checkoffers":
                    GrandExchange.sendOfferTracker(player);
                    return true;

                case "setdisplay":
                case "changedisplay":
                    if (!player.isMember()) {
                        player.getPackets().sendGameMessage("You need to be at least a member to use ::setdisplay.");
                        return true;
                    } else {
                        player.temporaryAttribute().put("setdisplay", Boolean.TRUE);
                        player.getPackets().sendInputNameScript("Enter the display name you wish:");
                    }
                    return true;
                case "lockxp":
                    player.setXpLocked(player.isXpLocked() ? false : true);
                    player.getPackets()
                            .sendGameMessage("You have " + (player.isXpLocked() ? "unlocked" : "locked") + " your xp.");
                    return true;
                case "hideyell":
                case "toggleyell":
                    player.setYellOff(!player.isYellOff());
                    player.getPackets()
                            .sendGameMessage("You have turned " + (player.isYellOff() ? "off" : "on") + " yell.");
                    return true;
                case "changepass":
                    String message = "";
                    for (int i = 1; i < cmd.length; i++)
                        message += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    if (message.length() > 15 || message.length() < 5) {
                        player.getPackets().sendGameMessage("You cannot set your password to over 15 chars.");
                        return true;
                    }
                    player.setPassword(Encrypt.encryptSHA1(cmd[1]));
                    player.getPackets().sendGameMessage("You changed your password! Your new password is " + cmd[1] + ".");
                    return true;
                case "setlvl":
                case "setlevel":
                    if (!player.canUseCommand()) {
                        player.getPackets().sendGameMessage("You can't do that command here.");
                        return true;
                    }
                    if (player.getEquipment().wearingArmour()) {
                        player.getPackets().sendGameMessage("You can't wear any armour when using this command.");
                        return true;
                    }
                    name = "";
                    for (int i = 3; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    if (cmd.length < 3/* && player.getRights() == 0 */) {
                        player.getPackets().sendGameMessage("Usage ::setlevel skillId level");
                        return true;
                    }
                    try {
                        int skill1 = Integer.parseInt(cmd[1]);
                        int level = Integer.parseInt(cmd[2]);
                        if (level < 0 || level > 99) {
                            player.getPackets().sendGameMessage("Please choose a valid level.");
                            return true;
                        }
                        if (level < player.getSkills().getLevel(skill1)) {
                            player.getSkills().set(skill1, level);
                            player.getSkills().setXp(skill1, Skills.getXPForLevel(level));
                        } else {
                            System.out.println("Current Xp: " + Utils.getFormattedNumber(player.getSkills().getXp(skill1), ',') + " Xp for level: " + level + ": " + Utils.getFormattedNumber(Skills.getXPForLevel(level), ','));
                            System.out.println("xp to add: " + (Utils.getFormattedNumber((int) (Skills.getXPForLevel(level) - player.getSkills().getXp(skill1)), ',')));
                            player.getSkills().addXpNoBonus(skill1, Skills.getXPForLevel(level) - player.getSkills().getXp(skill1));
                        }
                        player.getAppearence().generateAppearenceData();
                        player.getSkills().switchXPPopup(true);
                        player.getSkills().switchXPPopup(true);
                        return true;
                    } catch (NumberFormatException e) {
                        player.getPackets().sendGameMessage("Usage ::setlevel skillId level");
                    }
                    return true;
                case "setdeathsother":
                    if (cmd.length == 2 || cmd.length == 3) {
                        Player p = World.getPlayerByDisplayName(Utils.formatPlayerNameForDisplay(cmd[1]));
                        int amount5 = 1;
                        if (cmd.length == 3) {
                            try {
                                amount5 = Integer.parseInt(cmd[2]);
                            } catch (NumberFormatException e) {
                                amount5 = 1;
                            }
                        }
                        if (p != null) {
                            try {
                                p.deathCount = amount5;
                                return true;
                            } catch (NumberFormatException e) {
                            }
                        }
                    } else {
                        player.getPackets().sendGameMessage("Use: ::setdeathsother username amount");
                    }
                    return true;
                case "bitem":
                    if (player.isAtWild()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " in the wilderness.");
                        return true;
                    }
                    try {
                        int itemId1 = Integer.valueOf(cmd[1]);
                        int amount3 = 1;
                        if (cmd.length == 3)
                            amount3 = Integer.parseInt(cmd[2]);
                        player.getBank().addItem(itemId1, amount3, true);
                        player.getPackets().sendGameMessage("You spawn " + amount3 + " x "
                                + ItemDefinitions.getItemDefinitions(itemId1).getName() + " to your bank.");
                    } catch (NumberFormatException e) {
                        player.getPackets().sendGameMessage("Use: ::bankitem id (optional:amount)");
                    }
                    return true;
                case "item":
                    if (player.isAtWild()) {
                        player.getPackets().sendGameMessage("You can't use ::" + cmd[0] + " in the wilderness.");
                        return true;
                    }
                    try {
                        int itemId1 = Integer.valueOf(cmd[1]);
                        int amount4 = 1;
                        if (cmd.length == 3)
                            amount4 = Integer.parseInt(cmd[2]);
                        player.getInventory().addItem(itemId1, amount4);
                        player.getPackets().sendGameMessage("You spawn " + amount4 + " x "
                                + ItemDefinitions.getItemDefinitions(itemId1).getName() + ".");
                    } catch (NumberFormatException e) {
                        player.getPackets().sendGameMessage("Use: ::item id (optional:amount)");
                    }
                    return true;

                case "sound":
                    int soundId = Integer.valueOf(cmd[1]);
                    player.sm("play sound " + soundId);
                    player.getPackets().sendSound(soundId, 0, 0);
                    return true;

                case "sound2":
                    soundId = Integer.valueOf(cmd[1]);
                    player.sm("play sound " + soundId);
                    player.getPackets().sendSound(soundId, 0, 1);
                    return true;

                case "s":
                    soundId = Integer.valueOf(cmd[1]);
                    player.sm("play sound " + soundId);
                    player.getPackets().sendSound(soundId, 0, 2);
                    return true;

                case "korasi":
                    player.getPackets().sendSound(3865, 0, 1);
                    player.getPackets().sendSound(3853, 0, 1);
                    return true;

                case "setlevelother":
                    name = "";
                    for (int i = 3; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    if (cmd.length < 4) {
                        player.getPackets().sendGameMessage("Usage ::setlevel skillId level username");
                        return true;
                    }
                    try {
                        int skill = Integer.parseInt(cmd[1]);
                        int level = Integer.parseInt(cmd[2]);
                        if (level < 0 || level > 99) {
                            player.getPackets().sendGameMessage("Please choose a valid level.");
                            return true;
                        }
                        if (skill == 3 && level < 10) {
                            player.getPackets().sendGameMessage("You cannot have lower than 10 hitpoints.");
                            return true;
                        }
                        if (skill < 0 || skill > 24) {
                            player.getPackets().sendGameMessage("You can only set combat stats 0-24");
                            return true;
                        }
                        target.getPackets().sendGameMessage("You were given " + level + " levels in " + skill + ".");
                        target.getSkills().set(skill, level);
                        target.getSkills().setXp(skill, Skills.getXPForLevel(level));
                        target.getDialogueManager().startDialogue("LevelUp", skill);
                        target.getAppearence().generateAppearenceData();
                        target.getSkills().switchXPPopup(true);
                        target.getSkills().switchXPPopup(true);
                        return true;
                    } catch (NumberFormatException e) {
                        player.getPackets().sendGameMessage("Usage ::setlevel skillId level");
                    }
                    return true;
                case "wildy":
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    if (target == null)
                        player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
                    else
                        target.getControlerManager().startControler("WildernessControler");
                    target.setNextWorldTile(player);
                    return true;
                case "teletome":
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    if (target == null)
                        player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
                    else {
                        target.setNextWorldTile(player);
                    }
                    return true;

                case "telealltome":
                    for (Player players : World.getPlayers()) {
                        players.setNextWorldTile(player);
                    }
                    return true;

                case "matchaccounts":// admin tool more specific
                    String[] playerName = {Utils.formatPlayerNameForProtocol(cmd[1]),
                            Utils.formatPlayerNameForProtocol(cmd[2])};
                    Player[] targets = new Player[2];
                    byte count1 = 0;
                    for (String pn : playerName) {
                        if (!AccountCreation.exists(pn)) {
                            player.getPackets().sendGameMessage("No such account named %s was found in the database.", pn);
                            return true;
                        }
                        targets[count1] = AccountCreation.loadPlayer(pn);
                        targets[count1].setUsername(pn);
                        count1++;
                    }

                    if (targets[0] == null || targets[1] == null)
                        return true;

                    ArrayList<String> ip1 = targets[0].getIPList(), ip2 = targets[1].getIPList();
                    boolean match = false;
                    for (String ip : ip1)
                        for (String ipx : ip2) {
                            if (ip == null || ipx == null)
                                continue;
                            if (ip.equalsIgnoreCase(ipx)) {
                                player.getPackets().sendPanelBoxMessage("IP link between " + playerName[0] + " and "
                                        + playerName[1] + " found! [" + ip + "]");
                                match = true;
                            }
                        }
                    player.getPackets().sendGameMessage(
                            match ? "One or more matches found between the accounts %s and %s"
                                    : "No matches were found between the accounts %s and %s",
                            targets[0].getUsername(), targets[1].getUsername());
                    return true;
                case "pos":
                    player.getPackets()
                            .sendPanelBoxMessage("Coords: " + player.getX() + ", " + player.getY() + ", "
                                    + player.getPlane() + ", regionId: " + player.getRegionId() + ", rx: "
                                    + player.getChunkX() + ", ry: " + player.getChunkY());
                    return true;

                case "killnpc":
                    for (NPC n : World.getNPCs()) {
                        if (n == null || n.getId() != Integer.parseInt(cmd[1]))
                            continue;
                        n.sendDeath(n);
                    }
                    return true;
                case "clearchat":
                    player.sm(" ");
                    player.sm(" ");
                    player.sm(" ");
                    player.sm(" ");
                    player.sm(" ");
                    player.sm(" ");
                    player.sm(" ");
                    player.sm(" ");
                    player.sm(" ");
                    player.sm(" ");
                    return true;
                case "master":
                    for (int i = 0; i < 24; i++) {
                        player.getSkills().set(i, 99);
                        player.getSkills().setXp(i, Skills.getXPForLevel(99));
                    }
                    player.getSkills().set(24, 120);
                    player.getSkills().setXp(24, Skills.getXPForLevel(120));
                    for (int i = 0; i < 25; i++)
                        player.getDialogueManager().startDialogue("LevelUp", i);
                    player.getAppearence().generateAppearenceData();
                    player.getSkills().switchXPPopup(true);
                    player.getSkills().switchXPPopup(true);
                    return true;
                case "milestonelevels":
                    for (int i = 0; i < 24; i++) {
                        player.getSkills().setXp(i, Skills.getXPForLevel(player.getSkills().getLevelForXp(i) + 10));
                        player.getSkills().set(i, player.getSkills().getLevelForXp(i));
                    }
                    player.getAppearence().generateAppearenceData();
                    player.getSkills().switchXPPopup(true);
                    player.getSkills().switchXPPopup(true);
                    return true;
                case "adnpc":
                    try {
                        World.spawnNPC(Integer.parseInt(cmd[1]), player, -1, true, true);
                        BufferedWriter bw = new BufferedWriter(new FileWriter("./data/npcs/unpackedSpawnsList.txt", true));
                        bw.write("//" + NPCDefinitions.getNPCDefinitions(Integer.parseInt(cmd[1])).name + " spawned by "
                                + player.getUsername());
                        bw.newLine();
                        bw.write(Integer.parseInt(cmd[1]) + " - " + player.getX() + " " + player.getY() + " "
                                + player.getPlane());
                        bw.newLine();
                        bw.close();
                        player.sm("Added NPC: " + NPCDefinitions.getNPCDefinitions(Integer.parseInt(cmd[1])).name + " to X:"
                                + player.getX() + ", Y:" + player.getY() + ", H:" + player.getPlane());
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    return true;
                case "adobject":
                    try {
                        int rotation = 0;
                        if (cmd.length < 3) {
                            type = 10;
                            rotation = 0;
                        } else if (cmd.length == 3) {
                            type = Integer.parseInt(cmd[2]);
                            rotation = 0;
                        } else {
                            type = Integer.parseInt(cmd[2]);
                            rotation = Integer.parseInt(cmd[3]);
                            if (type > 22 || type < 0) {
                                type = 10;
                            }
                        }
                        World.spawnObject(new WorldObject(Integer.valueOf(cmd[1]), type, rotation, player.getX(),
                                player.getY(), player.getPlane()));
                        BufferedWriter bw = new BufferedWriter(new FileWriter("./data/map/unpackedSpawnsList.txt", true));
                        bw.write("//" + ObjectDefinitions.getObjectDefinitions(Integer.parseInt(cmd[1])).name
                                + " spawned by " + player.getUsername());
                        bw.newLine();
                        bw.write(Integer.parseInt(cmd[1]) + " " + type + " " + rotation + " - " + player.getX() + " " + player.getY() + " "
                                + player.getPlane() + " true");
                        bw.newLine();
                        bw.close();
                        player.sm("Added Object: " + ObjectDefinitions.getObjectDefinitions(Integer.parseInt(cmd[1])).name
                                + " to X:" + player.getX() + ", Y:" + player.getY() + ", H:" + player.getPlane());
                    } catch (Throwable t) {
                        t.printStackTrace();
                    }
                    return true;
                case "npc":
                    try {
                        World.spawnNPC(Integer.parseInt(cmd[1]), player, -1, true, true);
                        player.sm(Integer.valueOf(cmd[1]) + " " + player.getX() + " " + player.getY() + " "
                                + player.getPlane());
                        Logger.log("NPC", Integer.valueOf(cmd[1]) + " " + player.getX() + " " + player.getY() + " "
                                + player.getPlane());
                        return true;
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("Use: ::npc id(Integer)");
                    }
                    return true;
                case "getobject":
                    WorldObject object = World.getStandardWallObject(player);
                    if (object == null)
                        object = World.getStandardFloorObject(player);
                    if (object == null)
                        object = World.getObjectWithType(player, 10);
                    if (object == null)
                        object = World.getStandardWallDecoration(player);
                    if (object == null) {
                        object = World.getStandardFloorDecoration(player);
                    }
                    if (object == null) {
                        player.sm("Unknown object under you.");
                        return false;
                    }
                    player.sm("Found object " + object.getName() + " - " + object.getId() + ", " + object.getType() + " at " + object.getX() + ", " + object.getY() + ", " + object.getPlane());
                    return true;
                case "interactive":
                    player.getPackets().sendRunScript(26, "Debug");
                    player.sm("Objects interactive.");
                    return true;
                case "testloop":
                    for (int z = 0; z <= 3; z++) {
                        for (int x = player.getX() - 3; x <= player.getX() + 3; x++) {
                            for (int y = player.getY() - 3; y <= player.getY() + 3; y++) {
                                WorldTile tile = new WorldTile(x, y, z);
                                World.sendGraphics(player, new Graphics(4, 0, 0), tile);
                                for (int i = 0; i <= 22; i++) {
                                    object = World.getObjectWithType(tile, i);
                                    if (object != null)
                                        player.sm("Found object at " + object.getName() + " - " + object.getId() + ", " + object.getType() + " at: " + object.getX() + "," + object.getY() + "," + object.getPlane());
                                }
                            }
                        }
                    }
                    return true;
                case "getobjects":
                    for (int z = 0; z <= 3; z++) {
                        for (int x = player.getX() - 1; x <= player.getX() + 1; x++) {
                            for (int y = player.getY() - 1; y <= player.getY() + 1; y++) {
                                WorldTile tile = new WorldTile(x, y, z);
                                object = World.getObjectWithType(tile, 10);
                                if (object == null)
                                    object = World.getStandardFloorObject(tile);
                                if (object == null)
                                    object = World.getStandardWallObject(tile);
                                if (object == null)
                                    object = World.getStandardWallDecoration(tile);
                                if (object == null) {
                                    object = World.getStandardFloorDecoration(tile);
                                    if (object == null) {
                                        for (int i = 0; i <= 22; i++) {
                                            object = World.getObjectWithType(tile, i);
                                            if (object != null) {
                                                player.sm("Found object at " + object.getName() + " - " + object.getId() + ", " + object.getType() + " at: " + object.getX() + "," + object.getY() + "," + object.getPlane());
                                                i = 23;
                                            }
                                        }
                                    }
                                    if (object != null)
                                        player.sm("Found object at " + object.getName() + " - " + object.getId() + ", " + object.getType() + " at: " + object.getX() + "," + object.getY() + "," + object.getPlane());
                                    else
                                        player.sm("Didnt find any object at: " + tile.getX() + ", " + tile.getY() + ", " + tile.getPlane());
                                }
                            }
                        }
                    }
                    return true;
                case "getwall":
                    object = World.getStandardWallObject(player);
                    if (object == null)
                        object = World.getStandardWallDecoration(player);
                    if (object == null) {
                        player.sm("Unknown wall under you.");
                        return false;
                    }
                    player.sm(object.getName() + " - " + object.getId() + ", " + object.getType());
                    return true;
                case "clip":
                    player.sm("tile clipped?: " + World.isClipped(player.getPlane(), player.getX(), player.getY()));
                    return true;
                case "wall":
                    player.sm("tile wallfree? " + World.isWallsFree(player.getPlane(), player.getX(), player.getY()));
                    return true;

                case "removeobject":
                    object = World.getStandardWallObject(player);
                    if (object == null)
                        object = World.getStandardFloorObject(player);
                    if (object == null)
                        object = World.getObjectWithType(player, 10);
                    if (object == null)
                        object = World.getStandardWallDecoration(player);
                    if (object == null) {
                        object = World.getStandardFloorDecoration(player);
                    }
                    if (object == null) {
                        player.sm("Unknown object under you.");
                        return false;
                    }
                    World.removeObject(object);
                    return true;
                case "object":
                    int rotation = 0;
                    try {
                        if (cmd.length < 3) {
                            type = 10;
                            rotation = 0;
                        } else if (cmd.length == 3) {
                            type = Integer.parseInt(cmd[2]);
                            rotation = 0;
                        } else {
                            type = Integer.parseInt(cmd[2]);
                            rotation = Integer.parseInt(cmd[3]);
                            if (type > 22 || type < 0) {
                                type = 10;
                            }
                        }
                        World.spawnObject(new WorldObject(Integer.valueOf(cmd[1]), type, rotation, player.getX(),
                                player.getY(), player.getPlane()));
                        player.sm(Integer.valueOf(cmd[1]) + " " + type + " " + rotation + " - " + player.getX() + " "
                                + player.getY() + " " + player.getPlane());
                        Logger.log("Object", Integer.valueOf(cmd[1]) + " 10 0 - " + player.getX() + " " + player.getY()
                                + " " + player.getPlane());
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("Use: setkills id");
                    }
                    return true;
                case "changepassother":
                    name = cmd[1];
                    target = AccountCreation.loadPlayer(name);
                    if (!AccountCreation.exists(name)) {
                        player.sm(name + " does not exists.");
                        return true;
                    }
                    if (target == null) {
                        target = AccountCreation.loadPlayer(name);
                    }
                    target.setPassword(Encrypt.encryptSHA1(cmd[2]));
                    player.getPackets().sendGameMessage("You changed their password!");
                    AccountCreation.savePlayer(target);
                    return true;
                case "forcemovement":
                    WorldTile toTile = player.transform(0, 5, 0);
                    player.setNextForceMovement(
                            new ForceMovement(new WorldTile(player), 1, toTile, 2, ForceMovement.NORTH));
                    return true;
                case "gamble":
                    player.getPackets().sendInputIntegerScript(true,
                            "Enter the amount you wish to gamble (Max 100m, Min 1m):");
                    player.temporaryAttribute().put("gambling", Boolean.TRUE);
                    return true;

                case "getpts":
                    player.setAvalonPoints(1000000);
                    player.getPackets().sendGameMessage("You now have 1,000,000 " + Settings.SERVER_NAME + " points.");
                    return true;
                case "getpkp":
                    player.setPKP(1000000);
                    player.getPackets().sendGameMessage("You now have 1,000,000 pk points.");
                    return true;
                case "resetpkp":
                    player.setPKP(0);
                    return true;
                case "tonpc":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("Use: ::tonpc id(-1 for player)");
                        return true;
                    }
                    try {
                        player.getAppearence().transformIntoNPC(Integer.valueOf(cmd[1]));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("Use: ::tonpc id(-1 for player)");
                    }
                    return true;
                case "inter":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("Use: ::inter interfaceId");
                        return true;
                    }
                    try {
                        player.getInterfaceManager().sendInterface(Integer.valueOf(cmd[1]));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("Use: ::inter interfaceId");
                    }
                    return true;
                case "cinter":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("Use: ::inter interfaceId");
                        return true;
                    }
                    try {
                        player.getInterfaceManager().sendChatBoxInterface(Integer.valueOf(cmd[1]));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("Use: ::inter interfaceId");
                    }
                    return true;
                case "empty":
                    player.getInventory().reset();
                    return true;
                case "bank":
                    player.getBank().openBank();
                    return true;
                case "tele":
                    if (cmd.length < 3) {
                        player.getPackets().sendPanelBoxMessage("Use: ::tele coordX coordY");
                        return true;
                    }
                    try {
                        player.resetWalkSteps();
                        player.setNextWorldTile(new WorldTile(Integer.valueOf(cmd[1]), Integer.valueOf(cmd[2]),
                                cmd.length >= 4 ? Integer.valueOf(cmd[3]) : player.getPlane()));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("Use: ::tele coordX coordY plane");
                    }
                    return true;
                case "emote":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("Use: ::emote id");
                        return true;
                    }
                    player.animate(new Animation(-1));
                    try {
                        player.animate(new Animation(-1));
                        player.animate(new Animation(Integer.valueOf(cmd[1])));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("Use: ::emote id");
                    }
                    return true;
                case "remote":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("Use: ::emote id");
                        return true;
                    }
                    try {
                        player.getAppearence().setRenderEmote(Integer.valueOf(cmd[1]));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("Use: ::emote id");
                    }
                    return true;
                case "spec":
                    player.getCombatDefinitions().resetSpecialAttack();
                    return true;
                case "gfx":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("Use: ::gfx id");
                        return true;
                    }
                    try {
                        player.gfx(new Graphics(Integer.valueOf(cmd[1]), 0, 0));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("Use: ::gfx id");
                    }
                    return true;
                case "anim":
                    if (cmd.length < 2) {
                        player.getPackets().sendPanelBoxMessage("Use: ::anim id");
                        return true;
                    }
                    try {
                        player.animate(new Animation(Integer.valueOf(cmd[1])));
                    } catch (NumberFormatException e) {
                        player.getPackets().sendPanelBoxMessage("Use: ::anim id");
                    }
                    return true;
                case "sync":
                    int animId = Integer.parseInt(cmd[1]);
                    int gfxId = Integer.parseInt(cmd[2]);
                    int height = cmd.length > 3 ? Integer.parseInt(cmd[3]) : 0;
                    player.animate(new Animation(animId));
                    player.gfx(new Graphics(gfxId, 0, height));
                    return true;
            }
        }
        return false;
    }

    public static boolean processSupportCommands(Player player, String[] cmd, boolean console, boolean clientCommand) {
        String name;
        Player target;
        if (clientCommand) {
            ;
        } else {
            switch (cmd[0]) {
                case "forcekick":
                case "kick":
                    if (!isDeveloper(player)) {
                        player.sm("cant kick developer");
                        return true;
                    }
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    target.forceLogout();
                    player.getPackets().sendGameMessage("You have kicked: " + target.getDisplayName() + ".");
                    return true;
                case "unnull":
                case "sendhome":
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    target.unlock();
                    target.getControlerManager().forceStop();
                    if (target.getNextWorldTile() == null)
                        target.setNextWorldTile(Settings.RESPAWN_PLAYER_LOCATION);
                    target.getPackets().sendGameMessage("You have been sent home by: " + player.getDisplayName());
                    player.getPackets().sendGameMessage("You have sent home: " + target.getDisplayName() + ".");
                    return true;
                case "teleto":
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    if (target == null)
                        player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
                    if (target.getAppearence().isHidden()) {
                        player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
                        return true;
                    } else
                        player.setNextWorldTile(target);
                    return true;

            }
        }
        return clientCommand;
    }

    public static boolean processModCommand(Player player, String[] cmd, boolean console, boolean clientCommand) {
        if (clientCommand) {
        } else {
            String name;
            Player target;
            switch (cmd[0]) {
                case "answer":
                case "open":
                case "access":
                case "openticket":
                    if (player.isInLiveChat) {
                        player.sm("<col=ff000>You cannot handle more than one ticket at a time.");
                        return true;
                    }
                    String username2 = cmd[1].substring(cmd[1].indexOf(" ") + 1);
                    Player requester = World.getPlayerByDisplayName(username2);
                    if (requester.isInLiveChat) {
                        player.sm(
                                "<col=ff000>" + requester.getDisplayName() + " is currently already placed in a chatroom.");
                        return true;
                    }
                    if (requester.isRequestingChat)
                        TicketSystem.answerTicket(requester, player);
                    else
                        player.sm("<col=ff000>" + requester.getDisplayName() + " has no open tickets.");
                    return true;
                case "mute":
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    if (target != null) {
                        target.mute(target.getUsername(), "-", 1);
                        player.getPackets().sendGameMessage("You have muted 1 day: " + target.getDisplayName() + ".");
                        player.getPackets()
                                .sendGameMessage("Display: " + target.getDisplayName() + " User: " + target.getUsername());
                    } else {
                        name = Utils.formatPlayerNameForProtocol(name);
                        if (!AccountCreation.exists(name)) {
                            player.getPackets().sendGameMessage(
                                    "Account name " + Utils.formatPlayerNameForDisplay(name) + " doesn't exist.");
                            return true;
                        }
                        target = AccountCreation.loadPlayer(name);
                        target.setUsername(name);
                        target.mute(target.getUsername(), "-", 1);
                        player.getPackets()
                                .sendGameMessage("You have muted 1 day: " + Utils.formatPlayerNameForDisplay(name) + ".");
                        AccountCreation.savePlayer(target);
                    }
                    return true;

                case "unmute":
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    if (target != null) {
                        target.liftMute(false, player);
                        player.getPackets().sendGameMessage("You have unmuted: " + target.getDisplayName() + ".");
                        player.getPackets()
                                .sendGameMessage("Display: " + target.getDisplayName() + " User: " + target.getUsername());
                    } else {
                        name = Utils.formatPlayerNameForProtocol(name);
                        if (!AccountCreation.exists(name)) {
                            player.getPackets().sendGameMessage(
                                    "Account name " + Utils.formatPlayerNameForDisplay(name) + " doesn't exist.");
                            return true;
                        }
                        target = AccountCreation.loadPlayer(name);
                        target.setUsername(name);
                        target.liftMute(false, player);
                        player.getPackets()
                                .sendGameMessage("You have unmuted: " + Utils.formatPlayerNameForDisplay(name) + ".");
                        AccountCreation.savePlayer(target);
                    }
                    return true;

                case "yell":
                    String data = "";
                    for (int i = 1; i < cmd.length; i++) {
                        data += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    }
                    ServerMessage.filterMessage(player, Utils.fixChatMessage(data), false);
                    archiveYell(player, Utils.fixChatMessage(data));
                    return true;

                case "permban":
                    if (!isDeveloper(player)) {
                        player.getPackets().sendGameMessage("You don't have permission to use this command.");
                        return true;
                    }
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    if (target != null) {
                        if (target.isDeveloper()) {
                            target.getPackets()
                                    .sendGameMessage("<col=ff0000>" + player.getDisplayName() + " just tried to ban you!");
                            return true;
                        }
                        if (target.getGeManager() == null) {
                            target.setGeManager(new GrandExchangeManager());
                        }
                        target.getGeManager().setPlayer(target);
                        target.getGeManager().init();
                        GrandExchange.removeOffers(target);
                        target.setPermBanned(true);
                        target.getSession().getChannel().close();
                        player.getPackets().sendGameMessage("You have perm banned: " + target.getDisplayName() + ".");
                        player.getPackets()
                                .sendGameMessage("Display: " + target.getDisplayName() + " User: " + target.getUsername());
                    } else {
                        name = Utils.formatPlayerNameForProtocol(name);
                        if (!AccountCreation.exists(name)) {
                            player.getPackets().sendGameMessage(
                                    "Account name " + Utils.formatPlayerNameForDisplay(name) + " doesn't exist.");
                            return true;
                        }
                        target = AccountCreation.loadPlayer(name);
                        if (target.isDeveloper())
                            return true;
                        if (target.getGeManager() == null)
                            target.setGeManager(new GrandExchangeManager());
                        target.getGeManager().setPlayer(target);
                        target.getGeManager().init();
                        GrandExchange.removeOffers(target);
                        target.setUsername(name);
                        target.setPermBanned(true);
                        player.getPackets()
                                .sendGameMessage("You have perm banned: " + Utils.formatPlayerNameForDisplay(name) + ".");
                        AccountCreation.savePlayer(target);
                    }
                    return true;
                case "ban":
                    if (!isDeveloper(player))
                        return true;
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    if (target != null) {
                        if (target.isDeveloper()) {
                            target.getPackets()
                                    .sendGameMessage("<col=ff0000>" + player.getDisplayName() + " just tried to ban you!");
                            return true;
                        }
                        if (target.getGeManager() == null) {
                            target.setGeManager(new GrandExchangeManager());
                        }
                        target.getGeManager().setPlayer(target);
                        target.getGeManager().init();
                        GrandExchange.removeOffers(target);
                        target.setBanned(Utils.currentTimeMillis() + (48 * 60 * 60 * 1000));
                        target.getSession().getChannel().close();
                        player.getPackets().sendGameMessage("You have banned 48 hours: " + target.getDisplayName() + ".");
                        player.getPackets()
                                .sendGameMessage("Display: " + target.getDisplayName() + " User: " + target.getUsername());
                    } else {
                        name = Utils.formatPlayerNameForProtocol(name);
                        if (!AccountCreation.exists(name)) {
                            player.getPackets().sendGameMessage(
                                    "Account name " + Utils.formatPlayerNameForDisplay(name) + " doesn't exist.");
                            return true;
                        }
                        target = AccountCreation.loadPlayer(name);
                        if (target.isDeveloper()) {
                            return true;
                        }
                        if (target.getGeManager() == null) {
                            target.setGeManager(new GrandExchangeManager());
                        }
                        target.getGeManager().setPlayer(target);
                        target.getGeManager().init();
                        GrandExchange.removeOffers(target);
                        target.setUsername(name);
                        target.setBanned(Utils.currentTimeMillis() + (48 * 60 * 60 * 1000));
                        player.getPackets().sendGameMessage(
                                "You have banned 48 hours: " + Utils.formatPlayerNameForDisplay(name) + ".");
                        AccountCreation.savePlayer(target);
                    }
                    return true;
                case "unban":
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    if (target != null) {
                        IPBanL.unban(target);
                        player.getPackets().sendGameMessage("You have unbanned: " + target.getDisplayName() + ".");
                    } else {
                        name = Utils.formatPlayerNameForProtocol(name);
                        if (!AccountCreation.exists(name)) {
                            player.getPackets().sendGameMessage(
                                    "Account name " + Utils.formatPlayerNameForDisplay(name) + " doesn't exist.");
                            return true;
                        }
                        target = AccountCreation.loadPlayer(name);
                        target.setUsername(name);
                        target.setPermBanned(false);
                        IPBanL.unban(target);
                        player.getPackets().sendGameMessage("You have unbanned: " + target.getDisplayName() + ".");
                        AccountCreation.savePlayer(target);
                    }
                    return true;

                case "forcekick":
                case "kick":
                    if (player.isAtWild()) {
                        player.getPackets().sendGameMessage("You cannot use this command in the wilderness.");
                        return true;
                    }
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    if (target == null)
                        player.sm(name + " is not logged in.");
                    else {
                        target.forceLogout();
                        player.sm("You kicked player: " + name + ".");
                    }
                    return true;
                case "teleto":
                    if (player.isAtWild()) {
                        player.getPackets().sendGameMessage("You cannot use this command in the wilderness.");
                        return true;
                    }
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    if (target == null)
                        player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
                    if (target.getAppearence().isHidden()) {
                        player.getPackets().sendGameMessage("Couldn't find player " + name + ".");
                        return true;
                    } else
                        player.setNextWorldTile(target);
                    return true;
                case "teletome":
                    if (player.isAtWild()) {
                        player.getPackets().sendGameMessage("You cannot use this command in the wilderness.");
                        return true;
                    }
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    if (target.isDeveloper()) {
                        player.getPackets().sendGameMessage("Unable to teleport a developer to you.");
                        return true;
                    }
                    target.setNextWorldTile(player);
                    return true;
                case "sendhome":
                    if (player.isAtWild()) {
                        player.getPackets().sendGameMessage("You cannot use this command in the wilderness.");
                        return true;
                    }
                    name = "";
                    for (int i = 1; i < cmd.length; i++)
                        name += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
                    target = World.getPlayerByDisplayName(name);
                    if (target == null) {
                        player.getPackets().sendGameMessage("This player is offline.");
                    } else {
                        target.unlock();
                        target.getControlerManager().forceStop();
                        target.setNextWorldTile(Settings.START_PLAYER_LOCATION);
                        player.getPackets().sendGameMessage("You have sent home " + target.getDisplayName() + ".");
                    }
                    return true;
            }
        }
        return false;
    }

    public static void archiveLogs(Player player, String[] cmd) {
        try {
            String location = "";
            if (player.isDeveloper()) {
                location = "data/logs/commands/admin/" + player.getUsername() + ".txt";
            } else if (player.isModerator()) {
                location = "data/logs/commands/mod/" + player.getUsername() + ".txt";
            } else if (player.getPlayerRank().getRank()[0] == Rank.PLAYERSUPPORT) {
                location = "data/logs/commands/support/" + player.getUsername() + ".txt";
            } else {
                location = "data/logs/commands/player/" + player.getUsername() + ".txt";
            }
            String afterCMD = "";
            for (int i = 1; i < cmd.length; i++)
                afterCMD += cmd[i] + ((i == cmd.length - 1) ? "" : " ");
            BufferedWriter writer = new BufferedWriter(new FileWriter(location, true));
            writer.write("[" + currentTime("dd MMMMM yyyy 'at' hh:mm:ss z") + "] - ::" + cmd[0] + " " + afterCMD);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String currentTime(String dateFormat) {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(cal.getTime());
    }

    private static boolean isDeveloper(Player player) {
        for (String name : Settings.DEVELOPERS) {
            if (player.getUsername().equalsIgnoreCase(name))
                return true;
        }
        return false;
    }

    public static void archiveYell(Player player, String message) {
        try {
            String location = "";
            location = "data/logs/yell/" + player.getUsername() + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(location, true));
            writer.write("[" + currentTime("dd MMMMM yyyy 'at' hh:mm:ss z") + "] - ::yell" + message);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Doesn't let it be instanced
     */

    private Commands() {

    }
}
