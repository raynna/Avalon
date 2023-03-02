package com.rs.game.player.controlers;

import com.rs.Settings;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.teleportation.TeleportsData.TeleportStore;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.HexColours;
import com.rs.utils.Utils;
import com.rs.utils.HexColours.Colour;

public class EdgevillePvPControler extends Controler {

    private boolean showingSkull;

    public static void enterPVP(Player player) {
        player.getControlerManager().startControler("EdgevillePvPControler");
        player.setNextWorldTile(TeleportStore.EDGEVILLE_PVP_INSTANCE.getTile());
        player.message("You " + HexColours.getShortMessage(Colour.RED, "enter") + " edgeville pvp instance.");
        player.getAppearence().generateAppearenceData();
    }

    public static void leavePVP(Player player) {
        player.getControlerManager().forceStop();
        player.setNextWorldTile(new WorldTile(Settings.HOME_PLAYER_LOCATION));
        player.getAppearence().generateAppearenceData();
        player.message("You " + HexColours.getShortMessage(Colour.RED, "leave") + " edgeville pvp instance.");
        player.setCanPvp(false);
        player.getPackets().sendGlobalConfig(1000, 0);
        player.getAppearence().generateAppearenceData();
    }

    @Override
    public void start() {
        checkBoosts(player);
    }

    public static void checkBoosts(Player player) {
        boolean changed = false;
        int level = player.getSkills().getLevelForXp(Skills.ATTACK);
        int maxLevel = (int) (level + 5 + (level * 0.15));
        if (maxLevel < player.getSkills().getLevel(Skills.ATTACK)) {
            player.getSkills().set(Skills.ATTACK, maxLevel);
            changed = true;
        }
        level = player.getSkills().getLevelForXp(Skills.STRENGTH);
        maxLevel = (int) (level + 5 + (level * 0.15));
        if (maxLevel < player.getSkills().getLevel(Skills.STRENGTH)) {
            player.getSkills().set(Skills.STRENGTH, maxLevel);
            changed = true;
        }
        level = player.getSkills().getLevelForXp(Skills.DEFENCE);
        maxLevel = (int) (level + 5 + (level * 0.15));
        if (maxLevel < player.getSkills().getLevel(Skills.DEFENCE)) {
            player.getSkills().set(Skills.DEFENCE, maxLevel);
            changed = true;
        }
        level = player.getSkills().getLevelForXp(Skills.RANGE);
        maxLevel = (int) (level + 5 + (level * 0.1));
        if (maxLevel < player.getSkills().getLevel(Skills.RANGE)) {
            player.getSkills().set(Skills.RANGE, maxLevel);
            changed = true;
        }
        level = player.getSkills().getLevelForXp(Skills.MAGIC);
        maxLevel = level + 7;
        if (maxLevel < player.getSkills().getLevel(Skills.MAGIC)) {
            player.getSkills().set(Skills.MAGIC, maxLevel);
            changed = true;
        }
        if (changed)
            player.getPackets().sendGameMessage("Your extreme potion bonus has been reduced.");
    }

    @Override
    public boolean login() {
        start();
        sendInterfaces();
        moved();
        return false;
    }

    @Override
    public boolean keepCombating(Entity target) {
        if (target instanceof NPC)
            return true;
        if (!canAttack(target))
            return false;
        if (target instanceof Player) {
            Player p2 = (Player) target;
            if (isSafe(player) || isSafe(p2))
                return false;
            if (!player.attackedBy.containsKey(p2))
                player.setWildernessSkull();
        }
        return true;
    }

    @Override
    public boolean canAttack(Entity target) {
        player.faceEntity(target);
        if (target instanceof Player) {
            Player p2 = (Player) target;
            if (player.isCanPvp() && !p2.isCanPvp()) {
                player.message("That player is not in the pvp area.");
                return false;
            }
            if (Math.abs(player.getSkills().getCombatLevel() - p2.getSkills().getCombatLevel()) > getWildLevel()) {
                player.message("You can't attack " + p2.getDisplayName() + " - your level difference is too great.");
                return false;
            }
            if (canHit(target))
                return true;
            return false;
        }
        return true;
    }

    @Override
    public boolean canHit(Entity target) {
        if (target instanceof NPC)
            return true;
        Player p2 = (Player) target;
        if (Math.abs(player.getSkills().getCombatLevel() - p2.getSkills().getCombatLevel()) > getWildLevel())
            return false;
        return true;
    }

    @Override
    public boolean processMagicTeleport(WorldTile toTile) {
        if (player.getTeleBlockDelay() > Utils.currentTimeMillis()) {
            player.getPackets().sendGameMessage("A mysterious force prevents you from teleporting.");
            return false;
        }
        return true;

    }

    @Override
    public boolean processItemTeleport(WorldTile toTile) {
        if (player.getTeleBlockDelay() > Utils.currentTimeMillis()) {
            player.getPackets().sendGameMessage("A mysterious force prevents you from teleporting.");
            return false;
        }
        return true;
    }

    @Override
    public boolean processJewerlyTeleport(WorldTile toTile) {
        if (player.getTeleBlockDelay() > Utils.currentTimeMillis()) {
            player.getPackets().sendGameMessage("A mysterious force prevents you from teleporting.");
            return false;
        }
        return true;
    }

    @Override
    public boolean processObjectTeleport(WorldTile toTile) {
        if (player.getTeleBlockDelay() > Utils.currentTimeMillis()) {
            player.getPackets().sendGameMessage("A mysterious force prevents you from teleporting.");
            return false;
        }
        return true;
    }

