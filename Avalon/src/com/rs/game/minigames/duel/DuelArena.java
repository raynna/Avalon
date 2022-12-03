package com.rs.game.minigames.duel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.Animation;
import com.rs.game.Entity;
import com.rs.game.ForceTalk;
import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.AccountCreation;
import com.rs.game.player.Equipment;
import com.rs.game.player.Player;
import com.rs.game.player.actions.combat.PlayerCombat;
import com.rs.game.player.content.Foods.Food;
import com.rs.game.player.content.ItemConstants;
import com.rs.game.player.content.Pots.Pot;
import com.rs.game.player.controlers.Controler;
import com.rs.game.tasks.WorldTask;
import com.rs.game.tasks.WorldTasksManager;
import com.rs.net.decoders.WorldPacketsDecoder;
import com.rs.net.decoders.handlers.ButtonHandler;
import com.rs.utils.EconomyPrices;
import com.rs.utils.Utils;

public class DuelArena extends Controler {

	enum DuelStage {
		DECLINED, NO_SPACE, SECOND, DONE
	}

	public transient Player target;

	public boolean ifFriendly, isDueling;

	private final WorldTile[] LOBBY_TELEPORTS = { new WorldTile(3367, 3275, 0), new WorldTile(3360, 3275, 0),
			new WorldTile(3358, 3270, 0), new WorldTile(3363, 3268, 0), new WorldTile(3370, 3268, 0),
			new WorldTile(3367, 3267, 0), new WorldTile(3376, 3275, 0), new WorldTile(3377, 3271, 0),
			new WorldTile(3375, 3269, 0), new WorldTile(3381, 3277, 0) };

	private void accept(boolean firstStage) {
		if (target == null || player == null)
			return;
		long seconds = TimeUnit.MILLISECONDS.toSeconds(player.getRuleCount() - Utils.currentTimeMillis());
		long otherseconds = TimeUnit.MILLISECONDS.toSeconds(target.getRuleCount() - Utils.currentTimeMillis());
		if (seconds > 0 || otherseconds > 0) {
			target.getPackets().sendIComponentText(ifFriendly ? 637 : 631, ifFriendly ? 20 : 41,
					"You can accept in " + seconds + " seconds.");
			player.getPackets().sendIComponentText(ifFriendly ? 637 : 631, ifFriendly ? 20 : 41,
					"You can accept in " + otherseconds + " seconds.");
			return;
		}
		boolean accepted = (Boolean) player.temporaryAttribute().get("acceptedDuel");
		boolean targetAccepted = (Boolean) target.temporaryAttribute().get("acceptedDuel");
		DuelRules rules = player.getLastDuelRules();
		if (!rules.canAccept(player.getLastDuelRules().getStake(), target.getLastDuelRules().getStake()))
			return;
		if (accepted && targetAccepted) {
			if (firstStage) {
				if (nextStage())
					if (target != null) {
						((DuelArena) target.getControlerManager().getControler()).nextStage();
					}
			} else {
				player.setCloseInterfacesEvent(null);
				player.closeInterfaces();
				closeDuelInteraction(true, DuelStage.DONE);
			}
			return;
		}
		player.temporaryAttribute().put("acceptedDuel", true);
		refreshScreenMessages(firstStage, ifFriendly);
	}

	public void addItem(int slot, int amount) {
		if (!hasTarget()) {
			return;
		}
		Item item = player.getInventory().getItem(slot);
		if (item == null) {
			return;
		}
		if (!ItemConstants.isTradeable(item)) {
			player.getPackets().sendGameMessage("That item cannot be staked!");
			return;
		}
		Item[] itemsBefore = player.getLastDuelRules().getStake().getItemsCopy();
		int maxAmount = player.getInventory().getItems().getNumberOf(item);
		int itemAmount = amount;
		if (itemAmount > maxAmount)
			itemAmount = maxAmount;
		for (Item duelItems : player.getLastDuelRules().getStake().getContainerItems()) {
			if (duelItems == null)
				continue;
			if (duelItems.getAmount() == Integer.MAX_VALUE) {
				player.getPackets().sendGameMessage("You can't stake more of that item.");
				return;
			}
			if (duelItems.getAmount() + itemAmount < 0)
				itemAmount = Integer.MAX_VALUE - itemAmount;
		}
		player.getLastDuelRules().getStake().add(new Item(item.getId(), itemAmount));
		player.getInventory().deleteItem(slot, new Item(item.getId(), itemAmount));
		refreshItems(itemsBefore);
		refresh(slot);
		cancelAccepted();
		refreshStakedWealth();
		player.setRuleCount(6);
		target.setRuleCount(6);
	}

	private void refreshStakedWealth() {
		player.getPackets().sendIComponentText(631, 44,
				"Your stake: " + Utils.formatDoubledAmountShort(getStakedValue(player)) + " GP");
		player.getPackets().sendIComponentText(631, 45,
				"Opponent's stake: " + Utils.formatDoubledAmountShort(getStakedValue(target)) + " GP");
		target.getPackets().sendIComponentText(631, 44,
				"Your stake: " + Utils.formatDoubledAmountShort(getStakedValue(target)) + " GP");
		target.getPackets().sendIComponentText(631, 45,
				"Opponent's stake: " + Utils.formatDoubledAmountShort(getStakedValue(player)) + " GP");
	}

