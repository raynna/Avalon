package com.rs.game.player.actions.skills.woodcutting;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.CombatEventNPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.game.player.actions.skills.woodcutting.LumberjackOutfit.Pieces;
import com.rs.game.player.content.tasksystem.TaskManager.Tasks;
import com.rs.utils.Utils;

/**
 *
 * @Improved Andreas - AvalonPK
 *
 */

public final class Woodcutting extends Action {

    public static enum TreeDefinitions {

        NORMAL(1, 25, 1511, 10, 4, 8, 100), // TODO

        EVERGREEN(1, 25, 1511, 10, 4, 8, 0),

        DEAD(1, 25, 1511, 10, 4, 8, 0),

        SWAMP(1, 25, 1511, 10, 4, 8, 0),

        ACHEY(1, 25, 2862, 10, 4, 8, 0),

        VINES(34, 1, 5555, 10, 4, 60, 0),

        OAK(15, 37.5, 1521, 15, 8, 15, 15), // TODO

        WILLOW(30, 67.5, 1519, 25, 12, 51, 15), // TODO

        MAPLE(45, 100, 1517, 35, 16, 72, 10),

        YEW(60, 175, 1515, 50, 17, 94, 10), // TODO

        IVY(68, 332.5, -1, 55, 17, 58, 10),

        MAGIC(75, 250, 1513, 70, 21, 121, 10),

        CURSED_MAGIC(82, 250, 1513, 70, 21, 121, 10),

        FRUIT_TREES(1, 25, -1, 20, 4, 1341, 8),

        BLOODWOOD(85, 320, 24121, 74, 25, 242, 20),

        TANGLE_GUM_VINE(1, 35, 17682, 20, 4, 49706, 8),

        SEEPING_ELM_TREE(10, 60, 17684, 25, 4, 49708, 12),

        BLOOD_SPINDLE_TREE(20, 85, 17686, 35, 4, 49710, 16),

        UTUKU_TREE(30, 115, 17688, 60, 4, 49712, 51),

        SPINEBEAM_TREE(40, 145, 17690, 76, 16, 49714, 68),

        BOVISTRANGLER_TREE(50, 175, 17692, 85, 16, 49716, 75),

        THIGAT_TREE(60, 210, 17694, 95, 16, 49718, 83),

        CORPESTHORN_TREE(70, 245, 17696, 111, 16, 49720, 90),

        ENTGALLOW_TREE(80, 285, 17698, 120, 17, 49722, 94),

        GRAVE_CREEPER_TREE(90, 330, 17700, 150, 21, 49724, 121),
        MAHOGANY_TREE(50, 125, 6332, 35, 16, 72, 10),
        TEAK_TREE(35, 85, 6333, 35, 16, 72, 10),
        ;

        private int level;
        private double xp;
        private int logsId;
        private int logBaseTime;
        private int logRandomTime;
        private int respawnDelay;
        private int randomLifeProbability;

        private TreeDefinitions(int level, double xp, int logsId, int logBaseTime, int logRandomTime, int respawnDelay,
                                int randomLifeProbability) {
            this.level = level;
            this.xp = xp;
            this.logsId = logsId;
            this.logBaseTime = logBaseTime;
            this.logRandomTime = logRandomTime;
            this.respawnDelay = respawnDelay;
            this.randomLifeProbability = randomLifeProbability;
        }

        public int getLevel() {
            return level;
        }

        public double getXp() {
            return xp;
        }

        public int getLogsId() {
            return logsId;
        }

        public int getLogBaseTime() {
            return logBaseTime;
        }

        public int getLogRandomTime() {
            return logRandomTime;
        }

        public int getRespawnDelay() {
            return respawnDelay;
        }

        public int getRandomLifeProbability() {
            return randomLifeProbability;
        }
    }

    public enum HatchetDefinitions {

        BRONZE(1351, 1, 1, 879),

        IRON(1349, 1, 2, 877),

        STEEL(1353, 6, 3, 875),

        BLACK(1361, 11, 4, 873),

        MITHRIL(1355, 21, 5, 871),

        ADAMANT(1357, 31, 7, 869),

        RUNE(1359, 41, 10, 867),

        DRAGON(6739, 61, 13, 2846),

        INFERNO(13661, 61, 10, 10251);

        private int itemId, levelRequried, axeTime, emoteId;

        private HatchetDefinitions(int itemId, int levelRequried, int axeTime, int emoteId) {
            this.itemId = itemId;
            this.levelRequried = levelRequried;
            this.axeTime = axeTime;
            this.emoteId = emoteId;
        }

        public int getItemId() {
            return itemId;
        }

        public int getLevelRequried() {
            return levelRequried;
        }

        public int getAxeTime() {
            return axeTime;
        }

        public int getEmoteId() {
            return emoteId;
        }
    }

