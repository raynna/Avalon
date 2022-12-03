package com.rs.game.area;

import com.rs.game.WorldTile;
import com.rs.game.area.shapes.Polygon;

public final class KingBlackDragon extends Area {

	@Override
	public Area update() {
		return this;
	}

	@Override
	public String name() {
		return "KBD Multi";
	}

	@Override
	public Shape[] shapes() {
		return new Shape[] { new Polygon(new WorldTile[] { new WorldTile(2296, 4673, 0), new WorldTile(2245, 4673, 0),
				new WorldTile(2245, 4720, 0), new WorldTile(2296, 4720, 0) }) };
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
