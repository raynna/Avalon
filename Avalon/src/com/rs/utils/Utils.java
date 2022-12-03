package com.rs.utils;

import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.math.BigInteger;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import com.alex.utils.Constants;
import com.rs.Settings;
import com.rs.cache.Cache;
import com.rs.game.Entity;
import com.rs.game.World;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.game.player.Player;
import com.rs.game.player.Skills;

import net.firefang.ip2c.Country;
import net.firefang.ip2c.IP2Country;

public final class Utils {

	/** The Constant ALGORITHM_LOCK. */
	private static final Object ALGORITHM_LOCK = new Object();

	/** The Constant RANDOM. */
	public static final Random RANDOM = new Random();

	/** The time correction. */
	private static long timeCorrection;

	/** The last time update. */
	private static long lastTimeUpdate;

	/**
	 * Current time millis.
	 *
	 * @return the long
	 */

	public static WorldTile getRandomTile(Player player, int radius) {
		boolean canSpawn = false;
		WorldTile tile = null;
		while (!canSpawn) {
			tile = new WorldTile(player.getX() - Utils.random(-radius, radius),
					player.getY() + Utils.random(-radius, radius), player.getPlane());
			if (!World.isTileFree(tile.getPlane(), tile.getX(), tile.getY(), 1))
				continue;
			canSpawn = true;
		}
		return tile;
	}

	public static boolean collides(Entity entity, Entity target) {
		return entity.getPlane() == target.getPlane() && colides(entity.getX(), entity.getY(), entity.getSize(),
				target.getX(), target.getY(), target.getSize());
	}

	public static List<Entity> getAroundEntities(Player conductor, WorldTile tile, int range) {
		List<Entity> entities = new ArrayList<Entity>();
		for (int regionId : conductor.getMapRegionsIds()) {
			List<Integer> npcIndexes = World.getRegion(regionId).getNPCsIndexes();
			if (npcIndexes != null)
				for (int npcIndex : npcIndexes) {
					final NPC n = World.getNPCs().get(npcIndex);
					if (n == null || Utils.getDistance(tile, n) > range)
						continue;
					entities.add(n);
				}
			List<Integer> playersIndexes = World.getRegion(regionId).getPlayerIndexes();
			if (playersIndexes != null)
				for (Integer playerIndex : playersIndexes) {
					Player p = World.getPlayers().get(playerIndex);
					if (p == null || !p.hasStarted() || p.hasFinished() || Utils.getDistance(tile, p) > range)
						continue;
					entities.add(p);
				}
		}
		return entities;
	}

	public static WorldTile getFreeTile(WorldTile center, int distance) {
		WorldTile tile = center;
		for (int i = 0; i < 10; i++) {
			tile = new WorldTile(center, distance);
			if (World.isTileFree(tile.getPlane(), tile.getX(), tile.getY(), 1))
				return tile;
		}
		return center;
	}

	public static final int[] DOOR_ROTATION_DIR_X = { -1, 0, 1, 0 };

	public static final int[] DOOR_ROTATION_DIR_Y = { 0, 1, 0, -1 };

	public static final int getAngle(int xOffset, int yOffset) {
		return ((int) (Math.atan2(-xOffset, -yOffset) * 2607.5945876176133)) & 0x3fff;
	}

	public static synchronized long currentTimeMillis() {
		long l = System.currentTimeMillis();
		if (l < lastTimeUpdate)
			timeCorrection += lastTimeUpdate - l;
		lastTimeUpdate = l;
		return l + timeCorrection;
	}

	/**
	 * Gets the formatted number.
	 *
	 * @param amount the amount
	 * @return the formatted number
	 */
	public static String getFormattedNumber(int amount) {
		DecimalFormat format = new DecimalFormat("#");
		if (amount >= 10_000_000_000L) {
			return format.format(amount / 1_000_000_000L) + "b";
		} else if (amount >= 10_000_000) {
			return format.format(amount / 1_000_000) + "m";
		} else if (amount >= 100_000) {
			return format.format(amount / 1_000) + "k";
		}
		return getFormattedNumber(amount, ',');
	}
	
	public static String format(int number) {
		return NumberFormat.getNumberInstance(Locale.UK).format(number);
	}

	/**
	 * Format amount.
	 *
	 * @param amount the amount
	 * @return the string
	 */
	public static String formatAmount(long amount) {
		DecimalFormat format = new DecimalFormat("#");
		if (amount >= 10_000_000_000L) {
			return format.format(amount / 1_000_000_000L) + "b";
		} else if (amount >= 10_000_000) {
			return format.format(amount / 1_000_000) + "m";
		} else if (amount >= 100_000) {
			return format.format(amount / 1_000) + "k";
		}
		return getFormattedNumber(amount, ',');
	}

	/**
	 * Format amount2.
	 *
	 * @param amount the amount
	 * @return the string
	 */
	public static String formatAmount2(long amount) {
		if (amount >= 10_000_000_000L) {
			return getFormattedNumber(amount / 1_000_000_000L, ',') + "b";
		} else if (amount >= 1_000_000_000) {
			return getFormattedNumber(amount / 1_000_000, ',') + "m";
		}
		return getFormattedNumber(amount, ',');
	}

	/**
	 * Format doubled amount.
	 *
	 * @param amount the amount
	 * @return the string
	 */
	public static String formatDoubledAmount(long amount) {
		String regularFormat = getFormattedNumber(amount, ',');
		String millionFormat = getFormattedNumber(amount * 0.000_001, ',') + "m";
		if (amount >= 1_000_000_000L) {
			return "(" + (new DecimalFormat(".##").format(amount * 0.000_000_001)).replace(',', '.') + "b) - "
					+ millionFormat;
		} else if (amount >= 1_000_000) {
			return "(" + (new DecimalFormat(".#").format(amount * 0.000_001)).replace(',', '.') + "m) - "
					+ regularFormat;
		} else if (amount >= 100_000) {
			return "(" + (getFormattedNumber(amount / 1_000, ',')) + "k) - " + regularFormat;
		}
		return (amount < 100_000 ? regularFormat + "" : (getFormattedNumber(amount, ',') + " - " + regularFormat));
	}

	public static String formatMillionAmount(long amount) {
		String regularFormat = getFormattedNumber(amount, ',');
		String millionFormat = getFormattedNumber(amount * 0.000_001, ',') + "m";
		if (amount >= 1_000_000_000L) {
			return millionFormat;
		} else if (amount >= 1_000_000) {
			return regularFormat;
		} else if (amount >= 100_000) {
			return regularFormat;
		}
		return (amount < 100_000 ? regularFormat + "" : (getFormattedNumber(amount, ',') + " - " + regularFormat));
	}

	/**
	 * Format doubled amount2.
	 *
	 * @param amount the amount
	 * @return the string
	 */
	public static String formatDoubledAmount2(long amount) {
		String secondFormat = getFormattedNumber(amount, ',');
		if (amount >= 10_000_000_000L) {
			return "(" + (getFormattedNumber(amount / 1_000_000_000L, ',')) + "b) - " + secondFormat;
		} else if (amount >= Integer.MAX_VALUE) {
			return "(" + (getFormattedNumber(amount / 1_000_000, ',')) + "m) - " + secondFormat;
		}
		return (amount < 1_000_000 ? secondFormat + "" : (getFormattedNumber(amount, ',') + " - " + secondFormat));
	}

	/**
	 * Format doubled amount short.
	 *
	 * @param amount the amount
	 * @return the string
	 */
	public static String formatDoubledAmountShort(long amount) {
		if (amount >= 10_000_000_000L) {
			return (getFormattedNumber(amount / 1_000_000_000L, ',')) + "b";
		} else if (amount >= Integer.MAX_VALUE) {
			return (getFormattedNumber(amount / 1_000_000, ',')) + "m";
		}
		return (getFormattedNumber(amount, ','));
	}

