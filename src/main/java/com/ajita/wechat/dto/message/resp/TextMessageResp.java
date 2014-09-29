package com.ajita.wechat.dto.message.resp;
import com.ajita.wechat.dto.MsgType;;

/**
 * 文本消息
 * 
 */
public class TextMessageResp extends BaseMessageResp {
	public TextMessageResp(){
		this.setMsgType(MsgType.text.toString());
	}
	// 回复的消息内容
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
}