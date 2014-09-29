package com.ajita.wechat.service.baidu;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.ajita.wechat.util.baidu.BaiduWebApiUtil;
import com.ajita.wechat.util.baidu.GeoAddress;
import com.ajita.wechat.util.baidu.GeoAddress.AmbitusPosition;

public class BaiduHandler {
	public static String handleTxtMessage(String word) {
		String url = null;
		try {
			url = String.format("http://www.baidu.com/s?wd=%s&ie=utf-8",
					URLEncoder.encode(word, "UTF-8"));
		} catch (UnsupportedEncodingException e1) {
		}
		return String.format("<a href='%s'>帮你百度 ~  %s</a>", url, word);
	}

	/**
	 * 处理Location类型的消息，显示所发送位置的具体信息
	 * @param locationX 纬度
	 * @param locationY 经度
	 * @return
	 */
	public static String handleLocationMessage(float locationX, float locationY) {
		String result = "未获取到您发送位置的具体信息！";
		try {
			GeoAddress addr = BaiduWebApiUtil.geocodingXY2City(locationY,
					locationX);
			if (addr != null && addr.getFormatted_address() != null) {
				StringBuffer sb = new StringBuffer();
				if (addr.getPois() != null) {
					AmbitusPosition[] ambituses = addr.getPois();
					for (int i = 0; i < (ambituses.length > 5 ? 5
							: ambituses.length); i++) {
						sb.append(ambituses[i].getName() + ","
								+ ambituses[i].getAddr() + ";");
					}
				}
				String ambs = sb.toString();
				result = String
						.format("您发送位置为：%s；坐标：%s；所属商圈：%s；周边信息有：%s。",
								addr.getFormatted_address(),
								addr.getLocation(),
								addr.getBusiness(),
								ambs.length() > 0 ? ambs.substring(0,
										ambs.length() - 1) : "");
			}
		} catch (UnsupportedEncodingException e1) {
		} catch (IOException e) {
		}
		return result;
	}
}
