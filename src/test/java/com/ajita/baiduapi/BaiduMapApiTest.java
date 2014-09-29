package com.ajita.baiduapi;

import java.io.IOException;

import com.ajita.wechat.util.baidu.BaiduWebApiUtil;
import com.ajita.wechat.util.baidu.GeoAddress;
import com.ajita.wechat.util.baidu.GeoLocation;
import com.ajita.wechat.util.baidu.GeoAddress.AmbitusPosition;
import com.ajita.wechat.util.baidu.IpLocation;

import junit.framework.TestCase;

public class BaiduMapApiTest extends TestCase {
	/**
	 * 测试把经纬度转化为具体地址信息
	 */
	public void testGeocodingCity2XY() {
		try {
			String result = null;
			GeoLocation loc = BaiduWebApiUtil.geocodingCity2XY("省政府", "合肥");
			if (loc != null && loc.getLocation() != null) {
				result = String.format("您发送的位置纬度为：%s；经度为：%s。", loc
						.getLocation().getLat(), loc.getLocation().getLng());
			} else {
				result = "未获取到您发送位置的经纬度信息！";
			}
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 测试经纬度查询所查地理位置的地址信息
	 */
	public void testGeocodingXY2City() {
		try {
			String result = null;
			GeoAddress addr = BaiduWebApiUtil
					.geocodingXY2City(100f, 39.983424f);
			if (addr != null && addr.getFormatted_address() != null) {
				AmbitusPosition[] ambituses = addr.getPois();
				StringBuffer sb = new StringBuffer();
				for (AmbitusPosition amb : ambituses) {
					sb.append(amb.getName() + "," + amb.getAddr() + ";");
				}
				String ambs = sb.toString();
				result = String
						.format("您坐标所在位置为：%s；坐标为：%s；所属商圈：%s；周边信息有：%s。",
								addr.getFormatted_address(),
								addr.getLocation(),
								addr.getBusiness(),
								ambs.length() > 0 ? ambs.substring(0,
										ambs.length() - 1) : "");
			} else {
				result = "未获取到您发送坐标的位置信息！";
			}
			System.out.println(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void testGetAddressByIp() {
		try {
			IpLocation addr = BaiduWebApiUtil.getAddressByIp("192.168.39.12");
			if (addr != null && addr.getContent() != null
					&& addr.getContent().getAddress_detail() != null) {
				System.out.println(addr.getContent().getAddress_detail()
						.getCity_code());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
