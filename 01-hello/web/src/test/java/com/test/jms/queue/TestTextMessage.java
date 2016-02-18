package com.test.jms.queue;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * User: 胡荆陵
 * Desc: 用于测试 JSM 生产者和消费者
 * Date: 2012-12-12
 */
public class TestTextMessage {

    private String user = ActiveMQConnection.DEFAULT_USER;
    private String password = ActiveMQConnection.DEFAULT_PASSWORD;
    private String url = ActiveMQConnection.DEFAULT_BROKER_URL;
//    private String url = "tcp://localhost:61617";
    private boolean transacted = true;
    private String subject = "helloQueue";

    @Test
    public void testProducer(){
        Connection connection = null;
        try {
            //连接工厂，用于产生连接
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(user, password, url);
            //创建连接
            connection = connectionFactory.createConnection();
            connection.start();
            //创建会话
            Session session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
            //创建消息队列
            Destination destination = session.createQueue(subject);
            //创建消息生产者
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
//            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            //消息
            DataFactory dataFactory = new DataFactory();
            for (int i = 1; i <= 10; i++) {
                String str = "[message]" + dataFactory.getRandomText(5,30);
                TextMessage message = session.createTextMessage(str);
                producer.send(message);
                if(transacted){
                    session.commit();
                }
                System.out.println("发送消息 " + i + ":" + message.getText());
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
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(transacted, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(subject);

            MessageProducer replyProducer = session.createProducer(null);
            replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            MessageConsumer consumer = session.createConsumer(destination);

            int i = 1;
            Message message = null;
            while ((message = consumer.receive(1000))!=null){
                TextMessage textMessage = (TextMessage) message;
                String str = textMessage.getText();
                System.out.println("接收消息 "+i+":"+str);
                session.commit();
                i++;
            }
            consumer.close();
            session.close();
            connection.close();
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
