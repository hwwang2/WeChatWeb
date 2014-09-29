package com.ajita.wechat.dto;

public enum EventType {
	subscribe("subscribe"), unsubscribe("unsubscribe"),

	VIEW("VIEW"), SCAN("SCAN"), LOCATION("LOCATION"), CLICK("CLICK");

	private String value;

	private EventType(String value) {
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
