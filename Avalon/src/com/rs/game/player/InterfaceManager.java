package com.rs.game.player;

import java.util.concurrent.ConcurrentHashMap;

import com.rs.Settings;
import com.rs.game.player.content.clans.ClansManager;

public class InterfaceManager {

	/*
	 * Tab Ids Combat = 0 Task system = 1 Stats/kills = 2 Quest Journals = 3
	 * Inventory = 4 Worn equipment = 5 Prayer = 6 Magic spellbook = 7 SOF = 8
	 * Friend list = 9 Friend chat = 10 Clan = 11 Settings = 12 Emotes = 13
	 * Music = 14 Notes = 15
	 * 
	 */

	public static final int FIXED_WINDOW_ID = 548;
	public static final int RESIZABLE_WINDOW_ID = 746;
	public static final int CHAT_BOX_TAB = 13;
	public static final int FIXED_SCREEN_TAB_ID = 27;
	public static final int RESIZABLE_SCREEN_TAB_ID = 28;
	public static final int FIXED_INV_TAB_ID = 166;
	public static final int RESIZABLE_INV_TAB_ID = 108;
	public transient Player player;

	private final ConcurrentHashMap<Integer, int[]> openedinterfaces = new ConcurrentHashMap<Integer, int[]>();
	private final ConcurrentHashMap<Integer, Integer> openedinterfacesb = new ConcurrentHashMap<Integer, Integer>();

	public boolean resizableScreen;
	private int windowsPane;
	private int rootInterface;

	public InterfaceManager(Player player) {
		this.player = player;
	}

	public void sendTab(int tabId, int interfaceId) {
		player.getPackets().sendInterface(true, isResizableScreen() ? RESIZABLE_WINDOW_ID : FIXED_WINDOW_ID, tabId,
				interfaceId);
	}

	public void sendChatBoxInterface(int interfaceId) {
		player.getPackets().sendInterface(true, 752, CHAT_BOX_TAB, interfaceId);
	}

	public void closeChatBoxInterface() {
		player.getPackets().closeInterface(CHAT_BOX_TAB);
	}

	public void sendHealth() {
		player.getInterfaceManager().sendOverlay(3001, false);
	}
	
	public void setDefaultRootInterface() {
		setRootInterface(resizableScreen ? 746 : 548);
	}
	
	public void closeHealth(boolean fullScreen) {
		player.getInterfaceManager().closeOverlay(false);
	}
	
	public void sendOverlay(int interfaceId, boolean fullScreen, int x) {
		player.getPackets().sendInterface(true, isResizableScreen() ? RESIZABLE_WINDOW_ID : FIXED_WINDOW_ID, 
				x, interfaceId);
	}

	public void sendOverlay(int interfaceId, boolean fullScreen) {
		sendTab(isResizableScreen() ? fullScreen ? 1 : 11 : 0, interfaceId);
	}

	public void closeOverlay(boolean fullScreen) {
		player.getPackets().closeInterface(isResizableScreen() ? fullScreen ? 1 : 11 : 0);
	}
	
	public void closeTab(boolean fullScreen, int tab) {
		player.getPackets().closeInterface(isResizableScreen() ? fullScreen ? 1 : 11 : tab);
	}
	
	public void closeTab(int tab) {
		player.getPackets().closeInterface(tab);
	}

	public void sendQuestTab() {
		sendTab(isResizableScreen() ? 114 : 174, 3002);
	}
	
	public void sendDungTab() {
		sendTab(isResizableScreen() ? 114 : 174, 939);
	}

	public void sendInterface(int interfaceId) {
		player.getPackets().sendInterface(false, isResizableScreen() ? RESIZABLE_WINDOW_ID : FIXED_WINDOW_ID,
				isResizableScreen() ? RESIZABLE_SCREEN_TAB_ID : FIXED_SCREEN_TAB_ID, interfaceId);
	}

