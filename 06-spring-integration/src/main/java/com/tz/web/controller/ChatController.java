package com.tz.web.controller;

import com.tz.bean.Message;
import com.tz.bean.OutputMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Created by hjl on 2016/2/18.
 */
@Controller
public class ChatController {

    @RequestMapping(value = "/demo1", method = RequestMethod.GET)
    public String demo1(){
        System.out.println("demo1...");
        return "demo1";
    }

}
