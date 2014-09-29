package com.ajita.wechat.dto.event;

import com.ajita.wechat.dto.CommonEvent;

/**
 * 关注/取消关注事件
 * 
 * @author Administrator
 * 
 */
public class SubscribeEvent extends CommonEvent {
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		return buffer.toString();
	}
}
