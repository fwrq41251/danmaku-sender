package com.winry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.winry.dto.LoginResult;
import com.winry.form.LoginForm;
import com.winry.util.HttpHelper;

@Service
public class LoginService {

	@Value("${userid}")
	private String username;

	@Value("${password}")
	private String password;

	@Autowired
	private PasswordService passwordService;

	/**
	 * login with default username and password
	 * 
	 * @return
	 */
	public LoginResult login() {
		return login(this.username, this.password);
	}

	/**
	 * login with given username and password
	 * 
	 * @return
	 */
	public LoginResult login(String username, String password) {
		LoginForm form = new LoginForm();
		form.userId = username;
		form.pwd = passwordService.encrypt(password);
		return HttpHelper.url("https://passport.bilibili.com/ajax/miniLogin/login").form(form)
				.execute(LoginResult.class);
	}
}
