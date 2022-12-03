package com.rs.game.player.actions.skills.thieving;

import java.util.List;

import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.npc.NPC;
import com.rs.game.player.Equipment;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.thieving.BlackIbisOutfit.Pieces;
import com.rs.game.player.content.AxeHut;
import com.rs.game.player.content.PirateHut;
import com.rs.game.player.content.tasksystem.TaskManager.Tasks;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
//import com.rs.game.player.content.agility.PirateHut;
import com.rs.utils.Utils;

/**
 * Handles the Thieving Skill
 **/

public class Thieving {

    public enum Stalls {
        VEGETABAL(4706, 2, new int[]{1957, 1965, 1942, 1982, 1550}, 1, 2, 10, 34381),
        CAKE(34384, 1, new int[]{1891, 1897, 2309}, 1, 2.5, 16, 34381),
        CRAFTING(0, 5, new int[]{1755, 1592, 1597}, 1, 7, 16, 34381),
        MONKEY_FOOD(0, 5, new int[]{1963}, 1, 7, 16, 34381),
        MONKEY_GENERAL(0, 5, new int[]{1931, 2347, 590}, 1, 7, 16, 34381),
        TEA_STALL(0, 5, new int[]{712}, 1, 7, 16, 34381),
        SILK_STALL(34383, 45, new int[]{950}, 1, 2.5, 94, 34381),
        WINE_STALL(14011, 22, new int[]{1937, 1993, 1987, 1935, 7919}, 1, 16, 27, 2046),
        SEED_STALL(7053, 27, new int[]{5096, 5097, 5098, 5099, 5100, 5101, 5102, 5103, 5105}, 30, 11, 10, 2047),
        FUR_STALL(34387, 35, new int[]{6814, 958}, 1, 15, 36, 34381),
        FISH_STALL(4705, 42, new int[]{331, 359, 377}, 1, 16, 42, 34381),
        CROSSBOW_STALL(0, 1, new int[]{9177, 9179, 9181, 9183, 9185}, 1, 11, 52, 34381),
        SILVER_STALL(0, 50, new int[]{442}, 1, 30, 54, 34381),
        SPICE_STALL(34386, 65, new int[]{2007}, 1, 80, 81, 34381),
        MAGIC_STALL(0, 65, new int[]{556, 557, 554, 555, 563}, 30, 80, 100, 34381),
        SCIMITAR_STALL(0, 45, new int[]{1327, 1329, 1331, 1333}, 1, 5, 100, 34381),
        GEM_STALL(34385, 75, new int[]{1623, 1621, 1619, 1617, 1631}, 1, 2.5, 160, 34381);

        private int[] item;
        private int level;
        private int amount;
        private int objectId;
        private int replaceObject;
        private double experience;
        private double seconds;

        Stalls(int objectId, int level, int[] item, int amount, double seconds, double experience, int replaceObject) {
            this.objectId = objectId;
            this.level = level;
            this.item = item;
            this.amount = amount;
            this.seconds = seconds;
            this.experience = experience;
            this.replaceObject = replaceObject;
        }

        public int getReplaceObject() {
            return replaceObject;
        }

        public int getObjectId() {
            return objectId;
        }

        public int getItem(int count) {
            return item[count];
        }

        public int getAmount() {
            return amount;
        }

        public int getLevel() {
            return level;
        }

        public double getTime() {
            return seconds;
        }

        public double getExperience() {
            return experience;
        }
    }

    public static boolean isGuard(int npcId) {
        if (npcId == 32 || npcId == 21 || npcId == 2256 || npcId == 23)
            return true;
        else
            return false;
    }

    public static double getThievingBoost(Player player) {
        double boost = 1.0;
        for (Pieces pieces : BlackIbisOutfit.data) {
            if (player.getEquipment().containsOneItem(pieces.getItemId()))
                boost += 0.01;
        }
        if (hasBlackIbisSuit(player))
            boost += 0.01;
        return boost;
    }

    private static boolean hasBlackIbisSuit(Player player) {
        boolean hasSet = true;
        for (Pieces pieces : BlackIbisOutfit.data) {
            if (!player.getEquipment().containsOneItem(pieces.getItemId()))
                hasSet = false;
        }
        return hasSet;
    }

