package com.test;

import com.tz.jms.JmsConfig;
import com.tz.service.AppConfig;
import com.tz.web.socket.SocketConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hjl on 2016/2/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {
        AppConfig.class
        ,JmsConfig.class
})
public class TestJms {

    @Autowired
    JmsTemplate jmsTemplate;

    @Test
    public void test1(){
        Map<String, String> map = new HashMap();
        map.put("key1", "value1");
        map.put("key2", "value2");
        jmsTemplate.convertAndSend(map);

        //todo...
        //https://dzone.com/articles/spring-jms-activemq
    }

}
