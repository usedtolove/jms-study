package com.tz.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by hjl on 2016/2/24.
 */
@Controller
public class HiController {

    @Resource(name = "si-hiChannel")
    private MessageChannel myRequestChannel;

    @RequestMapping("/hi/{name}")
    public String send(@PathVariable String name) {
        myRequestChannel.send(MessageBuilder.withPayload(name).build());
        return "demo2";
    }

}
