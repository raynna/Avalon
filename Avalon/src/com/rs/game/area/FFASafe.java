package com.rs.game.area;

import com.rs.game.WorldTile;
import com.rs.game.area.shapes.Polygon;

public final class FFASafe extends Area {

	@Override
	public Area update() {
		return this;
	}

	@Override
	public String name() {
		return "FFA Safe";
	}

	@Override
	public Shape[] shapes() {
		return new Shape[] { new Polygon(new WorldTile[] { new WorldTile(2875, 5627, 0), new WorldTile(2756, 5627, 0),
				new WorldTile(2756, 5508, 0), new WorldTile(2875, 5508, 0) }) };
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
