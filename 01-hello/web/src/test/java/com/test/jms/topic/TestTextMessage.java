package com.test.jms.topic;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Test;

import javax.jms.*;
import java.util.Date;
import java.util.UUID;

/**
 * User: 胡荆陵
 * Desc: 用于测试 JSM 生产者和消费者
 * Date: 2012-12-12
 */
public class TestTextMessage{

    private String user = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
//    private String url = "tcp://localhost:61617";
    private boolean transacted = true;
    private String subject = "hello.topic";

    @Test
    public void testProducer(){
        TopicConnection connection = null;
        try {
            //连接工厂，用于产生连接
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            System.out.println("user:"+user);
            System.out.println("password:"+password);
            System.out.println("url:"+url);
            //创建连接
            connection = connectionFactory.createTopicConnection();
            connection.start();

            //创建会话
            TopicSession pubSession = connection.createTopicSession(transacted, Session.AUTO_ACKNOWLEDGE);
            //创建消息主题
            Topic topic = pubSession.createTopic(subject);
            //创建消息生产者
            TopicPublisher publisher = pubSession.createPublisher(topic);
            publisher.setDeliveryMode(DeliveryMode.PERSISTENT);
            //循环发送消息
            DataFactory dataFactory = new DataFactory();
            for (int i = 1; i <= 1000; i++) {
                String str = "[message]" + dataFactory.getRandomText(10,20);
                TextMessage message = pubSession.createTextMessage(str);
                publisher.publish(message);
                if(transacted){
                    pubSession.commit();
                }
                System.out.println("发送消息 " + i + ":" + message.getText()+new Date());
                try {
                    Thread.sleep(3000);
                } catch (Exception e) {
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test
    public void testConsumer(){
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            TopicConnection connection = connectionFactory.createTopicConnection();
            connection.start();

            System.out.println("user:"+user);
            System.out.println("password:"+password);
            System.out.println("url:"+url);

            TopicSession subSession = connection.createTopicSession(transacted, Session.AUTO_ACKNOWLEDGE);
            Topic topic = subSession.createTopic(subject);

            //创建订阅者
            TopicSubscriber subscriber = subSession.createSubscriber(topic);

            //设置 JMS 消息监听器
            subscriber.setMessageListener(new MessageListener() {
                public void onMessage(Message message) {
                    try {
                        TextMessage textMessage = (TextMessage) message;
                        String text = textMessage.getText();
                        System.out.println("接收到消息: "+text+ new Date());
                    } catch (JMSException jmse) {
                        jmse.printStackTrace();
                    }
                }
            });

            while (true){
                try {
                    Thread.sleep(2000);
                } catch (Exception e) {
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
