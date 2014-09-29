package com.ajita.wechat;

import java.io.StringReader;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;

import com.ajita.wechat.dto.AbstractMessage;
import com.ajita.wechat.util.MsgUtil;

import junit.framework.TestCase;

public class Dom4jTest extends TestCase {
	public void testDomParse() throws DocumentException {
		try {
			String xml = "<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[FromUser]]></FromUserName>\n<CreateTime>123456789</CreateTime>\n<MsgType><![CDATA[event]]></MsgType>\n<Event><![CDATA[subscribe]]></Event>\n</xml>";
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(new StringReader(xml));
			Node node = document.selectSingleNode("xml");
			Node toNode = node.selectSingleNode("ToUserName");
			String text = toNode.getText();
			System.out.println(text);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void testParseMsg() {
		try {
			String xml = "<xml>\n<ToUserName><![CDATA[toUser]]></ToUserName>\n<FromUserName><![CDATA[FromUser]]></FromUserName>\n<CreateTime>123456789</CreateTime>\n<MsgType><![CDATA[event]]></MsgType>\n<Event><![CDATA[subscribe]]></Event>\n</xml>";
			AbstractMessage msg = MsgUtil.parseMessage(xml);
			System.out.println(msg.getMsgType());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
