package com.ajita.wechat.util;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.ajita.wechat.common.MultiMediaType;

public class WeChatInterfaceUtil {
	private static Logger logger = LogManager
			.getLogger(WeChatInterfaceUtil.class);

	/**
	 * access_token是公众号的全局唯一票据，公众号调用各接口时都需使用access_token。
	 * 正常情况下access_token有效期为7200秒，重复获取将导致上次获取的access_token失效。
	 * 
	 * 根据appid和appSecret获取微信的access_token
	 * 
	 * @param appId
	 *            第三方用户唯一凭证
	 * @param appSecret
	 *            第三方用户唯一凭证密钥，即appsecret
	 */
	public static Map<String, String> getAccessToken(String appId,
			String appSecret) {
		HttpClient httpClient = HttpClientBuilder.create().build();
		String url = String
				.format("https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=%s&secret=%s",
						appId, appSecret);
		HttpUriRequest request = new HttpGet(url);

		try {
			// 发送请求，返回响应
			HttpResponse response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				ContentType type = ContentType.getOrDefault(entity);
				Charset charSet = type.getCharset();
				if (charSet == null) {
					charSet = getOrDefaultCharset(type);
				}
				byte[] bytes = EntityUtils.toByteArray(entity);
				String result = new String(bytes, charSet);

				Map<String, String> ret = GsonUtil.parseMap(result);
				if (ret.containsKey("access_token"))
					return ret;

				logger.error("调用微信获取AccessToken接口返回结果错误" + result);
			} else {
				throw new Exception("接口返回HTTP状态错误，非200");
			}
		} catch (ClientProtocolException e) {
			logger.error("调用微信获取AccessToken接口协议异常", e);
		} catch (IOException e) {
			logger.error("调用微信获取AccessToken接口IO异常", e);
		} catch (Exception e) {
			logger.error("调用微信获取AccessToken接口异常", e);
		}
		return null;
	}

	/**
	 * 上传多媒体文件
	 * 
	 * @param fileURI
	 *            文件URI
	 * @param accessToken
	 *            缓存的身份标识Token
	 * @param type
	 *            文件类型
	 * @return
	 * @throws IOException
	 */
	public static boolean uploadMultiMedia(String fileURI, String accessToken,
			MultiMediaType multiMediaType) throws IOException {
		String url = String
				.format("http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=%s&type=%s",
						accessToken, multiMediaType);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {

			HttpPost httppost = new HttpPost(url);
			FileBody bin = new FileBody(new File(fileURI),
					ContentType.MULTIPART_FORM_DATA);
			StringBody comment = new StringBody("A binary file of some kind",
					ContentType.TEXT_PLAIN);
			HttpEntity reqEntity = MultipartEntityBuilder.create()
					.addPart("media", bin).addPart("comment", comment).build();
			httppost.setEntity(reqEntity);
			System.out
					.println("executing request " + httppost.getRequestLine());
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				System.out.println("----------------------------------------");
				System.out.println(response.getStatusLine());
				HttpEntity resEntity = response.getEntity();

				ContentType type = ContentType.getOrDefault(resEntity);
				Charset charSet = type.getCharset();
				if (charSet == null) {
					charSet = getOrDefaultCharset(type);
				}
				byte[] bytes = EntityUtils.toByteArray(resEntity);
				String result = new String(bytes, charSet);

				Map<String, String> ret = GsonUtil.parseMap(result);
				if (ret.containsKey("errcode")
						&& "0".equals(ret.get("errcode")))
					return true;
//				EntityUtils.consume(resEntity);
//				if (resEntity != null) {
//					System.out.println("Response content length: "
//							+ resEntity.getContentLength());
//					System.out.println("Response content: "
//							+ resEntity.getContent());
//				}

			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return false;
	}

	/**
	 * 创建自定义菜单
	 * 目前自定义菜单最多包括3个一级菜单，每个一级菜单最多包含5个二级菜单。一级菜单最多4个汉字，二级菜单最多7个汉字，多出来的部分将会以
	 * “...”代替。请注意，创建自定义菜单后，由于微信客户端缓存，需要24小时微信客户端才会展现出来。建议测试时可以尝试取消关注公众账号后再次关注，
	 * 则可以看到创建后的效果。
	 * 
	 * @param menuJson
	 *            自定义菜单的json字符串 示例： { "button":[ { "type":"click",
	 *            "name":"今日歌曲", "key":"V1001_TODAY_MUSIC" }, { "type":"click",
	 *            "name":"歌手简介", "key":"V1001_TODAY_SINGER" }, { "name":"菜单",
	 *            "sub_button":[ { "type":"view", "name":"搜索",
	 *            "url":"http://www.soso.com/" }, { "type":"view", "name":"视频",
	 *            "url":"http://v.qq.com/" }, { "type":"click", "name":"赞一下我们",
	 *            "key":"V1001_GOOD" } ] }] }
	 * @return 自定义菜单是否成功
	 */
	public static boolean customMenu(String menuJson, String accessToken)
			throws IOException {
		String url = String
				.format("https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s",
						accessToken);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {

			HttpPost httppost = new HttpPost(url);
			// httppost.setEntity(new UrlEncodedFormEntity("", HTTP.UTF_8));
			httppost.setEntity(new StringEntity(menuJson, "UTF-8"));
			CloseableHttpResponse response = httpclient.execute(httppost);
			try {
				HttpEntity resEntity = response.getEntity();

				ContentType type = ContentType.getOrDefault(resEntity);
				Charset charSet = type.getCharset();
				if (charSet == null) {
					charSet = getOrDefaultCharset(type);
				}
				byte[] bytes = EntityUtils.toByteArray(resEntity);
				String result = new String(bytes, charSet);

				Map<String, String> ret = GsonUtil.parseMap(result);
				if (ret.containsKey("errcode")
						&& "0".equals(ret.get("errcode")))
					return true;
			} finally {
				response.close();
			}
		} finally {
			httpclient.close();
		}
		return false;
	}

	private static Charset getOrDefaultCharset(ContentType type) {
		String charset = type.getParameter("encoding");
		return Charset.forName(charset != null ? charset : "UTF-8");
	}
}