	public void sendInventoryInterface(int childId) {
		player.getPackets().sendInterface(false, isResizableScreen() ? RESIZABLE_WINDOW_ID : FIXED_WINDOW_ID,
				isResizableScreen() ? RESIZABLE_INV_TAB_ID : FIXED_INV_TAB_ID, childId);
	}

	public void sendTimerInterface(Player player) {
		player.getInterfaceManager().sendOverlay(3000, false);
		player.getPackets().sendIComponentText(3000, 5, "");
		player.getPackets().sendIComponentText(3000, 6, "");
		player.getPackets().sendIComponentText(3000, 7, "");
		player.getPackets().sendIComponentText(3000, 8, "");
		player.getPackets().sendIComponentText(3000, 9, "");
	}

	public final void sendInterfaces() {
		if (player.getDisplayMode() == 2 || player.getDisplayMode() == 3) {
			setResizableScreen(true);
			sendFullScreenInterfaces();
		} else {
			setResizableScreen(false);
			sendFixedInterfaces();
		}
		//player.getSkills().sendInterfaces();
		player.getSkills().resetXPDisplay();
		player.getCombatDefinitions().sendUnlockAttackStylesButtons();
		player.getMusicsManager().unlockMusicPlayer();
		player.getEmotesManager().unlockEmotesBook();
		player.getInventory().unlockInventoryOptions();
		player.getPrayer().unlockPrayerBookButtons();
		//player.getInterfaceManager().sendTimerInterface(player);
		sendTimerInterface();
		ClansManager.unlockBanList(player);
		player.getPackets().sendIComponentText(182, 1,
				"When you finished playing " + Settings.SERVER_NAME + ", click the log out button to save your progress properly.");
		if (player.getFamiliar() != null && player.isRunning())
			player.getFamiliar().unlock();
		player.getControlerManager().sendInterfaces();
	}

	public void replaceRealChatBoxInterface(int interfaceId) {
		player.getPackets().sendInterface(true, 752, 11, interfaceId);
	}

	public void closeReplacedRealChatBoxInterface() {
		player.getPackets().closeInterface(752, 11);
	}

	public void sendWindowPane() {
		player.getPackets().sendWindowsPane(isResizableScreen() ? 746 : 548, 0);
	}

	public void sendFullScreenInterfaces() {
		player.getPackets().sendWindowsPane(746, 0);
		sendTab(21, 752);
		sendTab(22, 751);
		sendTab(15, 745);
		sendTab(25, 754);
		sendTab(195, 748);
		sendTab(196, 749);
		sendTab(197, 750);
		sendTab(198, 747);
		player.getPackets().sendInterface(true, 752, 9, 137);
		sendTab(119, 1139);
		player.getPackets().sendGlobalConfig(823, 1);
		sendCombatStyles();
		sendTaskSystem();
		sendSkills();
		sendQuestTab();
		sendInventory();
		sendEquipment();
		sendPrayerBook();
		sendMagicBook();
		sendTab(120, 550); // friend list
		sendTab(121, 1109); // 551 ignore now friendchat
		sendTab(122, 1110); // 589 old clan chat now new clan chat
		sendSettings();
		sendEmotes();
		sendTab(125, 187); // music
		sendTab(126, 34); // notes
		sendTab(129, 182); // logout*/
	}

	public void sendFixedInterfaces() {
		player.getPackets().sendWindowsPane(548, 0);
		sendTab(161, 752);
		sendTab(37, 751);
		sendTab(23, 745);
		sendTab(25, 754);
		sendTab(155, 747);
		sendTab(151, 748);
		sendTab(152, 749);
		sendTab(153, 750);
		player.getPackets().sendInterface(true, 752, 9, 137);
		player.getPackets().sendInterface(true, 548, 9, 167);
		//sendTab(player.getInterfaceManager().hasRezizableScreen() ? 11 : 0, 1252);
		sendTab(119, 1139);
		sendMagicBook();
		sendPrayerBook();
		sendEquipment();
		sendInventory();
		sendQuestTab();
		sendSof();
		sendTab(181, 1109);// 551 ignore now friendchat
		sendTab(182, 1110);// 589 old clan chat now new clan chat
		sendTab(180, 550);// friend list
		sendTab(185, 187);// music
		sendTab(186, 34); // notes
		sendTab(189, 182);
		sendSkills();
		sendEmotes();
		sendSettings();
		sendTaskSystem();
		sendCombatStyles();
		sendTimerInterface();
	}
	
