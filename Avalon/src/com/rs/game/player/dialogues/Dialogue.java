package com.rs.game.player.dialogues;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.cache.loaders.NPCDefinitions;
import com.rs.game.player.Player;
/*
 * Tweaks, few improvements and bug fixes @NoContinueDialogues
 * Credits: Tristam - Project Rain
 */

public abstract class Dialogue {

	protected transient Player player;
	protected byte stage = -1;
	protected int stageInt = -1;
	protected final int END = -1;
	protected String stageName;

	public Dialogue() {

	}
	
	public int getNPCID() {
		return -1;
	}
	

	public Object[] parameters;

	public void setPlayer(Player player) {
		this.player = player;
	}

	public abstract void start();

	public abstract void run(int interfaceId, int componentId);

	public abstract void finish();

	protected final void end() {
		player.getDialogueManager().finishDialogue();
	}

	protected static final short SEND_1_TEXT_INFO = 210;
	protected static final short SEND_2_TEXT_INFO = 211;
	protected static final short SEND_3_TEXT_INFO = 212;
	protected static final short SEND_4_TEXT_INFO = 213;
	protected static final String DEFAULT = "Select an Option";
	protected static final String SEND_DEFAULT_OPTIONS_TITLE = "Select an Option", TITLE = "Select An Option";
	protected static final short SEND_2_OPTIONS = 236;
	protected static final short SEND_3_OPTIONS = 230;
	protected static final short SEND_4_OPTIONS = 237;
	protected static final short SEND_5_OPTIONS = 238;
	protected static final short SEND_2_LARGE_OPTIONS = 229;
	protected static final short SEND_3_LARGE_OPTIONS = 231;
	protected static final short SEND_1_TEXT_CHAT = 241;
	protected static final short SEND_2_TEXT_CHAT = 242;
	protected static final short SEND_3_TEXT_CHAT = 243;
	protected static final short SEND_4_TEXT_CHAT = 244;
	protected static final short SEND_NO_CONTINUE_1_TEXT_CHAT = 245;
	protected static final short SEND_NO_CONTINUE_2_TEXT_CHAT = 246;
	protected static final short SEND_NO_CONTINUE_3_TEXT_CHAT = 247;
	protected static final short SEND_NO_CONTINUE_4_TEXT_CHAT = 248;
	protected static final short SEND_NO_EMOTE = -1;
	protected final static short SEND_ITEM_DIALOGUE = 1189;
	protected static final byte IS_NOTHING = -1;
	protected static final byte IS_PLAYER = 0;
	protected static final byte IS_NPC = 1;
	protected static final byte IS_ITEM = 2;
	protected static final byte IS_ITEM_WITH_SIZE = 3;
	protected static final byte DOUBLE_ITEM_SELECTION = 4;
	public static final int HAPPY_FACE = 9843;
	public static final int ASKING_FACE = 9829;
	public static final int BLANK_FACE = 9772;
	public static final int SAD_FACE = 9768;
	public static final int UPSET_FACE = 9776;
	public static final int SCARED_FACE = 9780;
	public static final int MILDLY_ANGRY_FACE = 9784;
	public static final int ANGRY_FACE = 9788;
	public static final int VERY_ANGRY_FACE = 9792;
	public static final int MANIAC_FACE = 9800;
	public static final int NOT_TALKING_JUST_LISTENING_FACE = 9804;
	public static final int PLAIN_TALKING_FACE = 9808;
	public static final int WTF_FACE = 9820;
	public static final int SHAKING_NO_FACE = 9824;
	public static final int UNSURE_FACE = 9836;
	public static final int LISTENS_THEN_LAUGHS_FACE = 9840;
	public static final int GOOFY_LAUGH_FACE = 9851;
	public static final int THINKING_THEN_TALKING_FACE = 9859;
	public static final int NONONO_FACE = 9844;
	public static final int SAD_WTF_FACE = 9776;
	protected static final int REALLY_SAD = 9760, 
			DEPRESSED = 9770, 
			MEAN_FACE = 9785, 
			MEAN_HEAD_BANG = 9790, 
			EVIL = 9795, 
			WHAT_THE_CRAP = 9800, 
			CALM = 9805, 
			CALM_TALK = 9810, 
			TOUGH = 9815, 
			SNOBBY = 9820, 
			SNOBBY_HEAD_MOVE = 9825, 
			DRUNK_HAPPY_TIRED = 9835, 
			TALKING_ALOT = 9845, 
			HAPPY = 9850, 
			BAD_ASS = 9855, 
			THINKING = 9860, 
			COOL_YES = 9864, 
			LAUGH_EXCITED = 9851, 
			NORMAL = 9827,
			WORRIED = 9775, 
			CONFUSED = 9830,
			DRUNK = 9835, 
			MAD = 9785, 
			ANGERY = 9790, 
			SAD = 9775, 
			SCARED = 9780,
			SECRELTY_TALKING = 9838,
			ASKING = 9829,
			BLANK = 9772,
			UPSET = 9776,
			MILDLY_ANGRY = 9784,
			ANGRY = 9784,
			VERY_ANGRY = 9792,
			MANIAC = 9800,
			NOT_TALKING_JUST_LISTENING = 9804,
			PLAIN_TALKING = 9808,
			UNSURE = 9836,
			LISTENS_THEN_LAUGHS = 9840,
			GOOFY_LAUGH = 9851,
			THINKING_THEN_TALKING = 9859,
			NONONO = 9844,
			SAD_SHOCKED = 9776;

