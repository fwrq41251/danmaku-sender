package com.winry.service;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.winry.dto.MyPublicKey;
import com.winry.util.HttpHelper;

@Service
public class PublicKeyService {

	public MyPublicKey getPublicKey() {
		String url = "https://passport.bilibili.com/login?act=getkey&_=" + new Date().getTime();
		return HttpHelper.url(url).execute(MyPublicKey.class);
	}
}
