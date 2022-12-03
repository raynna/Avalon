package com.rs.game.tasks;

public abstract class WorldTask implements Runnable {

	public boolean needRemove;

	public final void stop() {
		needRemove = true;
	}
}
