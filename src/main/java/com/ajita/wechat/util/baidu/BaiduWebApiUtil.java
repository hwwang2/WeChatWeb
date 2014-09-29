package com.ajita.wechat.util.baidu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import com.google.gson.Gson;

/**
 * 百度地图Web API的调用
 * 
 * @author Administrator
 * 
 */
public class BaiduWebApiUtil {
	private static String ak = "qzIgYIRjVCS5DVd8A8j8GUGH";

	/**
	 * 查询某地的经纬度地理位置信息
	 * 
	 * @param place
	 *            查询的具体位置
	 * @param city
	 *            位置所在的城市，没有为null，填了之后查出的可能性更大
	 * @return 返回具体的经纬度信息，未查询出来为空
	 * @throws IOException
	 *             网络失败异常信息
	 */
	public static GeoLocation geocodingCity2XY(String place, String city)
			throws IOException {
		GeoLocation loc = null;
		String url = String
				.format("http://api.map.baidu.com/geocoder/v2/?address=%s&output=json&ak=%s",
						URLEncoder.encode(place, "UTF-8"), ak);
		if (city != null)
			url += "&city=" + URLEncoder.encode(city, "UTF-8");
		String result = sendHttpRequest(url, "GET", null, null);

		Gson gson = new Gson();
		BaiduResult ret1 = gson.fromJson(result, BaiduResult.class);
		if (ret1.getStatus() == 0) {
			loc = gson.fromJson(gson.toJson(ret1.getResult()),
					GeoLocation.class);
		} else {
			// TODO 处理百度返回的异常信息
		}

		return loc;
	}

	/**
	 * 逆地理编码，根据经纬度查询位置
	 * 
	 * @param longitude
	 *            经度
	 * @param latitude
	 *            纬度
	 * @return 返回地理位置信息
	 * @throws IOException
	 *             网络异常
	 */
	public static GeoAddress geocodingXY2City(float longitude, float latitude)
			throws IOException {
		GeoAddress addr = null;
		String url = String
				.format("http://api.map.baidu.com/geocoder/v2/?location=%f,%f&ak=%s&output=json&pois=1",
						latitude, longitude, ak);
		String result = sendHttpRequest(url, "GET", null, null);

		Gson gson = new Gson();
		BaiduResult ret1 = gson.fromJson(result, BaiduResult.class);
		if (ret1.getStatus() == 0) {
			addr = gson.fromJson(gson.toJson(ret1.getResult()),
					GeoAddress.class);
		} else {
			// TODO 处理百度返回的异常信息
		}
		return addr;
	}

	public static IpLocation getAddressByIp(String ip) throws IOException {
		IpLocation location = null;
		String url = String.format(
				"http://api.map.baidu.com/location/ip?ip=%s&ak=%s&coor=bd09ll",
				ip, ak);
		String result = sendHttpRequest(url, "GET", null, null);

		Gson gson = new Gson();
		BaiduResult ret1 = gson.fromJson(result, BaiduResult.class);
		if (ret1.getStatus() == 0) {
			location = gson.fromJson(result, IpLocation.class);
		} else {
			// TODO 处理百度返回的异常信息
		}

		return location;
	}

	/**
	 * 发送HTTP请求
	 * 
	 * @param urlString
	 *            请求url
	 * @param method
	 *            请求类型：GET或者POST
	 * @param parameters
	 *            传入参数
	 * @param properties
	 *            HTTP连接属性
	 * @return 返回响应内容
	 * @throws IOException
	 *             网络异常
	 */
	public static String sendHttpRequest(String urlString, String method,
			Map<String, String> parameters, Map<String, String> properties)
			throws IOException {
		HttpURLConnection urlConnection = null;

		if (method.equalsIgnoreCase("GET") && parameters != null) {
			StringBuffer param = new StringBuffer();
			int i = urlString.indexOf("?") > 0 ? 1 : 0;
			for (String key : parameters.keySet()) {
				if (i == 0)
					param.append("?");
				else
					param.append("&");
				String tmp = URLEncoder.encode(parameters.get(key), "UTF-8");
				param.append(key).append("=").append(tmp);
				i++;
			}
			urlString += param;
		}
		URL url = new URL(urlString);
		urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setRequestMethod(method);
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		urlConnection.setUseCaches(false);

		if (properties != null)
			for (String key : properties.keySet()) {
				urlConnection.addRequestProperty(key, properties.get(key));
			}
		else {
			urlConnection.setRequestProperty("Content-Type",
					"text/html;charset=utf-8");
		}

		if (method.equalsIgnoreCase("POST") && parameters != null) {
			StringBuffer param = new StringBuffer();
			for (String key : parameters.keySet()) {
				param.append("&");
				param.append(key).append("=").append(parameters.get(key));
			}
			urlConnection.getOutputStream().write(param.toString().getBytes());
			urlConnection.getOutputStream().flush();
			urlConnection.getOutputStream().close();
		}

		try {
			InputStream in = urlConnection.getInputStream();

			String ecod = urlConnection.getContentEncoding();
			if (ecod == null) {
				ecod = getEncodeFromUrlConnection(urlConnection);
			}
			if (ecod == null)
				ecod = "UTF-8";

			BufferedReader bufferedReader = new BufferedReader(
					new InputStreamReader(in, ecod));
			StringBuffer temp = new StringBuffer();
			String line = bufferedReader.readLine();
			while (line != null) {
				temp.append(line).append("\r\n");
				line = bufferedReader.readLine();
			}
			bufferedReader.close();

			return temp.toString();
		} catch (IOException e) {
			throw e;
		} finally {
			if (urlConnection != null)
				urlConnection.disconnect();
		}
	}

	/**
	 * 获取HTTP连接返回的字符编码格式
	 * 
	 * @param urlConnection
	 * @return
	 */
	public static String getEncodeFromUrlConnection(
			HttpURLConnection urlConnection) {
		String ecod = urlConnection.getContentEncoding();
		if (ecod == null) {
			String contentType = urlConnection.getContentType();
			if (contentType != null && contentType.indexOf(";") > 0) {
				String[] arr = contentType.split(";");
				for (String item : arr) {
					if (item != null && item.indexOf("=") > 0) {
						String[] arr2 = item.split("=");
						if (arr2 != null && arr2.length > 1
								&& "charset".equalsIgnoreCase(arr2[0])) {
							ecod = arr2[1];
							break;
						}
					}
				}
			}
		}
		return ecod;
	}

}
