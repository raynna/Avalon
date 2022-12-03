package com.rs.game.area;

import com.rs.game.WorldTile;
import com.rs.game.area.shapes.Polygon;

public final class DKSMulti extends Area {

	@Override
	public Area update() {
		return this;
	}

	@Override
	public String name() {
		return "Dks Multi";
	}

	@Override
	public Shape[] shapes() {
		return new Shape[] { new Polygon(new WorldTile[] { new WorldTile(2886, 4472, 0), new WorldTile(2945, 4472, 0),
				new WorldTile(2945, 4420, 0), new WorldTile(2886, 4420, 0) }) };
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
