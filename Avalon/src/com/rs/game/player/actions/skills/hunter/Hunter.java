package com.rs.game.player.actions.skills.hunter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rs.game.Animation;
import com.rs.game.ForceTalk;
import com.rs.game.Graphics;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.content.treasuretrails.TreasureTrailsManager;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

public class Hunter {

    public static final Animation CAPTURE_ANIMATION = new Animation(6606);

    public enum FlyingEntities {

        BABY_IMPLING(11238, 1, 1, 20, 25, 17,
                new Item[]{new Item(1755),
                        new Item(1734),
                        new Item(1733),
                        new Item(1985),
                        new Item(2347),
                        new Item(1759),
                        new Item(319)},

                null,

                new Item[]{new Item(2007),
                        new Item(1779),
                        new Item(7170),
                        new Item(401),
                        new Item(1438),
                        new Item(2355),
                        new Item(1607),
                        new Item(1743),
                        new Item(379),
                        new Item(1761),
                        new Item(TreasureTrailsManager.CLUE_SCROLLS[0])},

                null),

        BUTTERFLYTEST(11238, 1, 1, 20, 25, 17, new Item[]{new Item(1, 2), new Item(2, Utils.random(1, 5)), new Item(3), new Item(4)}, null, null, null) {
            @Override
            public void effect(Player player) {
                // stat boost
            }
        };

        static final List<FlyingEntities> flyingEntities = new ArrayList<FlyingEntities>();

        static {
            for (FlyingEntities impling : FlyingEntities.values())
                flyingEntities.add(impling);
        }

        public static FlyingEntities forItemId(int itemId) {
            for (FlyingEntities imp : FlyingEntities.values()) {
                if (imp.getItemId() == itemId)
                    return imp;
            }
            return null;
        }

        private int npcId, level, reward, itemId;
        private double puroExperience, rsExperience;
        private Item[] common, uncommon, rare, extremelyRare;
        private Graphics graphics;

        private FlyingEntities(int itemId, int npcId, int reward, double puroExperience, double rsExperience, int level,
                               Graphics graphics, Item[] common, Item[] uncommon, Item[] rare, Item[] extremelyRare) {
            this.itemId = itemId;
            this.npcId = npcId;
            this.reward = reward;
            this.puroExperience = puroExperience;
            this.rsExperience = rsExperience;
            this.level = level;
            this.common = common;
            this.uncommon = uncommon;
            this.rare = rare;
            this.extremelyRare = extremelyRare;
            this.graphics = graphics;
        }

        private FlyingEntities(int itemId, int npcId, int reward, double puroExperience, double rsExperience, int level,
                               Item[] common, Item[] uncommon, Item[] rare, Item[] extremelyRare) {
            this.itemId = itemId;
            this.npcId = npcId;
            this.reward = reward;
            this.puroExperience = puroExperience;
            this.rsExperience = rsExperience;
            this.level = level;
            this.common = common;
            this.uncommon = uncommon;
            this.rare = rare;
            this.extremelyRare = extremelyRare;
        }

        public int getNpcId() {
            return npcId;
        }

        public int getLevel() {
            return level;
        }

        public int getReward() {
            return reward;
        }

        public double getPuroExperience() {
            return puroExperience;
        }

        public double getRsExperience() {
            return rsExperience;
        }

        public Item[] getCommon() {
            return common;
        }

        public int getItemId() {
            return itemId;
        }

        public Item[] getUncommon() {
            return uncommon;
        }

        public Item[] getRare() {
            return rare;
        }

        public Item[] getExtremelyRare() {
            return extremelyRare;
        }

        public Graphics getGraphics() {
            return graphics;
        }

        public void effect(Player player) {

        }
    }

    public interface DynamicFormula {

        public int getExtraProbablity(Player player);

    }

