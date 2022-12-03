package com.rs.game.area;

import com.rs.game.WorldTile;
import com.rs.game.area.shapes.Polygon;

public final class ChaosTunnelMulti extends Area {

	@Override
	public Area update() {
		return this;
	}

	@Override
	public String name() {
		return "Chaos Tunnel Multi";
	}

	@Override
	public Shape[] shapes() {
		return new Shape[] { new Polygon(new WorldTile[] { new WorldTile(3120, 5585, 0), new WorldTile(3340, 5585, 0),
				new WorldTile(3340, 5420, 0), new WorldTile(3120, 5420, 0) }) };
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