	protected final int Listening = 9804, Plain = 9808, Snobby = 9832, Unsure = 9836, ListenAndLaugh = 9840,
			Swaying = 9844, Normal = 9847, Laughing = 9851, Sad = 9760, Crying = 9765, Why = 9776, Angry = 9788,
			Furious = 9792, Thinking = 9827, Happy = 9850;

	private static int[] getIComponentsIds(short interId) {
		int[] childOptions;
		switch (interId) {
		case SEND_1_TEXT_INFO:
			childOptions = new int[1];
			childOptions[0] = 1;
			break;
		case SEND_ITEM_DIALOGUE:
			childOptions = new int[2];
			childOptions[0] = 5;
			childOptions[1] = 4;
			break;
		case SEND_2_TEXT_INFO:
			childOptions = new int[2];
			childOptions[0] = 1;
			childOptions[1] = 2;
			break;
		case SEND_3_TEXT_INFO:
			childOptions = new int[3];
			childOptions[0] = 1;
			childOptions[1] = 2;
			childOptions[2] = 3;
			break;
		case SEND_4_TEXT_INFO:
			childOptions = new int[4];
			childOptions[0] = 1;
			childOptions[1] = 2;
			childOptions[2] = 3;
			childOptions[3] = 4;
			break;
		case SEND_2_LARGE_OPTIONS:
			childOptions = new int[3];
			childOptions[0] = 1;
			childOptions[1] = 2;
			childOptions[2] = 3;
			break;
		case SEND_3_LARGE_OPTIONS:
			childOptions = new int[4];
			childOptions[0] = 1;
			childOptions[1] = 2;
			childOptions[2] = 3;
			childOptions[3] = 4;
			break;
		case SEND_2_OPTIONS:
			childOptions = new int[3];
			childOptions[0] = 0;
			childOptions[1] = 1;
			childOptions[2] = 2;
			break;
		case SEND_3_OPTIONS:
			childOptions = new int[4];
			childOptions[0] = 1;
			childOptions[1] = 2;
			childOptions[2] = 3;
			childOptions[3] = 4;
			break;
		case SEND_4_OPTIONS:
			childOptions = new int[5];
			childOptions[0] = 0;
			childOptions[1] = 1;
			childOptions[2] = 2;
			childOptions[3] = 3;
			childOptions[4] = 4;
			break;
		case SEND_5_OPTIONS:
			childOptions = new int[6];
			childOptions[0] = 0;
			childOptions[1] = 1;
			childOptions[2] = 2;
			childOptions[3] = 3;
			childOptions[4] = 4;
			childOptions[5] = 5;
			break;
		case SEND_1_TEXT_CHAT:
		case SEND_NO_CONTINUE_1_TEXT_CHAT:
			childOptions = new int[2];
			childOptions[0] = 3;
			childOptions[1] = 4;
			break;
		case SEND_2_TEXT_CHAT:
		case SEND_NO_CONTINUE_2_TEXT_CHAT:
			childOptions = new int[3];
			childOptions[0] = 3;
			childOptions[1] = 4;
			childOptions[2] = 5;
			break;
		case SEND_3_TEXT_CHAT:
		case SEND_NO_CONTINUE_3_TEXT_CHAT:
			childOptions = new int[4];
			childOptions[0] = 3;
			childOptions[1] = 4;
			childOptions[2] = 5;
			childOptions[3] = 6;
			break;
		case SEND_4_TEXT_CHAT:
		case SEND_NO_CONTINUE_4_TEXT_CHAT:
			childOptions = new int[5];
			childOptions[0] = 3;
			childOptions[1] = 4;
			childOptions[2] = 5;
			childOptions[3] = 6;
			childOptions[4] = 7;
			break;
		default:
			return null;
		}
		return childOptions;
	}

