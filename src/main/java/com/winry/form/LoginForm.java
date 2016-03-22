package com.winry.form;

import com.winry.util.FormUtil;
import okhttp3.RequestBody;

/**
 * Created by cong on 2016/3/22.
 */
public class LoginForm implements Form {

    public String userId;

    public String pwd;

    public String captcha = "";

    public String keep = "1";

    @Override
    public RequestBody build() {
        return FormUtil.toFormBody(this);
    }

}
