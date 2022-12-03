package com.rs.game.area;

import com.rs.game.WorldTile;
import com.rs.game.area.shapes.Polygon;

public final class CorpMulti extends Area {

	@Override
	public Area update() {
		return this;
	}

	@Override
	public String name() {
		return "Corp Multi";
	}

	@Override
	public Shape[] shapes() {
		return new Shape[] { new Polygon(new WorldTile[] { new WorldTile(2970, 4400, 0), new WorldTile(2970, 4363, 0),
				new WorldTile(3004, 4363, 0), new WorldTile(3004, 4400, 0) }) };
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