	public boolean sendNPCDialogue(int npcId, int animationId, String... text) {
		return sendEntityDialogue(IS_NPC, npcId, animationId, text);
	}

	public boolean sendPlayerDialogue(int animationId, String... text) {
		return sendEntityDialogue(IS_PLAYER, -1, animationId, text);
	}

	public boolean sendEntityDialogue(int type, int entityId, int animationId, String... text) {
		String title = "";
		if (type == IS_PLAYER) {
			title = player.getDisplayName();
		} else if (type == IS_NPC) {
			title = NPCDefinitions.getNPCDefinitions(entityId).name;
		} else if (type == IS_ITEM)
			title = ItemDefinitions.getItemDefinitions(entityId).getName();
		return sendEntityDialogue(type, title, entityId, animationId, text);
	}

	public int getP() {
		return 1;
	}

	public static final int OPTION_1 = 11, OPTION_2 = 13, OPTION_3 = 14, OPTION_4 = 15, OPTION_5 = 16;


	public boolean sendOptionsDialogue(String title, String... options) {
		int i = 0;
		player.getInterfaceManager().sendChatBoxInterface(1188);
		Object params[] = new Object[options.length + 1];
		params[i++] = Integer.valueOf(options.length);
		List<String> optionsList = Arrays.asList(options);
		Collections.reverse(optionsList);
		for (Iterator<String> iterator = optionsList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			params[i++] = string;
		}

		player.getPackets().sendIComponentText(1188, 20, title);
		player.getPackets().sendRunScript(5589, params);
		return true;
	}

	public boolean sendOptions(String title, String... options) {
		int i = 0;
		player.getInterfaceManager().sendChatBoxInterface(1188);
		Object params[] = new Object[options.length + 1];
		params[i++] = Integer.valueOf(options.length);
		List<String> optionsList = Arrays.asList(options);
		Collections.reverse(optionsList);
		for (Iterator<String> iterator = optionsList.iterator(); iterator.hasNext();) {
			String string = (String) iterator.next();
			params[i++] = string;
		}

		player.getPackets().sendIComponentText(1188, 20, title);
		player.getPackets().sendRunScript(5589, params);
		return true;
	}


	public static boolean sendNPCDialogueNoContinue(Player player, int npcId, int animationId, String... text) {
		return sendEntityDialogueNoContinue(player, IS_NPC, npcId, animationId, text);
	}

