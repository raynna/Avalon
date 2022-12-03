package com.rs.game.area;

import java.util.LinkedList;
import java.util.List;

import com.rs.game.player.Player;

public abstract class Area {

	private transient List<Player> players = new LinkedList<Player>();

	public abstract Area update();

	public abstract String name();

	public abstract Shape[] shapes();

	public abstract boolean member();

	public abstract Environment environment();

	public List<Player> players() {
		return players;
	}

	public enum Environment {
		NORMAL, DESERT, GODWARS;
	}

}