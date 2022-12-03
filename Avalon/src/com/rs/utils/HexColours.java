package com.rs.utils;

public class HexColours {

	public enum Colours {

		WHITE("ffffff"),

		RED("b50404"),

		YELLOW("EAEC2C"),

		GREEN("3bad5a"),

		DARK_GREEN("366e45"),

		BLACK("000000"),
		
		PURPLE("9966CC"),
		
		DARK_PURPLE("450089");

		private String hex;

		private Colours(String hex) {
			this.setHex("<col=" + hex + ">");
		}

		public String getHex() {
			return hex;
		}

		public void setHex(String hex) {
			this.hex = hex;
		}
	}

	public enum Shadow {

		WHITE("ffffff"),

		RED("b50404"),

		YELLOW("EAEC2C"),

		GREEN("3bad5a"),

		DARK_GREEN("366e45"),

		BLACK("000000"),
		
		PURPLE("9966CC"),
		
		DARK_PURPLE("450089");

		private String shad;

		private Shadow(String shad) {
			this.setShad("<shad=" + shad + ">");
		}

		public String getShad() {
			return shad;
		}

		public void setShad(String shad) {
			this.shad = shad;
		}
	}

	public static String end() {
		return "</col></shad>";
	}

	public static String getShortMessage(Colours colour, String message) {
		return colour.getHex() + message + end();
	}

	public static String getMessage(Colours colour, String message) {
		return colour.getHex() + message;
	}

	public static String getMessageWithShad(Shadow shad, Colours colour, String message) {
		return shad.getShad() + colour.getHex() + message;
	}
}