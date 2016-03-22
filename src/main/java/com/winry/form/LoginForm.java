package com.winry.form;

import com.winry.util.FormUtil;
import okhttp3.RequestBody;

/**
 * Created by cong on 2016/3/22.
 */
public class LoginForm implements Form {

    public String userId;

    public String pwd;

    public String captcha;

    public String keep;

    @Override
    public RequestBody build() {
        this.captcha = "";
        this.keep = "1";
        return FormUtil.toFormBody(this);
    }

}
