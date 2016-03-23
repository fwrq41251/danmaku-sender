package com.winry.form;

import java.util.Date;

/**
 * Created by cong on 2016/3/23.
 */
public class SendMsgForm extends Form {

	public String color = "16777215";

	public String fontSize = "25";

	public String mode = "1";

	public String msg;

	public String rnd = "" + new Date().getTime();

	public String roomId;
}