    private int inDangerSprite = 1577, inSafeSprite = 1576;

    private static int getTabId(Player player) {
        return player.getInterfaceManager().isResizableScreen() ? 42 : 11;
    }

    @Override
    public void sendInterfaces() {
        if (!player.getInterfaceManager().containsInterface(3043))
            player.getInterfaceManager().sendTab(getTabId(player), 3043);
        int minus = player.getSkills().getCombatLevel() - getWildLevel();
        int plus = player.getSkills().getCombatLevel() + getWildLevel();
        StringBuilder builder = new StringBuilder();
        if (minus< 4)
            builder.append("3 - " + plus);
        else if (plus > 137)
            builder.append(minus + " - 138");
        else if (plus < 138 && minus > 3)
            builder.append(minus + " - " + plus);
        player.getPackets().sendIComponentText(3043, 3, builder.toString());
        builder = new StringBuilder();
        builder.append("EP: ");
        if (player.getEP() >= 70)
            builder.append(HexColours.Colour.GREEN.getHex());
        if (player.getEP() >= 35 && player.getEP() <= 69)
            builder.append(HexColours.Colour.YELLOW.getHex());
        if (player.getEP() < 35)
            builder.append(HexColours.Colour.RED.getHex());
        builder.append(player.getEP() + "%");
        player.getPackets().sendIComponentText(3043, 4, builder.toString());
        if (isSafe(player))
            player.getPackets().sendSpriteOnIComponent(3043, 2, inSafeSprite);
        else
            player.getPackets().sendSpriteOnIComponent(3043, 2, inDangerSprite);
    }

    @Override
    public boolean sendDeath() {
        final Player instance = player;
        if (instance.hasFinished() || !instance.isActive())
            return false;
        player.resetWalkSteps();
        player.lock(7);
        player.animate(new Animation(836));
        if (player.getFamiliar() != null)
            player.getFamiliar().sendDeath(player);
        player.checkPetDeath();
        WorldTasksManager.schedule(new WorldTask() {
            int loop;

            @Override
            public void run() {
                if (loop == 0) {
                } else if (loop == 1) {
                    player.getPackets().sendGameMessage("Oh dear, you have died.");
                } else if (loop == 2) {
                    Player killer = player.getMostDamageReceivedSourcePlayer();
                    if (killer != null) {
                        player.sendItemsOnDeath(killer, true);
                        killer.increaseKillCount(player);
                    } else
                        player.sendItemsOnDeath(instance, true);
                    player.getEquipment().init();
                    player.getInventory().init();
                    player.reset();
                    player.setNextWorldTile(TeleportStore.EDGEVILLE_PVP_INSTANCE.getTile());
                    player.animate(new Animation(-1));
                } else if (loop == 3) {
                    player.getPackets().sendMusicEffect(90);
                    stop();
                }
                loop++;
            }
        }, 0, 1);
        return false;
    }

    @Override
    public void moved() {
        boolean interfacesOpen = false;
        if (!interfacesOpen) {
            sendInterfaces();
            interfacesOpen = true;
        }
        if (isAtPvP(player) && !isAtBank(player)) {
            player.setCanPvp(true);
            player.getPackets().sendGlobalConfig(1000, player.getSkills().getCombatLevel() + player.getSkills().getSummoningCombatLevel());
            player.getPackets().sendSpriteOnIComponent(3043, 2, inDangerSprite);
            player.getAppearence().generateAppearenceData();
            sendInterfaces();
        } else if (isAtBank(player)) {
            sendInterfaces();
            removeIcon();
            player.getPackets().sendGlobalConfig(1000, 0);
            player.setCanPvp(false);
            player.getPackets().sendSpriteOnIComponent(3043, 2, inSafeSprite);
            player.getAppearence().generateAppearenceData();
        } else if (!isAtPvP(player) && !isAtBank(player)) {
            player.getPackets().sendGlobalConfig(1000, 0);
            player.getAppearence().generateAppearenceData();
            forceClose();
        }
    }

    public void removeIcon() {
        if (showingSkull) {
            showingSkull = false;
            player.setCanPvp(false);
            player.getAppearence().generateAppearenceData();
            player.getEquipment().refresh(null);
        }
    }

    @Override
    public boolean logout() {
        player.getInterfaceManager().closeTab(getTabId(player));
        return false;
    }

    @Override
    public void forceClose() {
        player.getInterfaceManager().closeTab(getTabId(player));
        removeControler();
        player.setCanPvp(false);
        player.getAppearence().generateAppearenceData();
    }

    public static final boolean isAtPvP(WorldTile tile) {
        return (tile.getX() >= 64 && tile.getX() <= 127 && tile.getY() >= 64 && tile.getY() <= 127 && !isAtBank(tile));
    }

    public static final boolean isAtBank(WorldTile tile) {
        return (tile.getX() >= 83 && tile.getX() <= 90 && tile.getY() >= 72 && tile.getY() <= 83);
    }

    public static boolean isSafe(Player player) {
        return (player.getX() >= 83 && player.getX() <= 90 && player.getY() >= 72 && player.getY() <= 83);
    }

    public static boolean isDanger(Player player) {
        return (player.getX() >= 64 && player.getX() <= 127 && player.getY() >= 64 && player.getY() <= 127);
    }

    public int getWildLevel() {
        return 15;
    }

}
