package com.rs.game.player.content.pos;

import com.rs.game.item.Item;
import com.rs.game.player.Player;
import com.rs.utils.Utils;

public class PoSOffer extends Item {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2065088539554138377L;
	
	public int COINS = 995;

	public PoSOffer(int id, int amount, int price) {
		super(id, amount);
		this.setPrice(price);
		this.setOwner(owner);
	}

	private transient Player owner;
	private transient int slot;

	private int price;
	private int totalPriceSoFar;
	private int totalAmmountSoFar;
	private boolean canceled;
	private boolean buying;
	private String username;
	@SuppressWarnings("unused")
	private long data;

	public void link(int slot, Player owner) {
		this.slot = slot;
		this.setOwner(owner);
		this.data = Utils.currentTimeMillis();
	}

	public void unlink() {
		setOwner(null);
	}

	public void update() {
		if (getOwner() == null)
			return;
		if (getOwner().getSession() != null) {
			sendItems();
		}
	}

	public void sendItems() {
		if (getOwner() == null)
			return;
	}

	public int getPrice() {
		return price;
	}
	
	public void setPrice(int price) {
		this.price = price;
	}

	public boolean forceRemove() {
		return isCompleted();
	}

	protected boolean isCompleted() {
		return canceled || totalAmmountSoFar >= getAmount();
	}

	public int getTotalAmmountSoFar() {
		return totalAmmountSoFar;
	}

	public int getTotalPriceSoFar() {
		return totalPriceSoFar;
	}

	public int getSlot() {
		return slot;
	}

	public boolean isBuying() {
		return buying;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
