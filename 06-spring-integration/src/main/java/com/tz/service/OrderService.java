package com.tz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.samples.cafe.Order;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * Created by hjl-game on 2016/2/24.
 */
@Service(value = "orderService")
public class OrderService {

    @Autowired
    JmsTemplate jmsTemplate;

    public void serve(Order order){
        System.out.println("OrderService serve() run...");
        System.out.println("order:"+order);

        System.out.println("Sending a text message...");
//        jmsTemplate.send("mailbox-destination", messageCreator);

        MessageCreator messageCreator = new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage("ping!");
            }
        };

        jmsTemplate.send("/app/chat", messageCreator);

    }


}
