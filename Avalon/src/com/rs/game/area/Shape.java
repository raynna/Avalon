package com.rs.game.area;

import com.rs.game.WorldTile;

public abstract class Shape {

	private WorldTile[] areas;
	private ShapeType type;

	public abstract boolean inside(WorldTile location);

	public WorldTile[] areas() {
		return areas;
	}

	public Shape areas(WorldTile[] areas) {
		this.areas = areas;
		return this;
	}

	public ShapeType type() {
		return type;
	}

	public Shape type(ShapeType type) {
		this.type = type;
		return this;
	}

	public enum ShapeType {
		RECTANGLE, POLYGON;
	}

}