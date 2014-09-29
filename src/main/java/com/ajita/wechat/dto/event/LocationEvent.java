package com.ajita.wechat.dto.event;

import com.ajita.wechat.dto.CommonEvent;

/**
 * 上报地理位置事件
 * 
 * @author Administrator
 * 
 */
public class LocationEvent extends CommonEvent {
	private String latitude;// 地理位置纬度
	private String longitude;// 地理位置经度
	private String precision;// 地理位置精度

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\nlatitude:" + latitude);
		buffer.append("\nlongitude:" + longitude);
		buffer.append("\nprecision:" + precision);
		return buffer.toString();
	}
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}
}
