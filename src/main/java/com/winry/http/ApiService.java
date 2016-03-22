package com.winry.http;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.winry.dto.PublicKey;
import com.winry.util.JsonUtil;

@Service
public class ApiService {

	@Value("${username}")
	private String username;

	@Value("${password}")
	private String password;

	private static OkHttpClient client = new OkHttpClient();

	public PublicKey getPublicKey() {
		Request request = new Request.Builder().url("https://passport.bilibili.com/login?act=getkey").build();
		try {
			Response response = client.newCall(request).execute();
			String result = response.body().string();
			return JsonUtil.toObject(PublicKey.class, result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void login() {
		Request request = new Request.Builder().url("https://account.bilibili.com/ajax/miniLogin/login").build();
		try {
			Response response = client.newCall(request).execute();
			String result = response.body().string();
			System.out.println(result);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public void send(String txt) {

	}
}
