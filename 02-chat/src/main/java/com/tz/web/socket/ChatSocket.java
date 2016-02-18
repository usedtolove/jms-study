package com.tz.web.socket;

import com.tz.bean.Message;
import com.tz.bean.OutputMessage;
import com.tz.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by hjl on 2016/2/18.
 */
@Controller
public class ChatSocket {

    @Autowired
    HelloService helloService;

    @MessageMapping(value = "/chat") //<== Endpoint
    @SendTo(value = "/topic/message") //<==Broker
    public OutputMessage handleMessage(Message message){
        System.out.println("handleMessage...:"+message);
        OutputMessage outputMessage = helloService.sayHi(message);
        System.out.println("outputMessage:"+outputMessage);
        return outputMessage;
    }

}
