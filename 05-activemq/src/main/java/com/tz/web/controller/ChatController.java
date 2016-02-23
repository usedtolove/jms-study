package com.tz.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

    @RequestMapping(value = "/demo2", method = RequestMethod.GET)
    public String demo2(){
        System.out.println("demo2...");
        return "demo2";
    }
}
