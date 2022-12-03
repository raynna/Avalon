package com.rs.game.player.tasks;

import com.rs.game.player.Player;
import com.rs.game.player.tasks.TaskManager.Progress;

public abstract class Task {

	protected transient Player player;
	protected int taskID;
	protected Progress progress;

	public abstract void start();

	public abstract void finish();

}
