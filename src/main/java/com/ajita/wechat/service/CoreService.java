package com.ajita.wechat.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.ajita.wechat.dto.AbstractMessage;
import com.ajita.wechat.dto.CommonEvent;
import com.ajita.wechat.dto.CommonMessage;
import com.ajita.wechat.dto.EventType;
import com.ajita.wechat.dto.MsgType;
import com.ajita.wechat.dto.event.ClickEvent;
import com.ajita.wechat.dto.message.LocationMessage;
import com.ajita.wechat.dto.message.TextMessage;
import com.ajita.wechat.dto.message.resp.TextMessageResp;
import com.ajita.wechat.service.baidu.BaiduHandler;
import com.ajita.wechat.util.MsgUtil;

/**
 * 核心服务类
 * 
 */
public class CoreService {

	private static DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest2(HttpServletRequest request) {
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";
			AbstractMessage msg = MsgUtil
					.parseMessage(request.getInputStream());
			if (msg.isEvent()) {
				CommonEvent evt = (CommonEvent) msg;
				EventType evtType = evt.getEvent();
				switch (evtType) {
				case CLICK:
					ClickEvent clkevt = (ClickEvent) evt;
					String key = clkevt.getEventKey();
					respContent = String.format("你点击了key为%s的按钮菜单！", key);
					break;
				case subscribe:
					respContent = "谢谢您的关注！！！";
					break;
				default:
					respContent = String.format("你触发了%s类型的事件！", evtType);
				}
			} else {
				CommonMessage cmsg = (CommonMessage) msg;
				MsgType mType = cmsg.getMsgType();
				switch (mType) {
				case text:
					TextMessage tMsg = (TextMessage) cmsg;
					respContent = BaiduHandler.handleTxtMessage(tMsg
							.getContent());
					break;
				case location:
					LocationMessage lMsg = (LocationMessage) cmsg;
					respContent = BaiduHandler.handleLocationMessage(
							lMsg.getLocation_X(), lMsg.getLocation_Y());
					break;
				default:
					respContent = String.format("你发送了%s类型的消息！", mType);
				}
			}

			TextMessageResp resp = new TextMessageResp();
			resp.setBasicInfoFromReq(msg);
			resp.setContent(respContent);
			respMessage = MsgUtil.serializeMessage(resp);

			System.out.println(String.format("%s~~%s~~%s~~%s",
					msg.getFromUserName(), msg.getMsgType(), respMessage,
					dateFormat.format(new Date())));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return respMessage;
	}
}
