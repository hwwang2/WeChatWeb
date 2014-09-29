package com.ajita.wechat.dto;

/**
 * 消息及事件的基类
 * 
 * @author Administrator
 * 
 */
public abstract class AbstractMessage {
	private String toUserName;// 接收方
	private String fromUserName;// 发送方
	private String createTime;// 消息创建时间
	private MsgType msgType;// 消息类型

	public boolean isEvent() {
		return MsgType.event.equals(msgType);
	}

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("toUserName:" + toUserName);
		buffer.append("\nfromUserName:" + toUserName);
		buffer.append("\nmsgType:" + toUserName);
		buffer.append("\ncreateTime:" + toUserName);
		return buffer.toString();
	}

	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public MsgType getMsgType() {
		return msgType;
	}

	public void setMsgType(MsgType msgType) {
		this.msgType = msgType;
	}
}
