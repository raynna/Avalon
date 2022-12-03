package com.rs.game.player;

public class ChatMessage {

	private String message;
	private String filteredMessage;

	public ChatMessage(String message) {
		this.message = message;
	}

	public String getMessage(boolean filtered) {
		return filtered ? filteredMessage : message;
	}
}
