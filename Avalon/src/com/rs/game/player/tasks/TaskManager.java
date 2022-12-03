package com.rs.game.player.tasks;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.rs.game.player.Player;
import com.rs.utils.Logger;

public class TaskManager {

	private ArrayList<Tasks> tasks = new ArrayList<Tasks>(
			com.rs.game.player.content.tasksystem.TaskManager.Tasks.values().length);

	public TaskManager(Player player) {

	}

	public void startTask(Object task, Object[] parameters) {
		if (task == null) {
			return;
		}
		Task M = Tasks.getTask(task);
		tasks.set(M.taskID, (Tasks) task);
	}

	public void init() {
		refreshTasks();
	}

	public void refreshTasks() {
		// TODO Auto-generated method stub
	}

	public ArrayList<Tasks> getTasks() {
		return tasks;
	}

	public void setTask(ArrayList<Tasks> tasks) {
		this.tasks = tasks;
	}

	public static enum Progress {

		STARTED,

		PROGRESS,

		COMPLETED;

	}

	public static class Tasks {

		private static final Map<Object, Class<Task>> tasks = new HashMap<Object, Class<Task>>(1);

		public static Task getTask(Object task) {
			if (task instanceof Task) {
				return (Task) task;
			}
			Class<Task> taskClass = tasks.get(task);
			if (taskClass == null) {
				return null;
			}
			try {
				return taskClass.newInstance();
			} catch (Throwable e) {
				Logger.handle(e);
				;
			}
			return null;
		}

		static {

		}

	}

}
