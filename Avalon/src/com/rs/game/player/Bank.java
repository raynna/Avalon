package com.rs.game.player;

import java.io.Serializable;

import com.rs.Settings;
import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.item.Item;
import com.rs.game.npc.familiar.Familiar;
import com.rs.game.player.content.ItemConstants;
import com.rs.utils.EconomyPrices;
import com.rs.utils.Utils;

public class Bank implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1551246756081236625L;

	// tab, items
	public Item[][] bankTabs;
	@SuppressWarnings("unused")
	private short bankPin;
	private int lastX;

	private long recoveryDelay, activatedTime, lockPunishment;
	private byte[] actualPin, requestedPin;
	private transient byte stage;

	private boolean boughtSlots;
	private transient int freeSize, memberSize;

	private transient Player player;
	private transient int currentTab;
	private transient Item[] lastContainerCopy;
	private transient boolean withdrawNotes;
	private boolean placeholders;
	private transient boolean insertItems;

	public static final long MAX_BANK_SIZE = Settings.FREE_TO_PLAY ? 68 : 468;

	public Bank() {
		bankTabs = new Item[1][0];
	}

	public int getAmountOfItem(Item item) {
		if (bankTabs == null)
			return 0;
		for (int index = 0; index < bankTabs.length; index++) {
			if (bankTabs[index] == null)
				continue;
			for (Item reference : bankTabs[index]) {
				if (reference == null)
					continue;
				if (reference.getId() == item.getId())
					return reference.getAmount();
			}
		}
		return 0;
	}

	@SuppressWarnings("unused")
	private void checkPinStatus() {
		if (recoveryDelay < Utils.currentTimeMillis()) {
			if (requestedPin != null) {
				actualPin = requestedPin;
				requestedPin = null;
			} else
				actualPin = null;
		} else {
			player.getInterfaceManager().sendInterface(14);
			player.setCloseInterfacesEvent(new Runnable() {

				@Override
				public void run() {
					if (actualPin != null)
						openPin();
				}
			});
		}
	}

	public long getBankValue() {
		long value = 0;
		for (Item bank : player.getBank().getContainerCopy()) {
			if (bank == null)
				continue;
			long amount = bank.getAmount();
			value += bank.getDefinitions().getTipitPrice() * amount;
		}
		return value;
	}

	public void init() {
		calculateBankSize();
		boughtSlots = false;
		setBoughtSlotsAmount(100);
	}

	private void calculateBankSize() {
		freeSize = 0;
		memberSize = 0;
		for (Item[] tab : bankTabs) {
			for (Item item : tab) {
				if (item.getDefinitions().isMembersOnly()) {
					memberSize++;
				} else {
					freeSize++;
				}
			}
		}
	}

	private void refreshBankSize() {
		player.getPackets().sendGlobalConfig(1024, isBoughtSlots() ? 1 : 0);
		player.getPackets().sendGlobalConfig(1038, freeSize);
		player.getPackets().sendGlobalConfig(192, memberSize + freeSize);
		player.getPackets().sendGlobalConfig(638, freeSize);

	}

	public boolean isBoughtSlots() {
		return boughtSlots;
	}

	public void setBoughtSlots(boolean boughtSlots) {
		this.boughtSlots = boughtSlots;
	}

	public void openPinSettings() {

	}

	public void openPin() {
		player.getPackets().sendGlobalConfig(98, 1);
		player.getVarsManager().sendVarBit(1010, -1);
		player.getInterfaceManager().sendInterface(13);
		player.getInterfaceManager().setInterface(false, 13, 5, 759);
		for (int i = 0; i < 40; i += 4) {
			player.getPackets().sendUnlockIComponentOptionSlots(759, i, 0, 100, true, 0, 1, 2);
		}
		if (recoveryDelay >= Utils.currentTimeMillis()) {
			int days = (int) (recoveryDelay / 86400000);
			int hours = days / 24;
			player.getPackets().sendIComponentText(13, 27,
					actualPin != null ? "Your bankpin will be set in " + days + " days and " + hours + " hours."
							: "Your bankpin will be deleted in " + days + " days and " + hours + " hours.");
		} else
			player.getPackets().sendIComponentText(13, 27, "Bank of " + Settings.SERVER_NAME);
	}

	public void checkPinInput(boolean isConfirmation, int componentId) {
		setRequestedPin((byte) ((componentId - 4) / 4));
		if (stage++ == 4) {
			byte[] requestedPin = getRequestedPin();
			if (actualPin == null || requestedPin != actualPin) {
				if (isConfirmation) {
					this.requestedPin = requestedPin;
					recoveryDelay += (86400000 * 3);
					StringBuilder builder = new StringBuilder();
					builder.append("Your PIN has been set, the personal identification number is : ");
					for (int pinNumber : requestedPin)
						builder.append(pinNumber + " - ");
					player.getPackets().sendGameMessage(builder.toString());
				} else {
					player.getPackets().sendGameMessage("The PIN you have put is incorrect.");
				}
				player.closeInterfaces();
			} else if (requestedPin == actualPin) {
				player.getPackets().sendGameMessage("Successfully entered your PIN number.");
				player.getBank().openBank();
				setActivatedTime(getActivatedTime() + (3600000 * 6));
			} else {
				player.getPackets().sendGameMessage(
						"The PIN you have selected is in the same sequence your currently have. Please pick different numbers or sequences.");
			}
		}
	}

	private byte[] getRequestedPin() {
		byte[] pin = (byte[]) player.temporaryAttribute().get("temporary_pin");
		if (pin == null)
			return (byte[]) player.temporaryAttribute().put("temporary_pin", new byte[4]);
		return pin;
	}

	private void setRequestedPin(byte value) {
		byte[] pin = (byte[]) player.temporaryAttribute().get("temporary_pin");
		if (pin == null)
			pin = new byte[5];
		pin[stage] = value;
		player.temporaryAttribute().put("temporary_pin", pin);
	}

	public void removeItem(int id) {
		if (bankTabs != null) {
			for (int i = 0; i < bankTabs.length; i++) {
				for (int i2 = 0; i2 < bankTabs[i].length; i2++) {
					if (bankTabs[i][i2].getId() == id)
						bankTabs[i][i2].setId(-1); // dwarf remains
				}
			}
		}
	}

	public void removePlaceholders() {
		for (Item items : player.getBank().getContainerCopy()) {
			if (items == null)
				continue;
			if (items.getDefinitions().isNoted())
				removeItem(items.getId());
		}
		player.getPackets().sendGameMessage("Your placeholders were deleted.");
	}

	public void setPlayer(Player player) {
		this.player = player;
		if (bankTabs == null || bankTabs.length == 0)
			bankTabs = new Item[1][0];
	}

	public void reset() {
		bankTabs = new Item[1][0];
	}

	public void setItem(int slotId, int amt) {
		Item item = getItem(slotId);
		if (item == null) {
			refreshItems();
			refreshTabs();
			refreshViewingTab();
		}
	}

	public void openPlayerBank(Player victim) {
		if (victim == null) {
			return;
		}
		player.getInterfaceManager().sendInterface(762);
		player.getInterfaceManager().sendInventoryInterface(763);
		player.getPackets().sendItems(95, victim.getBank().getContainerCopy());
		refreshViewingTab();
		refreshTabs();
		unlockButtons();
	}

	public void refreshTabs() {
		for (int slot = 1; slot < 9; slot++)
			refreshTab(slot);
	}

	public int getTabSize(int slot) {
		if (slot >= bankTabs.length)
			return 0;
		return bankTabs[slot].length;
	}

	public void withdrawLastAmount(int bankSlot) {
		withdrawItem(bankSlot, lastX);
	}

	public void withdrawItemButOne(int fakeSlot) {
		int[] fromRealSlot = getRealSlot(fakeSlot);
		Item item = getItem(fromRealSlot);
		if (item == null)
			return;
		if (item.getAmount() <= 1) {
			player.getPackets().sendGameMessage("You only have one of this item in your bank");
			return;
		}
		withdrawItem(fakeSlot, item.getAmount() - 1);
	}

	public void depositLastAmount(int bankSlot) {
		depositItem(bankSlot, lastX, true);
	}

	public void depositAllInventory(boolean banking) {
		Inventory inventory = player.getInventory();
		if (player.getInventory().getFreeSlots() == 28) {
			player.getPackets().sendGameMessage("You have nothing in your inventory to bank.");
			return;
		}
		for (Item items : inventory.getItems().toArray()) {
			if (items == null)
				continue;
			if (items.getDefinitions().isNoted())
				items.setId(items.getDefinitions().getCertId());
		}
		int space = addItems(inventory.getItems().getContainerItems(), banking);
		if (space != 0) {
			for (int i = 0; i < space; i++)
				inventory.getItems().set(i, null);
			inventory.refresh();
		} else {
			player.getPackets().sendGameMessage("You don't have enough bank space.");
		}
		refreshTab(currentTab);
		refreshItems();
	}

	public void depositAllBob(boolean banking) {
		Familiar familiar = player.getFamiliar();
		if (familiar == null || familiar.getBob() == null) {
			player.getPackets().sendGameMessage("You don't have anything in your beast of burden to bank.");
			return;
		}
		int space = addItems(familiar.getBob().getBeastItems().getContainerItems(), banking);
		if (space != 0) {
			for (int i = 0; i < space; i++)
				familiar.getBob().getBeastItems().set(i, null);
			familiar.getBob().sendInterItems();
		}
		if (space < familiar.getBob().getBeastItems().getSize()) {
			player.getPackets().sendGameMessage("Not enough space in your bank.");
			return;
		}
		refreshItems();
	}

	public void depositAllEquipment(boolean banking) {
		int space = addItems(player.getEquipment().getItems().getContainerItems(), banking);
		if (player.getEquipment().getItem(Equipment.SLOT_AMULET) == null
				&& player.getEquipment().getItem(Equipment.SLOT_ARROWS) == null
				&& player.getEquipment().getItem(Equipment.SLOT_AURA) == null
				&& player.getEquipment().getItem(Equipment.SLOT_CAPE) == null
				&& player.getEquipment().getItem(Equipment.SLOT_CHEST) == null
				&& player.getEquipment().getItem(Equipment.SLOT_FEET) == null
				&& player.getEquipment().getItem(Equipment.SLOT_HANDS) == null
				&& player.getEquipment().getItem(Equipment.SLOT_HAT) == null
				&& player.getEquipment().getItem(Equipment.SLOT_LEGS) == null
				&& player.getEquipment().getItem(Equipment.SLOT_RING) == null
				&& player.getEquipment().getItem(Equipment.SLOT_SHIELD) == null
				&& player.getEquipment().getItem(Equipment.SLOT_WEAPON) == null) {
			player.getPackets().sendGameMessage("You have nothing equipped to bank.");
			return;
		}
		if (space != 0) {
			for (int i = 0; i < space; i++)
				player.getEquipment().getItems().set(i, null);
			player.getEquipment().init();
			player.getAppearence().generateAppearenceData();
		}
		refreshItems();
	}

	public void depositMoneyPouch(boolean banking) {
		Item coins = player.getBank().getItem(995);
		if (player.getMoneyPouch().getTotal() == 0) {
			player.getPackets().sendGameMessage("You don't have any coins in money pouch to bank.");
			return;
		}
		if (coins != null) {
			if (coins.getAmount() == Integer.MAX_VALUE) {
				player.getPackets().sendGameMessage("You cannot store more coins.");
				return;
			}
		}
		if (!player.getBank().hasBankSpace()) {
			player.sm("Not enough bank space.");
			return;
		}
		int leftOver = 0;
		if (coins != null && coins.getAmount() + player.getMoneyPouch().getTotal() < 0) {
			leftOver = Integer.MAX_VALUE - coins.getAmount();
			removeItem2(getItemSlot(995), Integer.MAX_VALUE, true, false);
			addItem(995, Integer.MAX_VALUE, true);
			player.getMoneyPouch().removeMoneyMisc(leftOver);
		} else {
			addItem(995, player.getMoneyPouch().getTotal(), banking);
			player.getMoneyPouch().removeMoneyMisc(player.getMoneyPouch().getTotal());
		}
		refreshItems();
	}

	public void collapse(int tabId) {
		if (tabId == 0 || tabId >= bankTabs.length)
			return;
		Item[] items = bankTabs[tabId];
		for (Item item : items)
			removeItem2(getItemSlot(item.getId()), item.getAmount(), false, true);
		for (Item item : items)
			addItem(item.getId(), item.getAmount(), 0, false);
		refreshTabs();
		refreshItems();
	}

	public void insertItem(int fromSlot, int tab, int index) {
		if (index == fromSlot)
			return;
		Item[] bankTab = new Item[bankTabs[tab].length];
		for (int i = 0; i < bankTabs[tab].length; i++) {
			if ((i < index || (i > index && i > fromSlot)) && index < fromSlot)
				bankTab[i] = bankTabs[tab][i];
			else if ((i >= index || i < fromSlot) && fromSlot < index)
				bankTab[i] = bankTabs[tab][i];
			else if (i == (fromSlot < index ? index - 1 : index))
				bankTab[i] = bankTabs[tab][fromSlot];
			else
				bankTab[i] = bankTabs[tab][fromSlot < index ? i + 1 : i - 1];
		}
		bankTabs[tab] = bankTab;
		refreshItems();
		refreshTabs();
		refreshViewingTab();
	}

	public void switchItem(int fromSlot, int toSlot, int fromComponentId, int toComponentId) {
		// System.out.println(fromSlot+", "+toSlot+", "+fromComponentId+",
		// "+toComponentId);
		if (toSlot == 65535) {
			int toTab = toComponentId >= 76 ? 8 - (84 - toComponentId) : 9 - ((toComponentId - 46) / 2);
			if (toTab < 0 || toTab > 9)
				return;
			if (bankTabs.length == toTab) {
				int[] fromRealSlot = getRealSlot(fromSlot);
				if (fromRealSlot == null)
					return;
				if (toTab == fromRealSlot[0]) {
					switchItem(fromSlot, getStartSlot(toTab));
					return;
				}
				Item item = getItem(fromRealSlot);
				if (item == null)
					return;
				removeItem2(fromSlot, item.getAmount(), false, true);
				createTab();
				bankTabs[bankTabs.length - 1] = new Item[] { item };
				refreshTab(fromRealSlot[0]);
				refreshTab(toTab);
				refreshItems();
			} else if (bankTabs.length > toTab) {
				int[] fromRealSlot = getRealSlot(fromSlot);
				if (fromRealSlot == null)
					return;
				if (toTab == fromRealSlot[0]) {
					switchItem(fromSlot, getStartSlot(toTab));
					return;
				}
				Item item = getItem(fromRealSlot);
				if (item == null)
					return;
				boolean removed = removeItem2(fromSlot, item.getAmount(), false, true);
				if (!removed)
					refreshTab(fromRealSlot[0]);
				else if (fromRealSlot[0] != 0 && toTab >= fromRealSlot[0])
					toTab -= 1;
				refreshTab(fromRealSlot[0]);
				addItem(item.getId(), item.getAmount(), toTab, true);
			}
		} else {
			switchItem(fromSlot, toSlot);
		}
	}

	public void switchItem(int fromSlot, int toSlot) {
		int[] fromRealSlot = getRealSlot(fromSlot);
		Item fromItem = getItem(fromRealSlot);
		if (fromItem == null)
			return;
		int[] toRealSlot = getRealSlot(toSlot);
		Item toItem = getItem(toRealSlot);
		if (toItem == null)
			return;
		if (insertItems) {
			insertItem(fromSlot, fromRealSlot[0], toSlot);
			return;
		}
		bankTabs[fromRealSlot[0]][fromRealSlot[1]] = toItem;
		bankTabs[toRealSlot[0]][toRealSlot[1]] = fromItem;
		refreshTab(fromRealSlot[0]);
		if (fromRealSlot[0] != toRealSlot[0])
			refreshTab(toRealSlot[0]);
		refreshItems();
	}

	public void openSetPin() { // TODO

	}

	public void openDepositBox() {
		player.getInterfaceManager().sendInterface(11);
		player.getInterfaceManager().closeInventory();
		player.getInterfaceManager().closeEquipment();
		final int lastGameTab = player.getInterfaceManager().openGameTab(9); // friends
		// tab
		sendBoxInterItems();
		player.getPackets().sendIComponentText(11, 13, "The Bank of " + Settings.SERVER_NAME + " - Deposit Box");
		player.setCloseInterfacesEvent(new Runnable() {
			@Override
			public void run() {
				player.temporaryAttribute().remove("Banking");
				player.getInterfaceManager().sendInventory();
				player.getInventory().unlockInventoryOptions();
				player.getInterfaceManager().sendEquipment();
				player.getInterfaceManager().openGameTab(lastGameTab);
			}
		});
	}

	public void sendBoxInterItems() {
		player.getPackets().sendInterSetItemsOptionsScript(11, 17, 93, 6, 5, "Deposit-1", "Deposit-5", "Deposit-10",
				"Deposit-All", "Deposit-X", "Examine");
		player.getPackets().sendUnlockIComponentOptionSlots(11, 17, 0, 27, 0, 1, 2, 3, 4, 5);
	}

	public void openBank() {
		lastContainerCopy = null;
		player.getInterfaceManager().sendInterface(762);
		player.getInterfaceManager().sendInventoryInterface(763);
		player.getVarsManager().sendVarBit(8348, 0);
		refreshViewingTab();
		refreshTabs();
		unlockButtons();
		sendItems();
		refreshLastX();
		refreshBankSize();
	}

	public void refreshLastX() {
		player.getPackets().sendConfig(1249, lastX);
	}

	public void createTab() {
		int slot = bankTabs.length;
		Item[][] tabs = new Item[slot + 1][];
		System.arraycopy(bankTabs, 0, tabs, 0, slot);
		tabs[slot] = new Item[0];
		bankTabs = tabs;
	}

	public void destroyTab(int slot) {
		Item[][] tabs = new Item[bankTabs.length - 1][];
		System.arraycopy(bankTabs, 0, tabs, 0, slot);
		System.arraycopy(bankTabs, slot + 1, tabs, slot, bankTabs.length - slot - 1);
		bankTabs = tabs;
		if (currentTab != 0 && currentTab >= slot)
			currentTab--;
	}

	public boolean hasBankSpace() {
		return getBankSize() < getMaxBankSpace();
	}

	private int getMaxBankSpace() {
		return (int) (Settings.FREE_TO_PLAY ? 68 : (68 + MAX_BANK_SIZE + (isBoughtSlots() ? getBoughtSlots() : 0)));
	}

	private int boughtSlotsAmount;

	public void setBoughtSlotsAmount(int amount) {
		boughtSlotsAmount = amount;
	}

	public int getBoughtSlots() {
		return boughtSlotsAmount;
	}

	public void withdrawItem(int[] slots, int quantity) {
		if (quantity < 1)
			return;
		if (slots == null)
			return;
		Item item = getItem(slots);
		if (item == null)
			return;
		if (item.getAmount() < quantity)
			item = new Item(item.getId(), item.getAmount());
		else
			item = new Item(item.getId(), quantity);
		boolean noted = false;
		ItemDefinitions defs = item.getDefinitions();
		if (withdrawNotes) {
			if (!defs.isNoted() && defs.getCertId() != -1) {
				item.setId(defs.getCertId());
				noted = true;
			} else
				player.getPackets().sendGameMessage("You cannot withdraw this item as a note.");
		}
		if (noted || defs.isStackable()) {
			if (player.getInventory().getItems().containsOne(item)) {
				int slot = player.getInventory().getItems().getThisItemSlot(item);
				Item invItem = player.getInventory().getItems().get(slot);
				if (invItem.getAmount() + item.getAmount() <= 0) {
					item.setAmount(Integer.MAX_VALUE - invItem.getAmount());
					player.getPackets().sendGameMessage("Not enough space in your inventory.");
				}
			} else if (!player.getInventory().hasFreeSlots()) {
				player.getPackets().sendGameMessage("Not enough space in your inventory.");
				return;
			}
		} else {
			int freeSlots = player.getInventory().getFreeSlots();
			if (freeSlots == 0) {
				player.getPackets().sendGameMessage("Not enough space in your inventory.");
				return;
			}
			if (freeSlots < item.getAmount()) {
				item.setAmount(freeSlots);
				player.getPackets().sendGameMessage("Not enough space in your inventory.");
			}
		}
		removeItem(slots, item.getAmount(), true, false);
		player.getInventory().addItem(item);
	}

	public void withdrawItem(int bankSlot, int quantity) {
		if (quantity < 1)
			return;
		Item item = getItem(getRealSlot(bankSlot));
		if (item == null)
			return;
		if (item.getDefinitions().isNoted()) {
			removeItem(bankSlot, item.getAmount(), true, true);
			return;
		}
		if (item.getAmount() < quantity)
			item = new Item(item.getId(), item.getAmount());
		else
			item = new Item(item.getId(), quantity);
		boolean noted = false;
		ItemDefinitions defs = item.getDefinitions();
		if (withdrawNotes) {
			if (!defs.isNoted() && defs.getCertId() != -1) {
				item.setId(defs.getCertId());
				noted = true;
			} else
				player.getPackets().sendGameMessage("You cannot withdraw this item as a note.");
		}
		if (noted || defs.isStackable()) {
			if (player.getInventory().getItems().containsOne(item)) {
				int slot = player.getInventory().getItems().getThisItemSlot(item);
				Item invItem = player.getInventory().getItems().get(slot);
				if (invItem.getAmount() + item.getAmount() <= 0) {
					item.setAmount(Integer.MAX_VALUE - invItem.getAmount());
					player.getPackets().sendGameMessage("Not enough space in your inventory.");
				}
			} else if (!player.getInventory().hasFreeSlots()) {
				player.getPackets().sendGameMessage("Not enough space in your inventory.");
				return;
			}
		} else {
			int freeSlots = player.getInventory().getFreeSlots();
			if (freeSlots == 0) {
				player.getPackets().sendGameMessage("Not enough space in your inventory.");
				return;
			}
			if (freeSlots < item.getAmount()) {
				item.setAmount(freeSlots);
				player.getPackets().sendGameMessage("Not enough space in your inventory.");
			}
		}
		if (item.getId() != 995) {
			if (placeholders && !item.getDefinitions().isStackable() && item.getDefinitions().getCertId() != -1) {
				removeItem(bankSlot, item.getAmount(), true, false);
			} else {
				removeItem2(bankSlot, item.getAmount(), true, false);
			}
		}
		if (item.getId() == 995)
			player.getMoneyPouch().addMoneyFromBank(item.getAmount(), bankSlot);
		else
			player.getInventory().addItem(item);
	}

	public void sendExamine(int fakeSlot) {
		int[] slot = getRealSlot(fakeSlot);
		if (slot == null)
			return;
		Item item = bankTabs[slot[0]][slot[1]];
		long price;
		long amount;
		// player.getPackets().sendGameMessage(ItemExamines.getExamine(item));
		if (!ItemConstants.isTradeable(item)) {
			player.getPackets()
					.sendGameMessage("[Price Checker] " + item.getDefinitions().getName() + " is untradeable.");
			return;
		}
		if (item.getId() == 995) {
			player.getPackets().sendGameMessage("[Price Checker] " + Utils.getFormattedNumber(item.getAmount(), ',')
					+ " x " + item.getDefinitions().getName() + ".");
			return;
		}
		if ((item.getDefinitions().isNoted() || item.getDefinitions().isStackable()) && item.getAmount() > 1) {
			amount = item.getAmount();
			price = EconomyPrices.getPrice(item.getId()) * amount;
			player.getPackets()
					.sendGameMessage("[Price Checker] " + Utils.getFormattedNumber(item.getAmount(), ',') + " x "
							+ item.getDefinitions().getName() + ": " + Utils.formatMillionAmount(price) + " coins. ("
							+ Utils.getFormattedNumber(EconomyPrices.getPrice(item.getId()), ',') + " each)");
		} else {
			amount = item.getAmount();
			price = EconomyPrices.getPrice(item.getId()) * amount;
			player.getPackets().sendGameMessage("[Price Checker]  "
					+ (item.getAmount() > 1 ? Utils.getFormattedNumber(item.getAmount(), ',') + " x " : "")
					+ item.getDefinitions().getName() + ": " + Utils.formatMillionAmount(price) + " coins."
					+ (item.getAmount() > 1
							? " (" + Utils.getFormattedNumber(EconomyPrices.getPrice(item.getId()), ',') + " each)"
							: ""));
		}
	}

	public void depositItem(int invSlot, int quantity, boolean refresh) {
		if (quantity < 1 || invSlot < 0 || invSlot > 27)
			return;
		Item item = player.getInventory().getItem(invSlot);
		if (item == null)
			return;
		int amt = player.getInventory().getItems().getNumberOf(item);
		if (amt < quantity)
			item = new Item(item.getId(), amt);
		else
			item = new Item(item.getId(), quantity);
		ItemDefinitions defs = item.getDefinitions();
		int originalId = item.getId();
		if (defs.isNoted() && defs.getCertId() != -1)
			item.setId(defs.getCertId());
		Item bankedItem = getItem(item.getId());
		if (bankedItem != null && bankedItem.getAmount() == Integer.MAX_VALUE) {
			player.getPackets().sendGameMessage("Not enough space in your bank.");
			return;
		}
		if (bankedItem != null) {
			if (bankedItem.getAmount() + item.getAmount() < 0) {
				item.setAmount(Integer.MAX_VALUE - bankedItem.getAmount());
				player.getPackets().sendGameMessage("Not enough space in your bank.");
			}
		} else if (!hasBankSpace()) {
			player.getPackets().sendGameMessage("Not enough space in your bank.");
			return;
		}
		player.getInventory().deleteItem(invSlot, new Item(originalId, item.getAmount()));
		addItem(item, refresh);
	}

	public void addItem(Item item, boolean refresh) {
		addItem(item.getId(), item.getAmount(), refresh);
	}

	public int addItems(Item[] items, boolean refresh) {
		int space = (int) (getMaxBankSpace() - getBankSize());
		if (space != 0) {
			space = (space < items.length ? space : items.length);
			for (int i = 0; i < space; i++) {
				if (items[i] == null)
					continue;
				addItem(items[i], false);
			}
			if (refresh) {
				refreshTabs();
				refreshItems();
			}
		}
		return space;
	}

	public void addItem(int id, int quantity, boolean refresh) {
		addItem(id, quantity, currentTab, refresh);
	}

	public void addItem(int id, int quantity, int creationTab, boolean refresh) {
		int[] slotInfo = getItemSlot(id);
		if (slotInfo == null) {
			if (creationTab >= bankTabs.length) {
				creationTab = bankTabs.length - 1;
			}
			if (creationTab < 0) {
				creationTab = 0;
			}
			int slot = bankTabs[creationTab].length;
			Item[] tab = new Item[slot + 1];
			System.arraycopy(bankTabs[creationTab], 0, tab, 0, slot);
			tab[slot] = new Item(id, quantity);
			bankTabs[creationTab] = tab;
			if (tab[slot].getDefinitions().isMembersOnly()) {
				memberSize++;
			} else {
				freeSize++;
			}
			if (refresh) {
				refreshTab(creationTab);
			}
		} else {
			Item item = bankTabs[slotInfo[0]][slotInfo[1]];
			if (item.getDefinitions().isNoted()) {
				bankTabs[slotInfo[0]][slotInfo[1]] = new Item(item.getDefinitions().getCertId(), quantity);
			} else
				bankTabs[slotInfo[0]][slotInfo[1]] = new Item(item.getId(), item.getAmount() + quantity);
		}
		if (refresh) {
			refreshItems();
			calculateBankSize();
		}
	}

	public boolean removeItem(int fakeSlot, int quantity, boolean refresh, boolean forceDestroy) {
		return removeItem(getRealSlot(fakeSlot), quantity, refresh, forceDestroy);
	}

	public boolean removeItem2(int fakeSlot, int quantity, boolean refresh, boolean forceDestroy) {
		return removeItem2(getRealSlot(fakeSlot), quantity, refresh, forceDestroy);
	}

	public boolean removeItem2(int[] slot, int quantity, boolean refresh, boolean forceDestroy) {
		if (slot == null) {
			player.getPackets().sendGameMessage("slot is null.");
			return false;
		}
		Item item = bankTabs[slot[0]][slot[1]];
		boolean destroyed = false;
		if (quantity >= item.getAmount()) {
			if ((item.getDefinitions().getCertId() == -1 || item.getDefinitions().isStackable()) && placeholders)
				player.getPackets().sendGameMessage("You can't create a placeholder out of " + item.getName() + ".");
			if (bankTabs[slot[0]].length == 1 && (forceDestroy || bankTabs.length != 1)) {
				destroyTab(slot[0]);
				if (refresh)
					refreshTabs();
				destroyed = true;
			} else {
				Item[] tab = new Item[bankTabs[slot[0]].length - 1];
				System.arraycopy(bankTabs[slot[0]], 0, tab, 0, slot[1]);
				System.arraycopy(bankTabs[slot[0]], slot[1] + 1, tab, slot[1], bankTabs[slot[0]].length - slot[1] - 1);
				bankTabs[slot[0]] = tab;
				if (refresh)
					refreshTab(slot[0]);
			}
		} else {
			bankTabs[slot[0]][slot[1]] = new Item(item.getId(), item.getAmount() - quantity);
		}
		if (refresh)
			refreshItems();
		return destroyed;
	}

	public boolean removeItem(int[] slot, int quantity, boolean refresh, boolean forceDestroy) {
		if (slot == null) {
			player.getPackets().sendGameMessage("slot is null.");
			return false;
		}
		// Item item = bankTabs[slot[0]][slot[1]];
		boolean destroyed = false;
		/*
		 * if (item.getDefinitions().isNoted()) {
		 * player.getPackets().sendGameMessage("Placeholder for item " + item.getName()
		 * + " deleted."); if (bankTabs[slot[0]].length == 1 && (forceDestroy ||
		 * bankTabs.length != 1)) { destroyTab(slot[0]); if (refresh) refreshTabs();
		 * destroyed = true; } else { Item[] tab = new Item[bankTabs[slot[0]].length -
		 * 1]; System.arraycopy(bankTabs[slot[0]], 0, tab, 0, slot[1]);
		 * System.arraycopy(bankTabs[slot[0]], slot[1] + 1, tab, slot[1],
		 * bankTabs[slot[0]].length - slot[1] - 1); bankTabs[slot[0]] = tab; if
		 * (refresh) refreshTab(slot[0]); } } else { if (quantity >= item.getAmount() &&
		 * item.getDefinitions().canBeNoted()) {
		 * player.getPackets().sendGameMessage("Placeholder for item " + item.getName()
		 * + " created."); int certId =
		 * ItemDefinitions.getItemDefinitions(item.getId()).getCertId();
		 * bankTabs[slot[0]][slot[1]] = new Item(certId, 1); if (refresh)
		 * refreshItems(); return false; } bankTabs[slot[0]][slot[1]] = new
		 * Item(item.getId(), item.getAmount() - quantity); }
		 */
		if (bankTabs[slot[0]].length == 1 && (forceDestroy || bankTabs.length != 1)) {
			destroyTab(slot[0]);
			if (refresh)
				refreshTabs();
			destroyed = true;
		} else {
			Item[] tab = new Item[bankTabs[slot[0]].length - 1];
			System.arraycopy(bankTabs[slot[0]], 0, tab, 0, slot[1]);
			System.arraycopy(bankTabs[slot[0]], slot[1] + 1, tab, slot[1], bankTabs[slot[0]].length - slot[1] - 1);
			bankTabs[slot[0]] = tab;
			if (refresh)
				refreshTab(slot[0]);
		}
		if (refresh)
			refreshItems();
		return destroyed;
	}

	public Item getItem(int id) {
		for (int slot = 0; slot < bankTabs.length; slot++) {
			for (Item item : bankTabs[slot])
				if (item.getId() == id)
					return item;
		}
		return null;
	}

	public boolean containsOneItem(int... itemIds) {
		for (int itemId : itemIds) {
			if (getItem(itemId) != null)
				return true;
		}
		return false;
	}

	public int[] getItemSlot(int id) {
		for (int tab = 0; tab < bankTabs.length; tab++) {
			for (int slot = 0; slot < bankTabs[tab].length; slot++) {
				if (bankTabs[tab][slot].getId() == ItemDefinitions.getItemDefinitions(id).getCertId())
					return new int[] { tab, slot };
				if (bankTabs[tab][slot].getId() == id)
					return new int[] { tab, slot };
			}
		}
		return null;
	}

	public Item getItem(int[] slot) {
		if (slot == null)
			return null;
		return bankTabs[slot[0]][slot[1]];
	}

	public int getStartSlot(int tabId) {
		int slotId = 0;
		for (int tab = 1; tab < (tabId == 0 ? bankTabs.length : tabId); tab++)
			slotId += bankTabs[tab].length;

		return slotId;

	}

	public int[] getRealSlot(int slot) {
		for (int tab = 1; tab < bankTabs.length; tab++) {
			if (slot >= bankTabs[tab].length)
				slot -= bankTabs[tab].length;
			else
				return new int[] { tab, slot };
		}
		if (slot >= bankTabs[0].length)
			return null;
		return new int[] { 0, slot };
	}

	public void refreshViewingTab() {
		player.getPackets().sendConfigByFile(4893, currentTab + 1);
	}

	public void refreshValue() {
		long bank = getBankValue();
		String wealth = Utils.formatDoubledAmount(bank) + " GP";
		if (bank == 0) {
			player.getPackets().sendIComponentText(762, 47, "Where's your bank m8?");
		} else if (bank > 0 && bank <= 9999999) {
			player.getPackets().sendIComponentText(762, 47, "<col=FFFFFF>" + wealth);
		} else if (bank > 10000000 && bank <= 999999999) {
			player.getPackets().sendIComponentText(762, 47, "<col=ff000>" + wealth);
		} else if (bank >= 1000000000) {
			player.getPackets().sendIComponentText(762, 47, "<col=ffc800>" + wealth);
		}
	}

	public void refreshTab(int slot) {
		if (slot == 0)
			return;
		player.getPackets().sendConfigByFile(4885 + (slot - 1), getTabSize(slot));
		calculateBankSize();
		refreshBankSize();
	}

	public void sendItems() {
		player.getPackets().sendItems(95, getContainerCopy());
	}

	public void refreshItems(int[] slots) {
		player.getPackets().sendUpdateItems(95, getContainerCopy(), slots);
	}

	public Item[] getContainerCopy() {
		if (lastContainerCopy == null)
			lastContainerCopy = generateContainer();
		return lastContainerCopy;
	}

	public void setBankTabs(Item[][] container) {
		this.bankTabs = container;
	}

	public void refreshItems() {
		refreshItems(generateContainer(), getContainerCopy());
		calculateBankSize();
		refreshBankSize();
	}

	public void refreshItems(Item[] itemsAfter, Item[] itemsBefore) {
		if (itemsBefore.length != itemsAfter.length) {
			lastContainerCopy = itemsAfter;
			sendItems();
			return;
		}
		int[] changedSlots = new int[itemsAfter.length];
		int count = 0;
		for (int index = 0; index < itemsAfter.length; index++) {
			if (itemsBefore[index] != itemsAfter[index])
				changedSlots[count++] = index;
		}
		int[] finalChangedSlots = new int[count];
		System.arraycopy(changedSlots, 0, finalChangedSlots, 0, count);
		lastContainerCopy = itemsAfter;
		refreshItems(finalChangedSlots);
		calculateBankSize();
		refreshBankSize();
	}

	public int getBankSize() {
		int size = 0;
		for (int i = 0; i < bankTabs.length; i++) {
			size += bankTabs[i].length;
		}
		return size;
		// return freeSize + memberSize;
	}

	public Item[] generateContainer() {
		Item[] container = new Item[getBankSize()];
		if (getBankSize() == 0) {
			bankTabs = new Item[1][0];
			return new Item[] {};
		}
		int count = 0;
		for (int slot = 1; slot < bankTabs.length; slot++) {
			System.arraycopy(bankTabs[slot], 0, container, count, bankTabs[slot].length);
			count += bankTabs[slot].length;
		}
		System.arraycopy(bankTabs[0], 0, container, count, bankTabs[0].length);
		return container;
	}

	public void unlockButtons() {
		player.getPackets().sendIComponentSettings(762, 95, 0, getMaxBankSpace(), 2622718);
		player.getPackets().sendIComponentSettings(763, 0, 0, 27, 2425982);
	}

	public boolean getWithdrawNotes() {
		return withdrawNotes;
	}

	public void switchWithdrawNotes() {
		withdrawNotes = !withdrawNotes;
		player.getPackets().sendConfig(115, withdrawNotes ? 1 : 0);
	}

	public void switchPlaceholders() {
		placeholders = !placeholders;
		player.getPackets().sendGameMessage("Placeholders are now " + (placeholders ? "enabled." : "disabled."));
		if (!placeholders) {
			removePlaceholders();
		}
	}

	public void switchInsertItems() {
		insertItems = !insertItems;
		player.getPackets().sendConfig(305, insertItems ? 1 : 0);
	}

	public void setCurrentTab(int currentTab) {
		if (currentTab >= bankTabs.length)
			return;
		this.currentTab = currentTab;
	}

	public int getLastX() {
		return lastX;
	}

	public void setLastX(int lastX) {
		this.lastX = lastX;
	}

	public long getActivatedTime() {
		return activatedTime;
	}

	public void setActivatedTime(long activatedTime) {
		this.activatedTime = activatedTime;
	}

	public long getLockPunishment() {
		return lockPunishment;
	}

	public void setLockPunishment(long lockPunishment) {
		this.lockPunishment = lockPunishment;
	}
}
