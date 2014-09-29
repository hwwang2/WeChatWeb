package com.ajita.wechat.util.baidu;

/**
 * 根据IP查询出的地址
 * 
 * @author Administrator
 * 
 */
public class IpLocation {
	private String address;// #地址 如"CN|北京|北京|None|CHINANET|1|None",
	private IpLocationContent content;// #详细内容
	private int status;// : 0 #返回状态码

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public IpLocationContent getContent() {
		return content;
	}

	public void setContent(IpLocationContent content) {
		this.content = content;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * IP地址查询出的详细内容
	 * 
	 * @author Administrator
	 * 
	 */
	public class IpLocationContent {
		private String address;// : "北京市", #简要地址
		private Address_detail address_detail;// #详细地址信息
		private IpPoint point;// #百度经纬度坐标值

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public Address_detail getAddress_detail() {
			return address_detail;
		}

		public void setAddress_detail(Address_detail address_detail) {
			this.address_detail = address_detail;
		}

		public IpPoint getPoint() {
			return point;
		}

		public void setPoint(IpPoint point) {
			this.point = point;
		}
		
		public class IpPoint{
			private float x;//经度
			private float y;//纬度
			public float getX() {
				return x;
			}
			public void setX(float x) {
				this.x = x;
			}
			public float getY() {
				return y;
			}
			public void setY(float y) {
				this.y = y;
			}
		}

		/**
		 * 地址详细
		 * 
		 * @author Administrator
		 * 
		 */
		public class Address_detail {
			private String city;// "北京市", #城市
			private String city_code;// 131, #百度城市代码
			private String district;// "", #区县
			private String province;// "北京市", #省份
			private String street;// "", #街道
			private String street_number;// "" #门址

			public String getCity() {
				return city;
			}

			public void setCity(String city) {
				this.city = city;
			}

			public String getCity_code() {
				return city_code;
			}

			public void setCity_code(String city_code) {
				this.city_code = city_code;
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
	}
}