	public void sendTimerInterface() {
		sendTab(player.getInterfaceManager().isResizableScreen() ? 26 : 31, 3039);
		
	}

	public void sendStaffPanel() {
		sendTab(resizableScreen ? 119 : 179, 506);
		player.getPackets().sendGlobalConfig(823, 1);
	}
	
	public void sendSof() {
		sendTab(resizableScreen ? 119 : 179, 1139);
		player.getPackets().sendGlobalConfig(823, 1);
		player.getPackets().sendHideIComponent(1139, 8, true);
		player.getPackets().sendHideIComponent(1139, 12, true);
	}

	public void sendXPPopup() {
		sendTab(isResizableScreen() ? 38 : 10, 1213); // xp
	}

	public void sendXPDisplay() {
		sendXPDisplay(1215); // xp counter
	}

	public void sendXPDisplay(int interfaceId) {
		sendTab(isResizableScreen() ? 27 : 29, interfaceId); // xp counter
	}

	public void closeXPPopup() {
		player.getPackets().closeInterface(isResizableScreen() ? 38 : 10);
	}

	public void closeXPDisplay() {
		player.getPackets().closeInterface(isResizableScreen() ? 27 : 29);
	}

	public void sendEquipment() {
		sendTab(isResizableScreen() ? 116 : 176, 387);
	}

	public void closeInterface(int one, int two) {
		player.getPackets().closeInterface(isResizableScreen() ? two : one);
	}

	public void closeEquipment() {
		player.getPackets().closeInterface(isResizableScreen() ? 116 : 176);
	}

	public void sendInventory() {
		sendTab(isResizableScreen() ? 115 : 175, Inventory.INVENTORY_INTERFACE);
	}

	public void closeInventory() {
		player.getPackets().closeInterface(isResizableScreen() ? 115 : 175);
	}

	public void closeSkills() {
		player.getPackets().closeInterface(isResizableScreen() ? 113 : 206);
	}

	public void closeCombatStyles() {
		player.getPackets().closeInterface(isResizableScreen() ? 111 : 204);
	}

	public void closeTaskSystem() {
		player.getPackets().closeInterface(isResizableScreen() ? 112 : 205);
	}

	public void sendCombatStyles() {
		sendTab(isResizableScreen() ? 111 : 171, 884);
	}

	public void sendTaskSystem() {
		sendTab(resizableScreen ? 112 : 172, 3002);
	}

	public void sendSkills() {
		sendTab(isResizableScreen() ? 113 : 173, 320);
	}

	public void sendSettings() {
		sendSettings(261);
	}

	public void sendSettings(int interfaceId) {
		sendTab(isResizableScreen() ? 123 : 183, interfaceId);
	}

	public void sendPrayerBook() {
		sendTab(isResizableScreen() ? 117 : 177, 271);
	}

	public void closePrayerBook() {
		player.getPackets().closeInterface(isResizableScreen() ? 117 : 210);
	}

	public void sendMagicBook() {
		sendTab(isResizableScreen() ? 118 : 178, player.getCombatDefinitions().getSpellBook());
		switch (player.getCombatDefinitions().getSpellBook()) {
		case 430:
			player.getPackets().sendHideIComponent(430, 24, true);
			player.getPackets().sendHideIComponent(430, 68, true);
			player.getPackets().sendHideIComponent(430, 70, true);
			player.getPackets().sendHideIComponent(430, 71, true);
			player.getPackets().sendHideIComponent(430, 77, true);
			break;
		}
	}

	public void closeMagicBook() {
		player.getPackets().closeInterface(isResizableScreen() ? 118 : 211);
	}