    public static void handleStalls(final Player player, final WorldObject object) {
        if (player.getAttackedBy() != null && player.getAttackedByDelay() > Utils.currentTimeMillis()) {
            player.getPackets().sendGameMessage("You can't do this while you're under combat.");
            return;
        }
        for (final Stalls stall : Stalls.values()) {
            if (stall.getObjectId() == object.getId()) {
                if (player.getSkills().getLevel(Skills.THIEVING) < stall.getLevel()) {
                    player.getPackets().sendGameMessage(
                            "You need a thieving level of " + stall.getLevel() + " to steal from this.", true);
                    return;
                }
                if (player.getInventory().getFreeSlots() <= 0) {
                    player.getPackets().sendGameMessage("Not enough space in your inventory.", true);
                    return;
                }
                if (player.getThievingDelay() > Utils.currentTimeMillis())
                    return;
                player.setThievingDelay(5);
                player.animate(new Animation(881));
                player.lock(2);
                WorldTasksManager.schedule(new WorldTask() {

                    @Override
                    public void run() {
                        if (!World.containsObjectWithId(object, object.getId()))
                            return;
                        if (stall.getObjectId() == 34384) {
                            player.getTaskManager().checkComplete(Tasks.STEAL_FROM_BAKERS_STALL);
                            player.getMoneyPouch().addMoney(5000, false);
                        }
                        if (stall.getObjectId() == 34383) {
                            player.getTaskManager().checkComplete(Tasks.STEAL_FROM_SILK_STALL);
                            player.getMoneyPouch().addMoney(7500, false);
                        }
                        if (stall.getObjectId() == 34385) {
                            player.getTaskManager().checkComplete(Tasks.STEAL_FROM_GEM_STALL);
                            player.getMoneyPouch().addMoney(10000, false);
                        }
                        BlackIbisOutfit.addPiece(player);
                        player.getInventory().addItem(stall.getItem(Utils.getRandom(stall.item.length - 1)),
                                Utils.random(1, stall.getAmount()));
                        double totalXp = stall.getExperience();
                        totalXp *= getThievingBoost(player);
                        player.getSkills().addSkillingXp(Skills.THIEVING, totalXp, getThievingBoost(player));
                        checkGuards(player);
                        WorldObject emptyStall = new WorldObject(stall.getReplaceObject(), object.getType(), object.getRotation(), object);
                        if (World.removeObjectTemporary(object, (int) (stall.getTime() * 1500)))
                            World.spawnObject(emptyStall);
                        stop();
                        /*
                         * World.spawnObjectTemporary(emptyStall, (int) (1500 * stall.getTime()));
                         */
                    }
                }, 0, 0);
            }
        }
    }

    public static void checkGuards(Player player) {
        NPC guard = null;
        int lastDistance = -1;
        for (int regionId : player.getMapRegionsIds()) {
            List<Integer> npcIndexes = World.getRegion(regionId).getNPCsIndexes();
            if (npcIndexes == null)
                continue;
            for (int npcIndex : npcIndexes) {
                NPC npc = World.getNPCs().get(npcIndex);
                if (npc == null)
                    continue;
                if (!isGuard(npc.getId()) || npc.isUnderCombat() || npc.isDead() || !npc.withinDistance(player, 4)
                        || !npc.clipedProjectile(player, true))
                    continue;
                int distance = Utils.getDistance(npc.getX(), npc.getY(), player.getX(), player.getY());
                if (lastDistance == -1 || lastDistance > distance) {
                    guard = npc;
                    lastDistance = distance;
                }
            }
        }
        if (guard != null) {
            guard.setNextForceTalk(new ForceTalk("Hey, what do you think you are doing!"));
            guard.setTarget(player);
        }
    }

    public static void pickHamHideout(Player player, WorldObject object) {
        player.sm("You attempt to picklock the trapdoor..");
        player.faceObject(object);
        player.lock(3);
        WorldTasksManager.schedule(new WorldTask() {

            @Override
            public void run() {
                int thievingLevel = player.getSkills().getLevel(Skills.THIEVING);
                int increasedChance = (int) (thievingLevel * 0.5);
                double ratio = Utils.getRandom(100) - increasedChance;
                if (ratio * thievingLevel < 10) {
                    player.sm("You fail to picklock the trapdoor and your hands begin to numb down.");
                    player.drainLevel(Skills.THIEVING, 1);
                    player.addXp(Skills.THIEVING, 1);
                    stop();
                    return;
                }
                player.sm("You successfully picklock the trapdoor.");
                player.addXp(Skills.THIEVING, 4);
                player.sendVarBit(object.getConfigByFile(), 1);
                stop();
            }
        }, 2, 2);
    }

