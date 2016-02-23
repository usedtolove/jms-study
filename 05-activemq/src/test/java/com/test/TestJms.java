package com.test;

import com.alibaba.fastjson.JSON;
import com.tz.jms.JmsConfig;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.Destination;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hjl on 2016/2/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { JmsConfig.class })
public class TestJms {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Autowired
    private Destination destination;

    private DataFactory dataFactory = new DataFactory();

    private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Test
    public void testSend() {
        int min = dataFactory.getNumberBetween(5,10);
        int max = dataFactory.getNumberBetween(10, 15);

//        for (int i = 0; i < 100; i++) {
        for (int i = 0; i < 10; i++) {
            Map<String, String> map = new HashMap();
            map.put("info", dataFactory.getRandomText(min, max));
            map.put("time", dateFormat.format(new Date()));

            String jsonStr = JSON.toJSONString(map);
            jmsTemplate.convertAndSend(jsonStr);
        }
    }

    @Test
    public void testReceive(){
//        Message msg = jmsTemplate.receive(destination);
//        System.out.println("msg:"+msg);

        Object obj = jmsTemplate.receiveAndConvert();
        System.out.println("obj:"+obj);
        if(obj instanceof Map){
            Map map = (Map) obj;
            System.out.println(map.get("key1"));
            System.out.println(map.get("key2"));
        }
    }

}