    public static void captureFlyingEntity(final Player player, final NPC npc) {
        final String name = npc.getDefinitions().name.toUpperCase();
        final FlyingEntities instance = FlyingEntities.valueOf(name);
        final boolean isImpling = name.toLowerCase().contains("impling");
        if (!player.getInventory().containsItem(isImpling ? 1 : 11, 1))
            return;
        player.lock(1);
        player.getPackets().sendGameMessage("You swing your net...");
        player.animate(CAPTURE_ANIMATION);
        WorldTasksManager.schedule(new WorldTask() {
            @Override
            public void run() {
                if (isSuccessful(player, instance.getLevel(), new DynamicFormula() {

                    @Override
                    public int getExtraProbablity(Player player) {
                        if (player.getInventory().containsItem(3000, 1)
                                || player.getEquipment().getItem(3).getId() == 3000)
                            return 3;// magic net
                        else if (player.getInventory().containsItem(3001, 1)
                                || player.getEquipment().getItem(3).getId() == 3000)
                            return 2;// regular net
                        return 1;// hands
                    }
                })) {
                    player.getSkills().addXp(Skills.HUNTER, instance.getRsExperience());
                    npc.finish();
                    player.getInventory().addItem(new Item(instance.getReward(), 1));
                    player.getPackets().sendGameMessage("...and you successfully caputure the " + name.toLowerCase());
                } else {
                    if (isImpling) {
                        npc.setNextForceTalk(new ForceTalk("Tehee, you missed me!"));
                        WorldTasksManager.schedule(new WorldTask() {
                            @Override
                            public void run() {
                                WorldTile teleTile = npc;
                                for (int trycount = 0; trycount < 10; trycount++) {
                                    teleTile = new WorldTile(npc, 10);
                                    if (World.canMoveNPC(npc.getPlane(), teleTile.getX(), teleTile.getY(),
                                            player.getSize()))
                                        break;
                                    teleTile = npc;
                                }
                                npc.setNextWorldTile(teleTile);
                            }
                        });
                    }
                    player.getPackets().sendGameMessage("...you stumble and miss the " + name.toLowerCase());
                }
            }
        });
    }

    public static void openJar(Player player, int slotId, Item jar, FlyingEntities instance) {
        boolean isImpling = instance.toString().toLowerCase().contains("impling");
        if (!player.getInventory().hasFreeSlots()) {
            player.sm("You need at least 1 inventory space to open the jar.");
            return;
        }
        Item item = null;
        if (isImpling) {
            int random = Utils.getRandom(100);
            int type = -1;
            while (type == -1) {
                random = Utils.getRandom(100);
                if (random <= 0) {
                    type = instance.getExtremelyRare() == null ? -1 : 3;
                } else if (random > 1 && random <= 10) {
                    type = instance.getRare() == null ? -1 : 2;
                } else if (random > 10 && random <= 30) {
                    type = instance.getUncommon() == null ? -1 : 1;
                } else {
                    type = 0;
                }
            }
            if (type == 0)
                item = instance.getCommon()[Utils.getRandom(instance.getCommon().length - 1)];
            if (type == 1)
                item = instance.getUncommon()[Utils.getRandom(instance.getUncommon().length - 1)];
            if (type == 2)
                item = instance.getRare()[Utils.getRandom(instance.getRare().length - 1)];
            if (type == 4)
                item = instance.getExtremelyRare()[Utils.getRandom(instance.getExtremelyRare().length - 1)];
        } else
            instance.effect(player);
        player.getInventory().deleteItem(slotId, jar);
        if (item != null)
            player.getInventory().addItem(item);
        if (Utils.random(100) > 93) {
            player.getPackets().sendGameMessage("You press too hard on the jar and the glass shatters in your hands.");
        } else {
            player.getInventory().addItem(11260, 1);
        }
    }

    static int[] requiredLogs = new int[]{1151, 1153, 1155, 1157, 1519, 1521, 13567, 1521, 2862, 10810, 6332, 12581};

    public static void createLoggedObject(Player player, WorldObject object, boolean kebbits) {
        if (!player.getInventory().containsOneItem(requiredLogs)) {
            player.getPackets().sendGameMessage("You need to have logs to create this trap.");
            return;
        }
        player.lock(3);
        player.getActionManager().setActionDelay(3);
        player.animate(new Animation(5208));
        if (World.removeObjectTemporary(object, 300000, false)) {
            World.spawnObjectTemporary(
                    new WorldObject(kebbits ? 19206 : -1, object.getType(), object.getRotation(), object), 300000);
            Item item = null;
            for (int requiredLog : requiredLogs) {
                if ((item = player.getInventory().getItems().lookup(requiredLog)) != null) {
                    player.getInventory().deleteItem(item);
                }
            }
        }
    }

    public static boolean isSuccessful(Player player, int dataLevel, DynamicFormula formula) {
        int hunterlevel = player.getSkills().getLevel(Skills.HUNTER);
        int increasedProbability = formula == null ? 1 : formula.getExtraProbablity(player);
        int level = Utils.random(hunterlevel + increasedProbability) + 1;
        double ratio = level / (Utils.random(dataLevel + 4) + 1);
        if (Math.round(ratio * dataLevel) < dataLevel) {
            return false;
        }
        return true;
    }
}
