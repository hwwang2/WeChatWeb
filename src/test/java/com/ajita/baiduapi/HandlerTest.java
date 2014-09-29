package com.ajita.baiduapi;

import com.ajita.wechat.service.BaiduHandler;

import junit.framework.TestCase;

public class HandlerTest extends TestCase {
	public void testBaiduTxtHandler() {
		String result = BaiduHandler.handleTxtMessage("你好");
		
		assertNotNull(result);
	}
}
