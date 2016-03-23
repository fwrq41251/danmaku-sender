package com.winry.service;

import java.io.IOException;

import com.winry.form.Form;
import com.winry.util.JsonUtil;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by cong on 2016/3/22.
 */
public class HttpService {

	private OkHttpClient client = new OkHttpClient();

	public <T> T execute(Class<T> Clazz, String url) {
		Request request = getBuilder(url).build();
		return call(Clazz, request);
	}

	public <T> T execute(Class<T> Clazz, String url, Form form) {
		RequestBody formBody = form.build();
		Request request = getBuilder(url).post(formBody).build();
		return call(Clazz, request);
	}

	private Builder getBuilder(String url) {
		return new Request.Builder().url(url);
	}

	private <T> T call(Class<T> Clazz, Request request) {
		try {
			Response response = client.newCall(request).execute();
			String result = response.body().string();
			return JsonUtil.toObject(Clazz, result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
