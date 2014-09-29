package com.ajita.wechat.dto;

/**
 * 事件基类
 * 
 * 关于重试的消息排重，推荐使用FromUserName + CreateTime 排重。
 * 
 * @author Administrator
 * 
 */
public class CommonEvent extends AbstractMessage {
	private EventType event;// 事件类型

	public EventType getEvent() {
		return event;
	}

	public void setEvent(EventType eType) {
		this.event = eType;
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\nEvent:" + event);
		return buffer.toString();
	}
}
