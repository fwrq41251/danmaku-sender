package com.winry.http;

import com.winry.dto.PublicKey;
import com.winry.form.LoginForm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ApiService extends HttpService {

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    public PublicKey getPublicKey() {
        return super.execute(PublicKey.class, "https://passport.bilibili.com/login?act=getkey");
    }

    public void login() {
        super.execute(null, "https://account.bilibili.com/ajax/miniLogin/login", new LoginForm());
    }

    public void send(String txt) {

    }
}