	public void addPouch(int slot, int amount) {
		if (!hasTarget()) {
			return;
		}
		Item[] itemsBefore = player.getLastDuelRules().getStake().getItemsCopy();
		if (player.getMoneyPouch().getTotal() == 0) {
			player.getPackets().sendGameMessage("You don't have enough money to do that.");
			return;
		}
		if (amount > player.getMoneyPouch().getTotal())
			amount = player.getMoneyPouch().getTotal();
		player.getLastDuelRules().getStake().add(new Item(995, amount));
		player.getMoneyPouch().removeMoneyMisc(amount);
		refreshItems(itemsBefore);
		refresh(slot);
		refreshStakedWealth();
		cancelAccepted();
		player.setRuleCount(6);
		target.setRuleCount(6);
	}

	private void beginBattle(boolean started) {
		if (started) {
			int random = 0;
			if (player.getLastDuelRules().getRule(24)) {
				WorldTile[] teleports = getPossibleWorldTilesSummoning();
				random = Utils.getRandom(1);
				player.setNextWorldTile(random == 0 ? teleports[0] : teleports[1]);
				target.setNextWorldTile(random == 0 ? teleports[1] : teleports[0]);
			} else {
				WorldTile[] teleports = getPossibleWorldTiles();
				random = Utils.getRandom(1);
				player.setNextWorldTile(random == 0 ? teleports[0] : teleports[1]);
				target.setNextWorldTile(random == 0 ? teleports[1] : teleports[0]);
			}
		}
		player.setLastBonfire(0);
		player.setOverload(-2);
		player.setPrayerRenewal(-2);
		player.getSkills().restoreSkills();
		player.getPrayer().closeAllPrayers();
		player.stopAll();
		player.lock(2);
		player.reset(true);
		isDueling = true;
		player.temporaryAttribute().put("startedDuel", true);
		player.temporaryAttribute().put("canFight", false);
		player.setCanPvp(true);
		player.getHintIconsManager().addHintIcon(target, 1, -1, false);
		WorldTasksManager.schedule(new WorldTask() {
			int count = 3;

			@Override
			public void run() {
				if (count > 0)
					player.setNextForceTalk(new ForceTalk("" + count));
				if (count == 0) {
					player.temporaryAttribute().put("canFight", true);
					player.setNextForceTalk(new ForceTalk("FIGHT!"));
					this.stop();
				}
				count--;
			}
		}, 0, 2);
		if (player.getCustomDuelRule().get(1).equals(true) && !DDS(player.getEquipment().getWeaponId())) {
			ButtonHandler.sendRemove(player, Equipment.SLOT_WEAPON);
			player.sm("<col=990000>Remember to equip your dds!");
		}
	}

	@Override
	public boolean canAttack(Entity target) {
		if (player.temporaryAttribute().get("canFight") == Boolean.FALSE) {
			player.getPackets().sendGameMessage("The duel hasn't started yet.", true);
			return false;
		}
		if (player.isDead() || target.isDead())
			return false;
		return true;
	}

	public void cancelAccepted() {
		boolean canceled = false;
		if ((Boolean) player.temporaryAttribute().get("acceptedDuel")) {
			player.temporaryAttribute().put("acceptedDuel", false);
			canceled = true;
		}
		if ((Boolean) target.temporaryAttribute().get("acceptedDuel")) {
			target.temporaryAttribute().put("acceptedDuel", false);
			canceled = true;
		}
		if (canceled)
			refreshScreenMessages(canceled, ifFriendly);
	}

	@Override
	public boolean canEat(Food food) {
		if (player.getLastDuelRules().getRule(4) && isDueling) {
			player.getPackets().sendGameMessage("You cannot eat during this duel.", true);
			return false;
		}
		return true;
	}

