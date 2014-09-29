package com.ajita.wechat.dto.message;

import com.ajita.wechat.dto.CommonMessage;

/**
 * 链接消息
 * 
 * @author Administrator
 * 
 */
public class LinkMessage extends CommonMessage {
	private String title; // 消息标题
	private String description; // 消息描述
	private String url;// 消息链接

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\ntitle:" + title);
		buffer.append("\ndescription:" + description);
		buffer.append("\nurl:" + url);
		return buffer.toString();
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
