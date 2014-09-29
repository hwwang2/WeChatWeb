package com.ajita.wechat;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import com.ajita.wechat.common.MultiMediaType;
import com.ajita.wechat.dto.custommenu.EventButton;
import com.ajita.wechat.dto.custommenu.MenuButton;
import com.ajita.wechat.dto.custommenu.ParentButton;
import com.ajita.wechat.dto.custommenu.ViewButton;
import com.ajita.wechat.dto.custommenu.WeChatMenu;
import com.ajita.wechat.util.WeChatInterfaceUtil;
import com.google.gson.Gson;

public class WeChatInterfaceTest extends TestCase {
	private String access_token = "Y5MVOtGyXXOX98iRNXcDr1-42rvG379nLkJfBVGctQd0mWJIFMt5SjCGIxN5HXAY";

	public void testGetAccessToken() {
		String appId = "wx10c1e376142194a8";
		String appSecret = "1a468df36342a19786c96d2e1669c55a";
		Map<String, String> result = WeChatInterfaceUtil.getAccessToken(appId,
				appSecret);
		// {"access_token":"JtJtHatZytbRVaPMscaAKkgVCeiChHf4lPt-ocvm2lRTFJ-amLSdYPnlULpms5hf","expires_in":7200}
		assertTrue(result != null && result.containsKey("access_token"));
		access_token = result.get("access_token");
		System.out.println(result.get("access_token"));
	}

	public void testGetUploadMedia() {
		String fileURI = "E:/photo/IMG_20140302_130450.jpg";
		String accessToken = access_token;
		try {
			boolean ret = WeChatInterfaceUtil.uploadMultiMedia(fileURI, accessToken,
					MultiMediaType.image);
			assertTrue(ret);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void testCreateCustomMenu() {
		Gson gson = new Gson();
		WeChatMenu menu = new WeChatMenu();
		List<MenuButton> btns = new ArrayList<MenuButton>();
		btns.add(new ViewButton("百度", "http://www.baidu.com"));
		ParentButton btn2=new ParentButton("网址导航");
		List<MenuButton> btn2subs=new ArrayList<MenuButton>();
		ViewButton btn21=new ViewButton("手机新浪", "http://sina.cn");
		btn2subs.add(btn21);
		ViewButton btn22=new ViewButton("网易", "http://www.163.com");
		btn2subs.add(btn22);
		EventButton btn23=new EventButton("点击事件1", "click00001");
		btn2subs.add(btn23);
		btn2.setSub_button(btn2subs);
		btns.add(btn2);
		btns.add(new ViewButton("自定义页面", "http://192.168.39.12/"));
		menu.setButton(btns);
		String menuStr = gson.toJson(menu);
		System.out.println(menuStr);
		try {
			boolean ret = WeChatInterfaceUtil.customMenu(menuStr, access_token);
			assertTrue(ret);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