	public void sendEmotes() {
		sendTab(isResizableScreen() ? 124 : 184, 590);
	}

	public void closeEmotes() {
		player.getPackets().closeInterface(isResizableScreen() ? 124 : 217);
	}

	public boolean addInterface(int windowId, int tabId, int childId) {
		if (openedinterfaces.containsKey(tabId))
			player.getPackets().closeInterface(tabId);
		openedinterfaces.put(tabId, new int[] { childId, windowId });
		return openedinterfaces.get(tabId)[0] == childId;
	}

	public boolean containsInterface(int tabId, int childId) {
		if (childId == windowsPane)
			return true;
		if (!openedinterfaces.containsKey(tabId))
			return false;
		return openedinterfaces.get(tabId)[0] == childId;
	}

	public int getTabWindow(int tabId) {
		if (!openedinterfaces.containsKey(tabId))
			return FIXED_WINDOW_ID;
		return openedinterfaces.get(tabId)[1];
	}

	public boolean containsInterface(int childId) {
		if (childId == windowsPane)
			return true;
		for (int[] value : openedinterfaces.values())
			if (value[0] == childId)
				return true;
		return false;
	}

	public boolean containsTab(int tabId) {
		return openedinterfaces.containsKey(tabId);
	}

	public void removeAll() {
		openedinterfaces.clear();
	}

	public boolean containsScreenInter() {
		return containsTab(isResizableScreen() ? RESIZABLE_SCREEN_TAB_ID : FIXED_SCREEN_TAB_ID);
	}

	public void closeScreenInterface() {
		player.getPackets().closeInterface(isResizableScreen() ? RESIZABLE_SCREEN_TAB_ID : FIXED_SCREEN_TAB_ID);
	}

	public boolean containsInventoryInter() {
		return containsTab(isResizableScreen() ? RESIZABLE_INV_TAB_ID : FIXED_INV_TAB_ID);
	}

	public void closeInventoryInterface() {
		player.getPackets().closeInterface(isResizableScreen() ? RESIZABLE_INV_TAB_ID : FIXED_INV_TAB_ID);
	}

	public boolean containsChatBoxInter() {
		return containsTab(CHAT_BOX_TAB);
	}

	public boolean removeTab(int tabId) {
		return openedinterfaces.remove(tabId) != null;
	}

	public boolean removeInterface(int tabId, int childId) {
		if (!openedinterfaces.containsKey(tabId))
			return false;
		if (openedinterfaces.get(tabId)[0] != childId)
			return false;
		return openedinterfaces.remove(tabId) != null;
	}

	public void sendFadingInterface(int backgroundInterface) {
		if (hasRezizableScreen())
			player.getPackets().sendInterface(true, RESIZABLE_WINDOW_ID, 12, backgroundInterface);
		else
			player.getPackets().sendInterface(true, FIXED_WINDOW_ID, 11, backgroundInterface);
	}

	public void closeFadingInterface() {
		if (hasRezizableScreen())
			player.getPackets().closeInterface(12);
		else
			player.getPackets().closeInterface(11);
	}

	public void sendScreenInterface(int backgroundInterface, int interfaceId) {
		player.getInterfaceManager().closeScreenInterface();

		if (hasRezizableScreen()) {
			player.getPackets().sendInterface(false, RESIZABLE_WINDOW_ID, 40, backgroundInterface);
			player.getPackets().sendInterface(false, RESIZABLE_WINDOW_ID, 41, interfaceId);
		} else {
			player.getPackets().sendInterface(false, FIXED_WINDOW_ID, 200, backgroundInterface);
			player.getPackets().sendInterface(false, FIXED_WINDOW_ID, 201, interfaceId);

		}

		player.setCloseInterfacesEvent(new Runnable() {
			@Override
			public void run() {
				if (hasRezizableScreen()) {
					player.getPackets().closeInterface(40);
					player.getPackets().closeInterface(41);
				} else {
					player.getPackets().closeInterface(200);
					player.getPackets().closeInterface(201);
				}
			}
		});
	}

