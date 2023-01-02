package com.rs.game.npc.drops;

import java.util.HashMap;
import java.util.List;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

/**
 * Contains the RDT (Rare Drop Table) operations.
 *
 * @author Java100
 * @version 1.0
 * @since 11/07/2018
 */
public final class MobRewardRDT {

    /**
     * The rewards map handled as a list of possibilities via the key = rarity.
     */
    private HashMap<Integer, List<RDT>> rewards = new HashMap<>();

    /**
     * The current singleton generator.
     */
    private static final MobRewardRDT mobRewardRDT = new MobRewardRDT();

    /**
     * Get the singleton generator of this class.
     *
     * @return the instance.
     */
    public static MobRewardRDT getInstance() {
        return mobRewardRDT;
    }

    /**
     * Get the rewards map.
     *
     * @return the rewards map.
     */
    public final HashMap<Integer, List<RDT>> getRewards() {
        return rewards;
    }

    public void structureNode() {

    }

    public enum RDT {
        UNCUT_SAPPHITE(1623, 1, MobRewardNodeBuilder.COMMON),

        UNCUT_EMERALD(1621, 1, MobRewardNodeBuilder.COMMON),

        UNCUT_RUBY(1619, 1, MobRewardNodeBuilder.COMMON),

        UNCUT_DIAMOND(1617, 1, MobRewardNodeBuilder.UNCOMMON),

        COINS_1(995, 250, 490, MobRewardNodeBuilder.RARE),

        LOOP_HALF_KEY(987, 1, MobRewardNodeBuilder.UNCOMMON),

        TOOTH_HALF_KEY(985, 1, MobRewardNodeBuilder.UNCOMMON),

        SPIN_TICKET(24154, 1, MobRewardNodeBuilder.RARE),

        CHAOS_TALISMAN(1452, 1, MobRewardNodeBuilder.COMMON),

        NATURE_TALISMAN(1462, 1, MobRewardNodeBuilder.UNCOMMON),

        DRAGON_HELM(1149, 1, MobRewardNodeBuilder.RARE),

        RUNE_KITESHIELD(1201, 1, MobRewardNodeBuilder.VERY_RARE),

        SHIELD_LEFT_HALF(2366, 1, MobRewardNodeBuilder.VERY_RARE),

        RUNE_JAVELIN(830, 5, MobRewardNodeBuilder.UNCOMMON),

        DRAGON_SPEAR(1249, 1, MobRewardNodeBuilder.VERY_RARE),

        RUNE_SPEAR(1247, 1, MobRewardNodeBuilder.VERY_RARE),

        RUNE_ARROW(892, 150, MobRewardNodeBuilder.RARE),

        SILVER_ORE(443, 100, MobRewardNodeBuilder.RARE),

        BLOOD_RUNE(565, 50, MobRewardNodeBuilder.RARE),

        DEATH_RUNE(560, 5, 50, MobRewardNodeBuilder.RARE),

        LAW_RUNE(563, 45, MobRewardNodeBuilder.RARE),

        NATURE_RUNE(561, 47, 73, MobRewardNodeBuilder.RARE),

        SOUL_RUNE(566, 20, MobRewardNodeBuilder.RARE),

        COINS2(995, 250, 490, MobRewardNodeBuilder.COMMON),

        BIG_BONES(533, 154, 500, MobRewardNodeBuilder.UNCOMMON),

        PURE_ESSENCE(7937, 100, 14500, MobRewardNodeBuilder.UNCOMMON),

        AIR_ORB(574, 1000, MobRewardNodeBuilder.RARE),

        DRAGONSTONE(1615, 1, MobRewardNodeBuilder.RARE),

        FIRE_ORB(570, 1000, MobRewardNodeBuilder.RARE),

        VECNA_SKULL(20667, 1, MobRewardNodeBuilder.VERY_RARE),

        ADAMANT_BOLTS(9143, 200, MobRewardNodeBuilder.UNCOMMON),

        RUNE_ARROW2(892, 150, 500, MobRewardNodeBuilder.UNCOMMON),

        ONYX_BOLTS(9342, 150, MobRewardNodeBuilder.VERY_RARE),

        ADAMANT_BAR(2362, 1450, 7000, MobRewardNodeBuilder.RARE),

        UNCUT_DRAGONSTONE(1631, 1, MobRewardNodeBuilder.UNCOMMON),

        CLEAN_RANARR(258, 33, MobRewardNodeBuilder.UNCOMMON),

        CLEAN_SNAPDRAGON(3001, 30, 120, MobRewardNodeBuilder.UNCOMMON),

        CLEAN_TOADFLAX(2999, 250, 250, MobRewardNodeBuilder.UNCOMMON),

