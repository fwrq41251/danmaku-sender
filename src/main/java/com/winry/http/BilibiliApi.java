package com.winry.http;

import java.io.IOException;

import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.winry.dto.PublicKey;
import com.winry.util.JsonUtil;

public class BilibiliApi {

	private static OkHttpClient client = new OkHttpClient();

	public static PublicKey getPublicKey() {
		HttpUrl url = new HttpUrl.Builder().scheme("https").host("passport.bilibili.com").addPathSegment("login")
				.addQueryParameter("act", "getkey").build();
		Request request = new Request.Builder().url(url).build();
		try {
			Response response = client.newCall(request).execute();
			String result = response.body().string();
			return JsonUtil.toObject(PublicKey.class, result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
