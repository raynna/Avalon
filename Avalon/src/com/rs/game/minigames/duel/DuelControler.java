package com.rs.game.minigames.duel;

import com.rs.game.WorldTile;
import com.rs.game.player.Player;
import com.rs.game.player.RouteEvent;
import com.rs.game.player.controlers.Controler;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.utils.Utils;

/**
 * 
 * @Improved Andreas - AvalonPK
 * 
 */

public class DuelControler extends Controler {

	public static void challenge(Player player) {
		player.closeInterfaces();
		Boolean friendly = (Boolean) player.temporaryAttribute().remove("WillDuelFriendly");
		if (friendly == null)
			return;
		Player target = (Player) player.temporaryAttribute().remove("DuelTarget");
		if (target == null || target.hasFinished() || !target.withinDistance(player, 14)
				|| !(target.getControlerManager().getControler() instanceof DuelControler)) {
			player.getPackets()
					.sendGameMessage("Unable to find " + (target == null ? "your target" : target.getDisplayName()));
			return;
		}
		if (player.getPlayerRank().isIronman()) {
			player.message("You cannot duel as a " + (player.getPlayerRank().isHardcore() ? "Hardcore ironman." : "Ironman."));
			return;
		}
		if (target.getPlayerRank().isIronman()) {
			player.message("You cannot duel a " + (target.getPlayerRank().isHardcore() ? "Hardcore ironman." : "Ironman."));
			return;
		}
		if (player.getAuraManager().isActivated()) {
			player.message("You can't chellange somneone while having a aura activated.");
			return;
		}
		if (player.getFamiliar() != null) {
			player.message("You can't chellange someone while having a familiar spawed.");
			return;
		}
		if (target.getFamiliar() != null) {
			player.message("Your target has a familiar that needs to be dismissed.");
			return;
		}
		// if (player.isAdmin()) {
		// player.sendMessage("You're an admin and can't start a duel.");
		// return;
		// }
		if (player.getEmotesManager().getNextEmoteEnd() > Utils.currentTimeMillis()) {
			player.message("You have to finish your emote before dueling.");
			return;
		}
		player.temporaryAttribute().put("DuelChallenged", target);
		player.temporaryAttribute().put("DuelFriendly", friendly);
		player.getPackets().sendGameMessage("Sending " + target.getDisplayName() + " a request...");
		target.getPackets().sendDuelChallengeRequestMessage(player, friendly);
	}

	@Override
	public boolean canPlayerOption1(final Player target) {
		player.stopAll();
		if (target.getInterfaceManager().containsScreenInter() || target.isLocked()) {
			player.setRouteEvent(new RouteEvent(target, new Runnable() {
				@Override
				public void run() {
					player.getPackets().sendGameMessage("The other player is busy.");
				}
			}));
			return false;
		}
		if (target.temporaryAttribute().get("DuelChallenged") == player) {
			player.setRouteEvent(new RouteEvent(target, new Runnable() {
				@Override
				public void run() {
					sendDuelConfigs(target);
				}
			}));
			return false;
		}
		player.setRouteEvent(new RouteEvent(target, new Runnable() {
			@Override
			public void run() {
				sendDuelConfigs(target);
			}
		}));
		return false;
	}

	@Override
	public void forceClose() {
		remove();
	}

	@Override
	public boolean login() {
		start();
		return false;
	}

	@Override
	public boolean logout() {
		return false;
	}

	@Override
	public void magicTeleported(int type) {
		player.getPackets().closeInterface(player.getInterfaceManager().hasRezizableScreen() ? 11 : 0);
		removeControler();
		remove();
	}

	@Override
	public void moved() {
		if (player.getRegionId() != 13363) {
			removeControler();
			remove();
		}
	}

	@Override
	public boolean processItemTeleport(WorldTile toTile) {
		player.getPackets().closeInterface(player.getInterfaceManager().hasRezizableScreen() ? 11 : 0);
		return true;
	}

	@Override
	public boolean processMagicTeleport(WorldTile toTile) {
		player.getPackets().closeInterface(player.getInterfaceManager().hasRezizableScreen() ? 11 : 0);
		return true;
	}

	@Override
	public boolean processJewerlyTeleport(WorldTile toTile) {
		player.getPackets().closeInterface(player.getInterfaceManager().hasRezizableScreen() ? 11 : 0);
		return true;
	}

	public void remove() {
		player.getPackets().closeInterface(player.getInterfaceManager().hasRezizableScreen() ? 11 : 0);
		player.getAppearence().generateAppearenceData();
		player.getPackets().sendPlayerOption("null", 1, false);
	}

	public void sendDuelConfigs(final Player target) {
		if (target == null)
			return;
		if (target.getInterfaceManager().containsScreenInter() || target.isLocked()) {
			player.getPackets().sendGameMessage("The other player is busy.");
			return;
		}
		if (player.getX() == target.getX() && player.getY() == target.getY()) {
			if (!player.addWalkSteps(player.getX() - 1, player.getY(), 1))
				if (!player.addWalkSteps(player.getX() + 1, player.getY(), 1))
					if (!player.addWalkSteps(player.getX(), player.getY() + 1, 1))
						player.addWalkSteps(player.getX(), player.getY() - 1, 1);
		}
		if (!target.withinDistance(player, 14)) {
			player.getPackets().sendGameMessage("Unable to find target " + target.getDisplayName());
			return;
		}
		if (target.temporaryAttribute().get("DuelChallenged") == player) {
			player.getControlerManager().removeControlerWithoutCheck();
			target.getControlerManager().removeControlerWithoutCheck();
			target.temporaryAttribute().remove("DuelChallenged");
			player.setLastDuelRules(new DuelRules(player, target));
			target.setLastDuelRules(new DuelRules(target, player));
			player.getControlerManager().startControler("DuelArena", target,
					target.temporaryAttribute().get("DuelFriendly"));
			target.getControlerManager().startControler("DuelArena", player,
					target.temporaryAttribute().remove("DuelFriendly"));
			return;
		}
		WorldTasksManager.schedule(new WorldTask() {
			@Override
			public void run() {
				player.temporaryAttribute().put("DuelTarget", target);
				player.getInterfaceManager().sendInterface(640);
				player.temporaryAttribute().put("WillDuelFriendly", true);
				player.getPackets().sendConfig(283, 67108864);
			}
		});
		player.faceEntity(target);
	}

	@Override
	public void sendInterfaces() {
		if (player.getRegionId() == 13363) {
			player.getInterfaceManager().sendTab(player.getInterfaceManager().hasRezizableScreen() ? 11 : 0, 638);
		}
	}
	
	@Override
	public void start() {
		sendInterfaces();
		player.getPackets().sendPlayerOption("Challenge", 1, false);
		player.getAppearence().generateAppearenceData();
		moved();
	}
}
