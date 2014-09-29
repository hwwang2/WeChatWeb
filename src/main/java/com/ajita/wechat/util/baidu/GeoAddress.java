package com.ajita.wechat.util.baidu;

import com.ajita.wechat.util.baidu.GeoLocation.Location;

/**
 * 地址信息，包括所属商圈，周边信息等
 * 
 * @author Administrator
 * 
 */
public class GeoAddress {
	private int status;// constant 返回结果状态值， 成功返回0，其他值请查看附录。
	private Location location; // 经纬度信息
	private String formatted_address;// 结构化地址信息
	private String business;// 所在商圈信息，如 "人民大学,中关村,苏州街"
	private AddressComponent addressComponent;//

	private AmbitusPosition[] pois;// （周边poi数组）

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

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public AddressComponent getAddressComponent() {
		return addressComponent;
	}

	public void setAddressComponent(AddressComponent addressComponent) {
		this.addressComponent = addressComponent;
	}

	public AmbitusPosition[] getPois() {
		return pois;
	}

	public void setPois(AmbitusPosition[] pois) {
		this.pois = pois;
	}

	/**
	 * 
	 * @author Administrator
	 * 
	 */
	public class AddressComponent {
		private String city;// 城市名
		private String district;// 区县名
		private String province;// 省名
		private String street;// 街道名
		private String street_number;// 街道门牌号

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getProvince() {
			return province;
		}

		public void setProvince(String province) {
			this.province = province;
		}

		public String getStreet() {
			return street;
		}

		public void setStreet(String street) {
			this.street = street;
		}

		public String getStreet_number() {
			return street_number;
		}

		public void setStreet_number(String street_number) {
			this.street_number = street_number;
		}
	}

	/**
	 * 周边信息
	 * 
	 * @author Administrator
	 * 
	 */
	public class AmbitusPosition {
		private String addr;// 地址信息
		private String cp;// 数据来源
		private String distance;// 离坐标点距离
		private String name;// poi名称
		private String poiType;// poi类型，如’ 办公大厦,商务大厦’
		private Location point;// poi坐标{x,y}
		private String tel;// 电话
		private String uid;// poi唯一标识
		private String zip;// 邮编

		public String getAddr() {
			return addr;
		}

		public void setAddr(String addr) {
			this.addr = addr;
		}

		public String getCp() {
			return cp;
		}

		public void setCp(String cp) {
			this.cp = cp;
		}

		public String getDistance() {
			return distance;
		}

		public void setDistance(String distance) {
			this.distance = distance;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPoiType() {
			return poiType;
		}

		public void setPoiType(String poiType) {
			this.poiType = poiType;
		}

		public Location getPoint() {
			return point;
		}

		public void setPoint(Location point) {
			this.point = point;
		}

		public String getTel() {
			return tel;
		}

		public void setTel(String tel) {
			this.tel = tel;
		}

		public String getUid() {
			return uid;
		}

		public void setUid(String uid) {
			this.uid = uid;
		}

		public String getZip() {
			return zip;
		}

		public void setZip(String zip) {
			this.zip = zip;
		}
	}
}
