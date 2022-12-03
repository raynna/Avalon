package com.rs.game.area;

import com.rs.game.WorldTile;
import com.rs.game.area.shapes.Polygon;

public final class ForinthryDungeonMulti extends Area {

	@Override
	public Area update() {
		return this;
	}

	@Override
	public String name() {
		return "ForinthryMulti";
	}

	@Override
	public Shape[] shapes() {
		return new Shape[] { new Polygon(new WorldTile[] { new WorldTile(3095, 10059, 0), new WorldTile(3095, 10070, 0),
				new WorldTile(3060, 10070, 0), new WorldTile(3086, 10070, 0), new WorldTile(3069, 10083, 0),
				new WorldTile(3072, 10111, 0), new WorldTile(3009, 10117, 0), new WorldTile(3008, 10134, 0),
				new WorldTile(3008, 10163, 0), new WorldTile(3044, 10163, 0), new WorldTile(3070, 10168, 0),
				new WorldTile(3084, 10176, 0), new WorldTile(3135, 10179, 0), new WorldTile(3135, 10056, 0),
				new WorldTile(3095, 10059, 0) }) };
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
