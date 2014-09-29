package com.ajita.wechat.common;

/**
 * 媒体文件类型，分别有图片（image）、语音（voice）、视频（video）和缩略图（thumb，主要用于视频与音乐格式的缩略图）
 * 
 * @author Administrator
 * 
 */
public enum MultiMediaType {
	image("image"), voice("voice"), video("video"), thumb("thumb");

	private String value;

	private MultiMediaType(String value) {
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
