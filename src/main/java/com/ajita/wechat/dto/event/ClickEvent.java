package com.ajita.wechat.dto.event;

import com.ajita.wechat.dto.CommonEvent;

/**
 * 自定义菜单点击事件
 * 
 * @author Administrator
 * 
 */
public class ClickEvent extends CommonEvent {
	private String eventKey;// 事件KEY值，与自定义菜单接口中KEY值对应

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\neventKey:" + eventKey);
		return buffer.toString();
	}
	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}
