package com.rs.game.player.actions.skills.fishing;

import java.util.HashMap;
import java.util.Map;

import com.rs.game.Animation;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.npc.CombatEventNPC;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.Action;
import com.rs.game.player.actions.skills.fishing.FishingOutfit.Pieces;
import com.rs.game.player.content.tasksystem.TaskManager.Tasks;
import com.rs.utils.Utils;

/**
 * 
 * @Improved Andreas - AvalonPK
 * 
 */

public class Fishing extends Action {

	public enum Fish {// inventory id, min level to catch, experience

		CRAYFISH(13435, 1, 10),

		ANCHOVIES(321, 15, 40),

		BASS(363, 46, 100),

		COD(341, 23, 45),

		CAVE_FISH(15264, 85, 300),

		HERRING(345, 10, 30),

		LOBSTER(377, 40, 90),

		MACKEREL(353, 16, 20),

		MANTA(389, 81, 46),

		MONKFISH(7944, 62, 120),

		PIKE(349, 25, 60),

		SALMON(331, 30, 70),

		SARDINES(327, 5, 20),

		SEA_TURTLE(395, 79, 38),

		SEAWEED(401, 30, 0),

		OYSTER(407, 30, 0),

		SHARK(383, 76, 110),

		SHRIMP(317, 1, 10),

		SWORDFISH(371, 50, 100),

		TROUT(335, 20, 50),

		TUNA(359, 35, 80),

		CAVEFISH(15264, 85, 300),

		ROCKTAIL(15270, 90, 385);

		private final int id, level;
		private final double xp;

		private Fish(int id, int level, double xp) {
			this.id = id;
			this.level = level;
			this.xp = xp;
		}

		public int getId() {
			return id;
		}

		public int getLevel() {
			return level;
		}

		public double getXp() {
			return xp;
		}
	}

	public enum FishingSpots {

		CAVEFISH_SHOAL(8841, 1, 307, 313, new Animation(622), Fish.CAVE_FISH),

		ROCKTAIL_SHOAL(8842, 1, 307, 15263, new Animation(622), Fish.ROCKTAIL),

		NET(327, 1, 303, -1, new Animation(621), Fish.SHRIMP, Fish.ANCHOVIES),

		BAIT(327, 2, 307, 313, new Animation(622), Fish.SARDINES, Fish.HERRING),

		LURE(328, 1, 309, 314, new Animation(622), Fish.TROUT, Fish.SALMON),

		LURE2(329, 1, 309, 314, new Animation(622), Fish.TROUT, Fish.SALMON),

		BAIT2(328, 2, 307, 313, new Animation(622), Fish.PIKE),

		BAIT3(329, 2, 307, 313, new Animation(622), Fish.PIKE, Fish.CAVE_FISH),

		CAGE(312, 1, 301, -1, new Animation(619), Fish.LOBSTER),

		HARPOON(312, 2, 311, -1, new Animation(618), Fish.TUNA, Fish.SWORDFISH),

		BIG_NET(313, 1, 305, -1, new Animation(620), Fish.MACKEREL, Fish.COD, Fish.BASS, Fish.SEAWEED, Fish.OYSTER),

		HARPOON2(313, 2, 311, -1, new Animation(6705), Fish.SHARK),

		NET2(952, 1, 303, -1, new Animation(621), Fish.SHRIMP),

		CAGE2(6267, 1, 13431, -1, new Animation(621), Fish.CRAYFISH);

		private final Fish[] fish;
		private final int id, option, tool, bait;
		private final Animation animation;

		static final Map<Integer, FishingSpots> spot = new HashMap<Integer, FishingSpots>();

		public static FishingSpots forId(int id) {
			return spot.get(id);
		}

		static {
			for (FishingSpots spots : FishingSpots.values())
				spot.put(spots.id | spots.option << 24, spots);
		}