	public static boolean sendPlayerDialogueNoContinue(Player player, int animationId, String... text) {
		return sendEntityDialogueNoContinue(player, IS_PLAYER, -1, animationId, text);
	}

	public static boolean sendEntityDialogueNoContinue(Player player, int type, int entityId, int animationId,
			String... text) {
		String title = "";
		if (type == IS_PLAYER) {
			title = player.getDisplayName();
		} else if (type == IS_NPC) {
			title = NPCDefinitions.getNPCDefinitions(entityId).name;
		} else if (type == IS_ITEM)
			title = ItemDefinitions.getItemDefinitions(entityId).getName();
		return sendEntityDialogueNoContinue(player, type, title, entityId, animationId, text);
	}

	public static boolean sendEntityDialogueNoContinue(Player player, int type, String title, int entityId,
			int animationId, String... texts) {
		StringBuilder builder = new StringBuilder();
		for (int line = 0; line < texts.length; line++)
			builder.append(" " + texts[line]);
		String text = builder.toString();
		if (type == IS_ITEM) {
			player.getInterfaceManager().sendChatBoxInterface(1190);
			player.getPackets().sendItemOnIComponent(1190, 1, entityId, animationId);
			player.getPackets().sendIComponentText(1190, 4, text);
		} else {
			player.getInterfaceManager().sendChatBoxInterface(1192);
			player.getPackets().sendIComponentText(1192, 16, title);
			player.getPackets().sendIComponentText(1192, 12, text);
			player.getPackets().sendEntityOnIComponent(type == IS_PLAYER, entityId, 1192, 11);
			if (animationId != -1)
				player.getPackets().sendIComponentAnimation(animationId, 1192, 11);
		}
		return true;
	}

	public static void closeNoContinueDialogue(Player player) {
		player.getInterfaceManager().closeReplacedRealChatBoxInterface();
	}

	public boolean sendItemDialogue(int itemId, int amount, String... text) {
		return sendEntityDialogue(IS_ITEM, itemId, amount, text);
	}

	public static boolean sendItemDialogueNoContinue(Player player, int itemId, int amount, String... text) {
		return sendEntityDialogueNoContinue(player, IS_ITEM, itemId, amount, text);
	}


	public static boolean sendDoubleItemSelection(Player player, int[] item1, int[] item2, String option1,
			String option2) {
		return sendDoubleItemSelect(player, DOUBLE_ITEM_SELECTION, item1, item2, option1, option2);
	}

	public static boolean sendDoubleItemSelect(Player player, int type, int[] item1, int[] item2, String option1,
			String option2) {
		if (type == DOUBLE_ITEM_SELECTION) {
			player.getInterfaceManager().sendChatBoxInterface(1185);
			player.getPackets().sendItemOnIComponent(1185, 4, item1[0], item1[1]);
			player.getPackets().sendItemOnIComponent(1185, 5, item2[0], item2[1]);
			player.getPackets().sendIComponentText(1185, 20, option1);
			player.getPackets().sendIComponentText(1185, 25, option2);
		}
		return true;
	}


	public boolean sendEntityDialogue(int type, String title, int entityId, int animationId, String... texts) {
		StringBuilder builder = new StringBuilder();
		for (int line = 0; line < texts.length; line++)
			builder.append(" " + texts[line]);
		String text = builder.toString();
		if (type == IS_NPC) {
			player.getInterfaceManager().sendChatBoxInterface(1184);
			player.getPackets().sendIComponentText(1184, 17, title);
			player.getPackets().sendIComponentText(1184, 13, text);
			player.getPackets().sendNPCOnIComponent(1184, 11, entityId);
			if (animationId != -1)
				player.getPackets().sendIComponentAnimation(animationId, 1184, 11);
		} else if (type == IS_PLAYER) {
			player.getInterfaceManager().sendChatBoxInterface(1191);
			player.getPackets().sendIComponentText(1191, 8, title);
			player.getPackets().sendIComponentText(1191, 17, text);
			player.getPackets().sendPlayerOnIComponent(1191, 15);
			if (animationId != -1)
				player.getPackets().sendIComponentAnimation(animationId, 1191, 15);
		} else if (type == IS_ITEM) {
			player.getInterfaceManager().sendChatBoxInterface(1189);
			player.getPackets().sendItemOnIComponent(1189, 1, entityId, animationId);
			player.getPackets().sendIComponentText(1189, 4, text);
		}
		return true;
	}

