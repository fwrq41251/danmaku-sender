package com.winry;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.Files;
import com.squareup.okhttp.HttpUrl;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.winry.dto.PublicKey;
import com.winry.util.EncryptionUtil;
import com.winry.util.JsonUtil;

public class BilibiliApiTest {

	@Test
	public void getPublicKey() throws IOException {
		OkHttpClient client = new OkHttpClient();
		HttpUrl url = new HttpUrl.Builder().scheme("https").host("passport.bilibili.com").addPathSegment("login")
				.addQueryParameter("act", "getkey").build();
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		String result = response.body().string();
		System.out.println(result);
		PublicKey publicKey = JsonUtil.toObject(PublicKey.class, result);
		System.out.println(publicKey);
	}

	@Test
	public void encryptPassword() throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
		// PublicKey publicKey = mapper.readValue(json, PublicKey.class);
		// String password = publicKey.getHash() + "580685";
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdScM09sZJqFPX7bvmB2y6i08J"
				+ "bHsa0v4THafPbJN9NoaZ9Djz1LmeLkVlmWx1DwgHVW+K7LVWT5FV3johacVRuV98"
				+ "37+RNntEK6SE82MPcl7fA++dmW2cLlAjsIIkrX+aIvvSGCuUfcWpWFy3YVDqhuHr" + "NDjdNcaefJIQHMW+sQIDAQAB";
		byte[] bytes = Base64.getDecoder().decode(publicKey.getBytes("utf-8"));
		X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		java.security.PublicKey key = keyFactory.generatePublic(spec);
		String enctrypted = Base64.getEncoder().encodeToString(EncryptionUtil.encrypt("580685", key));
		System.out.println(enctrypted);
	}

	@Test
	public void jsonTest() throws IOException {
		String json = Files.toString(new File("key"), Charset.forName("utf-8"));
		ObjectMapper mapper = new ObjectMapper();
		PublicKey publicKey = mapper.readValue(json, PublicKey.class);
		System.out.println(publicKey);
	}

	@Test
	public void bese64Test() throws UnsupportedEncodingException {
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdScM09sZJqFPX7bvmB2y6i08J"
				+ "bHsa0v4THafPbJN9NoaZ9Djz1LmeLkVlmWx1DwgHVW+K7LVWT5FV3johacVRuV98"
				+ "37+RNntEK6SE82MPcl7fA++dmW2cLlAjsIIkrX+aIvvSGCuUfcWpWFy3YVDqhuHr" + "NDjdNcaefJIQHMW+sQIDAQAB";
		byte[] bytes = Base64.getDecoder().decode(publicKey.getBytes("utf-8"));
		System.out.println(new String(bytes));
	}
}
