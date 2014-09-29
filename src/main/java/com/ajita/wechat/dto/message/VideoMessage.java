package com.ajita.wechat.dto.message;

import com.ajita.wechat.dto.CommonMessage;

/**
 * 视频消息
 * 
 * @author Administrator
 * 
 */
public class VideoMessage extends CommonMessage {
	/**
	 * 视频消息媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	private String mediaId;
	/**
	 * 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据。
	 */
	private String thumbMediaId;

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\nmediaId:" + mediaId);
		buffer.append("\nthumbMediaId:" + thumbMediaId);
		return buffer.toString();
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getThumbMediaId() {
		return thumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
	}
}