    public static boolean pickSouthGate(final Player player, final WorldObject object) {
        player.getPackets().sendGameMessage("You attempt to picklock the gate..");
        player.lock(3);
        player.animate(new Animation(881));
        player.faceObject(object);
        WorldTasksManager.schedule(new WorldTask() {
            int loop = 0;

            @Override
            public void run() {
                if (loop == 1) {
                    pickSouthGate2(player, object);
                } else if (loop == 3) {
                    stop();
                }
                loop++;
            }
        }, 0, (int) 0.25);
        return true;
    }

    public static boolean pickSouthGate2(Player player, WorldObject object) {
        if (player.temporaryAttribute().get("numbFingers") == null)
            player.temporaryAttribute().put("numbFingers", 0);
        int thievingLevel = player.getSkills().getLevel(Skills.THIEVING);
        int increasedChance = getIncreasedChance(player);
        int decreasedChance = (Integer) player.temporaryAttribute().get("numbFingers");
        int level = Utils.getRandom(thievingLevel + (increasedChance - decreasedChance)) + 1;
        double ratio = level / (Utils.getRandom(45 + 5) + 1);
        if (Math.round(ratio * thievingLevel) < (player.getAttackedByDelay() > 0 ? 50 : 40)
                / player.getAuraManager().getThievingAccurayMultiplier()) {
            player.getPackets().sendGameMessage("You fail to picklock the gate and your hands begin to numb down.");
            player.temporaryAttribute().put("numbFingers", decreasedChance + 1);
            return false;
        }
        player.getPackets().sendGameMessage("You successfully picklock the gate.");
        AxeHut.GateSouth(player, object);
        return true;
    }

    public static boolean pickNorthGate(final Player player, final WorldObject object) {
        player.getPackets().sendGameMessage("You attempt to picklock the gate..");
        player.lock(3);
        player.animate(new Animation(881));
        player.faceObject(object);
        WorldTasksManager.schedule(new WorldTask() {
            int loop = 0;

            @Override
            public void run() {
                if (loop == 1) {
                    pickNorthGate2(player, object);
                } else if (loop == 3) {
                    stop();
                }
                loop++;
            }
        }, 0, (int) 0.25);
        return true;
    }

    public static boolean pickNorthGate2(Player player, WorldObject object) {
        if (player.temporaryAttribute().get("numbFingers") == null)
            player.temporaryAttribute().put("numbFingers", 0);
        int thievingLevel = player.getSkills().getLevel(Skills.THIEVING);
        int increasedChance = getIncreasedChance(player);
        int decreasedChance = (Integer) player.temporaryAttribute().get("numbFingers");
        int level = Utils.getRandom(thievingLevel + (increasedChance - decreasedChance)) + 1;
        double ratio = level / (Utils.getRandom(45 + 5) + 1);
        if (Math.round(ratio * thievingLevel) < (player.getAttackedByDelay() > 0 ? 50 : 40)
                / player.getAuraManager().getThievingAccurayMultiplier()) {
            player.getPackets().sendGameMessage("You fail to picklock the gate and your hands begin to numb down.");
            player.temporaryAttribute().put("numbFingers", decreasedChance + 1);
            return false;
        }
        player.getPackets().sendGameMessage("You successfully picklock the gate.");
        AxeHut.GateNorth(player, object);
        return true;
    }

    public static boolean pickEastDoor(final Player player, final WorldObject object) {
        player.getPackets().sendGameMessage("You attempt to picklock the door..");
        player.lock(3);
        player.animate(new Animation(881));
        WorldTasksManager.schedule(new WorldTask() {
            int loop = 0;

            @Override
            public void run() {
                if (loop == 1) {
                    pickEastDoor2(player, object);
                } else if (loop == 3) {
                    stop();
                }
                loop++;
            }
        }, 0, (int) 0.25);
        return true;
    }

