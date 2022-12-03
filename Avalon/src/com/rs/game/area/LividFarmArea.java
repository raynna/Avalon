package com.rs.game.area;

import com.rs.game.WorldTile;
import com.rs.game.area.shapes.Polygon;

public final class LividFarmArea extends Area {

	@Override
	public Area update() {
		return this;
	}

	@Override
	public String name() {
		return "Livid Farm";
	}

	@Override
	public Shape[] shapes() {
		return new Shape[] { new Polygon(new WorldTile[] {

				new WorldTile(2107, 3939, 0),

				new WorldTile(2107, 3941, 0),

				new WorldTile(2096, 3941, 0),

				new WorldTile(2096, 3951, 0),

				new WorldTile(2107, 3951, 0),

				new WorldTile(2107, 3953, 0),

				new WorldTile(2120, 3953, 0),

				new WorldTile(2120, 3941, 0),

				new WorldTile(2115, 3941, 0),

				new WorldTile(2115, 3939, 0)}) };
	}

	@Override
	public boolean member() {
		return true;
	}

	@Override
	public Environment environment() {
		return Environment.NORMAL;
	}

}
