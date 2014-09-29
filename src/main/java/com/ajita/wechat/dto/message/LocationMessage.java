package com.ajita.wechat.dto.message;

import com.ajita.wechat.dto.CommonMessage;

/**
 * 地理位置消息
 * 
 * @author Administrator
 * 
 */
public class LocationMessage extends CommonMessage {
	private float location_X; // 地理位置维度
	private float location_Y; // 地理位置经度
	private String scale; // 地图缩放大小
	private String label; // 地理位置信息

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\nlocation_X:" + location_X);
		buffer.append("\nlocation_Y:" + location_Y);
		buffer.append("\nscale:" + scale);
		buffer.append("\nlabel:" + label);
		return buffer.toString();
	}

	public float getLocation_X() {
		return location_X;
	}

	public void setLocation_X(float location_X) {
		this.location_X = location_X;
	}

	public float getLocation_Y() {
		return location_Y;
	}

	public void setLocation_Y(float location_Y) {
		this.location_Y = location_Y;
	}

	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
