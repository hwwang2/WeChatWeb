package com.ajita.wechat.dto;

public enum MsgType {
	text("text"),

	image("image"),

	voice("voice"),

	video("video"),

	location("location"),

	link("link"),

	event("event");

	/** value */
	private String value;

	private MsgType(String value) {
		this.value = value;
	}

	/**
	 * getter method
	 * 
	 * @see ErrorCode#value
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
}