	@Override
	public boolean canEquip(int slotId, int itemId) {
		DuelRules rules = player.getLastDuelRules();
		if (isDueling) {
			if (rules.getRule(15) && twoHanded(itemId) == true) {
				player.getPackets().sendGameMessage("You can't equip "
						+ ItemDefinitions.getItemDefinitions(itemId).getName().toLowerCase() + " during this duel.");
				return false;
			}
			if (rules.getRule(10 + slotId)) {
				player.getPackets().sendGameMessage("You can't equip "
						+ ItemDefinitions.getItemDefinitions(itemId).getName().toLowerCase() + " during this duel.");
				return false;
			}
			if (player.getCustomDuelRule().get(1).equals(true) && !DDS(itemId)) {
				player.sm("You can't equip <col=ff0000>"
						+ Utils.fixChatMessage(ItemDefinitions.getItemDefinitions(itemId).getName())
						+ "</col> in this Duel mode.");
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean canHit(Entity target) {
		Player p2 = (Player) target;
		if (player.isDead() || p2.isDead())
			return false;
		return true;
	}

	@Override
	public boolean canMove(int dir) {
		if (player.getLastDuelRules().getRule(25) && isDueling) {
			player.getPackets().sendGameMessage("You cannot move during this duel!", true);
			return false;
		}
		return true;
	}

	@Override
	public boolean canPot(Pot pot) {
		if (player.getLastDuelRules().getRule(3) && isDueling) {
			player.getPackets().sendGameMessage("You cannot drink during this duel.", true);
			return false;
		}
		return true;
	}

	@Override
	public boolean canSummonFamiliar() {
		if (player.getLastDuelRules().getRule(24) && isDueling)
			return true;
		player.getPackets().sendGameMessage("Summoning has been disabled during this duel!");
		return false;
	}

	protected void closeDuelInteraction(boolean started, DuelStage duelStage) {
		Player oldTarget = target;
		if (duelStage != DuelStage.DONE) {
			target = null;
			WorldTasksManager.schedule(new WorldTask() {
				@Override
				public void run() {
					player.getControlerManager().startControler("DuelControler");
				}
			});
			for (Item item : player.getLastDuelRules().getStake().getContainerItems()) {
				if (item == null)
					continue;
				if (item.getId() == 995) {
					player.getMoneyPouch().addMoney(item.getAmount(), false);
				} else {
					if (player.getInventory().hasFreeSlots())
						player.getInventory().addItem(item);
					else
						World.addGroundItem(item, new WorldTile(player), player, true, 60);
				}
			}
			player.getInventory().init();
			player.getLastDuelRules().getStake().clear();
		} else {
			removeEquipment();
			beginBattle(started);
		}
		if (oldTarget == null)
			return;
		Controler controler = oldTarget.getControlerManager().getControler();
		if (controler == null || !(controler instanceof DuelArena))
			return;
		DuelArena targetConfiguration = (DuelArena) controler;
		if (controler instanceof DuelArena) {
			if (targetConfiguration.hasTarget()) {
				oldTarget.setCloseInterfacesEvent(null);
				oldTarget.closeInterfaces();
				if (duelStage != DuelStage.DONE)
					player.getControlerManager().removeControlerWithoutCheck();
				if (started)
					targetConfiguration.closeDuelInteraction(false, duelStage);
				if (duelStage == DuelStage.DONE)
					player.getPackets().sendGameMessage("Your battle will begin shortly.");
				else if (duelStage == DuelStage.SECOND)
					player.getPackets().sendGameMessage("Please check if these settings are correct.");
				else if (duelStage == DuelStage.DECLINED)
					oldTarget.getPackets().sendGameMessage("Other player declined the duel!");
				else if (duelStage == DuelStage.DECLINED) {
					oldTarget.getPackets().sendGameMessage("You do not have enough space to continue!");
					oldTarget.getPackets().sendGameMessage("Other player does not have enough space to continue!");
				}
			}
		}
	}

	public void endDuel(Player victor, Player loser) {
		endDuel(victor, loser, true);
	}
	
	public static String currentTime(String dateFormat) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}
	
	public static void archiveDuel(Player player, Player p2, ItemsContainer<Item> items, ItemsContainer<Item> items2) {
		try {
			String location = "";
			location = "data/logs/duel/" + player.getUsername() + ".txt";
			BufferedWriter writer = new BufferedWriter(new FileWriter(location, true));
			writer.write("[" + currentTime("dd MMMMM yyyy 'at' hh:mm:ss z") + "] - " + player.getUsername() + " staked "
					+ p2.getUsername() + " items:");
			writer.newLine();
			for (Item item : items.getContainerItems()) {
				if (item == null)
					continue;
				writer.write(item.getDefinitions().getName() + " x " + item.getAmount());
				writer.newLine();
			}
			writer.write("for " + p2.getUsername() + "'s:");
			writer.newLine();
			for (Item item : items2.getContainerItems()) {
				if (item == null)
					continue;
				writer.write(item.getDefinitions().getName() + " x " + item.getAmount());
				writer.newLine();
			}
			writer.newLine();
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> wonItems = new ArrayList<String>();

	public void endDuel(final Player victor, final Player loser, boolean removeLoserControler) {
		if (player.isCanPvp() && target.isCanPvp()) {
			startEndingTeleport(victor);
			startEndingTeleport(loser);
			sendFinishInterface(victor, loser);
			CopyOnWriteArrayList<Item> lostItems = new CopyOnWriteArrayList<Item>();
			archiveDuel(player, loser, victor.getLastDuelRules().getStake(), loser.getLastDuelRules().getStake());
			if (player.getLastDuelRules().getStake() != null) {
				for (Item item : player.getLastDuelRules().getStake().getContainerItems()) {
					if (item == null)
						continue;
					lostItems.add(item);
				}
				for (Item item : player.getLastDuelRules().getStake().getContainerItems()) {
					if (item == null)
						continue;
					if (item.getId() != 995) {
						if (target.getInventory().hasFreeSlots()) {
							target.getInventory().addItem(item);
						} else {
							World.addGroundItem(item, new WorldTile(target), target, true, 60);
							target.getPackets().sendGameMessage("Items were dropped on the ground.");
						}
					} else {
						target.getMoneyPouch().addMoney(item.getAmount(), false);
					}
					Item wonItem = new Item(item.getId(), item.getAmount());
					if (EconomyPrices.getPrice(item.getId()) * item.getAmount() >= 10000000)
						wonItems.add(wonItem.getDefinitions().getName() + "x" + wonItem.getAmount());
				}
				if (!wonItems.isEmpty()) {
					World.sendWorldMessage("<img=7><col=36648b>News: " + victor.getDisplayName()
							+ " just won a stake worth a total of " + Utils.formatDoubledAmount(getStakedValue(loser))
							+ "!", false);
					wonItems.clear();
				}
				CopyOnWriteArrayList<Item> wonItems = new CopyOnWriteArrayList<Item>();
				for (Item item : player.getLastDuelRules().getStake().getContainerItems()) {
					if (item == null)
						continue;
					wonItems.add(item);
				}
				AccountCreation.savePlayer(victor);
				player.getLastDuelRules().resetStake();
			}
			if (target.getLastDuelRules().getStake() != null) {
				for (Item item : target.getLastDuelRules().getStake().getContainerItems()) {
					if (item == null)
						continue;
					if (item.getId() != 995) {
						if (target.getInventory().hasFreeSlots()) {
							target.getInventory().addItem(item);
						} else {
							World.addGroundItem(item, new WorldTile(target), target, true, 60);
							target.getPackets().sendGameMessage("Items were dropped on the ground.");
						}
					} else {
						target.getMoneyPouch().addMoney(item.getAmount(), false);
					}
				}
				target.getLastDuelRules().resetStake();
			}

			if (loser.getControlerManager().getControler() != null && removeLoserControler) {
				AccountCreation.savePlayer(loser);
				loser.getControlerManager().removeControlerWithoutCheck();
			}
			loser.setCanPvp(false);
			loser.getHintIconsManager().removeUnsavedHintIcon();
			loser.reset();
			if (victor.getControlerManager().getControler() != null)
				victor.getControlerManager().removeControlerWithoutCheck();
			victor.setCanPvp(false);
			victor.getHintIconsManager().removeUnsavedHintIcon();
			victor.reset(true);
			loser.getPackets().sendGameMessage("You lost the duel to " + victor.getDisplayName() + ".");
			victor.getPackets().sendGameMessage("You easily defeated " + loser.getDisplayName() + ".");
			victor.getCustomDuelRule().put(1, false);
			loser.getCustomDuelRule().put(1, false);
		}
	}

	public long getStakedValue(Player loser) {
		long value = 0;
		for (Item staked : loser.getLastDuelRules().getStake().getItemsCopy()) {
			if (staked == null)
				continue;
			long amount = staked.getAmount();
			value += staked.getDefinitions().getTipitPrice() * amount;
		}
		return value;
	}

	public void endDuelNoItems(final Player victor, final Player loser, boolean removeLoserControler) {

		if (loser.getControlerManager().getControler() != null && removeLoserControler) {
			AccountCreation.savePlayer(loser);
			loser.getControlerManager().removeControlerWithoutCheck();
		}
		loser.setCanPvp(false);
		loser.getHintIconsManager().removeUnsavedHintIcon();
		loser.reset();
		if (victor.getControlerManager().getControler() != null)
			victor.getControlerManager().removeControlerWithoutCheck();
		victor.setCanPvp(false);
		victor.getHintIconsManager().removeUnsavedHintIcon();
		victor.reset(true);
	}

	private String getAcceptMessage(boolean firstStage) {
		if (target.temporaryAttribute().get("acceptedDuel") == Boolean.TRUE)
			return "<col=ffc800>Other player has accepted.";
		else if (player.temporaryAttribute().get("acceptedDuel") == Boolean.TRUE)
			return "<col=ffc800>Waiting for other player...";
		return firstStage ? "" : "<col=ffc800>Please look over the agreements to the duel.";
	}

	private WorldTile[] getPossibleWorldTiles() {
		final int arenaChoice = Utils.getRandom(2);
		WorldTile[] locations = new WorldTile[2];
		int[] arenaBoundariesX = { 3337, 3367, 3336 };
		int[] arenaBoundariesY = { 3246, 3227, 3208 };
		int[] maxOffsetX = { 14, 14, 16 };
		int[] maxOffsetY = { 10, 10, 10 };
		int finalX = arenaBoundariesX[arenaChoice] + Utils.getRandom(maxOffsetX[arenaChoice]);
		int finalY = arenaBoundariesY[arenaChoice] + Utils.getRandom(maxOffsetY[arenaChoice]);
		locations[0] = (new WorldTile(finalX, finalY, 0));
		if (player.getLastDuelRules().getRule(25)) {
			int direction = Utils.getRandom(1);
			if (direction == 0) {
				finalX--;
			} else {
				finalY++;
			}
		} else {
			finalX = arenaBoundariesX[arenaChoice] + Utils.getRandom(maxOffsetX[arenaChoice]);
			finalY = arenaBoundariesY[arenaChoice] + Utils.getRandom(maxOffsetY[arenaChoice]);
		}
		locations[1] = (new WorldTile(finalX, finalY, 0));
		return locations;
	}

	private WorldTile[] getPossibleWorldTilesSummoning() {
		final int arenaChoice = Utils.getRandom(2);
		WorldTile[] locations = new WorldTile[2];
		int[] arenaBoundariesX = { 3209, 3208, 3226 };
		int[] arenaBoundariesY = { 5168, 5176, 5176 };
		int[] maxOffsetX = { 1, 1, 1 };
		int[] maxOffsetY = { 1, 1, 1 };
		int finalX = arenaBoundariesX[arenaChoice] + Utils.getRandom(maxOffsetX[arenaChoice]);
		int finalY = arenaBoundariesY[arenaChoice] + Utils.getRandom(maxOffsetY[arenaChoice]);
		locations[0] = (new WorldTile(finalX, finalY, 0));
		if (player.getLastDuelRules().getRule(25)) {
			int direction = Utils.getRandom(1);
			if (direction == 0) {
				finalX--;
			} else {
				finalY++;
			}
		} else {
			finalX = arenaBoundariesX[arenaChoice] + Utils.getRandom(maxOffsetX[arenaChoice]);
			finalY = arenaBoundariesY[arenaChoice] + Utils.getRandom(maxOffsetY[arenaChoice]);
		}
		locations[1] = (new WorldTile(finalX, finalY, 0));
		return locations;
	}

	public Entity getTarget() {
		if (hasTarget())
			return target;
		return null;
	}

	public boolean hasTarget() {
		return target != null;
	}

	public boolean isDueling() {
		return isDueling;
	}

	public boolean isWearingTwoHandedWeapon() {
		int weaponId = player.getEquipment().getWeaponId();
		if (weaponId == 4153 || weaponId == 11235 || weaponId == 861 || weaponId == 18353 || weaponId == 20171
				|| weaponId == 14484)
			return true;
		return false;
	}

	@Override
	public boolean keepCombating(Entity victim) {
		DuelRules rules = player.getLastDuelRules();
		boolean isRanging = PlayerCombat.isRanging(player) != 0;
		if (player.temporaryAttribute().get("canFight") == Boolean.FALSE) {
			player.getPackets().sendGameMessage("The duel hasn't started yet.", true);
			return false;
		}
		if (target != victim)
			return false;
		if (player.getCombatDefinitions().getSpellId() > 0 && rules.getRule(2) && isDueling) {
			player.getPackets().sendGameMessage("You cannot use Magic in this duel!", true);
			return false;
		} else if (isRanging && rules.getRule(0) && isDueling) {
			player.getPackets().sendGameMessage("You cannot use Range in this duel!", true);
			return false;
		} else if (!isRanging && rules.getRule(1) && player.getCombatDefinitions().getSpellId() <= 0 && isDueling) {
			player.getPackets().sendGameMessage("You cannot use Melee in this duel!", true);
			return false;
		}
		if (player.isDead() || victim.isDead())
			return false;
		return true;
	}

	@Override
	public boolean login() {
		startEndingTeleport(player);
		removeControler();
		return true;
	}

	@Override
	public boolean logout() {
		if (isDueling) {
			sendFinishInterface(target, player);
			for (Item item : player.getLastDuelRules().getStake().getContainerItems()) {
				if (item == null)
					continue;
				if (item.getId() == 995) {
					target.getMoneyPouch().addMoney(item.getAmount(), false);
				} else {
					if (target.getInventory().hasFreeSlots())
						target.getInventory().addItem(item);
					else
						World.addGroundItem(item, new WorldTile(target), target, true, 60);
				}
			}
			player.getInventory().init();
			player.getLastDuelRules().getStake().clear();
			for (Item item : target.getLastDuelRules().getStake().getContainerItems()) {
				if (item == null)
					continue;
				if (item.getId() == 995) {
					target.getMoneyPouch().addMoney(item.getAmount(), false);
				} else {
					if (target.getInventory().hasFreeSlots())
						target.getInventory().addItem(item);
					else
						World.addGroundItem(item, new WorldTile(target), target, true, 60);
				}
			}
			target.getInventory().init();
			target.getLastDuelRules().getStake().clear();
			target.getDialogueManager().startDialogue("SimpleMessage",
					Utils.formatPlayerNameForDisplay(player.getUsername()) + " disconnected, you win!");
			target.getHintIconsManager().removeAll();
			startEndingTeleport(target);
			startEndingTeleport(player);
			endDuelNoItems(target, player, false);
		} else
			closeDuelInteraction(true, DuelStage.DECLINED);
		return isDueling ? false : true;
	}

	@Override
	public void magicTeleported(int type) {
		if (type != -1)
			return;
	}

	public boolean nextStage() {
		if (!hasTarget())
			return false;
		if (player.getInventory().getItems().getUsedSlots()
				+ target.getLastDuelRules().getStake().getUsedSlots() > 28) {
			player.setCloseInterfacesEvent(null);
			player.closeInterfaces();
			closeDuelInteraction(true, DuelStage.NO_SPACE);
			player.sm("You do not have enough space in your inventory for the stake!");
			return false;
		}
		player.temporaryAttribute().put("acceptedDuel", false);
		openConfirmationScreen(false);
		player.getInterfaceManager().closeInventoryInterface();
		return true;
	}

	private void openConfirmationScreen(boolean ifFriendly) {
		player.getInterfaceManager().sendInterface(ifFriendly ? 639 : 626);
		refreshScreenMessage(false, ifFriendly);
	}

	private void openDuelScreen(Player target, boolean ifFriendly) {
		if (!ifFriendly) {
			sendOptions(player);
			player.getLastDuelRules().getStake().clear();
		}
		player.temporaryAttribute().put("acceptedDuel", false);
		player.getInterfaceManager().sendInterface(ifFriendly ? 637 : 631);
		player.getPackets().sendItems(134, false, player.getLastDuelRules().getStake());
		player.getPackets().sendItems(134, true, player.getLastDuelRules().getStake());
		player.getPackets().sendIComponentText(ifFriendly ? 637 : 631, ifFriendly ? 16 : 38,
				" " + Utils.formatPlayerNameForDisplay(target.getUsername()));
		player.getPackets().sendIComponentText(ifFriendly ? 637 : 631, ifFriendly ? 18 : 40,
				"" + (target.getSkills().getCombatLevelWithSummoning()));
		player.getPackets().sendConfig(286, 0);
		player.getPackets().sendGlobalString(274, target.getDisplayName());
		player.getCustomDuelRule().put(1, false);
		target.getCustomDuelRule().put(1, false);
		player.getPackets().sendIComponentText(631, 69, "DDS Only");
		player.temporaryAttribute().put("firstScreen", true);
		refreshScreenMessage(true, ifFriendly);
		player.setCloseInterfacesEvent(new Runnable() {
			@Override
			public void run() {
				closeDuelInteraction(true, DuelStage.DECLINED);
			}
		});
	}

	public void sendFlash(int slot) {
		player.getPackets().sendInterFlashScript(631, 48, 3, 3, slot);
		target.getPackets().sendInterFlashScript(631, 49, 3, 3, slot);

	}

	public void refreshSpecialDuel(Player player, Entity target) {
		Player p2 = (Player) target;
		DuelRules rules = player.getLastDuelRules();
		player.getPackets().sendIComponentText(631, 69, "<col=ffc900>Dds only: "
				+ (player.getCustomDuelRule().get(1).equals(true) ? "<col=ff000>ENABLED" : "<col=ff0000>DISABLED"));
		p2.getPackets().sendIComponentText(631, 69, "<col=ffc900>Dds only: "
				+ (p2.getCustomDuelRule().get(1).equals(true) ? "<col=ff000>ENABLED" : "<col=ff0000>DISABLED"));
		rules.setRules(0);
		rules.setRules(2);
		rules.setRules(3);
		rules.setRules(4);
		rules.setRules(5);
		rules.setRules(25);
		rules.setRules(10, 21);
		rules.setRules(11, 22);
		rules.setRules(12, 23);
		rules.setRules(23, 31);
		rules.setRules(14, 25);
		rules.setRules(15, 26);
		rules.setRules(17, 27);
		rules.setRules(19, 28);
		rules.setRules(20, 29);
		rules.setRules(22, 30);
	}

	@Override
	public boolean processButtonClick(int interfaceId, int componentId, int slotId, int slotId2, int packetId) {
		if (target == null)
			return true;
		Controler controler = target.getControlerManager().getControler();
		if (!(controler instanceof DuelArena)) {
			return true;
		}
		synchronized (this) {
			synchronized (controler) {
				DuelRules rules = player.getLastDuelRules();
				Player p2 = (Player) target;
				if (player.getCustomDuelRule().get(1).equals(true) && p2.getCustomDuelRule().get(1).equals(true)
						&& componentId != 68 && componentId != 53 && componentId != 86 && componentId != 46 && interfaceId == 631) {
					player.sm("You must toggle the special mode off to make changes.");
					p2.sm("You must toggle the special mode off to make changes.");
					return false;
				}
				switch (interfaceId) {
				case 182:
					if (componentId == 6 || componentId == 13) {
						player.getPackets().sendGameMessage("Logging out from a duel isn't allowed!");
						return false;
					}
					return true;
				case 271:
				case 749:
					if (rules.getRule(5) && isDueling) {
						player.getPrayer().closeAllPrayers();
						player.getPackets().sendGameMessage("You can't use prayers in this duel.");
						return false;
					}
					return true;
				case 506:
					player.getDialogueManager().startDialogue("SimpleMessage",
							"A magical force prevents you from using the teleport.");
					return false;
				case 193:
				case 430:
				case 192:
					if (rules.getRule(2) && isDueling)
						return false;
					return true;
				case 884:
					if (componentId == 4) {
						if (rules.getRule(9) && isDueling) {
							player.getPackets().sendGameMessage("You can't use special attacks in this duel.");
							return false;
						}
					}
					return true;
				case 631:
					switch (componentId) {
					case 53:
						player.temporaryAttribute().put("duel_addingmoney", true);
						player.getPackets().sendRunScript(108,
								new Object[] { "                          Your money pouch contains "
										+ Utils.getFormattedNumber(player.getMoneyPouch().getTotal(), ',') + " coins."
										+ "                           How many would you like to add?" });
						return false;
					case 68:
						if (!DDS(player.getEquipment().getWeaponId()) || !DDS(p2.getEquipment().getWeaponId())) {
							player.sm(
									"<col=990000><u>Both of you must have equipped the Dragon Dagger to toggle this special duel mode.");
							p2.sm("<col=990000><u>Both of you must have equipped the Dragon Dagger to toggle this special duel mode.");
							return false;
						}
						player.getCustomDuelRule().put(1,
								player.getCustomDuelRule().get(1).equals(false) ? true : false);
						p2.getCustomDuelRule().put(1, p2.getCustomDuelRule().get(1).equals(false) ? true : false);
						refreshSpecialDuel(player, p2);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 56: // no range
						rules.setRules(0);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 57: // no melee
						rules.setRules(1);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 58: // no magic
						rules.setRules(2);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 59: // fun wep
						player.getPackets().sendGameMessage("Currently disabled.");
						return false;
					case 60: // no forfiet
						rules.setRules(7);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 61: // no drinks
						rules.setRules(3);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 62: // no food
						rules.setRules(4);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 63: // no prayer
						rules.setRules(5);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 64: // no movement
						rules.setRules(25);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						if (rules.getRule(6)) {
							rules.setRule(6, false);
							player.getPackets().sendGameMessage("You can't have movement without obstacles.");
						}
						return false;
					case 65: // obstacles
						player.getPackets().sendGameMessage("Currently disabled.");
						return false;
					case 66: // enable summoning
						// rules.setRules(24);
						player.getPackets().sendGameMessage("Currently disabled.");
						return false;
					case 67:// no spec
						rules.setRules(9);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 21:// no helm
						rules.setRules(10, 21);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 22:// no cape
						rules.setRules(11, 22);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 23:// no ammy
						rules.setRules(12, 23);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 31:// arrows
						rules.setRules(23, 31);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 24:// weapon
						rules.setRules(13, 24);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 25:// body
						rules.setRules(14, 25);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 26:// shield
						rules.setRules(15, 26);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 27:// legs
						rules.setRules(17, 27);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 28:// ring
						rules.setRules(19, 28);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 29: // bots
						rules.setRules(20, 29);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 30: // gloves
						rules.setRules(22, 30);
						((DuelArena) target.getControlerManager().getControler()).cancelAccepted();
						return false;
					case 107:
						closeDuelInteraction(true, DuelStage.DECLINED);
						return false;
					case 46:
						accept(true);
						return false;
					case 47:
						switch (packetId) {
						case WorldPacketsDecoder.ACTION_BUTTON1_PACKET:
							removeItem(slotId, 1);
							refresh(slotId);
							return false;
						case WorldPacketsDecoder.ACTION_BUTTON2_PACKET:
							removeItem(slotId, 5);
							refresh(slotId);
							return false;
						case WorldPacketsDecoder.ACTION_BUTTON3_PACKET:
							removeItem(slotId, 10);
							refresh(slotId);
							return false;
						case WorldPacketsDecoder.ACTION_BUTTON4_PACKET:
							Item item = player.getLastDuelRules().getStake().get(slotId);
							if (item == null)
								return false;
							removeItem(slotId, player.getLastDuelRules().getStake().getNumberOf(item));
							refresh(slotId);
							return false;
						case WorldPacketsDecoder.ACTION_BUTTON5_PACKET:
							player.temporaryAttribute().put("duel_item_X_Slot", slotId);
							player.temporaryAttribute().put("duel_isWithdraw", Boolean.TRUE);
							player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
							return false;
						}
						return false;
					}
				case 628:
					switch (packetId) {
					case WorldPacketsDecoder.ACTION_BUTTON1_PACKET:
						addItem(slotId, 1);
						refresh(slotId);
						return false;
					case WorldPacketsDecoder.ACTION_BUTTON2_PACKET:
						addItem(slotId, 5);
						refresh(slotId);
						return false;
					case WorldPacketsDecoder.ACTION_BUTTON3_PACKET:
						addItem(slotId, 10);
						refresh(slotId);
						return false;
					case WorldPacketsDecoder.ACTION_BUTTON4_PACKET:
						Item item = player.getInventory().getItems().get(slotId);
						if (item == null)
							return false;
						addItem(slotId, player.getInventory().getItems().getNumberOf(item));
						refresh(slotId);
						return false;
					case WorldPacketsDecoder.ACTION_BUTTON5_PACKET:
						player.temporaryAttribute().put("duel_item_X_Slot", slotId);
						player.temporaryAttribute().remove("duel_isWithdraw");
						player.getPackets().sendRunScript(108, new Object[] { "Enter Amount:" });
						return false;
					}
				case 626:
					switch (componentId) {
					case 43:
						accept(false);
						return false;
					}
				case 637: // friendly
					switch (componentId) {
					case 25: // no range
						rules.setRules(0);
						return false;
					case 26: // no melee
						rules.setRules(1);
						return false;
					case 27: // no magic
						rules.setRules(2);
						return false;
					case 28: // fun wep
						player.getPackets().sendGameMessage("We might add this in the future.");
						return false;
					case 29: // no forfiet
						rules.setRules(7);
						return false;
					case 30: // no drinks
						rules.setRules(3);
						return false;
					case 31: // no food
						rules.setRules(4);
						return false;
					case 32: // no prayer
						rules.setRules(5);
						return false;
					case 33: // no movement
						rules.setRules(25);
						if (rules.getRule(6)) {
							rules.setRule(6, false);
							player.getPackets().sendGameMessage("You can't have movement without obstacles.");
						}
						return false;
					case 34: // obstacles
						player.getPackets().sendGameMessage("Currently disabled.");
						return false;
					case 35: // enable summoning
						// player.getPackets().sendGameMessage("Currently
						// disabled.");
						rules.setRules(24);
						return false;
					case 36:// no spec
						rules.setRules(9);
						return false;
					case 43:// no helm
						rules.setRules(10);
						return false;
					case 44:// no cape
						rules.setRules(11);
						return false;
					case 45:// no ammy
						rules.setRules(12);
						return false;
					case 53:// arrows
						rules.setRules(23);
						return false;
					case 46:// weapon
						rules.setRules(13);
						return false;
					case 47:// body
						rules.setRules(14);
						return false;
					case 48:// shield
						rules.setRules(15);
						return false;
					case 49:// legs
						rules.setRules(17);
						return false;
					case 50:// ring
						rules.setRules(19);
						return false;
					case 51: // bots
						rules.setRules(20);
						return false;
					case 52: // gloves
						rules.setRules(22);
						return false;
					case 86:
						closeDuelInteraction(true, DuelStage.DECLINED);
						return false;
					case 21:
						accept(true);
						return false;
					}
				case 639:
					switch (componentId) {
					case 25:
						accept(false);
						return false;
					}
				}
			}
		}
		return true;
	}

	@Override
	public boolean processItemTeleport(WorldTile toTile) {
		player.getDialogueManager().startDialogue("SimpleMessage",
				"A magical force prevents you from teleporting from the arena.");
		return false;
	}

	@Override
	public boolean processMagicTeleport(WorldTile toTile) {
		player.getDialogueManager().startDialogue("SimpleMessage",
				"A magical force prevents you from teleporting from the arena.");
		return false;
	}

	@Override
	public boolean processObjectClick1(WorldObject object) {
		/*
		 * if(getWinner() == null) {
		 * player.getDialogueManager().startDialogue("ForfeitDialouge"); }
		 */
		return false;
	}

	public void refresh(int... slots) {
		player.getPackets().sendUpdateItems(134, player.getLastDuelRules().getStake(), slots);
		target.getPackets().sendUpdateItems(134, true, player.getLastDuelRules().getStake().getContainerItems(), slots);
	}

	private void refreshItems(Item[] itemsBefore) {
		int[] changedSlots = new int[itemsBefore.length];
		int count = 0;
		for (int index = 0; index < itemsBefore.length; index++) {
			Item item = player.getLastDuelRules().getStake().getContainerItems()[index];
			if (item != null)
				if (itemsBefore[index] != item) {
					changedSlots[count++] = index;
				}
		}
		int[] finalChangedSlots = new int[count];
		System.arraycopy(changedSlots, 0, finalChangedSlots, 0, count);
		refresh(finalChangedSlots);
	}

	private void refreshScreenMessage(boolean firstStage, boolean ifFriendly) {
		player.getPackets().sendIComponentText(firstStage ? (ifFriendly ? 637 : 631) : (ifFriendly ? 639 : 626),
				firstStage ? (ifFriendly ? 20 : 41) : (ifFriendly ? 23 : 35),
				"<col=ff0000>" + getAcceptMessage(firstStage));
	}

	private void refreshScreenMessages(boolean firstStage, boolean ifFriendly) {
		refreshScreenMessage(firstStage, ifFriendly);
		if (!ifFriendly) {
			player.getPackets().sendIComponentText(626, 25, "");
			player.getPackets().sendIComponentText(626, 26, "");
		}
		if (target != null) {
			((DuelArena) target.getControlerManager().getControler()).refreshScreenMessage(firstStage, ifFriendly);
		}
	}

	private void removeEquipment() {
		int slot = 0;
		for (int i = 10; i < 23; i++) {
			if (i == 14) {
				if (player.getEquipment().hasTwoHandedWeapon() || isWearingTwoHandedWeapon() == true)
					ButtonHandler.sendRemove(target, 3);
			}
			if (player.getLastDuelRules().getRule(i)) {
				slot = i - 10;
				ButtonHandler.sendRemove(player, slot);
			}
		}
	}

	public void removeItem(final int slot, int amount) {
		if (!hasTarget())
			return;
		Item item = player.getLastDuelRules().getStake().get(slot);
		if (item == null)
			return;
		Item[] itemsBefore = player.getLastDuelRules().getStake().getItemsCopy();
		int maxAmount = player.getLastDuelRules().getStake().getNumberOf(item);
		if (amount < maxAmount)
			item = new Item(item.getId(), amount);
		else
			item = new Item(item.getId(), maxAmount);
		player.getLastDuelRules().getStake().remove(slot, item);
		if (item.getId() == 995)
			player.getMoneyPouch().addMoney(item.getAmount(), false);
		else
			player.getInventory().addItem(item);
		sendFlash(slot);
		refreshItems(itemsBefore);
		refreshStakedWealth();
		cancelAccepted();
		player.setRuleCount(6);
		target.setRuleCount(6);
	}

	@Override
	public boolean sendDeath() {
		player.lock(7);
		player.animate(new Animation(836));
		WorldTasksManager.schedule(new WorldTask() {
			int loop;

			@Override
			public void run() {
				player.stopAll();
				target.closeInterfaces();
				if (loop == 1) {
				player.getPackets().sendGameMessage("Oh dear, you have died.");
				} else if (loop == 2) {
					player.animate(new Animation(-1));
					endDuel(target, player);
				} else if (loop == 3) {
					player.getPackets().sendMusicEffect(90);
					stop();
				}
				loop++;
			}
		}, 0, 1);
		return false;
	}

	private void sendFinishInterface(Player player, Player loser) {
		player.getInterfaceManager().sendInterface(634);
		player.getPackets().sendIComponentText(634, 17, "Close");
		player.getPackets().sendInterSetItemsOptionsScript(634, 28, 136, 6, 4, "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(634, 28, 0, 35, new int[] { 0, 1, 2, 3, 4, 5 });
		if (loser.getLastDuelRules().getStake() != null) {
			player.getPackets().sendItems(136, loser.getLastDuelRules().getStake());
		}
		player.getPackets().sendIComponentText(634, 30, "Name: ");
		player.getPackets().sendIComponentText(634, 31, "Combat Level: ");
		player.getPackets().sendIComponentText(634, 33, "" + Utils.formatPlayerNameForDisplay(loser.getUsername()));
		player.getPackets().sendIComponentText(634, 32, "" + loser.getSkills().getCombatLevelWithSummoning());
	}

	private void sendOptions(Player player) {
		player.getInterfaceManager().sendInventoryInterface(628);
		player.getPackets().sendUnlockIComponentOptionSlots(628, 0, 0, 27, 0, 1, 2, 3, 4, 5);
		player.getPackets().sendInterSetItemsOptionsScript(628, 0, 93, 4, 7, "Stake 1", "Stake 5", "Stake 10",
				"Stake All", "Stake X");
		player.getPackets().sendUnlockIComponentOptionSlots(631, 47, 0, 27, 0, 1, 2, 3, 4, 5);
		player.getPackets().sendInterSetItemsOptionsScript(631, 0, 120, 4, 7, "Remove 1", "Remove 5", "Remove 10",
				"Remove All", "Remove X");
	}

	@Override
	public void start() {
		this.target = (Player) getArguments()[0];
		ifFriendly = (boolean) getArguments()[1];
		openDuelScreen(target, ifFriendly);
	}

	private void startEndingTeleport(Player player) {
		WorldTile tile = LOBBY_TELEPORTS[Utils.random(LOBBY_TELEPORTS.length)];
		WorldTile teleTile = tile;
		for (int trycount = 0; trycount < 10; trycount++) {
			teleTile = new WorldTile(tile, 2);
			if (World.canMoveNPC(tile.getPlane(), teleTile.getX(), teleTile.getY(), player.getSize()))
				break;
			teleTile = tile;
		}
		player.setNextWorldTile(teleTile);
	}

	public boolean DDS(int itemId) {
		ItemDefinitions defs = ItemDefinitions.getItemDefinitions(itemId);
		String name = defs == null ? "" : defs.getName().toLowerCase();
		if (name.contains("dragon dagger"))
			return true;
		return false;
	}

	public boolean twoHanded(int itemId) {
		ItemDefinitions defs = ItemDefinitions.getItemDefinitions(itemId);
		String name = defs == null ? "" : defs.getName().toLowerCase();
		if (name.contains(" bow") || name.contains("godsword") || name.contains("anchor")
				|| name.contains("hand cannon") || name.contains(" ket-om") || name.contains("2h")
				|| name.contains("claws") || name.contains("greataxe") || name.contains("spear")
				|| name.contains("katana") || name.contains("zaryte") || name.contains("halberd")
				|| name.contains("maul") || name.contains("shortbow") || name.contains("longbow")
				|| name.contains("saradomin sword") || name.contains("claws"))
			return true;
		return false;
	}
}