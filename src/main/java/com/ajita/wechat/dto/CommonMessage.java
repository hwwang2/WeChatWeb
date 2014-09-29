package com.ajita.wechat.dto;

/**
 * 消息基类
 * 
 * 关于重试的消息排重，推荐使用FromUserName + CreateTime 排重。
 * 
 * @author Administrator
 * 
 */
public class CommonMessage extends AbstractMessage {
	/**
	 * 消息ID，可以用来唯一标识一条消息
	 * 
	 * 关于重试的消息排重，推荐使用msgid排重。
	 */
	private String msgId;

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\nmsgId:" + msgId);
		return buffer.toString();
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
}
