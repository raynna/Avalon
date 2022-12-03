package com.rs.game;

public class Rectangle {

	/**
	 * X of rect.
	 */
	private int x;
	/**
	 * Y of rect.
	 */
	private int y;
	/**
	 * Size X of rect.
	 */
	private int sizeX;
	/**
	 * Size Y of rect.
	 */
	private int sizeY;

	public Rectangle(int x, int y, int sizeX, int sizeY) {
		this.x = x;
		this.y = y;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getSizeX() {
		return sizeX;
	}

	public int getSizeY() {
		return sizeY;
	}

}
