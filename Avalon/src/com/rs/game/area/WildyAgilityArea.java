package com.rs.game.area;

import com.rs.game.WorldTile;
import com.rs.game.area.shapes.Polygon;

public final class WildyAgilityArea extends Area {

	@Override
	public Area update() {
		return this;
	}

	@Override
	public String name() {
		return "Wildy Agility Multi";
	}

	@Override
	public Shape[] shapes() {
		return new Shape[] { new Polygon(new WorldTile[] { new WorldTile(3007, 3927, 0), new WorldTile(3007, 3912, 0),
				new WorldTile(2984, 3912, 0), new WorldTile(2984, 3927, 0) }) };
	}

	@Override
	public boolean member() {
		return false;
	}

	@Override
	public Environment environment() {
		return Environment.NORMAL;
	}

}
