package com.ajita.wechat.dto.custommenu;

import java.util.List;

/**
 * 微信自定义菜单，可直接序列化为满足微信要求的菜单
 * 
 * @author Administrator
 * 
 */
public class WeChatMenu {
	private List<MenuButton> button;

	public List<MenuButton> getButton() {
		return button;
	}

	public void setButton(List<MenuButton> button) {
		this.button = button;
	}
}
