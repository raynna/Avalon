package com.rs.utils;

import java.security.SecureRandom;
import java.util.List;

/**
 * Used for random generation of numbers for probability events and random selection.
 *
 * @author Patrick/!knd6060#4741
 */
public class Rand {
	private static final SecureRandom gen = new SecureRandom();

	/**
	 * Random generator for hitting a certain probability.
	 *
	 *    @Param denom The 1/denom chance that this returns true.
	 *    @Return Whether the chance was hit.
	 */
	public static boolean hit(int denom) {
		return gen.nextInt(denom) == 0;
	}

	/**
	 * Random generator for hitting a certain probability.
	 *
	 *    @Param percent The percent rate that this returns true.
	 *    @Return Whether the percent was hit.
	 */
	public static boolean hitPercent(int percent) {
		return gen.nextInt(100) < percent;
	}


	/**
	 * Random generator for hitting a certain probability.
	 *
	 *    @Param p The probability of success / the probability that this returns true. p must be between 0 and 1 inclusive.
	 *    @Return Whether the probability was hit.
	 */
	public static boolean hitProbability(double p) {
		return gen.nextDouble() < p;
	}

	/**
	 * Returns a random element of a generic list.
	 *
	 *    @Param list
	 *    @Return random element from the array
	 */
	public static <T> T randElement(List<T> list) {
		return list.get(gen.nextInt(list.size()));
	}

	/**
	 * Returns a random element of a generic array.
	 *
	 *    @Param array
	 *    @Return random element from the array
	 */
	public static <T> T randElement(T[] array) {
		return array[gen.nextInt(array.length)];
	}

	/**
	 * Returns a random element of a int[] array.
	 *
	 *    @Param array
	 *    @Return random element from the array
	 */
	public static int randElement(int[] array) {
		return array[gen.nextInt(array.length)];
	}

	/**
	 * Returns a random element of a boolean[] array.
	 *
	 *    @Param array
	 *    @Return random element from the array
	 */
	public static boolean randElement(boolean[] array) {
		return array[gen.nextInt(array.length)];
	}

	/**
	 * Returns a random element of a double[] array.
	 *
	 *    @Param array
	 *    @Return random element from the array
	 */
	public static double randElement(double[] array) {
		return array[gen.nextInt(array.length)];
	}

	/**
	 * Returns a random element of a byte[] array.
	 *
	 *    @Param array
	 *    @Return random element from the array
	 */
	public static byte randElement(byte[] array) {
		return array[gen.nextInt(array.length)];
	}

	/**
	 * Returns a random element of a char[] array.
	 *
	 *    @Param array
	 *    @Return random element from the array
	 */
	public static char randElement(char[] array) {
		return array[gen.nextInt(array.length)];
	}

	/**
	 * Returns a random element of a float[] array.
	 *
	 *    @Param array
	 *    @Return random element from the array
	 */
	public static float randElement(float[] array) {
		return array[gen.nextInt(array.length)];
	}

	/**
	 * Returns a random element of a short[] array.
	 *
	 *    @Param array
	 *    @Return random element from the array
	 */
	public static short randElement(short[] array) {
		return array[gen.nextInt(array.length)];
	}

	/**
	 * Returns a random element of a long[] array.
	 *
	 *    @Param array
	 *    @Return random element from the array
	 */
	public static long randElement(long[] array) {
		return array[gen.nextInt(array.length)];
	}

	public static int inclusive(int low, int high) {
		return low + gen.nextInt(high - low + 1);
	}

	public static boolean nextBoolean() {
		return gen.nextBoolean();
	}

	/**
	 *
	 *    @Param bound
	 *    @Return random number between 0 inclusive and bound exclusive
	 */
	public static int nextInt(int bound) {
		return gen.nextInt(bound);
	}

	/**
	 *
	 *    @Param bound
	 *    @Return random number between 0 inclusive and bound inclusive
	 */
	public static int nextInclusiveInt(int bound) {
		return gen.nextInt(bound + 1);
	}

	public static double nextDouble() {
		return gen.nextDouble();
	}

	public static double nextFloat() {
		return gen.nextFloat();
	}
}