    private WorldObject tree;
    private TreeDefinitions definitions;

    private int emoteId;
    private boolean usingBeaver = false;
    private int axeTime;

    public Woodcutting(WorldObject tree, TreeDefinitions definitions) {
        this.tree = tree;
        this.definitions = definitions;
    }

    @Override
    public boolean start(Player player) {
        if (!checkAll(player))
            return false;
        player.getPackets().sendGameMessage(usingBeaver ? "Your beaver uses its strong teeth to chop down the tree..."
                : "You swing your hatchet at the " + (TreeDefinitions.IVY == definitions ? "ivy" : "tree") + ".", true);
        setActionDelay(player, getWoodcuttingDelay(player));
        return true;
    }

    private int getWoodcuttingDelay(Player player) {
        int summoningBonus = player.getFamiliar() != null
                ? (player.getFamiliar().getId() == 6808 || player.getFamiliar().getId() == 6807) ? 10 : 0
                : 0;
        int wcTimer = definitions.getLogBaseTime() + Utils.getRandom(definitions.getLevel())
                - (player.getSkills().getLevel(8) + summoningBonus) - Utils.getRandom(axeTime);
        if (wcTimer < 1 + definitions.getLogRandomTime())
            wcTimer = 1 + Utils.getRandom(definitions.getLogRandomTime());
        wcTimer /= player.getAuraManager().getWoodcuttingAccurayMultiplier();
        return wcTimer;
    }

    public int getStumpId() {
        switch (tree.getId()) {
            case 3300:// swamp tree
            case 9354:
            case 9355:
            case 9366:
            case 9387:
            case 9388:
                return 9389;
            case 11866:// dead tree
                return 11864;
            case 38782:
            case 38760:
                return 40350;
            case 38783:
            case 38784:
                return 40352;
            case 38785:
            case 38786:
                return 40354;
            case 38787:
                return 40356;
            case 38788:
                return 40357;
            case 38731:
                return 38754;
            case 38732:
                return 38741;
            case 38627:
            case 38616:
                return 38725;
            case 4674:
            case 46277:
            case 51843:
                return 54766;
            case 70076:
                return 70081;
            case 70075:
                return 70080;
            case 70074:
                return 70079;
            case 70077:
            case 46274:
                return 70082;
            case 38755:
                return 38759;
            case 63176:
            case 37823:
                return 63179;
            case 37821:
                return 37822;
            case 69554:
                return 69555;
            case 69556:
                return 69557;
            case 4135:
            case 19153:
                return 4136;
            case 61192:
                return 40355;
            default:
                return 0;
        }
    }

    private boolean checkAll(Player player) {
        if (!setAxe(player) || !hasAxe(player)) {
            player.getPackets().sendGameMessage("You need an axe to chop down this tree.");
            player.getPackets().sendGameMessage("You do not have an axe which you have the woodcutting level to use.");
            return false;
        }
        if (!hasWoodcuttingLevel(player))
            return false;
        if (!player.getInventory().hasFreeSlots()) {
            player.getPackets().sendGameMessage("Not enough space in your inventory.");
            return false;
        }
        return true;
    }

    public static HatchetDefinitions getHatchet(Player player) {
        HatchetDefinitions hatchet = null;
        for (HatchetDefinitions def : HatchetDefinitions.values()) {
            if (player.getEquipment().getWeaponId() == def.itemId
                    || player.getInventory().containsItem(def.itemId, 1)) {
                hatchet = def;
                if (player.getSkills().getLevel(Skills.WOODCUTTING) < hatchet.levelRequried) {
                    hatchet = null;
                    break;
                }
            }
        }
        return hatchet;
    }

    private boolean hasWoodcuttingLevel(Player player) {
        if (definitions.getLevel() > player.getSkills().getLevel(Skills.WOODCUTTING)) {
            player.getPackets().sendGameMessage(
                    "You need a Woodcutting level of " + definitions.getLevel() + " to chop down this tree.");
            return false;
        }
        return true;
    }