        CLEAN_TORSTOL(270, 10, 100, MobRewardNodeBuilder.UNCOMMON),

        YEW_LOGS(1516, 100, 4500, MobRewardNodeBuilder.RARE),

        ADAMANTITE_ORE(450, 150, 800, MobRewardNodeBuilder.UNCOMMON),

        COAL(454, 150, 7500, MobRewardNodeBuilder.UNCOMMON),

        RUNITE_ORE(452, 1, 100, MobRewardNodeBuilder.RARE),

        SARADOMIN_BREW(6686, 250, MobRewardNodeBuilder.VERY_RARE),

        RAW_SHARK(384, 250, 500, MobRewardNodeBuilder.UNCOMMON),

        WATERMELON_SEED(5321, 3, MobRewardNodeBuilder.UNCOMMON),

        MAGIC_SEED(5316, 1, 6, MobRewardNodeBuilder.RARE),

        MORCHELLA_MUSHROOM_SPORE(21620, 4, MobRewardNodeBuilder.RARE),

        PALM_TREE_SEED(5289, 10, MobRewardNodeBuilder.RARE),

        SNAPDRAGON_SEED(5300, 1, MobRewardNodeBuilder.RARE),

        TORSTOL_SEED(5304, 1, 31, MobRewardNodeBuilder.RARE),

        YEW_SEED(5315, 1, 50, MobRewardNodeBuilder.RARE),

        EARTH_TALISMAN(1441, 25, 35, MobRewardNodeBuilder.UNCOMMON),

        FIRE_TALISMAN(1443, 25, 56, MobRewardNodeBuilder.UNCOMMON),

        WATER_TALISMAN(1444, 1, MobRewardNodeBuilder.UNCOMMON),

        DRAGON_DAGGER(1215, 1, MobRewardNodeBuilder.UNCOMMON),

        RAW_SWORDFISH(372, 125, 1000, MobRewardNodeBuilder.UNCOMMON),

        BATTLESTAFF(1392, 200, MobRewardNodeBuilder.RARE),

        DRAGON_DAGGER2(1216, 50, MobRewardNodeBuilder.VERY_RARE),

        ;
        /**
         * The rarity of the RDT reward.
         */
        final int rarity;

        /**
         * The reward instanced as {@link MobReward}.
         */
        private final MobReward reward;

        /**
         * Get the reward.
         *
         * @return the reward.
         */
        public MobReward getReward() {
            return reward;
        }

        /**
         * Construct a RDT element.
         *
         * @param id     The item id.
         * @param amount The amount of the item.
         * @param rarity The rarity.
         */
        private RDT(int id, int amount, int rarity) {
            this.reward = new MobReward(id, amount);
            this.rarity = rarity;
        }

        /**
         * Construct a RDT element.
         *
         * @param id     The item id.
         * @param min    The minimum amount of the item.
         * @param max    The maximum amount of the item.
         * @param rarity The rarity.
         */
        private RDT(int id, int min, int max, int rarity) {
            this.reward = new MobReward(id, min, max);
            this.rarity = rarity;
        }

    }

    private boolean shake(Player player, int max) {
        int row = (int) (ItemDefinitions.getItemDefinitions(player.getEquipment().getRingId()).getName().toLowerCase()
                .contains("ring of wealth") ? max * 0.05 : 0);
        int random = Utils.getRandom(max - row);
        if (random <= 0) {
            if (row > 0) {
                player.getPackets().sendGameMessage("<col=ff7000>Your ring of wealth shines more brightly!</col>");
            }
            return true;
        }
        return false;
    }

    /**
     * Generate an item from the rare drop table.
     *
     * @param player Is the player wearing a ring of wealth.
     * @return the generated item or null if player is unlucky.
     */
    public Item generateReward(Player player) {
        if (shake(player, 100)) {
            final int rarity = MobRewardGenerator.getGenerator().generateRarityNode(player);
            if (rarity == -1) {
                return null;
            }
            final List<RDT> node = getInstance().getRewards().get(rarity);
            if (node.size() <= 0) {
                return null;
            }
            final int shake = Utils.random(node.size());
            final Item reward = node.get(shake).getReward().generateItem();
            player.getTemporaryAttributtes().put("RARITY_NODE", 2);
            player.getPackets().sendGameMessage("<col=ff0000>You recieved an extra drop: " + (reward.getAmount() > 1 ? reward.getAmount() + " x " : "") + reward.getName() + ".");
            return reward; // gets an item!
        } else {
            return null;
        }
    }

    /**
     * Construct a new reward generator.
     */
    public MobRewardRDT() {// try now

    }

}