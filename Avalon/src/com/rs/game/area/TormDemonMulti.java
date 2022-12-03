package com.rs.game.area;

import com.rs.game.WorldTile;
import com.rs.game.area.shapes.Polygon;

public final class TormDemonMulti extends Area {

	@Override
	public Area update() {
		return this;
	}

	@Override
	public String name() {
		return "Tormdemon Multi";
	}

	@Override
	public Shape[] shapes() {
		return new Shape[] { new Polygon(new WorldTile[] { new WorldTile(2570, 5700, 0), new WorldTile(2570, 5755, 0),
				new WorldTile(2630, 5755, 0), new WorldTile(2630, 5700, 0) }) };
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
