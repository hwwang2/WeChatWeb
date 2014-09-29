package com.ajita.wechat.util.baidu;

/**
 * 百度API查询出的地理位置，主要以经纬度表示
 * 
 * @author Administrator
 * 
 */
public class GeoLocation {
	private int status;// Int 返回结果状态值， 成功返回0，其他值请查看附录。
	private Location location;// object 经纬度坐标
	private int precise; // Int 位置的附加信息，是否精确查找。1为精确查找，0为不精确。
	private int confidence; // Int 可信度
	private String level; // string 地址类型，如“商务大厦”

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getPrecise() {
		return precise;
	}

	public void setPrecise(int precise) {
		this.precise = precise;
	}

	public int getConfidence() {
		return confidence;
	}

	public void setConfidence(int confidence) {
		this.confidence = confidence;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * 地理位置
	 * 
	 * @author Administrator
	 * 
	 */
	public class Location {
		/**
		 * 纬度值
		 */
		private float lat;

		/**
		 * 经度值
		 */
		private float lng;

		public float getLat() {
			return lat;
		}

		public void setLat(float lat) {
			this.lat = lat;
		}

		public float getLng() {
			return lng;
		}

		public void setLng(float lng) {
			this.lng = lng;
		}

		public String toString() {
			return String.format("经度 %4f，纬度 %4f", lng, lat);
		}
	}
}
