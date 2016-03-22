package com.winry.form;

import com.winry.util.FormUtil;
import okhttp3.RequestBody;

import java.util.Date;

/**
 * Created by cong on 2016/3/23.
 */
public class SendMsgForm implements Form {

    public String color = "16777215";

    public String fontSize = "25";

    public String mode = "1";

    public String msg;

    public String rnd = "" + new Date().getTime();

    public String roomId;

    @Override
    public RequestBody build() {
        return FormUtil.toFormBody(this);
    }
}
