package com.winry.controller;

import com.winry.dto.SendMsgResult;
import com.winry.service.SendDanmakuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SendController {

    @Autowired
    private SendDanmakuService sendDanmakuService;

    @RequestMapping(value = "send/{msg}", method = RequestMethod.GET)
    @ResponseBody
    public String send(@PathVariable String msg) {
        SendMsgResult result = sendDanmakuService.send(msg);
        return "success:" + result.success();
    }

    @RequestMapping(value = "send/{roomId}/{msg}", method = RequestMethod.GET)
    @ResponseBody
    public String send(@PathVariable String roomId, @PathVariable String msg) {
        SendMsgResult result = sendDanmakuService.send(roomId, msg);
        return "success:" + result.success();
    }
}
