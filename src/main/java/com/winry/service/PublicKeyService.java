package com.winry.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.winry.dto.MyPublicKey;

@Service
public class PublicKeyService extends HttpService {

	public MyPublicKey getPublicKey() {
		String url = "https://passport.bilibili.com/login?act=getkey&_=" + new Date().getTime();
		return super.execute(MyPublicKey.class, url);
	}
}
