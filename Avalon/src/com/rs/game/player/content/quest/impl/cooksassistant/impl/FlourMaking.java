package com.rs.game.player.content.quest.impl.cooksassistant.impl;//package com.rs.game.player.content.quest.impl.cooksassistant.impl;
//
//import com.rs.game.Animation;
//import com.rs.game.item.Item;
//import com.rs.game.player.Player;
//import com.rs.game.player.content.quest.QuestState;
//import com.rs.game.player.content.quest.QuestList.Quests;
//
//public class FlourMaking {
//	
//	@SuppressWarnings("unused")
//	private static final Animation ANIMATION = new Animation(3571);
//
//	private static final Item EMPTY_POT = new Item(1931);
//
//	private static final Item FLOUR = new Item(1933);
//	
//	private static final Item EXTRA_FINE_FLOUR =new Item(15414);
//
//	private static final int CONFIG = 695;
//	
//	
//	public static final class FlourExtension {
//
//		private transient final Player player;
//
//		private static int charges;
//
//		private static int semiCharges;
//
//		
//		FlourExtension(final Player player) {
//			this.player = player;
//		}
//
//		
//		public static void extend(final Player player) {
//			if (player.getExtension(FlourExtension.class) == null) {
//				player.addExtension(FlourExtension.class, new FlourExtension(player));
//			}
//		}
//
//		
//		public final void increment(final int increment, boolean semi) {
//			if (semi) {
//				semiCharges += increment;
//			} else {
//				charges += increment;
//			}
//		}
//
//		public final void decrement(final int increment) {
//			charges -= increment;
//		}
//
//		
//		public final void fill() {
//			semiCharges += 1;
//		}
//
//	
//		public final static void emptyChute() {
//			charges += semiCharges;
//			semiCharges = 0;
//		}
//
//		public final boolean isEmpty() {
//			return charges < 1;
//		}
//
//		public int getCharges() {
//			return charges;
//		}
//
//		public static int getSemiCharges() {
//			return semiCharges;
//		}
//
//		@SuppressWarnings("static-access")
//		public void setSemiCharges(int semiCharges) {
//			this.semiCharges = semiCharges;
//		}
//
//		@SuppressWarnings("static-access")
//		public void setCharges(int charges) {
//			this.charges = charges;
//		}
//
//		
//		public Player getPlayer() {
//			return player;
//		}
//
//	}
//	
//	private static boolean empty(final Player player, final FlourExtension extension) {
//		if (extension.getCharges() < 1) {
//			return true;
//		}
//		if (!player.getInventory().contains(EMPTY_POT)) {
//			player.getPackets().sendGameMessage("I need an empty pot to hold the flour in.");
//			return true;
//		}
//		if (player.getQuestManager().get(Quests.COOKS_ASSISTANT).getStage() > 0 && player.getInventory().removeItems(EMPTY_POT)) {
//			player.getInventory().addItem(EXTRA_FINE_FLOUR);
//			extension.decrement(1);
//			player.getPackets().sendGameMessage(!extension.isEmpty()  ? "You fill a pot with flour from the bin." : "You fill a pot with the last of the flour in the bin.");
//			if (extension.getCharges() < 1) {
//				//ConfigDefinitions.getConfigDefinitions(CONFIG);
//				player.getPackets().sendConfig(CONFIG, 0);
//			}
//		}
//		if (player.getInventory().removeItems(EMPTY_POT) && player.getQuestManager().get(Quests.COOKS_ASSISTANT).getState() == QuestState.NOT_STARTED) {
//			player.getInventory().addItem(FLOUR);
//			extension.decrement(1);
//			player.getPackets().sendGameMessage(!extension.isEmpty()  ? "You fill a pot with flour from the bin." : "You fill a pot with the last of the flour in the bin.");
//			if (extension.getCharges() < 1) {
//				//ConfigDefinitions.getConfigDefinitions(CONFIG);
//				player.getPackets().sendConfig(CONFIG, 0);
//			}
//		}
//		return true;
//	}
//	
//	public static final class GrainHopper {
//		
//		
//		private static final Item GRAIN = new Item(1947);
//		
//		
//		private static final Animation ANIMATION = new Animation(3571);
//		
//
//		@SuppressWarnings("static-access")
//		public static boolean handle(Player player) {
//			FlourExtension.extend(player);
//			final FlourExtension extension = player.getExtension(FlourExtension.class);
//			if (extension.getSemiCharges() > 0) {
//				player.getPackets().sendGameMessage("There is already grain in the hopper.");
//				return true;
//			}
//			if (player.getInventory().removeItems(GRAIN)) {
//				player.setNextAnimation(ANIMATION);
//				extension.fill();
//				player.getPackets().sendGameMessage("You put the grain in the hopper.");
//			}
//			return true;
//		}
//
//	}
//	
//	public static final class CooksAssistant {
//
//		public static boolean handle(Player player) {
//			FlourExtension.extend(player);
//			final FlourExtension extension = player.getExtension(FlourExtension.class);
//			player.lock(3);
//			empty(player, extension);
//			return true;
//		}
//		
//	}
//	
//	public static final class FillPotHandler {
//
//		public static boolean handle(Player player) {
//			FlourExtension.extend(player);
//			final FlourExtension extension = player.getExtension(FlourExtension.class);
//			player.lock(3);
//			empty(player, extension);
//			return true;
//		}
//		
//	}
//}
