package com.rs.game.minigames.lividfarm;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.game.player.actions.skills.herblore.Herblore.Ingredients;
import com.rs.utils.Utils;

/**
 * @author -Andreas 21 feb. 2020 00:58:21
 * @project source
 * 
 */

public class LividStore {

	private static Wish[] wishes = Wish.values();

	public enum Wish {

		LET_IT_RAIN_SEEDS(18000) {

			@Override
			public void process(Player player) {
				int[] seeds = { 5321, 5280, 5106, 5323, 5286, 5285 };
				for (int ids : seeds) {
					World.updateGroundItem(new Item(ids, Utils.random(7, 11)), Utils.getRandomTile(player, 3), player,
							60, 0);
				}
				removeProduce(player);
				player.getDialogueManager().startDialogue("SimpleMessage",
						"Your wish has come true.. <br>It's raining seeds!");
			}
		},

		GIMMIE_HERBS(18000) {

			@Override
			public void process(Player player) {
				if (player.getInventory().getFreeSlots() < 5) {
					player.getDialogueManager().startDialogue("SimpleMessage",
							"You need at least 5 inventory spaces free!");
					return;
				}
				Item[] herbs = { new Item(214, 3), new Item(210, 3), new Item(216, 2), new Item(212, 2),
						new Item(2486) };
				for (Item item : herbs) {
					player.getInventory().addItem(item);
				}
				removeProduce(player);
				player.getDialogueManager().startDialogue("SimpleMessage",
						"Your wish has come true.. <br>You recieved some herbs.");
			}
		},

		VIAL_MY_HERBS(5500) {

			Ingredients[] ingredients = Ingredients.values();

			@Override
			public void process(Player player) {
				int i = 0;
				for (Ingredients ingredient : ingredients) {
					if (i >= 50)
						break;
					ItemDefinitions def = ItemDefinitions.getItemDefinitions(ingredient.getItemId());
					ItemDefinitions pot = ItemDefinitions.getItemDefinitions(ingredient.getRewards()[0]);
					if (player.getInventory().containsOneItem(def.getId())) {
						int amount = player.getInventory().getNumberOf(def.getId());
						if (i + amount > 50)
							amount = 50 - i;
						i += amount;
						player.getInventory().deleteItem(def.getId(), amount);
						player.getInventory().addItem(pot.getId(), amount);
					}
					if (player.getInventory().containsOneItem(def.getCertId())) {
						int amount = player.getInventory().getNumberOf(def.getCertId());
						if (i + amount > 50)
							amount = 50 - i;
						i += amount;
						player.getInventory().deleteItem(def.getCertId(), amount);
						player.getInventory().addItem(pot.getCertId(), amount);
					}
				}
				if (i == 0) {
					player.getDialogueManager().startDialogue("SimpleMessage",
							"You don't have any cleaned herbs in your inventory.");
				} else {
					removeProduce(player);
					player.getDialogueManager().startDialogue("SimpleMessage", "Your wish has come true.. <br>" + i
							+ " of your cleaned herbs got converted into unfinished potions.");
				}
			}
		},

		TURN_LUNAR_LUMBER_INTO_RUNES(5500) {

			@Override
			public void process(Player player) {
				// TODO
			}
		},

		REDUCE_THE_FISH_I_BURN(18000) {

			@Override
			public void process(Player player) {
				removeProduce(player);
				player.getTemporaryAttributes().put("LIVID_WISH_BURN", 1800000 + Utils.currentTimeMillis());
				player.getDialogueManager().startDialogue("SimpleMessage",
						"Your wish has come true.. <br>You are less likely to burn fish while cooking for 30 minutes.");
			}
		},

		MORE_PLANKS_PLEASE(18000) {

			@Override
			public void process(Player player) {
				removeProduce(player);
				player.getTemporaryAttributes().put("LIVID_WISH_PLANK", 1800000 + Utils.currentTimeMillis());
				player.getDialogueManager().startDialogue("SimpleMessage",
						"Your wish has come true.. <br>You now have a 1/10 chance to recieve an extra plank while using Plank make spell for 30 minutes.");
			}
		},

		LET_IT_RAIN_AWESOME_SEED(55000) {

			@Override
			public void process(Player player) {
				Item[] seeds = { new Item(5295, 4), new Item(5321, 4), new Item(5286, 6), new Item(5287, 2),
						new Item(5288, 2), new Item(5313, 4) };
				for (Item item : seeds) {
					World.updateGroundItem(item, Utils.getRandomTile(player, 3), player, 60, 0);
				}
				removeProduce(player);
				player.getDialogueManager().startDialogue("SimpleMessage",
						"Your wish has come true.. <br>It's raining awesome seeds!");
			}
		},

