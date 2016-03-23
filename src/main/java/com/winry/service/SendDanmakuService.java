package com.winry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.winry.context.CookieContext;
import com.winry.dto.SendMsgResult;
import com.winry.form.SendMsgForm;
import com.winry.util.HttpHelper;

@Service
public class SendDanmakuService {

	@Value("${roomid}")
	private String roomId;

	@Autowired
	private CookieContext cookieContext;

	public SendMsgResult send(String msg) {
		return this.send(this.roomId, msg);
	}

	public SendMsgResult send(String roomId, String msg) {
		SendMsgForm form = new SendMsgForm();
		form.roomId = roomId;
		form.msg = msg;
		return HttpHelper.url("http://live.bilibili.com/msg/send").form(form).header(cookieContext.getHeader())
				.execute(SendMsgResult.class);
	}
}
