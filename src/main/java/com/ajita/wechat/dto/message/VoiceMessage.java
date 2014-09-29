package com.ajita.wechat.dto.message;

import com.ajita.wechat.dto.CommonMessage;

/**
 * 音频消息
 * 
 * @author Administrator
 * 
 */
public class VoiceMessage extends CommonMessage {
	/**
	 * 语音消息媒体id，可以调用多媒体文件下载接口拉取数据
	 */
	private String mediaId;
	/**
	 * 语音格式，如amr，speex等
	 */
	private String format;
	/**
	 * 语音识别结果
	 */
	private String recognition;

	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append(super.toString());
		buffer.append("\nmediaId:" + mediaId);
		buffer.append("\nformat:" + format);
		buffer.append("\nrecognition:" + recognition);
		return buffer.toString();
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getRecognition() {
		return recognition;
	}

	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}
}
