package com.ajita.wechat.dto.event;

import com.ajita.wechat.dto.CommonEvent;

/**
 * 如果用户还未关注公众号，则用户可以关注公众号，关注后微信会将带场景值关注事件推送给开发者。
 * 
 * 如果用户还未关注公众号，则用户可以关注公众号，关注后微信会将带场景值关注事件推送给开发者
 * 
 * @author Administrator
 * 
 */
public class SubscribeScanEvent extends CommonEvent {
	private String eventKey;// 事件KEY值
	private String ticket;// 二维码的ticket，可用来换取二维码图片

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\neventKey:" + eventKey);
		buffer.append("\nticket:" + eventKey);
		return buffer.toString();
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

	public String getTicket() {
		return ticket;
	}

	public void setTicket(String ticket) {
		this.ticket = ticket;
	}
}
