package com.rs.game.area;

import com.rs.game.WorldTile;
import com.rs.game.area.shapes.Polygon;

public final class Godwars extends Area {

	@Override
	public Area update() {
		return this;
	}

	@Override
	public String name() {
		return "Godwars";
	}

	@Override
	public Shape[] shapes() {
		return new Shape[] { new Polygon(new WorldTile[] { new WorldTile(2822, 5371, 0), new WorldTile(2952, 5371, 0),
				new WorldTile(2952, 5240, 0), new WorldTile(2822, 5240, 0) }) };
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