	/**
	 * Gets the formatted number.
	 *
	 * @param amount    the amount
	 * @param seperator the seperator
	 * @return the formatted number
	 */
	public static String getFormattedNumber(double amount, char seperator) {
		String str = new DecimalFormat("#,###,###").format(amount);
		char[] rebuff = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= '0' && c <= '9')
				rebuff[i] = c;
			else
				rebuff[i] = seperator;
		}
		return new String(rebuff);
	}

	public static String getFormattedNumber2(double amount, char seperator) {
		String str = new DecimalFormat("##").format(amount);
		char[] rebuff = new char[str.length()];
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c >= '0' && c <= '9')
				rebuff[i] = c;
			else
				rebuff[i] = seperator;
		}
		return new String(rebuff);
	}

	/**
	 * Gets the time.
	 *
	 * @param start the start
	 * @param end   the end
	 * @return the time
	 */
	public static String getTime(long start, long end) {
		if (end == -1)
			return "Permanent";
		long elapsedTimeMillis = end - start;
		int elapsedTimeMin = (int) (elapsedTimeMillis / (60 * 1000F));
		int elapsedTimeHour = (int) (elapsedTimeMillis / (60 * 60 * 1000F));
		int elapsedTimeDay = (int) (elapsedTimeMillis / (24 * 60 * 60 * 1000F));
		if (elapsedTimeDay != 0) {
			if (elapsedTimeDay == 1)
				return "24 Hours";
			else if (elapsedTimeDay == 2)
				return "48 Hours";
			return elapsedTimeDay + " Days";
		}
		if (elapsedTimeHour != 0) {
			if (elapsedTimeHour == 1)
				return "1 Hour";
			return elapsedTimeHour + " Hours";
		}
		if (elapsedTimeMin != 0) {
			if (elapsedTimeMin == 1)
				return "1 Minute";
			return elapsedTimeMin + " Minutes";
		}
		return "Error";
	}

	/**
	 * Crypt rsa.
	 *
	 * @param data     the data
	 * @param exponent the exponent
	 * @param modulus  the modulus
	 * @return the byte[]
	 */
	public static byte[] cryptRSA(byte[] data, BigInteger exponent, BigInteger modulus) {
		return new BigInteger(data).modPow(exponent, modulus).toByteArray();
	}

	/**
	 * Encrypt using m d5.
	 *
	 * @param buffer the buffer
	 * @return the byte[]
	 */
	public static final byte[] encryptUsingMD5(byte[] buffer) {
		synchronized (ALGORITHM_LOCK) {
			try {
				MessageDigest algorithm = MessageDigest.getInstance("MD5");
				algorithm.update(buffer);
				byte[] digest = algorithm.digest();
				algorithm.reset();
				return digest;
			} catch (Throwable e) {
				Logger.handle(e);
			}
			return null;
		}
	}

	/**
	 * Format string.
	 *
	 * @param text the text
	 * @return the string
	 */
	public static String formatString(String text) {
		if (text == null)
			return "";
		text = text.replaceAll("_", " ");
		text = text.toLowerCase();
		StringBuilder newText = new StringBuilder();
		boolean wasSpace = true;
		for (int i = 0; i < text.length(); i++) {
			if (wasSpace) {
				newText.append(("" + text.charAt(i)).toUpperCase());
				wasSpace = false;
			} else {
				newText.append(text.charAt(i));
			}

			if (text.charAt(i) == ' ') {
				wasSpace = true;
			}
		}
		return newText.toString();
	}

	/**
	 * Gets the calander.
	 *
	 * @return the calander
	 */
	public static Date getCalander() {
		return Calendar.getInstance().getTime();
	}

	/**
	 * Gets the month.
	 *
	 * @param month the month
	 * @return the month
	 */
	public static String getMonth(int month) {
		return new DateFormatSymbols().getMonths()[month - 1];
	}

	/**
	 * Gets the day.
	 *
	 * @author Tristam
	 * @return the day
	 */
	public static String getDay() {
		String today = "";
		String suffixes[] = { "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th", "th",
				"th", "th", "th", "th", "th", "th", "st", "nd", "rd", "th", "th", "th", "th", "th", "th", "th", "st" };
		Date date = new Date();
		SimpleDateFormat formatDayOfMonth = new SimpleDateFormat("d");
		int day = Integer.parseInt(formatDayOfMonth.format(date));
		today = day + suffixes[day];
		return today;
	}

	/**
	 * Gets the registered accounts.
	 *
	 * @author Tristam
	 * @return the registered accounts
	 */
	public static String getRegisteredAccounts() {
		Calendar now = Calendar.getInstance();
		String account = "";
		File path = new File(Settings.ECONOMY_MODE == 0 ? "data/ecocharacters" : "data/spawncharacters");
		File[] files = path.listFiles();
		int accounts = 0;
		for (int i = 0; i < files.length; i++) {
			stop: if (files[i].isFile()) {
				accounts++;
				account = "There " + (accounts > 1 ? "are " : "is ") + accounts + " "
						+ (accounts > 1 ? "accounts " : "account ") + "registered as of " + getDay() + " "
						+ getMonth(now.get(Calendar.MONTH) + 1) + ".";
				break stop;
			}
		}
		return account;
	}

	/**
	 * Grab country day time month.
	 *
	 * @param country the country
	 * @return the string
	 */
	public static String GrabCountryDayTimeMonth(boolean country) {
		Calendar now = Calendar.getInstance();
		return getDay() + " " + getMonth(now.get(Calendar.MONTH) + 1) + " " + getCurrentTime() + " "
				+ Calendar.getInstance().getTimeZone().getDisplayName();
	}

	/**
	 * Gets the formated time short.
	 *
	 * @param seconds the seconds
	 * @return the formated time short
	 */
	public static String getFormatedTimeShort(long seconds) {
		long minutes = seconds / 60;
		long hours = minutes / 60;
		long days = hours / 24;
		minutes -= hours * 60;
		seconds -= (hours * 60 * 60) + (minutes * 60);
		hours -= days * 24;
		if (days > 0)
			return days + "d:" + hours + "h:" + minutes + "m:" + seconds + "s";
		else if (hours > 0)
			return hours + "h:" + minutes + "m:" + seconds + "s";
		else if (minutes > 0)
			return minutes + "m:" + seconds + "s";
		return seconds + "s";
	}

	/**
	 * Gets the formated time short2.
	 *
	 * @param seconds the seconds
	 * @return the formated time short2
	 */
	public static String getFormatedTimeShort2(long seconds) {
		long minutes = seconds / 60;
		long hours = minutes / 60;
		long days = hours / 24;
		minutes -= hours * 60;
		seconds -= (hours * 60 * 60) + (minutes * 60);
		hours -= days * 24;
		return (days != 0 ? days + "" + ("d:") : "") + (hours != 0 ? hours + "" + ("h:") : "")
				+ (minutes != 0 ? minutes + "" + ("m:") : "") + (seconds != 0 ? seconds + "" + ("s") : "");
	}

	public static String getTimePiece(long time) {
		return "Time " + time;
	}

	/**
	 * Gets the formated time short2.
	 *
	 * @param
	 * @return the formated time short2
	 */
	public static String getFormatedTimeShort3(long milliseconds) {
		long seconds = TimeUnit.MILLISECONDS.toSeconds(currentTimeMillis() - milliseconds);
		long minutes = TimeUnit.SECONDS.toMinutes(currentTimeMillis() - seconds);
		long hours = TimeUnit.MINUTES.toHours(currentTimeMillis() - minutes);
		long days = TimeUnit.HOURS.toDays(currentTimeMillis() - hours);
		return (days != 0 ? days + "" + ("d:") : "") + (hours != 0 ? hours + "" + ("h:") : "")
				+ (minutes != 0 ? minutes + "" + ("m:") : "") + (seconds != 0 ? seconds + "" + ("s") : "");
	}

	/**
	 * Gets the formated time.
	 *
	 * @param seconds the seconds
	 * @return the formated time
	 */
	public static String getFormatedTime(long seconds) {
		long minutes = seconds / 60;
		long hours = minutes / 60;
		long days = hours / 24;
		minutes -= hours * 60;
		seconds -= (hours * 60 * 60) + (minutes * 60);
		hours -= days * 24;
		return (days != 0 ? days + " " + (days > 1 ? "days " : "day ") : "")
				+ (hours != 0 ? hours + " " + (hours > 1 ? "hours " : "hour ") : "")
				+ (minutes != 0 ? minutes + " " + (minutes > 1 ? "minutes " : "minute ") : "")
				+ (seconds != 0 ? seconds + " " + (seconds > 1 ? "seconds " : "second ") : "");
	}

	public static String getCurrentTime2() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss a");
		String time = sdf.format(date);
		return time;
	}

	/**
	 * Gets the current time.
	 *
	 * @return the current time
	 */
	public static String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String time = sdf.format(date);
		return time;
	}

	/**
	 * Gets the country name.
	 *
	 * @param IP          the ip
	 * @param CountryCode the country code
	 * @return the country name
	 */
	public static String getCountryName(String IP, boolean CountryCode) {
		String Country = null;
		try {
			int cache = IP2Country.MEMORY_CACHE;
			IP2Country ip2 = new IP2Country(cache);
			Country country = ip2.getCountry(IP);
			Country = (country == null ? "UNKNOWN"
					: country.getName() + (CountryCode ? " | " + country.get2cStr() : ""));
		} catch (IOException e) {
			System.err.print("Could not grab the country / region for " + IP);
		}
		return Country;
	}

	public static long getUpTime() {
		RuntimeMXBean mx = ManagementFactory.getRuntimeMXBean();
		DateFormat df = new SimpleDateFormat("HH mm ss");
		df.setTimeZone(TimeZone.getTimeZone("GMT+0"));
		long time = 0;
		time = mx.getUptime();
		return time;
	}

	/**
	 * Colides.
	 *
	 * @param x1    the x1
	 * @param y1    the y1
	 * @param size1 the size1
	 * @param x2    the x2
	 * @param y2    the y2
	 * @param size2 the size2
	 * @return true, if successful
	 */
	public static boolean colides(int x1, int y1, int size1, int x2, int y2, int size2) {
		int distanceX = x1 - x2;
		int distanceY = y1 - y2;
		return distanceX < size2 && distanceX > -size1 && distanceY < size2 && distanceY > -size1;
	}

	/**
	 * In circle.
	 *
	 * @param location the location
	 * @param center   the center
	 * @param radius   the radius
	 * @return true, if successful
	 */
	public static boolean inCircle(WorldTile location, WorldTile center, int radius) {
		return getDistance(center, location) <= radius;
	}

	/**
	 * Checks if is on range.
	 *
	 * @param x1          the x1
	 * @param y1          the y1
	 * @param size1       the size1
	 * @param x2          the x2
	 * @param y2          the y2
	 * @param size2       the size2
	 * @param maxDistance the max distance
	 * @return true, if is on range
	 */
	public static boolean isOnRange(int x1, int y1, int size1, int x2, int y2, int size2, int maxDistance) {
		int distanceX = x1 - x2;
		int distanceY = y1 - y2;
		if (distanceX > size2 + maxDistance || distanceX < -size1 - maxDistance || distanceY > size2 + maxDistance
				|| distanceY < -size1 - maxDistance)
			return false;
		return true;
	}

	/**
	 * Checks if is on range.
	 *
	 * @param x1    the x1
	 * @param y1    the y1
	 * @param x2    the x2
	 * @param y2    the y2
	 * @param sizeX the size x
	 * @param sizeY the size y
	 * @return true, if is on range
	 */
	/*
	 * dont use this one
	 */
	public static boolean isOnRange(int x1, int y1, int x2, int y2, int sizeX, int sizeY) {
		int distanceX = x1 - x2;
		int distanceY = y1 - y2;
		if (distanceX > sizeX || distanceX < -1 || distanceY > sizeY || distanceY < -1)
			return false;
		return true;
	}

	/**
	 * Copy file.
	 *
	 * @param sourceFile the source file
	 * @param destFile   the dest file
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void copyFile(File sourceFile, File destFile) throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;
		try {
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

	/**
	 * Gets the classes.
	 *
	 * @param packageName the package name
	 * @return the classes
	 * @throws ClassNotFoundException the class not found exception
	 * @throws IOException            Signals that an I/O exception has occurred.
	 */
	@SuppressWarnings({ "rawtypes" })
	public static Class[] getClasses(String packageName) throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		assert classLoader != null;
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile().replaceAll("%20", " ")));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes.toArray(new Class[classes.size()]);
	}

	/**
	 * Find classes.
	 *
	 * @param directory   the directory
	 * @param packageName the package name
	 * @return the list
	 */
	@SuppressWarnings("rawtypes")
	private static List<Class> findClasses(File directory, String packageName) {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				assert !file.getName().contains(".");
				classes.addAll(findClasses(file, packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) {
				try {
					classes.add(Class
							.forName(packageName + '.' + file.getName().substring(0, file.getName().length() - 6)));
				} catch (Throwable e) {

				}
			}
		}
		return classes;
	}

	/**
	 * Gets the distance.
	 *
	 * @param t1 the t1
	 * @param t2 the t2
	 * @return the distance
	 */
	public static final int getDistance(WorldTile t1, WorldTile t2) {
		return getDistance(t1.getX(), t1.getY(), t2.getX(), t2.getY());
	}

	/**
	 * Gets the distance.
	 *
	 * @param coordX1 the coord x1
	 * @param coordY1 the coord y1
	 * @param coordX2 the coord x2
	 * @param coordY2 the coord y2
	 * @return the distance
	 */
	public static final int getDistance(int coordX1, int coordY1, int coordX2, int coordY2) {
		int deltaX = coordX2 - coordX1;
		int deltaY = coordY2 - coordY1;
		return ((int) Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2)));
	}

	/** The Constant DIRECTION_DELTA_X. */
	public static final byte[] DIRECTION_DELTA_X = new byte[] { -1, 0, 1, -1, 1, -1, 0, 1 };

	/** The Constant DIRECTION_DELTA_Y. */
	public static final byte[] DIRECTION_DELTA_Y = new byte[] { 1, 1, 1, 0, 0, -1, -1, -1 };

	/**
	 * Gets the npc move direction.
	 *
	 * @param dd the dd
	 * @return the npc move direction
	 */
	public static int getNpcMoveDirection(int dd) {
		if (dd < 0)
			return -1;
		return getNpcMoveDirection(DIRECTION_DELTA_X[dd], DIRECTION_DELTA_Y[dd]);
	}

	/**
	 * Gets the npc move direction.
	 *
	 * @param dx the dx
	 * @param dy the dy
	 * @return the npc move direction
	 */
	public static int getNpcMoveDirection(int dx, int dy) {
		if (dx == 0 && dy > 0)
			return 0;
		if (dx > 0 && dy > 0)
			return 1;
		if (dx > 0 && dy == 0)
			return 2;
		if (dx > 0 && dy < 0)
			return 3;
		if (dx == 0 && dy < 0)
			return 4;
		if (dx < 0 && dy < 0)
			return 5;
		if (dx < 0 && dy == 0)
			return 6;
		if (dx < 0 && dy > 0)
			return 7;
		return -1;
	}

	/**
	 * Gets the coord offsets near.
	 *
	 * @param size the size
	 * @return the coord offsets near
	 */
	public static final int[][] getCoordOffsetsNear(int size) {
		int[] xs = new int[4 + (4 * size)];
		int[] xy = new int[xs.length];
		xs[0] = -size;
		xy[0] = 1;
		xs[1] = 1;
		xy[1] = 1;
		xs[2] = -size;
		xy[2] = -size;
		xs[3] = 1;
		xy[2] = -size;
		for (int fakeSize = size; fakeSize > 0; fakeSize--) {
			xs[(4 + ((size - fakeSize) * 4))] = -fakeSize + 1;
			xy[(4 + ((size - fakeSize) * 4))] = 1;
			xs[(4 + ((size - fakeSize) * 4)) + 1] = -size;
			xy[(4 + ((size - fakeSize) * 4)) + 1] = -fakeSize + 1;
			xs[(4 + ((size - fakeSize) * 4)) + 2] = 1;
			xy[(4 + ((size - fakeSize) * 4)) + 2] = -fakeSize + 1;
			xs[(4 + ((size - fakeSize) * 4)) + 3] = -fakeSize + 1;
			xy[(4 + ((size - fakeSize) * 4)) + 3] = -size;
		}
		return new int[][] { xs, xy };
	}

	/**
	 * Gets the face direction.
	 *
	 * @param xOffset the x offset
	 * @param yOffset the y offset
	 * @return the face direction
	 */
	public static final int getFaceDirection(int xOffset, int yOffset) {
		return ((int) (Math.atan2(-xOffset, -yOffset) * 2607.5945876176133)) & 0x3fff;
	}

	/**
	 * Gets the move direction.
	 *
	 * @param xOffset the x offset
	 * @param yOffset the y offset
	 * @return the move direction
	 */
	public static final int getMoveDirection(int xOffset, int yOffset) {
		if (xOffset < 0) {
			if (yOffset < 0)
				return 5;
			else if (yOffset > 0)
				return 0;
			else
				return 3;
		} else if (xOffset > 0) {
			if (yOffset < 0)
				return 7;
			else if (yOffset > 0)
				return 2;
			else
				return 4;
		} else {
			if (yOffset < 0)
				return 6;
			else if (yOffset > 0)
				return 1;
			else
				return -1;
		}
	}

	/**
	 * Gets the graphic definitions size.
	 *
	 * @return the graphic definitions size
	 */
	public static final int getGraphicDefinitionsSize() {
		int lastArchiveId = Cache.STORE.getIndexes()[21].getLastArchiveId();
		return lastArchiveId * 256 + Cache.STORE.getIndexes()[21].getValidFilesCount(lastArchiveId);
	}

	/**
	 * Gets the animation definitions size.
	 *
	 * @return the animation definitions size
	 */
	public static final int getAnimationDefinitionsSize() {
		int lastArchiveId = Cache.STORE.getIndexes()[20].getLastArchiveId();
		return lastArchiveId * 128 + Cache.STORE.getIndexes()[20].getValidFilesCount(lastArchiveId);
	}

	/**
	 * Gets the config definitions size.
	 *
	 * @return the config definitions size
	 */
	public static final int getConfigDefinitionsSize() {
		int lastArchiveId = Cache.STORE.getIndexes()[22].getLastArchiveId();
		return lastArchiveId * 256 + Cache.STORE.getIndexes()[22].getValidFilesCount(lastArchiveId);
	}

	/**
	 * Gets the object definitions size.
	 *
	 * @return the object definitions size
	 */
	public static final int getObjectDefinitionsSize() {
		int lastArchiveId = Cache.STORE.getIndexes()[16].getLastArchiveId();
		return lastArchiveId * 256 + Cache.STORE.getIndexes()[16].getValidFilesCount(lastArchiveId);
	}

	/**
	 * Gets the NPC definitions size.
	 *
	 * @return the NPC definitions size
	 */
	public static final int getNPCDefinitionsSize() {
		int lastArchiveId = Cache.STORE.getIndexes()[18].getLastArchiveId();
		return lastArchiveId * 128 + Cache.STORE.getIndexes()[18].getValidFilesCount(lastArchiveId);
	}

	// 22314

	/**
	 * Gets the item definitions size.
	 *
	 * @return the item definitions size
	 */
	public static final int getItemDefinitionsSize() {
		int lastArchiveId = Cache.STORE.getIndexes()[Constants.ITEM_DEFINITIONS_INDEX].getLastArchiveId();//22314
		return (lastArchiveId * 256 + Cache.STORE.getIndexes()[Constants.ITEM_DEFINITIONS_INDEX].getValidFilesCount(lastArchiveId)) - 22314;
	}

	/**
	 * Item exists.
	 *
	 * @param id the id
	 * @return true, if successful
	 */
	public static boolean itemExists(int id) {
		if (id >= getItemDefinitionsSize()) // set because of custom items
			return false;
		return Cache.STORE.getIndexes()[19].fileExists(id >>> 8, 0xff & id);
	}

	/**
	 * Gets the interface definitions size.
	 *
	 * @return the interface definitions size
	 */
	public static final int getInterfaceDefinitionsSize() {
		return Cache.STORE.getIndexes()[3].getLastArchiveId() + 1;
	}

	/**
	 * Gets the interface definitions components size.
	 *
	 * @param interfaceId the interface id
	 * @return the interface definitions components size
	 */
	public static final int getInterfaceDefinitionsComponentsSize(int interfaceId) {
		return Cache.STORE.getIndexes()[3].getLastFileId(interfaceId) + 1;
	}

	/**
	 * Checks if is quick chat valid.
	 *
	 * @param id the id
	 * @return true, if is quick chat valid
	 */
	public static final boolean isQuickChatValid(int id) {
		return Cache.STORE.getIndexes()[24].fileExists(1, id);
	}

	/**
	 * Format player name for protocol.
	 *
	 * @param name the name
	 * @return the string
	 */
	public static String formatPlayerNameForProtocol(String name) {
		if (name == null)
			return "";
		name = name.replaceAll(" ", "_");
		name = name.toLowerCase();
		return name;
	}

	/**
	 * Format player name for display.
	 *
	 * @param name the name
	 * @return the string
	 */
	public static String formatPlayerNameForDisplay(String name) {
		if (name == null)
			return "Error grabbing name";
		name = name.replaceAll("_", " ");
		name = name.toLowerCase();
		StringBuilder newName = new StringBuilder();
		boolean wasSpace = true;
		for (int i = 0; i < name.length(); i++) {
			if (wasSpace) {
				newName.append(("" + name.charAt(i)).toUpperCase());
				wasSpace = false;
			} else {
				newName.append(name.charAt(i));
			}
			if (name.charAt(i) == ' ') {
				wasSpace = true;
			}
		}
		return newName.toString();
	}

	/**
	 * Random.
	 *
	 * @param maxValue the max value
	 * @return the int
	 */
	public static final int random(int maxValue) {
		if (maxValue <= 0)
			return 0;
		return RANDOM.nextInt(maxValue);
	}

	/**
	 * Gets the random.
	 *
	 * @param maxValue the max value
	 * @return the random
	 */
	public static final int getRandom(int maxValue) {
		return (int) (Math.random() * (maxValue));
	}

	public static final double getRandom(double maxValue) {
		return (double) (Math.random() * (maxValue));
	}

	public static final double randomDouble() {
		return new Random().nextDouble();
	}

	/**
	 * Random.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the int
	 */
	public static final int random(int min, int max) {
		final int n = Math.abs(max + 1 - min);
		return Math.min(min, max + 1) + (n == 0 ? 0 : random(n));
	}

	/**
	 * Random.
	 *
	 * @param min the min
	 * @param max the max
	 * @return the double
	 */
	public static final double random(double min, double max) {
		final double n = Math.abs(max - min);
		return Math.min(min, max) + (n == 0 ? 0 : random((int) n));
	}

	/**
	 * Next.
	 *
	 * @param max the max
	 * @param min the min
	 * @return the int
	 */
	public static final int next(int max, int min) {
		return min + (int) (Math.random() * ((max - min) + 1));
	}

	/**
	 * Gets the random double.
	 *
	 * @param maxValue the max value
	 * @return the random double
	 */
	public static final double getRandomDouble(double maxValue) {
		return (Math.random() * (maxValue + 1));

	}

	/**
	 * Gets the random double.
	 *
	 * @param maxValue the max value
	 * @return the random double
	 */
	public static final double getRandomDouble2(double maxValue) {
		return (Math.random() * (maxValue));

	}

	/**
	 * Long to string.
	 *
	 * @param l the l
	 * @return the string
	 */
	public static final String longToString(long l) {
		if (l <= 0L || l >= 0x5b5b57f8a98a5dd1L)
			return null;
		if (l % 37L == 0L)
			return null;
		int i = 0;
		char ac[] = new char[12];
		while (l != 0L) {
			long l1 = l;
			l /= 37L;
			ac[11 - i++] = VALID_CHARS[(int) (l1 - l * 37L)];
		}
		return new String(ac, 12 - i, i);
	}

	/** The Constant VALID_CHARS. */
	public static final char[] VALID_CHARS = { '_', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
			'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7',
			'8', '9' };

	/**
	 * Invalid account name.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	public static boolean invalidAccountName(String name) {
		return name.length() < 1 || name.length() > 12 || name.startsWith("_") || name.endsWith("_")
				|| name.contains("__") || containsInvalidCharacter(name);
	}

	/**
	 * Invalid display name.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	public static boolean invalidDisplayName(String name) {
		return name.length() < 2 || name.length() > 12 || name.startsWith("_") || name.endsWith("_")
				|| name.contains("__") || containsInvalidCharacter(name);
	}

	/**
	 * Invalid auth id.
	 *
	 * @param auth the auth
	 * @return true, if successful
	 */
	public static boolean invalidAuthId(String auth) {
		return auth.length() != 10 || auth.contains("_") || containsInvalidCharacter(auth);
	}

	/**
	 * Contains invalid character.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	public static boolean containsInvalidCharacter(char c) {
		for (char vc : VALID_CHARS) {
			if (vc == c)
				return false;
		}
		return true;
	}

	/**
	 * Contains invalid character.
	 *
	 * @param name the name
	 * @return true, if successful
	 */
	public static boolean containsInvalidCharacter(String name) {
		for (char c : name.toCharArray()) {
			if (containsInvalidCharacter(c))
				return true;
		}
		return false;
	}

	/**
	 * String to long.
	 *
	 * @param s the s
	 * @return the long
	 */
	public static final long stringToLong(String s) {
		long l = 0L;
		for (int i = 0; i < s.length() && i < 12; i++) {
			char c = s.charAt(i);
			l *= 37L;
			if (c >= 'A' && c <= 'Z')
				l += (1 + c) - 65;
			else if (c >= 'a' && c <= 'z')
				l += (1 + c) - 97;
			else if (c >= '0' && c <= '9')
				l += (27 + c) - 48;
		}
		while (l % 37L == 0L && l != 0L) {
			l /= 37L;
		}
		return l;
	}

	/**
	 * Pack gj string2.
	 *
	 * @param position the position
	 * @param buffer   the buffer
	 * @param String   the string
	 * @return the int
	 */
	public static final int packGJString2(int position, byte[] buffer, String String) {
		int length = String.length();
		int offset = position;
		for (int index = 0; length > index; index++) {
			int character = String.charAt(index);
			if (character > 127) {
				if (character > 2047) {
					buffer[offset++] = (byte) ((character | 919275) >> 12);
					buffer[offset++] = (byte) (128 | ((character >> 6) & 63));
					buffer[offset++] = (byte) (128 | (character & 63));
				} else {
					buffer[offset++] = (byte) ((character | 12309) >> 6);
					buffer[offset++] = (byte) (128 | (character & 63));
				}
			} else
				buffer[offset++] = (byte) character;
		}
		return offset - position;
	}

	/**
	 * Calculate gj string2 length.
	 *
	 * @param String the string
	 * @return the int
	 */
	public static final int calculateGJString2Length(String String) {
		int length = String.length();
		int gjStringLength = 0;
		for (int index = 0; length > index; index++) {
			char c = String.charAt(index);
			if (c > '\u007f') {
				if (c <= '\u07ff')
					gjStringLength += 2;
				else
					gjStringLength += 3;
			} else
				gjStringLength++;
		}
		return gjStringLength;
	}

	/**
	 * Gets the name hash.
	 *
	 * @param name the name
	 * @return the name hash
	 */
	public static final int getNameHash(String name) {
		name = name.toLowerCase();
		int hash = 0;
		for (int index = 0; index < name.length(); index++)
			hash = method1258(name.charAt(index)) + ((hash << 5) - hash);
		return hash;
	}

	/**
	 * Method1258.
	 *
	 * @param c the c
	 * @return the byte
	 */
	public static final byte method1258(char c) {
		byte charByte;
		if (c > 0 && c < '\200' || c >= '\240' && c <= '\377') {
			charByte = (byte) c;
		} else if (c != '\u20AC') {
			if (c != '\u201A') {
				if (c != '\u0192') {
					if (c == '\u201E') {
						charByte = -124;
					} else if (c != '\u2026') {
						if (c != '\u2020') {
							if (c == '\u2021') {
								charByte = -121;
							} else if (c == '\u02C6') {
								charByte = -120;
							} else if (c == '\u2030') {
								charByte = -119;
							} else if (c == '\u0160') {
								charByte = -118;
							} else if (c == '\u2039') {
								charByte = -117;
							} else if (c == '\u0152') {
								charByte = -116;
							} else if (c != '\u017D') {
								if (c == '\u2018') {
									charByte = -111;
								} else if (c != '\u2019') {
									if (c != '\u201C') {
										if (c == '\u201D') {
											charByte = -108;
										} else if (c != '\u2022') {
											if (c == '\u2013') {
												charByte = -106;
											} else if (c == '\u2014') {
												charByte = -105;
											} else if (c == '\u02DC') {
												charByte = -104;
											} else if (c == '\u2122') {
												charByte = -103;
											} else if (c != '\u0161') {
												if (c == '\u203A') {
													charByte = -101;
												} else if (c != '\u0153') {
													if (c == '\u017E') {
														charByte = -98;
													} else if (c != '\u0178') {
														charByte = 63;
													} else {
														charByte = -97;
													}
												} else {
													charByte = -100;
												}
											} else {
												charByte = -102;
											}
										} else {
											charByte = -107;
										}
									} else {
										charByte = -109;
									}
								} else {
									charByte = -110;
								}
							} else {
								charByte = -114;
							}
						} else {
							charByte = -122;
						}
					} else {
						charByte = -123;
					}
				} else {
					charByte = -125;
				}
			} else {
				charByte = -126;
			}
		} else {
			charByte = -128;
		}
		return charByte;
	}

	/** The a char array6385. */
	public static char[] aCharArray6385 = { '\u20ac', '\0', '\u201a', '\u0192', '\u201e', '\u2026', '\u2020', '\u2021',
			'\u02c6', '\u2030', '\u0160', '\u2039', '\u0152', '\0', '\u017d', '\0', '\0', '\u2018', '\u2019', '\u201c',
			'\u201d', '\u2022', '\u2013', '\u2014', '\u02dc', '\u2122', '\u0161', '\u203a', '\u0153', '\0', '\u017e',
			'\u0178' };

	/**
	 * Gets the unformated message.
	 *
	 * @param messageDataLength the message data length
	 * @param messageDataOffset the message data offset
	 * @param messageData       the message data
	 * @return the unformated message
	 */
	public static final String getUnformatedMessage(int messageDataLength, int messageDataOffset, byte[] messageData) {
		char[] cs = new char[messageDataLength];
		int i = 0;
		for (int i_6_ = 0; i_6_ < messageDataLength; i_6_++) {
			int i_7_ = 0xff & messageData[i_6_ + messageDataOffset];
			if ((i_7_ ^ 0xffffffff) != -1) {
				if ((i_7_ ^ 0xffffffff) <= -129 && (i_7_ ^ 0xffffffff) > -161) {
					int i_8_ = aCharArray6385[i_7_ - 128];
					if (i_8_ == 0)
						i_8_ = 63;
					i_7_ = i_8_;
				}
				cs[i++] = (char) i_7_;
			}
		}
		return new String(cs, 0, i);
	}

	/**
	 * Gets the formated message.
	 *
	 * @param message the message
	 * @return the formated message
	 */
	public static final byte[] getFormatedMessage(String message) {
		int i_0_ = message.length();
		byte[] is = new byte[i_0_];
		for (int i_1_ = 0; (i_1_ ^ 0xffffffff) > (i_0_ ^ 0xffffffff); i_1_++) {
			int i_2_ = message.charAt(i_1_);
			if (((i_2_ ^ 0xffffffff) >= -1 || i_2_ >= 128) && (i_2_ < 160 || i_2_ > 255)) {
				if ((i_2_ ^ 0xffffffff) != -8365) {
					if ((i_2_ ^ 0xffffffff) == -8219)
						is[i_1_] = (byte) -126;
					else if ((i_2_ ^ 0xffffffff) == -403)
						is[i_1_] = (byte) -125;
					else if (i_2_ == 8222)
						is[i_1_] = (byte) -124;
					else if (i_2_ != 8230) {
						if ((i_2_ ^ 0xffffffff) != -8225) {
							if ((i_2_ ^ 0xffffffff) != -8226) {
								if ((i_2_ ^ 0xffffffff) == -711)
									is[i_1_] = (byte) -120;
								else if (i_2_ == 8240)
									is[i_1_] = (byte) -119;
								else if ((i_2_ ^ 0xffffffff) == -353)
									is[i_1_] = (byte) -118;
								else if ((i_2_ ^ 0xffffffff) != -8250) {
									if (i_2_ == 338)
										is[i_1_] = (byte) -116;
									else if (i_2_ == 381)
										is[i_1_] = (byte) -114;
									else if ((i_2_ ^ 0xffffffff) == -8217)
										is[i_1_] = (byte) -111;
									else if (i_2_ == 8217)
										is[i_1_] = (byte) -110;
									else if (i_2_ != 8220) {
										if (i_2_ == 8221)
											is[i_1_] = (byte) -108;
										else if ((i_2_ ^ 0xffffffff) == -8227)
											is[i_1_] = (byte) -107;
										else if ((i_2_ ^ 0xffffffff) != -8212) {
											if (i_2_ == 8212)
												is[i_1_] = (byte) -105;
											else if ((i_2_ ^ 0xffffffff) != -733) {
												if (i_2_ != 8482) {
													if (i_2_ == 353)
														is[i_1_] = (byte) -102;
													else if (i_2_ != 8250) {
														if ((i_2_ ^ 0xffffffff) == -340)
															is[i_1_] = (byte) -100;
														else if (i_2_ != 382) {
															if (i_2_ == 376)
																is[i_1_] = (byte) -97;
															else
																is[i_1_] = (byte) 63;
														} else
															is[i_1_] = (byte) -98;
													} else
														is[i_1_] = (byte) -101;
												} else
													is[i_1_] = (byte) -103;
											} else
												is[i_1_] = (byte) -104;
										} else
											is[i_1_] = (byte) -106;
									} else
										is[i_1_] = (byte) -109;
								} else
									is[i_1_] = (byte) -117;
							} else
								is[i_1_] = (byte) -121;
						} else
							is[i_1_] = (byte) -122;
					} else
						is[i_1_] = (byte) -123;
				} else
					is[i_1_] = (byte) -128;
			} else
				is[i_1_] = (byte) i_2_;
		}
		return is;
	}

	/**
	 * Method2782.
	 *
	 * @param value the value
	 * @return the char
	 */
	public static char method2782(byte value) {
		int byteChar = 0xff & value;
		if (byteChar == 0)
			throw new IllegalArgumentException(
					"Non cp1252 character 0x" + Integer.toString(byteChar, 16) + " provided");
		if ((byteChar ^ 0xffffffff) <= -129 && byteChar < 160) {
			int i_4_ = aCharArray6385[-128 + byteChar];
			if ((i_4_ ^ 0xffffffff) == -1)
				i_4_ = 63;
			byteChar = i_4_;
		}
		return (char) byteChar;
	}

	/**
	 * Gets the hash map size.
	 *
	 * @param size the size
	 * @return the hash map size
	 */
	public static int getHashMapSize(int size) {
		size--;
		size |= size >>> -1810941663;
		size |= size >>> 2010624802;
		size |= size >>> 10996420;
		size |= size >>> 491045480;
		size |= size >>> 1388313616;
		return 1 + size;
	}

	/**
	 * Walk dirs 0 - South-West 1 - South 2 - South-East 3 - West 4 - East 5 -
	 * North-West 6 - North 7 - North-East.
	 *
	 * @param dx the dx
	 * @param dy the dy
	 * @return the player walking direction
	 */
	public static int getPlayerWalkingDirection(int dx, int dy) {
		if (dx == -1 && dy == -1) {
			return 0;
		}
		if (dx == 0 && dy == -1) {
			return 1;
		}
		if (dx == 1 && dy == -1) {
			return 2;
		}
		if (dx == -1 && dy == 0) {
			return 3;
		}
		if (dx == 1 && dy == 0) {
			return 4;
		}
		if (dx == -1 && dy == 1) {
			return 5;
		}
		if (dx == 0 && dy == 1) {
			return 6;
		}
		if (dx == 1 && dy == 1) {
			return 7;
		}
		return -1;
	}

	/**
	 * Gets the player running direction.
	 *
	 * @param dx the dx
	 * @param dy the dy
	 * @return the player running direction
	 */
	public static int getPlayerRunningDirection(int dx, int dy) {
		if (dx == -2 && dy == -2)
			return 0;
		if (dx == -1 && dy == -2)
			return 1;
		if (dx == 0 && dy == -2)
			return 2;
		if (dx == 1 && dy == -2)
			return 3;
		if (dx == 2 && dy == -2)
			return 4;
		if (dx == -2 && dy == -1)
			return 5;
		if (dx == 2 && dy == -1)
			return 6;
		if (dx == -2 && dy == 0)
			return 7;
		if (dx == 2 && dy == 0)
			return 8;
		if (dx == -2 && dy == 1)
			return 9;
		if (dx == 2 && dy == 1)
			return 10;
		if (dx == -2 && dy == 2)
			return 11;
		if (dx == -1 && dy == 2)
			return 12;
		if (dx == 0 && dy == 2)
			return 13;
		if (dx == 1 && dy == 2)
			return 14;
		if (dx == 2 && dy == 2)
			return 15;
		return -1;
	}

	/**
	 * Complete quick message.
	 *
	 * @param player the player
	 * @param fileId the file id
	 * @param data   the data
	 * @return the byte[]
	 */
	public static byte[] completeQuickMessage(Player player, int fileId, byte[] data) {
		if (fileId == 1)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.AGILITY) };
		else if (fileId == 8)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.ATTACK) };
		else if (fileId == 13)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.CONSTRUCTION) };
		else if (fileId == 16)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.COOKING) };
		else if (fileId == 23)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.CRAFTING) };
		else if (fileId == 30)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.DEFENCE) };
		else if (fileId == 34)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.FARMING) };
		else if (fileId == 41)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.FIREMAKING) };
		else if (fileId == 47)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.FISHING) };
		else if (fileId == 55)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.FLETCHING) };
		else if (fileId == 62)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.HERBLORE) };
		else if (fileId == 70)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.HITPOINTS) };
		else if (fileId == 74)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.HUNTER) };
		else if (fileId == 135)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.MAGIC) };
		else if (fileId == 127)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.MINING) };
		else if (fileId == 120)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.PRAYER) };
		else if (fileId == 116)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.RANGE) };
		else if (fileId == 111)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.RUNECRAFTING) };
		else if (fileId == 103)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.SLAYER) };
		else if (fileId == 96)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.SMITHING) };
		else if (fileId == 92)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.STRENGTH) };
		else if (fileId == 85)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.SUMMONING) };
		else if (fileId == 79)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.THIEVING) };
		else if (fileId == 142)
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.WOODCUTTING) };
		else if (fileId == 990) {
			data = new byte[] { (byte) player.getSkills().getLevelForXp(Skills.DUNGEONEERING) };
		} else if (fileId == 965) {
			int value = player.getHitpoints();
			data = new byte[] { (byte) (value >> 24), (byte) (value >> 16), (byte) (value >> 8), (byte) value };
		} else if (fileId == 1108) {
			int value = player.getDominionTower().getKilledBossesCount();
			data = new byte[] { (byte) (value >> 24), (byte) (value >> 16), (byte) (value >> 8), (byte) value };
		} else if (fileId == 1109) {
			long value = player.getDominionTower().getTotalScore();
			data = new byte[] { (byte) (value >> 24), (byte) (value >> 16), (byte) (value >> 8), (byte) value };
		} else if (fileId == 1110) {
			int value = player.getDominionTower().getMaxFloorClimber();
			data = new byte[] { (byte) (value >> 24), (byte) (value >> 16), (byte) (value >> 8), (byte) value };
		} else if (fileId == 1111) {
			int value = player.getDominionTower().getMaxFloorEndurance();
			data = new byte[] { (byte) (value >> 24), (byte) (value >> 16), (byte) (value >> 8), (byte) value };
		} else if (fileId == 1134) {
			int value = player.getCrucibleHighScore();
			data = new byte[] { (byte) (value >> 24), (byte) (value >> 16), (byte) (value >> 8), (byte) value };
		}

		else if (Settings.DEBUG)
			Logger.log("Utils", "qc: " + fileId + ", " + (data == null ? 0 : data.length));
		return data;
	}

	/**
	 * Decipher.
	 *
	 * @param key  the key
	 * @param data the data
	 * @return the byte[]
	 */
	public static byte[] decipher(int[] key, byte[] data) {
		return decipher(key, data, 0, data.length);
	}

	public static byte[] decipher(int[] key, byte[] data, int offset, int length) {
		int numBlocks = (length - offset) / 8;
		ByteBuffer buffer = ByteBuffer.wrap(data);
		buffer.position(offset);
		for (int i = 0; i < numBlocks; i++) {
			int y = buffer.getInt();
			int z = buffer.getInt();
			int sum = -957401312;
			int delta = -1640531527;
			int numRounds = 32;
			while (numRounds > 0) {
				z -= ((y >>> 5 ^ y << 4) + y ^ sum + key[sum >>> 11 & 0x56c00003]);
				sum -= delta;
				y -= ((z >>> 5 ^ z << 4) - -z ^ sum + key[sum & 0x3]);
				numRounds--;
			}
			buffer.position(buffer.position() - 8);
			buffer.putInt(y);
			buffer.putInt(z);
		}
		return buffer.array();
	}

	/**
	 * Decode base37.
	 *
	 * @param value the value
	 * @return the string
	 */

	public static String decodeBase37(long value) {
		char[] chars = new char[12];
		int pos = 0;
		while (value != 0) {
			int remainder = (int) (value % 37);
			value /= 37;

			char c;
			if (remainder >= 1 && remainder <= 26) {
				c = (char) ('a' + remainder - 1);
			} else if (remainder >= 27 && remainder <= 36) {
				c = (char) ('0' + remainder - 27);
			} else {
				c = '_';
			}

			chars[chars.length - pos++ - 1] = c;
		}
		return new String(chars, chars.length - pos, pos);
	}

	public static final int[] DEFAULT_LOBBY_CONFIGS = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, -476379337, -1634348113, 919975670, 100098016, -941649356, 33599227, 0, 0, 0, 2, 0, 10, 2, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 3, 3, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 52776083, 0,
			2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 50, 2, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 4, 4, 0, 1, 1, 0, 0, 0, 0, 0,
			130, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 557858, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 24, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0,
			1000, 0, 0, 0, 0, 0, 9, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 272, 0, 1000, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 58720640,
			0, 24596, 0, 0, 0, 0, 2048, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			269026962, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0, 0, 0, 0, 0, 0, 0,
			1073742208, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 262177, 0, 256, 0, 0, 0, 0, 0,
			0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1076961290, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 1073741824, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, -1409286144, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 99, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2147483648, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 34079488, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 272, 0, 0, 0, 0, 0, 0, 0, 1073741824, 0, 0, 0, 0, 8192, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 407044218, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 536870912, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 134217730, 0, 2049, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			201326625, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4224, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 15, 0, 0, 0,
			0, 0, 0, 0, 33554432, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 4194304, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			1073741824, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 134232576, 32768, 0, 0, 0, 0, 0, 4194304, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, -1, 0, 262144, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -2147483648, 0, 0, 0, -1, -1, -1, -1, -1, -1, 0, 0, -1,
			-1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 256, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 268435456, 0, 0, 0, 0, 0, 0, 0, 0, 537133056, 2048, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 262273, 536877056, 0, 393216, 32, 0, 4096, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, -1, -1, -1, 1073742848, 65536, 268435456, 0, 0, 0, 512, 0,
			0, 0, 0, 0, 291912, 5672, 260092, 0, 60248, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 508, 0, 1, 0, 0,
			-1, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2048, -2130681854, 0, -1,
			-1, -1, -1, 0, 10, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 64, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0,
			-1, 0, 0, 60, 0, 0, 470286336, 132096, 1, 1276, 0, 0, 0, 0, 0, 0, 0, 0, 0, 536870912, -1, 0, 54332549, 0, 0,
			0, -1, 0, 67174408, -872415232, 256, 8388608, 0, 0, 0, 4353, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 590848, 0, 0, 0, 0, 0, 0, -1, 0, 1001, 0, 0, 0, 0, 82663, 1025, 0, 134217728, 14, 0, 0, 0,
			0, 0, 557057, 1476461568, 16384, 4096, 3721, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 525312, 0, 0, 0, 0, 0, -1,
			0, 0, 0, 0, 0, 0, 0, 0, 22271787, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 12582912, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, -1, 0, 0, 0, 0,
			-1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 65536, 0, -1879048192, 0, 16384, 1, 16384, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 1,
			0, -2147483648, 0, 0, -2147483648, 0, 0, 0, 0, 0, 0, 512, 0, 0, 0, 0, 0, 16777216, 0, 0, 0, 25165824, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 67108864, 0, 0, 0, 268435456, 0, 136347657, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, -2144919536, 0, 0, -1, 65540, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 511305630, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			1073744067, 0, 65536, 0, 0, 0, 0, 0, 0, 536870912, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 65535, 65535, 65535,
			1073741823, 2147483647, 1073741823, 1073741823, 1073741823, 1073741823, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 134218080, 0, 0, 0, 0, 545260028, 0, 0, 0, 0, 0, 0, 0, 0, 262144, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, -2080374784, 0, 0, 0, 0, 0, 0, -2147483648, 279019520, -2147483648, 134217728, 0, 0, 0, 0, 0,
			-1, 0, 0, 0, 0, 0, 751720, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, -1, 268437504, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 4194304, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 100673544, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 677729578,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			956430392, 25171979, 151589, 3, 4718466, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			393216, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 8388608, 33554432, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 67108864, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			41476, 0, 0, 0, 0, 32, 536870912, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 40632322, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 819320, 0, 0, 0, 0, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 4194304, 0, 0, 0, 0, 0, 0, 0, 0, 0, 256,
			0, 0, 0, 0, 0, 0, 0, 1048559, 589721, -1, 0, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 589855, 0, 0, -1, 0, 0, 402655232, 98353, 0, 0, 0, -1137689604,
			1073807344, 0, 0, 0, 10, 0, 0, 0, 8386561, 0, 0, -1, -1, -1, -1, -1, -1, -1, 1536, 8192, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 77, 1733, -16912800, 4594664, 5359015, 3721, 0, 0, 0, -2147483648, 1310720, -1,
			0, 0, 0, 0, 0, 0, 0, 1342177408, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 16777216, 0, 0, 32289, -1, -1, 0, 0,
			0, 77, 52, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, };

	/**
	 * Fix chat message.
	 *
	 * @param message the message
	 * @return the string
	 */
	public static String fixChatMessage(String message) {
		StringBuilder newText = new StringBuilder();
		boolean wasSpace = true;
		boolean exception = false;
		for (int i = 0; i < message.length(); i++) {
			if (!exception) {
				if (wasSpace) {
					newText.append(("" + message.charAt(i)).toUpperCase());
					if (!String.valueOf(message.charAt(i)).equals(" "))
						wasSpace = false;
				} else {
					newText.append(("" + message.charAt(i)).toLowerCase());
				}
			} else {
				newText.append(("" + message.charAt(i)));
			}
			if (String.valueOf(message.charAt(i)).contains(":"))
				exception = true;
			else if (String.valueOf(message.charAt(i)).contains(".") || String.valueOf(message.charAt(i)).contains("!")
					|| String.valueOf(message.charAt(i)).contains("?")
					|| String.valueOf(message.charAt(i)).contains("_"))
				wasSpace = true;
		}
		return newText.toString();
	}

	/**
	 * Instantiates a new utils.
	 */
	private Utils() {

	}

	/**
	 * Gets the 32 bit value.
	 *
	 * @return the 32 bit value
	 */
	public static int get32BitValue(boolean[] array, boolean trueCondition) {
		int value = 0;
		for (int index = 1; index < array.length + 1; index++) {
			if (array[index - 1] == trueCondition) {
				value += 1 << index;
			}
		}

		return value;
	}

	/**
	 * Roll a dice and get a random number. (Basic RNG)
	 * 
	 * @param min The minimum amount to roll.
	 * @param max The maximum amount to roll.
	 * @return the resulting roll.
	 */
	public static int randomise(int min, int max) {
		return new Random().nextInt(max) + min;
	}

	/**
	 * Get a random number as a percentile.
	 * 
	 * @return a percentage between 0 and 100;
	 */
	public static double percentileDiceRoll() {
		return Double.valueOf(randomise(0, 10000000) / 100000);
	}

	/**
	 * Enum containing the key to identify what to do in a safe math number
	 * operation.
	 */
	public static enum SafeMathNumberOperation {

		ADD,

		MULTIPLY

	}

	/**
	 * Safely math with a number and keep it bound to {@link Integer#MAX_VALUE}.
	 * 
	 * @param operation The operation to perform.
	 * @param value     The value to math with.
	 * @param value2    The second value to math with.
	 * @return the answer.
	 */
	public static int safelyMathNumber(SafeMathNumberOperation operation, long value, long value2) {
		long answer = Long.valueOf(value);
		if (operation == SafeMathNumberOperation.ADD) {
			answer = value + value2;
		} else if (operation == SafeMathNumberOperation.MULTIPLY) {
			answer = value * value2;
		}
		return answer > Integer.MAX_VALUE ? Integer.MAX_VALUE : Integer.valueOf(answer + "");
	}

	public static String formatTime(long time) {
		long currentTime = Utils.currentTimeMillis();
		long hours = TimeUnit.MILLISECONDS.toHours(time - currentTime);
		long minutes = TimeUnit.MILLISECONDS.toMinutes(time - currentTime);
		long seconds = TimeUnit.MILLISECONDS.toSeconds(time - currentTime);
		StringBuilder string = new StringBuilder();
		if (hours > 0)
			string.append(hours + "h:");
		if (minutes > 0)
			string.append(minutes + "m:");
		string.append(seconds + "s");
		return string.toString();
	}

	private static final byte[][] ANGLE_DIRECTION_DELTA = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 },
			{ 1, 1 }, { 1, 0 }, { 1, -1 } };

	public static byte[] getDirection(int angle) {
		int v = angle >> 11;
		return ANGLE_DIRECTION_DELTA[v];
	}

	public static int getProjectileTimeNew(WorldTile from, int fromSizeX, int fromSizeY, WorldTile to, int toSizeX, int toSizeY, double speed) {
		int fromX = from.getX() * 2 + fromSizeX;
		int fromY = from.getY() * 2 + fromSizeY;

		int toX = to.getX() * 2 + toSizeX;
		int toY = to.getY() * 2 + toSizeY;

		fromX /= 2;
		fromY /= 2;
		toX /= 2;
		toY /= 2;

		int deltaX = fromX - toX;
		int deltaY = fromY - toY;
		int sqrt = (int) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
		return (int) (sqrt * (10 / speed));
	}
	
	public static int projectileTimeToCycles(int time) {
		return (time + 29) / 30;
		/* return Math.max(1, (time+14)/30); */
	}
	
	public static int getProjectileTimeSoulsplit(WorldTile from, int fromSizeX, int fromSizeY, WorldTile to, int toSizeX, int toSizeY) {
		int fromX = from.getX() * 2 + fromSizeX;
		int fromY = from.getY() * 2 + fromSizeY;

		int toX = to.getX() * 2 + toSizeX;
		int toY = to.getY() * 2 + toSizeY;

		fromX /= 2;
		fromY /= 2;
		toX /= 2;
		toY /= 2;

		int deltaX = fromX - toX;
		int deltaY = fromY - toY;
		int sqrt = (int) Math.sqrt((deltaX * deltaX) + (deltaY * deltaY));
		sqrt *= 15;
		sqrt -= sqrt % 30;
		return Math.max(30, sqrt);
	}

}