    public static boolean pickEastDoor2(Player player, WorldObject object) {
        if (player.temporaryAttribute().get("numbFingers") == null)
            player.temporaryAttribute().put("numbFingers", 0);
        int thievingLevel = player.getSkills().getLevel(Skills.THIEVING);
        int increasedChance = getIncreasedChance(player);
        int decreasedChance = (Integer) player.temporaryAttribute().get("numbFingers");
        int level = Utils.getRandom(thievingLevel + (increasedChance - decreasedChance)) + 1;
        double ratio = level / (Utils.getRandom(45 + 5) + 1);
        if (Math.round(ratio * thievingLevel) < (player.getAttackedByDelay() > 0 ? 50 : 40)
                / player.getAuraManager().getThievingAccurayMultiplier()) {
            player.getPackets().sendGameMessage("You fail to picklock the door and your hands begin to numb down.");
            player.temporaryAttribute().put("numbFingers", decreasedChance + 1);
            return false;
        }
        player.getPackets().sendGameMessage("You successfully picklock the door.");
        PirateHut.EnterEastDoor(player, object);
        return true;
    }

    public static boolean pickWestDoor(final Player player, final WorldObject object) {
        player.getPackets().sendGameMessage("You attempt to picklock the door..");
        player.lock(3);
        player.animate(new Animation(881));
        WorldTasksManager.schedule(new WorldTask() {
            int loop = 0;

            @Override
            public void run() {
                if (loop == 1) {
                    pickWestDoor2(player, object);
                } else if (loop == 5) {
                    stop();
                }
                loop++;
            }
        }, 0, (int) 0.25);
        return true;
    }

    public static boolean pickWestDoor2(Player player, WorldObject object) {
        if (player.temporaryAttribute().get("numbFingers") == null)
            player.temporaryAttribute().put("numbFingers", 0);
        int thievingLevel = player.getSkills().getLevel(Skills.THIEVING);
        int increasedChance = getIncreasedChance(player);
        int decreasedChance = (Integer) player.temporaryAttribute().get("numbFingers");
        int level = Utils.getRandom(thievingLevel + (increasedChance - decreasedChance)) + 1;
        double ratio = level / (Utils.getRandom(45 + 5) + 1);
        if (Math.round(ratio * thievingLevel) < (player.getAttackedByDelay() > 0 ? 50 : 40)
                / player.getAuraManager().getThievingAccurayMultiplier()) {
            player.getPackets().sendGameMessage("You fail to picklock the door and your hands begin to numb down.");
            player.temporaryAttribute().put("numbFingers", decreasedChance + 1);
            return false;
        }
        player.getPackets().sendGameMessage("You successfully picklock the door.");
        PirateHut.EnterWestDoor(player, object);
        return true;
    }

    public static boolean pickNorthDoor(final Player player, final WorldObject object) {
        player.getPackets().sendGameMessage("You attempt to picklock the door..");
        player.lock(3);
        player.animate(new Animation(881));
        WorldTasksManager.schedule(new WorldTask() {
            int loop = 0;

            @Override
            public void run() {
                if (loop == 1) {
                    pickNorthDoor2(player, object);
                } else if (loop == 3) {
                    stop();
                }
                loop++;
            }
        }, 0, (int) 0.25);
        return true;
    }

    public static boolean pickNorthDoor2(Player player, WorldObject object) {
        if (player.temporaryAttribute().get("numbFingers") == null)
            player.temporaryAttribute().put("numbFingers", 0);
        int thievingLevel = player.getSkills().getLevel(Skills.THIEVING);
        int increasedChance = getIncreasedChance(player);
        int decreasedChance = (Integer) player.temporaryAttribute().get("numbFingers");
        int level = Utils.getRandom(thievingLevel + (increasedChance - decreasedChance)) + 1;
        double ratio = level / (Utils.getRandom(45 + 5) + 1);
        if (Math.round(ratio * thievingLevel) < (player.getAttackedByDelay() > 0 ? 50 : 40)
                / player.getAuraManager().getThievingAccurayMultiplier()) {
            player.getPackets().sendGameMessage("You fail to picklock the door and your hands begin to numb down.");
            player.temporaryAttribute().put("numbFingers", decreasedChance + 1);
            return false;
        }
        player.getPackets().sendGameMessage("You successfully picklock the door.");
        PirateHut.EnterNorthDoor(player, object);
        return true;
    }

    private static int getIncreasedChance(Player player) {
        int chance = 0;
        if (Equipment.getItemSlot(Equipment.SLOT_HANDS) == 10075)
            chance += 12;
        player.getEquipment();
        if (Equipment.getItemSlot(Equipment.SLOT_CAPE) == 15349)
            chance += 15;
        return chance;
    }

}
