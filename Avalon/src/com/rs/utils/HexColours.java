package com.rs.utils;

public class HexColours {

	public enum Colour {

		WHITE("ffffff"),

		RED("ff0000"),

		DARK_RED("660000"),

		ORANGE("f9782c"),

		BROWN("7f6000"),

		YELLOW("cdc550"),

		GREEN("008a05"),

		DARK_GREEN("366e45"),

		BLACK("000000"),
		
		PURPLE("9966CC"),

		BLUE("00528f"),
		
		DARK_PURPLE("450089");

		private String hex;

		Colour(String hex) {
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

		Shadow(String shad) {
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

	public static String getShortMessage(Colour colour, String message) {
		return colour.getHex() + message + end();
	}

	public static String getMessage(Colour colour, String message) {
		return colour.getHex() + message;
	}

	public static String getMessageWithShad(Shadow shad, Colour colour, String message) {
		return shad.getShad() + colour.getHex() + message;
	}
}