	public void sendTabInterfaces(boolean hidden) {
		if (hidden) {
			player.getInterfaceManager().closeCombatStyles();
			player.getInterfaceManager().closeSkills();
			player.getInterfaceManager().closeInventory();
			player.getInterfaceManager().closeEquipment();
			player.getInterfaceManager().closePrayerBook();
			player.getInterfaceManager().closeMagicBook();
			player.getInterfaceManager().closeEmotes();
		} else {
			player.getInterfaceManager().sendCombatStyles();
			player.getCombatDefinitions().sendUnlockAttackStylesButtons();
			player.getInterfaceManager().sendQuestTab();
			player.getInterfaceManager().sendSkills();
			player.getInterfaceManager().sendInventory();
			player.getInventory().unlockInventoryOptions();
			player.getInterfaceManager().sendEquipment();
			player.getInterfaceManager().sendPrayerBook();
			player.getPrayer().unlockPrayerBookButtons();
			player.getInterfaceManager().sendMagicBook();
			player.getInterfaceManager().sendEmotes();
			player.getEmotesManager().unlockEmotesBook();
			player.getInterfaceManager().sendTaskSystem();
			player.getInterfaceManager().sendQuestTab();
		}
	}

	public boolean hasRezizableScreen() {
		return isResizableScreen();
	}

	public void setWindowsPane(int windowsPane) {
		this.windowsPane = windowsPane;
	}

	public int getWindowsPane() {
		return windowsPane;
	}

	public void gazeOrbOfOculus() {
		player.getPackets().sendWindowsPane(475, 0);
		player.getPackets().sendInterface(true, 475, 57, 751);
		player.getPackets().sendInterface(true, 475, 55, 752);
		player.setCloseInterfacesEvent(new Runnable() {

			@Override
			public void run() {
				player.getPackets().sendWindowsPane(player.getInterfaceManager().hasRezizableScreen() ? 746 : 548, 0);
				player.getPackets().sendResetCamera();
			}

		});
	}

	/*
	 * returns lastGameTab
	 */
	public int openGameTab(int tabId) {
		player.getPackets().sendGlobalConfig(168, tabId);
		int lastTab = 4; // tabId
		// tab = tabId;
		return lastTab;
	}

	public boolean isResizableScreen() {
		return resizableScreen;
	}

	public void setResizableScreen(boolean resizableScreen) {
		this.resizableScreen = resizableScreen;
	}

	@SuppressWarnings("unused")
	private void clearChilds(int parentInterfaceId) {
		for (int key : openedinterfaces.keySet()) {
			if (key >> 16 == parentInterfaceId)
				openedinterfaces.remove(key);
		}
	}

	private void clearChildsB(int parentInterfaceId) {
		for (int key : openedinterfaces.keySet()) {
			if (key >> 16 == parentInterfaceId)
				openedinterfaces.remove(key);
		}
	}

	public void setInterface(boolean clickThrought, int parentInterfaceId, int parentInterfaceComponentId,
			int interfaceId) {
		int parentUID = getComponentUId(parentInterfaceId, parentInterfaceComponentId);
		Integer oldInterface = openedinterfacesb.get(parentUID);
		if (oldInterface != null)
			clearChildsB(oldInterface);
		openedinterfacesb.put(parentUID, interfaceId);
		player.getPackets().sendInterface(clickThrought, parentUID, interfaceId);
	}

	public int getComponentUId(int interfaceId, int componentId) {
		return interfaceId << 16 | componentId;
	}

	public int getRootInterface() {
		return rootInterface;
	}
	
	public void setRootInterface(int rootInterface, boolean gc) {
		this.rootInterface = rootInterface;
		player.getPackets().sendRootInterface(rootInterface, gc ? 3 : 0);
	}
	
	public void setDefaultRootInterface2() {
		setRootInterface(resizableScreen ? 746 : 548, false);
	}

	public void setRootInterface(int rootInterface) {
		this.rootInterface = rootInterface;
	}

}
