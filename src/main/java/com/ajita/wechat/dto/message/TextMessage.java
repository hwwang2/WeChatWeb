package com.ajita.wechat.dto.message;

import com.ajita.wechat.dto.CommonMessage;

/**
 * 文本消息
 * 
 * @author Administrator
 * 
 */
public class TextMessage extends CommonMessage {
	private String content; // 消息正文

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\ncontent:" + content);
		return buffer.toString();
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