		private FishingSpots(int id, int option, int tool, int bait, Animation animation, Fish... fish) {
			this.id = id;
			this.tool = tool;
			this.bait = bait;
			this.animation = animation;
			this.fish = fish;
			this.option = option;
		}

		public Fish[] getFish() {
			return fish;
		}

		public int getId() {
			return id;
		}

		public int getOption() {
			return option;
		}

		public int getTool() {
			return tool;
		}

		public int getBait() {
			return bait;
		}

		public Animation getAnimation() {
			return animation;
		}
	}

	private FishingSpots spot;

	private NPC npc;
	private WorldTile tile;
	private int fishId;

	private final int[] BONUS_FISH = { 341, 349, 401, 407 };

	private boolean multipleCatch;

	public Fishing(FishingSpots spot, NPC npc) {
		this.spot = spot;
		this.npc = npc;
		tile = new WorldTile(npc);
	}

	@Override
	public boolean start(Player player) {
		if (!checkAll(player))
			return false;
		fishId = getRandomFish(player);
		if (spot.getFish()[fishId] == Fish.TUNA || spot.getFish()[fishId] == Fish.SHARK
				|| spot.getFish()[fishId] == Fish.SWORDFISH) {
			if (Utils.getRandom(50) <= 5) {
				if (player.getSkills().getLevel(Skills.AGILITY) >= spot.getFish()[fishId].getLevel())
					multipleCatch = true;
			}
		}
		player.getPackets().sendGameMessage("You attempt to capture a fish...", true);
		setActionDelay(player, getFishingDelay(player));
		return true;
	}

	@Override
	public boolean process(Player player) {
		player.animate(spot.getAnimation());
		return checkAll(player);
	}

	private int getFishingDelay(Player player) {
		int playerLevel = player.getSkills().getLevel(Skills.FISHING);
		int fishLevel = spot.getFish()[fishId].getLevel();
		int modifier = spot.getFish()[fishId].getLevel();
		int randomAmt = Utils.random(4);
		double cycleCount = 1, otherBonus = 0;
		if (player.getFamiliar() != null)
			otherBonus = getSpecialFamiliarBonus(player.getFamiliar().getId());
		cycleCount = Math.ceil(((fishLevel + otherBonus) * 50 - playerLevel * 10) / modifier * 0.25 - randomAmt * 10);
		if (cycleCount < 1)
			cycleCount = 1;
		int delay = (int) cycleCount + 1;
		delay /= player.getAuraManager().getFishingAccurayMultiplier();
		return delay;

	}

	private int getSpecialFamiliarBonus(int id) {
		switch (id) {
		case 6796:
		case 6795:
			return 1;
		}
		return -1;
	}

	private int getRandomFish(Player player) {
		int random = Utils.random(spot.getFish().length);
		int difference = player.getSkills().getLevel(Skills.FISHING) - spot.getFish()[random].getLevel();
		if (difference < 0)
			return random = 0;
		if (random < -1)
			return random = 0;
		return random;
	}

	@Override
	public int processWithDelay(Player player) {
		addFish(player);
		return getFishingDelay(player);
	}

	private boolean doubleLoot(Player player) {
		Item fish = new Item(spot.getFish()[fishId].getId(), 1);
		int chance = 5;
		if (Utils.getRandom(100) <= chance) {
			player.getPackets().sendGameMessage("You manage to catch "
					+ (spot.getFish()[fishId] == Fish.ANCHOVIES || spot.getFish()[fishId] == Fish.SHRIMP ? "some"
							: "an")
					+ " extra " + fish.getDefinitions().getName() + ".");
			return true;
		}
		return false;
	}

	private double getFishingBoost(Player player) {
		double boost = 1.0;
		for (Pieces pieces : FishingOutfit.data) {
			if (player.getEquipment().containsOneItem(pieces.getItemId()))
				boost += 0.01;
		}
		if (hasFishingSuit(player))
				boost += 0.01;
		return boost;
	}

