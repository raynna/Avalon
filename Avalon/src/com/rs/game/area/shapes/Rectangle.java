package com.rs.game.area.shapes;

import com.rs.game.WorldTile;
import com.rs.game.area.Shape;

public abstract class Rectangle extends Shape {

	public Rectangle(WorldTile northEast, WorldTile southWest) {
		areas(new WorldTile[] { northEast, southWest }).type(ShapeType.RECTANGLE);
	}

	@Override
	public boolean inside(WorldTile location) {
		if (areas()[0].getPlane() != location.getPlane() || areas()[1].getPlane() != location.getPlane())
			return false;
		if (areas()[0].getX() < location.getX() || areas()[1].getX() > location.getX())
			return false;

		if (areas()[0].getY() < location.getY() || areas()[1].getY() > location.getY())
			return false;

		return true;
	}
}