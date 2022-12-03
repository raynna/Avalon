package com.rs.game.player.content;

import java.io.Serializable;
import java.util.Arrays;

import com.rs.game.item.Item;
import com.rs.game.item.ItemsContainer;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;
import com.rs.game.player.actions.skills.summoning.Summoning;
import com.rs.game.player.actions.skills.summoning.Summoning.Pouch;
import com.rs.net.decoders.handlers.ButtonHandler;

public final class CustomGear implements Serializable {

	private static final long serialVersionUID = 5667753111392604622L;

	final static int MODERN = 0, ANCIENT = 1, LUNAR = 2;
	private ItemsContainer<Item> invo;
	private ItemsContainer<Item> equip;
	private double[] xp;
	private int spellbook;
	private boolean prayerBook;
	private Pouch familiar;
	private String name;
	private int icon = -1;
	private int id;
	private ItemsContainer<Item> runes;

	public CustomGear(Player player, String name) {
		save(player, name);
	}
	
	public CustomGear(Player player, String name, int icon) {
		save(player, name, icon);
	}

	private int getSpellBook(Player player) {
		int book = player.getCombatDefinitions().getSpellBook();
		return book == 192 ? MODERN : book == 193 ? ANCIENT : book == 430 ? LUNAR : MODERN;
	}

	public void printAll() {
		//System.out.println("name: " + name + ", attxp: " + xp[0] + ", spellbook: " + spellbook + ", pray: " + prayerBook
		//		+ ", familiar: " + familiar);
	}

	private void save(Player player, String name) {
		this.invo = player.getInventory().getItems().asItemContainer();
		this.equip = player.getEquipment().getItems().asItemContainer();
		this.xp = Arrays.copyOf(player.getSkills().getXp(), 7);
		this.spellbook = getSpellBook(player);
		this.prayerBook = player.getPrayer().isAncientCurses();
		this.runes = player.getRunePouch() == null ? null : player.getRunePouch().asItemContainer();
		familiar = player.getFamiliar() == null ? null : player.getFamiliar().getPouch();
		this.name = name;
	}
	
	private void save(Player player, String name, int icon) {
		this.invo = player.getInventory().getItems().asItemContainer();
		this.equip = player.getEquipment().getItems().asItemContainer();
		this.xp = Arrays.copyOf(player.getSkills().getXp(), 7);
		this.spellbook = getSpellBook(player);
		this.prayerBook = player.getPrayer().isAncientCurses();
		this.runes = player.getRunePouch() == null ? null : player.getRunePouch().asItemContainer();
		familiar = player.getFamiliar() == null ? null : player.getFamiliar().getPouch();
		this.name = name;
		this.icon = icon;
	}

	private void setEquipment(Player player) {
		for (int index = 0; index < equip.getContainerItems().length; index++) {
			player.getEquipment().getItems().set(index, equip.getContainerItems()[index]);
		}
	}

	private void setInventory(Player player) {
		for (int index = 0; index < invo.getContainerItems().length; index++) {
			player.getInventory().getItems().set(index, invo.getContainerItems()[index]);
		}
	}

	private void setItems(Player p) {
		setInventory(p);
		setEquipment(p);
	}

	private void setMisc(Player player) {
		player.getCombatDefinitions().setSpellBook(spellbook);
		player.getPrayer().setPrayerBook(prayerBook);
		player.getRunePouch().reset();
		if (player.getRunePouch() != null && player.getInventory().containsOneItem(24497)) {
		for (Item r : runes.getContainerItems()) {
			player.getRunePouch().add(r);
			}
		}
		if (player.getFamiliar() == null && familiar != null)
			Summoning.spawnFamiliar(player, familiar);
	}

	private void setStats(Player player) {
		for (int id = 0; id < xp.length; id++)
			player.getSkills().setXp(id, xp[id]);
	}

	private void refresh(Player player) {
		player.getInventory().init();
		player.getEquipment().init();
		player.getSkills().restoreSkills();
		player.setHitpoints(player.getMaxHitpoints());
		player.refreshHitPoints();
		player.getPrayer().restorePrayer(player.getSkills().getLevel(Skills.PRAYER) * 10);
		player.getInterfaceManager().closeXPDisplay();
		player.getInterfaceManager().sendXPDisplay();
		ButtonHandler.refreshEquipBonuses(player);
		player.getAppearence().generateAppearenceData();
	}

	public String getName() {
		return name;
	}
	
	public int getIcon() {
		return icon;
	}

	public void set(Player p) {
		setItems(p);
		setMisc(p);
		setStats(p);
		refresh(p);
	}

	public ItemsContainer<Item> getInventory() {
		return invo;
	}

	public ItemsContainer<Item> getEquipment() {
		return equip;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}