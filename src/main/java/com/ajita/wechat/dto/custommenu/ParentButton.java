package com.ajita.wechat.dto.custommenu;

import java.util.List;

/**
 * 包含二级菜单的一级菜单
 * 
 * @author Administrator
 * 
 */
public class ParentButton extends MenuButton {
	public ParentButton() {
	}

	public ParentButton(String name) {
		this.setName(name);
	}

	private List<MenuButton> sub_button;

	public List<MenuButton> getSub_button() {
		return sub_button;
	}

	public void setSub_button(List<MenuButton> sub_button) {
		this.sub_button = sub_button;
	}
}