    private boolean setAxe(Player player) {
        int level = player.getSkills().getLevel(8);
        int weaponId = player.getEquipment().getWeaponId();
        if (weaponId != -1) {
            switch (weaponId) {
                case 6739: // dragon axe
                    if (level >= 61) {
                        emoteId = 2846;
                        axeTime = 13;
                        return true;
                    }
                    break;
                case 1359: // rune axe
                    if (level >= 41) {
                        emoteId = 867;
                        axeTime = 10;
                        return true;
                    }
                    break;
                case 1357: // adam axe
                    if (level >= 31) {
                        emoteId = 869;
                        axeTime = 7;
                        return true;
                    }
                    break;
                case 1355: // mit axe
                    if (level >= 21) {
                        emoteId = 871;
                        axeTime = 5;
                        return true;
                    }
                    break;
                case 1361: // black axe
                    if (level >= 11) {
                        emoteId = 873;
                        axeTime = 4;
                        return true;
                    }
                    break;
                case 1353: // steel axe
                    if (level >= 6) {
                        emoteId = 875;
                        axeTime = 3;
                        return true;
                    }
                    break;
                case 1349: // iron axe
                    emoteId = 877;
                    axeTime = 2;
                    return true;
                case 1351: // bronze axe
                    emoteId = 879;
                    axeTime = 1;
                    return true;
                case 13661: // Inferno adze
                    if (level >= 61) {
                        emoteId = 10251;
                        axeTime = 10;
                        return true;
                    }
                    break;
            }
        }
        if (player.getInventory().containsOneItem(6739)) {
            if (level >= 61) {
                emoteId = 2846;
                axeTime = 13;
                return true;
            }
        }
        if (player.getInventory().containsOneItem(1359)) {
            if (level >= 41) {
                emoteId = 867;
                axeTime = 10;
                return true;
            }
        }
        if (player.getInventory().containsOneItem(1357)) {
            if (level >= 31) {
                emoteId = 869;
                axeTime = 7;
                return true;
            }
        }
        if (player.getInventory().containsOneItem(1355)) {
            if (level >= 21) {
                emoteId = 871;
                axeTime = 5;
                return true;
            }
        }
        if (player.getInventory().containsOneItem(1361)) {
            if (level >= 11) {
                emoteId = 873;
                axeTime = 4;
                return true;
            }
        }
        if (player.getInventory().containsOneItem(1353)) {
            if (level >= 6) {
                emoteId = 875;
                axeTime = 3;
                return true;
            }
        }
        if (player.getInventory().containsOneItem(1349)) {
            emoteId = 877;
            axeTime = 2;
            return true;
        }
        if (player.getInventory().containsOneItem(1351) || player.getToolbelt().contains(1351)) {
            emoteId = 879;
            axeTime = 1;
            return true;
        }
        if (player.getInventory().containsOneItem(13661)) {
            if (level >= 61) {
                emoteId = 10251;
                axeTime = 10;
                return true;
            }
        }
        return false;

    }

    private boolean hasAxe(Player player) {
        if (player.getInventory().containsOneItem(1351, 1349, 1353, 1355, 1357, 1361, 1359, 6739, 13661)
                || player.getToolbelt().contains(1351))
            return true;
        int weaponId = player.getEquipment().getWeaponId();
        if (weaponId == -1)
            return false;
        switch (weaponId) {
            case 1351:// Bronze Axe
            case 1349:// Iron Axe
            case 1353:// Steel Axe
            case 1361:// Black Axe
            case 1355:// Mithril Axe
            case 1357:// Adamant Axe
            case 1359:// Rune Axe
            case 6739:// Dragon Axe
            case 13661: // Inferno adze
                return true;
            default:
                return false;
        }

    }

    @Override
    public boolean process(Player player) {
        player.animate(new Animation(usingBeaver ? 1 : emoteId));
        return checkTree(player);
    }

    private boolean usedDeplateAurora;

    private boolean doubleLoot(Player player, boolean message) {
        int chance = 6;
        if (Utils.getRandom(100) <= chance) {
            if (message)
                player.getPackets().sendGameMessage("You managed to chop an extra " + tree.getDefinitions().name + ".");
            return true;
        }
        return false;
    }

    @Override
    public int processWithDelay(Player player) {
        addLog(player);
        player.animate(new Animation(usingBeaver ? 1 : emoteId));
        if (!usedDeplateAurora && (1 + Math.random()) < player.getAuraManager().getChanceNotDepleteMN_WC()) {
            usedDeplateAurora = true;
        } else if (Utils.getRandom(definitions.getRandomLifeProbability()) == 0) {
            long time = definitions.respawnDelay * 600;
            if (definitions == TreeDefinitions.VINES) {
                handleVines(player);
            } else {
                World.spawnObjectTemporary(new WorldObject(getStumpId(), tree.getType(), tree.getRotation(),
                        tree.getX(), tree.getY(), tree.getPlane()), time);
            }
            if (tree.getPlane() < 3) {
                if (definitions != TreeDefinitions.IVY || definitions != TreeDefinitions.VINES) {
                    WorldObject object = World
                            .getStandardFloorObject(new WorldTile(tree.getX() - 1, tree.getY() - 1, tree.getPlane() + 1));
                    if (object == null) {
                        object = World
                                .getStandardFloorObject(new WorldTile(tree.getX(), tree.getY() - 1, tree.getPlane() + 1));
                        if (object == null) {
                            object = World.getStandardFloorObject(
                                    new WorldTile(tree.getX() - 1, tree.getY(), tree.getPlane() + 1));
                            if (object == null) {
                                object = World.getStandardFloorObject(
                                        new WorldTile(tree.getX(), tree.getY(), tree.getPlane() + 1));
                            }
                        }
                    }

                    if (object != null) {
                        World.removeObjectTemporary(object, time, true);
                        player.animate(new Animation(-1));
                    }
                }
            }
            return -1;
        }
        if (!player.getInventory().hasFreeSlots()) {
            player.animate(new Animation(-1));
            player.getPackets().sendGameMessage("Not enough space in your inventory.");
            return -1;
        }
        return getWoodcuttingDelay(player);
    }

