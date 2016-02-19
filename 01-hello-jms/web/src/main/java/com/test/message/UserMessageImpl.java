package com.test.message;

import com.test.dao.UserDao;
import com.test.dao.UserDaoImpl;
import com.test.entity.User;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * User: 胡荆陵
 * Date: 12-12-13
 * User 消息接口实现类
 */
public class UserMessageImpl implements UserMessage{

    private static final String SUBJECT = "USER.QUEUE";
    private static final String JMS_USER = "user";
    private static final String JMS_PASS = "user";
//    private static final String JMS_URL = "tcp://localhost:61617";
    private static final String JMS_URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    public void send(User user) {
        Connection connection = null;
        try {
            //连接工厂，用于产生连接
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(JMS_USER, JMS_PASS, JMS_URL);
            //创建连接
            connection = connectionFactory.createConnection();
            connection.start();
            //创建会话
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //创建消息队列
            Destination destination = session.createQueue(SUBJECT);
            //创建消息生产者
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.PERSISTENT);
            //创建 ObjectMessage
            ObjectMessage objectMessage = session.createObjectMessage();
            objectMessage.setObject(user);
            producer.send(objectMessage);
            session.commit();
            System.out.println("发送消息: " + user.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void processUserQueue() {
        try {
            ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(JMS_USER, JMS_PASS, JMS_URL);
            Connection connection = connectionFactory.createConnection();
            connection.start();

            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            Destination destination = session.createQueue(SUBJECT);

            MessageProducer replyProducer = session.createProducer(null);
            replyProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            MessageConsumer consumer = session.createConsumer(destination);

            int i = 1;
            Message message = null;
            while ((message = consumer.receive(1000))!=null){
                ObjectMessage objectMessage = (ObjectMessage) message;
                User user = (User) objectMessage.getObject();
                System.out.println("接收并处理消息 "+i+":"+user.toString());
                UserDao userDao = new UserDaoImpl();
                userDao.save(user);
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
