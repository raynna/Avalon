package com.rs.game.timer;

import java.util.concurrent.TimeUnit;

/**
 * Created by Bart on 8/12/2015.
 */
public class TimerRepository {
	
	private Indexer<Timer> timers = new Indexer<>(TimerKey.cachedValues.length + 1);

	public boolean has(TimerKey key) {
		Timer timer = timers.get(key.ordinal());
		return timer != null && timer.ticks() > 0;
	}
	
	public void register(Timer timer) {
		timers.set(timer.key().ordinal(), timer);
	}
	
	public int left(TimerKey key) {
		Timer timer = timers.get(key.ordinal());
		return timer == null ? 0 : timer.ticks();
	}
	
	public String asHoursAndMinutesLeft(TimerKey key) {
		long ms = left(key) * 600;
		int hours = (int) TimeUnit.MILLISECONDS.toHours(ms);
		int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(ms);
		String str = "";
		
		if (hours > 0) {
			if (hours > 1) {
				str = hours + " hours";
			} else {
				str = "one hour";
			}
			
			if (minutes > 0) {
				str += " and ";
			}
		}
		
		if (minutes == 1) {
			str += "one minute";
		} else if (minutes > 0) {
			str += minutes + " minutes";
		}
		
		return str;
	}
	
	public String asMinutesAndSecondsLeft(TimerKey key) {
		long ms = left(key) * 600;
		int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(ms);
		int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(ms) % 60;
		String str = "";
		
		if (minutes > 0) {
			if (minutes > 1) {
				str = minutes + " minutes";
			} else {
				str = "one minute";
			}
			
			if (seconds > 0) {
				str += " and ";
			}
		}
		
		if (seconds == 1) {
			str += "one second";
		} else if (seconds > 0) {
			str += seconds + " seconds";
		}
		
		return str;
	}

	public String asMinutesLeft(TimerKey key) {
		long ms = left(key) * 600;
		int minutes = (int) TimeUnit.MILLISECONDS.toMinutes(ms);
		String str = "";

		if (minutes > 0) {
			if (minutes > 1) {
				str = minutes + " mins";
			} else {
				str = "One min";
			}
		}

		return str;
	}

	public String asSeconds(TimerKey key) {
		long ms = left(key) * 600;
		int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(ms);
		String str = "";

		if (seconds > 0) {
			if (seconds > 1) {
				str = seconds + " secs";
			} else {
				str = "One sec";
			}
		}

		return str;
	}
	
	public void register(TimerKey key, int ticks) {
		timers.set(key.ordinal(), new Timer(key, ticks));
		System.out.println(key.name() + " has been registered for " + ticks + " ticks.");
	}
	
	/**
	 * Extend up to (if exists) the given ticks, or register new
	 */
	public void extendOrRegister(TimerKey key, int ticks) {
		Timer t = timers.get(key.ordinal());
		if (t == null) {
			t = new Timer(key, ticks);
		} else if (t.ticks() < ticks) {
			t.ticks(ticks);
		}
		timers.set(key.ordinal(), t);
	}
	
	/**
	 * Register if non-existant, or extend.
	 */
	public void addOrSet(TimerKey key, int ticks) {
		Timer t = timers.get(key.ordinal());
		if (t == null) {
			t = new Timer(key, ticks);
		} else {
			t.ticks(t.ticks() + ticks);
		}
		timers.set(key.ordinal(), t);
	}
	
	public void cancel(TimerKey name) {
		timers.set(name.ordinal(), null);
		System.out.println(name.name() + " has been canceled.");
	}
	
	public void cycle() {
		if (!timers.isEmpty()) {
			for (Timer entry : timers) {
				if (entry != null) {
					entry.tick();
				}
			}
		}
	}
	
	public Indexer<Timer> timers() {
		return timers;
	}
	
}