package com.ajita.wechat.dto.message;

import com.ajita.wechat.dto.CommonMessage;

/**
 * 图片消息
 * 
 * @author Administrator
 * 
 */
public class ImageMessage extends CommonMessage {
	private String picUrl;// 图片链接
	private String mediaId;// 图片消息媒体id，可以调用多媒体文件下载接口拉取数据。

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\npicUrl:" + picUrl);
		buffer.append("\nmediaId:" + mediaId);
		return buffer.toString();
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
}
