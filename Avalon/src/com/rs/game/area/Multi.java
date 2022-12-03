package com.rs.game.area;

import com.rs.game.WorldTile;
import com.rs.game.area.shapes.Polygon;

public final class Multi extends Area {

	@Override
	public Area update() {
		return this;
	}

	@Override
	public String name() {
		return "Multi Area";
	}

	@Override
	public Shape[] shapes() {
		return new Shape[] { new Polygon(new WorldTile[] { new WorldTile(3136, 3523, 0), new WorldTile(3136, 3608, 0),
				new WorldTile(3008, 3608, 0), new WorldTile(3008, 3711, 0), new WorldTile(3077, 3711, 0),
				new WorldTile(3149, 3711, 0), new WorldTile(3149, 3856, 0), new WorldTile(3008, 3856, 0),
				new WorldTile(3008, 3903, 0), new WorldTile(3053, 3903, 0), new WorldTile(3057, 3899, 0),
				new WorldTile(3074, 3899, 0), new WorldTile(3078, 3903, 0), new WorldTile(3200, 3903, 0),
				new WorldTile(3200, 3969, 0), new WorldTile(3398, 3969, 0), new WorldTile(3398, 3839, 0),
				new WorldTile(3327, 3839, 0), new WorldTile(3327, 3523, 0), new WorldTile(3136, 5323, 0) }) };
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
