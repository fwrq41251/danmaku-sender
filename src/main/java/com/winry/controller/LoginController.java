package com.winry.controller;

import com.winry.context.CookieContext;
import com.winry.dto.LoginResult;
import com.winry.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by cong on 2016/3/23.
 */
@Controller
public class LoginController {

    @Autowired
    private LoginService loginService;
    @Autowired
    private CookieContext cookieContext;

    @RequestMapping(value = "login/{username}/{password}", method = RequestMethod.GET)
    @ResponseBody
    public String login(@PathVariable String username, @PathVariable String password) {
        LoginResult result = loginService.login(username, password);
        cookieContext.refresh(result);
        return "success:" + result.success();
    }
}
