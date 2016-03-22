package com.winry.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SendController {

	@RequestMapping(value = "send/{txt}", method = RequestMethod.GET)
	@ResponseBody
	public String handleFileUpload(@PathVariable String txt) {
		return "sended txt:" + txt;
	}
}
