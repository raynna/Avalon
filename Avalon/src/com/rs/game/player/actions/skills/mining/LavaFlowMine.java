package com.rs.game.player.actions.skills.mining;

import com.rs.game.Animation;
import com.rs.game.WorldObject;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.utils.HexColours;
import com.rs.utils.HexColours.Colours;
import com.rs.utils.Utils;

public class LavaFlowMine extends MiningBase {

	/**
	 * @author: miles M
	 */

	private WorldObject object;

	public LavaFlowMine(WorldObject object) {
		this.object = object;
	}

	@Override
	public boolean start(Player player) {
		if (!checkAll(player))
			return false;
		setActionDelay(player, getMiningDelay(player));
		return true;
	}

	private int getMiningDelay(Player player) {
		int summoningBonus = getSummoningBonus(player, 0);
		int oreBaseTime = 50;
		int oreRandomTime = 20;
		int mineTimer = oreBaseTime
				- (player.getSkills().getLevel(Skills.MINING) + summoningBonus)
				- Utils.random(pickaxeTime);
		if (mineTimer < 1 + oreRandomTime)
			mineTimer = 1 + Utils.random(oreRandomTime);
		mineTimer /= player.getAuraManager().getMininingAccurayMultiplier();
		return mineTimer;
	}

	@Override
	public boolean process(Player player) {
		player.animate(new Animation(getEmoteId(player.getEquipment()
				.getWeaponId())));
		player.faceObject(object);
		if (checkAll(player)) {
			if (Utils.random(250) == 0) {
				player.sm("The intense heat forces to you take a break.");
				player.stopAll();
				player.animate(new Animation(-1));
			}
			if (Utils.random(18) == 0) {
				addXP(player);
			}
			if (!hasPetRock(player) && Utils.random(10000) == 0) {
				player.getInventory().addItem(new Item(3695, 1));
				player.sm("<img=5><col=b25200>You have recieved a " + (HexColours.getShortMessage(Colours.RED, "Pet Rock")) + "!");
			}
			return true;
		}
		return false;
	}

	private boolean checkAll(Player player) {
		if (!hasPickaxe(player)) {
			player.getPackets().sendGameMessage(
					"You dont have the required level to use this pickaxe.");
			return false;
		}
		if (!player.getInventory().hasFreeSlots()) {
			player.sm("You need to clear more inventory space before continuing.");
			return false;
		}
		return hasMiningLevel(player);
	}

	private boolean hasMiningLevel(Player player) {
		if (68 > player.getSkills().getLevel(Skills.MINING)) {
			player.getPackets().sendGameMessage(
					"You need a mining level of 68 to mine this rock.");
			return false;
		}
		return true;
	}

	@Override
	public int processWithDelay(Player player) {
		addXP(player);
		return getMiningDelay(player);
	}

	private void addXP(Player player) {
		double totalXp = Utils.random(47, 59);
		if (hasMiningSuit(player))
			totalXp *= 1.056;
		player.getSkills().addXp(Skills.MINING, totalXp);
		int rng = Utils.getRandom(3);
		player.getInventory().addItem(new Item(rng == 0 ? 453 : 444));
		player.getPackets().sendGameMessage("You mine away some crust.", true);
		player.lavaflowCrustsMined++;
	}

	private int getEmoteId(int pickaxe) {
		switch (pickaxe) {
		case 15259: // dragon pickaxe
			return 12190;
		case 1275: // rune pickaxe
			return 624;
		case 1271: // adam pickaxe
			return 628;
		case 1273: // mith pickaxe
			return 629;
		case 1269: // steel pickaxe
			return 627;
		case 1267: // iron pickaxe
			return 626;
		case 1265: // bronze axe
			return 625;
		case 32646: // crystal axe
			return 25159;
		case 13661: // Inferno adze
			return 10222;
		}
		return 625;
	}

	@Override
	public void stop(Player player) {
		setActionDelay(player, 3);
	}
}