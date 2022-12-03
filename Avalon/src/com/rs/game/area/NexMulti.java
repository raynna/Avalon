package com.rs.game.area;

import com.rs.game.WorldTile;
import com.rs.game.area.shapes.Polygon;

public final class NexMulti extends Area {

	@Override
	public Area update() {
		return this;
	}

	@Override
	public String name() {
		return "Nex Multi";
	}

	@Override
	public Shape[] shapes() {
		return new Shape[] { new Polygon(new WorldTile[] { new WorldTile(2911, 5218, 0), new WorldTile(2939, 5218, 0),
				new WorldTile(2939, 5189, 0), new WorldTile(2911, 5189, 0) }) };
	}

	@Override
	public boolean member() {
		return false;
	}

	@Override
	public Environment environment() {
		return Environment.GODWARS;
	}

}
