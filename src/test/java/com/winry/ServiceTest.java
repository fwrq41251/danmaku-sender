package com.winry;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.winry.dto.LoginResult;
import com.winry.service.LoginService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(App.class)
public class ServiceTest {

	@Autowired
	private LoginService loginService;

	@Test
	public void loginTest() {
		LoginResult result = loginService.login();
		Assert.assertEquals(true, result.success());
		System.out.println(result.getData().getCrossDomain());
	}
}
