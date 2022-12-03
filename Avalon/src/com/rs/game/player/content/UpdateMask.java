package com.rs.game.player.content;

public class UpdateMask {

	private int duration;
	private int[] colors;

	public UpdateMask(int duration, int[] colors) {
		this.setDuration(duration);
		this.colors = colors;
	}

	public UpdateMask(int i, Integer valueOf, Integer valueOf2, Integer valueOf3, Integer valueOf4) {
		this.setDuration(i);
		this.colors = new int[] { valueOf, valueOf2, valueOf3, valueOf4 };
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int[] getColors() {
		return colors;
	}

	public void setColor(int index, byte color) {
		this.colors[index] = color;
	}

}