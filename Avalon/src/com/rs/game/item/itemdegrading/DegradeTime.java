package com.rs.game.item.itemdegrading;

import java.util.concurrent.TimeUnit;

public class DegradeTime {

	private long time;
	private TimeUnit timeUnit;

	DegradeTime(TimeUnit timeUnit, long time) {
		this.timeUnit = timeUnit;
		this.time = time;
	}

	public TimeUnit getUnit() {
		return timeUnit;
	}

	public long getTotalTime() {
		return this.getUnit().toSeconds(time);
	}

	public int getTicks() {
		return (int) (this.getUnit().toSeconds(time) / 0.6);
	}
}