	public boolean sendDialogue(String... texts) {
		StringBuilder builder = new StringBuilder();
		for (int line = 0; line < texts.length; line++)
			builder.append((line == 0 ? "<p=" + getP() + ">" : "<br>") + texts[line]);
		String text = builder.toString();
		player.getInterfaceManager().sendChatBoxInterface(1186);
		player.getPackets().sendIComponentText(1186, 1, text);
		return true;
	}


	public boolean sendEntityDialogue(short interId, String[] talkDefinitons, byte type, int entityId,
			int animationId) {
		if (type == IS_PLAYER || type == IS_NPC) {
			String[] texts = new String[talkDefinitons.length - 1];
			for (int i = 0; i < texts.length; i++)
				texts[i] = talkDefinitons[i + 1];
			sendEntityDialogue(type, talkDefinitons[0], entityId, animationId, texts);
			return true;
		}
		int[] componentOptions = getIComponentsIds(interId);
		if (componentOptions == null)
			return false;
		player.getInterfaceManager().sendChatBoxInterface(interId);
		if (talkDefinitons.length != componentOptions.length)
			return false;
		for (int childOptionId = 0; childOptionId < componentOptions.length; childOptionId++)
			player.getPackets().sendIComponentText(interId, componentOptions[childOptionId],
					talkDefinitons[childOptionId]);
		if (type == IS_PLAYER || type == IS_NPC) {
			player.getPackets().sendEntityOnIComponent(type == IS_PLAYER, entityId, interId, 2);
			if (animationId != -1)
				player.getPackets().sendIComponentAnimation(animationId, interId, 2);
		} else if (type == IS_ITEM) {
			if (animationId == -1)
				animationId = 1;
			player.getPackets().sendItemOnIComponent(interId, 1, entityId, animationId);
		}
		return true;
	}
	
	public void sendPlayerChat(Mood mood, String... text) {
		StringBuilder dialogue = new StringBuilder(player.getDisplayName() + "%65%");
		for (int i = 0; i < text.length; i++)
			dialogue.append(text[i] + "%65%");
		sendEntityDialogue(SEND_1_TEXT_CHAT, dialogue.toString().split("%65%"), IS_PLAYER, player.getIndex(), mood.getIndex());
	}
	
	public void sendNPCChat(Mood mood, String... text) {
		sendNPCDialogue(getNPCID(), mood.getIndex(), text);
	}
	
	public boolean sendHandedItem(int item, String... text) {
		StringBuilder builder = new StringBuilder();
		for (int line = 0; line < text.length; line++)
			builder.append((line == 0 ? "<p=" + getP() + ">" : "<br>") + text[line]);
		String texts = builder.toString();
		int amount = 1;
		player.getInterfaceManager().sendChatBoxInterface(1189);
		player.getPackets().sendIComponentText(1189, 4, texts);
		player.getPackets().sendItemOnIComponent(1189, 1, item, amount);
		return true;
	}
	
	public boolean sendItemDialogue(int item, String... text) {
		StringBuilder builder = new StringBuilder();
		for (int line = 0; line < text.length; line++)
			builder.append((line == 0 ? "<p=" + getP() + ">" : "<br>") + text[line]);
		String texts = builder.toString();
		int amount = 1;
		player.getInterfaceManager().sendChatBoxInterface(1189);
		player.getPackets().sendIComponentText(1189, 4, texts);
		player.getPackets().sendItemOnIComponent(1189, 1, item, amount);
		return true;
	}
	


}
