package com.tz.web.socket;

import com.tz.bean.Message;
import com.tz.bean.OutputMessage;
import com.tz.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by hjl on 2016/2/18.
 */
@Controller
public class ChatSocket {

    @Autowired
    HelloService helloService;

    @MessageMapping(value = "/chat") //<== Endpoint
//    @SendTo(value = "/topic/message") //<==Broker
    @SendTo(value = "/names") //<==Broker
    public OutputMessage handleMessage(Message message){
        System.out.println("handleMessage...:"+message);
        OutputMessage outputMessage = helloService.sayHi(message);
        System.out.println("outputMessage:"+outputMessage);
        return outputMessage;
    }

}
