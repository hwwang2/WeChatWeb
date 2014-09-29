package com.ajita.wechat.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InterfaceController {
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public void validate(HttpServletRequest request, HttpServletResponse resp) {
		String echostr = request.getParameter("echostr");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String[] wxreqs = new String[] { "wechat", timestamp, nonce };
		Arrays.sort(wxreqs);
		String initSHA1 = wxreqs[0] + wxreqs[1] + wxreqs[2];
		SHA1 sha1 = new SHA1();
		initSHA1 = sha1.Encrypt(initSHA1);

		try {
			if (!"".equals(echostr) && echostr != null
					&& signature.equals(initSHA1)) {
				PrintWriter writer;
				writer = resp.getWriter();
				writer.write(echostr);
				writer.flush();
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/chat", method = RequestMethod.POST)
	public void handleMsg(HttpServletRequest request, HttpServletResponse resp) {
		String echostr = request.getParameter("echostr");
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String[] wxreqs = new String[] { "wechat", timestamp, nonce };
		Arrays.sort(wxreqs);
		String initSHA1 = wxreqs[0] + wxreqs[1] + wxreqs[2];
		SHA1 sha1 = new SHA1();
		initSHA1 = sha1.Encrypt(initSHA1);

		try {
			if (!"".equals(echostr) && echostr != null
					&& signature.equals(initSHA1)) {
				PrintWriter writer;
				writer = resp.getWriter();
				writer.write(echostr);
				writer.flush();
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
