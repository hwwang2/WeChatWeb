package com.ajita.wechat.util;

import java.io.InputStream;
import java.io.StringReader;
import java.io.Writer;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.ajita.wechat.dto.AbstractMessage;
import com.ajita.wechat.dto.CommonEvent;
import com.ajita.wechat.dto.EventType;
import com.ajita.wechat.dto.MsgType;
import com.ajita.wechat.dto.event.ClickEvent;
import com.ajita.wechat.dto.event.LocationEvent;
import com.ajita.wechat.dto.event.ScanEvent;
import com.ajita.wechat.dto.event.SubscribeEvent;
import com.ajita.wechat.dto.event.SubscribeScanEvent;
import com.ajita.wechat.dto.message.ImageMessage;
import com.ajita.wechat.dto.message.LinkMessage;
import com.ajita.wechat.dto.message.LocationMessage;
import com.ajita.wechat.dto.message.TextMessage;
import com.ajita.wechat.dto.message.VideoMessage;
import com.ajita.wechat.dto.message.VoiceMessage;
import com.ajita.wechat.dto.message.resp.BaseMessageResp;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * 消息辅助类，用来将开放平台传递来的报文转化为具体的消息
 * 
 * @author Administrator
 * 
 */
public class MsgUtil {
	static Logger logger = LogManager.getLogger(MsgUtil.class);
	/**
	 * 扩展xstream，使其支持CDATA块
	 * 
	 * @date 2013-05-19
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});

	/**
	 * 解析消息
	 * 
	 * @param xml
	 *            入参报文
	 * @return 返回的具体消息
	 */
	public static AbstractMessage parseMessage(String xml) {
		AbstractMessage msg = null;
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new StringReader(xml));
			Node root = document.selectSingleNode("xml");
			Node tempNode = root.selectSingleNode("MsgType");
			String msgType = tempNode.getText();

			msg = buildMessage(msgType, root);

			tempNode = root.selectSingleNode("ToUserName");
			String toUser = tempNode.getText();
			msg.setToUserName(toUser);

			tempNode = root.selectSingleNode("FromUserName");
			String fromUser = tempNode.getText();
			msg.setFromUserName(fromUser);

			tempNode = root.selectSingleNode("CreateTime");
			String createTime = tempNode.getText();
			msg.setCreateTime(createTime);
		} catch (Exception ex) {
			logger.error("解析消息失败，原始xml报文为：" + xml, ex);
		}
		return msg;
	}

	/**
	 * 解析消息
	 * 
	 * @param stream
	 *            输入流
	 * @return 返回的具体消息
	 */
	public static AbstractMessage parseMessage(InputStream stream) {
		AbstractMessage msg = null;
		try {
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(stream);
			Node root = document.selectSingleNode("xml");
			Node tempNode = root.selectSingleNode("MsgType");
			String msgType = tempNode.getText();

			msg = buildMessage(msgType, root);

			tempNode = root.selectSingleNode("ToUserName");
			String toUser = tempNode.getText();
			msg.setToUserName(toUser);

			tempNode = root.selectSingleNode("FromUserName");
			String fromUser = tempNode.getText();
			msg.setFromUserName(fromUser);

			tempNode = root.selectSingleNode("CreateTime");
			String createTime = tempNode.getText();
			msg.setCreateTime(createTime);
		} catch (Exception ex) {
			logger.error("从输入流解析消息失败", ex);
		}
		return msg;
	}

	private static AbstractMessage buildMessage(String msgType, Node root) {
		AbstractMessage msg = null;
		Node node = null;
		MsgType type = MsgType.valueOf(msgType);
		switch (type) {
		case text:
			TextMessage msg0 = new TextMessage();
			node = root.selectSingleNode("Content");
			msg0.setContent(node.getText());
			msg = msg0;
			break;
		case image:
			ImageMessage msg1 = new ImageMessage();
			node = root.selectSingleNode("PicUrl");
			msg1.setPicUrl(node.getText());
			node = root.selectSingleNode("MediaId");
			msg1.setMediaId(node.getText());
			break;
		case link:
			LinkMessage msg2 = new LinkMessage();
			node = root.selectSingleNode("Title");
			msg2.setTitle(node.getText());
			node = root.selectSingleNode("Url");
			msg2.setUrl(node.getText());
			node = root.selectSingleNode("Description");
			msg2.setDescription(node.getText());
			msg = msg2;
			break;
		case location:
			LocationMessage msg3 = new LocationMessage();
			node = root.selectSingleNode("Location_X");
			msg3.setLocation_X(Float.valueOf(node.getText()));
			node = root.selectSingleNode("Location_Y");
			msg3.setLocation_Y(Float.valueOf(node.getText()));
			node = root.selectSingleNode("Label");
			msg3.setLabel(node.getText());
			node = root.selectSingleNode("Scale");
			msg3.setScale(node.getText());
			msg = msg3;
			break;
		case video:
			VideoMessage msg4 = new VideoMessage();
			node = root.selectSingleNode("MediaId");
			msg4.setMediaId(node.getText());
			node = root.selectSingleNode("ThumbMediaId");
			msg4.setThumbMediaId(node.getText());
			msg = msg4;
			break;
		case voice:
			VoiceMessage msg5 = new VoiceMessage();
			node = root.selectSingleNode("MediaId");
			msg5.setMediaId(node.getText());
			node = root.selectSingleNode("Format");
			msg5.setFormat(node.getText());
			node = root.selectSingleNode("Recognition");
			if (node != null)
				msg5.setRecognition(node.getText());
			msg = msg5;
			break;
		default:
			String evtType = root.selectSingleNode("Event").getText();
			EventType eType = EventType.valueOf(evtType);
			CommonEvent event = buildEvent(eType, root);
			msg = event;
			break;
		}
		msg.setMsgType(type);
		return msg;
	}

	private static CommonEvent buildEvent(EventType eType, Node root) {
		CommonEvent evt = null;
		Node node = null;
		switch (eType) {
		case unsubscribe:
			SubscribeEvent evt1 = new SubscribeEvent();
			evt = evt1;
			break;
		case subscribe:
			node = root.selectSingleNode("EventKey");
			if (node == null) {
				SubscribeEvent evt2 = new SubscribeEvent();
				evt = evt2;
			} else {
				SubscribeScanEvent evt3 = new SubscribeScanEvent();
				evt3.setEventKey(node.getText());
				node = root.selectSingleNode("Ticket");
				evt3.setTicket(node != null ? node.getText() : "");
				evt = evt3;
			}
			break;
		case SCAN:
			ScanEvent evt4 = new ScanEvent();
			node = root.selectSingleNode("EventKey");
			evt4.setEventKey(node.getText());
			node = root.selectSingleNode("Ticket");
			evt4.setTicket(node != null ? node.getText() : "");
			evt = evt4;
			break;
		case LOCATION:
			LocationEvent evt5 = new LocationEvent();
			node = root.selectSingleNode("Latitude");
			evt5.setLatitude(node.getText());
			node = root.selectSingleNode("Longitude");
			evt5.setLongitude(node.getText());
			node = root.selectSingleNode("Precision");
			evt5.setPrecision(node.getText());
			evt = evt5;
			break;
		case CLICK:
			ClickEvent evt6 = new ClickEvent();
			node = root.selectSingleNode("EventKey");
			evt6.setEventKey(node.getText());
			evt = evt6;
			break;
		default:
			break;
		}
		evt.setEvent(eType);
		return evt;
	}

	public static String serializeMessage(BaseMessageResp msg) {
		xstream.alias("xml", msg.getClass());
		return xstream.toXML(msg);
	}
}
