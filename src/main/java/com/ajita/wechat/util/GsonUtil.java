package com.ajita.wechat.util;

import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class GsonUtil {
	public static Map<String, String> parseMap(String json) {
		Gson gson = new Gson();
		return gson.fromJson(json, new TypeToken<Map<String, String>>() {
		}.getType());
		//return null;
	}
}
