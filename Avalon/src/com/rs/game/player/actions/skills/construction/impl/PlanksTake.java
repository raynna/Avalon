package com.rs.game.player.actions.skills.construction.impl;

import com.rs.game.Animation;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.actions.Action;
import com.rs.game.player.actions.skills.woodcutting.LumberjackOutfit;
import com.rs.game.player.controlers.construction.SawmillController;

public class PlanksTake extends Action {

	private SawmillController sawmill;
	private int amount;

	public PlanksTake(int amount, SawmillController sawmill) {
		this.amount = amount;
		this.sawmill = sawmill;
	}

	@Override
	public boolean start(Player player) {
		return process(player);
	}

	@Override
	public boolean process(Player player) {
		if (!player.getInventory().hasFreeSlots()) {
			player.getPackets().sendGameMessage("Your inventory is full.");
			return false;
		}
		if (!sawmill.hasPlanks()) {
			player.getPackets().sendGameMessage("You have no planks left.");
			return false;
		}
		return amount > 0;
	}

	@Override
	public int processWithDelay(Player player) {
		player.animate(new Animation(8908));
		LumberjackOutfit.addPiece(player);
		player.getInventory().addItem(new Item(960));
		sawmill.removePlank();
		return amount-- == 1 ? -1 : 1;
	}

	@Override
	public void stop(Player player) {
		setActionDelay(player, LumberjackOutfit.hasAllPieces(player) ? 1 : 3);
	}
}