	private void addFish(Player player) {
		Item fish = new Item(spot.getFish()[fishId].getId(), doubleLoot(player) ? 2 : 1);
		player.getPackets().sendGameMessage(getMessage(fish), true);
		player.getInventory().deleteItem(spot.getBait(), 1);
		double totalXp = spot.getFish()[fishId].getXp();
		double multiplier = getFishingBoost(player);
		totalXp *= multiplier;
		FishingOutfit.addPiece(player);
		player.getInventory().addItem(fish);
		if (doubleLoot(player)) {
			if (player.getInventory().hasFreeSlots())
				player.getInventory().addItem(fish);
			else
				World.updateGroundItem(fish, player, player, 60, 0);
			totalXp *= 2;
		}
		player.getSkills().addSkillingXp(Skills.FISHING, totalXp, multiplier);
        int rngEvent = Utils.getRandom(6000);
        if (rngEvent == 0) {
        	CombatEventNPC.startRandomEvent(player, Skills.FISHING);
        }
		if (fish.getId() == 317)
			player.getTaskManager().checkComplete(Tasks.FISH_SHRIMP);
		if (fish.getId() == 383)
			player.getTaskManager().checkComplete(Tasks.FISH_SHARK);
		if (player.getFamiliar() != null) {
			if (Utils.getRandom(50) == 0 && getSpecialFamiliarBonus(player.getFamiliar().getId()) > 0) {
				player.getInventory().addItem(new Item(BONUS_FISH[Utils.random(BONUS_FISH.length)]));
				player.getSkills().addXp(Skills.FISHING, 5.5);
			}
		}
		fishId = getRandomFish(player);
		if (Utils.getRandom(50) == 0 && FishingSpotsHandler.moveSpot(npc))
			player.animate(new Animation(-1));
	}

	private boolean hasFishingSuit(Player player) {
		boolean hasSet = true;
		for (Pieces pieces : FishingOutfit.data) {
			if (pieces == Pieces.FISH_HAT)
				continue;
			if (!player.getEquipment().containsOneItem(pieces.getItemId()))
				hasSet = false;
		}
		return hasSet;
	}

	private String getMessage(Item fish) {
		if (spot.getFish()[fishId] == Fish.ANCHOVIES || spot.getFish()[fishId] == Fish.SHRIMP)
			return "You manage to catch some " + fish.getDefinitions().getName().toLowerCase() + ".";
		else if (multipleCatch)
			return "Your quick reactions allow you to catch two " + fish.getDefinitions().getName().toLowerCase() + ".";
		else
			return "You manage to catch a " + fish.getDefinitions().getName().toLowerCase() + ".";
	}

	private boolean checkAll(Player player) {
		if (player.getSkills().getLevel(Skills.FISHING) < spot.getFish()[fishId].getLevel()) {
			player.getPackets().sendGameMessage(
					"You need a fishing level of " + spot.getFish()[fishId].getLevel() + " to fish here.");
			return false;
		}
		if (!player.getInventory().containsItem(spot.getTool(), 1) && !player.getToolbelt().contains(spot.getTool())) {
			player.getPackets().sendGameMessage("You need a "
					+ new Item(spot.getTool()).getDefinitions().getName().toLowerCase() + " to fish here.");
			return false;
		}
		if (!player.getInventory().containsOneItem(spot.getBait()) && spot.getBait() != -1) {
			player.getPackets().sendGameMessage("You don't have "
					+ new Item(spot.getBait()).getDefinitions().getName().toLowerCase() + " to fish here.");
			return false;
		}
		if (!player.getInventory().hasFreeSlots()) {
			player.animate(new Animation(-1));
			player.getDialogueManager().startDialogue("SimpleMessage", "You don't have enough inventory space.");
			return false;
		}
		if (tile.getX() != npc.getX() || tile.getY() != npc.getY())
			return false;
		return true;
	}

	@Override
	public void stop(final Player player) {
		setActionDelay(player, 3);
	}
}
