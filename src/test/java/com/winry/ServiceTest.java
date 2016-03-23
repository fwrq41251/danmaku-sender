package com.winry;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.winry.dto.LoginResult;
import com.winry.dto.SendMsgResult;
import com.winry.service.LoginService;
import com.winry.service.SendDanmakuService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class ServiceTest {

	@Autowired
	private LoginService loginService;

	@Autowired
	private SendDanmakuService sendDanmakuService;

	@Test
	public void loginTest() {
		LoginResult result = loginService.login();
		Assert.assertEquals(true, result.success());
		System.out.println(result.getData().getCrossDomain());
	}

	@Test
	public void sendTest() {
		String msg = LocalDate.now().toString();
		SendMsgResult result = sendDanmakuService.send(msg);
		Assert.assertEquals(true, result.success());
		System.out.println(result.getMsg());
	}
}