		ID_LIKE_A_NEW_PATCH(-1) {

			@Override
			public void process(Player player) {
				// TODO
			}
		},

		GIVE_ME_AN_ARCANE_CAPACITOR(18000) {

			@Override
			public void process(Player player) {
				if (!player.getInventory().hasFreeSlots()) {
					player.getDialogueManager().startDialogue("SimpleMessage", "You don't have any inventory space.");
					return;
				}
				removeProduce(player);
				player.getInventory().addItem(21513, 1);
				player.getDialogueManager().startDialogue("SimpleMessage",
						"Your wish has come true.. <br>You have been given an Arcane capacitor!");
			}
		},

		PROTECT_A_PATCH_FOR_ME(-1) {

			@Override
			public void process(Player player) {
				// TODO
			}
		};

		private int produce;

		public abstract void process(Player player);

		private Wish(int produce) {
			this.produce = produce;
		}

		public void removeProduce(Player player) {
			player.getLivid().removeProduce(produce);
		}

		public int getProduce() {
			return produce;
		}

		public static Wish getComponentById(int i) {
			int value = (i >= 525 ? ((i - 470) / 9) : (i - 335) / 9);
			for (Wish s : wishes) {
				if (i == 554 || i == 395 || i == 387)
					continue;
				if (i == 565)
					return Wish.PROTECT_A_PATCH_FOR_ME;
				if (s.ordinal() == value)
					return s;
			}
			return null;
		}
	}

	private static Spell[] spells = Spell.values();

	public enum Spell {

		SOUTH_FALADOR_TELEPORT(69840, 263),

		REPAIR_RUNE_POUCH(139760, 272),

		TELEPORT_TO_NORTH_ARDOUGNE(227120, 281),

		REMOVE_FARMING(314480, 290),

		SPIRITUALISE_FOOD(401840, 299),

		MAKE_LEATHER(489200, 308),

		DISRUPTION_SHIELD(576560, 317),

		VENGEANCE_GROUP(663920, 326),

		TELEPORT_TO_TROLLHEIM(806400, 467),

		TELEPORT_GROUP_TO_TROLLHEIM(806400, 477),

		BORROWED_POWER(850000, 487);

		private int produce;
		private int componentId;

		private Spell(int produce, int componentId) {
			this.produce = produce;
			this.componentId = componentId;
		}

		public static Spell getComponentById(int i) {
			for (Spell s : spells) {
				if (i == s.getComponent()) {
					return s;
				}
			}
			return null;
		}

		public int getComponent() {
			return componentId;
		}

		public int getProduce() {
			return produce;
		}

		public String getName() {
			return Utils.formatString(name());
		}
	}

	public static void handleButtons(Player player, int id) {
		player.getInterfaceManager().closeChatBoxInterface();
		Wish wish = Wish.getComponentById(id);
		if (wish != null) {
			makeWish(player, wish);
			return;
		}
		Spell spell = Spell.getComponentById(id);
		if (spell != null) {
			learnSpell(player, spell);
			return;
		}
	}

	public static void openInterface(Player player) {
		player.getInterfaceManager().sendInterface(1083);
	}

	public static void learnSpell(Player player, Spell spell) {
		if (spell.ordinal() > player.getLivid().getSpellAmount()) {
			player.getPackets().sendGameMessage("You must learn all the prior spells before learning this.");
			return;
		}
		if (player.getLivid().getProduce() < spell.getProduce()) {
			player.getPackets().sendGameMessage("You need at least " + Utils.getFormattedNumber(spell.getProduce(), ',')
					+ " produce to learn this spell.");
			return;
		}
		player.getInterfaceManager().closeScreenInterface();
		player.getDialogueManager().startDialogue("PaulinePolaris", 13619, spell);
	}

	public static void makeWish(Player player, Wish wish) {
		if (!player.getLivid().getSpellsLearned().contains(Spell.BORROWED_POWER))// checks if all spells unlocked or not
			return;// serversided safety check
		if (player.getLivid().getProduce() < wish.getProduce()) {
			player.getPackets().sendGameMessage("You need at least " + Utils.getFormattedNumber(wish.getProduce(), ',')
					+ " produce to wish for this.");
			return;
		}
		player.getDialogueManager().startDialogue("PaulinePolaris", 13619, wish);
	}
}
