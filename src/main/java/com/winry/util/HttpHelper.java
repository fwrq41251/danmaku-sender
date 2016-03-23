package com.winry.util;

import java.io.IOException;

import com.winry.form.Form;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;

/**
 * Created by cong on 2016/3/22.
 */
public class HttpHelper {

	private Builder builder;

	protected HttpHelper(Builder builder) {
		super();
		this.builder = builder;
	}

	public static HttpHelper url(String url) {
		HttpHelper helper = new HttpHelper(new Request.Builder().url(url));
		return helper;
	}

	public HttpHelper form(Form form) {
		builder.post(form.build());
		return this;
	}

	public HttpHelper header(Tuple_2 head) {
		builder.addHeader(head._1(), head._2());
		return this;
	}

	public <T> T execute(Class<T> Clazz) {
		OkHttpClient client = new OkHttpClient();
		Request request = builder.build();
		try {
			Response response = client.newCall(request).execute();
			String result = response.body().string();
			return JsonUtil.toObject(Clazz, result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

}
