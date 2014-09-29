package com.ajita.wechat.dto.custommenu;

/**
 * 打开某个连接的菜单
 * 
 * @author Administrator
 * 
 */
public class ViewButton extends MenuButton {
	public ViewButton() {
	}

	public ViewButton(String name, String url) {
		this.setName(name);
		this.url = url;
	}

	/**
	 * click： 用户点击click类型按钮后，微信服务器会通过消息接口推送消息类型为event
	 * 的结构给开发者（参考消息接口指南），并且带上按钮中开发者填写的key值，开发者可以通过自定义的key值与用户进行交互； view：
	 * 用户点击view类型按钮后，微信客户端将会打开开发者在按钮中填写的url值
	 * （即网页链接），达到打开网页的目的，建议与网页授权获取用户基本信息接口结合，获得用户的登入个人信息。
	 */
	public final String type = "view";
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
