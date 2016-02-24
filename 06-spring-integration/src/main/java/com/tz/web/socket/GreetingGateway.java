package com.tz.web.socket;

import org.springframework.integration.annotation.Gateway;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

/**
 * Created by hjl on 2016/2/24.
 */
@MessagingGateway
@Controller
public interface GreetingGateway {

    @MessageMapping("/greetingGw")
    @SendToUser(value = "/queue/answer", broadcast = true)
    @Gateway(requestChannel = "greetingChannel")
    void greeting(String payload);

}
