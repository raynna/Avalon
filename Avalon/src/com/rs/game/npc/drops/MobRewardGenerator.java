package com.rs.game.npc.drops;

import java.util.HashMap;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.utils.Iterate;
import com.rs.utils.Utils;

/**
 * Contains the spoils for a {@link Player} when they defeat a {@link Mob}.
 *
 * @author Andreas
 * @version 1.0
 * @since 10/07/2018
 */
public final class MobRewardGenerator {

	/**
	 * The path of the drop table.
	 */
	private static final String DROP_TABLE_PATH = "bin/com/rs/game/npc/drops/droptable";

	/**
	 * The logger for this specific class.
	 */
	// private static final Logger logger =
	// Logger.getLogger(MobRewardGenerator.class.getSimpleName());

	/**
	 * The rewards map holding all mob drops.
	 */
	private final HashMap<Object, Class<? extends MobRewardNodeBuilder>> rewards = new HashMap<>();

	/**
	 * The current generator.
	 */
	private static final MobRewardGenerator mobRewardGenerator = new MobRewardGenerator();

	/**
	 * Get the current generator for the server.
	 * 
	 * @return the mob reward generator.
	 */
	public static MobRewardGenerator getGenerator() {
		return mobRewardGenerator;
	}

	/**
	 * Get the rewards map.
	 * 
	 * @return the rewards map.
	 */
	public HashMap<Object, Class<? extends MobRewardNodeBuilder>> getRewards() {
		return rewards;
	}

	/**
	 * Populate the rewards map.
	 */
	public void populateNodeBuilders() {
		Iterate.classes(DROP_TABLE_PATH, node -> {
			((MobRewardNodeBuilder) node).populateAll();
		});
	}

	/**
	 * Generate the reward for killing a mob.
	 * 
	 * @param mob    The mob that was killed.
	 * @param player The player(s) who dealt the most damage.
	 * @return an {@link Item} array containing the reward.
	 */
	public Item[] generateReward(final NPC mob, final Player player) {
		if (getGenerator().getRewards().size() == 0) {
			MobRewardGenerator.getGenerator().populateNodeBuilders();
		}
		Class<? extends MobRewardNodeBuilder> clazz = getGenerator().getRewards().get(mob.getId());
		if (clazz == null)
			clazz = getGenerator().getRewards().get(mob.getName());
		if (clazz == null)
			return null;
		try {
			MobRewardNodeBuilder node = clazz.getConstructor().newInstance();
			node.populate(player);
			return node.generateReward(player, mob);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private int VERY_RARE = 3;
	private int RARE = 2;
	private int UNCOMMON = 1;
	private int COMMON = 0;

	private boolean roll(Player player, int max) {
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
	 * Get a generated rarity node.
	 * 
	 * @param player
	 * 
	 * 
	 * @return a randomly generated rarity node.
	 */
	public int generateRarityNode(Player player) {
		if (roll(player, 128)) {
			return VERY_RARE;
		}
		if (roll(player, 32)) {
			return RARE;
		}
		if (roll(player, 8)) {
			return UNCOMMON;
		}
		return COMMON;
	}

	/**
	 * Default private constructor to prevent instantiation by other classes.
	 */
	private MobRewardGenerator() {

	}

}