    private double getWoodcuttingBoost(Player player) {
        double boost = 1.0;
        for (Pieces pieces : LumberjackOutfit.data) {
            if (player.getEquipment().containsOneItem(pieces.getItemId()))
                boost += 0.01;
        }
        if (hasLumberJackSuit(player))
            boost += 0.01;
        return boost;
    }

    private boolean hasLumberJackSuit(Player player) {
        boolean hasSet = true;
        for (Pieces pieces : LumberjackOutfit.data) {
            if (!player.getEquipment().containsOneItem(pieces.getItemId()))
                hasSet = false;
        }
        return hasSet;
    }

    public void addLog(Player player) {
        double totalXp = definitions.getXp();
        if (definitions == TreeDefinitions.IVY) {
            player.getPackets().sendGameMessage("You succesfully cut an ivy vine.", true);
        } else if (definitions == TreeDefinitions.VINES) {
            player.getPackets().sendGameMessage("You succesfully cut the vines", true);
        } else {
            String logName = ItemDefinitions.getItemDefinitions(definitions.getLogsId()).getName().toLowerCase();
            player.getPackets().sendGameMessage("You get some " + logName + ".", true);
        }
        LumberjackOutfit.addPiece(player);
        if (definitions != TreeDefinitions.VINES) {
            player.getInventory().addItem(definitions.getLogsId(), 1);
        }
        if (doubleLoot(player, true)) {
            if (definitions != TreeDefinitions.VINES && definitions != TreeDefinitions.IVY) {
                if (player.getInventory().hasFreeSlots())
                    player.getInventory().addItem(definitions.getLogsId(), 1);
                else
                    World.updateGroundItem(new Item(definitions.getLogsId(), 1), player, player, 60, 0);
                totalXp *= 2;
            }
        }
        totalXp *= getWoodcuttingBoost(player);
        player.getSkills().addXp(Skills.WOODCUTTING, totalXp);
        int rngEvent = Utils.getRandom(6000);
        if (rngEvent == 0) {
        	CombatEventNPC.startRandomEvent(player, Skills.WOODCUTTING);
        }
        if (definitions == TreeDefinitions.NORMAL || definitions == TreeDefinitions.EVERGREEN
                || definitions == TreeDefinitions.DEAD) {
            player.getTaskManager().checkComplete(Tasks.CHOP_LOGS);
        }
        if (definitions == TreeDefinitions.MAPLE) {
            player.getTaskManager().checkComplete(Tasks.CHOP_MAPLE_LOGS);
        }
        if (definitions == TreeDefinitions.YEW) {
            player.getTaskManager().checkComplete(Tasks.CHOP_YEW_LOGS);
        }
        if (definitions == TreeDefinitions.MAGIC) {
            player.getTaskManager().checkComplete(Tasks.CHOP_MAGIC_LOGS);
        }
        if (player.getEquipment().getWeaponId() == 13661
				&& !(definitions == TreeDefinitions.IVY)) {
			if (Utils.getRandom(3) == 0) {
		player.getSkills().addXp(Skills.FIREMAKING, definitions.getXp() * 1);
		player.getInventory().deleteItem(definitions.logsId, 1);
		player.getPackets().sendGameMessage("The adze's heat instantly incinerates the log.");
		player.gfx(new Graphics(1776));
			}
		}
    }

    private boolean checkTree(Player player) {
        return World.containsObjectWithId(tree, tree.getId());
    }

    private void handleVines(Player player) {
        WorldObject cutVine = new WorldObject(tree.getId(), tree.getType(), tree.getRotation(), tree.getX(),
                tree.getY(), tree.getY(), tree.getPlane());
        World.removeObjectTemporary(tree, 2400, false);
        if (player.getX() > cutVine.getX())
            player.addWalkSteps(cutVine.getX() - 1, cutVine.getY(), 2, false);
        else if (player.getX() < cutVine.getX())
            player.addWalkSteps(cutVine.getX() + 1, cutVine.getY(), 2, false);
        else if (player.getY() > cutVine.getY())
            player.addWalkSteps(cutVine.getX(), cutVine.getY() - 1, 2, false);
        else if (player.getY() < cutVine.getY())
            player.addWalkSteps(cutVine.getX(), cutVine.getY() + 1, 2, false);

    }

    @Override
    public void stop(Player player) {
        setActionDelay(player, 3);
    }

}
